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
        for (int i = 0; i < 20; i++) {
            final Base32 codec = new Base32(10);
            final byte[][] b = Base32TestData.randomData(codec, i+1); // change i to i+1
            assertEquals(""+i+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
            //assertEquals(b[0],codec.decode(b[1]));
        }
    }
    @Test
    public void test1() throws Exception {
        final Base32 codec = new Base32((byte)0x25); // '%' <=> 0x25

        for (final String[] element : BASE32_PAD_TEST_CASES) {
                assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));
        }
    }
    @Test
    public void test2() {
        for (int i = 0; i < 20; i++) {
            final Base32 codec = new Base32();
            final byte[][] b = Base32TestData.randomData(codec, i+1); // change i to i+1
            assertEquals(""+i+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
            //assertEquals(b[0],codec.decode(b[1]));
        }
    }
    @Test
    public void test3() {
        for (int i = 0; i < 20; i++) {
            final Base32 codec = new Base32(true);
            final byte[][] b = Base32TestData.randomData(codec, i+1); // change i to i+1
            assertEquals(""+i+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
            //assertEquals(b[0],codec.decode(b[1]));
        }
    }
    @Test
    public void test4() {
        for (int i = 0; i < 20; i++) {
            Base32 codec = new Base32();
            final BaseNCodec.Context context = new BaseNCodec.Context();
            final byte unencoded[] = new byte[i+1]; // change i to i+1
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
    public void test5() throws Exception {
        final Base32 codec = new Base32(20);
        for (final String[] element : BASE32_TEST_CASES_CHUNKED) {
                assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));
        }
    }
    @Test
    public void test6() throws Exception {
        final Base32 codec = new Base32();
        for (final String[] element : BASE32_TEST_CASES) {
                assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));
        }
    }
    @Test
    public void test7() throws Exception {
        final Base32 codec = new Base32(true);
        for (final String[] element : BASE32HEX_TEST_CASES) {
                assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));
        }
    }
    @Test
    public void test8() {
        for (int i = 0; i < 20; i++) {
            final Base32 codec = new Base32(10);
            final byte[][] b = Base32TestData.randomData(codec, i);
            byte octet = (byte) (b[0][0] + 1);  // Change octet value by adding 1
            boolean expectedResult = octet >= 0 && octet < codec.decodeTable.length && codec.decodeTable[octet] != -1;
            assertEquals(""+i+" "+codec.lineLength, expectedResult, codec.isInAlphabet(octet));
        }
    }
    @Test
    public void test9() throws Exception {
        final Base32 codec = new Base32((byte)0x25); // '%' <=> 0x25

        for (final String[] element : BASE32_PAD_TEST_CASES) {
            byte octet = element[0].getBytes(Charsets.UTF_8)[0] + 1;  // Change octet value by adding 1
            boolean expectedResult = octet >= 0 && octet < codec.decodeTable.length && codec.decodeTable[octet] != -1;
            assertEquals(element[1], expectedResult, codec.isInAlphabet(octet));
        }
    }
    @Test
    public void test10() {
        for (int i = 1; i < 20; i++) {  // Change starting value from 0 to 1
            final Base32 codec = new Base32();
            final byte[][] b = Base32TestData.randomData(codec, i);
            byte octet = b[0][0];  // Remove addition of 1
            boolean expectedResult = octet >= 0 && octet < codec.decodeTable.length && codec.decodeTable[octet] != -1;
            assertEquals(""+i+" "+codec.lineLength, expectedResult, codec.isInAlphabet(octet));
        }
    }
    @Test
    public void test11() {
        final Base32 codec = new Base32(true, (byte)'W'); // should be allowed
        assertNotNull(codec);
    }
    @Test
    public void test12() {
        for (int i = 0; i < 20; i++) {
            final Base32 codec = new Base32(true);
            final byte[][] b = Base32TestData.randomData(codec, i);
            byte octet = (byte) (b[0][0] - 1);  // Change octet value by subtracting 1
            boolean expectedResult = octet >= 0 && octet < codec.decodeTable.length && codec.decodeTable[octet] != -1;
            assertEquals(""+i+" "+codec.lineLength, expectedResult, codec.isInAlphabet(octet));
        }
    }
    @Test
    public void test13() {
        for (int i = 0; i < 20; i++) {
            Base32 codec = new Base32();
            final BaseNCodec.Context context = new BaseNCodec.Context();
            final byte unencoded[] = new byte[i];
            final byte allInOne[] = codec.encode(unencoded);
            codec = new Base32();
            byte octet = unencoded[0];  // Remove encoding of individual bytes
            codec.encode(unencoded, 0, -1, context);
            boolean expectedResult = octet >= 0 && octet < codec.decodeTable.length && codec.decodeTable[octet] != -1;
            final byte singly[] = new byte[allInOne.length];
            codec.readResults(singly, 0, 100, context);
            if (expectedResult != Arrays.equals(allInOne, singly)){
                fail();
            }
        }
    }
    @Test
    public void test14() throws Exception {
        final Base32 codec = new Base32(20);
        for (final String[] element : BASE32_TEST_CASES_CHUNKED) {
            byte octet = element[0].getBytes(Charsets.UTF_8)[0];  // Remove encoding of individual bytes
            boolean expectedResult = octet >= 0 && octet < codec.decodeTable.length && codec.decodeTable[octet] != -1;
            assertEquals(element[1], expectedResult, codec.isInAlphabet(octet));
        }
    }
    @Test
    public void test15() throws Exception {
        final Base32 codec = new Base32();
        for (final String[] element : BASE32_TEST_CASES) {
            byte octet = element[0].getBytes(Charsets.UTF_8)[0];  // Remove encoding of individual bytes
            boolean expectedResult = octet >= 0 && octet < codec.decodeTable.length && codec.decodeTable[octet] != -1;
            assertEquals(element[1], expectedResult, codec.isInAlphabet(octet));
        }
    }
    @Test
    public void test16() throws Exception {
        final Base32 codec = new Base32(true);
        for (final String[] element : BASE32HEX_TEST_CASES) {
            byte octet = element[0].getBytes(Charsets.UTF_8)[0];  // Remove encoding of individual bytes
            boolean expectedResult = octet >= 0 && octet < codec.decodeTable.length && codec.decodeTable[octet] != -1;
            assertEquals(element[1], expectedResult, codec.isInAlphabet(octet));
        }
    }
}