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
        final byte[] in = new byte[]{(byte) 0xAB, (byte) 0xCD};
        final int inPos = 0;
        final int inAvail = -1;
        final BaseNCodec.Context context = new BaseNCodec.Context();
        
        codec.encode(in, inPos, inAvail, context);
        assertTrue(context.eof);
    }
    @Test
    public void test9() {
        final Base32 codec = new Base32();
        final byte[] in = new byte[]{(byte) 0xAB, (byte) 0xCD};
        final int inPos = 0;
        final int inAvail = -1;
        final BaseNCodec.Context context = new BaseNCodec.Context();
        
        codec.encode(in, inPos, inAvail, context);
        assertTrue(context.eof);
    }
    @Test
    public void test10() {
        final Base32 codec = new Base32();
        final byte[] in = new byte[]{(byte) 0xAB, (byte) 0xCD};
        final int inPos = 0;
        final int inAvail = -1;
        final BaseNCodec.Context context = new BaseNCodec.Context();
        context.modulus = 1;
        
        codec.encode(in, inPos, inAvail, context);
        assertTrue(context.eof);
        assertEquals(2, context.pos);
        assertEquals(Base32.PAD, context.buffer[context.pos - 1]);
    }
    @Test
    public void test11() {
        final Base32 codec = new Base32();
        final byte[] in = new byte[]{(byte) 0xAB, (byte) 0xCD};
        final int inPos = 0;
        final int inAvail = -1;
        final BaseNCodec.Context context = new BaseNCodec.Context();
        context.modulus = 2;
        
        codec.encode(in, inPos, inAvail, context);
        assertTrue(context.eof);
        assertEquals(4, context.pos);
        assertEquals(Base32.PAD, context.buffer[context.pos - 1]);
        assertEquals(Base32.PAD, context.buffer[context.pos - 2]);
    }
    @Test
    public void test12() {
        final Base32 codec = new Base32();
        final byte[] in = new byte[]{(byte) 0xAB, (byte) 0xCD};
        final int inPos = 0;
        final int inAvail = -1;
        final BaseNCodec.Context context = new BaseNCodec.Context();
        context.modulus = 3;
        
        codec.encode(in, inPos, inAvail, context);
        assertTrue(context.eof);
        assertEquals(5, context.pos);
        assertEquals(Base32.PAD, context.buffer[context.pos - 1]);
        assertEquals(Base32.PAD, context.buffer[context.pos - 2]);
        assertEquals(Base32.PAD, context.buffer[context.pos - 3]);
    }
    @Test
    public void test13() {
        final Base32 codec = new Base32();
        final byte[] in = new byte[]{(byte) 0xAB, (byte) 0xCD};
        final int inPos = 0;
        final int inAvail = -1;
        final BaseNCodec.Context context = new BaseNCodec.Context();
        context.modulus = 4;
        
        codec.encode(in, inPos, inAvail, context);
        assertTrue(context.eof);
        assertEquals(8, context.pos);
        assertEquals(Base32.PAD, context.buffer[context.pos - 1]);
        assertEquals(Base32.PAD, context.buffer[context.pos - 2]);
        assertEquals(Base32.PAD, context.buffer[context.pos - 3]);
        assertEquals(Base32.PAD, context.buffer[context.pos - 4]);
    }
    @Test (expected = IllegalStateException.class)
    public void test14() {
        final Base32 codec = new Base32();
        final byte[] in = new byte[]{(byte) 0xAB, (byte) 0xCD};
        final int inPos = 0;
        final int inAvail = -1;
        final BaseNCodec.Context context = new BaseNCodec.Context();
        context.modulus = 5;
        
        codec.encode(in, inPos, inAvail, context);
    }
    @Test
    public void test15() {
        final Base32 codec = new Base32();
        final byte[] in = new byte[]{(byte) 0xAB, (byte) 0xCD, (byte) 0xEF};
        final int inPos = 0;
        final int inAvail = 0;
        final BaseNCodec.Context context = new BaseNCodec.Context();
        
        codec.encode(in, inPos, inAvail, context);
        assertEquals(0, context.pos);
    }
@Test
public void test16() {
    final Base32 codec = new Base32(true);
    final byte[][] b = Base32TestData.randomData(codec, 20);
    assertEquals("20 "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
    assertFalse(codec.isInAlphabet((byte) -1));
}
@Test
public void test17() throws Exception {
    final Base32 codec = new Base32(true);
    for (final String[] element : BASE32HEX_TEST_CASES) {
        assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));
        assertTrue(codec.isInAlphabet((byte) 255));
    }
}
@Test
public void test18() {
    Base32 codec = new Base32();
    final BaseNCodec.Context context = new BaseNCodec.Context();
    final byte unencoded[] = new byte[20];
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
    assertTrue(codec.isInAlphabet((byte) 128));
}
@Test
public void test19() {
    final Base32 codec = new Base32(10);
    final byte[][] b = Base32TestData.randomData(codec, 20);
    assertEquals("20 "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
    assertFalse(codec.isInAlphabet((byte) -2));
}
@Test
public void test20() throws Exception {
    final Base32 codec = new Base32(20);
    for (final String[] element : BASE32_TEST_CASES_CHUNKED) {
        assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));
        assertTrue(codec.isInAlphabet((byte) 127));
    }
}
@Test
public void test21() throws Exception {
    final Base32 codec = new Base32((byte)0x25); // '%' <=> 0x25

    for (final String[] element : BASE32_PAD_TEST_CASES) {
        assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));
        assertFalse(codec.isInAlphabet((byte) -1));
    }
}
@Test
public void test22() throws Exception {
    final Base32 codec = new Base32();
    for (final String[] element : BASE32_TEST_CASES) {
        assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));
        assertTrue(codec.isInAlphabet((byte) 127));
    }
}
@Test
public void test23() {
    final Base32 codec = new Base32();
    final byte[][] b = Base32TestData.randomData(codec, 20);
    assertEquals("20 "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
    assertFalse(codec.isInAlphabet((byte) -2));
}
@Test
public void test24() {
    final Base32 codec = new Base32(true, (byte)'W'); // should be allowed
    assertNotNull(codec);
    assertTrue(codec.isInAlphabet((byte) 64));
}
}