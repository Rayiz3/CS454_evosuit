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
    public void test0() throws Exception {
        String qpdata = "If you believe that truth=3Dbeauty, then surely mathematics is the most " +
                "b=\r\neautiful branch of philosophy.";
        String expected = "If you believe that truth=beauty, then surely mathematics is the most " +
                "beautiful branch of philosophy.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(qpdata, qpcodec.encode(expected));

        String decoded = qpcodec.decode(qpdata);
        assertEquals(qpdata, qpcodec.encode(decoded));
        
        // Change input to kill more mutants
        qpdata = "If you believe that truth=3Dbeauty, then surely mathematics is the most " +
                "b=\r\neautiful branch of philosophy. ";
        expected = "If you believe that truth=beauty, then surely mathematics is the most " +
                "beautiful branch of philosophy. ";

        qpcodec = new QuotedPrintableCodec();
        assertEquals(qpdata, qpcodec.encode(expected));

        decoded = qpcodec.decode(qpdata);
        assertEquals(qpdata, qpcodec.encode(decoded));
    }
    @Test
    public void test1() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there =\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Hello there =3D=0D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        
        // Change input to kill more mutants
        plain = "= Hello there =\r\n ";
        encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Hello there =3D=0D=0A ", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test2() {
        BitSet printable = new BitSet(256);
        printable.set('a', 'z' + 1);
        printable.set('A', 'Z' + 1);
        printable.set('0', '9' + 1);
        printable.set(' ');

        byte[] bytes = "Hello".getBytes();
        byte[] encoded = QuotedPrintableCodec.encodeQuotedPrintable(printable, bytes);
        assertEquals(bytes, encoded); // original bytes are printable

        bytes = "Hello\n".getBytes();
        encoded = QuotedPrintableCodec.encodeQuotedPrintable(printable, bytes);
        assertArrayEquals("Hello=0A".getBytes(), encoded); // newline is encoded

        bytes = "Hello~".getBytes();
        encoded = QuotedPrintableCodec.encodeQuotedPrintable(printable, bytes);
        assertArrayEquals("Hello~".getBytes(), encoded); // tilde is printable, not encoded
    }
    @Test
    public void test3() {
        BitSet printable = new BitSet(256);
        printable.set('a', 'z' + 1);

        byte[] bytes = "Hello\n".getBytes();
        byte[] encoded = QuotedPrintableCodec.encodeQuotedPrintable(printable, bytes);
        assertArrayEquals("Hello\n".getBytes(), encoded); // newline is whitespace at the end, not encoded

        bytes = "Hello\nWorld".getBytes();
        encoded = QuotedPrintableCodec.encodeQuotedPrintable(printable, bytes);
        assertArrayEquals("Hello\nWorld".getBytes(), encoded); // newline within string is not encoded
    }
    @Test
    public void test4() {
        byte[] printableBytes = new byte[]{32, 45, 46, 95, 126};
        BitSet printable = new BitSet(256);
        for (byte b : printableBytes) {
            printable.set(b);
        }

        byte[] bytes = new byte[]{-1, -2, -3};
        byte[] expected = new byte[]{61, 2, 61, 3};
        byte[] encoded = QuotedPrintableCodec.encodeQuotedPrintable(printable, bytes);
        assertArrayEquals(expected, encoded); // non-printable bytes are encoded

        bytes = new byte[]{-1, -2, 32, 45};
        expected = new byte[]{61, 2, 20, 45};
        encoded = QuotedPrintableCodec.encodeQuotedPrintable(printable, bytes);
        assertArrayEquals(expected, encoded); // non-printable bytes before printable bytes are encoded
    }
    @Test
    public void test5() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there =\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Hello there =3D=0D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test6() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "1+1 =3D 2";
        String decoded = (String) qpcodec.decode((Object) plain);
        assertEquals("Basic quoted-printable decoding test", 
            "1+1 = 2", decoded);

        byte[] plainBA = plain.getBytes("UTF-8");
        byte[] decodedBA = (byte[]) qpcodec.decode((Object) plainBA);
        decoded = new String(decodedBA);
        assertEquals("Basic quoted-printable decoding test", 
            "1+1 = 2", decoded);
            
        Object result = qpcodec.decode((Object) null);
        assertEquals( "Decoding a null Object should return null", null, result);
        
        try {
            Object dObj = new Double(0.0);
            qpcodec.decode( dObj );
            fail( "Trying to url encode a Double object should cause an exception.");
        } catch (DecoderException ee) {
            // Exception expected, test segment passes.
        }
    }
    @Test
    public void test7() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test8() {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("INVALID");
        String plain = "Hello there!";
        try {
            qpcodec.encode(plain);
            fail( "We set the encoding to a bogus INVALID value, this shouldn't have worked.");
        } catch (EncoderException ee) {
            // Exception expected, test segment passes.
        }
        try {
            qpcodec.decode(plain);
            fail( "We set the encoding to a bogus INVALID value, this shouldn't have worked.");
        } catch (DecoderException ee) {
            // Exception expected, test segment passes.
        }
    }
    @Test
    public void test9() throws Exception {
        String qpdata = "If you believe that truth=3Dbeauty, then surely=20=\r\nmathematics " +
                "is the most beautiful branch of philosophy.";
        String expected = "If you believe that truth=beauty, then surely mathematics " +
                "is the most beautiful branch of philosophy.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(expected, qpcodec.decode(qpdata));

        String encoded = qpcodec.encode(expected);
        assertEquals(expected, qpcodec.decode(encoded));
    }
    @Test
    public void test10() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=3D=0D=0A", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test11() throws Exception {

        String ru_msg = constructString(RUSSIAN_STUFF_UNICODE); 
        String ch_msg = constructString(SWISS_GERMAN_STUFF_UNICODE); 
        
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        
        assertEquals(
            "=D0=92=D1=81=D0=B5=D0=BC_=D0=BF=D1=80=D0=B8=D0=B2=D0=B5=D1=82", 
        qpcodec.encode(ru_msg, CharEncoding.UTF_8)
        );
        assertEquals("Gr=C3=BCezi_z=C3=A4m=C3=A4", qpcodec.encode(ch_msg, CharEncoding.UTF_8));
        
        assertEquals(ru_msg, qpcodec.decode(qpcodec.encode(ru_msg, CharEncoding.UTF_8), CharEncoding.UTF_8));
        assertEquals(ch_msg, qpcodec.decode(qpcodec.encode(ch_msg, CharEncoding.UTF_8), CharEncoding.UTF_8));
    }
    @Test
    public void test12() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "1+1 = 2";
        String encoded = new String(QuotedPrintableCodec.
            encodeQuotedPrintable(null, plain.getBytes("UTF-8")));
        assertEquals("Basic quoted-printable encoding test", 
            "1+1 =3D 2", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        
    }
    @Test
    public void test13() throws Exception {
        byte[] plain = null;
        byte[] result = QuotedPrintableCodec.decodeQuotedPrintable( plain );
        assertEquals("Result should be null", null, result);
    }
    @Test
    public void test14() throws Exception {
        String qpdata = "CRLF in an\n encoded text should be=20=\r\n\rskipped in the\r decoding.";
        String expected = "CRLF in an encoded text should be skipped in the decoding.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(expected, qpcodec.decode(qpdata));

        String encoded = qpcodec.encode(expected);
        assertEquals(expected, qpcodec.decode(encoded));
    }
    @Test
    public void test15() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        try {
            qpcodec.decode("=");
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
        try {
            qpcodec.decode("=A");
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
        try {
            qpcodec.decode("=WW");
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
    }
@Test
public void test16() throws Exception {
    String qpdata = "If you believe that truth=3Dbeauty, then surely mathematics is the most " +
            "b=\r\neautiful branch of philosophy.";
    String expected = "If you believe that truth=beauty, then surely mathematics is the most " +
            "beautiful branch of philosophy.";

    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    assertEquals(expected, qpcodec.encode(expected)); // mutated the input to be the expected value

    String decoded = qpcodec.decode(qpdata);
    assertEquals(qpdata, qpcodec.encode(decoded));
}
@Test
public void test17() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "= Hello there =\r\n";
    String encoded = qpcodec.encode(plain);
    assertEquals("Basic quoted-printable encoding test",
        "=3D Hello there =3D=0D=0A", qpcodec.encode(encoded)); // mutated the input to be the encoded value
    assertEquals("Basic quoted-printable decoding test",
        plain, qpcodec.decode(encoded));
}
@Test
public void test18() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
    String encoded = qpcodec.encode(plain);
    assertEquals("Safe chars quoted-printable encoding test",
        plain, qpcodec.encode(encoded)); // mutated the input to be the encoded value
    assertEquals("Safe chars quoted-printable decoding test",
        plain, qpcodec.decode(encoded));
}
@Test
public void test19() throws Exception {
    final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

    String plain ="This is a example of a quoted-printable text file. There is no end to it\t";
    String expected = "This is a example of a quoted-printable text file. There is no end to i=\r\nt=09";

    assertEquals(expected, qpcodec.encode(expected)); // mutated the input to be the expected value

    plain ="This is a example of a quoted-printable text file. There is no end to it ";
    expected = "This is a example of a quoted-printable text file. There is no end to i=\r\nt=20";

    assertEquals(expected, qpcodec.encode(expected)); // mutated the input to be the expected value

    // whitespace before soft break
    plain ="This is a example of a quoted-printable text file. There is no end to   ";
    expected = "This is a example of a quoted-printable text file. There is no end to=20=\r\n =20";

    assertEquals(expected, qpcodec.encode(expected)); // mutated the input to be the expected value

    // non-printable character before soft break
    plain ="This is a example of a quoted-printable text file. There is no end to=  ";
    expected = "This is a example of a quoted-printable text file. There is no end to=3D=\r\n =20";

    assertEquals(expected, qpcodec.encode(expected)); // mutated the input to be the expected value
}
@Test
public void test20() {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("NONSENSE");
    String plain = "Hello there!";
    try {
        qpcodec.encode(plain); // mutated the input to be the plain value
        fail( "We set the encoding to a bogus NONSENSE vlaue, this shouldn't have worked.");
    } catch (EncoderException ee) {
        // Exception expected, test segment passes.
    }
    try {
        qpcodec.decode(plain); // mutated the input to be the plain value
        fail( "We set the encoding to a bogus NONSENSE vlaue, this shouldn't have worked.");
    } catch (DecoderException ee) {
        // Exception expected, test segment passes.
    }
}
@Test
public void test21() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "1+1 = 2";
    String encoded = (String) qpcodec.encode((Object) plain);
    assertEquals("Basic quoted-printable encoding test",
        "1+1 =3D 2", qpcodec.encode(encoded)); // mutated the input to be the encoded value

    byte[] plainBA = plain.getBytes("UTF-8");
    byte[] encodedBA = (byte[]) qpcodec.encode((Object) plainBA);
    encoded = new String(encodedBA);
    assertEquals("Basic quoted-printable encoding test",
        "1+1 =3D 2", qpcodec.encode(encoded)); // mutated the input to be the encoded value

    Object result = qpcodec.encode((Object) null);
    assertEquals( "Encoding a null Object should return null", null, result);

    try {
        Object dObj = new Double(3.0);
        qpcodec.encode( dObj );
        fail( "Trying to url encode a Double object should cause an exception.");
    } catch (EncoderException ee) {
        // Exception expected, test segment passes.
    }
}
@Test
public void test22() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    byte[] plain = null;
    byte[] encoded = qpcodec.encode(plain);
    assertEquals("Encoding a null string should return null",
        null, qpcodec.encode(encoded)); // mutated the input to be the encoded value
}
@Test
public void test23() throws Exception {
    final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

    String plain ="This is a example of a quoted-printable text file. This might contain sp=cial chars.";
    String expected = "This is a example of a quoted-printable text file. This might contain sp=3D=\r\ncial chars.";
    assertEquals(expected, qpcodec.encode(expected)); // mutated the input to be the expected value

    plain ="This is a example of a quoted-printable text file. This might contain ta\tbs as well.";
    expected = "This is a example of a quoted-printable text file. This might contain ta=09=\r\nbs as well.";
    assertEquals(expected, qpcodec.encode(expected)); // mutated the input to be the expected value
}
@Test
public void test24() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "=\r\n";
    String encoded = qpcodec.encode(plain);
    assertEquals("Unsafe chars quoted-printable encoding test",
        "=3D=0D=0A", qpcodec.encode(encoded)); // mutated the input to be the encoded value
    assertEquals("Unsafe chars quoted-printable decoding test",
        plain, qpcodec.decode(encoded));
}
@Test
public void test25() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String test = null;
    String result = qpcodec.encode( test, "charset" );
    assertEquals("Result should be null", null, qpcodec.encode(result)); // mutated the input to be the result value
}
@Test
public void test26() throws Exception {
    // whitespace, but does not need to be encoded
    String plain ="This is a example of a quoted=printable text file. There is no tt";
    String expected = "This is a example of a quoted=3Dprintable text file. There is no tt";

    assertEquals(expected, new QuotedPrintableCodec().encode(expected)); // mutated the input to be the expected value
}
@Test
public void test27() throws Exception {
    String plain = "Hello there!";
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("UnicodeBig");
    qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
    String encoded1 = qpcodec.encode(plain, "UnicodeBig");
    String encoded2 = qpcodec.encode(plain);
    assertEquals(encoded1, qpcodec.encode(encoded2)); // mutated the input to be the encoded value
}
    @Test
    public void test28() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertNull("Null string quoted-printable encoding test", 
            qpcodec.encode((String)null));
        assertNull("Null string quoted-printable decoding test", 
            qpcodec.decode((String)null));
        
        // Regression test: empty string
        String empty = "";
        assertEquals("Empty string quoted-printable encoding test", 
            "", qpcodec.encode(empty));
        assertEquals("Empty string quoted-printable decoding test", 
            empty, qpcodec.decode(empty));
    }
    @Test
    public void test29() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there =\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Hello there =3D=0D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        
        // Regression test: plain text without special characters
        String text = "Hello world";
        assertEquals("Plain text quoted-printable encoding test", 
            text, qpcodec.encode(text));
        assertEquals("Plain text quoted-printable decoding test", 
            text, qpcodec.decode(text));
    }
    @Test
    public void test30() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "1+1 =3D 2";
        String decoded = (String) qpcodec.decode((Object) plain);
        assertEquals("Basic quoted-printable decoding test", 
            "1+1 = 2", decoded);

        byte[] plainBA = plain.getBytes("UTF-8");
        byte[] decodedBA = (byte[]) qpcodec.decode((Object) plainBA);
        decoded = new String(decodedBA);
        assertEquals("Basic quoted-printable decoding test", 
            "1+1 = 2", decoded);
            
        Object result = qpcodec.decode((Object) null);
        assertEquals( "Decoding a null Object should return null", null, result);
        
        try {
            Object dObj = new Double(3.0);
            qpcodec.decode( dObj );
            fail( "Trying to url encode a Double object should cause an exception.");
        } catch (DecoderException ee) {
            // Exception expected, test segment passes.
        }
    }
    @Test
    public void test31() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        
        // Regression test: safe characters with spaces
        String safe = "abc 123";
        assertEquals("Safe chars with spaces quoted-printable encoding test", 
            safe, qpcodec.encode(safe));
        assertEquals("Safe chars with spaces quoted-printable decoding test", 
            safe, qpcodec.decode(safe));
    }
    @Test
    public void test32() {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("NONSENSE");
           String plain = "Hello there!";
            try {
               qpcodec.encode(plain);
                fail( "We set the encoding to a bogus NONSENSE value, this shouldn't have worked.");
            } catch (EncoderException ee) {
                // Exception expected, test segment passes.
            }
            try {
               qpcodec.decode(plain);
                fail( "We set the encoding to a bogus NONSENSE value, this shouldn't have worked.");
            } catch (DecoderException ee) {
                // Exception expected, test segment passes.
            }
    }
    @Test
    public void test33() throws Exception {
        String qpdata = "If you believe that truth=3Dbeauty, then surely=20=\r\nmathematics " +
                "is the most beautiful branch of philosophy.";
        String expected = "If you believe that truth=beauty, then surely mathematics " +
                "is the most beautiful branch of philosophy.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(expected, qpcodec.decode(qpdata));

        String encoded = qpcodec.encode(expected);
        assertEquals(expected, qpcodec.decode(encoded));
    }
    @Test
    public void test34() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=3D=0D=0A", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        
        // Regression test: unsafe characters with special encoding
        String unsafe = "&<>";
        String unsafeEncoded = "=26=3C=3E";
        assertEquals("Unsafe chars quoted-printable encoding test with special characters", 
            unsafeEncoded, qpcodec.encode(unsafe));
        assertEquals("Unsafe chars quoted-printable decoding test with special characters", 
            unsafe, qpcodec.decode(unsafeEncoded));
    }
    @Test
    public void test35() throws Exception {

        String ru_msg = constructString(RUSSIAN_STUFF_UNICODE); 
        String ch_msg = constructString(SWISS_GERMAN_STUFF_UNICODE); 
        
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        
        assertEquals(
            "=D0=92=D1=81=D0=B5=D0=BC_=D0=BF=D1=80=D0=B8=D0=B2=D0=B5=D1=82", 
        qpcodec.encode(ru_msg, CharEncoding.UTF_8)
        );
        assertEquals("Gr=C3=BCezi_z=C3=A4m=C3=A4", qpcodec.encode(ch_msg, CharEncoding.UTF_8));
        
        assertEquals(ru_msg, qpcodec.decode(qpcodec.encode(ru_msg, CharEncoding.UTF_8), CharEncoding.UTF_8));
        assertEquals(ch_msg, qpcodec.decode(qpcodec.encode(ch_msg, CharEncoding.UTF_8), CharEncoding.UTF_8));
    }
    @Test
    public void test36() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "1+1 = 2";
        String encoded = new String(QuotedPrintableCodec.
            encodeQuotedPrintable(null, plain.getBytes("UTF-8")));
        assertEquals("Basic quoted-printable encoding test", 
            "1+1 =3D 2", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        
    }
    @Test
    public void test37() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String test = null;
        String result = qpcodec.decode( test, "charset" );
        assertEquals("Result should be null", null, result);
    }
    @Test
    public void test38() throws Exception {
        String qpdata = "CRLF in an\n encoded text should be=20=\r\n\rskipped in the\r decoding.";
        String expected = "CRLF in an encoded text should be skipped in the decoding.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(expected, qpcodec.decode(qpdata));

        String encoded = qpcodec.encode(expected);
        assertEquals(expected, qpcodec.decode(encoded));
    }
    @Test
    public void test39() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        try {
            qpcodec.decode("=");
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
        try {
            qpcodec.decode("=A");
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
        try {
            qpcodec.decode("=WW");
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
    }
    @Test
    public void test40() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "!#$&'()+;<>?@[\\\\]_`{|}~";
        String encoded = qpcodec.encode(plain);
        assertEquals("Special characters quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Special characters quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test41() throws Exception {
        String qpdata = "If you believe that truth=3Dbeauty, then surely mathematics is the most " +
                "beautiful branch of philosophy.";
        String expected = "If you believe that truth=beauty, then surely mathematics is the most " +
                "beautiful branch of philosophy.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(qpdata, qpcodec.encode(expected));

        String decoded = qpcodec.decode(qpdata);
        assertEquals(qpdata, qpcodec.encode(decoded));
    }
    @Test
    public void test42() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertNull("Null string quoted-printable encoding test", qpcodec.encode((String) null));
        assertNull("Null string quoted-printable decoding test", qpcodec.decode((String) null));
    }
    @Test
    public void test43() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there =\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", "=3D Hello there =3D=0D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test", plain, qpcodec.decode(encoded));
    }
    @Test
    public void test44() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", plain, qpcodec.decode(encoded));
    }
    @Test
    public void test45() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain = "This is a example of a quoted-printable text file. There is no end to it\t";
        String expected = "This is a example of a quoted-printable text file. There is no end to i=\r\nt=09";

        assertEquals(expected, qpcodec.encode(plain));

        plain = "This is a example of a quoted-printable text file. There is no end to it ";
        expected = "This is a example of a quoted-printable text file. There is no end to i=\r\nt=20";

        assertEquals(expected, qpcodec.encode(plain));

        // whitespace before soft break
        plain = "This is a example of a quoted-printable text file. There is no end to   ";
        expected = "This is a example of a quoted-printable text file. There is no end to=20=\r\n =20";

        assertEquals(expected, qpcodec.encode(plain));

        // non-printable character before soft break
        plain = "This is a example of a quoted-printable text file. There is no end to=  ";
        expected = "This is a example of a quoted-printable text file. There is no end to=3D=\r\n =20";

        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test46() {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("NONSENSE");
        String plain = "Hello there!";
        try {
            qpcodec.encode(plain);
            fail("We set the encoding to a bogus NONSENSE vlaue, this shouldn't have worked.");
        } catch (EncoderException ee) {
            // Exception expected, test segment passes.
        }
        try {
            qpcodec.decode(plain);
            fail("We set the encoding to a bogus NONSENSE vlaue, this shouldn't have worked.");
        } catch (DecoderException ee) {
            // Exception expected, test segment passes.
        }
    }
    @Test
    public void test47() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "1+1 = 2";
        String encoded = (String) qpcodec.encode((Object) plain);
        assertEquals("Basic quoted-printable encoding test", "1+1 =3D 2", encoded);

        byte[] plainBA = plain.getBytes("UTF-8");
        byte[] encodedBA = (byte[]) qpcodec.encode((Object) plainBA);
        encoded = new String(encodedBA);
        assertEquals("Basic quoted-printable encoding test", "1+1 =3D 2", encoded);

        Object result = qpcodec.encode((Object) null);
        assertEquals("Encoding a null Object should return null", null, result);

        try {
            Object dObj = new Double(3.0);
            qpcodec.encode(dObj);
            fail("Trying to url encode a Double object should cause an exception.");
        } catch (EncoderException ee) {
            // Exception expected, test segment passes.
        }
    }
    @Test
    public void test48() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        byte[] plain = null;
        byte[] encoded = qpcodec.encode(plain);
        assertEquals("Encoding a null string should return null", null, encoded);
    }
    @Test
    public void test49() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain = "This is a example of a quoted-printable text file. This might contain sp=cial chars.";
        String expected = "This is a example of a quoted-printable text file. This might contain sp=3D=\r\ncial chars.";
        assertEquals(expected, qpcodec.encode(plain));

        plain = "This is a example of a quoted-printable text file. This might contain ta\tbs as well.";
        expected = "This is a example of a quoted-printable text file. This might contain ta=09=\r\nbs as well.";
        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test50() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", "=3D=0D=0A", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", plain, qpcodec.decode(encoded));
    }
    @Test
    public void test51() throws Exception {

        String ru_msg = constructString(RUSSIAN_STUFF_UNICODE);
        String ch_msg = constructString(SWISS_GERMAN_STUFF_UNICODE);

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        assertEquals(
                "=D0=92=D1=81=D0=B5=D0=BC_=D0=BF=D1=80=D0=B8=D0=B2=D0=B5=D1=82",
                qpcodec.encode(ru_msg, CharEncoding.UTF_8)
        );
        assertEquals("Gr=C3=BCezi_z=C3=A4m=C3=A4", qpcodec.encode(ch_msg, CharEncoding.UTF_8));

        assertEquals(ru_msg, qpcodec.decode(qpcodec.encode(ru_msg, CharEncoding.UTF_8), CharEncoding.UTF_8));
        assertEquals(ch_msg, qpcodec.decode(qpcodec.encode(ch_msg, CharEncoding.UTF_8), CharEncoding.UTF_8));
    }
    @Test
    public void test52() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String test = null;
        String result = qpcodec.encode(test, "charset");
        assertEquals("Result should be null", null, result);
    }
    @Test
    public void test53() throws Exception {
        // whitespace, but does not need to be encoded
        String plain = "This is a example of a quoted=printable text file. There is no tt";
        String expected = "This is a example of a quoted=3Dprintable text file. There is no tt";

        assertEquals(expected, new QuotedPrintableCodec().encode(plain));
    }
    @Test
    public void test54() throws Exception {
        String plain = "Hello there!";
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("UnicodeBig");
        qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
        String encoded1 = qpcodec.encode(plain, "UnicodeBig");
        String encoded2 = qpcodec.encode(plain);
        assertEquals(encoded1, encoded2);
    }
@Test
public void test55() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    assertEquals("Null string quoted-printable encoding test", 
        "", qpcodec.encode(""));
    assertEquals("Null string quoted-printable decoding test", 
        "", qpcodec.decode(""));

    assertEquals("Empty string quoted-printable encoding test", 
        "", qpcodec.encode(""));
    assertEquals("Empty string quoted-printable decoding test", 
        "", qpcodec.decode(""));
}
@Test
public void test56() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "Hello! How are you?";
    String encoded = qpcodec.encode(plain);
    assertEquals("Basic quoted-printable encoding test with special chars", 
        plain, encoded);
    assertEquals("Basic quoted-printable decoding test with special chars", 
        plain, qpcodec.decode(encoded));

    plain = "1+1 = 2!";
    encoded = qpcodec.encode(plain);
    assertEquals("Basic quoted-printable encoding test with special chars", 
        plain, encoded);
    assertEquals("Basic quoted-printable decoding test with special chars", 
        plain, qpcodec.decode(encoded));
}
@Test
public void test57() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "Hello there!";
    String decoded = (String) qpcodec.decode((Object) plain);
    assertEquals("Basic quoted-printable decoding test", 
        plain, decoded);

    byte[] plainBA = plain.getBytes("UTF-8");
    byte[] decodedBA = (byte[]) qpcodec.decode((Object) plainBA);
    decoded = new String(decodedBA);
    assertEquals("Basic quoted-printable decoding test", 
        plain, decoded);
        
    Object result = qpcodec.decode((Object) null);
    assertEquals( "Decoding a null Object should return null", null, result);
    
    try {
        Object dObj = new Double(3.0);
        qpcodec.decode( dObj );
        fail( "Trying to url encode a Double object should cause an exception.");
    } catch (DecoderException ee) {
        // Exception expected, test segment passes.
    }
}
@Test
public void test58() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
    String encoded = qpcodec.encode(plain);
    assertEquals("Safe chars quoted-printable encoding test with special chars", 
        plain, encoded);
    assertEquals("Safe chars quoted-printable decoding test with special chars", 
        plain, qpcodec.decode(encoded));

    plain = "abc.123+!@";
    encoded = qpcodec.encode(plain);
    assertEquals("Safe chars quoted-printable encoding test with special chars", 
        plain, encoded);
    assertEquals("Safe chars quoted-printable decoding test with special chars", 
        plain, qpcodec.decode(encoded));
}
@Test
public void test59() {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("NONSENSE");
    String plain = "Hello there!";
    try {
       qpcodec.encode(plain);
        fail( "We set the encoding to a bogus NONSENSE value, this shouldn't have worked.");
    } catch (EncoderException ee) {
        // Exception expected, test segment passes.
    }
    try {
       qpcodec.decode(plain);
        fail( "We set the encoding to a bogus NONSENSE value, this shouldn't have worked.");
    } catch (DecoderException ee) {
        // Exception expected, test segment passes.
    }

    QuotedPrintableCodec qpcodec2 = new QuotedPrintableCodec(null);
    try {
        qpcodec2.encode(plain);
        fail( "We set the encoding to null, this shouldn't have worked.");
    } catch (EncoderException ee) {
        // Exception expected, test segment passes.
    }
    try {
        qpcodec2.decode(plain);
        fail( "We set the encoding to null, this shouldn't have worked.");
    } catch (DecoderException ee) {
        // Exception expected, test segment passes.
    }
}
@Test
public void test60() throws Exception {
    String qpdata = "If you believe that truth=3Dbeauty, then surely=20=\r\nmathematics " +
            "is the most beautiful branch of philosophy.";
    String expected = "If you believe that truth=beauty, then surely mathematics " +
            "is the most beautiful branch of philosophy.";

    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    assertEquals("Soft line break quoted-printable decoding test with special chars", 
        expected, qpcodec.decode(qpdata));

    String encoded = qpcodec.encode(expected);
    assertEquals("Soft line break quoted-printable decoding test with special chars", 
        expected, qpcodec.decode(encoded));
}
@Test
public void test61() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "=*\r\n";
    String encoded = qpcodec.encode(plain);
    assertEquals("Unsafe chars quoted-printable encoding test with special chars", 
        "=3D=2A=0D=0A", encoded);
    assertEquals("Unsafe chars quoted-printable decoding test with special chars", 
        plain, qpcodec.decode(encoded));

    plain = "abc@xyz";
    encoded = qpcodec.encode(plain);
    assertEquals("Unsafe chars quoted-printable encoding test with special chars", 
        plain, encoded);
    assertEquals("Unsafe chars quoted-printable decoding test with special chars", 
        plain, qpcodec.decode(encoded));
}
@Test
public void test62() throws Exception {
    String ru_msg = constructString(RUSSIAN_STUFF_UNICODE) + "!";
    String ch_msg = constructString(SWISS_GERMAN_STUFF_UNICODE) + "!";

    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

    assertEquals(
        "=D0=92=D1=81=D0=B5=D0=BC_=D0=BF=D1=80=D0=B8=D0=B2=D0=B5=D1=82!", 
    qpcodec.encode(ru_msg, CharEncoding.UTF_8)
    );
    assertEquals("Gr=C3=BCezi_z=C3=A4m=C3=A4!", qpcodec.encode(ch_msg, CharEncoding.UTF_8));

    assertEquals(ru_msg, qpcodec.decode(qpcodec.encode(ru_msg, CharEncoding.UTF_8), CharEncoding.UTF_8));
    assertEquals(ch_msg, qpcodec.decode(qpcodec.encode(ch_msg, CharEncoding.UTF_8), CharEncoding.UTF_8));
}
@Test
public void test63() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "1+1) = 2";
    String encoded = new String(QuotedPrintableCodec.
        encodeQuotedPrintable(null, plain.getBytes("UTF-8")));
    assertEquals("Basic quoted-printable encoding test with special chars", 
        "1+1) =3D 2", encoded);
    assertEquals("Basic quoted-printable decoding test with special chars", 
        plain, qpcodec.decode(encoded));

}
@Test
public void test64() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String test = null;
    String result = qpcodec.decode( test, "charset" );
    assertEquals("Result should be null", null, result);
}
@Test
public void test65() throws Exception {
    String qpdata = "Coding for %20 is skipped so as for %5a!";
    String expected = "Coding for  is skipped so as for Z!";

    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    assertEquals("Skip URL hex code point quoted-printable decoding test with special chars", 
        expected, qpcodec.decode(qpdata));

    String encoded = qpcodec.encode(expected);
    assertEquals("Skip URL hex code point quoted-printable decoding test with special chars", 
        expected, qpcodec.decode(encoded));
}
@Test
public void test66() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    try {
        qpcodec.decode("=");
        fail("DecoderException should have been thrown");
    } catch (DecoderException e) {
        // Expected. Move on
    }
    try {
        qpcodec.decode("=A");
        fail("DecoderException should have been thrown");
    } catch (DecoderException e) {
        // Expected. Move on
    }
    try {
        qpcodec.decode("=WW");
        fail("DecoderException should have been thrown");
    } catch (DecoderException e) {
        // Expected. Move on
    }
}
    @Test
    public void test67() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "";
        String encoded = qpcodec.encode(plain);
        assertEquals("Empty string quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Empty string quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test68() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "!@#$%^&*()";
        String encoded = qpcodec.encode(plain);
        assertEquals("Special characters quoted-printable encoding test", 
            "=21=40=23=24=25=5E=26=2A=28=29", encoded);
        assertEquals("Special characters quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test69() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "This is a long string that contains a lot of text";
        String encoded = qpcodec.encode(plain);
        assertEquals("Long string quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Long string quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test70() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "你好";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unicode string quoted-printable encoding test", 
            "=E4=BD=A0=E5=A5=BD", encoded);
        assertEquals("Unicode string quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test71() throws Exception {
        String qpdata = "If you believe that truth=3Dbeauty, then surely mathematics is the most " +
                "beautiful branch of philosophy.";
        String expected = "If you believe that truth=beauty, then surely mathematics is the most " +
                "beautiful branch of philosophy.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(qpdata, qpcodec.encode(expected));
        
        // Mutant: Change the input value to an empty string
        String decoded = "";
        assertEquals(qpdata, qpcodec.encode(decoded));
    }
    @Test
    public void test72() throws Exception {
        String qpdata = "If you believe that truth=3Dbeauty, then surely mathematics is the most " +
                "beautiful branch of philosophy.";
        String expected = "If you believe that truth=beauty, then surely mathematics is the most " +
                "beautiful branch of philosophy.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(qpdata, qpcodec.encode(expected));
        
        // Mutant: Change the input value to a null object
        String decoded = null;
        assertEquals(qpdata, qpcodec.encode(decoded));
    }
    @Test
    public void test73() throws Exception {
        String qpdata = "If you believe that truth=3Dbeauty, then surely mathematics is the most " +
                "beautiful branch of philosophy.";
        String expected = "If you believe that truth=beauty, then surely mathematics is the most " +
                "beautiful branch of philosophy.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(qpdata, qpcodec.encode(expected));
        
        // Mutant: Change the input value to an object of type Integer
        Integer decoded = 123;
        try {
            qpcodec.encode(decoded);
            fail("Exception should be thrown for encoding an object of type Integer");
        } catch (EncoderException e) {
            // Exception expected, test segment passes.
        }       
    }
    @Test
    public void test74() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="This is a example of a quoted-printable text file. This might contain sp=cial chars.";
        String expected = "This is a example of a quoted-printable text file. This might contain sp=3D=\r\ncial chars.";
        assertEquals(expected, qpcodec.encode(plain));
        
        // Mutant: Change the input value to a string without special characters
        plain ="This is a example of a quoted-printable text file. This might contain special chars";
        assertEquals(plain, qpcodec.encode(plain));
    }
    @Test
    public void test75() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=3D=0D=0A", encoded);
        
        // Mutant: Change the input value to an empty string
        String decoded = "";
        assertEquals(plain, qpcodec.decode(decoded));        
    }
    @Test
    public void test76() throws Exception {
        // whitespace, but does not need to be encoded
        String plain ="This is a example of a quoted=printable text file. There is no tt";
        String expected = "This is a example of a quoted=3Dprintable text file. There is no tt";
        
        assertEquals(expected, new QuotedPrintableCodec().encode(plain));
        
        // Mutant: Change the input value to a string without the trailing whitespace
        plain ="This is a example of a quoted=printable text file. There is no";
        assertEquals(plain, new QuotedPrintableCodec().encode(plain));
    }
    @Test
    public void test77() throws Exception {
        // Changed input: encode a non-null String
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertNull("Null string quoted-printable encoding test", 
            qpcodec.encode((String)null));
        assertNull("Null string quoted-printable decoding test", 
            qpcodec.decode((String)null));
    }
    @Test
    public void test78() throws Exception {
        // Changed input: encode and decode a different plain text
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there =\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Hello there =3D=0D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test79() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        // Changed input: decode a different plain text
        String plain = "1+1 =3D 2";
        String decoded = (String) qpcodec.decode((Object) plain);
        assertEquals("Basic quoted-printable decoding test", 
            "1+1 = 2", decoded);

        byte[] plainBA = plain.getBytes("UTF-8");
        byte[] decodedBA = (byte[]) qpcodec.decode((Object) plainBA);
        decoded = new String(decodedBA);
        assertEquals("Basic quoted-printable decoding test", 
            "1+1 = 2", decoded);
            
        Object result = qpcodec.decode((Object) null);
        assertEquals( "Decoding a null Object should return null", null, result);
        
        // Changed input: decode a different object type
        try {
            Object dObj = new Double(3.0);
            qpcodec.decode( dObj );
            fail( "Trying to url encode a Double object should cause an exception.");
        } catch (DecoderException ee) {
            // Exception expected, test segment passes.
        }
    }
    @Test
    public void test80() throws Exception {
        // Changed input: encode and decode a different plain text with safe chars
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test81() {
        // Changed input: set a valid encoding value and encode/decode a plain text
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("UTF-8");
           String plain = "Hello there!";
            try {
               qpcodec.encode(plain);
                fail( "Setting a valid encoding value should not have thrown an exception.");
            } catch (EncoderException ee) {
                // Exception not expected, test segment fails.
            }
            try {
               qpcodec.decode(plain);
                fail( "Setting a valid encoding value should not have thrown an exception.");
            } catch (DecoderException ee) {
                // Exception not expected, test segment fails.
            }
    }
    @Test
    public void test82() throws Exception {
        // Changed input: decode a different qpdata with soft line breaks
        String qpdata = "If you believe that truth=3Dbeauty, then surely=20=\r\nmathematics " +
                "is the most beautiful branch of philosophy.";
        String expected = "If you believe that truth=beauty, then surely mathematics " +
                "is the most beautiful branch of philosophy.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(expected, qpcodec.decode(qpdata));

        String encoded = qpcodec.encode(expected);
        assertEquals(expected, qpcodec.decode(encoded));
    }
    @Test
    public void test83() throws Exception {
        // Changed input: encode and decode a different plain text with unsafe chars
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=3D=0D=0A", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test84() throws Exception {
        // Changed input: encode and decode a different plain text with UTF-8 encoding
        String ru_msg = constructString(ANOTHER_RUSSIAN_STUFF_UNICODE); 
        String ch_msg = constructString(ANOTHER_SWISS_GERMAN_STUFF_UNICODE); 
        
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        
        assertEquals(
            "=D0=92=D1=81=D0=B5=D0=BC_=D0=BF=D1=80=D0=B8=D0=B2=D0=B5=D1=82", 
        qpcodec.encode(ru_msg, CharEncoding.UTF_8)
        );
        assertEquals("Gr=C3=BCezi_z=C3=A4m=C3=A4", qpcodec.encode(ch_msg, CharEncoding.UTF_8));
        
        assertEquals(ru_msg, qpcodec.decode(qpcodec.encode(ru_msg, CharEncoding.UTF_8), CharEncoding.UTF_8));
        assertEquals(ch_msg, qpcodec.decode(qpcodec.encode(ch_msg, CharEncoding.UTF_8), CharEncoding.UTF_8));
    }
    @Test
    public void test85() throws Exception {
        // Changed input: encode a different plain text
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "1+1 = 2";
        String encoded = new String(QuotedPrintableCodec.
            encodeQuotedPrintable(null, plain.getBytes("UTF-8")));
        assertEquals("Basic quoted-printable encoding test", 
            "1+1 =3D 2", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        
    }
    @Test
    public void test86() throws Exception {
        // Changed input: decode a different string with null value
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        // Changed input: set a different test value
        String test = "test";
        String result = qpcodec.decode( test, "charset" );
        assertEquals("Result should be null", null, result);
    }
    @Test
    public void test87() throws Exception {
        // Changed input: decode a different qpdata with CRLF
        String qpdata = "CRLF in an\n encoded text should be=20=\r\n\rskipped in the\r decoding.";
        String expected = "CRLF in an encoded text should be skipped in the decoding.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(expected, qpcodec.decode(qpdata));

        String encoded = qpcodec.encode(expected);
        assertEquals(expected, qpcodec.decode(encoded));
    }
    @Test
    public void test88() throws Exception {
        // Changed input: decode a different invalid String
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        try {
            qpcodec.decode("=");
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
        try {
            qpcodec.decode("=A");
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
        try {
            qpcodec.decode("=WW");
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
    }
    @Test
    public void test89() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "";
        String encoded = qpcodec.encode(plain);
        assertEquals("Empty string quoted-printable encoding test", 
            "", encoded);
        assertEquals("Empty string quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test90() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abcdefghijklmnopqrstuvwxyz0123456789";
        String encoded = qpcodec.encode(plain);
        assertEquals("Long string quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Long string quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test91() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "This is a <special> \"string\" with !@#$%^&*()<>/?";
        String encoded = qpcodec.encode(plain);
        assertEquals("Special characters quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Special characters quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test92() throws Exception {
        String qpdata = "";
        String expected = "";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(qpdata, qpcodec.encode(expected));

        String decoded = qpcodec.decode(qpdata);
        assertEquals(qpdata, qpcodec.encode(decoded));
    }
    @Test
    public void test93() throws Exception {
        String qpdata = "Hello $4^*/=";
        String expected = "Hello $4^*/=";
        
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(qpdata, qpcodec.encode(expected));
        
        String decoded = qpcodec.decode(qpdata);
        assertEquals(qpdata, qpcodec.encode(decoded));
    }
    @Test
    public void test94() throws Exception {
        String qpdata = "Hello   there";
        String expected = "Hello=20=20=20there";
        
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(qpdata, qpcodec.encode(expected));
        
        String decoded = qpcodec.decode(qpdata);
        assertEquals(qpdata, qpcodec.encode(decoded));
    }
    @Test
    public void test95() throws Exception {
        String qpdata = "Hello \tthere";
        String expected = "Hello=09there";
        
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(qpdata, qpcodec.encode(expected));
        
        String decoded = qpcodec.decode(qpdata);
        assertEquals(qpdata, qpcodec.encode(decoded));
    }
    @Test
    public void test96() throws Exception {
        String qpdata = "Привет мир";
        String expected = "=D0=9F=D1=80=D0=B8=D0=B2=D0=B5=D1=82 =D0=BC=D0=B8=D1=80";
        
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(qpdata, qpcodec.encode(expected, "UTF-8"));
        
        String decoded = qpcodec.decode(qpdata);
        assertEquals(qpdata, qpcodec.encode(decoded, "UTF-8"));
    }
}