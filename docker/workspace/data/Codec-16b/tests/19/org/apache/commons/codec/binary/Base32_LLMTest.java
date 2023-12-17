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
        // Regression test 1
        final Base32 codec = new Base32(true);
        final byte[][] b = Base32TestData.randomData(codec, 0);
        assertEquals(""+0+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
        //assertEquals(b[0],codec.decode(b[1]));

        // Regression test 2
        final Base32 codec2 = new Base32(true);
        final byte[][] b2 = Base32TestData.randomData(codec2, 1);
        assertEquals(""+1+" "+codec2.lineLength,b2[1].length,codec2.getEncodedLength(b2[0]));
        //assertEquals(b[0],codec.decode(b[1]));
        
        // ... (add additional regression tests here)
    }
    @Test
    public void test1() throws Exception {
        // Regression test 1
        final Base32 codec = new Base32(true);
        assertEquals(BASE32HEX_TEST_CASES[0][1], codec.encodeAsString(BASE32HEX_TEST_CASES[0][0].getBytes(Charsets.UTF_8)));

        // Regression test 2
        final Base32 codec2 = new Base32(true);
        assertEquals(BASE32HEX_TEST_CASES[1][1], codec2.encodeAsString(BASE32HEX_TEST_CASES[1][0].getBytes(Charsets.UTF_8)))

        // ... (add additional regression tests here)
    }
    @Test
    public void test2() {
        // Regression test 1
        Base32 codec = new Base32();
        BaseNCodec.Context context = new BaseNCodec.Context();
        final byte unencoded[] = new byte[0];
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

        // Regression test 2
        Base32 codec2 = new Base32();
        BaseNCodec.Context context2 = new BaseNCodec.Context();
        final byte unencoded2[] = new byte[1];
        final byte allInOne2[] = codec.encode(unencoded2);
        codec2 = new Base32();
        for (int j=0; j< unencoded2.length; j++) {
            codec2.encode(unencoded2, j, 1, context2);
        }
        codec2.encode(unencoded2, 0, -1, context2);
        final byte singly2[] = new byte[allInOne2.length];
        codec2.readResults(singly2, 0, 100, context2);
        if (!Arrays.equals(allInOne2, singly2)){
            fail();
        }

        // ... (add additional regression tests here)
    }
    @Test
    public void test3() {
        // Regression test 1
        final Base32 codec = new Base32(10);
        final byte[][] b = Base32TestData.randomData(codec, 0);
        assertEquals(""+0+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
        //assertEquals(b[0],codec.decode(b[1]));

        // Regression test 2
        final Base32 codec2 = new Base32(10);
        final byte[][] b2 = Base32TestData.randomData(codec2, 1);
        assertEquals(""+1+" "+codec2.lineLength,b2[1].length,codec2.getEncodedLength(b2[0]));
        //assertEquals(b[0],codec.decode(b[1]));
        
        // ... (add additional regression tests here)
    }
    @Test
    public void test4() throws Exception {
        // Regression test 1
        final Base32 codec = new Base32(20);
        assertEquals(BASE32_TEST_CASES_CHUNKED[0][1], codec.encodeAsString(BASE32_TEST_CASES_CHUNKED[0][0].getBytes(Charsets.UTF_8)));

        // Regression test 2
        final Base32 codec2 = new Base32(20);
        assertEquals(BASE32_TEST_CASES_CHUNKED[1][1], codec2.encodeAsString(BASE32_TEST_CASES_CHUNKED[1][0].getBytes(Charsets.UTF_8)));

        // ... (add additional regression tests here)
    }
    @Test
    public void test5() throws Exception {
        // Regression test 1
        final Base32 codec = new Base32((byte)0x25); // '%' <=> 0x25
        assertEquals(BASE32_PAD_TEST_CASES[0][1], codec.encodeAsString(BASE32_PAD_TEST_CASES[0][0].getBytes(Charsets.UTF_8)));

        // Regression test 2
        final Base32 codec2 = new Base32((byte)0x25); // '%' <=> 0x25
        assertEquals(BASE32_PAD_TEST_CASES[1][1], codec2.encodeAsString(BASE32_PAD_TEST_CASES[1][0].getBytes(Charsets.UTF_8)));

        // ... (add additional regression tests here)
    }
    @Test
    public void test6() throws Exception {
        // Regression test 1
        final Base32 codec = new Base32();
        assertEquals(BASE32_TEST_CASES[0][1], codec.encodeAsString(BASE32_TEST_CASES[0][0].getBytes(Charsets.UTF_8)));

        // Regression test 2
        final Base32 codec2 = new Base32();
        assertEquals(BASE32_TEST_CASES[1][1], codec2.encodeAsString(BASE32_TEST_CASES[1][0].getBytes(Charsets.UTF_8)));

        // ... (add additional regression tests here)
    }
    @Test
    public void test7() {
        // Regression test 1
        final Base32 codec = new Base32();
        final byte[][] b = Base32TestData.randomData(codec, 0);
        assertEquals(""+0+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
        //assertEquals(b[0],codec.decode(b[1]));

        // Regression test 2
        final Base32 codec2 = new Base32();
        final byte[][] b2 = Base32TestData.randomData(codec2, 1);
        assertEquals(""+1+" "+codec2.lineLength,b2[1].length,codec2.getEncodedLength(b2[0]));
        //assertEquals(b[0],codec.decode(b[1]));
        
        // ... (add additional regression tests here)
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
        final Base32 codec = new Base32();
        assertFalse(codec.isInAlphabet((byte)-1));
    }
    @Test
    public void test18() {
        final Base32 codec = new Base32();
        codec.decodeTable = new int[0];
        assertFalse(codec.isInAlphabet((byte)0));
    }
    @Test
    public void test19() {
        final Base32 codec = new Base32();
        assertFalse(codec.isInAlphabet((byte)255));
    }
    @Test
    public void test20() {
        final Base32 codec = new Base32();
        assertTrue(codec.isInAlphabet((byte)15));
    }
}