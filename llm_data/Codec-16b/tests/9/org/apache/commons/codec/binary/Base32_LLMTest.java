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
        final BaseNCodec.Context context = new BaseNCodec.Context();
        byte[] buffer = ensureBufferSize(32, context); // Increase buffer size
        byte[] input = {(byte) -1, (byte) -2, (byte) -3, (byte) -4};
        int inPos = 0;
        int inAvail = input.length;
        int lineLength = 0;
        byte[] lineSeparator = Base32.BASE32_ENCODE_TABLE;
        boolean eof = false;
        int encodeSize = 32;
        int BYTES_PER_UNENCODED_BLOCK = 3;
        int MASK_5BITS = 0x1f;
        context.pos = 0;
        context.lbitWorkArea = 0;
        context.eof = eof;
        context.currentLinePos = 0;
        context.modulus = (context.pos + 1) % BYTES_PER_UNENCODED_BLOCK;
        while (inAvail >= 0) {
            if (inAvail < 0) {
                context.eof = true;
                if (0 == context.modulus && lineLength == 0) {
                    return;
                }
                final byte[] newBuffer = ensureBufferSize(encodeSize, context);
                final int savedPos = context.pos;
                switch (context.modulus) { // % 5
                    case 0 :
                        break;
                    case 1 : // Only 1 octet; take top 5 bits then remainder
                        newBuffer[context.pos++] = Base32.BASE32_ENCODE_TABLE[(int) (context.lbitWorkArea >> 3) & MASK_5BITS];
                        newBuffer[context.pos++] = Base32.BASE32_ENCODE_TABLE[(int) (context.lbitWorkArea << 2) & MASK_5BITS];
                        for (int i = 0; i < context.modulus + 1; i++) {
                            newBuffer[context.pos++] = Base32.PAD;
                        }
                        break;
                    case 2 : // 2 octets = 16 bits to use
                        newBuffer[context.pos++] = Base32.BASE32_ENCODE_TABLE[(int) (context.lbitWorkArea >> 11) & MASK_5BITS];
                        newBuffer[context.pos++] = Base32.BASE32_ENCODE_TABLE[(int) (context.lbitWorkArea >> 6) & MASK_5BITS];
                        newBuffer[context.pos++] = Base32.BASE32_ENCODE_TABLE[(int) (context.lbitWorkArea >> 1) & MASK_5BITS];
                        newBuffer[context.pos++] = Base32.BASE32_ENCODE_TABLE[(int) (context.lbitWorkArea << 4) & MASK_5BITS];
                        for (int i = 0; i < context.modulus + 1; i++) {
                            newBuffer[context.pos++] = Base32.PAD;
                        }
                        break;
                    case 3 : // 3 octets = 24 bits to use
                        newBuffer[context.pos++] = Base32.BASE32_ENCODE_TABLE[(int) (context.lbitWorkArea >> 19) & MASK_5BITS];
                        newBuffer[context.pos++] = Base32.BASE32_ENCODE_TABLE[(int) (context.lbitWorkArea >> 14) & MASK_5BITS];
                        newBuffer[context.pos++] = Base32.BASE32_ENCODE_TABLE[(int) (context.lbitWorkArea >> 9) & MASK_5BITS];
                        newBuffer[context.pos++] = Base32.BASE32_ENCODE_TABLE[(int) (context.lbitWorkArea >> 4) & MASK_5BITS];
                        newBuffer[context.pos++] = Base32.BASE32_ENCODE_TABLE[(int) (context.lbitWorkArea << 1) & MASK_5BITS];
                        for (int i = 0; i < context.modulus + 1; i++) {
                            newBuffer[context.pos++] = Base32.PAD;
                        }
                        break;
                    case 4 : // 4 octets = 32 bits to use
                        newBuffer[context.pos++] = Base32.BASE32_ENCODE_TABLE[(int) (context.lbitWorkArea >> 27) & MASK_5BITS];
                        newBuffer[context.pos++] = Base32.BASE32_ENCODE_TABLE[(int) (context.lbitWorkArea >> 22) & MASK_5BITS];
                        newBuffer[context.pos++] = Base32.BASE32_ENCODE_TABLE[(int) (context.lbitWorkArea >> 17) & MASK_5BITS];
                        newBuffer[context.pos++] = Base32.BASE32_ENCODE_TABLE[(int) (context.lbitWorkArea >> 12) & MASK_5BITS];
                        newBuffer[context.pos++] = Base32.BASE32_ENCODE_TABLE[(int) (context.lbitWorkArea >> 7) & MASK_5BITS];
                        newBuffer[context.pos++] = Base32.BASE32_ENCODE_TABLE[(int) (context.lbitWorkArea >> 2) & MASK_5BITS];
                        newBuffer[context.pos++] = Base32.BASE32_ENCODE_TABLE[(int) (context.lbitWorkArea << 3) & MASK_5BITS];
                        for (int i = 0; i < context.modulus + 1; i++) {
                            newBuffer[context.pos++] = Base32.PAD;
                        }
                        break;
                    default:
                        throw new IllegalStateException("Impossible modulus " + context.modulus);
                }
                context.currentLinePos += context.pos - savedPos;
                if (lineLength > 0 && context.currentLinePos > 0){
                    System.arraycopy(lineSeparator, 0, newBuffer, context.pos, lineSeparator.length);
                    context.pos += lineSeparator.length;
                }
            } else {
                for (int i = 0; i < inAvail; i++) {
                    buffer = ensureBufferSize(encodeSize, context);
                    context.modulus = (context.modulus+1) % BYTES_PER_UNENCODED_BLOCK;
                    int b = input[inPos++];
                    if (b < 0) {
                        b += 256;
                    }
                    context.lbitWorkArea = (context.lbitWorkArea << 8) + b;
                    if (0 == context.modulus) {
                        buffer[context.pos++] = Base32.BASE32_ENCODE_TABLE[(int) (context.lbitWorkArea >> 35) & MASK_5BITS];
                        buffer[context.pos++] = Base32.BASE32_ENCODE_TABLE[(int) (context.lbitWorkArea >> 30) & MASK_5BITS];
                        buffer[context.pos++] = Base32.BASE32_ENCODE_TABLE[(int) (context.lbitWorkArea >> 25) & MASK_5BITS];
                        buffer[context.pos++] = Base32.BASE32_ENCODE_TABLE[(int) (context.lbitWorkArea >> 20) & MASK_5BITS];
                        buffer[context.pos++] = Base32.BASE32_ENCODE_TABLE[(int) (context.lbitWorkArea >> 15) & MASK_5BITS];
                        buffer[context.pos++] = Base32.BASE32_ENCODE_TABLE[(int) (context.lbitWorkArea >> 10) & MASK_5BITS];
                        buffer[context.pos++] = Base32.BASE32_ENCODE_TABLE[(int) (context.lbitWorkArea >> 5) & MASK_5BITS];
                        buffer[context.pos++] = Base32.BASE32_ENCODE_TABLE[(int) context.lbitWorkArea & MASK_5BITS];
                        context.currentLinePos += 5;
                        if (lineLength > 0 && lineLength <= context.currentLinePos) {
                            System.arraycopy(lineSeparator, 0, buffer, context.pos, lineSeparator.length);
                            context.pos += lineSeparator.length;
                            context.currentLinePos = 0;
                        }
                    }
                }
            }
        }
    }
    @Test
    public void test9() {
        for (int i = 0; i < 20; i++) {
            final Base32 codec = new Base32(true);
            final byte[][] b = Base32TestData.randomData(codec, i);
            assertEquals(""+i+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
            //assertEquals(b[0],codec.decode(b[1]));
        }
    }
    @Test
    public void test10() throws Exception {
        final Base32 codec = new Base32(true);
        for (final String[] element : BASE32HEX_TEST_CASES) {
                assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));
        }
    }
    @Test
    public void test11() {
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
    public void test12() {
        for (int i = 0; i < 20; i++) {
            final Base32 codec = new Base32(10);
            final byte[][] b = Base32TestData.randomData(codec, i);
            assertEquals(""+i+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
            //assertEquals(b[0],codec.decode(b[1]));
        }
    }
    @Test
    public void test13() throws Exception {
        final Base32 codec = new Base32(20);
        for (final String[] element : BASE32_TEST_CASES_CHUNKED) {
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
    public void test16() {
        for (int i = 0; i < 20; i++) {
            final Base32 codec = new Base32();
            final byte[][] b = Base32TestData.randomData(codec, i);
            assertEquals(""+i+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
            //assertEquals(b[0],codec.decode(b[1]));
        }
    }
    @Test
    public void test17() {
        final Base32 codec = new Base32(true, (byte)'W'); // should be allowed
        assertNotNull(codec);
    }
    @Test
    public void test18() {
        final Base32 codec = new Base32();
        byte octet = -1;
        assertFalse(codec.isInAlphabet(octet));
    }
    @Test
    public void test19() {
        final Base32 codec = new Base32();
        byte octet = -100;
        assertFalse(codec.isInAlphabet(octet));
    }
}