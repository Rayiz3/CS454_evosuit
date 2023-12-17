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
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test1() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test2() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "0123456789";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test3() throws Exception {
        // whitespace, but does not need to be encoded
        String plain ="This is a example of a quoted=printable text file. There is no tt";
        String expected = "This is a example of a quoted=3Dprintable text file. There is no tt";

        assertEquals(expected, new QuotedPrintableCodec().encode(plain));
    }
    @Test
    public void test4() throws Exception {
        // whitespace at the beginning and the end, but does not need to be encoded
        String plain =" This is a example of a quoted-printable text file. There is no tt ";
        String expected = " This is a example of a quoted-printable text file. There is no tt ";

        assertEquals(expected, new QuotedPrintableCodec().encode(plain));
    }
    @Test
    public void test5() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="This is a example of a quoted-printable text file. This might contain sp=cial chars.";
        String expected = "This is a example of a quoted-printable text file. This might contain sp=3D=\r\ncial chars.";
        assertEquals(expected, qpcodec.encode(plain));

        plain ="This is a example of a quoted-printable text file. This might contain ta\tbs as well.";
        expected = "This is a example of a quoted-printable text file. This might contain ta=09=\r\nbs as well.";
        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test6() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="This is a example of a quoted-printable text file. This might contain special chars.";
        String expected = "This is a example of a quoted-printable text file. This might contain special chars.";
        assertEquals(expected, qpcodec.encode(plain));

        plain ="This is a example of a quoted-printable text file. This might contain tabs as well.";
        expected = "This is a example of a quoted-printable text file. This might contain tabs as well.";
        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test7() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        byte[] plain = null;
        byte[] encoded = qpcodec.encode(plain);
        assertEquals("Encoding a null string should return null", 
            null, encoded);
    }
    @Test
    public void test8() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = null;
        String encoded = qpcodec.encode(plain);
        assertEquals("Encoding a null string should return null", 
            null, encoded);
    }
    @Test
    public void test9() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        // Change the plain string to a value that contains an unsafe character
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]ñ";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test10() throws Exception {
        // Change the plain string to a value that contains an unsafe character
        String plain ="This is a example of a quoted=printable text file. There is no tß";
        String expected = "This is a example of a quoted=3Dprintable text file. There is no tß";

        assertEquals(expected, new QuotedPrintableCodec().encode(plain));
    }
    @Test
    public void test11() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        // Change the plain string to a value that contains an unsafe character
        String plain ="This is a example of a quoted-printable text file. This might contain sp=cial charsÑ.";
        String expected = "This is a example of a quoted-printable text file. This might contain sp=3D=\r\ncial charsÑ.";
        assertEquals(expected, qpcodec.encode(plain));

        // Change the plain string to a value that contains a tab character
        plain ="This is a example of a quoted-printable text file. This might contain ta\tbs as well.";
        expected = "This is a example of a quoted-printable text file. This might contain ta=09=\r\nbs as well.";
        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test12() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        // Change the plain byte array to null
        byte[] plain = null;
        byte[] encoded = qpcodec.encode(plain);
        assertEquals("Encoding a null string should return null", 
            null, encoded);
    }
    @Test
    public void test13() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        // Change the plain string to a value that includes a whitespace before the soft break
        String plain ="This is a example of a quoted-printable text file. There is no end to it\t";
        String expected = "This is a example of a quoted-printable text file. There is no end to i=\r\nt=09";

        assertEquals(expected, qpcodec.encode(plain));

        // Change the plain string to a value that includes a whitespace before the soft break
        plain ="This is a example of a quoted-printable text file. There is no end to it ";
        expected = "This is a example of a quoted-printable text file. There is no end to i=\r\nt=20";

        assertEquals(expected, qpcodec.encode(plain));

        // Change the plain string to a value that includes a whitespace before the soft break
        plain ="This is a example of a quoted-printable text file. There is no end to   ";
        expected = "This is a example of a quoted-printable text file. There is no end to=20=\r\n =20";

        assertEquals(expected, qpcodec.encode(plain));

        // Change the plain string to a value that includes a non-printable character before the soft break
        plain ="This is a example of a quoted-printable text file. There is no end to=  ";
        expected = "This is a example of a quoted-printable text file. There is no end to=3D=\r\n =20";

        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test14() throws Exception {
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
    }
    @Test
    public void test15() throws Exception {
        String plain = "Hello there!";
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("UnicodeBig");
        qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
        String encoded1 = qpcodec.encode(plain, "UnicodeBig");
        String encoded2 = qpcodec.encode(plain);
        assertEquals(encoded1, encoded2);
    }
    @Test
    public void test16() throws Exception {
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
    public void test17() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=3D=0D=0A", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test18() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there =\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Hello there =3D=0D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test19() throws Exception {

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
    public void test20() throws Exception {
        String qpdata = "If you believe that truth=3Dbeauty, then surely mathematics is the most " +
                "b=\r\neautiful branch of philosophy.";
        String expected = "If you believe that truth=beauty, then surely mathematics is the most " +
                "beautiful branch of philosophy.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(qpdata, qpcodec.encode(expected));

        String decoded = qpcodec.decode(qpdata);
        assertEquals(qpdata, qpcodec.encode(decoded));
    }
    @Test
    public void test21() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        
        // Additional test case with non safe char
        String plain2 = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]_";
        String encoded2 = qpcodec.encode(plain2);
        assertEquals("Safe chars quoted-printable encoding test with non safe char", 
            plain2, encoded2);
        assertEquals("Safe chars quoted-printable decoding test with non safe char", 
            plain2, qpcodec.decode(encoded2));
    }
    @Test
    public void test22() throws Exception {
        byte[] plain = null;
        byte[] result = QuotedPrintableCodec.decodeQuotedPrintable( plain );
        assertEquals("Result should be null", null, result);
        
        // Additional test case with non-null array
        byte[] plain2 = {72, 101, 108, 108, 111}; // "Hello"
        byte[] result2 = QuotedPrintableCodec.decodeQuotedPrintable(plain2);
        assertArrayEquals("Decoding non-null array, should return the same array", plain2, result2);
    }
    @Test
    public void test23() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=3D=0D=0A", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        
        // Additional test case with non-unsafe char
        String plain2 = "=\rA";
        String encoded2 = qpcodec.encode(plain2);
        assertEquals("Unsafe chars quoted-printable encoding test with non-unsafe char", 
            "=3D=0AA", encoded2);
        assertEquals("Unsafe chars quoted-printable decoding test with non-unsafe char", 
            plain2, qpcodec.decode(encoded2));
    }
    @Test
    public void test24() throws Exception {
        String qpdata = "CRLF in an\n encoded text should be=20=\r\n\rskipped in the\r decoding.";
        String expected = "CRLF in an encoded text should be skipped in the decoding.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(expected, qpcodec.decode(qpdata));

        String encoded = qpcodec.encode(expected);
        assertEquals(expected, qpcodec.decode(encoded));
        
        // Additional test case with additional CRLF
        String qpdata2 = "CRLF in an\n encoded text should be=20=\r\n\rskipped in the\r decoding.\n\r";
        String expected2 = "CRLF in an encoded text should be skipped in the decoding.\n";

        assertEquals(expected2, qpcodec.decode(qpdata2));

        String encoded2 = qpcodec.encode(expected2);
        assertEquals(expected2, qpcodec.decode(encoded2));
    }
    @Test
    public void test25() throws Exception {
        String qpdata = "If you believe that truth=3Dbeauty, then surely=20=\r\nmathematics " +
                "is the most beautiful branch of philosophy.";
        String expected = "If you believe that truth=beauty, then surely mathematics " +
                "is the most beautiful branch of philosophy.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(expected, qpcodec.decode(qpdata));

        String encoded = qpcodec.encode(expected);
        assertEquals(expected, qpcodec.decode(encoded));
        
        // Additional test case with additional soft line break
        String qpdata2 = "If you believe that truth=3Dbeauty, then surely=20=\r\nmathematics " +
                "is the \rmost beautiful branch of philosophy.";
        String expected2 = "If you believe that truth=beauty, then surely mathematics " +
                "is the most beautiful branch of philosophy.";

        assertEquals(expected2, qpcodec.decode(qpdata2));

        String encoded2 = qpcodec.encode(expected2);
        assertEquals(expected2, qpcodec.decode(encoded2));
    }
    @Test
    public void test26() {
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
            
            // Additional test case with different encoding
            QuotedPrintableCodec qpcodec2 = new QuotedPrintableCodec("UTF-8");
            String plain2 = "Hello there!";
             try {
                qpcodec2.encode(plain2);
                 fail( "We set the encoding to a different encoding value, this shouldn't have worked.");
             } catch (EncoderException ee) {
                 // Exception expected, test segment passes.
             }
             try {
                qpcodec2.decode(plain2);
                 fail( "We set the encoding to a different encoding value, this shouldn't have worked.");
             } catch (DecoderException ee) {
                 // Exception expected, test segment passes.
             }
    }
    @Test
    public void test27() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "1+1 = 2";
        String encoded = new String(QuotedPrintableCodec.
            encodeQuotedPrintable(null, plain.getBytes("UTF-8")));
        assertEquals("Basic quoted-printable encoding test", 
            "1+1 =3D 2", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        
        // Additional test case with different plain string
        String plain2 = "2-1 = 1";
        String encoded2 = new String(QuotedPrintableCodec.
            encodeQuotedPrintable(null, plain2.getBytes("UTF-8")));
        assertEquals("Basic quoted-printable encoding test", 
            "2-1 =3D 1", encoded2);
        assertEquals("Basic quoted-printable decoding test", 
            plain2, qpcodec.decode(encoded2));
        
    }
    @Test
    public void test28() throws Exception {
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
        
        // Additional test case with different Double value
        try {
            Object dObj = new Double(4.0);
            qpcodec.decode( dObj );
            fail( "Trying to url encode a different Double value should cause an exception.");
        } catch (DecoderException ee) {
            // Exception expected, test segment passes.
        }
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
        
        // Additional test case with different plain string
        String plain2 = "= Hi there =\r\n";
        String encoded2 = qpcodec.encode(plain2);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Hi there =3D=0D=0A", encoded2);
        assertEquals("Basic quoted-printable decoding test", 
            plain2, qpcodec.decode(encoded2));
    }
    @Test
    public void test30() throws Exception {

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
        
        // Additional test case with different encoding
        QuotedPrintableCodec qpcodec2 = new QuotedPrintableCodec("ISO-8859-1");
        
        assertEquals(
                "=92=3F-=3F=E7........", 
            qpcodec2.encode(ru_msg, CharEncoding.ISO_8859_1)
            );
        assertEquals("Gr=FCezi_z=E4m=E4", qpcodec2.encode(ch_msg, CharEncoding.ISO_8859_1));
            
        assertEquals(ru_msg, qpcodec2.decode(qpcodec2.encode(ru_msg, CharEncoding.ISO_8859_1), CharEncoding.ISO_8859_1));
        assertEquals(ch_msg, qpcodec2.decode(qpcodec2.encode(ch_msg, CharEncoding.ISO_8859_1), CharEncoding.ISO_8859_1));
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
        
        // Additional test case with invalid character  _.-_.
        try {
            qpcodec.decode("=.A=_..");
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
    }
    @Test
    public void test32() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertNull("Null string quoted-printable encoding test", 
            qpcodec.encode((String)null));
        assertNull("Null string quoted-printable decoding test", 
            qpcodec.decode((String)null));
        
        // Regression test - change null string to empty string
        assertEquals("", qpcodec.encode(""));
        assertEquals("", qpcodec.decode(""));
    }
    @Test
    public void test33() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        
        // Regression test - change safe characters to include '+' and '='
        plain = "abc123_-+.*~!@#$%^&()=+{}\"\\;:`,/[]";
        encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test (including '+' and '=')", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test (including '+' and '=')", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test34() throws Exception {
        // whitespace, but does not need to be encoded
        String plain ="This is a example of a quoted=printable text file. There is no tt";
        String expected = "This is a example of a quoted=3Dprintable text file. There is no tt";

        assertEquals(expected, new QuotedPrintableCodec().encode(plain));
        
        // Regression test - change string to contain special characters ('=' and '.')
        plain ="This is a example of a quoted-printable text file. There is no tt";
        expected = "This is a example of a quoted=printable text file=2E There is no tt";

        assertEquals(expected, new QuotedPrintableCodec().encode(plain));
    }
    @Test
    public void test35() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="This is a example of a quoted-printable text file. This might contain sp=cial chars.";
        String expected = "This is a example of a quoted-printable text file. This might contain sp=3D=\r\ncial chars.";
        assertEquals(expected, qpcodec.encode(plain));

        plain ="This is a example of a quoted-printable text file. This might contain ta\tbs as well.";
        expected = "This is a example of a quoted-printable text file. This might contain ta=09=\r\nbs as well.";
        assertEquals(expected, qpcodec.encode(plain));
        
        // Regression test - change string to contain '%' character
        plain ="This is a example of a quoted-printable text file. This might contain sp=cial % chars.";
        expected = "This is a example of a quoted-printable text file. This might contain sp=3D=\r\ncial =25 chars.";
        assertEquals(expected, qpcodec.encode(plain));
        
        // Regression test - change string to contain non-printable character
        plain ="This is a example of a quoted-printable text file. This might contain sp=cial chars.";
        expected = "This is a example of a quoted-printable text file. This might contain sp=3D=\r\ncial =0A chars.";
        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test36() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        byte[] plain = null;
        byte[] encoded = qpcodec.encode(plain);
        assertEquals("Encoding a null string should return null", 
            null, encoded);
        
        // Regression test - change null byte array to empty byte array
        byte[] empty = new byte[0];
        assertEquals("", new QuotedPrintableCodec().encode(empty));
    }
    @Test
    public void test37() throws Exception {
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
        
        // Regression test - change spaces before and after soft break to tabs
        plain ="This is a example of a quoted-printable text file. There is no end to   ";
        expected = "This is a example of a quoted-printable text file. There is no end to=09=\r\n=09";

        assertEquals(expected, qpcodec.encode(plain));

        plain ="This is a example of a quoted-printable text file. There is no end to   ";
        expected = "This is a example of a quoted-printable text file. There is no end to=09=\r\n=09";

        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test38() throws Exception {
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
        
        // Regression test - encode non-String and non-byte array object
        Object obj = new Integer(123);
        assertEquals("123", new QuotedPrintableCodec().encode(obj));
    }
    @Test
    public void test39() throws Exception {
        String plain = "Hello there!";
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("UnicodeBig");
        qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
        String encoded1 = qpcodec.encode(plain, "UnicodeBig");
        String encoded2 = qpcodec.encode(plain);
        assertEquals(encoded1, encoded2);
        
        // Regression test - change the default encoding to "UTF-16"
        qpcodec = new QuotedPrintableCodec("UTF-16");
        qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
        encoded1 = qpcodec.encode(plain, "UTF-16");
        encoded2 = qpcodec.encode(plain);
        assertEquals(encoded1, encoded2);
    }
    @Test
    public void test40() {
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
            
        // Regression test - change the encoding to "ISO-8859-1"
        qpcodec = new QuotedPrintableCodec("ISO-8859-1");
        try {
            qpcodec.encode(plain);
            fail( "We set the encoding to ISO-8859-1, this shouldn't have worked.");
        } catch (EncoderException ee) {
            // Exception expected, test segment passes.
        }
        try {
            qpcodec.decode(plain);
            fail( "We set the encoding to ISO-8859-1, this shouldn't have worked.");
        } catch (DecoderException ee) {
            // Exception expected, test segment passes.
        }
    }
    @Test
    public void test41() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String test = null;
        String result = qpcodec.encode( test, "charset" );
        assertEquals("Result should be null", null, result);
        
        // Regression test - change the charset to empty string
        result = qpcodec.encode( test, "" );
        assertEquals("Result should be null", null, result);
    }
    @Test
    public void test42() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=3D=0D=0A", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        
        // Regression test - change the unsafe character '=' to '&'
        plain = "&\r\n";
        encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=26=0D=0A", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test43() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there =\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Hello there =3D=0D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        
        // Regression test - change the string to contain non-printable characters
        plain = "=\tHello \167\164\145\162 ";
        encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D=09Hello \\167\\164\\145\\162 ", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test44() throws Exception {

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
        
        // Regression test - change the charset to "UTF-16"
        assertEquals(
            "=FF=FE=\u0412\u0441\u0435\u043C_\u043F\u0440\u0438\u0432=\u0435\u0442", 
        new QuotedPrintableCodec().encode(ru_msg, "UTF-16")
        );
        assertEquals("Gr=FCezi_z=E4m=E4", new QuotedPrintableCodec().encode(ch_msg, "UTF-16"));
    }
    @Test
    public void test45() throws Exception {
        String qpdata = "If you believe that truth=3Dbeauty, then surely mathematics is the most " +
                "b=\r\neautiful branch of philosophy.";
        String expected = "If you believe that truth=beauty, then surely mathematics is the most " +
                "beautiful branch of philosophy.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(qpdata, qpcodec.encode(expected));

        String decoded = qpcodec.decode(qpdata);
        assertEquals(qpdata, qpcodec.encode(decoded));
        
        // Regression test - change string to contain '_' characters before soft break
        qpdata = "If you believe that truth=3Dbeauty, then surely mathe_matics is the most " +
                "b=\r\neautiful branch of philosophy.";
        expected = "If you believe that truth=beauty, then surely mathe_matics is the most " +
                "beautiful branch of philosophy.";

        assertEquals(expected, qpcodec.encode(expected));

        decoded = qpcodec.decode(expected);
        assertEquals(expected, qpcodec.encode(decoded));
    }
    @Test
    public void test46() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertNull("Null string quoted-printable encoding test", 
            qpcodec.encode((String)null));
        assertNull("Null string quoted-printable decoding test", 
            qpcodec.decode((String)null));
    }
    @Test
    public void test47() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test48() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=3D=0D=0A", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test49() throws Exception {
        String qpdata = "CRLF in an\n encoded text should be=20=\r\n\rskipped in the\r decoding.";
        String expected = "CRLF in an encoded text should be skipped in the decoding.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(expected, qpcodec.decode(qpdata));

        String encoded = qpcodec.encode(expected);
        assertEquals(expected, qpcodec.decode(encoded));
    }
    @Test
    public void test50() throws Exception {
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
    public void test51() {
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
    }
    @Test
    public void test52() throws Exception {
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
    public void test53() throws Exception {
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
    public void test54() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there =\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Hello there =3D=0D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test55() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String test = null;
        String result = qpcodec.decode( test, "charset" );
        assertEquals("Result should be null", null, result);
    }
    @Test
    public void test56() throws Exception {

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
    public void test57() throws Exception {
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
    public void test58() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = ""; // empty string
        String encoded = qpcodec.encode(plain);
        assertEquals("Empty input quoted-printable encoding test", 
            "", encoded);
        assertEquals("Empty input quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test59() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = " ";
        String encoded = qpcodec.encode(plain);
        assertEquals("Space quoted-printable encoding test", 
            "=20", encoded);
        assertEquals("Space quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test60() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "\r";
        String encoded = qpcodec.encode(plain);
        assertEquals("CR quoted-printable encoding test", 
            "=0D", encoded);
        assertEquals("CR quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test61() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("LF quoted-printable encoding test", 
            "=0A", encoded);
        assertEquals("LF quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test62() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("CRLF quoted-printable encoding test", 
            "=0D=0A", encoded);
        assertEquals("CRLF quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
@Test
public void test63() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    assertNull("Null string quoted-printable encoding test",
            qpcodec.encode((String) null));
    assertNull("Null string quoted-printable decoding test",
            qpcodec.decode((String) null));
}
@Test
public void test64() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
    String encoded = qpcodec.encode(plain);
    assertEquals("Safe chars quoted-printable encoding test",
            plain, encoded);
    assertEquals("Safe chars quoted-printable decoding test",
            plain, qpcodec.decode(encoded));
}
@Test
public void test65() throws Exception {
    // whitespace, but does not need to be encoded
    String plain = "This is a example of a quoted=printable text file. There is no tt";
    String expected = "This is a example of a quoted=3Dprintable text file. There is no tt";

    assertEquals(expected, new QuotedPrintableCodec().encode(plain));
}
@Test
public void test66() throws Exception {
    final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

    String plain = "This is a example of a quoted-printable text file. This might contain sp=cial chars.";
    String expected = "This is a example of a quoted-printable text file. This might contain sp=3D=\r\ncial chars.";
    assertEquals(expected, qpcodec.encode(plain));

    plain = "This is a example of a quoted-printable text file. This might contain ta\tbs as well.";
    expected = "This is a example of a quoted-printable text file. This might contain ta=09=\r\nbs as well.";
    assertEquals(expected, qpcodec.encode(plain));
}
@Test
public void test67() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    byte[] plain = null;
    byte[] encoded = qpcodec.encode(plain);
    assertEquals("Encoding a null string should return null",
            null, encoded);
}
@Test
public void test68() throws Exception {
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
public void test69() throws Exception {
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
public void test70() throws Exception {
    String plain = "Hello there!";
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("UnicodeBig");
    qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
    String encoded1 = qpcodec.encode(plain, "UnicodeBig");
    String encoded2 = qpcodec.encode(plain);
    assertEquals(encoded1, encoded2);
}
@Test
public void test71() {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("NONSENSE");
    String plain = "Hello there!";
    try {
        qpcodec.encode(plain);
        fail("We set the encoding to a bogus NONSENSE value, this shouldn't have worked.");
    } catch (EncoderException ee) {
        // Exception expected, test segment passes.
    }
    try {
        qpcodec.decode(plain);
        fail("We set the encoding to a bogus NONSENSE value, this shouldn't have worked.");
    } catch (DecoderException ee) {
        // Exception expected, test segment passes.
    }
}
@Test
public void test72() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String test = null;
    String result = qpcodec.encode(test, "charset");
    assertEquals("Result should be null", null, result);
}
@Test
public void test73() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "=\r\n";
    String encoded = qpcodec.encode(plain);
    assertEquals("Unsafe chars quoted-printable encoding test",
            "=3D=0D=0A", encoded);
    assertEquals("Unsafe chars quoted-printable decoding test",
            plain, qpcodec.decode(encoded));
}
@Test
public void test74() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "= Hello there =\r\n";
    String encoded = qpcodec.encode(plain);
    assertEquals("Basic quoted-printable encoding test",
            "=3D Hello there =3D=0D=0A", encoded);
    assertEquals("Basic quoted-printable decoding test",
            plain, qpcodec.decode(encoded));
}
@Test
public void test75() throws Exception {

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
public void test76() throws Exception {
    String qpdata = "If you believe that truth=3Dbeauty, then surely mathematics is the most " +
            "b=\r\neautiful branch of philosophy.";
    String expected = "If you believe that truth=beauty, then surely mathematics is the most " +
            "beautiful branch of philosophy.";

    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    assertEquals(qpdata, qpcodec.encode(expected));

    String decoded = qpcodec.decode(qpdata);
    assertEquals(qpdata, qpcodec.encode(decoded));
}
@Test
public void test77() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String encoded = qpcodec.encode("");
    assertEquals("", qpcodec.decode(encoded));
}
@Test
public void test78() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "\r\n";
    String encoded = qpcodec.encode(plain);
    assertEquals("\r\n", qpcodec.decode(encoded));
}
@Test
public void test79() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "123456";
    String encoded = qpcodec.encode(plain);
    assertEquals(plain, qpcodec.decode(encoded));
}
@Test
public void test80() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "   ";
    String encoded = qpcodec.encode(plain);
    assertEquals(plain, qpcodec.decode(encoded));
}
@Test
public void test81() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "!@#$%^&*()";
    String encoded = qpcodec.encode(plain);
    assertEquals(plain, qpcodec.decode(encoded));
}
    @Test
    public void test82() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertNull("Null string quoted-printable encoding test",
            qpcodec.encode((String)null));
        assertNull("Null string quoted-printable decoding test",
            qpcodec.decode((String)null));
        
        // Regression test case to kill mutants
        assertNotNull("Empty string quoted-printable encoding test",
            qpcodec.encode(""));
        assertNull("Empty string quoted-printable decoding test",
            qpcodec.decode(""));
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
        
        // Regression test case to kill mutants
        String anotherPlain = "";
        assertEquals("Empty string quoted-printable encoding test",
            "", qpcodec.encode(anotherPlain));
        assertEquals("Empty string quoted-printable decoding test",
            anotherPlain, qpcodec.decode(""));
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
        
        // Regression test case to kill mutants
        String anotherPlain = "!=\r\n";
        assertEquals("Unsafe chars quoted-printable encoding test",
            "=21=3D=0D=0A", qpcodec.encode(anotherPlain));
    }
    @Test
    public void test85() throws Exception {
        String qpdata = "CRLF in an\n encoded text should be=20=\r\n\rskipped in the\r decoding.";
        String expected = "CRLF in an encoded text should be skipped in the decoding.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(expected, qpcodec.decode(qpdata));

        String encoded = qpcodec.encode(expected);
        assertEquals(expected, qpcodec.decode(encoded));
        
        // Regression test case to kill mutants
        String anotherQpData = "\n";
        assertEquals("", qpcodec.decode(anotherQpData));
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
        
        // Regression test case to kill mutants
        String anotherQpData = "No soft line break";
        assertEquals(anotherQpData, qpcodec.decode(anotherQpData));
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
        
        // Regression test case to kill mutants
        QuotedPrintableCodec anotherQpCodec = new QuotedPrintableCodec(null);
        try {
           plain = "Hello there!";
           anotherQpCodec.encode(plain);
           fail("We set the encoding to null, this shouldn't have worked.");
        } catch (EncoderException ee) {
           // Exception expected, test segment passes.
        }
        try {
           plain = "Hello there!";
           anotherQpCodec.decode(plain);
           fail("We set the encoding to null, this shouldn't have worked.");
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
        
        // Regression test case to kill mutants
        String anotherPlain = "Some string";
        assertEquals("Basic quoted-printable encoding test",
            anotherPlain, qpcodec.encode(anotherPlain));
        assertEquals("Basic quoted-printable decoding test",
            anotherPlain, qpcodec.decode(anotherPlain));
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
        
        // Regression test case to kill mutants
        String anotherPlain = null;
        result = qpcodec.decode((Object) anotherPlain);
        assertEquals( "Decoding a null Object should return null", null, result);
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
        
        // Regression test case to kill mutants
        String anotherPlain = "? Hello there";
        assertEquals("Basic quoted-printable encoding test",
            "=3F Hello there", qpcodec.encode(anotherPlain));
    }
    @Test
    public void test91() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String test = null;
        String result = qpcodec.decode( test, "charset" );
        assertEquals("Result should be null", null, result);
        
        // Regression test case to kill mutants
        String anotherTest = "";
        result = qpcodec.decode(anotherTest, "charset");
        assertEquals("Result should be null", anotherTest, result);
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
        
        // Regression test case to kill mutants
        String anotherRu_msg = "";
        String anotherCh_msg = "";
        assertEquals("", qpcodec.encode(anotherRu_msg, CharEncoding.UTF_8));
        assertEquals("", qpcodec.encode(anotherCh_msg, CharEncoding.UTF_8));
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
        
        // Regression test case to kill mutants
        String anotherQpData = "=";
        try {
            qpcodec.decode(anotherQpData);
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
        String anotherQpData2 = "some string";
        try {
            qpcodec.decode(anotherQpData2);
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
    }
    @Test
    public void test94() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertNull("Null string quoted-printable encoding test 1", 
            qpcodec.encode((String)null));
        assertNull("Null string quoted-printable decoding test 1", 
            qpcodec.decode((String)null));
        assertNull("Null string quoted-printable encoding test 2", 
            qpcodec.encode(""));
        assertNull("Null string quoted-printable decoding test 2", 
            qpcodec.decode(""));
    }
    @Test
    public void test95() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        
        String plain2 = "ABC123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded2 = qpcodec.encode(plain2);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain2, encoded2);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain2, qpcodec.decode(encoded2));
    }
    @Test
    public void test96() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=3D=0D=0A", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        
        String plain2 = "=\n";
        String encoded2 = qpcodec.encode(plain2);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=3D=0D=0A", encoded2);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain2, qpcodec.decode(encoded2));
    }
    @Test
    public void test97() throws Exception {
        String qpdata = "CRLF in an\n encoded text should be=20=\r\n\rskipped in the\r decoding.";
        String expected = "CRLF in an encoded text should be skipped in the decoding.";
        String qpdata2 = "CRLF in an\n encoded text should be = \r\n skipped in the\r decoding.";
        String expected2 = "CRLF in an encoded text should be  skipped in the decoding.";
        
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(expected, qpcodec.decode(qpdata));
        assertEquals(expected2, qpcodec.decode(qpdata2));

        String encoded = qpcodec.encode(expected);
        assertEquals(expected, qpcodec.decode(encoded));
        
        String encoded2 = qpcodec.encode(expected2);
        assertEquals(expected2, qpcodec.decode(encoded2));
    }
    @Test
    public void test98() throws Exception {
        String qpdata = "If you believe that truth=3Dbeauty, then surely=20=\r\nmathematics " +
                "is the most beautiful branch of philosophy.";
        String expected = "If you believe that truth=beauty, then surely mathematics " +
                "is the most beautiful branch of philosophy.";
        String qpdata2 = "If you believe that truth=3Dbeauty, th=\r\n in surely=20=\r\nmaths";
        String expected2 = "If you believe that truth=beauty, th in surely mathematics";
        
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(expected, qpcodec.decode(qpdata));
        assertEquals(expected2, qpcodec.decode(qpdata2));

        String encoded = qpcodec.encode(expected);
        assertEquals(expected, qpcodec.decode(encoded));
        
        String encoded2 = qpcodec.encode(expected2);
        assertEquals(expected2, qpcodec.decode(encoded2));
    }
    @Test
    public void test99() {
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
        
        QuotedPrintableCodec qpcodec2 = new QuotedPrintableCodec("UTF-8");
        try {
            qpcodec2.encode(plain);
            fail( "We set the encoding to a bogus UTF-8 vlaue, this shouldn't have worked.");
        } catch (EncoderException ee) {
            // Exception expected, test segment passes.
        }
        try {
            qpcodec2.decode(plain);
            fail( "We set the encoding to a bogus UTF-8 vlaue, this shouldn't have worked.");
        } catch (DecoderException ee) {
            // Exception expected, test segment passes.
        }
    }
    @Test
    public void test100() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "1+1 = 2";
        String encoded = new String(QuotedPrintableCodec.
            encodeQuotedPrintable(null, plain.getBytes("UTF-8")));
        assertEquals("Basic quoted-printable encoding test", 
            "1+1 =3D 2", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        
        String plain2 = "2+1 = 3";
        String encoded2 = new String(QuotedPrintableCodec.
            encodeQuotedPrintable(null, plain.getBytes("UTF-8")));
        assertEquals("Basic quoted-printable encoding test", 
            "2+1 =3D 3", encoded2);
        assertEquals("Basic quoted-printable decoding test", 
            plain2, qpcodec.decode(encoded2));
    }
    @Test
    public void test101() throws Exception {
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
        
        try {
            Object dObj = new Double(4.0);
            qpcodec.decode( dObj );
            fail( "Trying to url encode a Double object should cause an exception.");
        } catch (DecoderException ee) {
            // Exception expected, test segment passes.
        }
    }
    @Test
    public void test102() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there =\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Hello there =3D=0D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        
        String plain2 = "= Hi there =\r\n";
        String encoded2 = qpcodec.encode(plain2);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Hi there =3D=0D=0A", encoded2);
        assertEquals("Basic quoted-printable decoding test", 
            plain2, qpcodec.decode(encoded2));
    }
    @Test
    public void test103() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String test = null;
        String result = qpcodec.decode( test, "charset" );
        assertEquals("Result should be null", null, result);
        
        String test2 = "";
        String result2 = qpcodec.decode( test2, "charset" );
        assertEquals("Result should be null", null, result2);
    }
    @Test
    public void test104() throws Exception {

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
    public void test105() throws Exception {
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
        
        try {
            qpcodec.decode("A+B=C");
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
    }
    @Test
    public void test106() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertNull("Null string quoted-printable encoding test", 
            qpcodec.encode((String)null));
        assertNull("Null string quoted-printable decoding test", 
            qpcodec.decode((String)null));
        // New test case with empty string
        assertEquals("Empty string quoted-printable encoding test", 
            "", qpcodec.encode(""));
    }
    @Test
    public void test107() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));

        // New test case with safe characters at the end
        plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]end";
        encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));

        // New test case with safe characters at the beginning
        plain = "startabc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));

        // New test case with safe characters at both ends
        plain = "startabc123_-.*~!@#$%^&()+{}\"\\;:`,/[]end";
        encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test108() throws Exception {
        // whitespace, but does not need to be encoded
        String plain ="This is a example of a quoted=printable text file. There is no tt";
        String expected = "This is a example of a quoted=3Dprintable text file. There is no tt";

        assertEquals(expected, new QuotedPrintableCodec().encode(plain));

        // New test case with final and trailing whitespaces
        plain ="This is a example of a quoted=printable text file. There is no tt          ";
        expected = "This is a example of a quoted=3Dprintable text file. There is no tt          ";

        assertEquals(expected, new QuotedPrintableCodec().encode(plain));
    }
    @Test
    public void test109() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="This is a example of a quoted-printable text file. This might contain sp=cial chars.";
        String expected = "This is a example of a quoted-printable text file. This might contain sp=3D=\r\ncial chars.";
        assertEquals(expected, qpcodec.encode(plain));

        // New test case with trailing whitespace
        plain ="This is a example of a quoted-printable text file. This might contain sp=cial chars.(end)          ";
        expected = "This is a example of a quoted-printable text file. This might contain sp=3D=\r\ncial chars.(end)          ";
        assertEquals(expected, qpcodec.encode(plain));
        
        // New test case with trailing tab
        plain ="This is a example of a quoted-printable text file. This might contain sp=cial chars.(end)\t";
        expected = "This is a example of a quoted-printable text file. This might contain sp=3D=\r\ncial chars.(end)\t";
        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test110() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        byte[] plain = null;
        byte[] encoded = qpcodec.encode(plain);
        assertEquals("Encoding a null string should return null", 
            null, encoded);
        // New test case with empty byte array
        plain = new byte[0];
        encoded = qpcodec.encode(plain);
        assertEquals("Encoding an empty byte array should return an empty byte array", 
            0, encoded.length);
    }
    @Test
    public void test111() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="This is a example of a quoted-printable text file. There is no end to it\t";
        String expected = "This is a example of a quoted=printable text file. There is no end to it=\r\n\t";

        assertEquals(expected, qpcodec.encode(plain));

        plain ="This is a example of a quoted-printable text file. There is no end to it ";
        expected = "This is a example of a quoted=printable text file. There is no end to it=\r\n ";

        assertEquals(expected, qpcodec.encode(plain));

        // whitespace before soft break
        plain ="This is a example of a quoted-printable text file. There is no end to   ";
        expected = "This is a example of a quoted=printable text file. There is no end to  =\r\n  ";

        assertEquals(expected, qpcodec.encode(plain));

        // non-printable character before soft break
        plain ="This is a example of a quoted-printable text file. There is no end to=  ";
        expected = "This is a example of a quoted=printable text file. There is no end to=3D\r\n  ";

        assertEquals(expected, qpcodec.encode(plain));

        // New test case with space before soft break
        plain ="This is a example of a quoted-printable text file. There is no end to  ";
        expected = "This is a example of a quoted-printable text file. There is no end to =\r\n ";

        assertEquals(expected, qpcodec.encode(plain));

        // New test case with space and tab before soft break
        plain ="This is a example of a quoted-printable text file. There is no end to \t ";
        expected = "This is a example of a quoted-printable text file. There is no end to =\r\n \t ";

        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test112() throws Exception {
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

        // New test case with Integer object
        Object iObj = new Integer(5);
        try {
            qpcodec.encode(iObj);
            fail("Trying to url encode an Integer object should cause an exception.");
        } catch (EncoderException ee) {
            // Exception expected, test segment passes.
        }
    }
    @Test
    public void test113() throws Exception {
        String plain = "Hello there!";
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("UnicodeBig");
        qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
        String encoded1 = qpcodec.encode(plain, "UnicodeBig");
        String encoded2 = qpcodec.encode(plain);
        assertEquals(encoded1, encoded2);

        // New test case with different default encoding
        plain = "Hi!";
        qpcodec = new QuotedPrintableCodec("UTF-16");
        qpcodec.encode(plain);
        encoded1 = qpcodec.encode(plain, "UTF-16");
        encoded2 = qpcodec.encode(plain);
        assertEquals(encoded1, encoded2);
    }
    @Test
    public void test114() {
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

        // New test case with null encoding
        qpcodec = new QuotedPrintableCodec(null);
        try {
            qpcodec.encode(plain);
            fail( "We set the encoding to null, this shouldn't have worked.");
        } catch (NullPointerException npe) {
            // Exception expected, test segment passes.
        }
    }
    @Test
    public void test115() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String test = null;
        String result = qpcodec.encode( test, "charset" );
        assertEquals("Result should be null", null, result);

        // New test case with null charset
        result = qpcodec.encode(test, null);
        assertEquals("Result should be null", null, result);

        // New test case with empty charset
        result = qpcodec.encode(test, "");
        assertEquals("Result should be null", null, result);
    }
    @Test
    public void test116() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=3D=0D=0A", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));

        // New test case with unsafe character at the end
        plain = "=\r\na";
        encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=3D=0D=0Aa", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));

        // New test case with unsafe character at the beginning
        plain = "a=\r\n";
        encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "a=3D=0D=0A", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));

        // New test case with unsafe character at both ends
        plain = "a=\r\na";
        encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "a=3D=0D=0Aa", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test117() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there =\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Hello there =3D=0D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));

        // New test case with equal sign in plain text
        plain = "Hello= there =\r\n";
        encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "Hello=3D there =3D=0D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test118() throws Exception {

        String ru_msg = constructString(RUSSIAN_STUFF_UNICODE); 
        String ch_msg = constructString(SWISS_GERMAN_STUFF_UNICODE); 
        
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        
        assertEquals(
            "=D0=92=D1=81=D0=B5=D0=BC_=D0=BF=D1=80=D0=B8=D0=B2=D0=B5=D1=82", 
        qpcodec.encode(ru_msg, CharEncoding.UTF_8)
        );
        assertEquals("Gr=C3=BCezi_z=C3=A4m=C3=A4", qpcodec.encode(ch_msg, CharEncoding.UTF_8));
        
        assertEquals(ru_msg, qpcodec.decode(qpcodec.encode(ru_msg, CharEncoding.UTF_8),
            CharEncoding.UTF_8));
        assertEquals(ch_msg, qpcodec.decode(qpcodec.encode(ch_msg, CharEncoding.UTF_8),
            CharEncoding.UTF_8));

        // New test case with invalid UTF-8 characters
        String invalidUTF8 = "This is an invalid UTF-8 character: \uFFFE";
        encoded = qpcodec.encode(invalidUTF8, CharEncoding.UTF_8);
        assertEquals("Invalid UTF-8 characters quoted-printable encoding test", 
            "This is an invalid UTF-8 character: =EF=BF=BE", encoded);
        assertEquals("Invalid UTF-8 characters quoted-printable decoding test", 
            invalidUTF8, qpcodec.decode(encoded, CharEncoding.UTF_8));
    }
    @Test
    public void test119() throws Exception {
        String qpdata = "If you believe that truth=3Dbeauty, then surely mathematics is the most " +
                "b=\r\neautiful branch of philosophy.";
        String expected = "If you believe that truth=beauty, then surely mathematics is the most " +
                "beautiful branch of philosophy.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(qpdata, qpcodec.encode(expected));

        String decoded = qpcodec.decode(qpdata);
        assertEquals(qpdata, qpcodec.encode(decoded));

        // New test case with quoted printable encoded text containing multiple soft line breaks
        qpdata = qpcodec.encode("This is a test\r\nThis is another test");
        qpdata = qpdata.replaceAll("=\r?\n\r?\n", "   ");
        assertEquals("If you believe that truth=3Dbeauty, then surely mathematics is the most " +
                "beautiful branch of philosophy.   ", qpcodec.decode(qpdata));
    }
    @Test
    public void test120() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertNull("Null string quoted-printable encoding test", 
            qpcodec.encode((String)null));
        assertNull("Null string quoted-printable decoding test", 
            qpcodec.decode((String)null));
        // Regression test 1: Change input to empty string
        assertEquals("Empty string quoted-printable encoding test",
                "",
                qpcodec.encode(""));
        assertNull("Null string quoted-printable decoding test 2",
                qpcodec.decode(""));
    }
    @Test
    public void test121() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        // Regression test 2: Change input to all safe characters
        assertEquals("All safe characters quoted-printable encoding test",
                plain,
                qpcodec.encode("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_-.@"));
        assertEquals("All safe characters quoted-printable decoding test",
                "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_-.@",
                qpcodec.decode("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_-.@"));
    }
    @Test
    public void test122() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=3D=0D=0A", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        // Regression test 3: Change input to other unsafe characters
        assertEquals("Unsafe characters quoted-printable encoding test",
                "=3D=0D=0A=20",
                qpcodec.encode("= \r\n "));
        assertEquals("Unsafe characters quoted-printable decoding test",
                "= \r\n ",
                qpcodec.decode("=3D=0D=0A=20"));
    }
    @Test
    public void test123() throws Exception {
        String qpdata = "CRLF in an\n encoded text should be=20=\r\n\rskipped in the\r decoding.";
        String expected = "CRLF in an encoded text should be skipped in the decoding.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(expected, qpcodec.decode(qpdata));
        // Regression test 4: Change input to include multiple CRLF sequences
        assertEquals(expected, qpcodec.decode("CRLF in an\n encoded text should be=20=\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\nskipped in the\r\r\n\rdecoding."));
    }
    @Test
    public void test124() throws Exception {
        String qpdata = "If you believe that truth=3Dbeauty, then surely=20=\r\nmathematics " +
                "is the most beautiful branch of philosophy.";
        String expected = "If you believe that truth=beauty, then surely mathematics " +
                "is the most beautiful branch of philosophy.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(expected, qpcodec.decode(qpdata));
        // Regression test 5: Change input to include additional soft line breaks
        assertEquals(expected, qpcodec.decode("If you believe that truth=3Dbeauty, then surely=20=\r\nmathematics " +
                "is the most beautiful branch of philosophy.\r\n\r\n"));
    }
    @Test
    public void test125() {
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
        // Regression test 6: Change encoding to a valid one
        qpcodec = new QuotedPrintableCodec(Charset.defaultCharset());
        assertEquals("Hello there!", qpcodec.encode(plain));
        assertEquals("Hello there!", qpcodec.decode(plain));
    }
    @Test
    public void test126() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "1+1 = 2";
        String encoded = new String(QuotedPrintableCodec.
            encodeQuotedPrintable(null, plain.getBytes("UTF-8")));
        assertEquals("Basic quoted-printable encoding test", 
            "1+1 =3D 2", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        // Regression test 7: Change input to URL encoding
        assertEquals("Basic url encoding test",
                "1%2B1+%3D+2",
                new String(QuotedPrintableCodec.
                        encodeQuotedPrintable(null, plain.getBytes("UTF-8")), "UTF-8"));
        assertEquals("Basic url decoding test",
                plain,
                qpcodec.decode("1%2B1+%3D+2"));
    }
    @Test
    public void test127() throws Exception {
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
        // No additional regression tests for this method.
    }
    @Test
    public void test128() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there =\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Hello there =3D=0D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        // Regression test 8: Change input to include additional line breaks
        assertEquals("Basic quoted-printable encoding test with line breaks",
                "=3D Hello there =3D=\r\n\r\n\r",
                qpcodec.encode("= Hello there =\r\n\r\n\r"));
        assertEquals("Basic quoted-printable decoding test with line breaks",
                "= Hello there =\r\n\r\n\r",
                qpcodec.decode("=3D Hello there =3D=\r\n\r\n\r"));
    }
    @Test
    public void test129() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String test = null;
        String result = qpcodec.decode( test, "charset" );
        assertEquals("Result should be null", null, result);
        // Regression test 9: Change charset to a valid one
        result = qpcodec.decode(test, Charset.defaultCharset());
        assertEquals("Result should be null with default charset", null, result);
    }
    @Test
    public void test130() throws Exception {

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
        // No additional regression tests for this method.
    }
    @Test
    public void test131() throws Exception {
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
        // Regression test 10: Change input to include invalid characters
        try {
            qpcodec.decode("$");
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
        try {
            qpcodec.decode("&");
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
        try {
            qpcodec.decode("%");
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
    }
    @Test
    public void test132() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        qpcodec.setDefaultCharset(null);
        assertEquals(null, qpcodec.getDefaultCharset());
    }
    @Test
    public void test133() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        qpcodec.setDefaultCharset("");
        assertEquals("", qpcodec.getDefaultCharset());
    }
    @Test
    public void test134() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        qpcodec.setDefaultCharset("UTF-8");
        assertEquals("UTF-8", qpcodec.getDefaultCharset());
    }
    @Test
    public void test135() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain, null);
        assertEquals(plain, encoded);
        String decoded = qpcodec.decode(encoded, null);
        assertEquals(plain, decoded);
    }
    @Test
    public void test136() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain, "");
        assertEquals(plain, encoded);
        String decoded = qpcodec.decode(encoded, "");
        assertEquals(plain, decoded);
    }
    @Test
    public void test137() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain, "UTF-8");
        assertEquals(plain, encoded);
        String decoded = qpcodec.decode(encoded, "UTF-8");
        assertEquals(plain, decoded);
    }
    @Test
    public void test138() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertNull("Null string quoted-printable encoding test", 
            qpcodec.encode((String)null));
        assertNull("Null string quoted-printable decoding test", 
            qpcodec.decode((String)null));
        
        // Regression test case 1
        assertEquals("Null string quoted-printable encoding test", 
            "xyz", qpcodec.encode("xyz")); 
        assertEquals("Null string quoted-printable decoding test", 
            "abc", qpcodec.decode("abc")); 
    }
    @Test
    public void test139() throws Exception {
        String qpdata = "If you believe that truth=3Dbeauty, then surely mathematics is the most " +
                "b=\r\neautiful branch of philosophy.";
        String expected = "If you believe that truth=beauty, then surely mathematics is the most " +
                "beautiful branch of philosophy.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(qpdata, qpcodec.encode(expected));
        
        // Regression test case 1
        assertEquals(qpdata, qpcodec.encode(""));
        
        // Regression test case 2
        assertEquals(qpdata, qpcodec.encode("If you believe"));

        String decoded = qpcodec.decode(qpdata);
        assertEquals(qpdata, qpcodec.encode(decoded));
    }
}