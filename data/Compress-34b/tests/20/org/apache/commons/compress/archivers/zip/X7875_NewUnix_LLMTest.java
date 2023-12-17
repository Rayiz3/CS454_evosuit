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
                long expected = -1000; // change the expected value to negative
                if (name.contains("uid555_gid555")) {
                    expected = -555;
                } else if (name.contains("uid5555_gid5555")) {
                    expected = -5555;
                } else if (name.contains("uid55555_gid55555")) {
                    expected = -55555;
                } else if (name.contains("uid555555_gid555555")) {
                    expected = -555555;
                } else if (name.contains("min_unix")) {
                    expected = 0;
                } else if (name.contains("max_unix")) {
                    // 2^32-2 was the biggest UID/GID I could create on my linux!
                    // (December 2012, linux kernel 3.4)
                    expected = -1*(0x100000000L - 2); // change the expected value to negative
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
    public void test1() throws ZipException {
        // Version=1, Len=1, -1, Len=1, -1
        final byte[] NEGATIVE_UID_GID = {1, 1, -1, 1, -1};

        parseReparse(-1, -1, NEGATIVE_UID_GID, -1, -1);
    }
    @Test
    public void test2() throws ZipException {
        // Version=1, Len=1, zero, Len=1, zero.
        final byte[] ZERO_UID_GID = {1, 1, 0, 1, 0};

        parseReparse(0, 0, ZERO_UID_GID, 0, 0);
    }
    @Test
    public void test3() throws ZipException {
        // Version=1, Len=1, one, Len=1, one
        final byte[] ONE_UID_GID = {1, 1, 1, 1, 1};

        parseReparse(1, 1, ONE_UID_GID, 1, 1);
    }
    @Test
    public void test4() throws ZipException {
        // Version=1, Len=5, 2^32, Len=5, 2^32 + 1
        // Esoteric test:  can we handle 40 bit numbers?
        final byte[] LENGTH_5 = {1, 5, 0, 0, 0, 0, 1, 5, 1, 0, 0, 0, 1};

        // Version=1, Len=8, 2^63 - 2, Len=8, 2^63 - 1
        // Esoteric test:  can we handle 64 bit numbers?
        final byte[] LENGTH_8 = {1, 8, -2, -1, -1, -1, -1, -1, -1, 127, 8, -1, -1, -1, -1, -1, -1, -1, 127};

        final long TWO_TO_32 = 0x100000000L;
        final long MAX = TWO_TO_32 - 2;

        parseReparse(TWO_TO_32, TWO_TO_32 + 1, LENGTH_5, TWO_TO_32, TWO_TO_32 + 1);
        parseReparse(Long.MAX_VALUE - 1, Long.MAX_VALUE, LENGTH_8, Long.MAX_VALUE - 1, Long.MAX_VALUE);
    }
    @Test
    public void test5() throws ZipException {
        // Version=1, Len=1, zero, Len=1, negative one.
        final byte[] NEGATIVE_UID_GID = {1, 1, 0, 1, -1};

        parseReparse(0, -1, NEGATIVE_UID_GID, 0, -1);
    }
    @Test
    public void test6() throws ZipException {
        // Version=1, Len=2, one thousand, Len=2, -32000
        final byte[] LARGE_NEGATIVE_UID_GID = {1, 2, -24, 3, -128, 0};

        parseReparse(1000, -32000, LARGE_NEGATIVE_UID_GID, 1000, -32000);
    }
    @Test
    public void test7() throws Exception {
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertTrue(xf.equals(o));
        xf.setUID(0); // regression test: setting UID to 0
        assertFalse(xf.equals(o));
    }
    @Test
    public void test8() throws Exception {
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertTrue(xf.equals(o));
        xf.setUID(65535); // regression test: setting UID to maximum value
        assertFalse(xf.equals(o));
    }
    @Test
    public void test9() throws Exception {
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertTrue(xf.equals(o));
        xf.setUID(1001); // regression test: setting UID to a different value
        assertFalse(xf.equals(o));
    }
    @Test
    public void test10() {
        // Version=1, Len=1, 100, Len=1, 100
        final byte[] HUNDRED_UID_GID = {1, 1, 100, 1, 100};

        // Version=1, Len=2, 5000, Len=2, 5000
        final byte[] FIVE_THOUSAND_UID_GID = {1, 2, -24, 19, 2, -24, 19};

        // Version=1, Len=3, 1000000, Len=2, 1000000
        final byte[] ONE_MILLION_UID_GID = {1, 3, 64, -16, -96, 1, 3, 64, -16, -96};

        // Version=1, Len=4, 2000000000, Len=4, 2000000000
        final byte[] TWO_BILLION_UID_GID = {1, 4, 0, -38, -20, 79, 1, 4, 0, -38, -20, 79};

        setGID(100);
        assertEquals(100, ZipUtil.bigToLong(this.gid));
        assertTrue(Arrays.equals(HUNDRED_UID_GID, ZipUtil.longToBytes(this.gid)));

        setGID(5000);
        assertEquals(5000, ZipUtil.bigToLong(this.gid));
        assertTrue(Arrays.equals(FIVE_THOUSAND_UID_GID, ZipUtil.longToBytes(this.gid)));

        setGID(1000000);
        assertEquals(1000000, ZipUtil.bigToLong(this.gid));
        assertTrue(Arrays.equals(ONE_MILLION_UID_GID, ZipUtil.longToBytes(this.gid)));

        setGID(2000000000);
        assertEquals(2000000000, ZipUtil.bigToLong(this.gid));
        assertTrue(Arrays.equals(TWO_BILLION_UID_GID, ZipUtil.longToBytes(this.gid)));

    }
    @Test
    public void test11() throws Exception {
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
    public void test12() throws ZipException {

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
    public void test13() {
        int uidSize = trimLeadingZeroesForceMinLength(uid.toByteArray()).length;
        int gidSize = trimLeadingZeroesForceMinLength(gid.toByteArray()).length;

        // The 3 comes from:  version=1 + uidsize=1 + gidsize=1
        return new ZipShort(3 + uidSize + gidSize);
    }
    @Test
    public void test14() {
        int uidSize = 1;
        int gidSize = 1;

        // The 3 comes from:  version=1 + uidsize=1 + gidsize=1
        return new ZipShort(3 + uidSize + gidSize);
    }
    @Test
    public void test15() {
        int uidSize = 5;
        int gidSize = 7;

        // The 3 comes from:  version=1 + uidsize=1 + gidsize=1
        return new ZipShort(3 + uidSize + gidSize);
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

    // Regression testcases
    parseReparse(2, 2, ONE_UID_GID, 2, 2);
    parseReparse(500, 500, ONE_THOUSAND_UID_GID, 500, 500);
    parseReparse(MAX+1, MAX+1, UNIX_MAX_UID_GID, MAX+1, MAX+1);
    parseReparse(TWO_TO_32+10, TWO_TO_32+10, LENGTH_5, TWO_TO_32+10, TWO_TO_32+10);
    parseReparse(Long.MAX_VALUE-2, Long.MAX_VALUE-1, LENGTH_8, Long.MAX_VALUE-2, Long.MAX_VALUE-1);
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
    public void test18() throws ZipException {

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
    public void test19() {
        // Version=1, Len=4, (2^32 - 2), Len=4, (2^32 - 2)
        byte[] maxUidAndGid = {1, 4, 0xFE, 0xFF, 0xFF, 0xFF, 4, 0xFE, 0xFF, 0xFF, 0xFF};
        xf.parseFromLocalFileData(maxUidAndGid, 0, maxUidAndGid.length);

        assertEquals(0xFFFFFFFE, xf.getUID());
        assertEquals(0xFFFFFFFE, xf.getGID());
        assertTrue(Arrays.equals(maxUidAndGid, xf.getLocalFileDataData()));
    }
    @Test
    public void test20() {
        // Version=1, Len=0, Len=0
        byte[] zeroLength = {1, 0, 0};
        xf.parseFromLocalFileData(zeroLength, 0, zeroLength.length);

        assertEquals(0, xf.getUID());
        assertEquals(0, xf.getGID());
        assertTrue(Arrays.equals(zeroLength, xf.getLocalFileDataData()));
    }
    @Test(expected = IllegalArgumentException.class)
    public void test21() {
        // Version=2, Len=4, 1, Len=1, 1
        byte[] invalidVersion = {2, 4, 0, 0, 0, 1, 1, 1};
        xf.parseFromLocalFileData(invalidVersion, 0, invalidVersion.length);
    }
    @Test(expected = IllegalArgumentException.class)
    public void test22() {
        // Version=1, Len=3, 255
        byte[] noDataForLengths = {1, 3, 0xFF};
        xf.parseFromLocalFileData(noDataForLengths, 0, noDataForLengths.length);
    }
    @Test
    public void test23() {
        // Test case 1: Empty byte array
        byte[] expected1 = new byte[0];
        byte[] result1 = getCentralDirectoryData();
        assertArrayEquals(expected1, result1);

        // Test case 2: Non-empty byte array
        byte[] expected2 = {1, 2, 3};
        byte[] result2 = getCentralDirectoryData();
        assertArrayEquals(expected2, result2);

        // Test case 3: Null byte array
        byte[] expected3 = null;
        byte[] result3 = getCentralDirectoryData();
        assertEquals(expected3, result3);
    }
    @Test
    public void test24() throws ZipException {
        byte[] data = {0, 2, 100, 200, 0, 2, 150, 250};
        int offset = 0;
        int length = data.length;

        try {
            parseFromLocalFileData(data, offset, length);
            assertEquals(0, version);
            assertEquals(2, uid);
            assertEquals(2, gid);
        } catch (ZipException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }
    @Test
    public void test25() throws ZipException {
        byte[] data = {1, 1, -1, 1, -1};
        int offset = 0;
        int length = data.length;

        try {
            parseFromLocalFileData(data, offset, length);
            assertEquals(255, uid);
            assertEquals(128, gid);
        } catch (ZipException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }
    @Test
    public void test26() throws ZipException {
        byte[] data = {1, 8, -1, -1, -1, -1, -1, -1, -1, 127, 8, -1, -1, -1, -1, -1, -1, -1, 127};
        int offset = 0;
        int length = data.length;

        try {
            parseFromLocalFileData(data, offset, length);
            assertEquals(Long.MAX_VALUE - 1, uid);
            assertEquals(Long.MAX_VALUE, gid);
        } catch (ZipException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }
    @Test
    public void test27() throws ZipException {
        byte[] data = {1, 0, 0};
        int offset = 0;
        int length = data.length;

        try {
            parseFromLocalFileData(data, offset, length);
            assertEquals(0, uid);
            assertEquals(0, gid);
        } catch (ZipException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }
    @Test
    public void test28() throws ZipException {
        byte[] data = {1, 4, -2, -1, -1, -1, 4, -2, -1, -1, -1};
        int offset = 0;
        int length = data.length;

        try {
            parseFromLocalFileData(data, offset, length);
            assertEquals(4294967294L, uid);
            assertEquals(4294967294L, gid);
        } catch (ZipException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }
    @Test
    public void test29() throws ZipException {
        byte[] data = {1, 5, 0, 0, 0, 0, 1, 5, 1, 0, 0, 0, 1};
        int offset = 0;
        int length = data.length;

        try {
            parseFromLocalFileData(data, offset, length);
            assertEquals(4294967296L, uid);
            assertEquals(4294967297L, gid);
        } catch (ZipException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
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
            zae.removeExtraField(X7875); // Remove the extra field

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
public void test31() throws Exception {
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
            long expected = 2000; // Change the expected UID and GID values
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
    public void test32() throws Exception {
        File archive = getFile("COMPRESS-211_uid_gid_zip_test.zip");
        ZipFile zf = null;

        try {
            zf = new ZipFile(archive);
            Enumeration<ZipArchiveEntry> en = zf.getEntries();

            while (en.hasMoreElements()) {

                ZipArchiveEntry zae = en.nextElement();
                String name = zae.getName();
                X7875_NewUnix xf = (X7875_NewUnix) zae.getExtraField(X7875);

                // Change the input value to a negative UID
                long expected = -1;
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

                // Change the input value to a zero GID
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
                assertEquals(0, xf.getGID());
            }
        } finally {
            if (zf != null) {
                zf.close();
            }
        }
    }
    @Test
    public void test34() throws Exception {
        File archive = getFile("COMPRESS-211_uid_gid_zip_test.zip");
        ZipFile zf = null;

        try {
            zf = new ZipFile(archive);
            Enumeration<ZipArchiveEntry> en = zf.getEntries();

            while (en.hasMoreElements()) {

                ZipArchiveEntry zae = en.nextElement();
                String name = zae.getName();
                X7875_NewUnix xf = (X7875_NewUnix) zae.getExtraField(X7875);

                // Change the input value to the maximum UID
                long expected = 0x100000000L - 2;
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
        xf.setUID(0);
        xf.setGID(0);

        assertEquals("0x7875 Zip Extra Field: UID=0 GID=0", xf.toString());

        xf.setUID(123);
        xf.setGID(456);

        assertEquals("0x7875 Zip Extra Field: UID=123 GID=456", xf.toString());
    }
    @Test
    public void test36() throws Exception {
        xf.setUID(0);
        xf.setGID(0);

        ZipExtraField xf2 = new ZipExtraField();

        assertTrue(xf.equals(xf));
        assertFalse(xf.equals(null));
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.equals(xf2));

        xf.setUID(123);
        xf.setGID(456);

        assertFalse(xf.equals(xf2));
    }
    @Test
    public void test37() throws Exception {
        xf.setUID(0);
        xf.setGID(0);

        ZipExtraField xf2 = new ZipExtraField();

        assertEquals(xf.hashCode(), xf.hashCode());
        assertEquals(xf.hashCode(), xf2.hashCode());

        xf.setUID(123);
        xf.setGID(456);

        assertNotEquals(xf.hashCode(), xf2.hashCode());
    }
    @Test
    public void test38() throws Exception {
        xf.setUID(0);
        xf.setGID(0);

        ZipExtraField xf2 = xf.clone();

        assertEquals(xf, xf2);

        xf.setUID(123);
        xf.setGID(456);

        assertNotEquals(xf, xf2);
    }
    @Test
    public void test39() throws Exception {
        // Create a different object to compare with the cloned object
        Object obj = new Object();
        
        // Clone the object
        Object clonedObj = obj.clone();
        
        // Verify that the cloned object is not the same as the original object
        assertFalse(obj.equals(clonedObj));
        assertNotSame(obj, clonedObj);
    }
    @Test
    public void test40() throws Exception {
        // Create a ZipExtraField object
        ZipExtraField xf = new ZipExtraField();
        
        // Clone the object
        ZipExtraField clonedXf = (ZipExtraField) xf.clone();
        
        // Change the field values of the original object
        xf.setUID(12345);
        xf.setVersion(2);
        
        // Verify that the field values of the cloned object are not the same as the field values of the original object
        assertNotEquals(xf.getUID(), clonedXf.getUID());
        assertNotEquals(xf.getVersion(), clonedXf.getVersion());
    }
    @Test
    public void test41() throws Exception {
        // Create a ZipExtraField object with specific field values
        ZipExtraField xf = new ZipExtraField();
        xf.setUID(12345);
        xf.setVersion(2);
        
        // Clone the object
        ZipExtraField clonedXf = (ZipExtraField) xf.clone();
        
        // Verify that the field values of the cloned object are the same as the field values of the original object
        assertEquals(xf.getUID(), clonedXf.getUID());
        assertEquals(xf.getVersion(), clonedXf.getVersion());
    }
    @Test
    public void test42() throws Exception {
        // Create a different object to compare with the cloned object
        Object obj = new Object();
        
        // Clone the object
        Object clonedObj = obj.clone();
        
        // Verify that the hash code of the cloned object is not the same as the hash code of the original object
        assertNotEquals(obj.hashCode(), clonedObj.hashCode());
    }
    @Test
    public void test43() {
        X7875_NewUnix xf1 = new X7875_NewUnix(1, "1001", "2000");
        X7875_NewUnix xf2 = new X7875_NewUnix(1, "1000", "2000");
        assertFalse(xf1.equals(xf2));
    }
    @Test
    public void test44() {
        X7875_NewUnix xf1 = new X7875_NewUnix(1, "1000", "1000");
        X7875_NewUnix xf2 = new X7875_NewUnix(1, "1000", "2000");
        assertFalse(xf1.equals(xf2));
    }
    @Test
    public void test45() {
        X7875_NewUnix xf1 = new X7875_NewUnix("1", "1000", "2000");
        X7875_NewUnix xf2 = new X7875_NewUnix(1, "1000", "2000");
        assertFalse(xf1.equals(xf2));
    }
    @Test
    public void test46() {
        X7875_NewUnix xf1 = new X7875_NewUnix(1, null, "2000");
        X7875_NewUnix xf2 = new X7875_NewUnix(1, "1000", "2000");
        assertFalse(xf1.equals(xf2));
    }
    @Test
    public void test47() {
        X7875_NewUnix xf1 = new X7875_NewUnix(1, "1000", null);
        X7875_NewUnix xf2 = new X7875_NewUnix(1, "1000", "2000");
        assertFalse(xf1.equals(xf2));
    }
    @Test
    public void test48() throws Exception {
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
    public void test49() throws Exception {
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertTrue(xf.equals(o));
        xf.setUID(12345);
        assertFalse(xf.equals(o));
    }
    @Test
    public void test50() {
        // Create a new X7875_NewUnix object with version 0
        X7875_NewUnix xf = new X7875_NewUnix();
        xf.setVersion(0);
        xf.setUID(1000);
        xf.setGID(1000);

        // Check that the hashCode calculation is correct
        assertEquals(-1234567 * 0, xf.hashCode());

        // Check that the UID and GID values are correct
        assertEquals(1000, xf.getUID());
        assertEquals(1000, xf.getGID());
    }
    @Test
    public void test51() {
        // Create a new X7875_NewUnix object with a negative version
        X7875_NewUnix xf = new X7875_NewUnix();
        xf.setVersion(-1);
        xf.setUID(2000);
        xf.setGID(2000);

        // Check that the hashCode calculation is correct
        assertEquals(-1234567 * -1, xf.hashCode());

        // Check that the UID and GID values are correct
        assertEquals(2000, xf.getUID());
        assertEquals(2000, xf.getGID());
    }
    @Test
    public void test52() {
        // Create a new X7875_NewUnix object with the maximum UID and GID values
        X7875_NewUnix xf = new X7875_NewUnix();
        xf.setVersion(2);
        xf.setUID(0xffffffffL - 2);
        xf.setGID(0xffffffffL - 2);

        // Check that the hashCode calculation is correct
        assertEquals(-1234567 * 2, xf.hashCode());

        // Check that the UID and GID values are correct
        assertEquals(0xffffffffL - 2, xf.getUID());
        assertEquals(0xffffffffL - 2, xf.getGID());
    }
    @Test
    public void test53() {
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
        final byte[] SEQUENCE_LEADING_ZERO_EXCEPTION = {0, 1, -128, 0};
        final byte[] TRAILING_ZERO_EXCEPTION = {0, 1, 2, 0};

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
        assertTrue(Arrays.equals(SEQUENCE_LEADING_ZERO, trimTest(SEQUENCE_LEADING_ZERO_EXCEPTION)));
        assertTrue(Arrays.equals(TRAILING_ZERO, trimTest(TRAILING_ZERO_EXCEPTION)));
    }
    @Test
    public void test54() {
        ...
        final byte[] SEQUENCE_LEADING_ZERO_EXCEPTION = {0, 1, -128, 0};
        final byte[] TRAILING_ZERO_EXCEPTION = {0, 1, 2, 0};

        ...
        assertTrue(Arrays.equals(SEQUENCE_LEADING_ZERO, trimTest(SEQUENCE_LEADING_ZERO_EXCEPTION)));
        assertTrue(Arrays.equals(TRAILING_ZERO, trimTest(TRAILING_ZERO_EXCEPTION)));
    }
}