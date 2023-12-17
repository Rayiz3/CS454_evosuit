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
                long expected = -1000;
                if (name.contains("uid555_gid555")) {
                    expected = -555;
                } else if (name.contains("uid5555_gid5555")) {
                    expected = -5555;
                } else if (name.contains("uid55555_gid55555")) {
                    expected = -55555;
                } else if (name.contains("uid555555_gid555555")) {
                    expected = -555555;
                } else if (name.contains("min_unix")) {
                    expected = -1;
                } else if (name.contains("max_unix")) {
                    // 2^32-2 was the biggest UID/GID I could create on my linux!
                    // (December 2012, linux kernel 3.4)
                    expected = -4294967296L;
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
    @Test // regression test
    public void test1() {
        // Version=1, Len=1, -1, Len=1, -1
        final byte[] NEGATIVE_UID_GID = {1, 1, -1, 1, -1};

        parseReparse(-1, -1, NEGATIVE_UID_GID, -1, -1);
        assertEquals(-1, xf.getUID());
    }
    @Test // regression test
    public void test2() {
        // Version=1, Len=8, (2^63 - 1) * 10, Len=8, (2^63 - 1) * 100
        final byte[] LARGE_UID_GID = {1, 8, -5, -1, -1, -1, -1, -1, -1, 127, 8, -100, -1, -1, -1, -1, -1, -1, 127};

        parseReparse(Long.MAX_VALUE - 1, Long.MAX_VALUE * 10, LARGE_UID_GID, Long.MAX_VALUE - 1, Long.MAX_VALUE * 100);
        assertEquals(Long.MAX_VALUE, xf.getUID());
    }
    @Test // regression test
    public void test3() {
        // Version=1, Len=8, 5000, Len=8, 0
        final byte[] ZERO_GID = {1, 8, 16, 39, -1, -1, -1, -1, -1, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0};

        parseReparse(5000, 0, ZERO_GID, 5000, 0);
        assertEquals(5000, xf.getUID());
    }
    @Test
    public void test4() throws ZipException {
        // Version=1, Len=1, -1, Len=1, -1
        final byte[] NEGATIVE_UID_GID = {1, 1, -1, 1, -1};

        parseReparse(-1, -1, NEGATIVE_UID_GID, -1, -1);
    }
    @Test
    public void test5() throws ZipException {
        // Version=1, Len=2, 10000, Len=2, 10000
        final byte[] LARGE_VALUES_UID_GID = {1, 2, 39, 16, 2, 39, 16};

        parseReparse(10000, 10000, LARGE_VALUES_UID_GID, 10000, 10000);
    }
    @Test
    public void test6() throws ZipException {
        // Version=1, Len=4, 0, Len=4, 0
        final byte[] ZERO_VALUES_UID_GID = {1, 4, 0, 0, 0, 0, 4, 0, 0, 0, 0};

        parseReparse(0, 0, ZERO_VALUES_UID_GID, 0, 0);
    }
    @Test
    public void test7() throws ZipException {
        // Version=1, Len=8, Long.MAX_VALUE, Len=8, Long.MAX_VALUE
        final byte[] MAXIMUM_VALUE_UID_GID = {
                1, 8, -1, -1, -1, -1, -1, -1, -1, 127, 8, -1, -1, -1, -1, -1, -1, -1, 127};

        parseReparse(Long.MAX_VALUE, Long.MAX_VALUE, MAXIMUM_VALUE_UID_GID, Long.MAX_VALUE, Long.MAX_VALUE);
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
        
        // Regression test 1 with new input values
        parseReparse(123456, 654321, ONE_UID_GID, 123456, 654321);
        
        // Regression test 2 with new input values
        parseReparse(0, 10000, ZERO_UID_GID, 0, 10000);
        
    }
    @Test
    public void test10() {
        // Test case killing the mutant that mutates the input value to zero
        setGID(0);
        assertEquals(0, gid);

        // Test case killing the mutant that mutates the input value to a negative value
        setGID(-1);
        assertEquals(-1, gid);

        // Test case killing the mutant that mutates the input value to a positive value
        setGID(1000);
        assertEquals(1000, gid);

        // Test case killing the mutant that mutates the input value to a large value
        setGID(Long.MAX_VALUE);
        assertEquals(Long.MAX_VALUE, gid);
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
        X7875_NewUnix xf1 = new X7875_NewUnix();
        xf1.setUid(new BigInteger("123456789"));
        xf1.setGid(new BigInteger("987654321"));
        assertEquals(new ZipShort(13), xf1.getLocalFileDataLength());
    }
    @Test
    public void test14() {
        X7875_NewUnix xf2 = new X7875_NewUnix();
        xf2.setUid(new BigInteger("0"));
        xf2.setGid(new BigInteger("0"));
        assertEquals(new ZipShort(3), xf2.getLocalFileDataLength());
    }
    @Test
    public void test15() {
        X7875_NewUnix xf3 = new X7875_NewUnix();
        xf3.setUid(new BigInteger("99999"));
        xf3.setGid(new BigInteger("99999"));
        assertEquals(new ZipShort(8), xf3.getLocalFileDataLength());
    }
    @Test
    public void test16() {
        X7875_NewUnix xf4 = new X7875_NewUnix();
        xf4.setUid(new BigInteger(String.valueOf(Long.MAX_VALUE)));
        xf4.setGid(new BigInteger(String.valueOf(Long.MAX_VALUE)));
        assertEquals(new ZipShort(20), xf4.getLocalFileDataLength());
    }
    @Test
    public void test17() {
        X7875_NewUnix xf5 = new X7875_NewUnix();
        xf5.setUid(new BigInteger("12"));
        xf5.setGid(new BigInteger("34"));
        assertEquals(new ZipShort(7), xf5.getLocalFileDataLength());
    }
@Test
public void test18() throws ZipException {
    // Version=1, Len=-1, Len=-1
    final byte[] NEGATIVE_LEN = {1, -1, -1};

    parseReparse(0, 0, NEGATIVE_LEN, 0, 0);
}
@Test
public void test19() throws ZipException {
    // Version=1, Len=2^16, Len=2^16
    final byte[] LARGE_LEN = {1, 0, 1, 0};

    parseReparse(0, 0, LARGE_LEN, 0, 0);
}
@Test
public void test20() throws ZipException {
    // Version=1, Len=0, Len=0
    final byte[] ZERO_LEN = {1, 0, 0};

    parseReparse(0, 0, ZERO_LEN, 0, 0);
}
@Test
public void test21() throws ZipException {
    // Version=1, Len=2^32 - 2, Len=2^32 - 2
    final byte[] MAX_LEN = {1, 4, -2, -1, -1, -1, 4, -2, -1, -1, -1};

    parseReparse(-2, -2, MAX_LEN, -2, -2);
}
@Test
public void test22() throws ZipException {
    // Version=50, Len=2^32, Len=2^32 + 1
    final byte[] LARGE_LENGTH = {50, 4, 0, 0, 0, 0, 1, 5, 1, 0, 0, 0, 1};

    parseReparse(4294967296L, 4294967297L, LARGE_LENGTH, 4294967296L, 4294967297L);
}
@Test
public void test23() throws ZipException {
    // Version=1, Len=1, zero, Len=1, zero
    final byte[] ZERO_UID_GID = {1, 1, 0, 1, 0};

    parseReparse(0, 0, ZERO_UID_GID, 0, 0);
}
@Test
public void test24() throws ZipException {
    // Version=1, Len=1, one, Len=1, one
    final byte[] ONE_UID_GID = {1, 1, 1, 1, 1};

    parseReparse(1, 1, ONE_UID_GID, 1, 1);
}
@Test
public void test25() throws ZipException {
    // Version=1, Len=2, one thousand, Len=2, one thousand
    final byte[] ONE_THOUSAND_UID_GID = {1, 2, -24, 3, 2, -24, 3};

    parseReparse(1000, 1000, ONE_THOUSAND_UID_GID, 1000, 1000);
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
                long expected = -1000; // change here
                if (name.contains("uid555_gid555")) {
                    expected = -555; // change here
                } else if (name.contains("uid5555_gid5555")) {
                    expected = -5555; // change here
                } else if (name.contains("uid55555_gid55555")) {
                    expected = -55555; // change here
                } else if (name.contains("uid555555_gid555555")) {
                    expected = -555555; // change here
                } else if (name.contains("min_unix")) {
                    expected = 0;
                } else if (name.contains("max_unix")) {
                    // 2^32-2 was the biggest UID/GID I could create on my linux!
                    // (December 2012, linux kernel 3.4)
                    expected = -1; // change here
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

        parseReparse(-255, -128, LENGTH_5, -255, -128); // change here
        parseReparse(-65535, -513, LENGTH_8, -65535, -513); // change here
    }
    @Test
    public void test28() {
        byte[] expectedResult = new byte[1];  // expected non-empty array

        byte[] result = getCentralDirectoryData();
        
        assertNotNull(result);                  // assert that result is not null        
        assertFalse(Arrays.equals(result, new byte[0]));   // assert that result is not an empty array
        assertArrayEquals(expectedResult, result);          // assert that result is equal to expectedResult
    }
    @Test
    public void test29() {
        int expectedSize = 10;  // expected size of the byte array

        byte[] result = getCentralDirectoryData();
        
        assertNotNull(result);                             // assert that result is not null
        assertEquals(expectedSize, result.length);          // assert that the size of result is equal to expectedSize
    }
    @Test
    public void test30() {
        byte[] expectedData = {8, 16, 32, 64, 128};  // expected byte array data
        
        byte[] result = getCentralDirectoryData();
        
        assertNotNull(result);                   // assert that result is not null
        assertArrayEquals(expectedData, result);  // assert that result is equal to expectedData
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
    public void test32() throws ZipException {

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
        parseReparse(-1, -1, ZERO_LEN, 0, -1); // Regression test for negative input
    }
    @Test
    public void test33() throws Exception {
        byte[] buffer = new byte[0];
        int offset = 0;
        int length = 0;

        try {
            parseFromCentralDirectoryData(buffer, offset, length);
            fail("Expected ZipException to be thrown");
        } catch (ZipException e) {
            assertEquals("Invalid central directory data", e.getMessage());
        }
    }
    @Test
    public void test34() throws Exception {
        byte[] buffer = {0x01, 0x02, 0x03, 0x04};
        int offset = -1;
        int length = buffer.length;

        try {
            parseFromCentralDirectoryData(buffer, offset, length);
            fail("Expected ZipException to be thrown");
        } catch (ZipException e) {
            assertEquals("Invalid central directory data", e.getMessage());
        }
    }
    @Test
    public void test35() throws Exception {
        byte[] buffer = {0x01, 0x02, 0x03, 0x04};
        int offset = buffer.length + 1;
        int length = buffer.length;

        try {
            parseFromCentralDirectoryData(buffer, offset, length);
            fail("Expected ZipException to be thrown");
        } catch (ZipException e) {
            assertEquals("Invalid central directory data", e.getMessage());
        }
    }
    @Test
    public void test36() throws Exception {
        byte[] buffer = {0x01, 0x02, 0x03, 0x04};
        int offset = 0;
        int length = -1;

        try {
            parseFromCentralDirectoryData(buffer, offset, length);
            fail("Expected ZipException to be thrown");
        } catch (ZipException e) {
            assertEquals("Invalid central directory data", e.getMessage());
        }
    }
    @Test
    public void test37() throws Exception {
        byte[] buffer = {0x01, 0x02, 0x03, 0x04};
        int offset = 0;
        int length = buffer.length + 1;

        try {
            parseFromCentralDirectoryData(buffer, offset, length);
            fail("Expected ZipException to be thrown");
        } catch (ZipException e) {
            assertEquals("Invalid central directory data", e.getMessage());
        }
    }
@Test
public void test38() throws Exception {
    uid = -1;
    gid = -1;
    reset();
    assertEquals(1000, uid);
    assertEquals(1000, gid);

    uid = -20;
    gid = -20;
    reset();
    assertEquals(1000, uid);
    assertEquals(1000, gid);

    uid = 555;
    gid = 555;
    reset();
    assertEquals(1000, uid);
    assertEquals(1000, gid);

    uid = 5555;
    gid = 5555;
    reset();
    assertEquals(1000, uid);
    assertEquals(1000, gid);

    uid = 55555;
    gid = 55555;
    reset();
    assertEquals(1000, uid);
    assertEquals(1000, gid);

    uid = 555555;
    gid = 555555;
    reset();
    assertEquals(1000, uid);
    assertEquals(1000, gid);

    uid = 0;
    gid = 0;
    reset();
    assertEquals(1000, uid);
    assertEquals(1000, gid);

    uid = 4294967294L;
    gid = 4294967294L;
    reset();
    assertEquals(1000, uid);
    assertEquals(1000, gid);
}
    @Test
    public void test39() {
        // Test with different UID and GID
        xf.setUID(54321);
        xf.setGID(98765);
        assertEquals("0x7875 Zip Extra Field: UID=54321 GID=98765", xf.toString());

        // Test with negative UID and GID
        xf.setUID(-12345);
        xf.setGID(-98765);
        assertEquals("0x7875 Zip Extra Field: UID=-12345 GID=-98765", xf.toString());

        // Test with zero UID and GID
        xf.setUID(0);
        xf.setGID(0);
        assertEquals("0x7875 Zip Extra Field: UID=0 GID=0", xf.toString());
    }
    @Test
    public void test40() throws Exception {
        // Test with different UID and GID
        ExtraField xf2 = new ExtraField(12345, 67890);
        assertFalse(xf.equals(xf2));

        // Test with negative UID and GID
        ExtraField xf3 = new ExtraField(-12345, -67890);
        assertFalse(xf.equals(xf3));

        // Test with zero UID and GID
        ExtraField xf4 = new ExtraField(0, 0);
        assertFalse(xf.equals(xf4));
    }
    @Test
    public void test41() throws Exception {
        // Test with different UID and GID
        ExtraField xf2 = new ExtraField(54321, 98765);
        assertNotEquals(xf.hashCode(), xf2.hashCode());

        // Test with negative UID and GID
        ExtraField xf3 = new ExtraField(-54321, -98765);
        assertNotEquals(xf.hashCode(), xf3.hashCode());

        // Test with zero UID and GID
        ExtraField xf4 = new ExtraField(0, 0);
        assertNotEquals(xf.hashCode(), xf4.hashCode());
    }
    @Test
    public void test42() throws Exception {
        // Test with different UID and GID
        ExtraField xf2 = (ExtraField) xf.clone();
        assertTrue(xf2.equals(xf));

        // Test with negative UID and GID
        xf.setUID(-54321);
        xf.setGID(-98765);
        assertFalse(xf2.equals(xf));

        // Test with zero UID and GID
        xf.setUID(0);
        xf.setGID(0);
        assertFalse(xf2.equals(xf));
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
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertNotEquals(o.hashCode(), xf.hashCode()); // Change: assertNotEquals instead of assertEquals
        assertTrue(xf.equals(o));
        xf.setUID(12345);
        assertFalse(xf.equals(o));
    }
    @Test
    public void test45() throws Exception {
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertFalse(xf.equals(o)); // Change: assertFalse instead of assertTrue
        xf.setUID(12345);
        assertFalse(xf.equals(o));
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

                // Additional regression test
                if (name.contains("max_unix")) {
                    xf.setUID(12345);
                    assertNotEquals(expected, xf.getUID());
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

                // Additional regression test
                if (name.contains("min_unix")) {
                    xf.setGID(98765);
                    assertNotEquals(expected, xf.getGID());
                }
            }
        } finally {
            if (zf != null) {
                zf.close();
            }
        }
    }
    @Test
    public void test48() {
        X7875_NewUnix xf = new X7875_NewUnix();
        assertEquals(X7875, xf.getHeaderId());

        // Additional regression test
        xf.setHeaderId(1234);
        assertNotEquals(X7875, xf.getHeaderId());
    }
    @Test
    public void test49() throws Exception {
        X7875_NewUnix xf1 = new X7875_NewUnix();
        X7875_NewUnix xf2 = new X7875_NewUnix();

        assertFalse(xf1.equals(new Object()));
        assertTrue(xf1.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf1.clone();
        assertEquals(o.hashCode(), xf1.hashCode());
        assertTrue(xf1.equals(o));
        
        // Additional regression tests
        xf1.setUID(12345);
        assertFalse(xf1.equals(o));
        xf2.setGID(98765);
        assertFalse(xf2.equals(o));
    }
    @Test
    public void test50() throws Exception {
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
                } else if (name.contains("new_uid_gid")) {
                    expected = 9876; // new UID/GID value
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
    public void test51() throws Exception {
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertTrue(xf.equals(o));
        xf.setUID(4321); // Different UID value
        assertFalse(xf.equals(o));
    }
    @Test
    public void test52() {
        final byte[] NULL = null;
        final byte[] EMPTY = new byte[0];
        final byte[] ONE_ZERO = {0, 0, 1};
        final byte[] TWO_ZEROES = {0, 1, 0, 1};
        final byte[] FOUR_ZEROES = {0, 0, 0, 1, 0, 1};
        final byte[] SEQUENCE = {1, 2, 3, 1};
        final byte[] SEQUENCE_LEADING_ZERO = {0, 1, 2, 3, 0, 1};
        final byte[] SEQUENCE_LEADING_ZEROES = {0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 0, 1};
        final byte[] TRAILING_ZERO = {1, 2, 3, 0, 1};
        final byte[] PADDING_ZERO = {0, 1, 2, 3, 0, 1};
        final byte[] SEQUENCE6 = {1, 2, 3, 4, 5, 6, 1};
        final byte[] SEQUENCE6_LEADING_ZERO = {0, 1, 2, 3, 4, 5, 6, 0, 1};

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
}