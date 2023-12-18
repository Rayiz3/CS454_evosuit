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
            //assertEquals(b[0],codec.decode(b[1]));
        }
    }
    @Test
    public void test1() {
        final Base32 codec = new Base32(true);
        final byte[][] b = Base32TestData.randomData(codec, 1);
        assertEquals(""+1+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
    }
    @Test
    public void test2() {
        final Base32 codec = new Base32(true);
        final byte[][] b = Base32TestData.randomData(codec, 2);
        assertEquals(""+2+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
    }
    @Test
    public void test3() throws Exception {
        final Base32 codec = new Base32(true);
        for (final String[] element : BASE32HEX_TEST_CASES) {
                assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));
        }
    }
    @Test
    public void test4() throws Exception {
        final Base32 codec = new Base32(true);
        for (final String[] element : BASE32HEX_TEST_CASES) {
                assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));
        }
    }
    @Test
    public void test5() {
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
    public void test6() {
        Base32 codec = new Base32();
        final BaseNCodec.Context context = new BaseNCodec.Context();
        final byte unencoded[] = new byte[1];
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
    @Test
    public void test7() {
        Base32 codec = new Base32();
        final BaseNCodec.Context context = new BaseNCodec.Context();
        final byte unencoded[] = new byte[2];
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
    @Test
    public void test8() {
        for (int i = 0; i < 20; i++) {
            final Base32 codec = new Base32(10);
            final byte[][] b = Base32TestData.randomData(codec, i);
            assertEquals(""+i+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
            //assertEquals(b[0],codec.decode(b[1]));
        }
    }
    @Test
    public void test9() {
        final Base32 codec = new Base32(10);
        final byte[][] b = Base32TestData.randomData(codec, 1);
        assertEquals(""+1+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
    }
    @Test
    public void test10() {
        final Base32 codec = new Base32(10);
        final byte[][] b = Base32TestData.randomData(codec, 2);
        assertEquals(""+2+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
    }
    @Test
    public void test11() throws Exception {
        final Base32 codec = new Base32(20);
        for (final String[] element : BASE32_TEST_CASES_CHUNKED) {
                assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));
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
        final Base32 codec = new Base32((byte)0x25); // '%' <=> 0x25

        for (final String[] element : BASE32_PAD_TEST_CASES) {
                assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));
        }
    }
    @Test
    public void test15() throws Exception {
        final Base32 codec = new Base32();
        for (final String[] element : BASE32_TEST_CASES) {
                assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));
        }
    }
    @Test
    public void test16() throws Exception {
        final Base32 codec = new Base32();
        for (final String[] element : BASE32_TEST_CASES) {
                assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));
        }
    }
    @Test
    public void test17() {
        for (int i = 0; i < 20; i++) {
            final Base32 codec = new Base32();
            final byte[][] b = Base32TestData.randomData(codec, i);
            assertEquals(""+i+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
            //assertEquals(b[0],codec.decode(b[1]));
        }
    }
    @Test
    public void test18() {
        final Base32 codec = new Base32();
        final byte[][] b = Base32TestData.randomData(codec, 1);
        assertEquals(""+1+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
    }
    @Test
    public void test19() {
        final Base32 codec = new Base32();
        final byte[][] b = Base32TestData.randomData(codec, 2);
        assertEquals(""+2+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
    }
    @Test
    public void test20() {
        for (int i = 0; i < 20; i++) {
            final Base32 codec = new Base32(true);
            final byte[][] b = Base32TestData.randomData(codec, i);
            assertEquals(""+i+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
            //assertEquals(b[0],codec.decode(b[1]));
        }
        
        // Regression Test Cases
        
        // Test when octet is less than 0
        final Base32 codec1 = new Base32(true);
        byte octet1 = -1;
        assertFalse(codec1.isInAlphabet(octet1));
        
        // Test when octet is equal to 0
        final Base32 codec2 = new Base32(true);
        byte octet2 = 0;
        assertTrue(codec2.isInAlphabet(octet2));
        
        // Test when octet is greater than or equal to 32
        final Base32 codec3 = new Base32(true);
        byte octet3 = 32;
        assertFalse(codec3.isInAlphabet(octet3));
        
        // Test when octet is less than 0 and greater than or equal to 32
        final Base32 codec4 = new Base32(true);
        byte octet4 = 33;
        assertFalse(codec4.isInAlphabet(octet4));
    }
    @Test
    public void test21() throws Exception {
        final Base32 codec = new Base32(true);
        for (final String[] element : BASE32HEX_TEST_CASES) {
                assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));
        }
        
        // Regression Test Cases
        
        // Test when element[0] is an empty string
        final Base32 codec1 = new Base32(true);
        String[] element1 = {"" , "68656c6c6f"};
        assertEquals("", codec1.encodeAsString(element1[0].getBytes(Charsets.UTF_8)));
    }
}