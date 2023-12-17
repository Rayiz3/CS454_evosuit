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

                // Change the header id to kill more mutants
                assertEquals(X7888, xf.getHeaderId());
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
        // Change the header id to kill more mutants
        assertEquals(X7888, xf.getHeaderId());
    }
    @Test
    public void test2() throws ZipException {
        // Existing test cases ...

        // Additional test cases to kill more mutants

        // Version=1, Len=3, -2^24 - 1, Len=3, -2^24
        // Test case for negative input values
        final byte[] NEGATIVE_INPUT = {1, 3, -1, -1, -1, -128, 3, -1, -1, -1};
        parseReparse(-16777217, -16777216, NEGATIVE_INPUT, -16777217, -16777216);
    }
    @Test
    public void test3() throws ZipException {
        // Version=1, Len=0, Len=0. This should fail mutant killed by testParseReparse
        final byte[] ZERO_LEN = {1, 0, 0};

        // Version=1, Len=1, zero, Len=1, zero. This should fail mutant killed by testParseReparse
        final byte[] ZERO_UID_GID = {1, 1, 0, 1, 0};

        // Version=1, Len=1, one, Len=1, one. This should fail mutant killed by testParseReparse
        final byte[] ONE_UID_GID = {1, 1, 1, 1, 1};

        // Version=1, Len=2, one thousand, Len=2, one thousand. This should fail mutant killed by testParseReparse
        final byte[] ONE_THOUSAND_UID_GID = {1, 2, -24, 3, 2, -24, 3};

        // (2^32 - 2). This should fail mutant killed by testParseReparse
        final byte[] UNIX_MAX_UID_GID = {1, 4, -2, -1, -1, -1, 4, -2, -1, -1, -1};

        // Version=1, Len=5, 2^32, Len=5, 2^32 + 1. This should fail mutant killed by testParseReparse
        final byte[] LENGTH_5 = {1, 5, 0, 0, 0, 0, 1, 5, 1, 0, 0, 0, 1};

        // Version=1, Len=8, 2^63 - 2, Len=8, 2^63 - 1. This should fail mutant killed by testParseReparse
        final byte[] LENGTH_8 = {1, 8, -2, -1, -1, -1, -1, -1, -1, 127, 8, -1, -1, -1, -1, -1, -1, -1, 127};

        // New test cases:

        // Version=1, Len=4, 0, 0, 0, 0, 4, 0, 0, 0, 0. This should fail mutant killed by testParseReparse
        final byte[] FOUR_ZERO_UID_GID = {1, 4, 0, 0, 0, 0, 4, 0, 0, 0, 0};

        // Version=1, Len=4, 0, 0, 0, 0, 4, 1, 0, 0, 0. This should fail mutant killed by testParseReparse
        final byte[] FOUR_ONE_UID_GID = {1, 4, 0, 0, 0, 0, 4, 1, 0, 0, 0};

        // Version=1, Len=4, 1, 0, 0, 0, 4, 0, 0, 0, 0. This should fail mutant killed by testParseReparse
        final byte[] FOUR_ONE_THOUSAND_UID_GID = {1, 4, 1, 0, 0, 0, 4, 0, 0, 0, 0};

        // Version=1, Len=4, 255, 255, 255, 255, 4, 255, 255, 255, 255. This should fail mutant killed by testParseReparse
        final byte[] FOUR_UNIX_MAX_UID_GID = {1, 4, (byte)255, (byte)255, (byte)255, (byte)255, 4, (byte)255, (byte)255, (byte)255, (byte)255};

        // Version=1, Len=9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0. This should fail mutant killed by testParseReparse
        final byte[] NINE_ZERO_UID_GID = {1, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        parseReparse(0, 0, ZERO_LEN, 0, 0);
        parseReparse(0, 0, ZERO_UID_GID, 0, 0);
        parseReparse(1, 1, ONE_UID_GID, 1, 1);
        parseReparse(1000, 1000, ONE_THOUSAND_UID_GID, 1000, 1000);
        parseReparse(MAX, MAX, UNIX_MAX_UID_GID, MAX, MAX);
        parseReparse(-2, -2, UNIX_MAX_UID_GID, MAX, MAX);
        parseReparse(TWO_TO_32, TWO_TO_32 + 1, LENGTH_5, TWO_TO_32, TWO_TO_32 + 1);
        parseReparse(Long.MAX_VALUE - 1, Long.MAX_VALUE, LENGTH_8, Long.MAX_VALUE - 1, Long.MAX_VALUE);
        parseReparse(0, 0, FOUR_ZERO_UID_GID, 0, 0);
        parseReparse(0, 0, FOUR_ONE_UID_GID, 0, 0);
        parseReparse(1, 0, FOUR_ONE_THOUSAND_UID_GID, 0, 0);
        parseReparse(255, 255, FOUR_UNIX_MAX_UID_GID, 255, 255);
        parseReparse(0, 0, NINE_ZERO_UID_GID, 0, 0);
    }
    @Test
    public void test4() throws Exception {
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertTrue(xf.equals(o));
        xf.setUID(0); // Regression test case 1: Changing the input value to 0
        assertFalse(xf.equals(o));
        xf.setUID(-1); // Regression test case 2: Changing the input value to -1
        assertFalse(xf.equals(o));
        xf.setUID(Long.MAX_VALUE); // Regression test case 3: Changing the input value to Long.MAX_VALUE
        assertFalse(xf.equals(o));
    }
    @Test
    public void test5() throws ZipException {

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
        parseReparse(255, 128, LENGTH_5, 255, 128); // Regression test case 4: Changing the input value to 255
        parseReparse(65535, 513, LENGTH_5, 65535, 513); // Regression test case 5: Changing the input value to 65535
        parseReparse(-2, -1, LENGTH_5, MAX, MAX); // Regression test case 6: Changing the input value to -1
        
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
public void test6() {
    // Value = 0
    long value0 = 0;
    obj.setGID(value0);
    assertEquals(ZipUtil.longToBig(value0), obj.getGID());

    // Value = 1
    long value1 = 1;
    obj.setGID(value1);
    assertEquals(ZipUtil.longToBig(value1), obj.getGID());

    // Value = -1
    long valueMinus1 = -1;
    obj.setGID(valueMinus1);
    assertEquals(ZipUtil.longToBig(valueMinus1), obj.getGID());

    // Value = Long.MAX_VALUE
    long valueMax = Long.MAX_VALUE;
    obj.setGID(valueMax);
    assertEquals(ZipUtil.longToBig(valueMax), obj.getGID());

    // Value = Long.MIN_VALUE
    long valueMin = Long.MIN_VALUE;
    obj.setGID(valueMin);
    assertEquals(ZipUtil.longToBig(valueMin), obj.getGID());

    // Value = random positive number
    long valueRandomPos = 12345;
    obj.setGID(valueRandomPos);
    assertEquals(ZipUtil.longToBig(valueRandomPos), obj.getGID());

    // Value = random negative number
    long valueRandomNeg = -56789;
    obj.setGID(valueRandomNeg);
    assertEquals(ZipUtil.longToBig(valueRandomNeg), obj.getGID());
}
    @Test
    public void test7() {
        // Test case 1
        int uidSize1 = 1;
        int gidSize1 = 1;
        // Expected result: 3 + uidSize1 + gidSize1 = 3 + 1 + 1 = 5
        assertEquals(5, getLocalFileDataLength(uidSize1, gidSize1).getValue());

        // Test case 2
        int uidSize2 = 2;
        int gidSize2 = 5;
        // Expected result: 3 + uidSize2 + gidSize2 = 3 + 2 + 5 = 10
        assertEquals(10, getLocalFileDataLength(uidSize2, gidSize2).getValue());

        // Test case 3
        int uidSize3 = 3;
        int gidSize3 = 10;
        // Expected result: 3 + uidSize3 + gidSize3 = 3 + 3 + 10 = 16
        assertEquals(16, getLocalFileDataLength(uidSize3, gidSize3).getValue());
    }
    private ZipShort getLocalFileDataLength(int uidSize, int gidSize) {
        // The 3 comes from: version=1 + uidsize=1 + gidsize=1
        return new ZipShort(3 + uidSize + gidSize);
    }
    @Test
    public void test8() throws ZipException {
        // test input with negative values
        final byte[] NEGATIVE_VALUES = {1, -1, -1};
        parseReparse(-1, -1, NEGATIVE_VALUES, -1, -1);
    }
    @Test
    public void test9() throws ZipException {
        // test input with large positive values
        final byte[] LARGE_VALUES = {1, 127, 127};
        parseReparse(127, 127, LARGE_VALUES, 127, 127);
    }
    @Test
    public void test10() throws ZipException {
        // test input with zeros
        final byte[] ZEROS = {1, 0, 0};
        parseReparse(0, 0, ZEROS, 0, 0);
    }
    @Test
    public void test11() throws ZipException {
        // test input with large values
        final byte[] LARGE_VALUES = {1, 1000, 1000};
        parseReparse(1000, 1000, LARGE_VALUES, 1000, 1000);
    }
    @Test
    public void test12() throws ZipException {
        // test input with maximum value
        final byte[] MAX_VALUE = {1, -2, -1, -1, -1};
        parseReparse(-1, -1, MAX_VALUE, -1, -1);
    }
    @Test
    public void test13() throws ZipException {
        // test input with large values
        final byte[] LARGE_VALUES = {1, 1000, 1000};
        parseReparse(1000, 1000, LARGE_VALUES, 1000, 1000);
    }
    @Test
    public void test14() throws ZipException {
        // test input with esoteric values
        final byte[] ESOTERIC_VALUES = {1, 5, 0, 0, 0, 0, 1};
        parseReparse(0x100000000L, 0x100000001L, ESOTERIC_VALUES, 0x100000000L, 0x100000001L);
    }
    @Test
    public void test15() throws ZipException {
        // test input with esoteric values
        final byte[] ESOTERIC_VALUES = {1, 8, -2, -1, -1, -1, -1, -1, -1, 127};
        parseReparse(Long.MAX_VALUE - 1, Long.MAX_VALUE, ESOTERIC_VALUES, Long.MAX_VALUE - 1, Long.MAX_VALUE);
    }
    @Test
    public void test16() throws ZipException {
        // test input with spurious zeroes
        final byte[] SPURIOUS_ZEROES = {1, 4, -1, 0, 0, 0, 4, -128, 0, 0, 0};
        parseReparse(-1, -128, SPURIOUS_ZEROES, -1, -128);
    }
    @Test
    public void test17() throws ZipException {
        // test input with spurious zeroes
        final byte[] SPURIOUS_ZEROES = {1, 4, -1, -1, 0, 0, 4, 1, 2, 0, 0};
        parseReparse(-1, 513, SPURIOUS_ZEROES, -1, 513);
    }
    @Test
    public void test18() throws Exception {
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

                // Regression test case
                xf.parseFromLocalFileData(null, 0, 0);
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

        // Version=1, Len=8, 2^63 - 2, Len=8, 2^63 - 1
        // Esoteric test:  can we handle 64 bit numbers?
        final byte[] LENGTH_8 = {1, 8, -2, -1, -1, -1, -1, -1, -1, 127, 8, -1, -1, -1, -1, -1, -1, -1, 127};

        final long TWO_TO_32 = 0x100000000L;
        final long MAX = TWO_TO_32 - 2;

        parseReparamRegression(0, 0, ZERO_LEN, 0, 0);
        parseReparamRegression(0, 0, ZERO_UID_GID, 0, 0);
        parseReparamRegression(1, 1, ONE_UID_GID, 1, 1);
        parseReparamRegression(1000, 1000, ONE_THOUSAND_UID_GID, 1000, 1000);
        parseReparamRegression(MAX, MAX, UNIX_MAX_UID_GID, MAX, MAX);
        parseReparamRegression(-2, -2, UNIX_MAX_UID_GID, MAX, MAX);
        parseReparamRegression(Long.MAX_VALUE - 1, Long.MAX_VALUE, LENGTH_8, Long.MAX_VALUE - 1, Long.MAX_VALUE);

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

        // Regression test case
        parseReparamRegression(TWO_TO_32, TWO_TO_32 + 1, LENGTH_5, TWO_TO_32, TWO_TO_32 + 1);
    }
    @Test
    public void test20() {
        byte[] input1 = new byte[5];
        byte[] input2 = new byte[10];
        byte[] input3 = new byte[15];
        
        byte[] result1 = getCentralDirectoryData(input1);
        byte[] result2 = getCentralDirectoryData(input2);
        byte[] result3 = getCentralDirectoryData(input3);
        
        assertNotEquals(result1.length, result2.length);
        assertNotEquals(result2.length, result3.length);
    }
    @Test
    public void test21() {
        byte[] input1 = {1, 2, 3};
        byte[] input2 = {4, 5, 6};
        byte[] input3 = {7, 8, 9};
        
        byte[] result1 = getCentralDirectoryData(input1);
        byte[] result2 = getCentralDirectoryData(input2);
        byte[] result3 = getCentralDirectoryData(input3);
        
        assertNotEquals(result1, result2);
        assertNotEquals(result2, result3);
    }
    @Test
    public void test22() throws Exception {
        byte[] data = {1, 2, 3, 4, 5};
        int offset = 0;
        int length = 5;

        reset();
        this.version = signedByteToUnsignedInt(data[offset++]);
        int uidSize = signedByteToUnsignedInt(data[offset++]);
        byte[] uidBytes = new byte[uidSize];
        System.arraycopy(data, offset, uidBytes, 0, uidSize);
        offset += uidSize;
        this.uid = new BigInteger(1, reverse(uidBytes));

        int gidSize = signedByteToUnsignedInt(data[offset++]);
        byte[] gidBytes = new byte[gidSize];
        System.arraycopy(data, offset, gidBytes, 0, gidSize);
        this.gid = new BigInteger(1, reverse(gidBytes));

        assertEquals(1, version);
        assertEquals(2, uidSize);
        assertArrayEquals(new byte[]{3, 4}, uidBytes);
        assertEquals(5, gidSize);
        assertArrayEquals(new byte[]{}, gidBytes);
    }
    @Test
    public void test23() throws Exception {
        byte[] data = {1, 2, 2, 1, 1, 3};
        int offset = 0;
        int length = 6;

        reset();
        this.version = signedByteToUnsignedInt(data[offset++]);
        int uidSize = signedByteToUnsignedInt(data[offset++]);
        byte[] uidBytes = new byte[uidSize];
        System.arraycopy(data, offset, uidBytes, 0, uidSize);
        offset += uidSize;
        this.uid = new BigInteger(1, reverse(uidBytes));

        int gidSize = signedByteToUnsignedInt(data[offset++]);
        byte[] gidBytes = new byte[gidSize];
        System.arraycopy(data, offset, gidBytes, 0, gidSize);
        this.gid = new BigInteger(1, reverse(gidBytes));

        assertEquals(1, version);
        assertEquals(2, uidSize);
        assertArrayEquals(new byte[]{2, 1}, uidBytes);
        assertEquals(1, gidSize);
        assertArrayEquals(new byte[]{3}, gidBytes);
    }
    @Test
    public void test24() throws Exception {
        byte[] data = {2, 1, 1, 1};
        int offset = 0;
        int length = 4;

        reset();
        this.version = signedByteToUnsignedInt(data[offset++]);
        int uidSize = signedByteToUnsignedInt(data[offset++]);
        byte[] uidBytes = new byte[uidSize];
        System.arraycopy(data, offset, uidBytes, 0, uidSize);
        offset += uidSize;
        this.uid = new BigInteger(1, reverse(uidBytes));

        int gidSize = signedByteToUnsignedInt(data[offset++]);
        byte[] gidBytes = new byte[gidSize];
        System.arraycopy(data, offset, gidBytes, 0, gidSize);
        this.gid = new BigInteger(1, reverse(gidBytes));

        assertEquals(2, version);
        assertEquals(1, uidSize);
        assertArrayEquals(new byte[]{1}, uidBytes);
        assertEquals(1, gidSize);
        assertArrayEquals(new byte[]{1}, gidBytes);
    }
    @Test
    public void test25() throws Exception {
        byte[] data = {1, 2, 2, 0, 0};
        int offset = 0;
        int length = 5;

        reset();
        this.version = signedByteToUnsignedInt(data[offset++]);
        int uidSize = signedByteToUnsignedInt(data[offset++]);
        byte[] uidBytes = new byte[uidSize];
        System.arraycopy(data, offset, uidBytes, 0, uidSize);
        offset += uidSize;
        this.uid = new BigInteger(1, reverse(uidBytes));

        int gidSize = signedByteToUnsignedInt(data[offset++]);
        byte[] gidBytes = new byte[gidSize];
        System.arraycopy(data, offset, gidBytes, 0, gidSize);
        this.gid = new BigInteger(1, reverse(gidBytes));

        assertEquals(1, version);
        assertEquals(2, uidSize);
        assertArrayEquals(new byte[]{2, 0}, uidBytes);
        assertEquals(0, gidSize);
        assertArrayEquals(new byte[]{}, gidBytes);
    }
    @Test
    public void test26() throws Exception {
        byte[] data = {1, 4, -1, -1, -1};
        int offset = 0;
        int length = 5;

        reset();
        this.version = signedByteToUnsignedInt(data[offset++]);
        int uidSize = signedByteToUnsignedInt(data[offset++]);
        byte[] uidBytes = new byte[uidSize];
        System.arraycopy(data, offset, uidBytes, 0, uidSize);
        offset += uidSize;
        this.uid = new BigInteger(1, reverse(uidBytes));

        int gidSize = signedByteToUnsignedInt(data[offset++]);
        byte[] gidBytes = new byte[gidSize];
        System.arraycopy(data, offset, gidBytes, 0, gidSize);
        this.gid = new BigInteger(1, reverse(gidBytes));

        assertEquals(1, version);
        assertEquals(4, uidSize);
        assertArrayEquals(new byte[]{-1, -1, -1}, uidBytes);
        assertEquals(0, gidSize);
        assertArrayEquals(new byte[]{}, gidBytes);
    }
    @Test
    public void test27() throws Exception {
        byte[] data = {1, 4, -1, -1, -1, 4, -1, -1, -1};
        int offset = 0;
        int length = 9;

        reset();
        this.version = signedByteToUnsignedInt(data[offset++]);
        int uidSize = signedByteToUnsignedInt(data[offset++]);
        byte[] uidBytes = new byte[uidSize];
        System.arraycopy(data, offset, uidBytes, 0, uidSize);
        offset += uidSize;
        this.uid = new BigInteger(1, reverse(uidBytes));

        int gidSize = signedByteToUnsignedInt(data[offset++]);
        byte[] gidBytes = new byte[gidSize];
        System.arraycopy(data, offset, gidBytes, 0, gidSize);
        this.gid = new BigInteger(1, reverse(gidBytes));

        assertEquals(1, version);
        assertEquals(4, uidSize);
        assertArrayEquals(new byte[]{-1, -1, -1}, uidBytes);
        assertEquals(4, gidSize);
        assertArrayEquals(new byte[]{-1, -1, -1}, gidBytes);
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
    public void test29() throws Exception {
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
    uid = 0;
    gid = 0;

    reset();

    assertEquals(1000, uid);
    assertEquals(1000, gid);
}
@Test
public void test36() {
    uid = 500;
    gid = 500;

    reset();

    assertEquals(1000, uid);
    assertEquals(1000, gid);
}
@Test
public void test37() {
    uid = -500;
    gid = -500;

    reset();

    assertEquals(1000, uid);
    assertEquals(1000, gid);
}
@Test
public void test38() {
    uid = Long.MAX_VALUE;

    reset();

    assertEquals(1000, uid);
    assertEquals(1000, gid);
}
@Test
public void test39() {
    gid = Long.MAX_VALUE;

    reset();

    assertEquals(1000, uid);
    assertEquals(1000, gid);
}
@Test
public void test40() {
    ZipExtraField xf = new ZipExtraField();
    xf.setUID(54321);
    xf.setGID(98765);
    String expected = "0x7875 Zip Extra Field: UID=54321 GID=98765";
    String actual = xf.toString();
    assertEquals(expected, actual);
}
@Test
public void test41() {
    ZipExtraField xf = new ZipExtraField();
    xf.setUID(54321);
    String expected = "0x7875 Zip Extra Field: UID=54321 GID=12345";
    String actual = xf.toString();
    assertEquals(expected, actual);
}
@Test
public void test42() {
    ZipExtraField xf = new ZipExtraField();
    xf.setGID(98765);
    String expected = "0x7875 Zip Extra Field: UID=12345 GID=98765";
    String actual = xf.toString();
    assertEquals(expected, actual);
}
    @Test
    public void test43() throws Exception {
        // Original test case
        Object o1 = xf.clone();
        assertTrue(xf.equals(o1));

        // Mutant killing test cases
        Object o2 = new XfImpl();
        assertFalse(xf.equals(o2));

        Object o3 = null;
        assertFalse(xf.equals(o3));

        Object o4 = new Random();
        assertFalse(xf.equals(o4));
    }
@Test
public void test44() {
    X7875_NewUnix xf1 = new X7875_NewUnix(1, "uid", "gid");
    X7875_NewUnix xf2 = new X7875_NewUnix(2, "uid", "gid");
    
    assertFalse(xf1.equals(xf2));
}
@Test
public void test45() {
    X7875_NewUnix xf1 = new X7875_NewUnix(1, "uid1", "gid");
    X7875_NewUnix xf2 = new X7875_NewUnix(1, "uid2", "gid");
    
    assertFalse(xf1.equals(xf2));
}
@Test
public void test46() {
    X7875_NewUnix xf1 = new X7875_NewUnix(1, "uid", "gid1");
    X7875_NewUnix xf2 = new X7875_NewUnix(1, "uid", "gid2");
    
    assertFalse(xf1.equals(xf2));
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
            }
        } finally {
            if (zf != null) {
                zf.close();
            }
        }
    }
    @Test
    public void test48() throws Exception {
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertTrue(xf.equals(o));
        xf.setUID(12345);
        assertFalse(xf.equals(o));
    }
    @Test
    public void test49() throws Exception {
        X7875_NewUnix xf = new X7875_NewUnix();
        xf.setVersion(0);
        xf.setUID(100);
        xf.setGID(200);
        int expected = -1234567 * xf.getVersion() ^
                Integer.rotateLeft(xf.getUID().hashCode(), 16) ^
                xf.getGID().hashCode();
        assertEquals(expected, xf.hashCode());
    }
    @Test
    public void test50() throws Exception {
        X7875_NewUnix xf = new X7875_NewUnix();
        xf.setVersion(1);
        xf.setUID(-100);
        xf.setGID(200);
        int expected = -1234567 * xf.getVersion() ^
                Integer.rotateLeft(xf.getUID().hashCode(), 16) ^
                xf.getGID().hashCode();
        assertEquals(expected, xf.hashCode());
    }
    @Test
    public void test51() throws Exception {
        X7875_NewUnix xf = new X7875_NewUnix();
        xf.setVersion(1);
        xf.setUID(100);
        xf.setGID(-200);
        int expected = -1234567 * xf.getVersion() ^
                Integer.rotateLeft(xf.getUID().hashCode(), 16) ^
                xf.getGID().hashCode();
        assertEquals(expected, xf.hashCode());
    }
    @Test
    public void test52() throws Exception {
        X7875_NewUnix xf = new X7875_NewUnix();
        xf.setVersion(1);
        xf.setUID(0);
        xf.setGID(0);
        int expected = -1234567 * xf.getVersion() ^
                Integer.rotateLeft(xf.getUID().hashCode(), 16) ^
                xf.getGID().hashCode();
        assertEquals(expected, xf.hashCode());
    }
    @Test
    public void test53() throws Exception {
        X7875_NewUnix xf = new X7875_NewUnix();
        xf.setVersion(1);
        xf.setUID(1000000);
        xf.setGID(2000000);
        int expected = -1234567 * xf.getVersion() ^
                Integer.rotateLeft(xf.getUID().hashCode(), 16) ^
                xf.getGID().hashCode();
        assertEquals(expected, xf.hashCode());
    }
    @Test
    public void test54() throws Exception {
        X7875_NewUnix xf = new X7875_NewUnix();
        xf.setVersion(1);
        xf.setUID(0x100000000L - 2);
        xf.setGID(0x100000000L - 2);
        int expected = -1234567 * xf.getVersion() ^
                Integer.rotateLeft(xf.getUID().hashCode(), 16) ^
                xf.getGID().hashCode();
        assertEquals(expected, xf.hashCode());
    }
    @Test
    public void test55() throws ZipException {

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

        parseReparse(99, 99, ZERO_LEN, 99, 99);
        parseReparse(99, 99, ZERO_UID_GID, 99, 99);
        parseReparse(99, 99, ONE_UID_GID, 99, 99);
        parseReparse(999, 999, ONE_THOUSAND_UID_GID, 999, 999);
        parseReparse(99, 99, UNIX_MAX_UID_GID, 99, 99);
        parseReparse(99, 99, LENGTH_5, 99, 99);
        parseReparse(99, 99, LENGTH_8, 99, 99);

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
}