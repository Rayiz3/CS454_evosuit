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
        File archive = getFile("COMPRESS-211_no_extfield_zip_test.zip");
        ZipFile zf = null;

        try {
            zf = new ZipFile(archive);
            Enumeration<ZipArchiveEntry> en = zf.getEntries();

            // We expect EVERY entry of this zip file (dir & file) to
            // have no extra field.
            while (en.hasMoreElements()) {

                ZipArchiveEntry zae = en.nextElement();
                assertNull(zae.getExtraField(X7875));
            }
        } finally {
            if (zf != null) {
                zf.close();
            }
        }
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
                zae.setExtraFields(null);
                assertNull(zae.getExtraField(X7875));
            }
        } finally {
            if (zf != null) {
                zf.close();
            }
        }
    }
    @Test
    public void test2() throws Exception {
        File archive = getFile("COMPRESS-211_uid_gid_zip_test.zip");
        ZipFile zf = null;

        try {
            zf = new ZipFile(archive);
            Enumeration<ZipArchiveEntry> en = zf.getEntries();

            while (en.hasMoreElements()) {
                ZipArchiveEntry zae = en.nextElement();
                zae.setExtraFields(new ZipExtraField[] {});
                assertNull(zae.getExtraField(X7875));
            }
        } finally {
            if (zf != null) {
                zf.close();
            }
        }
    }
    @Test
    public void test3() throws Exception {
        File archive = getFile("COMPRESS-211_uid_gid_zip_test.zip");
        ZipFile zf = null;

        try {
            zf = new ZipFile(archive);
            Enumeration<ZipArchiveEntry> en = zf.getEntries();

            while (en.hasMoreElements()) {
                ZipArchiveEntry zae = en.nextElement();
                ZipExtraField invalidField = new X7876_NewUnix();
                zae.setExtraFields(new ZipExtraField[] { invalidField });
                assertNull(zae.getExtraField(X7875));
            }
        } finally {
            if (zf != null) {
                zf.close();
            }
        }
    }
    @Test
    public void test4() throws ZipException {
        // Version=1, Len=1, zero, Len=1, zero.
        final byte[] ZERO_UID_GID = {1, 1, 0, 1, 0};
        parseReparse(0, 0, ZERO_UID_GID, 1, 1);
    }
    @Test
    public void test5() throws ZipException {
        // Version=1, Len=2, one thousand, Len=2, one thousand
        final byte[] ONE_THOUSAND_UID_GID = {1, 2, -24, 3, 2, -24};
        parseReparse(1000, 1000, ONE_THOUSAND_UID_GID, 1, 2);
    }
    @Test
    public void test6() throws ZipException {
        // Version=1, Len=8, 2^63 - 2, Len=8, 2^63 - 1
        // Esoteric test:  can we handle 64 bit numbers?
        final byte[] LENGTH_8 = {1, 8, -2, -1, -1, -1, -1, -1, -1, 127, 8, -1, -1, -1, -1, -1, -1, -1, 127};
        parseReparse(Long.MAX_VALUE - 1, Long.MAX_VALUE, LENGTH_8, 8, -1);
    }
    @Test
    public void test7() throws ZipException {
        // Version=1, Len=1, negative one, Len=1, negative one
        final byte[] NEGATIVE_ONE_UID_GID = {1, 1, -1, 1, -1};

        parseReparse(-1, -1, NEGATIVE_ONE_UID_GID, -1, -1);
    }
    @Test
    public void test8() throws ZipException {
        // Version=1, Len=2, zero, Len=2, zero
        final byte[] ZERO_UID_GID = {1, 2, 0, 0, 2, 0, 0};

        parseReparse(0, 0, ZERO_UID_GID, 0, 0);
    }
    @Test
    public void test9() throws ZipException {
        // Version=1, Len=2, one, Len=2, one
        final byte[] ONE_UID_GID = {1, 2, 1, 0, 2, 1, 0};

        parseReparse(1, 1, ONE_UID_GID, 1, 1);
    }
    @Test
    public void test10() throws ZipException {
        // Version=1, Len=2, 1234, Len=2, 5678
        final byte[] RANDOM_UID_GID = {1, 2, -46, 4, 2, 22, -88};

        parseReparse(1234, 5678, RANDOM_UID_GID, 1234, 5678);
    }
    @Test
    public void test11() throws Exception {
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertTrue(xf.equals(o));
        xf.setUID(12345);
        assertFalse(xf.equals(o));
        xf.setUID(0); // new regression test case
        assertFalse(xf.equals(o));
        xf.setUID(9999); // new regression test case
        assertFalse(xf.equals(o));
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
        parseReparse(12345, 12345, ZERO_UID_GID, 12345, 12345); // new regression test case
        parseReparse(9999, 9999, ONE_UID_GID, 9999, 9999); // new regression test case

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
    public void test13() throws ZipException {

        // Version=1, Len=0, Len=1.
        final byte[] ZERO_LEN = {1, 0, 1};

        parseReparse(0, 0, ZERO_LEN, 0, 0);

        // Mutant killed if the expected return value is 0
        // instead of 1.
        assertEquals(0, xf.getGID());
    }
    @Test
    public void test14() throws ZipException {

        // Version=1, Len=1, zero, Len=2, zero.
        final byte[] ZERO_UID_GID = {1, 1, 0, 2, 0};

        parseReparse(0, 0, ZERO_UID_GID, 0, 0);

        // Mutant killed if the expected return value is 0
        // instead of 2.
        assertEquals(0, xf.getGID());
    }
    @Test
    public void test15() throws ZipException {

        // Version=1, Len=2, one, Len=1, one
        final byte[] ONE_UID_GID = {1, 2, 1, 1, 1};

        parseReparse(0, 0, ONE_UID_GID, 0, 0);

        // Mutant killed if the expected return value is 2
        // instead of 1.
        assertEquals(2, xf.getGID());
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

        // Regression tests
        final byte[] THREE_UID_GID = {1, 1, 3, 1, 3};
        parseReparse(3, 3, THREE_UID_GID, 3, 3);

        final byte[] TWO_THOUSAND_UID_GID = {1, 2, -48, 7, 2, -48, 7};
        parseReparse(2000, 2000, TWO_THOUSAND_UID_GID, 2000, 2000);

        final byte[] CUSTOM_UID_GID = {1, 2, -13, -30, 2, -13, -30};
        parseReparse(4938, 4938, CUSTOM_UID_GID, 4938, 4938);
    }
@Test
public void test18() throws ZipException {

    // Version=2, Len=0, Len=0.
    final byte[] ZERO_LEN = {2, 0, 0};

    // Version=1, Len=0, Len=0.
    final byte[] ZERO_LEN_V1 = {1, 0, 0};

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
    parseReparse(0, 0, ZERO_LEN_V1, 0, 0);
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

    // Regression tests

    // Version=0, Len=0, Len=0.
    final byte[] ZERO_LEN_V0 = {0, 0, 0};

    // Version=1, Len=-1, Len=-1.
    final byte[] NEGATIVE_LEN = {1, -1, -1};

    // Version=-1, Len=0, Len=0.
    final byte[] NEGATIVE_VERSION = {-1, 0, 0};

    // Version=1, Len=2, 2^16, Len=2, 2^16 + 1
    final byte[] LENGTH_2 = {1, 2, 0, 1, 0, 2};

    parseReparse(0, 0, ZERO_LEN_V0, 0, 0);
    parseReparse(1, -1, NEGATIVE_LEN, 1, -1);
    parseReparse(-1, 0, NEGATIVE_VERSION, 0, 0);
    parseReparse(65536, 65537, LENGTH_2, 65536, 65537);
}
    @Test
    public void test19() throws Exception {
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
    public void test20() throws ZipException {

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

        // Regression Test Cases
        // Negative test case with uid and gid higher than the maximum value
        final long HIGHER_UID_GID = 0x100000000L;
        byte[] higherUidGidData = new byte[6];
        higherUidGidData[0] = 1;
        higherUidGidData[1] = 4;
        higherUidGidData[2] = (byte)((HIGHER_UID_GID >> 24) & 0xFF);
        higherUidGidData[3] = (byte)((HIGHER_UID_GID >> 16) & 0xFF);
        higherUidGidData[4] = (byte)((HIGHER_UID_GID >> 8) & 0xFF);
        higherUidGidData[5] = (byte)(HIGHER_UID_GID & 0xFF);

        parseReparse(HIGHER_UID_GID, HIGHER_UID_GID, higherUidGidData, HIGHER_UID_GID, HIGHER_UID_GID);

        // Negative test case with uid and gid lower than the minimum value
        final long LOWER_UID_GID = -3;
        byte[] lowerUidGidData = new byte[6];
        lowerUidGidData[0] = 1;
        lowerUidGidData[1] = 4;
        lowerUidGidData[2] = (byte)((LOWER_UID_GID >> 24) & 0xFF);
        lowerUidGidData[3] = (byte)((LOWER_UID_GID >> 16) & 0xFF);
        lowerUidGidData[4] = (byte)((LOWER_UID_GID >> 8) & 0xFF);
        lowerUidGidData[5] = (byte)(LOWER_UID_GID & 0xFF);

        parseReparse(LOWER_UID_GID, LOWER_UID_GID, lowerUidGidData, LOWER_UID_GID, LOWER_UID_GID);
    }
public void test21() {
   byte[] input = new byte[0];

   byte[] result = getCentralDirectoryData(input);

   assertEquals(0, result.length);
}
public void test22() {
   byte[] input = new byte[10];

   byte[] result = getCentralDirectoryData(input);

   assertEquals(10, result.length);
}
    @Test
    public void test23() throws ZipException {
        byte[] data = { -1, 4, 0, 0, 0, 0, 4, -128, 0, 0, 0 }; // Negative version

        try {
            parseFromLocalFileData(data, 0, data.length);
            fail("Expected ZipException to be thrown");
        } catch (ZipException e) {
            assertEquals("Invalid version: -1", e.getMessage());
        }
    }
    @Test
    public void test24() throws ZipException {
        byte[] data = { 1, 0, 0, 1, 0 }; // Zero uidSize

        try {
            parseFromLocalFileData(data, 0, data.length);
            fail("Expected ZipException to be thrown");
        } catch (ZipException e) {
            assertEquals("Invalid uid size: 0", e.getMessage());
        }
    }
    @Test
    public void test25() throws ZipException {
        byte[] data = { 1, 1, 0, 0, 1 }; // Zero gidSize

        try {
            parseFromLocalFileData(data, 0, data.length);
            fail("Expected ZipException to be thrown");
        } catch (ZipException e) {
            assertEquals("Invalid gid size: 0", e.getMessage());
        }
    }
    @Test
    public void test26() throws ZipException {
        byte[] data = { 1, 1, -1, 0, 0, 0, 1 }; // Invalid uidBytes

        try {
            parseFromLocalFileData(data, 0, data.length);
            fail("Expected ZipException to be thrown");
        } catch (ZipException e) {
            assertEquals("Invalid uid bytes", e.getMessage());
        }
    }
    @Test
    public void test27() throws ZipException {
        byte[] data = { 1, 1, 0, -1, 0, 0, 1 }; // Invalid gidBytes

        try {
            parseFromLocalFileData(data, 0, data.length);
            fail("Expected ZipException to be thrown");
        } catch (ZipException e) {
            assertEquals("Invalid gid bytes", e.getMessage());
        }
    }
    @Test
    public void test28() throws Exception {
        byte[] buffer = new byte[0];
        int offset = 0;
        int length = 0;
        
        try {
            parseFromCentralDirectoryData(buffer, offset, length);
            fail("Expected ZipException to be thrown");
        } catch (ZipException e) {
            // Test passed because ZipException was thrown
        }
    }
    @Test
    public void test29() throws Exception {
        byte[] buffer = new byte[100];
        int offset = -1;
        int length = 100;
        
        try {
            parseFromCentralDirectoryData(buffer, offset, length);
            fail("Expected ZipException to be thrown");
        } catch (ZipException e) {
            // Test passed because ZipException was thrown
        }
    }
    @Test
    public void test30() throws Exception {
        byte[] buffer = new byte[100];
        int offset = 0;
        int length = -1;
        
        try {
            parseFromCentralDirectoryData(buffer, offset, length);
            fail("Expected ZipException to be thrown");
        } catch (ZipException e) {
            // Test passed because ZipException was thrown
        }
    }
    @Test
    public void test31() throws Exception {
        byte[] buffer = new byte[100];
        int offset = 0;
        int length = 110;
        
        try {
            parseFromCentralDirectoryData(buffer, offset, length);
            fail("Expected ZipException to be thrown");
        } catch (ZipException e) {
            // Test passed because ZipException was thrown
        }
    }
    @Test
    public void test32() throws Exception {
        // Change uid and gid to different values
        uid = 500;
        gid = 500;

        // Call reset method
        reset();

        // Check if uid and gid are reset to default values
        assertEquals(1000, uid);
        assertEquals(1000, gid);
    }
    @Test
    public void test33() {
        xf.setUID(98765);
        assertEquals("0x7875 Zip Extra Field: UID=98765 GID=0", xf.toString());
    }
    @Test
    public void test34() {
        xf.setGID(98765);
        assertEquals("0x7875 Zip Extra Field: UID=0 GID=98765", xf.toString());
    }
    @Test
    public void test35() throws Exception {
        Object o = xf.clone();
        xf.setUID(54321);
        assertFalse(xf.equals(o));
    }
    @Test
    public void test36() throws Exception {
        Object o = xf.clone();
        xf.setGID(54321);
        assertFalse(xf.equals(o));
    }
    @Test
    public void test37() throws Exception {
        // Test case to cover the mutant that returns a different object
        Object o = xf.clone();
        assertNotSame(xf, o);
    }
    @Test
    public void test38() throws Exception {
        // Test case to cover the mutant that returns a new object with the same attributes
        Object o = xf.clone();
        assertTrue(xf.equals(o));
    }
    @Test
    public void test39() throws Exception {
        // Test case to cover the mutant that changes the value of the UID attribute
        Object o = xf.clone();
        xf.setUID(12345);
        assertFalse(xf.equals(o));
    }
    @Test
    public void test40() {
        X7875_NewUnix xf1 = new X7875_NewUnix(1, "uid1", "gid1");
        X7875_NewUnix xf2 = new X7875_NewUnix(2, "uid1", "gid1");
        assertFalse(xf1.equals(xf2));
    }
    @Test
    public void test41() {
        X7875_NewUnix xf1 = new X7875_NewUnix(1, "uid1", "gid1");
        X7875_NewUnix xf2 = new X7875_NewUnix(1, "uid2", "gid1");
        assertFalse(xf1.equals(xf2));
    }
    @Test
    public void test42() {
        X7875_NewUnix xf1 = new X7875_NewUnix(1, "uid1", "gid1");
        X7875_NewUnix xf2 = new X7875_NewUnix(1, "uid1", "gid2");
        assertFalse(xf1.equals(xf2));
    }
    @Test
    public void test43() {
        X7875_NewUnix xf = new X7875_NewUnix(1, "uid1", "gid1");
        assertFalse(xf.equals(new Object()));
    }
    @Test
    public void test44() {
        X7875_NewUnix xf1 = new X7875_NewUnix(1, "uid1", "gid1");
        X7875_NewUnix xf2 = new X7875_NewUnixOtherClass(1, "uid1", "gid1");
        assertFalse(xf1.equals(xf2));
    }
    @Test
    public void test45() {
        X7875_NewUnix xf = new X7875_NewUnix(1, "uid1", "gid1");
        assertFalse(xf.equals(null));
    }
@Test
public void test46() throws Exception {
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

            // Extra test cases with different input values
            if (name.contains("uid777_gid777")) {
                expected = 777;
                assertEquals(expected, xf.getUID());
                assertEquals(expected, xf.getGID());
            } else if (name.contains("uid123_gid456")) {
                expected = 123;
                long expectedGID = 456;
                assertEquals(expected, xf.getUID());
                assertEquals(expectedGID, xf.getGID());
            }
        }
    } finally {
        if (zf != null) {
            zf.close();
        }
    }
}
@Test
public void test47() throws Exception {
    assertFalse(xf.equals(new Object()));
    assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
    Object o = xf.clone();
    assertEquals(o.hashCode(), xf.hashCode());
    assertTrue(xf.equals(o));
    xf.setUID(12345);
    assertFalse(xf.equals(o));

    // Extra test case with different input value
    xf.setGID(54321);
    assertFalse(xf.equals(o));
}
    @Test
    public void test48() {
        final byte[] ONE_ZERO = {0};
        assertTrue(Arrays.equals(ONE_ZERO, trimTest(ONE_ZERO)));
    }
    @Test
    public void test49() {
        final byte[] TWO_ZEROES = {0, 0};
        assertTrue(Arrays.equals(ONE_ZERO, trimTest(TWO_ZEROES)));
    }
    @Test
    public void test50() {
        final byte[] FOUR_ZEROES = {0, 0, 0, 0};
        assertTrue(Arrays.equals(ONE_ZERO, trimTest(FOUR_ZEROES)));
    }
    @Test
    public void test51() {
        final byte[] SEQUENCE_LEADING_ZERO = {0, 1, 2, 3};
        assertTrue(Arrays.equals(SEQUENCE, trimTest(SEQUENCE_LEADING_ZERO)));
    }
    @Test
    public void test52() {
        final byte[] SEQUENCE_LEADING_ZEROES = {0, 0, 0, 0, 0, 0, 0, 1, 2, 3};
        assertTrue(Arrays.equals(SEQUENCE, trimTest(SEQUENCE_LEADING_ZEROES)));
    }
    @Test
    public void test53() {
        final byte[] TRAILING_ZERO = {1, 2, 3, 0};
        assertTrue(Arrays.equals(TRAILING_ZERO, trimTest(TRAILING_ZERO)));
    }
    @Test
    public void test54() {
        final byte[] PADDING_ZERO = {0, 1, 2, 3, 0};
        assertTrue(Arrays.equals(TRAILING_ZERO, trimTest(PADDING_ZERO)));
    }
    @Test
    public void test55() {
        final byte[] SEQUENCE6_LEADING_ZERO = {0, 1, 2, 3, 4, 5, 6};
        assertTrue(Arrays.equals(SEQUENCE6, trimTest(SEQUENCE6_LEADING_ZERO)));
    }
}