package org.apache.commons.compress.archivers.zip;
import org.junit.Before;
import org.junit.Test;
import java.io.File;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.zip.ZipException;
import static org.apache.commons.compress.AbstractTestCase.getFile;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class X7875_NewUnix_LLMTest {
    @Test
    public void test0() {
        assertEquals(HEADER_ID, xf.getHeaderId());
    }
    @Test
    public void test1() throws Exception {
        File archive = getFile("COMPRESS-211_uid_gid_zip_test.zip");
        ZipFile zf = null;

        try {
            zf = new ZipFile(archive);
            Enumeration<ZipArchiveEntry> en = zf.getEntries();

            while (en.hasMoreElements()) {
                ZipArchiveEntry zae = en.nextElement();
                String name = zae.getName();
                X7875_NewUnix xf = (X7875_NewUnix) zae.getExtraField(X7875);

                // The directory entry in the test zip file is uid/gid 1000.
                long expected = 1000;
                if (name.contains("uid555_gid555")) {
                    expected = 555;
                } else if (name.contains("uid5555_gid5555")) {
                    expected = 5555;
                } else if (name.contains("uid55555_gid55555")) {
                    expected = 55555;
                } else if (name.contains("uid555555_gid555555")) {
                    expected = 555555;
                } else if (name.contains("min_unix")) {
                    expected = 0;
                } else if (name.contains("max_unix")) {
                    // 2^32-2 was the biggest UID/GID I could create on my linux!
                    // (December 2012, linux kernel 3.4)
                    expected = 0x100000000L - 2;
                }
                
                // Change the input for xf.getUID() and xf.getGID()
                assertEquals(expected + 1, xf.getUID());
                assertEquals(expected + 1, xf.getGID());
            }
        } finally {
            if (zf != null) {
                zf.close();
            }
        }
    }
    @Test
    public void test2() throws ZipException {

        // Version=1, Len=0, Len=0.
        final byte[] ZERO_LEN = {1, 0, 0};

        // Version=1, Len=1, zero, Len=1, zero.
        final byte[] ZERO_UID_GID = {1, 1, 0, 1, 0};

        // Version=1, Len=1, one, Len=1, one
        final byte[] ONE_UID_GID = {1, 1, 1, 1, 1};

        // Version=1, Len=2, one thousand, Len=2, one thousand
        final byte[] ONE_THOUSAND_UID_GID = {1, 2, -24, 3, 2, -24, 3};

        // (2^32 - 2).   I guess they avoid (2^32 - 1) since it's identical to -1 in
        // two's complement, and -1 often has a special meaning.
        final byte[] UNIX_MAX_UID_GID = {1, 4, -2, -1, -1, -1, 4, -2, -1, -1, -1};

        // Version=1, Len=5, 2^32, Len=5, 2^32 + 1
        // Esoteric test:  can we handle 40 bit numbers?
        final byte[] LENGTH_5 = {1, 5, 0, 0, 0, 0, 1, 5, 1, 0, 0, 0, 1};

        // Version=1, Len=8, 2^63 - 2, Len=8, 2^63 - 1
        // Esoteric test:  can we handle 64 bit numbers?
        final byte[] LENGTH_8 = {1, 8, -2, -1, -1, -1, -1, -1, -1, 127, 8, -1, -1, -1, -1, -1, -1, -1, 127};

        final long TWO_TO_32 = 0x100000000L;
        final long MAX = TWO_TO_32 - 2;

        parseReparse(0, 0, ZERO_LEN, 0, 0);
        parseReparse(0, 0, ZERO_UID_GID, 0, 0);
        parseReparse(1, 1, ONE_UID_GID, 1, 1);
        parseReparse(1000, 1000, ONE_THOUSAND_UID_GID, 1000, 1000);
        parseReparse(MAX, MAX, UNIX_MAX_UID_GID, MAX, MAX);
        parseReparse(-2, -2, UNIX_MAX_UID_GID, MAX, MAX);
        parseReparse(TWO_TO_32, TWO_TO_32 + 1, LENGTH_5, TWO_TO_32, TWO_TO_32 + 1);
        parseReparse(Long.MAX_VALUE - 1, Long.MAX_VALUE, LENGTH_8, Long.MAX_VALUE - 1, Long.MAX_VALUE);
        
        // Additional test cases
        parseReparse(Long.MIN_VALUE, Long.MIN_VALUE + 2, LENGTH_8, Long.MIN_VALUE, Long.MIN_VALUE + 2);

        // We never emit this, but we should be able to parse it:
        final byte[] SPURIOUS_ZEROES_1 = {1, 4, -1, 0, 0, 0, 4, -128, 0, 0, 0};
        final byte[] EXPECTED_1 = {1, 1, -1, 1, -128};
        xf.parseFromLocalFileData(SPURIOUS_ZEROES_1, 0, SPURIOUS_ZEROES_1.length);

        assertEquals(255, xf.getUID());
        assertEquals(128, xf.getGID());
        assertTrue(Arrays.equals(EXPECTED_1, xf.getLocalFileDataData()));

        final byte[] SPURIOUS_ZEROES_2 = {1, 4, -1, -1, 0, 0, 4, 1, 2, 0, 0};
        final byte[] EXPECTED_2 = {1, 2, -1, -1, 2, 1, 2};
        xf.parseFromLocalFileData(SPURIOUS_ZEROES_2, 0, SPURIOUS_ZEROES_2.length);

        assertEquals(65535, xf.getUID());
        assertEquals(513, xf.getGID());
        assertTrue(Arrays.equals(EXPECTED_2, xf.getLocalFileDataData()));
    }
    @Test
    public void test3() throws ZipException {
        // Version=1, Len=1, -1, Len=1, -1
        final byte[] NEGATIVE_UID_GID = {1, 1, -1, 1, -1};
        
        parseReparse(-1, -1, NEGATIVE_UID_GID, -1, -1);
    }
    @Test
    public void test4() throws ZipException {
        // Version=1, Len=5, 2^32 + 1, Len=5, 2^32 + 2
        final byte[] LARGE_POSITIVE_UID_GID = {1, 5, 1, 0, 0, 0, 1, 5, 2, 0, 0, 0};
        
        parseReparse(4294967297L, 4294967298L, LARGE_POSITIVE_UID_GID, 4294967297L, 4294967298L);
    }
    @Test
    public void test5() throws ZipException {
        // Version=1, Len=1, zero, Len=1, zero
        final byte[] ZERO_UID_GID = {1, 1, 0, 1, 0};
        
        parseReparse(0, 0, ZERO_UID_GID, 0, 0);
    }
    @Test
    public void test6() throws ZipException {
        // Existing test cases...

        // Test cases to kill more mutants

        // Version=1, Len=1, negative one, Len=1, negative one
        final byte[] NEGATIVE_ONE_UID_GID = {1, 1, -1, 1, -1};
        parseReparse(-1, -1, NEGATIVE_ONE_UID_GID, -1, -1);

        // Version=1, Len=2, negative one thousand, Len=2, negative one thousand
        final byte[] NEGATIVE_ONE_THOUSAND_UID_GID = {1, 2, 24, 3, 2, 24, 3};
        parseReparse(-1000, -1000, NEGATIVE_ONE_THOUSAND_UID_GID, -1000, -1000);

        // Version=1, Len=2, two^31-2, Len=2, two^31-1
        final byte[] LENGTH_2 = {1, 2, -2, -1, 127, 2, -1, -1, 127};
        parseReparse(Integer.MAX_VALUE - 2, Integer.MAX_VALUE - 1, LENGTH_2, Integer.MAX_VALUE - 2, Integer.MAX_VALUE - 1);

        // Version=1, Len=3, 2^24, Len=3, 2^24 + 1
        final byte[] NEGATIVE_LENGTH_3 = {1, 3, 0, -128, 0, 1, 3, 1, -128, 0};
        parseReparse(16777216, 16777217, NEGATIVE_LENGTH_3, 16777216, 16777217);
    }
    @Test
    public void test7() throws Exception {
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertTrue(xf.equals(o));
        xf.setUID(12345);
        assertFalse(xf.equals(o));
    }
    @Test
    public void test8() throws ZipException {
        // Length = -1, Len = 0, Len = 0
        final byte[] LENGTH_NEGATIVE = {-1, 0, 0};

        parseReparse(-1, 0, LENGTH_NEGATIVE, 0, 0);

        // Killing Mutant1 - Changing input value
    }
    @Test
    public void test9() throws ZipException {
        // Length = -1, Len = 1, Len = 1
        final byte[] LENGTH_NEGATIVE = {-1, 1, 1};

        parseReparse(-1, 1, LENGTH_NEGATIVE, 1, 1);

        // Killing Mutant2 - Changing input value
    }
    @Test
    public void test10() throws ZipException {
        // Length = -1, Len = 2, Len = 2
        final byte[] LENGTH_NEGATIVE = {-1, 2, 2};

        parseReparse(-1, 2, LENGTH_NEGATIVE, 2, 2);

        // Killing Mutant3 - Changing input value
    }
    @Test
    public void test11() throws ZipException {
        // Length = 0, Len = -1, Len = 0
        final byte[] LENGTH_NEGATIVE = {0, -1, 0};

        parseReparse(0, -1, LENGTH_NEGATIVE, 0, 0);

        // Killing Mutant4 - Changing input value
    }
    @Test
    public void test12() throws ZipException {
        // Length = 0, Len = 0, Len = -1
        final byte[] LENGTH_NEGATIVE = {0, 0, -1};

        parseReparse(0, 0, LENGTH_NEGATIVE, 0, 0);

        // Killing Mutant5 - Changing input value
    }
    @Test
    public void test13() throws ZipException {
        // Length = 1, Len = -1, Len = 1
        final byte[] LENGTH_NEGATIVE = {1, -1, 1};

        parseReparse(1, -1, LENGTH_NEGATIVE, 1, 1);

        // Killing Mutant6 - Changing input value
    }
    @Test
    public void test14() throws ZipException {
        // Length = 1, Len = 1, Len = -1
        final byte[] LENGTH_NEGATIVE = {1, 1, -1};

        parseReparse(1, 1, LENGTH_NEGATIVE, 1, 1);

        // Killing Mutant7 - Changing input value
    }
    @Test
    public void test15() throws ZipException {
        // Length = 2, Len = -1, Len = 2
        final byte[] LENGTH_NEGATIVE = {2, -1, 2};

        parseReparse(2, -1, LENGTH_NEGATIVE, 2, 2);

        // Killing Mutant8 - Changing input value
    }
    @Test
    public void test16() {
        // The uid size is zero
        zf.getLocalFileDataLength().getUidSize();
    }
    @Test
    public void test17() {
        // The gid size is zero
        zf.getLocalFileDataLength().getGidSize();
    }
    @Test
    public void test18() {
        // The uid size is negative
        zf.getLocalFileDataLength().setUidSize(-1);
    }
    @Test
    public void test19() {
        // The gid size is negative
        zf.getLocalFileDataLength().setGidSize(-1);
    }
    @Test
    public void test20() {
        // The uid size is Integer.MAX_VALUE
        zf.getLocalFileDataLength().setUidSize(Integer.MAX_VALUE);
    }
    @Test
    public void test21() {
        // The gid size is Integer.MAX_VALUE
        zf.getLocalFileDataLength().setGidSize(Integer.MAX_VALUE);
    }
    @Test
    public void test22() {
        // The uid size is a very big number
        long bigUidSize = 100000000000L;
        zf.getLocalFileDataLength().setUidSize(bigUidSize);
    }
    @Test
    public void test23() {
        // The gid size is a very big number
        long bigGidSize = 100000000000L;
        zf.getLocalFileDataLength().setGidSize(bigGidSize);
    }
@Test
public void test24() throws ZipException {

    // Regression test case to kill mutants
    // Version=-1, Len=0, Len=0.
    final byte[] NEGATIVE_VERSION_ZERO_LEN = {-1, 0, 0};
    parseReparse(-1, 0, NEGATIVE_VERSION_ZERO_LEN, 0, 0);

    // Regression test case to kill mutants
    // Version=1, Len=0, Len=0.
    final byte[] ZERO_LEN = {1, 0, 0};
    parseReparse(1, 0, ZERO_LEN, 0, 0);

    // Regression test case to kill mutants
    // Version=1, Len=1, zero, Len=1, zero.
    final byte[] ZERO_UID_GID = {1, 1, 0, 1, 0};
    parseReparse(1, 1, ZERO_UID_GID, 1, 0);

    // Regression test case to kill mutants
    // Version=1, Len=1, one, Len=1, one
    final byte[] ONE_UID_GID = {1, 1, 1, 1, 1};
    parseReparse(1, 1, ONE_UID_GID, 1, 1);

    // Regression test case to kill mutants
    // Version=1, Len=2, one thousand, Len=2, one thousand
    final byte[] ONE_THOUSAND_UID_GID = {1, 2, -24, 3, 2, -24, 3};
    parseReparse(1000, 1000, ONE_THOUSAND_UID_GID, 1000, 1000);

    // Regression test case to kill mutants
    // (2^32 - 2).   I guess they avoid (2^32 - 1) since it's identical to -1 in
    // two's complement, and -1 often has a special meaning.
    final byte[] UNIX_MAX_UID_GID = {1, 4, -2, -1, -1, -1, 4, -2, -1, -1, -1};
    parseReparse(-2, -2, UNIX_MAX_UID_GID, -1, -1);

    // Regression test case to kill mutants
    // Version=1, Len=5, 2^32, Len=5, 2^32 + 1
    // Esoteric test:  can we handle 40 bit numbers?
    final byte[] LENGTH_5 = {1, 5, 0, 0, 0, 0, 1, 5, 1, 0, 0, 0, 1};
    parseReparse(1099511627776L, 1099511627777L, LENGTH_5, 1099511627776L, 1099511627777L);

    // Regression test case to kill mutants
    // Version=1, Len=8, 2^63 - 2, Len=8, 2^63 - 1
    // Esoteric test:  can we handle 64 bit numbers?
    final byte[] LENGTH_8 = {1, 8, -2, -1, -1, -1, -1, -1, -1, 127, 8, -1, -1, -1, -1, -1, -1, -1, 127};
    parseReparse(Long.MAX_VALUE - 1, Long.MAX_VALUE, LENGTH_8, Long.MAX_VALUE - 1, Long.MAX_VALUE);

    // We never emit this, but we should be able to parse it:
    final byte[] SPURIOUS_ZEROES_1 = {1, 4, -1, 0, 0, 0, 4, -128, 0, 0, 0};
    final byte[] EXPECTED_1 = {1, 1, -1, 1, -128};
    xf.parseFromLocalFileData(SPURIOUS_ZEROES_1, 0, SPURIOUS_ZEROES_1.length);

    assertEquals(255, xf.getUID());
    assertEquals(128, xf.getGID());
    assertTrue(Arrays.equals(EXPECTED_1, xf.getLocalFileDataData()));

    final byte[] SPURIOUS_ZEROES_2 = {1, 4, -1, -1, 0, 0, 4, 1, 2, 0, 0};
    final byte[] EXPECTED_2 = {1, 2, -1, -1, 2, 1, 2};
    xf.parseFromLocalFileData(SPURIOUS_ZEROES_2, 0, SPURIOUS_ZEROES_2.length);

    assertEquals(65535, xf.getUID());
    assertEquals(513, xf.getGID());
    assertTrue(Arrays.equals(EXPECTED_2, xf.getLocalFileDataData()));
}
    @Test
    public void test25() throws Exception {
        File archive = getFile("COMPRESS-211_uid_gid_zip_test.zip");
        ZipFile zf = null;

        try {
            zf = new ZipFile(archive);
            Enumeration<ZipArchiveEntry> en = zf.getEntries();

            // We expect EVERY entry of this zip file (dir & file) to
            // contain extra field 0x7875.
            while (en.hasMoreElements()) {

                ZipArchiveEntry zae = en.nextElement();
                String name = zae.getName();
                X7875_NewUnix xf = (X7875_NewUnix) zae.getExtraField(X7875);

                // The directory entry in the test zip file is uid/gid 1000.
                long expected = 1000;
                if (name.contains("uid555_gid555")) {
                    expected = 555;
                } else if (name.contains("uid5555_gid5555")) {
                    expected = 5555;
                } else if (name.contains("uid55555_gid55555")) {
                    expected = 55555;
                } else if (name.contains("uid555555_gid555555")) {
                    expected = 555555;
                } else if (name.contains("min_unix")) {
                    expected = 0;
                } else if (name.contains("max_unix")) {
                    // 2^32-2 was the biggest UID/GID I could create on my linux!
                    // (December 2012, linux kernel 3.4)
                    expected = 0x100000000L - 2;
                }
                assertEquals(expected, xf.getUID());
                assertEquals(expected, xf.getGID());
            }
        } finally {
            if (zf != null) {
                zf.close();
            }
        }
    }
    @Test
    public void test26() throws ZipException {

        // Version=1, Len=0, Len=0.
        final byte[] ZERO_LEN = {1, 0, 0};

        // Version=1, Len=1, zero, Len=1, zero.
        final byte[] ZERO_UID_GID = {1, 1, 0, 1, 0};

        // Version=1, Len=1, one, Len=1, one
        final byte[] ONE_UID_GID = {1, 1, 1, 1, 1};

        // Version=1, Len=2, one thousand, Len=2, one thousand
        final byte[] ONE_THOUSAND_UID_GID = {1, 2, -24, 3, 2, -24, 3};

        // (2^32 - 2).   I guess they avoid (2^32 - 1) since it's identical to -1 in
        // two's complement, and -1 often has a special meaning.
        final byte[] UNIX_MAX_UID_GID = {1, 4, -2, -1, -1, -1, 4, -2, -1, -1, -1};

        // Version=1, Len=5, 2^32, Len=5, 2^32 + 1
        // Esoteric test:  can we handle 40 bit numbers?
        final byte[] LENGTH_5 = {1, 5, 0, 0, 0, 0, 1, 5, 1, 0, 0, 0, 1};

        // Version=1, Len=8, 2^63 - 2, Len=8, 2^63 - 1
        // Esoteric test:  can we handle 64 bit numbers?
        final byte[] LENGTH_8 = {1, 8, -2, -1, -1, -1, -1, -1, -1, 127, 8, -1, -1, -1, -1, -1, -1, -1, 127};

        final long TWO_TO_32 = 0x100000000L;
        final long MAX = TWO_TO_32 - 2;

        parseReparse(0, 0, ZERO_LEN, 0, 0);
        parseReparse(0, 0, ZERO_UID_GID, 0, 0);
        parseReparse(1, 1, ONE_UID_GID, 1, 1);
        parseReparse(1000, 1000, ONE_THOUSAND_UID_GID, 1000, 1000);
        parseReparse(MAX, MAX, UNIX_MAX_UID_GID, MAX, MAX);
        parseReparse(-2, -2, UNIX_MAX_UID_GID, MAX, MAX);
        parseReparse(TWO_TO_32, TWO_TO_32 + 1, LENGTH_5, TWO_TO_32, TWO_TO_32 + 1);
        parseReparse(Long.MAX_VALUE - 1, Long.MAX_VALUE, LENGTH_8, Long.MAX_VALUE - 1, Long.MAX_VALUE);

        // We never emit this, but we should be able to parse it:
        final byte[] SPURIOUS_ZEROES_1 = {1, 4, -1, 0, 0, 0, 4, -128, 0, 0, 0};
        final byte[] EXPECTED_1 = {1, 1, -1, 1, -128};
        xf.parseFromLocalFileData(SPURIOUS_ZEROES_1, 0, SPURIOUS_ZEROES_1.length);

        assertEquals(255, xf.getUID());
        assertEquals(128, xf.getGID());
        assertTrue(Arrays.equals(EXPECTED_1, xf.getLocalFileDataData()));

        final byte[] SPURIOUS_ZEROES_2 = {1, 4, -1, -1, 0, 0, 4, 1, 2, 0, 0};
        final byte[] EXPECTED_2 = {1, 2, -1, -1, 2, 1, 2};
        xf.parseFromLocalFileData(SPURIOUS_ZEROES_2, 0, SPURIOUS_ZEROES_2.length);

        assertEquals(65535, xf.getUID());
        assertEquals(513, xf.getGID());
        assertTrue(Arrays.equals(EXPECTED_2, xf.getLocalFileDataData()));
    }
    @Test
    public void test27() throws Exception {
        byte[] uidBytes = BigInteger.valueOf(-555).toByteArray();
        byte[] gidBytes = BigInteger.valueOf(-555).toByteArray();
        uidBytes = trimLeadingZeroesForceMinLength(uidBytes);
        gidBytes = trimLeadingZeroesForceMinLength(gidBytes);
        byte[] data = new byte[3 + uidBytes.length + gidBytes.length];
        reverse(uidBytes);
        reverse(gidBytes);
        int pos = 0;
        data[pos++] = unsignedIntToSignedByte(version);
        data[pos++] = unsignedIntToSignedByte(uidBytes.length);
        System.arraycopy(uidBytes, 0, data, pos, uidBytes.length);
        pos += uidBytes.length;
        data[pos++] = unsignedIntToSignedByte(gidBytes.length);
        System.arraycopy(gidBytes, 0, data, pos, gidBytes.length);

        long expected = -555;
        assertEquals(expected, xf.getUID());
        assertEquals(expected, xf.getGID());
    }
    @Test
    public void test28() throws Exception {
        byte[] uidBytes = BigInteger.valueOf(1000000000).toByteArray();
        byte[] gidBytes = BigInteger.valueOf(1000000000).toByteArray();
        uidBytes = trimLeadingZeroesForceMinLength(uidBytes);
        gidBytes = trimLeadingZeroesForceMinLength(gidBytes);
        byte[] data = new byte[3 + uidBytes.length + gidBytes.length];
        reverse(uidBytes);
        reverse(gidBytes);
        int pos = 0;
        data[pos++] = unsignedIntToSignedByte(version);
        data[pos++] = unsignedIntToSignedByte(uidBytes.length);
        System.arraycopy(uidBytes, 0, data, pos, uidBytes.length);
        pos += uidBytes.length;
        data[pos++] = unsignedIntToSignedByte(gidBytes.length);
        System.arraycopy(gidBytes, 0, data, pos, gidBytes.length);

        long expected = 1000000000;
        assertEquals(expected, xf.getUID());
        assertEquals(expected, xf.getGID());
    }
    @Test
    public void test29() throws Exception {
        byte[] uidBytes = BigInteger.ZERO.toByteArray();
        byte[] gidBytes = BigInteger.ZERO.toByteArray();
        uidBytes = trimLeadingZeroesForceMinLength(uidBytes);
        gidBytes = trimLeadingZeroesForceMinLength(gidBytes);
        byte[] data = new byte[3 + uidBytes.length + gidBytes.length];
        reverse(uidBytes);
        reverse(gidBytes);
        int pos = 0;
        data[pos++] = unsignedIntToSignedByte(version);
        data[pos++] = unsignedIntToSignedByte(uidBytes.length);
        System.arraycopy(uidBytes, 0, data, pos, uidBytes.length);
        pos += uidBytes.length;
        data[pos++] = unsignedIntToSignedByte(gidBytes.length);
        System.arraycopy(gidBytes, 0, data, pos, gidBytes.length);

        long expected = 0;
        assertEquals(expected, xf.getUID());
        assertEquals(expected, xf.getGID());
    }
public void test30() {
    byte[] result = getCentralDirectoryData();
    assertNotNull(result);
    assertEquals(0, result.length);
}
public void test31() {
    byte[] result = getCentralDirectoryData();
    assertNotNull(result);
    assertNotEquals(0, result.length);
}
    @Test
    public void test32() throws Exception {
        File archive = getFile("COMPRESS-211_uid_gid_zip_test.zip");
        ZipFile zf = null;

        try {
            zf = new ZipFile(archive);
            Enumeration<ZipArchiveEntry> en = zf.getEntries();

            // We expect EVERY entry of this zip file (dir & file) to
            // contain extra field 0x7875.
            while (en.hasMoreElements()) {

                ZipArchiveEntry zae = en.nextElement();
                String name = zae.getName();
                X7875_NewUnix xf = (X7875_NewUnix) zae.getExtraField(X7875);

                // The directory entry in the test zip file is uid/gid 1000.
                long expected = 1000;
                if (name.contains("uid555_gid555")) {
                    expected = 555;
                } else if (name.contains("uid5555_gid5555")) {
                    expected = 5555;
                } else if (name.contains("uid55555_gid55555")) {
                    expected = 55555;
                } else if (name.contains("uid555555_gid555555")) {
                    expected = 555555;
                } else if (name.contains("min_unix")) {
                    expected = 0;
                } else if (name.contains("max_unix")) {
                    // 2^32-2 was the biggest UID/GID I could create on my linux!
                    // (December 2012, linux kernel 3.4)
                    expected = 0x100000000L - 2;
                }
                assertEquals(expected, xf.getUID());
                assertEquals(expected, xf.getGID());
            }
        } finally {
            if (zf != null) {
                zf.close();
            }
        }
    }
    @Test
    public void test33() throws ZipException {

        // Version=1, Len=0, Len=0.
        final byte[] ZERO_LEN = {1, 0, 0};

        // Version=1, Len=1, zero, Len=1, zero.
        final byte[] ZERO_UID_GID = {1, 1, 0, 1, 0};

        // Version=1, Len=1, one, Len=1, one
        final byte[] ONE_UID_GID = {1, 1, 1, 1, 1};

        // Version=1, Len=2, one thousand, Len=2, one thousand
        final byte[] ONE_THOUSAND_UID_GID = {1, 2, -24, 3, 2, -24, 3};

        // (2^32 - 2).   I guess they avoid (2^32 - 1) since it's identical to -1 in
        // two's complement, and -1 often has a special meaning.
        final byte[] UNIX_MAX_UID_GID = {1, 4, -2, -1, -1, -1, 4, -2, -1, -1, -1};

        // Version=1, Len=5, 2^32, Len=5, 2^32 + 1
        // Esoteric test:  can we handle 40 bit numbers?
        final byte[] LENGTH_5 = {1, 5, 0, 0, 0, 0, 1, 5, 1, 0, 0, 0, 1};

        // Version=1, Len=8, 2^63 - 2, Len=8, 2^63 - 1
        // Esoteric test:  can we handle 64 bit numbers?
        final byte[] LENGTH_8 = {1, 8, -2, -1, -1, -1, -1, -1, -1, 127, 8, -1, -1, -1, -1, -1, -1, -1, 127};

        final long TWO_TO_32 = 0x100000000L;
        final long MAX = TWO_TO_32 - 2;

        parseReparse(0, 0, ZERO_LEN, 0, 0);
        parseReparse(0, 0, ZERO_UID_GID, 0, 0);
        parseReparse(1, 1, ONE_UID_GID, 1, 1);
        parseReparse(1000, 1000, ONE_THOUSAND_UID_GID, 1000, 1000);
        parseReparse(MAX, MAX, UNIX_MAX_UID_GID, MAX, MAX);
        parseReparse(-2, -2, UNIX_MAX_UID_GID, MAX, MAX);
        parseReparse(TWO_TO_32, TWO_TO_32 + 1, LENGTH_5, TWO_TO_32, TWO_TO_32 + 1);
        parseReparse(Long.MAX_VALUE - 1, Long.MAX_VALUE, LENGTH_8, Long.MAX_VALUE - 1, Long.MAX_VALUE);

        // We never emit this, but we should be able to parse it:
        final byte[] SPURIOUS_ZEROES_1 = {1, 4, -1, 0, 0, 0, 4, -128, 0, 0, 0};
        final byte[] EXPECTED_1 = {1, 1, -1, 1, -128};
        xf.parseFromLocalFileData(SPURIOUS_ZEROES_1, 0, SPURIOUS_ZEROES_1.length);

        assertEquals(255, xf.getUID());
        assertEquals(128, xf.getGID());
        assertTrue(Arrays.equals(EXPECTED_1, xf.getLocalFileDataData()));

        final byte[] SPURIOUS_ZEROES_2 = {1, 4, -1, -1, 0, 0, 4, 1, 2, 0, 0};
        final byte[] EXPECTED_2 = {1, 2, -1, -1, 2, 1, 2};
        xf.parseFromLocalFileData(SPURIOUS_ZEROES_2, 0, SPURIOUS_ZEROES_2.length);

        assertEquals(65535, xf.getUID());
        assertEquals(513, xf.getGID());
        assertTrue(Arrays.equals(EXPECTED_2, xf.getLocalFileDataData()));

        // Additional test cases for regression testing
        final byte[] ZERO_LEN_NEW = {1, 0, 1}; // Change the length from 0 to 1
        parseReparse(0, 0, ZERO_LEN_NEW, 0, 0);

        final byte[] ONE_UID_GID_NEW = {1, 1, 2, 1, 2}; // Change the UID and GID from 1 to 2
        parseReparse(2, 2, ONE_UID_GID_NEW, 2, 2);

        // Change the UID and GID from 1000 to 1001
        final byte[] ONE_THOUSAND_UID_GID_NEW = {1, 2, -24, 3, 2, -24, 4};
        parseReparse(1001, 1001, ONE_THOUSAND_UID_GID_NEW, 1001, 1001);
    }
@Test
public void test34() throws Exception {
    byte[] buffer = {};
    int offset = 0;
    int length = 0;
    
    try {
        parseFromCentralDirectoryData(buffer, offset, length);
        fail("Expected ZipException to be thrown");
    } catch (ZipException e) {
        assertEquals("Invalid buffer size", e.getMessage());
    }
}
@Test
public void test35() throws Exception {
    byte[] buffer = {1, 2, 3};
    int offset = -1;
    int length = 3;
    
    try {
        parseFromCentralDirectoryData(buffer, offset, length);
        fail("Expected ZipException to be thrown");
    } catch (ZipException e) {
        assertEquals("Invalid offset value", e.getMessage());
    }
}
@Test
public void test36() throws Exception {
    byte[] buffer = {1, 2, 3};
    int offset = 4;
    int length = 3;
    
    try {
        parseFromCentralDirectoryData(buffer, offset, length);
        fail("Expected ZipException to be thrown");
    } catch (ZipException e) {
        assertEquals("Invalid offset value", e.getMessage());
    }
}
@Test
public void test37() throws Exception {
    byte[] buffer = {1, 2, 3};
    int offset = 0;
    int length = -1;
    
    try {
        parseFromCentralDirectoryData(buffer, offset, length);
        fail("Expected ZipException to be thrown");
    } catch (ZipException e) {
        assertEquals("Invalid length value", e.getMessage());
    }
}
@Test
public void test38() throws Exception {
    byte[] buffer = {1, 2, 3};
    int offset = 0;
    int length = 4;
    
    try {
        parseFromCentralDirectoryData(buffer, offset, length);
        fail("Expected ZipException to be thrown");
    } catch (ZipException e) {
        assertEquals("Invalid length value", e.getMessage());
    }
}
    @Test
    public void test39() {
        assertEquals(X7875, xf.getHeaderId());
    }
    @Test
    public void test40() throws Exception {
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertTrue(xf.equals(o));
        xf.setUID(12345);
        assertFalse(xf.equals(o));
    }
    @Test
    public void test41() {
        final byte[] NULL = null;
        final byte[] EMPTY = new byte[0];
        final byte[] ONE_ZERO = {0};
        final byte[] TWO_ZEROES = {0, 0};
        final byte[] FOUR_ZEROES = {0, 0, 0, 0};
        final byte[] SEQUENCE = {1, 2, 3};
        final byte[] SEQUENCE_LEADING_ZERO = {0, 1, 2, 3};
        final byte[] SEQUENCE_LEADING_ZEROES = {0, 0, 0, 0, 0, 0, 0, 1, 2, 3};
        final byte[] TRAILING_ZERO = {1, 2, 3, 0};
        final byte[] PADDING_ZERO = {0, 1, 2, 3, 0};
        final byte[] SEQUENCE6 = {1, 2, 3, 4, 5, 6};
        final byte[] SEQUENCE6_LEADING_ZERO = {0, 1, 2, 3, 4, 5, 6};

        assertTrue(NULL == trimTest(NULL));
        assertTrue(Arrays.equals(ONE_ZERO, trimTest(EMPTY)));
        assertTrue(Arrays.equals(ONE_ZERO, trimTest(ONE_ZERO)));
        assertTrue(Arrays.equals(ONE_ZERO, trimTest(TWO_ZEROES)));
        assertTrue(Arrays.equals(ONE_ZERO, trimTest(FOUR_ZEROES)));
        assertTrue(Arrays.equals(SEQUENCE, trimTest(SEQUENCE)));
        assertTrue(Arrays.equals(SEQUENCE, trimTest(SEQUENCE_LEADING_ZERO)));
        assertTrue(Arrays.equals(SEQUENCE, trimTest(SEQUENCE_LEADING_ZEROES)));
        assertTrue(Arrays.equals(TRAILING_ZERO, trimTest(TRAILING_ZERO)));
        assertTrue(Arrays.equals(TRAILING_ZERO, trimTest(PADDING_ZERO)));
        assertTrue(Arrays.equals(SEQUENCE6, trimTest(SEQUENCE6)));
        assertTrue(Arrays.equals(SEQUENCE6, trimTest(SEQUENCE6_LEADING_ZERO)));
    }
    @Test
    public void test42() throws Exception {
        File archive = getFile("COMPRESS-211_uid_gid_zip_test.zip");
        ZipFile zf = null;

        try {
            zf = new ZipFile(archive);
            Enumeration<ZipArchiveEntry> en = zf.getEntries();

            // We expect EVERY entry of this zip file (dir & file) to
            // contain extra field 0x7875.
            while (en.hasMoreElements()) {

                ZipArchiveEntry zae = en.nextElement();
                String name = zae.getName();
                X7875_NewUnix xf = (X7875_NewUnix) zae.getExtraField(X7875);

                // The directory entry in the test zip file is uid/gid 1000.
                long expected = 1000;
                if (name.contains("uid555_gid555")) {
                    expected = 555;
                } else if (name.contains("uid5555_gid5555")) {
                    expected = 5555;
                } else if (name.contains("uid55555_gid55555")) {
                    expected = 55555;
                } else if (name.contains("uid555555_gid555555")) {
                    expected = 555555;
                } else if (name.contains("min_unix")) {
                    expected = 0;
                } else if (name.contains("max_unix")) {
                    // 2^32-2 was the biggest UID/GID I could create on my linux!
                    // (December 2012, linux kernel 3.4)
                    expected = 0x100000000L - 2;
                }
                assertEquals(expected, xf.getUID());
                assertEquals(expected, xf.getGID());
            }
        } finally {
            if (zf != null) {
                zf.close();
            }
        }
    }
    @Test
    public void test43() throws ZipException {

        // Version=1, Len=0, Len=0.
        final byte[] ZERO_LEN = {1, 0, 0};

        // Version=1, Len=1, zero, Len=1, zero.
        final byte[] ZERO_UID_GID = {1, 1, 0, 1, 0};

        // Version=1, Len=1, one, Len=1, one
        final byte[] ONE_UID_GID = {1, 1, 1, 1, 1};

        // Version=1, Len=2, one thousand, Len=2, one thousand
        final byte[] ONE_THOUSAND_UID_GID = {1, 2, -24, 3, 2, -24, 3};

        // (2^32 - 2).   I guess they avoid (2^32 - 1) since it's identical to -1 in
        // two's complement, and -1 often has a special meaning.
        final byte[] UNIX_MAX_UID_GID = {1, 4, -2, -1, -1, -1, 4, -2, -1, -1, -1};

        // Version=1, Len=5, 2^32, Len=5, 2^32 + 1
        // Esoteric test:  can we handle 40 bit numbers?
        final byte[] LENGTH_5 = {1, 5, 0, 0, 0, 0, 1, 5, 1, 0, 0, 0, 1};

        // Version=1, Len=8, 2^63 - 2, Len=8, 2^63 - 1
        // Esoteric test:  can we handle 64 bit numbers?
        final byte[] LENGTH_8 = {1, 8, -2, -1, -1, -1, -1, -1, -1, 127, 8, -1, -1, -1, -1, -1, -1, -1, 127};

        final long TWO_TO_32 = 0x100000000L;
        final long MAX = TWO_TO_32 - 2;

        parseReparse(0, 0, ZERO_LEN, 0, 0);
        parseReparse(0, 0, ZERO_UID_GID, 0, 0);
        parseReparse(1, 1, ONE_UID_GID, 1, 1);
        parseReparse(1000, 1000, ONE_THOUSAND_UID_GID, 1000, 1000);
        parseReparse(MAX, MAX, UNIX_MAX_UID_GID, MAX, MAX);
        parseReparse(-2, -2, UNIX_MAX_UID_GID, MAX, MAX);
        parseReparse(TWO_TO_32, TWO_TO_32 + 1, LENGTH_5, TWO_TO_32, TWO_TO_32 + 1);
        parseReparse(Long.MAX_VALUE - 1, Long.MAX_VALUE, LENGTH_8, Long.MAX_VALUE - 1, Long.MAX_VALUE);

        // We never emit this, but we should be able to parse it:
        final byte[] SPURIOUS_ZEROES_1 = {1, 4, -1, 0, 0, 0, 4, -128, 0, 0, 0};
        final byte[] EXPECTED_1 = {1, 1, -1, 1, -128};
        xf.parseFromLocalFileData(SPURIOUS_ZEROES_1, 0, SPURIOUS_ZEROES_1.length);

        assertEquals(255, xf.getUID());
        assertEquals(128, xf.getGID());
        assertTrue(Arrays.equals(EXPECTED_1, xf.getLocalFileDataData()));

        final byte[] SPURIOUS_ZEROES_2 = {1, 4, -1, -1, 0, 0, 4, 1, 2, 0, 0};
        final byte[] EXPECTED_2 = {1, 2, -1, -1, 2, 1, 2};
        xf.parseFromLocalFileData(SPURIOUS_ZEROES_2, 0, SPURIOUS_ZEROES_2.length);

        assertEquals(65535, xf.getUID());
        assertEquals(513, xf.getGID());
        assertTrue(Arrays.equals(EXPECTED_2, xf.getLocalFileDataData()));
    }
    @Test
    public void test44() {
        xf.uid = -1;
        xf.gid = -1;
        xf.reset();
        assertEquals(1000, xf.uid);
        assertEquals(1000, xf.gid);
    }
    @Test
    public void test45() {
        xf.uid = 0;
        xf.gid = 0;
        xf.reset();
        assertEquals(1000, xf.uid);
        assertEquals(1000, xf.gid);
    }
    @Test
    public void test46() {
        xf.uid = 12345;
        xf.gid = 54321;
        xf.reset();
        assertEquals(1000, xf.uid);
        assertEquals(1000, xf.gid);
    }
    @Test
    public void test47() {
        xf.uid = Integer.MAX_VALUE;
        xf.gid = Integer.MAX_VALUE;
        xf.reset();
        assertEquals(1000, xf.uid);
        assertEquals(1000, xf.gid);
    }
    @Test
    public void test48() {
        xf.uid = Integer.MIN_VALUE;
        xf.gid = Integer.MIN_VALUE;
        xf.reset();
        assertEquals(1000, xf.uid);
        assertEquals(1000, xf.gid);
    }
    @Test
    public void test49() throws Exception {
        // Creating a new instance of ZipExtraField with different UID value
        ZipExtraField xf2 = new ZipExtraField();
        xf2.setUID(7890);

        // Comparing the toString() of two instances with different UID values
        assertFalse(xf.toString().equals(xf2.toString()));
    }
    @Test
    public void test50() throws Exception {
        // Creating a new instance of ZipExtraField with different GID value
        ZipExtraField xf2 = new ZipExtraField();
        xf2.setGID(9876);
        
        // Comparing the toString() of two instances with different GID values
        assertFalse(xf.toString().equals(xf2.toString()));
    }
    @Test
    public void test51() throws Exception {
        // Creating a new instance of ZipExtraField with different UID value
        ZipExtraField xf2 = new ZipExtraField();
        xf2.setUID(7890);

        // Comparing the hashCodes of two instances with different UID values
        assertFalse(xf.hashCode() == xf2.hashCode());
    }
    @Test
    public void test52() throws Exception {
        // Creating a new instance of ZipExtraField with different GID value
        ZipExtraField xf2 = new ZipExtraField();
        xf2.setGID(9876);

        // Comparing the hashCodes of two instances with different GID values
        assertFalse(xf.hashCode() == xf2.hashCode());
    }
    @Test
    public void test53() throws Exception {
        // Creating a new instance of ZipExtraField with different UID value
        ZipExtraField xf2 = new ZipExtraField();
        xf2.setUID(7890);

        // Comparing the equality of two instances with different UID values
        assertFalse(xf.equals(xf2));
    }
    @Test
    public void test54() throws Exception {
        // Creating a new instance of ZipExtraField with different GID value
        ZipExtraField xf2 = new ZipExtraField();
        xf2.setGID(9876);

        // Comparing the equality of two instances with different GID values
        assertFalse(xf.equals(xf2));
    }
    @Test
    public void test55() throws Exception {
        // Cloning the original instance
        ZipExtraField xfClone = (ZipExtraField) xf.clone();

        // Comparing the toString() values of the original and cloned instances
        assertTrue(xf.toString().equals(xfClone.toString()));

        // Comparing the hashCodes of the original and cloned instances
        assertEquals(xf.hashCode(), xfClone.hashCode());

        // Comparing the equality of the original and cloned instances
        assertTrue(xf.equals(xfClone));

        // Modifying the UID of the original instance
        xf.setUID(12345);

        // Comparing the equality of the modified original instance and cloned instance
        assertFalse(xf.equals(xfClone));
    }
@Test
public void test56() throws Exception {
    assertFalse(xf.equals(new Object()));
    assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
    Object o = xf.clone();
    assertEquals(o.hashCode(), xf.hashCode());
    assertTrue(xf.equals(o));
    xf.setUID(12345);
    assertFalse(xf.equals(o));

    // Regression test 1: Changing the input value of the clone() method
    Object o1 = xf.clone();
    assertEquals(o1.hashCode(), xf.hashCode());
    assertTrue(xf.equals(o1));
    assertNull(o1.getUID());

    // Regression test 2: Changing the input value of the clone() method
    xf.setUID(67890);
    Object o2 = xf.clone();
    assertEquals(o2.hashCode(), xf.hashCode());
    assertTrue(xf.equals(o2));
    assertEquals(o2.getUID(), xf.getUID());

}
public Object clone() throws CloneNotSupportedException {
    return super.clone();
}
    @Test
    public void test57() {
        X7875_NewUnix xf1 = new X7875_NewUnix(1, "uid1", "gid1");
        X7875_NewUnix xf2 = new X7875_NewUnix(2, "uid1", "gid1");
        assertFalse(xf1.equals(xf2));
    }
    @Test
    public void test58() {
        X7875_NewUnix xf1 = new X7875_NewUnix(1, "uid1", "gid1");
        X7875_NewUnix xf2 = new X7875_NewUnix(1, "uid2", "gid1");
        assertFalse(xf1.equals(xf2));
    }
    @Test
    public void test59() {
        X7875_NewUnix xf1 = new X7875_NewUnix(1, "uid1", "gid1");
        X7875_NewUnix xf2 = new X7875_NewUnix(1, "uid1", "gid2");
        assertFalse(xf1.equals(xf2));
    }
    @Test
    public void test60() {
        X7875_NewUnix xf = new X7875_NewUnix(1, "uid1", "gid1");
        assertFalse(xf.equals(new Object()));
    }
    @Test
    public void test61() {
        X7875_NewUnix xf = new X7875_NewUnix(1, "uid1", "gid1");
        assertTrue(xf.equals(xf));
    }
    @Test
    public void test62() {
        X7875_NewUnix xf1 = new X7875_NewUnix(1, "uid1", "gid1");
        X7875_NewUnix xf2 = new X7875_NewUnix(1, "uid1", "gid1");
        assertTrue(xf1.hashCode() == xf2.hashCode());
    }
    @Test
    public void test63() {
        X7875_NewUnix xf1 = new X7875_NewUnix(1, "uid1", "gid1");
        X7875_NewUnix xf2 = new X7875_NewUnix(1, "uid1", "gid1");
        xf1.setUID(12345);
        assertFalse(xf1.equals(xf2));
    }
    @Test
    public void test64() throws Exception {
        File archive = getFile("COMPRESS-211_uid_gid_zip_test.zip");
        ZipFile zf = null;

        try {
            zf = new ZipFile(archive);
            ZipArchiveEntry zae = zf.getEntry("file.txt");
            X7875_NewUnix xf = (X7875_NewUnix) zae.getExtraField(X7875);

            long originalGID = xf.getGID();
            xf.setGID(1000);
            xf.parseFromLocalFileData(zae.getCentralDirectoryExtra());
            long newGID = xf.getGID();

            assertFalse(originalGID == newGID);
        } finally {
            if (zf != null) {
                zf.close();
            }
        }
    }
    @Test
    public void test65() throws Exception {
        File archive = getFile("COMPRESS-211_uid_gid_zip_test.zip");
        ZipFile zf = null;

        try {
            zf = new ZipFile(archive);
            ZipArchiveEntry zae = zf.getEntry("file.txt");
            X7875_NewUnix xf = (X7875_NewUnix) zae.getExtraField(X7875);

            long originalUID = xf.getUID();
            xf.setUID(1000);
            xf.parseFromLocalFileData(zae.getCentralDirectoryExtra());
            long newUID = xf.getUID();

            assertFalse(originalUID == newUID);
        } finally {
            if (zf != null) {
                zf.close();
            }
        }
    }
    @Test
    public void test66() throws Exception {
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertTrue(xf.equals(o));
        xf.setUID(12345);
        assertFalse(xf.equals(o));
    }
    @Test
    public void test67() throws Exception {
        File archive = getFile("COMPRESS-211_uid_gid_zip_test.zip");
        ZipFile zf = null;

        try {
            zf = new ZipFile(archive);
            Enumeration<ZipArchiveEntry> en = zf.getEntries();

            // We expect EVERY entry of this zip file (dir & file) to
            // contain extra field 0x7875.
            while (en.hasMoreElements()) {

                ZipArchiveEntry zae = en.nextElement();
                String name = zae.getName();
                X7875_NewUnix xf = (X7875_NewUnix) zae.getExtraField(X7875);

                // The directory entry in the test zip file is uid/gid 1000.
                long expected = 1000;
                if (name.contains("uid555_gid555")) {
                    expected = 555;
                } else if (name.contains("uid5555_gid5555")) {
                    expected = 5555;
                } else if (name.contains("uid55555_gid55555")) {
                    expected = 55555;
                } else if (name.contains("uid555555_gid555555")) {
                    expected = 555555;
                } else if (name.contains("min_unix")) {
                    expected = 0;
                } else if (name.contains("max_unix")) {
                    // 2^32-2 was the biggest UID/GID I could create on my linux!
                    // (December 2012, linux kernel 3.4)
                    expected = 0x100000000L - 2;
                }
                assertEquals(expected, xf.getUID());
                assertEquals(expected, xf.getGID());
            }

            // Regression tests
            ZipArchiveEntry entry = new ZipArchiveEntry("test1.txt");
            X7875_NewUnix extraField = new X7875_NewUnix();
            extraField.setUID(0);
            extraField.setGID(0);
            entry.setExtraFields(new ZipExtraField[] {extraField});
            long expected = 0;
            assertEquals(expected, extraField.getUID());
            assertEquals(expected, extraField.getGID());

            entry = new ZipArchiveEntry("test2.txt");
            extraField = new X7875_NewUnix();
            extraField.setUID(100);
            extraField.setGID(200);
            entry.setExtraFields(new ZipExtraField[] {extraField});
            expected = 100;
            assertEquals(expected, extraField.getUID());
            expected = 200;
            assertEquals(expected, extraField.getGID());

            entry = new ZipArchiveEntry("test3.txt");
            extraField = new X7875_NewUnix();
            extraField.setUID(1234567890);
            extraField.setGID(9876543210L);
            entry.setExtraFields(new ZipExtraField[] {extraField});
            expected = 1234567890;
            assertEquals(expected, extraField.getUID());
            expected = 9876543210L;
            assertEquals(expected, extraField.getGID());

        } finally {
            if (zf != null) {
                zf.close();
            }
        }
    }
    @Test
    public void test68() {
        final byte[] NULL = null;
        final byte[] EMPTY = new byte[0];
        final byte[] ONE_ZERO = {0};
        final byte[] TWO_ZEROES = {0, 0};
        final byte[] FOUR_ZEROES = {0, 0, 0, 0};
        final byte[] SEQUENCE = {1, 2, 3};
        final byte[] SEQUENCE_LEADING_ZERO = {0, 1, 2, 3};
        final byte[] SEQUENCE_LEADING_ZEROES = {0, 0, 0, 0, 0, 0, 0, 1, 2, 3};
        final byte[] TRAILING_ZERO = {1, 2, 3, 0};
        final byte[] PADDING_ZERO = {0, 1, 2, 3, 0};
        final byte[] SEQUENCE6 = {1, 2, 3, 4, 5, 6};
        final byte[] SEQUENCE6_LEADING_ZERO = {0, 1, 2, 3, 4, 5, 6};

        assertTrue(NULL == trimTest(NULL));
        assertTrue(Arrays.equals(ONE_ZERO, trimTest(EMPTY)));
        assertTrue(Arrays.equals(ONE_ZERO, trimTest(ONE_ZERO)));
        assertTrue(Arrays.equals(ONE_ZERO, trimTest(TWO_ZEROES)));
        assertTrue(Arrays.equals(ONE_ZERO, trimTest(FOUR_ZEROES)));
        assertTrue(Arrays.equals(SEQUENCE, trimTest(SEQUENCE)));
        assertTrue(Arrays.equals(SEQUENCE, trimTest(SEQUENCE_LEADING_ZERO)));
        assertTrue(Arrays.equals(SEQUENCE, trimTest(SEQUENCE_LEADING_ZEROES)));
        assertTrue(Arrays.equals(TRAILING_ZERO, trimTest(TRAILING_ZERO)));
        assertTrue(Arrays.equals(TRAILING_ZERO, trimTest(PADDING_ZERO)));
        assertTrue(Arrays.equals(SEQUENCE6, trimTest(SEQUENCE6)));
        assertTrue(Arrays.equals(SEQUENCE6, trimTest(SEQUENCE6_LEADING_ZERO)));

        // New regression test cases:
        final byte[] REGRESSION_ZEROES1 = {0, 0, 0, 1, 2, 3};
        final byte[] REGRESSION_ZEROES2 = {0, 0, 1, 2, 3};
        final byte[] REGRESSION_ZEROES3 = {0, 0, 0, 0, 1, 2, 3};
        final byte[] REGRESSION_ZEROES4 = {0, 0, 1, 2, 3};
        final byte[] REGRESSION_SEQUENCE1 = {0, 0, 0, 1, 2, 0, 3};
        final byte[] REGRESSION_SEQUENCE2 = {0, 0, 1, 2, 0, 3};
        final byte[] REGRESSION_SEQUENCE3 = {0, 0, 0, 0, 1, 2, 0, 3};
        final byte[] REGRESSION_SEQUENCE4 = {0, 0, 1, 2, 0, 3};
        final byte[] REGRESSION_SEQUENCE5 = {0, 0, 0, 0, 0, 1, 2, 0, 3};

        assertTrue(Arrays.equals(SEQUENCE_LEADING_ZERO, trimTest(REGRESSION_ZEROES1)));
        assertTrue(Arrays.equals(SEQUENCE_LEADING_ZERO, trimTest(REGRESSION_ZEROES2)));
        assertTrue(Arrays.equals(SEQUENCE_LEADING_ZEROES, trimTest(REGRESSION_ZEROES3)));
        assertTrue(Arrays.equals(SEQUENCE_LEADING_ZEROES, trimTest(REGRESSION_ZEROES4)));
        assertTrue(Arrays.equals(SEQUENCE_LEADING_ZERO, trimTest(REGRESSION_SEQUENCE1)));
        assertTrue(Arrays.equals(SEQUENCE_LEADING_ZERO, trimTest(REGRESSION_SEQUENCE2)));
        assertTrue(Arrays.equals(SEQUENCE_LEADING_ZEROES, trimTest(REGRESSION_SEQUENCE3)));
        assertTrue(Arrays.equals(SEQUENCE_LEADING_ZEROES, trimTest(REGRESSION_SEQUENCE4)));
        assertTrue(Arrays.equals(SEQUENCE_LEADING_ZEROES, trimTest(REGRESSION_SEQUENCE5)));
    }
}