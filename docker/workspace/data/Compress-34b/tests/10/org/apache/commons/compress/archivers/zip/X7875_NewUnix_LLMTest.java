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
        assertEquals(X7875, xf.getHeaderId());
        assertEquals(ZipShort.getBytes(X7875), xf.getHeaderId().getBytes());
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
                assertEquals(expected, xf.getUID());
                assertEquals(expected, xf.getGID());
                
                //Test case with a different input value
                assertEquals(ZipShort.getBytes(X7876), xf.getHeaderId().getBytes());
            }
        } finally {
            if (zf != null) {
                zf.close();
            }
        }
    }
    @Test
    public void test2() throws ZipException {

        // Version=1, Len=0, Len=0.
        final byte[] ZERO_LEN = {0, 0, 0};

        parseReparse(0, 0, ZERO_LEN, 0, 0);
        
    }
    @Test
    public void test3() throws ZipException {

        // Version=1, Len=1, zero, Len=1, zero.
        final byte[] ZERO_UID_GID = {0, 1, 0, 1, 0};

        parseReparse(0, 0, ZERO_UID_GID, 0, 0);
        
    }
    @Test
    public void test4() throws ZipException {

        // Version=1, Len=1, one, Len=1, one
        final byte[] ONE_UID_GID = {1, 1, 1, 1, 1};

        parseReparse(1, 1, ONE_UID_GID, 1, 1);
        
    }
    @Test
    public void test5() throws ZipException {

        // Version=1, Len=2, one thousand, Len=2, one thousand
        final byte[] ONE_THOUSAND_UID_GID = {1, 2, -24, 3, 2, -24, 3};

        parseReparse(1000, 1000, ONE_THOUSAND_UID_GID, 1000, 1000);
        
    }
    @Test
    public void test6() throws ZipException {

        // (2^32 - 2).   I guess they avoid (2^32 - 1) since it's identical to -1 in
        // two's complement, and -1 often has a special meaning.
        final byte[] UNIX_MAX_UID_GID = {0, 4, -2, -1, -1, -1, 4, -2, -1, -1, -1};

        parseReparse(Long.MAX_VALUE - 1, Long.MAX_VALUE, UNIX_MAX_UID_GID, Long.MAX_VALUE - 1, Long.MAX_VALUE);
        
    }
    @Test
    public void test7() throws ZipException {

        // Version=1, Len=5, 2^32, Len=5, 2^32 + 1
        // Esoteric test:  can we handle 40 bit numbers?
        final byte[] LENGTH_5 = {1, 5, 0, 0, 0, 0, 1};

        parseReparse(0x100000000L, 0x100000001L, LENGTH_5, 0x100000000L, 0x100000001L);
        
    }
    @Test
    public void test8() throws ZipException {

        // Version=1, Len=8, 2^63 - 2, Len=8, 2^63 - 1
        // Esoteric test:  can we handle 64 bit numbers?
        final byte[] LENGTH_8 = {1, 8, -2, -1, -1, -1, -1, -1, -1, 127};

        final long MAX = Long.MAX_VALUE;
        parseReparse(MAX - 2, MAX - 1, LENGTH_8, MAX - 2, MAX - 1);
        
    }
    @Test
    public void test9() throws ZipException {
        final byte[] ZERO_LEN = {0, 0, 0};
        parseReparse(0, 0, ZERO_LEN, 0, 0);
    }
    @Test
    public void test10() throws ZipException {
        final byte[] ZERO_UID_GID = {0, 1, 0, 1, 0};
        parseReparse(0, 0, ZERO_UID_GID, 0, 0);
    }
    @Test
    public void test11() throws ZipException {
        final byte[] ONE_UID_GID = {1, 1, 1, 1, 1};
        parseReparse(1, 1, ONE_UID_GID, 1, 1);
    }
    @Test
    public void test12() throws ZipException {
        final byte[] ONE_THOUSAND_UID_GID = {1, 2, -24, 3, 2, -24, 3};
        parseReparse(1000, 1000, ONE_THOUSAND_UID_GID, 1000, 1000);
    }
    @Test
    public void test13() throws ZipException {
        final byte[] UNIX_MAX_UID_GID = {1, 4, -2, -1, -1, -1, 4, -2, -1, -1, -1};
        parseReparse(4294967294L, 4294967294L, UNIX_MAX_UID_GID, 4294967294L, 4294967294L);
    }
    @Test
    public void test14() throws ZipException {
        final byte[] LENGTH_5 = {1, 5, 0, 0, 0, 0, 1, 5, 1, 0, 0, 0, 1};
        parseReparse(4294967296L, 4294967297L, LENGTH_5, 4294967296L, 4294967297L);
    }
    @Test
    public void test15() throws ZipException {
        final byte[] LENGTH_8 = {1, 8, -2, -1, -1, -1, -1, -1, -1, 127, 8, -1, -1, -1, -1, -1, -1, -1, 127};
        parseReparse(Long.MAX_VALUE - 2, Long.MAX_VALUE - 1, LENGTH_8, Long.MAX_VALUE - 2, Long.MAX_VALUE - 1);
    }
    @Test
    public void test16() throws Exception {
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
                    expected = 4294967294L;
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
    public void test17() throws Exception {
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
                    expected = 555556;
                } else if (name.contains("min_unix")) {
                    expected = 1;
                } else if (name.contains("max_unix")) {
                    expected = 4294967295L;
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

        // Additional test cases
        parseReparse(-1000, -1000, ONE_THOUSAND_UID_GID, -1000, -1000);
        parseReparse(0, 0, UNIX_MAX_UID_GID, 0, 0);
        parseReparse(-100, -100, ZERO_UID_GID, -100, -100);
        parseReparse(TWO_TO_32 + 1, TWO_TO_32 + 1, ONE_UID_GID, TWO_TO_32 + 1, TWO_TO_32 + 1);

    }
    @Test
    public void test19() throws ZipException {

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

        // Regression test cases
        parseReparse(0, 1, ZERO_UID_GID, 0, 1);
        parseReparse(100, 200, ONE_UID_GID, 100, 200);
        parseReparse(5000, 10000, ONE_THOUSAND_UID_GID, 5000, 10000);
        parseReparse(MAX - 1, MAX + 1, UNIX_MAX_UID_GID, MAX - 1, MAX + 1);
        parseReparse(TWO_TO_32 + 1, TWO_TO_32 + 2, LENGTH_5, TWO_TO_32 + 1, TWO_TO_32 + 2);
        parseReparse(Long.MAX_VALUE - 2, Long.MAX_VALUE - 1, LENGTH_8, Long.MAX_VALUE - 2, Long.MAX_VALUE - 1);

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
    public void test20() throws Exception {
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
    public void test21() throws ZipException {

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
    public void test22() {
        int uidSize = 0;
        int gidSize = 0;

        // The 3 comes from:  version=1 + uidsize=1 + gidsize=1
        int expected = 3;
        assertEquals(expected, new ZipShort(3 + uidSize + gidSize).getValue());
    }
    @Test
    public void test23() {
        int uidSize = -1;
        int gidSize = -1;

        // The 3 comes from:  version=1 + uidsize=1 + gidsize=1
        int expected = 3;
        assertEquals(expected, new ZipShort(3 + uidSize + gidSize).getValue());
    }
    @Test
    public void test24() {
        int uidSize = 100000;
        int gidSize = 100000;

        // The 3 comes from:  version=1 + uidsize=1 + gidsize=1
        int expected = 100003;
        assertEquals(expected, new ZipShort(3 + uidSize + gidSize).getValue());
    }
@Test
public void test25() throws ZipException {

    // Version=1, Len=-1, Len=-1.
    final byte[] NEG_LEN = {1, -1, -1};

    parseReparse(-1, -1, NEG_LEN, -1, -1);

}
@Test
public void test26() throws ZipException {

    // Version=0, Len=0, Len=0.
    final byte[] ZERO_LEN = {0, 0, 0};

    parseReparse(0, 0, ZERO_LEN, 0, 0);

}
@Test
public void test27() throws ZipException {

    // Version=1, Len=1000000, Len=1000000.
    final byte[] LARGE_LEN = {1, -64, -96, -96, 15, 0, 1, -64, -96, -96, 15, 0};

    parseReparse(1000000, 1000000, LARGE_LEN, 1000000, 1000000);

}
    public void test28() {
        // Create a mock byte array with some data
        byte[] data = new byte[] { 1, 2, 3, 4 };

        // Pass the mock data as input to the getCentralDirectoryData method
        byte[] result = getCentralDirectoryData(data);

        // Verify that the result is not an empty byte array
        assertEquals(false, result.length == 0);
    }
    public void test29() {
        // Create a mock byte array with some data
        byte[] data = new byte[] { 5, 6, 7, 8 };

        // Pass the mock data as input to the getCentralDirectoryData method
        byte[] result = getCentralDirectoryData(data);

        // Verify that the result is not an empty byte array
        assertEquals(false, result.length == 0);
    }
    public void test30() {
        // Create a mock byte array with some data
        byte[] data = new byte[] { 9, 10, 11, 12 };

        // Pass the mock data as input to the getCentralDirectoryData method
        byte[] result = getCentralDirectoryData(data);

        // Verify that the result is not an empty byte array
        assertEquals(false, result.length == 0);
    }
    public void test31() {
        // Create a mock byte array with some data
        byte[] data = new byte[] { 13, 14, 15, 16 };

        // Pass the mock data as input to the getCentralDirectoryData method
        byte[] result = getCentralDirectoryData(data);

        // Verify that the result is not an empty byte array
        assertEquals(false, result.length == 0);
    }
    public void test32() {
        // Create a mock byte array with some data
        byte[] data = new byte[0];

        // Pass the mock data as input to the getCentralDirectoryData method
        byte[] result = getCentralDirectoryData(data);

        // Verify that the result is not an empty byte array
        assertEquals(false, result.length == 0);
    }
    @Test
    public void test33() throws ZipException {
        // Version=0, Len=0, Len=0.
        final byte[] ZERO_LEN = {0, 0, 0};

        parseReparse(0, 0, ZERO_LEN, 0, 0);
    }
    @Test
    public void test34() throws ZipException {
        // Version=1, Len=0, Len=0.
        final byte[] ZERO_LEN = {1, 0, 0};

        parseReparse(0, 0, ZERO_LEN, 0, 0);
    }
    @Test
    public void test35() throws ZipException {
        // Version=1, Len=1, zero, Len=1, zero.
        final byte[] ZERO_UID_GID = {1, 1, 0, 1, 0};

        parseReparse(0, 0, ZERO_UID_GID, 0, 0);
    }
    @Test
    public void test36() throws ZipException {
        // Version=1, Len=1, one, Len=1, one
        final byte[] ONE_UID_GID = {1, 1, 1, 1, 1};

        parseReparse(1, 1, ONE_UID_GID, 1, 1);
    }
    @Test
    public void test37() throws ZipException {
        // Version=1, Len=2, one thousand, Len=2, one thousand
        final byte[] ONE_THOUSAND_UID_GID = {1, 2, -24, 3, 2, -24, 3};

        parseReparse(1000, 1000, ONE_THOUSAND_UID_GID, 1000, 1000);
    }
    @Test
    public void test38() throws ZipException {
        // (2^32 - 2).   I guess they avoid (2^32 - 1) since it's identical to -1 in
        // two's complement, and -1 often has a special meaning.
        final byte[] UNIX_MAX_UID_GID = {1, 4, -2, -1, -1, -1, 4, -2, -1, -1, -1};

        parseReparse(Long.MAX_VALUE - 1, Long.MAX_VALUE - 1, UNIX_MAX_UID_GID, Long.MAX_VALUE - 1, Long.MAX_VALUE - 1);
    }
    @Test
    public void test39() throws ZipException {
        // Version=1, Len=5, 2^32, Len=5, 2^32 + 1
        // Esoteric test:  can we handle 40 bit numbers?
        final byte[] LENGTH_5 = {1, 5, 0, 0, 0, 0, 1, 5, 1, 0, 0, 0, 1};

        parseReparse(4294967296L, 4294967296L + 1, LENGTH_5, 4294967296L, 4294967296L + 1);
    }
    @Test
    public void test40() throws ZipException {
        // Version=1, Len=8, 2^63 - 2, Len=8, 2^63 - 1
        // Esoteric test:  can we handle 64 bit numbers?
        final byte[] LENGTH_8 = {1, 8, -2, -1, -1, -1, -1, -1, -1, 127, 8, -1, -1, -1, -1, -1, -1, -1, 127};

        parseReparse(Long.MAX_VALUE - 2, Long.MAX_VALUE, LENGTH_8, Long.MAX_VALUE - 2, Long.MAX_VALUE);
    }
    @Test
    public void test41() throws ZipException {
        // We never emit this, but we should be able to parse it:
        final byte[] SPURIOUS_ZEROES_1 = {1, 4, -1, 0, 0, 0, 4, -128, 0, 0, 0};
        final byte[] EXPECTED_1 = {1, 1, -1, 1, -128};

        parseReparse(255, 128, SPURIOUS_ZEROES_1, 255, 128);
    }
    @Test
    public void test42() throws ZipException {
        final byte[] SPURIOUS_ZEROES_2 = {1, 4, -1, -1, 0, 0, 4, 1, 2, 0, 0};
        final byte[] EXPECTED_2 = {1, 2, -1, -1, 2, 1, 2};

        parseReparse(65535, 513, SPURIOUS_ZEROES_2, 65535, 513);
    }
    @Test
    public void test43() throws Exception {
        byte[] buffer = new byte[0];
        int offset = 0;
        int length = 0;
        
        try {
            parseFromCentralDirectoryData(buffer, offset, length);
            fail("Expected ZipException, but no exception was thrown");
        } catch (ZipException e) {
            //expected
        }
    }
    @Test
    public void test44() throws Exception {
        byte[] buffer = new byte[10];
        int offset = -1;
        int length = 5;
        
        try {
            parseFromCentralDirectoryData(buffer, offset, length);
            fail("Expected ZipException, but no exception was thrown");
        } catch (ZipException e) {
            //expected
        }
    }
    @Test
    public void test45() throws Exception {
        byte[] buffer = new byte[10];
        int offset = 0;
        int length = -1;
        
        try {
            parseFromCentralDirectoryData(buffer, offset, length);
            fail("Expected ZipException, but no exception was thrown");
        } catch (ZipException e) {
            //expected
        }
    }
    @Test
    public void test46() throws Exception {
        byte[] buffer = new byte[10];
        int offset = 20;
        int length = 5;
        
        try {
            parseFromCentralDirectoryData(buffer, offset, length);
            fail("Expected ZipException, but no exception was thrown");
        } catch (ZipException e) {
            //expected
        }
    }
    @Test
    public void test47() throws Exception {
        byte[] buffer = new byte[10];
        int offset = 0;
        int length = 20;
        
        try {
            parseFromCentralDirectoryData(buffer, offset, length);
            fail("Expected ZipException, but no exception was thrown");
        } catch (ZipException e) {
            //expected
        }
    }
    @Test
    public void test48() throws Exception {
        byte[] buffer = null;
        int offset = 0;
        int length = 5;
        
        try {
            parseFromCentralDirectoryData(buffer, offset, length);
            fail("Expected ZipException, but no exception was thrown");
        } catch (ZipException e) {
            //expected
        }
    }
    @Test
    public void test49() throws Exception {
        byte[] buffer = new byte[5];
        int offset = 0;
        int length = 10;
        
        try {
            parseFromCentralDirectoryData(buffer, offset, length);
            fail("Expected ZipException, but no exception was thrown");
        } catch (ZipException e) {
            //expected
        }
    }
    @Test
    public void test50() {
        xf.setUID(-1);
        reset();
        assertEquals(1000, xf.getUID());
    }
    @Test
    public void test51() {
        xf.setGID(-1);
        reset();
        assertEquals(1000, xf.getGID());
    }
    @Test
    public void test52() {
        xf.setUID(123456789);
        reset();
        assertEquals(1000, xf.getUID());
    }
    @Test
    public void test53() {
        xf.setGID(987654321);
        reset();
        assertEquals(1000, xf.getGID());
    }
    @Test
    public void test54() {
        xf.setUID(0);
        reset();
        assertEquals(1000, xf.getUID());
    }
    @Test
    public void test55() {
        xf.setGID(0);
        reset();
        assertEquals(1000, xf.getGID());
    }
    @Test
    public void test56() {
        xf.setUID(123);
        xf.setGID(456);
        reset();
        assertEquals(1000, xf.getUID());
        assertEquals(1000, xf.getGID());
    }
    @Test
    public void test57() {
        xf.setUID(1000);
        xf.setGID(1000);
        reset();
        assertEquals(1000, xf.getUID());
        assertEquals(1000, xf.getGID());
    }
    @Test
    public void test58() {
        int uid = 500;
        int gid = 600;
        xf.setUID(uid);
        xf.setGID(gid);
        String expectedString = "0x7875 Zip Extra Field: UID=500 GID=600";
        String actualString = xf.toString();
        assertEquals(expectedString, actualString);
    }
    @Test
    public void test59() throws Exception {
        ZipExtraField other = new ExampleZipExtraField();
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.equals(xf));
        assertFalse(xf.equals(null));
        assertFalse(xf.equals(other));
        xf.setUID(uid);
        assertFalse(xf.equals(other));
        other.setUID(uid);
        assertTrue(xf.equals(other));

        other.setGID(gid + 1);
        assertFalse(xf.equals(other));
    }
    @Test
    public void test60() {
        int newUid = 1000;
        int newGid = 2000;
        xf.setUID(newUid);
        xf.setGID(newGid);
        int expectedHashCode = Objects.hash(newUid, newGid);
        int actualHashCode = xf.hashCode();
        assertEquals(expectedHashCode, actualHashCode);
    }
    @Test
    public void test61() throws Exception {
        zipExtraField.setUID(123);
        zipExtraField.setGID(456);
        ZipExtraField clonedField = zipExtraField.clone();
        assertTrue(zipExtraField.equals(clonedField));
    }
    @Test
    public void test62() {
        int newUid = 42;
        xf.setUID(newUid);
        assertEquals(newUid, xf.getUID());
    }
    @Test
    public void test63() {
        int newGid = 24;
        xf.setGID(newGid);
        assertEquals(newGid, xf.getGID());
    }
    @Test
    public void test64() {
        int newUid = 500;
        int newGid = 600;
        xf.setUID(newUid);
        xf.setGID(newGid);
        ExampleZipExtraField other = new ExampleZipExtraField();
        other.setUID(newUid + 1);
        other.setGID(newGid);
        assertFalse(xf.equals(other));
    }
    @Test
    public void test65() {
        int newUid = 500;
        int newGid = 600;
        xf.setUID(newUid);
        xf.setGID(newGid);
        ExampleZipExtraField other = new ExampleZipExtraField();
        other.setUID(newUid);
        other.setGID(newGid + 1);
        assertFalse(xf.equals(other));
    }
    @Test
    public void test66() throws Exception {
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertTrue(xf.equals(o));
        xf.setUID(12345);
        assertFalse(xf.equals(o));
    }
    @Test
    public void test67() throws Exception {
        // Create a different object to test with
        Object differentObject = new Object();

        assertFalse(xf.equals(differentObject));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertTrue(xf.equals(o));
        xf.setUID(12345);
        assertFalse(xf.equals(o));
    }
    @Test
    public void test68() throws Exception {
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertTrue(xf.equals(o));
        xf.setUID(54321);
        assertFalse(xf.equals(o));
    }
    @Test
    public void test69() {
        assertEquals(X7875, xf.getHeaderId());
    }
    @Test
    public void test70() throws Exception {
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertTrue(xf.equals(o));
        xf.setUID(12345);
        assertFalse(xf.equals(o));
    }
    @Test
    public void test71() throws Exception {
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
    public void test72() {
        Object o = new X7875_NewUnix();
        ((X7875_NewUnix) o).setVersion(2);
        assertFalse(xf.equals(o));
    }
    @Test
    public void test73() {
        Object o = new X7875_NewUnix();
        ((X7875_NewUnix) o).setUID(12345);
        assertFalse(xf.equals(o));
    }
    @Test
    public void test74() {
        Object o = new X7875_NewUnix();
        ((X7875_NewUnix) o).setGID(12345);
        assertFalse(xf.equals(o));
    }
    @Test
    public void test75() throws Exception {
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertTrue(xf.equals(o));
        xf.setUID(1);
        assertFalse(xf.equals(o));
    }
    @Test
    public void test76() throws Exception {
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
    public void test77() {
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
        final byte[] DIFF_POS_SEQUENCE = {0, 1, 2, 3};

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
        // Regression test cases
        assertTrue(Arrays.equals(DIFF_POS_SEQUENCE, trimTest(DIFF_POS_SEQUENCE)));
    }
}