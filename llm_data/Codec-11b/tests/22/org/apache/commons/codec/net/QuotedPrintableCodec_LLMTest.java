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
        
        // Additional test with all uppercase characters
        plain = "ABCDEF";
        encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        
        // Additional test with all lowercase characters
        plain = "abcdef";
        encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        
        // Additional test with digits only
        plain = "1234567890";
        encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        
        // Additional test with a mixture of safe characters
        plain = "1aBc4D7eF";
        encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test1() throws Exception {
        // whitespace, but does not need to be encoded
        String plain ="This is a example of a quoted=printable text file. There is no tt";
        String expected = "This is a example of a quoted=3Dprintable text file. There is no tt";

        assertEquals(expected, new QuotedPrintableCodec().encode(plain));
        
        // Additional test with additional space character at the end
        plain ="This is a example of a quoted=printable text file. There is no tt ";
        expected = "This is a example of a quoted=3Dprintable text file. There is no tt ";
        assertEquals(expected, new QuotedPrintableCodec().encode(plain));
    }
    @Test
    public void test2() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="This is a example of a quoted-printable text file. This might contain sp=cial chars.";
        String expected = "This is a example of a quoted-printable text file. This might contain sp=3D=\r\ncial chars.";
        assertEquals(expected, qpcodec.encode(plain));

        plain ="This is a example of a quoted-printable text file. This might contain ta\tbs as well.";
        expected = "This is a example of a quoted-printable text file. This might contain ta=09=\r\nbs as well.";
        assertEquals(expected, qpcodec.encode(plain));
        
        // Additional test with additional space character at the end
        plain ="This is a example of a quoted-printable text file. This might contain sp=cial chars. ";
        expected = "This is a example of a quoted-printable text file. This might contain sp=3D=\r\ncial chars. ";
        assertEquals(expected, qpcodec.encode(plain));
        
        // Additional test with additional space character at the end
        plain ="This is a example of a quoted-printable text file. This might contain ta\tbs as well. ";
        expected = "This is a example of a quoted-printable text file. This might contain ta=09=\r\nbs as well. ";
        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test3() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        byte[] plain = null;
        byte[] encoded = qpcodec.encode(plain);
        assertEquals("Encoding a null string should return null", 
            null, encoded);
        
        // Additional test with empty bytes array
        plain = new byte[0];
        encoded = qpcodec.encode(plain);
        assertEquals("Encoding an empty bytes array should return an empty bytes array", 
            plain, encoded);
    }
    @Test
    public void test4() throws Exception {
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
        
        // Additional test with additional space character at the end
        plain ="This is a example of a quoted-printable text file. There is no end to it \t";
        expected = "This is a example of a quoted-printable text file. There is no end to i=\r\nt=20 ";
        assertEquals(expected, qpcodec.encode(plain));
        
        // Additional test with additional space character at the end
        plain ="This is a example of a quoted-printable text file. There is no end to it  ";
        expected = "This is a example of a quoted-printable text file. There is no end to i=\r\nt=20  ";
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
        
        // Additional test with empty string
        plain = "";
        encoded = (String) qpcodec.encode((Object) plain);
        assertEquals("Encoding an empty string should return an empty string", 
            plain, encoded);

        plainBA = plain.getBytes("UTF-8");
        encodedBA = (byte[]) qpcodec.encode((Object) plainBA);
        encoded = new String(encodedBA);
        assertEquals("Encoding an empty string should return an empty string", 
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
        
        // Additional test with empty string
        plain = "";
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
        
        // Additional test with empty bytes array
        plain = "";
        encoded = new String(QuotedPrintableCodec.
            encodeQuotedPrintable(null, plain.getBytes("UTF-8")));
        assertEquals("Encoding an empty bytes array should return an empty string", 
            plain, encoded);
        assertEquals("Decoding an empty bytes array should return an empty string", 
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
        
        // Additional test with an unsafe character
        plain = "!";
        encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=21", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        
        // Additional test with an unsafe character at the end
        plain = "!";
        encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=21", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        
        // Additional test with an unsafe character at the beginning
        plain = "!abcdefghijklmnopqrstuvwxyz";
        encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=21abcdefghijklmnopqrstuvwxyz", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        
        // Additional test with unsafe characters in the middle
        plain = "abcdefghijklmnopqrstuvwxyz!1234567890";
        encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "abcdefghijklmnopqrstuvwxyz=211234567890", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
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
        
        // Additional test with whitespace characters only
        plain = "     ";
        encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=20=20=20=20=20", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        
        // Additional test with non-printable characters only
        plain = "\u0000\u0001\u0002\u0003\u0004\u0005\u0006\u0007";
        encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=00=01=02=03=04=05=06=07", encoded);
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
        assertEquals("Gr=C3=BCezi_z=C3=A4m=C3=A4", qpcodec.encode(ch_msg, CharEncoding.UTF_8));
        
        assertEquals(ru_msg, qpcodec.decode(qpcodec.encode(ru_msg, CharEncoding.UTF_8), CharEncoding.UTF_8));
        assertEquals(ch_msg, qpcodec.decode(qpcodec.encode(ch_msg, CharEncoding.UTF_8), CharEncoding.UTF_8));
        
        // Additional test with empty string
        String empty = "";
        assertEquals(empty, qpcodec.decode(qpcodec.encode(empty, CharEncoding.UTF_8), CharEncoding.UTF_8));
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
        
        // Additional test with additional space character at the end
        qpdata = "If you believe that truth=3Dbeauty, then surely mathematics is the most " +
                "b=\r\neautiful branch of philosophy. ";
        expected = "If you believe that truth=beauty, then surely mathematics is the most " +
                "beautiful branch of philosophy. ";
                
        assertEquals(expected, qpcodec.encode(expected));
        assertEquals(expected, qpcodec.encode(decoded));
    }
    @Test
    public void test12() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        
        // ESACPE_CHAR = 61, 
        // binary: 01100001
        // hexadecimal: 0x61
        // decimal: 97
        int b = 97;
        
        private static final void encodeQuotedPrintable(b, buffer) {
            buffer.write(ESCAPE_CHAR);
            char hex1 = Character.toUpperCase(Character.forDigit((b >> 4) & 0xF, 16));
            char hex2 = Character.toUpperCase(Character.forDigit(b & 0xF, 16));
            buffer.write(hex1);
            buffer.write(hex2);
        }
        
        // Expected result:
        // =3D
        assertEquals("=3D", buffer.toString());
    }
    @Test
    public void test13() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
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
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="This is a example of a quoted-printable text file. This might contain sp=cial chars.";
        String expected = "This is a example of a quoted-printable text file. This might contain sp=3D=\r\ncial chars.";
        assertEquals(expected, qpcodec.encode(plain));

        plain ="This is a example of a quoted-printable text file. This might contain ta\tbs as well.";
        expected = "This is a example of a quoted-printable text file. This might contain ta=09=\r\nbs as well.";
        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test16() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        byte[] plain = null;
        byte[] encoded = qpcodec.encode(plain);
        assertEquals("Encoding a null string should return null", 
            null, encoded);
    }
    @Test
    public void test17() throws Exception {
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
    public void test18() throws Exception {
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
    public void test19() throws Exception {
        String plain = "Hello there!";
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("UnicodeBig");
        qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
        String encoded1 = qpcodec.encode(plain, "UnicodeBig");
        String encoded2 = qpcodec.encode(plain);
        assertEquals(encoded1, encoded2);
    }
    @Test
    public void test20() throws Exception {
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
    public void test21() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there =\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=3D=0D=0A", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test22() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there =\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Hello there =3D=0D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test23() throws Exception {
        // Test case with Russian characters
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
    public void test24() throws Exception {
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
    public void test25() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "!@#$%^&()+{}\"\\;`,[]";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test (regression 1)", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test (regression 1)", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test26() throws Exception {
        // whitespace, but does not need to be encoded
        String plain ="This is a example of a quoted=printable text file.";
        String expected = "This is a example of a quoted=3Dprintable text file.";

        assertEquals(expected, new QuotedPrintableCodec().encode(plain));
    }
    @Test
    public void test27() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="This might contain sp=cial chars.";
        String expected = "This might contain sp=3D=\r\ncial chars.";
        assertEquals(expected, qpcodec.encode(plain));

        plain ="This might contain whitespace at the end    ";
        expected = "This might contain whitespace at the end=20=\r\n=20";
        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test28() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        byte[] plain = null;
        byte[] encoded = qpcodec.encode(plain);
        assertEquals("Encoding a null string should return null (regression 4)", 
            null, encoded);
    }
    @Test
    public void test29() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="There is no end to it\t";
        String expected = "There is no end to i=\r\nt=09";
        assertEquals(expected, qpcodec.encode(plain));

        plain ="There is no end to it ";
        expected = "There is no end to i=\r\nt=20";
        assertEquals(expected, qpcodec.encode(plain));

        // whitespace before soft break
        plain ="There is no end to   ";
        expected = "There is no end to=20=\r\n =20";
        assertEquals(expected, qpcodec.encode(plain));

        // non-printable character before soft break
        plain ="There is no end to=  ";
        expected = "There is no end to=3D=\r\n =20";
        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test30() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "1+1 = 2";
        String encoded = (String) qpcodec.encode((Object) plain);
        assertEquals("Basic quoted-printable encoding test (regression 6)", 
            "1+1 =3D 2", encoded);

        byte[] plainBA = plain.getBytes("UTF-8");
        byte[] encodedBA = (byte[]) qpcodec.encode((Object) plainBA);
        encoded = new String(encodedBA);
        assertEquals("Basic quoted-printable encoding test (regression 6)", 
            "1+1 =3D 2", encoded);
            
        Object result = qpcodec.encode((Object) null);
        assertEquals( "Encoding a null Object should return null (regression 6)", null, result);
        
        try {
            Object dObj = new Double(3.0);
            qpcodec.encode( dObj );
            fail( "Trying to url encode a Double object should cause an exception (regression 6).");
        } catch (EncoderException ee) {
            // Exception expected, test segment passes.
        }
    }
    @Test
    public void test31() throws Exception {
        String plain = "Hello Wörld!";
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("UnicodeBig");
        qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
        String encoded1 = qpcodec.encode(plain, "UnicodeBig");
        String encoded2 = qpcodec.encode(plain);
        assertEquals(encoded1, encoded2);
    }
    @Test
    public void test32() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "1+1 = 2";
        String encoded = new String(QuotedPrintableCodec.
            encodeQuotedPrintable(null, plain.getBytes("UTF-8")));
        assertEquals("Basic quoted-printable encoding test (regression 8)", 
            "1+1 =3D 2", encoded);
        assertEquals("Basic quoted-printable decoding test (regression 8)", 
            plain, qpcodec.decode(encoded));
        
    }
    @Test
    public void test33() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello world! =\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test (regression 9)", 
            "=3D=0D=0A", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test (regression 9)", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test34() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello world! =\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test (regression 10)", 
            "=3D Hello world! =3D=0D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test (regression 10)", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test35() throws Exception {
        // Test case with Russian characters
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
    public void test37() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "!@#$%^&()+{}",;
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test (regression 13)", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test38() throws Exception {
        // whitespace, but does not need to be encoded
        String plain ="This is a example of a quoted=printable text file. The end.";
        String expected = "This is a example of a quoted=3Dprintable text file. The end.";

        assertEquals(expected, new QuotedPrintableCodec().encode(plain));
    }
    @Test
    public void test39() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="This might contain sp=cial chars at the end.";
        String expected = "This might contain sp=3D=\r\ncial chars at the end.";
        assertEquals(expected, qpcodec.encode(plain));

        plain ="This might contain whitespace at the end        ";
        expected = "This might contain whitespace at the end=20=\r\n=20=20=20=20";
        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test40() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        byte[] plain = new byte[] {1, 2, 3, 4};
        byte[] encoded = qpcodec.encode(plain);
        assertEquals("Encoding a null string should return null (regression 16)", 
            null, encoded);
    }
    @Test
    public void test41() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="Ends with a soft break\t";
        String expected = "Ends with a soft break i=\r\nt=09";
        assertEquals(expected, qpcodec.encode(plain));

        plain ="Ends with a soft break ";
        expected = "Ends with a soft break i=\r\nt=20";
        assertEquals(expected, qpcodec.encode(plain));

        // whitespace before soft break
        plain ="Ends with a soft break   ";
        expected = "Ends with a soft break=20=\r\n=20=20";
        assertEquals(expected, qpcodec.encode(plain));

        // non-printable character before soft break
        plain ="Ends with a soft break=  ";
        expected = "Ends with a soft break=3D=\r\n=20";
        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test42() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "3.14 = π";
        String encoded = (String) qpcodec.encode((Object) plain);
        assertEquals("Basic quoted-printable encoding test (regression 18)", 
            "3.14 =3D =CF=80", encoded);

        byte[] plainBA = plain.getBytes("UTF-8");
        byte[] encodedBA = (byte[]) qpcodec.encode((Object) plainBA);
        encoded = new String(encodedBA);
        assertEquals("Basic quoted-printable encoding test (regression 18)", 
            "3.14 =3D =CF=80", encoded);
            
        Object result = qpcodec.encode((Object) "π");
        assertEquals( "Encoding an Object should return null if the encoding is not supported (regression 18)", null, result);
    }
    @Test
    public void test43() throws Exception {
        String plain = "Hello Wörld!";
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("UTF-8");
        qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
        String encoded1 = qpcodec.encode(plain, "UTF-8");
        String encoded2 = qpcodec.encode(plain);
        assertEquals(encoded1, encoded2);
    }
    @Test
    public void test44() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "3.14 + 2 = 5.14";
        String encoded = new String(QuotedPrintableCodec.
            encodeQuotedPrintable(null, plain.getBytes("UTF-8")));
        assertEquals("Basic quoted-printable encoding test (regression 20)", 
            "3.14 + 2 =3D 5.14", encoded);
        assertEquals("Basic quoted-printable decoding test (regression 20)", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test45() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=Foo!@#";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test (regression 21)", 
            "=3D Foo!@#", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test (regression 21)", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test46() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=Hello Wörld!=";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test (regression 22)", 
            "=3D Hello W=C3=B6rld!=", encoded);
        assertEquals("Basic quoted-printable decoding test (regression 22)", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test47() throws Exception {
        String ru_msg = constructString(RUSSIAN_STUFF_UNICODE); 
        String ch_msg = constructString(SWISS_GERMAN_STUFF_UNICODE); 
        
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        
        assertEquals(
            "=D0=92=D1=81=D0=E0=E0", 
        qpcodec.encode(ru_msg, "UTF-16")
        );
        assertEquals("Gr=C8=DCezi=C4=83=C4=95m=C3=A6", qpcodec.encode(ch_msg, "UTF-16"));
        
        assertEquals(ru_msg, qpcodec.decode(qpcodec.encode(ru_msg, "UTF-16"), "UTF-16"));
        assertEquals(ch_msg, qpcodec.decode(qpcodec.encode(ch_msg, "UTF-16"), "UTF-16"));
    }
    @Test
    public void test48() throws Exception {
        String qpdata = "If you believe!@#$%^&()*{} in mathematics, then surely mathematics is the most " +
                "b=\r\neautiful branch of philosophy.";
        String expected = "If you believe!@#$%^&()*{} in mathematics, then surely mathematics is the most " +
                "beautiful branch of philosophy.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(qpdata, qpcodec.encode(expected));

        String decoded = qpcodec.decode(qpdata);
        assertEquals(qpdata, qpcodec.encode(decoded));
    }
    @Test
    public void test49() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "789?abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test (regression 25)", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test50() throws Exception {
        // whitespace, but does not need to be encoded
        String plain ="This is a example of a quoted=printable text file. There is no questions about tt";
        String expected = "This is a example of a quoted=3Dprintable text file. There is no questions about tt";

        assertEquals(expected, new QuotedPrintableCodec().encode(plain));
    }
    @Test
    public void test51() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="This is a example of a quoted-printable text file. This might contain sp=cial chars!!!";
        String expected = "This is a example of a quoted-printable text file. This might contain sp=3D=\r\ncial chars!!!";
        assertEquals(expected, qpcodec.encode(plain));

        plain ="This is a example of a quoted-printable text file. This might contain ta\tbs as well!!!";
        expected = "This is a example of a quoted-printable text file. This might contain ta=09=\r\nbs as well!!!";
        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test52() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        byte[] plain = new byte[] {1, 2, 3, 4, 5};
        byte[] encoded = qpcodec.encode(plain);
        assertEquals("Encoding a null string should return null (regression 28)", 
            null, encoded);
    }
    @Test
    public void test53() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="This is a example of a quoted-printable text file. Ends with a soft break\t";
        String expected = "This is a example of a quoted-printable text file. Ends with a soft break i=\r\nt=09";
        assertEquals(expected, qpcodec.encode(plain));

        plain ="This is a example of a quoted-printable text file. Ends with a soft break ";
        expected = "This is a example of a quoted-printable text file. Ends with a soft break i=\r\nt=20";
        assertEquals(expected, qpcodec.encode(plain));

        // whitespace before soft break
        plain ="This is a example of a quoted-printable text file. Ends with a soft break   ";
        expected = "This is a example of a quoted-printable text file. Ends with a soft break=20=\r\n=20";
        assertEquals(expected, qpcodec.encode(plain));

        // non-printable character before soft break
        plain ="This is a example of a quoted-printable text file. Ends with a soft break=  ";
        expected = "This is a example of a quoted-printable text file. Ends with a soft break=3D=\r\n=20";
        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test54() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "1+1 = 2!!!";
        String encoded = (String) qpcodec.encode((Object) plain);
        assertEquals("Basic quoted-printable encoding test (regression 30)", 
            "This is a test object 1+1 =3D 2!!!", encoded);

        byte[] plainBA = plain.getBytes("UTF-8");
        byte[] encodedBA = (byte[]) qpcodec.encode((Object) plainBA);
        encoded = new String(encodedBA);
        assertEquals("Basic quoted-printable encoding test (regression 30)", 
            "This is a test object 1+1 =3D 2!!!", encoded);
            
        Object result = qpcodec.encode((Object) "\"Encōdé my sübÜ!\"");
        assertEquals( "Encoding an Object should return null if the encoding is not supported", null, result);
    }
    @Test
    public void test55() throws Exception {
        String plain = "Hello Wörld! Test";
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("UTF-8");
        qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
        String encoded1 = qpcodec.encode(plain, "UTF-8");
        String encoded2 = qpcodec.encode(plain);
        assertEquals(encoded1, encoded2);
    }
    @Test
    public void test56() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "3.14 + 2 = 5.14!!!";
        String encoded = new String(QuotedPrintableCodec.
            encodeQuotedPrintable(null, plain.getBytes("UTF-8")));
        assertEquals("Basic quoted-printable encoding test (regression 32)", 
            "This is a test object 3.14 + 2 =3D 5.14!!!", encoded);
        assertEquals("Basic quoted-printable decoding test (regression 32)", 
            plain, qpcodec.decode(encoded));
        
    }
    @Test
    public void test57() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=Foo!@#!!+=-";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test (regression 33)", 
            "=3D Foo!@#!!+=-!!!", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test (regression 33)", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test58() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=Hello Wörld!==-";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test (regression 34)", 
            "=3D Hello W=C3=B6rld!==-=3D=0D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test (regression 34)", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test59() throws Exception {
        String ru_msg = constructString(RUSSIAN_STUFF_UNICODE); 
        String ch_msg = constructString(SWISS_GERMAN_STUFF_UNICODE); 
        
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        
        assertEquals(
            "=D0=92=D1=81=D0=E0=E0=40==", 
        qpcodec.encode(ru_msg, "UTF-16")
        );
        assertEquals("Gr=C8=DCezi=C4=83=C4=95m=C3=A6=40==", qpcodec.encode(ch_msg, "UTF-16"));
        
        assertEquals(ru_msg, qpcodec.decode(qpcodec.encode(ru_msg, "UTF-16"), "UTF-16"));
        assertEquals(ch_msg, qpcodec.decode(qpcodec.encode(ch_msg, "UTF-16"), "UTF-16"));
    }
    @Test
    public void test60() throws Exception {
        String qpdata = "If you believe!@#$%^&()*{} in mathematics, then surely mathematics is the most " +
                "b=\r\neautiful branch of philosophy?#!*/";
        String expected = "If you believe!@#$%^&()*{} in mathematics, then surely mathematics is the most " +
                "beautiful branch of philosophy?#!*/";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(qpdata, qpcodec.encode(expected));

        String decoded = qpcodec.decode(qpdata);
        assertEquals(qpdata, qpcodec.encode(decoded));
    }
    @Test
    public void test61() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "0123456789=?abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test (regression 37)", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test62() throws Exception {
        // whitespace, but does not need to be encoded
        String plain ="There is no end to it!!";
        String expected = "There is no end to i=\r\nt!!";

        assertEquals(expected, new QuotedPrintableCodec().encode(plain));
    }
    @Test
    public void test63() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="This is a example of a quoted-printable text file. This might contain sp=cial chars!!!";
        String expected = "This is a example of a quoted-printable text file. This might contain sp=3D=\r\ncial chars!!!";
        assertEquals(expected, qpcodec.encode(plain));

        plain ="This is a example of a quoted-printable text file. This might contain ta\tbs as well!!!";
        expected = "This is a example of a quoted-printable text file. This might contain ta=09=\r\nbs as well!!!";
        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test64() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        byte[] plain = new byte[] {};
        byte[] encoded = qpcodec.encode(plain);
        assertEquals("Encoding a null string should return null (regression 40)", 
            null, encoded);
    }
    @Test
    public void test65() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="There is no end to it\t!!";
        String expected = "There is no end to i=\r\nt=09!!";
        assertEquals(expected, qpcodec.encode(plain));

        plain ="There is no end to it !!";
        expected = "There is no end to i=\r\nt=20!!";
        assertEquals(expected, qpcodec.encode(plain));

        // whitespace before soft break
        plain ="There is no end to   !!";
        expected = "There is no end to=20=\r\n=20!!";
        assertEquals(expected, qpcodec.encode(plain));

        // non-printable character before soft break
        plain ="There is no end to=  !!";
        expected = "There is no end to=3D=\r\n=20!!";
        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test66() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "1+1 = 2!!!";
        String encoded = (String) qpcodec.encode((Object) plain);
        assertEquals("Basic quoted-printable encoding test (regression 42)", 
            "This is a test object 1+1 =3D 2!!!", encoded);

        byte[] plainBA = plain.getBytes("UTF-8");
        byte[] encodedBA = (byte[]) qpcodec.encode((Object) plainBA);
        encoded = new String(encodedBA);
        assertEquals("Basic quoted-printable encoding test (regression 42)", 
            "This is a test object 1+1 =3D 2!!!", encoded);
            
        Object result = qpcodec.encode((Object) "\nNew Line = \r\n Carriage Return = \r");
        assertEquals( "Encoding an Object should return null if the encoding is not supported", null, result);
    }
    @Test
    public void test67() throws Exception {
        String plain = "Hello Wörlä! With sōmè punctUation.";
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("UTF-8");
        qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
        String encoded1 = qpcodec.encode(plain, "UTF-8");
        String encoded2 = qpcodec.encode(plain);
        assertEquals(encoded1, encoded2);
    }
    @Test
    public void test68() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "3.14 + 2 = 5.14$$$";
        String encoded = new String(QuotedPrintableCodec.
            encodeQuotedPrintable(null, plain.getBytes("UTF-8")));
        assertEquals("Basic quoted-printable encoding test (regression 44)", 
            "This is a test object 3.14 + 2 =3D 5.14$$$", encoded);
        assertEquals("Basic quoted-printable decoding test (regression 44)", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test69() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=Foo!@#!!+=-\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test (regression 45)", 
            "=3D Foo!@#!!+=-\n", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test (regression 45)", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test70() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=Hello Wörld!==-\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test (regression 46)", 
            "=3D Hello W=C3=B6rld!==-\n", encoded);
        assertEquals("Basic quoted-printable decoding test (regression 46)", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test71() throws Exception {
        String ru_msg = constructString(RUSSIAN_STUFF_UNICODE); 
        String ch_msg = constructString(SWISS_GERMAN_STUFF_UNICODE); 
        
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        
        assertEquals(
            "=D0=92=D1=81=D0=E0=E0=40====", 
        qpcodec.encode(ru_msg, "UTF-16")
        );
        assertEquals("Gr=C8=DCezi=C4=83=C4=95m=C3=A6=40====", qpcodec.encode(ch_msg, "UTF-16"));
        
        assertEquals(ru_msg, qpcodec.decode(qpcodec.encode(ru_msg, "UTF-16"), "UTF-16"));
        assertEquals(ch_msg, qpcodec.decode(qpcodec.encode(ch_msg, "UTF-16"), "UTF-16"));
    }
    @Test
    public void test72() throws Exception {
        String qpdata = "If you believe!@#$%^&()*{} in mathematics, then surely mathematics is the most " +
                "b=\r\neautiful branch of philosophy?#!*/-\n";
        String expected = "If you believe!@#$%^&()*{} in mathematics, then surely mathematics is the most " +
                "beautiful branch of philosophy?#!*/-\n";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(qpdata, qpcodec.encode(expected));

        String decoded = qpcodec.decode(qpdata);
        assertEquals(qpdata, qpcodec.encode(decoded));
    }
    @Test
    public void test73() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "####Hello3$%^&*( ) <>?:";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test (regression 49)", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test74() throws Exception {
        // whitespace, but does not need to be encoded
        String plain ="End";
        String expected = "End";

        assertEquals(expected, new QuotedPrintableCodec().encode(plain));
    }
    @Test
    public void test75() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="This might contain sp=cial chars!!!";
        String expected = "This might contain sp=3D=\r\ncial chars!!!";
        assertEquals(expected, qpcodec.encode(plain));

        plain ="This might contain whitespace as well        ";
        expected = "This might contain whitespace as well=20=\r\n=20=20=20=20=20=20";
        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test76() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        byte[] plain = new byte[0];
        byte[] encoded = qpcodec.encode(plain);
        assertEquals("Encoding a null string should return null (regression 52)", 
            null, encoded);
    }
    @Test
    public void test77() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="End ends with a soft break\t!!!";
        String expected = "End ends with a soft break i=\r\nt=09!!!";
        assertEquals(expected, qpcodec.encode(plain));

        plain ="End ends with a soft break ";
        expected = "End ends with a soft break i=\r\nt=20!";
        assertEquals(expected, qpcodec.encode(plain));

        // whitespace before soft break
        plain ="End ends with a soft break   ";
        expected = "End ends with a soft break=20=\r\n=20=20=20!";
        assertEquals(expected, qpcodec.encode(plain));

        // non-printable character before soft break
        plain ="End ends with a soft break=  ";
        expected = "End ends with a soft break=3D=\r\n=20!";
        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test78() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "3.14 + 2 = 5.14!!!";
        String encoded = (String) qpcodec.encode((Object) plain);
        assertEquals("Basic quoted-printable encoding test (regression 54)", 
            "3.14 + 2 =3D 5.14!!!", encoded);

        byte[] plainBA = plain.getBytes("UTF-8");
        byte[] encodedBA = (byte[]) qpcodec.encode((Object) plainBA);
        encoded = new String(encodedBA);
        assertEquals("Basic quoted-printable encoding test (regression 54)", 
            "3.14 + 2 =3D 5.14!!!", encoded);
            
        Object result = qpcodec.encode((Object) "\nNew Læne = \r\n CaarrØge Return = \r");
        assertEquals( "Encoding an Object should return null if the encoding is not supported", null, result);
    }
    @Test
    public void test79() throws Exception {
        String plain = "Hello Wörld!";
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("UTF-8");
        qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
        String encoded1 = qpcodec.encode(plain, "UTF-8");
        String encoded2 = qpcodec.encode(plain);
        assertEquals(encoded1, encoded2);
    }
    @Test
    public void test80() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "3.14 + 2 = 5.14";
        String encoded = new String(QuotedPrintableCodec.
            encodeQuotedPrintable(null, plain.getBytes("UTF-8")));
        assertEquals("Basic quoted-printable encoding test (regression 56)", 
            "3.14 + 2 =3D 5.14", encoded);
        assertEquals("Basic quoted-printable decoding test (regression 56)", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test81() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=Hello Wörld! =";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test (regression 57)", 
            "=3D Hello W=C3=B6rld! =", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test (regression 57)", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test82() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=Hello Wörld! =";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test (regression 58)", 
            "=3D Hello W=C3=B6rld! =", encoded);
        assertEquals("Basic quoted-printable decoding test (regression 58)", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test83() throws Exception {
        String ru_msg = constructString(RUSSIAN_STUFF_UNICODE); 
        String ch_msg = constructString(SWISS_GERMAN_STUFF_UNICODE); 
        
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        
        assertEquals(
            "=D0=92=D1=81=D0=E0=E0=", 
        qpcodec.encode(ru_msg, "UTF-16")
        );
        assertEquals("Gr=C8=DCezi=C4=83=C4=95m=C3=A6=", qpcodec.encode(ch_msg, "UTF-16"));
        
        assertEquals(ru_msg, qpcodec.decode(qpcodec.encode(ru_msg, "UTF-16"), "UTF-16"));
        assertEquals(ch_msg, qpcodec.decode(qpcodec.encode(ch_msg, "UTF-16"), "UTF-16"));
    }
    @Test
    public void test84() throws Exception {
        String qpdata = "If you believe!@#$%^&()*{} in mathematics, then surely mathematics is the most " +
                "b=\r\neautiful branch of philosophy?#!*/-= ";
        String expected = "If you believe!@#$%^&()*{} in mathematics, then surely mathematics is the most " +
                "beautiful branch of philosophy?#!*/-= ";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(qpdata, qpcodec.encode(expected));

        String decoded = qpcodec.decode(qpdata);
        assertEquals(qpdata, qpcodec.encode(decoded));
    }
    @Test
    public void test85() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test86() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:,/?=_";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test87() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain, CharEncoding.UTF_16);
        assertEquals("Safe chars quoted-printable encoding test with different encoding", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test with different encoding", 
            plain, qpcodec.decode(encoded, CharEncoding.UTF_16));
    }
    @Test
    public void test88() throws Exception {
        byte[] plain = null;
        byte[] result = QuotedPrintableCodec.decodeQuotedPrintable( plain );
        assertEquals("Result should be null", null, result);
    }
    @Test
    public void test89() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=3D=0D=0A", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test90() throws Exception {
        String qpdata = "CRLF in an\n encoded text should be=20=\r\n\rskipped in the\r decoding.";
        String expected = "CRLF in an encoded text should be skipped in the decoding.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(expected, qpcodec.decode(qpdata));

        String encoded = qpcodec.encode(expected);
        assertEquals(expected, qpcodec.decode(encoded));
    }
    @Test
    public void test91() throws Exception {
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
    public void test92() {
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
    public void test95() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there =\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Hello there =3D=0D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test96() throws Exception {

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
    public void test97() throws Exception {
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
    public void test98() throws Exception {
        byte[] plain = null;
        try {
            QuotedPrintableCodec.decodeQuotedPrintable(plain);
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
    }
    @Test
    public void test99() throws Exception {
        byte[] plain = new byte[0];
        byte[] result = QuotedPrintableCodec.decodeQuotedPrintable(plain);
        assertArrayEquals("Result should be an empty array", new byte[0], result);
    }
    @Test
    public void test100() throws Exception {
        byte[] plain = new byte[] { 0x41, 0x09, 0x47, 0x43 }; // A\tGC
        byte[] result = QuotedPrintableCodec.decodeQuotedPrintable(plain);
        assertArrayEquals("Result should be the same array", plain, result);
    }
    @Test
    public void test101() throws Exception {
        byte[] plain = new byte[] { 0x41, 0x3D, 0x0D, 0x0A, 0x42 }; // A=\r\nB
        byte[] result = QuotedPrintableCodec.decodeQuotedPrintable(plain);
        assertArrayEquals("Result should be A+B", new byte[] { 0x41, 0x42 }, result);
    }
    @Test
    public void test102() throws Exception {
        byte[] plain = new byte[] { 0x41, 0x3D, 0x32, 0x33, 0x0D, 0x0A }; // A=23\r\n
        byte[] result = QuotedPrintableCodec.decodeQuotedPrintable(plain);
        assertArrayEquals("Result should be invalid", new byte[] { 0x41, 0x3D, 0x32, 0x33 }, result);
    }
    @Test
    public void test103() throws Exception {
        byte[] plain = new byte[] { 0x41, 0x3D, 0x46, 0x47, 0x0D, 0x0A }; // A=FG\r\n
        byte[] result = QuotedPrintableCodec.decodeQuotedPrintable(plain);
        assertArrayEquals("Result should be invalid", new byte[] { 0x41, 0x3D, 0x46, 0x47 }, result);
    }
    @Test
    public void test104() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertNull("Null string quoted-printable encoding test", 
            qpcodec.encode((String)null));
        assertNull("Null string quoted-printable decoding test", 
            qpcodec.decode((String)null));
        
        // Regression Test
        assertNotNull("Empty string quoted-printable encoding test", 
            qpcodec.encode(""));
        assertNull("Empty string quoted-printable decoding test", 
            qpcodec.decode(""));
    }
    @Test
    public void test105() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        
        // Regression Test
        String plain2 = "ABC";
        String encoded2 = qpcodec.encode(plain2);
        String decoded2 = qpcodec.decode(encoded2);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain2, encoded2);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain2, decoded2);
    }
    @Test
    public void test106() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=3D=0D=0A", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        
        // Regression Test
        String plain2 = "A\r\nB";
        String encoded2 = qpcodec.encode(plain2);
        String decoded2 = qpcodec.decode(encoded2);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=41=0D=0A=42", encoded2);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain2, decoded2);
    }
    @Test
    public void test107() throws Exception {
        String qpdata = "CRLF in an\n encoded text should be=20=\r\n\rskipped in the\r decoding.";
        String expected = "CRLF in an encoded text should be skipped in the decoding.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(expected, qpcodec.decode(qpdata));

        String encoded = qpcodec.encode(expected);
        assertEquals(expected, qpcodec.decode(encoded));
        
        // Regression Test
        String qpdata2 = "A text";
        String expected2 = "A text";
        
        String encoded2 = qpcodec.encode(expected2);
        assertEquals(encoded2, qpcodec.encode(qpdata2));
        assertEquals(expected2, qpcodec.decode(encoded2));
        assertEquals(qpdata2, qpcodec.decode(encoded2));
    }
    @Test
    public void test108() throws Exception {
        String qpdata = "If you believe that truth=3Dbeauty, then surely=20=\r\nmathematics " +
                "is the most beautiful branch of philosophy.";
        String expected = "If you believe that truth=beauty, then surely mathematics " +
                "is the most beautiful branch of philosophy.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(expected, qpcodec.decode(qpdata));

        String encoded = qpcodec.encode(expected);
        assertEquals(expected, qpcodec.decode(encoded));
        
        // Regression Test
        String qpdata2 = "Hello=20World";
        String expected2 = "Hello World";
        
        String encoded2 = qpcodec.encode(expected2);
        assertEquals(expected2, qpcodec.decode(encoded2));
        assertEquals(expected2, qpcodec.decode(qpdata2));
        assertEquals(encoded2, qpcodec.encode(qpdata2));
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
            
        // Regression Test
        try {
            QuotedPrintableCodec qpcodec2 = new QuotedPrintableCodec("");
               qpcodec2.decode(plain);
            qpcodec2.encode(plain);
                fail( "We set the encoding to an empty string, this shouldn't have worked.");
            } catch (DecoderException | EncoderException ee) {
                // Exception expected, test segment passes.
            }
    }
    @Test
    public void test110() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "1+1 = 2";
        String encoded = new String(QuotedPrintableCodec.
            encodeQuotedPrintable(null, plain.getBytes("UTF-8")));
        assertEquals("Basic quoted-printable encoding test", 
            "1+1 =3D 2", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        
        // Regression Test
        String plain2 = "A&B = C";
        String encoded2 = qpcodec.encode(plain2);
        String decoded2 = qpcodec.decode(encoded2);
        assertEquals("Basic quoted-printable encoding test", 
            "A=26B =20=43", encoded2);
        assertEquals("Basic quoted-printable decoding test", 
            plain2, decoded2);
    }
    @Test
    public void test111() throws Exception {
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
        
        // Regression Test
        Object result2 = qpcodec.decode((Object) "");
        assertEquals( "Decoding an empty string Object should return null", null, result2);
        
        try {
            qpcodec.decode( (Object) new Double(0.0) );
            fail( "Trying to url encode a Double object should cause an exception.");
        } catch (DecoderException ee) {
            // Exception expected, test segment passes.
        }
    }
    @Test
    public void test112() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there =\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Hello there =3D=0D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        
        // Regression Test
        String plain2 = "A=2BB=2BC=2BD";
        String encoded2 = qpcodec.encode(plain2);
        String decoded2 = qpcodec.decode(encoded2);
        assertEquals("Basic quoted-printable encoding test", 
            "=41=2B=42=2B=43=2B=44", encoded2);
        assertEquals("Basic quoted-printable decoding test", 
            plain2, decoded2);
    }
    @Test
    public void test113() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String test = null;
        String result = qpcodec.decode( test, "charset" );
        assertEquals("Result should be null", null, result);
        
        // Regression Test
        String test2 = "";
        String result2 = qpcodec.decode( test2, "charset" );
        assertEquals("Result should be null", "", result2);
    }
    @Test
    public void test114() throws Exception {

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
        
        // Regression Test
        String ru_msg2 = constructString("Russian String"); 
        String ch_msg2 = constructString("Swiss German String"); 
        
        assertEquals(
            "=52=75=73=73=69=61=6E2053=74=72=69=6E67", 
        qpcodec.encode(ru_msg2, CharEncoding.UTF_8)
        );
        assertEquals("http://www.ceyoniq.ch/ceyMP/images/Reddot/2_2_2_Emul_sw.jpg",
        		qpcodec.decode("http://www.ceyoniq.ch/ceyMP/images/Reddot/2_2_2_Emul_sw=2Ejpg", CharEncoding.UTF_8)
        );
        
        assertEquals(ru_msg2, qpcodec.decode(qpcodec.encode(ru_msg2, CharEncoding.UTF_8), CharEncoding.UTF_8));
        assertEquals(ch_msg2, qpcodec.decode(qpcodec.encode(ch_msg2, CharEncoding.UTF_8), CharEncoding.UTF_8));
    }
    @Test
    public void test115() throws Exception {
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
        
        // Regression Test
        try {
            qpcodec.decode("==");
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
        try {
            qpcodec.decode("=09");
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
        try {
            qpcodec.decode("=41=");
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
    }
    @Test
    public void test116() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertNull("Null string quoted-printable encoding test", 
            qpcodec.encode((String)null));
        assertNull("Null string quoted-printable decoding test", 
            qpcodec.decode((String)null));
        
        // Regression test case: empty string
        assertEquals("", qpcodec.encode(""));
        assertEquals("", qpcodec.decode(""));
    }
    @Test
    public void test117() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        
        // Regression test case: string with spaces
        plain = "hello world";
        encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test with spaces", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test with spaces", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test118() throws Exception {
        // whitespace, but does not need to be encoded
        String plain ="This is a example of a quoted=printable text file. There is no tt";
        String expected = "This is a example of a quoted=3Dprintable text file. There is no tt";

        assertEquals(expected, new QuotedPrintableCodec().encode(plain));
    }
    @Test
    public void test119() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="This is a example of a quoted-printable text file. This might contain sp=cial chars.";
        String expected = "This is a example of a quoted-printable text file. This might contain sp=3D=\r\ncial chars.";
        assertEquals(expected, qpcodec.encode(plain));

        plain ="This is a example of a quoted-printable text file. This might contain ta\tbs as well.";
        expected = "This is a example of a quoted-printable text file. This might contain ta=09=\r\nbs as well.";
        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test120() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        byte[] plain = null;
        byte[] encoded = qpcodec.encode(plain);
        assertEquals("Encoding a null string should return null", 
            null, encoded);
    }
    @Test
    public void test121() throws Exception {
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
    public void test122() throws Exception {
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
    public void test123() throws Exception {
        String plain = "Hello there!";
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("UnicodeBig");
        qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
        String encoded1 = qpcodec.encode(plain, "UnicodeBig");
        String encoded2 = qpcodec.encode(plain);
        assertEquals(encoded1, encoded2);
    }
    @Test
    public void test124() {
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
    public void test125() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String test = null;
        String result = qpcodec.encode( test, "charset" );
        assertEquals("Result should be null", null, result);
    }
    @Test
    public void test126() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=3D=0D=0A", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test127() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there =\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Hello there =3D=0D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test128() throws Exception {

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
    public void test129() throws Exception {
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
    public void test130() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "!@#$%^&*()_+-=`~[]{}|;':,.<>?/\\";
        String encoded = qpcodec.encode(plain);
        assertEquals("Special chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Special chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test131() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "漢字";
        String encoded = qpcodec.encode(plain);
        assertEquals("Non-ASCII chars quoted-printable encoding test", 
            "=E6=BC=A2=E5=AD=97", encoded);
        assertEquals("Non-ASCII chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test132() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "\n\t";
        String encoded = qpcodec.encode(plain);
        assertEquals("Control chars quoted-printable encoding test", 
            "=0A=09", encoded);
        assertEquals("Control chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test133() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertNull("Null string quoted-printable encoding test", 
            qpcodec.encode((String)null));
        assertNull("Null string quoted-printable decoding test", 
            qpcodec.decode((String)null));
    }
    @Test
    public void test134() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test135() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=3D=0D=0A", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test136() throws Exception {
        String qpdata = "CRLF in an\n encoded text should be=20=\r\n\rskipped in the\r decoding.";
        String expected = "CRLF in an encoded text should be skipped in the decoding.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(expected, qpcodec.decode(qpdata));

        String encoded = qpcodec.encode(expected);
        assertEquals(expected, qpcodec.decode(encoded));
    }
    @Test
    public void test137() throws Exception {
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
    public void test138() {
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
    public void test139() throws Exception {
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
    public void test140() throws Exception {
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
    public void test141() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there =\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Hello there =3D=0D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test142() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String test = null;
        String result = qpcodec.decode( test, "charset" );
        assertEquals("Result should be null", null, result);
    }
    @Test
    public void test143() throws Exception {

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
    public void test144() throws Exception {
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
    public void test145() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String invalidCharset = "INVALID_CHARSET";
        String plain = "Hello there!";
        try {
            qpcodec.decode(plain, invalidCharset);
            fail("DecoderException should have been thrown due to invalid charset");
        } catch (DecoderException e) {
            // Expected. Move on
        }
    }
    @Test
    public void test146() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String specialChars = "=Hello=World!=";
        String decoded = qpcodec.decode(specialChars);
        assertEquals("Decoded string should be 'Hello World!'", "Hello World!", decoded);
    }
    @Test
    public void test147() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String emptyString = "";
        String decoded = qpcodec.decode(emptyString);
        assertEquals("Decoded string should be empty", "", decoded);
    }
    @Test
    public void test148() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String nonAsciiChars = "=D0=92=D0=BE=D0=B7=D1=80=D0=B0=D1=81=D1=82";
        String decoded = qpcodec.decode(nonAsciiChars);
        assertEquals("Decoded string should be 'Возраст'", "Возраст", decoded);
    }
    @Test
    public void test149() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertNull("Null string quoted-printable encoding test", 
            qpcodec.encode((String)null));
        assertNull("Null string quoted-printable decoding test", 
            qpcodec.decode((String)null));
    }
    @Test
    public void test150() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test151() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=3D=0D=0A", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test152() throws Exception {
        String qpdata = "CRLF in an\n encoded text should be=20=\r\n\rskipped in the\r decoding.";
        String expected = "CRLF in an encoded text should be skipped in the decoding.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(expected, qpcodec.decode(qpdata));

        String encoded = qpcodec.encode(expected);
        assertEquals(expected, qpcodec.decode(encoded));
    }
    @Test
    public void test153() throws Exception {
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
    public void test154() {
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
    public void test155() throws Exception {
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
    public void test156() throws Exception {
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
    public void test157() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there =\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Hello there =3D=0D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test158() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String test = null;
        String result = qpcodec.decode( test, "charset" );
        assertEquals("Result should be null", null, result);
    }
    @Test
    public void test159() throws Exception {

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
    public void test160() throws Exception {
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
    public void test161() throws Exception {
        // Change input from null to empty string
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals("Empty string quoted-printable encoding test", 
            "", qpcodec.encode(""));
        assertEquals("Empty string quoted-printable decoding test", 
            "", qpcodec.decode(""));
    }
    @Test
    public void test162() throws Exception {
        // Change input from "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]" to "abc 123_-.*~!@#$%^&()+{}\"\\;:`,/[]"
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc 123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars with whitespace quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars with whitespace quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test163() throws Exception {
        // Change input from "=\r\n" to "=\r\n\s\t"
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=\r\n\s\t";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars with additional unsafe chars quoted-printable encoding test", 
            "=3D=0D=0A=5Cs=09", encoded);
        assertEquals("Unsafe chars with additional unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test164() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertNotNull("Null string quoted-printable encoding test", 
            qpcodec.encode(""));
        assertNotNull("Null string quoted-printable decoding test", 
            qpcodec.decode(""));
    }
    @Test
    public void test165() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain);
        assertNotEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertNotEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test166() throws Exception {
        // whitespace, but does not need to be encoded
        String plain ="This is a example of a quoted-printable text file. There is no de";
        String expected = "This is a example of a quoted=3Dprintable text file. There is no de";

        assertNotEquals(expected, new QuotedPrintableCodec().encode(plain));
    }
    @Test
    public void test167() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="This is a example of a quoted-printable text file. This might contain sp";
        String expected = "This is a example of a quoted-printable text file. This might contain sp=3D=\r\ncial chars.";
        assertNotEquals(expected, qpcodec.encode(plain));

        plain ="This is a example of a quoted-printable text file. This might contain ta";
        expected = "This is a example of a quoted-printable text file. This might contain ta=09=\r\nbs as well.";
        assertNotEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test168() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = null;
        byte[] encoded = qpcodec.encode(plain);
        assertNotEquals("Encoding a null string should return null", null, encoded);
    }
    @Test
    public void test169() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="This is a example of a quoted-printable text file. There is no end to ";
        String expected = "This is a example of a quoted-printable text file. There is no end to i=\r\nt=09";

        assertNotEquals(expected, qpcodec.encode(plain));

        plain ="This is a example of a quoted-printable text file. There is no end to it ";
        expected = "This is a example of a quoted-printable text file. There is no end to i=\r\nt=20";

        assertNotEquals(expected, qpcodec.encode(plain));

        // whitespace before soft break
        plain ="This is a example of a quoted-printable text file. There is no end to  ";
        expected = "This is a example of a quoted-printable text file. There is no end to=20=\r\n =20";

        assertNotEquals(expected, qpcodec.encode(plain));

        // non-printable character before soft break
        plain ="This is a example of a quoted-printable text file. There is no end to= ";
        expected = "This is a example of a quoted-printable text file. There is no end to=3D=\r\n =20";

        assertNotEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test170() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "+1 - 1";
        String encoded = (String) qpcodec.encode((Object) plain);
        assertNotEquals("Basic quoted-printable encoding test", 
            "3D 2", encoded);

        byte[] plainBA = plain.getBytes("UTF-8");
        byte[] encodedBA = (byte[]) qpcodec.encode((Object) plainBA);
        encoded = new String(encodedBA);
        assertNotEquals("Basic quoted-printable encoding test", 
            "3D 2", encoded);
            
        Object result = qpcodec.encode((Object) "test");
        assertNotEquals( "Encoding a null Object should return null", null, result);
        
        try {
            Object dObj = new Double(3.0);
            qpcodec.encode( dObj );
            fail( "Trying to url encode a Double object should cause an exception.");
        } catch (EncoderException ee) {
            // Exception expected, test segment passes.
        }
    }
    @Test
    public void test171() throws Exception {
        String plain = "Hello world!";
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("UTF-8");
        qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
        String encoded1 = qpcodec.encode(plain, "UTF-8");
        String encoded2 = qpcodec.encode(plain);
        assertNotEquals(encoded1, encoded2);
    }
    @Test
    public void test172() {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("INVALID_ENCODING");
           String plain = "Hello there!";
            try {
               qpcodec.encode(plain);
                fail( "We set the encoding to a bogus INVALID_ENCODING value, this shouldn't have worked.");
            } catch (EncoderException ee) {
                // Exception expected, test segment passes.
            }
            try {
               qpcodec.decode(plain);
                fail( "We set the encoding to a bogus INVALID_ENCODING value, this shouldn't have worked.");
            } catch (DecoderException ee) {
                // Exception expected, test segment passes.
            }
    }
    @Test
    public void test173() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String test = "";
        String result = qpcodec.encode( test, "charset" );
        assertNotEquals("Result should be null", null, result);
    }
    @Test
    public void test174() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "@";
        String encoded = qpcodec.encode(plain);
        assertNotEquals("Unsafe chars quoted-printable encoding test", 
            plain, encoded);
        assertNotEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test175() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = " Hello there ";
        String encoded = qpcodec.encode(plain);
        assertNotEquals("Basic quoted-printable encoding test", 
            "= Hello there =", encoded);
        assertNotEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test176() throws Exception {

        String ru_msg = "Привет"; 
        String ch_msg = "Grüezi";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        
        assertNotEquals(
            "=D0=92=D1=81=D0=B5=D0=BC_=D0=BF=D1=80=D0=B8=D0=B2=D0=B5=D1=82", 
        qpcodec.encode(ru_msg, "UTF-8")
        );
        assertNotEquals("Gr=C3=BCezi_z=C3=A4m=C3=A4", qpcodec.encode(ch_msg, "UTF-8"));
        
        assertNotEquals(ru_msg, qpcodec.decode(qpcodec.encode(ru_msg, "UTF-8"), "UTF-8"));
        assertNotEquals(ch_msg, qpcodec.decode(qpcodec.encode(ch_msg, "UTF-8"), "UTF-8"));
    }
    @Test
    public void test177() throws Exception {
        String qpdata = "If you believe that truth=beauty, then surely mathematics is the most " +
                "beautiful b\n" +
                "ranch of philosophy.";
        String expected = "If you believe that truth=beauty, then surely mathematics is the most " +
                "beautiful branch of philosophy.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertNotEquals(qpdata, qpcodec.encode(expected));

        String decoded = qpcodec.decode(qpdata);
        assertNotEquals(qpdata, qpcodec.encode(decoded));
    }
    @Test
    public void test178() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertNull("Null string quoted-printable encoding test", 
            qpcodec.encode((String)null));
        assertNull("Null string quoted-printable decoding test", 
            qpcodec.decode((String)null));
        
        // Regression test: passing an empty string as input
        assertEquals("", qpcodec.encode(""));
        assertEquals("", qpcodec.decode(""));
        
        // Regression test: passing a string with only whitespace characters as input
        assertEquals("", qpcodec.encode("   "));
        assertEquals("", qpcodec.decode("   "));
    }
    @Test
    public void test179() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        
        // Regression test: passing a string with additional safe characters as input
        String plain2 = "^_|";
        String encoded2 = qpcodec.encode(plain2);
        assertEquals("^_=5F=7C", encoded2);
        assertEquals(plain2, qpcodec.decode(encoded2));
    }
    @Test
    public void test180() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=3D=0D=0A", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        
        // Regression test: passing a string with additional unsafe characters as input
        String plain2 = "<>";
        String encoded2 = qpcodec.encode(plain2);
        assertEquals(plain2, qpcodec.decode(encoded2));
    }
    @Test
    public void test181() throws Exception {
        String qpdata = "CRLF in an\n encoded text should be=20=\r\n\rskipped in the\r decoding.";
        String expected = "CRLF in an encoded text should be skipped in the decoding.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(expected, qpcodec.decode(qpdata));

        String encoded = qpcodec.encode(expected);
        assertEquals(expected, qpcodec.decode(encoded));
        
        // Regression test: passing a string with additional CRLF characters as input
        String qpdata2 = "This is an\nexample with\rcustom CRLF characters.";
        String expected2 = "This is anexample withcustom CRLF characters.";
        
        assertEquals(expected2, qpcodec.decode(qpdata2));

        String encoded2 = qpcodec.encode(expected2);
        assertEquals(expected2, qpcodec.decode(encoded2));
    }
    @Test
    public void test182() throws Exception {
        String qpdata = "If you believe that truth=3Dbeauty, then surely=20=\r\nmathematics " +
                "is the most beautiful branch of philosophy.";
        String expected = "If you believe that truth=beauty, then surely mathematics " +
                "is the most beautiful branch of philosophy.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(expected, qpcodec.decode(qpdata));

        String encoded = qpcodec.encode(expected);
        assertEquals(expected, qpcodec.decode(encoded));
        
        // Regression test: passing a string without soft line breaks as input
        String qpdata2 = "This is a string without soft line breaks.";
        String expected2 = "This is a string without soft line breaks.";
        
        assertEquals(expected2, qpcodec.decode(qpdata2));

        String encoded2 = qpcodec.encode(expected2);
        assertEquals(expected2, qpcodec.decode(encoded2));
    }
    @Test
    public void test183() {
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
            
           // Regression test: passing a valid encoding as input, but with non-quitable characters
            QuotedPrintableCodec qpcodec2 = new QuotedPrintableCodec("UTF-16");
           String plain2 = "Hello there!";
            try {
               qpcodec2.encode(plain2);
                fail( "We set the encoding to UTF-16, which doesn't support quoted-printable encoding, this shouldn't have worked.");
            } catch (EncoderException ee) {
                // Exception expected, test segment passes.
            }
    }
    @Test
    public void test184() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "1+1 = 2";
        String encoded = new String(QuotedPrintableCodec.
            encodeQuotedPrintable(null, plain.getBytes("UTF-8")));
        assertEquals("Basic quoted-printable encoding test", 
            "1+1 =3D 2", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        
        // Regression test: passing a different bit set as input
        BitSet bitSet = new BitSet();
        bitSet.set(8);
        bitSet.set(10);
        bitSet.set(11);
        
        String plain2 = "1+1 = 2";
        String encoded2 = new String(QuotedPrintableCodec.
            encodeQuotedPrintable(bitSet, plain2.getBytes("UTF-8")));
        assertEquals(plain2, qpcodec.decode(encoded2));
    }
    @Test
    public void test185() throws Exception {
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
    public void test186() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there =\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Hello there =3D=0D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        
        // Regression test: passing a string with fewer equal signs as input
        String plain2 = "= Hello there \r\n";
        String encoded2 = qpcodec.encode(plain2);
        assertEquals(encoded2, encoded);
        
        // Regression test: passing a string with additional equal signs as input
        String plain3 = "== Hello there =\r\n";
        String encoded3 = qpcodec.encode(plain3);
        assertEquals("=3D=3D Hello there =3D=0D=0A", encoded3);
    }
    @Test
    public void test187() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String test = null;
        String result = qpcodec.decode( test, "charset" );
        assertEquals("Result should be null", null, result);
        
        // Regression test: passing a non-null input string with a null charset
        String test2 = "abc";
        String result2 = qpcodec.decode( test2, null );
        assertEquals("Result should be equal to the input", test2, result2);
    }
    @Test
    public void test188() throws Exception {

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
        
        // Regression test: passing an encoded string with a different charset
        String encoded = "=D0=92=D1=81=D0=B5=D0=BC_=D0=BF=D1=80=D0=B8=D0=B2=D0=B5=D1=82";
        assertEquals(ru_msg, qpcodec.decode(encoded, "US-ASCII"));
    }
    @Test
    public void test189() throws Exception {
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
        
        // Regression test: passing an encoded string with an invalid character
        try {
            qpcodec.decode("=0Z");
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
    }
    @Test
    public void test190() {
        // Test with different default charsets
        QuotedPrintableCodec qpcodec1 = new QuotedPrintableCodec("UTF-8");
        assertEquals("UTF-8", qpcodec1.getDefaultCharset());

        QuotedPrintableCodec qpcodec2 = new QuotedPrintableCodec("ISO-8859-1");
        assertEquals("ISO-8859-1", qpcodec2.getDefaultCharset());
    }
    @Test
    public void test191() throws Exception {
        // Test with empty input
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "";
        String encoded = qpcodec.encode(plain);
        assertEquals("", encoded);
        assertEquals("", qpcodec.decode(encoded));
    }
    @Test
    public void test192() throws Exception {
        // Test with special characters in input
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc#123=_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain);
        assertEquals("abc#123=_-.*~!@#$%^&()+{}\"\\;:`,/[]", encoded);
        assertEquals("abc#123=_-.*~!@#$%^&()+{}\"\\;:`,/[]", qpcodec.decode(encoded));
    }
    @Test
    public void test193() throws Exception {
        // Test with multiple occurrences of the same characters in input
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "AAA";
        String encoded = qpcodec.encode(plain);
        assertEquals("AAA", encoded);
        assertEquals("AAA", qpcodec.decode(encoded));
    }
    @Test
    public void test194() throws Exception {
        // Test with input containing multiple lines
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "This is a\nsample\ninput\ntext";
        String encoded = qpcodec.encode(plain);
        assertEquals("This is a=\nsample=\ninput=\ntext", encoded);
        assertEquals("This is a\nsample\ninput\ntext", qpcodec.decode(encoded));
    }
    @Test
    public void test195() throws Exception {
        // Test with long input
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "This is a very long input text. It is used to test the encoding and decoding methods.";
        String encoded = qpcodec.encode(plain);
        String expectedEncoded = "This is a very long input text. It is used to test the encoding and decoding methods.";
        assertEquals(expectedEncoded, encoded);
        assertEquals(plain, qpcodec.decode(encoded));
    }
    @Test
    public void test196() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(null, qpcodec.encode((String)null));
        assertEquals(null, qpcodec.decode((String)null));
    }
    @Test
    public void test197() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain);
        assertEquals(plain, encoded);
        assertEquals(plain, qpcodec.decode(encoded));
    }
    @Test
    public void test198() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain ="This is a example of a quoted=printable text file. There is no tt";
        String expected = "This is a example of a quoted=3Dprintable text file. There is no tt";
        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test199() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain ="This is a example of a quoted-printable text file. This might contain sp=cial chars.";
        String expected = "This is a example of a quoted-printable text file. This might contain sp=3D=\r\ncial chars.";
        assertEquals(expected, qpcodec.encode(plain));

        plain ="This is a example of a quoted-printable text file. This might contain ta\tbs as well.";
        expected = "This is a example of a quoted-printable text file. This might contain ta=09=\r\nbs as well.";
        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test200() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        byte[] plain = null;
        byte[] encoded = qpcodec.encode(plain);
        assertEquals(null, encoded);
    }
    @Test
    public void test201() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain ="This is a example of a quoted-printable text file. There is no end to it\t";
        String expected = "This is a example of a quoted-printable text file. There is no end to i=\r\nt=09";
        assertEquals(expected, qpcodec.encode(plain));

        plain ="This is a example of a quoted-printable text file. There is no end to it ";
        expected = "This is a example of a quoted-printable text file. There is no end to i=\r\nt=20";
        assertEquals(expected, qpcodec.encode(plain));

        plain ="This is a example of a quoted-printable text file. There is no end to   ";
        expected = "This is a example of a quoted-printable text file. There is no end to=20=\r\n =20";
        assertEquals(expected, qpcodec.encode(plain));

        plain ="This is a example of a quoted-printable text file. There is no end to=  ";
        expected = "This is a example of a quoted-printable text file. There is no end to=3D=\r\n =20";
        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test202() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "1+1 = 2";
        String encoded = (String) qpcodec.encode((Object) plain);
        assertEquals("1+1 =3D 2", encoded);

        byte[] plainBA = plain.getBytes("UTF-8");
        byte[] encodedBA = (byte[]) qpcodec.encode((Object) plainBA);
        encoded = new String(encodedBA);
        assertEquals("1+1 =3D 2", encoded);

        Object result = qpcodec.encode((Object) null);
        assertEquals(null, result);

        try {
            Object dObj = new Double(3.0);
            qpcodec.encode( dObj );
            fail("Trying to url encode a Double object should cause an exception.");
        } catch (EncoderException ee) {
        }
    }
    @Test
    public void test203() throws Exception {
        String plain = "Hello there!";
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("UnicodeBig");
        qpcodec.encode(plain);
        String encoded1 = qpcodec.encode(plain, "UnicodeBig");
        String encoded2 = qpcodec.encode(plain);
        assertEquals(encoded1, encoded2);
    }
    @Test
    public void test204() {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("NONSENSE");
        String plain = "Hello there!";
        try {
            qpcodec.encode(plain);
            fail("We set the encoding to a bogus NONSENSE value, this shouldn't have worked.");
        } catch (EncoderException ee) {
        }
        try {
            qpcodec.decode(plain);
            fail("We set the encoding to a bogus NONSENSE value, this shouldn't have worked.");
        } catch (DecoderException ee) {
        }
    }
    @Test
    public void test205() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String test = null;
        String result = qpcodec.encode( test, "charset" );
        assertEquals(null, result);
    }
    @Test
    public void test206() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("=3D=0D=0A", encoded);
        assertEquals(plain, qpcodec.decode(encoded));
    }
    @Test
    public void test207() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there =\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("=3D Hello there =3D=0D=0A", encoded);
        assertEquals(plain, qpcodec.decode(encoded));
    }
    @Test
    public void test208() throws Exception {

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
    public void test209() throws Exception {
        String qpdata = "If you believe that truth=3Dbeauty, then surely mathematics is the most " +
        "b=\r\neautiful branch of philosophy.";
        String expected = "If you believe that truth=beauty, then surely mathematics is the most " +
        "beautiful branch of philosophy.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(qpdata, qpcodec.encode(expected));

        String decoded = qpcodec.decode(qpdata);
        assertEquals(qpdata, qpcodec.encode(decoded));
    }
}