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
        // New regression test cases
        final Base32 codec = new Base32(true);
        final byte[][] b = Base32TestData.randomData(codec, 21);
        assertEquals(""+21+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
        //assertEquals(b[0],codec.decode(b[1]));
    }
    @Test
    public void test1() throws Exception {
        final Base32 codec = new Base32(true);
        for (final String[] element : BASE32HEX_TEST_CASES) {
                assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));
        }
        // New regression test cases
        final String[] element = {"test", "746573"};
        assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));
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
        // New regression test cases
        final Base32 codec = new Base32();
        final BaseNCodec.Context context = new BaseNCodec.Context();
        final byte unencoded[] = new byte[21];
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
        for (int i = 0; i < 20; i++) {
            final Base32 codec = new Base32(10);
            final byte[][] b = Base32TestData.randomData(codec, i);
            assertEquals(""+i+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
            //assertEquals(b[0],codec.decode(b[1]));
        }
        // New regression test cases
        final Base32 codec = new Base32(10);
        final byte[][] b = Base32TestData.randomData(codec, 21);
        assertEquals(""+21+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
        //assertEquals(b[0],codec.decode(b[1]));
    }
    @Test
    public void test4() throws Exception {
        final Base32 codec = new Base32(20);
        for (final String[] element : BASE32_TEST_CASES_CHUNKED) {
                assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));
        }
        // New regression test cases
        final String[] element = {"test", "746573"};
        assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));
    }
    @Test
    public void test5() throws Exception {
        final Base32 codec = new Base32((byte)0x25); // '%' <=> 0x25

        for (final String[] element : BASE32_PAD_TEST_CASES) {
                assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));
        }
        // New regression test cases
        final String[] element = {"test", "g5sstnw="};
        assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));
    }
    @Test
    public void test6() throws Exception {
        final Base32 codec = new Base32();
        for (final String[] element : BASE32_TEST_CASES) {
                assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));
        }
        // New regression test cases
        final String[] element = {"test", "orsxg5df"};
        assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));
    }
    @Test
    public void test7() {
        for (int i = 0; i < 20; i++) {
            final Base32 codec = new Base32();
            final byte[][] b = Base32TestData.randomData(codec, i);
            assertEquals(""+i+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
            //assertEquals(b[0],codec.decode(b[1]));
        }
        // New regression test cases
        final Base32 codec = new Base32();
        final byte[][] b = Base32TestData.randomData(codec, 21);
        assertEquals(""+21+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
        //assertEquals(b[0],codec.decode(b[1]));
    }
    @Test
    public void test8() {
        for (int i = 0; i < 20; i++) {
            final Base32 codec = new Base32(true);
            final byte[][] b = Base32TestData.randomData(codec, i);
            assertEquals(""+i+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
            byte mutatedByte = (byte)(b[0] + 1); // Mutated input
            assertFalse(codec.isInAlphabet(mutatedByte)); // Kill the mutant
            //assertEquals(b[0],codec.decode(b[1]));
        }
    }
    @Test
    public void test9() throws Exception {
        final Base32 codec = new Base32(true);
        for (final String[] element : BASE32HEX_TEST_CASES) {
                byte[] mutatedBytes = element[0].getBytes(Charsets.UTF_8); // Mutated input
                assertFalse(codec.isInAlphabet(mutatedBytes[0])); // Kill the mutant
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
                assertFalse(codec.isInAlphabet(unencoded[j])); // Kill the mutant
            }
            byte mutatedByte = (byte)(unencoded[0] + 1); // Mutated input
            assertFalse(codec.isInAlphabet(mutatedByte)); // Kill the mutant
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
            byte mutatedByte = (byte)(b[0] + 1); // Mutated input
            assertFalse(codec.isInAlphabet(mutatedByte)); // Kill the mutant
            //assertEquals(b[0],codec.decode(b[1]));
        }
    }
    @Test
    public void test12() throws Exception {
        final Base32 codec = new Base32(20);
        for (final String[] element : BASE32_TEST_CASES_CHUNKED) {
                byte[] mutatedBytes = element[0].getBytes(Charsets.UTF_8); // Mutated input
                assertFalse(codec.isInAlphabet(mutatedBytes[0])); // Kill the mutant
                assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));
        }
    }
    @Test
    public void test13() throws Exception {
        final Base32 codec = new Base32((byte)0x25); // '%' <=> 0x25

        for (final String[] element : BASE32_PAD_TEST_CASES) {
                byte[] mutatedBytes = element[0].getBytes(Charsets.UTF_8); // Mutated input
                assertFalse(codec.isInAlphabet(mutatedBytes[0])); // Kill the mutant
                assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));
        }
    }
    @Test
    public void test14() throws Exception {
        final Base32 codec = new Base32();
        for (final String[] element : BASE32_TEST_CASES) {
                byte[] mutatedBytes = element[0].getBytes(Charsets.UTF_8); // Mutated input
                assertFalse(codec.isInAlphabet(mutatedBytes[0])); // Kill the mutant
                assertEquals(element[1], codec.encodeAsString(element[0].getBytes(Charsets.UTF_8)));
        }
    }
    @Test
    public void test15() {
        for (int i = 0; i < 20; i++) {
            final Base32 codec = new Base32();
            final byte[][] b = Base32TestData.randomData(codec, i);
            assertEquals(""+i+" "+codec.lineLength,b[1].length,codec.getEncodedLength(b[0]));
            byte mutatedByte = (byte)(b[0] + 1); // Mutated input
            assertFalse(codec.isInAlphabet(mutatedByte)); // Kill the mutant
            //assertEquals(b[0],codec.decode(b[1]));
        }
    }
    @Test
    public void test16() {
        final Base32 codec = new Base32(true, (byte)'W'); // should be allowed
        assertNotNull(codec);
    }
}