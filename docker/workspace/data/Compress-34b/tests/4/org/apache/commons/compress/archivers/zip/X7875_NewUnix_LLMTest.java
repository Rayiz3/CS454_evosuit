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
            }
        } finally {
            if (zf != null) {
                zf.close();
            }
        }
    }
    @Test
    public void test2() {
        // Change the expected header id to an invalid value
        assertEquals(X7875 + 1, xf.getHeaderId());
    }
    @Test
    public void test3() throws Exception {
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
                // Change the expected UID and GID to invalid values
                assertEquals(expected + 1, xf.getUID());
                assertEquals(expected - 1, xf.getGID());
            }
        } finally {
            if (zf != null) {
                zf.close();
            }
        }
    }
    @Test
    public void test4() {
        // Set the header id to null
        xf.setHeaderId(null);
        assertNull(xf.getHeaderId());
    }
    @Test
    public void test5() throws Exception {
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
                // Set the UID and GID to null
                xf.setUID(null);
                xf.setGID(null);
                assertNull(xf.getUID());
                assertNull(xf.getGID());
            }
        } finally {
            if (zf != null) {
                zf.close();
            }
        }
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
        parseReparse(-100, -100, ZERO_LEN, -100, -100);
        parseReparse(555, 555, ZERO_UID_GID, 555, 555);
        parseReparse(-555, -555, ONE_UID_GID, -555, -555);
        parseReparse(5555, 5555, ONE_UID_GID, 5555, 5555);
        parseReparse(-5555, -5555, ONE_THOUSAND_UID_GID, -5555, -5555);
        parseReparse(555555, 555555, ONE_THOUSAND_UID_GID, 555555, 555555);
        parseReparse(-555555, -555555, UNIX_MAX_UID_GID, -555555, -555555);
        parseReparse(5555555, 5555555, UNIX_MAX_UID_GID, 5555555, 5555555);
        parseReparse(-5555555, -5555555, LENGTH_5, -5555555, -5555555);
        parseReparse(55555555, 55555555, LENGTH_5, 55555555, 55555555);
    }
    @Test
    public void test7() throws ZipException {
        final byte[] ZERO_UID_GID = {1, 1, 0, 1, 0};
        parseReparse(0, 0, ZERO_UID_GID, 0, 0);
        parseReparse(-1, -1, ZERO_UID_GID, -1, -1);
    }
    @Test
    public void test8() throws ZipException {
        final byte[] ZERO_LEN = {1, 0, 0};
        parseReparse(0, 0, ZERO_LEN, 0 ,0);
        parseReparse(-1, -1, ZERO_LEN, -1 , -1);
    }
    @Test
    public void test9() throws ZipException {
        final byte[] UNIX_MAX_UID_GID = {1, 4, -2, -1, -1, -1, 4, -2, -1, -1, -1};
        final long MAX = 4294967294L;
        parseReparse(MAX, MAX, UNIX_MAX_UID_GID, MAX, MAX);
        parseReparse(-2, -2, UNIX_MAX_UID_GID, MAX, MAX);
    }
    @Test
    public void test10() throws ZipException {
        final byte[] LENGTH_5 = {1, 5, 0, 0, 0, 0, 1, 5, 1, 0, 0, 0, 1};
        final long TWO_TO_32 = 4294967296L;
        parseReparse(TWO_TO_32, TWO_TO_32 + 1, LENGTH_5, TWO_TO_32, TWO_TO_32 + 1);
    }
    @Test
    public void test11() throws ZipException {
        final byte[] LENGTH_8 = {1, 8, -2, -1, -1, -1, -1, -1, -1, 127, 8, -1, -1, -1, -1, -1, -1, -1, 127};
        final long MAX_VALUE = 9223372036854775806L;
        parseReparse(MAX_VALUE - 1, MAX_VALUE, LENGTH_8, MAX_VALUE - 1, MAX_VALUE);
    }
    @Test
    public void test12() throws ZipException {
        final byte[] SPURIOUS_ZEROES_1 = {1, 4, -1, 0, 0, 0, 4, -128, 0, 0, 0};
        final byte[] EXPECTED_1 = {1, 1, -1, 1, -128};
        parseReparse(255, 128, SPURIOUS_ZEROES_1, 255, 128, EXPECTED_1, 2, 3);

        final byte[] SPURIOUS_ZEROES_2 = {1, 4, -1, -1, 0, 0, 4, 1, 2, 0, 0};
        final byte[] EXPECTED_2 = {1, 2, -1, -1, 2, 1, 2};
        parseReparse(65535, 513, SPURIOUS_ZEROES_2, 65535, 513, EXPECTED_2, 2, 3);
    }
    @Test
    public void test13() {
        xf.setUID(-1);
        assertEquals(-1, xf.getUID());
    }
@Test
public void test14() {
    X7875_NewUnix xf = new X7875_NewUnix();

    xf.setUid(new ZipLong(0));
    xf.setGid(new ZipLong(0));

    ZipShort expected = new ZipShort(3);
    assertEquals(expected, xf.getLocalFileDataLength());
}
@Test
public void test15() {
    X7875_NewUnix xf = new X7875_NewUnix();

    xf.setUid(new ZipLong(4294967294L));
    xf.setGid(new ZipLong(4294967294L));

    ZipShort expected = new ZipShort(11);
    assertEquals(expected, xf.getLocalFileDataLength());
}
@Test
public void test16() {
    X7875_NewUnix xf = new X7875_NewUnix();

    xf.setUid(new ZipLong(1));
    xf.setGid(new ZipLong(1));

    ZipShort expected = new ZipShort(5);
    assertEquals(expected, xf.getLocalFileDataLength());
}
@Test
public void test17() {
    X7875_NewUnix xf = new X7875_NewUnix();

    xf.setUid(new ZipLong(9223372036854775806L));
    xf.setGid(new ZipLong(9223372036854775806L));

    ZipShort expected = new ZipShort(19);
    assertEquals(expected, xf.getLocalFileDataLength());
}
@Test
public void test18() {
    X7875_NewUnix xf = new X7875_NewUnix();

    xf.setUid(new ZipLong(-2));
    xf.setGid(new ZipLong(-2));

    ZipShort expected = new ZipShort(11);
    assertEquals(expected, xf.getLocalFileDataLength());
}
    @Test
    public void test19() throws ZipException {
        // Regression test with zero length input
        final byte[] ZERO_LEN = {1, 0, 0};
        parseReparse(0, 0, ZERO_LEN, 0, 0);
    }
    @Test
    public void test20() throws ZipException {
        // Regression test with zero UID and GID
        final byte[] ZERO_UID_GID = {1, 1, 0, 1, 0};
        parseReparse(0, 0, ZERO_UID_GID, 0, 0);
    }
    @Test
    public void test21() throws ZipException {
        // Regression test with one UID and GID
        final byte[] ONE_UID_GID = {1, 1, 1, 1, 1};
        parseReparse(1, 1, ONE_UID_GID, 1, 1);
    }
    @Test
    public void test22() throws ZipException {
        // Regression test with one thousand UID and GID
        final byte[] ONE_THOUSAND_UID_GID = {1, 2, -24, 3, 2, -24, 3};
        parseReparse(1000, 1000, ONE_THOUSAND_UID_GID, 1000, 1000);
    }
    @Test
    public void test23() throws ZipException {
        // Regression test with maximum Unix UID and GID
        final byte[] UNIX_MAX_UID_GID = {1, 4, -2, -1, -1, -1, 4, -2, -1, -1, -1};
        final long MAX = 4294967294L;
        parseReparse(MAX, MAX, UNIX_MAX_UID_GID, MAX, MAX);
    }
    @Test
    public void test24() throws ZipException {
        // Regression test with length 5
        final byte[] LENGTH_5 = {1, 5, 0, 0, 0, 0, 1, 5, 1, 0, 0, 0, 1};
        final long TWO_TO_32 = 0x100000000L;
        parseReparse(TWO_TO_32, TWO_TO_32 + 1, LENGTH_5, TWO_TO_32, TWO_TO_32 + 1);
    }
    @Test
    public void test25() throws ZipException {
        // Regression test with length 8
        final byte[] LENGTH_8 = {1, 8, -2, -1, -1, -1, -1, -1, -1, 127, 8, -1, -1, -1, -1, -1, -1, -1, 127};
        parseReparse(Long.MAX_VALUE - 1, Long.MAX_VALUE, LENGTH_8, Long.MAX_VALUE - 1, Long.MAX_VALUE);
    }
    @Test
    public void test26() throws ZipException {
        // Regression test with spurious zeroes 1
        final byte[] SPURIOUS_ZEROES_1 = {1, 4, -1, 0, 0, 0, 4, -128, 0, 0, 0};
        final byte[] EXPECTED_1 = {1, 1, -1, 1, -128};
        parseReparse(255, 128, SPURIOUS_ZEROES_1, 255, 128);
    }
    @Test
    public void test27() throws ZipException {
        // Regression test with spurious zeroes 2
        final byte[] SPURIOUS_ZEROES_2 = {1, 4, -1, -1, 0, 0, 4, 1, 2, 0, 0};
        final byte[] EXPECTED_2 = {1, 2, -1, -1, 2, 1, 2};
        parseReparse(65535, 513, SPURIOUS_ZEROES_2, 65535, 513);
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
        
        // Additional regression test cases
        final byte[] PRIME_UID_GID = {1, 1, 71, 1, 71};
        parseReparse(71, 71, PRIME_UID_GID, 71, 71);
        
        final byte[] NEGATIVE_UID_GID = {1, 1, -1, 1, -1};
        parseReparse(-1, -1, NEGATIVE_UID_GID, -1, -1);
        
        final byte[] ALTERNATING_UID_GID = {1, 5, 1, 0, 1, 0, 0};
        parseReparse(65536, 65536, ALTERNATING_UID_GID, 65536, 65536);
    }
@Test
public void test30() {
    byte[] expectedResult = new byte[10];
    byte[] result = getCentralDirectoryData();
    assertArrayEquals(expectedResult, result);
}
@Test
public void test31() {
    byte[] expectedResult = new byte[100];
    byte[] result = getCentralDirectoryData();
    assertArrayEquals(expectedResult, result);
}
@Test
public void test32() {
    byte[] expectedResult = new byte[50];
    byte[] result = getCentralDirectoryData();
    assertArrayEquals(expectedResult, result);
}
    @Test
    public void test33() throws Exception {
        byte[] data = { -1, 2, 3, 4, 5, 6, 7 };
        int offset = 0;
        int length = data.length;

        parseFromLocalFileData(data, offset, length);

        // Assert that version is correctly parsed
        assertEquals(255, version);

        // Assert that uid and gid are correctly parsed
        assertEquals(-1, uid.intValue());
        assertEquals(2, gid.intValue());
    }
    @Test
    public void test34() throws Exception {
        byte[] data = { 1, 4, 0, 0, 0, 0, 4, -128, 0, 0, 0 };
        int offset = 0;
        int length = data.length;

        parseFromLocalFileData(data, offset, length);

        // Assert that version is correctly parsed
        assertEquals(1, version);

        // Assert that uid and gid are correctly parsed
        assertEquals(0, uid.intValue());
        assertEquals(2147483648L, gid.longValue());
    }
    @Test
    public void test35() throws Exception {
        byte[] data = { 1, 1, -128, -128, 1, -128 };
        int offset = 0;
        int length = data.length;

        parseFromLocalFileData(data, offset, length);

        // Assert that version is correctly parsed
        assertEquals(1, version);

        // Assert that uid and gid are correctly parsed
        assertEquals(128, uid.intValue());
        assertEquals(-2147483648L, gid.longValue());
    }
    @Test
    public void test36() throws Exception {
        byte[] buffer = new byte[0];
        int offset = 0;
        int length = 0;

        // Expecting a ZipException to be thrown since the buffer is empty
        assertThrows(ZipException.class, () ->
                parseFromCentralDirectoryData(buffer, offset, length));
    }
    @Test
    public void test37() throws Exception {
        byte[] buffer = new byte[10];
        int offset = -1;
        int length = 10;

        // Expecting a ZipException to be thrown since the offset is negative
        assertThrows(ZipException.class, () ->
                parseFromCentralDirectoryData(buffer, offset, length));
    }
    @Test
    public void test38() throws Exception {
        byte[] buffer = new byte[10];
        int offset = 0;
        int length = 20;

        // Expecting a ZipException to be thrown since the length is greater than the buffer size
        assertThrows(ZipException.class, () ->
                parseFromCentralDirectoryData(buffer, offset, length));
    }
    @Test
    public void test39() {
        assertEquals(X7875, xf.getHeaderId());
        // Regression test 1: Change input value to null
        assertNull(xf.getHeaderId());
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
        // Regression test 2: Change input value to negative UID and GID
        xf.setUID(-12345);
        assertFalse(xf.equals(o));
        // Regression test 3: Change input value to zero UID and GID
        xf.setUID(0);
        assertFalse(xf.equals(o));
        // Regression test 4: Change input value to maximum UID and GID
        xf.setUID(Integer.MAX_VALUE);
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
        // Regression test 5: Change input value to longer sequence
        final byte[] LONG_SEQUENCE_LEADING_ZEROES = {0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3};
        assertTrue(Arrays.equals(SEQUENCE, trimTest(LONG_SEQUENCE_LEADING_ZEROES)));
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
                // Regression test 6: Change input value to negative UID and GID
                assertEquals(-expected, xf.getUID());
                assertEquals(-expected, xf.getGID());
                // Regression test 7: Change input value to zero UID and GID
                assertEquals(0, xf.getUID());
                assertEquals(0, xf.getGID());
                // Regression test 8: Change input value to maximum UID and GID
                assertEquals(Integer.MAX_VALUE, xf.getUID());
                assertEquals(Integer.MAX_VALUE, xf.getGID());
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
        // Regression test 9: Change input value to negative UID and GID
        parseReparse(-MAX, -MAX, UNIX_MAX_UID_GID, MAX, MAX);
        // Regression test 10: Change input value to zero UID and GID
        parseReparse(0, 0, UNIX_MAX_UID_GID, MAX, MAX);
        // Regression test 11: Change input value to maximum UID and GID
        parseReparse(Integer.MAX_VALUE, Integer.MAX_VALUE, UNIX_MAX_UID_GID, MAX, MAX);
    }
@Test
public void test44() throws Exception {
    ZipExtraField xf1 = new ZipExtraField();
    xf1.setUID(9999);
    xf1.setGID(7777);

    ZipExtraField xf2 = new ZipExtraField();
    xf2.setUID(8888);
    xf2.setGID(7777);

    assertFalse(xf1.equals(xf2));
}
@Test
public void test45() throws Exception {
    ZipExtraField xf1 = new ZipExtraField();
    xf1.setUID(5555);
    xf1.setGID(6666);

    ZipExtraField xf2 = new ZipExtraField();
    xf2.setUID(5555);
    xf2.setGID(7777);

    assertFalse(xf1.equals(xf2));
}
    @Test
    public void test46() throws Exception {
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertTrue(xf.equals(o));
        // Change the clone object
        ExtraField newXf = new ExtraField();
        assertFalse(xf.equals(newXf));
    }
    @Test
    public void test47() throws Exception {
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertTrue(xf.equals(o));
        // Change the UID
        xf.setUID(54321);
        assertFalse(xf.equals(o));
    }
    @Test
    public void test48() {
        assertEquals(X7875, xf.getHeaderId());
        // Regression test case 1: Changing the input value of xf.getHeaderId()
        // to a different value
        assertNotEquals(1234, xf.getHeaderId());
    }
    @Test
    public void test49() throws Exception {
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertTrue(xf.equals(o));
        xf.setUID(12345);
        assertFalse(xf.equals(o));
        // Regression test case 2: Changing the input value of xf.setUID()
        // to a different value
        xf.setUID(54321);
        assertFalse(xf.equals(o));
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
                }
                assertEquals(expected, xf.getUID());
                assertEquals(expected, xf.getGID());
                // Regression test case 3: Changing the input value of xf.getUID()
                // to a different value
                assertNotEquals(1234, xf.getUID());
            }
        } finally {
            if (zf != null) {
                zf.close();
            }
        }
    }
    @Test
    public void test51() throws Exception {
        xf.setVersion(-5);
        int expected = 1234567 * -5;
        expected ^= Integer.rotateLeft(uid.hashCode(), 16);
        expected ^= gid.hashCode();
        assertEquals(expected, xf.hashCode());
    }
    @Test
    public void test52() throws Exception {
        xf.setUID(Integer.MAX_VALUE);
        int expected = -1234567 * version;
        expected ^= Integer.rotateLeft(Integer.MAX_VALUE, 16);
        expected ^= gid.hashCode();
        assertEquals(expected, xf.hashCode());
    }
    @Test
    public void test53() throws Exception {
        xf.setGID(null);
        int expected = -1234567 * version;
        expected ^= Integer.rotateLeft(uid.hashCode(), 16);
        expected ^= null.hashCode();
        assertEquals(expected, xf.hashCode());
    }
    @Test
    public void test54() throws Exception {
        xf.setUID(0);
        int expected = -1234567 * version;
        expected ^= Integer.rotateLeft(0, 16);
        expected ^= gid.hashCode();
        assertEquals(expected, xf.hashCode());
    }
    @Test
    public void test55() throws Exception {
        assertFalse(xf.equals(version));
    }
    @Test
    public void test56() throws Exception {
        assertFalse(xf.equals(null));
    }
    @Test
    public void test57() throws Exception {
        assertTrue(xf.equals(xf));
    }
    @Test
    public void test58() throws Exception {
        X7875_NewUnix other = new X7875_NewUnix();
        other.setVersion(version);
        other.setUID(555);
        other.setGID(gid);
        assertFalse(xf.equals(other));
    }
    @Test
    public void test59() throws Exception {
        X7875_NewUnix other = new X7875_NewUnix();
        other.setVersion(version);
        other.setUID(uid);
        other.setGID(555);
        assertFalse(xf.equals(other));
    }
    @Test
    public void test60() throws Exception {
        X7875_NewUnix other = new X7875_NewUnix();
        other.setVersion(2);
        other.setUID(uid);
        other.setGID(gid);
        assertFalse(xf.equals(other));
    }
    @Test
    public void test61() throws Exception {
        X7875_NewUnix other = new X7875_NewUnix();
        other.setVersion(version);
        other.setUID(uid);
        other.setGID(gid);
        assertTrue(xf.equals(other));
    }
    @Test
    public void test62() throws Exception {
        X7875_OldUnix other = new X7875_OldUnix();
        other.setVersion(version);
        other.setUID(uid);
        other.setGID(gid);
        assertFalse(xf.equals(other));
    }
    @Test
    public void test63() throws Exception {
        X7875_NewUnix other = new X7875_NewUnix();
        other.setVersion(null);
        other.setUID(null);
        other.setGID(null);
        assertFalse(xf.equals(other));
    }
    @Test
    public void test64(){
        final byte[] ALL_ZEROES = {0, 0, 0, 0};
        final byte[] ONE_ZERO = {0, 0, 0, 1};
                
        // Regression test case 1, all zeroes
        assertTrue(Arrays.equals(ALL_ZEROES, trimTest(ALL_ZEROES)));

        // Regression test case 2, one zero
        assertTrue(Arrays.equals(ONE_ZERO, trimTest(ONE_ZERO)));
    }
    @Test
    public void test65() throws ZipException {
        final byte[] REGRESSION_ONE = {1, 1, -1, 1, 0};
        final byte[] REGRESSION_TWO = {1, 4, -1, -1, -1, -1, 4, 1, 2, 3, 0};
        final byte[] REGRESSION_THREE = {1, 8, -2, -1, -1, -1, -1, -1, -1, 127, 8, -1, -1, -1, -1, -1, -1, -1, 0};

        // Regression test case 1, unexpected zeroes
        xf.parseFromLocalFileData(REGRESSION_ONE, 0, REGRESSION_ONE.length);
        assertEquals(-1, xf.getUID());
        assertEquals(1, xf.getGID());

        // Regression test case 2, unexpected zeroes
        xf.parseFromLocalFileData(REGRESSION_TWO, 0, REGRESSION_TWO.length);
        assertEquals(-1, xf.getUID());
        assertEquals(-1, xf.getGID());

        // Regression test case 3, unexpected zeroes
        xf.parseFromLocalFileData(REGRESSION_THREE, 0, REGRESSION_THREE.length);
        assertEquals(-2, xf.getUID());
        assertEquals(-1, xf.getGID());
    }
    @Test
    public void test66() throws Exception {
        File archive = getFile("COMPRESS-211_uid_gid_zip_test.zip");
        ZipFile zf = null;

        try {
            zf = new ZipFile(archive);
            Enumeration<ZipArchiveEntry> en = zf.getEntries();

            while (en.hasMoreElements()) {

                ZipArchiveEntry zae = en.nextElement();
                String name = zae.getName();
                X7875_NewUnix xf = (X7875_NewUnix) zae.getExtraField(X7875);

                // Additional test for directory entry with different UID/GID values
                if (name.contains("uid1_gid5")) {
                    assertEquals(1, xf.getUID());
                    assertEquals(5, xf.getGID());
                }
            }
        } finally {
            if (zf != null) {
                zf.close();
            }
        }
    }
}