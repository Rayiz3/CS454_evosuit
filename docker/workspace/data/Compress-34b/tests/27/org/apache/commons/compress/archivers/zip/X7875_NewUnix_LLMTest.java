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
    ZipShort differentHeaderId = new ZipShort(1234);
    xf.setHeaderId(differentHeaderId);
    assertEquals(differentHeaderId, xf.getHeaderId());
}
    @Test
    public void test1() throws ZipException {

        // Version=1, Len=3, 1 million, Len=3, 10 million
        final byte[] MILLION_UID_GID = {1, 3, 16, -67, 33, 1, 3, -16, 80, 61};

        // Version=1, Len=4, 2 million, Len=4, 20 million
        final byte[] TWO_MILLION_UID_GID = {1, 4, 32, -126, 65, 0, 0, 64, -15, -24};

        // Version=1, Len=6, 4 billion-1, Len=6, 4 billion
        final byte[] FOUR_BILLION_UID_GID = {1, 6, -1, -1, -1, -1, 15, -1, -1, -1, -1, 0};
        

        parseReparse(1000000, 10000000, MILLION_UID_GID, 1000000, 10000000);
        parseReparse(2000000, 20000000, TWO_MILLION_UID_GID, 2000000, 20000000);
        parseReparse(3999999999L, 4000000000L, FOUR_BILLION_UID_GID, 3999999999L, 4000000000L);
        
    }
    @Test
    public void test2() throws ZipException {

        ...

        // Regression test case 1: giv=1234
        final byte[] GIVEN_1 = {1, 2, 210, 4, 2, 210, 4};
        parseReparse(1234, 1234, GIVEN_1, 1234, 1234);

        // Regression test case 2: giv=-4321
        final byte[] GIVEN_2 = {1, 2, -79, -17, 2, -79, -17};
        parseReparse(-4321, -4321, GIVEN_2, -4321, -4321);

        // Regression test case 3: giv=0
        final byte[] GIVEN_3 = {1, 1, 0, 1, 0};
        parseReparse(0, 0, GIVEN_3, 0, 0);

        ...

    }
    @Test
    public void test3() throws Exception {
        ...

        // Regression test case 1: expected=777
        long expected1 = 777;
        xf.parseFromLocalFileData(EXPECTED_1, 0, EXPECTED_1.length);
        assertEquals(expected1, xf.getUID());
        assertEquals(expected1, xf.getGID());

        // Regression test case 2: expected=99999
        long expected2 = 99999;
        xf.parseFromLocalFileData(EXPECTED_2, 0, EXPECTED_2.length);
        assertEquals(expected2, xf.getUID());
        assertEquals(expected2, xf.getGID());

        ...

    }
    @Test
    public void test4() throws Exception {
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertTrue(xf.equals(o));
        xf.setUID(12345);
        assertFalse(xf.equals(o));
        xf.setUID(-12345); // Regression test to kill more mutants
        assertFalse(xf.equals(o));
    }
    @Test
    public void test5() throws ZipException {

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
        parseReparse(-TWO_TO_32, -TWO_TO_32 + 1, LENGTH_5, -TWO_TO_32, -TWO_TO_32 + 1); // Regression test to kill more mutants

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
    public void test6() {
        // Test case where l is zero
        long l1 = 0;
        setGID(l1);
        assertEquals(ZipUtil.longToBig(l1), gid);

        // Test case where l is a positive non-zero value
        long l2 = 100;
        setGID(l2);
        assertEquals(ZipUtil.longToBig(l2), gid);

        // Test case where l is a negative value
        long l3 = -100;
        setGID(l3);
        assertEquals(ZipUtil.longToBig(l3), gid);
    }
    @Test
    public void test7() {
        // Test case 1: uidSize = 0, gidSize = 0
        int uidSize1 = 0;
        int gidSize1 = 0;
        int expected1 = 3 + uidSize1 + gidSize1;
        assertEquals(expected1, getLocalFileDataLength(uidSize1, gidSize1));

        // Test case 2: uidSize = 1, gidSize = 1
        int uidSize2 = 1;
        int gidSize2 = 1;
        int expected2 = 3 + uidSize2 + gidSize2;
        assertEquals(expected2, getLocalFileDataLength(uidSize2, gidSize2));

        // Test case 3: uidSize = 2, gidSize = 2
        int uidSize3 = 2;
        int gidSize3 = 2;
        int expected3 = 3 + uidSize3 + gidSize3;
        assertEquals(expected3, getLocalFileDataLength(uidSize3, gidSize3));

        // Test case 4: uidSize = 10, gidSize = 5
        int uidSize4 = 10;
        int gidSize4 = 5;
        int expected4 = 3 + uidSize4 + gidSize4;
        assertEquals(expected4, getLocalFileDataLength(uidSize4, gidSize4));
    }
    @Test
    public void test8() {
        ZipShort result;

        // Test case 1
        result = objectUnderTest.getCentralDirectoryLength();
        assertEquals(ZipShort.ZERO, result);

        // Test case 2
        objectUnderTest.setLocalFileDataLength(ZipShort.ONE);
        result = objectUnderTest.getCentralDirectoryLength();
        assertEquals(ZipShort.ONE, result);

        // Test case 3
        objectUnderTest.setLocalFileDataLength(new ZipShort(1000));
        result = objectUnderTest.getCentralDirectoryLength();
        assertEquals(new ZipShort(1000), result);

        // Test case 4
        objectUnderTest.setLocalFileDataLength(new ZipShort(Long.MAX_VALUE));
        result = objectUnderTest.getCentralDirectoryLength();
        assertEquals(new ZipShort(Long.MAX_VALUE), result);

        // Test case 5
        objectUnderTest.setLocalFileDataLength(new ZipShort(-1));
        result = objectUnderTest.getCentralDirectoryLength();
        assertEquals(ZipShort.ZERO, result);

        // Test case 6
        objectUnderTest.setLocalFileDataLength(new ZipShort(-2));
        result = objectUnderTest.getCentralDirectoryLength();
        assertEquals(ZipShort.ZERO, result);

        // Test case 7
        objectUnderTest.setLocalFileDataLength(new ZipShort(0x100000000L));
        result = objectUnderTest.getCentralDirectoryLength();
        assertEquals(ZipShort.ZERO, result);

        // Test case 8
        objectUnderTest.setLocalFileDataLength(new ZipShort(Long.MAX_VALUE - 1));
        result = objectUnderTest.getCentralDirectoryLength();
        assertEquals(ZipShort.ZERO, result);
    }
    @Test
    public void test9() throws Exception {
        byte[] uidBytes = new byte[]{};
        byte[] gidBytes = new byte[]{};

        byte[] data = new byte[3 + uidBytes.length + gidBytes.length];

        int pos = 0;
        data[pos++] = unsignedIntToSignedByte(1);
        data[pos++] = unsignedIntToSignedByte(uidBytes.length);
        System.arraycopy(uidBytes, 0, data, pos, uidBytes.length);
        pos += uidBytes.length;
        data[pos++] = unsignedIntToSignedByte(gidBytes.length);
        System.arraycopy(gidBytes, 0, data, pos, gidBytes.length);

        assertArrayEquals(new byte[]{1, 0, 0}, data);

    }
    @Test
    public void test10() throws Exception {
        byte[] uidBytes = new byte[]{0};
        byte[] gidBytes = new byte[]{0};

        byte[] data = new byte[3 + uidBytes.length + gidBytes.length];

        int pos = 0;
        data[pos++] = unsignedIntToSignedByte(1);
        data[pos++] = unsignedIntToSignedByte(uidBytes.length);
        System.arraycopy(uidBytes, 0, data, pos, uidBytes.length);
        pos += uidBytes.length;
        data[pos++] = unsignedIntToSignedByte(gidBytes.length);
        System.arraycopy(gidBytes, 0, data, pos, gidBytes.length);

        assertArrayEquals(new byte[]{1, 1, 0, 1, 0}, data);
    }
    @Test
    public void test11() throws Exception {
        byte[] uidBytes = new byte[]{1};
        byte[] gidBytes = new byte[]{1};

        byte[] data = new byte[3 + uidBytes.length + gidBytes.length];

        int pos = 0;
        data[pos++] = unsignedIntToSignedByte(1);
        data[pos++] = unsignedIntToSignedByte(uidBytes.length);
        System.arraycopy(uidBytes, 0, data, pos, uidBytes.length);
        pos += uidBytes.length;
        data[pos++] = unsignedIntToSignedByte(gidBytes.length);
        System.arraycopy(gidBytes, 0, data, pos, gidBytes.length);

        assertArrayEquals(new byte[]{1, 1, 1, 1, 1}, data);
    }
    @Test
    public void test12() throws Exception {
        byte[] uidBytes = new byte[]{-24, 3};
        byte[] gidBytes = new byte[]{-24, 3};

        byte[] data = new byte[3 + uidBytes.length + gidBytes.length];

        int pos = 0;
        data[pos++] = unsignedIntToSignedByte(1);
        data[pos++] = unsignedIntToSignedByte(uidBytes.length);
        System.arraycopy(uidBytes, 0, data, pos, uidBytes.length);
        pos += uidBytes.length;
        data[pos++] = unsignedIntToSignedByte(gidBytes.length);
        System.arraycopy(gidBytes, 0, data, pos, gidBytes.length);

        assertArrayEquals(new byte[]{1, 2, -24, 3, -24, 3}, data);
    }
    @Test
    public void test13() throws Exception {
        byte[] uidBytes = new byte[]{-2, -1, -1, -1};
        byte[] gidBytes = new byte[]{-2, -1, -1, -1};

        byte[] data = new byte[3 + uidBytes.length + gidBytes.length];

        int pos = 0;
        data[pos++] = unsignedIntToSignedByte(1);
        data[pos++] = unsignedIntToSignedByte(uidBytes.length);
        System.arraycopy(uidBytes, 0, data, pos, uidBytes.length);
        pos += uidBytes.length;
        data[pos++] = unsignedIntToSignedByte(gidBytes.length);
        System.arraycopy(gidBytes, 0, data, pos, gidBytes.length);

        assertArrayEquals(new byte[]{1, 4, -2, -1, -1, -1, -2, -1, -1, -1}, data);
    }
    @Test
    public void test14() {
        byte[] input = {1, 2, 3, 4};
        byte[] result = getCentralDirectoryData(input);
        assertNotNull(result);
        assertEquals(4, result.length);
    }
    @Test
    public void test15() {
        byte[] input = null;
        byte[] result = getCentralDirectoryData(input);
        assertNull(result);
    }
    @Test
    public void test16() throws ZipException {

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
        parseReparse(555, 555, UNIX_MAX_UID_GID, MAX, MAX); //additional test case
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
    public void test17() throws Exception {
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
    public void test18() throws Exception {
        File archive = getFile("COMPRESS-211_uid_gid_zip_test.zip");
        ZipFile zf = null;
        byte[] buffer = new byte[1024];

        try {
            zf = new ZipFile(archive);
            Enumeration<ZipArchiveEntry> en = zf.getEntries();

            // We expect EVERY entry of this zip file (dir & file) to
            // contain extra field 0x7875.
            while (en.hasMoreElements()) {

                ZipArchiveEntry zae = en.nextElement();
                String name = zae.getName();

                // Read the data with different offset
                int offset = 5;
                int length = buffer.length - offset;
                zf.getCentralDirectory().getData().readFully(buffer, offset, length);

                // Parse from the central directory data with different offset
                X7875_NewUnix xf = new X7875_NewUnix();
                xf.parseFromCentralDirectoryData(buffer, offset, length);

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
    public void test19() throws Exception {
        File archive = getFile("COMPRESS-211_uid_gid_zip_test.zip");
        ZipFile zf = null;
        byte[] buffer = new byte[1024];

        try {
            zf = new ZipFile(archive);
            Enumeration<ZipArchiveEntry> en = zf.getEntries();

            // We expect EVERY entry of this zip file (dir & file) to
            // contain extra field 0x7875.
            while (en.hasMoreElements()) {

                ZipArchiveEntry zae = en.nextElement();
                String name = zae.getName();

                // Read the data with different length
                int offset = 0;
                int length = buffer.length - 1;
                zf.getCentralDirectory().getData().readFully(buffer, offset, length);

                // Parse from the central directory data with different length
                X7875_NewUnix xf = new X7875_NewUnix();
                xf.parseFromCentralDirectoryData(buffer, offset, length);

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
    public void test20() {
        uid = 0;
        gid = 0;
        reset();
        assertEquals(0, uid);
        assertEquals(0, gid);
    }
    @Test
    public void test21() {
        uid = 123;
        gid = 456;
        reset();
        assertEquals(123, uid);
        assertEquals(456, gid);
    }
    @Test
    public void test22() {
        uid = -1;
        gid = -1;
        reset();
        assertEquals(-1, uid);
        assertEquals(-1, gid);
    }
    @Test
    public void test23() {
        uid = Integer.MAX_VALUE;
        gid = Integer.MAX_VALUE;
        reset();
        assertEquals(Integer.MAX_VALUE, uid);
        assertEquals(Integer.MAX_VALUE, gid);
    }
    @Test
    public void test24() {
        uid = Integer.MIN_VALUE;
        gid = Integer.MIN_VALUE;
        reset();
        assertEquals(Integer.MIN_VALUE, uid);
        assertEquals(Integer.MIN_VALUE, gid);
    }
    @Test
    public void test25() {
        uid = 1000000;
        gid = 2000000;
        reset();
        assertEquals(1000000, uid);
        assertEquals(2000000, gid);
    }
    @Test
    public void test26() throws Exception {
        // Test case 1: UID is changed
        xf.setUID(54321);
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertTrue(xf.equals(o));
        assertFalse(xf.equals(o));
        
        // Test case 2: GID is changed
        xf.setGID(98765);
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o2 = xf.clone();
        assertEquals(o2.hashCode(), xf.hashCode());
        assertTrue(xf.equals(o2));
        assertFalse(xf.equals(o2));
    }
    @Test
    public void test27() throws Exception {
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertTrue(xf.equals(o));
        xf.setUID(12345);
        assertFalse(xf.equals(o));
    }
    @Test
    public void test28() throws Exception {
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertFalse(xf.equals(o));
        xf.setUID(67890);
        assertFalse(xf.equals(o));
    }
    @Test
    public void test29() throws Exception {
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertSame(xf, o);
        assertFalse(xf.equals(o));
    }
    @Test
    public void test30() throws Exception {
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertFalse(xf.equals(o));
        xf.setUID(0);
        assertTrue(xf.equals(o));
    }
    @Test
    public void test31() throws Exception {
        File archive = getFile("COMPRESS-211_uid_gid_zip_test.zip");
        ZipFile zf = null;

        try {
            zf = new ZipFile(archive);
            Enumeration<ZipArchiveEntry> en = zf.getEntries();

            // Test Case 1: Changing the version
            while (en.hasMoreElements()) {
                ZipArchiveEntry zae = en.nextElement();
                String name = zae.getName();
                X7875_NewUnix xf = (X7875_NewUnix) zae.getExtraField(X7875);
                xf.setVersion(1); // change version to 1
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
                assertFalse(xf.equals(new X7875_NewUnix(expected, expected))); // mutant-killing test case
            }

            // Test Case 2: Changing the uid
            en = zf.getEntries();
            while (en.hasMoreElements()) {
                ZipArchiveEntry zae = en.nextElement();
                String name = zae.getName();
                X7875_NewUnix xf = (X7875_NewUnix) zae.getExtraField(X7875);
                String uidString = name.split("_")[0].substring(3);
                long uid = Long.parseLong(uidString);
                xf.setUid(uid); // change uid
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
                assertFalse(xf.equals(new X7875_NewUnix(expected, expected))); // mutant-killing test case
            }

            // Test Case 3: Changing the gid
            en = zf.getEntries();
            while (en.hasMoreElements()) {
                ZipArchiveEntry zae = en.nextElement();
                String name = zae.getName();
                X7875_NewUnix xf = (X7875_NewUnix) zae.getExtraField(X7875);
                String gidString = name.split("_")[1].substring(3);
                long gid = Long.parseLong(gidString);
                xf.setGid(gid); // change gid
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
                assertFalse(xf.equals(new X7875_NewUnix(expected, expected))); // mutant-killing test case
            }

        } finally {
            if (zf != null) {
                zf.close();
            }
        }
    }
    @Test
    public void test32() {
        // Test Case 4: Changing the header ID
        X7875_NewUnix xf = new X7875_NewUnix(1000, 1000);
        xf.setHeaderId(123); // change header ID
        assertEquals(123, xf.getHeaderId()); // mutant-killing test case
    }
    @Test
    public void test33() throws Exception {
        // Test Case 5: Changing the type of object being compared
        assertFalse(xf.equals(new X7875_NewUnix(1000, 1000))); // mutant-killing test case
        
        // Test Case 6: Changing the string representation
        assertTrue(xf.toString().startsWith("0x7875 Test Extra Field")); // mutant-killing test case

        // Test Case 7: Changing the UID
        Object o = xf.clone();
        xf.setUid(12345); // change UID
        assertFalse(xf.equals(o)); // mutant-killing test case
    }
}

    @Test
    public void test34() throws Exception {
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
    public void test35() throws Exception {
    }
    
    @Test
    public void test36() throws Exception {
        try {
            zf = new ZipFile(archive);
            Enumeration<ZipArchiveEntry> en = zf.getEntries();

            while (en.hasMoreElements()) {
                ZipArchiveEntry zae = en.nextElement();
                String name = zae.getName();
                X7875_NewUnix xf = (X7875_NewUnix) zae.getExtraField(X7875);

                long expectedUID = -1;
                long expectedGID = -1;
                if (name.contains("uid555_gid555")) {
                    expectedUID = 666;
                    expectedGID = 777;
                } else if (name.contains("uid5555_gid5555")) {
                    expectedUID = 6666;
                    expectedGID = 7777;
                } else if (name.contains("uid55555_gid55555")) {
                    expectedUID = 66666;
                    expectedGID = 77777;
                } else if (name.contains("uid555555_gid555555")) {
                    expectedUID = 666666;
                    expectedGID = 777777;
                } else if (name.contains("min_unix")) {
                    expectedUID = -1;
                    expectedGID = -1;
                } else if (name.contains("max_unix")) {
                    expectedUID = 0x100000000L - 3;
                    expectedGID = 0x100000000L - 4;
                }
                assertEquals(expectedUID, xf.getUID());
                assertEquals(expectedGID, xf.getGID());
            }
        } finally {
            if (zf != null) {
                zf.close();
            }
        }
    }

    @Test
    public void test37() {
    }

}