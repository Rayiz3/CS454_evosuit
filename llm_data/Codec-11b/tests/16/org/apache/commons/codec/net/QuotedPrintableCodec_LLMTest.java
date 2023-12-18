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

        // Mutant test case 1: Change the value of b to 0xFF
        qpcodec.encodeQuotedPrintable(0xFF, new ByteArrayOutputStream());
        // Mutant test case 2: Change the value of b to 0x00
        qpcodec.encodeQuotedPrintable(0x00, new ByteArrayOutputStream());
        // Mutant test case 3: Change the value of b to 0x5F
        qpcodec.encodeQuotedPrintable(0x5F, new ByteArrayOutputStream());
        // Mutant test case 4: Change the value of b to 0x20
        qpcodec.encodeQuotedPrintable(0x20, new ByteArrayOutputStream());
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

        // Mutant test case 1: Change the value of plain to "= Hello there =\r\nIf you believe that truth=beauty, then surely mathematics is the most beautiful branch of philosophy."
        qpcodec.encode("= Hello there =\r\nIf you believe that truth=beauty, then surely mathematics is the most beautiful branch of philosophy.");
        // Mutant test case 2: Change the value of plain to ""
        qpcodec.encode("");
        // Mutant test case 3: Change the value of plain to "= Hello there =\r\n\n"
        qpcodec.encode("= Hello there =\r\n\n");
        // Mutant test case 4: Change the value of plain to "  "
        qpcodec.encode("  ");
    }
    @Test
    public void test2() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));

        // Mutant test case 1: Change the value of plain to "     "
        qpcodec.encode("     ");
        // Mutant test case 2: Change the value of plain to "[/]"
        qpcodec.encode("[/]");
        // Mutant test case 3: Change the value of plain to "abc"
        qpcodec.encode("abc");
        // Mutant test case 4: Change the value of plain to "\""
        qpcodec.encode("\"");
    }
    @Test
    public void test3() throws Exception {
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

        // Mutant test case 1: Change the value of plain to null
        qpcodec.encode(null);
        // Mutant test case 2: Change the value of plain to "This is a example of a quoted-printable text file. There is no end to it."
        qpcodec.encode("This is a example of a quoted-printable text file. There is no end to it.");
        // Mutant test case 3: Change the value of plain to "This is a example of a quoted-printable text file. There is no end to it"
        qpcodec.encode("This is a example of a quoted-printable text file. There is no end to it");
        // Mutant test case 4: Change the value of plain to "              "
        qpcodec.encode("              ");
    }
    @Test
    public void test4() throws Exception {
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

        // Mutant test case 1: Change the value of plain to null
        qpcodec.encode(null);
        // Mutant test case 2: Change the value of plain to new Object()
        qpcodec.encode(new Object());
        // Mutant test case 3: Change the value of plain to the Double object
        qpcodec.encode(new Double(3.0));
        // Mutant test case 4: Change the value of plain to 1
        qpcodec.encode(1);
    }
    @Test
    public void test5() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        byte[] plain = null;
        byte[] encoded = qpcodec.encode(plain);
        assertEquals("Encoding a null string should return null", 
            null, encoded);

        // Mutant test case 1: Change the value of plain to a non-null byte array
        qpcodec.encode("text".getBytes());
        // Mutant test case 2: Change the value of plain to an empty byte array
        qpcodec.encode("".getBytes());
        // Mutant test case 3: Change the value of plain to "null"
        qpcodec.encode("null".getBytes());
        // Mutant test case 4: Change the value of plain to plain.toString().getBytes()
        qpcodec.encode("text".toString().getBytes());
    }
    @Test
    public void test6() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="This is a example of a quoted-printable text file. This might contain sp=cial chars.";
        String expected = "This is a example of a quoted-printable text file. This might contain sp=3D=\r\ncial chars.";
        assertEquals(expected, qpcodec.encode(plain));

        plain ="This is a example of a quoted-printable text file. This might contain ta\tbs as well.";
        expected = "This is a example of a quoted-printable text file. This might contain ta=09=\r\nbs as well.";
        assertEquals(expected, qpcodec.encode(plain));

        // Mutant test case 1: Change the value of plain to "This is a example of a quoted-printable text file. This might contain sp=cial"
        qpcodec.encode("This is a example of a quoted-printable text file. This might contain sp=cial");
        // Mutant test case 2: Change the value of plain to "This is a example of a quoted-printable text file. This might contain ta\tbs"
        qpcodec.encode("This is a example of a quoted-printable text file. This might contain ta\tbs");
        // Mutant test case 3: Change the value of plain to "This is a example of a quoted-printable text file. This might contain special chars."
        qpcodec.encode("This is a example of a quoted-printable text file. This might contain special chars.");
        // Mutant test case 4: Change the value of plain to "This is a example of a quoted-printable text file. This might contain tabs as well."
        qpcodec.encode("This is a example of a quoted-printable text file. This might contain tabs as well.");
    }
    @Test
    public void test7() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=3D=0D=0A", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));

        // Mutant test case 1: Change the value of plain to "\t"
        qpcodec.encode("\t");
        // Mutant test case 2: Change the value of plain to " "
        qpcodec.encode(" ");
        // Mutant test case 3: Change the value of plain to "\n"
        qpcodec.encode("\n");
        // Mutant test case 4: Change the value of plain to "\r"
        qpcodec.encode("\r");
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

        // Mutant test case 1: Change the value of ru_msg to null
        qpcodec.encode(null, CharEncoding.UTF_8);
        // Mutant test case 2: Change the value of ru_msg to ""
        qpcodec.encode("", CharEncoding.UTF_8);
        // Mutant test case 3: Change the value of ru_msg to "1"
        qpcodec.encode("1", CharEncoding.UTF_8);
        // Mutant test case 4: Change the value of ru_msg to constructString(SWISS_GERMAN_STUFF_UNICODE)
        qpcodec.encode(constructString(SWISS_GERMAN_STUFF_UNICODE), CharEncoding.UTF_8);
    }
    @Test
    public void test9() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "1+1 = 2";
        String encoded = new String(QuotedPrintableCodec.
            encodeQuotedPrintable(null, plain.getBytes("UTF-8")));
        assertEquals("Basic quoted-printable encoding test", 
            "1+1 =3D 2", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        
        // Mutant test case 1: Change the value of plain to "1+1"
        new String(QuotedPrintableCodec.
            encodeQuotedPrintable(null, "1+1".getBytes("UTF-8")));
        // Mutant test case 2: Change the value of plain to "+"
        new String(QuotedPrintableCodec.
            encodeQuotedPrintable(null, "+".getBytes("UTF-8")));
        // Mutant test case 3: Change the value of plain to " "
        new String(QuotedPrintableCodec.
            encodeQuotedPrintable(null, " ".getBytes("UTF-8")));
        // Mutant test case 4: Change the value of plain to "null"
        new String(QuotedPrintableCodec.
            encodeQuotedPrintable(null, "null".getBytes("UTF-8")));
    }
    @Test
    public void test10() throws Exception {
        // whitespace, but does not need to be encoded
        String plain ="This is a example of a quoted=printable text file. There is no tt";
        String expected = "This is a example of a quoted=3Dprintable text file. There is no tt";

        assertEquals(expected, new QuotedPrintableCodec().encode(plain));

        // Mutant test case 1: Change the value of plain
        new QuotedPrintableCodec().encode("");
        // Mutant test case 2: Change the value of plain to "="
        new QuotedPrintableCodec().encode("=");
        // Mutant test case 3: Change the value of plain to null
        new QuotedPrintableCodec().encode(null);
        // Mutant test case 4: Change the value of plain to "This is a example of a quoted=printable text file. There is no ttt"
        new QuotedPrintableCodec().encode("This is a example of a quoted=printable text file. There is no ttt");
    }
    @Test
    public void test11() throws Exception {
        String plain = "Hello there!";
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("UnicodeBig");
        qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
        String encoded1 = qpcodec.encode(plain, "UnicodeBig");
        String encoded2 = qpcodec.encode(plain);
        assertEquals(encoded1, encoded2);

        // Mutant test case 1: Change the value of plain to "null"
        qpcodec.encode(null);
        // Mutant test case 2: Change the value of plain to "Hello"
        qpcodec.encode("Hello");
        // Mutant test case 3: Change the value of plain to ""
        qpcodec.encode("");
        // Mutant test case 4: Change the value of plain to "Hello there! Hello there!"
        qpcodec.encode("Hello there! Hello there!");
    }
    @Test
    public void test12() throws Exception {
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
    public void test13() throws Exception {
        String qpdata = "If you believe that truth=3Dbeauty, then surely mathematics is the most " +
                " b =  \r\n       eautiful branch of philosophy.";
        String expected = "If you believe that truth=beauty, then surely mathematics is the most " +
                " beautiful branch of philosophy.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(qpdata, qpcodec.encode(expected));

        String decoded = qpcodec.decode(qpdata);
        assertEquals(qpdata, qpcodec.encode(decoded));
    }
    @Test
    public void test14() throws Exception {
        String qpdata = "If you believe that truth=3Dbeauty, then surely mathematics is the most " +
                "b =  \r\n       ";
        String expected = "If you believe that truth=beauty, then surely mathematics is the most " +
                " b =  ";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(expected, qpcodec.encode(qpdata));

        String decoded = qpcodec.decode(expected);
        assertEquals(expected, qpcodec.encode(decoded));
    }
    @Test
    public void test15() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there =\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Hello there =3D=0D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test16() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=  Hello there =\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D  Hello there =3D=0D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test17() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there = ";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Hello there =3D=20", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test18() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test19() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc 123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test20() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[] ";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test21() throws Exception {
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
    public void test22() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="This is a example of a quoted-printable text file. There is no end to it   \t";
        String expected = "This is a example of a quoted-printable text file. There is no end to i=\r\nt=09";

        assertEquals(expected, qpcodec.encode(plain));

        plain ="This is a example of a quoted-printable text file. There is no end to it   ";
        expected = "This is a example of a quoted-printable text file. There is no end to i=\r\nt=20";

        assertEquals(expected, qpcodec.encode(plain));

        // whitespace before soft break
        plain ="This is a example of a quoted-printable text file. There is no end to    ";
        expected = "This is a example of a quoted-printable text file. There is no end to=20=\r\n =20";

        assertEquals(expected, qpcodec.encode(plain));

        // non-printable character before soft break
        plain ="This is a example of a quoted-printable text file. There is no end to = ";
        expected = "This is a example of a quoted-printable text file. There is no end to=3D=\r\n =20";

        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test23() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="This is a example of a quoted-printable text file. There is no end to it ";
        String expected = "This is a example of a quoted-printable text file. There is no end to i=\r\nt=20";

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
    public void test24() throws Exception {
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
    public void test25() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        byte[] plain = null;
        byte[] encoded = qpcodec.encode(plain);
        assertEquals("Encoding a null string should return null", 
            null, encoded);
    }
    @Test
    public void test26() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="This is a example of a quoted-printable text file. This might contain sp=cial chars.";
        String expected = "This is a example of a quoted-printable text file. This might contain sp=3D=\r\ncial chars.";
        assertEquals(expected, qpcodec.encode(plain));

        plain ="This is a example of a quoted-printable text file. This might contain ta\tbs as well.";
        expected = "This is a example of a quoted-printable text file. This might contain ta=09=\r\nbs as well.";
        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test27() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="This is a example of a quoted-printable text file. This might contain sp=cial chars.   ";
        String expected = "This is a example of a quoted-printable text file. This might contain sp=3D=\r\ncial chars.   ";
        assertEquals(expected, qpcodec.encode(plain));

        plain ="This is a example of a quoted-printable text file. This might contain ta\tbs as well.   ";
        expected = "This is a example of a quoted-printable text file. This might contain ta=09=\r\nbs as well.   ";
        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test28() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="This is a example of a quoted-printable text file. This might contain sp=cial chars. ";
        String expected = "This is a example of a quoted-printable text file. This might contain sp=3D=\r\ncial chars. ";
        assertEquals(expected, qpcodec.encode(plain));

        plain ="This is a example of a quoted-printable text file. This might contain ta\tbs as well. ";
        expected = "This is a example of a quoted-printable text file. This might contain ta=09=\r\nbs as well. ";
        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test29() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=3D=0D=0A", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test30() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = " =\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=3D =3D=0D=0A", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test31() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=\r\n ";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=3D=0D=0A ", encoded);
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

        String ru_msg = constructString(RUSSIAN_STUFF_UNICODE); 
        String ch_msg = constructString(SWISS_GERMAN_STUFF_UNICODE); 
        
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        
        assertEquals(
            "=D0=92=D1=81=D0=B5=D0=BC_=D0=BF=D1=80=D0=B8=D0=B2=D0=B5=D1=82   ", 
        qpcodec.encode(ru_msg + "   ", CharEncoding.UTF_8)
        );
        assertEquals("Gr=C3=BCezi_z=C3=A4m=C3=A4   ", qpcodec.encode(ch_msg + "   ", CharEncoding.UTF_8));
        
        assertEquals(ru_msg + "   ", qpcodec.decode(qpcodec.encode(ru_msg + "   ", CharEncoding.UTF_8), CharEncoding.UTF_8));
        assertEquals(ch_msg + "   ", qpcodec.decode(qpcodec.encode(ch_msg + "   ", CharEncoding.UTF_8), CharEncoding.UTF_8));
    }
    @Test
    public void test34() throws Exception {
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
    public void test35() throws Exception {
        // whitespace, but does not need to be encoded
        String plain ="This is a example of a quoted=printable text file. There is no tt";
        String expected = "This is a example of a quoted=3Dprintable text file. There is no tt";

        assertEquals(expected, new QuotedPrintableCodec().encode(plain));
    }
    @Test
    public void test36() throws Exception {
        // whitespace, but does not need to be encoded
        String plain ="This is a example of a quoted=printable text file. There is no tt       ";
        String expected = "This is a example of a quoted=3Dprintable text file. There is no tt       ";

        assertEquals(expected, new QuotedPrintableCodec().encode(plain));
    }
    @Test
    public void test37() throws Exception {
        // whitespace, but does not need to be encoded
        String plain ="This is a example of a quoted=printable text file. There is no tt ";
        String expected = "This is a example of a quoted=3Dprintable text file. There is no tt ";

        assertEquals(expected, new QuotedPrintableCodec().encode(plain));
    }
    @Test
    public void test38() throws Exception {
        String plain = "Hello there!";
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("UnicodeBig");
        qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
        String encoded1 = qpcodec.encode(plain, "UnicodeBig");
        String encoded2 = qpcodec.encode(plain);
        assertEquals(encoded1, encoded2);
    }
    @Test
    public void test39() throws Exception {
        String plain = "Hello there!   ";
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("UnicodeBig");
        qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
        String encoded1 = qpcodec.encode(plain, "UnicodeBig");
        String encoded2 = qpcodec.encode(plain);
        assertEquals(encoded1, encoded2);
    }
    @Test
    public void test40() throws Exception {
        String plain = "Hello there! ";
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("UnicodeBig");
        qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
        String encoded1 = qpcodec.encode(plain, "UnicodeBig");
        String encoded2 = qpcodec.encode(plain);
        assertEquals(encoded1, encoded2);
    }
    @Test
    public void test41() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        byte[] bytes = null;
        byte[] result = QuotedPrintableCodec.decodeQuotedPrintable(bytes);
        assertEquals("Decoding empty input should return null", null, result);
    }
    @Test
    public void test42() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        byte[] bytes = "=48=65=6C=6C=6F=20=3C=21=2D=5F=2D=23=2A=7E=21=40=23=24=25=5E=26=28=29=7B=7D=22=5C=3B=3A=60=2C=2F=5B=5D".getBytes();
        byte[] expected = "Hello <!-_-*~!@#$%^&()+{}\"\\;:`,/[]".getBytes();
        byte[] result = QuotedPrintableCodec.decodeQuotedPrintable(bytes);
        assertArrayEquals("Decoding special characters should return the expected result", expected, result);
    }
    @Test
    public void test43() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        byte[] bytes = "=49=66=20=79=6F=75=20=62=65=6C=69=65=76=65=20=74=68=61=74=20=74=72=75=74=68=3D=62=65=61=75=74=79=2C" +
                "=20=74=68=65=6E=20=73=75=72=65=6C=79=20=6D=61=74=68=65" +
                "=6D=61=74=69=63=73=20=69=73=20=74=68=65=20=6D=6F=73=74" +
                "=20=62=65=61=75=74=69=66=75=6C=20=62=72=61=6E=63=68=20=6F" +
                "=66=20=70=68=69=6C=6F=73=6F=70=68=79=2E".getBytes();
        byte[] expected = "If you believe that truth=beauty, then surely mathematics " +
                "is the most beautiful branch of philosophy.".getBytes();
        byte[] result = QuotedPrintableCodec.decodeQuotedPrintable(bytes);
        assertArrayEquals("Decoding line breaks should return the expected result", expected, result);
    }
    @Test
    public void test44() {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        byte[] bytes = "Hello".getBytes();
        try {
            qpcodec.decodeQuotedPrintable(bytes);
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
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
    public void test46() throws Exception {
        String qpdata = "If you believe that truth=3Dbeauty, then surely mathematics is the most " +
                "b=";
        String expected = "If you believe that truth=beauty, then surely mathematics is the most " +
                "beautiful branch of philosophy.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(qpdata, qpcodec.encode(expected));

        String decoded = qpcodec.decode(qpdata);
        assertEquals(qpdata, qpcodec.encode(decoded));
    }
    @Test
    public void test47() throws Exception {
        String qpdata = "If you believe that truth=3Dbeauty, then surely mathematics is the most " +
                "be";
        String expected = "If you believe that truth=beauty, then surely mathematics is the most " +
                "beautiful branch of philosophy.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(qpdata, qpcodec.encode(expected));

        String decoded = qpcodec.decode(qpdata);
        assertEquals(qpdata, qpcodec.encode(decoded));
    }
    @Test
    public void test48() throws Exception {
        String qpdata = "If you believe that truth=3Dbeauty, then surely mathematics is the most " +
                "beautiful branch of philosophy.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(qpdata, qpcodec.encode(qpdata));

        String decoded = qpcodec.decode(qpdata);
        assertEquals(qpdata, qpcodec.encode(decoded));
    }
    @Test
    public void test49() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertNull("Null string quoted-printable encoding test", 
            qpcodec.encode((String)null));
        assertNull("Null string quoted-printable decoding test", 
            qpcodec.decode((String)null));
    }
    @Test
    public void test50() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertNull("Null string quoted-printable encoding test", 
            qpcodec.encode("null"));
        assertNull("Null string quoted-printable decoding test", 
            qpcodec.decode("null"));
    }
    @Test
    public void test51() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there =\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Hello there =3D=0D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test52() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test53() throws Exception {
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
    public void test54() {
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
    public void test55() throws Exception {
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
    public void test56() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        byte[] plain = null;
        byte[] encoded = qpcodec.encode(plain);
        assertEquals("Encoding a null string should return null", 
            null, encoded);
    }
    @Test
    public void test57() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="This is a example of a quoted-printable text file. This might contain sp=cial chars.";
        String expected = "This is a example of a quoted-printable text file. This might contain sp=3D=\r\ncial chars.";
        assertEquals(expected, qpcodec.encode(plain));

        plain ="This is a example of a quoted-printable text file. This might contain ta\tbs as well.";
        expected = "This is a example of a quoted-printable text file. This might contain ta=09=\r\nbs as well.";
        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test58() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=3D=0D=0A", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test59() throws Exception {

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
    public void test60() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String test = null;
        String result = qpcodec.encode( test, "charset" );
        assertEquals("Result should be null", null, result);
    }
    @Test
    public void test61() throws Exception {
        // whitespace, but does not need to be encoded
        String plain ="This is a example of a quoted=printable text file. There is no ttt";
        String expected = "This is a example of a quoted=3Dprintable text file. There is no ttt";

        assertEquals(expected, new QuotedPrintableCodec().encode(plain));
    }
    @Test
    public void test62() throws Exception {
        String plain = "Hello there!";
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("UnicodeBig");
        qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
        String encoded1 = qpcodec.encode(plain, "UnicodeBig");
        String encoded2 = qpcodec.encode(plain);
        assertEquals(encoded1, encoded2);
    }
    @Test
    public void test63() throws Exception {
        String plain = "Hello there!";
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("UTF-8");
        qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
        String encoded1 = qpcodec.encode(plain, "UTF-8");
        String encoded2 = qpcodec.encode(plain);
        assertEquals(encoded1, encoded2);
    }
    @Test
    public void test64() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "";
        String encoded = qpcodec.encode(plain);
        assertEquals("Empty string quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Empty string quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test65() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "This is a very long string. This is a very long string. This is a very long string. This is a very long string. This is a very long string. This is a very long string. This is a very long string. This is a very long string. This is a very long string. This is a very long string. This is a very long string. ";
        String encoded = qpcodec.encode(plain);
        assertEquals("Long string quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Long string quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test66() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "Special characters: @#$%";
        String encoded = qpcodec.encode(plain);
        assertEquals("Special characters quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Special characters quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test67() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "Non-ASCII characters: àéïö";
        String encoded = qpcodec.encode(plain);
        assertEquals("Non-ASCII characters quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Non-ASCII characters quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test68() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "Large numbers: 12345678901234567890";
        String encoded = qpcodec.encode(plain);
        assertEquals("Large numbers quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Large numbers quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test69() throws Exception {
        String qpdata = "If you believe that truth=3Dbeauty, then surely mathematics is the most " +
                "b=\r\neautiful branch of philosophy.";
        String expected = "If you believe that truth=beauty, then surely mathematics is the most " +
                "beautiful branch of philosophy.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        // Changing input by removing soft line break
        qpdata = "If you believe that truth=3Dbeauty, then surely mathematics is the most " +
                "beautiful branch of philosophy.";
        assertEquals(qpdata, qpcodec.encode(expected));
    }
    @Test
    public void test70() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        // Changing input by passing a non-null string
        String str = "Hello World";
        assertEquals("Null string quoted-printable encoding test", 
            qpcodec.encode(str));
        assertEquals("Null string quoted-printable decoding test", 
            qpcodec.decode(str));
    }
    @Test
    public void test71() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there =\r\n";
        String encoded = qpcodec.encode(plain);
        // Changing input by removing equal sign at the beginning
        plain = " Hello there =\r\n";
        assertEquals("Basic quoted-printable encoding test", 
            "=3D" + plain, encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test72() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain);
        // Changing input by adding a space at the beginning
        plain = " " + plain;
        assertEquals("Safe chars quoted-printable encoding test", 
            qpcodec.encode(plain), encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            qpcodec.decode(encoded), plain);
    }
    @Test
    public void test73() throws Exception {
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
    public void test74() {
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
    public void test75() throws Exception {
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
    public void test76() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        byte[] plain = null;
        byte[] encoded = qpcodec.encode(plain);
        assertEquals("Encoding a null string should return null", 
            null, encoded);
    }
    @Test
    public void test77() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="This is a example of a quoted-printable text file. This might contain sp=cial chars.";
        String expected = "This is a example of a quoted-printable text file. This might contain sp=3D=\r\ncial chars.";
        assertEquals(expected, qpcodec.encode(plain));

        plain ="This is a example of a quoted-printable text file. This might contain ta\tbs as well.";
        expected = "This is a example of a quoted-printable text file. This might contain ta=09=\r\nbs as well.";
        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test78() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=3D=0D=0A", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test79() throws Exception {

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
    public void test80() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String test = null;
        String result = qpcodec.encode( test, "charset" );
        assertEquals("Result should be null", null, result);
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
        String plain = "Hello there!";
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("UnicodeBig");
        qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
        String encoded1 = qpcodec.encode(plain, "UnicodeBig");
        String encoded2 = qpcodec.encode(plain);
        assertEquals(encoded1, encoded2);
    }
@Test
public void test83() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "";
    String encoded = qpcodec.encode(plain);
    assertEquals("Empty string quoted-printable encoding test", 
        "", encoded);
    assertEquals("Empty string quoted-printable decoding test", 
        plain, qpcodec.decode(encoded));
}
@Test
public void test84() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "&$@#*";
    String encoded = qpcodec.encode(plain);
    assertEquals("Special characters quoted-printable encoding test", 
        "=26=24=40=23=2A", encoded);
    assertEquals("Special characters quoted-printable decoding test", 
        plain, qpcodec.decode(encoded));
}
@Test
public void test85() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "This is a very long string that is used for testing purposes and should be encoded and decoded correctly";
    String encoded = qpcodec.encode(plain);
    assertEquals("Long string quoted-printable encoding test", 
        "This is a very long string that is used for testing purposes and should be encoded and decoded correctly", encoded);
    assertEquals("Long string quoted-printable decoding test", 
        plain, qpcodec.decode(encoded));
}
    @Test
    public void test86() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        
        // Regression test with empty string
        assertNull("Empty string quoted-printable encoding test", qpcodec.encode(""));
        assertNull("Empty string quoted-printable decoding test", qpcodec.decode(""));
        
        // Regression test with non-empty string
        String nonEmptyString = "Hello World!";
        assertNull("Non-empty string quoted-printable encoding test", qpcodec.encode(nonEmptyString));
        assertNull("Non-empty string quoted-printable decoding test", qpcodec.decode(nonEmptyString));
    }
    @Test
    public void test87() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        
        // Regression test with different plain text
        String plain1 = "= Hello there =\r\n";
        String encoded1 = qpcodec.encode(plain1);
        assertEquals("Basic quoted-printable encoding test 1", "=3D Hello there =3D=0D=0A", encoded1);
        assertEquals("Basic quoted-printable decoding test 1", plain1, qpcodec.decode(encoded1));
        
        String plain2 = "This is a test!";
        String encoded2 = qpcodec.encode(plain2);
        assertEquals("Basic quoted-printable encoding test 2", plain2, encoded2);
        assertEquals("Basic quoted-printable decoding test 2", plain2, qpcodec.decode(encoded2));
    }
    @Test
    public void test88() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "1+1 =3D 2";
        
        // Regression test with different Object types
        String decodedString = (String) qpcodec.decode((Object) plain);
        assertEquals("Basic quoted-printable decoding test 1", "1+1 = 2", decodedString);

        byte[] plainBA = plain.getBytes("UTF-8");
        byte[] decodedBA = (byte[]) qpcodec.decode((Object) plainBA);
        decodedString = new String(decodedBA);
        assertEquals("Basic quoted-printable decoding test 2", "1+1 = 2", decodedString);
            
        Object result = qpcodec.decode((Object) null);
        assertEquals("Decoding a null Object should return null", null, result);

        try {
            Object dObj = new Double(3.0);
            qpcodec.decode( dObj );
            fail( "Trying to url encode a Double object should cause an exception.");
        } catch (DecoderException ee) {
            // Exception expected, test segment passes.
        }
    }
    @Test
    public void test89() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        
        // Regression test with different safe characters
        String safeChars1 = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded1 = qpcodec.encode(safeChars1);
        assertEquals("Safe chars quoted-printable encoding test 1", safeChars1, encoded1);
        assertEquals("Safe chars quoted-printable decoding test 1", safeChars1, qpcodec.decode(encoded1));
        
        String safeChars2 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String encoded2 = qpcodec.encode(safeChars2);
        assertEquals("Safe chars quoted-printable encoding test 2", safeChars2, encoded2);
        assertEquals("Safe chars quoted-printable decoding test 2", safeChars2, qpcodec.decode(encoded2));
    }
    @Test
    public void test90() {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("NONSENSE");
        String plain = "Hello there!";
        
        // Regression test with invalid encoding
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
    public void test91() throws Exception {
        String qpdata = "If you believe that truth=3Dbeauty, then surely=20=\r\nmathematics " +
                "is the most beautiful branch of philosophy.";
        String expected = "If you believe that truth=beauty, then surely mathematics " +
                "is the most beautiful branch of philosophy.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        
        // Regression test with different soft line break position
        assertEquals(expected, qpcodec.decode(qpdata));

        String encoded = qpcodec.encode(expected);
        assertEquals(expected, qpcodec.decode(encoded));
        
        String qpdata2 = "If you\n believe that truth=beauty, then surely mathematics " +
                "is the most beautiful branch of philosophy.";
        String expected2 = "If you\n believe that truth=beauty, then surely mathematics " +
                "is the most beautiful branch of philosophy.";
        
        assertEquals(expected2, qpcodec.decode(qpdata2));
        
        String encoded2 = qpcodec.encode(expected2);
        assertEquals(expected2, qpcodec.decode(encoded2));
    }
    @Test
    public void test92() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        
        // Regression test with unsafe characters
        String unsafeChars = "=\r\n";
        String encoded = qpcodec.encode(unsafeChars);
        assertEquals("Unsafe chars quoted-printable encoding test", "=3D=0D=0A", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", unsafeChars, qpcodec.decode(encoded));
    }
    @Test
    public void test93() throws Exception {

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
    public void test94() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "1+1 = 2";
        
        // Regression test with null BitSet
        String encoded = new String(QuotedPrintableCodec.encodeQuotedPrintable(null, plain.getBytes("UTF-8")));
        assertEquals("Basic quoted-printable encoding test", "1+1 =3D 2", encoded);
        assertEquals("Basic quoted-printable decoding test", plain, qpcodec.decode(encoded));
        
    }
    @Test
    public void test95() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String test = null;
        
        // Regression test with null input string
        String result = qpcodec.decode(test, "charset");
        assertEquals("Result should be null", null, result);
    }
    @Test
    public void test96() throws Exception {
        String qpdata = "CRLF in an\n encoded text should be=20=\r\n\rskipped in the\r decoding.";
        String expected = "CRLF in an encoded text should be skipped in the decoding.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        
        // Regression test with different CRLF positions
        assertEquals(expected, qpcodec.decode(qpdata));

        String encoded = qpcodec.encode(expected);
        assertEquals(expected, qpcodec.decode(encoded));
        
        String qpdata2 = "CRL\nF in an encoded text should be skipped in the decoding.";
        String expected2 = "CRL\nF in an encoded text should be skipped in the decoding.";
        
        assertEquals(expected2, qpcodec.decode(qpdata2));
        
        String encoded2 = qpcodec.encode(expected2);
        assertEquals(expected2, qpcodec.decode(encoded2));
    }
    @Test
    public void test97() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        
        // Regression test with different invalid encoded strings
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
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        Object obj = null;
        assertNull("Null object quoted-printable encoding test", 
            qpcodec.encode(obj));
    }
    @Test
    public void test99() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        Object obj = new Integer(10);
        try {
            qpcodec.encode(obj);
            fail("Quoted-printable encoding a non-string, non-byte array object should throw an exception.");
        } catch (EncoderException ee) {
            // Exception expected, test segment passes.
        }
    }
    @Test
    public void test100() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        // Change input value from null to ""
        assertNull("Null string quoted-printable encoding test", 
            qpcodec.encode(""));
        assertNull("Null string quoted-printable decoding test", 
            qpcodec.decode(""));
    }
    @Test
    public void test101() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        // Change input value from "= Hello there =\r\n" to "Hello there"
        String plain = "Hello there";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=48=65=6C=6C=6F=20=74=68=65=72=65", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test102() throws Exception {
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
    public void test103() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        // Change input value from "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]" to "abc123"
        String plain = "abc123";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test104() {
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
    public void test105() throws Exception {
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
    public void test106() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        // Change input value from "=\r\n" to "="
        String plain = "=";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=3D", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test107() throws Exception {

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
    public void test108() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        // Change input value from "1+1 = 2" to "1+1"
        String plain = "1+1";
        String encoded = new String(QuotedPrintableCodec.
            encodeQuotedPrintable(null, plain.getBytes("UTF-8")));
        assertEquals("Basic quoted-printable encoding test", 
            "1+1", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        
    }
    @Test
    public void test109() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String test = null;
        String result = qpcodec.decode( test, "charset" );
        assertEquals("Result should be null", null, result);
    }
    @Test
    public void test110() throws Exception {
        String qpdata = "CRLF in an\n encoded text should be=20=\r\n\rskipped in the\r decoding.";
        String expected = "CRLF in an encoded text should be skipped in the decoding.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(expected, qpcodec.decode(qpdata));

        String encoded = qpcodec.encode(expected);
        assertEquals(expected, qpcodec.decode(encoded));
    }
    @Test
    public void test111() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        // Change input value from "=" to "A"
        try {
            qpcodec.decode("A");
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
        // Change input value from "=A" to "=W"
        try {
            qpcodec.decode("=W");
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
        // Change input value from "=W" to "=0"
        try {
            qpcodec.decode("=0");
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
    }
    @Test
    public void test112() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "";
        String encoded = qpcodec.encode(plain);
        assertEquals("Empty string test", "", encoded);
        assertEquals("Empty string decode test", plain, qpcodec.decode(encoded));
    }
    @Test
    public void test113() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "!@#$%^&*()-_=+~`[]\\{}|;':\",./<>?";
        String encoded = qpcodec.encode(plain);
        assertEquals("Special characters test", plain, encoded);
        assertEquals("Special characters decode test", plain, qpcodec.decode(encoded));
    }
    @Test
    public void test114() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "A B C D E";
        String encoded = qpcodec.encode(plain);
        assertEquals("Whitespace test", plain, encoded);
        assertEquals("Whitespace decode test", plain, qpcodec.decode(encoded));
    }
    @Test
    public void test115() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "This is a long string that will be encoded";
        String encoded = qpcodec.encode(plain);
        assertEquals("Long string test", plain, encoded);
        assertEquals("Long string decode test", plain, qpcodec.decode(encoded));
    }
    @Test
    public void test116() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "世界";
        String encoded = qpcodec.encode(plain);
        assertEquals("Non-ASCII characters test", plain, encoded);
        assertEquals("Non-ASCII characters decode test", plain, qpcodec.decode(encoded));
    }
    @Test
    public void test117() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "   ABCD";
        String encoded = qpcodec.encode(plain);
        assertEquals("Leading whitespace test", plain, encoded);
        assertEquals("Leading whitespace decode test", plain, qpcodec.decode(encoded));
    }
    @Test
    public void test118() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "ABCD   ";
        String encoded = qpcodec.encode(plain);
        assertEquals("Trailing whitespace test", plain, encoded);
        assertEquals("Trailing whitespace decode test", plain, qpcodec.decode(encoded));
    }
    @Test
    public void test119() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "This is a long string\nthat spans multiple lines\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Multiple lines test", plain, encoded);
        assertEquals("Multiple lines decode test", plain, qpcodec.decode(encoded));
    }
    @Test
    public void test120() throws Exception {
        String qpdata = null;
        String expected = null;

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(qpdata, qpcodec.encode(expected));

        String decoded = qpcodec.decode(qpdata);
        assertEquals(qpdata, qpcodec.encode(decoded));
    }
    @Test
    public void test121() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = null;
        String encoded = qpcodec.encode(plain);
        assertEquals(null, encoded);
        assertEquals(null, qpcodec.decode(encoded));
    }
    @Test
    public void test122() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "";
        String encoded = qpcodec.encode(plain);
        assertEquals("", encoded);
        assertEquals("", qpcodec.decode(encoded));
    }
    @Test
    public void test123() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="";
        String expected = "";

        assertEquals(expected, qpcodec.encode(plain));

        plain ="";
        expected = "";

        assertEquals(expected, qpcodec.encode(plain));

        // whitespace before soft break
        plain ="";
        expected = "";

        assertEquals(expected, qpcodec.encode(plain));

        // non-printable character before soft break
        plain ="";
        expected = "";

        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test124() {
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
    public void test125() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = null;
        String encoded = (String) qpcodec.encode((Object) plain);
        assertEquals(null, encoded);

        byte[] plainBA = null;
        byte[] encodedBA = (byte[]) qpcodec.encode((Object) plainBA);
        assertEquals(null, encodedBA);

        Object result = qpcodec.encode((Object) null);
        assertEquals(null, result);

        try {
            Object dObj = null;
            qpcodec.encode( dObj );
            fail( "Trying to url encode a null Object should cause an exception.");
        } catch (EncoderException ee) {
            // Exception expected, test segment passes.
        }
    }
    @Test
    public void test126() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        byte[] plain = null;
        byte[] encoded = qpcodec.encode(plain);
        assertEquals(null, encoded);
    }
    @Test
    public void test127() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="";
        String expected = "";
        assertEquals(expected, qpcodec.encode(plain));

        plain ="";
        expected = "";
        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test128() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = null;
        String encoded = qpcodec.encode(plain);
        assertEquals(null, encoded);
        assertEquals(null, qpcodec.decode(encoded));
    }
}