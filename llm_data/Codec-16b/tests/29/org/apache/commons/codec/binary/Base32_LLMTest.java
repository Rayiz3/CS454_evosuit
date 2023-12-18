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
    public void test8(){
        final Base32 codec = new Base32();
        final byte[] b = new byte[0];
        assertEquals(0, codec.getEncodedLength(b));
    }
    @Test
    public void test9(){
        final Base32 codec = new Base32();
        final BaseNCodec.Context context = new BaseNCodec.Context();
        final byte[] b = new byte[]{66};
        final byte[] expectedResult = new byte[]{74,65,61,61,61,61,61,61};
        final byte[] allInOne = codec.encode(b);
        for(int j=0; j<b.length; j++){
            codec.encode(b, j, 1, context);
        }
        codec.encode(b, 0, -1, context);
        final byte[] singly = new byte[allInOne.length];
        codec.readResults(singly, 0, 100, context);
        assertArrayEquals(expectedResult, singly);
    }
    @Test
    public void test10() {
        final Base32 codec = new Base32();
        final BaseNCodec.Context context = new BaseNCodec.Context();
        final byte[] b = new byte[]{-128};
        final byte[] allInOne = codec.encode(b);
        for(int j=0; j<b.length; j++){
            codec.encode(b, j, 1, context);
        }
        codec.encode(b, 0, -1, context);
        final byte[] singly = new byte[allInOne.length];
        codec.readResults(singly, 0, 100, context);
        assertArrayEquals(allInOne, singly);
    }
    @Test
    public void test11(){
        final Base32 codec = new Base32();
        final byte[] b = null;
        assertEquals(0, codec.getEncodedLength(b));
    }
    @Test
    public void test12() {
        final Base32 codec = new Base32(-1);
        final BaseNCodec.Context context = new BaseNCodec.Context();
        final byte[] b = new byte[]{66};
        final byte[] expectedResult = new byte[]{74,65}; // lineSeparator not appended as lineLength is negative
        final byte[] allInOne = codec.encode(b);
        for(int j=0; j<b.length; j++){
            codec.encode(b, j, 1, context);
        }
        codec.encode(b, 0, -1, context);
        final byte[] singly = new byte[allInOne.length];
        codec.readResults(singly, 0, 100, context);
        assertArrayEquals(expectedResult, singly);
    }
    @Test
    public void test13(){
        final Base32 codec = new Base32((byte) 0x25); // '%' <=> 0x25;
        final BaseNCodec.Context context = new BaseNCodec.Context();
        final byte[] b = new byte[]{-128};
        final byte[] expectedResult = new byte[]{74,38,37,37,37,37,37,37};
        final byte[] allInOne = codec.encode(b);
        for(int j=0; j<b.length; j++){
            codec.encode(b, j, 1, context);
        }
        codec.encode(b, 0, -1, context);
        final byte[] singly = new byte[allInOne.length];
        codec.readResults(singly, 0, 100, context);
        assertArrayEquals(expectedResult, singly);
    }
    @Test
    public void test14() {
        for (int i = 0; i < 20; i++) {
            final Base32 codec = new Base32(true);
            final byte[][] b = Base32TestData.randomData(codec, i);
            assertEquals(""+i+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
            //assertEquals(b[0],codec.decode(b[1]));

            // New regression test case
            final byte[][] b2 = Base32TestData.randomData(codec, i+1);
            assertEquals(""+i+" "+codec.lineLength,b2[1].length,codec.getEncodedLength(b2[0]));
        }
    }
    @Test
    public void test15() throws Exception {
        final Base32 codec = new Base32(true);
        for (final String[] element : BASE32HEX_TEST_CASES) {
                assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));

                // New regression test case
                final byte[] bytes = element[0].getBytes(Charsets.UTF_8);
                bytes[0]++; // Change the first character of the input
                assertNotEquals(element[1], codec.encodeAsString(bytes));
        }
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

            // New regression test case
            final byte unencoded2[] = new byte[i+1];
            final byte allInOne2[] = codec.encode(unencoded2);
            assertArrayEquals(allInOne2, allInOne);
        }
    }
    @Test
    public void test17() {
        for (int i = 0; i < 20; i++) {
            final Base32 codec = new Base32(10);
            final byte[][] b = Base32TestData.randomData(codec, i);
            assertEquals(""+i+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
            //assertEquals(b[0],codec.decode(b[1]));

            // New regression test case
            final Base32 codec2 = new Base32(5);
            final byte[][] b2 = Base32TestData.randomData(codec2, i+1);
            assertEquals(""+i+" "+codec2.lineLength,b2[1].length,codec2.getEncodedLength(b2[0]));
        }
    }
    @Test
    public void test18() throws Exception {
        final Base32 codec = new Base32(20);
        for (final String[] element : BASE32_TEST_CASES_CHUNKED) {
                assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));

                // New regression test case
                final byte[] bytes = element[0].getBytes(Charsets.UTF_8);
                bytes[0]++; // Change the first character of the input
                assertNotEquals(element[1], codec.encodeAsString(bytes));
        }
    }
    @Test
    public void test19() throws Exception {
        final Base32 codec = new Base32((byte)0x25); // '%' <=> 0x25

        for (final String[] element : BASE32_PAD_TEST_CASES) {
                assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));

                // New regression test case
                final byte[] bytes = element[0].getBytes(Charsets.UTF_8);
                bytes[0]++; // Change the first character of the input
                assertNotEquals(element[1], codec.encodeAsString(bytes));
        }
    }
    @Test
    public void test20() throws Exception {
        final Base32 codec = new Base32();
        for (final String[] element : BASE32_TEST_CASES) {
                assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));

                // New regression test case
                final byte[] bytes = element[0].getBytes(Charsets.UTF_8);
                bytes[0]++; // Change the first character of the input
                assertNotEquals(element[1], codec.encodeAsString(bytes));
        }
    }
    @Test
    public void test21() {
        for (int i = 0; i < 20; i++) {
            final Base32 codec = new Base32();
            final byte[][] b = Base32TestData.randomData(codec, i);
            assertEquals(""+i+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
            //assertEquals(b[0],codec.decode(b[1]));

            // New regression test case
            final byte[][] b2 = Base32TestData.randomData(codec, i+1);
            assertEquals(""+i+" "+codec.lineLength,b2[1].length,codec.getEncodedLength(b2[0]));
        }
    }
    @Test
    public void test22() {
        final Base32 codec = new Base32(true, (byte)'W'); // should be allowed
        assertNotNull(codec);

        // New regression test case
        final Base32 codec2 = new Base32(false, (byte)'W');
        assertNotNull(codec2);
    }
}