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
        Base32 codec = new Base32();
        final byte[][] b = Base32TestData.emptyData(codec);
        assertEquals(0, codec.getEncodedLength(b[0]));
    }
    @Test
    public void test1() {
        Base32 codec = new Base32();
        final byte[] bytes = {-1, -2, -3};
        final byte[] encodedBytes = codec.encode(bytes);
        assertNotEquals(bytes, encodedBytes);
    }
    @Test
    public void test2() {
        for (int i = 0; i < 20; i++) {
            final Base32 codec = new Base32(true);
            final byte[][] b = Base32TestData.randomData(codec, i);
            assertEquals(""+i+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
            //assertEquals(b[0],codec.decode(b[1]));
        }
        // Additional test case
        Base32 codec = new Base32(true);
        final byte[][] b = Base32TestData.randomData(codec, 10);
        assertEquals(b[1].length, codec.getEncodedLength(b[0]));
    }
    @Test
    public void test3() throws Exception {
        final Base32 codec = new Base32(true);
        for (final String[] element : BASE32HEX_TEST_CASES) {
                assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));
        }
        // Additional test case
        final String[] element = {"Hello", "32b2"};
        assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));
    }
    @Test
    public void test4() {
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
        // Additional test case
        Base32 codec = new Base32();
        final BaseNCodec.Context context = new BaseNCodec.Context();
        final byte unencoded[] = {10, 20};
        final byte allInOne[] = codec.encode(unencoded);
        codec = new Base32(codec.lineLength);
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
    @Test
    public void test5() {
        for (int i = 0; i < 20; i++) {
            final Base32 codec = new Base32(10);
            final byte[][] b = Base32TestData.randomData(codec, i);
            assertEquals(""+i+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
            //assertEquals(b[0],codec.decode(b[1]));
        }
        // Additional test case
        final Base32 codec = new Base32(10);
        final byte[][] b = Base32TestData.randomData(codec, 8);
        assertEquals(""+10+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
    }
    @Test
    public void test6() throws Exception {
        final Base32 codec = new Base32(20);
        for (final String[] element : BASE32_TEST_CASES_CHUNKED) {
                assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));
        }
        // Additional test case
        final String[] element = {"Hello", "MWM2"};
        assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));
    }
    @Test
    public void test7() throws Exception {
        final Base32 codec = new Base32((byte)0x25); // '%' <=> 0x25

        for (final String[] element : BASE32_PAD_TEST_CASES) {
                assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));
        }
        // Additional test case
        final String[] element = {"Base32", "%U"};
        assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));
    }
    @Test
    public void test8() throws Exception {
        final Base32 codec = new Base32();
        for (final String[] element : BASE32_TEST_CASES) {
                assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));
        }
        // Additional test case
        final String[] element = {"Hello World", "NBSWY3DP"};
        assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));
    }
    @Test
    public void test9() {
        for (int i = 0; i < 20; i++) {
            final Base32 codec = new Base32();
            final byte[][] b = Base32TestData.randomData(codec, i);
            assertEquals(""+i+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
            //assertEquals(b[0],codec.decode(b[1]));
        }
        // Additional test case
        final Base32 codec = new Base32();
        final byte[][] b = Base32TestData.randomData(codec, 9);
        assertEquals(""+9+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
    }
    @Test
    public void test10() {
        for (int i = 0; i < 20; i++) {
            final Base32 codec = new Base32(true);
            final byte[][] b = Base32TestData.randomData(codec, i);
            assertEquals(""+i+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
            //assertEquals(b[0],codec.decode(b[1]));

            // Regression Test 1: octet is less than 0
            int mutatedOctet1 = -1;
            assertFalse(codec.isInAlphabet((byte) mutatedOctet1));
            
            // Regression Test 2: octet is equal to 0
            int mutatedOctet2 = 0;
            assertTrue(codec.isInAlphabet((byte) mutatedOctet2));

            // Regression Test 3: octet is between 0 and decodeTable.length but decodeTable[octet] is -1
            int mutatedOctet3 = 1;
            codec.decodeTable[mutatedOctet3] = -1;
            assertFalse(codec.isInAlphabet((byte) mutatedOctet3));

            // Regression Test 4: octet is greater than or equal to decodeTable.length
            int mutatedOctet4 = codec.decodeTable.length;
            assertFalse(codec.isInAlphabet((byte) mutatedOctet4));
        }
    }
    @Test
    public void test11() throws Exception {
        final Base32 codec = new Base32(true);
        for (final String[] element : BASE32HEX_TEST_CASES) {
                assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));

                // Regression Test 5: element[0].getBytes(Charsets.UTF_8) is empty
                assertEquals("", codec.encodeAsString("".getBytes(Charsets.UTF_8)));

                // Regression Test 6: element[0].getBytes(Charsets.UTF_8) contains only whitespace characters
                assertEquals("", codec.encodeAsString("     ".getBytes(Charsets.UTF_8)));
        }
    }
    @Test
    public void test12() {
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

            // Regression Test 7: unencoded is empty
            byte[] mutatedUnencoded1 = new byte[0];
            byte[] mutatedAllInOne1 = codec.encode(mutatedUnencoded1);
            assertEquals("", new String(codec.decode(mutatedAllInOne1)));

            // Regression Test 8: unencoded contains only whitespace characters
            byte[] mutatedUnencoded2 = "     ".getBytes(Charsets.UTF_8);
            byte[] mutatedAllInOne2 = codec.encode(mutatedUnencoded2);
            assertEquals("", new String(codec.decode(mutatedAllInOne2)));
        }
    }
    @Test
    public void test13() {
        for (int i = 0; i < 20; i++) {
            final Base32 codec = new Base32(10);
            final byte[][] b = Base32TestData.randomData(codec, i);
            assertEquals(""+i+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
            //assertEquals(b[0],codec.decode(b[1]));

            // Regression Test 9: chunkSize is less than 0
            int mutatedChunkSize1 = -1;
            Base32 mutatedCodec1 = new Base32(mutatedChunkSize1);
            assertEquals(b[1].length,mutatedCodec1.getEncodedLength(b[0]));

            // Regression Test 10: chunkSize is equal to 0
            int mutatedChunkSize2 = 0;
            Base32 mutatedCodec2 = new Base32(mutatedChunkSize2);
            assertEquals(b[1].length,mutatedCodec2.getEncodedLength(b[0]));

            // Regression Test 11: chunkSize is greater than the length of b[1]
            int mutatedChunkSize3 = b[1].length + 1;
            Base32 mutatedCodec3 = new Base32(mutatedChunkSize3);
            assertEquals(b[1].length,mutatedCodec3.getEncodedLength(b[0]));
        }
    }
    @Test
    public void test14() throws Exception {
        final Base32 codec = new Base32(20);
        for (final String[] element : BASE32_TEST_CASES_CHUNKED) {
                assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));

                // Regression Test 12: element[0].getBytes(Charsets.UTF_8) is empty
                assertEquals("", codec.encodeAsString("".getBytes(Charsets.UTF_8)));

                // Regression Test 13: element[0].getBytes(Charsets.UTF_8) contains only whitespace characters
                assertEquals("", codec.encodeAsString("     ".getBytes(Charsets.UTF_8)));
        }
    }
    @Test
    public void test15() throws Exception {
        final Base32 codec = new Base32((byte)0x25); // '%' <=> 0x25

        for (final String[] element : BASE32_PAD_TEST_CASES) {
                assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));

                // Regression Test 14: element[0].getBytes(Charsets.UTF_8) is empty
                assertEquals("", codec.encodeAsString("".getBytes(Charsets.UTF_8)));

                // Regression Test 15: element[0].getBytes(Charsets.UTF_8) contains only whitespace characters
                assertEquals("", codec.encodeAsString("     ".getBytes(Charsets.UTF_8)));
        }
    }
    @Test
    public void test16() throws Exception {
        final Base32 codec = new Base32();
        for (final String[] element : BASE32_TEST_CASES) {
                assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));

                // Regression Test 16: element[0].getBytes(Charsets.UTF_8) is empty
                assertEquals("", codec.encodeAsString("".getBytes(Charsets.UTF_8)));

                // Regression Test 17: element[0].getBytes(Charsets.UTF_8) contains only whitespace characters
                assertEquals("", codec.encodeAsString("     ".getBytes(Charsets.UTF_8)));
        }
    }
    @Test
    public void test17() {
        for (int i = 0; i < 20; i++) {
            final Base32 codec = new Base32();
            final byte[][] b = Base32TestData.randomData(codec, i);
            assertEquals(""+i+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
            //assertEquals(b[0],codec.decode(b[1]));

            // Regression Test 18: b[0] is empty
            byte[] mutatedB1 = new byte[0];
            assertEquals(0, codec.getEncodedLength(mutatedB1));

            // Regression Test 19: b[0] contains only whitespace characters
            byte[] mutatedB2 = "     ".getBytes(Charsets.UTF_8);
            assertEquals(0, codec.getEncodedLength(mutatedB2));
        }
    }
    @Test
    public void test18() {
        final Base32 codec = new Base32(true, (byte)'W'); // should be allowed
        assertNotNull(codec);
    }
}