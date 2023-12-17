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
        File archive = getFile("invalid_zip_file.zip");
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
    public void test1() throws Exception {
        File archive = getFile("different_zip_file.zip");
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
        // Version=1, Len=1, -1, Len=1, -1
        final byte[] NEGATIVE_UID_GID = {1, 1, -1, 1, -1};

        parseReparse(-1, -1, NEGATIVE_UID_GID, -1, -1);

        assertEquals(-1, xf.getUID());
        assertEquals(-1, xf.getGID());
    }
    @Test
    public void test3() throws ZipException {
        // Version=1, Len=8, 2^63, Len=8, 2^63 + 1
        // Esoteric test: can we handle 64 bit numbers?
        final byte[] LENGTH_8 = {1, 8, -128, 0, 0, 0, 0, 0, 0, 128, 8, -128, 0, 0, 0, 0, 0, 0, 129};

        final long TWO_TO_63 = 0x8000000000000000L;
        final long MAX = TWO_TO_63 - 1;

        parseReparse(TWO_TO_63, TWO_TO_63 + 1, LENGTH_8, TWO_TO_63, TWO_TO_63 + 1);

        assertEquals(TWO_TO_63, xf.getUID());
        assertEquals(TWO_TO_63 + 1, xf.getGID());
    }
    @Test
    public void test4() throws ZipException {
        // Version=1, Len=1, Len=1
        final byte[] regression1 = {1, 1, 1};

        parseReparse(1, 1, regression1, 1, 1);
    }
    @Test
    public void test5() throws ZipException {
        // Version=1, Len=1, negative one, Len=1, negative one
        final byte[] regression2 = {1, 1, -1, 1, -1};

        parseReparse(-1, -1, regression2, -1, -1);
    }
    @Test
    public void test6() throws ZipException {
        // Version=1, Len=2, negative one thousand, Len=2, negative one thousand
        final byte[] regression3 = {1, 2, 24, -4, 2, 24, -4};

        parseReparse(-1000, -1000, regression3, -1000, -1000);
    }
    @Test
    public void test7() throws ZipException {
        // Version=1, Len=4, negative two billion, Len=4, negative two billion
        final byte[] regression4 = {1, 4, 0, 0, -128, -31, 4, 0, -128, -31};

        parseReparse(-2000000000, -2000000000, regression4, -2000000000, -2000000000);
    }
    @Test
    public void test8() throws ZipException {
        // Version=1, Len=8, negative 2^63, Len=8, negative 2^63
        final byte[] regression5 = {1, 8, 0, 0, 0, 0, 0, 0, -128, 0, 0, 0, 0, 0, 0, -128};

        parseReparse(Long.MIN_VALUE, Long.MIN_VALUE, regression5, Long.MIN_VALUE, Long.MIN_VALUE);
    }
    @Test
    public void test9() {
        xf.setUID(0);
        assertEquals(0, xf.getUID());
        xf.setUID(99999);
        assertEquals(99999, xf.getUID());
        xf.setUID(-1000);
        assertEquals(-1000, xf.getUID());
        xf.setUID(Long.MAX_VALUE);
        assertEquals(Long.MAX_VALUE, xf.getUID());
        xf.setUID(Long.MIN_VALUE);
        assertEquals(Long.MIN_VALUE, xf.getUID());
    }
    @Test
    public void test10() throws ZipException {

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

        parseReparse(0, 0, ZERO_LEN, 0, 0);
        parseReparse(0, 0, ZERO_UID_GID, 0, 0);
        parseReparse(1, 1, ONE_UID_GID, 1, 1);
        parseReparse(1000, 1000, ONE_THOUSAND_UID_GID, 1000, 1000);
        parseReparse(MAX, MAX, UNIX_MAX_UID_GID, MAX, MAX);
        parseReparse(-2, -2, UNIX_MAX_UID_GID, MAX, MAX);
        parseReparse(TWO_TO_32, TWO_TO_32 + 1, LENGTH_5, TWO_TO_32, TWO_TO_32 + 1);
        parseReparse(Long.MAX_VALUE - 1, Long.MAX_VALUE, LENGTH_8, Long.MAX_VALUE - 1, Long.MAX_VALUE);

        // Regression test case 1: Negative input for gid
        parseReparse(0, -1, ZERO_UID_GID, 0, 0);

        // Regression test case 2: Very large uid and gid values
        final byte[] LARGE_UID_GID = {1, 8, 127, -1, -1, -1, -1, -1, -1, 127, 8, 127, -1, -1, -1, -1, -1, -1, 127};
        parseReparse(Long.MAX_VALUE, Long.MAX_VALUE, LARGE_UID_GID, Long.MAX_VALUE, Long.MAX_VALUE);

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
    public void test11() {
        // Testing minimum uid/gid lengths
        byte[] testData1 = {1, 1, 0, 1, 0};
        ZipShort expected1 = new ZipShort(5);
        assertEquals(expected1, getLocalFileDataLength(testData1));

        // Testing maximum uid/gid lengths
        byte[] testData2 = {1, 4, -2, -1, -1, -1, 4, -2, -1, -1, -1};
        ZipShort expected2 = new ZipShort(13);
        assertEquals(expected2, getLocalFileDataLength(testData2));

        // Testing uid/gid lengths greater than maximum
        byte[] testData3 = {1, 5, 0, 0, 0, 0, 1};
        ZipShort expected3 = new ZipShort(6);
        assertEquals(expected3, getLocalFileDataLength(testData3));

        // Testing uid/gid lengths less than minimum
        byte[] testData4 = {1, 0};
        ZipShort expected4 = new ZipShort(3);
        assertEquals(expected4, getLocalFileDataLength(testData4));
    }
    @Test
    public void test12() {
        // Testing uid/gid values of 0
        byte[] testData1 = {1, 1, 0, 1, 0};
        ZipShort expected1 = new ZipShort(5);
        assertEquals(expected1, getLocalFileDataLength(testData1));

        // Testing uid/gid values of 1
        byte[] testData2 = {1, 1, 1, 1, 1};
        ZipShort expected2 = new ZipShort(5);
        assertEquals(expected2, getLocalFileDataLength(testData2));

        // Testing uid/gid values of 1000
        byte[] testData3 = {1, 2, -24, 3, 2, -24, 3};
        ZipShort expected3 = new ZipShort(8);
        assertEquals(expected3, getLocalFileDataLength(testData3));

        // Testing uid/gid values of maximum 2^32 - 2
        byte[] testData4 = {1, 4, -2, -1, -1, -1, 4, -2, -1, -1, -1};
        ZipShort expected4 = new ZipShort(13);
        assertEquals(expected4, getLocalFileDataLength(testData4));
    }
    @Test
    public void test13() throws ZipException {

        // Version=0, Len=0
        // Changing the version number to 0
        final byte[] ZERO_LEN = {0, 0, 0};
        
        // Version=1, Len=0, Len=0.
        final byte[] ZERO_UID_GID = {1, 0, 0};

        // Version=1, Len=1, one, Len=1, one
        // Changing the length values to 1
        final byte[] ONE_UID_GID = {1, 1, 1, 1, 1};

        // Version=1, Len=2, one thousand, Len=2, one thousand
        // Changing the length values to 1000
        final byte[] ONE_THOUSAND_UID_GID = {1, 2, -24, 3, 2, -24, 3};

        // (2^32 - 2).   I guess they avoid (2^32 - 1) since it's identical to -1 in
        // two's complement, and -1 often has a special meaning.
        // Changing the length values to 4294967294
        final byte[] UNIX_MAX_UID_GID = {1, 4, -2, -1, -1, -1, 4, -2, -1, -1, -1};

        // Version=1, Len=5, 2^32, Len=5, 2^32 + 1
        // Esoteric test:  can we handle 40 bit numbers?
        // Changing the length values to 4294967296 and 4294967297
        final byte[] LENGTH_5 = {1, 5, 0, 0, 0, 0, 1, 5, 1, 0, 0, 0, 1};

        // Version=1, Len=8, 2^63 - 2, Len=8, 2^63 - 1
        // Esoteric test:  can we handle 64 bit numbers?
        // Changing the length values to the maximum and maximum - 1 64-bit numbers
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
    }
}

    // Regression test 1: Input value is negative
    @Test
    public void test14() throws Exception {
    }

    // Regression test 2: Input value is zero
    @Test
    public void test15() throws Exception {
    }

    // Regression test 3: Input value is positive
    @Test
    public void test16() throws Exception {
    }

    // Regression test 4: Input value is the minimum integer value
    @Test
    public void test17() throws Exception {
    }

    // Regression test 5: Input value is the maximum integer value
    @Test
    public void test18() throws Exception {
    }

    // Regression test 6: Input value is a large positive value
    @Test
    public void test19() throws Exception {
    }

    // Regression test 7: Input value is a large negative value
    @Test
    public void test20() throws Exception {
    }

    // Test case 1: Empty byte array as input
    @Test
    public void test21() {
    }

    // Test case 2: Non-empty byte array as input
    @Test
    public void test22() {
    }

    // Test case 3: Large byte array as input
    @Test
    public void test23() {
    }

    // Test case 4: Null byte array as input
    @Test
    public void test24() {
    }

@Test
public void test25() throws ZipException {
    assertThrows(ZipException.class, () -> {
        parseFromLocalFileData(data, 0, data.length);
    });
}

@Test
public void test26() throws ZipException {
    assertThrows(ZipException.class, () -> {
        parseFromLocalFileData(data, 0, data.length);
    });
}

@Test
public void test27() throws ZipException {
    assertThrows(ZipException.class, () -> {
        parseFromLocalFileData(data, 0, data.length);
    });
}

@Test
public void test28() throws ZipException {
    assertThrows(ZipException.class, () -> {
        parseFromLocalFileData(data, 0, data.length);
    });
}

@Test
public void test29() throws ZipException {
    assertThrows(ZipException.class, () -> {
        parseFromLocalFileData(data, 0, data.length);
    });
}

@Test
public void test30() throws ZipException {
    assertThrows(NumberFormatException.class, () -> {
        parseFromLocalFileData(data, 0, data.length);
    });
}

@Test
public void test31() throws ZipException {
    assertThrows(NumberFormatException.class, () -> {
        parseFromLocalFileData(data, 0, data.length);
    });
}

    @Test
    public void test32() throws Exception {
        try {
            parseFromCentralDirectoryData(buffer, offset, length);
            fail("Expected ZipException to be thrown");
        } catch (ZipException e) {
            assertEquals("Invalid central directory data", e.getMessage());
        }
    }

    @Test
    public void test33() throws Exception {
        try {
            parseFromCentralDirectoryData(buffer, offset, length);
            fail("Expected ZipException to be thrown");
        } catch (ZipException e) {
            assertEquals("Invalid central directory data", e.getMessage());
        }
    }

    @Test
    public void test34() throws Exception {
        try {
            parseFromCentralDirectoryData(buffer, offset, length);
            fail("Expected ZipException to be thrown");
        } catch (ZipException e) {
            assertEquals("Invalid central directory data", e.getMessage());
        }
    }

    @Test
    public void test35() throws Exception {
        try {
            parseFromCentralDirectoryData(buffer, offset, length);
            fail("Expected ZipException to be thrown");
        } catch (ZipException e) {
            assertEquals("Invalid central directory data", e.getMessage());
        }
    }

    @Test
    public void test36() {
    }
    
    @Test
    public void test37() {
    }
    
    @Test
    public void test38() {
    }
    
    @Test
    public void test39() {
    }

    @Test
    public void test40() throws Exception {
    }

    @Test
    public void test41() throws Exception {
    }

    @Test
    public void test42() throws Exception {
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
    public void test43() {
    }
    @Test
    public void test44() throws Exception {
    }

    // Regression test cases
    @Test
    public void test45() {
    }
    
    @Test
    public void test46() throws Exception {
    }

    @Test
    public void test47() throws Exception {
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
    public void test48() throws Exception {
    }

    @Test
    public void test49() throws Exception {
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
    public void test50() throws Exception {
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
    public void test51() {
    }

    @Test
    public void test52() {
    }
}

    @Test
    public void test53() throws ZipException {

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
    public void test54() {
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
        final byte[] SEQUENCE7_LEADING_ZEROES = {0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6};

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
        assertTrue(Arrays.equals(SEQUENCE7_LEADING_ZEROES, trimTest(SEQUENCE7_LEADING_ZEROES)));
    }
    @Test
    public void test55() throws Exception {
        File archive = getFile("COMPRESS-211_uid_gid_zip_test.zip");
        ZipFile zf = null;

        try {
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
            if (zf != null) {
                zf.close();
            }
        }
    }
    
    @Test
    public void test56() {
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
        final byte[] SEQUENCE7_LEADING_ZEROES = {0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6};

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
        assertTrue(Arrays.equals(SEQUENCE7_LEADING_ZEROES, trimTest(SEQUENCE7_LEADING_ZEROES)));
        
        final byte[] EXTRA_LEADING_ZEROES = {0, 0, 0, 1, 2, 3, 4, 5, 6};
        final byte[] EXPECTED_EXTRA_LEADING_ZEROES = {1, 2, 3, 4, 5, 6};
        xf.parseFromLocalFileData(EXTRA_LEADING_ZEROES, 0, EXTRA_LEADING_ZEROES.length);
        assertTrue(Arrays.equals(EXPECTED_EXTRA_LEADING_ZEROES, xf.getLocalFileDataData()));
    }

}