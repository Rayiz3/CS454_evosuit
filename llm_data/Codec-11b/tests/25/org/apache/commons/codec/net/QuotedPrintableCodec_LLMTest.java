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
        String plain = "ab#1_;.+?/!@#$%^&()-={}\"\\;:`,";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test (regress1)", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test (regress1)", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test1() throws Exception {
        // whitespace, but does not need to be encoded
        String plain ="This is a example of a quoted=printable text file! There is no end to it!";
        String expected = "This is a example of a quoted=3Dprintable text file! There is no end to it!";

        assertEquals(expected, new QuotedPrintableCodec().encode(plain));
    }
    @Test
    public void test2() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="This is a example of a quoted-printable text file! This might contain special characters.";
        String expected = "This is a example of a quoted-printable text file! This might contain special characters.";
        assertEquals(expected, qpcodec.encode(plain));

        plain ="This is a example of a quoted-printable text file! This might contain t\rabs as well.";
        expected = "This is a example of a quoted-printable text file! This might contain t=09bs as well.";
        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test3() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        byte[] plain = new byte[0];
        byte[] encoded = qpcodec.encode(plain);
        assertEquals("Encoding a null string should return null (regress1)", 
            null, encoded);
    }
    @Test
    public void test4() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="This is a example of a quoted-printable text file! There is no end to it!";
        String expected = "This is a example of a quoted-printable text file! There is no end to it=";

        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test5() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "1+1 = 2!";
        String encoded = (String) qpcodec.encode((Object) plain);
        assertEquals("Basic quoted-printable encoding test (regress1)", 
            "1+1 =3D 2!", encoded);

        Object result = qpcodec.encode((Object) null);
        assertEquals( "Encoding a null Object should return null (regress1)", null, result);
    }
    @Test
    public void test6() throws Exception {
        String plain = "Hello, world!";
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("Unicode");
        qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
        String encoded1 = qpcodec.encode(plain, "Unicode");
        String encoded2 = qpcodec.encode(plain);
        assertEquals(encoded1, encoded2);
    }
    @Test
    public void test7() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "1+1 = 2!";
        String encoded = new String(QuotedPrintableCodec.
            encodeQuotedPrintable(null, plain.getBytes("UTF-8")));
        assertEquals("Basic quoted-printable encoding test (regress1)", 
            "1+1 =3D 2!", encoded);
        assertEquals("Basic quoted-printable decoding test (regress1)", 
            plain, qpcodec.decode(encoded));
        
    }
    @Test
    public void test8() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=,!$";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test (regress1)", 
            "=3D,!$", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test (regress1)", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test9() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello, world=";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test (regress1)", 
            "=3D Hello, world=3D", encoded);
        assertEquals("Basic quoted-printable decoding test (regress1)", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test10() throws Exception {

        String ru_msg = constructString(RUSSIAN_STUFF_UNICODE); 
        String ch_msg = constructString(SWISS_GERMAN_STUFF_UNICODE); 
        
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
    public void test11() throws Exception {
        String qpdata = "If you believe that truth=3Dbeauty, then surely mathematics is the most " +
                "b=";
        String expected = "If you believe that truthbeauty, then surely mathematics is the most " +
                "b=";

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
    }
    @Test
    public void test13() throws Exception {
        // whitespace, but does not need to be encoded
        String plain ="This is a example of a quoted=printable text file. There is no tt";
        String expected = "This is a example of a quoted=3Dprintable text file. There is no tt";

        assertEquals(expected, new QuotedPrintableCodec().encode(plain));
    }
    @Test
    public void test14() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="This is a example of a quoted-printable text file. This might contain sp=cial chars.";
        String expected = "This is a example of a quoted-printable text file. This might contain sp=3D=\r\ncial chars.";
        assertEquals(expected, qpcodec.encode(plain));

        plain ="This is a example of a quoted-printable text file. This might contain ta\tbs as well.";
        expected = "This is a example of a quoted-printable text file. This might contain ta=09=\r\nbs as well.";
        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test15() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        byte[] plain = null;
        byte[] encoded = qpcodec.encode(plain);
        assertEquals("Encoding a null string should return null", 
            null, encoded);
    }
    @Test
    public void test16() throws Exception {
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
        
        assertEquals(ru_msg, qpcodec.decode(qpcodec.encode(ru_msg, CharEncoding.UTF_8), CharEncoding.UTF_8));
        assertEquals(ch_msg, qpcodec.decode(qpcodec.encode(ch_msg, CharEncoding.UTF_8), CharEncoding.UTF_8));
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
        byte[] plain = null;
        byte[] result = QuotedPrintableCodec.decodeQuotedPrintable( plain );
        assertEquals("Result should be null", null, result);
    }
    @Test
    public void test26() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=3D=0D=0A", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test27() throws Exception {
        String qpdata = "CRLF in an\n encoded text should be=20=\r\n\rskipped in the\r decoding.";
        String expected = "CRLF in an encoded text should be skipped in the decoding.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(expected, qpcodec.decode(qpdata));

        String encoded = qpcodec.encode(expected);
        assertEquals(expected, qpcodec.decode(encoded));
    }
    @Test
    public void test28() throws Exception {
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
    public void test29() {
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
    public void test30() throws Exception {
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
    public void test31() throws Exception {
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
    public void test32() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there =\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Hello there =3D=0D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test33() throws Exception {

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
    public void test34() throws Exception {
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
    public void test35() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "";
        String encoded = qpcodec.encode(plain);
        assertEquals("Empty input quoted-printable encoding test", 
            "", encoded);
        assertEquals("Empty input quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test36() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "  \t \r\n ";
        String encoded = qpcodec.encode(plain);
        assertEquals("Whitespace input quoted-printable encoding test", 
            "  \t =20=0D=0A ", encoded);
        assertEquals("Whitespace input quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test37() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "1234567890";
        String encoded = qpcodec.encode(plain);
        assertEquals("Numbers input quoted-printable encoding test", 
            "1234567890", encoded);
        assertEquals("Numbers input quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test38() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertNull("Null string quoted-printable encoding test", 
            qpcodec.encode((String)null));
        assertNull("Null string quoted-printable decoding test", 
            qpcodec.decode((String)null));
        assertEquals("Reg test 1", qpcodec.encode(new byte[]{}), new byte[]{});
        assertEquals("Reg test 2", qpcodec.decode(new byte[]{}), new byte[]{});
    }
    @Test
    public void test39() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        assertEquals("Reg test 3", qpcodec.encode(plain.getBytes()), plain.getBytes());
        assertEquals("Reg test 4", qpcodec.decode(plain.getBytes()), plain.getBytes());
    }
    @Test
    public void test40() throws Exception {
        // whitespace, but does not need to be encoded
        String plain ="This is a example of a quoted=printable text file. There is no tt";
        String expected = "This is a example of a quoted=3Dprintable text file. There is no tt";

        assertEquals(expected, new QuotedPrintableCodec().encode(plain.getBytes()));
    }
    @Test
    public void test41() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="This is a example of a quoted-printable text file. This might contain sp=cial chars.";
        String expected = "This is a example of a quoted-printable text file. This might contain sp=3D=\r\ncial chars.";
        assertEquals(expected, qpcodec.encode(plain));

        plain ="This is a example of a quoted-printable text file. This might contain ta\tbs as well.";
        expected = "This is a example of a quoted-printable text file. This might contain ta=09=\r\nbs as well.";
        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test42() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        byte[] plain = null;
        byte[] encoded = qpcodec.encode(plain);
        assertEquals("Encoding a null string should return null", 
            null, encoded);
    }
    @Test
    public void test43() throws Exception {
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
    }
    @Test
    public void test44() throws Exception {
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
    public void test45() throws Exception {
        String plain = "Hello there!";
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("UnicodeBig");
        qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
        String encoded1 = qpcodec.encode(plain, "UnicodeBig");
        String encoded2 = qpcodec.encode(plain);
        assertEquals(encoded1, encoded2);
    }
    @Test
    public void test46() {
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
    public void test47() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String test = null;
        String result = qpcodec.encode( test, "charset" );
        assertEquals("Result should be null", null, result);
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
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there =\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Hello there =3D=0D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test50() throws Exception {

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
        byte[] byteRU = null;
        byte[] byteCH = null;
        try{
            byteRU = ru_msg.getBytes(Charset.forName("UTF-8"));
            byteCH = ch_msg.getBytes(Charset.forName("UTF-8"));
        } catch (Exception e) {}
        assertEquals("Reg test 5", qpcodec.decode(qpcodec.encode(byteRU), CharEncoding.UTF_8), byteRU);
        assertEquals("Reg test 6", qpcodec.decode(qpcodec.encode(byteCH), CharEncoding.UTF_8), byteCH);
    }
    @Test
    public void test51() throws Exception {
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
public void test52() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "";
    String encoded = qpcodec.encode(plain);
    assertEquals("Empty string quoted-printable encoding test", "", encoded);
    assertEquals("Empty string quoted-printable decoding test", plain, qpcodec.decode(encoded));
}
@Test
public void test53() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = " !\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";
    String encoded = qpcodec.encode(plain);
    assertEquals("Special chars quoted-printable encoding test", 
        "=20!=22#$%&'=28)=2A+,=2D./:;<=>?@[\\]^_=60=7B|}=7E", encoded);
}

@Test
public void test54() throws Exception {
}

@Test
public void test55() throws Exception {
}

    @Test
    public void test56() throws Exception {
    }
    @Test
    public void test57() throws Exception {
    }
    @Test
    public void test58() throws Exception {
    }
    @Test
    public void test59() throws Exception {
    }
    @Test
    public void test60() throws Exception {
    }
    @Test
    public void test61() throws Exception {
    }
    @Test
    public void test62() throws Exception {
        try {
            Object dObj = new Double(3.0);
            qpcodec.encode( dObj );
            fail("Trying to url encode a Double object should cause an exception.");
        } catch (EncoderException ee) {
            // Exception expected, test segment passes.
        }
    }
    @Test
    public void test63() throws Exception {
    }
    @Test
    public void test64() {
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
    public void test65() throws Exception {
    }
    @Test
    public void test66() throws Exception {
    }
    @Test
    public void test67() throws Exception {
    }
    @Test
    public void test68() throws Exception {
    }
    @Test
    public void test69() throws Exception {
    }

@Test
public void test70() throws Exception {
}

@Test
public void test71() throws Exception {
}

@Test
public void test72() throws Exception {
}

@Test
public void test73() throws Exception {
}

@Test
public void test74() throws Exception {
}

@Test
public void test75() {
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
public void test76() throws Exception {
}

@Test
public void test77() throws Exception {
    try {
        Object dObj = new Double(3.0);
        qpcodec.decode( dObj );
        fail( "Trying to url encode a Double object should cause an exception.");
    } catch (DecoderException ee) {
        // Exception expected, test segment passes.
    }
}

@Test
public void test78() throws Exception {
}

@Test
public void test79() throws Exception {
}

@Test
public void test80() throws Exception {
}

@Test
public void test81() throws Exception {
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
public void test82() throws Exception {
}

@Test
public void test83() throws Exception {
}

@Test
public void test84() throws Exception {
}

@Test
public void test85() throws Exception {
}

@Test
public void test86() throws Exception {
}

@Test
public void test87() {
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
public void test88() throws Exception {
}

@Test
public void test89() throws Exception {
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
}

@Test
public void test91() throws Exception {
}

@Test
public void test92() throws Exception {
}

@Test
public void test93() throws Exception {
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
}

@Test
public void test95() throws Exception {
    try {
        qpcodec.encode(plain, "INVALID_CHARSET");
        fail("EncoderException should have been thrown");
    } catch (EncoderException e) {
        // Expected. Move on
    }
    try {
        qpcodec.decode(plain, "INVALID CHARSET");
        fail("DecoderException should have been thrown");
    } catch (DecoderException e) {
        // Expected. Move on
    }
}

@Test
public void test96() throws Exception {
}

@Test
public void test97() throws Exception {
}

@Test
public void test98() throws Exception {
}

@Test
public void test99() throws Exception {
}

    @Test
    public void test100() throws Exception {
    }
    @Test
    public void test101() throws Exception {
    }
    @Test
    public void test102() throws Exception {
    }
    @Test
    public void test103() throws Exception {
    }
    @Test
    public void test104() throws Exception {
    }
    @Test
    public void test105() {
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
    public void test106() throws Exception {
    }
    @Test
    public void test107() throws Exception {
        try {
            Object dObj = new Double(3.0);
            qpcodec.decode( dObj );
            fail( "Trying to url encode a Double object should cause an exception.");
        } catch (DecoderException ee) {
            // Exception expected, test segment passes.
        }
    }
    @Test
    public void test108() throws Exception {
    }
    @Test
    public void test109() throws Exception {
    }
    @Test
    public void test110() throws Exception {
    }
    @Test
    public void test111() throws Exception {
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
    
    // Additional test cases
    @Test
    public void test112() throws Exception {
    }
    @Test
    public void test113() throws Exception {
    }
    @Test
    public void test114() throws Exception {
    }
    @Test
    public void test115() throws Exception {
    }

    @Test
    public void test116() throws Exception {
    }
    
    @Test
    public void test117() throws Exception {
    }
    
    @Test
    public void test118() throws Exception {
    }
    
    @Test
    public void test119() throws Exception {
    }
    
    @Test
    public void test120() throws Exception {
    }
    
    @Test
    public void test121() throws Exception {
    }

    @Test
    public void test122() throws Exception {
        try {
            Object dObj = new Double(3.0);
            qpcodec.encode( dObj );
            fail( "Trying to url encode a Double object should cause an exception.");
        } catch (EncoderException ee) {
            // Exception expected, test segment passes.
        }
    }

    @Test
    public void test123() throws Exception {
    }

    @Test
    public void test124() {
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
    public void test125() throws Exception {
    }
    
    @Test
    public void test126() throws Exception {
    }
    
    @Test
    public void test127() throws Exception {
    }
    
    @Test
    public void test128() throws Exception {
    }
    
    @Test
    public void test129() throws Exception {
    }
    
    // Regression test case
    @Test
    public void test130() throws Exception {
        try {
            Object obj = new Object();
            qpcodec.encode(obj);
            fail("Expected EncoderException to be thrown");
        } catch (EncoderException e) {
            // Exception expected, test passes
        }
    }
    
    // Regression test case
    @Test
    public void test131() throws Exception {
    }

    @Test
    public void test132() throws Exception {
    }
    
    @Test
    public void test133() throws Exception {
    }
    
    @Test
    public void test134() throws Exception {
    }
    
    @Test
    public void test135() throws Exception {
    }
    
    @Test
    public void test136() throws Exception {
    }
    
    @Test
    public void test137() throws Exception {
        try {
            qpcodec.decode(plain);
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
    }

    @Test
    public void test138() throws Exception {
    }
    @Test
    public void test139() throws Exception {
    }
    @Test
    public void test140() throws Exception {
    }

    @Test
    public void test141() throws Exception {
    }
    
    @Test
    public void test142() throws Exception {
    }
    
    @Test
    public void test143() throws Exception {
    }
    
    @Test
    public void test144() throws Exception {
    }
    
    @Test
    public void test145() throws Exception {
    }
    
    @Test
    public void test146() throws Exception {
    }
    
    @Test
    public void test147() throws Exception {
        try {
            Object dObj = new Double(3.0);
            qpcodec.encode( dObj );
            fail( "Trying to url encode a Double object should cause an exception.");
        } catch (EncoderException ee) {
            // Exception expected, test segment passes.
        }
    }
    
    @Test
    public void test148() throws Exception {
    }
    
    @Test
    public void test149() {
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
    public void test150() throws Exception {
    }
    
    @Test
    public void test151() throws Exception {
    }
    
    @Test
    public void test152() throws Exception {
    }
    
    @Test
    public void test153() throws Exception {
    }
    
    @Test
    public void test154() throws Exception {
    }

}