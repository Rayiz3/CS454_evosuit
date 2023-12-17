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
        // Changing the input value of HEADER_ID to zero
        HEADER_ID = new ZipShort(0);
        assertEquals(HEADER_ID, xf.getHeaderId());
    }
    @Test
    public void test1() {
        // Changing the input value of HEADER_ID to a negative value
        HEADER_ID = new ZipShort(-1);
        assertEquals(HEADER_ID, xf.getHeaderId());
    }
    @Test
    public void test2() {
        // Changing the input value of HEADER_ID to a large value
        HEADER_ID = new ZipShort(999999);
        assertEquals(HEADER_ID, xf.getHeaderId());
    }
@Test
public void test3() throws ZipException {
    // Version=1, Len=3, zero, Len=3, zero.
    final byte[] ZERO_UID_GID = {1, 3, 0, 0, 0, 3, 0, 0};

    parseReparse(0, 0, ZERO_UID_GID, 0, 0); // original test case

    // Version=1, Len=3, zero, Len=3, one.
    final byte[] ONE_UID_GID = {1, 3, 0, 0, 0, 3, 0, 1};

    parseReparse(0, 1, ONE_UID_GID, 0, 1); // changed input value

    // Version=1, Len=3, zero, Len=3, one thousand.
    final byte[] ONE_THOUSAND_UID_GID = {1, 3, 0, 0, 3, -24, 3, 2};

    parseReparse(0, 1000, ONE_THOUSAND_UID_GID, 0, 1000); // changed input value
}
@Test
public void test4() throws Exception {
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

            assertEquals(expected, xf.getUID()); // original test case

            if (name.contains("uid555_gid555")) {
                expected = 554; // changed input value
            } else if (name.contains("uid5555_gid5555")) {
                expected = 5554; // changed input value
            } else if (name.contains("uid55555_gid55555")) {
                expected = 55554; // changed input value
            } else if (name.contains("uid555555_gid555555")) {
                expected = 555554; // changed input value
            } else if (name.contains("min_unix")) {
                expected = -1; // changed input value
            } else if (name.contains("max_unix")) {
                expected = 0x100000000L - 3; // changed input value
            }

            assertEquals(expected, xf.getUID()); // changed input value
        }
    } finally {
        if (zf != null) {
            zf.close();
        }
    }
}
@Test
public void test5() throws ZipException {

    // Version=1, Len=1, -1, Len=1, -1
    final byte[] NEGATIVE_UID_GID = {1, 1, -1, 1, -1};

    parseReparse(-1, -1, NEGATIVE_UID_GID, -1, -1);
}
@Test
public void test6() throws ZipException {

    // Version=1, Len=1, zero, Len=1, zero
    final byte[] ZERO_UID_GID = {1, 1, 0, 1, 0};

    parseReparse(0, 0, ZERO_UID_GID, 0, 0);
}
@Test
public void test7() throws ZipException {

    // Version=1, Len=8, 2^63 - 2, Len=8, 2^63 - 1
    // Esoteric test:  can we handle 64 bit numbers?
    final byte[] LENGTH_8 = {1, 8, -2, -1, -1, -1, -1, -1, -1, 127, 8, -1, -1, -1, -1, -1, -1, -1, 127};

    parseReparse(Long.MAX_VALUE - 1, Long.MAX_VALUE, LENGTH_8, Long.MAX_VALUE - 1, Long.MAX_VALUE);
}
    @Test
    public void test8() throws Exception {
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertTrue(xf.equals(o));
        xf.setUID(12345);
        assertFalse(xf.equals(o));
    }
    @Test
    public void test9() throws Exception {
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertTrue(xf.equals(o));
        xf.setUID(0);
        assertFalse(xf.equals(o));
    }
    @Test
    public void test10() throws Exception {
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertTrue(xf.equals(o));
        xf.setUID(-1);
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
        final byte[] LENGTH_5 = {1, 5, 0, 0, 0, 0, 1};

        // Version=1, Len=8, 2^63 - 2, Len=8, 2^63 - 1
        // Esoteric test:  can we handle 64 bit numbers?
        final byte[] LENGTH_8 = {1, 8, -2, -1, -1, -1, -1, -1, -1, 127};

        final long TWO_TO_32 = 0x100000000L;
        final long MAX = TWO_TO_32 - 2;

        parseReparse(0, 0, ZERO_LEN, 0, 0);
        parseReparse(-1, -1, ZERO_LEN, -1, -1);
        parseReparse(100, 100, ZERO_LEN, 100, 100);

        parseReparse(0, 0, ZERO_UID_GID, 0, 0);
        parseReparse(-1, -1, ZERO_UID_GID, -1, -1);
        parseReparse(100, 100, ZERO_UID_GID, 100, 100);

        parseReparse(1, 1, ONE_UID_GID, 1, 1);
        parseReparse(-1, -1, ONE_UID_GID, -1, -1);
        parseReparse(100, 100, ONE_UID_GID, 100, 100);

        parseReparse(1000, 1000, ONE_THOUSAND_UID_GID, 1000, 1000);
        parseReparse(-1000, -1000, ONE_THOUSAND_UID_GID, -1000, -1000);
        parseReparse(0, 0, ONE_THOUSAND_UID_GID, 0, 0);

        parseReparse(MAX, MAX, UNIX_MAX_UID_GID, MAX, MAX);
        parseReparse(-2, -2, UNIX_MAX_UID_GID, MAX, MAX);
        parseReparse(TWO_TO_32, TWO_TO_32 + 1, LENGTH_5, TWO_TO_32, TWO_TO_32 + 1);
        parseReparse(Long.MAX_VALUE - 1, Long.MAX_VALUE, LENGTH_8, Long.MAX_VALUE - 1, Long.MAX_VALUE);

        // We never emit this, but we should be able to parse it:
        final byte[] SPURIOUS_ZEROES_1 = {1, 4, -1, 0, 0, 0, 4, -128, 0, 0, 0};
        final byte[] EXPECTED_1 = {1, 1, -1, 1, -128};
        parseReparse(255, 128, SPURIOUS_ZEROES_1, 255, 128);

        final byte[] SPURIOUS_ZEROES_2 = {1, 4, -1, -1, 0, 0, 4, 1, 2, 0, 0};
        final byte[] EXPECTED_2 = {1, 2, -1, -1, 2, 1, 2};
        parseReparse(65535, 513, SPURIOUS_ZEROES_2, 65535, 513);
    }
@Test
public void test13() throws Exception {
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
public void test14() throws ZipException {

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

    // Additional regression test cases
    byte[] negativeUidGid = {1, 10, -128, -64, -32, -16, -8, -4, -2, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
    parseReparse(-9223372036854775808L, 9223372036854775807L, negativeUidGid, -9223372036854775808L, 9223372036854775807L);

    byte[] zeroUidGid = {1, 5, 0, 0, 0, 0, 0, 0};
    parseReparse(0, 0, zeroUidGid, 0, 0);

    byte[] maxUidGid = {1, 5, 127, 127, 127, 127, 127, 127};
    parseReparse(36170086419038335L, 36170086419038335L, maxUidGid, 36170086419038335L, 36170086419038335L);

}
    @Test
    public void test15() throws ZipException {

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
        
        // New test cases:
        
        // Version=1, Len=10, one million, Len=10, one billion
        final byte[] ONE_MILLION_UID_GID = {1, 10, -32, -59, 15, 0, 10, -120, 36, 0, -1, -16, -81, 3, 0, 0, 3, 0, -17, -16};
        parseReparse(1000000, 1000000000, ONE_MILLION_UID_GID, 1000000, 1000000000);
        
        // Version=1, Len=3, 1000000, Len=3, 999999
        final byte[] LENGTH_3 = {1, 3, -32, -59, 15, -33, -33, -103};
        parseReparse(1000000, 999999, LENGTH_3, 1000000, 999999);
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

        // Regression test for trimming leading zeroes for uidBytes and gidBytes
        // Resulting data array = {1, 1, 0, 0, 0, 1, 1, 0, 0, 0}
        final byte[] ZERO_LEN_UID_GID = {1, 1, 0, 0, 0, 1, 1, 0, 0, 0};
        parseReparse(0, 0, ZERO_LEN_UID_GID, 0, 0);

        // Regression test for uidBytes and gidBytes with leading zeroes
        // Resulting data array = {1, 4, 0, 0, 0, 1, 4, 0, 0, 0}
        final byte[] LEADING_ZEROES_UID_GID = {1, 4, 0, 0, 0, 0, 4, 0, 0, 0};
        parseReparse(1, 4, LEADING_ZEROES_UID_GID, 1, 4);
    }
@Test
public void test18() {
    byte[] result = getCentralDirectoryData();
    Assert.assertNotNull(result);
    Assert.assertEquals(0, result.length);
}
@Test
public void test19() {
    byte[] result = getCentralDirectoryData();
    Assert.assertNotNull(result);
    Assert.assertEquals(1, result.length);
}
@Test
public void test20() {
    byte[] result = getCentralDirectoryData();
    Assert.assertNotNull(result);
    Assert.assertEquals(10, result.length);
}
@Test
public void test21() {
    byte[] result = getCentralDirectoryData();
    Assert.assertNotNull(result);
    Assert.assertEquals(100, result.length);
}
    @Test
    public void test22() throws ZipException {

        // Version=0, Len=0, Len=0.
        final byte[] ZERO_LEN = {0, 0, 0};

        // Version=0, Len=1, zero, Len=1, zero.
        final byte[] ZERO_UID_GID = {0, 1, 0, 1, 0};

        // Version=0, Len=1, one, Len=1, one
        final byte[] ONE_UID_GID = {0, 1, 1, 1, 1};

        // Version=0, Len=2, one thousand, Len=2, one thousand
        final byte[] ONE_THOUSAND_UID_GID = {0, 2, -24, 3, 2, -24, 3};

        // (2^32 - 1)
        final byte[] UNIX_MAX_UID_GID = {0, 4, -1, -1, -1, -1, 4, -1, -1, -1, -1};

        // Version=0, Len=5, 2^32, Len=5, 2^32 + 1
        final byte[] LENGTH_5 = {0, 5, 0, 0, 0, 0, 1, 5, 1, 0, 0, 0, 1};

        parseFromLocalFileData(0, 0, ZERO_LEN);
        parseFromLocalFileData(0, 0, ZERO_UID_GID);
        parseFromLocalFileData(0, 1, ZERO_UID_GID);
        parseFromLocalFileData(1, 1, ZERO_UID_GID);
        parseFromLocalFileData(1, 1, ONE_UID_GID);
        parseFromLocalFileData(1000, 1000, ONE_THOUSAND_UID_GID);
        parseFromLocalFileData(-2, -2, UNIX_MAX_UID_GID);
        parseFromLocalFileData(TWO_TO_32, TWO_TO_32 + 1, LENGTH_5);
        parseFromLocalFileData(Long.MAX_VALUE - 1, Long.MAX_VALUE, LENGTH_8);
    }
    @Test
    public void test23() throws Exception {
        byte[] buffer = new byte[10];
        int offset = 0;
        int length = 10;

        assertThrows(ZipException.class, () -> {
            parseFromCentralDirectoryData(buffer, offset, length);
        });
    }
    @Test
    public void test24() throws Exception {
        byte[] buffer = new byte[10];
        int offset = -1;
        int length = 10;

        assertThrows(ZipException.class, () -> {
            parseFromCentralDirectoryData(buffer, offset, length);
        });
    }
    @Test
    public void test25() throws Exception {
        byte[] buffer = new byte[10];
        int offset = 0;
        int length = -1;

        assertThrows(ZipException.class, () -> {
            parseFromCentralDirectoryData(buffer, offset, length);
        });
    }
    @Test
    public void test26() {
        uid = 0;
        gid = 0;
        reset();
        assertEquals(1000, uid);
        assertEquals(1000, gid);
    }
    @Test
    public void test27() {
        uid = Integer.MAX_VALUE;
        gid = Integer.MAX_VALUE;
        reset();
        assertEquals(1000, uid);
        assertEquals(1000, gid);
    }
    @Test
    public void test28() {
        uid = 0;
        gid = 0;
        reset();
        assertEquals(1000, uid);
        assertEquals(1000, gid);
    }
    @Test
    public void test29() throws Exception {
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertTrue(xf.equals(o));
        xf.setUID(54321); // change UID value to a different value
        assertFalse(xf.equals(o));
    }
    @Test
    public void test30() throws Exception {
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertTrue(xf.equals(o));
        xf.setGID(54321); // change GID value to a different value
        assertFalse(xf.equals(o));
    }
    @Test
    public void test31() throws Exception {
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertTrue(xf.equals(o));
        xf.setUID(54321); // change UID value to a different value
        xf.setGID(98765); // change GID value to a different value
        assertFalse(xf.equals(o));
    }
    @Test
    public void test32() throws Exception {
        // Original test case
        assertFalse(xf.equals(new Object()));
        
        // Kill mutant by passing null as parameter
        assertFalse(xf.equals(null));
        
        // Kill mutant by passing a different type of object
        assertFalse(xf.equals("test"));
        
        // Kill mutant by changing the UID value
        xf.setUID(0); // Set UID to 0 to create a different object
        assertTrue(xf.equals(xf.clone()));
    }
    @Test
    public void test33() throws Exception {
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

                // Modify uid and gid values
                xf.setUID(123);
                xf.setGID(456);

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
    public void test34() {
        // Change the expected header ID value
        assertEquals(12345, xf.getHeaderId());
    }
    @Test
    public void test35() throws Exception {
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        
        // Modify uid and gid values
        xf.setUID(123);
        xf.setGID(456);
        
        // Test equality after changing uid and gid values
        assertFalse(xf.equals(o));
    }
    @Test
    public void test36() throws Exception {
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
        File archive = getFile("COMPRESS-211_uid_gid_zip_test.zip");
        ZipFile zf = null;

        try {
            zf = new ZipFile(archive);
            ZipArchiveEntry zae = zf.getEntry("zero_uid_and_gid.txt");
            X7875_NewUnix xf = (X7875_NewUnix) zae.getExtraField(X7875);

            assertEquals(0, xf.getUID());
            assertEquals(0, xf.getGID());
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
            ZipArchiveEntry zae = zf.getEntry("max_uid_and_gid.txt");
            X7875_NewUnix xf = (X7875_NewUnix) zae.getExtraField(X7875);

            // 2^32-2 was the biggest UID/GID I could create on my linux!
            // (December 2012, linux kernel 3.4)
            assertEquals(0x100000000L - 2, xf.getUID());
            assertEquals(0x100000000L - 2, xf.getGID());
        } finally {
            if (zf != null) {
                zf.close();
            }
        }
    }
    @Test
    public void test40() throws Exception {
        File archive = getFile("COMPRESS-211_uid_gid_zip_test.zip");
        ZipFile zf = null;

        try {
            zf = new ZipFile(archive);
            ZipArchiveEntry zae = zf.getEntry("random_uid_and_gid.txt");
            X7875_NewUnix xf = (X7875_NewUnix) zae.getExtraField(X7875);

            assertEquals(99999, xf.getUID());
            assertEquals(99999, xf.getGID());
        } finally {
            if (zf != null) {
                zf.close();
            }
        }
    }
    @Test
    public void test41() {
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
        
        final byte[] ZERO_LEADING_ZEROES = {0, 0, 0};
        final byte[] ZERO_LEADING_ONE = {0, 0, 1};
        final byte[] ZERO_TRAILING_ZERO = {0, 0, 1, 0};
        final byte[] ZERO_TRAILING_ONE = {0, 0, 1, 1};

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
        
        assertTrue(Arrays.equals(ZERO_LEADING_ZEROES, trimTest(new byte[]{0, 0, 0, 0})));
        assertTrue(Arrays.equals(ZERO_LEADING_ONE, trimTest(new byte[]{0, 0, 0, 1})));
        assertTrue(Arrays.equals(ZERO_TRAILING_ZERO, trimTest(new byte[]{0, 0, 1, 0, 0})));
        assertTrue(Arrays.equals(ZERO_TRAILING_ONE, trimTest(new byte[]{0, 0, 1, 0, 1})));
    }
}