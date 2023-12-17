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
        final byte[] in = new byte[] {65, 66, 67, 68};
        final int inPos = 0;
        final int inAvail = -1;
        final Context context = new Context();
        context.eof = false;

        codec.encode(in, inPos, inAvail, context);
        assertEquals(false, context.eof);
    }
    @Test
    public void test9() {
        final Base32 codec = new Base32();
        final byte[] in = new byte[] {65};
        final int inPos = 0;
        final int inAvail = 1;
        final Context context = new Context();
        context.eof = false;
        context.modulus = 1;
        context.lbitWorkArea = 65;

        codec.encode(in, inPos, inAvail, context);
        assertEquals(0, context.modulus);
        assertEquals(1, context.pos);
        assertEquals(0, context.currentLinePos);
    }
    @Test
    public void test10() {
        final Base32 codec = new Base32();
        final byte[] in = new byte[] {65, 66};
        final int inPos = 0;
        final int inAvail = 2;
        final Context context = new Context();
        context.eof = false;
        context.modulus = 2;
        context.lbitWorkArea = 16961;

        codec.encode(in, inPos, inAvail, context);
        assertEquals(0, context.modulus);
        assertEquals(2, context.pos);
        assertEquals(0, context.currentLinePos);
    }
    @Test
    public void test11() {
        final Base32 codec = new Base32();
        final byte[] in = new byte[] {65, 66, 67};
        final int inPos = 0;
        final int inAvail = 3;
        final Context context = new Context();
        context.eof = false;
        context.modulus = 3;
        context.lbitWorkArea = 4324519;

        codec.encode(in, inPos, inAvail, context);
        assertEquals(0, context.modulus);
        assertEquals(3, context.pos);
        assertEquals(0, context.currentLinePos);
    }
    @Test
    public void test12() {
        final Base32 codec = new Base32();
        final byte[] in = new byte[] {65, 66, 67, 68};
        final int inPos = 0;
        final int inAvail = 4;
        final Context context = new Context();
        context.eof = false;
        context.modulus = 4;
        context.lbitWorkArea = 691198912;

        codec.encode(in, inPos, inAvail, context);
        assertEquals(0, context.modulus);
        assertEquals(3, context.pos);
        assertEquals(0, context.currentLinePos);
    }
    @Test
    public void test13() {
        final Base32 codec = new Base32();
        final byte[] in = new byte[] {65, 66, 67, 68, 69};
        final int inPos = 0;
        final int inAvail = 5;
        final Context context = new Context();
        context.eof = false;
        context.modulus = 5;
        context.lbitWorkArea = 11169955841L;

        codec.encode(in, inPos, inAvail, context);
        assertEquals(0, context.modulus);
        assertEquals(5, context.pos);
        assertEquals(0, context.currentLinePos);
    }
    @Test
    public void test14() {
        final Base32 codec = new Base32();
        final byte[] in = new byte[] {65, 66, 67, 68};
        final int inPos = 0;
        final int inAvail = 4;
        final Context context = new Context();
        context.eof = true;
        context.modulus = 0;
        context.currentLinePos = 0;

        codec.lineLength = 5;
        codec.encode(in, inPos, inAvail, context);
        assertEquals(8, context.pos);
        assertEquals(4, context.currentLinePos);
    }
    @Test
    public void test15() {
        final Base32 codec = new Base32();
        final byte[] in = new byte[] {65, 66, 67, 68};
        final int inPos = 0;
        final int inAvail = 4;
        final Context context = new Context();
        context.eof = true;
        context.modulus = 0;
        context.currentLinePos = 3;

        codec.lineLength = 0;
        codec.encode(in, inPos, inAvail, context);
        assertEquals(4, context.pos);
        assertEquals(4, context.currentLinePos);
    }
    @Test
    public void test16() {
        final Base32 codec = new Base32();
        final byte[] in = new byte[] {65, 66, 67, 68};
        final int inPos = 0;
        final int inAvail = 4;
        final Context context = new Context();
        context.eof = true;
        context.modulus = 0;
        context.currentLinePos = 4;

        codec.lineLength = 5;
        codec.encode(in, inPos, inAvail, context);
        assertEquals(8, context.pos);
        assertEquals(0, context.currentLinePos);
    }
    @Test
    public void test17() {
        Base32 codec = new Base32();
        byte outOfRange = 127;
        assertFalse(codec.isInAlphabet(outOfRange));
        
        codec = new Base32(true);
        assertTrue(codec.isInAlphabet(outOfRange));
    }
    @Test
    public void test18() {
        Base32 codec = new Base32();
        byte inRange = 65;
        assertTrue(codec.isInAlphabet(inRange));
        
        codec = new Base32(true);
        assertTrue(codec.isInAlphabet(inRange));
    }
    @Test
    public void test19() {
        Base32 codec = new Base32();
        byte inRange = -1;
        assertFalse(codec.isInAlphabet(inRange));
        
        codec = new Base32(true);
        assertFalse(codec.isInAlphabet(inRange));
    }
}