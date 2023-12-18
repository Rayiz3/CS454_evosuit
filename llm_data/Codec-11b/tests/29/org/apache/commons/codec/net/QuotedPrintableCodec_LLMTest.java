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
        
        // Change input to contain special characters
        plain = "abc123_-.*~!@#$%^&()[]{},.;:?/\\";
        encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding regression test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding regression test", 
            plain, qpcodec.decode(encoded));

        // Change input to contain space
        plain = "abc123_-.*~!@# $%^&()[]{},.;:?/\\";
        encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding regression test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding regression test", 
            plain, qpcodec.decode(encoded));

        // Change input to contain capitalized letters
        plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]ABCDE";
        encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding regression test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding regression test", 
            plain, qpcodec.decode(encoded));

        // Change input to contain lowercase letters
        plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]abcde";
        encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding regression test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding regression test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test1() throws Exception {
        // whitespace, but does not need to be encoded
        String plain ="This is a example of a quoted=printable text file. There is no tt";
        String expected = "This is a example of a quoted=3Dprintable text file. There is no tt";

        assertEquals(expected, new QuotedPrintableCodec().encode(plain));
        
        // Change input to contain special characters
        plain = "This is a example of a quoted=printable text file. There is no tt!";
        expected = "This is a example of a quoted=3Dprintable text file. There is no tt!";
        
        assertEquals(expected, new QuotedPrintableCodec().encode(plain));
        
        // Change input to contain digits
        plain = "This is a example of a quoted=printable text file. There is no 123";
        expected = "This is a example of a quoted=3Dprintable text file. There is no 123";
        
        assertEquals(expected, new QuotedPrintableCodec().encode(plain));
    }
    @Test
    public void test2() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="This is a example of a quoted-printable text file. This might contain sp=cial chars.";
        String expected = "This is a example of a quoted-printable text file. This might contain sp=3D=\r\ncial chars.";
        assertEquals(expected, qpcodec.encode(plain));
        
        // Change input to contain digits
        plain ="This is a example of a quoted-printable text file. This might contain sp=cial chars 123.";
        expected = "This is a example of a quoted-printable text file. This might contain sp=3D=\r\ncial chars 123.";
        assertEquals(expected, qpcodec.encode(plain));
        
        // Change input to contain capitalized A-Z letters
        plain ="This is a example of a quoted-printable text file. This might contain sp=cial chars ABC.";
        expected = "This is a example of a quoted-printable text file. This might contain sp=3D=\r\ncial chars ABC.";
        assertEquals(expected, qpcodec.encode(plain));
        
        // Change input to contain lowercase a-z letters
        plain ="This is a example of a quoted-printable text file. This might contain sp=cial chars abc.";
        expected = "This is a example of a quoted-printable text file. This might contain sp=3D=\r\ncial chars abc.";
        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test3() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        
        // Change input to empty byte array
        byte[] plain = new byte[0];
        byte[] encoded = qpcodec.encode(plain);
        assertEquals("Encoding an empty byte array should return an empty byte array", 
            0, encoded.length);
    }
    @Test
    public void test4() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="This is a example of a quoted-printable text file. There is no end to it\t";
        String expected = "This is a example of a quoted-printable text file. There is no end to i=\r\nt=09";

        assertEquals(expected, qpcodec.encode(plain));
        
        // Change input to contain special characters
        plain ="This is a example of a quoted-printable text file. There is no end to it@";
        expected = "This is a example of a quoted-printable text file. There is no end to i=\r\nt@";
        assertEquals(expected, qpcodec.encode(plain));
        
        // Change input to contain digits
        plain ="This is a example of a quoted-printable text file. There is no end to it123";
        expected = "This is a example of a quoted-printable text file. There is no end to i=\r\nt123";
        assertEquals(expected, qpcodec.encode(plain));
        
        // Change input to contain capitalized A-Z letters
        plain ="This is a example of a quoted-printable text file. There is no end to itABC";
        expected = "This is a example of a quoted-printable text file. There is no end to i=\r\ntABC";
        assertEquals(expected, qpcodec.encode(plain));
        
        // Change input to contain lowercase a-z letters
        plain ="This is a example of a quoted-printable text file. There is no end to itabc";
        expected = "This is a example of a quoted-printable text file. There is no end to i=\r\ntabc";
        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test5() throws Exception {
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
        
        // Change input to contain special characters
        plain = "test!@#$%^&*()_+-=,./<>?`~\"';:[]{}|";
        encoded = (String) qpcodec.encode((Object) plain);
        assertEquals("Basic quoted-printable encoding regression test", 
            plain, encoded);

        // Change input to contain digits
        plain = "test0123456789";
        encoded = (String) qpcodec.encode((Object) plain);
        assertEquals("Basic quoted-printable encoding regression test", 
            plain, encoded);

        // Change input to contain capitalized A-Z letters
        plain = "ABCDEF";
        encoded = (String) qpcodec.encode((Object) plain);
        assertEquals("Basic quoted-printable encoding regression test", 
            plain, encoded);

        // Change input to contain lowercase a-z letters
        plain = "abcdef";
        encoded = (String) qpcodec.encode((Object) plain);
        assertEquals("Basic quoted-printable encoding regression test", 
            plain, encoded);
    }
    @Test
    public void test6() throws Exception {
        String plain = "Hello there!";
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("UnicodeBig");
        qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
        String encoded1 = qpcodec.encode(plain, "UnicodeBig");
        String encoded2 = qpcodec.encode(plain);
        assertEquals(encoded1, encoded2);
        
        // Change input to contain special characters
        plain = "Hello there!@#$%^&*()_+-=,./<>?`~\"';:[]{}|";
        qpcodec = new QuotedPrintableCodec("UnicodeBig");
        qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
        encoded1 = qpcodec.encode(plain, "UnicodeBig");
        encoded2 = qpcodec.encode(plain);
        assertEquals(encoded1, encoded2);

        // Change input to contain digits
        plain = "Hello there!0123456789";
        qpcodec = new QuotedPrintableCodec("UnicodeBig");
        qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
        encoded1 = qpcodec.encode(plain, "UnicodeBig");
        encoded2 = qpcodec.encode(plain);
        assertEquals(encoded1, encoded2);

        // Change input to contain capitalized A-Z letters
        plain = "Hello there!ABCDEF";
        qpcodec = new QuotedPrintableCodec("UnicodeBig");
        qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
        encoded1 = qpcodec.encode(plain, "UnicodeBig");
        encoded2 = qpcodec.encode(plain);
        assertEquals(encoded1, encoded2);

        // Change input to contain lowercase a-z letters
        plain = "Hello there!abcdef";
        qpcodec = new QuotedPrintableCodec("UnicodeBig");
        qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
        encoded1 = qpcodec.encode(plain, "UnicodeBig");
        encoded2 = qpcodec.encode(plain);
        assertEquals(encoded1, encoded2);
    }
    @Test
    public void test7() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "1+1 = 2";
        String encoded = new String(QuotedPrintableCodec.
            encodeQuotedPrintable(null, plain.getBytes("UTF-8")));
        assertEquals("Basic quoted-printable encoding test", 
            "1+1 =3D 2", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        
        // Change input to contain special characters
        plain = "1+1 = 2!@#$%^&*()_+=,./<>?`~\"';:[]{}|";
        encoded = new String(QuotedPrintableCodec.
            encodeQuotedPrintable(null, plain.getBytes("UTF-8")));
        assertEquals("Basic quoted-printable encoding regression test", 
            plain, encoded);
        assertEquals("Basic quoted-printable decoding regression test", 
            plain, qpcodec.decode(encoded));

        // Change input to contain digits
        plain = "1+1 = 2!@#$%^&*()_+=,./<>?`~\"';:[]{}|1234567890";
        encoded = new String(QuotedPrintableCodec.
            encodeQuotedPrintable(null, plain.getBytes("UTF-8")));
        assertEquals("Basic quoted-printable encoding regression test", 
            plain, encoded);
        assertEquals("Basic quoted-printable decoding regression test", 
            plain, qpcodec.decode(encoded));

        // Change input to contain capitalized A-Z letters
        plain = "1+1 = 2!@#$%^&*()_+=,./<>?`~\"';:[]{}|ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        encoded = new String(QuotedPrintableCodec.
            encodeQuotedPrintable(null, plain.getBytes("UTF-8")));
        assertEquals("Basic quoted-printable encoding regression test", 
            plain, encoded);
        assertEquals("Basic quoted-printable decoding regression test", 
            plain, qpcodec.decode(encoded));

        // Change input to contain lowercase a-z letters
        plain = "1+1 = 2!@#$%^&*()_+=,./<>?`~\"';:[]{}|abcdefghijklmnopqrstuvwxyz";
        encoded = new String(QuotedPrintableCodec.
            encodeQuotedPrintable(null, plain.getBytes("UTF-8")));
        assertEquals("Basic quoted-printable encoding regression test", 
            plain, encoded);
        assertEquals("Basic quoted-printable decoding regression test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test8() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=3D=0D=0A", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));

        // Change input to contain special characters
        plain = "=\r\n!@#$%^&*()_+=,./<>?`~\"';:[]{}|";
        encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding regression test", 
            "=3D=0D=0A!@#$%^&*()_+=,./<>?`~\"';:[]{}|", encoded);
        assertEquals("Unsafe chars quoted-printable decoding regression test", 
            plain, qpcodec.decode(encoded));

        // Change input to contain digits
        plain = "=\r\n!@#$%^&*()_+=,./<>?`~\"';:[]{}|1234567890";
        encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding regression test", 
            "=3D=0D=0A!@#$%^&*()_+=,./<>?`~\"';:[]{}|1234567890", encoded);
        assertEquals("Unsafe chars quoted-printable decoding regression test", 
            plain, qpcodec.decode(encoded));

        // Change input to contain capitalized A-Z letters
        plain = "=\r\n!@#$%^&*()_+=,./<>?`~\"';:[]{}|ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding regression test", 
            "=3D=0D=0A!@#$%^&*()_+=,./<>?`~\"';:[]{}|ABCDEFGHIJKLMNOPQRSTUVWXYZ", encoded);
        assertEquals("Unsafe chars quoted-printable decoding regression test", 
            plain, qpcodec.decode(encoded));

        // Change input to contain lowercase a-z letters
        plain = "=\r\n!@#$%^&*()_+=,./<>?`~\"';:[]{}|abcdefghijklmnopqrstuvwxyz";
        encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding regression test", 
            "=3D=0D=0A!@#$%^&*()_+=,./<>?`~\"';:[]{}|abcdefghijklmnopqrstuvwxyz", encoded);
        assertEquals("Unsafe chars quoted-printable decoding regression test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test9() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there =\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Hello there =3D=0D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        
        // Change input to contain special characters
        plain = "= Hello there =\r\n!@#$%^&*()_+=,./<>?`~\"';:[]{}|";
        encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding regression test", 
            "=3D Hello there =3D=0D=0A!@#$%^&*()_+=,./<>?`~\"';:[]{}|", encoded);
        assertEquals("Basic quoted-printable decoding regression test", 
            plain, qpcodec.decode(encoded));

        // Change input to contain digits
        plain = "= Hello there =\r\n!@#$%^&*()_+=,./<>?`~\"';:[]{}|1234567890";
        encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding regression test", 
            "=3D Hello there =3D=0D=0A!@#$%^&*()_+=,./<>?`~\"';:[]{}|1234567890", encoded);
        assertEquals("Basic quoted-printable decoding regression test", 
            plain, qpcodec.decode(encoded));

        // Change input to contain capitalized A-Z letters
        plain = "= Hello there =\r\n!@#$%^&*()_+=,./<>?`~\"';:[]{}|ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding regression test", 
            "=3D Hello there =3D=0D=0A!@#$%^&*()_+=,./<>?`~\"';:[]{}|ABCDEFGHIJKLMNOPQRSTUVWXYZ", encoded);
        assertEquals("Basic quoted-printable decoding regression test", 
            plain, qpcodec.decode(encoded));

        // Change input to contain lowercase a-z letters
        plain = "= Hello there =\r\n!@#$%^&*()_+=,./<>?`~\"';:[]{}|abcdefghijklmnopqrstuvwxyz";
        encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding regression test", 
            "=3D Hello there =3D=0D=0A!@#$%^&*()_+=,./<>?`~\"';:[]{}|abcdefghijklmnopqrstuvwxyz", encoded);
        assertEquals("Basic quoted-printable decoding regression test", 
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
        assertEquals("Gr=C3=BCezi_z=C3=A4m=C3=A4", qpcodec.encode(ch_msg, CharEncoding.UTF_8));
        
        assertEquals(ru_msg, qpcodec.decode(qpcodec.encode(ru_msg, CharEncoding.UTF_8), CharEncoding.UTF_8));
        assertEquals(ch_msg, qpcodec.decode(qpcodec.encode(ch_msg, CharEncoding.UTF_8), CharEncoding.UTF_8));
        
        // Change input to contain special characters
        ru_msg = constructString(RUSSIAN_STUFF_UNICODE) + "!@#$%^&*()_+=,./<>?`~\"';:[]{}|";
        ch_msg = constructString(SWISS_GERMAN_STUFF_UNICODE) + "!@#$%^&*()_+=,./<>?`~\"';:[]{}|";
        
        assertEquals(
            "=D0=92=D1=81=D0=B5=D0=BC_=D0=BF=D1=80=D0=B8=D0=B2=D0=B5=D1=82!@#$%^&*()_+=,./<>?`~\"';:[]{}|", 
        qpcodec.encode(ru_msg, CharEncoding.UTF_8)
        );
        assertEquals("Gr=C3=BCezi_z=C3=A4m=C3=A4!@#$%^&*()_+=,./<>?`~\"';:[]{}|", qpcodec.encode(ch_msg, CharEncoding.UTF_8));
        
        assertEquals(ru_msg, qpcodec.decode(qpcodec.encode(ru_msg, CharEncoding.UTF_8), CharEncoding.UTF_8));
        assertEquals(ch_msg, qpcodec.decode(qpcodec.encode(ch_msg, CharEncoding.UTF_8), CharEncoding.UTF_8));
    }
    @Test
    public void test11() throws Exception {
        String qpdata = "If you believe that truth=3Dbeauty, then surely mathematics is the most " +
                "b=\r\neautiful branch of philosophy.";
        String expected = "If you believe that truth=beauty, then surely mathematics is the most " +
                "beautiful branch of philosophy.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(qpdata, qpcodec.encode(expected));

        String decoded = qpcodec.decode(qpdata);
        assertEquals(qpdata, qpcodec.encode(decoded));
        
        // Change input to contain special characters
        qpdata = "If you believe that truth=3Dbeauty, then surely mathematics is the most " +
                "b=\r\neautiful branch of philosophy.!@#$%^&*()_+=,./<>?`~\"';:[]{}|";
        expected = "If you believe that truth=beauty, then surely mathematics is the most " +
                "beautiful branch of philosophy.!@#$%^&*()_+=,./<>?`~\"';:[]{}|";

        assertEquals(expected, qpcodec.encode(qpdata));

        decoded = qpcodec.decode(expected);
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
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]1234567890";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars with numbers quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars with numbers quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test14() throws Exception {
        // whitespace, but does not need to be encoded
        String plain ="This is a example of a quoted=printable text file. There is no tt";
        String expected = "This is a example of a quoted=3Dprintable text file. There is no tt";

        assertEquals(expected, new QuotedPrintableCodec().encode(plain));
    }
    @Test
    public void test15() throws Exception {
        // whitespace, but does not need to be encoded
        String plain ="This is a example of a quoted=printable text file! There is no tt";
        String expected = "This is a example of a quoted=3Dprintable text file! There is no tt";

        assertEquals(expected, new QuotedPrintableCodec().encode(plain));
    }
    @Test
    public void test16() throws Exception {
        // whitespace, but does not need to be encoded
        String plain ="This is a example of a quoted=printable text file. There is no tt 1234567890";
        String expected = "This is a example of a quoted=3Dprintable text file. There is no tt 1234567890";

        assertEquals(expected, new QuotedPrintableCodec().encode(plain));
    }
    @Test
    public void test17() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="This is a example of a quoted-printable text file. This might contain sp=cial chars.";
        String expected = "This is a example of a quoted-printable text file. This might contain sp=3D=\r\ncial chars.";
        assertEquals(expected, qpcodec.encode(plain));

        plain ="This is a example of a quoted-printable text file! This might contain sp=cial chars.";
        expected = "This is a example of a quoted-printable text file! This might contain sp=3D=\r\ncial chars.";
        assertEquals(expected, qpcodec.encode(plain));

        plain ="This is a example of a quoted-printable text file? This might contain sp=cial chars.";
        expected = "This is a example of a quoted-printable text file? This might contain sp=3D=\r\ncial chars.";
        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test18() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="This is a example of a quoted-printable text file. This might contain sp=cial chars. 1234567890";
        String expected = "This is a example of a quoted-printable text file. This might contain sp=3D=\r\ncial chars. 1234567890";
        assertEquals(expected, qpcodec.encode(plain));

        plain ="This is a example of a quoted-printable text file! This might contain sp=cial chars. 1234567890";
        expected = "This is a example of a quoted-printable text file! This might contain sp=3D=\r\ncial chars. 1234567890";
        assertEquals(expected, qpcodec.encode(plain));

        plain ="This is a example of a quoted-printable text file? This might contain sp=cial chars. 1234567890";
        expected = "This is a example of a quoted-printable text file? This might contain sp=3D=\r\ncial chars. 1234567890";
        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test19() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        byte[] plain = null;
        byte[] encoded = qpcodec.encode(plain);
        assertEquals("Encoding a null string should return null", 
            null, encoded);
    }
    @Test
    public void test20() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="This is a example of a quoted-printable text file. There is no end to it\t";
        String expected = "This is a example of a quoted-printable text file. There is no end to i=\r\nt=09";

        assertEquals(expected, qpcodec.encode(plain));

        plain ="This is a example of a quoted-printable text file. There is no end to it ";
        expected = "This is a example of a quoted=printable text file. There is no end to i=\r\nt=20";

        assertEquals(expected, qpcodec.encode(plain));

        // whitespace before soft break
        plain ="This is a example of a quoted-printable text file. There is no end to   ";
        expected = "This is a example of a quoted=printable text file. There is no end to=20=\r\n =20";

        assertEquals(expected, qpcodec.encode(plain));

        // non-printable character before soft break
        plain ="This is a example of a quoted-printable text file. There is no end to=  ";
        expected = "This is a example of a quoted=printable text file. There is no end to=3D=\r\n =20";

        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test21() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="This is a example of a quoted-printable text file. There is no end to it 1234567890";
        String expected = "This is a example of a quoted=printable text file. There is no end to i=\r\nt=20 1234567890";

        assertEquals(expected, qpcodec.encode(plain));

        plain ="This is a example of a quoted-printable text file. There is no end to it  1234567890";
        expected = "This is a example of a quoted=printable text file. There is no end to i=\r\nt=20 =20 1234567890";

        assertEquals(expected, qpcodec.encode(plain));

        // whitespace before soft break
        plain ="This is a example of a quoted-printable text file. There is no end to   1234567890";
        expected = "This is a example of a quoted=printable text file. There is no end to=20=\r\n =20 1234567890";

        assertEquals(expected, qpcodec.encode(plain));

        // non-printable character before soft break
        plain ="This is a example of a quoted-printable text file. There is no end to=  1234567890";
        expected = "This is a example of a quoted=printable text file. There is no end to=3D=\r\n =20 1234567890";

        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test22() throws Exception {
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
    public void test23() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "1+1 = 2 and 1-1 = 0";
        String encoded = (String) qpcodec.encode((Object) plain);
        assertEquals("Basic quoted-printable encoding test", 
            "1+1 =3D 2 and 1-1 =3D 0", encoded);

        byte[] plainBA = plain.getBytes("UTF-8");
        byte[] encodedBA = (byte[]) qpcodec.encode((Object) plainBA);
        encoded = new String(encodedBA);
        assertEquals("Basic quoted-printable encoding test", 
            "1+1 =3D 2 and 1-1 =3D 0", encoded);
            
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
    public void test24() throws Exception {
        String plain = "Hello there!";
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("UnicodeBig");
        qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
        String encoded1 = qpcodec.encode(plain, "UnicodeBig");
        String encoded2 = qpcodec.encode(plain);
        assertEquals(encoded1, encoded2);
    }
    @Test
    public void test25() throws Exception {
        String plain = "Hello there! 1234567890";
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("UnicodeBig");
        qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
        String encoded1 = qpcodec.encode(plain, "UnicodeBig");
        String encoded2 = qpcodec.encode(plain);
        assertEquals(encoded1, encoded2);
    }
    @Test
    public void test26() throws Exception {
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
    public void test27() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=3D=0D=0A", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test28() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=\r\n and 1234567890";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars with numbers quoted-printable encoding test", 
            "=3D=0D=0A and 1234567890", encoded);
        assertEquals("Unsafe chars with numbers quoted-printable decoding test", 
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
        String plain = "= Hello there =\r\n and 1234567890";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Hello there =3D=0D=0A and 1234567890", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test31() throws Exception {

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
    public void test32() throws Exception {

        String ru_msg = constructString(RUSSIAN_STUFF_UNICODE); 
        String ch_msg = constructString(SWISS_GERMAN_STUFF_UNICODE); 
        String appendedString = " 1234567890";
        
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        
        assertEquals(
            "=D0=92=D1=81=D0=B5=D0=BC_=D0=BF=D1=80=D0=B8=D0=B2=D0=B5=D1=82" + appendedString, 
        qpcodec.encode(ru_msg + appendedString, CharEncoding.UTF_8)
        );
        assertEquals("Gr=C3=BCezi_z=C3=A4m=C3=A4" + appendedString, qpcodec.encode(ch_msg + appendedString, CharEncoding.UTF_8));
        
        assertEquals(ru_msg + appendedString, qpcodec.decode(qpcodec.encode(ru_msg + appendedString, CharEncoding.UTF_8), CharEncoding.UTF_8));
        assertEquals(ch_msg + appendedString, qpcodec.decode(qpcodec.encode(ch_msg + appendedString, CharEncoding.UTF_8), CharEncoding.UTF_8));
    }
    @Test
    public void test33() throws Exception {
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
    public void test34() throws Exception {
        String qpdata = "If you believe that truth=3Dbeauty, then surely mathematics is the most " +
                "beautiful branch of philosophy. 1234567890";
        String expected = "If you believe that truth=beauty, then surely mathematics is the most " +
                "beautiful branch of philosophy. 1234567890";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(qpdata, qpcodec.encode(expected));

        String decoded = qpcodec.decode(qpdata);
        assertEquals(qpdata, qpcodec.encode(decoded));
    }
    @Test
    public void test35() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";

        // Test case where special characters are encoded as separate octets
        String encoded = qpcodec.encode("abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]");
        assertEquals("Safe chars quoted-printable encoding test - separate octets", 
            "=61=62=63=31=32=33=5F=2D=2E=2A=7E=21=40=23=24=25=5E=26=28=29=2B=7B=7D=22=5C=3B=3A=60=2C=2F=5B=5D", 
            encoded);
        assertEquals("Safe chars quoted-printable decoding test - separate octets", 
            plain, qpcodec.decode(encoded));

        // Test case where special characters are encoded as single octets
        encoded = qpcodec.encode("abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]", "UTF-8");
        assertEquals("Safe chars quoted-printable encoding test - single octets", 
            encoded, "=abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]");
        assertEquals("Safe chars quoted-printable decoding test - single octets", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test36() throws Exception {
        byte[] result = QuotedPrintableCodec.decodeQuotedPrintable(null);
        assertEquals("Result should be null", null, result);

        // Test case with an empty array
        result = QuotedPrintableCodec.decodeQuotedPrintable(new byte[0]);
        assertEquals("Result should be an empty array", 0, result.length);
    }
    @Test
    public void test37() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=\r\n";

        // Test case where CR and LF are encoded as separate octets
        String encoded = qpcodec.encode("=\r\n");
        assertEquals("Unsafe chars quoted-printable encoding test - separate octets", 
            "=3D=0D=0A", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test - separate octets", 
            plain, qpcodec.decode(encoded));

        // Test case where CR and LF are encoded as single octets
        encoded = qpcodec.encode("=\r\n", "UTF-8");
        assertEquals("Unsafe chars quoted-printable encoding test - single octets", 
            encoded, "=3D=0A");
        assertEquals("Unsafe chars quoted-printable decoding test - single octets", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test38() throws Exception {
        String qpdata = "CRLF in an\n encoded text should be=20=\r\n\rskipped in the\r decoding.";
        String expected = "CRLF in an encoded text should be skipped in the decoding.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        
        // Test case where CRLF is not preceded with a hex-encoded octet
        assertEquals(expected, qpcodec.decode(qpdata));

        // Test case where CRLF is preceded with a hex-encoded octet
        String encoded = qpcodec.encode(expected);
        assertEquals(expected, qpcodec.decode(encoded));
    }
    @Test
    public void test39() throws Exception {
        String qpdata = "If you believe that truth=3Dbeauty, then surely=20=\r\nmathematics " +
                "is the most beautiful branch of philosophy.";
        String expected = "If you believe that truth=beauty, then surely mathematics " +
                "is the most beautiful branch of philosophy.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        
        // Test case where a soft line break is preceded with a hex-encoded octet
        assertEquals(expected, qpcodec.decode(qpdata));

        // Test case where a soft line break is not preceded with a hex-encoded octet
        String encoded = qpcodec.encode(expected);
        assertEquals(expected, qpcodec.decode(encoded));
    }
    @Test
    public void test40() {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("NONSENSE");
        String plain = "Hello there!";
        
        // Test case where the encoding parameter is set to an invalid value
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
    public void test41() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "1+1 = 2";

        // Test case with null BitSet
        String encoded = new String(QuotedPrintableCodec.encodeQuotedPrintable(null, plain.getBytes("UTF-8")));
        assertEquals("Basic quoted-printable encoding test - null BitSet", 
            "1+1 =3D 2", encoded);
        assertEquals("Basic quoted-printable decoding test - null BitSet", 
            plain, qpcodec.decode(encoded));
        
        // Test case with empty BitSet
        BitSet bitSet = new BitSet();
        encoded = new String(QuotedPrintableCodec.encodeQuotedPrintable(bitSet, plain.getBytes("UTF-8")));
        assertEquals("Basic quoted-printable encoding test - empty BitSet", 
            "1+1 =3D 2", encoded);
        assertEquals("Basic quoted-printable decoding test - empty BitSet", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test42() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "1+1 = 2";

        // Test case with a String object
        String decoded = (String) qpcodec.decode((Object) plain);
        assertEquals("Basic quoted-printable decoding test - String object", 
            "1+1 = 2", decoded);

        // Test case with a byte array object
        byte[] plainBA = plain.getBytes("UTF-8");
        byte[] decodedBA = (byte[]) qpcodec.decode((Object) plainBA);
        decoded = new String(decodedBA);
        assertEquals("Basic quoted-printable decoding test - byte array object", 
            "1+1 = 2", decoded);
        
        // Test case with a null object
        Object result = qpcodec.decode((Object) null);
        assertEquals( "Decoding a null Object should return null", null, result);
        
        // Test case with a Double object
        try {
            Object dObj = new Double(3.0);
            qpcodec.decode( dObj );
            fail( "Trying to url encode a Double object should cause an exception.");
        } catch (DecoderException ee) {
            // Exception expected, test segment passes.
        }
    }
    @Test
    public void test43() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there =\r\n";

        // Test case with padding around the input string
        String encoded = qpcodec.encode("= Hello there =\r\n");
        assertEquals("Basic quoted-printable encoding test - padding around", 
            "=3D Hello there =3D=0D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test - padding around", 
            plain, qpcodec.decode(encoded));

        // Test case without padding around the input string
        encoded = qpcodec.encode("= Hello there =\r\n", "UTF-8");
        assertEquals("Basic quoted-printable encoding test - no padding around", 
            encoded, "=3D Hello there =3D=0D=0A");
        assertEquals("Basic quoted-printable decoding test - no padding around", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test44() throws Exception {

        String ru_msg = constructString(RUSSIAN_STUFF_UNICODE); 
        String ch_msg = constructString(SWISS_GERMAN_STUFF_UNICODE); 
        
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        
        // Test case with Russian text
        String encoded = qpcodec.encode(ru_msg, CharEncoding.UTF_8);
        assertEquals("UTF-8 round trip test - Russian text", "=D0=92=D1=81=D0=B5=D0=BC_=D0=BF=D1=80=D0=B8=D0=B2=D0=B5=D1=82", encoded);
        assertEquals("UTF-8 round trip test - Russian text", ru_msg, qpcodec.decode(encoded, CharEncoding.UTF_8));

        // Test case with Swiss German text
        encoded = qpcodec.encode(ch_msg, CharEncoding.UTF_8);
        assertEquals("UTF-8 round trip test - Swiss German text", "Gr=C3=BCezi_z=C3=A4m=C3=A4", encoded);
        assertEquals("UTF-8 round trip test - Swiss German text", ch_msg, qpcodec.decode(encoded, CharEncoding.UTF_8));
    }
    @Test
    public void test45() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        
        // Test case with a single equal sign
        try {
            qpcodec.decode("=");
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }

        // Test case with a single equal sign followed by a hex digit
        try {
            qpcodec.decode("=A");
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }

        // Test case with a single equal sign followed by two hex digits
        try {
            qpcodec.decode("=WW");
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
    }
    @Test
    public void test46() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertNull("Null string quoted-printable encoding test", 
            qpcodec.encode((String)""));
        assertNull("Null string quoted-printable decoding test", 
            qpcodec.decode((String)""));
    }
    @Test
    public void test47() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "ABC123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test48() throws Exception {
        // whitespace, but does not need to be encoded
        String plain ="This is a example of a quoted=printable text file. There is no tt   ";
        String expected = "This is a example of a quoted=3Dprintable text file. There is no tt=20=20=20";

        assertEquals(expected, new QuotedPrintableCodec().encode(plain));
    }
    @Test
    public void test49() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="This is a example of a quoted-printable text file. This might contain sp=cial chars.\n";
        String expected = "This is a example of a quoted-printable text file. This might contain sp=3D=\r\ncial chars.\n";
        assertEquals(expected, qpcodec.encode(plain));

        plain ="This is a example of a quoted-printable text file. This might contain ta\tbs as well.\n";
        expected = "This is a example of a quoted-printable text file. This might contain ta=09=\r\nbs as well.\n";
        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test50() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        byte[] plain = "".getBytes();
        byte[] encoded = qpcodec.encode(plain);
        assertEquals("Encoding a null string should return null", 
            null, encoded);
    }
    @Test
    public void test51() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="This is a example of a quoted-printable text file. There is no end to it\t   ";
        String expected = "This is a example of a quoted-printable text file. There is no end to i=\r\nt=09=20=20";

        assertEquals(expected, qpcodec.encode(plain));

        plain ="This is a example of a quoted-printable text file. There is no end to it    ";
        expected = "This is a example of a quoted-printable text file. There is no end to i=\r\nt=20    ";

        assertEquals(expected, qpcodec.encode(plain));

        // whitespace before soft break
        plain ="This is a example of a quoted-printable text file. There is no end to   \n";
        expected = "This is a example of a quoted-printable text file. There is no end to=20=20=\r\n =20\n";

        assertEquals(expected, qpcodec.encode(plain));

        // non-printable character before soft break
        plain ="This is a example of a quoted-printable text file. There is no end to=  \n";
        expected = "This is a example of a quoted-printable text file. There is no end to=3D=\r\n =20\n";

        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test52() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "true";
        String encoded = (String) qpcodec.encode((Object) plain);
        assertEquals("Basic quoted-printable encoding test", 
            "true", encoded);

        byte[] plainBA = plain.getBytes("UTF-8");
        byte[] encodedBA = (byte[]) qpcodec.encode((Object) plainBA);
        encoded = new String(encodedBA);
        assertEquals("Basic quoted-printable encoding test", 
            "true", encoded);
            
        Object result = qpcodec.encode((Object) null);
        assertEquals( "Encoding a null Object should return null", null, result);
        
        try {
            Object bObj = new Boolean(true);
            qpcodec.encode( bObj );
            fail( "Trying to url encode a Boolean object should cause an exception.");
        } catch (EncoderException ee) {
            // Exception expected, test segment passes.
        }
    }
    @Test
    public void test53() throws Exception {
        String plain = "Hello there!";
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("Shift_JIS");
        qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
        String encoded1 = qpcodec.encode(plain, "Shift_JIS");
        String encoded2 = qpcodec.encode(plain);
        assertEquals(encoded1, encoded2);
    }
    @Test
    public void test54() {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("INVALIDENCODING");
           String plain = "Hello there!";
            try {
               qpcodec.encode(plain);
                fail( "We set the encoding to a bogus INVALIDENCODING value, this shouldn't have worked.");
            } catch (EncoderException ee) {
                // Exception expected, test segment passes.
            }
            try {
               qpcodec.decode(plain);
                fail( "We set the encoding to a bogus INVALIDENCODING value, this shouldn't have worked.");
            } catch (DecoderException ee) {
                // Exception expected, test segment passes.
            }
    }
    @Test
    public void test55() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String test = "   ";
        String result = qpcodec.encode( test, "charset" );
        assertEquals("Result should be null", null, result);
    }
    @Test
    public void test56() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = ":";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=3A", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test57() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there =\r\n ";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Hello there =3D=0D=0A=20", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test58() throws Exception {

        String ru_msg = constructString(RUSSIAN_STUFF_UNICODE + "EXTRA"); 
        String ch_msg = constructString(SWISS_GERMAN_STUFF_UNICODE + "EXTRA"); 
        
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        
        assertEquals(
            "=D0=92=D1=81=D0=B5=D0=BC_=D0=BF=D1=80=D0=B8=D0=B2=D0=B5=D1=82EXTRA", 
        qpcodec.encode(ru_msg, CharEncoding.UTF_8)
        );
        assertEquals("Gr=C3=BCezi_z=C3=A4m=C3=A4EXTRA", qpcodec.encode(ch_msg, CharEncoding.UTF_8));
        
        assertEquals(ru_msg, qpcodec.decode(qpcodec.encode(ru_msg, CharEncoding.UTF_8), CharEncoding.UTF_8));
        assertEquals(ch_msg, qpcodec.decode(qpcodec.encode(ch_msg, CharEncoding.UTF_8), CharEncoding.UTF_8));
    }
    @Test
    public void test59() throws Exception {
        String qpdata = "If you believe that truth=3Dbeauty, then surely mathematics is the most " +
                "b=\r\neautiful branch of philosophy.   ";
        String expected = "If you believe that truth=beauty, then surely mathematics is the most " +
                "beautiful branch of philosophy.   ";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(qpdata, qpcodec.encode(expected));

        String decoded = qpcodec.decode(qpdata);
        assertEquals(qpdata, qpcodec.encode(decoded));
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
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test62() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=3D=0D=0A", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test63() throws Exception {
        String qpdata = "CRLF in an\n encoded text should be=20=\r\n\rskipped in the\r decoding.";
        String expected = "CRLF in an encoded text should be skipped in the decoding.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(expected, qpcodec.decode(qpdata));

        String encoded = qpcodec.encode(expected);
        assertEquals(expected, qpcodec.decode(encoded));
    }
    @Test
    public void test64() throws Exception {
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
    public void test65() {
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
    public void test66() throws Exception {
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
    public void test67() throws Exception {
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
    public void test68() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there =\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Hello there =3D=0D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test69() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String test = null;
        String result = qpcodec.decode( test, "charset" );
        assertEquals("Result should be null", null, result);
    }
    @Test
    public void test70() throws Exception {

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
    public void test71() throws Exception {
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
    public void test72() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "";
        String encoded = qpcodec.encode(plain);
        assertEquals("Empty string quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Empty string quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test73() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "    ";
        String encoded = qpcodec.encode(plain);
        assertEquals("Whitespace quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Whitespace quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test74() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "1234567890";
        String encoded = qpcodec.encode(plain);
        assertEquals("Digits quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Digits quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test75() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "!@#$%^&*()";
        String encoded = qpcodec.encode(plain);
        assertEquals("Special characters quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Special characters quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test76() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertNull("Null string quoted-printable encoding test", 
            qpcodec.encode((String)null));
        assertNull("Null string quoted-printable decoding test", 
            qpcodec.decode((String)null));
        
        // Regression test case 1: Empty string
        assertEquals("", qpcodec.encode(""));
        assertEquals("", qpcodec.decode(""));
    }
    @Test
    public void test77() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        
        // Regression test case 2: String includes safe characters
        assertEquals("abc", qpcodec.encode("abc"));
        assertEquals("abc", qpcodec.decode("abc"));
    }
    @Test
    public void test78() throws Exception {
        // whitespace, but does not need to be encoded
        String plain ="This is a example of a quoted=printable text file. There is no tt";
        String expected = "This is a example of a quoted=3Dprintable text file. There is no tt";

        assertEquals(expected, new QuotedPrintableCodec().encode(plain));
        
        // Regression test case 3: Trailing whitespace
        assertEquals("abc ", qpcodec.encode("abc "));
        assertEquals("abc ", qpcodec.decode("abc "));
    }
    @Test
    public void test79() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="This is a example of a quoted-printable text file. This might contain sp=cial chars.";
        String expected = "This is a example of a quoted-printable text file. This might contain sp=3D=\r\ncial chars.";
        assertEquals(expected, qpcodec.encode(plain));

        plain ="This is a example of a quoted-printable text file. This might contain ta\tbs as well.";
        expected = "This is a example of a quoted-printable text file. This might contain ta=09=\r\nbs as well.";
        assertEquals(expected, qpcodec.encode(plain));
        
        // Regression test case 4: Trailing special characters
        assertEquals("abc!", qpcodec.encode("abc!"));
        assertEquals("abc!", qpcodec.decode("abc!"));
    }
    @Test
    public void test80() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        byte[] plain = null;
        byte[] encoded = qpcodec.encode(plain);
        assertEquals("Encoding a null string should return null", 
            null, encoded);
        
        // Regression test case 5: Empty byte array
        assertArrayEquals(new byte[0], qpcodec.encode(new byte[0]));
    }
    @Test
    public void test81() throws Exception {
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
        
        // Regression test case 6: Trailing whitespace and soft break
        assertEquals("abc \t", qpcodec.encode("abc \t"));
        assertEquals("abc \t", qpcodec.decode("abc \t"));
    }
    @Test
    public void test82() throws Exception {
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
        
        // Regression test case 7: Encoding and decoding null object
        assertNull(qpcodec.encode((Object) null));
        assertNull(qpcodec.decode((Object) null));
    }
    @Test
    public void test83() throws Exception {
        String plain = "Hello there!";
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("UnicodeBig");
        qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
        String encoded1 = qpcodec.encode(plain, "UnicodeBig");
        String encoded2 = qpcodec.encode(plain);
        assertEquals(encoded1, encoded2);
        
        // Regression test case 8: Using default encoding
        assertEquals("abc", new QuotedPrintableCodec().encode("abc"));
        assertEquals("abc", new QuotedPrintableCodec().decode("abc"));
    }
    @Test
    public void test84() {
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
        
        // Regression test case 9: Using invalid encoding
        try {
            new QuotedPrintableCodec("INVALID");
            fail("Using invalid encoding should cause an exception.");
        } catch (IllegalCharsetNameException | IllegalArgumentException e) {
            // Exception expected, test segment passes.
        }
    }
    @Test
    public void test85() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String test = null;
        String result = qpcodec.encode( test, "charset" );
        assertEquals("Result should be null", null, result);
        
        // Regression test case 10: Encoding null string with null charset
        assertEquals(null, new QuotedPrintableCodec().encode(null, null));
    }
    @Test
    public void test86() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=3D=0D=0A", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        
        // Regression test case 11: Unsafe characters
        assertEquals("!@", qpcodec.encode("!@"));
        assertEquals("!@", qpcodec.decode("!@"));
    }
    @Test
    public void test87() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there =\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Hello there =3D=0D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        
        // Regression test case 12: Special characters
        assertEquals("=3B", qpcodec.encode(";"));
        assertEquals("=3B", qpcodec.decode(";"));
    }
    @Test
    public void test88() throws Exception {

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
        
        // Regression test case 13: Invalid UTF-8 encoding
        try {
            qpcodec.encode("abc", "INVALID");
            fail("Invalid encoding should cause an exception.");
        } catch (UnsupportedEncodingException e) {
            // Exception expected, test segment passes.
        }
    }
    @Test
    public void test89() throws Exception {
        String qpdata = "If you believe that truth=3Dbeauty, then surely mathematics is the most " +
                "b=\r\neautiful branch of philosophy.";
        String expected = "If you believe that truth=beauty, then surely mathematics is the most " +
                "beautiful branch of philosophy.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(qpdata, qpcodec.encode(expected));

        String decoded = qpcodec.decode(qpdata);
        assertEquals(qpdata, qpcodec.encode(decoded));
        
        // Regression test case 14: Soft line break
        assertEquals("abc=\n", qpcodec.encode("abc\n"));
        assertEquals("abc=\r\n", qpcodec.encode("abc\r\n"));
    }
    @Test
    public void test90() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertNull("Null string quoted-printable encoding test", 
            qpcodec.encode((String)null));
        assertNull("Null string quoted-printable decoding test", 
            qpcodec.decode((String)null));
        
        // regression test for mutant 1: change null value to an empty string
        assertEquals("", qpcodec.encode(""));
        assertNull(qpcodec.decode(""));
        
        // regression test for mutant 2: change null value to a non-null value
        assertEquals("hello", qpcodec.encode("hello"));
        assertEquals("hello", qpcodec.decode("hello"));
    }
    @Test
    public void test91() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        
        // regression test for mutant 3: change plain string to an empty string
        assertEquals("", qpcodec.encode(""));
        assertEquals("", qpcodec.decode(""));
        
        // regression test for mutant 4: change plain string to a longer string
        String longPlain = "this is a long string that should not be encoded";
        assertEquals(longPlain, qpcodec.encode(longPlain));
        assertEquals(longPlain, qpcodec.decode(longPlain));
    }
    @Test
    public void test92() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=3D=0D=0A", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        
        // regression test for mutant 5: change unsafe string to a safe string
        String safeString = "abc";
        assertEquals(safeString, qpcodec.encode(safeString));
        assertEquals(safeString, qpcodec.decode(safeString));
        
        // regression test for mutant 6: change unsafe string to a longer string
        String longPlain = "this is a long =\r\n";
        String longEncoded = qpcodec.encode(longPlain);
        assertEquals("this is a long =3D=0D=0A", longEncoded);
        assertEquals(longPlain, qpcodec.decode(longEncoded));
    }
    @Test
    public void test93() throws Exception {
        String qpdata = "CRLF in an\n encoded text should be=20=\r\n\rskipped in the\r decoding.";
        String expected = "CRLF in an encoded text should be skipped in the decoding.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(expected, qpcodec.decode(qpdata));

        String encoded = qpcodec.encode(expected);
        assertEquals(expected, qpcodec.decode(encoded));
        
        // regression test for mutant 7: change qpdata to an empty string
        assertEquals("", qpcodec.decode(""));
        assertEquals("", qpcodec.encode(""));
        
        // regression test for mutant 8: remove the equals sign from qpdata
        String newQpdata = "CRLF in an\n encoded text should be20=\r\n\rskipped in the\r decoding.";
        assertEquals(expected, qpcodec.decode(newQpdata));
    }
    @Test
    public void test94() throws Exception {
        String qpdata = "If you believe that truth=3Dbeauty, then surely=20=\r\nmathematics " +
                "is the most beautiful branch of philosophy.";
        String expected = "If you believe that truth=beauty, then surely mathematics " +
                "is the most beautiful branch of philosophy.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(expected, qpcodec.decode(qpdata));

        String encoded = qpcodec.encode(expected);
        assertEquals(expected, qpcodec.decode(encoded));
        
        // regression test for mutant 9: remove the equals sign in qpdata
        String newQpdata = "If you believe that truth3Dbeauty, then surely=20=\r\nmathematics " +
                "is the most beautiful branch of philosophy.";
        assertEquals(expected, qpcodec.decode(newQpdata));
    }
    @Test
    public void test95() {
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
        
        // regression test for mutant 10: change "NONSENSE" to a valid encoding value
        QuotedPrintableCodec validCodec = new QuotedPrintableCodec("UTF-8");
        assertEquals("Hello there!", validCodec.encode(plain));
        assertEquals("Hello there!", validCodec.decode(plain));
        
        // regression test for mutant 11: change plain string to an empty string
        assertEquals("", qpcodec.encode(""));
        assertNull(qpcodec.decode(""));
    }
    @Test
    public void test96() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "1+1 = 2";
        String encoded = new String(QuotedPrintableCodec.
            encodeQuotedPrintable(null, plain.getBytes("UTF-8")));
        assertEquals("Basic quoted-printable encoding test", 
            "1+1 =3D 2", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        
        // regression test for mutant 12: change plain string to an empty string
        assertEquals("", qpcodec.encode(""));
        assertEquals("", qpcodec.decode(""));
        
        // regression test for mutant 13: change plain string to a longer string
        String longPlain = "this is a long string that should not be encoded";
        assertEquals(longPlain, qpcodec.encode(longPlain));
        assertEquals(longPlain, qpcodec.decode(longPlain));
    }
    @Test
    public void test97() throws Exception {
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
        
        // regression test for mutant 14: change plain string to an empty string
        assertEquals("", qpcodec.encode(""));
        assertEquals("", qpcodec.decode(""));
        
        // regression test for mutant 15: change plain string to a non-null value
        assertEquals("hello", qpcodec.encode("hello"));
        assertEquals("hello", qpcodec.decode("hello"));
    }
    @Test
    public void test98() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there =\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Hello there =3D=0D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        
        // regression test for mutant 16: remove equals signs from plain string
        String newPlain = " Hello there \r\n";
        String newEncoded = qpcodec.encode(newPlain);
        assertEquals(newPlain, qpcodec.decode(newEncoded));
        
        // regression test for mutant 17: change plain string to an empty string
        assertEquals("", qpcodec.encode(""));
        assertEquals("", qpcodec.decode(""));
    }
    @Test
    public void test99() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String test = null;
        String result = qpcodec.decode( test, "charset" );
        assertEquals("Result should be null", null, result);
        
        // regression test for mutant 18: change test string to an empty string
        assertEquals("", qpcodec.decode("", "charset"));
        
        // regression test for mutant 19: change test string to a non-null value
        String nonNullTest = "hello";
        assertEquals(nonNullTest, qpcodec.decode(nonNullTest, "charset"));
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
        assertEquals("Gr=C3=BCezi_z=C3=A4m=C3=A4", qpcodec.encode(ch_msg, CharEncoding.UTF_8));
        
        assertEquals(ru_msg, qpcodec.decode(qpcodec.encode(ru_msg, CharEncoding.UTF_8), CharEncoding.UTF_8));
        assertEquals(ch_msg, qpcodec.decode(qpcodec.encode(ch_msg, CharEncoding.UTF_8), CharEncoding.UTF_8));
        
        // regression test for mutant 20: change ru_msg string to an empty string
        assertEquals("", qpcodec.encode("", CharEncoding.UTF_8));
        assertEquals("", qpcodec.decode("", CharEncoding.UTF_8));
        
        // regression test for mutant 21: change ru_msg string to a non-empty string
        assertEquals("hello", qpcodec.encode("hello", CharEncoding.UTF_8));
        assertEquals("hello", qpcodec.decode("hello", CharEncoding.UTF_8));
    }
    @Test
    public void test101() throws Exception {
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
        
        // regression test for mutant 22: change "=" to a different character
        try {
            qpcodec.decode("a");
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
        
        // regression test for mutant 23: change "=" to a longer string
        try {
            qpcodec.decode("abc");
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
    }
    @Test
    public void test102() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertNull("Null string quoted-printable encoding test", 
            qpcodec.encode((String)null));
        assertNull("Null string quoted-printable decoding test", 
            qpcodec.decode((String)null));
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
    }
    @Test
    public void test105() throws Exception {
        String qpdata = "CRLF in an\n encoded text should be=20=\r\n\rskipped in the\r decoding.";
        String expected = "CRLF in an encoded text should be skipped in the decoding.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(expected, qpcodec.decode(qpdata));

        String encoded = qpcodec.encode(expected);
        assertEquals(expected, qpcodec.decode(encoded));
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
    }
    @Test
    public void test111() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String test = null;
        String result = qpcodec.decode( test, "charset" );
        assertEquals("Result should be null", null, result);
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
    public void test114() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "1234567890";
        String encoded = qpcodec.encode(plain);
        assertEquals("All numbers quoted-printable encoding test", 
            plain, encoded);
        assertEquals("All numbers quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test115() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "";
        String encoded = qpcodec.encode(plain);
        assertEquals("Empty string quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Empty string quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test116() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "äöüßÇÁÉÍÓNőű";
        String encoded = qpcodec.encode(plain);
        assertEquals("Special chars quoted-printable encoding test", 
            "=C3=A4=C3=B6=C3=BC=C3=9F=C3=87=C3=81=C3=89=C3=8D=C3=93=C5=91=C5=B1", encoded);
        assertEquals("Special chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
@Test
public void test117() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    assertNull("Null string quoted-printable encoding test", 
        qpcodec.encode((String)null));
    assertNull("Null string quoted-printable decoding test", 
        qpcodec.decode((String)null));
}
@Test
public void test118() throws Exception {
    ...
}
@Test
public void test119() throws Exception {
    ...
}
@Test
public void test120() throws Exception {
    ...
}
@Test
public void test121() throws Exception {
    ...
}
@Test
public void test122() throws Exception {
    ...
}
@Test
public void test123() throws Exception {
    ...
}
@Test
public void test124() throws Exception {
    ...
}
@Test
public void test125() {
    ...
}
@Test
public void test126() throws Exception {
    ...
}
@Test
public void test127() throws Exception {
    ...
}
@Test
public void test128() throws Exception {
    ...
}
@Test
public void test129() throws Exception {
    ...
}
@Test
public void test130() throws Exception {
    ...
}
@Test
public void test131() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

    Object[] objectArray = new Object[] {"abc", "123", "!@#"};
    String expected = "abc=3D\r\n123=3D\r\n!@#=3D\r\n";

    assertEquals(expected, qpcodec.encode(objectArray));
    assertArrayEquals(objectArray, qpcodec.decode(expected));
}
@Test
public void test132() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

    Number number = 123.45;
    String expected = "123.45";

    assertEquals(expected, qpcodec.encode(number));
    assertEquals(number, qpcodec.decode(expected));
}
@Test
public void test133() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

    Boolean bool = true;
    String expected = "true";

    assertEquals(expected, qpcodec.encode(bool));
    assertEquals(bool, qpcodec.decode(expected));
}
@Test
public void test134() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

    char ch = 'A';
    String expected = "A";

    assertEquals(expected, qpcodec.encode(ch));
    assertEquals(ch, qpcodec.decode(expected).charAt(0));
}
@Test
public void test135() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

    String plain = "abcdefghijklmnopqrstuvwxyz";
    String expected = "abcdefghijklmnopqrstuvwxyz";

    assertEquals(expected, qpcodec.encode(plain));
    assertEquals(plain, qpcodec.decode(expected));
}
@Test
public void test136() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

    String plain = "";
    String expected = "";

    assertEquals(expected, qpcodec.encode(plain));
    assertEquals(plain, qpcodec.decode(expected));
}
    @Test
    public void test137() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertNull("Null string quoted-printable encoding test", 
            qpcodec.encode((String)null));
        assertNull("Null string quoted-printable decoding test", 
            qpcodec.decode((String)null));
        // Regression test 1: change null string to empty string
        assertEquals("", qpcodec.encode(""));
        assertNull(qpcodec.decode(""));
        // Regression test 2: change null string to string with only whitespaces
        assertEquals("   ", qpcodec.encode("   "));
        assertEquals("   ", qpcodec.decode("   "));
    }
    @Test
    public void test138() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        // Regression test: change string with safe characters to string with non-safe characters
        String newPlain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]!";
        String newEncoded = qpcodec.encode(newPlain);
        assertEquals("Safe chars quoted-printable encoding test", 
            newPlain, newEncoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            newPlain, qpcodec.decode(newEncoded));
    }
    @Test
    public void test139() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=3D=0D=0A", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        // Regression test: change string with unsafe characters to string without unsafe characters
        String newPlain = "aaaa";
        String newEncoded = qpcodec.encode(newPlain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            newPlain, newEncoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            newPlain, qpcodec.decode(newEncoded));
    }
    @Test
    public void test140() throws Exception {
        String qpdata = "CRLF in an\n encoded text should be=20=\r\n\rskipped in the\r decoding.";
        String expected = "CRLF in an encoded text should be skipped in the decoding.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(expected, qpcodec.decode(qpdata));

        String encoded = qpcodec.encode(expected);
        assertEquals(expected, qpcodec.decode(encoded));
        // Regression test: change '=\r\n' to '=\n'
        String newQpData = "CRLF in an\n encoded text should be=20=\n\rskipped in the\r decoding.";
        String newExpected = "CRLF in an encoded text should be skipped in the decoding.";
        assertEquals(newExpected, qpcodec.decode(newQpData));

        String newEncoded = qpcodec.encode(newExpected);
        assertEquals(newExpected, qpcodec.decode(newEncoded));
    }
    @Test
    public void test141() throws Exception {
        String qpdata = "If you believe that truth=3Dbeauty, then surely=20=\r\nmathematics " +
                "is the most beautiful branch of philosophy.";
        String expected = "If you believe that truth=beauty, then surely mathematics " +
                "is the most beautiful branch of philosophy.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(expected, qpcodec.decode(qpdata));

        String encoded = qpcodec.encode(expected);
        assertEquals(expected, qpcodec.decode(encoded));
        // Regression test: change '=20=\r\n' to '=20\n'
        String newQpData = "If you believe that truth=3Dbeauty, then surely=20\nmathematics " +
                "is the most beautiful branch of philosophy.";
        assertEquals(expected, qpcodec.decode(newQpData));

        String newEncoded = qpcodec.encode(expected);
        assertEquals(expected, qpcodec.decode(newEncoded));
    }
    @Test
    public void test142() {
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
        // Regression test: change "NONSENSE" to "NONSE"
        qpcodec = new QuotedPrintableCodec("NONSE");
        try {
            qpcodec.encode(plain);
            fail( "We set the encoding to a bogus NONSE value, this shouldn't have worked.");
        } catch (EncoderException ee) {
            // Exception expected, test segment passes.
        }
        try {
            qpcodec.decode(plain);
            fail( "We set the encoding to a bogus NONSE value, this shouldn't have worked.");
        } catch (DecoderException ee) {
            // Exception expected, test segment passes.
        }
    }
    @Test
    public void test143() throws Exception {
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
    public void test144() throws Exception {
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
        // Regression test: change Double(3.0) to Double(2.0)
        try {
            Object newDObj = new Double(2.0);
            qpcodec.decode( newDObj );
            fail( "Trying to url encode a Double object should cause an exception.");
        } catch (DecoderException ee) {
            // Exception expected, test segment passes.
        }
    }
    @Test
    public void test145() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there =\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Hello there =3D=0D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        // Regression test: change plain string to lowercase
        String newPlain = "= hello there =\r\n";
        String newEncoded = qpcodec.encode(newPlain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D hello there =3D=0D=0A", newEncoded);
        assertEquals("Basic quoted-printable decoding test", 
            newPlain, qpcodec.decode(newEncoded));
    }
    @Test
    public void test146() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String test = null;
        String result = qpcodec.decode( test, "charset" );
        assertEquals("Result should be null", null, result);
        // Regression test: change null string to empty string
        String newTest = "";
        String newResult = qpcodec.decode( newTest, "charset" );
        assertEquals("Result should be empty string", "", newResult);
    }
    @Test
    public void test147() throws Exception {

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
        // Regression test: change RUSSIAN_STUFF_UNICODE to RUSSIAN_STUFF_UNICODE with some characters removed
        String newRu_msg = constructString(new int[]{1072,1091,1074}); 
        String newCh_msg = constructString(SWISS_GERMAN_STUFF_UNICODE); 
        
        assertEquals(
            "=D0=B4=D1=83=D1=88", 
        qpcodec.encode(newRu_msg, CharEncoding.UTF_8)
        );
        assertEquals("Gr=C3=BCezi_z=C3=A4m=C3=A4", qpcodec.encode(ch_msg, CharEncoding.UTF_8));
        
        assertEquals(newRu_msg, qpcodec.decode(qpcodec.encode(newRu_msg, CharEncoding.UTF_8), CharEncoding.UTF_8));
        assertEquals(ch_msg, qpcodec.decode(qpcodec.encode(ch_msg, CharEncoding.UTF_8), CharEncoding.UTF_8));
    }
    @Test
    public void test148() throws Exception {
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
        // Regression test: change '=' to '?'
        try {
            qpcodec.decode("?");
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
        try {
            qpcodec.decode("?A");
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
        try {
            qpcodec.decode("?WW");
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
    }
@Test
public void test149() {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "";
    String encoded = qpcodec.encode(plain);
    assertEquals("Empty string encoding test", "", encoded);
    assertEquals("Empty string decoding test", plain, qpcodec.decode(encoded));
}
@Test
public void test150() {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = null;
    String encoded = qpcodec.encode(plain);
    assertNull("Null string encoding test", encoded);
    assertNull("Null string decoding test", qpcodec.decode(encoded));
}
@Test
public void test151() {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "!@#$%^&*()_+-=[]{}|;':\",.<>/?";
    String encoded = qpcodec.encode(plain);
    assertEquals("Special characters encoding test", plain, encoded);
    assertEquals("Special characters decoding test", plain, qpcodec.decode(encoded));
}
    @Test
    public void test152() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertNull("Null string quoted-printable encoding test", 
            qpcodec.encode((String)null));
        assertNull("Null string quoted-printable decoding test", 
            qpcodec.decode((String)null));
        
        // Regression test case: Change input to empty string
        assertEquals("", qpcodec.encode(""));
        assertEquals("", qpcodec.decode(""));
        
        // Regression test case: Change input to a string with leading whitespace
        assertEquals("  abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]", qpcodec.encode("  abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]"));
        assertEquals("  abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]", qpcodec.decode("  abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]"));
    }
    @Test
    public void test153() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        
        // Regression test case: Change input to a string with special characters
        assertEquals("[]~!@#{}\"\\;:`/abc123_-.*+", qpcodec.encode("[]~!@#{}\"\\;:`/abc123_-.*+"));
        assertEquals("[]~!@#{}\"\\;:`/abc123_-.*+", qpcodec.decode("[]~!@#{}\"\\;:`/abc123_-.*+"));
    }
    @Test
    public void test154() throws Exception {
        // whitespace, but does not need to be encoded
        String plain ="This is a example of a quoted=printable text file. There is no tt";
        String expected = "This is a example of a quoted=3Dprintable text file. There is no tt";

        assertEquals(expected, new QuotedPrintableCodec().encode(plain));
        
        // Regression test case: Change input to a string with special characters
        assertEquals("This is a example of a quoted=printable text file$ There *s n! tt", new QuotedPrintableCodec().encode("This is a example of a quoted=printable text file$ There *s n! tt"));
    }
    @Test
    public void test155() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="This is a example of a quoted-printable text file. This might contain sp=cial chars.";
        String expected = "This is a example of a quoted-printable text file. This might contain sp=3D=\r\ncial chars.";
        assertEquals(expected, qpcodec.encode(plain));

        plain ="This is a example of a quoted-printable text file. This might contain ta\tbs as well.";
        expected = "This is a example of a quoted-printable text file. This might contain ta=09=\r\nbs as well.";
        assertEquals(expected, qpcodec.encode(plain));
        
        // Regression test case: Change input to a string with special characters
        plain ="This is a example of a quoted-printable text file. This might c#nt@in sp=cial ch@rs.";
        expected = "This is a example of a quoted-printable text file. This might c#nt=3D=\r\ncial ch@rs.";
        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test156() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        byte[] plain = null;
        byte[] encoded = qpcodec.encode(plain);
        assertEquals("Encoding a null string should return null", 
            null, encoded);
        
        // Regression test case: Change input to a non-null byte array
        byte[] nonNullInput = new byte[] {1, 2, 3, 4, 5};
        assertArrayEquals(nonNullInput, qpcodec.encode(nonNullInput));
    }
    @Test
    public void test157() throws Exception {
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
        
        // Regression test case: Change input to a string with special characters
        plain ="This is a example of a quoted-printable text file. There is no end to%t!";
        expected = "This is a example of a quoted-printable text file. There is no end to=3D=\r\n%t!";
        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test158() throws Exception {
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
        
        // Regression test case: Change input to a non-null plain object
        Object nonNullObject = new Object();
        assertEquals(nonNullObject, qpcodec.encode(nonNullObject));
    }
    @Test
    public void test159() throws Exception {
        String plain = "Hello there!";
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("UnicodeBig");
        qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
        String encoded1 = qpcodec.encode(plain, "UnicodeBig");
        String encoded2 = qpcodec.encode(plain);
        assertEquals(encoded1, encoded2);
        
        // Regression test case: Change input to a string with special characters
        assertEquals("G@m$~`<;", new QuotedPrintableCodec("UnicodeBig").encode("G@m$~`<;"));
    }
    @Test
    public void test160() {
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
            
        // Regression test case: Change input to a string with special characters
        try {
            qpcodec.encode("G@am$~`"); // If a different encoding is used, an exception should be thrown
            fail("Expected EncoderException to be thrown");
        } catch (EncoderException ee) {
            // Exception expected, test segment passes
        }
        try {
            qpcodec.decode("G@am$~`"); // If a different encoding is used, an exception should be thrown
            fail("Expected DecoderException to be thrown");
        } catch (DecoderException ee) {
            // Exception expected, test segment passes
        }
    }
    @Test
    public void test161() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String test = null;
        String result = qpcodec.encode( test, "charset" );
        assertEquals("Result should be null", null, result);
        
        // Regression test case: Change input to a non-null string
        assertEquals("abc", qpcodec.encode("abc", "charset"));
    }
    @Test
    public void test162() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=3D=0D=0A", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        
        // Regression test case: Change input to a string with special characters
        plain = "!abc [] =<>#?";
        encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=21abc [] =3D<>#?", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test163() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there =\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Hello there =3D=0D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        
        // Regression test case: Change input to a string with special characters
        plain = "!abc [] =<>#?\r\n";
        encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=21abc [] =3D<>#?=0D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test164() throws Exception {

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
        
        // Regression test case: Change input to a string with special characters
        String specialChars = "[]{}()*~!@#$%&<>?;:|";
        assertEquals("[]{}()*~!@#$%&<>?;:|", qpcodec.decode(qpcodec.encode(specialChars, CharEncoding.UTF_8), CharEncoding.UTF_8));
    }
    @Test
    public void test165() throws Exception {
        String qpdata = "If you believe that truth=3Dbeauty, then surely mathematics is the most " +
                "b=\r\neautiful branch of philosophy.";
        String expected = "If you believe that truth=beauty, then surely mathematics is the most " +
                "beautiful branch of philosophy.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(qpdata, qpcodec.encode(expected));

        String decoded = qpcodec.decode(qpdata);
        assertEquals(qpdata, qpcodec.encode(decoded));
        
        // Regression test case: Change input to a string with special characters
        String specialChars = "[]{}()*~!@#$%&<>?;:|";
        assertEquals(specialChars, qpcodec.decode(qpcodec.encode(specialChars)));
    }
}