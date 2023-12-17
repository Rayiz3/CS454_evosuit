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
                long expected = 2000; // Change the expected value to a different UID/GID
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
                if (!name.contains("uid555_gid555")) { // Change the condition to make it false
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
    public void test2() {
        assertEquals(X7875, "headerId"); // Change the expected value to a different headerId
    }
@Test
public void test3() throws ZipException {
    final byte[] ZERO_UID_GID = {1, 1, 0, 1, 0};
    parseReparse(1, 1, ZERO_UID_GID, 1, 1);
    assertEquals(1, xf.getUID());
    assertEquals(1, xf.getGID());
}
@Test
public void test4() throws ZipException {
    final byte[] ONE_UID_GID = {1, 1, 1, 1, 1};
    parseReparse(1000, 1000, ONE_UID_GID, 1000, 1000);
    assertEquals(1000, xf.getUID());
    assertEquals(1000, xf.getGID());
}
@Test
public void test5() throws ZipException {
    final byte[] UNIX_MAX_UID_GID = {1, 4, -2, -1, -1, -1, 4, -2, -1, -1, -1};
    parseReparse(Long.MAX_VALUE - 1, Long.MAX_VALUE, UNIX_MAX_UID_GID, Long.MAX_VALUE - 1, Long.MAX_VALUE);
    assertEquals(Long.MAX_VALUE - 1, xf.getUID());
    assertEquals(Long.MAX_VALUE, xf.getGID());
}
@Test
public void test6() throws Exception {
    File archive = getFile("COMPRESS-211_uid_gid_zip_test.zip");
    ZipFile zf = null;

    try {
        zf = new ZipFile(archive);
        Enumeration<ZipArchiveEntry> en = zf.getEntries();

        while (en.hasMoreElements()) {
            ZipArchiveEntry zae = en.nextElement();
            String name = zae.getName();
            X7875_NewUnix xf = (X7875_NewUnix) zae.getExtraField(X7875);

            long expected = 555;

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
    public void test7() throws ZipException {
    
        // Version=1, Len=0, Len=0.
        final byte[] ZERO_LEN = {1, 0, 0};
        final byte[] ZERO_LEN_MODIFIED = {0, 0, 0}; // Modified input
        
        // Version=1, Len=1, zero, Len=1, zero.
        final byte[] ZERO_UID_GID = {1, 1, 0, 1, 0};
        final byte[] ZERO_UID_GID_MODIFIED = {1, 1, 1, 0}; // Modified input
        
        // Version=1, Len=1, one, Len=1, one
        final byte[] ONE_UID_GID = {1, 1, 1, 1, 1};
        final byte[] ONE_UID_GID_MODIFIED = {1, 1, 1, 0, 1}; // Modified input
        
        // Version=1, Len=2, one thousand, Len=2, one thousand
        final byte[] ONE_THOUSAND_UID_GID = {1, 2, -24, 3, 2, -24, 3};
        final byte[] ONE_THOUSAND_UID_GID_MODIFIED = {1, 2, -24, 3, -3, -23, -3}; // Modified input
        
        // (2^32 - 2).   I guess they avoid (2^32 - 1) since it's identical to -1 in
        // two's complement, and -1 often has a special meaning.
        final byte[] UNIX_MAX_UID_GID = {1, 4, -2, -1, -1, -1, 4, -2, -1, -1, -1};
        final byte[] UNIX_MAX_UID_GID_MODIFIED = {1, 4, -2, -1, -1, -1, -4, -1, -1, -1}; // Modified input
        
        // Version=1, Len=5, 2^32, Len=5, 2^32 + 1
        // Esoteric test:  can we handle 40 bit numbers?
        final byte[] LENGTH_5 = {1, 5, 0, 0, 0, 0, 1, 5, 1, 0, 0, 0, 1};
        final byte[] LENGTH_5_MODIFIED = {1, 5, -1, -1, -1, -1, 1, 5, 0, 0, 0, 0}; // Modified input
        
        // Version=1, Len=8, 2^63 - 2, Len=8, 2^63 - 1
        // Esoteric test:  can we handle 64 bit numbers?
        final byte[] LENGTH_8 = {1, 8, -2, -1, -1, -1, -1, -1, -1, 127, 8, -1, -1, -1, -1, -1, -1, -1, 127};
        final byte[] LENGTH_8_MODIFIED = {1, 8, -1, -1, -1, -1, -1, -1, -1, -128, 8, -1, -1, -1, -1, -1, -1, -1, 127}; // Modified input
        
        final long TWO_TO_32 = 0x100000000L;
        final long MAX = TWO_TO_32 - 2;
        
        parseReparse(0, 0, ZERO_LEN, 0, 0);
        parseReparse(0, 0, ZERO_LEN_MODIFIED, 0, 0); // Modified input
        parseReparse(0, 0, ZERO_UID_GID, 0, 0);
        parseReparse(0, 0, ZERO_UID_GID_MODIFIED, 0, 0); // Modified input
        parseReparse(1, 1, ONE_UID_GID, 1, 1);
        parseReparse(1, 1, ONE_UID_GID_MODIFIED, 1, 1); // Modified input
        parseReparse(1000, 1000, ONE_THOUSAND_UID_GID, 1000, 1000);
        parseReparse(1000, 1000, ONE_THOUSAND_UID_GID_MODIFIED, 1000, 1000); // Modified input
        parseReparse(MAX, MAX, UNIX_MAX_UID_GID, MAX, MAX);
        parseReparse(MAX, MAX, UNIX_MAX_UID_GID_MODIFIED, MAX, MAX); // Modified input
        parseReparse(-2, -2, UNIX_MAX_UID_GID, MAX, MAX);
        parseReparse(TWO_TO_32, TWO_TO_32 + 1, LENGTH_5, TWO_TO_32, TWO_TO_32 + 1);
        parseReparse(TWO_TO_32, TWO_TO_32 + 1, LENGTH_5_MODIFIED, TWO_TO_32, TWO_TO_32 + 1); // Modified input
        parseReparse(Long.MAX_VALUE - 1, Long.MAX_VALUE, LENGTH_8, Long.MAX_VALUE - 1, Long.MAX_VALUE);
        parseReparse(Long.MAX_VALUE - 1, Long.MAX_VALUE, LENGTH_8_MODIFIED, Long.MAX_VALUE - 1, Long.MAX_VALUE); // Modified input
        
        // We never emit this, but we should be able to parse it:
        final byte[] SPURIOUS_ZEROES_1 = {1, 4, -1, 0, 0, 0, 4, -128, 0, 0, 0};
        final byte[] SPURIOUS_ZEROES_1_MODIFIED = {1, 4, -1, 1, 0, 0, 4, 127, 0, 0, 0}; // Modified input
        final byte[] EXPECTED_1 = {1, 1, -1, 1, -128};
        xf.parseFromLocalFileData(SPURIOUS_ZEROES_1, 0, SPURIOUS_ZEROES_1.length);
        assertEquals(255, xf.getUID());
        assertEquals(128, xf.getGID());
        assertTrue(Arrays.equals(EXPECTED_1, xf.getLocalFileDataData()));
        
        xf.parseFromLocalFileData(SPURIOUS_ZEROES_1_MODIFIED, 0, SPURIOUS_ZEROES_1_MODIFIED.length);
        assertEquals(1, xf.getUID());
        assertEquals(127, xf.getGID());
        assertTrue(Arrays.equals(EXPECTED_1, xf.getLocalFileDataData())); // Unchanged output

        final byte[] SPURIOUS_ZEROES_2 = {1, 4, -1, -1, 0, 0, 4, 1, 2, 0, 0};
        final byte[] SPURIOUS_ZEROES_2_MODIFIED = {1, 4, -1, -1, 1, 0, 4, 127, 2, 0, 0}; // Modified input
        final byte[] EXPECTED_2 = {1, 2, -1, -1, 2, 1, 2};
        xf.parseFromLocalFileData(SPURIOUS_ZEROES_2, 0, SPURIOUS_ZEROES_2.length);
        assertEquals(65535, xf.getUID());
        assertEquals(513, xf.getGID());
        assertTrue(Arrays.equals(EXPECTED_2, xf.getLocalFileDataData()));

        xf.parseFromLocalFileData(SPURIOUS_ZEROES_2_MODIFIED, 0, SPURIOUS_ZEROES_2_MODIFIED.length);
        assertEquals(65535, xf.getUID());
        assertEquals(513, xf.getGID());
        assertTrue(Arrays.equals(EXPECTED_2, xf.getLocalFileDataData())); // Unchanged output
    }
    @Test
    public void test8() {
        xf.setUID(0);
        assertEquals(0, xf.getUID());

        xf.setUID(1);
        assertEquals(1, xf.getUID());

        xf.setUID(-1);
        assertEquals(-1, xf.getUID());

        xf.setUID(1000);
        assertEquals(1000, xf.getUID());

        xf.setUID(2147483647);
        assertEquals(2147483647, xf.getUID());

        xf.setUID(-2147483647);
        assertEquals(-2147483647, xf.getUID());

        xf.setUID(4294967296L);
        assertEquals(4294967296L, xf.getUID());

        xf.setUID(9223372036854775807L);
        assertEquals(9223372036854775807L, xf.getUID());
    }
    @Test
    public void test9() throws ZipException {

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
        
        // Regression tests
        parseReparse(255, 128, ZERO_LEN, 255, 128);
        parseReparse(0, 0, ZERO_UID_GID, 0, 0);
        parseReparse(1, 1, ONE_UID_GID, 1, 1);
        parseReparse(1000, 1000, ONE_THOUSAND_UID_GID, 1000, 1000);
        parseReparse(MAX, MAX, ZERO_UID_GID, MAX, MAX);
        parseReparse(-2, -2, ZERO_LEN, -2, -2);
        parseReparse(TWO_TO_32, TWO_TO_32, ZERO_UID_GID, TWO_TO_32, TWO_TO_32);
        parseReparse(Long.MAX_VALUE, Long.MAX_VALUE, ZERO_LEN, Long.MAX_VALUE, Long.MAX_VALUE);

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
    public void test10() throws Exception {
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
    public void test11() throws ZipException {

        // Version=1, Len=0, Len=0.
        final byte[] ZERO_LEN = {1, 0, 0};

        // Version=1, Len=1, zero, Len=1, zero.
        final byte[] ZERO_UID_GID = {1, 1, 0, 1, 0};

        // Version=1, Len=1, one, Len=1, one
        final byte[] ONE_UID_GID = {1, 1, 1, 1, 1};

        // Version=1, Len=2, one thousand, Len=2, one thousand
        final byte[] ONE_THOUSAND_UID_GID = {1, 2, 0x03, 0xf8, 0x03, 0xf8};

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
        long uid = 1;
        long gid = 1;
        
        int uidSize = trimLeadingZeroesForceMinLength(toByteArray(uid)).length;
        int gidSize = trimLeadingZeroesForceMinLength(toByteArray(gid)).length;
        assertEquals(3 + uidSize + gidSize, getLocalFileDataLength());
        
        uid = 0;
        gid = 0;
        
        uidSize = trimLeadingZeroesForceMinLength(toByteArray(uid)).length;
        gidSize = trimLeadingZeroesForceMinLength(toByteArray(gid)).length;
        assertEquals(3 + uidSize + gidSize, getLocalFileDataLength());
        
        uid = -2;
        gid = -2;
        
        uidSize = trimLeadingZeroesForceMinLength(toByteArray(uid)).length;
        gidSize = trimLeadingZeroesForceMinLength(toByteArray(gid)).length;
        assertEquals(3 + uidSize + gidSize, getLocalFileDataLength());
    }
    @Test
    public void test13() throws ZipException {
        final byte[] ZERO_LEN = {1, 0, 0};
        final byte[] ZERO_UID_GID = {1, 1, 0, 1, 0};
        final byte[] ONE_UID_GID = {1, 1, 1, 1, 1};
        final byte[] ONE_THOUSAND_UID_GID = {1, 2, -24, 3, 2, -24, 3};
        final byte[] UNIX_MAX_UID_GID = {1, 4, -2, -1, -1, -1, 4, -2, -1, -1, -1};
        final byte[] LENGTH_5 = {1, 5, 0, 0, 0, 0, 1, 5, 1, 0, 0, 0, 1};
        final byte[] LENGTH_8 = {1, 8, -2, -1, -1, -1, -1, -1, -1, 127, 8, -1, -1, -1, -1, -1, -1, -1, 127};
        final byte[] SPURIOUS_ZEROES_1 = {1, 4, -1, 0, 0, 0, 4, -128, 0, 0, 0};
        final byte[] EXPECTED_1 = {1, 1, -1, 1, -128};
        final byte[] SPURIOUS_ZEROES_2 = {1, 4, -1, -1, 0, 0, 4, 1, 2, 0, 0};
        final byte[] EXPECTED_2 = {1, 2, -1, -1, 2, 1, 2};

        parseReparse(23, 45, ZERO_LEN, 23, 45);
        parseReparse(34, 67, ZERO_UID_GID, 34, 67);
        parseReparse(4, 87, ONE_UID_GID, 4, 87);
        parseReparse(78, 91, ONE_THOUSAND_UID_GID, 78, 91);
        parseReparse(56, 12, UNIX_MAX_UID_GID, 56, 12);
        parseReparse(-45, -67, UNIX_MAX_UID_GID, 56, 12);
        parseReparse(90, 23, LENGTH_5, 90, 23);
        parseReparse(64, 83, LENGTH_8, 64, 83);

        xf.parseFromLocalFileData(SPURIOUS_ZEROES_1, 0, SPURIOUS_ZEROES_1.length);
        assertEquals(23, xf.getUID());
        assertEquals(-128, xf.getGID());
        assertTrue(Arrays.equals(EXPECTED_1, xf.getLocalFileDataData()));

        xf.parseFromLocalFileData(SPURIOUS_ZEROES_2, 0, SPURIOUS_ZEROES_2.length);
        assertEquals(170, xf.getUID());
        assertEquals(2, xf.getGID());
        assertTrue(Arrays.equals(EXPECTED_2, xf.getLocalFileDataData()));
    }
    @Test
    public void test14() {
        long uid = 0;
        long gid = 100;

        X7875_NewUnix xf = new X7875_NewUnix(uid, gid);
        byte[] expectedData = {1, 1, 0, 1, 100};
        byte[] actualData = xf.getLocalFileDataData();

        assertArrayEquals(expectedData, actualData);
    }
    @Test
    public void test15() {
        long uid = Long.MAX_VALUE;
        long gid = 500;

        X7875_NewUnix xf = new X7875_NewUnix(uid, gid);
        byte[] expectedData = {
            1, 
            8, 
            -1, -1, -1, -1, -1, -1, -1, 
            500
        };
        byte[] actualData = xf.getLocalFileDataData();

        assertArrayEquals(expectedData, actualData);
    }
    @Test
    public void test16() {
        long uid = -100;
        long gid = -200;

        X7875_NewUnix xf = new X7875_NewUnix(uid, gid);
        byte[] expectedData = {
            1, 
            4, 
            -1, -1, -1, -100, 
            4, 
            -1, -1, -1, -200
        };
        byte[] actualData = xf.getLocalFileDataData();

        assertArrayEquals(expectedData, actualData);
    }
    @Test
    public void test17() {
        long uid = 9876543210L;
        long gid = 1234567890L;

        X7875_NewUnix xf = new X7875_NewUnix(uid, gid);
        byte[] expectedData = {
            1, 
            5, 
            58, 52, 112, 2, 0, 
            5, 
            202, 150, 73, 46, 0
        };
        byte[] actualData = xf.getLocalFileDataData();

        assertArrayEquals(expectedData, actualData);
    }
public void test18() {
    // Test case 1: Empty byte array
    byte[] expected = new byte[0];
    byte[] actual = getCentralDirectoryData();
    assertEquals(expected, actual);

    // Test case 2: Non-empty byte array
    byte[] expected = new byte[]{1, 2, 3, 4};
    byte[] actual = getCentralDirectoryData();
    assertEquals(expected, actual);
}
    @Test
    public void test19() throws Exception {
        // Version=0, Len=0, Len=0.
        final byte[] ZERO_LEN = {0, 0, 0};
        
        parseReparse(0, 0, ZERO_LEN, 0, 0);
    }
    @Test
    public void test20() throws Exception {
        // Version=1, Len=10, 1000000000, Len=10, 2000000000.
        // Esoteric test:  can we handle large numbers?
        final byte[] LENGTH_10 = {1, 10, -78, 69, -127, 75, 0, 0, 0, 0, 0, 1, 10, 32, -16, -127, 75, 0, 0, 0, 2};
        
        parseReparse(1000000000, 2000000000, LENGTH_10, 1000000000, 2000000000);
    }
    @Test
    public void test21() throws Exception {
        // Version=1, Len=1, 128, Len=1, 128.
        final byte[] ONE_HUNDRED_TWENTY_EIGHT_UID_GID = {1, 1, -128, 1, -128};
        
        parseReparse(128, 128, ONE_HUNDRED_TWENTY_EIGHT_UID_GID, 128, 128);
    }
    @Test
    public void test22() throws Exception {
        // Version=1, Len=4, -1, -1, -1, -1, Len=4, -1, -1, -1, -2.
        // Esoteric test: can we handle negative numbers?
        final byte[] NEGATIVE_UID_GID = {1, 4, -1, -1, -1, -1, 4, -1, -1, -1, -2};
        
        parseReparse(-1, -2, NEGATIVE_UID_GID, -1, -2);
    }
    @Test
    public void test23() throws Exception {
        // Version=1, Len=3, 999, Len=3, 999.
        final byte[] NINE_HUNDRED_NINETY_NINE_UID_GID = {1, 3, -25, 3, 3, -25, 3};
        
        parseReparse(999, 999, NINE_HUNDRED_NINETY_NINE_UID_GID, 999, 999);
    }
@Test
public void test24() {
    byte[] buffer = new byte[0];
    int offset = 0;
    int length = 0;

    try {
        parseFromCentralDirectoryData(buffer, offset, length);
        fail("Expected ZipException to be thrown");
    } catch (ZipException e) {
        // Expected exception
    }
}
@Test
public void test25() {
    byte[] buffer = new byte[10];
    int offset = -1;
    int length = 5;

    try {
        parseFromCentralDirectoryData(buffer, offset, length);
        fail("Expected ZipException to be thrown");
    } catch (ZipException e) {
        // Expected exception
    }
}
@Test
public void test26() {
    byte[] buffer = new byte[10];
    int offset = 0;
    int length = -1;

    try {
        parseFromCentralDirectoryData(buffer, offset, length);
        fail("Expected ZipException to be thrown");
    } catch (ZipException e) {
        // Expected exception
    }
}
@Test
public void test27() {
    byte[] buffer = new byte[10];
    int offset = 15;
    int length = 5;

    try {
        parseFromCentralDirectoryData(buffer, offset, length);
        fail("Expected ZipException to be thrown");
    } catch (ZipException e) {
        // Expected exception
    }
}
@Test
public void test28() {
    byte[] buffer = new byte[10];
    int offset = 0;
    int length = 15;

    try {
        parseFromCentralDirectoryData(buffer, offset, length);
        fail("Expected ZipException to be thrown");
    } catch (ZipException e) {
        // Expected exception
    }
}
    @Test
    public void test29() {
        uid = -1;
        gid = -1;
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
        uid = 123;
        gid = 123;
        reset();
        assertEquals(1000, uid);
        assertEquals(1000, gid);
    }
    @Test
    public void test32() {
        uid = Integer.MAX_VALUE;
        gid = Integer.MAX_VALUE;
        reset();
        assertEquals(1000, uid);
        assertEquals(1000, gid);
    }
    @Test
    public void test33() {
        uid = Integer.MIN_VALUE;
        gid = Integer.MIN_VALUE;
        reset();
        assertEquals(1000, uid);
        assertEquals(1000, gid);
    }
    @Test
    public void test34() {
        xf.setUID(67890);
        assertEquals("0x7875 Zip Extra Field: UID=67890 GID=0", xf.toString());
    }
    @Test
    public void test35() throws Exception {
        ZipExtraField anotherXf = new MyZipExtraField();
        assertFalse(xf.equals(anotherXf));
        assertFalse(xf.equals(null));
        assertFalse(xf.hashCode() == anotherXf.hashCode());
    }
    @Test
    public void test36() throws Exception {
        xf.setUID(54321);
        MyZipExtraField clonedXf = (MyZipExtraField) xf.clone();
        assertEquals(xf.getUID(), clonedXf.getUID());
        assertEquals(xf.getGID(), clonedXf.getGID());
        assertNotSame(xf, clonedXf);
    }
    @Test
    public void test37() throws Exception {
        // Test with different object type
        assertFalse(xf.equals(new Object()));
        
        // Test with null object
        assertFalse(xf.equals(null));
        
        // Test with the same object
        assertTrue(xf.equals(xf.clone()));
        
        // Test with different object UID
        ZipExtraField clonedField = (ZipExtraField) xf.clone();
        clonedField.setUID(12345);
        assertFalse(xf.equals(clonedField));
    }
    @Test
    public void test38() throws Exception {
        File archive = getFile("COMPRESS-211_uid_gid_zip_test.zip");
        ZipFile zf = null;

        try {
            zf = new ZipFile(archive);
            Enumeration<ZipArchiveEntry> en = zf.getEntries();

            while (en.hasMoreElements()) {

                ZipArchiveEntry zae = en.nextElement();
                String name = zae.getName();
                X7875_NewUnix xf = (X7875_NewUnix) zae.getExtraField(X7875);

                long expected = 100; // Changing expected value to a different number
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
    public void test39() {
        assertEquals(X7875, xf.getHeaderId());
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

                // The directory entry in the test zip file is uid/gid 1000.
                long expected = 2000; // Modified the expected UID
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
    public void test42() throws Exception {
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertTrue(xf.equals(o));
        xf.setUID(9876); // Modified the UID value
        assertFalse(xf.equals(o));
    }
@Test
public void test43() {
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
    final byte[] FIVE_ZEROES = {0, 0, 0, 0, 0};
    final byte[] FIVE_SEQUENCE = {1, 2, 3, 4, 5};

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
    assertTrue(Arrays.equals(FIVE_ZEROES, trimTest(FIVE_ZEROES)));
    assertTrue(Arrays.equals(FIVE_SEQUENCE, trimTest(FIVE_SEQUENCE)));
}
}