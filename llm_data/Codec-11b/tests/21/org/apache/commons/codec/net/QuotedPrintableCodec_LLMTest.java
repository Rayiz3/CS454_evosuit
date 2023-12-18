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
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        // Change plain to a different value
        String plain = "test123";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test1() throws Exception {
        // whitespace, but does not need to be encoded
        String plain ="This is a example of a quoted=printable text file. There is no tt";
        // Change plain to a different value
        String plain ="This is a test.";
        String expected = "This is a example of a quoted=3Dprintable text file. There is no tt";
        
        // Change expected to a different value
        String expected = "This symbol is not encoded.";

        assertEquals(expected, new QuotedPrintableCodec().encode(plain));
    }
    @Test
    public void test2() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="This is a example of a quoted-printable text file. This might contain sp=cial chars.";
        // Change plain to a different value
        String plain ="This is a test.";
        String expected = "This is a example of a quoted-printable text file. This might contain sp=3D=\r\ncial chars.";
        
        // Change expected to a different value
        String expected = "This symbol is not encoded.";

        assertEquals(expected, qpcodec.encode(plain));

        plain ="This is a example of a quoted-printable text file. This might contain ta\tbs as well.";
        // Change plain to a different value
        String plain ="This is a test.";
        expected = "This is a example of a quoted-printable text file. This might contain ta=09=\r\nbs as well.";
        
        // Change expected to a different value
        String expected = "This symbol is not encoded.";

        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test3() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        byte[] plain = null;
        // Change plain to a different value
        byte[] plain = "test".getBytes("UTF-8");
        byte[] encoded = qpcodec.encode(plain);
        assertEquals("Encoding a null string should return null", 
            null, encoded);
    }
    @Test
    public void test4() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="This is a example of a quoted-printable text file. There is no end to it\t";
        // Change plain to a different value
        String plain ="This is a test.";
        String expected = "This is a example of a quoted-printable text file. There is no end to i=\r\nt=09";
        
        // Change expected to a different value
        String expected = "This symbol is not encoded.";

        assertEquals(expected, qpcodec.encode(plain));

        plain ="This is a example of a quoted-printable text file. There is no end to it ";
        // Change plain to a different value
        String plain ="This is a test.";
        expected = "This is a example of a quoted-printable text file. There is no end to i=\r\nt=20";
        
        // Change expected to a different value
        String expected = "This symbol is not encoded.";

        assertEquals(expected, qpcodec.encode(plain));

        // whitespace before soft break
        plain ="This is a example of a quoted-printable text file. There is no end to   ";
        // Change plain to a different value
        String plain ="This is a test.";
        expected = "This is a example of a quoted-printable text file. There is no end to=20=\r\n =20";
        
        // Change expected to a different value
        String expected = "This symbol is not encoded.";

        assertEquals(expected, qpcodec.encode(plain));

        // non-printable character before soft break
        plain ="This is a example of a quoted-printable text file. There is no end to=  ";
        // Change plain to a different value
        String plain ="This is a test.";
        expected = "This is a example of a quoted-printable text file. There is no end to=3D=\r\n =20";
        
        // Change expected to a different value
        String expected = "This symbol is not encoded.";

        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test5() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "1+1 = 2";
        // Change plain to a different value
        String plain = "test123";
        String encoded = (String) qpcodec.encode((Object) plain);
        assertEquals("Basic quoted-printable encoding test", 
            "1+1 =3D 2", encoded);

        byte[] plainBA = plain.getBytes("UTF-8");
        byte[] encodedBA = (byte[]) qpcodec.encode((Object) plainBA);
        encoded = new String(encodedBA);
        assertEquals("Basic quoted-printable encoding test", 
            "1+1 =3D 2", encoded);
            
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
    public void test6() throws Exception {
        String plain = "Hello there!";
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("UnicodeBig");
        qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
        String encoded1 = qpcodec.encode(plain, "UnicodeBig");
        String encoded2 = qpcodec.encode(plain);
        assertEquals(encoded1, encoded2);
    }
    @Test
    public void test7() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "1+1 = 2";
        // Change plain to a different value
        String plain = "test123";
        String encoded = new String(QuotedPrintableCodec.
            encodeQuotedPrintable(null, plain.getBytes("UTF-8")));
        assertEquals("Basic quoted-printable encoding test", 
            "1+1 =3D 2", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        
    }
    @Test
    public void test8() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=\r\n";
        // Change plain to a different value
        String plain = "=test123";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=3D=0D=0A", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test9() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there =\r\n";
        // Change plain to a different value
        String plain = "= test =\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Hello there =3D=0D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test10() throws Exception {

        String ru_msg = constructString(RUSSIAN_STUFF_UNICODE); 
        String ch_msg = constructString(SWISS_GERMAN_STUFF_UNICODE); 
        
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        
        assertEquals(
            "=D0=92=D1=81=D0=B5=D0=BC_=D0=BF=D1=80=D0=B8=D0=B2=D0=B5=D1=82", 
        qpcodec.encode(ru_msg, CharEncoding.UTF_8)
        );
        // Change ru_msg to a different value
        String ru_msg = "This is a test.";
        assertEquals("Gr=C3=BCezi_z=C3=A4m=C3=A4", qpcodec.encode(ch_msg, CharEncoding.UTF_8));
        
        assertEquals(ru_msg, qpcodec.decode(qpcodec.encode(ru_msg, CharEncoding.UTF_8), CharEncoding.UTF_8));
        assertEquals(ch_msg, qpcodec.decode(qpcodec.encode(ch_msg, CharEncoding.UTF_8), CharEncoding.UTF_8));
    }
    @Test
    public void test11() throws Exception {
        String qpdata = "If you believe that truth=3Dbeauty, then surely mathematics is the most " +
                "b=\r\neautiful branch of philosophy.";
        // Change qpdata to a different value
        String qpdata = "This is a test.";
        String expected = "If you believe that truth=beauty, then surely mathematics is the most " +
                "beautiful branch of philosophy.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(qpdata, qpcodec.encode(expected));

        String decoded = qpcodec.decode(qpdata);
        assertEquals(qpdata, qpcodec.encode(decoded));
    }
    @Test
    public void test12() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));

        // Regression Test Case 1: plain contains special characters
        plain = "abc123_-.ěščřžýáíé!@#$%^&()+{}\"\\;:`,/[]";
        encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test (regression)", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test (regression)", 
            plain, qpcodec.decode(encoded));

        // Regression Test Case 2: plain is empty string
        plain = "";
        encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test (regression)", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test (regression)", 
            plain, qpcodec.decode(encoded));

        // Regression Test Case 3: plain only contains spaces
        plain = "    ";
        encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test (regression)", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test (regression)", 
            plain, qpcodec.decode(encoded));

        // Regression Test Case 4: plain is a long string
        plain = "ThisIsALongStringThisIsALongStringThisIsALongStringThisIsALongStringThisIsALongString";
        encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test (regression)", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test (regression)", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test13() throws Exception {
        // Regression Test Case 1: plain contains '=' character
        String plain ="This is a example of a quoted=printable text file. There is no tt=";
        String expected = "This is a example of a quoted=3Dprintable text file. There is no tt=3D";

        assertEquals(expected, new QuotedPrintableCodec().encode(plain));

        // Regression Test Case 2: plain is empty string
        plain = "";
        expected = "";

        assertEquals(expected, new QuotedPrintableCodec().encode(plain));
    }
    @Test
    public void test14() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        // Regression Test Case 1: plain contains '=' character
        String plain ="This is a example of a quoted-printable text file. This might contain sp=cial chars=";
        String expected = "This is a example of a quoted-printable text file. This might contain sp=3Dcial chars=3D";
        assertEquals(expected, qpcodec.encode(plain));

        // Regression Test Case 2: plain contains tab character
        plain ="This is a example of a quoted-printable text file. This might contain ta\tbs as well=";
        expected = "This is a example of a quoted-printable text file. This might contain ta=09=3D=\r\nbs as well=3D";
        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test15() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        byte[] plain = null;
        byte[] encoded = qpcodec.encode(plain);
        assertEquals("Encoding a null string should return null", 
            null, encoded);

        // Regression Test Case 1: plain is an empty byte array
        plain = new byte[0];
        encoded = qpcodec.encode(plain);
        assertEquals("Encoding an empty byte array should return an empty byte array", 
            plain, encoded);

        // Regression Test Case 2: plain contains some values
        plain = new byte[] {1, 2, 3, 4, 5};
        encoded = qpcodec.encode(plain);
        byte[] expected = new byte[] {1, 2, 3, 4, 5};
        assertArrayEquals("Encoding a byte array should return the same byte array", 
            expected, encoded);
    }
    @Test
    public void test16() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="This is a example of a quoted-printable text file. There is no end to it\t=";
        String expected = "This is a example of a quoted-printable text file. There is no end to i=\r\n=\t";

        assertEquals(expected, qpcodec.encode(plain));

        plain ="This is a example of a quoted-printable text file. There is no end to it =";
        expected = "This is a example of a quoted-printable text file. There is no end to i=\r\n=\t";

        assertEquals(expected, qpcodec.encode(plain));

        // whitespace before soft break
        plain ="This is a example of a quoted-printable text file. There is no end to   =";
        expected = "This is a example of a quoted-printable text file. There is no end to=20=\r\n=20";

        assertEquals(expected, qpcodec.encode(plain));

        // non-printable character before soft break
        plain ="This is a example of a quoted-printable text file. There is no end to=  =";
        expected = "This is a example of a quoted-printable text file. There is no end to=3D=\r\n=20";

        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test17() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "1+1 = 2";
        String encoded = (String) qpcodec.encode((Object) plain);
        assertEquals("Basic quoted-printable encoding test", 
            "1+1 =3D 2", encoded);

        byte[] plainBA = plain.getBytes("UTF-8");
        byte[] encodedBA = (byte[]) qpcodec.encode((Object) plainBA);
        encoded = new String(encodedBA);
        assertEquals("Basic quoted-printable encoding test", 
            "1+1 =3D 2", encoded);
            
        Object result = qpcodec.encode((Object) null);
        assertEquals( "Encoding a null Object should return null", null, result);
        
        try {
            Object dObj = new Double(3.0);
            qpcodec.encode( dObj );
            fail( "Trying to url encode a Double object should cause an exception.");
        } catch (EncoderException ee) {
            // Exception expected, test segment passes.
        }

        // Regression Test Case 1: plain contains special characters
        plain = "1+1 = 2 has some special characters = $ * äß ?";
        encoded = (String) qpcodec.encode((Object) plain);
        assertEquals("Basic quoted-printable encoding test (regression)", 
            "1+1 =3D 2 has some special characters =3D $ * =C3=A4=C3=9F ?",
            encoded);
            
        plainBA = plain.getBytes("UTF-8");
        encodedBA = (byte[]) qpcodec.encode((Object) plainBA);
        encoded = new String(encodedBA);
        assertEquals("Basic quoted-printable encoding test (regression)", 
            "1+1 =3D 2 has some special characters =3D $ * =C3=A4=C3=9F ?",
            encoded);

        Object dObj = new Double(3.0);
        result = qpcodec.encode( dObj );
        assertNull( "Expected null return when encoding a Double object", result);
    }
    @Test
    public void test18() throws Exception {
        String plain = "Hello there!";
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("UnicodeBig");
        qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
        String encoded1 = qpcodec.encode(plain, "UnicodeBig");
        String encoded2 = qpcodec.encode(plain);
        assertEquals(encoded1, encoded2);
    }
    @Test
    public void test19() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "1+1 = 2";
        String encoded = new String(QuotedPrintableCodec.
            encodeQuotedPrintable(null, plain.getBytes("UTF-8")));
        assertEquals("Basic quoted-printable encoding test", 
            "1+1 =3D 2", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));

        // Regression Test Case 1: plain contains special characters
        plain = "1+1 = 2 has some special characters = $ * äß ?";
        encoded = new String(QuotedPrintableCodec.
            encodeQuotedPrintable(null, plain.getBytes("UTF-8")));
        assertEquals("Basic quoted-printable encoding test (regression)", 
            "1+1 =3D 2 has some special characters =3D $ * =C3=A4=C3=9F ?",
            encoded);
        assertEquals("Basic quoted-printable decoding test (regression)", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test20() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=3D=0D=0A", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));

        // Regression Test Case 1: plain contains special characters
        plain = "=\r\n has some special characters = $ * äß ?";
        encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test (regression)", 
            "=3D=0D=0A has some special characters =3D $ * =C3=A4=C3=9F ?",
            encoded);
        assertEquals("Unsafe chars quoted-printable decoding test (regression)", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test21() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there =\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Hello there =3D=0D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));

        // Regression Test Case 1: plain contains special characters
        plain = "= Hello there has some special characters = $ * äß ?";
        encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test (regression)", 
            "=3D Hello there has some special characters =3D $ * =C3=A4=C3=9F ?",
            encoded);
        assertEquals("Basic quoted-printable decoding test (regression)", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test22() throws Exception {

        String ru_msg = constructString(RUSSIAN_STUFF_UNICODE); 
        String ch_msg = constructString(SWISS_GERMAN_STUFF_UNICODE); 
        
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        
        assertEquals(
            "=D0=92=D1=81=D0=B5=D0=BC_=D0=BF=D1=80=D0=B8=D0=B2=D0=B5=D1=82", 
        qpcodec.encode(ru_msg, CharEncoding.UTF_8)
        );
        assertEquals("Gr=C3=BCezi_z=C3=A4m=C3=A4", qpcodec.encode(ch_msg, CharEncoding.UTF_8));
        
        String decoded = qpcodec.decode(qpcodec.encode(ru_msg, CharEncoding.UTF_8), CharEncoding.UTF_8);
        assertEquals("Decoding the encoded message should return the original message", 
            ru_msg, decoded);
        
        decoded = qpcodec.decode(qpcodec.encode(ch_msg, CharEncoding.UTF_8), CharEncoding.UTF_8);
        assertEquals("Decoding the encoded message should return the original message", 
            ch_msg, decoded);

        // Regression Test Case 1: ru_msg contains special characters
        ru_msg = constructString(SPECIAL_CHARS_UNICODE); 
        assertEquals(
            "=E2=98=BA=E2=98=BA=E2=98=BA=E2=98=BA", 
        qpcodec.encode(ru_msg, CharEncoding.UTF_8)
        );
        decoded = qpcodec.decode(qpcodec.encode(ru_msg, CharEncoding.UTF_8), CharEncoding.UTF_8);
        assertEquals("Decoding the encoded message should return the original message", 
            ru_msg, decoded);
        
        // Regression Test Case 2: ch_msg contains special characters
        ch_msg = constructString(SPECIAL_CHARS_UNICODE); 
        assertEquals("~~~=~~~=~~~=~~~", qpcodec.encode(ch_msg, CharEncoding.UTF_8));
        decoded = qpcodec.decode(qpcodec.encode(ch_msg, CharEncoding.UTF_8), CharEncoding.UTF_8);
        assertEquals("Decoding the encoded message should return the original message", 
            ch_msg, decoded);
    }
    @Test
    public void test23() throws Exception {
        String qpdata = "If you believe that truth=3Dbeauty, then surely mathematics is the most " +
                "b=\r\neautiful branch of philosophy.";
        String expected = "If you believe that truth=beauty, then surely mathematics is the most " +
                "beautiful branch of philosophy.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(qpdata, qpcodec.encode(expected));
        
        String decoded = qpcodec.decode(qpdata);
        assertEquals(qpdata, qpcodec.encode(decoded));

        // Regression Test Case 1: qpdata contains special characters
        qpdata = "If you believe that truth=3Dbeauty, then surely mathematics is the most " +
                "b=\r\ne@utiful branch of philosophy.";
        expected = "If you believe that truth=beauty, then surely mathematics is the most " +
                "b=\r\ne@utiful branch of philosophy.";

        assertEquals(expected, qpcodec.encode(expected));
        
        decoded = qpcodec.decode(qpdata);
        assertEquals(qpdata, qpcodec.encode(decoded));
    }
    @Test
    public void test24() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test25() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=3D=0D=0A", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test26() throws Exception {
        String qpdata = "CRLF in an\n encoded text should be=20=\r\n\rskipped in the\r decoding.";
        String expected = "CRLF in an encoded text should be skipped in the decoding.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(expected, qpcodec.decode(qpdata));

        String encoded = qpcodec.encode(expected);
        assertEquals(expected, qpcodec.decode(encoded));
    }
    @Test
    public void test27() throws Exception {
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
    public void test28() throws Exception {
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
    public void test29() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there =\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Hello there =3D=0D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
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
    public void test32() throws Exception {
        byte[] plain = new byte[0];
        byte[] result = QuotedPrintableCodec.decodeQuotedPrintable( plain );
        assertEquals("Result should be an empty byte array", 0, result.length);
    }
    @Test
    public void test33() throws Exception {
        byte[] plain = { 65 };
        byte[] result = QuotedPrintableCodec.decodeQuotedPrintable( plain );
        assertEquals("Result should be an array with one element", 1, result.length);
    }
    @Test
    public void test34() throws Exception {
        byte[] plain = { 65, 61 };
        byte[] result = QuotedPrintableCodec.decodeQuotedPrintable( plain );
        assertEquals("Result should be an array with one element", 2, result.length);
        assertEquals("First element should be the same as the input", 65, result[0]);
        assertEquals("Second element should be the same as the input", 61, result[1]);
    }
    @Test
    public void test35() throws Exception {
        byte[] plain = { 65, 61, 32 };
        byte[] result = QuotedPrintableCodec.decodeQuotedPrintable( plain );
        assertEquals("Result should be an array with two elements", 2, result.length);
        assertEquals("First element should be the same as the input", 65, result[0]);
        assertEquals("Second element should be the same as the input", 61, result[1]);
    }
    @Test
    public void test36() throws Exception {
        byte[] plain = { 65, 61, 49 };
        byte[] result = QuotedPrintableCodec.decodeQuotedPrintable( plain );
        assertEquals("Result should be an array with one element", 1, result.length);
        assertEquals("Element should be the converted char", 49, result[0]);
    }
    @Test
    public void test37() throws Exception {
        byte[] plain = { 65, 61, 48, 49 };
        byte[] result = QuotedPrintableCodec.decodeQuotedPrintable( plain );
        assertEquals("Result should be an array with three elements", 1, result.length);
        assertEquals("First element should be the same as the input", 65, result[0]);
        assertEquals("Second element should be the same as the input", 61, result[1]);
        assertEquals("Third element should be the same as the input", 48, result[2]);
        assertEquals("Fourth element should be the same as the input", 49, result[3]);
    }
    @Test
    public void test38() throws Exception {
        byte[] plain = { 65, 61, 49, 50 };
        byte[] result = QuotedPrintableCodec.decodeQuotedPrintable( plain );
        assertEquals("Result should be an array with two elements", 2, result.length);
        assertEquals("First element should be the converted char", 49, result[0]);
        assertEquals("Second element should be the converted char", 50, result[1]);
    }
    @Test
    public void test39() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "";
        String encoded = qpcodec.encode(plain);
        assertEquals("Empty string quoted-printable encoding test", 
            plain, encoded);
    }
    @Test
    public void test40() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "This string contains special characters: 123!@#";
        String encoded = qpcodec.encode(plain);
        assertEquals("String with special characters quoted-printable encoding test", 
            plain, encoded);
    }
    @Test
    public void test41() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "This string contains whitespace: hello world";
        String encoded = qpcodec.encode(plain);
        assertEquals("String with whitespace quoted-printable encoding test", 
            plain, encoded);
    }
    @Test
    public void test42() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = generateLongString(10000);
        String encoded = qpcodec.encode(plain);
        assertEquals("Long string quoted-printable encoding test", 
            plain, encoded);
    }
    @Test
    public void test43() {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("INVALID");
        String plain = "Hello there!";
        try {
           qpcodec.encode(plain);
            fail( "We set the encoding to an invalid value, this shouldn't have worked.");
        } catch (EncoderException ee) {
            // Exception expected, test segment passes.
        }
    }
    @Test
    public void test44() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertNull("Null string quoted-printable encoding test", 
            qpcodec.encode((String)null));
        assertNull("Null string quoted-printable decoding test", 
            qpcodec.decode((String)null));
        
        // Regression test case
        assertEquals("Empty string quoted-printable encoding test", "", qpcodec.encode(""));
        assertNull("Empty string quoted-printable decoding test", qpcodec.decode(""));
    }
    @Test
    public void test45() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        
        // Regression test case
        String newPlain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]<>";
        String newEncoded = qpcodec.encode(newPlain);
        assertEquals("Safe chars with special char quoted-printable encoding test", 
            newPlain, newEncoded);
        assertEquals("Safe chars with special char quoted-printable decoding test", 
            newPlain, qpcodec.decode(newEncoded));
    }
    @Test
    public void test46() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=3D=0D=0A", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        
        // Regression test case
        String newPlain = "<>\r\n";
        String newEncoded = qpcodec.encode(newPlain);
        assertEquals("Unsafe chars with special char quoted-printable encoding test", 
            "=3C=3E=3D=0D=0A", newEncoded);
        assertEquals("Unsafe chars with special char quoted-printable decoding test", 
            newPlain, qpcodec.decode(newEncoded));
    }
    @Test
    public void test47() throws Exception {
        String qpdata = "CRLF in an\n encoded text should be=20=\r\n\rskipped in the\r decoding.";
        String expected = "CRLF in an encoded text should be skipped in the decoding.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(expected, qpcodec.decode(qpdata));

        String encoded = qpcodec.encode(expected);
        assertEquals(expected, qpcodec.decode(encoded));
        
        // Regression test case
        String newQpData = qpdata.replace("skipped", "=skipped").replace("text", "<text>");
        String newExpected = expected.replace("decoding", "=decoding");
        
        assertEquals(newExpected, qpcodec.decode(newQpData));
        assertEquals(newExpected, qpcodec.decode(qpcodec.encode(newExpected)));
    }
    @Test
    public void test48() throws Exception {
        String qpdata = "If you believe that truth=3Dbeauty, then surely=20=\r\nmathematics " +
                "is the most beautiful branch of philosophy.";
        String expected = "If you believe that truth=beauty, then surely mathematics " +
                "is the most beautiful branch of philosophy.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(expected, qpcodec.decode(qpdata));

        String encoded = qpcodec.encode(expected);
        assertEquals(expected, qpcodec.decode(encoded));
        
        // Regression test case
        String newQpData = qpdata.replace("If you", "=If you");
        String newExpected = expected.replace("believe", "=believe")
                                    .replace("then", "=then");
        
        assertEquals(newExpected, qpcodec.decode(newQpData));
        assertEquals(newExpected, qpcodec.decode(qpcodec.encode(newExpected)));
    }
    @Test
    public void test49() {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("NONSENSE");
           String plain = "Hello there!";
            try {
               qpcodec.encode(plain);
                fail( "We set the encoding to a bogus NONSENSE vlaue, this shouldn't have worked.");
            } catch (EncoderException ee) {
                // Exception expected, test segment passes.
            }
            try {
               qpcodec.decode(plain);
                fail( "We set the encoding to a bogus NONSENSE vlaue, this shouldn't have worked.");
            } catch (DecoderException ee) {
                // Exception expected, test segment passes.
            }
            
            // Regression test case
            QuotedPrintableCodec newQpcodec = new QuotedPrintableCodec("NON-ASCII");
            try {
               newQpcodec.encode(plain);
                fail( "We set the encoding to a non-standard NON-ASCII encoding, this shouldn't have worked.");
            } catch (EncoderException ee) {
                // Exception expected, test segment passes.
            }
            try {
               newQpcodec.decode(plain);
                fail( "We set the encoding to a non-standard NON-ASCII encoding, this shouldn't have worked.");
            } catch (DecoderException ee) {
                // Exception expected, test segment passes.
            }
    }
    @Test
    public void test50() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "1+1 = 2";
        String encoded = new String(QuotedPrintableCodec.
            encodeQuotedPrintable(null, plain.getBytes("UTF-8")));
        assertEquals("Basic quoted-printable encoding test", 
            "1+1 =3D 2", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        
        // Regression test case
        String newPlain = "4+5 = 9";
        String newEncoded = new String(QuotedPrintableCodec.
            encodeQuotedPrintable(null, newPlain.getBytes("UTF-8")));
        
        assertEquals("Basic quoted-printable encoding test", 
            "4+5 =3D 9", newEncoded);
        assertEquals("Basic quoted-printable decoding test", 
            newPlain, qpcodec.decode(newEncoded));
    }
    @Test
    public void test51() throws Exception {
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
        // Regression test case
        try {
            Object iObj = new Integer(5);
            qpcodec.decode( iObj );
            fail( "Trying to url encode an Integer object should cause an exception.");
        } catch (DecoderException ee) {
            // Exception expected, test segment passes.
        }
    }
    @Test
    public void test52() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there =\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Hello there =3D=0D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        
        // Regression test case
        String newPlain = "=3D Hello there =\r\n";
        String newEncoded = qpcodec.encode(newPlain);
        assertEquals("Basic quoted-printable encoding test with extra escape characters", 
            "=3D=3D Hello there =3D=0D=0A", newEncoded);
        assertEquals("Basic quoted-printable decoding test with extra escape characters", 
            newPlain, qpcodec.decode(newEncoded));
    }
    @Test
    public void test53() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String test = null;
        String result = qpcodec.decode( test, "charset" );
        assertEquals("Result should be null", null, result);
        
        // Regression test case
        String newTest = null;
        String newResult = qpcodec.decode( newTest, "utf-8" );
        assertEquals("Result should be null", null, newResult);
    }
    @Test
    public void test54() throws Exception {

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
        
        // Regression test case
        String newRuMsg = constructString(RUSSIAN_STUFF_UNICODE) + "!";
        String newChMsg = constructString(SWISS_GERMAN_STUFF_UNICODE) + "!";
        
        assertEquals(
            "=D0=92=D1=81=D0=B5=D0=BC_=D0=BF=D1=80=D0=B8=D0=B2=D0=B5=D1=82!",
        qpcodec.encode(newRuMsg, CharEncoding.UTF_8)
        );
        assertEquals("Gr=C3=BCezi_z=C3=A4m=C3=A4!", qpcodec.encode(newChMsg, CharEncoding.UTF_8));
        
        assertEquals(newRuMsg, qpcodec.decode(qpcodec.encode(newRuMsg, CharEncoding.UTF_8), CharEncoding.UTF_8));
        assertEquals(newChMsg, qpcodec.decode(qpcodec.encode(newChMsg, CharEncoding.UTF_8), CharEncoding.UTF_8));
    }
    @Test
    public void test55() throws Exception {
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
        
        // Regression test case
        try {
            qpcodec.decode("123==");
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
        try {
            qpcodec.decode("=123=");
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
        try {
            qpcodec.decode("123===");
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
    }
    @Test
    public void test56() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertNull("Null string quoted-printable encoding test", 
            qpcodec.encode((String)null));
        assertNull("Null string quoted-printable decoding test", 
            qpcodec.decode((String)null));
        
        // Regression test - pString with empty string
        assertEquals("", qpcodec.encode(""));
        assertEquals("", qpcodec.decode(""));
    }
    @Test
    public void test57() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        
        // Regression test - pString with only safe characters and no special characters
        plain = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        encoded = qpcodec.encode(plain);
        assertEquals("Safe chars only quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars only quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test58() throws Exception {
        // whitespace, but does not need to be encoded
        String plain ="This is a example of a quoted=printable text file. There is no tt";
        String expected = "This is a example of a quoted=3Dprintable text file. There is no tt";

        assertEquals(expected, new QuotedPrintableCodec().encode(plain));
        
        // Regression test - pString with multiple occurrences of special characters that need encoding
        plain ="This is a example of a quoted-printable text file. Here are some special characters - =";
        expected = "This is a example of a quoted-printable text file. Here are some special characters =-\r\n=";

        assertEquals(expected, new QuotedPrintableCodec().encode(plain));
    }
    @Test
    public void test59() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="This is a example of a quoted-printable text file. This might contain sp=cial chars.";
        String expected = "This is a example of a quoted-printable text file. This might contain sp=3D=\r\ncial chars.";
        assertEquals(expected, qpcodec.encode(plain));

        plain ="This is a example of a quoted-printable text file. This might contain ta\tbs as well.";
        expected = "This is a example of a quoted-printable text file. This might contain ta=09=\r\nbs as well.";
        assertEquals(expected, qpcodec.encode(plain));
        
        // Regression test - pString with multiple special characters at the end
        plain ="This is a example of a quoted-printable text file. This might contain tabs= ";
        expected = "This is a example of a quoted-printable text file. This might contain tabs=09=20";

        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test60() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        byte[] plain = null;
        byte[] encoded = qpcodec.encode(plain);
        assertEquals("Encoding a null string should return null", 
            null, encoded);
        
        // Regression test - plain with empty byte array
        byte[] emptyByteArray = new byte[0];
        encoded = qpcodec.encode(emptyByteArray);
        assertEquals("Encoding an empty byte array should return an empty byte array", 
            emptyByteArray, encoded);
    }
    @Test
    public void test61() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="This is a example of a quoted-printable text file. There is no end to it\t";
        String expected = "This is a example of a quoted-printable text file. There is no end to i=\r\nt=09";

        assertEquals(expected, qpcodec.encode(plain));

        plain ="This is a example of a quoted-printable text file. There is no end to it ";
        expected = "This is a example of a quoted-printable text file. There is no end to i=\r\nt=20";

        assertEquals(expected, qpcodec.encode(plain));

        // whitespace before soft break
        plain ="This is a example of a quoted-printable text file. There is no end to   ";
        expected = "This is a example of a quoted-printable text file. There is no end to=20=\r\n =20";

        assertEquals(expected, qpcodec.encode(plain));

        // non-printable character before soft break
        plain ="This is a example of a quoted-printable text file. There is no end to=  ";
        expected = "This is a example of a quoted-printable text file. There is no end to=3D=\r\n =20";

        assertEquals(expected, qpcodec.encode(plain));
        
        // Regression test - plain with multiple characters before soft break
        plain ="This is a example of a quoted-printable text file. There is = so=";
        expected = "This is a example of a quoted-printable text file. There is =20=20=\r\n =20";

        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test62() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "1+1 = 2";
        String encoded = (String) qpcodec.encode((Object) plain);
        assertEquals("Basic quoted-printable encoding test", 
            "1+1 =3D 2", encoded);
        
        // Regression test - pString with empty string
        plain = "";
        encoded = (String) qpcodec.encode((Object) plain);
        assertEquals("Encoding an empty string should return an empty string", 
            "", encoded);
        
        byte[] plainBA = plain.getBytes("UTF-8");
        byte[] encodedBA = (byte[]) qpcodec.encode((Object) plainBA);
        encoded = new String(encodedBA);
        assertEquals("Basic quoted-printable encoding test", 
            "1+1 =3D 2", encoded);
            
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
    public void test63() throws Exception {
        String plain = "Hello there!";
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("UnicodeBig");
        qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
        String encoded1 = qpcodec.encode(plain, "UnicodeBig");
        String encoded2 = qpcodec.encode(plain);
        assertEquals(encoded1, encoded2);
        
        // Regression test - pString with empty string
        plain = "";
        encoded1 = qpcodec.encode(plain, "UnicodeBig");
        encoded2 = qpcodec.encode(plain);
        assertEquals("Encoding an empty string with different charsets should return an empty string", 
            "", encoded1);
        assertEquals("Encoding an empty string with different charsets should return an empty string", 
            "", encoded2);
    }
    @Test
    public void test64() {
        QuotedPrintableCodec qpcodec = null;
        try {
            qpcodec = new QuotedPrintableCodec("NONSENSE");
            fail("Creating an instance of QuotedPrintableCodec with an invalid encoding should throw an exception");
        } catch (UnsupportedEncodingException e) {
            // Exception expected, test segment passes.
        }
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
    public void test65() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String test = null;
        String result = qpcodec.encode( test, "charset" );
        assertEquals("Result should be null", null, result);
        
        // Regression test - test with empty string
        test = "";
        result = qpcodec.encode( test, "charset" );
        assertEquals("Encoding an empty string should return an empty string", 
            "", result);
    }
    @Test
    public void test66() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=3D=0D=0A", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        
        // Regression test - plain with multiple occurrences of unsafe characters
        plain = "=0D=0A=\r\n=";
        encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=3D0D=3D0A=3D=0D=0A=3D", encoded);
    }
    @Test
    public void test67() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there =\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Hello there =3D=0D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        
        // Regression test - plain with multiple occurrences of special characters that need encoding
        plain = "= Hello = there =";
        encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Hello =3D there =3D", encoded);
    }
    @Test
    public void test68() throws Exception {

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
        
        // Regression test - pString with empty string
        ru_msg = "";
        ch_msg = "";
        
        assertEquals("", qpcodec.encode(ru_msg, CharEncoding.UTF_8));
        assertEquals("", qpcodec.encode(ch_msg, CharEncoding.UTF_8));
        
        assertEquals(ru_msg, qpcodec.decode("", CharEncoding.UTF_8));
        assertEquals(ch_msg, qpcodec.decode("", CharEncoding.UTF_8));
    }
    @Test
    public void test69() throws Exception {
        String qpdata = "If you believe that truth=3Dbeauty, then surely mathematics is the most " +
                "b=\r\neautiful branch of philosophy.";
        String expected = "If you believe that truth=beauty, then surely mathematics is the most " +
                "beautiful branch of philosophy.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(qpdata, qpcodec.encode(expected));

        String decoded = qpcodec.decode(qpdata);
        assertEquals(qpdata, qpcodec.encode(decoded));
        
        // Regression test - pString with multiple occurrences of soft line breaks
        expected = "If you believe that truth=beauty, beautiful right? Then surely mathematics is the most " +
                "beautiful branch of philosophy.";

        assertEquals(qpdata.replace(" ", "  "), qpcodec.encode(expected));
    }
    @Test
    public void test70() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertNull("Null string quoted-printable encoding test", 
            qpcodec.encode((String)null));
        assertNull("Null string quoted-printable decoding test", 
            qpcodec.decode((String)null));

        // Regression test with different input
        assertNotNull("Empty string quoted-printable encoding test", 
            qpcodec.encode(""));
        assertNotNull("Empty string quoted-printable decoding test", 
            qpcodec.decode(""));
    }
    @Test
    public void test71() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));

        // Regression test with different input
        plain = "abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test72() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=3D=0D=0A", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));

        // Regression test with different input
        plain = "abc\r\ndef";
        encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "abc=0D=0Adef", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test73() throws Exception {
        String qpdata = "CRLF in an\n encoded text should be=20=\r\n\rskipped in the\r decoding.";
        String expected = "CRLF in an encoded text should be skipped in the decoding.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(expected, qpcodec.decode(qpdata));

        String encoded = qpcodec.encode(expected);
        assertEquals(expected, qpcodec.decode(encoded));

        // Regression test with different input
        qpdata = "CRLF in an\r encoded text should be=20=\r\n\rskipped in the\r decoding.";
        expected = "CRLF in an encoded text should be skipped in the decoding.";

        assertEquals(expected, qpcodec.decode(qpdata));

        encoded = qpcodec.encode(expected);
        assertEquals(expected, qpcodec.decode(encoded));
    }
    @Test
    public void test74() throws Exception {
        String qpdata = "If you believe that truth=3Dbeauty, then surely=20=\r\nmathematics " +
                "is the most beautiful branch of philosophy.";
        String expected = "If you believe that truth=beauty, then surely mathematics " +
                "is the most beautiful branch of philosophy.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(expected, qpcodec.decode(qpdata));

        String encoded = qpcodec.encode(expected);
        assertEquals(expected, qpcodec.decode(encoded));

        // Regression test with different input
        qpdata = "If you believe that truth=beauty, then surely=20=\r\nmathematics " +
                "is the most beautiful branch of philosophy.";
        expected = "If you believe that truth=beauty, then surely mathematics " +
                "is the most beautiful branch of philosophy.";

        assertEquals(expected, qpcodec.decode(qpdata));

        encoded = qpcodec.encode(expected);
        assertEquals(expected, qpcodec.decode(encoded));
    }
    @Test
    public void test75() {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("NONSENSE");
           String plain = "Hello there!";
            try {
               qpcodec.encode(plain);
                fail( "We set the encoding to a bogus NONSENSE vlaue, this shouldn't have worked.");
            } catch (EncoderException ee) {
                // Exception expected, test segment passes.
            }
            try {
               qpcodec.decode(plain);
                fail( "We set the encoding to a bogus NONSENSE vlaue, this shouldn't have worked.");
            } catch (DecoderException ee) {
                // Exception expected, test segment passes.
            }

            // Regression test with different input
            qpcodec = new QuotedPrintableCodec("INVALID");
            plain = "Hello there!";
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
    public void test76() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "1+1 = 2";
        String encoded = new String(QuotedPrintableCodec.
            encodeQuotedPrintable(null, plain.getBytes("UTF-8")));
        assertEquals("Basic quoted-printable encoding test", 
            "1+1 =3D 2", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));

        // Regression test with different input
        plain = "Let's go!";
        encoded = new String(QuotedPrintableCodec.
            encodeQuotedPrintable(null, plain.getBytes("UTF-8")));
        assertEquals("Basic quoted-printable encoding test", 
            "Let=27s go!", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        
    }
    @Test
    public void test77() throws Exception {
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

        // Regression test with different input
        plain = "Hello";
        Double doubleObj = new Double(3.0);
        try {
           qpcodec.decode( doubleObj );
            fail( "Trying to url encode a Double object should cause an exception.");
        } catch (DecoderException ee) {
            // Exception expected, test segment passes.
        }
    }
    @Test
    public void test78() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there =\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Hello there =3D=0D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));

        // Regression test with different input
        plain = "Hello\r\nworld";
        encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "Hello=0D=0Aworld", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test79() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String test = null;
        String result = qpcodec.decode( test, "charset" );
        assertEquals("Result should be null", null, result);

        // Regression test with different input
        test = "Hello";
        result = qpcodec.decode( test, "charset" );
        assertEquals("Result should be null", null, result);
    }
    @Test
    public void test80() throws Exception {

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

        // Regression test with different input
        ru_msg = "Привет";
        ch_msg = "Grüezi zäme";

        assertEquals(
            "=D0=9F=D1=80=D0=B8=D0=B2=D0=B5=D1=82", 
        qpcodec.encode(ru_msg, CharEncoding.UTF_8)
        );
        assertEquals("Gr=C3=BCezi_z=C3=A4me", qpcodec.encode(ch_msg, CharEncoding.UTF_8));
        
        assertEquals(ru_msg, qpcodec.decode(qpcodec.encode(ru_msg, CharEncoding.UTF_8), CharEncoding.UTF_8));
        assertEquals(ch_msg, qpcodec.decode(qpcodec.encode(ch_msg, CharEncoding.UTF_8), CharEncoding.UTF_8));
    }
    @Test
    public void test81() throws Exception {
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

        // Regression test with different input
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
            qpcodec.decode("=W");
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
    }
    @Test
    public void test82() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertNull("Null string quoted-printable encoding test", 
            qpcodec.encode((String)null));
        assertNull("Null string quoted-printable decoding test", 
            qpcodec.decode((String)null));
    }
    @Test
    public void test83() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test84() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=3D=0D=0A", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test85() throws Exception {
        String qpdata = "CRLF in an\n encoded text should be=20=\r\n\rskipped in the\r decoding.";
        String expected = "CRLF in an encoded text should be skipped in the decoding.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(expected, qpcodec.decode(qpdata));

        String encoded = qpcodec.encode(expected);
        assertEquals(expected, qpcodec.decode(encoded));
    }
    @Test
    public void test86() throws Exception {
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
    public void test87() {
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
    public void test88() throws Exception {
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
    public void test89() throws Exception {
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
    public void test90() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there =\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Hello there =3D=0D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test91() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String test = null;
        String result = qpcodec.decode( test, "charset" );
        assertEquals("Result should be null", null, result);
    }
    @Test
    public void test92() throws Exception {

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
    public void test93() throws Exception {
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
    public void test94() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals("", qpcodec.decode(qpcodec.encode("")));
    }
    @Test
    public void test95() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String nonAscii = "你好";
        String encoded = qpcodec.encode(nonAscii);
        assertEquals(nonAscii, qpcodec.decode(encoded));
    }
    @Test
    public void test96() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String longString = "1234567890".repeat(100);
        String encoded = qpcodec.encode(longString);
        assertEquals(longString, qpcodec.decode(encoded));
    }
@Test
public void test97() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    byte[] plain = "    ".getBytes();
    byte[] encoded = qpcodec.encode(plain);
    assertEquals("Encoding a whitespace byte array should return the same byte array", 
        new String(plain), new String(encoded));
}
@Test
public void test98() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "    ";
    String encoded = qpcodec.encode(plain);
    assertEquals("Encoding a whitespace string should return the same string", 
        plain, encoded);
}
@Test
public void test99() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "!@#$%^&*";
    String encoded = qpcodec.encode(plain);
    assertEquals("Encoding a string that contains only special characters should return the same string", 
        plain, encoded);
}
@Test
public void test100() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "1234567890";
    String encoded = qpcodec.encode(plain);
    assertEquals("Encoding a string that contains only numeric characters should return the same string", 
        plain, encoded);
}
@Test
public void test101() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "abc123!@#";
    String encoded = qpcodec.encode(plain);
    assertEquals("Encoding a string that contains a mix of safe and special characters should return the same string", 
        plain, encoded);
}
    @Test
    public void test102() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertNull("Null string quoted-printable encoding test", 
            qpcodec.encode((String)null));
        assertNull("Null string quoted-printable decoding test", 
            qpcodec.decode((String)null));
        
        // Regression test 1
        assertEquals("=", qpcodec.encode("="));  // mutant: change "=" to "=3D"
        assertEquals("=", qpcodec.decode("="));  // mutant: change "=" to "=3D"
    }
    @Test
    public void test103() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        
        // Regression test 2
        assertEquals("0?", qpcodec.encode("0E"));   // mutant: change "0E" to "0?E"
        assertEquals("0?", qpcodec.decode("0?"));   // mutant: change "0?" to "0?E"
    }
    @Test
    public void test104() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=3D=0D=0A", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        
        // Regression test 3
        assertEquals("2", qpcodec.encode("2+"));   // mutant: change "2+" to "2=E+"
        assertEquals("2", qpcodec.decode("2="));   // mutant: change "2" to "2=E"
    }
    @Test
    public void test105() throws Exception {
        String qpdata = "CRLF in an\n encoded text should be=20=\r\n\rskipped in the\r decoding.";
        String expected = "CRLF in an encoded text should be skipped in the decoding.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(expected, qpcodec.decode(qpdata));

        String encoded = qpcodec.encode(expected);
        assertEquals(expected, qpcodec.decode(encoded));
        
        // Regression test 4
        assertEquals("a", qpcodec.encode("a\nb"));  // mutant: change "a\nb" to "a=E\nb"
        assertEquals("a", qpcodec.decode("a="));   // mutant: change "a=" to "a=E"
    }
    @Test
    public void test106() throws Exception {
        String qpdata = "If you believe that truth=3Dbeauty, then surely=20=\r\nmathematics " +
                "is the most beautiful branch of philosophy.";
        String expected = "If you believe that truth=beauty, then surely mathematics " +
                "is the most beautiful branch of philosophy.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(expected, qpcodec.decode(qpdata));

        String encoded = qpcodec.encode(expected);
        assertEquals(expected, qpcodec.decode(encoded));
        
        // Regression test 5
        assertEquals("=", qpcodec.decode("=3"));  // mutant: change "=3" to "=3D"
    }
    @Test
    public void test107() {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("NONSENSE");
           String plain = "Hello there!";
            try {
               qpcodec.encode(plain);
                fail( "We set the encoding to a bogus NONSENSE vlaue, this shouldn't have worked.");
            } catch (EncoderException ee) {
                // Exception expected, test segment passes.
            }
            try {
               qpcodec.decode(plain);
                fail( "We set the encoding to a bogus NONSENSE vlaue, this shouldn't have worked.");
            } catch (DecoderException ee) {
                // Exception expected, test segment passes.
            }
        
        // Regression test 6
        qpcodec = new QuotedPrintableCodec("UTF-Encoding");
        try {
            qpcodec.encode(plain);
            fail("We set the encoding to a bogus UTF-Encoding value, this shouldn't have worked.");
        } catch (EncoderException ee) {
            // Exception expected, test segment passes.
        }
        try {
            qpcodec.decode(plain);
            fail("We set the encoding to a bogus UTF-Encoding value, this shouldn't have worked.");
        } catch (DecoderException ee) {
            // Exception expected, test segment passes.
        }
    }
    @Test
    public void test108() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "1+1 = 2";
        String encoded = new String(QuotedPrintableCodec.
            encodeQuotedPrintable(null, plain.getBytes("UTF-8")));
        assertEquals("Basic quoted-printable encoding test", 
            "1+1 =3D 2", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        
        // Regression test 7
        plain = "1+1 = 2 + 3";
        encoded = new String(QuotedPrintableCodec.
            encodeQuotedPrintable(null, plain.getBytes("UTF-8")));
        assertEquals("Basic quoted-printable encoding test", 
            "1+1 =3D 2 +=3D 3", encoded);  // mutant: change "3" to "=3D 3"
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test109() throws Exception {
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
        
        // Regression test 8
        qpcodec = new QuotedPrintableCodec("NONSENSE");
        try {
            qpcodec.decode((Object) plain);
            fail("We set the encoding to a bogus NONSENSE vlaue, this shouldn't have worked.");
        } catch (DecoderException ee) {
            // Exception expected, test segment passes.
        }
        
        // Regression test 9
        try {
            qpcodec.decode((Object) null);
            fail("We set the encoding to a bogus NONSENSE vlaue, this shouldn't have worked.");
        } catch (DecoderException ee) {
            // Exception expected, test segment passes.
        }
    }
    @Test
    public void test110() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there =\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Hello there =3D=0D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        
        // Regression test 10
        assertEquals("=!", qpcodec.encode("@!"));   // mutant: change "@!" to "=40!"
        assertEquals("=!", qpcodec.decode("=40!"));   // mutant: change "=40!" to "=40@"
    }
    @Test
    public void test111() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String test = null;
        String result = qpcodec.decode( test, "charset" );
        assertEquals("Result should be null", null, result);
        
        // Regression test 11
        test = "= Hello =";
        result = qpcodec.decode( test, null );
        assertEquals("Result should be '= Hello ='", "= Hello =", result);
    }
    @Test
    public void test112() throws Exception {

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
        
        // Regression test 12
        assertEquals(ru_msg, qpcodec.decode(qpcodec.encode(ru_msg, CharEncoding.UTF_8), "UTF-Encoding"));
        assertEquals(ch_msg, qpcodec.decode(qpcodec.encode(ch_msg, CharEncoding.UTF_8), "UTF-Encoding"));
    }
    @Test
    public void test113() throws Exception {
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
        
        // Regression test 13
        try {
            qpcodec.decode("@");
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
        try {
            qpcodec.decode("A=");
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
        try {
            qpcodec.decode("A=W");
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
    }
@Test
public void test114() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "";
    String encoded = qpcodec.encode(plain);
    assertEquals("Empty string encoding test", "", encoded);
    assertEquals("Empty string decoding test", plain, qpcodec.decode(encoded));
}
@Test
public void test115() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
            "Pellentesque aliquet gravida tellus, eget viverra magna condimentum eget.";
    String encoded = qpcodec.encode(plain);
    assertEquals("Long string encoding test", plain, encoded);
    assertEquals("Long string decoding test", plain, qpcodec.decode(encoded));
}
@Test
public void test116() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "!@#$%^&*()";
    String encoded = qpcodec.encode(plain);
    assertEquals("Special characters encoding test", plain, encoded);
    assertEquals("Special characters decoding test", plain, qpcodec.decode(encoded));
}
@Test
public void test117() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "Hello\nworld";
    String encoded = qpcodec.encode(plain);
    assertEquals("Newline character encoding test", plain.replace("\n", "=0A"), encoded);
    assertEquals("Newline character decoding test", plain, qpcodec.decode(encoded));
}
@Test
public void test118() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "Hello\rworld";
    String encoded = qpcodec.encode(plain);
    assertEquals("Carriage return character encoding test", plain.replace("\r", "=0D"), encoded);
    assertEquals("Carriage return character decoding test", plain, qpcodec.decode(encoded));
}
@Test
public void test119() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "example=";
    String encoded = qpcodec.encode(plain);
    assertEquals("Equals character at end encoding test", plain.replace("=", "=3D"), encoded);
    assertEquals("Equals character at end decoding test", plain, qpcodec.decode(encoded));
}
@Test
public void test120() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "ex=ample";
    String encoded = qpcodec.encode(plain);
    assertEquals("Equals character in middle encoding test", plain.replace("=", "=3D"), encoded);
    assertEquals("Equals character in middle decoding test", plain, qpcodec.decode(encoded));
}
@Test
public void test121() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "Hello there!";
    String encoded = qpcodec.encode(plain);
    assertEquals("Whitespace characters encoding test", plain, encoded);
    assertEquals("Whitespace characters decoding test", plain, qpcodec.decode(encoded));
}
@Test
public void test122() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    assertNull("Null string quoted-printable encoding test", 
        qpcodec.encode((String)null));
    assertNull("Null string quoted-printable decoding test", 
        qpcodec.decode((String)null));
    // Regression test - empty string
    assertEquals("Empty string quoted-printable encoding test", 
        "", qpcodec.encode(""));
    assertEquals("Empty string quoted-printable decoding test", 
        "", qpcodec.decode(""));
}
@Test
public void test123() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
    String encoded = qpcodec.encode(plain);
    assertEquals("Safe chars quoted-printable encoding test", 
        plain, encoded);
    assertEquals("Safe chars quoted-printable decoding test", 
        plain, qpcodec.decode(encoded));
    // Regression test - plain string with whitespace
    plain = "  Hello World  ";
    encoded = qpcodec.encode(plain);
    assertEquals("Whitespace quoted-printable encoding test",
        "  Hello World  ", encoded);
    assertEquals("Whitespace quoted-printable decoding test",
        "  Hello World  ", qpcodec.decode(encoded));
}
@Test
public void test124() throws Exception {
    // Regression test - plain string with whitespace in the middle
    String plain ="This is a example of a quoted=printable text file. There is no tt";
    String expected = "This is a example of a quoted=3Dprintable text file. There is no tt";

    assertEquals(expected, new QuotedPrintableCodec().encode(plain));
}
@Test
public void test125() throws Exception {
    final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

    String plain ="This is a example of a quoted-printable text file. This might contain sp=cial chars.";
    String expected = "This is a example of a quoted-printable text file. This might contain sp=3D=\r\ncial chars.";
    assertEquals(expected, qpcodec.encode(plain));

    plain ="This is a example of a quoted-printable text file. This might contain ta\tbs as well.";
    expected = "This is a example of a quoted-printable text file. This might contain ta=09=\r\nbs as well.";
    assertEquals(expected, qpcodec.encode(plain));
    // Regression test - plain string with trailing spaces
    plain ="This is a example of a quoted-printable text file. This ends with spaces      ";
    expected = "This is a example of a quoted-printable text file. This ends with spaces      ";
    assertEquals(expected, qpcodec.encode(plain));
}
@Test
public void test126() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    byte[] plain = null;
    byte[] encoded = qpcodec.encode(plain);
    assertEquals("Encoding a null string should return null", 
        null, encoded);
}
@Test
public void test127() throws Exception {
    final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

    String plain ="This is a example of a quoted-printable text file. There is no end to it\t";
    String expected = "This is a example of a quoted-printable text file. There is no end to i=\r\nt=09";

    assertEquals(expected, qpcodec.encode(plain));

    plain ="This is a example of a quoted-printable text file. There is no end to it ";
    expected = "This is a example of a quoted-printable text file. There is no end to i=\r\nt=20";

    assertEquals(expected, qpcodec.encode(plain));

    // whitespace before soft break
    plain ="This is a example of a quoted-printable text file. There is no end to   ";
    expected = "This is a example of a quoted-printable text file. There is no end to=20=\r\n =20";

    assertEquals(expected, qpcodec.encode(plain));

    // non-printable character before soft break
    plain ="This is a example of a quoted-printable text file. There is no end to=  ";
    expected = "This is a example of a quoted-printable text file. There is no end to=3D=\r\n =20";

    assertEquals(expected, qpcodec.encode(plain));
    // Regression test - plain string with whitespace before soft break
    plain ="This is a example of a quoted-printable text file. There is no end to  ";
    expected = "This is a example of a quoted-printable text file. There is no end to=20";
    assertEquals(expected, qpcodec.encode(plain));
    // Regression test - plain string with non-printable character before soft break
    plain ="This is a example of a quoted-printable text file. There is no end to=";
    expected = "This is a example of a quoted-printable text file. There is no end to=3D=";
    assertEquals(expected, qpcodec.encode(plain));
}
@Test
public void test128() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "1+1 = 2";
    String encoded = (String) qpcodec.encode((Object) plain);
    assertEquals("Basic quoted-printable encoding test", 
        "1+1 =3D 2", encoded);

    byte[] plainBA = plain.getBytes("UTF-8");
    byte[] encodedBA = (byte[]) qpcodec.encode((Object) plainBA);
    encoded = new String(encodedBA);
    assertEquals("Basic quoted-printable encoding test", 
        "1+1 =3D 2", encoded);
        
    Object result = qpcodec.encode((Object) null);
    assertEquals( "Encoding a null Object should return null", null, result);
    
    try {
        Object dObj = new Double(3.0);
        qpcodec.encode( dObj );
        fail( "Trying to url encode a Double object should cause an exception.");
    } catch (EncoderException ee) {
        // Exception expected, test segment passes.
    }
    // Regression test - encoding an integer object
    Object intObj = 42;
    try {
        qpcodec.encode( intObj );
        fail( "Trying to url encode an Integer object should cause an exception.");
    } catch (EncoderException ee) {
        // Exception expected, test segment passes.
    }
}
@Test
public void test129() throws Exception {
    String plain = "Hello there!";
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("UnicodeBig");
    qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
    String encoded1 = qpcodec.encode(plain, "UnicodeBig");
    String encoded2 = qpcodec.encode(plain);
    assertEquals(encoded1, encoded2);
    // Regression test - changing the encoding
    qpcodec = new QuotedPrintableCodec("UTF-16");
    qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
    encoded1 = qpcodec.encode(plain, "UTF-16");
    encoded2 = qpcodec.encode(plain);
    assertEquals(encoded1, encoded2);
}
@Test
public void test130() {
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
public void test131() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String test = null;
    String result = qpcodec.encode( test, "charset" );
    assertEquals("Result should be null", null, result);
    // Regression test - encode a string with null charset
    test = "Hello world!";
    result = qpcodec.encode( test, null );
    assertEquals("Result should be null", null, result);
}
@Test
public void test132() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "=\r\n";
    String encoded = qpcodec.encode(plain);
    assertEquals("Unsafe chars quoted-printable encoding test", 
        "=3D=0D=0A", encoded);
    assertEquals("Unsafe chars quoted-printable decoding test", 
        plain, qpcodec.decode(encoded));
    // Regression test - plain string with unsafe character
    plain = "This string contains an =";
    encoded = qpcodec.encode(plain);
    assertEquals("Unsafe chars quoted-printable encoding test", 
        plain.replace("=", "=3D"), encoded);
    assertEquals("Unsafe chars quoted-printable decoding test", 
        plain, qpcodec.decode(encoded));
}
@Test
public void test133() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "= Hello there =\r\n";
    String encoded = qpcodec.encode(plain);
    assertEquals("Basic quoted-printable encoding test", 
        "=3D Hello there =3D=0D=0A", encoded);
    assertEquals("Basic quoted-printable decoding test", 
        plain, qpcodec.decode(encoded));
    // Regression test - plain string with leading whitespace
    plain = "  Hello World  ";
    encoded = qpcodec.encode(plain);
    assertEquals("Whitespace quoted-printable encoding test",
        "  Hello World  ", encoded);
    assertEquals("Whitespace quoted-printable decoding test",
        "  Hello World  ", qpcodec.decode(encoded));
}
@Test
public void test134() throws Exception {

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
    // Regression test - encode and decode a string with different characters
    String testString = "#$%^&*() éßö";
    assertEquals(testString, qpcodec.decode(qpcodec.encode(testString, CharEncoding.UTF_8), CharEncoding.UTF_8));
}
@Test
public void test135() throws Exception {
    String qpdata = "If you believe that truth=3Dbeauty, then surely mathematics is the most " +
            "b=\r\neautiful branch of philosophy.";
    String expected = "If you believe that truth=beauty, then surely mathematics is the most " +
            "beautiful branch of philosophy.";

    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    assertEquals(qpdata, qpcodec.encode(expected));

    String decoded = qpcodec.decode(qpdata);
    assertEquals(qpdata, qpcodec.encode(decoded));
    // Regression test - plain string with encoded character
    String plain = "This contains an = and a soft line break =\r\n";
    String encoded = qpcodec.encode(plain);
    assertEquals("Soft line break quoted-printable encoding test",
        plain.replace("=", "=3D").replace("\r\n", "=0D=0A"), encoded);
    assertEquals("Soft line break quoted-printable decoding test",
        plain, qpcodec.decode(encoded));
}
}