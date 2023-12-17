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
    public void test1() throws Exception {
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
    public void test2() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="This is an example of a quoted-printable text file. This might contain sp=cial chars.";
        String expected = "This is an example of a quoted-printable text file. This might contain sp=3D=\r\ncial chars.";
        assertEquals(expected, qpcodec.encode(plain));

        plain ="This is an example of a quoted-printable text file. This might contain ta\tbs as well.";
        expected = "This is an example of a quoted-printable text file. This might contain ta=09=\r\nbs as well.";
        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test3() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=3D=0D=0A", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test4() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test5() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "2+2 = 4";
        String encoded = new String(QuotedPrintableCodec.
            encodeQuotedPrintable(null, plain.getBytes("UTF-8")));
        assertEquals("Basic quoted-printable encoding test", 
            "2+2 =3D 4", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        
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
    public void test9() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        byte[] plain = null;
        byte[] encoded = qpcodec.encode(plain);
        assertEquals("Encoding a null string should return null", 
            null, encoded);
    }
    @Test
    public void test10() throws Exception {
        // whitespace, but does not need to be encoded
        String plain ="This is an example of a quoted=printable text file. There is no tt";
        String expected = "This is an example of a quoted=3Dprintable text file. There is no tt";

        assertEquals(expected, new QuotedPrintableCodec().encode(plain));
    }
    @Test
    public void test11() throws Exception {
        String plain = "Hello there!";
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("UnicodeBig");
        qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
        String encoded1 = qpcodec.encode(plain, "UnicodeBig");
        String encoded2 = qpcodec.encode(plain);
        assertEquals(encoded1, encoded2);
    }

    @Test
    public void test12() throws Exception {
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
    public void test13() throws Exception {
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
        String plain = "=\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=3D=0D=0A", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test16() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test17() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "2+2 = 4";
        String encoded = new String(QuotedPrintableCodec.
            encodeQuotedPrintable(null, plain.getBytes("UTF-8")));
        assertEquals("Basic quoted-printable encoding test", 
            "2+2 =3D 4", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        
    }
    @Test
    public void test18() throws Exception {
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
    public void test19() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there =\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Hello there =3D=0D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test20() throws Exception {

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
    public void test21() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        byte[] plain = null;
        byte[] encoded = qpcodec.encode(plain);
        assertEquals("Encoding a null string should return null", 
            null, encoded);
    }
    @Test
    public void test22() throws Exception {
        // whitespace, but does not need to be encoded
        String plain ="This is a example of a quoted=printable text file. There is no tt";
        String expected = "This is a example of a quoted=3Dprintable text file. There is no tt";

        assertEquals(expected, new QuotedPrintableCodec().encode(plain));
    }
    @Test
    public void test23() throws Exception {
        String plain = "Hello there!";
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("UnicodeBig");
        qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
        String encoded1 = qpcodec.encode(plain, "UnicodeBig");
        String encoded2 = qpcodec.encode(plain);
        assertEquals(encoded1, encoded2);
    }

    @Test
    public void test24() throws Exception {
        byte[] plain = new byte[0];
        byte[] result = QuotedPrintableCodec.decodeQuotedPrintable( plain );
        assertEquals("Result should be an empty array", 0, result.length);
    }
    
    @Test
    public void test25() throws Exception {
        byte[] plain = {65};
        byte[] result = QuotedPrintableCodec.decodeQuotedPrintable( plain );
        assertEquals("Result should be the same as input", plain, result);
    }
    
    @Test
    public void test26() throws Exception {
        String qpdata = "=ZZ"; 
        try {
            byte[] result = QuotedPrintableCodec.decodeQuotedPrintable(qpdata.getBytes());
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
    }
    
    @Test
    public void test27() throws Exception {
        String qpdata = "=3C=3E=22"; 
        byte[] result = QuotedPrintableCodec.decodeQuotedPrintable(qpdata.getBytes());
        assertEquals("Result should be the same as input", qpdata.getBytes(), result);
    }
    
    @Test
    public void test28() throws Exception {
        String qpdata = "Hello=0D=0AWorld!"; 
        byte[] result = QuotedPrintableCodec.decodeQuotedPrintable(qpdata.getBytes());
        assertEquals("Result should be \"Hello World!\"", "Hello World!", new String(result));
    }
    
    @Test
    public void test29() throws Exception {
        String qpdata = "Invalid encoding test=GG"; 
        try {
            byte[] result = QuotedPrintableCodec.decodeQuotedPrintable(qpdata.getBytes());
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
    }
    
    @Test
    public void test30() throws Exception {
        byte[] plain = "".getBytes();
        byte[] result = QuotedPrintableCodec.decodeQuotedPrintable( plain );
        assertEquals("Result should be an empty array", 0, result.length);
    }
    
    @Test
    public void test31() throws Exception {
        String qpdata = "=5E=23=2B"; 
        byte[] result = QuotedPrintableCodec.decodeQuotedPrintable(qpdata.getBytes());
        assertEquals("Result should be \"^#+\"", "^#+", new String(result));
    }
    
    @Test
    public void test32() throws Exception {
        byte[] plain = null;
        byte[] result = QuotedPrintableCodec.decodeQuotedPrintable( plain );
        assertEquals("Result should be null", null, result);
    }
    
    @Test
    public void test33() throws Exception {
        String qpdata = "This is a test="; 
        byte[] result = QuotedPrintableCodec.decodeQuotedPrintable(qpdata.getBytes());
        assertEquals("Result should be the same as input", qpdata.getBytes(), result);
    }
    
    @Test
    public void test34() throws Exception {
        String qpdata = "Invalid encoded chars=8G"; 
        try {
            byte[] result = QuotedPrintableCodec.decodeQuotedPrintable(qpdata.getBytes());
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
    }
    
    @Test
    public void test35() throws Exception {
        byte[] plain = null;
        byte[] result = QuotedPrintableCodec.decodeQuotedPrintable( plain );
        assertEquals("Result should be null", null, result);
    }
    
    @Test
    public void test36() throws Exception {
        byte[] plain = new byte[0];
        byte[] result = QuotedPrintableCodec.decodeQuotedPrintable( plain );
        assertEquals("Result should be an empty array", 0, result.length);
    }
    
    @Test
    public void test37() throws Exception {
        String qpdata = "=XX"; 
        try {
            byte[] result = QuotedPrintableCodec.decodeQuotedPrintable(qpdata.getBytes());
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
    }
    
    @Test
    public void test38() throws Exception {
        String qpdata = "=21=22=23=24"; 
        byte[] result = QuotedPrintableCodec.decodeQuotedPrintable(qpdata.getBytes());
        assertEquals("Result should be \"!\"\"#$\"", "!\"\"#$", new String(result));
    }
    
    @Test
    public void test39() throws Exception {
        String qpdata = "This is a test=";
        byte[] result = QuotedPrintableCodec.decodeQuotedPrintable(qpdata.getBytes());
        assertEquals("Result should be the same as input", qpdata.getBytes(), result);
    }

    @Test
    public void test40() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="This is a example of a quoted-printable text file. There is no end to it\t";
        String expected = "This is a example of a quoted-printable text file. There is no end to i=\r\nt=09";

        assertEquals(expected, qpcodec.encode(plain));

        // Regression test 1
        plain ="This is a new regression test 1\t";
        expected = "This is a new regression test 1=\r\nt=09";

        assertEquals(expected, qpcodec.encode(plain));

        plain ="This is a example of a quoted-printable text file. There is no end to it ";
        expected = "This is a example of a quoted-printable text file. There is no end to i=\r\nt=20";

        assertEquals(expected, qpcodec.encode(plain));

        // Regression test 2
        plain ="This is a new regression test 2 ";
        expected = "This is a new regression test 2=\r\nt=20";

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
    public void test41() throws Exception {
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
            
        // Regression test 3
        String nullString = null;
        Object result = qpcodec.encode((Object) nullString);
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
    public void test42() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="This is a example of a quoted-printable text file. This might contain sp=cial chars.";
        String expected = "This is a example of a quoted-printable text file. This might contain sp=3D=\r\ncial chars.";
        assertEquals(expected, qpcodec.encode(plain));

        plain ="This is a example of a quoted-printable text file. This might contain ta\tbs as well.";
        expected = "This is a example of a quoted-printable text file. This might contain ta=09=\r\nbs as well.";
        assertEquals(expected, qpcodec.encode(plain));

        // Regression test 4
        plain = "New regression test 4=";
        expected = "New regression test 4=3D=\r\n";
        assertEquals(expected, qpcodec.encode(plain));
    }

    @Test
    public void test43() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=3D=0D=0A", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }

    @Test
    public void test44() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertNull("Null string quoted-printable encoding test", 
            qpcodec.encode((String)null));
        assertNull("Null string quoted-printable decoding test", 
            qpcodec.decode((String)null));
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
        String plain = "= Hello there =\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Hello there =3D=0D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }

    @Test
    public void test48() throws Exception {

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
    public void test49() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        byte[] plain = null;
        byte[] encoded = qpcodec.encode(plain);
        assertEquals("Encoding a null string should return null", 
            null, encoded);
    }

    @Test
    public void test50() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String test = null;
        String result = qpcodec.encode( test, "charset" );
        assertEquals("Result should be null", null, result);
    }

    @Test
    public void test51() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }

    @Test
    public void test52() throws Exception {
        String plain = "Hello there!";
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("UnicodeBig");
        qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
        String encoded1 = qpcodec.encode(plain, "UnicodeBig");
        String encoded2 = qpcodec.encode(plain);
        assertEquals(encoded1, encoded2);
    }

    @Test
    public void test53() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        byte[] test = new byte[0];
        byte[] result = qpcodec.decode(test);
        assertArrayEquals("Result should be an empty array", new byte[0], result);
    }
    
    @Test
    public void test54() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        byte[] test = "Hello %20World".getBytes();
        byte[] result = qpcodec.decode(test);
        assertArrayEquals("Result should be 'Hello World'", "Hello World".getBytes(), result);
    }
    
    @Test
    public void test55() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        byte[] test = "Bj%C3%B6rk".getBytes();
        byte[] result = qpcodec.decode(test);
        assertArrayEquals("Result should be 'Björk'", "Björk".getBytes(), result);
    }
    
    @Test
    public void test56() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        byte[] test = null;
        byte[] result = qpcodec.decode(test);
        assertEquals("Result should be null", null, result);
    }
    
    @Test
    public void test57() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        byte[] test = "".getBytes();
        byte[] result = qpcodec.decode(test);
        assertArrayEquals("Result should be an empty array", new byte[0], result);
    }
    
    @Test
    public void test58() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        byte[] test = new byte[1000000];
        Arrays.fill(test, (byte) 'A');
        byte[] result = qpcodec.decode(test);
        assertArrayEquals("Result should be an array of 'A's", test, result);
    }
    
    @Test
    public void test59() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("INVALID_ENCODING");
        byte[] test = "Hello".getBytes();
        try {
            byte[] result = qpcodec.decode(test);
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Exception expected, test passes
        }
    }

    @Test
    public void test60() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String test = null;
        String result = qpcodec.decode( test, "charset" );
        assertEquals("Result should be null", null, result);
    }
    
    @Test
    public void test61() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String test = "";
        String result = qpcodec.decode( test, "charset" );
        assertEquals("Result should be an empty string", "", result);
    }
    
    @Test
    public void test62() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String test = "=C3=A9"; // encoded representation of é
        String result = qpcodec.decode( test, "utf-8" );
        assertEquals("Result should be é", "é", result);
    }
    
    @Test
    public void test63() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String test = "=41=42=43"; // encoded representation of ABC
        try {
            qpcodec.decode( test, "invalidcharset" );
            fail("UnsupportedEncodingException should have been thrown");
        } catch (UnsupportedEncodingException e) {
            // Expected. Move on
        }
    }
    
    @Test
    public void test64() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String test = "=XY"; // invalid encoded representation
        try {
            qpcodec.decode( test, "utf-8" );
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
    }
    
    @Test
    public void test65() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String test = "=41=42=43"; // encoded representation of ABC
        try {
            qpcodec.decode( test, null );
            fail("UnsupportedEncodingException should have been thrown");
        } catch (UnsupportedEncodingException e) {
            // Expected. Move on
        }
    }

    @Test
    public void test66() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String test = "";
        String result = qpcodec.decode( test, "charset" );
        assertEquals("Result should be an empty string", "", result);
    }

    @Test
    public void test67() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String test = "This is a test message";
        String result = qpcodec.decode( test, "charset" );
        assertEquals("Result should be the same as the original string", test, result);
    }

    @Test
    public void test68() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String test = "This=20is=20a=20test=20message";
        String result = qpcodec.decode( test, "charset" );
        assertEquals("Result should be the decoded string", "This is a test message", result);
    }

    @Test
    public void test69() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String test = "This is a test message";
        try{
            String result = qpcodec.decode( test, "nonexistentCharset" );
            fail("The method should thow a DecoderException");
        }catch(DecoderException e){
            assertEquals("Exception message should be correct", "unsupportedCharset", e.getMessage());
        }
    }

    @Test
    public void test70() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        // Regression Test Case 1: Empty string
        String plain = "";
        String expected = "";
        assertEquals(expected, qpcodec.encode(plain));

        // Regression Test Case 2: String with only whitespace characters
        plain ="    ";
        expected = "=20=20=20=20";
        assertEquals(expected, qpcodec.encode(plain));

        // Regression Test Case 3: String with non-printable characters
        plain ="This is a example of a quoted-printable text file. There is no end to=  ";
        expected = "This is a example of a quoted-printable text file. There is no end to=3D=\r\n =20";
        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test71() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "1+1 = 2";

        // Regression Test Case 1: Object is a StringBuilder instead of String
        StringBuilder sb = new StringBuilder(plain);
        String expected = "1+1 =3D 2";
        assertEquals(expected, qpcodec.encode(sb));

        // Regression Test Case 2: Object is an Integer instead of String
        Integer integer = Integer.valueOf(plain);
        assertEquals(expected, qpcodec.encode(integer));
    }
    @Test
    public void test72() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        // Regression Test Case 1: Special characters at the end of the string
        String plain ="This is a example of a quoted-printable text file. This might contain sp=cial chars./";
        String expected = "This is a example of a quoted-printable text file. This might contain sp=3D=\r\ncial chars./";
        assertEquals(expected, qpcodec.encode(plain));

        // Regression Test Case 2: Tab characters at the end of the string
        plain ="This is a example of a quoted-printable text file. This might contain ta\tbs as well.\t";
        expected = "This is a example of a quoted-printable text file. This might contain ta=09=\r\nbs as well.=09";
        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test73() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=\r\n";
        String encoded = qpcodec.encode(plain);

        // Regression Test Case 1: Unsafe character = at the beginning of the string
        String unsafeExpected = "=3D=0D=0A";
        assertEquals("Unsafe chars quoted-printable encoding test", unsafeExpected, encoded);

        // Regression Test Case 2: Unsafe character = at the end of the string
        assertEquals("Unsafe chars quoted-printable decoding test", plain, qpcodec.decode(unsafeExpected));
    }
    @Test
    public void test74() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        // Regression Test Case 1: Null string
        String plain = null;
        String expected = null;
        assertNull("Null string quoted-printable encoding test", qpcodec.encode(plain));
        assertNull("Null string quoted-printable decoding test", qpcodec.decode(expected));
    }
    @Test
    public void test75() throws Exception {
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
    public void test76() {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("NONSENSE");

        // Regression Test Case 1: Encoding is set to an invalid value
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
    public void test77() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there =\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test",
                "=3D Hello there =3D=0D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test",
                plain, qpcodec.decode(encoded));
    }
    @Test
    public void test78() throws Exception {

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
    public void test79() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        byte[] plain = null;
        byte[] encoded = qpcodec.encode(plain);

        // Regression Test Case 1: Null string
        assertNull("Encoding a null string should return null",
                encoded);
    }
    @Test
    public void test80() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String test = null;
        String result = qpcodec.encode( test, "charset" );

        // Regression Test Case 1: Null encoding
        assertEquals("Result should be null", null, result);
    }
    @Test
    public void test81() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain);

        // Regression Test Case 1: String with safe characters
        assertEquals("Safe chars quoted-printable encoding test",
                plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test",
                plain, qpcodec.decode(encoded));
    }
    @Test
    public void test82() throws Exception {
        String plain = "Hello there!";
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("UnicodeBig");
        qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
        String encoded1 = qpcodec.encode(plain, "UnicodeBig");
        String encoded2 = qpcodec.encode(plain);

        // Regression Test Case 1: Use default encoding
        assertEquals(encoded1, encoded2);
    }


    @Test
    public void test83() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String test = "";
        String result = qpcodec.decode( test, "charset" );
        assertEquals("Result should be an empty string", "", result);
    }
    @Test
    public void test84() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        boolean pObject = true;
        try {
            Object result = qpcodec.decode((Object) pObject);
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
    }
    @Test
    public void test85() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "Hello there! 😊";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "Hello there! =F0=9F=98=8A", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test86() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "@#&";
        String encoded = qpcodec.encode(plain);
        assertEquals("Special chars quoted-printable encoding test", 
            "=40=23=26", encoded);
        assertEquals("Special chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test87() throws Exception {
        String qpdata = "If you    believe that truth=3Dbeauty, then surely truth=3D=20=\r\nbeauty.";
        String expected = "If you    believe that truth=beauty, then surely truth=beauty.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(expected, qpcodec.decode(qpdata));

        String encoded = qpcodec.encode(expected);
        assertEquals(expected, qpcodec.decode(encoded));
    }
    @Test
    public void test88() {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("");
        String plain = "Hello there!";
        try {
            qpcodec.encode(plain);
            fail( "Empty encoding should have caused an exception.");
        } catch (EncoderException ee) {
            // Exception expected, test segment passes.
        }
        try {
            qpcodec.decode(plain);
            fail( "Empty encoding should have caused an exception.");
        } catch (DecoderException ee) {
            // Exception expected, test segment passes.
        }
    }
    @Test
    public void test89() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there =\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Hello there =3D=0D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded, null));
    }
    @Test
    public void test90() throws Exception {

        String ru_msg = constructString(RUSSIAN_STUFF_UNICODE); 
        String ch_msg = constructString(SWISS_GERMAN_STUFF_UNICODE); 
        
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        
        assertEquals("New charset test", qpcodec.encode(ru_msg, "ISO-8859-1"));
        assertEquals("Gr=C3=BCezi_z=C3=A4m=C3=A4", qpcodec.encode(ch_msg, "ISO-8859-1"));
        
        assertEquals(ru_msg, qpcodec.decode(qpcodec.encode(ru_msg, "ISO-8859-1"), "ISO-8859-1"));
        assertEquals(ch_msg, qpcodec.decode(qpcodec.encode(ch_msg, "ISO-8859-1"), "ISO-8859-1"));
    }
    @Test
    public void test91() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        try {
            qpcodec.decode("=A");
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
        try {
            qpcodec.decode("=WW=AB");
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
    }
    @Test
    public void test92() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "a b";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            "a b", encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test93() throws Exception {
        String qpdata = "CRLF\r\nin an\n encoded text should =20=\r\nbe skipped in the\r decoding.";
        String expected = "CRLF\r\nin an\n encoded text should  be skipped in the\r decoding.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(expected, qpcodec.decode(qpdata));

        String encoded = qpcodec.encode(expected);
        assertEquals(expected, qpcodec.decode(encoded));
    }

    // Regression test: Change the plain text to include special characters
    @Test
    public void test94() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain = "This is a example of a quoted-printable text file. There is no end to it\tSpecial Characters: @#$%^&*()";
        String expected = "This is a example of a quoted-printable text file. There is no end to i=\r\nt=09Special Characters: @#$%^&*()";

        assertEquals(expected, qpcodec.encode(plain));

    }

    // Regression test: Change the plain text to include non-ASCII characters
    @Test
    public void test95() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain = "This is a example of a quoted-printable text file. There is no end to it é";
        String expected = "This is a example of a quoted-printable text file. There is no end to=3D=\r\nt é";
        assertEquals(expected, qpcodec.encode(plain));

    }

    // Regression test: Change the plain text to include line breaks
    @Test
    public void test96() throws Exception {
        String qpdata = "If you believe that truth=3Dbeauty, then surely=20=\r\nmathematics " +
                "is the most beautiful branch of philosophy.";
        String expected = "If you believe that truth=beauty, then surely mathematics " +
                "is the most beautiful branch of philosophy.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(expected, qpcodec.decode(qpdata));

    }
    

@Test
public void test97() throws Exception {
    final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

    String plain ="This is a modified example of a quoted-printable text file. There is no end to it\t";
    String expected = "This is a modified example of a quoted-printable text file. There is no end to i=\r\nt=09";

    assertEquals(expected, qpcodec.encode(plain));

    plain ="This is a modified example of a quoted-printable text file. There is no end to it ";
    expected = "This is a modified example of a quoted-printable text file. There is no end to i=\r\nt=20";

    assertEquals(expected, qpcodec.encode(plain));

    // whitespace before soft break
    plain ="This is a modified example of a quoted-printable text file. There is no end to   ";
    expected = "This is a modified example of a quoted-printable text file. There is no end to=20=\r\n =20";

    assertEquals(expected, qpcodec.encode(plain));

    // non-printable character before soft break
    plain ="This is a modified example of a quoted-printable text file. There is no end to=  ";
    expected = "This is a modified example of a quoted-printable text file. There is no end to=3D=\r\n =20";

    assertEquals(expected, qpcodec.encode(plain));
}
@Test
public void test98() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "2+2 = 4"; // Modified value
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
        Object dObj = new Double(4.0); // Modified value
        qpcodec.encode( dObj );
        fail( "Trying to url encode a Double object should cause an exception.");
    } catch (EncoderException ee) {
        // Exception expected, test segment passes.
    }
}
@Test
public void test99() throws Exception {
    final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

    String plain ="This is a modified example of a quoted-printable text file. This might contain sp=cial chars.";
    String expected = "This is a modified example of a quoted-printable text file. This might contain sp=3D=\r\ncial chars.";
    assertEquals(expected, qpcodec.encode(plain));

    plain ="This is a modified example of a quoted-printable text file. This might contain ta\tbs as well.";
    expected = "This is a modified example of a quoted-printable text file. This might contain ta=09=\r\nbs as well.";
    assertEquals(expected, qpcodec.encode(plain));
}
@Test
public void test100() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "=\r\n";
    String encoded = qpcodec.encode(plain);
    assertEquals("Unsafe chars quoted-printable encoding test", 
        "=3D=0D=0A", encoded);
    assertEquals("Unsafe chars quoted-printable decoding test",
        plain, qpcodec.decode(encoded));
}
@Test
public void test101() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    assertNull("Null string quoted-printable encoding test", 
        qpcodec.encode((String)null));
    assertNull("Null string quoted-printable decoding test", 
        qpcodec.decode((String)null));
}
@Test
public void test102() throws Exception {
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
public void test103() {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("INVALID"); // Modified value
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
public void test104() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "= Hello there =\r\n"; // Modified value
    String encoded = qpcodec.encode(plain);
    assertEquals("Basic quoted-printable encoding test", 
        "=3D Hello there =3D=0D=0A", encoded);
    assertEquals("Basic quoted-printable decoding test", 
        plain, qpcodec.decode(encoded));
}
@Test
public void test105() throws Exception {

    String ru_msg = constructString(MODIFIED_RUSSIAN_STUFF_UNICODE); // Modified value
    String ch_msg = constructString(MODIFIED_SWISS_GERMAN_STUFF_UNICODE); // Modified value
    
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
public void test106() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    byte[] plain = null;
    byte[] encoded = qpcodec.encode(plain);
    assertEquals("Encoding a null string should return null", 
        null, encoded);
}
@Test
public void test107() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String test = null;
    String result = qpcodec.encode( test, "charset" );
    assertEquals("Result should be null", null, result);
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
}
@Test
public void test109() throws Exception {
    String plain = "Hello there!";
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("ModifiedEncoding"); // Modified value
    qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
    String encoded1 = qpcodec.encode(plain, "ModifiedEncoding"); // Modified value
    String encoded2 = qpcodec.encode(plain);
    assertEquals(encoded1, encoded2);
}

}