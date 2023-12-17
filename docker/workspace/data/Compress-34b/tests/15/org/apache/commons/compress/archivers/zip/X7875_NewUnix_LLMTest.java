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
                } else if (name.contains("different_name")) {
                    expected = 123;
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
    public void test1() {
        ZipShort differentHeaderId = new ZipShort(9999);
        assertEquals(differentHeaderId, xf.getHeaderId());
    }
    @Test
    public void test2() throws ZipException {
        // Existing test cases...

        // Additional test cases
        parseReparse(Long.MAX_VALUE, Long.MAX_VALUE - 1, LENGTH_8, Long.MAX_VALUE, Long.MAX_VALUE - 1);
        parseReparse(-1, -1, UNIX_MAX_UID_GID, MAX, MAX);

    }
    @Test
    public void test3() {
        // Test case with minimum input value
        assertEquals(0L, ZipUtil.bigToLong(0));
        
        // Test case with maximum input value
        assertEquals(0xFFFFFFFFL, ZipUtil.bigToLong(-2));
        
        // Test case with a positive input value
        assertEquals(1000L, ZipUtil.bigToLong(1000));
        
        // Test case with a negative input value
        assertEquals(-1000L, ZipUtil.bigToLong(-1000));
    }
@Test
public void test4() throws Exception {
    assertFalse(xf.equals(new Object()));
    assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
    Object o = xf.clone();
    assertEquals(o.hashCode(), xf.hashCode());
    assertTrue(xf.equals(o));
    xf.setUID(-12345);
    assertFalse(xf.equals(o));
}
@Test
public void test5() throws Exception {
    // Version=1, Len=0, Len=0 with negative value.
    final byte[] ZERO_LEN = {1, -1, -1};

    parseReparse(0, 0, ZERO_LEN, 0, 0);
}
@Test
public void test6() throws ZipException {
    // Version=1, Len=2, negative one thousand, Len=2, negative one thousand
    final byte[] ONE_THOUSAND_UID_GID = {1, 2, -24, 3, -25, 3};

    parseReparse(-1000, -1000, ONE_THOUSAND_UID_GID, -1000, -1000);

    // Version=1, Len=5, negative 2^32, Len=5, negative 2^32 - 1
    final byte[] LENGTH_5 = {1, 5, -1, -1, -1, -1, -2};

    final long NEGATIVE_TWO_TO_32 = -0x100000000L;

    parseReparse(NEGATIVE_TWO_TO_32, NEGATIVE_TWO_TO_32 + 1, LENGTH_5, NEGATIVE_TWO_TO_32, NEGATIVE_TWO_TO_32 + 1);

    // Version=1, Len=8, negative 2^63 + 1, Len=8, negative 2^63 + 2
    final byte[] LENGTH_8 = {1, 8, 1, 0, 0, 0, 0, 0, 0, -128, 8, 1, 0, 0, 0, 0, 0, 0, -127};

    final long NEGATIVE_MAX = Long.MIN_VALUE + 1;

    parseReparse(NEGATIVE_MAX, NEGATIVE_MAX + 1, LENGTH_8, NEGATIVE_MAX, NEGATIVE_MAX + 1);
}
    @Test
    public void test7() throws ZipException {

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
        final byte[] LENGTH_5 = {1, 5, 0, 0, 0, 0, 1};

        // Version=1, Len=8, 2^63 - 2, Len=8, 2^63 - 1
        // Esoteric test:  can we handle 64 bit numbers?
        final byte[] LENGTH_8 = {1, 8, -2, -1, -1, -1, -1, -1, -1, 127};

        final long TWO_TO_32 = 0x100000000L;
        final long MAX = TWO_TO_32 - 2;

        parseReparse(50, 0, ZERO_LEN, 0, 0);
        parseReparse(0, 10, ZERO_LEN, 0, 0);
        parseReparse(5, 5, ZERO_LEN, 0, 0);
        parseReparse(0, 0, ZERO_UID_GID, 0, 0);
        parseReparse(1, 1, ZERO_UID_GID, 0, 0);
        parseReparse(0, 0, ONE_UID_GID, 0, 0);
        parseReparse(100, 100, ONE_UID_GID, 0, 0);
        parseReparse(MAX, MAX, ONE_UID_GID, 0, 0);
        parseReparse(-2, -2, ONE_UID_GID, 0, 0);
        parseReparse(TWO_TO_32, TWO_TO_32 + 1, ONE_UID_GID, 0, 0);
        parseReparse(Long.MAX_VALUE - 1, Long.MAX_VALUE, ONE_UID_GID, 0, 0);
        parseReparse(0, 0, ONE_THOUSAND_UID_GID, 0, 0);
        parseReparse(-1000, -1000, ONE_THOUSAND_UID_GID, 0, 0);
        parseReparse(MAX + 1, MAX + 1, UNIX_MAX_UID_GID, 0, 0);
        parseReparse(0, 0, UNIX_MAX_UID_GID, 0, 0);
        parseReparse(TWO_TO_32, TWO_TO_32, UNIX_MAX_UID_GID, TWO_TO_32, TWO_TO_32);
        parseReparse(0, 0, LENGTH_5, 0, 0);
        parseReparse(0, 0, LENGTH_8, 0, 0);

        // We never emit this, but we should be able to parse it:
        final byte[] SPURIOUS_ZEROES_1 = {1, 4, -1, 0, 0, 0, 4};
        final byte[] EXPECTED_1 = {1, 1, -1, 1};
        xf.parseFromLocalFileData(SPURIOUS_ZEROES_1, 0, SPURIOUS_ZEROES_1.length);

        assertEquals(255, xf.getUID());
        assertEquals(0, xf.getGID());
        assertTrue(Arrays.equals(EXPECTED_1, xf.getLocalFileDataData()));

        final byte[] SPURIOUS_ZEROES_2 = {1, 4, -1, -1, 0, 0, 4};
        final byte[] EXPECTED_2 = {1, 2, -1, -1, 2};
        xf.parseFromLocalFileData(SPURIOUS_ZEROES_2, 0, SPURIOUS_ZEROES_2.length);

        assertEquals(65535, xf.getUID());
        assertEquals(0, xf.getGID());
        assertTrue(Arrays.equals(EXPECTED_2, xf.getLocalFileDataData()));
    }
    @Test
    public void test8() {
        // Test case where uidSize is 0 and gidSize is 0
        int uidSize = 0;
        int gidSize = 0;
        assertEquals(3, getLocalFileDataLength(uidSize, gidSize).getValue());

        // Test case where uidSize is 1 and gidSize is 1
        uidSize = 1;
        gidSize = 1;
        assertEquals(5, getLocalFileDataLength(uidSize, gidSize).getValue());

        // Test case where uidSize is 2 and gidSize is 3
        uidSize = 2;
        gidSize = 3;
        assertEquals(8, getLocalFileDataLength(uidSize, gidSize).getValue());

        // Test case where uidSize is 5 and gidSize is 10
        uidSize = 5;
        gidSize = 10;
        assertEquals(18, getLocalFileDataLength(uidSize, gidSize).getValue());

        // Test case where uidSize is 10 and gidSize is 5
        uidSize = 10;
        gidSize = 5;
        assertEquals(18, getLocalFileDataLength(uidSize, gidSize).getValue());
    }
    @Test
    public void test9() throws ZipException {
        
        // Regression tests for ZERO_LEN case
        parseReparse(0, 0, new byte[]{1,0,1}, 0, 0);
        parseReparse(0, 0, new byte[]{1,0,-1}, 0, 0);
        
        // Regression tests for ZERO_UID_GID case
        parseReparse(0, 0, new byte[]{1,1,1}, 1, 0);
        parseReparse(0, 0, new byte[]{1,1,-1}, 1, 0);
        
        // Regression tests for ONE_UID_GID case
        parseReparse(0, 0, new byte[]{1,1,0}, 0, 1);
        parseReparse(0, 0, new byte[]{1,1,2}, 0, 1);
        
        // Regression tests for ONE_THOUSAND_UID_GID case
        parseReparse(0, 0, new byte[]{1,2,-23,3,2,-24,3}, 2, 2);
        parseReparse(0, 0, new byte[]{1,2,-25,3,2,-24,3}, 2, 2);
        
        // Regression tests for UNIX_MAX_UID_GID case
        parseReparse(0, 0, new byte[]{1,4,-1,-1,-1,-1,4,-1,-1,-1,-1}, -1, -1);
        parseReparse(0, 0, new byte[]{1,4,0,0,0,0,4,0,0,0,0}, 0, 0);
        
        // Regression tests for LENGTH_5 case
        parseReparse(0, 0, new byte[]{1,5,-1,-1,-1,-1,-1}, -1, -1);
        parseReparse(0, 0, new byte[]{1,5,0,0,0,0,0}, 0, 0);
        
        // Regression tests for LENGTH_8 case
        parseReparse(0, 0, new byte[]{1,8,-1,-1,-1,-1,-1,-1,-1,-1}, Long.MAX_VALUE, Long.MAX_VALUE - 1);
        parseReparse(0, 0, new byte[]{1,8,0,0,0,0,0,0,0,0}, 0, 0);
        
        // Regression tests for SPURIOUS_ZEROES_1 case
        parseReparse(0, 0, new byte[]{1,4,0,0,1,0,4,-127,0,0}, 1, 128);
        parseReparse(0, 0, new byte[]{1,4,-2,-2,0,0,4,-2,0,0}, UNIX_MAX_UID_GID[0], UNIX_MAX_UID_GID[1]);
        
        // Regression tests for SPURIOUS_ZEROES_2 case
        parseReparse(0, 0, new byte[]{1,4,-2,-2,1,0,4,2,1,0}, UNIX_MAX_UID_GID[2], UNIX_MAX_UID_GID[3]);
        parseReparse(0, 0, new byte[]{1,4,-1,-1,0,0,4,-1,0,0}, UNIX_MAX_UID_GID[2], UNIX_MAX_UID_GID[3]);
    }
    @Test
    public void test10() throws Exception {
        File archive = getFile("COMPRESS-211_uid_gid_zip_test.zip");
        ZipFile zf = null;

        try {
            zf = new ZipFile(archive);
            Enumeration<ZipArchiveEntry> en = zf.getEntries();

            while (en.hasMoreElements()) {
                ZipArchiveEntry zae = en.nextElement();
                String name = zae.getName();
                X7875_NewUnix xf = (X7875_NewUnix) zae.getExtraField(X7875);

                long expected = -1;
                if (name.contains("negative")) {
                    expected = -100;
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
    public void test11() throws Exception {
        File archive = getFile("COMPRESS-211_uid_gid_zip_test.zip");
        ZipFile zf = null;

        try {
            zf = new ZipFile(archive);
            Enumeration<ZipArchiveEntry> en = zf.getEntries();

            while (en.hasMoreElements()) {
                ZipArchiveEntry zae = en.nextElement();
                String name = zae.getName();
                X7875_NewUnix xf = (X7875_NewUnix) zae.getExtraField(X7875);

                byte[] empty = new byte[0];
                assertEquals(0, xf.getUID());
                assertEquals(0, xf.getGID());
                assertTrue(Arrays.equals(empty, xf.getLocalFileDataData()));
            }
        } finally {
            if (zf != null) {
                zf.close();
          }
        }
    }
    @Test
    public void test12() throws Exception {
        File archive = getFile("COMPRESS-211_uid_gid_zip_test.zip");
        ZipFile zf = null;

        try {
            zf = new ZipFile(archive);
            Enumeration<ZipArchiveEntry> en = zf.getEntries();

            while (en.hasMoreElements()) {
                ZipArchiveEntry zae = en.nextElement();
                String name = zae.getName();
                X7875_NewUnix xf = (X7875_NewUnix) zae.getExtraField(X7875);

                long expected = 1000000000L;
                if (name.contains("large")) {
                    expected = 2000000000L;
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
    public void test13() throws Exception {
        File archive = getFile("COMPRESS-211_uid_gid_zip_test.zip");
        ZipFile zf = null;

        try {
            zf = new ZipFile(archive);
            Enumeration<ZipArchiveEntry> en = zf.getEntries();

            while (en.hasMoreElements()) {
                ZipArchiveEntry zae = en.nextElement();
                String name = zae.getName();
                X7875_NewUnix xf = (X7875_NewUnix) zae.getExtraField(X7875);

                byte[] invalidLength = {1, 5, 0, 0, 0};
                byte[] validExtraData = {1, 5, 0, 0, 0, 0, 1};
                if (name.contains("invalidLength")) {
                    xf.parseFromLocalFileData(invalidLength, 0, invalidLength.length);
                } else {
                    xf.parseFromLocalFileData(validExtraData, 0, validExtraData.length);
                }
                assertEquals(0, xf.getUID());
                assertEquals(0, xf.getGID());
                assertTrue(Arrays.equals(validExtraData, xf.getLocalFileDataData()));
            }
        } finally {
            if (zf != null) {
                zf.close();
          }
        }
    }
    @Test
    public void test14() throws Exception {
        File archive = getFile("COMPRESS-211_uid_gid_zip_test.zip");
        ZipFile zf = null;

        try {
            zf = new ZipFile(archive);
            Enumeration<ZipArchiveEntry> en = zf.getEntries();

            while (en.hasMoreElements()) {
                ZipArchiveEntry zae = en.nextElement();
                String name = zae.getName();
                X7875_NewUnix xf = (X7875_NewUnix) zae.getExtraField(X7875);

                xf.parseFromLocalFileData(null, 0, 0);
                byte[] empty = new byte[0];
                assertEquals(0, xf.getUID());
                assertEquals(0, xf.getGID());
                assertTrue(Arrays.equals(empty, xf.getLocalFileDataData()));
            }
        } finally {
            if (zf != null) {
                zf.close();
          }
        }
    }
    @Test
    public void test15() throws Exception {
        File archive = getFile("COMPRESS-211_uid_gid_zip_test.zip");
        ZipFile zf = null;

        try {
            zf = new ZipFile(archive);
            Enumeration<ZipArchiveEntry> en = zf.getEntries();

            while (en.hasMoreElements()) {
                ZipArchiveEntry zae = en.nextElement();
                String name = zae.getName();
                X7875_NewUnix xf = (X7875_NewUnix) zae.getExtraField(X7875);

                byte[] invalidVersionAndLength = {0, 0, 0};
                byte[] validExtraData = {1, 5, 0, 0, 0, 0, 1};
                if (name.contains("invalidVersionAndLength")) {
                    xf.parseFromLocalFileData(invalidVersionAndLength, 0, invalidVersionAndLength.length);
                } else {
                    xf.parseFromLocalFileData(validExtraData, 0, validExtraData.length);
                }
                assertEquals(0, xf.getUID());
                assertEquals(0, xf.getGID());
                assertTrue(Arrays.equals(validExtraData, xf.getLocalFileDataData()));
            }
        } finally {
            if (zf != null) {
                zf.close();
          }
        }
    }
    @Test
    public void test16() {
        // Test case 1: Empty byte array
        byte[] expected1 = new byte[0];
        byte[] result1 = getCentralDirectoryData();
        assertArrayEquals(expected1, result1);

        // Test case 2: Non-empty byte array
        byte[] expected2 = {1, 2, 3, 4};
        byte[] result2 = getCentralDirectoryData();
        assertArrayEquals(expected2, result2);

        // Test case 3: Null input
        byte[] expected3 = null;
        byte[] result3 = getCentralDirectoryData();
        assertArrayEquals(expected3, result3);
    }
    @Test
    public void test17() throws Exception {
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
    public void test18() throws ZipException {

        // Version=1, Len=0, Len=0.
        final byte[] ZERO_LEN = {1, 0, 0};

        // Version=1, Len=1, zero, Len=1, zero.
        final byte[] ZERO_UID_GID = {1, 1, 0, 1, 0};

        // Version=1, Len=1, one, Len=1, one
        final byte[] ONE_UID_GID = {1, 1, 1, 1, 1};

        // Version=1, Len=2, one thousand, Len=2, one thousand
        final byte[] ONE_THOUSAND_UID_GID = {1, 2, -24, 3, 2, -24, 3};

        // (2^32 - 2)
        final byte[] UNIX_MAX_UID_GID = {1, 4, -2, -1, -1, -1, 4, -2, -1, -1, -1};

        // Version=1, Len=5, 2^32, Len=5, 2^32 + 1
        final byte[] LENGTH_5 = {1, 5, 0, 0, 0, 0, 1, 5, 1, 0, 0, 0, 1};

        // Version=1, Len=8, 2^63 - 2, Len=8, 2^63 - 1
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
    public void test19() throws ZipException {
        byte[] invalidData = {0};
        assertThrows(ZipException.class, () -> {
            xf.parseFromLocalFileData(invalidData, 0, invalidData.length);
        });
    }
    @Test
    public void test20() throws ZipException {
        byte[] longData = new byte[Integer.MAX_VALUE];
        assertThrows(ZipException.class, () -> {
            xf.parseFromLocalFileData(longData, 0, longData.length);
        });
    }
    @Test
    public void test21() throws ZipException {
        byte[] data = {1, 2, 3};
        int invalidOffset = 4;
        assertThrows(ZipException.class, () -> {
            xf.parseFromLocalFileData(data, invalidOffset, data.length);
        });
    }
    @Test
    public void test22() throws ZipException {
        byte[] data = {1, 2, 3};
        int invalidLength = -1;
        assertThrows(ZipException.class, () -> {
            xf.parseFromLocalFileData(data, 0, invalidLength);
        });
    }
    @Test
    public void test23() throws ZipException {
        byte[] emptyData = {};
        xf.parseFromLocalFileData(emptyData, 0, emptyData.length);
        assertNull(xf.getUID());
        assertNull(xf.getGID());
    }
    @Test
    public void test24() throws ZipException {
        byte[] zeroUIDAndGID = {1, 1, 0, 0, 0};
        xf.parseFromLocalFileData(zeroUIDAndGID, 0, zeroUIDAndGID.length);
        assertEquals(0, xf.getUID());
        assertEquals(0, xf.getGID());
    }
    @Test
    public void test25() throws ZipException {
        byte[] trueUIDAndGID = {1, 1, 1, 1, 1};
        xf.parseFromLocalFileData(trueUIDAndGID, 0, trueUIDAndGID.length);
        assertEquals(1, xf.getUID());
        assertEquals(1, xf.getGID());
    }
    @Test
    public void test26() throws ZipException {
        byte[] largeUIDAndGID = {1, 2, 127, -1, -1, -1, 127, -2, -1, -1, -1};
        xf.parseFromLocalFileData(largeUIDAndGID, 0, largeUIDAndGID.length);
        assertEquals(2147483647, xf.getUID());
        assertEquals(-2147483647, xf.getGID());
    }
    @Test
    public void test27() throws Exception {
        byte[] buffer = new byte[0];
        int offset = 0;
        int length = 0;

        try {
            parseFromCentralDirectoryData(buffer, offset, length);
            fail("Expected ZipException");
        } catch (ZipException e) {
            // Expected exception
        }
    }
    @Test
    public void test28() throws Exception {
        byte[] buffer = new byte[10];
        int offset = -1;
        int length = 10;

        try {
            parseFromCentralDirectoryData(buffer, offset, length);
            fail("Expected ZipException");
        } catch (ZipException e) {
            // Expected exception
        }
    }
    @Test
    public void test29() throws Exception {
        byte[] buffer = new byte[10];
        int offset = 20;
        int length = 10;

        try {
            parseFromCentralDirectoryData(buffer, offset, length);
            fail("Expected ZipException");
        } catch (ZipException e) {
            // Expected exception
        }
    }
    @Test
    public void test30() throws Exception {
        byte[] buffer = new byte[10];
        int offset = 0;
        int length = -1;

        try {
            parseFromCentralDirectoryData(buffer, offset, length);
            fail("Expected ZipException");
        } catch (ZipException e) {
            // Expected exception
        }
    }
    @Test
    public void test31() throws Exception {
        byte[] buffer = new byte[10];
        int offset = 0;
        int length = 20;

        try {
            parseFromCentralDirectoryData(buffer, offset, length);
            fail("Expected ZipException");
        } catch (ZipException e) {
            // Expected exception
        }
    }
    @Test
    public void test32() {
        uid = 2000;
        gid = 2000;

        reset();

        assertEquals(1000, uid);
        assertEquals(1000, gid);

        uid = 0;
        gid = 0;

        reset();

        assertEquals(1000, uid);
        assertEquals(1000, gid);

        uid = 555;
        gid = 555;

        reset();

        assertEquals(1000, uid);
        assertEquals(1000, gid);
    }
    @Test
    public void test33() throws Exception {
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
    public void test34() {
        reset();

        assertEquals(X7875, xf.getHeaderId());
    }
    @Test
    public void test35() {
        reset();

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
    public void test36() throws Exception {
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertTrue(xf.equals(o));
        xf.setUID(12345);
        assertFalse(xf.equals(o));
    }
    @Test
    public void test37() throws ZipException {

        final byte[] ZERO_LEN = {1, 0, 0};
        final byte[] ZERO_UID_GID = {1, 1, 0, 1, 0};
        final byte[] ONE_UID_GID = {1, 1, 1, 1, 1};
        final byte[] ONE_THOUSAND_UID_GID = {1, 2, -24, 3, 2, -24, 3};
        final byte[] UNIX_MAX_UID_GID = {1, 4, -2, -1, -1, -1, 4, -2, -1, -1, -1};
        final byte[] LENGTH_5 = {1, 5, 0, 0, 0, 0, 1, 5, 1, 0, 0, 0, 1};
        final byte[] LENGTH_8 = {1, 8, -2, -1, -1, -1, -1, -1, -1, 127, 8, -1, -1, -1, -1, -1, -1, -1, 127};
        final long TWO_TO_32 = 0x100000000L;
        final long MAX = TWO_TO_32 - 2;

        reset();

        parseReparse(0, 0, ZERO_LEN, 0, 0);
        parseReparse(0, 0, ZERO_UID_GID, 0, 0);
        parseReparse(1, 1, ONE_UID_GID, 1, 1);
        parseReparse(1000, 1000, ONE_THOUSAND_UID_GID, 1000, 1000);
        parseReparse(MAX, MAX, UNIX_MAX_UID_GID, MAX, MAX);
        parseReparse(-2, -2, UNIX_MAX_UID_GID, MAX, MAX);
        parseReparse(TWO_TO_32, TWO_TO_32 + 1, LENGTH_5, TWO_TO_32, TWO_TO_32 + 1);
        parseReparse(Long.MAX_VALUE - 1, Long.MAX_VALUE, LENGTH_8, Long.MAX_VALUE - 1, Long.MAX_VALUE);

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
    public void test38() throws Exception {
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertTrue(xf.equals(o));
        xf.setUID(12345);
        assertFalse(xf.equals(o));
        
        // Regression test 1: Changing the UID value
        xf.setUID(98765);
        assertFalse(xf.equals(o));
        
        // Regression test 2: Changing the GID value
        xf.setGID(54321);
        assertFalse(xf.equals(o));
    }
    @Test
    public void test39() throws Exception {
        // Test with null input
        assertNull(xf.clone());
        
        // Test with a different instance
        XFObject xf2 = new XFObject();
        Object clone = xf2.clone();
        assertTrue(clone instanceof XFObject);
        assertEquals(xf2, clone);
    }
    @Test
    public void test40() throws Exception {
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

                // New regression test cases
                if (name.contains("some_name")) {
                    xf.setUID(123);
                    xf.setGID(456);
                    assertNotEquals(expected, xf.getUID());
                    assertNotEquals(expected, xf.getGID());
                }
                if (name.contains("another_name")) {
                    xf.setUID(100);
                    xf.setGID(200);
                    assertNotEquals(expected, xf.getUID());
                    assertNotEquals(expected, xf.getGID());
                }
            }
        } finally {
            if (zf != null) {
                zf.close();
            }
        }
    }
    @Test
    public void test41() {
        assertEquals(X7875, xf.getHeaderId());

        // New regression test cases
        xf.setHeaderId(null);
        assertNull(xf.getHeaderId());
        xf.setHeaderId("some_header_id");
        assertEquals("some_header_id", xf.getHeaderId());
    }
    @Test
    public void test42() throws Exception {
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertTrue(xf.equals(o));
        xf.setUID(12345);
        assertFalse(xf.equals(o));

        // New regression test cases
        xf.setUID(54321);
        xf.setGID(98765);
        assertNotEquals(o, xf);
        xf.setUID(1000);
        xf.setGID(1000);
        assertEquals(o, xf);
    }
    @Test
    public void test43() throws Exception {
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
    public void test44() throws Exception {
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertTrue(xf.equals(o));
        xf.setUID(12345);
        assertFalse(xf.equals(o));
    }
    @Test
    public void test45() throws Exception {
        File newArchive = getFile("COMPRESS-211_uid_gid_zip_test_v2.zip");
        ZipFile newZf = null;

        try {
            newZf = new ZipFile(newArchive);
            Enumeration<ZipArchiveEntry> newEn = newZf.getEntries();

            // We expect EVERY entry of this new zip file (dir & file) to
            // contain extra field 0x7875.
            while (newEn.hasMoreElements()) {

                ZipArchiveEntry newZae = newEn.nextElement();
                String newName = newZae.getName();
                X7875_NewUnix newXf = (X7875_NewUnix) newZae.getExtraField(X7875);

                // The directory entry in the new test zip file is uid/gid 2000.
                long newExpected = 2000;
                if (newName.contains("uid777_gid777")) {
                    newExpected = 777;
                } else if (newName.contains("uid7777_gid7777")) {
                    newExpected = 7777;
                } else if (newName.contains("uid77777_gid77777")) {
                    newExpected = 77777;
                } else if (newName.contains("uid777777_gid777777")) {
                    newExpected = 777777;
                } else if (newName.contains("min_new_unix")) {
                    newExpected = 0;
                } else if (newName.contains("max_new_unix")) {
                    // 2^32-2 was the biggest UID/GID I could create on my linux!
                    // (December 2012, linux kernel 3.4)
                    newExpected = 0x100000000L - 2;
                }
                assertEquals(newExpected, newXf.getUID());
                assertEquals(newExpected, newXf.getGID());
            }
        } finally {
            if (newZf != null) {
                newZf.close();
            }
        }
    }
    @Test
    public void test46() {
        final byte[] ZEROES = {0, 0, 0, 0, 0};
        final byte[] SEQUENCE_ZERO = {0, 0, 0, 0, 1, 2, 3};
        final byte[] SEQUENCE_ZERO_LEADING_ZEROES = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3};

        assertTrue(Arrays.equals(ZEROES, trimTest(ZEROES)));
        assertTrue(Arrays.equals(SEQUENCE_ZERO, trimTest(SEQUENCE_ZERO)));
        assertTrue(Arrays.equals(SEQUENCE_ZERO, trimTest(SEQUENCE_ZERO_LEADING_ZEROES)));
    }
    @Test
    public void test47() {
        final byte[] SEQUENCE_LEADING_ZERO = {0, 0, 0, 1, 2, 3};
        final byte[] SEQUENCE_LEADING_ZEROES = {0, 0, 0, 0, 0, 0, 0, 1, 2, 3};
        final byte[] SEQUENCE_ZERO_LEADING_ZERO = {0, 0, 0, 0, 1, 2, 3, 4};
        final byte[] SEQUENCE_ZERO_LEADING_ZEROES = {0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4};
        
        assertTrue(Arrays.equals(SEQUENCE_LEADING_ZERO, trimTest(SEQUENCE_LEADING_ZERO)));
        assertTrue(Arrays.equals(SEQUENCE_LEADING_ZERO, trimTest(SEQUENCE_LEADING_ZEROES)));
        assertTrue(Arrays.equals(SEQUENCE_ZERO_LEADING_ZERO, trimTest(SEQUENCE_ZERO_LEADING_ZERO)));
        assertTrue(Arrays.equals(SEQUENCE_ZERO_LEADING_ZERO, trimTest(SEQUENCE_ZERO_LEADING_ZEROES)));
    }
}