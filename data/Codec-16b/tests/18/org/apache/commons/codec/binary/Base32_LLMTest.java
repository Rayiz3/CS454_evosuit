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

        // Regression test case
        final Base32 codec = new Base32(true);
        final byte[][] b = Base32TestData.randomData(codec, 20);
        assertEquals(""+20+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
        //assertEquals(b[0],codec.decode(b[1]));

        final Base32 codec2 = new Base32(true);
        final byte[][] b2 = Base32TestData.randomData(codec, 15);
        assertEquals(""+15+" "+codec2.lineLength,b2[1].length,codec2.getEncodedLength(b2[0]));
        //assertEquals(b2[0],codec2.decode(b2[1]));
    }
    @Test
    public void test1() throws Exception {
        final Base32 codec = new Base32(true);
        for (final String[] element : BASE32HEX_TEST_CASES) {
            assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));
        }

        // Regression test case
        final Base32 codec2 = new Base32(true);
        assertEquals("MZXW6IDCMQ======", codec2.encodeAsString("test".getBytes(Charsets.UTF_8)));
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

        // Regression test case
        final Base32 codec = new Base32();
        final BaseNCodec.Context context = new BaseNCodec.Context();
        final byte unencoded[] = {1, 3, 5, 7, 9};
        final byte allInOne[] = codec.encode(unencoded);
        codec.encode(unencoded, 0, -1, context);
        final byte singly[] = new byte[allInOne.length];
        codec.readResults(singly, 0, 100, context);
        assertTrue(Arrays.equals(allInOne, singly));
    }
    @Test
    public void test3() {
        for (int i = 0; i < 20; i++) {
            final Base32 codec = new Base32(10);
            final byte[][] b = Base32TestData.randomData(codec, i);
            assertEquals(""+i+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
            //assertEquals(b[0],codec.decode(b[1]));
        }

        // Regression test case
        final Base32 codec = new Base32(10);
        final byte[][] b = Base32TestData.randomData(codec, 20);
        assertEquals(""+20+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
        //assertEquals(b[0],codec.decode(b[1]));

        final Base32 codec2 = new Base32(10);
        final byte[][] b2 = Base32TestData.randomData(codec, 15);
        assertEquals(""+15+" "+codec2.lineLength,b2[1].length,codec2.getEncodedLength(b2[0]));
        //assertEquals(b2[0],codec2.decode(b2[1]));
    }
    @Test
    public void test4() throws Exception {
        final Base32 codec = new Base32(20);
        for (final String[] element : BASE32_TEST_CASES_CHUNKED) {
            assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));
        }

        // Regression test case
        final Base32 codec2 = new Base32(20);
        assertEquals("NBQWG", codec2.encodeAsString("test".getBytes(Charsets.UTF_8)));
    }
    @Test
    public void test5() throws Exception {
        final Base32 codec = new Base32((byte)0x25); // '%' <=> 0x25

        for (final String[] element : BASE32_PAD_TEST_CASES) {
            assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));
        }

        // Regression test case
        final Base32 codec2 = new Base32((byte)0x25); // '%' <=> 0x25
        assertEquals("6966J%��cgddmv9", codec2.encodeAsString("test".getBytes(Charsets.UTF_8)));
    }
    @Test
    public void test6() throws Exception {
        final Base32 codec = new Base32();
        for (final String[] element : BASE32_TEST_CASES) {
            assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));
        }

        // Regression test case
        final Base32 codec2 = new Base32();
        assertEquals("ORSXG5A=", codec2.encodeAsString("test".getBytes(Charsets.UTF_8)));
    }
    @Test
    public void test7() {
        for (int i = 0; i < 20; i++) {
            final Base32 codec = new Base32();
            final byte[][] b = Base32TestData.randomData(codec, i);
            assertEquals(""+i+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
            //assertEquals(b[0],codec.decode(b[1]));
        }

        // Regression test case
        final Base32 codec = new Base32();
        final byte[][] b = Base32TestData.randomData(codec, 20);
        assertEquals(""+20+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
        //assertEquals(b[0],codec.decode(b[1]));

        final Base32 codec2 = new Base32();
        final byte[][] b2 = Base32TestData.randomData(codec, 15);
        assertEquals(""+15+" "+codec2.lineLength,b2[1].length,codec2.getEncodedLength(b2[0]));
        //assertEquals(b2[0],codec2.decode(b2[1]));
    }
    @Test
    public void test8() {
        for (int i = 0; i < 20; i++) {
            final Base32 codec = new Base32(true);
            final byte[][] b = Base32TestData.randomData(codec, i);
            assertEquals(""+i+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
            //assertEquals(b[0],codec.decode(b[1]));
        }
    }
    @Test
    public void test9() throws Exception {
        final Base32 codec = new Base32(true);
        for (final String[] element : BASE32HEX_TEST_CASES) {
                assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));
        }
    }
    @Test
    public void test10() {
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
    public void test11() {
        for (int i = 0; i < 20; i++) {
            final Base32 codec = new Base32(10);
            final byte[][] b = Base32TestData.randomData(codec, i);
            assertEquals(""+i+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
            //assertEquals(b[0],codec.decode(b[1]));
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
        final Base32 codec = new Base32();
        for (final String[] element : BASE32_TEST_CASES) {
                assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));
        }
    }
    @Test
    public void test15() {
        for (int i = 0; i < 20; i++) {
            final Base32 codec = new Base32();
            final byte[][] b = Base32TestData.randomData(codec, i);
            assertEquals(""+i+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
            //assertEquals(b[0],codec.decode(b[1]));
        }
    }
    @Test
    public void test16() {
        final Base32 codec = new Base32(true, (byte)'W'); // should be allowed
        assertNotNull(codec);
    }
    @Test
    public void test17() {
        final Base32 codec = new Base32(true);
        assertFalse(codec.isInAlphabet((byte)-1));
    }
    @Test
    public void test18() {
        final Base32 codec = new Base32(true);
        assertTrue(codec.isInAlphabet((byte)0));
    }
    @Test
    public void test19() {
        final Base32 codec = new Base32(true);
        assertFalse(codec.isInAlphabet((byte)255));
    }
}