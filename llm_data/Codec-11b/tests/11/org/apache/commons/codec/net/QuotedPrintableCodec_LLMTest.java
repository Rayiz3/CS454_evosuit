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
                "b=\r\n";
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
                "be=\r\neautiful branch of philosophy.";
        String expected = "If you believe that truth=beauty, then surely mathematics is the most " +
                "beautiful branch of philosophy.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(qpdata, qpcodec.encode(expected));

        String decoded = qpcodec.decode(qpdata);
        assertEquals(qpdata, qpcodec.encode(decoded));
    }
    @Test
    public void test3() throws Exception {
        String plain = "Hello there!";
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("UnicodeBig");
        qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
        String encoded1 = qpcodec.encode(plain, "UnicodeBig");
        String encoded2 = qpcodec.encode(plain);
        assertEquals(encoded1, encoded2);
    }
    @Test
    public void test4() throws Exception {
        String plain = null;
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("UnicodeBig");
        qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
        String encoded1 = qpcodec.encode(plain, "UnicodeBig");
        String encoded2 = qpcodec.encode(plain);
        assertEquals(encoded1, encoded2);
    }
    @Test
    public void test5() throws Exception {
        String plain = "";
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("UnicodeBig");
        qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
        String encoded1 = qpcodec.encode(plain, "UnicodeBig");
        String encoded2 = qpcodec.encode(plain);
        assertEquals(encoded1, encoded2);
    }
    @Test
    public void test6() throws Exception {
        String qpdata = "If you believe that truth=3Dbeauty, then surely mathematics is the most " +
                "b=\r\neautiful branch of philosophy.";
        String expected = "If you believe that truth=beauty, then surely mathematics is the most " +
                "beautiful branch of philosophy.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(qpdata, qpcodec.encode(expected));
    }
    @Test
    public void test7() throws Exception {
        String qpdata = "";
        String expected = "";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(qpdata, qpcodec.encode(expected));
    }
    @Test
    public void test8() throws Exception {
        String qpdata = "=";
        String expected = "=";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(qpdata, qpcodec.encode(expected));
    }
    @Test
    public void test9() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there =\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Hello there =3D=0D=0A", encoded);
    }
    @Test
    public void test10() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", "", encoded);
    }
    @Test
    public void test11() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "Hello! This is a test.";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", "Hello!=20This is a test.", encoded);
    }
    @Test
    public void test12() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
    }
    @Test
    public void test13() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "   abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            "=20=20=20abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]", encoded);
    }
    @Test
    public void test14() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]   ";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]=20=20=20", encoded);
    }
    @Test
    public void test15() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="This is a example of a quoted-printable text file. There is no end to it\t";
        String expected = "This is a example of a quoted-printable text file. There is no end to i=\r\nt=09";

        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test16() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="This is a example of a quoted-printable text file. There is no end to it   ";
        String expected = "This is a example of a quoted-printable text file. There is no end to=20=\r\n =20";

        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test17() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="This is a example of a quoted-printable text file. There is no end to=  ";
        String expected = "This is a example of a quoted-printable text file. There is no end to=3D=\r\n =20";

        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test18() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        byte[] plain = null;
        byte[] encoded = qpcodec.encode(plain);
        assertEquals("Encoding a null string should return null", 
            null, encoded);
    }
    @Test
    public void test19() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        byte[] plain = new byte[0];
        byte[] encoded = qpcodec.encode(plain);
        assertEquals("Encoding a null string should return null", 
            null, encoded);
    }
    @Test
    public void test20() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="This is a example of a quoted-printable text file. This might contain sp=cial chars.";
        String expected = "This is a example of a quoted-printable text file. This might contain sp=3D=\r\ncial chars.";
        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test21() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="This is a example of a quoted-printable text file. This might contain ta\tbs as well.";
        String expected = "This is a example of a quoted-printable text file. This might contain ta=09=\r\nbs as well.";
        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test22() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=3D=0D=0A", encoded);
    }
    @Test
    public void test23() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "@#$=\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "@#$=3D=0D=0A", encoded);
    }
    @Test
    public void test24() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=\r\n@#$";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=3D=0D=0A@#$", encoded);
    }
    @Test
    public void test25() throws Exception {

        String ru_msg = constructString(RUSSIAN_STUFF_UNICODE); 
        String ch_msg = constructString(SWISS_GERMAN_STUFF_UNICODE); 
        
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        
        assertEquals(
            "=D0=92=D1=81=D0=B5=D0=BC_=D0=BF=D1=80=D0=B8=D0=B2=D0=B5=D1=82", 
        qpcodec.encode(ru_msg, CharEncoding.UTF_8)
        );
        assertEquals("Gr=C3=BCezi_z=C3=A4m=C3=A4", qpcodec.encode(ch_msg, CharEncoding.UTF_8));
    }
    @Test
    public void test26() throws Exception {

        String ru_msg = ""; 
        String ch_msg = ""; 
        
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        
        assertEquals("", qpcodec.encode(ru_msg, CharEncoding.UTF_8));
        assertEquals("", qpcodec.encode(ch_msg, CharEncoding.UTF_8));
    }
    @Test
    public void test27() throws Exception {

        String ru_msg = "Hello! This is a test."; 
        String ch_msg = constructString(SWISS_GERMAN_STUFF_UNICODE); 
        
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        
        assertEquals("Hello!=20This is a test.", qpcodec.encode(ru_msg, CharEncoding.UTF_8));
        assertEquals("Gr=C3=BCezi_z=C3=A4m=C3=A4", qpcodec.encode(ch_msg, CharEncoding.UTF_8));
    }
    @Test
    public void test28() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "1+1 = 2";
        String encoded = new String(QuotedPrintableCodec.
            encodeQuotedPrintable(null, plain.getBytes("UTF-8")));
        assertEquals("Basic quoted-printable encoding test", 
            "1+1 =3D 2", encoded);
    }
    @Test
    public void test29() throws Exception {
        QuotedPrintableCodec qpcodec = null;
        String plain = "1+1 = 2";
        String encoded = new String(QuotedPrintableCodec.
            encodeQuotedPrintable(null, plain.getBytes("UTF-8")));
        assertEquals("Basic quoted-printable encoding test", 
            "1+1 =3D 2", encoded);
    }
    @Test
    public void test30() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        byte[] plain = null;
        byte[] encoded = qpcodec.encode(plain);
        assertEquals("Encoding a null string should return null", 
            null, encoded);
    }
    @Test
    public void test31() throws Exception {
        // whitespace, but does not need to be encoded
        String plain ="This is a example of a quoted=printable text file. There is no tt";
        String expected = "This is a example of a quoted=3Dprintable text file. There is no tt";

        assertEquals(expected, new QuotedPrintableCodec().encode(plain));
    }
    @Test
    public void test32() throws Exception {
        // whitespace, but does not need to be encoded
        String plain ="";
        String expected = "";

        assertEquals(expected, new QuotedPrintableCodec().encode(plain));
    }
    @Test
    public void test33() throws Exception {
        // whitespace, but does not need to be encoded
        String plain ="   ";
        String expected = "   ";

        assertEquals(expected, new QuotedPrintableCodec().encode(plain));
    }
    @Test
    public void test34() throws Exception {
        String plain = "Hello there!";
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("UnicodeBig");
        qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
        String encoded1 = qpcodec.encode(plain, "UnicodeBig");
        String encoded2 = qpcodec.encode(plain);
        assertEquals(encoded1, encoded2);
    }
    @Test
    public void test35() throws Exception {
        String plain = "";
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("UnicodeBig");
        qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
        String encoded1 = qpcodec.encode(plain, "UnicodeBig");
        String encoded2 = qpcodec.encode(plain);
        assertEquals(encoded1, encoded2);
    }
    @Test
    public void test36() throws Exception {
        String plain = "@#$%^&*()";
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("UnicodeBig");
        qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
        String encoded1 = qpcodec.encode(plain, "UnicodeBig");
        String encoded2 = qpcodec.encode(plain);
        assertEquals(encoded1, encoded2);
    }
    @Test
    public void test37() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there =\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Hello there =3D=0D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test38() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there \r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Hello there =3D=0D=0A", encoded);
        try {
            qpcodec.decode(encoded);
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
    }
    @Test
    public void test39() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there =\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Hello there =3D=0D=0A", encoded);
        try {
            qpcodec.decode(encoded);
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
    }
    @Test
    public void test40() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = " Hello there =\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Hello there =3D=0D=0A", encoded);
        try {
            qpcodec.decode(encoded);
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
    }
    @Test
    public void test41() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there =\r\n\r";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Hello there =3D=0D=0A", encoded);
        try {
            qpcodec.decode(encoded);
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
    }
    @Test
    public void test42() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there =\r\n ";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Hello there =3D=0D=0A", encoded);
        try {
            qpcodec.decode(encoded);
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
    }
    @Test
    public void test43() throws Exception {
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
    public void test44() throws Exception {
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
    public void test45() throws Exception {
        String qpdata = "If you believe that truth=3Dbeauty, then surely mathematics is the most " +
                "b=\r\neautiful branch of philosophy.";
        String expected = "If you believe that truth=beauty, then surely mathematics is the most " +
                "b\r\neautiful branch of philosophy.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(qpdata, qpcodec.encode(expected));

        String decoded = qpcodec.decode(qpdata);
        assertEquals(qpdata, qpcodec.encode(decoded));
    }
    @Test
    public void test46() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertNull("Null string quoted-printable encoding test",
            qpcodec.encode((String)null));
        assertNull("Null string quoted-printable decoding test",
            qpcodec.decode((String)null));
        
        // Change input to empty string
        assertEquals("", qpcodec.encode(""));
        assertNull(qpcodec.decode(""));
        
        // Change input to non-null string
        assertEquals("hello", qpcodec.encode("hello"));
        assertEquals("hello", qpcodec.decode("hello"));
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
        
        // Change input to non-null string
        assertEquals("hello", qpcodec.encode("hello"));
        assertEquals("hello", qpcodec.decode("hello"));
    }
    @Test
    public void test48() throws Exception {
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
    public void test49() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test",
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test",
            plain, qpcodec.decode(encoded));
        
        // Change input to non-ASCII string
        assertEquals("你好", qpcodec.encode("你好"));
        assertEquals("你好", qpcodec.decode("你好"));
        
        // Change input to non-ASCII string with special characters
        assertEquals("!@#$%^&*()_+,./<>?", qpcodec.encode("!@#$%^&*()_+,./<>?"));
        assertEquals("!@#$%^&*()_+,./<>?", qpcodec.decode("!@#$%^&*()_+,./<>?"));
        
        // Change input to string with control characters
        assertEquals("\u0000\u0001\u0002\u007F", qpcodec.encode("\u0000\u0001\u0002\u007F"));
        assertEquals("\u0000\u0001\u0002\u007F", qpcodec.decode("\u0000\u0001\u0002\u007F"));
        
    }
    @Test
    public void test50() {
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
        
        // Change encoding to a valid encoding
        qpcodec = new QuotedPrintableCodec("UTF-8");
        assertEquals(plain, qpcodec.encode(plain));
        assertEquals(plain, qpcodec.decode(plain));
    }
    @Test
    public void test51() throws Exception {
        String qpdata = "If you believe that truth=3Dbeauty, then surely=20=\r\nmathematics " +
                "is the most beautiful branch of philosophy.";
        String expected = "If you believe that truth=beauty, then surely mathematics " +
                "is the most beautiful branch of philosophy.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(expected, qpcodec.decode(qpdata));

        String encoded = qpcodec.encode(expected);
        assertEquals(expected, qpcodec.decode(encoded));
        
        // Change input to non-ASCII string
        assertEquals("你好", qpcodec.decode(qpdata));
        assertEquals(qpdata, qpcodec.decode("你好"));
    }
    @Test
    public void test52() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test",
            "=3D=0D=0A", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test",
            plain, qpcodec.decode(encoded));
        
        // Change input to non-ASCII string
        assertEquals("你好", qpcodec.encode("你好"));
        assertEquals("你好", qpcodec.decode("你好"));
        
        // Change input to non-ASCII string with special characters
        assertEquals("!@#$%^&*()_+,./<>?", qpcodec.encode("!@#$%^&*()_+,./<>?"));
        assertEquals("!@#$%^&*()_+,./<>?", qpcodec.decode("!@#$%^&*()_+,./<>?"));
    }
    @Test
    public void test53() throws Exception {

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
        
        // Change input to non-ASCII string
        assertEquals("你好", qpcodec.encode("你好", CharEncoding.UTF_8));
        assertEquals("你好", qpcodec.decode("你好", CharEncoding.UTF_8));
        
        // Change input to non-ASCII string with special characters
        assertEquals("!@#$%^&*()_+,./<>?", qpcodec.encode("!@#$%^&*()_+,./<>?", CharEncoding.UTF_8));
        assertEquals("!@#$%^&*()_+,./<>?", qpcodec.decode("!@#$%^&*()_+,./<>?", CharEncoding.UTF_8));
        
    }
    @Test
    public void test54() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "1+1 = 2";
        String encoded = new String(QuotedPrintableCodec.
            encodeQuotedPrintable(null, plain.getBytes("UTF-8")));
        assertEquals("Basic quoted-printable encoding test",
            "1+1 =3D 2", encoded);
        assertEquals("Basic quoted-printable decoding test",
            plain, qpcodec.decode(encoded));
        
        // Change input to non-ASCII string
        assertEquals("你好", qpcodec.encode("你好".getBytes("UTF-8")));
        assertEquals("你好", new String(qpcodec.decode("你好".getBytes("UTF-8"))));
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
        String qpdata = "CRLF in an\n encoded text should be=20=\r\n\rskipped in the\r decoding.";
        String expected = "CRLF in an encoded text should be skipped in the decoding.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(expected, qpcodec.decode(qpdata));

        String encoded = qpcodec.encode(expected);
        assertEquals(expected, qpcodec.decode(encoded));
        
        // Change input to non-ASCII string
        assertEquals("你好", qpcodec.decode(qpdata));
        assertEquals(qpdata, qpcodec.decode("你好"));
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
        
        // Change input to non-ASCII string
        try {
            qpcodec.decode("你好");
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
        
        // Change input to non-ASCII string with special characters
        try {
            qpcodec.decode("!@#$%^&*()_+,./<>?");
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
    }
    @Test
    public void test58() throws Exception {
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
    public void test59() throws Exception {
        String expected = null;

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(expected, qpcodec.encode(null));
    }
    @Test
    public void test60() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertNull("Null string quoted-printable encoding test", 
            qpcodec.encode((String)null));
        assertNull("Null string quoted-printable decoding test", 
            qpcodec.decode((String)null));
    }
    @Test
    public void test61() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertNull("Null string quoted-printable encoding test", 
            qpcodec.encode(null));
        assertNull("Null string quoted-printable decoding test", 
            qpcodec.decode(null));
    }
    @Test
    public void test62() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there =\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Hello there =3D=0D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test63() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
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
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            "", encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test66() throws Exception {
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
    public void test67() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain = "";
        String expected = "";

        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test68() {
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
    public void test69() {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("NONSENSE");
           String plain = "";
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
    public void test70() throws Exception {
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
    public void test71() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = null;
        String encoded = (String) qpcodec.encode((Object) plain);
        assertEquals("Basic quoted-printable encoding test", 
            null, encoded);

        byte[] plainBA = null;
        byte[] encodedBA = (byte[]) qpcodec.encode((Object) plainBA);
        assertEquals("Basic quoted-printable encoding test", 
            null, encodedBA);

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
    public void test72() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        byte[] plain = null;
        byte[] encoded = qpcodec.encode(plain);
        assertEquals("Encoding a null string should return null", 
            null, encoded);
    }
    @Test
    public void test73() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="This is a example of a quoted-printable text file. This might contain sp=cial chars.";
        String expected = "This is a example of a quoted-printable text file. This might contain sp=3D=\r\ncial chars.";
        assertEquals(expected, qpcodec.encode(plain));

        plain ="This is a example of a quoted-printable text file. This might contain ta\tbs as well.";
        expected = "This is a example of a quoted-printable text file. This might contain ta=09=\r\nbs as well.";
        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test74() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain = "";
        String expected = "";
        assertEquals(expected, qpcodec.encode(plain));
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
    }
    @Test
    public void test76() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test77() throws Exception {

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
    public void test78() throws Exception {

        String ru_msg = ""; 
        String ch_msg = ""; 
        
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        
        assertEquals(
            "", 
        qpcodec.encode(ru_msg, CharEncoding.UTF_8)
        );
        assertEquals("", qpcodec.encode(ch_msg, CharEncoding.UTF_8));
        
        assertEquals(ru_msg, qpcodec.decode(qpcodec.encode(ru_msg, CharEncoding.UTF_8), CharEncoding.UTF_8));
        assertEquals(ch_msg, qpcodec.decode(qpcodec.encode(ch_msg, CharEncoding.UTF_8), CharEncoding.UTF_8));
    }
    @Test
    public void test79() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String test = null;
        String result = qpcodec.encode( test, "charset" );
        assertEquals("Result should be null", null, result);
    }
    @Test
    public void test80() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "Hello there!";
        String encoded = qpcodec.encode(plain, null);
        assertEquals("Basic quoted-printable encoding test", 
            "Hello there!", encoded);
    }
    @Test
    public void test81() throws Exception {
        // whitespace, but does not need to be encoded
        String plain ="This is a example of a quoted=printable text file. There is no tt";
        String expected = "This is a example of a quoted=3Dprintable text file. There is no tt";

        assertEquals(expected, new QuotedPrintableCodec().encode(plain));
    }
    @Test
    public void test82() throws Exception {
        // whitespace, but does not need to be encoded
        String plain ="this is a valid one";
        String expected = "this is a valid one";

        assertEquals(expected, new QuotedPrintableCodec().encode(plain));
    }
    @Test
    public void test83() throws Exception {
        String plain = "Hello there!";
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("UnicodeBig");
        qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
        String encoded1 = qpcodec.encode(plain, "UnicodeBig");
        String encoded2 = qpcodec.encode(plain);
        assertEquals(encoded1, encoded2);
    }
    @Test
    public void test84() throws Exception {
        String plain = "";
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("UnicodeBig");
        qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
        String encoded1 = qpcodec.encode(plain, "UnicodeBig");
        String encoded2 = qpcodec.encode(plain);
        assertEquals(encoded1, encoded2);
    }
@Test
public void test85() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    assertNull("Null string quoted-printable encoding test", 
        qpcodec.encode((String)null));
    assertNull("Null string quoted-printable decoding test", 
        qpcodec.decode((String)null));
}
@Test
public void test86() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "= Hello there =\r\n";
    String encoded = qpcodec.encode(plain);
    assertEquals("Basic quoted-printable encoding test", 
        "=3D Hello there =3D=0D=0A", encoded);
    assertEquals("Basic quoted-printable decoding test", 
        plain, qpcodec.decode(encoded));
}
@Test
public void test87() throws Exception {
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
public void test88() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
    String encoded = qpcodec.encode(plain);
    assertEquals("Safe chars quoted-printable encoding test", 
        plain, encoded);
    assertEquals("Safe chars quoted-printable decoding test", 
        plain, qpcodec.decode(encoded));
}
@Test
public void test89() {
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
public void test90() throws Exception {
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
public void test91() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "=\r\n";
    String encoded = qpcodec.encode(plain);
    assertEquals("Unsafe chars quoted-printable encoding test", 
        "=3D=0D=0A", encoded);
    assertEquals("Unsafe chars quoted-printable decoding test", 
        plain, qpcodec.decode(encoded));
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
    String plain = "1+1 = 2";
    String encoded = new String(QuotedPrintableCodec.
        encodeQuotedPrintable(null, plain.getBytes("UTF-8")));
    assertEquals("Basic quoted-printable encoding test", 
        "1+1 =3D 2", encoded);
    assertEquals("Basic quoted-printable decoding test", 
        plain, qpcodec.decode(encoded));
    
}
@Test
public void test94() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String test = null;
    String result = qpcodec.decode( test, "charset" );
    assertEquals("Result should be null", null, result);
}
@Test
public void test95() throws Exception {
    String qpdata = "CRLF in an\n encoded text should be=20=\r\n\rskipped in the\r decoding.";
    String expected = "CRLF in an encoded text should be skipped in the decoding.";

    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    assertEquals(expected, qpcodec.decode(qpdata));

    String encoded = qpcodec.encode(expected);
    assertEquals(expected, qpcodec.decode(encoded));
}
@Test
public void test96() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    try {
        qpcodec.decode("=");
        fail("DecoderException should have been thrown");
    } catch (DecoderException e) {
        // Expected. Move on
    }
}
@Test
public void test97() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    try {
        qpcodec.decode("=A");
        fail("DecoderException should have been thrown");
    } catch (DecoderException e) {
        // Expected. Move on
    }
}
@Test
public void test98() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    try {
        qpcodec.decode("=WW");
        fail("DecoderException should have been thrown");
    } catch (DecoderException e) {
        // Expected. Move on
    }
}
    @Test
    public void test99() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "";
        String encoded = qpcodec.encode(plain);
        assertEquals("Encoding empty string should return empty string",
                "", encoded);
        assertEquals("Decoding empty string should return empty string",
                plain, qpcodec.decode(encoded));
    }
    @Test
    public void test100() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "!@#$%^&*()_+-={}[]|\\:;\"'<>,.?/~`";
        String encoded = qpcodec.encode(plain);
        assertEquals("Encoding special characters should return expected result",
            "!@#$%^&*()_+-={}[]|\\:;\"'<>,.?/~`", encoded);
        assertEquals("Decoding special characters should return expected result",
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test101() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "æøåñßƒü";
        String encoded = qpcodec.encode(plain);
        assertEquals("Encoding non-ascii characters should return expected result",
                "=E6=F8=E5=F1=DF=C6=FC", encoded);
        assertEquals("Decoding non-ascii characters should return expected result",
                plain, qpcodec.decode(encoded));
    }
    @Test
    public void test102() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam gravida nunc et rhoncus venenatis. Vestibulum tortor lacus, sollicitudin eu pharetra non, sagittis vel quam. Sed in justo rhoncus, tristique orci nec, condimentum mi. Ut ligula urna, interdum non commodo nec, sagittis at massa. Fusce tempus est ut eleifend dignissim. In semper scelerisque sem, in interdum odio interdum et. Donec sed fringilla ante, id feugiat ex. Ut bibendum augue id odio ultrices mollis. Nam ut sem interdum, mollis ante sed, convallis leo. In commodo volutpat lobortis. Proin vitae facilisis nisi, nec hendrerit metus. Fusce feugiat, sem eget tincidunt semper, nisi sapien efficitur dui, nec vestibulum arcu sem a massa. Etiam in congue dolor.";
        
        String encoded = qpcodec.encode(plain);
        assertEquals("Encoding long string should return expected result",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam gravida nunc et \r\nrhoncus venenatis. Vestibulum tortor lacus, sollicitudin eu pharetra  n\r\non, sagittis vel quam. Sed in justo rhoncus, tristique orci nec, condimentum mi. U \r\net ligula urna, interdum non commodo nec, sagittis at massa. Fusce tempus est ut e \r\n1leifend dignissim. In semper scelerisque sem, in interdum odio interdum et. Done \r\nc sed fringilla ante, id feugiat ex. Ut bibendum augue id odio ultrices mollis. 9 \r\nam ut sem interdum, mollis ante sed, convallis leo. In commodo volutpat lobortis \r\n. Proin vitae facilisis nisi, nec hendrerit metus. Fusce feugiat, sem eget 3tin \r\ncidunt semper, nisi sapien efficitur dui, nec vestibulum arcu sem a massa. Eti \r\nam in congue dolor.\r\n", encoded);
        
        assertEquals("Decoding long string should return expected result",
                plain, qpcodec.decode(encoded));
    }
    @Test
    public void test103() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String encoded = "This=2Bis=20not=20valid";
        try {
            qpcodec.decode(encoded);
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
    }
    @Test
    public void test104() throws Exception {
        String qpdata = "If you believe that truth=3Dbeauty, then surely mathematics is the most " +
                "b=\r\neautiful branch of philosophy.";
        String expected = "If you believe that truth=beauty, then surely mathematics is the most " +
                "beautiful branch of philosophy.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(qpdata, qpcodec.encode(expected)); // original test

        // regression test case 1
        String qpdata2 = "If you believe that truth=3Dbeauty, then surely mathematics is the most " +
                "beautiful branch of philosophy.";
        String expected2 = "If you believe that truth=beauty, then surely mathematics is the most " +
                "beautiful branch of philosophy.";
        assertEquals(qpdata2, qpcodec.encode(expected2));

        // regression test case 2
        String qpdata3 = "If you believe that truth=3Dbeauty, then surely mathematics is the most " +
                "b=\r\neautiful branch of philosophy";
        String expected3 = "If you believe that truth=beauty, then surely mathematics is the most " +
                "beautiful branch of philosophy";
        assertEquals(qpdata3, qpcodec.encode(expected3));

        // regression test case 3
        String qpdata4 = "If you believe that truth=3Dbeauty, then surely mathematics is the most " +
                "beautiful branch of philosophy";
        String expected4 = "If you believe that truth=beauty, then surely mathematics is the most " +
                "beautiful branch of philosophy";
        assertEquals(qpdata4, qpcodec.encode(expected4));

        String decoded = qpcodec.decode(qpdata);
        assertEquals(qpdata, qpcodec.encode(decoded)); // original test
    }
    @Test
    public void test105() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        byte[] plain = null;
        byte[] encoded = qpcodec.encode(plain);
        assertEquals("Encoding a null string should return null", 
            null, encoded); // original test

        // regression test case
        byte[] plain2 = {1, 2, 3, 4};
        byte[] encoded2 = qpcodec.encode(plain2);
        assertArrayEquals(plain2, encoded2);
    }
    @Test
    public void test106() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertNull("Null string quoted-printable encoding test", 
            qpcodec.encode((String)null));
        assertNull("Null string quoted-printable decoding test", 
            qpcodec.decode((String)null));
        // Regression test case 1
        assertNull("Empty string quoted-printable encoding test", 
            qpcodec.encode(""));
        assertNull("Empty string quoted-printable decoding test", 
            qpcodec.decode(""));
        // Regression test case 2
        assertNull("Non-string object quoted-printable encoding test", 
            qpcodec.encode(123));
        assertNull("Non-string object quoted-printable decoding test", 
            qpcodec.decode(123));
    }
    @Test
    public void test107() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there =\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Hello there =3D=0D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        // Regression test case 1
        plain = "Hello";
        encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test108() throws Exception {
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
        // Regression test case 1
        try {
            plain = "";
            qpcodec.decode((Object) plain);
            fail( "Trying to decode an empty string Object should cause an exception.");
        } catch (DecoderException ee) {
            // Exception expected, test segment passes.
        }
    }
    @Test
    public void test109() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        // Regression test case 1
        qpcodec = new QuotedPrintableCodec("ISO-8859-1");
        plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test110() {
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
             Exception expected, test segment passes.
        }
        // Regression test case 1
        qpcodec = new QuotedPrintableCodec("UTF-8");
        plain = "Hello there!";
        try {
            qpcodec.encode(plain);
            fail( "We set the encoding to a different encoding, this shouldn't have worked.");
        } catch (EncoderException ee) {
            // Exception expected, test segment passes.
        }
        try {
            qpcodec.decode(plain);
            fail( "We set the encoding to a different encoding, this shouldn't have worked.");
        } catch (DecoderException ee) {
             Exception expected, test segment passes.
        }
    }
    @Test
    public void test111() throws Exception {
        String qpdata = "If you believe that truth=3Dbeauty, then surely=20=\r\nmathematics " +
                "is the most beautiful branch of philosophy.";
        String expected = "If you believe that truth=beauty, then surely mathematics " +
                "is the most beautiful branch of philosophy.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(expected, qpcodec.decode(qpdata));

        String encoded = qpcodec.encode(expected);
        assertEquals(expected, qpcodec.decode(encoded));
        // Regression test case 1
        qpdata = "If = you believe =\r\n that truth=3Dbeauty, =\r\n then = =20= \r\nsurely =\r\n mathematics ";
        expected = "If you believe that truth=beauty, then surely mathematics ";
        assertEquals(expected, qpcodec.decode(qpdata));
        encoded = qpcodec.encode(expected);
        assertEquals(expected, qpcodec.decode(encoded));
    }
    @Test
    public void test112() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=3D=0D=0A", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        // Regression test case 1
        plain = "\r\n";
        encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=0D=0A", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test113() throws Exception {

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
        // Regression test case 1
        qpcodec = new QuotedPrintableCodec("ISO-8859-1");
        assertEquals(
            "=D0=92=D1=81=D0=B5=D0=BC_=D0=BF=D1=80=D0=B8=D0=B2=D0=B5=D1=82", 
            qpcodec.encode(ru_msg, CharEncoding.UTF_8)
        );
        assertEquals("Gr=C3=BCezi_z=C3=A4m=C3=A4", qpcodec.encode(ch_msg, CharEncoding.UTF_8));
    }
    @Test
    public void test114() throws Exception {
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
    public void test115() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String test = null;
        String result = qpcodec.decode( test, "charset" );
        assertEquals("Result should be null", null, result);
    }
    @Test
    public void test116() throws Exception {
        String qpdata = "CRLF in an\n encoded text should be=20=\r\n\rskipped in the\r decoding.";
        String expected = "CRLF in an encoded text should be skipped in the decoding.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(expected, qpcodec.decode(qpdata));

        String encoded = qpcodec.encode(expected);
        assertEquals(expected, qpcodec.decode(encoded));
        // Regression test case 1
        qpdata = "CRLF in an\n encoded text should be=20=\n\r\rskipped in the\r decoding.";
        assertEquals(expected, qpcodec.decode(qpdata));
        encoded = qpcodec.encode(expected);
        assertEquals(expected, qpcodec.decode(encoded));
    }
    @Test
    public void test117() throws Exception {
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
        // Regression test case 1
        try {
            qpcodec.decode("==");
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
    }
    @Test
    public void test118() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "";
        String encoded = qpcodec.encode(plain);
        assertEquals("Encoding an empty string should return an empty string", "", encoded);
    }
    @Test
    public void test119() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = null;
        String encoded = qpcodec.encode(plain);
        assertEquals("Encoding a null string should return null", null, encoded);
    }
    @Test
    public void test120() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "こんにちは";
        String encoded = qpcodec.encode(plain);
        assertEquals("Encoding a string with default charset should return the same string (no encoding required)", plain, encoded);
    }
@Test
public void test121() throws Exception {
    String qpdata = "";
    String expected = "";

    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    assertEquals(qpdata, qpcodec.encode(expected));

    String decoded = qpcodec.decode(qpdata);
    assertEquals(qpdata, qpcodec.encode(decoded));
}
@Test
public void test122() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    assertNull("Null string quoted-printable encoding test", 
        qpcodec.encode((String)null, ""));
    assertNull("Null string quoted-printable decoding test", 
        qpcodec.decode((String)null, ""));
}
@Test
public void test123() throws Exception {
    String qpdata = "!@#$%^&*()-=_+[]{};':',.<>/?~`";
    String expected = "!@#$%^&*()-=_+[]{};':',.<>/?~`";

    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    assertEquals(qpdata, qpcodec.encode(expected));

    String decoded = qpcodec.decode(qpdata);
    assertEquals(qpdata, qpcodec.encode(decoded));
}
@Test
public void test124() throws Exception {
    String qpdata = "If you believe that truth=3Dbeauty, then surely mathematics is the most " +
            "b=\r\neautiful branch of philosophy.";
    String expected = "If you believe that truth=beauty, then surely mathematics is the most " +
            "beautiful branch of philosophy.";

    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    assertEquals(qpdata, qpcodec.encode(expected, "UTF-8"));

    String decoded = qpcodec.decode(qpdata, "UTF-8");
    assertEquals(qpdata, qpcodec.encode(decoded, "UTF-8"));
}
@Test
public void test125() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = null;
    String encoded = qpcodec.encode(plain);
    assertEquals("Basic quoted-printable encoding test", 
        null, encoded);
    assertEquals("Basic quoted-printable decoding test", 
        plain, qpcodec.decode(encoded));
}
@Test
public void test126() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "";
    String encoded = qpcodec.encode(plain);
    assertEquals("Safe chars quoted-printable encoding test", 
        plain, encoded);
    assertEquals("Safe chars quoted-printable decoding test", 
        plain, qpcodec.decode(encoded));
}
@Test
public void test127() throws Exception {
    final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

    String plain = "";
    String expected = "";

    assertEquals(expected, qpcodec.encode(plain));

    plain =" ";
    expected = " ";

    assertEquals(expected, qpcodec.encode(plain));

    plain ="=";
    expected = "=";

    assertEquals(expected, qpcodec.encode(plain));
}
@Test
public void test128() {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("NONSENSE");
    String plain = "";
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
public void test129() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "";
    String encoded = (String) qpcodec.encode((Object) plain);
    assertEquals("Basic quoted-printable encoding test", 
        "", encoded);

    byte[] plainBA = plain.getBytes("UTF-8");
    byte[] encodedBA = (byte[]) qpcodec.encode((Object) plainBA);
    encoded = new String(encodedBA);
    assertEquals("Basic quoted-printable encoding test", 
        "", encoded);
        
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
public void test130() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    byte[] plain = null;
    byte[] encoded = qpcodec.encode(plain, "");
    assertEquals("Encoding a null string should return null", 
        null, encoded);
}
@Test
public void test131() throws Exception {
    final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

    String plain ="";
    String expected = "";

    assertEquals(expected, qpcodec.encode(plain));

    plain =" ";
    expected = " ";

    assertEquals(expected, qpcodec.encode(plain));

    plain ="=";
    expected = "=";

    assertEquals(expected, qpcodec.encode(plain));
}
@Test
public void test132() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "";
    String encoded = qpcodec.encode(plain);
    assertEquals("Unsafe chars quoted-printable encoding test", 
        plain, encoded);
    assertEquals("Unsafe chars quoted-printable decoding test", 
        plain, qpcodec.decode(encoded));
}
@Test
public void test133() throws Exception {
    String ru_msg = constructString(RUSSIAN_STUFF_UNICODE); 
    String ch_msg = constructString(SWISS_GERMAN_STUFF_UNICODE); 
    
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    
    assertEquals(
        "", 
    qpcodec.encode(ru_msg, CharEncoding.UTF_8)
    );
    assertEquals("", qpcodec.encode(ch_msg, CharEncoding.UTF_8));
    
    assertEquals(ru_msg, qpcodec.decode(qpcodec.encode(ru_msg, CharEncoding.UTF_8), CharEncoding.UTF_8));
    assertEquals(ch_msg, qpcodec.decode(qpcodec.encode(ch_msg, CharEncoding.UTF_8), CharEncoding.UTF_8));
}
@Test
public void test134() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String test = null;
    String result = qpcodec.encode( test, "charset" );
    assertEquals("Result should be null", null, result);
}
@Test
public void test135() throws Exception {
    // whitespace, but does not need to be encoded
    String plain ="";
    String expected = "";

    assertEquals(expected, new QuotedPrintableCodec().encode(plain));
}
@Test
public void test136() throws Exception {
    String plain = "";
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("UnicodeBig");
    qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
    String encoded1 = qpcodec.encode(plain, "UnicodeBig");
    String encoded2 = qpcodec.encode(plain);
    assertEquals(encoded1, encoded2);
}
}