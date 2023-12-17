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
        assertEquals(A1234, xf.getHeaderId());
    }
    @Test
    public void test1() {
        assertEquals(B5678, xf.getHeaderId());
    }
    @Test
    public void test2() {
        assertEquals(C9101, xf.getHeaderId());
    }
    @Test
    public void test3() throws Exception {
        File archive = getFile("COMPRESS-211_regtest1.zip");
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
                long expected = 2000;
                if (name.contains("uid555_gid555")) {
                    expected = 555;
                } else if (name.contains("uid5555_gid5555")) {
                    expected = 5000;
                } else if (name.contains("uid55555_gid55555")) {
                    expected = 55550;
                } else if (name.contains("uid555555_gid555555")) {
                    expected = 5555550;
                } else if (name.contains("min_unix")) {
                    expected = 1000;
                } else if (name.contains("max_unix")) {
                    // 2^32-2 was the biggest UID/GID I could create on my linux!
                    // (December 2012, linux kernel 3.4)
                    expected = 0x100000000L - 1;
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
        File archive = getFile("COMPRESS-211_regtest2.zip");
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
                long expected = 3000;
                if (name.contains("uid555_gid555")) {
                    expected = 5055;
                } else if (name.contains("uid5555_gid5555")) {
                    expected = 55500;
                } else if (name.contains("uid55555_gid55555")) {
                    expected = 55055;
                } else if (name.contains("uid555555_gid555555")) {
                    expected = 555500;
				}else if (name.contains("min_unix")) {
                    expected = 5000;
                } else if (name.contains("max_unix")) {
                    // 2^32-2 was the biggest UID/GID I could create on my linux!
                    // (December 2012, linux kernel 3.4)
                    expected = 0x100000000L;
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
        final byte[] MAX_UINT = {(byte) 255, (byte) 255, (byte) 255, (byte) 255, 1, 1};
        parseReparse(4294967295L, 257, MAX_UINT, 4294967295L, 257);
    }
    @Test
    public void test6() throws ZipException {
        final byte[] NEGATIVE_ONE = {(byte) 255, (byte) 255, (byte) 255, (byte) 255, (byte) 255, (byte) 255, (byte) 255, (byte) 255};
        parseReparse(-1L, -1L, NEGATIVE_ONE, -1L, -1L);
    }
    @Test
    public void test7() throws ZipException {
        final byte[] NEGATIVE_MAX_LONG = {(byte) 255, (byte) 255, (byte) 255, (byte) 255, (byte) 255, (byte) 255, (byte) 255, (byte) 127};
        parseReparse(-9223372036854775808L, 9223372036854775807L, NEGATIVE_MAX_LONG, -9223372036854775808L, 9223372036854775807L);
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
                } else if (name.contains("max_long")) {
                    expected = 9223372036854775807L;
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
public void test9() throws ZipException {
    final byte[] input = {1, 0, 1}; // Version=1, Len=0, Len=1
    final long expected = 1;

    parseReparse(expected, expected, input, expected, expected);
}
@Test
public void test10() throws ZipException {
    final byte[] input = {1, 2, -1, -1, -1, -1, -1, -1, -1, 0, 0}; // Version=1, Len=8, 2^63-1, Len=0, Len=0
    final long expected = Long.MAX_VALUE - 1;

    parseReparse(expected, expected, input, expected, expected);
}
@Test
public void test11() throws ZipException {
    final byte[] input = {1, 3, 1, -99, 0, 1, 0}; // Version=1, Len=3, 1, -99, Len=1, 0
    final long expected = -2519;

    parseReparse(expected, expected, input, expected, expected);
}
@Test
public void test12() throws ZipException {
    ...
    // Regression test 1: Changed input value to negative
    parseReparse(-1000, -1000, ONE_THOUSAND_UID_GID, -1000, -1000);
    
    // Regression test 2: Changed input value to maximum positive long
    parseReparse(Long.MAX_VALUE, Long.MAX_VALUE, LENGTH_8, Long.MAX_VALUE, Long.MAX_VALUE);
    
    // Regression test 3: Changed input value to zero
    parseReparse(0, 0, ZERO_UID_GID, 0, 0);

}
    @Test
    public void test13() throws ZipException {
    
        // Version=1, Len=0, Len=0.
        final byte[] ZERO_LEN = {1, 0, 0};

        parseReparse(1, 1, ZERO_UID_GID, 0, 0);
        parseReparse(2, 2, ZERO_UID_GID, 0, 0);
        parseReparse(3, 3, ZERO_UID_GID, 0, 0);
        parseReparse(4, 4, ZERO_UID_GID, 0, 0);
        
        // Version=1, Len=1, zero, Len=1, zero.
        final byte[] ZERO_UID_GID = {1, 1, 0, 1, 0};
        
        parseReparse(0, 1, ZERO_UID_GID, 0, 0);
        parseReparse(0, 2, ZERO_UID_GID, 0, 0);
        parseReparse(0, 3, ZERO_UID_GID, 0, 0);
        parseReparse(0, 4, ZERO_UID_GID, 0, 0);
        
        // Version=1, Len=1, one, Len=1, one
        final byte[] ONE_UID_GID = {1, 1, 1, 1, 1};
        
        parseReparse(1, 0, ONE_UID_GID, 1, 1);
        parseReparse(2, 0, ONE_UID_GID, 1, 1);
        parseReparse(3, 0, ONE_UID_GID, 1, 1);
        parseReparse(4, 0, ONE_UID_GID, 1, 1);
        
        // Version=1, Len=2, one thousand, Len=2, one thousand
        final byte[] ONE_THOUSAND_UID_GID = {1, 2, -24, 3, 2, -24, 3};
        
        parseReparse(1000, 999, ONE_THOUSAND_UID_GID, 1000, 1000);
        parseReparse(999, 1000, ONE_THOUSAND_UID_GID, 999, 999);
        parseReparse(999, 999, ONE_THOUSAND_UID_GID, 999, 999);
        parseReparse(1000, 1000, ONE_THOUSAND_UID_GID, 1000, 1000);
        
        // (2^32 - 2). 
        final byte[] UNIX_MAX_UID_GID = {1, 4, -2, -1, -1, -1, 4, -2, -1, -1, -1};
        
        parseReparse(4294967294L, 4294967294L, UNIX_MAX_UID_GID, 4294967294L, 4294967294L);
        parseReparse(1L, 1L, UNIX_MAX_UID_GID, 4294967294L, 4294967294L);
        parseReparse(4294967294L, 1L, UNIX_MAX_UID_GID, 4294967294L, 4294967294L);
        parseReparse(1L, 4294967294L, UNIX_MAX_UID_GID, 4294967294L, 4294967294L);
        
        // Version=1, Len=5, 2^32, Len=5, 2^32 + 1
        // Esoteric test:  can we handle 40 bit numbers?
        final byte[] LENGTH_5 = {1, 5, 0, 0, 0, 0, 1, 5, 1, 0, 0, 0, 1};
        
        parseReparse(4294967296L, 4294967297L, LENGTH_5, 4294967296L, 4294967296L);
        parseReparse(4294967297L, 4294967296L, LENGTH_5, 4294967296L, 4294967296L);
        parseReparse(4294967296L, 4294967296L, LENGTH_5, 4294967296L, 4294967296L);
        parseReparse(4294967297L, 4294967297L, LENGTH_5, 4294967297L, 4294967297L);
        
        // Version=1, Len=8, 2^63 - 2, Len=8, 2^63 - 1
        // Esoteric test:  can we handle 64 bit numbers?
        final byte[] LENGTH_8 = {1, 8, -2, -1, -1, -1, -1, -1, -1, 127, 8, -1, -1, -1, -1, -1, -1, -1, 127};
        
        parseReparse(Long.MAX_VALUE - 2, Long.MAX_VALUE - 1, LENGTH_8, Long.MAX_VALUE - 2, Long.MAX_VALUE - 2);
        parseReparse(Long.MAX_VALUE - 1, Long.MAX_VALUE - 2, LENGTH_8, Long.MAX_VALUE - 2, Long.MAX_VALUE - 2);
        parseReparse(Long.MAX_VALUE - 2, Long.MAX_VALUE - 2, LENGTH_8, Long.MAX_VALUE - 2, Long.MAX_VALUE - 2);
        parseReparse(Long.MAX_VALUE - 1, Long.MAX_VALUE - 1, LENGTH_8, Long.MAX_VALUE - 1, Long.MAX_VALUE - 1);
    }
    @Test
    public void test14() throws Exception {
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
    public void test16() {
        // Version=1, uidsize=1, gidsize=1 => total length=3
        assertEquals(3, getLocalFileDataLength(0, 0));
    }
    @Test
    public void test17() {
        // Version=1, uidsize=4, gidsize=5 => total length=1 + 4 + 5 = 10
        assertEquals(10, getLocalFileDataLength(1234, 54321));
    }
    @Test
    public void test18() {
        // Version=1, uidsize=10, gidsize=10 => total length=1 + 10 + 10 = 21
        long maxUIDGID = 0x100000000L - 2; // 2^32 - 2
        assertEquals(21, getLocalFileDataLength(maxUIDGID, maxUIDGID));
    }
    @Test
    public void test19() {
        // Version=1, uidsize=10, gidsize=10 => total length=1 + 10 + 10 = 21
        long negativeUIDGID = -2;
        assertEquals(21, getLocalFileDataLength(negativeUIDGID, negativeUIDGID));
    }
    @Test
    public void test20() {
        // Version=1, uidsize=13, gidsize=15 => total length=1 + 13 + 15 = 29
        long largeUIDGID = 0x100000000L + 1; // 2^32 + 1
        assertEquals(29, getLocalFileDataLength(largeUIDGID, largeUIDGID));
    }
    @Test
    public void test21() throws ZipException {

        // Version=1, Len=0, Len=0.
        final byte[] ZERO_LEN = {2, 0, 0};

        // Version=1, Len=1, zero, Len=1, zero.
        final byte[] ZERO_UID_GID = {2, 1, 0, 1, 0};

        // Version=1, Len=1, one, Len=1, one
        final byte[] ONE_UID_GID = {2, 1, 1, 1, 1};

        // Version=1, Len=2, one thousand, Len=2, one thousand
        final byte[] ONE_THOUSAND_UID_GID = {2, 2, -24, 3, 2, -24, 3};

        // (2^32 - 2).   I guess they avoid (2^32 - 1) since it's identical to -1 in
        // two's complement, and -1 often has a special meaning.
        final byte[] UNIX_MAX_UID_GID = {2, 4, -2, -1, -1, -1, 4, -2, -1, -1, -1};

        // Version=1, Len=5, 2^32, Len=5, 2^32 + 1
        // Esoteric test:  can we handle 40 bit numbers?
        final byte[] LENGTH_5 = {2, 5, 0, 0, 0, 0, 1};

        // Version=1, Len=8, 2^63 - 2, Len=8, 2^63 - 1
        // Esoteric test:  can we handle 64 bit numbers?
        final byte[] LENGTH_8 = {2, 8, -2, -1, -1, -1, -1, -1, -1, 127, 8, -1, -1, -1, -1, -1, -1, -1, 127};

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
        final byte[] SPURIOUS_ZEROES_1 = {2, 4, -1, 0, 0, 0, 4, -128, 0, 0, 0};
        final byte[] EXPECTED_1 = {1, 1, -1, 1, -128};
        xf.parseFromLocalFileData(SPURIOUS_ZEROES_1, 0, SPURIOUS_ZEROES_1.length);

        assertEquals(255, xf.getUID());
        assertEquals(128, xf.getGID());
        assertTrue(Arrays.equals(EXPECTED_1, xf.getLocalFileDataData()));

        final byte[] SPURIOUS_ZEROES_2 = {2, 4, -1, -1, 0, 0, 4, 1, 2, 0, 0};
        final byte[] EXPECTED_2 = {1, 2, -1, -1, 2, 1, 2};
        xf.parseFromLocalFileData(SPURIOUS_ZEROES_2, 0, SPURIOUS_ZEROES_2.length);

        assertEquals(65535, xf.getUID());
        assertEquals(513, xf.getGID());
        assertTrue(Arrays.equals(EXPECTED_2, xf.getLocalFileDataData()));
    }
    @Test
    public void test22() throws Exception {
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
    public void test23() throws ZipException {

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

        // Extra test cases
        final byte[] MIN_UID_GID = {1, 1, -128, 1, -128};
        xf.parseFromLocalFileData(MIN_UID_GID, 0, MIN_UID_GID.length);
        assertEquals(Long.MIN_VALUE, xf.getUID());
        assertEquals(-128, xf.getGID());
        assertTrue(Arrays.equals(MIN_UID_GID, xf.getLocalFileDataData()));

        final byte[] NEGATIVE_UID_GID = {1, 1, -1, 1, -1};
        xf.parseFromLocalFileData(NEGATIVE_UID_GID, 0, NEGATIVE_UID_GID.length);
        assertEquals(-1, xf.getUID());
        assertEquals(-1, xf.getGID());
        assertTrue(Arrays.equals(NEGATIVE_UID_GID, xf.getLocalFileDataData()));
    }
@Test
public void test24() {
    byte[] input = new byte[10];
    
    byte[] result = getCentralDirectoryData(input);
    
    assertNotNull(result);
}
@Test
public void test25() {
    byte[] input = new byte[0];
    
    byte[] result = getCentralDirectoryData(input);
    
    assertNotNull(result);
}
@Test
public void test26() {
    byte[] input = new byte[1000];
    
    byte[] result = getCentralDirectoryData(input);
    
    assertNotNull(result);
}
@Test
public void test27() {
    byte[] input = null;
    
    byte[] result = getCentralDirectoryData(input);
    
    assertNotNull(result);
}
    @Test
    public void test28() throws Exception {
        // Regression tests to kill more mutants
        parseFromLocalFileData(new byte[]{1, 0, 0}, 0, 3); // kill mutant by changing the data array
        parseFromLocalFileData(new byte[]{1, 0, 0}, 1, 2); // kill mutant by changing the offset
        parseFromLocalFileData(new byte[]{1, 0, 0}, 0, 2); // kill mutant by changing the length

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

                // Regression tests to kill more mutants
                xf.setUID(500); // kill mutant by changing the expected value for UID
                assertEquals(500, xf.getUID());
                xf.setGID(500); // kill mutant by changing the expected value for GID
                assertEquals(500, xf.getGID());
            }
        } finally {
            if (zf != null) {
                zf.close();
            }
        }
    }
    @Test
    public void test29() throws ZipException {

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
        
        // Regression tests to kill more mutants
        xf.setUID(500); // kill mutant by changing the expected value for UID
        assertEquals(500, xf.getUID());
        xf.setGID(500); // kill mutant by changing the expected value for GID
        assertEquals(500, xf.getGID());
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
    public void test32() throws Exception {
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
public void test33() {
    assertEquals(X7875, xf.getHeaderId());
    assertEquals(12345, xf.getHeaderId()); // Regression test
}
@Test
public void test34() throws Exception {
    assertFalse(xf.equals(new Object()));
    assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
    Object o = xf.clone();
    assertEquals(o.hashCode(), xf.hashCode());
    assertTrue(xf.equals(o));
    xf.setUID(12345);
    assertFalse(xf.equals(o));
    xf.setUID(1000); // Regression test
    assertFalse(xf.equals(o));
}
@Test
public void test35() {
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
    final byte[] ONE_THOUSAND_SEQUENCE6 = {1, 2, 3, -24, 3, 101};
    
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
    assertTrue(Arrays.equals(ONE_THOUSAND_SEQUENCE6, trimTest(ONE_THOUSAND_SEQUENCE6))); // Regression test
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
        // Regression test
        en = zf.getEntries();
        while (en.hasMoreElements()) {
            ZipArchiveEntry zae = en.nextElement();
            X7875_NewUnix xf = (X7875_NewUnix) zae.getExtraField(X7875);
            xf.setUID(12345);
            assertEquals(12345, xf.getUID());
            xf.setUID(1000);
            assertEquals(1000, xf.getUID());
        }
    } finally {
        if (zf != null) {
            zf.close();
        }
    }
}
@Test
public void test37() throws ZipException {

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

    // Regression test
    byte[] INVALID_UID_GID = {1, 4, -24, -23, -24, -23, 4, -23, -24, -24, -23};
    xf.parseFromLocalFileData(INVALID_UID_GID, 0, INVALID_UID_GID.length);
    assertEquals(0, xf.getUID());
    assertEquals(0, xf.getGID());
}
    @Test
    public void test38() throws Exception {
        // Original test case
        assertFalse(xf.equals(new Object()));
        
        // Additional test cases
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        assertTrue(xf.toString().endsWith("UID=0 GID=0"));
        
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertTrue(xf.equals(o));
        
        xf.setUID(12345);
        assertFalse(xf.equals(o));
        
        // Regression test cases
        assertTrue(xf.toString().contains("UID=12345"));
        
        Object p = xf.clone();
        assertEquals(p.hashCode(), xf.hashCode());
        assertFalse(xf.equals(p));
        
        xf.setGID(67890);
        assertFalse(xf.equals(p));
    }
    @Test
    public void test39() throws Exception {
        Object o = null;
        try {
            o = xf.clone();
        } catch (CloneNotSupportedException e) {
            fail("CloneNotSupportedException should not be thrown");
        }
        assertNull(o);
    }
    @Test
    public void test40() throws Exception {
        Object o = new Object();
        try {
            o = xf.clone();
        } catch (CloneNotSupportedException e) {
            fail("CloneNotSupportedException should not be thrown");
        }
        assertNull(o);
    }
    @Test
    public void test41() throws Exception {
        xf.setUID(9876);
        Object o = null;
        try {
            o = xf.clone();
        } catch (CloneNotSupportedException e) {
            fail("CloneNotSupportedException should not be thrown");
        }
        assertNotNull(o);
        assertEquals(9876, ((ZipExtraField) o).getUID());
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
        X7875_NewUnix other = new X7875_NewUnix();
        other.setVersion(1);
        other.setUID(xf.getUID());
        other.setGID(xf.getGID());
        
        assertFalse(xf.equals(other));
    }
    @Test
    public void test46() {
        X7875_NewUnix other = new X7875_NewUnix();
        other.setVersion(xf.getVersion());
        other.setUID(111);
        other.setGID(xf.getGID());
        
        assertFalse(xf.equals(other));
    }
    @Test
    public void test47() {
        X7875_NewUnix other = new X7875_NewUnix();
        other.setVersion(xf.getVersion());
        other.setUID(xf.getUID());
        other.setGID(222);
        
        assertFalse(xf.equals(other));
    }
    @Test
    public void test48() {
        assertFalse(xf.equals(null));
    }
    @Test
    public void test49() {
        X7875_NewUnix other = new X7875_NewUnix();
        other.setVersion(1);
        other.setUID(xf.getUID());
        other.setGID(xf.getGID());
        
        assertNotEquals(xf.hashCode(), other.hashCode());
    }
    @Test
    public void test50() {
        X7875_NewUnix other = new X7875_NewUnix();
        other.setVersion(xf.getVersion());
        other.setUID(111);
        other.setGID(xf.getGID());
        
        assertNotEquals(xf.hashCode(), other.hashCode());
    }
    @Test
    public void test51() {
        X7875_NewUnix other = new X7875_NewUnix();
        other.setVersion(xf.getVersion());
        other.setUID(xf.getUID());
        other.setGID(222);
        
        assertNotEquals(xf.hashCode(), other.hashCode());
    }
    @Test
    public void test52() {
        X7875_NewUnix other = null;
        
        assertNotEquals(xf.hashCode(), other.hashCode());
    }
public class X7875_NewUnixTest {

    // Existing test cases
    @Test
    public void test53() throws Exception {
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertTrue(xf.equals(o));
        xf.setUID(12345);
        assertFalse(xf.equals(o));
    }

    @Test
    public void test54() throws Exception {
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
    
    // Regression test cases
    @Test
    public void test55() {
        X7875_NewUnix xf = new X7875_NewUnix(1, 0);
        int expectedHashCode = -1234567 * 1 ^ Integer.rotateLeft(0, 16);
        expectedHashCode ^= 0;
        int actualHashCode = xf.hashCode();
        assertEquals(expectedHashCode, actualHashCode);
    }

    @Test
    public void test56() {
        X7875_NewUnix xf = new X7875_NewUnix(0, 1);
        int expectedHashCode = -1234567 * 0 ^ Integer.rotateLeft(0, 16);
        expectedHashCode ^= 1;
        int actualHashCode = xf.hashCode();
        assertEquals(expectedHashCode, actualHashCode);
    }

    @Test
    public void test57() {
        X7875_NewUnix xf = new X7875_NewUnix(0, 0);
        int expectedHashCode = -1234567 * 0 ^ Integer.rotateLeft(0, 16);
        expectedHashCode ^= 0;
        int actualHashCode = xf.hashCode();
        assertEquals(expectedHashCode, actualHashCode);
    }
}
    @Test
    public void test58() {
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
        final byte[] SEQUENCE_WITH_LEADING_TRIPLE_ZEROES = {0, 0, 0, 1, 2, 3};
        final byte[] SEQUENCE_WITH_TRAILING_DOUBLE_ZEROES = {1, 2, 3, 0, 0};
        final byte[] SEQUENCE_WITH_PADDING_DOUBLE_ZEROES = {0, 1, 2, 3, 0, 0};
        final byte[] EXCESS_ZEROES = {0, 0, 0, 4, 5, 6};

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
        assertTrue(Arrays.equals(SEQUENCE, trimTest(SEQUENCE_WITH_LEADING_TRIPLE_ZEROES)));
        assertTrue(Arrays.equals(SEQUENCE, trimTest(SEQUENCE_WITH_TRAILING_DOUBLE_ZEROES)));
        assertTrue(Arrays.equals(SEQUENCE, trimTest(SEQUENCE_WITH_PADDING_DOUBLE_ZEROES)));
        assertTrue(Arrays.equals(SEQUENCE, trimTest(EXCESS_ZEROES)));
    }
}