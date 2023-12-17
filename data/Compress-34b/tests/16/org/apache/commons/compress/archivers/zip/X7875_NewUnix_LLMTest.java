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
                long expected = 2000;
                if (name.contains("uid555_gid555")) {
                    expected = 5555;
                } else if (name.contains("uid5555_gid5555")) {
                    expected = 55555;
                } else if (name.contains("uid55555_gid55555")) {
                    expected = 555555;
                } else if (name.contains("uid555555_gid555555")) {
                    expected = 5555555;
                } else if (name.contains("min_unix")) {
                    expected = 1000;
                } else if (name.contains("max_unix")) {
                    // 2^32-2 was the biggest UID/GID I could create on my linux!
                    // (December 2012, linux kernel 3.4)
                    expected = 0x100000000L - 3;
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
        assertEquals(X1234, xf.getHeaderId());
    }
    @Test
    public void test2() throws ZipException {
        // Regression test case 1: Changing the value of ZERO_LEN
        final byte[] ZERO_LEN = {1, 1, 0};

        // Regression test case 2: Changing the value of ONE_UID_GID
        final byte[] ONE_UID_GID = {1, 1, -1, 1, 1};

        // Regression test case 3: Changing the value of UNIX_MAX_UID_GID
        final byte[] UNIX_MAX_UID_GID = {1, 4, -2, -2, -2, -1, 4, -2, -2, -2, -1};

        parseReparse(0, 0, ZERO_LEN, 0, 0);
        parseReparse(1, 1, ONE_UID_GID, 1, 1);
        parseReparse(MAX, MAX, UNIX_MAX_UID_GID, MAX, MAX);

        // Regression test case 4: Changing the value of LENGTH_5
        final byte[] LENGTH_5 = {1, 4, -24, 3, 2, -24, 3};

        parseReparse(TWO_TO_32, TWO_TO_32 + 1, LENGTH_5, TWO_TO_32, TWO_TO_32 + 1);
    }
    @Test
    public void test3() throws ZipException {
        // Version=1, Len=1, 100, Len=1, 100
        final byte[] ONE_HUNDRED_UID_GID = {1, 1, 100, 1, 100};

        parseReparse(100, 100, ONE_HUNDRED_UID_GID, 100, 100);
    }
    @Test
    public void test4() throws Exception {
        File archive = getFile("COMPRESS-211_uid_gid_zip_test2.zip");
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

                // The directory entry in the test zip file is uid/gid 100.
                long expected = 100;
                if (name.contains("uid555_gid555")) {
                    expected = 555;
                } else if (name.contains("uid5555_gid5555")) {
                    expected = 5555;
                } else if (name.contains("uid55555_gid55555")) {
                    expected = 55555;
                } else if (name.contains("uid555555_gid555555")) {
                    expected = 0;
                } else if (name.contains("min_unix")) {
                    expected = 0;
                } else if (name.contains("max_unix")) {
                    // 2^32-3 was the biggest UID/GID I could create on my linux!
                    // (December 2012, linux kernel 3.4)
                    expected = 0x100000000L - 3;
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
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertTrue(xf.equals(o));
        xf.setUID(0); // change the input value to 0
        assertFalse(xf.equals(o));
    }
    @Test
    public void test6() throws ZipException {
        final byte[] ZERO_LEN = {1, 0, 0};
        final byte[] ZERO_UID_GID = {1, 1, 0, 1, 0};
        final byte[] ONE_UID_GID = {1, 1, 1, 1, 1};
        final byte[] ONE_THOUSAND_UID_GID = {1, 2, -24, 3, 2, -24, 3};
        final byte[] UNIX_MAX_UID_GID = {1, 4, -2, -1, -1, -1, 4, -2, -1, -1, -1};
        final byte[] LENGTH_5 = {1, 5, 0, 0, 0, 0, 1, 5, 1, 0, 0, 0, 1};
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
        parseReparse(12345L, 12345L, LENGTH_8, 12345L, 12345L); // add a new test case
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
        parseReparse(4294967294L, 4294967294L, UNIX_MAX_UID_GID, 4294967294L, 4294967294L);

        // Version=1, Len=5, 2^32, Len=5, 2^32 + 1
        // Esoteric test:  can we handle 40 bit numbers?
        final byte[] LENGTH_5 = {1, 5, 0, 0, 0, 0, 1, 5, 1, 0, 0, 0, 1};
        parseReparse(4294967296L, 4294967297L, LENGTH_5, 4294967296L, 4294967297L);

        // Version=1, Len=8, 2^63 - 2, Len=8, 2^63 - 1
        // Esoteric test:  can we handle 64 bit numbers?
        final byte[] LENGTH_8 = {1, 8, -2, -1, -1, -1, -1, -1, -1, 127, 8, -1, -1, -1, -1, -1, -1, -1, 127};
        parseReparse(9223372036854775806L, 9223372036854775807L, LENGTH_8, 9223372036854775806L, 9223372036854775807L);

        // Additional regression tests
        // Version=1, Len=2, Integer.MAX_VALUE, Len=2, Integer.MIN_VALUE
        final byte[] MAX_MIN = {1, 2, -1, -1, 1, 0, 2, -128, 0};
        parseReparse(Integer.MAX_VALUE, Integer.MIN_VALUE, MAX_MIN, Integer.MAX_VALUE, Integer.MIN_VALUE);

        // Version=1, Len=4, Long.MAX_VALUE, Len=4, Long.MIN_VALUE
        final byte[] LONG_MAX_MIN = {1, 4, -1, -1, -1, -1, -1, -1, -1, 127, 4, 0, 0, 0, 0, 0, 0, -128};
        parseReparse(Long.MAX_VALUE, Long.MIN_VALUE, LONG_MAX_MIN, Long.MAX_VALUE, Long.MIN_VALUE);

        // Version=1, Len=2, -1, Len=2, -1
        final byte[] NEGATIVE_ONE = {1, 2, -1, -1, 2, -1, -1};
        parseReparse(-1, -1, NEGATIVE_ONE, -1, -1);

        // Version=1, Len=3, 10000, Len=3, 20000
        final byte[] THOUSANDS = {1, 3, -112, 39, 2, -48, 78, 3};
        parseReparse(10000, 20000, THOUSANDS, 10000, 20000);
    }
@Test
public void test8() {
    // Test case where uid and gid sizes are both 1
    // Expected length = 3 + 1 + 1 = 5
    ZipShort zipShort1 = getLocalFileDataLength(1, 1);
    assertEquals(5, zipShort1.getValue());

    // Test case where uid and gid sizes are both zero
    // Expected length = 3 + 0 + 0 = 3
    ZipShort zipShort2 = getLocalFileDataLength(0, 0);
    assertEquals(3, zipShort2.getValue());

    // Test case where uid size is 2 and gid size is 1
    // Expected length = 3 + 2 + 1 = 6
    ZipShort zipShort3 = getLocalFileDataLength(2, 1);
    assertEquals(6, zipShort3.getValue());

    // Test case where uid and gid sizes are both 5
    // Expected length = 3 + 5 + 5 = 13
    ZipShort zipShort4 = getLocalFileDataLength(5, 5);
    assertEquals(13, zipShort4.getValue());

    // Test case where uid and gid sizes are both 10
    // Expected length = 3 + 10 + 10 = 23
    ZipShort zipShort5 = getLocalFileDataLength(10, 10);
    assertEquals(23, zipShort5.getValue());
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

        // Version=1, Len=2, 100, Len=2, 100
        final byte[] ONE_HUNDRED_UID_GID = {1, 2, 100, 0, 100, 0};
        parseReparse(100, 100, ONE_HUNDRED_UID_GID, 100, 100);

        // Version=1, Len=3, 1000, 10, Len=3, 999, 10
        final byte[] THREE_DIGITS_UID_GID = {1, 3, -24, 3, 10, -25, 3, 10};
        parseReparse(1000, 10, THREE_DIGITS_UID_GID, 999, 10);

        // Version=1, Len=5, 999, 100, Len=5, 1000, 1000
        final byte[] FIVE_DIGITS_UID_GID = {1, 5, -25, 3, 100, 100, 16, 4, -24, 3};
        parseReparse(999, 100, FIVE_DIGITS_UID_GID, 1000, 1000);

        // Version=1, Len=6, 2010, 2010, Len=6, 2010, 2010
        final byte[] SIX_DIGITS_UID_GID = {1, 6, -38, 7, -38, 7, -38, 7, -38, 7};
        parseReparse(2010, 2010, SIX_DIGITS_UID_GID, 2010, 2010);

        // Version=1, Len=7, 9999, 9999, Len=7, 10000, 1000000
        final byte[] SEVEN_DIGITS_UID_GID = {1, 7, -73, 27, -73, 27, -73, 27, -16, 38, 16, 58};
        parseReparse(9999, 9999, SEVEN_DIGITS_UID_GID, 10000, 1000000);
    }
    @Test
    public void test10() {
        // Test case with uid and gid of length 0
        byte[] expected1 = {1, 0, 0, 0};
        byte[] actual1 = getLocalFileDataData(new byte[0], new byte[0]);
        assertArrayEquals(expected1, actual1);

        // Test case with uid and gid of length 1
        byte[] expected2 = {1, 1, -1, 1, -128, 1};
        byte[] actual2 = getLocalFileDataData(new byte[]{(byte) -1}, new byte[]{1});
        assertArrayEquals(expected2, actual2);

        // Test case with uid and gid of length 2
        byte[] expected3 = {1, 2, -3, -4, -128, 127, -3, -4};
        byte[] actual3 = getLocalFileDataData(new byte[]{-3, -4}, new byte[]{-128, 127});
        assertArrayEquals(expected3, actual3);
    }
public class TestGetCentralDirectoryData {
    @Test
    public void test11() {
        byte[] expected = new byte[0];
        byte[] actual = getCentralDirectoryData(new byte[0]);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void test12() {
        byte[] data = {1, 2, 3, 4, 5};
        byte[] expected = {1, 2, 3, 4, 5};
        byte[] actual = getCentralDirectoryData(data);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void test13() {
        byte[] data = {-128, -127, -126, -125, -124, -123, -122, -121, -120, -119};
        byte[] expected = {-128, -127, -126, -125, -124, -123, -122, -121, -120, -119};
        byte[] actual = getCentralDirectoryData(data);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void test14() {
        byte[] data = {-1, -2, -3, -4, -5};
        byte[] expected = {-1, -2, -3, -4, -5};
        byte[] actual = getCentralDirectoryData(data);

        assertArrayEquals(expected, actual);
    }
}
   @Test
   public void test15() throws ZipException {
       byte[] data = {1, 0, 0, 1, 0, 1};
       methodUnderTest.parseFromLocalFileData(data, 0, data.length);
       assertEquals(1, methodUnderTest.getVersion());
       assertEquals(0, methodUnderTest.getUidSize());
       assertEquals(1, methodUnderTest.getGidSize());
       assertEquals(1, methodUnderTest.getUid());
       assertEquals(1, methodUnderTest.getGid());
   }
   @Test
   public void test16() throws ZipException {
       byte[] data = {1, 2, -24, 3, 2, -24, 3};
       methodUnderTest.parseFromLocalFileData(data, 0, data.length);
       assertEquals(1, methodUnderTest.getVersion());
       assertEquals(2, methodUnderTest.getUidSize());
       assertEquals(2, methodUnderTest.getGidSize());
       assertEquals(1000, methodUnderTest.getUid());
       assertEquals(1000, methodUnderTest.getGid());
   }
   @Test
   public void test17() throws ZipException {
       byte[] data = {1, 4, -2, -1, -1, -1, 4, -2, -1, -1, -1};
       methodUnderTest.parseFromLocalFileData(data, 0, data.length);
       assertEquals(1, methodUnderTest.getVersion());
       assertEquals(4, methodUnderTest.getUidSize());
       assertEquals(4, methodUnderTest.getGidSize());
       assertEquals(4294967294L, methodUnderTest.getUid());
       assertEquals(4294967294L, methodUnderTest.getGid());
   }
@Test
public void test18() throws Exception {
    byte[] buffer = null;
    int offset = 0;
    int length = 0;

    try {
        parseFromCentralDirectoryData(buffer, offset, length);
        fail("Expected ZipException");
    } catch (ZipException e) {
        // Expected behavior
    }
}
@Test
public void test19() throws Exception {
    byte[] buffer = new byte[10];
    int offset = -1;
    int length = 10;

    try {
        parseFromCentralDirectoryData(buffer, offset, length);
        fail("Expected ZipException");
    } catch (ZipException e) {
        // Expected behavior
    }
}
@Test
public void test20() throws Exception {
    byte[] buffer = new byte[10];
    int offset = 0;
    int length = -1;

    try {
        parseFromCentralDirectoryData(buffer, offset, length);
        fail("Expected ZipException");
    } catch (ZipException e) {
        // Expected behavior
    }
}
@Test
public void test21() throws Exception {
    byte[] buffer = new byte[5];
    int offset = 0;
    int length = 10;

    try {
        parseFromCentralDirectoryData(buffer, offset, length);
        fail("Expected ZipException");
    } catch (ZipException e) {
        // Expected behavior
    }
}
@Test
public void test22() throws Exception {
    byte[] buffer = new byte[10];
    int offset = 0;
    int length = 0;

    try {
        parseFromCentralDirectoryData(buffer, offset, length);
        fail("Expected ZipException");
    } catch (ZipException e) {
        // Expected behavior
    }
}
    @Test
    public void test23() {
        uid = 0; // change input value
        gid = 0; // change input value
        reset();
        assertEquals(1000, uid);
        assertEquals(1000, gid);
    }
    @Test
    public void test24() throws Exception {
        File archive = getFile("COMPRESS-211_uid_gid_zip_test.zip");
        ZipFile zf = null;
        
        uid = 0; // change input value
        gid = 0; // change input value

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
public void test25() throws Exception {
    xf.setUID(0);
    xf.setGID(0);
    assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field: UID=0 GID=0"));
}
@Test
public void test26() throws Exception {
    xf.setUID(0);
    xf.setGID(0);
    Object o = xf.clone();
    assertEquals(o.hashCode(), xf.hashCode());
    assertTrue(xf.equals(o));
}
@Test
public void test27() throws Exception {
    xf.setUID(0);
    xf.setGID(12345);
    assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field: UID=0 GID=12345"));
    xf.setUID(67890);
    assertFalse(xf.equals(new Object()));
}
    @Test
    public void test28() throws Exception {
        Object o = xf.clone();
        assertNotSame(o, xf);
    }
    @Test
    public void test29() throws Exception {
        Object o = xf.clone();
        assertTrue(o instanceof ZipExtraField);
    }
    @Test
    public void test30() throws Exception {
        Object o = xf.clone();
        assertEquals(o, xf);
    }
    @Test
    public void test31() throws Exception {
        Object o = xf.clone();
        assertNotEquals(o.hashCode(), xf.hashCode());
    }
    @Test
    public void test32() throws Exception {
        xf.setUID(54321);
        Object o = xf.clone();
        assertNotEquals(xf, o);
    }
    @Test
    public void test33() throws Exception {
        xf.setUID(null);
        Object o = xf.clone();
        assertNotEquals(xf, o);
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
        assertEquals(X7875, xf.getHeaderId());
    }
    @Test
    public void test36() throws Exception {
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertTrue(xf.equals(o));
        xf.setUID(12345);
        assertFalse(xf.equals(o));
    }
    @Test
    public void test37() {
        X7875_NewUnix xf = new X7875_NewUnix();
        xf.setVersion(2);
        assertFalse(xf.equals(new Object()));
    }
    @Test
    public void test38() {
        X7875_NewUnix xf = new X7875_NewUnix();
        xf.setUID(1001);
        assertFalse(xf.equals(new Object()));
    }
    @Test
    public void test39() {
        X7875_NewUnix xf = new X7875_NewUnix();
        xf.setGID(1001);
        assertFalse(xf.equals(new Object()));
    }
    @Test
    public void test40() {
        X7875_NewUnix xf = new X7875_NewUnix();
        assertFalse(xf.equals(new X7875_OldUnix()));
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
    public void test42() throws Exception {
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertTrue(xf.equals(o));
        xf.setUID(12345);
        assertFalse(xf.equals(o));
    }
    @Test
    public void test43() throws Exception {
        X7875_NewUnix xf = new X7875_NewUnix();
        xf.setVersion(2);
        int hashCode = xf.hashCode();
        assertEquals(-2469134, hashCode);
    }
    @Test
    public void test44() throws Exception {
        X7875_NewUnix xf = new X7875_NewUnix();
        xf.setUID(999);
        int hashCode = xf.hashCode();
        assertEquals(-1530559648, hashCode);
    }
    @Test
    public void test45() throws Exception {
        X7875_NewUnix xf = new X7875_NewUnix();
        xf.setGID(999);
        int hashCode = xf.hashCode();
        assertEquals(-1999998, hashCode);
    }
    @Test
    public void test46() throws Exception {
        X7875_NewUnix xf = new X7875_NewUnix();
        xf.setVersion(2);
        xf.setUID(999);
        int hashCode = xf.hashCode();
        assertEquals(-1533027094, hashCode);
    }
    @Test
    public void test47() throws Exception {
        X7875_NewUnix xf = new X7875_NewUnix();
        xf.setVersion(2);
        xf.setGID(999);
        int hashCode = xf.hashCode();
        assertEquals(-1999998, hashCode);
    }
    @Test
    public void test48() throws Exception {
        X7875_NewUnix xf = new X7875_NewUnix();
        xf.setUID(999);
        xf.setGID(999);
        int hashCode = xf.hashCode();
        assertEquals(-1469134, hashCode);
    }
    @Test
    public void test49() throws Exception {
        X7875_NewUnix xf = new X7875_NewUnix();
        xf.setVersion(2);
        xf.setUID(999);
        xf.setGID(999);
        int hashCode = xf.hashCode();
        assertEquals(-1466863704, hashCode);
    }
    @Test
    public void test50() {
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
        final byte[] ALL_ZEROES = {0, 0, 0, 0};
        final byte[] ONE_BYTE_ALL_ZEROES = {0, 0, 0, 0, 0};

        assertTrue(NULL == trimTest(NULL));
        assertTrue(Arrays.equals(ONE_ZERO, trimTest(EMPTY)));
        assertTrue(Arrays.equals(ONE_ZERO, trimTest(ONE_ZERO)));
        assertTrue(Arrays.equals(ONE_ZERO, trimTest(TWO_ZEROES)));
        assertTrue(Arrays.equals(ONE_ZERO, trimTest(FOUR_ZEROES)));
        assertTrue(Arrays.equals(ONE_ZERO, trimTest(ALL_ZEROES)));
        assertTrue(Arrays.equals(ONE_ZERO, trimTest(ONE_BYTE_ALL_ZEROES)));
        assertTrue(Arrays.equals(SEQUENCE, trimTest(SEQUENCE)));
        assertTrue(Arrays.equals(SEQUENCE, trimTest(SEQUENCE_LEADING_ZERO)));
        assertTrue(Arrays.equals(SEQUENCE, trimTest(SEQUENCE_LEADING_ZEROES)));
        assertTrue(Arrays.equals(TRAILING_ZERO, trimTest(TRAILING_ZERO)));
        assertTrue(Arrays.equals(TRAILING_ZERO, trimTest(PADDING_ZERO)));
        assertTrue(Arrays.equals(SEQUENCE6, trimTest(SEQUENCE6)));
        assertTrue(Arrays.equals(SEQUENCE6, trimTest(SEQUENCE6_LEADING_ZERO)));
    }
}