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
    assertNotEquals(X7875, xf.getHeaderId());
}
@Test
public void test1() throws Exception {
    File archive = getFile("COMPRESS-211_uid_gid_zip_test_invalid.zip");
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
            long expected = 555;
            if (name.contains("uid555_gid555")) {
                expected = 5555;
            } else if (name.contains("uid5555_gid5555")) {
                expected = 55555;
            } else if (name.contains("uid55555_gid55555")) {
                expected = 555555;
            } else if (name.contains("uid555555_gid555555")) {
                expected = 5555555;
            } else if (name.contains("min_unix")) {
                expected = 1;
            } else if (name.contains("max_unix")) {
                // 2^32-2 was the biggest UID/GID I could create on my linux!
                // (December 2012, linux kernel 3.4)
                expected = 0x100000000L - 1;
            }
            assertNotEquals(expected, xf.getUID());
            assertNotEquals(expected, xf.getGID());
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

        parseReparse(0, 0, ZERO_LEN, 0, 0);
        parseReparse(0, 0, ZERO_UID_GID, 0, 0);

        // Changed input value: from 1 to 5
        parseReparse(5, 5, ONE_UID_GID, 5, 5);

        parseReparse(1000, 1000, ONE_THOUSAND_UID_GID, 1000, 1000);

        // Changed input value: from MAX to -2
        parseReparse(-2, -2, UNIX_MAX_UID_GID, MAX, MAX);

        // Changed input value: from TWO_TO_32 to 10
        parseReparse(10, 11, LENGTH_5, TWO_TO_32, TWO_TO_32 + 1);
      
        // Changed input value: from Long.MAX_VALUE - 1 to 1
        parseReparse(1, 1, LENGTH_8, Long.MAX_VALUE - 1, Long.MAX_VALUE);

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
    public void test3() throws ZipException {
        // Version=1, Len=3, 2^32+1, Len=3, 2^32+1
        final byte[] LEN_3 = {1, 3, 0, 1, 0, 1, 3};
        parseReparse(TWO_TO_32 + 1, TWO_TO_32 + 1, LEN_3, TWO_TO_32 + 1, TWO_TO_32 + 1);
    }
    @Test
    public void test4() throws ZipException {
        // Version=1, Len=5, -2^31, Len=5, -2^31
        final byte[] NEGATIVE_LEN_5 = {1, 5, 0, 0, 0, -128, 5, 0, 0, 0, -128};
        parseReparse(Integer.MIN_VALUE, Integer.MIN_VALUE, NEGATIVE_LEN_5, Integer.MIN_VALUE, Integer.MIN_VALUE);
    }
    @Test
    public void test5() throws ZipException {
        // Version=1, Len=8, -2^63, Len=8, -2^63
        final byte[] NEGATIVE_LEN_8 = {1, 8, -128, 0, 0, 0, 0, 0, 0, 127, 8, -128, 0, 0, 0, 0, 0, 0, 127};
        parseReparse(Long.MIN_VALUE, Long.MIN_VALUE, NEGATIVE_LEN_8, Long.MIN_VALUE, Long.MIN_VALUE);
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
                
                // Mutate expected values
                long expected = 0;
                if (name.contains("uid555_gid555")) {
                    expected = 1;
                } else if (name.contains("uid5555_gid5555")) {
                    expected = 2;
                } else if (name.contains("uid55555_gid55555")) {
                    expected = 3;
                } else if (name.contains("uid555555_gid555555")) {
                    expected = 4;
                } else if (name.contains("min_unix")) {
                    expected = 5;
                } else if (name.contains("max_unix")) {
                    expected = 6;
                }
                X7875_NewUnix xf = (X7875_NewUnix) zae.getExtraField(X7875);
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
        parseReparse(0, 0, ZERO_LEN, 0, 0);
        
        // Version=1, Len=1, zero, Len=1, zero.
        final byte[] ZERO_UID_GID = {1, 1, 0, 1, 0};
        parseReparse(0, 0, ZERO_UID_GID, 0, 0);
        
        // Version=1, Len=1, one, Len=1, one
        final byte[] ONE_UID_GID = {1, 1, 1, 1, 1};
        parseReparse(1, 1, ONE_UID_GID, 1, 1);
        
        // Version=1, Len=2, one thousand, Len=2, one thousand
        final byte[] ONE_THOUSAND_UID_GID = {1, 2, -24, 3, 2, -24, 3};
        parseReparse(1000, 1000, ONE_THOUSAND_UID_GID, 1000, 1000);
        
        // (2^32 - 2)
        final byte[] UNIX_MAX_UID_GID = {1, 4, -2, -1, -1, -1, 4, -2, -1, -1, -1};
        parseReparse((long) Math.pow(2, 32) - 2, (long) Math.pow(2, 32) - 2, UNIX_MAX_UID_GID, (long) Math.pow(2, 32) - 2, (long) Math.pow(2, 32) - 2);
        
        // Version=1, Len=5, 2^32, Len=5, 2^32 + 1
        final byte[] LENGTH_5 = {1, 5, 0, 0, 0, 0, 1, 5, 1, 0, 0, 0, 1};
        parseReparse((long) Math.pow(2, 32), (long) Math.pow(2, 32) + 1, LENGTH_5, (long) Math.pow(2, 32), (long) Math.pow(2, 32) + 1);
        
        // Version=1, Len=8, 2^63 - 2, Len=8, 2^63 - 1
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
    public void test9() throws ZipException {
        final byte[] ZERO_LEN = {1, 0, 0};
        parseReparse(100, 0, ZERO_LEN, 100, 0);
    }
    @Test
    public void test10() throws ZipException {
        final byte[] ONE_UID_GID = {1, 1, 1, 1, 1};
        parseReparse(0, 100, ONE_UID_GID, 0, 100);
    }
    @Test
    public void test11() throws ZipException {
        final byte[] ONE_THOUSAND_UID_GID = {1, 2, -24, 3, 2, -24, 3};
        parseReparse(500, 500, ONE_THOUSAND_UID_GID, 500, 500);
    }
    @Test
    public void test12() throws ZipException {
        final byte[] UNIX_MAX_UID_GID = {1, 4, -2, -1, -1, -1, 4, -2, -1, -1, -1};
        parseReparse(-1, -1, UNIX_MAX_UID_GID, -1, -1);
    }
    @Test
    public void test13() throws ZipException {
        final byte[] LENGTH_5 = {1, 5, 0, 0, 0, 0, 1};
        parseReparse(Long.MAX_VALUE, 0, LENGTH_5, Long.MAX_VALUE, 0);
    }
    @Test
    public void test14() throws ZipException {
        final byte[] LENGTH_8 = {1, 8, -2, -1, -1, -1, -1, -1, -1};
        parseReparse(0, Long.MAX_VALUE, LENGTH_8, 0, Long.MAX_VALUE);
    }
    @Test
    public void test15() throws ZipException {
        final byte[] SPURIOUS_ZEROES_1 = {1, 4, -1, 0, 0, 0, 4, -128, 0, 0, 0};
        final byte[] EXPECTED_1 = {1, 1, -1, 1, -128};
        parseReparse(255, 128, SPURIOUS_ZEROES_1, 255, 128);
        assertEquals(255, xf.getUID());
        assertEquals(128, xf.getGID());
        assertTrue(Arrays.equals(EXPECTED_1, xf.getLocalFileDataData()));
    }
    @Test
    public void test16() throws ZipException {
        final byte[] SPURIOUS_ZEROES_2 = {1, 4, -1, -1, 0, 0, 4, 1, 2, 0, 0};
        final byte[] EXPECTED_2 = {1, 2, -1, -1, 2, 1, 2};
        parseReparse(65535, 513, SPURIOUS_ZEROES_2, 65535, 513);
        assertEquals(65535, xf.getUID());
        assertEquals(513, xf.getGID());
        assertTrue(Arrays.equals(EXPECTED_2, xf.getLocalFileDataData()));
    }
    @Test
    public void test17() {
        // Test case with uidSize = 0, gidSize = 0
        uid = new BigInteger("0");
        gid = new BigInteger("0");
        ZipShort expected1 = new ZipShort(3);
        assertEquals(expected1, getLocalFileDataLength());

        // Test case with uidSize = 1, gidSize = 0
        uid = new BigInteger("9");
        gid = new BigInteger("0");
        ZipShort expected2 = new ZipShort(4);
        assertEquals(expected2, getLocalFileDataLength());

        // Test case with uidSize = 0, gidSize = 2
        uid = new BigInteger("0");
        gid = new BigInteger("99");
        ZipShort expected3 = new ZipShort(5);
        assertEquals(expected3, getLocalFileDataLength());

        // Test case with uidSize = 3, gidSize = 4
        uid = new BigInteger("888");
        gid = new BigInteger("5555");
        ZipShort expected4 = new ZipShort(13);
        assertEquals(expected4, getLocalFileDataLength());
    }
    @Test
    public void test18() {
        // Test case with negative uid and gid values
        uid = new BigInteger("-555");
        gid = new BigInteger("-555");
        ZipShort expected = new ZipShort(7);
        assertEquals(expected, getLocalFileDataLength());
    }
    @Test
    public void test19() {
        // Test case with maximum uid and gid values
        uid = new BigInteger("4294967294");
        gid = new BigInteger("4294967294");
        ZipShort expected = new ZipShort(15);
        assertEquals(expected, getLocalFileDataLength());
    }
    @Test
    public void test20() {
        // Test case with large uid and gid values
        uid = new BigInteger("1234567890");
        gid = new BigInteger("9876543210");
        ZipShort expected = new ZipShort(21);
        assertEquals(expected, getLocalFileDataLength());
    }
    @Test
    public void test21() throws ZipException {
        final byte[] ZERO_LEN = {-1, 0, 0};

        parseReparse(-1, 0, ZERO_LEN, 0, 0);
    }
    @Test
    public void test22() throws ZipException {
        final byte[] LENGTH_8 = {1, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        
        parseReparse(Long.MAX_VALUE + 1, 0, LENGTH_8, Long.MAX_VALUE + 1, 0);
    }
    @Test
    public void test23() throws ZipException {
        final byte[] ZERO_LEN = {0, 0, 0};

        parseReparse(0, 0, ZERO_LEN, 0, 0);
    }
    @Test
    public void test24() throws ZipException {

        final byte[] LENGTH_8 = {1, 8, -1, -1, -1, -1, -1, -1, -1, 127, -1, -1, -1, -1, -1, -1, -1, 127};

        parseReparse(Long.MAX_VALUE, Long.MAX_VALUE, LENGTH_8, Long.MAX_VALUE, Long.MAX_VALUE);
    }
    @Test
    public void test25() throws ZipException {
        final byte[] ONE_UID_GID = {-128, 1, -128, 1, 1};

        parseReparse(-128, 1, ONE_UID_GID, 128, 1);
    }
@Test
public void test26() throws Exception {
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
            } else if (name.contains("negative_uid_gid")) {
                expected = -1;
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
public void test27() throws ZipException {

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

    // Regression test with negative values of uid and gid
    final byte[] NEGATIVE_UID_GID = {1, 2, -1, -1, -1, -1, 1, 2, -1, -1, -1};

    parseReparse(-1, -1, NEGATIVE_UID_GID, -1, -1);
}
    @Test
    public void test28() throws Exception {
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
    public void test33() throws ZipException {

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
    public void test34() throws Exception {
        byte[] buffer = null;
        int offset = 0;
        int length = 10;
        
        try {
            parseFromCentralDirectoryData(buffer, offset, length);
            fail("Expected ZipException to be thrown");
        } catch (ZipException e) {
            assertEquals("Invalid buffer", e.getMessage());
        }
    }
    @Test
    public void test35() throws Exception {
        byte[] buffer = new byte[10];
        int offset = -1;
        int length = 10;
        
        try {
            parseFromCentralDirectoryData(buffer, offset, length);
            fail("Expected ZipException to be thrown");
        } catch (ZipException e) {
            assertEquals("Invalid offset", e.getMessage());
        }
    }
    @Test
    public void test36() throws Exception {
        byte[] buffer = new byte[10];
        int offset = 0;
        int length = -1;
        
        try {
            parseFromCentralDirectoryData(buffer, offset, length);
            fail("Expected ZipException to be thrown");
        } catch (ZipException e) {
            assertEquals("Invalid length", e.getMessage());
        }
    }
    @Test
    public void test37() throws Exception {
        byte[] buffer = new byte[10];
        int offset = 20;
        int length = 5;
        
        try {
            parseFromCentralDirectoryData(buffer, offset, length);
            fail("Expected ZipException to be thrown");
        } catch (ZipException e) {
            assertEquals("Offset is out of range", e.getMessage());
        }
    }
    @Test
    public void test38() throws Exception {
        byte[] buffer = new byte[10];
        int offset = 0;
        int length = 15;
        
        try {
            parseFromCentralDirectoryData(buffer, offset, length);
            fail("Expected ZipException to be thrown");
        } catch (ZipException e) {
            assertEquals("Length is out of range", e.getMessage());
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
    public void test42() throws Exception {
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
    public void test43() throws ZipException {

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
    public void test44() {
        xf.reset();
        assertEquals(1000, xf.getUID());
        assertEquals(1000, xf.getGID());
    }
    @Test
    public void test45() {
        xf.setUID(123);
        xf.reset();
        assertEquals(1000, xf.getUID());
        assertEquals(1000, xf.getGID());
    }
    @Test
    public void test46() {
        xf.setGID(456);
        xf.reset();
        assertEquals(1000, xf.getUID());
        assertEquals(1000, xf.getGID());
    }
    @Test
    public void test47() {
        xf.reset();
        xf.setUID(123);
        xf.reset();
        xf.setGID(456);
        xf.reset();
        assertEquals(1000, xf.getUID());
        assertEquals(1000, xf.getGID());
    }
    @Test
    public void test48() {
        xf.setUID(0x100000000L - 2);
        xf.setGID(0x100000000L - 2);
        xf.reset();
        assertEquals(1000, xf.getUID());
        assertEquals(1000, xf.getGID());
    }
@Test
public void test49() {
    xf.setUID(54321);
    String expected = "0x7875 Zip Extra Field: UID=54321 GID=4321";
    assertEquals(expected, xf.toString());
}
@Test
public void test50() {
    xf.setGID(54321);
    String expected = "0x7875 Zip Extra Field: UID=1234 GID=54321";
    assertEquals(expected, xf.toString());
}
@Test
public void test51() {
    xf.setUID(54321);
    xf.setGID(54321);
    String expected = "0x7875 Zip Extra Field: UID=54321 GID=54321";
    assertEquals(expected, xf.toString());
}
@Test
public void test52() throws Exception {
    xf.setUID(54321);
    assertFalse(xf.equals(new Object()));
}
@Test
public void test53() throws Exception {
    xf.setGID(54321);
    assertFalse(xf.equals(new Object()));
}
@Test
public void test54() throws Exception {
    xf.setUID(54321);
    xf.setGID(54321);
    assertFalse(xf.equals(new Object()));
}
@Test
public void test55() throws Exception {
    xf.setUID(54321);
    Object o = xf.clone();
    assertNotEquals(o.hashCode(), xf.hashCode());
    assertFalse(xf.equals(o));
}
@Test
public void test56() throws Exception {
    xf.setGID(54321);
    Object o = xf.clone();
    assertNotEquals(o.hashCode(), xf.hashCode());
    assertFalse(xf.equals(o));
}
@Test
public void test57() throws Exception {
    xf.setUID(54321);
    xf.setGID(54321);
    Object o = xf.clone();
    assertNotEquals(o.hashCode(), xf.hashCode());
    assertFalse(xf.equals(o));
}
@Test
public void test58() throws Exception {
    xf.setUID(54321);
    xf.setUID(98765);
    Object o = xf.clone();
    assertEquals(o.hashCode(), xf.hashCode());
    assertTrue(xf.equals(o));
}
@Test
public void test59() throws Exception {
    xf.setGID(54321);
    xf.setGID(98765);
    Object o = xf.clone();
    assertEquals(o.hashCode(), xf.hashCode());
    assertTrue(xf.equals(o));
}
@Test
public void test60() throws Exception {
    xf.setUID(54321);
    xf.setGID(54321);
    xf.setUID(98765);
    xf.setGID(98765);
    Object o = xf.clone();
    assertEquals(o.hashCode(), xf.hashCode());
    assertTrue(xf.equals(o));
}
    @Test
    public void test61() throws Exception {
        // Create a clone of xf
        Object o = xf.clone();
        
        // Assert that the cloned object is not the same instance as xf
        assertNotSame(o, xf);
    }
    @Test
    public void test62() throws Exception {
        // Create a clone of xf
        Object o = xf.clone();
        
        // Assert that the cloned object is equal to xf
        assertTrue(xf.equals(o));
    }
    @Test
    public void test63() throws Exception {
        // Create a clone of xf
        Object o = xf.clone();
        
        // Assert that the hash code of the cloned object is the same as xf
        assertEquals(xf.hashCode(), o.hashCode());
    }
    @Test
    public void test64() throws Exception {
        // Create a clone of xf
        Object o = xf.clone();
        
        // Modify xf by setting a different UID
        xf.setUID(12345);
        
        // Assert that the modified xf is not equal to the cloned object
        assertFalse(o.equals(xf));
    }
    @Test
    public void test65() {
        X7875_NewUnix xf1 = new X7875_NewUnix();
        xf1.setVersion(1);

        X7875_NewUnix xf2 = new X7875_NewUnix();
        xf2.setVersion(2);

        assertFalse(xf1.equals(xf2));
    }
    @Test
    public void test66() {
        X7875_NewUnix xf1 = new X7875_NewUnix();
        xf1.setUid("uid1");

        X7875_NewUnix xf2 = new X7875_NewUnix();
        xf2.setUid("uid2");

        assertFalse(xf1.equals(xf2));
    }
    @Test
    public void test67() {
        X7875_NewUnix xf1 = new X7875_NewUnix();
        xf1.setGid("gid1");

        X7875_NewUnix xf2 = new X7875_NewUnix();
        xf2.setGid("gid2");

        assertFalse(xf1.equals(xf2));
    }
    @Test
    public void test68() {
        X7875_NewUnix xf1 = new X7875_NewUnix();
        xf1.setVersion(1);
        xf1.setUid("uid1");
        xf1.setGid("gid1");

        assertFalse(xf1.equals(new Object()));
    }
    @Test
    public void test69() {
        X7875_NewUnix xf1 = new X7875_NewUnix();
        xf1.setVersion(1);
        xf1.setUid("uid1");
        xf1.setGid("gid1");

        X7875_NewUnix xf2 = new X7875_NewUnix();
        xf2.setVersion(1);
        xf2.setUid("uid1");
        xf2.setGid("gid1");

        assertEquals(xf1.hashCode(), xf2.hashCode());
    }
    @Test
    public void test70() throws CloneNotSupportedException {
        X7875_NewUnix xf1 = new X7875_NewUnix();
        xf1.setVersion(1);
        xf1.setUid("uid1");
        xf1.setGid("gid1");

        X7875_NewUnix xf2 = (X7875_NewUnix) xf1.clone();

        assertTrue(xf1.equals(xf2));
    }
    @Test
    public void test71() throws Exception {
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertTrue(xf.equals(o));
        xf.setUID(12345);
        assertFalse(xf.equals(o));

        // Regression Test Case 1
        assertEquals(0, xf.hashCode());
        xf.setUID(0);
        assertEquals(-1578753183, xf.hashCode());

        // Regression Test Case 2
        xf.setUID(-1);
        assertEquals(-2147418112, xf.hashCode());

        // Regression Test Case 3
        xf.setUID(Integer.MAX_VALUE);
        assertEquals(-2147516824, xf.hashCode());
    }
    @Test
    public void test72() throws Exception {
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

                    // Regression Test Case 4
                    expected = -1;
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
    public void test73() {
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
        final byte[] SEQUENCE7_LEADING_ZERO = {0, 1, 2, 3, 4, 5, 6, 7};
      
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
        assertTrue(Arrays.equals(SEQUENCE7_LEADING_ZERO, trimTest(SEQUENCE7_LEADING_ZERO)));
    }
}