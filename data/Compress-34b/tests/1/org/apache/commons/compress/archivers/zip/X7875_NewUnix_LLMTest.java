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
        assertEquals(X7874, xf.getHeaderId());
    }
    @Test
    public void test1() throws Exception {
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
                    expected = 1234; // different value
                } else if (name.contains("uid5555_gid5555")) {
                    expected = 9999; // different value
                } else if (name.contains("uid55555_gid55555")) {
                    expected = 98765; // different value
                } else if (name.contains("uid555555_gid555555")) {
                    expected = 12345679; // different value
                } else if (name.contains("min_unix")) {
                    expected = 9876543; // different value
                } else if (name.contains("max_unix")) {
                    expected = 123456789; // different value
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
    public void test2() {
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
        parseReparse(5555, 5555, ONE_THOUSAND_UID_GID, 5555, 5555);
        parseReparse(555, 555, ONE_THOUSAND_UID_GID, 555, 555);
        parseReparse(55555, 55555, ONE_THOUSAND_UID_GID, 55555, 55555);
        parseReparse(555555, 555555, ONE_THOUSAND_UID_GID, 555555, 555555);
        parseReparse(0, 0, ZERO_UID_GID, 0, 0);
    }
    @Test
    public void test3() throws ZipException {

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

        // Test case with negative uid and gid
        final byte[] NEGATIVE_UID_GID = {1, 1, -1, 1, -1};
        parseReparse(-1, -1, NEGATIVE_UID_GID, -1, -1);

        // Test case with zero uid and maximum gid
        final byte[] ZERO_UID_MAX_GID = {1, 1, 0, 1, -2};
        parseReparse(0, -2, ZERO_UID_MAX_GID, 0, -2);
    }
    @Test
    public void test4() throws Exception {
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
    
    // Additional test case
    // Version=1, Len=3, 0, Len=3, 0
    final byte[] ZERO_UID_GID_2 = {1, 3, 0, 3, 0};
    parseReparse(0, 0, ZERO_UID_GID_2, 0, 0);

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
    public void test6() throws ZipException {
        final byte[] ZERO_LEN = {1, 0, 0};
        parseReparse(-1, 0, ZERO_LEN, -1, 0);
    }
    @Test
    public void test7() throws ZipException {
        final byte[] ZERO_UID_GID = {1, 1, 0, 1, 0};
        parseReparse(0, -1, ZERO_UID_GID, 0, -1);
    }
    @Test
    public void test8() throws ZipException {
        final byte[] ONE_UID_GID = {1, 1, 1, 1, 1};
        parseReparse(-1, 1, ONE_UID_GID, -1, 1);
    }
    @Test
    public void test9() throws ZipException {
        final byte[] ONE_THOUSAND_UID_GID = {1, 2, -24, 3, 2, -24, 3};
        parseReparse(-1, 1000, ONE_THOUSAND_UID_GID, -1, 1000);
    }
    @Test
    public void test10() throws ZipException {
        final byte[] UNIX_MAX_UID_GID = {1, 4, -2, -1, -1, -1, 4, -2, -1, -1, -1};
        parseReparse(-1, -1, UNIX_MAX_UID_GID, -1, -1);
    }
    @Test
    public void test11() throws ZipException {
        final byte[] LENGTH_5 = {1, 5, 0, 0, 0, 0, 1, 5, 1, 0, 0, 0, 1};
        parseReparse(-1, TWO_TO_32 + 1, LENGTH_5, -1, TWO_TO_32 + 1);
    }
    @Test
    public void test12() throws ZipException {
        final byte[] LENGTH_8 = {1, 8, -2, -1, -1, -1, -1, -1, -1, 127, 8, -1, -1, -1, -1, -1, -1, -1, 127};
        parseReparse(-1, Long.MAX_VALUE, LENGTH_8, -1, Long.MAX_VALUE);
    }
    @Test
    public void test13() throws ZipException {
        final byte[] SPURIOUS_ZEROES_1 = {1, 4, -1, 0, 0, 0, 4, -128, 0, 0, 0};
        final byte[] EXPECTED_1 = {1, 1, -1, 1, -128};
        parseReparse(-1, 128, SPURIOUS_ZEROES_1, -1, 128);
    }
    @Test
    public void test14() throws ZipException {
        final byte[] SPURIOUS_ZEROES_2 = {1, 4, -1, -1, 0, 0, 4, 1, 2, 0, 0};
        final byte[] EXPECTED_2 = {1, 2, -1, -1, 2, 1, 2};
        parseReparse(-1, 513, SPURIOUS_ZEROES_2, -1, 513);
    }
    @Test
    public void test15() throws Exception {
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
                assertEquals(1, xf.getUID());
                assertEquals(expected, xf.getGID());
            }
        } finally {
            if (zf != null) {
                zf.close();
            }
        }
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
                assertEquals(1, xf.getGID());
            }
        } finally {
            if (zf != null) {
                zf.close();
            }
        }
    }
    @Test
    public void test17() throws ZipException {
        parseReparse(0, 1, ZERO_LEN, 0, 0);
        parseReparse(0, 0, ZERO_UID_GID, 0, 0);
        parseReparse(1, 0, ONE_UID_GID, 1, 1);
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
    public void test18() throws ZipException {
        // Version=1, Len=0, Len=0.
        final byte[] ZERO_LEN2 = {0, 0, 0};
        parseReparse(0, 0, ZERO_LEN2, 0, 0);
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
    public void test19() {
        BigInteger uid = new BigInteger("100000000000000000000000000000"); // large value for uid
        BigInteger gid = new BigInteger("-200000000000000000000000000000"); // negative value for gid

        byte[] uidBytes = uid.toByteArray();
        byte[] gidBytes = gid.toByteArray();

        uidBytes = trimLeadingZeroesForceMinLength(uidBytes);
        gidBytes = trimLeadingZeroesForceMinLength(gidBytes);

        byte[] expectedData = new byte[3 + uidBytes.length + gidBytes.length];
        expectedData[0] = (byte) 1;
        expectedData[1] = (byte) uidBytes.length;
        System.arraycopy(uidBytes, 0, expectedData, 2, uidBytes.length);
        expectedData[uidBytes.length + 2] = (byte) gidBytes.length;
        System.arraycopy(gidBytes, 0, expectedData, uidBytes.length + 3, gidBytes.length);

        byte[] actualData = getLocalFileDataData(uid, gid);

        assertArrayEquals(expectedData, actualData);
    }
    @Test
    void testGetCentralDirectoryDataWithNonNullInput() {
        // Test case covering non-null input
        // Arrange
        byte[] input = {1, 2, 3};

        // Act
        byte[] result = getCentralDirectoryData(input);

        // Assert
        assertArrayEquals(input, result);
    }
    @Test
    void testGetCentralDirectoryDataWithNullInput() {
        // Test case covering null input
        // Arrange
        byte[] input = null;

        // Act
        byte[] result = getCentralDirectoryData(input);

        // Assert
        assertEquals(0, result.length);
    }
    @Test
    void testGetCentralDirectoryDataWithEmptyInput() {
        // Test case covering empty input
        // Arrange
        byte[] input = {};

        // Act
        byte[] result = getCentralDirectoryData(input);

        // Assert
        assertEquals(0, result.length);
    }
    @Test
    void testGetCentralDirectoryDataWithLargeInput() {
        // Test case covering large input
        // Arrange
        byte[] input = new byte[1000000];

        // Act
        byte[] result = getCentralDirectoryData(input);

        // Assert
        assertEquals(1000000, result.length);
    }
    @Test
    public void test20() throws ZipException {
        // Version=1, Len=1, zero, twice // testing offset is incremented correctly
        final byte[] ZERO_UID_GID_OFFSET = {1, 1, 0, 0, 0, 1, 0};

        // Version=1, Len=1, zero, Len=1, zero // testing UID and GID are properly reset
        final byte[] ZERO_UID_GID_RESET = {1, 1, 0, 1, 0, 0};

        // Version=1, Len=2, zero, Len=2, zero // testing UID and GID values
        final byte[] ZERO_UID_GID_VALUE = {1, 2, 0, 0, 0, 2, 0, 0};

        // Version=1, Len=1, one, Len=1, one // testing UID and GID values
        final byte[] ONE_UID_GID_VALUE = {1, 1, 1, 1, 1};

        // Version=1, Len=1, one, Len=1 // testing offset reaches end of data
        final byte[] ONE_UID_GID_OFFSET = {1, 1, 1, 1};

        // Version=1, Len=2, one thousand, Len=2, one thousand // testing UID and GID values
        final byte[] ONE_THOUSAND_UID_GID_VALUE = {1, 2, -24, 3, 0, 0};

        parseFromLocalFileDataMutation(ZERO_UID_GID_OFFSET, 0, 0); // mutant: increment offset twice instead of once
        parseFromLocalFileDataMutation(ZERO_UID_GID_RESET, 0, 0); // mutant: remove reset() call
        parseFromLocalFileDataMutation(ZERO_UID_GID_VALUE, 0, 0); // mutant: zeros instead of actual values in uidBytes and gidBytes
        parseFromLocalFileDataMutation(ONE_UID_GID_VALUE, 1, 1); // mutant: change offset to 1
        parseFromLocalFileDataMutation(ONE_UID_GID_OFFSET, 1, 1); // mutant: remove increment of offset
        parseFromLocalFileDataMutation(ONE_THOUSAND_UID_GID_VALUE, 1, 2); // mutant: change offset to 2
    }
    @Test
    public void test21() throws Exception {
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
                
                // Regression test case 1
                // Changing the offset value to test if it handles different offsets correctly
                parseFromCentralDirectoryData(buffer, offset+10, length);
                assertEquals(expected, xf.getUID());
                assertEquals(expected, xf.getGID());
                
                // Regression test case 2
                // Changing the length value to test if it handles different lengths correctly
                parseFromCentralDirectoryData(buffer, offset, length-10);
                assertEquals(expected, xf.getUID());
                assertEquals(expected, xf.getGID());
                
                // Regression test case 3
                // Changing the buffer value to test if it handles different buffers correctly
                parseFromCentralDirectoryData(new byte[length], offset, length);
                assertEquals(expected, xf.getUID());
                assertEquals(expected, xf.getGID());
                
                // Regression test case 4
                // Changing the offset value to test if it handles negative offsets correctly
                parseFromCentralDirectoryData(buffer, -1, length);
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
    public void test22() {
        assertEquals(X7875, xf.getHeaderId());
    }
    @Test
    public void test23() throws Exception {
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertTrue(xf.equals(o));
        xf.setUID(12345);
        assertFalse(xf.equals(o));
    }
    @Test
    public void test24() {
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
    public void test27() {
        uid = 123;
        gid = 456;
        reset();
        assertEquals(1000, uid);
        assertEquals(1000, gid);
    }
    @Test
    public void test28() {
        assertEquals(X7875, xf.getHeaderId());
        xf.setHeaderId(X131313);
        assertEquals(X131313, xf.getHeaderId());
    }
    @Test
    public void test29() throws Exception {
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertTrue(xf.equals(o));
        xf.setGID(54321);
        assertFalse(xf.equals(o));
    }
    @Test
    public void test30() throws Exception {
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
                
                // Change the input value for xf
                xf.setUID(12345);
                assertEquals(12345, xf.getUID());
            }
        } finally {
            if (zf != null) {
                zf.close();
            }
        }
    }
    @Test
    public void test31() throws ZipException {

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
        
        // Change the input values for the parseReparse tests
        parseReparse(0, 0, ZERO_LEN, 1, 1);
        parseReparse(0, 0, ZERO_UID_GID, 2, 2);
        parseReparse(1, 1, ONE_UID_GID, 3, 3);
        parseReparse(1000, 1000, ONE_THOUSAND_UID_GID, 4, 4);
        parseReparse(MAX, MAX, UNIX_MAX_UID_GID, 5, 5);
        parseReparse(-2, -2, UNIX_MAX_UID_GID, 6, 6);
        parseReparse(TWO_TO_32, TWO_TO_32 + 1, LENGTH_5, 7, 7);
        parseReparse(Long.MAX_VALUE - 1, Long.MAX_VALUE, LENGTH_8, 8, 8);
    }
    @Test
    public void test32() throws Exception {
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertTrue(xf.equals(o));
        xf.setUID(12345);
        assertFalse(xf.equals(o));
    }
    @Test
    public void test33() throws Exception {
        xf.setUID(54321);
        Object o = xf.clone();
        assertFalse(xf.equals(o));
    }
    @Test
    public void test34() throws Exception {
        xf.setUID(0);
        xf.setGID(0);
        assertEquals("0x7875 Zip Extra Field: UID=0 GID=0", xf.toString());
    }
    @Test
    public void test35() throws Exception {
        xf.setUID(54321);
        xf.setGID(98765);
        assertEquals("0x7875 Zip Extra Field: UID=54321 GID=98765", xf.toString());
    }
    @Test
    public void test36() throws Exception {
        xf.setGID(12345);
        Object o = xf.clone();
        assertFalse(xf.equals(o));
    }
    @Test
    public void test37() throws CloneNotSupportedException {
        assertThrows(CloneNotSupportedException.class, () -> {
            Object o = new ZipExtraField().clone();
        });
    }
    @Test
    public void test38() {
        ZipExtraField xf1 = new ZipExtraField();
        xf1.setUID(12345);

        ZipExtraField xf2 = new ZipExtraField();
        xf2.setUID(54321);

        assertFalse(xf1.equals(xf2));
    }
    @Test
    public void test39() {
        ZipExtraField xf1 = new ZipExtraField();
        xf1.setUID(12345);

        ZipExtraField xf2 = new ZipExtraField();
        xf2.setUID(54321);

        assertNotEquals(xf1.hashCode(), xf2.hashCode());
    }
    @Test
    public void test40() {
        X7875_NewUnix xf1 = new X7875_NewUnix(X7875, 1L, "uid1", "gid1");
        X7875_NewUnix xf2 = new X7875_NewUnix(X7875, 2L, "uid1", "gid1");
        assertFalse(xf1.equals(xf2));
    }
    @Test
    public void test41() {
        X7875_NewUnix xf1 = new X7875_NewUnix(X7875, 1L, "uid1", "gid1");
        X7875_NewUnix xf2 = new X7875_NewUnix(X7875, 1L, "uid2", "gid1");
        assertFalse(xf1.equals(xf2));
    }
    @Test
    public void test42() {
        X7875_NewUnix xf1 = new X7875_NewUnix(X7875, 1L, "uid1", "gid1");
        X7875_NewUnix xf2 = new X7875_NewUnix(X7875, 1L, "uid1", "gid2");
        assertFalse(xf1.equals(xf2));
    }
    @Test
    public void test43() {
        X7875_NewUnix xf = new X7875_NewUnix(X7875, 1L, "uid1", "gid1");
        assertFalse(xf.equals("NotAnX7875_NewUnixObject"));
    }
    @Test
    public void test44() {
        X7875_NewUnix xf = new X7875_NewUnix(X7875, 1L, "uid1", "gid1");
        assertFalse(xf.equals(null));
    }
    @Test
    public void test45() {
        X7875_NewUnix xf = new X7875_NewUnix(X7875, 1L, "uid1", "gid1");
        assertTrue(xf.equals(xf));
    }
    @Test
    public void test46() {
        X7875_NewUnix xf1 = new X7875_NewUnix(X7875, 1L, null, "gid1");
        X7875_NewUnix xf2 = new X7875_NewUnix(X7875, 1L, "uid1", "gid1");
        assertFalse(xf1.equals(xf2));
    }
    @Test
    public void test47() {
        X7875_NewUnix xf1 = new X7875_NewUnix(X7875, 1L, "uid1", null);
        X7875_NewUnix xf2 = new X7875_NewUnix(X7875, 1L, "uid1", "gid1");
        assertFalse(xf1.equals(xf2));
    }
    @Test
    public void test48() {
        X7875_NewUnix xf1 = new X7875_NewUnix(X7875, 1L, null, null);
        X7875_NewUnix xf2 = new X7875_NewUnix(X7875, 1L, "uid1", "gid1");
        assertFalse(xf1.equals(xf2));
    }
    @Test
    public void test49() {
        X7875_NewUnix xf1 = new X7875_NewUnix(X7875, 1L, null, "gid1");
        X7875_NewUnix xf2 = new X7875_NewUnix(X7875, 1L, null, "gid1");
        assertTrue(xf1.equals(xf2));
    }
    @Test
    public void test50() {
        X7875_NewUnix xf1 = new X7875_NewUnix(X7875, 1L, "uid1", null);
        X7875_NewUnix xf2 = new X7875_NewUnix(X7875, 1L, "uid1", null);
        assertTrue(xf1.equals(xf2));
    }
    @Test
    public void test51() {
        X7875_NewUnix xf1 = new X7875_NewUnix(X7875, 1L, null, null);
        X7875_NewUnix xf2 = new X7875_NewUnix(X7875, 1L, null, null);
        assertTrue(xf1.equals(xf2));
    }
    @Test
    public void test52() {
        X7875_NewUnix xf1 = new X7875_NewUnix(X7875, 1L, "uid1", "gid1");
        Integer xf2 = 1;
        assertFalse(xf1.equals(xf2));
    }
    @Test
    public void test53() {
        X7875_NewUnix xf1 = new X7875_NewUnix(X7875, 1L, "uid1", "gid1");
        Integer xf2 = 1;
        assertFalse(xf1.equals(xf2));
    }
    @Test
    public void test54() {
        X7875_NewUnix xf1 = new X7875_NewUnix(X7875, 1L, "uid1", "gid1");
        Integer xf2 = 1;
        assertFalse(xf1.equals(xf2));
    }
    @Test
    public void test55() {
        X7875_NewUnix xf1 = new X7875_NewUnix(X7875, 1L, null, null);
        Integer xf2 = 1;
        assertFalse(xf1.equals(xf2));
    }
    @Test
    public void test56() {
        X7875_NewUnix xf1 = new X7875_NewUnix(X7875, 1L, null, "gid1");
        Integer xf2 = 1;
        assertFalse(xf1.equals(xf2));
    }
    @Test
    public void test57() {
        X7875_NewUnix xf1 = new X7875_NewUnix(X7875, 1L, "uid1", null);
        Integer xf2 = 1;
        assertFalse(xf1.equals(xf2));
    }
    @Test
    public void test58() throws Exception {
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertTrue(xf.equals(o));
        xf.setUID(12345);
        assertFalse(xf.equals(o));
        // Regression test 1: Changing uid to 0
        xf.setUID(0);
        assertFalse(xf.equals(o));
        // Regression test 2: Changing gid to 0
        xf.setUID(12345);
        xf.setGID(0);
        assertFalse(xf.equals(o));
    }
    @Test
    public void test59() throws Exception {
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
                    // Regression test 3: Changing uid value to 0
                    xf.setUID(0);
                } else if (name.contains("uid5555_gid5555")) {
                    expected = 5555;
                    // Regression test 4: Changing gid value to 0
                    xf.setGID(0);
                } else if (name.contains("uid55555_gid55555")) {
                    expected = 55555;
                    // Regression test 5: Changing uid and gid values to 0
                    xf.setUID(0);
                    xf.setGID(0);
                } else if (name.contains("uid555555_gid555555")) {
                    expected = 555555;
                } else if (name.contains("min_unix")) {
                    expected = 0;
                    // Regression test 6: Changing uid and gid values to 0
                    xf.setUID(0);
                    xf.setGID(0);
                } else if (name.contains("max_unix")) {
                    // 2^32-2 was the biggest UID/GID I could create on my linux!
                    // (December 2012, linux kernel 3.4)
                    expected = 0x100000000L - 2;
                    // Regression test 7: Changing uid and gid values to 0
                    xf.setUID(0);
                    xf.setGID(0);
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
    public void test60() {
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
        final byte[] EXCESS_ZEROES = {0, 0, 0, 1, 2, 3};

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
        assertTrue(Arrays.equals(SEQUENCE, trimTest(EXCESS_ZEROES)));
    }
}