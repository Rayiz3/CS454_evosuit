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
        assertEquals(HEADER_ID, xf.getHeaderId());
    }
    @Test
    public void test1() {
        assertEquals(ANOTHER_HEADER_ID, xf.getHeaderId());
    }
    @Test
    public void test2() {
        assertNull(xf.getHeaderId());
    }
    @Test
    public void test3() throws Exception {
        File archive = getFile("empty.zip");
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
        File archive = getFile("COMPRESS-211_uid_gid_zip_test.zip");
        ZipFile zf = null;

        try {
            zf = new ZipFile(archive);
            Enumeration<ZipArchiveEntry> en = zf.getEntries();

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
                assertNotEquals("InvalidEntryName", xf.getHeaderId());
            }
        } finally {
            if (zf != null) {
                zf.close();
            }
        }
    }
    @Test
    public void test5() throws Exception {
        File archive = getFile("COMPRESS-211_uid_gid_zip_test.zip");
        ZipFile zf = null;

        try {
            zf = new ZipFile(archive);
            Enumeration<ZipArchiveEntry> en = zf.getEntries();

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
    public void test6() throws ZipException {

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
        
        // (2^32 - 2).
        final byte[] UNIX_MAX_UID_GID = {1, 4, -2, -1, -1, -1, 4, -2, -1, -1, -1};
        final long MAX = 4294967294L;
        parseReparse(MAX, MAX, UNIX_MAX_UID_GID, MAX, MAX);
        
        // Version=1, Len=5, 2^32, Len=5, 2^32 + 1
        final byte[] LENGTH_5 = {1, 5, 0, 0, 0, 0, 1, 5, 1, 0, 0, 0, 1};
        final long TWO_TO_32 = 4294967296L;
        parseReparse(TWO_TO_32, TWO_TO_32 + 1, LENGTH_5, TWO_TO_32, TWO_TO_32 + 1);
        
        // Version=1, Len=8, 2^63 - 2, Len=8, 2^63 - 1
        final byte[] LENGTH_8 = {1, 8, -2, -1, -1, -1, -1, -1, -1, 127, 8, -1, -1, -1, -1, -1, -1, -1, 127};
        final long MAX_LONG = 9223372036854775806L;
        parseReparse(MAX_LONG - 1, MAX_LONG, LENGTH_8, MAX_LONG - 1, MAX_LONG);
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
    public void test8() throws ZipException {
        // Test with negative values
        final byte[] NEGATIVE_VALUES = {1, 4, -128, -128, -128, 0, -128, -128, -128, 0};
        
        parseReparse(-2147483648, -2147483648, NEGATIVE_VALUES, -2147483648, -2147483648);
    }
    @Test
    public void test9() throws ZipException {
        // Test with maximum 32-bit unsigned value
        final byte[] MAX_UNSIGNED_VALUE = {1, 4, -128, -128, -128, -128, -128, -128, -128, 127};
        
        parseReparse(4294967295L, 4294967295L, MAX_UNSIGNED_VALUE, 4294967295L, 4294967295L);
    }
    @Test
    public void test10() throws ZipException {
        // Test with positive values greater than 32-bit unsigned maximum value
        final byte[] BIGGER_THAN_32_BIT_UNSIGNED_MAX = {1, 5, -128, -128, -128, -128, -128, -128, -128, 0, -128, -128};
        
        parseReparse(549755813888L, 549755813889L, BIGGER_THAN_32_BIT_UNSIGNED_MAX, 549755813888L, 549755813889L);
    }
    @Test
    public void test11() throws ZipException {
        // Test with zero value
        final byte[] ZERO_VALUE = {1, 4, 0, 0, 0, 0, 0, 0, 0, 0};
        
        parseReparse(0, 0, ZERO_VALUE, 0, 0);
    }
    @Test
    public void test12() throws ZipException {
        // Test with maximum signed long value
        final byte[] MAX_SIGNED_LONG_VALUE = {1, 8, -1, -1, -1, -1, -1, -1, -1, 127, -1, -1, -1, -1, -1, -1, -1, 127};
        
        parseReparse(9223372036854775806L, 9223372036854775807L, MAX_SIGNED_LONG_VALUE, 9223372036854775806L, 9223372036854775807L);
    }
    @Test
    public void test13() throws ZipException {
        // Test with minimum signed long value
        final byte[] MIN_SIGNED_LONG_VALUE = {1, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -128};
        
        parseReparse(-9223372036854775807L, -9223372036854775808L, MIN_SIGNED_LONG_VALUE, -9223372036854775807L, -9223372036854775808L);
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
    }
    @Test
    public void test15() throws Exception {
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertTrue(xf.equals(o));
        xf.setUID(12345);
        assertFalse(xf.equals(o));
    }
    @Test
    public void test16() throws ZipException {
        // Version=1, Len=-1, Len=-1.
        final byte[] NEGATIVE_LEN = {1, -1, -1};
        parseReparse(-1, -1, NEGATIVE_LEN, -1, -1);
    }
    @Test
    public void test17() throws ZipException {
        // Version=1, Len=2^32+1, Len=2^32+1.
        final byte[] LARGE_LEN = {1, 1, 0, 0, 1, 1};
        parseReparse(4294967297L, 4294967297L, LARGE_LEN, 4294967297L, 4294967297L);
    }
    @Test
    public void test18() throws ZipException {
        // Version=1, Len=0, Len=0.
        final byte[] ZERO_LEN = {1, 0, 0};
        parseReparse(0, 0, ZERO_LEN, 0, 0);
    }
    @Test
    public void test19() throws ZipException {
        // Version=2, Len=1, Len=1.
        final byte[] VERSION_2 = {2, 1, 1};
        parseReparse(1, 1, VERSION_2, 1, 1);
    }
    @Test
    public void test20() throws ZipException {
        // Version=1, Len=1, -1, Len=1, -1.
        final byte[] NEGATIVE_UID_GID = {1, 1, -1, 1, -1};
        parseReparse(-1, -1, NEGATIVE_UID_GID, -1, -1);
    }
    @Test
    public void test21() throws ZipException {
        // Version=1, Len=1, 0, Len=1, 1.
        final byte[] ZERO_UID_ONE_GID = {1, 1, 0, 1, 1};
        parseReparse(0, 1, ZERO_UID_ONE_GID, 0, 1);
    }
    @Test
    public void test22() throws ZipException {
        // Version=1, Len=1, 1, Len=1, 0.
        final byte[] ONE_UID_ZERO_GID = {1, 1, 1, 1, 0};
        parseReparse(1, 0, ONE_UID_ZERO_GID, 1, 0);
    }
    @Test
    public void test23() throws ZipException {
        // Version=1, Len=1, 0, Len=1, 0.
        final byte[] ZERO_UID_ZERO_GID = {1, 1, 0, 1, 0};
        parseReparse(0, 0, ZERO_UID_ZERO_GID, 0, 0);
    }
}

    @Test
    public void test24() throws ZipException {
    }

    @Test
    public void test25() throws Exception {
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
    public void test26() throws ZipException {
    }

    @Test
    public void test27() throws ZipException {
    }

    @Test
    public void test28() {
    }

    @Test
    public void test29() throws ZipException {
    }

    // Test case 1
    byte[] testData1 = {}; // Empty input data
    byte[] result1 = getCentralDirectoryData(testData1);
    // Assertion for the result
    
    // Test case 2
    byte[] testData2 = {1, 2, 3, 4}; // Non-empty input data
    byte[] result2 = getCentralDirectoryData(testData2);
    // Assertion for the result

    // Test with negative version number
    @Test
    public void test30() {
        assertThrows(ZipException.class, () -> {
            parseFromLocalFileData(data, 0, data.length);
        });
    }

    // Test with negative uidSize and gidSize
    @Test
    public void test31() {
        assertThrows(ZipException.class, () -> {
            parseFromLocalFileData(data, 0, data.length);
        });
    }

    // Test with negative uid value
    @Test
    public void test32() {
        assertThrows(ZipException.class, () -> {
            parseFromLocalFileData(data, 0, data.length);
        });
    }

    // Test with negative gid value
    @Test
    public void test33() {
        assertThrows(ZipException.class, () -> {
            parseFromLocalFileData(data, 0, data.length);
        });
    }

    // Test with offset greater than length
    @Test
    public void test34() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            parseFromLocalFileData(data, 10, data.length);
        });
    }

    // Test with offset less than 0
    @Test
    public void test35() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            parseFromLocalFileData(data, -5, data.length);
        });
    }

    @Test
    public void test36() throws Exception {
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
                int offset = -100; // negative offset
                int length = buffer.length - offset;
                assertThrows(ZipException.class, () -> xf.parseFromCentralDirectoryData(buffer, offset, length));
            }
        } finally {
            if (zf != null) {
                zf.close();
            }
        }
    }

    @Test
    public void test37() throws Exception {
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
                int offset = 0;
                int length = -100; // negative length
                assertThrows(ZipException.class, () -> xf.parseFromCentralDirectoryData(buffer, offset, length));
            }
        } finally {
            if (zf != null) {
                zf.close();
            }
        }
    }

    @Test
    public void test38() throws Exception {
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
                int offset = 0;
                int length = buffer.length + 100; // large length
                assertThrows(ZipException.class, () -> xf.parseFromCentralDirectoryData(buffer, offset, length));
            }
        } finally {
            if (zf != null) {
                zf.close();
            }
        }
    }

    // Regression test 1
    @Test
    public void test39() {
    }
    
    // Regression test 2
    @Test
    public void test40() throws Exception {
    }
    
    // Regression test 3
    @Test
    public void test41() throws Exception {
    }
    
    // Regression test 4
    @Test
    public void test42() {
    }
    
    // Regression test 5
    @Test
    public void test43() throws Exception {
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
    
    // Regression test 6
    @Test
    public void test44() throws ZipException {
    }

    @Test
    public void test45() {
    }
    
    @Test
    public void test46() throws Exception {
    }
    
    @Test
    public void test47() throws Exception {
    }

@Test
public void test48() throws Exception {
}

@Test
public void test49() throws Exception {
}

@Test
public void test50() throws Exception {
}


    @Test
    public void test51() {
    }
    
    @Test
    public void test52() throws Exception {
    }
    
    @Test
    public void test53() throws Exception {
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
                
                // Regression test 9: Changing input value of expected
                expected = 0;
                assertEquals(expected, xf.getUID());
                assertEquals(expected, xf.getGID());
                
                // Regression test 10: Changing input value of expected
                expected = 100;
                assertEquals(expected, xf.getUID());
                assertEquals(expected, xf.getGID());
                
                // Regression test 11: Changing input value of expected
                expected = 1000;
                assertEquals(expected, xf.getUID());
                assertEquals(expected, xf.getGID());
            }
        } finally {
            if (zf != null) {
                zf.close();
            }
        }
    }
}

    @Test
    public void test54() throws Exception {
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertTrue(xf.equals(o));
        xf.setUID(12345);
        assertFalse(xf.equals(o));
    }
    @Test
    public void test55() throws Exception {
        File archive = getFile("COMPRESS-211_uid_gid_zip_test.zip");
        ZipFile zf = null;

        try {
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
            if (zf != null) {
                zf.close();
            }
        }
    }

    // Regression test case 1
    @Test
    public void test56() {
        xf.setUID(9876543);
        assertEquals(-1234567 * xf.getVersion() ^ Integer.rotateLeft(xf.getUID().hashCode(), 16) ^ xf.getGID().hashCode(), xf.hashCode());
    }

    // Regression test case 2
    @Test
    public void test57() {
        xf.setUID(24681357);
        xf.setGID(13579246);
        assertEquals(-1234567 * xf.getVersion() ^ Integer.rotateLeft(xf.getUID().hashCode(), 16) ^ xf.getGID().hashCode(), xf.hashCode());
    }

    // Regression test case 3
    @Test
    public void test58() {
        xf.setUID(1);
        xf.setGID(1);
        assertEquals(-1234567 * xf.getVersion() ^ Integer.rotateLeft(xf.getUID().hashCode(), 16) ^ xf.getGID().hashCode(), xf.hashCode());
    }

    // Regression test case 4
    @Test
    public void test59() {
        xf.setUID(0);
        xf.setGID(0);
        assertEquals(-1234567 * xf.getVersion() ^ Integer.rotateLeft(xf.getUID().hashCode(), 16) ^ xf.getGID().hashCode(), xf.hashCode());
    }

    @Test
    public void test60() {
        final byte[] ZERO_UID_GID = {0, 0, 0, 0};
        final byte[] THREE_ZEROES = {0, 0, 0};
        final byte[] FIVE_ZEROES = {0, 0, 0, 0, 0};
        final byte[] SEQUENCE7_LEADING_ZEROES = {0, 0, 0, 0, 0, 0, 1, 2, 3};
        final byte[] TRAILING_ZERO2 = {1, 2, 3, 0, 0};
        final byte[] PADDING_ZERO2 = {0, 1, 2, 3, 0, 0};
        final byte[] SEQUENCE7 = {1, 2, 3, 4, 5, 6, 7};

        assertTrue(Arrays.equals(ZERO_UID_GID, trimTest(ZERO_UID_GID)));
        assertTrue(Arrays.equals(THREE_ZEROES, trimTest(THREE_ZEROES)));
        assertTrue(Arrays.equals(FIVE_ZEROES, trimTest(FIVE_ZEROES)));
        assertTrue(Arrays.equals(SEQUENCE7, trimTest(SEQUENCE7_LEADING_ZEROES)));
        assertTrue(Arrays.equals(TRAILING_ZERO2, trimTest(TRAILING_ZERO2)));
        assertTrue(Arrays.equals(TRAILING_ZERO2, trimTest(PADDING_ZERO2)));
        assertTrue(Arrays.equals(SEQUENCE7, trimTest(SEQUENCE7)));
    }

    @Test
    public void test61() throws ZipException {

        // Version=1, Len=0, Len=0.
        final byte[] ZERO_LEN = {0, 0, 0};

        // Version=1, Len=1, zero, Len=1, zero.
        final byte[] TWO_ZEROES = {0, 0, 0, 0};

        // Version=1, Len=5, 2^63 - 2, Len=8, 2^63 - 1
        // Esoteric test:  can we handle 64 bit numbers?
        final byte[] LENGTH_8 = {0, 0, -2, -1, -1, -1, -1, -1, -1, 127, 8, -1, -1, -1, -1, -1, -1, -1, 127};

        final long MIN = -9223372036854775808L;
        final long MAX2 = 9223372036854775807L;

        parseReparse(Long.MIN_VALUE, MAX2, ZERO_LEN, MIN, MAX2);
        parseReparse(Long.MAX_VALUE, Long.MIN_VALUE, TWO_ZEROES, MAX2, MIN);
        parseReparse(MIN, MAX2, LENGTH_8, MIN, MAX2);

        // We never emit this, but we should be able to parse it:
        final byte[] SPURIOUS_ZEROES_1 = {0, 0, -1, 0, 0, 0, 0, -128, 0, 0, 0};
        final byte[] EXPECTED_1 = {0, 0, -1, 0, -128};
        xf.parseFromLocalFileData(SPURIOUS_ZEROES_1, 0, SPURIOUS_ZEROES_1.length);

        assertEquals(255, xf.getUID());
        assertEquals(128, xf.getGID());
        assertTrue(Arrays.equals(EXPECTED_1, xf.getLocalFileDataData()));

        final byte[] SPURIOUS_ZEROES_2 = {0, 0, -1, -1, 0, 0, 0, 1, 2, 0, 0};
        final byte[] EXPECTED_2 = {0, 0, -1, -1, 2, 1, 2};
        xf.parseFromLocalFileData(SPURIOUS_ZEROES_2, 0, SPURIOUS_ZEROES_2.length);

        assertEquals(65535, xf.getUID());
        assertEquals(513, xf.getGID());
        assertTrue(Arrays.equals(EXPECTED_2, xf.getLocalFileDataData()));
    }

    @Test
    public void test62() throws Exception {
        File archive = getFile("COMPRESS-211_uid_gid_zip_test.zip");
        ZipFile zf = null;

        try {
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
                    // 2^63-2 was the biggest UID/GID
                    // (December 2012)
                    expected = Long.MIN_VALUE;
                }
                assertEquals(expected, xf.getUID());
                assertEquals(expected, xf.getGID());
            }
            if (zf != null) {
                zf.close();
            }
        }
    }

}