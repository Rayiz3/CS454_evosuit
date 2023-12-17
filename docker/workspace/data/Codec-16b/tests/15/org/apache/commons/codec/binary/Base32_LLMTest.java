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
            final Base32 codec = new Base32(true);
            final byte[][] b = Base32TestData.randomData(codec, i);
            assertEquals(""+i+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
            assertEquals(b[0], codec.decode(new String(b[1])));
        }
    }
    @Test
    public void test1() {
        for (int i = 0; i < 20; i++) {
            final Base32 codec = new Base32(true, '=', false);
            final byte[][] b = Base32TestData.randomData(codec, i);
            assertEquals(""+i+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
            assertEquals(b[0], codec.decode(new String(b[1])));
        }
    }
    @Test
    public void test2() {
        final Base32 codec = new Base32(true);
        // Test with invalid input (having characters other than lowercase hex characters)
        byte[] invalidInput = new byte[] {'A', 'B', 'C', 'D'};
        try {
            codec.encodeAsString(invalidInput);
            fail("IllegalArgumentException should have been thrown");
        } catch (IllegalArgumentException e) {
            // Pass
        }
    }
    @Test
    public void test3() throws Exception {
        final Base32 codec = new Base32(true);
        for (final String[] element : BASE32HEX_TEST_CASES) {
                assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));
                assertEquals(element[0], new String(codec.decode(element[1])));
        }
    }
    @Test
    public void test4() throws Exception {
        final Base32 codec = new Base32(true, '=', true);
        for (final String[] element : BASE32HEX_TEST_CASES) {
                assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));
                assertEquals(element[0], new String(codec.decode(element[1])));
        }
    }
    @Test
    public void test5() throws Exception {
        final Base32 codec = new Base32(true);
        // Test with invalid padding character
        String invalidInput = "AA======";
        try {
            codec.decode(invalidInput);
            fail("IllegalArgumentException should have been thrown");
        } catch (IllegalArgumentException e) {
            // Pass
        }
    }
    @Test
    public void test6() {
        for (int i = 0; i < 20; i++) {
            Base32 codec = new Base32(true);
            final BaseNCodec.Context context = new BaseNCodec.Context();
            final byte unencoded[] = new byte[i];
            final byte allInOne[] = codec.encode(unencoded);
            codec = new Base32(true);
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
    public void test7() {
        for (int i = 0; i < 20; i++) {
            Base32 codec = new Base32(true, '=', true);
            final BaseNCodec.Context context = new BaseNCodec.Context();
            final byte unencoded[] = new byte[i];
            final byte allInOne[] = codec.encode(unencoded);
            codec = new Base32(true, '=', true);
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
    public void test8() {
        for (int i = 0; i < 20; i++) {
            final Base32 codec = new Base32(10, null, true);
            final byte[][] b = Base32TestData.randomData(codec, i);
            assertEquals(""+i+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
            assertEquals(b[0], codec.decode(new String(b[1])));
        }
    }
    @Test
    public void test9() {
        for (int i = 0; i < 20; i++) {
            final Base32 codec = new Base32(10, null, true, '=', true);
            final byte[][] b = Base32TestData.randomData(codec, i);
            assertEquals(""+i+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
            assertEquals(b[0], codec.decode(new String(b[1])));
        }
    }
    @Test
    public void test10() throws Exception {
        final Base32 codec = new Base32(20, null, false);
        for (final String[] element : BASE32_TEST_CASES_CHUNKED) {
                assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));
                assertEquals(element[0], new String(codec.decode(element[1])));
        }
    }
    @Test
    public void test11() throws Exception {
        final Base32 codec = new Base32(20, null, true);
        for (final String[] element : BASE32_TEST_CASES_CHUNKED) {
                assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));
                assertEquals(element[0], new String(codec.decode(element[1])));
        }
    }
    @Test
    public void test12() throws Exception {
        final Base32 codec = new Base32((byte)0x25, null, true);
        for (final String[] element : BASE32_PAD_TEST_CASES) {
                assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));
                assertEquals(element[0], new String(codec.decode(element[1])));
        }
    }
    @Test
    public void test13() throws Exception {
        final Base32 codec = new Base32((byte)0x25, null, true, '=', true);
        for (final String[] element : BASE32_PAD_TEST_CASES) {
                assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));
                assertEquals(element[0], new String(codec.decode(element[1])));
        }
    }
    @Test
    public void test14() throws Exception {
        final Base32 codec = new Base32();
        for (final String[] element : BASE32_TEST_CASES) {
                assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));
                assertEquals(element[0], new String(codec.decode(element[1])));
        }
    }
    @Test
    public void test15() throws Exception {
        final Base32 codec = new Base32(null, true);
        for (final String[] element : BASE32_TEST_CASES) {
                assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));
                assertEquals(element[0], new String(codec.decode(element[1])));
        }
    }
    @Test
    public void test16() {
        for (int i = 0; i < 20; i++) {
            final Base32 codec = new Base32(true, '=', true);
            final byte[][] b = Base32TestData.randomData(codec, i);
            assertEquals(""+i+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
            assertEquals(b[0], codec.decode(new String(b[1])));
        }
    }
    @Test
    public void test17() {
        for (int i = 0; i < 20; i++) {
            final Base32 codec = new Base32(null, true);
            final byte[][] b = Base32TestData.randomData(codec, i);
            assertEquals(""+i+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
            assertEquals(b[0], codec.decode(new String(b[1])));
        }
    }
    @Test
    public void test18() {
        final Base32 codec = new Base32();
        // Test with input containing characters other than base32 characters
        byte[] invalidInput = new byte[] {'A', 'B', 'C', 'D'};
        try {
            codec.encodeAsString(invalidInput);
            fail("IllegalArgumentException should have been thrown");
        } catch (IllegalArgumentException e) {
            // Pass
        }
    }
    @Test
    public void test19() {
        // Original test case
        for (int i = 0; i < 20; i++) {
            final Base32 codec = new Base32(true);
            final byte[][] b = Base32TestData.randomData(codec, i);
            assertEquals(""+i+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
            //assertEquals(b[0],codec.decode(b[1]));
        }
        
        // Test case with octet < 0
        final Base32 codec1 = new Base32(true);
        byte[][] b1 = Base32TestData.randomData(codec1, -1);
        assertEquals(-1, codec1.isInAlphabet(b1[0][0]));
        
        // Test case with octet >= decodeTable.length
        final Base32 codec2 = new Base32(true);
        byte[][] b2 = Base32TestData.randomData(codec2, codec2.decodeTable.length);
        assertEquals(false, codec2.isInAlphabet(b2[0][0]));
        
        // Test case with generate complete test coverage
        final Base32 codec3 = new Base32(true);
        byte[][] b3 = Base32TestData.randomData(codec3, 0);
        assertEquals(true, codec3.isInAlphabet(b3[0][0]));
    }
    @Test
    public void test20() throws Exception {
        // Original test case
        final Base32 codec = new Base32(true);
        for (final String[] element : BASE32HEX_TEST_CASES) {
                assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));
        }
        
        // Test case with empty string
        final Base32 codec1 = new Base32(true);
        assertEquals("", codec1.encodeAsString("".getBytes(Charsets.UTF_8)));
        
        // Test case with null string
        final Base32 codec2 = new Base32(true);
        assertEquals("", codec2.encodeAsString(null));
    }
    @Test
    public void test21() {
        // Original test case
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
        
        // Test case with unencoded = null
        final Base32 codec1 = new Base32();
        final BaseNCodec.Context context1 = new BaseNCodec.Context();
        final byte allInOne1[] = codec1.encode(null);
        final byte singly1[] = new byte[allInOne1.length];
        codec1.readResults(singly1, 0, 100, context1);
        if (!Arrays.equals(allInOne1, singly1)){
            fail();
        }
    }
    @Test
    public void test22() {
        // Original test case
        for (int i = 0; i < 20; i++) {
            final Base32 codec = new Base32(10);
            final byte[][] b = Base32TestData.randomData(codec, i);
            assertEquals(""+i+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
            //assertEquals(b[0],codec.decode(b[1]));
        }
        
        // Test case with lineLength > octet.length
        final Base32 codec1 = new Base32(100);
        final byte[][] b1 = Base32TestData.randomData(codec1, 10);
        assertEquals(0, codec1.getEncodedLength(b1[0]));
    }
    @Test
    public void test23() throws Exception {
        // Original test case
        final Base32 codec = new Base32(20);
        for (final String[] element : BASE32_TEST_CASES_CHUNKED) {
                assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));
        }
        
        // Test case with chunk size = 0
        final Base32 codec1 = new Base32(0);
        for (final String[] element : BASE32_TEST_CASES_CHUNKED) {
                assertEquals(element[1], codec1.encodeAsString(element[0].getBytes(Charsets.UTF_8)));
        }
    }
    @Test
    public void test24() throws Exception {
        // Original test case
        final Base32 codec = new Base32((byte)0x25); // '%' <=> 0x25

        for (final String[] element : BASE32_PAD_TEST_CASES) {
                assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));
        }
        
        // Test case with empty string
        final Base32 codec1 = new Base32((byte)0x25);
        assertEquals("", codec1.encodeAsString("".getBytes(Charsets.UTF_8)));
    }
    @Test
    public void test25() throws Exception {
        // Original test case
        final Base32 codec = new Base32();
        for (final String[] element : BASE32_TEST_CASES) {
                assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));
        }
        
        // Test case with null string
        final Base32 codec1 = new Base32();
        assertEquals("", codec1.encodeAsString(null));
    }
    @Test
    public void test26() {
        // Original test case
        for (int i = 0; i < 20; i++) {
            final Base32 codec = new Base32();
            final byte[][] b = Base32TestData.randomData(codec, i);
            assertEquals(""+i+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
            //assertEquals(b[0],codec.decode(b[1]));
        }
        
        // Test case with empty input
        final Base32 codec1 = new Base32();
        final byte[][] b1 = Base32TestData.randomData(codec1, 0);
        assertEquals(0, codec1.getEncodedLength(b1[0]));
    }
    @Test
    public void test27() {
        // Original test case
        final Base32 codec = new Base32(true, (byte)'W'); // should be allowed
        assertNotNull(codec);
        
        // Test case with padding character is not allowed
        try {
            final Base32 codec1 = new Base32(true, (byte)' ');
            fail();
        } catch (IllegalArgumentException ex) {
            // pass
        }
    }
}