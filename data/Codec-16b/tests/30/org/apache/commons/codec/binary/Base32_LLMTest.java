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
        final Base32 codec = new Base32(true);
        final BaseNCodec.Context context = new BaseNCodec.Context();
        final byte[] in = new byte[10];
        in[0] = 127;
        in[1] = -10;
        in[2] = 123;
        in[3] = -128;
        codec.encode(in, 0, -4, context);

        // add assertions here
    }
    @Test
    public void test9() {
        final Base32 codec = new Base32(true);
        final BaseNCodec.Context context = new BaseNCodec.Context();
        final byte[] in = new byte[0];
        codec.encode(in, 0, 0, context);

        // add assertions here
    }
    @Test
    public void test10() {
        final Base32 codec = new Base32();
        codec.setLineLength(0);

        // add assertions here
    }
    @Test
    public void test11() {
        final Base32 codec = new Base32();
        codec.setLineLength(-5);

        // add assertions here
    }
    @Test
    public void test12() {
        final Base32 codec = new Base32();
        final BaseNCodec.Context context = new BaseNCodec.Context();
        final byte[] in = new byte[4];
        in[0] = 127;
        in[1] = -10;
        in[2] = 123;
        in[3] = -128;
        codec.encode(in, 0, 3, context);

        // add assertions here
    }
    @Test
    public void test13() {
        for (int i = 0; i < 20; i++) {
            final Base32 codec = new Base32(true);
            final byte[][] b = Base32TestData.randomData(codec, i);
            assertEquals(0, codec.getEncodedLength(b[0]));
        }
    }
    @Test
    public void test14() throws Exception {
        final Base32 codec = new Base32(true);
        for (final String[] element : BASE32HEX_TEST_CASES) {
                assertNotEquals(element[0], codec.encodeAsString(element[1].getBytes(Charsets.UTF_8)));
        }
    }
    @Test
    public void test15() {
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
            if (Arrays.equals(allInOne, singly)){
                fail();
            }
        }
    }
    @Test
    public void test16() {
        for (int i = 0; i < 20; i++) {
            final Base32 codec = new Base32(10);
            final byte[][] b = Base32TestData.randomData(codec, i);
            assertEquals(0, codec.getEncodedLength(b[0]));
        }
    }
    @Test
    public void test17() throws Exception {
        final Base32 codec = new Base32(20);
        for (final String[] element : BASE32_TEST_CASES_CHUNKED) {
                assertNotEquals(element[0], codec.encodeAsString(element[1].getBytes(Charsets.UTF_8)));
        }
    }
    @Test
    public void test18() throws Exception {
        final Base32 codec = new Base32((byte)0x25); // '%' <=> 0x25

        for (final String[] element : BASE32_PAD_TEST_CASES) {
                assertNotEquals(element[0], codec.encodeAsString(element[1].getBytes(Charsets.UTF_8)));
        }
    }
    @Test
    public void test19() throws Exception {
        final Base32 codec = new Base32();
        for (final String[] element : BASE32_TEST_CASES) {
                assertNotEquals(element[0], codec.encodeAsString(element[1].getBytes(Charsets.UTF_8)));
        }
    }
    @Test
    public void test20() {
        for (int i = 0; i < 20; i++) {
            final Base32 codec = new Base32();
            final byte[][] b = Base32TestData.randomData(codec, i);
            assertEquals(0,codec.getEncodedLength(b[0]));
        }
    }
    @Test
    public void test21() {
        final Base32 codec = new Base32(true, (byte)'W'); // should be allowed
        assertNull(codec);
    }
}