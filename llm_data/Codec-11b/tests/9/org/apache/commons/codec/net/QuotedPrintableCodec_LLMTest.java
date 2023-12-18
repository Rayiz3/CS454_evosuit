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
    }
    @Test
    public void test1() throws Exception {
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
    public void test2() throws Exception {
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
    public void test3() throws Exception {
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
    public void test4() throws Exception {
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
    public void test5() throws Exception {
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
    public void test6() throws Exception {
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
    public void test7() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there =\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Hello there =3D=0D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test8() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test9() throws Exception {
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
    public void test10() throws Exception {
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
    public void test11() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        byte[] plain = null;
        byte[] encoded = qpcodec.encode(plain);
        assertEquals("Encoding a null string should return null", 
            null, encoded);
    }
    @Test
    public void test12() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="This is a example of a quoted-printable text file. This might contain sp=cial chars.";
        String expected = "This is a example of a quoted-printable text file. This might contain sp=3D=\r\ncial chars.";
        assertEquals(expected, qpcodec.encode(plain));

        plain ="This is a example of a quoted-printable text file. This might contain ta\tbs as well.";
        expected = "This is a example of a quoted-printable text file. This might contain ta=09=\r\nbs as well.";
        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test13() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=3D=0D=0A", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test14() throws Exception {

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
    public void test15() throws Exception {
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
    public void test16() throws Exception {
        // whitespace, but does not need to be encoded
        String plain ="This is a example of a quoted=printable text file. There is no tt";
        String expected = "This is a example of a quoted=3Dprintable text file. There is no tt";

        assertEquals(expected, new QuotedPrintableCodec().encode(plain));
    }
    @Test
    public void test17() throws Exception {
        String plain = "Hello there!";
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("UnicodeBig");
        qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
        String encoded1 = qpcodec.encode(plain, "UnicodeBig");
        String encoded2 = qpcodec.encode(plain);
        assertEquals(encoded1, encoded2);
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
        
        // Regression test: Change the input value of the method
        plain = "= Goodbye =\r\n";
        encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Goodbye =3D=0D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test19() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "1+1 =3D 2";
        String decoded = (String) qpcodec.decode((Object) plain);
        assertEquals("Basic quoted-printable decoding test", 
            "1+1 = 2", decoded);
        
        // Regression test: Change the input value of the method
        plain = "2+2 =3D 4";
        decoded = (String) qpcodec.decode((Object) plain);
        assertEquals("Basic quoted-printable decoding test", 
            "2+2 = 4", decoded);

        byte[] plainBA = plain.getBytes("UTF-8");
        byte[] decodedBA = (byte[]) qpcodec.decode((Object) plainBA);
        decoded = new String(decodedBA);
        assertEquals("Basic quoted-printable decoding test", 
            "2+2 = 4", decoded);
            
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
    public void test20() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        
        // Regression test: Change the input value of the method
        plain = "xyz987_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test21() {
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
            
            // Regression test: Change the input value of the method
            qpcodec = new QuotedPrintableCodec("UNKNOWN");
            try {
                qpcodec.encode(plain);
                fail( "We set the encoding to a bogus UNKNOWN value, this shouldn't have worked.");
            } catch (EncoderException ee) {
                // Exception expected, test segment passes.
            }
            try {
                qpcodec.decode(plain);
                fail( "We set the encoding to a bogus UNKNOWN value, this shouldn't have worked.");
            } catch (DecoderException ee) {
                // Exception expected, test segment passes.
            }
            
    }
    @Test
    public void test22() throws Exception {
        String qpdata = "If you believe that truth=3Dbeauty, then surely=20=\r\nmathematics " +
                "is the most beautiful branch of philosophy.";
        String expected = "If you believe that truth=beauty, then surely mathematics " +
                "is the most beautiful branch of philosophy.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(expected, qpcodec.decode(qpdata));

        String encoded = qpcodec.encode(expected);
        assertEquals(expected, qpcodec.decode(encoded));
        
        // Regression test: Change the input value of the method
        qpdata = "If you believe that truth=3Dgoodness, then surely=20=\r\nmathematics " +
                "is the most beautiful branch of science.";
        expected = "If you believe that truth=goodness, then surely mathematics " +
                "is the most beautiful branch of science.";

        assertEquals(expected, qpcodec.decode(qpdata));

        encoded = qpcodec.encode(expected);
        assertEquals(expected, qpcodec.decode(encoded));
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
        
        // Regression test: Change the input value of the method
        plain = "=.\r\n";
        encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=3D.=0D=0A", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test24() throws Exception {

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
        
        // Regression test: Change the input value of the method
        ru_msg = constructString(RUSSIAN_STUFF_UNICODE_NEW); 
        ch_msg = constructString(SWISS_GERMAN_STUFF_UNICODE_NEW); 
        
        assertEquals(
            "=D0=98=D0=B3=D1=80=D0=B0_=D0=BF=D1=80=D0=B8=D0=BC=D0=B5=D1=80", 
        qpcodec.encode(ru_msg, CharEncoding.UTF_8)
        );
        assertEquals("Bla=C3=BC_zawies", qpcodec.encode(ch_msg, CharEncoding.UTF_8));
        
        assertEquals(ru_msg, qpcodec.decode(qpcodec.encode(ru_msg, CharEncoding.UTF_8), CharEncoding.UTF_8));
        assertEquals(ch_msg, qpcodec.decode(qpcodec.encode(ch_msg, CharEncoding.UTF_8), CharEncoding.UTF_8));
    }
    @Test
    public void test25() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "1+1 = 2";
        String encoded = new String(QuotedPrintableCodec.
            encodeQuotedPrintable(null, plain.getBytes("UTF-8")));
        assertEquals("Basic quoted-printable encoding test", 
            "1+1 =3D 2", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        
        // Regression test: Change the input value of the method
        plain = "5+5 = 10";
        encoded = new String(QuotedPrintableCodec.
            encodeQuotedPrintable(null, plain.getBytes("UTF-8")));
        assertEquals("Basic quoted-printable encoding test", 
            "5+5 =3D 10", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test26() throws Exception {
        byte[] plain = null;
        byte[] result = QuotedPrintableCodec.decodeQuotedPrintable( plain );
        assertEquals("Result should be null", null, result);
        
        // Regression test: Change the input value of the method
        byte[] plain2 = "test".getBytes("UTF-8");
        byte[] result2 = QuotedPrintableCodec.decodeQuotedPrintable( plain2 );
        assertEquals("Result should be the same as input", plain2, result2);
    }
    @Test
    public void test27() throws Exception {
        String qpdata = "CRLF in an\n encoded text should be=20=\r\n\rskipped in the\r decoding.";
        String expected = "CRLF in an encoded text should be skipped in the decoding.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(expected, qpcodec.decode(qpdata));

        String encoded = qpcodec.encode(expected);
        assertEquals(expected, qpcodec.decode(encoded));
        
        // Regression test: Change the input value of the method
        qpdata = "CRLF in an\n encoded text should be=20=\r\n\rskipped in the\r decoding test.";
        expected = "CRLF in an encoded text should be skipped in the decoding test.";

        assertEquals(expected, qpcodec.decode(qpdata));

        encoded = qpcodec.encode(expected);
        assertEquals(expected, qpcodec.decode(encoded));
    }
    @Test
    public void test28() throws Exception {
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

        // Regression test: Change the input value of the method
        try {
            qpcodec.decode("=AA");
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
        try {
            qpcodec.decode("=WWW");
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
    }
    @Test
    public void test29() throws Exception {
        String qpdata = "If you think that truth=3Dbeauty, then surely mathematics is the most " +
                "b=\r\neautiful branch of philosophy.";
        String expected = "If you think that truth=beauty, then surely mathematics is the most " +
                "beautiful branch of philosophy.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(qpdata, qpcodec.encode(expected));

        String decoded = qpcodec.decode(qpdata);
        assertEquals(qpdata, qpcodec.encode(decoded));
    }
    @Test
    public void test30() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertNull("Null string quoted-printable encoding test", 
            qpcodec.encode((String)null));
        assertNull("Null string quoted-printable decoding test", 
            qpcodec.decode((String)null));
    }
    @Test
    public void test31() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Greetings =\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Greetings =3D=0D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test32() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "xyz789_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test33() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="This is an example of a quoted-printable text file. There is no end to it\t";
        String expected = "This is an example of a quoted-printable text file. There is no end to i=\r\nt=09";

        assertEquals(expected, qpcodec.encode(plain));

        plain ="This is an example of a quoted-printable text file. There is no end to it ";
        expected = "This is an example of a quoted-printable text file. There is no end to i=\r\nt=20";

        assertEquals(expected, qpcodec.encode(plain));

        // whitespace before soft break
        plain ="This is an example of a quoted-printable text file. There is no end to   ";
        expected = "This is an example of a quoted-printable text file. There is no end to=20=\r\n =20";

        assertEquals(expected, qpcodec.encode(plain));

        // non-printable character before soft break
        plain ="This is an example of a quoted-printable text file. There is no end to=  ";
        expected = "This is an example of a quoted-printable text file. There is no end to=3D=\r\n =20";

        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test34() {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("NONSENSE");
           String plain = "Hello there, how are you?";
            try {
               qpcodec.encode(plain);
                fail( "We set the encoding to a bogus NONSENSE value, this should not have worked.");
            } catch (EncoderException ee) {
                // Exception expected, test segment passes.
            }
            try {
               qpcodec.decode(plain);
                fail( "We set the encoding to a bogus NONSENSE value, this should not have worked.");
            } catch (DecoderException ee) {
                // Exception expected, test segment passes.
            }
    }
    @Test
    public void test35() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "2+2 = 4";
        String encoded = (String) qpcodec.encode((Object) plain);
        assertEquals("Basic quoted-printable encoding test", 
            "2+2 =3D 4", encoded);

        byte[] plainBA = plain.getBytes("UTF-8");
        byte[] encodedBA = (byte[]) qpcodec.encode((Object) plainBA);
        encoded = new String(encodedBA);
        assertEquals("Basic quoted-printable encoding test", 
            "2+2 =3D 4", encoded);
            
        Object result = qpcodec.encode((Object) null);
        assertEquals( "Encoding a null Object should return null", null, result);
        
        try {
            Object dObj = new Double(4.0);
            qpcodec.encode( dObj );
            fail( "Trying to url encode a Double object should cause an exception.");
        } catch (EncoderException ee) {
            // Exception expected, test segment passes.
        }
    }
    @Test
    public void test36() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        byte[] plain = null;
        byte[] encoded = qpcodec.encode(plain);
        assertEquals("Encoding a null string should return null", 
            null, encoded);
    }
    @Test
    public void test37() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="This is an example of a quoted-printable text file. This might contain sp=cial chars.";
        String expected = "This is an example of a quoted-printable text file. This might contain sp=3D=\r\ncial chars.";
        assertEquals(expected, qpcodec.encode(plain));

        plain ="This is an example of a quoted-printable text file. This might contain ta\tbs as well.";
        expected = "This is an example of a quoted-printable text file. This might contain ta=09=\r\nbs as well.";
        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test38() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=3D=0D=0A", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test39() throws Exception {

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
    public void test40() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String test = null;
        String result = qpcodec.encode( test, "charset" );
        assertEquals("Result should be null", null, result);
    }
    @Test
    public void test41() throws Exception {
        // whitespace, but does not need to be encoded
        String plain ="This is an example of a quoted=printable text file. There is no tt";
        String expected = "This is an example of a quoted=3Dprintable text file. There is no tt";

        assertEquals(expected, new QuotedPrintableCodec().encode(plain));
    }
    @Test
    public void test42() throws Exception {
        String plain = "Hello there, how are you?";
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("UnicodeBig");
        qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
        String encoded1 = qpcodec.encode(plain, "UnicodeBig");
        String encoded2 = qpcodec.encode(plain);
        assertEquals(encoded1, encoded2);
    }
    @Test
    public void test43() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertNull("Null string quoted-printable encoding test", 
            qpcodec.encode((String)null));

        // Modified input: empty string
        assertNull("Null string quoted-printable decoding test", 
            qpcodec.decode(""));
    }
    @Test
    public void test44() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there =\r\n";

        // Modified input: encoded string with additional characters
        String encoded = qpcodec.encode(plain + " additional text");
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Hello there =3D=0D=0A additional text", encoded);

        // Modified input: decoded string with additional characters
        assertEquals("Basic quoted-printable decoding test", 
            plain + " additional text", qpcodec.decode(encoded));
    }
    @Test
    public void test45() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "1+1 =3D 2";

        // Modified input: decoded object with additional characters
        String decoded = (String) qpcodec.decode((Object) (plain + " additional text"));
        assertEquals("Basic quoted-printable decoding test", 
            "1+1 = 2 additional text", decoded);

        byte[] plainBA = plain.getBytes("UTF-8");
        byte[] decodedBA = (byte[]) qpcodec.decode((Object) plainBA);
        decoded = new String(decodedBA);
        assertEquals("Basic quoted-printable decoding test", 
            "1+1 = 2", decoded);
            
        // Modified input: null object
        Object result = qpcodec.decode((Object) null);
        assertEquals( "Decoding a null Object should return null", null, result);
        
        try {
            // Modified input: Double object
            Object dObj = new Double(3.0);
            qpcodec.decode( dObj );
            fail( "Trying to url encode a Double object should cause an exception.");
        } catch (DecoderException ee) {
            // Exception expected, test segment passes.
        }
    }
    @Test
    public void test46() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";

        // Modified input: encoded string with additional characters
        String encoded = qpcodec.encode(plain + " additional text");
        assertEquals("Safe chars quoted-printable encoding test", 
            plain + " additional text", encoded);

        // Modified input: decoded string with additional characters
        assertEquals("Safe chars quoted-printable decoding test", 
            plain + " additional text", qpcodec.decode(encoded));
    }
    @Test
    public void test47() {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("NONSENSE");
        String plain = "Hello there!";

        // Modified input: encoding set to empty string
        try {
            qpcodec.encode(plain);
            fail( "We set the encoding to a bogus NONSENSE value, this shouldn't have worked.");
        } catch (EncoderException ee) {
            // Exception expected, test segment passes.
        }

        // Modified input: decoding set to empty string
        try {
            qpcodec.decode(plain);
            fail( "We set the encoding to a bogus NONSENSE value, this shouldn't have worked.");
        } catch (DecoderException ee) {
            // Exception expected, test segment passes.
        }
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
    }
    @Test
    public void test49() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=\r\n";

        // Modified input: encoded string with additional characters
        String encoded = qpcodec.encode(plain + " additional text");
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=3D=0D=0A additional text", encoded);

        // Modified input: decoded string with additional characters
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain + " additional text", qpcodec.decode(encoded));
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
    }
    @Test
    public void test51() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "1+1 = 2";

        // Modified input: encoded string with additional characters
        String encoded = new String(QuotedPrintableCodec.
            encodeQuotedPrintable(null, (plain + " additional text").getBytes("UTF-8")));
        assertEquals("Basic quoted-printable encoding test", 
            "1+1 =3D 2 additional text", encoded);

        // Modified input: decoded string with additional characters
        assertEquals("Basic quoted-printable decoding test", 
            plain + " additional text", qpcodec.decode(encoded));
        
    }
    @Test
    public void test52() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String test = null;
        String result = qpcodec.decode( test, "charset" );
        assertEquals("Result should be null", null, result);
    }
    @Test
    public void test53() throws Exception {
        String qpdata = "CRLF in an\n encoded text should be=20=\r\n\rskipped in the\r decoding.";
        String expected = "CRLF in an encoded text should be skipped in the decoding.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(expected, qpcodec.decode(qpdata));

        String encoded = qpcodec.encode(expected);
        assertEquals(expected, qpcodec.decode(encoded));
    }
    @Test
    public void test54() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        try {
            // Modified input: encoded string with additional character
            qpcodec.decode("=" + " additional text");
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
        
        try {
            // Modified input: encoded string with incomplete encoding sequence
            qpcodec.decode("=A" + " additional text");
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
        
        try {
            // Modified input: encoded string with invalid encoding characters
            qpcodec.decode("=WW" + " additional text");
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
    }
    @Test
    public void test55() throws Exception {
        String qpdata = "";
        String expected = "";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(qpdata, qpcodec.encode(expected));

        String decoded = qpcodec.decode(qpdata);
        assertEquals(qpdata, qpcodec.encode(decoded));
    }
    @Test
    public void test56() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertFalse("Null string quoted-printable encoding test", 
            qpcodec.encode((String)null).isEmpty());
        assertFalse("Null string quoted-printable decoding test", 
            qpcodec.decode((String)null).isEmpty());
    }
    @Test
    public void test57() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there! =\r\n";
        String encoded = qpcodec.encode(plain);
        assertFalse("Basic quoted-printable encoding test", 
            encoded.isEmpty());
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test58() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test59() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="This is a example";
        String expected = "This is a example";

        assertEquals(expected, qpcodec.encode(plain));

        plain ="This is a example of a";
        expected = "This is a example of a";

        assertEquals(expected, qpcodec.encode(plain));

        // whitespace before soft break
        plain ="This is a example of a quoted-printable text file. There is no end to        ";
        expected = "This is a example of a quoted-printable text file. There is no end to=20=\r\n        ";

        assertEquals(expected, qpcodec.encode(plain));

        // non-printable character before soft break
        plain ="This is a example of a quoted-printable text file. There is no end to=     ";
        expected = "This is a example of a quoted-printable text file. There is no end to=3D=\r\n     ";

        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test60() {
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
    public void test61() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "1+1 = 2";
        String encoded = (String) qpcodec.encode((Object) plain);
        assertFalse("Basic quoted-printable encoding test", 
            encoded.isEmpty());

        byte[] plainBA = plain.getBytes("UTF-8");
        byte[] encodedBA = (byte[]) qpcodec.encode((Object) plainBA);
        encoded = new String(encodedBA);
        assertFalse("Basic quoted-printable encoding test", 
            encoded.isEmpty());
            
        Object result = qpcodec.encode((Object) null);
        assertNull( "Encoding a null Object should return null", result);
        
        try {
            Object dObj = new Double(3.0);
            qpcodec.encode( dObj );
            fail( "Trying to url encode a Double object should cause an exception.");
        } catch (EncoderException ee) {
            // Exception expected, test segment passes.
        }
    }
    @Test
    public void test62() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        byte[] plain = null;
        byte[] encoded = qpcodec.encode(plain);
        assertNull("Encoding a null string should return null", 
            encoded);
    }
    @Test
    public void test63() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="This is a example. This might contain sp=cial chars.";
        String expected = "This is a example. This might contain sp=3D=\r\ncial chars.";
        assertEquals(expected, qpcodec.encode(plain));

        plain ="This is a example. This might contain ta\tbs as well.";
        expected = "This is a example. This might contain ta=09=\r\nbs as well.";
        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test64() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=3D=0D=0A", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test65() throws Exception {

        String ru_msg = constructString(RUSSIAN_STUFF_UNICODE) + "c"; 
        String ch_msg = constructString(SWISS_GERMAN_STUFF_UNICODE);

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        assertFalse(qpcodec.encode(ru_msg, CharEncoding.UTF_8).isEmpty());
        assertFalse(qpcodec.encode(ch_msg, CharEncoding.UTF_8).isEmpty());
        
        assertFalse(qpcodec.decode(qpcodec.encode(ru_msg, CharEncoding.UTF_8), CharEncoding.UTF_8).isEmpty());
        assertFalse(qpcodec.decode(qpcodec.encode(ch_msg, CharEncoding.UTF_8), CharEncoding.UTF_8).isEmpty());
    }
    @Test
    public void test66() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String test = null;
        String result = qpcodec.encode( test, "charset" );
        assertNull("Result should be null", result);
    }
    @Test
    public void test67() throws Exception {
        // whitespace, but does not need to be encoded
        String plain ="This is a example of a quoted=printable text file. There is no tt";
        String expected = "This is a example of a quoted=3Dprintable text file. There is no tt";

        assertEquals(expected, new QuotedPrintableCodec().encode(plain));
    }
    @Test
    public void test68() throws Exception {
        String plain = "Hello there!";
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("UnicodeBig");
        qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
        String encoded1 = qpcodec.encode(plain, "UnicodeBig");
        String encoded2 = qpcodec.encode(plain);
        assertEquals(encoded1, encoded2);
    }
    @Test
    public void test69() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertNull("Null string quoted-printable encoding test", 
            qpcodec.encode((String)null));
        assertNull("Null string quoted-printable decoding test", 
            qpcodec.decode((String)null));
        qpcodec = new QuotedPrintableCodec("UTF-8");
        assertNull("Null string quoted-printable encoding test with specific charset", 
            qpcodec.encode((String)null));
        assertNull("Null string quoted-printable decoding test with specific charset", 
            qpcodec.decode((String)null));
    }
    @Test
    public void test70() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there =\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Hello there =3D=0D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        qpcodec = new QuotedPrintableCodec("UTF-8");
        encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test with specific charset", 
            "=3D Hello there =3D=0D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test with specific charset", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test71() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        
        // Test with plain string
        String plain = "1+1 =3D 2";
        String decoded = (String) qpcodec.decode((Object) plain);
        assertEquals("Basic quoted-printable decoding test", 
            "1+1 = 2", decoded);
        qpcodec = new QuotedPrintableCodec("UTF-8");
        decoded = (String) qpcodec.decode((Object) plain);
        assertEquals("Basic quoted-printable decoding test with specific charset", 
            "1+1 = 2", decoded);
            
        // Test with byte array
        byte[] plainBA = plain.getBytes("UTF-8");
        byte[] decodedBA = (byte[]) qpcodec.decode((Object) plainBA);
        decoded = new String(decodedBA);
        assertEquals("Basic quoted-printable decoding test", 
            "1+1 = 2", decoded);
        qpcodec = new QuotedPrintableCodec();
        decodedBA = (byte[]) qpcodec.decode((Object) plainBA);
        decoded = new String(decodedBA);
        assertEquals("Basic quoted-printable decoding test with specific charset", 
            "1+1 = 2", decoded);
            
        // Test with null object
        Object result = qpcodec.decode((Object) null);
        assertEquals( "Decoding a null Object should return null", null, result);
        qpcodec = new QuotedPrintableCodec("UTF-8");
        result = qpcodec.decode((Object) null);
        assertEquals( "Decoding a null Object should return null with specific charset", null, result);
        
        // Test with non-supported object
        try {
            Object dObj = new Double(3.0);
            qpcodec.decode( dObj );
            fail( "Trying to url encode a Double object should cause an exception.");
        } catch (DecoderException ee) {
            // Exception expected, test segment passes.
        }
        qpcodec = new QuotedPrintableCodec("UTF-8");
        try {
            Object dObj = new Double(3.0);
            qpcodec.decode( dObj );
            fail( "Trying to url encode a Double object should cause an exception with specific charset.");
        } catch (DecoderException ee) {
            // Exception expected, test segment passes.
        }
    }
    @Test
    public void test72() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        qpcodec = new QuotedPrintableCodec("UTF-8");
        encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test with specific charset", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test with specific charset", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test73() {
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
        qpcodec = new QuotedPrintableCodec("UTF-8");
        try {
               qpcodec.encode(plain);
                fail( "We set the encoding to a bogus NONSENSE value, this shouldn't have worked with specific charset.");
            } catch (EncoderException ee) {
                // Exception expected, test segment passes.
            }
            try {
               qpcodec.decode(plain);
                fail( "We set the encoding to a bogus NONSENSE value, this shouldn't have worked with specific charset.");
            } catch (DecoderException ee) {
                // Exception expected, test segment passes.
            }
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
        qpcodec = new QuotedPrintableCodec("UTF-8");
        assertEquals(expected, qpcodec.decode(qpdata));

        encoded = qpcodec.encode(expected);
        assertEquals(expected, qpcodec.decode(encoded));
    }
    @Test
    public void test75() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=3D=0D=0A", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        qpcodec = new QuotedPrintableCodec("UTF-8");
        encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test with specific charset", 
            "=3D=0D=0A", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test with specific charset", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test76() throws Exception {

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
        qpcodec = new QuotedPrintableCodec(CharEncoding.UTF_8);
        assertEquals(
            "=D0=92=D1=81=D0=B5=D0=BC_=D0=BF=D1=80=D0=B8=D0=B2=D0=B5=D1=82", 
        qpcodec.encode(ru_msg, CharEncoding.UTF_8)
        );
        assertEquals("Gr=C3=BCezi_z=C3=A4m=C3=A4", qpcodec.encode(ch_msg, CharEncoding.UTF_8));
        
        assertEquals(ru_msg, qpcodec.decode(qpcodec.encode(ru_msg, CharEncoding.UTF_8), CharEncoding.UTF_8));
        assertEquals(ch_msg, qpcodec.decode(qpcodec.encode(ch_msg, CharEncoding.UTF_8), CharEncoding.UTF_8));
    }
    @Test
    public void test77() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "1+1 = 2";
        String encoded = new String(QuotedPrintableCodec.
            encodeQuotedPrintable(null, plain.getBytes("UTF-8")));
        assertEquals("Basic quoted-printable encoding test", 
            "1+1 =3D 2", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        qpcodec = new QuotedPrintableCodec("UTF-8");
        encoded = new String(QuotedPrintableCodec.
            encodeQuotedPrintable(null, plain.getBytes("UTF-8")));
        assertEquals("Basic quoted-printable encoding test with specific charset", 
            "1+1 =3D 2", encoded);
        assertEquals("Basic quoted-printable decoding test with specific charset", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test78() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String test = null;
        String result = qpcodec.decode( test, "charset" );
        assertEquals("Result should be null", null, result);
        qpcodec = new QuotedPrintableCodec("UTF-8");
        result = qpcodec.decode( test, "charset" );
        assertEquals("Result should be null with specific charset", null, result);
    }
    @Test
    public void test79() throws Exception {
        String qpdata = "CRLF in an\n encoded text should be=20=\r\n\rskipped in the\r decoding.";
        String expected = "CRLF in an encoded text should be skipped in the decoding.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(expected, qpcodec.decode(qpdata));

        String encoded = qpcodec.encode(expected);
        assertEquals(expected, qpcodec.decode(encoded));
        qpcodec = new QuotedPrintableCodec("UTF-8");
        assertEquals(expected, qpcodec.decode(qpdata));

        encoded = qpcodec.encode(expected);
        assertEquals(expected, qpcodec.decode(encoded));
    }
    @Test
    public void test80() throws Exception {
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
        qpcodec = new QuotedPrintableCodec("UTF-8");
        try {
            qpcodec.decode("=");
            fail("DecoderException should have been thrown with specific charset");
        } catch (DecoderException e) {
            // Expected. Move on
        }
        try {
            qpcodec.decode("=A");
            fail("DecoderException should have been thrown with specific charset");
        } catch (DecoderException e) {
            // Expected. Move on
        }
        try {
            qpcodec.decode("=WW");
            fail("DecoderException should have been thrown with specific charset");
        } catch (DecoderException e) {
            // Expected. Move on
        }
    }
    @Test
    public void test81() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "";
        String encoded = qpcodec.encode(plain);
        assertEquals("Empty string quoted-printable encoding test", 
            "", encoded);
        assertEquals("Empty string quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test82() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "Hello$Friends!";
        String encoded = qpcodec.encode(plain);
        assertEquals("Special chars quoted-printable encoding test", 
            "Hello$=46riends!", encoded);
        assertEquals("Special chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test83() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "";
        for (int i=0; i<100000; i++) {
            plain += "a";
        }
        String encoded = qpcodec.encode(plain);
        assertEquals("Long string quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Long string quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test84() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertNull("Null object encoding", qpcodec.encode((Object) null));
    }
    @Test
    public void test85() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "";
        String encoded = qpcodec.encode(plain);
        assertEquals("Empty string encoding test", "", encoded);
        assertEquals("Empty string decoding test", plain, qpcodec.decode(encoded));
    }
    @Test
    public void test86() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[] ";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars with space encoding test",
                "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]",
                encoded);
        assertEquals("Safe chars with space decoding test",
                plain,
                qpcodec.decode(encoded));
    }
    @Test
    public void test87() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="                    ";
        String expected = "=\r\n";
        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test88() {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("NONSENSE");
        String plain = "Hello there! ";
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
    public void test89() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        Object result = qpcodec.encode((Object) null);
        assertEquals( "Encoding a null Object should return null", null, result);
    }
    @Test
    public void test90() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        byte[] plain = null;
        byte[] encoded = qpcodec.encode(plain);
        assertEquals("Encoding a null byte array should return null",
                null, encoded);
    }
    @Test
    public void test91() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="This is a example of a quoted-printable text file. This might contain sp=cial chars. ";
        String expected = "This is a example of a quoted-printable text file. This might contain sp=3Dcial chars.";
        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test92() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=\r\n ";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars with space encoding test",
                "=3D=0D=0A",
                encoded);
        assertEquals("Unsafe chars with space decoding test",
                plain,
                qpcodec.decode(encoded));
    }
    @Test
    public void test93() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        assertEquals("", qpcodec.encode("", CharEncoding.UTF_8));
        assertEquals("", qpcodec.decode("", CharEncoding.UTF_8));
    }
    @Test
    public void test94() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String test = "Hello there!";
        String result = qpcodec.encode(test, null);
        assertEquals("Result should be the same as original string", test, result);
    }
    @Test
    public void test95() throws Exception {
        String plain ="This is a example of a quoted=printable text file. There is no tt ";
        String expected = "This is a example of a quoted=3Dprintable text file. There is no tt";

        assertEquals(expected, new QuotedPrintableCodec().encode(plain));
    }
    @Test
    public void test96() throws Exception {
        String plain = "Hello there! ";
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("UnicodeBig");
        qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
        String encoded1 = qpcodec.encode(plain, "UnicodeBig");
        String encoded2 = qpcodec.encode(plain);
        assertEquals(encoded1, encoded2);
    }
    @Test
    public void test97() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "Hello\nthere!";
        String encoded = qpcodec.encode(plain);
        assertEquals("Encoding newline character test",
                "Hello=0D=0Athere!",
                encoded);
        assertEquals("Decoding newline character test",
                plain,
                qpcodec.decode(encoded));
    }
    @Test
    public void test98() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "Hello\rthere!";
        String encoded = qpcodec.encode(plain);
        assertEquals("Encoding carriage return character test",
                "Hello=0Dthere!",
                encoded);
        assertEquals("Decoding carriage return character test",
                plain,
                qpcodec.decode(encoded));
    }
    @Test
    public void test99() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "Hello\\there!";
        String encoded = qpcodec.encode(plain);
        assertEquals("Encoding backslash character test",
                "Hello\\=5Cthere!",
                encoded);
        assertEquals("Decoding backslash character test",
                plain,
                qpcodec.decode(encoded));
    }
    @Test
    public void test100() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "Hello=there!";
        String encoded = qpcodec.encode(plain);
        assertEquals("Encoding equals character test",
                "Hello=3Dthere!",
                encoded);
        assertEquals("Decoding equals character test",
                plain,
                qpcodec.decode(encoded));
    }
    @Test
    public void test101() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "Hello\tthere!";
        String encoded = qpcodec.encode(plain);
        assertEquals("Encoding tab character test",
                "Hello=09there!",
                encoded);
        assertEquals("Decoding tab character test",
                plain,
                qpcodec.decode(encoded));
    }
    @Test
    public void test102() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "Hello there!";
        String encoded = qpcodec.encode(plain);
        assertEquals("Encoding space character test",
                plain,
                encoded);
        assertEquals("Decoding space character test",
                plain,
                qpcodec.decode(encoded));
    }
    @Test
    public void test103() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "Hello there!\n\t";
        String encoded = qpcodec.encode(plain);
        assertEquals("Encoding special characters test",
                "Hello there!=0D=0A=09",
                encoded);
        assertEquals("Decoding special characters test",
                plain,
                qpcodec.decode(encoded));
    }
    @Test
    public void test104() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "Hello 你好!";
        String encoded = qpcodec.encode(plain);
        assertEquals("Encoding unicode characters test",
                plain,
                encoded);
        assertEquals("Decoding unicode characters test",
                plain,
                qpcodec.decode(encoded));
    }
    @Test
    public void test105() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertNull("Null string quoted-printable encoding test", 
            qpcodec.encode((String)null));
        assertNull("Null string quoted-printable decoding test", 
            qpcodec.decode((String)null));
        
        // Regression test case: Empty string
        String emptyString = "";
        assertEquals("Empty string quoted-printable encoding test", 
            emptyString, qpcodec.encode(emptyString));
        assertEquals("Empty string quoted-printable decoding test", 
            emptyString, qpcodec.decode(emptyString));
    }
    @Test
    public void test106() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there =\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Hello there =3D=0D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        
        // Regression test case: String without special characters
        String plainWithoutSpecialChars = "Hello World";
        String encodedWithoutSpecialChars = qpcodec.encode(plainWithoutSpecialChars);
        assertEquals("String without special characters quoted-printable encoding test", 
            plainWithoutSpecialChars, encodedWithoutSpecialChars);
        assertEquals("String without special characters quoted-printable decoding test", 
            plainWithoutSpecialChars, qpcodec.decode(encodedWithoutSpecialChars));
    }
    @Test
    public void test107() throws Exception {
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
        
        // Regression test case: Object of a different type
        Integer intObj = new Integer(10);
        try {
            qpcodec.decode(intObj);
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
    }
    @Test
    public void test108() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        
        // Regression test case: String with only one character
        String singleCharString = "a";
        String encodedSingleCharString = qpcodec.encode(singleCharString);
        assertEquals("Single character quoted-printable encoding test", 
            singleCharString, encodedSingleCharString);
        assertEquals("Single character quoted-printable decoding test", 
            singleCharString, qpcodec.decode(encodedSingleCharString));
    }
    @Test
    public void test109() {
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
            
         // Regression test case: Invalid encoding value
        String invalidEncoding = "INVALID";
        try {
            new QuotedPrintableCodec(invalidEncoding);
            fail("EncoderException should have been thrown");
        } catch (EncoderException e) {
            // Expected. Move on
        }
        try {
            new QuotedPrintableCodec(invalidEncoding);
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
    }
    @Test
    public void test110() throws Exception {
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
    public void test111() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=3D=0D=0A", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
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
    }
    @Test
    public void test113() throws Exception {
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
    public void test114() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String test = null;
        String result = qpcodec.decode( test, "charset" );
        assertEquals("Result should be null", null, result);
    }
    @Test
    public void test115() throws Exception {
        String qpdata = "CRLF in an\n encoded text should be=20=\r\n\rskipped in the\r decoding.";
        String expected = "CRLF in an encoded text should be skipped in the decoding.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(expected, qpcodec.decode(qpdata));

        String encoded = qpcodec.encode(expected);
        assertEquals(expected, qpcodec.decode(encoded));
    }
    @Test
    public void test116() throws Exception {
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
    public void test117() {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "";
        String expected = "";

        assertEquals(expected, qpcodec.encode(plain));
        assertEquals(expected, qpcodec.decode(plain));
    }
    @Test
    public void test118() {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "!@#$%^&*()_+{}|:\"<>?";
        String expected = "=21=40=23=24=25=5E=26=2A=28=29=5F=2B=7B=7D=7C=3A=22=3C=3E=3F";

        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test119() {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String qpdata = "=20=21=22=23=2A=2B=2C=2D=2E=2F";
        String expected = " !=#*+,-./";

        assertEquals(expected, qpcodec.decode(qpdata));
    }
    @Test
    public void test120() {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = null;

        assertNull(qpcodec.encode(plain));
        assertNull(qpcodec.decode(plain));
    }
    @Test
    public void test121() {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "     ";
        String expected = "=20=20=20=20=20";

        assertEquals(expected, qpcodec.encode(plain));
        assertEquals(plain, qpcodec.decode(expected));
    }
@Test
public void test122() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "= Hello there =\r\nHello";
    String encoded = qpcodec.encode(plain);
    assertEquals("Basic quoted-printable encoding regression test", 
        "=3D Hello there =3D=0D=0AHello", encoded);
    assertEquals("Basic quoted-printable decoding regression test", 
        plain, qpcodec.decode(encoded));
}
@Test
public void test123() throws Exception {
    String qpdata = "If you believe that truth=3Dbeauty, then surely mathematics is the most " +
            "b=\r\neautiful branch of philosophy.\r\nHello";
    String expected = "If you believe that truth=beauty, then surely mathematics is the most " +
            "beautiful branch of philosophy.\r\nHello";

    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    assertEquals(qpdata, qpcodec.encode(expected));
    assertEquals(expected, qpcodec.decode(qpdata));
}
@Test
public void test124() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]Hello";
    String encoded = qpcodec.encode(plain);
    assertEquals("Safe chars quoted-printable encoding regression test", 
        plain, encoded);
    assertEquals("Safe chars quoted-printable decoding regression test", 
        plain, qpcodec.decode(encoded));
}
@Test
public void test125() throws Exception {
    final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

    String plain ="This is a example of a quoted-printable text file. There is no end to it\tHello";
    String expected = "This is a example of a quoted-printable text file. There is no end to i=\n=t=09Hello";

    assertEquals(expected, qpcodec.encode(plain));

    plain ="This is a example of a quoted-printable text file. There is no end to it ";
    expected = "This is a example of a quoted-printable text file. There is no end to i=\n=t=20Hello";

    assertEquals(expected, qpcodec.encode(plain));

    // whitespace before soft break
    plain ="This is a example of a quoted-printable text file. There is no end to   ";
    expected = "This is a example of a quoted-printable text file. There is no end to=20=\n=20Hello";

    assertEquals(expected, qpcodec.encode(plain));

    // non-printable character before soft break
    plain ="This is a example of a quoted-printable text file. There is no end to=  ";
    expected = "This is a example of a quoted-printable text file. There is no end to=3D=\n=20Hello";

    assertEquals(expected, qpcodec.encode(plain));
}
@Test
public void test126() throws Exception {
    final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

    String plain ="This is a example of a quoted-printable text file. This might contain sp=cial chars. Hello";
    String expected = "This is a example of a quoted-printable text file. This might contain sp=3D=\n=cial chars. Hello";
    assertEquals(expected, qpcodec.encode(plain));

    plain ="This is a example of a quoted-printable text file. This might contain ta\tbs as well. Hello";
    expected = "This is a example of a quoted-printable text file. This might contain ta=09=\nbs as well. Hello";
    assertEquals(expected, qpcodec.encode(plain));
}
@Test
public void test127() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "=\r\nHello";
    String encoded = qpcodec.encode(plain);
    assertEquals("Unsafe chars quoted-printable encoding regression test", 
        "=3D=0D=0AHello", encoded);
    assertEquals("Unsafe chars quoted-printable decoding regression test", 
        plain, qpcodec.decode(encoded));
}
@Test
public void test128() throws Exception {
    String ru_msg = constructString(RUSSIAN_STUFF_UNICODE); 
    String ch_msg = constructString(SWISS_GERMAN_STUFF_UNICODE); 
    
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    
    assertEquals(
        "=D0=92=D1=81=D0=B5=D0=BC_=D0=BF=D1=80=D0=B8=D0=B2=D0=B5=D1=82Hello", 
    qpcodec.encode(ru_msg + "Hello", CharEncoding.UTF_8)
    );
    assertEquals("Gr=C3=BCezi_z=C3=A4m=C3=A4Hello", qpcodec.encode(ch_msg + "Hello", CharEncoding.UTF_8));
    
    assertEquals(ru_msg, qpcodec.decode(qpcodec.encode(ru_msg, CharEncoding.UTF_8), CharEncoding.UTF_8));
    assertEquals(ch_msg, qpcodec.decode(qpcodec.encode(ch_msg, CharEncoding.UTF_8), CharEncoding.UTF_8));
}
@Test
public void test129() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String test = null;
    String result = qpcodec.encode( test, "charset" );
    assertEquals("Result should be null", null, result);
}
@Test
public void test130() throws Exception {
    // whitespace, but does not need to be encoded
    String plain ="This is a example of a quoted=printable text file. There is no ttHello";
    String expected = "This is a example of a quoted=3Dprintable text file. There is no ttHello";

    assertEquals(expected, new QuotedPrintableCodec().encode(plain));
}
@Test
public void test131() throws Exception {
    String plain = "Hello there!";
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("UnicodeBig");
    qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
    String encoded1 = qpcodec.encode(plain, "UnicodeBig");
    String encoded2 = qpcodec.encode(plain);
    assertEquals(encoded1, encoded2);
}
}