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
            
            // Regression test 1
            {
                final byte[][] b = Base32TestData.randomData(codec, i);
                assertEquals(""+i+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
                //assertEquals(b[0],codec.decode(b[1]));
            }
            
            // Regression test 2
            {
                final byte[][] b = Base32TestData.randomData(codec, i);
                assertEquals(""+i+" "+codec.lineLength,b[1].length + 1,codec.getEncodedLength(b[0]));
                //assertEquals(b[0],codec.decode(b[1]));
            }
            
            // Regression test 3
            {
                final byte[][] b = Base32TestData.randomData(codec, i);
                assertEquals(""+i+" "+codec.lineLength,b[1].length - 1,codec.getEncodedLength(b[0]));
                //assertEquals(b[0],codec.decode(b[1]));
            }
        }
    }
    @Test
    public void test1() throws Exception {
        final Base32 codec = new Base32(true);
        for (final String[] element : BASE32HEX_TEST_CASES) {
            // Regression test 1
            {
                assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));
            }
            
            // Regression test 2
            {
                String encoded = codec.encodeAsString(element[0].getBytes(Charsets.UTF_8));
                assertEquals(element[1], encoded + "A");
            }
            
            // Regression test 3
            {
                String encoded = codec.encodeAsString(element[0].getBytes(Charsets.UTF_8));
                assertEquals(element[1], encoded.substring(1));
            }
        }
    }
    @Test
    public void test2() {
        for (int i = 0; i < 20; i++) {
            Base32 codec = new Base32();
            final BaseNCodec.Context context = new BaseNCodec.Context();
            final byte unencoded[] = new byte[i];
            
            // Regression test 1
            {
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
            
            // Regression test 2
            {
                final byte allInOne[] = codec.encode(unencoded);
                codec = new Base32();
                final BaseNCodec.Context context_ = new BaseNCodec.Context();
                for (int j=0; j< unencoded.length; j++) {
                    codec.encode(unencoded, j, 1, context_);
                }
                codec.encode(unencoded, 0, -1, context_);
                final byte singly[] = new byte[allInOne.length];
                codec.readResults(singly, 0, 100, context_);
                if (!Arrays.equals(allInOne, singly)){
                    fail();
                }
            }
        }
    }
    @Test
    public void test3() {
        for (int i = 0; i < 20; i++) {
            final Base32 codec = new Base32(10);
            
            // Regression test 1
            {
                final byte[][] b = Base32TestData.randomData(codec, i);
                assertEquals(""+i+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
                //assertEquals(b[0],codec.decode(b[1]));
            }
            
            // Regression test 2
            {
                final byte[][] b = Base32TestData.randomData(codec, i);
                assertEquals(""+i+" "+codec.lineLength,b[1].length + 1,codec.getEncodedLength(b[0]));
                //assertEquals(b[0],codec.decode(b[1]));
            }
            
            // Regression test 3
            {
                final byte[][] b = Base32TestData.randomData(codec, i);
                assertEquals(""+i+" "+codec.lineLength,b[1].length - 1,codec.getEncodedLength(b[0]));
                //assertEquals(b[0],codec.decode(b[1]));
            }
        }
    }
    @Test
    public void test4() throws Exception {
        final Base32 codec = new Base32(20);
        for (final String[] element : BASE32_TEST_CASES_CHUNKED) {
            
            // Regression test 1
            {
                assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));
            }
            
            // Regression test 2
            {
                String encoded = codec.encodeAsString(element[0].getBytes(Charsets.UTF_8));
                assertEquals(element[1], encoded + "A");
            }
            
            // Regression test 3
            {
                String encoded = codec.encodeAsString(element[0].getBytes(Charsets.UTF_8));
                assertEquals(element[1], encoded.substring(1));
            }
        }
    }
    @Test
    public void test5() throws Exception {
        final Base32 codec = new Base32((byte)0x25); // '%' <=> 0x25

        for (final String[] element : BASE32_PAD_TEST_CASES) {
            
            // Regression test 1
            {
                assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));
            }
            
            // Regression test 2
            {
                String encoded = codec.encodeAsString(element[0].getBytes(Charsets.UTF_8));
                assertEquals(element[1], encoded + "A");
            }
            
            // Regression test 3
            {
                String encoded = codec.encodeAsString(element[0].getBytes(Charsets.UTF_8));
                assertEquals(element[1], encoded.substring(1));
            }
        }
    }
    @Test
    public void test6() throws Exception {
        final Base32 codec = new Base32();
        for (final String[] element : BASE32_TEST_CASES) {
            
            // Regression test 1
            {
                assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));
            }
            
            // Regression test 2
            {
                String encoded = codec.encodeAsString(element[0].getBytes(Charsets.UTF_8));
                assertEquals(element[1], encoded + "A");
            }
            
            // Regression test 3
            {
                String encoded = codec.encodeAsString(element[0].getBytes(Charsets.UTF_8));
                assertEquals(element[1], encoded.substring(1));
            }
        }
    }
    @Test
    public void test7() {
        for (int i = 0; i < 20; i++) {
            final Base32 codec = new Base32();
            
            // Regression test 1
            {
                final byte[][] b = Base32TestData.randomData(codec, i);
                assertEquals(""+i+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
                //assertEquals(b[0],codec.decode(b[1]));
            }
            
            // Regression test 2
            {
                final byte[][] b = Base32TestData.randomData(codec, i);
                assertEquals(""+i+" "+codec.lineLength,b[1].length + 1,codec.getEncodedLength(b[0]));
                //assertEquals(b[0],codec.decode(b[1]));
            }
            
            // Regression test 3
            {
                final byte[][] b = Base32TestData.randomData(codec, i);
                assertEquals(""+i+" "+codec.lineLength,b[1].length - 1,codec.getEncodedLength(b[0]));
                //assertEquals(b[0],codec.decode(b[1]));
            }
        }
    }
    @Test
    public void test8() {
        for (int i = 0; i < 20; i++) {
            final Base32 codec = new Base32(true);
            final byte[][] b = Base32TestData.randomData(codec, i);
            assertEquals(""+i+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
            //assertEquals(b[0],codec.decode(b[1]));
        }
        
        // Regression test case 1: octet = -1
        final Base32 codec1 = new Base32(true);
        byte octet1 = -1;
        assertFalse(codec1.isInAlphabet(octet1));
        
        // Regression test case 2: octet = 0
        final Base32 codec2 = new Base32(true);
        byte octet2 = 0;
        assertTrue(codec2.isInAlphabet(octet2));

        // Regression test case 3: octet = 127
        final Base32 codec3 = new Base32(true);
        byte octet3 = 127;
        assertTrue(codec3.isInAlphabet(octet3));
    }
    @Test
    public void test9() throws Exception {
        final Base32 codec = new Base32(true);
        
        // Regression test case 1: element = "" (empty string)
        final String[] element1 = {"", ""};
        assertEquals(element1[1], codec.encodeAsString(element1[0].getBytes(Charsets.UTF_8)));
        
        // Regression test case 2: element = "ABC" 
        final String[] element2 = {"ABC", "CSQPY"};
        assertEquals(element2[1], codec.encodeAsString(element2[0].getBytes(Charsets.UTF_8)));
        
        // Regression test case 3: element = "abcdef"
        final String[] element3 = {"abcdef", "4AXQQ===="};
        assertEquals(element3[1], codec.encodeAsString(element3[0].getBytes(Charsets.UTF_8)));
    }
}