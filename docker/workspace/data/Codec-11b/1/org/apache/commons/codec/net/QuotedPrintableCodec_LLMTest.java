package org.apache.commons.codec.net;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.apache.commons.codec.CharEncoding;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.junit.Test;

public class QuotedPrintableCodec_LLMTest {

    @Test
    public void test0() {
        // Arrange
        byte[] input = null;
        // Act
        byte[] result = QuotedPrintableCodec.encodeQuotedPrintable(null, input);
        // Assert
        assertNull(result);
    }




@Test
public void test6() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    byte[] bytes = null;
    byte[] encoded = qpcodec.encode(bytes);
    assertNull("Encoding a null byte array should return null", encoded);
}

@Test
public void test7() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    byte[] bytes = new byte[0];
    byte[] encoded = qpcodec.encode(bytes);
    assertEquals("Encoding an empty byte array should return an empty byte array", 0, encoded.length);
}

@Test
public void test8() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    byte[] bytes = "Hello there".getBytes(CharEncoding.UTF_8);
    byte[] encoded = qpcodec.encode(bytes);
    assertEquals("Encoding a byte array should return the correctly encoded byte array", 
        "=48=65=6C=6C=6F=20=74=68=65=72=65", new String(encoded, CharEncoding.UTF_8));
}

@Test
public void test9() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String encoded = "=48=65=6C=6C=6F=20=57=6F=72=6C=64";
    String expected = "Hello World";
    assertEquals(expected, qpcodec.decode(encoded));
}

@Test
public void test10() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String encoded = "=48=65=6C=6C=6F=20=57=6F=72=6C=64";
    String expected = "Hello World";
    assertEquals(expected, qpcodec.decode(encoded));
}

@Test
public void test11() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String encoded = "";
    String expected = "";
    assertEquals(expected, qpcodec.decode(encoded));
}

@Test
public void test12() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String encoded = "This=20is=20a=20t=65st=20with=20sp=65cial=20ch=61ract=65rs";
    String expected = "This is a test with special characters";
    assertEquals(expected, qpcodec.decode(encoded));
}

@Test
public void test13() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "Test Plain Text";
    String encoded = qpcodec.encode(plain);
    assertEquals("Regression test 1", "Test Plain Text", qpcodec.decode(encoded));
}

@Test
public void test14() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "!@#$%^&*()_+=-1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String encoded = qpcodec.encode(plain);
    assertEquals("Regression test 2", 
        "!@#$%^&*()_+=-1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ", encoded);
}

@Test
public void test15() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "";
    String encoded = qpcodec.encode(plain);
    assertEquals("Regression test 3", "", qpcodec.decode(encoded));
}

@Test
public void test16() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "= Hello world =\r\n";
    String encoded = qpcodec.encode(plain);
    String decoded = qpcodec.decode(encoded, CharEncoding.UTF_8);
    assertEquals("Regression test 1", plain, decoded);
}

@Test
public void test17() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "Hello=20there";
    String encoded = qpcodec.encode(plain);
    String decoded = qpcodec.decode(encoded, CharEncoding.UTF_8);
    assertEquals("Regression test 2", "Hello there", decoded);
}

@Test
public void test18() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "=1+1 =3D 2\r\n";
    String encoded = qpcodec.encode(plain);
    String decoded = qpcodec.decode(encoded, CharEncoding.UTF_8);
    assertEquals("Regression test 3", "1+1 = 2", decoded);
}

    @Test
    public void test19() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String encoded = "=48=65=6C=6C=6F=20=57=6F=72=6C=64";
        String expected = "Hello World";
        String actual = qpcodec.decode(encoded);
        assertEquals(expected, actual);
    }
    
    @Test
    public void test20() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String encoded = "=3D=0D=0A";
        String expected = "=\r\n";
        String actual = qpcodec.decode(encoded);
        assertEquals(expected, actual);
    }
  
    @Test
    public void test21() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String encoded = "=41=20=42=20=43";
        String expected = "A B C";
        String actual = qpcodec.decode(encoded);
        assertEquals(expected, actual);
    }

@Test
public void test22() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    Object object = null;
    Object encoded = qpcodec.encode(object);
    assertNull("Encoding null object should return null", encoded);
}

@Test
public void test23() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    byte[] byteArray = { 65, 66, 67 };
    Object encoded = qpcodec.encode(byteArray);
    assertEquals("Encoding byte array should return the expected string", "ABC", encoded);
}

@Test
public void test24() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    Object object = new Integer(123);
    try {
        qpcodec.encode(object);
        fail("Encoding an unsupported object should throw an exception");
    } catch (EncoderException e) {
        // Exception expected, test passes
    }
}

@Test
public void test25() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    byte[] encoded = "=41=42=43".getBytes(CharEncoding.ISO_8859_1);
    byte[] decoded = qpcodec.decode(encoded);
    String decodedString = new String(decoded, CharEncoding.ISO_8859_1);
    assertEquals("Decoding a byte array using QuotedPrintableCodec", "ABC", decodedString);
}

@Test
public void test26() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String encoded = "=48=65=6C=6C=6F=20=57=6F=72=6C=64";
    String decoded = qpcodec.decode(encoded);
    assertEquals("Decoding a string using QuotedPrintableCodec", "Hello World", decoded);
}

@Test
public void test27() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    try {
        qpcodec.decode(123);
        fail("DecoderException should have been thrown");
    } catch (DecoderException e) {
        // Expected. Move on
    }
}



@Test
public void test29() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "= Hello there =\r\n";
    String charset = CharEncoding.UTF_8;
    String encoded = qpcodec.encode(plain, charset);
    assertEquals("Encoding with charset should return the expected encoded value", 
        "=3D Hello there =3D=0D=0A", encoded);
}


@Test
public void test31() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "";
    String charset = CharEncoding.UTF_8;
    String encoded = qpcodec.encode(plain, charset);
    assertEquals("Encoding an empty string should return an empty string",
        "", encoded);
}

}