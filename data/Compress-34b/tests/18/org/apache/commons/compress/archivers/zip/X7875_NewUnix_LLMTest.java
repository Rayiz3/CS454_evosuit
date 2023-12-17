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
                } else if (name.contains("test1")) {
                    expected = 1;
                } else if (name.contains("test2")) {
                    expected = 2;
                } else if (name.contains("test3")) {
                    expected = 3;
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
        assertEquals(X7875, xf.getHeaderId());
        assertEquals(X9875, xf2.getHeaderId());
        assertEquals(X5875, xf3.getHeaderId());
    }
    @Test
    public void test2() throws ZipException {
        // Existing test cases
        
        // Regression test case 1: Version=0, Len=0, Len=0.
        final byte[] ZERO_LEN_V0 = {0, 0, 0};

        // Regression test case 2: Version=0, Len=1, zero, Len=1, zero.
        final byte[] ZERO_UID_GID_V0 = {0, 1, 0, 1, 0};

        // Regression test case 3: Version=0, Len=1, one, Len=1, one
        final byte[] ONE_UID_GID_V0 = {0, 1, 1, 1, 1};

        // Regression test case 4: Version=0, Len=2, one thousand, Len=2, one thousand
        final byte[] ONE_THOUSAND_UID_GID_V0 = {0, 2, -24, 3, 2, -24, 3};

        // Regression test case 5: (2^32 - 1). 
        final byte[] UNIX_MAX_UID_GID_V0 = {0, 4, -1, -1, -1, -1, 4, -1, -1, -1, -1};

        // Regression test case 6: Version=0, Len=5, 2^32 , Len=5, 2^32 + 1
        final byte[] LENGTH_5_V0 = {0, 5, 0, 0, 0, 0, 1, 5, 1, 0, 0, 0, 1};

        // Regression test case 7: Version=0, Len=8, 2^63 - 2, Len=8, 2^63 - 1
        final byte[] LENGTH_8_V0 = {0, 8, -2, -1, -1, -1, -1, -1, -1, 127, 8, -1, -1, -1, -1, -1, -1, -1, 127};

        parseReparse(0, 0, ZERO_LEN_V0, 0, 0);
        parseReparse(0, 0, ZERO_UID_GID_V0, 0, 0);
        parseReparse(1, 1, ONE_UID_GID_V0, 1, 1);
        parseReparse(1000, 1000, ONE_THOUSAND_UID_GID_V0, 1000, 1000);
        parseReparse(-1, -1, UNIX_MAX_UID_GID_V0, -1, -1);
        parseReparse(4294967295L, 4294967296L, LENGTH_5_V0, 4294967295L, 4294967296L);
        parseReparse(Long.MAX_VALUE - 2, Long.MAX_VALUE - 1, LENGTH_8_V0, Long.MAX_VALUE - 2, Long.MAX_VALUE - 1);

        // Existing test cases
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
    
    // Inserted test case with negative GID
    final byte[] NEGATIVE_GID = {1, 1, 1, -1, 1};

    // Inserted test case with negative UID
    final byte[] NEGATIVE_UID = {1, 1, -1, 1, 1};

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
    parseReparse(1, 1, NEGATIVE_GID, 1, -7);
    parseReparse(1, 1, NEGATIVE_UID, -3, 1);
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
    public void test5() throws Exception {
        // Test case where l = 0
        xf.setUID(0);
        assertEquals(0, xf.getUID());

        // Test case where l = -1
        xf.setUID(-1);
        assertEquals(-1, xf.getUID());

        // Test case where l = 100
        xf.setUID(100);
        assertEquals(100, xf.getUID());

        // Test case where l = Long.MAX_VALUE
        xf.setUID(Long.MAX_VALUE);
        assertEquals(Long.MAX_VALUE, xf.getUID());
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

    parseReparse(50, 0, ZERO_LEN, 0, 0);
    parseReparse(0, 78, ZERO_UID_GID, 0, 0);
    parseReparse(98, 66, ONE_UID_GID, 1, 1);
    parseReparse(123, 987, ONE_THOUSAND_UID_GID, 1000, 1000);
    parseReparse(MAX - 1, MAX - 1, UNIX_MAX_UID_GID, MAX, MAX);
    parseReparse(-5, 5, UNIX_MAX_UID_GID, MAX, MAX);
    parseReparse(TWO_TO_32 + 2, TWO_TO_32 + 3, LENGTH_5, TWO_TO_32, TWO_TO_32 + 1);
    parseReparse(Long.MAX_VALUE - 2, Long.MAX_VALUE - 1, LENGTH_8, Long.MAX_VALUE - 1, Long.MAX_VALUE);

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
                    expected = 555;
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
                assertEquals(555, xf.getUID());
                assertEquals(555, xf.getGID());
            }
        } finally {
            if (zf != null) {
                zf.close();
            }
        }
    }
    @Test
    public void test8() throws Exception {
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
                assertEquals(0, xf.getUID());
                assertEquals(0, xf.getGID());
            }
        } finally {
            if (zf != null) {
                zf.close();
            }
        }
    }
    @Test
    public void test9() throws Exception {
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
                assertEquals(5555, xf.getUID());
                assertEquals(5555, xf.getGID());
            }
        } finally {
            if (zf != null) {
                zf.close();
            }
        }
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
    public void test11() throws ZipException {

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
public void test13() throws ZipException {

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

    // Additional test case with valid values
    final byte[] ONE_HUNDRED_UID_GID = {1, 2, 100, 3, 100, 3};
    parseReparse(100, 100, ONE_HUNDRED_UID_GID, 100, 100);

    // Additional test case with negative values
    final byte[] NEGATIVE_UID_GID = {1, 2, -100, -3, -100, -3};
    parseReparse(-100, -100, NEGATIVE_UID_GID, -100, -100);

    // Additional test case with large values
    final byte[] LARGE_UID_GID = {1, 2, -127, -1, -2, -1};
    parseReparse(129, 65534, LARGE_UID_GID, 129, 65534);
}
    @Test
    public void test14() {
        // Test case 1: Empty input data
        byte[] expected1 = {1, 0, 0};
        byte[] result1 = getLocalFileDataData(new byte[0], new byte[0]);
        assertArrayEquals(expected1, result1);

        // Test case 2: Input data with UID and GID each having length 1
        byte[] uidBytes2 = {(byte) 128};
        byte[] gidBytes2 = {(byte) 64};
        byte[] expected2 = {1, 1, (byte) -128, 1, 64};
        byte[] result2 = getLocalFileDataData(uidBytes2, gidBytes2);
        assertArrayEquals(expected2, result2);

        // Test case 3: Input data with UID and GID each having length 2
        byte[] uidBytes3 = {(byte) -1, (byte) -1};
        byte[] gidBytes3 = {(byte) -1, (byte) -1};
        byte[] expected3 = {1, 2, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1};
        byte[] result3 = getLocalFileDataData(uidBytes3, gidBytes3);
        assertArrayEquals(expected3, result3);

        // Test case 4: Input data with UID and GID each having length 8
        byte[] uidBytes4 = {(byte) 127, 0, 0, 0, 0, 0, 0, 0};
        byte[] gidBytes4 = {(byte) 127, 0, 0, 0, 0, 0, 0, 0};
        byte[] expected4 = {1, 8, (byte) 127, 0, 0, 0, 0, 0, 0, 0, (byte) 127, 0, 0, 0, 0, 0, 0, 0};
        byte[] result4 = getLocalFileDataData(uidBytes4, gidBytes4);
        assertArrayEquals(expected4, result4);
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
                // Regression test by changing the uid
                expected = 123;
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

        // Regression test by changing the uid and gid
        parseReparse(123, 123, ZERO_LEN, 0, 0);
        parseReparse(123, 123, ZERO_UID_GID, 0, 0);
        parseReparse(123, 123, ONE_UID_GID, 1, 1);
        parseReparse(123, 123, ONE_THOUSAND_UID_GID, 1000, 1000);
        parseReparse(123, 123, UNIX_MAX_UID_GID, MAX, MAX);
        parseReparse(-2, -2, UNIX_MAX_UID_GID, MAX, MAX);
        parseReparse(123, 123, LENGTH_5, TWO_TO_32, TWO_TO_32 + 1);
        parseReparse(123, 123, LENGTH_8, Long.MAX_VALUE - 1, Long.MAX_VALUE);

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
        byte[] buffer = new byte[0];
        int offset = 0;
        int length = 0;
        
        try {
            parseFromCentralDirectoryData(buffer, offset, length);
            fail("Expected ZipException to be thrown");
        } catch (ZipException e) {
            // expected exception
        }
    }
    @Test
    public void test18() throws Exception {
        byte[] buffer = new byte[10];
        int offset = -1;
        int length = 10;
        
        try {
            parseFromCentralDirectoryData(buffer, offset, length);
            fail("Expected ZipException to be thrown");
        } catch (ZipException e) {
            // expected exception
        }
    }
    @Test
    public void test19() throws Exception {
        byte[] buffer = new byte[10];
        int offset = 15;
        int length = 10;
        
        try {
            parseFromCentralDirectoryData(buffer, offset, length);
            fail("Expected ZipException to be thrown");
        } catch (ZipException e) {
            // expected exception
        }
    }
    @Test
    public void test20() throws Exception {
        byte[] buffer = new byte[10];
        int offset = 0;
        int length = -1;
        
        try {
            parseFromCentralDirectoryData(buffer, offset, length);
            fail("Expected ZipException to be thrown");
        } catch (ZipException e) {
            // expected exception
        }
    }
    @Test
    public void test21() throws Exception {
        byte[] buffer = new byte[10];
        int offset = 5;
        int length = 6;
        
        try {
            parseFromCentralDirectoryData(buffer, offset, length);
            fail("Expected ZipException to be thrown");
        } catch (ZipException e) {
            // expected exception
        }
    }
    @Test
    public void test22() throws Exception {
        byte[] buffer = new byte[10];
        int offset = 2;
        int length = 6;
        
        try {
            parseFromCentralDirectoryData(buffer, offset, length);
            fail("Expected ZipException to be thrown");
        } catch (ZipException e) {
            // expected exception
        }
    }
    @Test
    public void test23() {
        uid = 500;
        gid = 500;
        reset();
        assertEquals(1000, uid);
        assertEquals(1000, gid);
    }
    @Test
    public void test24() {
        uid = -500;
        gid = -500;
        reset();
        assertEquals(1000, uid);
        assertEquals(1000, gid);
    }
    @Test
    public void test25() {
        uid = 0;
        gid = 0;
        reset();
        assertEquals(1000, uid);
        assertEquals(1000, gid);
    }
    @Test
    public void test26() {
        uid = Integer.MAX_VALUE;
        gid = Integer.MAX_VALUE;
        reset();
        assertEquals(1000, uid);
        assertEquals(1000, gid);
    }
    @Test
    public void test27() {
        uid = Integer.MIN_VALUE;
        gid = Integer.MIN_VALUE;
        reset();
        assertEquals(1000, uid);
        assertEquals(1000, gid);
    }
    @Test
    public void test28() {
        // Different UID and GID values
        xf.setUID(12345);
        xf.setGID(67890);
        assertEquals("0x7875 Zip Extra Field: UID=12345 GID=67890", xf.toString());
    }
    @Test
    public void test29() {
        // Zero UID and GID values
        xf.setUID(0);
        xf.setGID(0);
        assertEquals("0x7875 Zip Extra Field: UID=0 GID=0", xf.toString());
    }
    @Test
    public void test30() {
        // Negative UID and GID values
        xf.setUID(-12345);
        xf.setGID(-67890);
        assertEquals("0x7875 Zip Extra Field: UID=-12345 GID=-67890", xf.toString());
    }
    @Test
    public void test31() throws Exception {
        // Create a different object to compare with cloned object
        Object obj = new Object();

        // Call clone method on xf object
        Object clonedObj = xf.clone();

        // Check that the cloned object is not equal to a different object
        assertFalse(clonedObj.equals(obj));
    }
    @Test
    public void test32() throws Exception {
        // Call clone method on xf object
        Object clonedObj = xf.clone();

        // Check that the cloned object is equal to the original object (xf)
        assertTrue(clonedObj.equals(xf));
    }
    @Test
    public void test33() throws Exception {
        // Call clone method on xf object
        Object clonedObj = xf.clone();

        // Set a different UID on xf object
        xf.setUID(123);

        // Check that the cloned object is not equal to the original object after setting UID
        assertFalse(clonedObj.equals(xf));
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
                xf.setVersion(2); // Change version to a different value
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
        X7875_NewUnix xf = new X7875_NewUnix();
        xf.setVersion(2); // Change version to a different value
        assertEquals(X7875, xf.getHeaderId());
    }
    @Test
    public void test36() throws Exception {
        X7875_NewUnix xf = new X7875_NewUnix();
        xf.setVersion(2); // Change version to a different value

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
                xf.setUID(2000); // Change uid to a different value
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
    public void test38() throws Exception {
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
                xf.setGID(2000); // Change gid to a different value
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
    public void test41() {
        X7875_NewUnix xf = new X7875_NewUnix();
        xf.setVersion(10);
        int expectedHash = -1234567 * 10;
        expectedHash ^= Integer.rotateLeft(xf.getUID().hashCode(), 16);
        expectedHash ^= xf.getGID().hashCode();
        assertEquals(expectedHash, xf.hashCode());
    }
    @Test
    public void test42() {
        X7875_NewUnix xf = new X7875_NewUnix();
        xf.setUID(Long.MAX_VALUE);
        int expectedHash = -1234567 * xf.getVersion();
        expectedHash ^= Integer.rotateLeft(Long.MAX_VALUE.hashCode(), 16);
        expectedHash ^= xf.getGID().hashCode();
        assertEquals(expectedHash, xf.hashCode());
    }
    @Test
    public void test43() {
        X7875_NewUnix xf = new X7875_NewUnix();
        xf.setGID(Long.MAX_VALUE);
        int expectedHash = -1234567 * xf.getVersion();
        expectedHash ^= Integer.rotateLeft(xf.getUID().hashCode(), 16);
        expectedHash ^= Long.MAX_VALUE.hashCode();
        assertEquals(expectedHash, xf.hashCode());
    }
    @Test
    public void test44() {
        X7875_NewUnix xf = new X7875_NewUnix();
        xf.setVersion(10);
        xf.setUID(Long.MAX_VALUE);
        xf.setGID(Long.MAX_VALUE);
        int expectedHash = -1234567 * 10;
        expectedHash ^= Integer.rotateLeft(Long.MAX_VALUE.hashCode(), 16);
        expectedHash ^= Long.MAX_VALUE.hashCode();
        assertEquals(expectedHash, xf.hashCode());
    }
}

    @Test
    public void test45() {
    }

}