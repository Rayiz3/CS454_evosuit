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
    }
    @Test
    public void test2() throws Exception {
        File archive = getFile("invalid_archive.zip");
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
    public void test3() throws Exception {
        File archive = getFile("empty_archive.zip");
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
    public void test4() throws Exception {
        File archive = getFile("different_format_archive.rar");
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
        File archive = getFile("not_a_zip_file.txt");
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
    public void test6() {
        // Version=1, Len=3, one, two
        final byte[] ONE_TWO_UID_GID = {1, 3, 1, 2, 1, 2};
        
        parseReparse(1, 2, ONE_TWO_UID_GID, 1, 2);
    }
    @Test
    public void test7() throws Exception {
        File archive = getFile("COMPRESS-211_uid_gid_zip_test.zip");
        ZipFile zf = null;

        try {
            zf = new ZipFile(archive);
            Enumeration<ZipArchiveEntry> en = zf.getEntries();

            while (en.hasMoreElements()) {
                ZipArchiveEntry zae = en.nextElement();
                String name = zae.getName();
                X7875_NewUnix xf = (X7875_NewUnix) zae.getExtraField(X7875);

                long expected = 200;
                if (name.contains("uid200_gid200")) {
                    expected = 555;
                } else if (name.contains("uid500_gid500")) {
                    expected = 500;
                } else if (name.contains("uid1000_gid1000")) {
                    expected = 1000;
                } else if (name.contains("uid10000_gid10000")) {
                    expected = 10000;
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
    public void test8() throws ZipException {

        // Version=1, Len=1, negative one, Len=1, negative one
        final byte[] NEGATIVE_UID_GID = {1, 1, -1, 1, -1};

        parseReparse(-1, -1, NEGATIVE_UID_GID, -1, -1);
        parseReparse(-1000, -1000, NEGATIVE_UID_GID, -1, -1);

        // We never emit this, but we should be able to parse it:
        final byte[] SPURIOUS_ZEROES_1 = {1, 4, -1, 0, 0, 0, 4, -128, 0, 0, 0};
        final byte[] EXPECTED_1 = {1, 1, -1, 1, -128};
        xf.parseFromLocalFileData(SPURIOUS_ZEROES_1, 0, SPURIOUS_ZEROES_1.length);

        assertEquals(-1, xf.getUID());
        assertEquals(-128, xf.getGID());
        assertTrue(Arrays.equals(EXPECTED_1, xf.getLocalFileDataData()));

        final byte[] SPURIOUS_ZEROES_2 = {1, 4, -1, -1, 0, 0, 4, 1, 2, 0, 0};
        final byte[] EXPECTED_2 = {1, 2, -1, -1, 2, 1, 2};
        xf.parseFromLocalFileData(SPURIOUS_ZEROES_2, 0, SPURIOUS_ZEROES_2.length);

        assertEquals(-1, xf.getUID());
        assertEquals(513, xf.getGID());
        assertTrue(Arrays.equals(EXPECTED_2, xf.getLocalFileDataData()));
    }
    @Test
    public void test9() throws ZipException {

        // (2^32 - 2).   I guess they avoid (2^32 - 1) since it's identical to -1 in
        // two's complement, and -1 often has a special meaning.
        final byte[] UNIX_MAX_UID_GID = {1, 4, -2, -1, -1, -1, 4, -2, -1, -1, -1};

        // Version=1, Len=8, 2^63 - 2, Len=8, 2^63 - 1
        // Esoteric test:  can we handle 64 bit numbers?
        final byte[] LENGTH_8 = {1, 8, -2, -1, -1, -1, -1, -1, -1, 127, 8, -1, -1, -1, -1, -1, -1, -1, 127};

        final long MAX = Long.MAX_VALUE - 1;

        parseReparse(MAX, MAX, UNIX_MAX_UID_GID, MAX, MAX);
        parseReparse(-2, -2, UNIX_MAX_UID_GID, MAX, MAX);
        parseReparse(Long.MAX_VALUE - 1, Long.MAX_VALUE, LENGTH_8, Long.MAX_VALUE - 1, Long.MAX_VALUE);
    }
    @Test
    public void test10() throws Exception {
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertTrue(xf.equals(o));
        xf.setUID(12345);
        assertFalse(xf.equals(o));
        // Regression test 1: test with UID equals to 0
        xf.setUID(0);
        assertFalse(xf.equals(o));
        // Regression test 2: test with UID equals to -1
        xf.setUID(-1);
        assertFalse(xf.equals(o));
        // Regression test 3: test with UID equals to Long.MAX_VALUE
        xf.setUID(Long.MAX_VALUE);
        assertFalse(xf.equals(o));
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
        // Regression test 4: test with minimum possible UID
        parseReparse(Long.MIN_VALUE, Long.MIN_VALUE + 1, LENGTH_8, Long.MIN_VALUE, Long.MIN_VALUE + 1);
        // Regression test 5: test with maximum possible UID
        parseReparse(Long.MAX_VALUE, Long.MAX_VALUE - 1, LENGTH_8, Long.MAX_VALUE, Long.MAX_VALUE - 1);
        
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
        // Regression test 6: test with SPURIOUS_ZEROES_2 changed to have UID equals to 0
        final byte[] SPURIOUS_ZEROES_3 = {1, 4, -1, -1, 0, 0, 4, 0, 0, 0, 0};
        final byte[] EXPECTED_3 = {1, 2, -1, -1, 0, 0, 0};
        xf.parseFromLocalFileData(SPURIOUS_ZEROES_3, 0, SPURIOUS_ZEROES_3.length);

        assertEquals(65535, xf.getUID());
        assertEquals(0, xf.getGID());
        assertTrue(Arrays.equals(EXPECTED_3, xf.getLocalFileDataData()));
    }
@Test
    public void test12() throws ZipException {

        // Version=1, Len=0, Len=0. <<<< CHANGE: 0 to 1
        final byte[] ZERO_LEN = {1, 1, 0};   <<<< CHANGE: 0 to 1

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

        // Additional test case: negative value
        parseReparse(-1, -1, UNIX_MAX_UID_GID, MAX, MAX);
        
        // Additional test case: large positive value
        final byte[] LARGE_POSITIVE_VALUE = {1, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        parseReparse(0, 0, LARGE_POSITIVE_VALUE, 0, 0);
    }
    @Test
    public void test13() {
        Zip8191 uid = new Zip8191(-1);
        Zip8191 gid = new Zip8191(0);
        ZipShort expected = new ZipShort(3 + 3 + 1);
        ZipShort result = getLocalFileDataLength(uid, gid);
        assertEquals(expected, result);
    }
    @Test
    public void test14() {
        Zip8191 uid = new Zip8191(0);
        Zip8191 gid = new Zip8191(0);
        ZipShort expected = new ZipShort(3 + 1 + 3);
        ZipShort result = getLocalFileDataLength(uid, gid);
        assertEquals(expected, result);
    }
    @Test
    public void test15() {
        Zip8191 uid = new Zip8191(100);
        Zip8191 gid = new Zip8191(200);
        ZipShort expected = new ZipShort(3 + 4 + 4);
        ZipShort result = getLocalFileDataLength(uid, gid);
        assertEquals(expected, result);
    }
    @Test
    public void test16() throws ZipException {
        
        // Version>1, Len=0, Len=0.
        final byte[] ZERO_LEN = {2, 0, 0};

        // Version=1, Len>1, zero, Len>1, zero
        final byte[] ZERO_UID_GID = {1, 2, 0, 0, 1, 0};

        // Version=1, Len>1, one, Len>1, one
        final byte[] ONE_UID_GID = {1, 2, 1, 0, 1, 0};

        // Version=1, Len>2, one thousand, Len>2, one thousand
        final byte[] ONE_THOUSAND_UID_GID = {1, 3, -24, 3, 2, -24, 3};

        // (2^32 - 3)
        final byte[] UNIX_MAX_UID_GID = {1, 4, -3, -1, -1, -1, 4, -3, -1, -1, -1};

        // Version=1, Len>5, 2^32, Len>5, 2^32 + 1
        // Esoteric test:  can we handle 40 bit numbers?
        final byte[] LENGTH_5 = {1, 6, 0, 0, 0, 0, 1, 5, 1, 0, 0, 0, 1};

        // Version=1, Len>8, 2^63 - 2, Len>8, 2^63 - 1
        // Esoteric test:  can we handle 64 bit numbers?
        final byte[] LENGTH_8 = {1, 9, -2, -1, -1, -1, -1, -1, -1, 127, 9, -1, -1, -1, -1, -1, -1, -1, 127};

        final long TWO_TO_32 = 0x100000000L;
        final long MAX = TWO_TO_32 - 3;

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

        // Regression test for Version>1, Len>2
        byte[] UID_GID = {2, 3, 1, 2, 3, 4, 1, 2, 3, 4};
        xf.parseFromLocalFileData(UID_GID, 0, UID_GID.length);
        assertEquals(16909060, xf.getUID());
        assertEquals(16909060, xf.getGID());
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
        // Version=1, Len=1, 256
        final byte[] INVALID_UID_GID = {1, 1, 1, 0, 0};

        xf.parseFromLocalFileData(INVALID_UID_GID, 0, INVALID_UID_GID.length);
        assertEquals(0, xf.getUID());
        assertEquals(0, xf.getGID());
    }
    @Test
    public void test20() {
        byte[] result = getCentralDirectoryData(new byte[0]);
        assertEquals(0, result.length);
    }
    @Test
    public void test21() {
        byte[] result = getCentralDirectoryData(null);
        assertNull(result);
    }
    @Test
    public void test22() {
        byte[] input = new byte[1000000];
        byte[] result = getCentralDirectoryData(input);
        assertNotNull(result);
        assertEquals(input.length, result.length);
    }
    @Test
    public void test23() {
        byte[] input = {1, 2, 3, 4, 5};
        byte[] result = getCentralDirectoryData(input);
        assertNotNull(result);
        assertEquals(input.length, result.length);
    }
    @Test
    public void test24() {
        byte[] input = {-1, -2, -3, -4, -5};
        byte[] result = getCentralDirectoryData(input);
        assertNotNull(result);
        assertEquals(input.length, result.length);
    }
    @Test
    public void test25() throws ZipException {
        // Test case to kill mutant M1: Change version from 1 to 0
        byte[] data1 = {0, 1, 0, 1, 0};
        assertThrows(ZipException.class, () -> {
            parseFromLocalFileData(data1, 0, data1.length);
        });

        // Test case to kill mutant M2: Change uidSize from 1 to 0
        byte[] data2 = {1, 0, 1, 1, 1};
       assertThrows(IndexOutOfBoundsException.class, () -> {
           parseFromLocalFileData(data2, 0, data2.length);
       });

        // Test case to kill mutant M3: Change uidSize from 1 to 2
        byte[] data3 = {1, 2, 1, 1, 1, 1};
        assertThrows(IndexOutOfBoundsException.class, () -> {
            parseFromLocalFileData(data3, 0, data3.length);
        });

        // Test case to kill mutant M4: Change uidSize from 1 to 255
        byte[] data4 = {(byte)255, 1, 1, 1, 1};
        assertThrows(IndexOutOfBoundsException.class, () -> {
            parseFromLocalFileData(data4, 0, data4.length);
        });

        // Test case to kill mutant M5: Change gidSize from 1 to 0
        byte[] data5 = {1, 1, 0, 1, 1};
        assertThrows(IndexOutOfBoundsException.class, () -> {
            parseFromLocalFileData(data5, 0, data5.length);
        });

        // Test case to kill mutant M6: Change gidSize from 1 to 2
        byte[] data6 = {1, 1, 1, 1, 1, 1};
        assertThrows(IndexOutOfBoundsException.class, () -> {
            parseFromLocalFileData(data6, 0, data6.length);
        });

        // Test case to kill mutant M7: Change gidSize from 1 to 255
        byte[] data7 = {1, 1, 1, (byte)255, 1};
        assertThrows(IndexOutOfBoundsException.class, () -> {
            parseFromLocalFileData(data7, 0, data7.length);
        });

        // Test case to kill mutant M8: Change the byte array size to 4
        byte[] data8 = {1, 1, 1, 1};
        assertThrows(IndexOutOfBoundsException.class, () -> {
            parseFromLocalFileData(data8, 0, data8.length);
        });
    }
    @Test
    public void test26() throws Exception {
        byte[] buffer = null;
        int offset = 0;
        int length = 0;
        assertThrows(ZipException.class, () -> {
            parseFromCentralDirectoryData(buffer, offset, length);
        });
    }
    @Test
    public void test27() throws Exception {
        byte[] buffer = new byte[10];
        int offset = -1;
        int length = 10;
        assertThrows(ZipException.class, () -> {
            parseFromCentralDirectoryData(buffer, offset, length);
        });
    }
    @Test
    public void test28() throws Exception {
        byte[] buffer = new byte[10];
        int offset = 0;
        int length = -1;
        assertThrows(ZipException.class, () -> {
            parseFromCentralDirectoryData(buffer, offset, length);
        });
    }
    @Test
    public void test29() throws Exception {
        byte[] buffer = new byte[0];
        int offset = 0;
        int length = 0;
        assertThrows(ZipException.class, () -> {
            parseFromCentralDirectoryData(buffer, offset, length);
        });
    }
    @Test
    public void test30() throws Exception {
        byte[] buffer = new byte[10];
        int offset = 0;
        int length = 20;
        assertThrows(ZipException.class, () -> {
            parseFromCentralDirectoryData(buffer, offset, length);
        });
    }
    @Test
    public void test31() throws Exception {
        byte[] buffer = new byte[10];
        int offset = 0;
        int length = 10;
        assertDoesNotThrow(() -> {
            parseFromCentralDirectoryData(buffer, offset, length);
        });
    }
    @Test
    public void test32() {
        uid = 0;
        gid = 0;
        reset();
        assertEquals(1000, uid);
        assertEquals(1000, gid);
    }
    @Test
    public void test33() {
        uid = -1;
        gid = -1;
        reset();
        assertEquals(1000, uid);
        assertEquals(1000, gid);
    }
    @Test
    public void test34() {
        uid = 1000000;
        gid = 1000000;
        reset();
        assertEquals(1000, uid);
        assertEquals(1000, gid);
    }
    @Test
    public void test35() throws Exception {
        assertEquals("0x7875 Zip Extra Field: UID=0 GID=0", xf.toString());
        
        xf.setUID(12345);
        assertEquals("0x7875 Zip Extra Field: UID=12345 GID=0", xf.toString());
        
        xf.setGID(67890);
        assertEquals("0x7875 Zip Extra Field: UID=12345 GID=67890", xf.toString());
    }
    @Test
    public void test36() throws Exception {
        assertFalse(xf.equals(null));
        
        Xf xf2 = new Xf();
        assertTrue(xf.equals(xf2)); 
        
        xf.setUID(12345);
        assertFalse(xf.equals(xf2));
        
        xf2.setUID(12345);
        assertTrue(xf.equals(xf2));
        
        xf.setGID(67890);
        assertFalse(xf.equals(xf2));
        
        xf2.setGID(67890);
        assertTrue(xf.equals(xf2));
        
        assertTrue(xf.equals(xf)); 
    }
    @Test
    public void test37() throws Exception {
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertTrue(xf.equals(o));
        xf.setUID(12345);
        assertFalse(xf.equals(o));
    }
    @Test
    public void test38() throws Exception {
        Object o = xf.clone();
        assertFalse(xf.equals(new Object()));
        assertNotEquals(o.hashCode(), xf.hashCode());
        assertFalse(xf.equals(o));
    }
    @Test
    public void test39() throws Exception {
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertTrue(xf.equals(o));
        xf.setUID(54321);
        assertFalse(xf.equals(o));
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
            }
        } finally {
            if (zf != null) {
                zf.close();
            }
        }
    }
    @Test
    public void test41() throws Exception {
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

                xf = null; // Setting xf to null
                assertEquals(false, xf.equals(new Object()));
            }
        } finally {
            if (zf != null) {
                zf.close();
            }
        }
    }
    @Test
    public void test42() {
        assertEquals(X7875, xf.getHeaderId());
    }
    @Test
    public void test43() {
        X7875_NewUnix xf = new X7875_NewUnix(2);
        
        assertEquals(2, xf.getHeaderId());
    }
    @Test
    public void test44() {
        X7875_NewUnix xf = new X7875_NewUnix(-1);
        
        assertEquals(-1, xf.getHeaderId());
    }
    @Test
    public void test45() {
        X7875_NewUnix xf = new X7875_NewUnix("ABC");
        
        assertEquals(0, xf.getHeaderId());
    }
    @Test
    public void test46() throws Exception {
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertTrue(xf.equals(o));
        xf.setUID(12345);
        assertFalse(xf.equals(o));
    }
    @Test
    public void test47() throws Exception {
        X7875_NewUnix xf1 = new X7875_NewUnix(1, "uid", "gid");
        xf1.setUID(9999);
        X7875_NewUnix xf2 = new X7875_NewUnix(1, "uid", "gid");
        xf2.setUID(8888);
        
        assertFalse(xf1.equals(xf2));
    }
    @Test
    public void test48() throws Exception {
        X7875_NewUnix xf1 = new X7875_NewUnix(1, "uid", "gid");
        xf1.setGID(9999);
        X7875_NewUnix xf2 = new X7875_NewUnix(1, "uid", "gid");
        xf2.setGID(8888);
        
        assertFalse(xf1.equals(xf2));
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
                assertEquals(-1 * expected, xf.getUID());
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
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertTrue(xf.equals(o));
        xf.setUID(12345);
        assertFalse(xf.equals(o));
    }
@Test
public void test51() {
    final byte[] ZEROES = {0, 0, 0, 0};
    final byte[] ONE_ZERO = {0};
    final byte[] TWO_ZEROES = {0, 0};
    final byte[] TRIMMED_ZEROES = {0};

    assertTrue(Arrays.equals(TRIMMED_ZEROES, trimTest(ZEROES)));
    assertTrue(Arrays.equals(ONE_ZERO, trimTest(ONE_ZERO)));
    assertTrue(Arrays.equals(ONE_ZERO, trimTest(TWO_ZEROES)));
}
@Test
public void test52() {
    final byte[] SEQUENCE = {1, 2, 3, 4};
    final byte[] SEQUENCE_LEADING_ZERO = {0, 1, 2, 3, 4};
    final byte[] SEQUENCE_LEADING_ZEROES = {0, 0, 0, 1, 2, 3, 4};
    final byte[] TRIMMED_SEQUENCE = {1, 2, 3, 4};

    assertTrue(Arrays.equals(TRIMMED_SEQUENCE, trimTest(SEQUENCE)));
    assertTrue(Arrays.equals(TRIMMED_SEQUENCE, trimTest(SEQUENCE_LEADING_ZERO)));
    assertTrue(Arrays.equals(TRIMMED_SEQUENCE, trimTest(SEQUENCE_LEADING_ZEROES)));
}
@Test
public void test53() {
    final byte[] TRAILING_ZERO = {1, 2, 3, 0};
    final byte[] PADDING_ZERO = {0, 1, 2, 3, 0};
    final byte[] TRIMMED_TRAILING_ZERO = {1, 2, 3};

    assertTrue(Arrays.equals(TRIMMED_TRAILING_ZERO, trimTest(TRAILING_ZERO)));
    assertTrue(Arrays.equals(TRIMMED_TRAILING_ZERO, trimTest(PADDING_ZERO)));
}
@Test
public void test54() {
    final byte[] SEQUENCE6 = {1, 2, 3, 4, 5, 6};
    final byte[] SEQUENCE6_LEADING_ZERO = {0, 1, 2, 3, 4, 5, 6};
    final byte[] TRIMMED_SEQUENCE6 = {1, 2, 3, 4, 5, 6};

    assertTrue(Arrays.equals(TRIMMED_SEQUENCE6, trimTest(SEQUENCE6)));
    assertTrue(Arrays.equals(TRIMMED_SEQUENCE6, trimTest(SEQUENCE6_LEADING_ZERO)));
}
}