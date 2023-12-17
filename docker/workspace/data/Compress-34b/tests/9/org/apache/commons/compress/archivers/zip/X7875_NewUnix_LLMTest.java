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
        assertEquals(HEADER_ID + 1, xf.getHeaderId());
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
        // Version=1 Len=0 Len=0.
        final byte[] ZERO_LEN = {1, 0, 0};

        parseReparse(-1, -1, ZERO_LEN, -1, -1); // Negative value for UID and GID
        parseReparse(2147483647, 2147483647, ZERO_LEN, 2147483647, 2147483647); // Maximum positive value for UID and GID
        parseReparse(0, 0, ZERO_LEN, 0, 0); // Minimum positive value for UID and GID
        parseReparse(126, 126, ZERO_LEN, 126, 126); // Arbitrary positive value for UID and GID
    }
@Test
public void test3() throws ZipException {

    // Version=1, Len=0, Len=0.
    final byte[] ZERO_LEN = {0, 0, 0};

    // Version=1, Len=1, zero, Len=1, zero.
    final byte[] ZERO_UID_GID = {0, 1, 0, 1, 0};

    // Version=1, Len=1, one, Len=1, one
    final byte[] ONE_UID_GID = {0, 1, 1, 1, 1};

    // Version=1, Len=2, one thousand, Len=2, one thousand
    final byte[] ONE_THOUSAND_UID_GID = {0, 2, -56, -24, 0, 2, -56, -24};

    // (2^32 - 2).   I guess they avoid (2^32 - 1) since it's identical to -1 in
    // two's complement, and -1 often has a special meaning.
    final byte[] UNIX_MAX_UID_GID = {0, 4, -1, -1, -1, -1, 0, 4, -1, -1, -1, -1};

    // Version=1, Len=5, 2^32, Len=5, 2^32 + 1
    // Esoteric test:  can we handle 40 bit numbers?
    final byte[] LENGTH_5 = {0, 5, 0, 0, 0, 0, 1, 5, 0, 1, 0, 0};

    // Version=1, Len=8, 2^63 - 2, Len=8, 2^63 - 1
    // Esoteric test:  can we handle 64 bit numbers?
    final byte[] LENGTH_8 = {0, 8, -1, -1, -1, -1, -1, -1, -1, 127, 0, 8, -1, -1, -1, -1, -1, -1, -1, 127};

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
    // Regression test case 1: Negative input
    parseReparse(-1000, -1000, ZERO_LEN, 0, 0);
    // Regression test case 2: Large input
    parseReparse(Long.MAX_VALUE, Long.MAX_VALUE, LENGTH_8, Long.MAX_VALUE, Long.MAX_VALUE);
}
    @Test
    public void test4() throws ZipException {

        // Version=1, Len=0, Len=0. [regression test]
        final byte[] ZERO_LEN = {1, 0, 0};

        // Version=1, Len=1, zero, Len=1, zero. [regression test]
        final byte[] ZERO_UID_GID = {1, 1, 0, 1, 0};

        // Version=1, Len=1, one, Len=1, one [regression test]
        final byte[] ONE_UID_GID = {1, 1, 1, 1, 1};

        // Version=1, Len=2, one thousand, Len=2, one thousand [regression test]
        final byte[] ONE_THOUSAND_UID_GID = {1, 2, -24, 3, 2, -24, 3};

        // (2^32 - 2).   I guess they avoid (2^32 - 1) since it's identical to -1 in
        // two's complement, and -1 often has a special meaning. [regression test]
        final byte[] UNIX_MAX_UID_GID = {1, 4, -2, -1, -1, -1, 4, -2, -1, -1, -1};

        // Version=1, Len=5, 2^32, Len=5, 2^32 + 1
        // Esoteric test:  can we handle 40 bit numbers? [regression test]
        final byte[] LENGTH_5 = {1, 5, 0, 0, 0, 0, 1, 5, 1, 0, 0, 0, 1};

        // Version=1, Len=8, 2^63 - 2, Len=8, 2^63 - 1
        // Esoteric test:  can we handle 64 bit numbers? [regression test]
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

        // We never emit this, but we should be able to parse it: [regression test]
        final byte[] SPURIOUS_ZEROES_1 = {1, 4, -1, 0, 0, 0, 4, -128, 0, 0, 0};
        final byte[] EXPECTED_1 = {1, 1, -1, 1, -128};
        xf.parseFromLocalFileData(SPURIOUS_ZEROES_1, 0, SPURIOUS_ZEROES_1.length);

        assertEquals(255, xf.getUID());
        assertEquals(128, xf.getGID());
        assertTrue(Arrays.equals(EXPECTED_1, xf.getLocalFileDataData()));
        
        // Regression tests with different input
        
        // Version=1, Len=0, Len=0. [regression test with different input] (original test with different input)
        final byte[] ZERO_LEN_DIFF = {0, 1, 1};

        // Version=1, Len=1, zero, Len=1, zero. [regression test with different input] (original test with different input)
        final byte[] ZERO_UID_GID_DIFF = {0, 0, 1, 0, 1};

        // Version=1, Len=1, one, Len=1, one [regression test with different input] (original test with different input)
        final byte[] ONE_UID_GID_DIFF = {1, 1, 1, 0, 0};

        // Version=1, Len=2, one thousand, Len=2, one thousand [regression test with different input] (original test with different input)
        final byte[] ONE_THOUSAND_UID_GID_DIFF = {1, 2, 0, 0, -24, -24, 2, 3, 2, -24};

        // (2^32 - 2).   I guess they avoid (2^32 - 1) since it's identical to -1 in
        // two's complement, and -1 often has a special meaning. [regression test with different input] (original test with different input)
        final byte[] UNIX_MAX_UID_GID_DIFF = {1, 4, -2, -1, -1, -1, 4, -2, -1, -1, -1};

        // Version=1, Len=5, 2^32, Len=5, 2^32 + 1
        // Esoteric test:  can we handle 40 bit numbers? [regression test with different input] (original test with different input)
        final byte[] LENGTH_5_DIFF = {3, 2, 0, 0, 0, 1, 5, 1, 0, 0, 0, 0};

        // Version=1, Len=8, 2^63 - 2, Len=8, 2^63 - 1
        // Esoteric test:  can we handle 64 bit numbers? [regression test with different input] (original test with different input)
        final byte[] LENGTH_8_DIFF = {8, 1, -1, -1, -1, -1, -1, -1, -1, 127, 8, -1, -1, -1, -1, -1, -1, -1, 127};

        parseReparse(0, 0, ZERO_LEN_DIFF, 0, 0);
        parseReparse(0, 0, ZERO_UID_GID_DIFF, 0, 0);
        parseReparse(1, 1, ONE_UID_GID_DIFF, 1, 1);
        parseReparse(1000, 1000, ONE_THOUSAND_UID_GID_DIFF, 1000, 1000);
        parseReparse(MAX, MAX, UNIX_MAX_UID_GID_DIFF, MAX, MAX);
        parseReparse(-2, -2, UNIX_MAX_UID_GID_DIFF, MAX, MAX);
        parseReparse(TWO_TO_32, TWO_TO_32 + 1, LENGTH_5_DIFF, TWO_TO_32, TWO_TO_32 + 1);
        parseReparse(Long.MAX_VALUE - 1, Long.MAX_VALUE, LENGTH_8_DIFF, Long.MAX_VALUE - 1, Long.MAX_VALUE);

        // We never emit this, but we should be able to parse it: [regression test with different input] (original test with different input)
        final byte[] SPURIOUS_ZEROES_2 = {1, 4, -1, -1, 0, 0, 4, 1, 2, 0, 0};
        final byte[] EXPECTED_2 = {1, 2, -1, -1, 2, 1, 2};
        xf.parseFromLocalFileData(SPURIOUS_ZEROES_2, 0, SPURIOUS_ZEROES_2.length);

        assertEquals(65535, xf.getUID());
        assertEquals(513, xf.getGID());
        assertTrue(Arrays.equals(EXPECTED_2, xf.getLocalFileDataData()));
        
        // Regression tests with different input
        
        // Version=1, Len=0, Len=0. [regression test with different input] (original test with different input)
        final byte[] ZERO_LEN_DIFF_2 = {1, 1, 1};

        // Version=1, Len=1, zero, Len=1, zero. [regression test with different input] (original test with different input)
        final byte[] ZERO_UID_GID_DIFF_2 = {1, 1, 0, 1, 1};

        // Version=1, Len=1, one, Len=1, one [regression test with different input] (original test with different input)
        final byte[] ONE_UID_GID_DIFF_2 = {1, 1, 1, 1, 0};

        // Version=1, Len=2, one thousand, Len=2, one thousand [regression test with different input] (original test with different input)
        final byte[] ONE_THOUSAND_UID_GID_DIFF_2 = {1, 2, -24, 3, 2, -24, 2};

        // (2^32 - 2).   I guess they avoid (2^32 - 1) since it's identical to -1 in
        // two's complement, and -1 often has a special meaning. [regression test with different input] (original test with different input)
        final byte[] UNIX_MAX_UID_GID_DIFF_2 = {1, 4, -2, -1, -1, -1, 4, -2, -1, -1, -2};

        // Version=1, Len=5, 2^32, Len=5, 2^32 + 1
        // Esoteric test:  can we handle 40 bit numbers? [regression test with different input] (original test with different input)
        final byte[] LENGTH_5_DIFF_2 = {1, 2, 0, 0, 0, 0, 1, 5, 0, 1, 2, 3};

        // Version=1, Len=8, 2^63 - 2, Len=8, 2^63 - 1
        // Esoteric test:  can we handle 64 bit numbers? [regression test with different input] (original test with different input)
        final byte[] LENGTH_8_DIFF_2 = {8, 1, -2, -2, -2, -2, -2, -2, -2, 126, 8, -2, -2, -2, -2, -2, -2, -2, 126};

        parseReparse(0, 0, ZERO_LEN_DIFF_2, 0, 0);
        parseReparse(0, 0, ZERO_UID_GID_DIFF_2, 0, 0);
        parseReparse(1, 1, ONE_UID_GID_DIFF_2, 1, 1);
        parseReparse(1000, 1000, ONE_THOUSAND_UID_GID_DIFF_2, 1000, 1000);
        parseReparse(MAX, MAX, UNIX_MAX_UID_GID_DIFF_2, MAX, MAX);
        parseReparse(-2, -2, UNIX_MAX_UID_GID_DIFF_2, MAX, MAX);
        parseReparse(TWO_TO_32, TWO_TO_32 + 1, LENGTH_5_DIFF_2, TWO_TO_32, TWO_TO_32 + 1);
        parseReparse(Long.MAX_VALUE - 1, Long.MAX_VALUE, LENGTH_8_DIFF_2, Long.MAX_VALUE - 1, Long.MAX_VALUE);

        // We never emit this, but we should be able to parse it: [regression test with different input] (original test with different input)
        final byte[] SPURIOUS_ZEROES_3 = {1, 5, 0, 0, 0, 1, 4, 0, 0, 1, 0};
        final byte[] EXPECTED_3 = {1, 0, 0, 1, 4, 0, 0, 1};
        xf.parseFromLocalFileData(SPURIOUS_ZEROES_3, 0, SPURIOUS_ZEROES_3.length);

        assertEquals(256, xf.getUID());
        assertEquals(260, xf.getGID());
        assertTrue(Arrays.equals(EXPECTED_3, xf.getLocalFileDataData()));
    }
    @Test
    public void test5() throws Exception {
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertTrue(xf.equals(o));
        xf.setUID(12345);
        assertFalse(xf.equals(o));
    }
    @Test
    public void test6() throws ZipException {

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
        
        // Regression tests with modified input values 
        parseReparse(1001, 1000, ONE_THOUSAND_UID_GID, 1000, 1000);
        parseReparse(MAX + 1, MAX + 1, UNIX_MAX_UID_GID, MAX, MAX);
        parseReparse(-3, -3, UNIX_MAX_UID_GID, MAX, MAX);
        parseReparse(TWO_TO_32 + 1, TWO_TO_32 + 2, LENGTH_5, TWO_TO_32, TWO_TO_32 + 1);
        parseReparse(Long.MAX_VALUE, Long.MAX_VALUE + 1, LENGTH_8, Long.MAX_VALUE - 1, Long.MAX_VALUE);

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
    public void test7() throws Exception {
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
    public void test8() throws ZipException {

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
    public void test9() {
        int uidSize = trimLeadingZeroesForceMinLength(uid.toByteArray()).length;
        int gidSize = trimLeadingZeroesForceMinLength(gid.toByteArray()).length;

        // The 3 comes from:  version=1 + uidsize=1 + gidsize=1
        assertEquals(3, uidSize);
        assertEquals(1, gidSize);
    }
    @Test
    public void test10() {
        int uidSize = trimLeadingZeroesForceMinLength(uid.toByteArray()).length;
        int gidSize = trimLeadingZeroesForceMinLength(gid.toByteArray()).length;

        // The 3 comes from:  version=1 + uidsize=1 + gidsize=1
        assertEquals(1, uidSize);
        assertEquals(3, gidSize);
    }
    @Test
    public void test11() {
        int uidSize = trimLeadingZeroesForceMinLength(uid.toByteArray()).length;
        int gidSize = trimLeadingZeroesForceMinLength(gid.toByteArray()).length;

        long expectedUIDSize = 4294967296L;
        long expectedGIDSize = 4;

        // The 3 comes from:  version=1 + uidsize=1 + gidsize=1
        assertEquals(expectedUIDSize, uidSize);
        assertEquals(expectedGIDSize, gidSize);
    }
    @Test
    public void test12() {
        int uidSize = trimLeadingZeroesForceMinLength(uid.toByteArray()).length;
        int gidSize = trimLeadingZeroesForceMinLength(gid.toByteArray()).length;

        long expectedUIDSize = 4;
        long expectedGIDSize = 4294967296L;

        // The 3 comes from:  version=1 + uidsize=1 + gidsize=1
        assertEquals(expectedUIDSize, uidSize);
        assertEquals(expectedGIDSize, gidSize);
    }
    @Test
    public void test13() throws ZipException {

        // Version=1, Len=0, Len=0.
        final byte[] ZERO_LEN = {1, 0, 0};
        final byte[] ZERO_LEN_MODIFIED = {2, 0, 0}; // Modified input value

        // Version=1, Len=1, zero, Len=1, zero.
        final byte[] ZERO_UID_GID = {1, 1, 0, 1, 0};
        final byte[] ZERO_UID_GID_MODIFIED = {2, 1, 0, 1, 0}; // Modified input value

        // Version=1, Len=1, one, Len=1, one
        final byte[] ONE_UID_GID = {1, 1, 1, 1, 1};
        final byte[] ONE_UID_GID_MODIFIED = {2, 1, 1, 1, 1}; // Modified input value

        // Version=1, Len=2, one thousand, Len=2, one thousand
        final byte[] ONE_THOUSAND_UID_GID = {1, 2, -24, 3, 2, -24, 3};
        final byte[] ONE_THOUSAND_UID_GID_MODIFIED = {2, 2, -24, 3, 2, -24, 3}; // Modified input value

        // (2^32 - 2).   I guess they avoid (2^32 - 1) since it's identical to -1 in
        // two's complement, and -1 often has a special meaning.
        final byte[] UNIX_MAX_UID_GID = {1, 4, -2, -1, -1, -1, 4, -2, -1, -1, -1};
        final byte[] UNIX_MAX_UID_GID_MODIFIED = {2, 4, -2, -1, -1, -1, 4, -2, -1, -1, -1}; // Modified input value

        // Version=1, Len=5, 2^32, Len=5, 2^32 + 1
        // Esoteric test:  can we handle 40 bit numbers?
        final byte[] LENGTH_5 = {1, 5, 0, 0, 0, 0, 1};
        final byte[] LENGTH_5_MODIFIED = {2, 5, 0, 0, 0, 0, 1}; // Modified input value

        // Version=1, Len=8, 2^63 - 2, Len=8, 2^63 - 1
        // Esoteric test:  can we handle 64 bit numbers?
        final byte[] LENGTH_8 = {1, 8, -2, -1, -1, -1, -1, -1, -1, 127};
        final byte[] LENGTH_8_MODIFIED = {2, 8, -2, -1, -1, -1, -1, -1, -1, 127}; // Modified input value

        final long TWO_TO_32 = 0x100000000L;
        final long MAX = TWO_TO_32 - 2;

        parseReparse(0, 0, ZERO_LEN, 0, 0);
        parseReparse(0, 0, ZERO_LEN_MODIFIED, 2, 0); // Modified input value
        parseReparse(0, 0, ZERO_UID_GID, 0, 0);
        parseReparse(1, 1, ONE_UID_GID, 1, 1);
        parseReparse(1, 1, ONE_UID_GID_MODIFIED, 1, 1); // Modified input value
        parseReparse(1000, 1000, ONE_THOUSAND_UID_GID, 1000, 1000);
        parseReparse(1000, 1000, ONE_THOUSAND_UID_GID_MODIFIED, 1000, 1000); // Modified input value
        parseReparse(MAX, MAX, UNIX_MAX_UID_GID, MAX, MAX);
        parseReparse(MAX, MAX, UNIX_MAX_UID_GID_MODIFIED, MAX, MAX); // Modified input value
        parseReparse(-2, -2, UNIX_MAX_UID_GID, MAX, MAX);
        parseReparse(-2, -2, UNIX_MAX_UID_GID_MODIFIED, MAX, MAX); // Modified input value
        parseReparse(TWO_TO_32, TWO_TO_32 + 1, LENGTH_5, TWO_TO_32, TWO_TO_32 + 1);
        parseReparse(TWO_TO_32, TWO_TO_32 + 1, LENGTH_5_MODIFIED, TWO_TO_32, TWO_TO_32 + 1); // Modified input value
        parseReparse(Long.MAX_VALUE - 1, Long.MAX_VALUE, LENGTH_8, Long.MAX_VALUE - 1, Long.MAX_VALUE);
        parseReparse(Long.MAX_VALUE - 1, Long.MAX_VALUE, LENGTH_8_MODIFIED, Long.MAX_VALUE - 1, Long.MAX_VALUE); // Modified input value
        
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
    public void test14() {
        // Generate unique byte array for uid and gid input
        byte[] uidBytes = {1, 2, 3, 4};
        byte[] gidBytes = {5, 6, 7, 8};

        // Set the version and expected length values
        byte version = 1;
        byte expectedUidLength = 4;
        byte expectedGidLength = 4;

        // Calculate the expected data length
        int expectedDataLength = 3 + expectedUidLength + expectedGidLength;

        // Create the expected data array
        byte[] expectedData = new byte[expectedDataLength];
        expectedData[0] = version;
        expectedData[1] = expectedUidLength;
        expectedData[2] = uidBytes[0];
        expectedData[3] = uidBytes[1];
        expectedData[4] = uidBytes[2];
        expectedData[5] = uidBytes[3];
        expectedData[6] = expectedGidLength;
        expectedData[7] = gidBytes[0];
        expectedData[8] = gidBytes[1];
        expectedData[9] = gidBytes[2];
        expectedData[10] = gidBytes[3];

        // Call the method under test
        byte[] result = getLocalFileDataData(uidBytes, gidBytes);

        // Verify the result
        assertArrayEquals(expectedData, result);
    }
    @Test
    public void test15() {
        // Generate unique byte array for uid and gid input
        byte[] uidBytes = {10, 20};
        byte[] gidBytes = {30, 40};

        // Set the version and expected length values
        byte version = 1;
        byte expectedUidLength = 2;
        byte expectedGidLength = 2;

        // Calculate the expected data length
        int expectedDataLength = 3 + expectedUidLength + expectedGidLength;

        // Create the expected data array
        byte[] expectedData = new byte[expectedDataLength];
        expectedData[0] = version;
        expectedData[1] = expectedUidLength;
        expectedData[2] = uidBytes[0];
        expectedData[3] = uidBytes[1];
        expectedData[4] = expectedGidLength;
        expectedData[5] = gidBytes[0];
        expectedData[6] = gidBytes[1];

        // Call the method under test
        byte[] result = getLocalFileDataData(uidBytes, gidBytes);

        // Verify the result
        assertArrayEquals(expectedData, result);
    }
    private byte[] getLocalFileDataData(byte[] uidBytes, byte[] gidBytes) {
        // BigInteger might prepend a leading-zero to force a positive representation
        // (e.g., so that the sign-bit is set to zero).  We need to remove that
        // before sending the number over the wire.
        uidBytes = trimLeadingZeroesForceMinLength(uidBytes);
        gidBytes = trimLeadingZeroesForceMinLength(gidBytes);

        // Calculate the data array length
        int dataLength = 3 + uidBytes.length + gidBytes.length;

        // Create the data array
        byte[] data = new byte[dataLength];

        // reverse() switches byte array from big-endian to little-endian.
        reverse(uidBytes);
        reverse(gidBytes);

        int pos = 0;
        data[pos++] = unsignedIntToSignedByte(version);
        data[pos++] = unsignedIntToSignedByte(uidBytes.length);
        System.arraycopy(uidBytes, 0, data, pos, uidBytes.length);
        pos += uidBytes.length;
        data[pos++] = unsignedIntToSignedByte(gidBytes.length);
        System.arraycopy(gidBytes, 0, data, pos, gidBytes.length);
        return data;
    }
    @Test
    public void test16() {
        // Test case 1: empty byte array
        byte[] result1 = getCentralDirectoryData();
        assertNotNull(result1);
        assertEquals(0, result1.length);

        // Test case 2: byte array with one element
        byte[] result2 = getCentralDirectoryData();
        assertNotNull(result2);
        assertEquals(1, result2.length);

        // Test case 3: byte array with multiple elements
        byte[] result3 = getCentralDirectoryData();
        assertNotNull(result3);
        assertTrue(result3.length > 1);
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

        // Additional test cases for regression testing

        // Version=1, Len=1, zero, Len=1, one
        final byte[] ZERO_ONE_UID_GID = {1, 1, 0, 1, 1};
        parseReparse(0, 1, ZERO_ONE_UID_GID, 0, 1);

        // Version=1, Len=2, one, Len=2, zero
        final byte[] ONE_ZERO_UID_GID = {1, 2, 1, 2, 0};
        parseReparse(1, 0, ONE_ZERO_UID_GID, 1, 0);

        // Version=1, Len=8, 2^63 - 2, Len=8, 2^63 - 2
        final byte[] LENGTH_8_2 = {1, 8, -2, -1, -1, -1, -1, -1, -1, 127, 8, -2, -1, -1, -1, -1, -1, -1, 127};
        parseReparse(Long.MAX_VALUE - 2, Long.MAX_VALUE - 2, LENGTH_8_2, Long.MAX_VALUE - 2, Long.MAX_VALUE - 2);
    }
@Test
public void test19() throws Exception {
    byte[] buffer = new byte[0];
    int offset = 0;
    int length = 0;
    
    try {
        parseFromCentralDirectoryData(buffer, offset, length);
        fail("Expected ZipException to be thrown");
    } catch (ZipException ex) {
        assertEquals("Invalid central directory data", ex.getMessage());
    }
}
@Test
public void test20() throws Exception {
    byte[] buffer = new byte[10];
    int offset = 0;
    int length = 15;
    
    try {
        parseFromCentralDirectoryData(buffer, offset, length);
        fail("Expected ZipException to be thrown");
    } catch (ZipException ex) {
        assertEquals("Invalid central directory data", ex.getMessage());
    }
}
@Test
public void test21() throws Exception {
    byte[] buffer = new byte[10];
    int offset = -1;
    int length = 10;
    
    try {
        parseFromCentralDirectoryData(buffer, offset, length);
        fail("Expected ZipException to be thrown");
    } catch (ZipException ex) {
        assertEquals("Invalid central directory data", ex.getMessage());
    }
}
@Test
public void test22() throws Exception {
    byte[] buffer = new byte[10];
    int offset = 0;
    int length = -1;
    
    try {
        parseFromCentralDirectoryData(buffer, offset, length);
        fail("Expected ZipException to be thrown");
    } catch (ZipException ex) {
        assertEquals("Invalid central directory data", ex.getMessage());
    }
}
@Test
public void test23() throws Exception {
    byte[] buffer = null;
    int offset = 0;
    int length = 0;
    
    try {
        parseFromCentralDirectoryData(buffer, offset, length);
        fail("Expected ZipException to be thrown");
    } catch (ZipException ex) {
        assertEquals("Invalid central directory data", ex.getMessage());
    }
}
    @Test
    public void test24() {
        assertEquals(X7875, xf.getHeaderId());
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
    public void test26() {
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
    public void test27() throws Exception {
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
    public void test28() throws ZipException {

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
    public void test29() {
        uid = 2000;
        gid = 2000;
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
    public void test31() {
        uid = -100;
        gid = -100;
        reset();
        assertEquals(1000, uid);
        assertEquals(1000, gid);
    }
    @Test
    public void test32() {
        xf.setUID(99999);
        assertTrue(xf.toString().contains("UID=99999"));
    }
    @Test
    public void test33() {
        xf.setGID(88888);
        assertTrue(xf.toString().contains("GID=88888"));
    }
    @Test
    public void test34() throws Exception {
        Object o = xf.clone();
        xf.setUID(99999);
        assertFalse(xf.equals(o));
    }
    @Test
    public void test35() throws Exception {
        Object o = xf.clone();
        xf.setGID(88888);
        assertFalse(xf.equals(o));
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
public void test37() throws Exception {
    assertFalse(xf.equals(null));
}
@Test
public void test38() throws Exception {
    assertFalse(xf.equals(new Integer(5)));
}
@Test
public void test39() throws Exception {
    xf.setUID(54321);
    Object o = xf.clone();
    assertNotEquals(o.hashCode(), xf.hashCode());
}
@Test
public void test40() throws Exception {
    Object o = xf.clone();
    xf.setUID(12345);
    ((ZipExtraField) o).setUID(12345);
    assertTrue(xf.equals(o));
}
@Test
public void test41() throws Exception {
    Object o = xf.clone();
    xf.setUID(98765);
    assertFalse(xf.equals(o));
}
    @Test
    public void test42() {
        assertEquals(X7875, xf.getHeaderId());
    }
    @Test
    public void test43() throws Exception {
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertTrue(xf.equals(o));
        xf.setUID(12345);
        assertFalse(xf.equals(o));
    }
    @Test
    public void test44() throws Exception {
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
    public void test45() {
        X7875_NewUnix xf2 = new X7875_NewUnix();
        xf2.setVersion(2);
        xf2.setUID(xf.getUID());
        xf2.setGID(xf.getGID());
        assertFalse(xf.equals(xf2));
    }
    @Test
    public void test46() {
        X7875_NewUnix xf2 = new X7875_NewUnix();
        xf2.setVersion(xf.getVersion());
        xf2.setUID(54321);
        xf2.setGID(xf.getGID());
        assertFalse(xf.equals(xf2));
    }
    @Test
    public void test47() {
        X7875_NewUnix xf2 = new X7875_NewUnix();
        xf2.setVersion(xf.getVersion());
        xf2.setUID(xf.getUID());
        xf2.setGID(54321);
        assertFalse(xf.equals(xf2));
    }
    @Test
    public void test48() throws Exception {
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertTrue(xf.equals(o));
        xf.setUID(12345);
        assertFalse(xf.equals(o));
        xf.setUID(0); // Regression test case - change UID to 0
        assertFalse(xf.equals(o));
        xf.setGID(54321); // Regression test case - change GID
        assertFalse(xf.equals(o));
    }
    @Test
    public void test49() throws Exception {
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
                
                // Regression test cases - change UID/GID values
                xf.setUID(1234);
                assertFalse(expected == xf.getUID());
                assertEquals(expected, xf.getGID());
                xf.setGID(4321);
                assertEquals(1234, xf.getUID());
                assertFalse(expected == xf.getGID());
            }
        } finally {
            if (zf != null) {
                zf.close();
            }
        }
    }
}