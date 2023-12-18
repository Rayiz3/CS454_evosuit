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
        final Base32 codec = new Base32(10);
        final byte[][] b = Base32TestData.randomData(codec, 20);
        assertEquals(b[1].length,codec.getEncodedLength(b[0]));
        assertEquals(b[0],codec.decode(b[1]));
    }
    @Test
    public void test1() throws Exception {
        final Base32 codec = new Base32((byte)0x25); // '%' <=> 0x25

        assertEquals("SGVsbG8gdGhlcmUh", codec.encodeAsString("Hello there!".getBytes(Charsets.UTF_8)));
    }
    @Test
    public void test2() {
        final Base32 codec = new Base32();
        final byte[][] b = Base32TestData.randomData(codec, 20);
        assertEquals(b[1].length,codec.getEncodedLength(b[0]));
        assertEquals(b[0],codec.decode(b[1]));
    }
    @Test
    public void test3() {
        final Base32 codec = new Base32(true);
        final byte[][] b = Base32TestData.randomData(codec, 20);
        assertEquals(b[1].length,codec.getEncodedLength(b[0]));
        assertEquals(b[0],codec.decode(b[1]));
    }
    @Test
    public void test4() {
        Base32 codec = new Base32();
        final BaseNCodec.Context context = new BaseNCodec.Context();
        final byte unencoded[] = new byte[5];
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
    public void test5() throws Exception {
        final Base32 codec = new Base32(20);
        assertEquals("Customer Order", codec.encodeAsString("Customer Order".getBytes(Charsets.UTF_8)));
    }
    @Test
    public void test6() throws Exception {
        final Base32 codec = new Base32();
        assertEquals("SGVsbG8gdGhlcmUh", codec.encodeAsString("Hello there!".getBytes(Charsets.UTF_8)));
    }
    @Test
    public void test7() throws Exception {
        final Base32 codec = new Base32(true);
        assertEquals("68656C6C6F20746865726521", codec.encodeAsString("Hello there!".getBytes(Charsets.UTF_8)));
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
    public void test9() throws Exception {
        final Base32 codec = new Base32((byte)0x25); // '%' <=> 0x25

        for (final String[] element : BASE32_PAD_TEST_CASES) {
                assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));
        }
    }
    @Test
    public void test10() {
        for (int i = 0; i < 20; i++) {
            final Base32 codec = new Base32();
            final byte[][] b = Base32TestData.randomData(codec, i);
            assertEquals(""+i+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
            //assertEquals(b[0],codec.decode(b[1]));
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
            assertEquals(""+i+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
            //assertEquals(b[0],codec.decode(b[1]));
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
    public void test14() throws Exception {
        final Base32 codec = new Base32(20);
        for (final String[] element : BASE32_TEST_CASES_CHUNKED) {
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
        final Base32 codec = new Base32(true);
        for (final String[] element : BASE32HEX_TEST_CASES) {
                assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));
        }
    }
    @Test
    public void test17() {
        final Base32 codec = new Base32();
        assertFalse(codec.isInAlphabet((byte) -1));
        assertFalse(codec.isInAlphabet((byte) -50));
        assertFalse(codec.isInAlphabet((byte) 127));
    }
    @Test
    public void test18() {
        final Base32 codec = new Base32();
        assertTrue(codec.isInAlphabet((byte) 0));
    }
    @Test
    public void test19() {
        final Base32 codec = new Base32();
        assertFalse(codec.isInAlphabet((byte) 255));
    }
}