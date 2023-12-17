package org.apache.commons.codec.binary;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import java.util.Arrays;
import org.apache.commons.codec.Charsets;
import org.junit.Test;

public class Base32_LLMTest {
    @Test
    public void test0() {
        // Test case 1: Test with empty input
        final Base32 codec1 = new Base32(true);
        final byte[][] b1 = Base32TestData.randomData(codec1, 0);
        assertEquals("" + 0 + " " + codec1.lineLength, b1[1].length, codec1.getEncodedLength(b1[0]));

        // Test case 2: Test with non-empty input
        final Base32 codec2 = new Base32(true);
        final byte[][] b2 = Base32TestData.randomData(codec2, 10);
        assertEquals("" + 10 + " " + codec2.lineLength, b2[1].length, codec2.getEncodedLength(b2[0]));

        // Test case 3: Test with line length of 0
        final Base32 codec3 = new Base32(true, 0);
        final byte[][] b3 = Base32TestData.randomData(codec3, 20);
        assertEquals("" + 20 + " " + codec3.lineLength, b3[1].length, codec3.getEncodedLength(b3[0]));

        // Test case 4: Test with line length of 10
        final Base32 codec4 = new Base32(true, 10);
        final byte[][] b4 = Base32TestData.randomData(codec4, 50);
        assertEquals("" + 50 + " " + codec4.lineLength, b4[1].length, codec4.getEncodedLength(b4[0]));
    }
    @Test
    public void test1() throws Exception {
        // Test case 1: Test with empty input
        final Base32 codec1 = new Base32(true);
        final String[] element1 = new String[] { "" };
        assertEquals("", codec1.encodeAsString(element1[0].getBytes(Charsets.UTF_8)));

        // Test case 2: Test with non-empty input
        final Base32 codec2 = new Base32(true);
        final String[] element2 = new String[] { "ABCDEF" };
        assertEquals("6162636465", codec2.encodeAsString(element2[0].getBytes(Charsets.UTF_8)));
    }
    @Test
    public void test2() {
        // Test case 1: Test with empty input
        final Base32 codec1 = new Base32();
        final BaseNCodec.Context context1 = new BaseNCodec.Context();
        final byte[] unencoded1 = new byte[0];
        final byte[] allInOne1 = codec1.encode(unencoded1);
        assertArrayEquals(allInOne1, codec1.encode(unencoded1, context1));

        // Test case 2: Test with non-empty input
        final Base32 codec2 = new Base32();
        final BaseNCodec.Context context2 = new BaseNCodec.Context();
        final byte[] unencoded2 = new byte[] { 1, 2, 3, 4, 5 };
        final byte[] allInOne2 = codec2.encode(unencoded2);
        assertArrayEquals(allInOne2, codec2.encode(unencoded2, context2));
    }
    @Test
    public void test3() {
        // Test case 1: Test with empty input
        final Base32 codec1 = new Base32(10);
        final byte[][] b1 = Base32TestData.randomData(codec1, 0);
        assertEquals("" + 0 + " " + codec1.lineLength, b1[1].length, codec1.getEncodedLength(b1[0]));

        // Test case 2: Test with non-empty input
        final Base32 codec2 = new Base32(10);
        final byte[][] b2 = Base32TestData.randomData(codec2, 10);
        assertEquals("" + 10 + " " + codec2.lineLength, b2[1].length, codec2.getEncodedLength(b2[0]));

        // Test case 3: Test with line length of 0
        final Base32 codec3 = new Base32(0);
        final byte[][] b3 = Base32TestData.randomData(codec3, 20);
        assertEquals("" + 20 + " " + codec3.lineLength, b3[1].length, codec3.getEncodedLength(b3[0]));

        // Test case 4: Test with line length of 10
        final Base32 codec4 = new Base32(10);
        final byte[][] b4 = Base32TestData.randomData(codec4, 50);
        assertEquals("" + 50 + " " + codec4.lineLength, b4[1].length, codec4.getEncodedLength(b4[0]));
    }
    @Test
    public void test4() throws Exception {
        // Test case 1: Test with empty input
        final Base32 codec1 = new Base32(20);
        final String[] element1 = new String[] { "" };
        assertEquals("", codec1.encodeAsString(element1[0].getBytes(Charsets.UTF_8)));

        // Test case 2: Test with non-empty input
        final Base32 codec2 = new Base32(20);
        final String[] element2 = new String[] { "ABCDEFGH" };
        assertEquals("4142434445464748", codec2.encodeAsString(element2[0].getBytes(Charsets.UTF_8)));
    }
    @Test
    public void test5() throws Exception {
        // Test case 1: Test with empty input
        final Base32 codec1 = new Base32((byte)0x25);
        final String[] element1 = new String[] { "" };
        assertEquals("", codec1.encodeAsString(element1[0].getBytes(Charsets.UTF_8)));

        // Test case 2: Test with non-empty input
        final Base32 codec2 = new Base32((byte)0x25);
        final String[] element2 = new String[] { "ABCDEFGH" };
        assertEquals("4142434445464748", codec2.encodeAsString(element2[0].getBytes(Charsets.UTF_8)));
    }
    @Test
    public void test6() throws Exception {
        // Test case 1: Test with empty input
        final Base32 codec1 = new Base32();
        final String[] element1 = new String[] { "" };
        assertEquals("", codec1.encodeAsString(element1[0].getBytes(Charsets.UTF_8)));

        // Test case 2: Test with non-empty input
        final Base32 codec2 = new Base32();
        final String[] element2 = new String[] { "ABCDEFGH" };
        assertEquals("4142434445464748", codec2.encodeAsString(element2[0].getBytes(Charsets.UTF_8)));
    }
    @Test
    public void test7() {
        // Test case 1: Test with empty input
        final Base32 codec1 = new Base32();
        final byte[][] b1 = Base32TestData.randomData(codec1, 0);
        assertEquals("" + 0 + " " + codec1.lineLength, b1[1].length, codec1.getEncodedLength(b1[0]));

        // Test case 2: Test with non-empty input
        final Base32 codec2 = new Base32();
        final byte[][] b2 = Base32TestData.randomData(codec2, 10);
        assertEquals("" + 10 + " " + codec2.lineLength, b2[1].length, codec2.getEncodedLength(b2[0]));
    }
    @Test
    public void test8() {
        for (int i = 0; i < 20; i++) {
            final Base32 codec = new Base32(true);
            final byte[][] b = Base32TestData.randomData(codec, i);
            assertEquals(""+i+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
            //assertEquals(b[0],codec.decode(b[1]));
        }
    }
    @Test
    public void test9() throws Exception {
        final Base32 codec = new Base32(true);
        for (final String[] element : BASE32HEX_TEST_CASES) {
                assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));
        }
    }
    @Test
    public void test10() {
        for (int i = 0; i < 20; i++) {
            Base32 codec = new Base32();
            final BaseNCodec.Context context = new BaseNCodec.Context();
            final byte unencoded[] = new byte[i];
            final byte allInOne[] = codec.encode(unencoded);
            codec = new Base32();
            for (int j=0; j< unencoded.length; j++) {
                codec.encode(unencoded, j, 1, context);
            }
            codec.encode(unencoded, 0, -1, context);
            final byte singly[] = new byte[allInOne.length];
            codec.readResults(singly, 0, 100, context);
            if (!Arrays.equals(allInOne, singly)){
                fail();
            }
        }
    }
    @Test
    public void test11() {
        for (int i = 0; i < 20; i++) {
            final Base32 codec = new Base32(10);
            final byte[][] b = Base32TestData.randomData(codec, i);
            assertEquals(""+i+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
            //assertEquals(b[0],codec.decode(b[1]));
        }
    }
    @Test
    public void test12() throws Exception {
        final Base32 codec = new Base32(20);
        for (final String[] element : BASE32_TEST_CASES_CHUNKED) {
                assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));
        }
    }
    @Test
    public void test13() throws Exception {
        final Base32 codec = new Base32((byte)0x25); // '%' <=> 0x25

        for (final String[] element : BASE32_PAD_TEST_CASES) {
                assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));
        }
    }
    @Test
    public void test14() throws Exception {
        final Base32 codec = new Base32();
        for (final String[] element : BASE32_TEST_CASES) {
                assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));
        }
    }
    @Test
    public void test15() {
        for (int i = 0; i < 20; i++) {
            final Base32 codec = new Base32();
            final byte[][] b = Base32TestData.randomData(codec, i);
            assertEquals(""+i+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
            //assertEquals(b[0],codec.decode(b[1]));
        }
    }
    @Test
    public void test16() {
        final Base32 codec = new Base32(true, (byte)'W'); // should be allowed
        assertNotNull(codec);
    }
    @Test
    public void test17() {
        final Base32 codec = new Base32(true);
        byte octet = -1;
        assertFalse(codec.isInAlphabet(octet));
    }
    @Test
    public void test18() {
        final Base32 codec = new Base32(true);
        byte octet = (byte) codec.decodeTable.length;
        assertFalse(codec.isInAlphabet(octet));
    }
    @Test
    public void test19() {
        final Base32 codec = new Base32(true);
        byte octet = 10;
        assertFalse(codec.isInAlphabet(octet));
    }
    @Test
    public void test20() {
        final Base32 codec = new Base32(true);
        byte octet = 0;
        assertTrue(codec.isInAlphabet(octet));
    }
}