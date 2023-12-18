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
        final Base32 codec = new Base32(true);
        final byte[][] b = Base32TestData.randomData(codec, 20);
        assertEquals(20+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
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
    }
    @Test
    public void test3() {
        final Base32 codec = new Base32(10);
        final byte[][] b = Base32TestData.randomData(codec, 20);
        assertEquals(20+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
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
        final Base32 codec = new Base32();
        final byte[][] b = Base32TestData.randomData(codec, 20);
        assertEquals(20+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
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
    public void test9() {
        final Base32 codec = new Base32(true);
        final byte[][] b = Base32TestData.randomData(codec, -1);
        assertEquals(""+-1+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
        //assertEquals(b[0],codec.decode(b[1]));
    }
    @Test
    public void test10() {
        final Base32 codec = new Base32(true);
        final byte[][] b = Base32TestData.randomData(codec, 0);
        assertEquals(""+0+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
        //assertEquals(b[0],codec.decode(b[1]));
    }
    @Test
    public void test11() {
        final Base32 codec = new Base32(true);
        final byte[][] b = Base32TestData.randomData(codec, 100);
        assertEquals(""+100+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
        //assertEquals(b[0],codec.decode(b[1]));
    }
    @Test
    public void test12() throws Exception {
        final Base32 codec = new Base32(true);
        for (final String[] element : BASE32HEX_TEST_CASES) {
                assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));
        }
    }
    @Test
    public void test13() throws Exception {
        final Base32 codec = new Base32(true);
        assertEquals(null, codec.encodeAsString(null));
    }
    @Test
    public void test14() throws Exception {
        final Base32 codec = new Base32(true);
        assertEquals("", codec.encodeAsString("".getBytes(Charsets.UTF_8)));
    }
    @Test
    public void test15() throws Exception {
        final Base32 codec = new Base32(true);
        final StringBuilder sb = new StringBuilder();
        for(int i=0; i<1000; i++) {
            sb.append("a");
        }
        final String input = sb.toString();
        assertEquals(input, codec.encodeAsString(input.getBytes(Charsets.UTF_8)));
    }
    @Test
    public void test16() {
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
    public void test17() {
        Base32 codec = new Base32();
        final BaseNCodec.Context context = new BaseNCodec.Context();
        final byte unencoded[] = new byte[-1];
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
    public void test18() {
        Base32 codec = new Base32();
        final BaseNCodec.Context context = new BaseNCodec.Context();
        final byte unencoded[] = new byte[100];
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
    public void test19() {
        for (int i = 0; i < 20; i++) {
            final Base32 codec = new Base32(10);
            final byte[][] b = Base32TestData.randomData(codec, i);
            assertEquals(""+i+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
            //assertEquals(b[0],codec.decode(b[1]));
        }
    }
    @Test
    public void test20() {
        final Base32 codec = new Base32(-1);
        final byte[][] b = Base32TestData.randomData(codec, 10);
        assertEquals(""+10+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
        //assertEquals(b[0],codec.decode(b[1]));
    }
    @Test
    public void test21() {
        final Base32 codec = new Base32(0);
        final byte[][] b = Base32TestData.randomData(codec, 10);
        assertEquals(""+10+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
        //assertEquals(b[0],codec.decode(b[1]));
    }
    @Test
    public void test22() {
        final Base32 codec = new Base32(100);
        final byte[][] b = Base32TestData.randomData(codec, 10);
        assertEquals(""+10+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
        //assertEquals(b[0],codec.decode(b[1]));
    }
    @Test
    public void test23() throws Exception {
        final Base32 codec = new Base32(20);
        for (final String[] element : BASE32_TEST_CASES_CHUNKED) {
                assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));
        }
    }
    @Test
    public void test24() throws Exception {
        final Base32 codec = new Base32(20);
        assertEquals(null, codec.encodeAsString(null));
    }
    @Test
    public void test25() throws Exception {
        final Base32 codec = new Base32(20);
        assertEquals("", codec.encodeAsString("".getBytes(Charsets.UTF_8)));
    }
    @Test
    public void test26() throws Exception {
        final Base32 codec = new Base32(20);
        final StringBuilder sb = new StringBuilder();
        for(int i=0; i<1000; i++) {
            sb.append("a");
        }
        final String input = sb.toString();
        assertEquals(input, codec.encodeAsString(input.getBytes(Charsets.UTF_8)));
    }
    @Test
    public void test27() throws Exception {
        final Base32 codec = new Base32((byte)0x25); // '%' <=> 0x25

        for (final String[] element : BASE32_PAD_TEST_CASES) {
                assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));
        }
    }
    @Test
    public void test28() throws Exception {
        final Base32 codec = new Base32((byte)0x25); // '%' <=> 0x25
        assertEquals(null, codec.encodeAsString(null));
    }
    @Test
    public void test29() throws Exception {
        final Base32 codec = new Base32((byte)0x25); // '%' <=> 0x25
        assertEquals("", codec.encodeAsString("".getBytes(Charsets.UTF_8)));
    }
    @Test
    public void test30() throws Exception {
        final Base32 codec = new Base32((byte)0x25); // '%' <=> 0x25
        final StringBuilder sb = new StringBuilder();
        for(int i=0; i<1000; i++) {
            sb.append("a");
        }
        final String input = sb.toString();
        assertEquals(input, codec.encodeAsString(input.getBytes(Charsets.UTF_8)));
    }
    @Test
    public void test31() throws Exception {
        final Base32 codec = new Base32();
        for (final String[] element : BASE32_TEST_CASES) {
                assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));
        }
    }
    @Test
    public void test32() throws Exception {
        final Base32 codec = new Base32();
        assertEquals(null, codec.encodeAsString(null));
    }
    @Test
    public void test33() throws Exception {
        final Base32 codec = new Base32();
        assertEquals("", codec.encodeAsString("".getBytes(Charsets.UTF_8)));
    }
    @Test
    public void test34() throws Exception {
        final Base32 codec = new Base32();
        final StringBuilder sb = new StringBuilder();
        for(int i=0; i<1000; i++) {
            sb.append("a");
        }
        final String input = sb.toString();
        assertEquals(input, codec.encodeAsString(input.getBytes(Charsets.UTF_8)));
    }
    @Test
    public void test35() {
        for (int i = 0; i < 20; i++) {
            final Base32 codec = new Base32();
            final byte[][] b = Base32TestData.randomData(codec, i);
            assertEquals(""+i+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
            //assertEquals(b[0],codec.decode(b[1]));
        }
    }
    @Test
    public void test36() {
        final Base32 codec = new Base32();
        final byte[][] b = Base32TestData.randomData(codec, -1);
        assertEquals(""+-1+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
        //assertEquals(b[0],codec.decode(b[1]));
    }
    @Test
    public void test37() {
        final Base32 codec = new Base32();
        final byte[][] b = Base32TestData.randomData(codec, 0);
        assertEquals(""+0+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
        //assertEquals(b[0],codec.decode(b[1]));
    }
    @Test
    public void test38() {
        final Base32 codec = new Base32();
        final byte[][] b = Base32TestData.randomData(codec, 100);
        assertEquals(""+100+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
        //assertEquals(b[0],codec.decode(b[1]));
    }
    @Test
    public void test39() {
        final Base32 codec = new Base32(true, (byte)'W'); // should be allowed
        assertNotNull(codec);
    }
    @Test
    public void test40() {
        final Base32 codec = new Base32(true, (byte)'W'); // should be allowed
        assertNotNull(codec);
    }
    @Test
    public void test41() {
        final Base32 codec = new Base32(true, (byte)-1);
        assertNotNull(codec);
    }
    @Test
    public void test42() {
        final Base32 codec = new Base32(true, (byte)'W'); // should be allowed
        assertNotNull(codec);
    }
}