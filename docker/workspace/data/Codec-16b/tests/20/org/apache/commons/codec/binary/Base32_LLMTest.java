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
    public void test1() throws Exception {
        final Base32 codec = new Base32(true);
        for (final String[] element : BASE32HEX_TEST_CASES) {
                assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));
        }
    }
    @Test
    public void test2() {
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
    public void test3() {
        for (int i = 0; i < 20; i++) {
            final Base32 codec = new Base32(10);
            final byte[][] b = Base32TestData.randomData(codec, i);
            assertEquals(""+i+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
            //assertEquals(b[0],codec.decode(b[1]));
        }
    }
    @Test
    public void test4() throws Exception {
        final Base32 codec = new Base32(20);
        for (final String[] element : BASE32_TEST_CASES_CHUNKED) {
                assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));
        }
    }
    @Test
    public void test5() throws Exception {
        final Base32 codec = new Base32((byte)0x25); // '%' <=> 0x25

        for (final String[] element : BASE32_PAD_TEST_CASES) {
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
    public void test7() {
        for (int i = 0; i < 20; i++) {
            final Base32 codec = new Base32();
            final byte[][] b = Base32TestData.randomData(codec, i);
            assertEquals(""+i+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
            //assertEquals(b[0],codec.decode(b[1]));
        }
    }
    @Test
    public void test8() {
        Base32 codec = new Base32();
        
        // Test valid characters in the alphabet
        assertTrue(codec.isInAlphabet((byte) 'A'));
        assertTrue(codec.isInAlphabet((byte) 'Z'));
        assertTrue(codec.isInAlphabet((byte) '2'));
        assertTrue(codec.isInAlphabet((byte) '7'));
    }
    @Test
    public void test9() {
        Base32 codec = new Base32();
        
        // Test invalid characters not in the alphabet
        assertFalse(codec.isInAlphabet((byte) ' '));
        assertFalse(codec.isInAlphabet((byte) '!'));
        assertFalse(codec.isInAlphabet((byte) ';'));
        assertFalse(codec.isInAlphabet((byte) '?'));
    }
    @Test
    public void test10() {
        Base32 codec = new Base32();
        
        // Test lowercase characters
        assertTrue(codec.isInAlphabet((byte) 'a'));
        assertTrue(codec.isInAlphabet((byte) 'z'));
    }
    @Test
    public void test11() {
        Base32 codec = new Base32();
        
        // Test special characters
        assertFalse(codec.isInAlphabet((byte) '@'));
        assertFalse(codec.isInAlphabet((byte) '#'));
        assertFalse(codec.isInAlphabet((byte) '$'));
        assertFalse(codec.isInAlphabet((byte) '&'));
        assertFalse(codec.isInAlphabet((byte) '*'));
    }
    @Test
    public void test12() {
        Base32 codec = new Base32();
        
        byte[] input = new byte[0];
        byte[] output = codec.encode(input);
        
        assertEquals(0, output.length);
    }
    @Test
    public void test13() {
        Base32 codec = new Base32();
        
        byte[] input = {65}; // ASCII value of 'A'
        byte[] output = codec.encode(input);
        
        assertEquals(2, output.length);
        assertEquals('Q', output[0]);
        assertEquals('A', output[1]);
    }
    @Test
    public void test14() {
        Base32 codec = new Base32();
        
        byte[] input = "HELLO".getBytes(Charsets.UTF_8);
        byte[] output = codec.encode(input);
        
        assertEquals(10, output.length);
        assertEquals('N', output[0]);
        assertEquals('B', output[1]);
        assertEquals('2', output[2]);
        assertEquals('X', output[3]);
        assertEquals('I', output[4]);
        assertEquals('D', output[5]);
        assertEquals('E', output[6]);
        assertEquals('Y', output[7]);
        assertEquals('3', output[8]);
        assertEquals('I', output[9]);
    }
    @Test
    public void test15() {
        Base32 codec = new Base32();
        
        byte[] input = new byte[0];
        byte[] output = codec.decode(input);
        
        assertEquals(0, output.length);
    }
    @Test
    public void test16() {
        Base32 codec = new Base32();
        
        byte[] input = {'Q', 'A'};
        byte[] output = codec.decode(input);
        
        assertEquals(1, output.length);
        assertEquals(65, output[0]); // ASCII value of 'A'
    }
    @Test
    public void test17() {
        Base32 codec = new Base32();
        
        byte[] input = {'N', 'B', '2', 'X', 'I', 'D', 'E', 'Y', '3', 'I'};
        byte[] output = codec.decode(input);
        
        byte[] expectedOutput = "HELLO".getBytes(Charsets.UTF_8);
        assertArrayEquals(expectedOutput, output);
    }
}