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
        final Base32 codec = new Base32();
        final byte[] in = new byte[]{1, 2, 3};
        final int inPos = 0;
        final int inAvail = -1;
        final BaseNCodec.Context context = new BaseNCodec.Context();
        context.modulus = 0;
        codec.encode(in, inPos, inAvail, context);
        assertTrue(context.eof);
    }
    @Test
    public void test9() {
        final Base32 codec = new Base32();
        final byte[] in = new byte[]{1, 2, 3};
        final int inPos = 0;
        final int inAvail = 3;
        final BaseNCodec.Context context = new BaseNCodec.Context();
        context.eof = false;
        context.modulus = 0;
        codec.lineLength = 0;
        codec.encode(in, inPos, inAvail, context);
        assertTrue(context.eof);
    }
    @Test
    public void test10() {
        final Base32 codec = new Base32();
        final byte[] in = new byte[]{1};
        final int inPos = 0;
        final int inAvail = 1;
        final BaseNCodec.Context context = new BaseNCodec.Context();
        context.modulus = 1;
        final byte[] buffer = new byte[codec.encodeSize()];
        context.pos = 0;
        codec.encode(in, inPos, inAvail, context);
        if (context.modulus == 1) {
            assertEquals(1, context.pos);
            for (int i = 0; i < context.pos; i++) {
                assertEquals(codec.encodeTable[(int)(context.lbitWorkArea >> (3 - i * 5)) & 31], buffer[i]);
            }
        }
    }
    @Test
    public void test11() {
        final Base32 codec = new Base32();
        final byte[] in = new byte[]{1, 2};
        final int inPos = 0;
        final int inAvail = 2;
        final BaseNCodec.Context context = new BaseNCodec.Context();
        context.modulus = 2;
        final byte[] buffer = new byte[codec.encodeSize()];
        context.pos = 0;
        codec.encode(in, inPos, inAvail, context);
        if (context.modulus == 2) {
            assertEquals(4, context.pos);
            for (int i = 0; i < context.pos; i++) {
                assertEquals(codec.encodeTable[(int)(context.lbitWorkArea >> (11 - i * 5)) & 31], buffer[i]);
            }
        }
    }
    @Test
    public void test12() {
        final Base32 codec = new Base32();
        final byte[] in = new byte[]{1, 2, 3};
        final int inPos = 0;
        final int inAvail = 3;
        final BaseNCodec.Context context = new BaseNCodec.Context();
        context.modulus = 3;
        final byte[] buffer = new byte[codec.encodeSize()];
        context.pos = 0;
        codec.encode(in, inPos, inAvail, context);
        if (context.modulus == 3) {
            assertEquals(7, context.pos);
            for (int i = 0; i < context.pos; i++) {
                assertEquals(codec.encodeTable[(int)(context.lbitWorkArea >> (19 - i * 5)) & 31], buffer[i]);
            }
        }
    }
    @Test
    public void test13() {
        final Base32 codec = new Base32();
        final byte[] in = new byte[]{1, 2, 3, 4};
        final int inPos = 0;
        final int inAvail = 4;
        final BaseNCodec.Context context = new BaseNCodec.Context();
        context.modulus = 4;
        final byte[] buffer = new byte[codec.encodeSize()];
        context.pos = 0;
        codec.encode(in, inPos, inAvail, context);
        if (context.modulus == 4) {
            assertEquals(8, context.pos);
            for (int i = 0; i < context.pos; i++) {
                assertEquals(codec.encodeTable[(int)(context.lbitWorkArea >> (27 - i * 5)) & 31], buffer[i]);
            }
        }
    }
    @Test
    public void test14() {
        final Base32 codec = new Base32(true);
        
        // Test with octet = -1
        assertFalse(codec.isInAlphabet((byte)-1));
        
        // Test with octet = 0
        assertTrue(codec.isInAlphabet((byte)0));
        
        // Test with octet = 1
        assertTrue(codec.isInAlphabet((byte)1));
        
        // Test with octet = 31
        assertTrue(codec.isInAlphabet((byte)31));
        
        // Test with octet = 32
        assertFalse(codec.isInAlphabet((byte)32));
        
        // Test with octet = 64
        assertFalse(codec.isInAlphabet((byte)64));
        
        // Test with octet = 65
        assertTrue(codec.isInAlphabet((byte)65));
        
        // Test with octet = 127
        assertFalse(codec.isInAlphabet((byte)127));
        
        // Test with octet = 128
        assertFalse(codec.isInAlphabet((byte)128));
        
        // Test with octet = -128
        assertFalse(codec.isInAlphabet((byte)-128));
        
        // Test with octet = -127
        assertFalse(codec.isInAlphabet((byte)-127));
        
        // Test with octet = -65
        assertTrue(codec.isInAlphabet((byte)-65));
        
        // Test with octet = -64
        assertFalse(codec.isInAlphabet((byte)-64));
        
        // Test with octet = -33
        assertFalse(codec.isInAlphabet((byte)-33));
        
        // Test with octet = -32
        assertTrue(codec.isInAlphabet((byte)-32));
        
        // Test with octet = -2
        assertTrue(codec.isInAlphabet((byte)-2));
        
        // Test with octet = 127
        assertFalse(codec.isInAlphabet((byte)-1));
    }
}