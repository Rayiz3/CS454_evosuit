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
        String qpdata = "If you believe that truth=3D3Dbeauty, then surely mathematics is the most " +
                "b=\r\neautiful branch of philosophy.";
        String expected = "If you believe that truth==beauty, then surely mathematics is the most " +
                "beautiful branch of philosophy.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(qpdata, qpcodec.encode(expected));

        String decoded = qpcodec.decode(qpdata);
        assertEquals(qpdata, qpcodec.encode(decoded));
    }
    @Test
    public void test1() throws Exception {
        String qpdata = "If you believe that truthglobal=3Dbeauty, then surely mathematics is the most " +
                "b=\r\neautiful branch of philosophy.";
        String expected = "If you believe that truthglobal==beauty, then surely mathematics is the most " +
                "beautiful branch of philosophy.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(qpdata, qpcodec.encode(expected));

        String decoded = qpcodec.decode(qpdata);
        assertEquals(qpdata, qpcodec.encode(decoded));
    }
    @Test
    public void test2() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=American people =\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D American people =3D=0D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test3() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= main world =\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D main world =3D=0D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test4() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abcoutes123_-.riu*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test5() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "tyw.123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test6() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="French people can = live in peaceful places=\t";
        String expected = "French people can = live in peaceful places=\r\nt=09";

        assertEquals(expected, qpcodec.encode(plain));

        plain ="French people can = live in peaceful places ";
        expected = "French people can = live in peaceful places=\r\nt=20";

        assertEquals(expected, qpcodec.encode(plain));

        // whitespace before soft break
        plain ="French people can = live in peaceful   ";
        expected = "French people can = live in peaceful=20=\r\n =20";

        assertEquals(expected, qpcodec.encode(plain));

        // non-printable character before soft break
        plain ="French people can = live in peaceful=  ";
        expected = "French people can = live in peaceful=3D=\r\n =20";

        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test7() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="Portuguese people can = live in big places tab=\t";
        String expected = "Portuguese people can = live in big places tab=\r\nt=09";

        assertEquals(expected, qpcodec.encode(plain));

        plain ="Portuguese people can = live in big places tab ";
        expected = "Portuguese people can = live in big places tab=\r\nt=20";

        assertEquals(expected, qpcodec.encode(plain));

        // whitespace before soft break
        plain ="Portuguese people can = live in big   ";
        expected = "Portuguese people can = live in big=20=\r\n =20";

        assertEquals(expected, qpcodec.encode(plain));

        // non-printable character before soft break
        plain ="Portuguese people can = live in big=  ";
        expected = "Portuguese people can = live in big=3D=\r\n =20";

        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test8() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "3/4 + 4/5 = 12";
        String encoded = (String) qpcodec.encode((Object) plain);
        assertEquals("Basic quoted-printable encoding test", 
            "3/4 + 4/5 =3D 12", encoded);

        byte[] plainBA = plain.getBytes("UTF-8");
        byte[] encodedBA = (byte[]) qpcodec.encode((Object) plainBA);
        encoded = new String(encodedBA);
        assertEquals("Basic quoted-printable encoding test", 
            "3/4 + 4/5 =3D 12", encoded);
            
        Object result = qpcodec.encode((Object) null);
        assertEquals( "Encoding a null Object should return null", null, result);
        
        try {
            Object iObj = new Integer(6);
            qpcodec.encode( iObj );
            fail( "Trying to url encode an Integer object should cause an exception.");
        } catch (EncoderException ee) {
            // Exception expected, test segment passes.
        }
    }
    @Test
    public void test9() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "8*9 = 128";
        String encoded = (String) qpcodec.encode((Object) plain);
        assertEquals("Basic quoted-printable encoding test", 
            "8*9 =3D 128", encoded);

        byte[] plainBA = plain.getBytes("UTF-8");
        byte[] encodedBA = (byte[]) qpcodec.encode((Object) plainBA);
        encoded = new String(encodedBA);
        assertEquals("Basic quoted-printable encoding test", 
            "8*9 =3D 128", encoded);
            
        Object result = qpcodec.encode((Object) null);
        assertEquals( "Encoding a null Object should return null", null, result);
        
        try {
            Object lObj = new Long(8192);
            qpcodec.encode( lObj );
            fail( "Trying to url encode a Long object should cause an exception.");
        } catch (EncoderException ee) {
            // Exception expected, test segment passes.
        }
    }
    @Test
    public void test10() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        byte[] plain = null;
        byte[] encoded = qpcodec.encode(plain);
        assertEquals("Encoding a null string should return null", 
            null, encoded);
    }
    @Test
    public void test11() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="Some people may have sp?cial values.";
        String expected = "Some people may have sp?cial values";

        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test12() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="Aspecis1@sme people ru#e#e sp#cial.";
        String expected = "Aspecis1@sme people ru#e#e sp#cial";

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
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=\t\t";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=3D=09=09", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test15() throws Exception {

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
        // whitespace, but does not need to be encoded
        String plain ="This is a mutation of a quoted=printable text that has no tt";
        String expected = "This is a mutation of a quoted=3Dprintable text that has no tt";

        assertEquals(expected, new QuotedPrintableCodec().encode(plain));
    }
    @Test
    public void test18() throws Exception {
        String plain = "Hello there!";
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("UTF-16");
        qpcodec.encode(plain); 
        String encoded1 = qpcodec.encode(plain, "UTF-16");
        String encoded2 = qpcodec.encode(plain);
        assertEquals(encoded1, encoded2);
    }
    @Test
    public void test19() throws Exception {
        String qpdata = "If you believe that truth=3Dbeauty, then surely mathematics is the most " +
                "b=\r\neautiful branch of philosophy.";
        String expected = "If you believe that truth=beauty, then surely mathematics is the most " +
                "beautiful branch of philosophy.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(qpdata, qpcodec.encode(expected));
        
        // Mutation: Remove the character '=' from qpdata
        qpdata = "If you believe that truth3Dbeauty, then surely mathematics is the most " +
                "b=\r\neautiful branch of philosophy.";
        
        assertNotEquals(qpdata, qpcodec.encode(expected));

        String decoded = qpcodec.decode(qpdata);
        assertEquals(qpdata, qpcodec.encode(decoded));
    }
    @Test
    public void test20() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there =\r\n";
        String encoded = qpcodec.encode(plain);
        
        // Mutation: Replace the character '=' with a different character
        plain = "@ Hello there =\r\n";

        assertNotEquals(encoded, qpcodec.encode(plain));
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test21() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain);
        
        // Mutation: Replace one of the characters in plain with a different character
        plain = "xbc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";

        assertNotEquals(encoded, qpcodec.encode(plain));
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test22() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="This is a example of a quoted-printable text file. There is no end to it\t";
        String expected = "This is a example of a quoted-printable text file. There is no end to i=\r\nt=09";

        assertEquals(expected, qpcodec.encode(plain));
        
        // Mutation: Remove the tab character from plain
        plain ="This is a example of a quoted-printable text file. There is no end to it ";

        assertNotEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test23() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="This is a example of a quoted-printable text file. This might contain sp=cial chars.";
        String expected = "This is a example of a quoted-printable text file. This might contain sp=3D=\r\ncial chars.";
        
        assertEquals(expected, qpcodec.encode(plain));
        
        // Mutation: Remove the equal sign (=) from plain
        plain ="This is a example of a quoted-printable text file. This might contain spcial chars.";

        assertNotEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test24() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=\r\n";
        String encoded = qpcodec.encode(plain);
        
        // Mutation: Remove the equal sign (=) from plain
        plain = "\r\n";

        assertNotEquals(encoded, qpcodec.encode(plain));
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test25() throws Exception {
        String plain = "Hello there!";
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("UnicodeBig");
        qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
        String encoded1 = qpcodec.encode(plain, "UnicodeBig");
        String encoded2 = qpcodec.encode(plain);
        
        // Mutation: Change the encoding parameter to a different encoding
        encoded1 = qpcodec.encode(plain, "UnicodeLittle");

        assertNotEquals(encoded1, encoded2);
    }
    @Test
    public void test26() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there =\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Hello there =3D=0D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test27() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "1+1 =3D 2";
        String decoded = (String) qpcodec.decode((Object) plain);
        assertEquals("Basic quoted-printable decoding test", 
            "1+1 = 2", decoded);
        
        // Changed input from byte[] to String
        String plainStr = "1+1 =3D 2";
        byte[] plainBA = plainStr.getBytes("UTF-8");
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
    public void test28() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
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
    public void test31() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=3D=0D=0A", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test32() throws Exception {

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
    public void test33() throws Exception {
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
    public void test34() throws Exception {
        byte[] plain = null;
        byte[] result = QuotedPrintableCodec.decodeQuotedPrintable( plain );
        assertEquals("Result should be null", null, result);
    }
    @Test
    public void test35() throws Exception {
        String qpdata = "CRLF in an\n encoded text should be=20=\r\n\rskipped in the\r decoding.";
        String expected = "CRLF in an encoded text should be skipped in the decoding.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(expected, qpcodec.decode(qpdata));

        String encoded = qpcodec.encode(expected);
        assertEquals(expected, qpcodec.decode(encoded));
    }
    @Test
    public void test36() throws Exception {
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
        
        // Changed input to have a valid encoding but with different values
        try {
            qpcodec.decode("=FF");
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
    }
    @Test
    public void test37() throws Exception {
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
    public void test38() throws Exception {
        String qpdata = "If you believe that truth=3Dbeauty, then surely mathematics is the most " +
                "b=\r\n" +
                "eautiful branch of philosophy.";
        String expected = "If you believe that truth=beauty, then surely mathematics is the most " +
                "beautiful branch of philosophy.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(qpdata, qpcodec.encode(expected));

        String decoded = qpcodec.decode(qpdata);
        assertEquals(qpdata, qpcodec.encode(decoded));
    }
    @Test
    public void test39() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertNull("Null string quoted-printable encoding test",
                qpcodec.encode((String)null));
        assertNull("Null string quoted-printable decoding test",
                qpcodec.decode((String)null));
    }
    @Test
    public void test40() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertNull("Null string quoted-printable encoding test",
                qpcodec.encode((String)null));
        assertNull("Null string quoted-printable decoding test",
                qpcodec.decode((String)null));
    }
    @Test
    public void test41() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there =\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test",
                "=3D Hello there =3D=0D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test",
                plain, qpcodec.decode(encoded));
    }
    @Test
    public void test42() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there =\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test",
                "=3D Hello there =3D=0D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test",
                plain, qpcodec.decode(encoded));
    }
    @Test
    public void test43() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test",
                plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test",
                plain, qpcodec.decode(encoded));
    }
    @Test
    public void test44() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test",
                plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test",
                plain, qpcodec.decode(encoded));
    }
    @Test
    public void test45() throws Exception {
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
    public void test46() throws Exception {
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
    public void test47() {
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
    public void test48() {
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
    public void test49() throws Exception {
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
    public void test50() throws Exception {
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
    public void test51() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        byte[] plain = null;
        byte[] encoded = qpcodec.encode(plain);
        assertEquals("Encoding a null string should return null",
                null, encoded);
    }
    @Test
    public void test52() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        byte[] plain = null;
        byte[] encoded = qpcodec.encode(plain);
        assertEquals("Encoding a null string should return null",
                null, encoded);
    }
    @Test
    public void test53() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="This is a example of a quoted-printable text file. This might contain sp=cial chars.";
        String expected = "This is a example of a quoted-printable text file. This might contain sp=3D=\r\ncial chars.";
        assertEquals(expected, qpcodec.encode(plain));

        plain ="This is a example of a quoted-printable text file. This might contain ta\tbs as well.";
        expected = "This is a example of a quoted-printable text file. This might contain ta=09=\r\nbs as well.";
        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test54() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="This is a example of a quoted-printable text file. This might contain sp=cial chars.";
        String expected = "This is a example of a quoted-printable text file. This might contain sp=3D=\r\ncial chars.";
        assertEquals(expected, qpcodec.encode(plain));

        plain ="This is a example of a quoted-printable text file. This might contain ta\tbs as well.";
        expected = "This is a example of a quoted-printable text file. This might contain ta=09=\r\nbs as well.";
        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test55() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test",
                "=3D=0D=0A", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test",
                plain, qpcodec.decode(encoded));
    }
    @Test
    public void test56() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test",
                "=3D=0D=0A", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test",
                plain, qpcodec.decode(encoded));
    }
    @Test
    public void test57() throws Exception {

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
    public void test58() throws Exception {

        String ru_msg = constructString(RUSSIAN_STUFF_UNICODE.replace("всем привет", "не всем привет"));
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
    public void test59() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String test = null;
        String result = qpcodec.encode(test, "charset");
        assertEquals("Result should be null", null, result);
    }
    @Test
    public void test60() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String test = null;
        String result = qpcodec.encode(test, "charset");
        assertEquals("Result should be null", null, result);
    }
    @Test
    public void test61() throws Exception {
        // whitespace, but does not need to be encoded
        String plain ="This is a example of a quoted=printable text file. There is no tt";
        String expected = "This is a example of a quoted=3Dprintable text file. There is no tt";

        assertEquals(expected, new QuotedPrintableCodec().encode(plain));
    }
    @Test
    public void test62() throws Exception {
        // whitespace, but does not need to be encoded
        String plain ="This is a example of a quoted=printable text file. There is no tt";
        String expected = "This is a example of a quoted=3Dprintable text file. There is no tt";

        assertEquals(expected, new QuotedPrintableCodec().encode(plain));
    }
    @Test
    public void test63() throws Exception {
        String plain = "Hello there!";
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("UnicodeBig");
        qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
        String encoded1 = qpcodec.encode(plain, "UnicodeBig");
        String encoded2 = qpcodec.encode(plain);
        assertEquals(encoded1, encoded2);
    }
    @Test
    public void test64() throws Exception {
        String plain = "Hello there!";
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("UnicodeBig");
        qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
        String encoded1 = qpcodec.encode(plain, "UnicodeBig");
        String encoded2 = qpcodec.encode(plain);
        assertEquals(encoded1, encoded2);
    }
    @Test
    public void test65() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "";
        String encoded = qpcodec.encode(plain);
        assertEquals("Encoding empty string test", 
            plain, encoded);
        assertEquals("Decoding empty string test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test66() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "<>&";
        String encoded = qpcodec.encode(plain);
        assertEquals("Encoding special characters test", 
            "=3C=3E=26", encoded);
        assertEquals("Decoding special characters test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test67() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "Hello World";
        String encoded = qpcodec.encode(plain);
        assertEquals("Encoding whitespace test", 
            plain, encoded);
        assertEquals("Decoding whitespace test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test68() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String encoded = qpcodec.encode(plain);
        assertEquals("Encoding long string test", 
            plain, encoded);
        assertEquals("Decoding long string test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test69() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "你好";
        String encoded = qpcodec.encode(plain);
        assertEquals("Encoding non-ASCII characters test", 
            "=E4=BD=A0=E5=A5=BD", encoded);
        assertEquals("Decoding non-ASCII characters test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test70() throws Exception {
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
    public void test71() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertNull("Null string quoted-printable encoding test", 
            qpcodec.encode(""));

        assertNull("Null string quoted-printable decoding test", 
            qpcodec.decode(""));
    }
    @Test
    public void test72() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there =\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Hello there =3D=0D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test73() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]|";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]|", encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test74() throws Exception {
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
    public void test75() {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("INVALID");
        String plain = "Hello there!";
        try {
            qpcodec.encode(plain);
            fail("We set the encoding to a bogus INVALID value, this shouldn't have worked.");
        } catch (EncoderException ee) {
            // Exception expected, test segment passes.
        }
        try {
            qpcodec.decode(plain);
            fail("We set the encoding to a bogus INVALID value, this shouldn't have worked.");
        } catch (DecoderException ee) {
            // Exception expected, test segment passes.
        }
    }
    @Test
    public void test76() throws Exception {
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
            qpcodec.encode( dObj );
            fail("Trying to url encode a Double object should cause an exception.");
        } catch (EncoderException ee) {
            // Exception expected, test segment passes.
        }
    }
    @Test
    public void test77() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        byte[] plain = new byte[0];
        byte[] encoded = qpcodec.encode(plain);
        assertEquals("Encoding a null string should return null", 
            null, encoded);
    }
    @Test
    public void test78() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="This is a example of a quoted=printable text file. There is no tt";
        String expected = "This is a example of a quoted=3Dprintable text file. There is no tt";

        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test79() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=3D=0D=0A", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
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
    }
    @Test
    public void test81() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String test = "";
        String result = qpcodec.encode(test, "charset");
        assertEquals("Result should be null", null, result);
    }
    @Test
    public void test82() throws Exception {
        // whitespace, but does not need to be encoded
        String plain ="This is a example of a quoted=printable text file. There is no tt";
        String expected = "This is a example of a quoted=3Dprintable text file. There is no tt";

        assertEquals(expected, new QuotedPrintableCodec().encode(plain));
    }
    @Test
    public void test83() throws Exception {
        String plain = "Hello there!";
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("UTF-16");
        qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
        String encoded1 = qpcodec.encode(plain, "UTF-16");
        String encoded2 = qpcodec.encode(plain);
        assertEquals(encoded1, encoded2);
    }
    @Test
    public void test84() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String emptyString = "";
        assertNull("Empty string quoted-printable encoding test", qpcodec.encode(emptyString));
        assertNull("Empty string quoted-printable decoding test", qpcodec.decode(emptyString));
        
        String nullString = null;
        assertNull("Null string quoted-printable encoding test", qpcodec.encode(nullString));
        assertNull("Null string quoted-printable decoding test", qpcodec.decode(nullString));
    }
    @Test
    public void test85() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String nonAsciiString = "你好";
        String expectedEncoded = "=E4=BD=A0=E5=A5=BD";
        String expectedDecoded = "你好";
        
        assertEquals("Non-ASCII string quoted-printable encoding test", expectedEncoded, qpcodec.encode(nonAsciiString));
        assertEquals("Non-ASCII string quoted-printable decoding test", expectedDecoded, qpcodec.decode(expectedEncoded));
    }
    @Test
    public void test86() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String url = "https://www.google.com";
        String expectedEncoded = "https://www.google.com";
        
        assertEquals("URL quoted-printable encoding test", expectedEncoded, qpcodec.encode(url));
        assertEquals("URL quoted-printable decoding test", url, qpcodec.decode(expectedEncoded));
    }
    @Test
    public void test87() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String specialChars = "!@#$%^&*()_+{}[]|\\\"':;<>,.?/~`'";
        String expectedEncoded = specialChars;
        
        assertEquals("Special characters quoted-printable encoding test", expectedEncoded, qpcodec.encode(specialChars));
        assertEquals("Special characters quoted-printable decoding test", specialChars, qpcodec.decode(expectedEncoded));
    }
    @Test
    public void test88() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "";
        String encoded = qpcodec.encode(plain);
        assertEquals("Empty string quoted-printable encoding test", "", encoded);
        assertEquals("Empty string quoted-printable decoding test", plain, qpcodec.decode(encoded));
    }
    @Test
    public void test89() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "Jalapeño";
        String encoded = qpcodec.encode(plain);
        assertEquals("Non-ASCII characters quoted-printable encoding test", 
            "Jalape=C3=B1o", encoded);
        assertEquals("Non-ASCII characters quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test90() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "   Hello";
        String encoded = qpcodec.encode(plain);
        assertEquals("Leading whitespace quoted-printable encoding test", 
            "   Hello", encoded);
        assertEquals("Leading whitespace quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test91() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "Hello   ";
        String encoded = qpcodec.encode(plain);
        assertEquals("Trailing whitespace quoted-printable encoding test", 
            "Hello   ", encoded);
        assertEquals("Trailing whitespace quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test92() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "Hello    World";
        String encoded = qpcodec.encode(plain);
        assertEquals("Multiple spaces quoted-printable encoding test", 
            "Hello    World", encoded);
        assertEquals("Multiple spaces quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test93() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "!@#$%^&*()-_=+[]{}|;:',.<>/?";
        String encoded = qpcodec.encode(plain);
        assertEquals("Special characters quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Special characters quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test94() throws Exception {
        String qpdata = "If you believe that truth=3Dbeauty, then surely mathematics is the most " +
                "b=\r\neautiful branch of philosophy.";
        String expected = "If you believe that truth=beauty, then surely mathematics is the most " +
                "beautiful branch of philosophy.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        Object differentObject = new Object();
        try{
             qpcodec.encode(differentObject);
            fail("The given object is not a byte[] or String, should cause an exception.");
        }catch(EncoderException ee){
            // Exception expected, test segment passes
        }
    }
    @Test
    public void test95() throws Exception {
        String qpdata = "If you believe that truth=3Dbeauty, then surely mathematics is the most " +
                "b=\r\neautiful branch of philosophy.";
        String expected = "If you believe that truth=beauty, then surely mathematics is the most " +
                "beautiful branch of philosophy.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String differentString = "This is a different string";
        try{
            qpcodec.encode(differentString);
            fail("The given string is not the same as expected string, should cause an exception.");
        }catch(EncoderException ee){
            // Exception expected, test segment passes
        }
    }
    @Test
    public void test96() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String differentString = "This is a different string";
        try{
            String encoded = qpcodec.encode(differentString);
            fail("The given string is not the same as original string, should cause an exception.");
        }catch(EncoderException ee){
            // Exception expected, test segment passes
        }
        
        try {
            qpcodec.decode(differentString);
            fail("The given string is not the same as original string, should cause an exception.");
        } catch (DecoderException ee) {
            // Exception expected, test segment passes.
        }
    }
    @Test
    public void test97() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="This is a example of a quoted-printable text file. There is no end to it\t";
        String expected = "This is a example of a quoted-printable text file. There is no end to i=\r\nt=09";

        String differentString = "This is a different string";
        try{
            String encoded = qpcodec.encode(differentString);
            fail("The given string is not the same as expected string, should cause an exception.");
        }catch(EncoderException ee){
            // Exception expected, test segment passes
        }
        
        plain ="This is a example of a quoted-printable text file. There is no end to it ";
        expected = "This is a example of a quoted-printable text file. There is no end to i=\r\nt=20";

        String differentString1 = "This is a different string";
        try{
            String encoded = qpcodec.encode(differentString1);
            fail("The given string is not the same as expected string, should cause an exception.");
        }catch(EncoderException ee){
            // Exception expected, test segment passes
        }

        // whitespace before soft break
        plain ="This is a example of a quoted-printable text file. There is no end to   ";
        expected = "This is a example of a quoted-printable text file. There is no end to=20=\r\n =20";

        String differentString2 = "This is a different string";
        try{
            String encoded = qpcodec.encode(differentString2);
            fail("The given string is not the same as expected string, should cause an exception.");
        }catch(EncoderException ee){
            // Exception expected, test segment passes
        }
        
        // non-printable character before soft break
        plain ="This is a example of a quoted-printable text file. There is no end to=  ";
        expected = "This is a example of a quoted-printable text file. There is no end to=3D=\r\n =20";

        String differentString3 = "This is a different string";
        try{
            String encoded = qpcodec.encode(differentString3);
            fail("The given string is not the same as expected string, should cause an exception.");
        }catch(EncoderException ee){
            // Exception expected, test segment passes
        }
    }
    @Test
    public void test98() {
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
    public void test99() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=\r\n";
        
        String differentString = "This is a different string";
        try{
            String encoded = qpcodec.encode(differentString);
            fail("The given string is not the same as expected string, should cause an exception.");
        }catch(EncoderException ee){
            // Exception expected, test segment passes
        }
        
        try {
            qpcodec.decode(differentString);
            fail("The given string is not the same as expected string, should cause an exception.");
        } catch (DecoderException ee) {
            // Exception expected, test segment passes.
        }
    }
    @Test
    public void test100() throws Exception {

        String ru_msg = constructString(RUSSIAN_STUFF_UNICODE); 
        String ch_msg = constructString(SWISS_GERMAN_STUFF_UNICODE); 
        
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        
        assertEquals(
            "=D0=92=D1=81=D0=B5=D0=BC_=D0=BF=D1=80=D0=B8=D0=B2=D0=B5=D1=82", 
        qpcodec.encode(ru_msg, CharEncoding.UTF_8)
        );
        String differentString1 = "This is a different string";
        try{
            String differentEncoded = qpcodec.encode(differentString1, CharEncoding.UTF_8);
            fail("The given string is not the same as expected string, should cause an exception.");
        }catch(EncoderException ee){
            // Exception expected, test segment passes
        }
        
        assertEquals("Gr=C3=BCezi_z=C3=A4m=C3=A4", qpcodec.encode(ch_msg, CharEncoding.UTF_8));
        
        String differentString2 = "This is a different string";
        try{
            String differentEncoded = qpcodec.encode(differentString2, CharEncoding.UTF_8);
            fail("The given string is not the same as expected string, should cause an exception.");
        }catch(EncoderException ee){
            // Exception expected, test segment passes
        }
        
        assertEquals(ru_msg, qpcodec.decode(qpcodec.encode(ru_msg, CharEncoding.UTF_8), CharEncoding.UTF_8));
        
        String differentString3 = "This is a different string";
        try{
            String differentDecoded = qpcodec.decode(differentString3, CharEncoding.UTF_8);
            fail("The given string is not the same as expected string, should cause an exception.");
        }catch(DecoderException ee){
            // Exception expected, test segment passes
        }
        
        assertEquals(ch_msg, qpcodec.decode(qpcodec.encode(ch_msg, CharEncoding.UTF_8), CharEncoding.UTF_8));
        
        String differentString4 = "This is a different string";
        try{
            String differentDecoded = qpcodec.decode(differentString4, CharEncoding.UTF_8);
            fail("The given string is not the same as expected string, should cause an exception.");
        }catch(DecoderException ee){
            // Exception expected, test segment passes
        }
    }
    @Test
    public void test101() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String test = null;
        String differentString = "This is a different string";
        try{
            String result = qpcodec.encode( differentString, "charset" );
            fail("The given string is not the same as expected string, should cause an exception.");
        }catch(EncoderException ee){
            // Exception expected, test segment passes
        }
    }
    @Test
    public void test102() throws Exception {
        // whitespace, but does not need to be encoded
        String plain ="This is a example of a quoted=printable text file. There is no tt";
        String expected = "This is a example of a quoted=3Dprintable text file. There is no tt";

        String differentString = "This is a different string";
        try{
            String encoded = new QuotedPrintableCodec().encode(differentString);
            fail("The given string is not the same as expected string, should cause an exception.");
        }catch(EncoderException ee){
            // Exception expected, test segment passes
        }
    }
    @Test
    public void test103() throws Exception {
        String plain = "Hello there!";
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("UnicodeBig");
        qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
        String encoded1 = qpcodec.encode(plain, "UnicodeBig");
        String encoded2 = qpcodec.encode(plain);
        assertEquals(encoded1, encoded2);
        
        String differentString = "This is a different string";
        try{
            qpcodec.encode(differentString);
            fail("The given string is not the same as expected string, should cause an exception.");
        }catch(EncoderException ee){
            // Exception expected, test segment passes
        }
    }
    @Test
    public void test104() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertNull("Null string quoted-printable encoding test", 
            qpcodec.encode((String)null));
        assertNull("Null string quoted-printable decoding test", 
            qpcodec.decode((String)null));
        
        // Regression test 1
        Object pObject = new ArrayList<>();
        try {
            qpcodec.decode(pObject);
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
    }
    @Test
    public void test105() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there =\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Hello there =3D=0D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        
        // Regression test 2
        String pObject = "= Test regression test =\r\n";
        assertEquals("Basic quoted-printable decoding test", 
            pObject, qpcodec.decode(pObject));
    }
    @Test
    public void test106() throws Exception {
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
public void test107() {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String expected = "UTF-8";
    
    assertEquals(expected, qpcodec.getDefaultCharset());
}
@Test
public void test108() {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("ISO-8859-1");
    String expected = "ISO-8859-1";
    
    assertEquals(expected, qpcodec.getDefaultCharset());
}
@Test
public void test109() {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec(null);
    String expected = "UTF-8";
    
    assertEquals(expected, qpcodec.getDefaultCharset());
}
    @Test
    public void test110() throws Exception {
        String qpdata = "If you believe that truth=3Dbeauty, then surely mathematics is the most " +
                "b=\r\neautiful branch of philosophy.";
        String expected = "If you believe that truth=beauty, then surely mathematics is the most " +
                "beautiful branch of philosophy.";

        // Replace the last character with a different one
        qpdata = qpdata.substring(0, qpdata.length() - 1) + "!";
        
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(qpdata, qpcodec.encode(expected));

        String decoded = qpcodec.decode(qpdata);
        assertEquals(qpdata, qpcodec.encode(decoded));
    }
    @Test
    public void test111() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        
        // Add a character in the middle of the string
        String plain = "= Hellmo there =\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", "=3D Hellmo there =3D=0D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test", plain, qpcodec.decode(encoded));
    }
    @Test
    public void test112() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        
        // Add a special character at the beginning of the string
        String plain = "#abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", plain, qpcodec.decode(encoded));
    }
    @Test
    public void test113() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        // Add an extra character after the soft break
        String plain ="This is a example of a quoted-printable text file. There is no end to it\t ";
        String expected = "This is a example of a quoted-printable text file. There is no end to i=\r\nt=09 ";

        assertEquals(expected, qpcodec.encode(plain));

        // Add an extra character after the space
        plain ="This is a example of a quoted-printable text file. There is no end to it  ";
        expected = "This is a example of a quoted-printable text file. There is no end to i=\r\nt=20 ";

        assertEquals(expected, qpcodec.encode(plain));

        // Add an extra space before the soft break
        plain ="This is a example of a quoted-printable text file. There is no end to   ";
        expected = "This is a example of a quoted-printable text file. There is no end to=20=\r\n =20 ";

        assertEquals(expected, qpcodec.encode(plain));

        // Add an extra non-printable character before the soft break
        plain ="This is a example of a quoted-printable text file. There is no end to=   ";
        expected = "This is a example of a quoted-printable text file. There is no end to=3D=\r\n =20 ";

        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test114() {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("NONSENSE");

        // Replace the string with an unsupported encoding
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
    public void test115() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        // Add an extra character after the equals sign
        String plain ="This is a example of a quoted-printable text file. This might contain sp= cial chars.";
        String expected = "This is a example of a quoted-printable text file. This might contain sp=3D=\r\ncial chars.";
        assertEquals(expected, qpcodec.encode(plain));

        // Add an extra tab after the first tab
        plain ="This is a example of a quoted-printable text file. This might contain tab\tbs as well.";
        expected = "This is a example of a quoted-printable text file. This might contain tab=09=\r\nbs as well.";
        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test116() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        // Add an extra equals sign at the beginning of the string
        String plain = "=0\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", "=3D=0D=0A", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", plain, qpcodec.decode(encoded));
    }
    @Test
    public void test117() throws Exception {

        // Modify the Russian and Swiss-German strings
        String ru_msg = constructString(RUSSIAN_STUFF_UNICODE) + " ";
        String ch_msg = constructString(SWISS_GERMAN_STUFF_UNICODE) + " ";
        
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        
        assertEquals("=D0=92=D1=81=D0=B5=D0=BC_=D0=BF=D1=80=D0=B8=D0=B2=D0=B5=D1=82 ", qpcodec.encode(ru_msg, CharEncoding.UTF_8));
        assertEquals("Gr=C3=BCezi_z=C3=A4m=C3=A4 ", qpcodec.encode(ch_msg, CharEncoding.UTF_8));
        
        assertEquals(ru_msg, qpcodec.decode(qpcodec.encode(ru_msg, CharEncoding.UTF_8), CharEncoding.UTF_8));
        assertEquals(ch_msg, qpcodec.decode(qpcodec.encode(ch_msg, CharEncoding.UTF_8), CharEncoding.UTF_8));
    }
    @Test
    public void test118() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        
        // Replace the charset with "null"
        String test = null;
        String result = qpcodec.encode(test, null);
        assertEquals("Result should be null", null, result);
    }
    @Test
    public void test119() throws Exception {
        String plain = "Hello there!";
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("UnicodeBig");
        qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
        
        // Replace the default encoding with an unsupported encoding
        String encoded1 = qpcodec.encode(plain, "UTF-16");
        String encoded2 = qpcodec.encode(plain);
        assertEquals(encoded1, encoded2);
    }
}