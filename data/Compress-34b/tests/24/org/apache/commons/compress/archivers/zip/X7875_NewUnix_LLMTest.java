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
public void test0() throws Exception {
    File archive = getFile("COMPRESS-211_uid_gid_zip_test.zip");
    ZipFile zf = null;
    long expected = 0;
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
            }

            // Change the expected UID/GID value to a different value
            expected = expected + 1;

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
    public void test1() throws ZipException {
        // Version=1, Len=3, 1234, Len=3, 4321.
        final byte[] DIFFERENT_UID_GID = {1, 3, 4, 210, 2, 3, 4, 321};
  
        parseReparse(1234, 4321, DIFFERENT_UID_GID, 1234, 4321);
    }
    @Test
    public void test2() throws Exception {
        File archive2 = getFile("COMPRESS-211_uid_gid_zip_test2.zip");
        ZipFile zf2 = null;

        try {
            zf2 = new ZipFile(archive2);
            Enumeration<ZipArchiveEntry> en2 = zf2.getEntries();

            // We expect EVERY entry of this zip file (dir & file) to
            // contain extra field 0x7875.
            while (en2.hasMoreElements()) {

                ZipArchiveEntry zae2 = en2.nextElement();
                String name2 = zae2.getName();
                X7875_NewUnix xf2 = (X7875_NewUnix) zae2.getExtraField(X7875);

                // The directory entry in the test zip file is uid/gid 1000.
                long expected2 = 10000;
                if (name2.contains("uid555_gid555")) {
                    expected2 = 555;
                } else if (name2.contains("uid5555_gid5555")) {
                    expected2 = 5555;
                } else if (name2.contains("uid55555_gid55555")) {
                    expected2 = 55555;
                } else if (name2.contains("uid555555_gid555555")) {
                    expected2 = 555555;
                } else if (name2.contains("min_unix")) {
                    expected2 = 0;
                } else if (name2.contains("max_unix")) {
                    // 2^32-2 was the biggest UID/GID I could create on my linux!
                    // (December 2012, linux kernel 3.4)
                    expected2 = 0x100000000L - 2;
                }
                assertEquals(expected2, xf2.getUID());
                assertEquals(expected2, xf2.getGID());
            }
        } finally {
            if (zf2 != null) {
                zf2.close();
            }
        }
    }
    @Test
    public void test3() throws ZipException {
        // existing test cases

        // Regression test 1: changing the value of ZERO_UID_GID
        final byte[] ZERO_UID_GID = {1, 1, 0, 2, 0};
        parseReparse(0, 0, ZERO_UID_GID, 0, 0);

        // Regression test 2: changing the value of ONE_UID_GID
        final byte[] ONE_UID_GID = {1, 1, 2, 1, 1};
        parseReparse(1, 1, ONE_UID_GID, 1, 1);

        // Regression test 3: changing the value of ONE_THOUSAND_UID_GID
        final byte[] ONE_THOUSAND_UID_GID = {1, 2, -24, 4, 2, -24, 4};
        parseReparse(1000, 1000, ONE_THOUSAND_UID_GID, 1000, 1000);

        // Regression test 4: changing the value of UNIX_MAX_UID_GID
        final byte[] UNIX_MAX_UID_GID = {1, 4, -3, -1, -1, -1, 4, -3, -1, -1, -1};
        parseReparse(Long.MAX_VALUE-1, Long.MAX_VALUE-1, UNIX_MAX_UID_GID, Long.MAX_VALUE-1, Long.MAX_VALUE-1);

        // Regression test 5: changing the value of LENGTH_5
        final byte[] LENGTH_5 = {1, 5, -1, -1, -1, -1, 1, 5, -1, -1, -1, 0};
        parseReparse(-1, 0, LENGTH_5, -1, 0);

        // Regression test 6: changing the value of LENGTH_8
        final byte[] LENGTH_8 = {1, 8, -3, -1, -1, -1, -1, -1, -1, 126, 8, -1, -1, -1, -1, -1, -1, -1, 126};
        parseReparse(Long.MAX_VALUE-2, Long.MAX_VALUE-1, LENGTH_8, Long.MAX_VALUE-2, Long.MAX_VALUE-1);

        // existing test cases
    }
    @Test
    public void test4() {
        long l = -1;
        xf.setUID(l);
        assertEquals(l, xf.getUID());
    }
    @Test
    public void test5() {
        long l = 0;
        xf.setUID(l);
        assertEquals(l, xf.getUID());
    }
    @Test
    public void test6() {
        long l = 1000;
        xf.setUID(l);
        assertEquals(l, xf.getUID());
    }
    @Test
    public void test7() {
        long l = Long.MAX_VALUE;
        xf.setUID(l);
        assertEquals(l, xf.getUID());
    }
    @Test
    public void test8() throws ZipException {
        // Version=1, Len=1, zero, Len=1, one
        final byte[] ZERO_UID_ONE_GID = {1, 1, 0, 1, 1};
        parseReparse(0, 1, ZERO_UID_ONE_GID, 0, 1);
    }
    @Test
    public void test9() throws ZipException {
        // Version=1, Len=2, one, Len=2, one thousand
        final byte[] ONE_UID_ONE_THOUSAND_GID = {1, 2, 1, 2, 3, 1};
        parseReparse(1, 1000, ONE_UID_ONE_THOUSAND_GID, 1, 1000);
    }
    @Test
    public void test10() throws ZipException {
        // Version=1, Len=5, 2^32, Len=5, 2^63 - 1
        final byte[] LENGTH_5_63BIT_MAX_GID = {1, 5, 0, 0, 0, 0, -1, -1, -1, -1, 127};
        parseReparse((long)Math.pow(2, 32), Long.MAX_VALUE, LENGTH_5_63BIT_MAX_GID, (long)Math.pow(2, 32), Long.MAX_VALUE);
    }
    @Test
    public void test11() {
        // Test case where uid and gid have minimum possible length
        byte[] uid = {0};
        byte[] gid = {0};
        int expected1 = 3 + uid.length + gid.length;
        int actual1 = getLocalFileDataLength(uid, gid);
        assertEquals(expected1, actual1);

        // Test case where uid and gid have maximum possible length
        byte[] uid2 = {0, 0, 0, 0, 0, 0, 0, 0, 0};
        byte[] gid2 = {0, 0, 0, 0, 0, 0, 0, 0, 0};
        int expected2 = 3 + uid2.length + gid2.length;
        int actual2 = getLocalFileDataLength(uid2, gid2);
        assertEquals(expected2, actual2);

        // Test case where uid and gid have different lengths
        byte[] uid3 = {0, 0, 0};
        byte[] gid3 = {0, 0, 0, 0, 0};
        int expected3 = 3 + uid3.length + gid3.length;
        int actual3 = getLocalFileDataLength(uid3, gid3);
        assertEquals(expected3, actual3);
    }
    private int getLocalFileDataLength(byte[] uid, byte[] gid) {
        int uidSize = trimLeadingZeroesForceMinLength(uid).length;
        int gidSize = trimLeadingZeroesForceMinLength(gid).length;

        // The 3 comes from:  version=1 + uidsize=1 + gidsize=1
        return 3 + uidSize + gidSize;
    }
    @Test
    public void test12() throws ZipException {

        // Version=1, Len=0, Len=0.
        final byte[] ZERO_LEN = {1, 0, 0};
        // Changing input value of ZERO_LEN
        final byte[] MOD_ZERO_LEN = {2, 0, 1};

        // Version=1, Len=1, zero, Len=1, zero.
        final byte[] ZERO_UID_GID = {1, 1, 0, 1, 0};
        // Changing input value of ZERO_UID_GID
        final byte[] MOD_ZERO_UID_GID = {1, 2, 0, 2, 0};

        // Version=1, Len=1, one, Len=1, one
        final byte[] ONE_UID_GID = {1, 1, 1, 1, 1};
        // Changing input value of ONE_UID_GID
        final byte[] MOD_ONE_UID_GID = {1, 2, 1, 2, 1};

        // Version=1, Len=2, one thousand, Len=2, one thousand
        final byte[] ONE_THOUSAND_UID_GID = {1, 2, -24, 3, 2, -24, 3};
        // Changing input value of ONE_THOUSAND_UID_GID
        final byte[] MOD_ONE_THOUSAND_UID_GID = {1, 3, -24, -23, -24, 3, 3};

        // (2^32 - 2).   I guess they avoid (2^32 - 1) since it's identical to -1 in
        // two's complement, and -1 often has a special meaning.
        final byte[] UNIX_MAX_UID_GID = {1, 4, -2, -1, -1, -1, 4, -2, -1, -1, -1};
        // Changing input value of UNIX_MAX_UID_GID
        final byte[] MOD_UNIX_MAX_UID_GID = {1, 4, 1, 0, 0, 0, 1, 4, 1, 1, 1};

        // Version=1, Len=5, 2^32, Len=5, 2^32 + 1
        // Esoteric test:  can we handle 40 bit numbers?
        final byte[] LENGTH_5 = {1, 5, 0, 0, 0, 0, 1, 5, 1, 0, 0, 0, 1};
        // Changing input value of LENGTH_5
        final byte[] MOD_LENGTH_5 = {1, 6, 0, 0, 0, 0, 0, 1, 6, 1, 0, 0, 0, 0};

        // Version=1, Len=8, 2^63 - 2, Len=8, 2^63 - 1
        // Esoteric test:  can we handle 64 bit numbers?
        final byte[] LENGTH_8 = {1, 8, -2, -1, -1, -1, -1, -1, -1, 127, 8, -1, -1, -1, -1, -1, -1, -1, 127};
        // Changing input value of LENGTH_8
        final byte[] MOD_LENGTH_8 = {1, 6, -2, -1, -1, -1, -1, 127, 8, -2, -1, -1, -1, -1, -1, -1, 127};

        final long TWO_TO_32 = 0x100000000L;
        final long MAX = TWO_TO_32 - 2;
        final long MOD_MAX = TWO_TO_32 + 1;

        parseReparse(0, 0, ZERO_LEN, 0, 0);
        parseReparse(0, 0, MOD_ZERO_LEN, 0, 0);

        parseReparse(0, 0, ZERO_UID_GID, 0, 0);
        parseReparse(0, 0, MOD_ZERO_UID_GID, 0, 0);

        parseReparse(1, 1, ONE_UID_GID, 1, 1);
        parseReparse(1, 1, MOD_ONE_UID_GID, 1, 1);

        parseReparse(1000, 1000, ONE_THOUSAND_UID_GID, 1000, 1000);
        parseReparse(1000, 1000, MOD_ONE_THOUSAND_UID_GID, 1000, 1000);

        parseReparse(MAX, MAX, UNIX_MAX_UID_GID, MAX, MAX);
        parseReparse(MAX, MAX, MOD_UNIX_MAX_UID_GID, MAX, MAX);

        parseReparse(TWO_TO_32, TWO_TO_32 + 1, LENGTH_5, TWO_TO_32, TWO_TO_32 + 1);
        parseReparse(TWO_TO_32, TWO_TO_32 + 1, MOD_LENGTH_5, TWO_TO_32, TWO_TO_32 + 1);

        parseReparse(Long.MAX_VALUE - 1, Long.MAX_VALUE, LENGTH_8, Long.MAX_VALUE - 1, Long.MAX_VALUE);
        parseReparse(Long.MAX_VALUE - 1, Long.MAX_VALUE, MOD_LENGTH_8, Long.MAX_VALUE - 1, Long.MAX_VALUE);

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
    public void test13() {
        byte[] uidBytes = BigInteger.valueOf(-1000).toByteArray();
        byte[] gidBytes = BigInteger.valueOf(-1000).toByteArray();

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

        assertEquals(expectedUID, xf.getUID());
        assertEquals(expectedGID, xf.getGID());
    }
    @Test
    public void test14() {
        byte[] uidBytes = BigInteger.valueOf(1000).toByteArray();
        byte[] gidBytes = BigInteger.valueOf(1000).toByteArray();

        uidBytes = trimLeadingZeroesForceMinLength(uidBytes);
        gidBytes = trimLeadingZeroesForceMinLength(gidBytes);

        byte[] data = new byte[version + uidBytes.length + gidBytes.length];

        reverse(uidBytes);
        reverse(gidBytes);

        int pos = 0;
        data[pos++] = unsignedIntToSignedByte(0);
        data[pos++] = unsignedIntToSignedByte(uidBytes.length);
        System.arraycopy(uidBytes, 0, data, pos, uidBytes.length);
        pos += uidBytes.length;
        data[pos++] = unsignedIntToSignedByte(gidBytes.length);
        System.arraycopy(gidBytes, 0, data, pos, gidBytes.length);

        assertEquals(expectedUID, xf.getUID());
        assertEquals(expectedGID, xf.getGID());
    }
    @Test
    public void test15() {
        byte[] uidBytes = BigInteger.valueOf(Long.MAX_VALUE - 1).toByteArray();
        byte[] gidBytes = BigInteger.valueOf(Long.MAX_VALUE).toByteArray();

        uidBytes = trimLeadingZeroesForceMinLength(uidBytes);
        gidBytes = trimLeadingZeroesForceMinLength(gidBytes);

        byte[] data = new byte[3 + uidBytes.length + gidBytes.length];

        reverse(uidBytes);
        reverse(gidBytes);

        int pos = 0;
        data[pos++] = unsignedIntToSignedByte(version);
        data[pos++] = unsignedIntToSignedByte(uidBytes.length);
        System.arraycopy(uidBytes, uidBytes.length-2, data, pos, uidBytes.length);
        pos += uidBytes.length;
        data[pos++] = unsignedIntToSignedByte(gidBytes.length);
        System.arraycopy(gidBytes, 0, data, pos, gidBytes.length);

        assertEquals(expectedUID, xf.getUID());
        assertEquals(expectedGID, xf.getGID());
    }
    @Test
    public void test16() throws Exception {
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
    public void test17() throws ZipException {
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
        
        // Regression tests
        parseReparse(10, 10, ZERO_LEN, 10, 10); // Test different uid and gid
        parseReparse(0, 0, ZERO_UID_GID, 0, 0); // Test same uid and gid
        parseReparse(100, 100, ONE_UID_GID, 100, 100); // Test different uid and gid
        parseReparse(1000, 1000, ONE_THOUSAND_UID_GID, 1000, 1000); // Test same uid and gid
        parseReparse(-1, -1, UNIX_MAX_UID_GID, MAX, MAX); // Test negative uid and gid
        parseReparse(TWO_TO_32 + 1, TWO_TO_32, LENGTH_5, TWO_TO_32 + 1, TWO_TO_32); // Test different uid and gid
        parseReparse(Long.MAX_VALUE, Long.MAX_VALUE - 1, LENGTH_8, Long.MAX_VALUE, Long.MAX_VALUE - 1); // Test different uid and gid
    }
    @Test
    public void test18() throws Exception {
        byte[] buffer = new byte[64]; // change the buffer length
        int offset = 0;
        int length = buffer.length;

        try {
            parseFromCentralDirectoryData(buffer, offset, length);
            fail("Expected ZipException to be thrown");
        } catch (ZipException e) {
            assertEquals("Invalid buffer length", e.getMessage());
        }
    }
    @Test
    public void test19() throws Exception {
        byte[] buffer = new byte[0]; // change the buffer to be empty
        int offset = 0;
        int length = buffer.length;

        try {
            parseFromCentralDirectoryData(buffer, offset, length);
            fail("Expected ZipException to be thrown");
        } catch (ZipException e) {
            assertEquals("Invalid buffer length", e.getMessage());
        }
    }
    @Test
    public void test20() throws Exception {
        byte[] buffer = new byte[128];
        int offset = -1; // change the offset to be negative
        int length = buffer.length;

        try {
            parseFromCentralDirectoryData(buffer, offset, length);
            fail("Expected ZipException to be thrown");
        } catch (ZipException e) {
            assertEquals("Invalid buffer offset", e.getMessage());
        }
    }
    @Test
    public void test21() throws Exception {
        byte[] buffer = new byte[128];
        int offset = buffer.length + 1; // change the offset to be greater than buffer length
        int length = buffer.length;

        try {
            parseFromCentralDirectoryData(buffer, offset, length);
            fail("Expected ZipException to be thrown");
        } catch (ZipException e) {
            assertEquals("Invalid buffer offset", e.getMessage());
        }
    }
    @Test
    public void test22() throws Exception {
        byte[] buffer = new byte[256];
        int offset = 0;
        int length = -1; // change the length to be negative

        try {
            parseFromCentralDirectoryData(buffer, offset, length);
            fail("Expected ZipException to be thrown");
        } catch (ZipException e) {
            assertEquals("Invalid buffer length", e.getMessage());
        }
    }
    @Test
    public void test23() throws Exception {
        byte[] buffer = new byte[256];
        int offset = 0;
        int length = buffer.length + 1; // change the length to be greater than buffer length

        try {
            parseFromCentralDirectoryData(buffer, offset, length);
            fail("Expected ZipException to be thrown");
        } catch (ZipException e) {
            assertEquals("Invalid buffer length", e.getMessage());
        }
    }
    @Test
    public void test24() {
        uid = 0;
        gid = 0;
        reset();
        assertEquals(1000, uid);
        assertEquals(1000, gid);
    }
    @Test
    public void test25() {
        uid = -1;
        gid = 0;
        reset();
        assertEquals(1000, uid);
        assertEquals(1000, gid);
    }
    @Test
    public void test26() {
        uid = Integer.MAX_VALUE;
        gid = 0;
        reset();
        assertEquals(1000, uid);
        assertEquals(1000, gid);
    }
    @Test
    public void test27() {
        uid = 0;
        gid = -1;
        reset();
        assertEquals(1000, uid);
        assertEquals(1000, gid);
    }
    @Test
    public void test28() {
        uid = 0;
        gid = Integer.MAX_VALUE;
        reset();
        assertEquals(1000, uid);
        assertEquals(1000, gid);
    }
    @Test
    public void test29() {
        uid = Integer.MAX_VALUE;
        gid = Integer.MAX_VALUE;
        reset();
        assertEquals(1000, uid);
        assertEquals(1000, gid);
    }
    @Test
    public void test30() {
        uid = 0;
        gid = 0;
        reset();
        assertEquals(1000, uid);
        assertEquals(1000, gid);
    }
    @Test
    public void test31() throws Exception {
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertTrue(xf.equals(o));
        xf.setUID(12345);
        assertFalse(xf.equals(o));
    }
    @Test
    public void test32() throws Exception {
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertTrue(xf.equals(o));
        xf.setUID(0);
        assertFalse(xf.equals(o));
    }
    @Test
    public void test33() throws Exception {
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertTrue(xf.equals(o));
        xf.setGID(0);
        assertFalse(xf.equals(o));
    }
    @Test
    public void test34() throws Exception {
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertTrue(xf.equals(o));
        xf.setUID(0);
        xf.setGID(0);
        assertFalse(xf.equals(o));
    }
    @Test
    public void test35() throws Exception {
        // Basic test case with different object
        assertFalse(xf.equals(new Object()));
        
        // Test case with different string representation
        assertTrue(xf.toString().startsWith("0x7876 Zip Extra Field"));
        
        // Test case with a clone that has different hashcode
        Object o = xf.clone();
        o.setUID(9876);
        assertNotEquals(o.hashCode(), xf.hashCode());
        
        // Test case with a clone that is not equal to the original object
        assertFalse(xf.equals(o));
        
        // Test case with a clone that is modified to be equal to the original object
        o.setUID(12345);
        assertTrue(xf.equals(o));
    }
    @Test
    public void test36() throws Exception {
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
        X7875_NewUnix xf = null;
        assertFalse(xf.equals(new Object()));
    }
    @Test
    public void test37() {
        assertEquals(X7875, xf.getHeaderId());
        xf = new X7875_NewUnix(-1L, 0L);
        assertEquals(X7875, xf.getHeaderId());
    }
    @Test
    public void test38() throws Exception {
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertTrue(xf.equals(o));
        
        X7875_NewUnix newXf = new X7875_NewUnix(12345L, 0L);
        assertFalse(xf.equals(newXf));
    }
    @Test
    public void test39() throws Exception {
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
    public void test41() throws Exception {
        File archive = getFile("COMPRESS-211_uid_gid_zip_test.zip");
        ZipFile zf = null;

        try {
            zf = new ZipFile(archive);
            Enumeration<ZipArchiveEntry> en = zf.getEntries();

            while (en.hasMoreElements()) {

                ZipArchiveEntry zae = en.nextElement();
                String name = zae.getName();
                X7875_NewUnix xf = (X7875_NewUnix) zae.getExtraField(X7875);

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
    public void test42() throws Exception {
        File archive = getFile("COMPRESS-211_uid_gid_zip_test.zip");
        ZipFile zf = null;

        try {
            zf = new ZipFile(archive);
            Enumeration<ZipArchiveEntry> en = zf.getEntries();

            while (en.hasMoreElements()) {

                ZipArchiveEntry zae = en.nextElement();
                String name = zae.getName();
                X7875_NewUnix xf = (X7875_NewUnix) zae.getExtraField(X7875);

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
                    expected = 0x100000000L - 2;
                }

                // Change UID value to test mutation
                xf.setUID(6789);

                // Ensure that the expected value is not equal to the mutated UID value
                assertFalse(expected == xf.getUID());

                // Ensure the GID is not affected
                assertEquals(expected, xf.getGID());
            }
        } finally {
            if (zf != null) {
                zf.close();
            }
        }
    }
    @Test
    public void test43() throws Exception {
        File archive = getFile("COMPRESS-211_uid_gid_zip_test.zip");
        ZipFile zf = null;

        try {
            zf = new ZipFile(archive);
            Enumeration<ZipArchiveEntry> en = zf.getEntries();

            while (en.hasMoreElements()) {

                ZipArchiveEntry zae = en.nextElement();
                String name = zae.getName();
                X7875_NewUnix xf = (X7875_NewUnix) zae.getExtraField(X7875);

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
                    expected = 0x100000000L - 2;
                }

                // Change GID value to test mutation
                xf.setGID(7890);

                // Ensure that the expected value is not equal to the mutated GID value
                assertFalse(expected == xf.getGID());

                // Ensure the UID is not affected
                assertEquals(expected, xf.getUID());
            }
        } finally {
            if (zf != null) {
                zf.close();
            }
        }
    }
    @Test
    public void test44() {
        final byte[] ONE_ZEROES = {0};
        final byte[] TWO_ZEROES = {0, 0, 0};
        final byte[] FOUR_ZEROES = {0, 0, 0, 0, 0};

        assertTrue(Arrays.equals(ONE_ZEROES, trimTest(ONE_ZEROES)));
        assertTrue(Arrays.equals(TWO_ZEROES, trimTest(TWO_ZEROES)));
        assertTrue(Arrays.equals(FOUR_ZEROES, trimTest(FOUR_ZEROES)));
    }
    @Test
    public void test45() {
        final byte[] SEQUENCE_LEADING_ZEROES = {0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3};
        final byte[] SEQUENCE6_LEADING_ZERO = {0, 1, 2, 3, 4, 5, 6, 0};

        assertTrue(Arrays.equals(SEQUENCE_LEADING_ZEROES, trimTest(SEQUENCE_LEADING_ZEROES)));
        assertTrue(Arrays.equals(SEQUENCE6_LEADING_ZERO, trimTest(SEQUENCE6_LEADING_ZERO)));
    }
}