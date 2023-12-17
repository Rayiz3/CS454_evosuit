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
        assertEquals(ZipShort(1234), xf.getHeaderId());
    }
    @Test
    public void test1() throws Exception {
        File archive = getFile("DIFFERENT_ARCHIVE.zip");
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
    public void test2() throws ZipException {

        // Version=-1, Len=0, Len=0.
        final byte[] NEGATIVE_VERSION = {-1, 0, 0};

        // Version=-1, Len=1, zero, Len=1, zero.
        final byte[] NEGATIVE_UID_GID = {-1, 1, 0, 1, 0};

        // Version=-1, Len=1, one, Len=1, one
        final byte[] NEGATIVE_ONE_UID_GID = {-1, 1, 1, 1, 1};

        // Version=-1, Len=2, one thousand, Len=2, one thousand
        final byte[] NEGATIVE_ONE_THOUSAND_UID_GID = {-1, 2, -24, 3, 2, -24, 3};

        parseReparse(-1, 0, NEGATIVE_VERSION, 0, 0);
        parseReparse(-1, 0, NEGATIVE_UID_GID, 0, 0);
        parseReparse(-1, 1, NEGATIVE_ONE_UID_GID, 1, 1);
        parseReparse(-1, 1000, NEGATIVE_ONE_THOUSAND_UID_GID, 1000, 1000);
    }
    @Test
    public void test3() throws ZipException {
        // Version=1, Len=1, negative one, Len=1, negative one
        final byte[] NEGATIVE_UID_GID = {1, 1, -1, 1, -1};
        
        parseReparse(-1, -1, NEGATIVE_UID_GID, -1, -1);
    }
    @Test
    public void test4() throws ZipException {
        // Version=1, Len=1, zero, Len=1, zero
        final byte[] ZERO_UID_GID = {1, 1, 0, 1, 0};

        parseReparse(0, 0, ZERO_UID_GID, 0, 0);
    }
    @Test
    public void test5() throws ZipException {
        // Version=1, Len=1, ten, Len=1, ten
        final byte[] TEN_UID_GID = {1, 1, 10, 1, 10};

        parseReparse(10, 10, TEN_UID_GID, 10, 10);
    }
    @Test
    public void test6() throws ZipException {

        // Version=1, Len=3, zero, Len=3, zero
        final byte[] ZERO_UID_GID_3 = {1, 3, 0, 0, 0, 3, 0, 0, 0};

        // Version=1, Len=3, one, Len=3, one
        final byte[] ONE_UID_GID_3 = {1, 3, 1, 0, 0, 3, 1, 0, 0};

        // Version=1, Len=3, fifty, Len=3, fifty
        final byte[] FIFTY_UID_GID_3 = {1, 3, 50, 0, 0, 3, 50, 0, 0};

        parseReparse(0, 0, ZERO_LEN, 0, 0);
        parseReparse(0, 0, ZERO_UID_GID, 0, 0);
        parseReparse(1, 1, ONE_UID_GID, 1, 1);
        parseReparse(1000, 1000, ONE_THOUSAND_UID_GID, 1000, 1000);
        parseReparse(MAX, MAX, UNIX_MAX_UID_GID, MAX, MAX);
        parseReparse(-2, -2, UNIX_MAX_UID_GID, MAX, MAX);
        parseReparse(TWO_TO_32, TWO_TO_32 + 1, LENGTH_5, TWO_TO_32, TWO_TO_32 + 1);
        parseReparse(Long.MAX_VALUE - 1, Long.MAX_VALUE, LENGTH_8, Long.MAX_VALUE - 1, Long.MAX_VALUE);

        parseReparse(0, 0, ZERO_UID_GID_3, 0, 0);
        parseReparse(1, 1, ONE_UID_GID_3, 1, 1);
        parseReparse(50, 50, FIFTY_UID_GID_3, 50, 50);
    }
    @Test
    public void test7() {
        setGID(0);
        assertEquals(0, this.gid);
        
        setGID(1);
        assertEquals(1, this.gid);
        
        setGID(1000);
        assertEquals(1000, this.gid);
        
        setGID(-2);
        assertEquals(-2, this.gid);
        
        setGID(4294967296L);
        assertEquals(4294967296L, this.gid);
        
        setGID(Long.MAX_VALUE);
        assertEquals(Long.MAX_VALUE, this.gid);
    }
    @Test
    public void test8() throws Exception {
        // Testing different uidSize and gidSize values
        int[] uidSizes = {1, 2, 3, 4, 5};
        int[] gidSizes = {1, 2, 3, 4, 5};

        for (int uidSize : uidSizes) {
            for (int gidSize : gidSizes) {
                byte[] uid = new byte[uidSize];
                byte[] gid = new byte[gidSize];
                byte[] expected = new byte[3 + uidSize + gidSize];
                expected[0] = 1; // version
                expected[1] = (byte) uidSize; // uidsize
                expected[2] = (byte) gidSize; // gidsize

                // Testing uid and gid values in range [0,1]
                Arrays.fill(uid, (byte) 0);
                Arrays.fill(gid, (byte) 0);
                System.arraycopy(uid, 0, expected, 3, uidSize);
                System.arraycopy(gid, 0, expected, 3 + uidSize, gidSize);
                ZipShort expectedDataLength = new ZipShort(expected.length);
                assertTestData(uid, gid, expectedDataLength, expected);

                // Testing uid and gid values in range [1,2]
                Arrays.fill(uid, (byte) 1);
                Arrays.fill(gid, (byte) 1);
                System.arraycopy(uid, 0, expected, 3, uidSize);
                System.arraycopy(gid, 0, expected, 3 + uidSize, gidSize);
                expectedDataLength = new ZipShort(expected.length);
                assertTestData(uid, gid, expectedDataLength, expected);

                // Testing uid and gid values in range [10,20]
                Arrays.fill(uid, (byte) 10);
                Arrays.fill(gid, (byte) 20);
                System.arraycopy(uid, 0, expected, 3, uidSize);
                System.arraycopy(gid, 0, expected, 3 + uidSize, gidSize);
                expectedDataLength = new ZipShort(expected.length);
                assertTestData(uid, gid, expectedDataLength, expected);
            }
        }

        // Testing edge case where uidSize and gidSize = 255
        int uidSize = 255;
        int gidSize = 255;
        byte[] uid = new byte[uidSize];
        byte[] gid = new byte[gidSize];
        byte[] expected = new byte[3 + uidSize + gidSize];
        expected[0] = 1; // version
        expected[1] = (byte) uidSize; // uidsize
        expected[2] = (byte) gidSize; // gidsize
        Arrays.fill(uid, (byte) 0);
        Arrays.fill(gid, (byte) 0);
        System.arraycopy(uid, 0, expected, 3, uidSize);
        System.arraycopy(gid, 0, expected, 3 + uidSize, gidSize);
        ZipShort expectedDataLength = new ZipShort(expected.length);
        assertTestData(uid, gid, expectedDataLength, expected);
    }
    private void assertTestData(byte[] uid, byte[] gid, ZipShort expectedDataLength, byte[] expected) {
        int uidValue = new BigInteger(uid).intValue();
        int gidValue = new BigInteger(gid).intValue();
        ZipShort result = getLocalFileDataLength(uidValue, gidValue);
        assertArrayEquals(expected, result.getBytes());
        assertEquals(expectedDataLength.getValue(), result.getValue());
    }
    private ZipShort getLocalFileDataLength(int uidSize, int gidSize) {
        int uidByteSize = uidSize <= 0 ? 0 : BigInteger.valueOf(uidSize).toByteArray().length;
        int gidByteSize = gidSize <= 0 ? 0 : BigInteger.valueOf(gidSize).toByteArray().length;

        // The 3 comes from: version=1 + uidsize=1 + gidsize=1
        return new ZipShort(3 + uidByteSize + gidByteSize);
    }
    @Test
    public void test9() throws Exception {
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
    public void test10() {
        X7875_NewUnix xf = new X7875_NewUnix();
        xf.parseFromLocalFileData(new byte[0], 0, 0);
        assertEquals(0, xf.getUID());
        assertEquals(0, xf.getGID());

        byte[] data = xf.getLocalFileDataData();
        assertEquals(3, data.length);
        assertEquals((byte)1, data[0]);
        assertEquals((byte)0, data[1]);
        assertEquals((byte)0, data[2]);
    }
    @Test
    public void test11() {
        X7875_NewUnix xf = new X7875_NewUnix();
        xf.parseFromLocalFileData(new byte[]{1, 1, -1, 1, -128}, 0, 5);
        assertEquals(255, xf.getUID());
        assertEquals(128, xf.getGID());

        byte[] data = xf.getLocalFileDataData();
        assertEquals(5, data.length);
        assertEquals((byte)1, data[0]);
        assertEquals((byte)1, data[1]);
        assertEquals((byte)-1, data[2]);
        assertEquals((byte)1, data[3]);
        assertEquals((byte)-128, data[4]);

        xf.parseFromLocalFileData(new byte[]{1, 2, -1, -1, 2, 1, 2}, 0, 7);
        assertEquals(65535, xf.getUID());
        assertEquals(513, xf.getGID());

        data = xf.getLocalFileDataData();
        assertEquals(7, data.length);
        assertEquals((byte)1, data[0]);
        assertEquals((byte)2, data[1]);
        assertEquals((byte)-1, data[2]);
        assertEquals((byte)-1, data[3]);
        assertEquals((byte)2, data[4]);
        assertEquals((byte)1, data[5]);
        assertEquals((byte)2, data[6]);
    }
    @Test
    public void test12() {
        X7875_NewUnix xf = new X7875_NewUnix();
        xf.parseFromLocalFileData(new byte[]{1, 5, 0, 0, 0, 0, 1}, 0, 7);
        assertEquals(0x100000000L, xf.getUID());
        assertEquals(0x100000001L, xf.getGID());

        byte[] data = xf.getLocalFileDataData();
        assertEquals(7, data.length);
        assertEquals((byte)1, data[0]);
        assertEquals((byte)5, data[1]);
        assertEquals((byte)0, data[2]);
        assertEquals((byte)0, data[3]);
        assertEquals((byte)0, data[4]);
        assertEquals((byte)0, data[5]);
        assertEquals((byte)1, data[6]);

        xf.parseFromLocalFileData(new byte[]{1, 8, -2, -1, -1, -1, -1, -1, -1, 127}, 0, 9);
        assertEquals(Long.MAX_VALUE - 1, xf.getUID());
        assertEquals(Long.MAX_VALUE, xf.getGID());

        data = xf.getLocalFileDataData();
        assertEquals(9, data.length);
        assertEquals((byte)1, data[0]);
        assertEquals((byte)8, data[1]);
        assertEquals((byte)-2, data[2]);
        assertEquals((byte)-1, data[3]);
        assertEquals((byte)-1, data[4]);
        assertEquals((byte)-1, data[5]);
        assertEquals((byte)-1, data[6]);
        assertEquals((byte)-1, data[7]);
        assertEquals((byte)-1, data[8]);
    }
public class MyClassTest {
    
    @Test
    public void test13() {
        // Test case 1
        byte[] result1 = getCentralDirectoryData(new byte[] {1, 2, 3, 4});
        // Assert statements
        
        // Test case 2
        byte[] result2 = getCentralDirectoryData(new byte[] {5, 6, 7, 8});
        // Assert statements
        
        // Test case 3
        byte[] result3 = getCentralDirectoryData(new byte[] {9, 10, 11, 12});
        // Assert statements
        
        // Additional test case - changing input value
        byte[] result4 = getCentralDirectoryData(new byte[0]);  // Empty array
        // Assert statements
        
        // Additional test case - changing input value
        byte[] result5 = getCentralDirectoryData(new byte[] {1});  // Single element array
        // Assert statements
    }
    
    public byte[] getCentralDirectoryData(byte[] input) {
        // Method implementation
    }
}
    @Test
    public void test14() throws Exception {
        // Regression tests to kill more mutants

        // Test case 1: Zero version
        // Version=0, Len=1, zero, Len=1, zero
        final byte[] ZERO_VERSION_UID_GID = {0, 1, 0, 1, 0};
        parseReparse(0, 0, ZERO_VERSION_UID_GID, 0, 0);

        // Test case 2: Negative version
        // Version=-1, Len=1, zero, Len=1, zero
        final byte[] NEGATIVE_VERSION_UID_GID = {-1, 1, 0, 1, 0};
        parseReparse(255, 0, NEGATIVE_VERSION_UID_GID, 0, 0);

        // Test case 3: Zero uid/gid size
        // Version=1, Len=0, Len=0
        final byte[] ZERO_UID_GID_SIZE = {1, 0, 0};
        parseReparse(1, 0, ZERO_UID_GID_SIZE, 0, 0);

        // Test case 4: Large uid/gid size
        // Version=1, Len=300, 255s, Len=300, 255s
        final byte[] LARGE_UID_GID_SIZE = new byte[602];
        Arrays.fill(LARGE_UID_GID_SIZE, (byte)255);
        LARGE_UID_GID_SIZE[0] = 1;
        LARGE_UID_GID_SIZE[1] = -54;
        LARGE_UID_GID_SIZE[2] = 1;
        parseReparse(1, 1, LARGE_UID_GID_SIZE, 1, 1);

        // Test case 5: Negative uid/gid size
        // Version=1, Len=-1, zero, Len=-1, zero
        final byte[] NEGATIVE_UID_GID_SIZE = {1, -1, 0, -1, 0};
        parseReparse(1, 255, NEGATIVE_UID_GID_SIZE, 255, 255);

        // Test case 6: Zero uid/gid bytes
        // Version=1, Len=1, zero, Len=0
        final byte[] ZERO_UID_GID_BYTES = {1, 1, 0, 0};
        parseReparse(1, 1, ZERO_UID_GID_BYTES, 0, 1);

        // Test case 7: Large uid/gid bytes
        // Version=1, Len=10, 255s, Len=10, 255s
        final byte[] LARGE_UID_GID_BYTES = new byte[31];
        Arrays.fill(LARGE_UID_GID_BYTES, (byte)255);
        LARGE_UID_GID_BYTES[0] = 1;
        LARGE_UID_GID_BYTES[1] = 10;
        LARGE_UID_GID_BYTES[2] = -1;
        LARGE_UID_GID_BYTES[13] = -1;
        LARGE_UID_GID_BYTES[30] = -1;
        parseReparse(1, 10, LARGE_UID_GID_BYTES, 0xffffffff, 0xffffffff);

        // Test case 8: Negative uid/gid bytes
        // Version=1, Len=1, ones, Len=1, ones
        final byte[] NEGATIVE_UID_GID_BYTES = {1, 1, -1, 1, -1};
        parseReparse(1, 1, NEGATIVE_UID_GID_BYTES, -1L, -1L);
    }
    @Test
    public void test15() throws Exception {
        byte[] buffer = new byte[0];
        int offset = 0;
        int length = 0;
        
        // Expecting a ZipException to be thrown due to empty buffer
        assertThrows(ZipException.class, () -> {
            parseFromCentralDirectoryData(buffer, offset, length);
        });
    }
    @Test
    public void test16() throws Exception {
        byte[] buffer = new byte[10];
        int offset = -1;
        int length = 10;
        
        // Expecting a ZipException to be thrown due to negative offset
        assertThrows(ZipException.class, () -> {
            parseFromCentralDirectoryData(buffer, offset, length);
        });
    }
    @Test
    public void test17() throws Exception {
        byte[] buffer = new byte[10];
        int offset = 0;
        int length = -1;
        
        // Expecting a ZipException to be thrown due to negative length
        assertThrows(ZipException.class, () -> {
            parseFromCentralDirectoryData(buffer, offset, length);
        });
    }
    @Test
    public void test18() throws Exception {
        byte[] buffer = new byte[10];
        int offset = 5;
        int length = 10;
        
        // Expecting a ZipException to be thrown due to buffer out of bounds
        assertThrows(ZipException.class, () -> {
            parseFromCentralDirectoryData(buffer, offset, length);
        });
    }
    @Test
    public void test19() throws Exception {
        byte[] buffer = new byte[10];
        int offset = 0;
        int length = 10;

        // Expecting the method to execute successfully without throwing any exception
        assertDoesNotThrow(() -> {
            parseFromCentralDirectoryData(buffer, offset, length);
        });
    }
    @Test
    public void test20() {
        assertEquals(X7875, xf.getHeaderId());
        // change input to different id
        assertEquals(DIFFERENT_ID, xf.getHeaderId());
    }
    @Test
    public void test21() throws Exception {
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertTrue(xf.equals(o));
        xf.setUID(12345);
        assertFalse(xf.equals(o));
        // change input to different UID
        xf.setUID(67890);
        assertFalse(xf.equals(o));
    }
    @Test
    public void test22() {
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
        // change input to different array
        assertTrue(Arrays.equals(DIFFERENT_SEQUENCE6, trimTest(DIFFERENT_SEQUENCE)));
    }
    @Test
    public void test23() throws Exception {
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
            // change input to different UID
            expected = 2000;
            assertEquals(expected, xf.getUID());
            assertEquals(expected, xf.getGID());

        } finally {
            if (zf != null) {
                zf.close();
            }
        }
    }
    @Test
    public void test24() throws ZipException {

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
        // change input to different byte array
        final byte[] SPURIOUS_ZEROES_2 = {1, 4, -1, -1, 0, 0, 4, 1, 2, 0, 0};
        final byte[] EXPECTED_2 = {1, 2, -1, -1, 2, 1, 2};
        xf.parseFromLocalFileData(SPURIOUS_ZEROES_2, 0, SPURIOUS_ZEROES_2.length);

        assertEquals(65535, xf.getUID());
        assertEquals(513, xf.getGID());
        assertTrue(Arrays.equals(EXPECTED_2, xf.getLocalFileDataData()));
    }
    @Test
    public void test25() throws Exception {
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertTrue(xf.equals(o));
        xf.setUID(12345);
        assertFalse(xf.equals(o));
    }
    @Test
    public void test26() throws Exception {
        Object obj = new Object();
        assertFalse(xf.equals(obj));
    }
    @Test
    public void test27() throws Exception {
        assertTrue(xf.toString().startsWith("0x7"));
    }
    @Test
    public void test28() throws Exception {
        Object o = xf.clone();
        assertNotEquals(o.hashCode(), xf.hashCode());
    }
    @Test
    public void test29() throws Exception {
        Object o = xf.clone();
        xf.setUID(98765);
        assertFalse(xf.equals(o));
    }
@Test
public void test30() throws Exception {
    assertFalse(xf.equals(new Object()));
    assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
    Object o = xf.clone();
    assertEquals(o.hashCode(), xf.hashCode());
    assertTrue(xf.equals(o));
    xf.setUID(54321);
    assertFalse(xf.equals(o));
}
@Test
public void test31() throws Exception {
    assertFalse(xf.equals(new Object()));
    assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
    Object o = xf.clone();
    assertEquals(o.hashCode(), xf.hashCode());
    assertTrue(xf.equals(o));
    xf.setUID(null);
    assertFalse(xf.equals(o));
}
    @Test
    public void test32() {
        // Change the expected value of getHeaderId() to an incorrect value
        assertEquals(0, xf.getHeaderId());
    }
    @Test
    public void test33() throws Exception {
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertTrue(xf.equals(o));

        // Change the value of uid to be equal to gid
        xf.setUID(gid);
        assertFalse(xf.equals(o));
    }
    @Test
    public void test34() throws Exception {
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

                // Change the expected value of UID and GID to an incorrect value
                if (name.contains("uid555_gid555")) {
                    expected = 10000;
                } else if (name.contains("uid5555_gid5555")) {
                    expected = -5555;
                } else if (name.contains("uid55555_gid55555")) {
                    expected = 0;
                } else if (name.contains("uid555555_gid555555")) {
                    expected = 12345;
                } else if (name.contains("min_unix")) {
                    expected = -1;
                } else if (name.contains("max_unix")) {
                    expected = Long.MAX_VALUE;
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
    public void test35() {
        X7875_NewUnix xf1 = new X7875_NewUnix();
        xf1.setVersion(1);
        xf1.setUID(12345);
        xf1.setGID(67890);
        int hc1 = -1234567 * xf1.getVersion();
        hc1 ^= Integer.rotateLeft(xf1.getUID().hashCode(), 16);
        hc1 ^= xf1.getGID().hashCode();

        X7875_NewUnix xf2 = new X7875_NewUnix();
        xf2.setVersion(2);
        xf2.setUID(54321);
        xf2.setGID(98760);
        int hc2 = -1234567 * xf2.getVersion();
        hc2 ^= Integer.rotateLeft(xf2.getUID().hashCode(), 16);
        hc2 ^= xf2.getGID().hashCode();

        assertNotEquals(hc1, hc2);
    }
    @Test
    public void test36() {
        X7875_NewUnix xf1 = new X7875_NewUnix();
        xf1.setVersion(1);
        xf1.setUID(12345);
        xf1.setGID(67890);

        X7875_NewUnix xf2 = new X7875_NewUnix();
        xf2.setVersion(1);
        xf2.setUID(12345);
        xf2.setGID(67890);

        assertTrue(xf1.equals(xf2));
        assertTrue(xf2.equals(xf1));

        xf2.setUID(54321);
        assertFalse(xf1.equals(xf2));
        assertFalse(xf2.equals(xf1));
    }
    @Test
    public void test37() {
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
        final byte[] LONGER_SEQ = {2, 3, 4, 5, 6};
        final byte[] LONGER_SEQ_LEADING_ZERO = {0, 2, 3, 4, 5, 6};
        final byte[] LONGER_SEQ_LEADING_ZEROES = {0, 0, 0, 0, 0, 0, 0, 2, 3, 4, 5, 6};
        
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
        assertTrue(Arrays.equals(LONGER_SEQ, trimTest(LONGER_SEQ)));
        assertTrue(Arrays.equals(LONGER_SEQ, trimTest(LONGER_SEQ_LEADING_ZERO)));
        assertTrue(Arrays.equals(LONGER_SEQ, trimTest(LONGER_SEQ_LEADING_ZEROES)));
    }
}