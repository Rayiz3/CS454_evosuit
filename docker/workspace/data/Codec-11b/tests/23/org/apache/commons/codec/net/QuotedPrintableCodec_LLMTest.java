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
        // whitespace, but does not need to be encoded
        String plain ="This is a example of a quoted=printable text file. There is no tt";
        String expected = "This is a example of a quoted=3Dprintable text file. There is no tt";

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
    }
    @Test
    public void test3() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        byte[] plain = null;
        byte[] encoded = qpcodec.encode(plain);
        assertEquals("Encoding a null string should return null", 
            null, encoded);
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
        assertEquals("Gr=C3=BCezi_z=C3=A4m=C3=A4", qpcodec.encode(ch_msg, CharEncoding.UTF_8));
        
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
    }
    @Test
    public void test12() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "!#+";
        String encoded = qpcodec.encode(plain);
        assertEquals("Encoding special chars test", 
            "=21#2B", encoded);
        assertEquals("Decoding special chars test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test13() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = " ";
        String encoded = qpcodec.encode(plain);
        assertEquals("Encoding space test", 
            "=20", encoded);
        assertEquals("Decoding space test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test14() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "00FF";
        String encoded = qpcodec.encode(plain);
        assertEquals("Encoding hex test", 
            "=30=30=46=46", encoded);
        assertEquals("Decoding hex test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test15() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz";
        String encoded = qpcodec.encode(plain);
        assertEquals("Encoding long string test", 
            plain, encoded);
        assertEquals("Decoding long string test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test16() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "";
        String encoded = qpcodec.encode(plain);
        assertEquals("Encoding empty string test", 
            plain, encoded);
        assertEquals("Decoding empty string test", 
            plain, qpcodec.decode(encoded));
    }
@Test
public void test17() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
    String encoded = qpcodec.encode(plain);
    assertEquals("Safe chars quoted-printable encoding test mutant 1", 
        plain, encoded);
    assertEquals("Safe chars quoted-printable decoding test mutant 1", 
        plain, qpcodec.decode(encoded));
}
@Test
public void test18() throws Exception {
    // whitespace, but does not need to be encoded
    String plain ="This is a example of a quoted=printable text file. There is no tt";
    String expected = "This is a example of a quoted=printable text file. There is no tt";

    assertEquals(expected, new QuotedPrintableCodec().encode(plain));
}
@Test
public void test19() throws Exception {
    final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

    String plain ="This is a example of a quoted-printable text file. This might contain sp=cial chars.";
    String expected = "This is a example of a quoted-printable text file. This might contain sp=3D=\r\ncial chars.";
    assertEquals(expected, qpcodec.encode(plain));

    plain ="This is a example of a quoted-printable text file. This might contain ta\tbs as well.";
    expected = "This is a example of a quoted-printable text file. This might contain ta=09=\r\nbs as well.";
    assertEquals(expected, qpcodec.encode(plain));
}
@Test
public void test20() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    byte[] plain = null;
    byte[] encoded = qpcodec.encode(plain);
    assertEquals("Encoding a null string should return null", 
        null, encoded);
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
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "1+1 = 2";
    String encoded = (String) qpcodec.encode((Object) plain);
    assertEquals("Basic quoted-printable encoding test mutant 1", 
        "1+1 =3D 2", encoded);

    byte[] plainBA = plain.getBytes("UTF-8");
    byte[] encodedBA = (byte[]) qpcodec.encode((Object) plainBA);
    encoded = new String(encodedBA);
    assertEquals("Basic quoted-printable encoding test mutant 1", 
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
    String plain = "Hello there!";
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("UnicodeBig");
    qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
    String encoded1 = qpcodec.encode(plain, "UnicodeBig");
    String encoded2 = qpcodec.encode(plain);
    assertEquals(encoded1, encoded2);
}
@Test
public void test24() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "1+1 = 2";
    String encoded = new String(QuotedPrintableCodec.
        encodeQuotedPrintable(null, plain.getBytes("UTF-8")));
    assertEquals("Basic quoted-printable encoding test mutant 1", 
        "1+1 =3D 2", encoded);
    assertEquals("Basic quoted-printable decoding test mutant 1", 
        plain, qpcodec.decode(encoded));
}
@Test
public void test25() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "=\r\n";
    String encoded = qpcodec.encode(plain);
    assertEquals("Unsafe chars quoted-printable encoding test mutant 1", 
        "=3D=0D=0A", encoded);
    assertEquals("Unsafe chars quoted-printable decoding test mutant 1", 
        plain, qpcodec.decode(encoded));
}
@Test
public void test26() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "= Hello there =\r\n";
    String encoded = qpcodec.encode(plain);
    assertEquals("Basic quoted-printable encoding test mutant 1", 
        "=3D Hello there =3D=0D=0A", encoded);
    assertEquals("Basic quoted-printable decoding test mutant 1", 
        plain, qpcodec.decode(encoded));
}
@Test
public void test27() throws Exception {

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
public void test28() throws Exception {
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
    public void test29() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        
        // Additional regression test case with same input
        String plain2 = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded2 = qpcodec.encode(plain2);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain2, encoded2);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain2, qpcodec.decode(encoded2));
    }
    @Test
    public void test30() throws Exception {
        byte[] plain = null;
        byte[] result = QuotedPrintableCodec.decodeQuotedPrintable( plain );
        assertEquals("Result should be null", null, result);

        // Additional regression test case with empty array
        byte[] plain2 = new byte[0];
        byte[] result2 = QuotedPrintableCodec.decodeQuotedPrintable( plain2 );
        assertEquals("Result should be null", null, result2);
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
        
        // Additional regression test case with same input
        String plain2 = "=\r\n";
        String encoded2 = qpcodec.encode(plain2);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=3D=0D=0A", encoded2);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain2, qpcodec.decode(encoded2));
    }
    @Test
    public void test32() throws Exception {
        String qpdata = "CRLF in an\n encoded text should be=20=\r\n\rskipped in the\r decoding.";
        String expected = "CRLF in an encoded text should be skipped in the decoding.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(expected, qpcodec.decode(qpdata));

        String encoded = qpcodec.encode(expected);
        assertEquals(expected, qpcodec.decode(encoded));
        
        // Additional regression test case with same input
        String qpdata2 = "CRLF in an\n encoded text should be=20=\r\n\rskipped in the\r decoding.";
        String expected2 = "CRLF in an encoded text should be skipped in the decoding.";

        assertEquals(expected2, qpcodec.decode(qpdata2));

        String encoded2 = qpcodec.encode(expected2);
        assertEquals(expected2, qpcodec.decode(encoded2));
    }
    @Test
    public void test33() throws Exception {
        String qpdata = "If you believe that truth=3Dbeauty, then surely=20=\r\nmathematics " +
                "is the most beautiful branch of philosophy.";
        String expected = "If you believe that truth=beauty, then surely mathematics " +
                "is the most beautiful branch of philosophy.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(expected, qpcodec.decode(qpdata));

        String encoded = qpcodec.encode(expected);
        assertEquals(expected, qpcodec.decode(encoded));
        
        // Additional regression test case with same input
        String qpdata2 = "If you believe that truth=3Dbeauty, then surely=20=\r\nmathematics " +
                "is the most beautiful branch of philosophy.";
        String expected2 = "If you believe that truth=beauty, then surely mathematics " +
                "is the most beautiful branch of philosophy.";

        assertEquals(expected2, qpcodec.decode(qpdata2));

        String encoded2 = qpcodec.encode(expected2);
        assertEquals(expected2, qpcodec.decode(encoded2));
    }
    @Test
    public void test34() {
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
        
        // Additional regression test case with same input
        QuotedPrintableCodec qpcodec2 = new QuotedPrintableCodec("NONSENSE");
        String plain2 = "Hello there!";
        
        try {
           qpcodec2.encode(plain2);
            fail( "We set the encoding to a bogus NONSENSE vlaue, this shouldn't have worked.");
        } catch (EncoderException ee) {
            // Exception expected, test segment passes.
        }
        try {
           qpcodec.decode(plain2);
            fail( "We set the encoding to a bogus NONSENSE vlaue, this shouldn't have worked.");
        } catch (DecoderException ee) {
            // Exception expected, test segment passes.
        }
    }
    @Test
    public void test35() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "1+1 = 2";
        String encoded = new String(QuotedPrintableCodec.
            encodeQuotedPrintable(null, plain.getBytes("UTF-8")));
        assertEquals("Basic quoted-printable encoding test", 
            "1+1 =3D 2", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        
        // Additional regression test case with same input
        String plain2 = "1+1 = 2";
        String encoded2 = new String(QuotedPrintableCodec.
            encodeQuotedPrintable(null, plain2.getBytes("UTF-8")));
        assertEquals("Basic quoted-printable encoding test", 
            "1+1 =3D 2", encoded2);
        assertEquals("Basic quoted-printable decoding test", 
            plain2, qpcodec.decode(encoded2));
    }
    @Test
    public void test36() throws Exception {
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
        
        // Additional regression test case with same input
        QuotedPrintableCodec qpcodec2 = new QuotedPrintableCodec();
        String plain2 = "1+1 =3D 2";
        String decoded2 = (String) qpcodec2.decode((Object) plain2);
        assertEquals("Basic quoted-printable decoding test", 
            "1+1 = 2", decoded2);

        byte[] plainBA2 = plain.getBytes("UTF-8");
        byte[] decodedBA2 = (byte[]) qpcodec2.decode((Object) plainBA2);
        decoded2 = new String(decodedBA2);
        assertEquals("Basic quoted-printable decoding test", 
            "1+1 = 2", decoded2);
        
        Object result2 = qpcodec2.decode((Object) null);
        assertEquals( "Decoding a null Object should return null", null, result2);
        
        try {
            Object dObj2 = new Double(3.0);
            qpcodec2.decode( dObj2 );
            fail( "Trying to url encode a Double object should cause an exception.");
        } catch (DecoderException ee) {
            // Exception expected, test segment passes.
        }
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
        
        // Additional regression test case with same input
        String plain2 = "= Hello there =\r\n";
        String encoded2 = qpcodec.encode(plain2);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Hello there =3D=0D=0A", encoded2);
        assertEquals("Basic quoted-printable decoding test", 
            plain2, qpcodec.decode(encoded2));
    }
    @Test
    public void test38() throws Exception {

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
        
        // Additional regression test case with same input
        String ru_msg2 = constructString(RUSSIAN_STUFF_UNICODE); 
        String ch_msg2 = constructString(SWISS_GERMAN_STUFF_UNICODE); 
        
        assertEquals(
            "=D0=92=D1=81=D0=B5=D0=BC_=D0=BF=D1=80=D0=B8=D0=B2=D0=B5=D1=82", 
        qpcodec.encode(ru_msg2, CharEncoding.UTF_8)
        );
        assertEquals("Gr=C3=BCezi_z=C3=A4m=C3=A4", qpcodec.encode(ch_msg2, CharEncoding.UTF_8));
        
        assertEquals(ru_msg2, qpcodec.decode(qpcodec.encode(ru_msg2, CharEncoding.UTF_8), CharEncoding.UTF_8));
        assertEquals(ch_msg2, qpcodec.decode(qpcodec.encode(ch_msg2, CharEncoding.UTF_8), CharEncoding.UTF_8));
    }
    @Test
    public void test39() throws Exception {
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
        
        // Additional regression test case with same input
        QuotedPrintableCodec qpcodec2 = new QuotedPrintableCodec();
        try {
            qpcodec2.decode("=");
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
        try {
            qpcodec2.decode("=A");
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
        try {
            qpcodec2.decode("=WW");
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
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
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test42() throws Exception {
        // whitespace, but does not need to be encoded
        String plain ="This is a example of a quoted=printable text file. There is no tt";
        String expected = "This is a example of a quoted=3Dprintable text file. There is no tt";

        assertEquals(expected, new QuotedPrintableCodec().encode(plain));
    }
    @Test
    public void test43() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="This is a example of a quoted-printable text file. This might contain sp=cial chars.";
        String expected = "This is a example of a quoted-printable text file. This might contain sp=3D=\r\ncial chars.";
        assertEquals(expected, qpcodec.encode(plain));

        plain ="This is a example of a quoted-printable text file. This might contain ta\tbs as well.";
        expected = "This is a example of a quoted-printable text file. This might contain ta=09=\r\nbs as well.";
        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test44() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        byte[] plain = null;
        byte[] encoded = qpcodec.encode(plain);
        assertEquals("Encoding a null string should return null", 
            null, encoded);
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
    public void test47() throws Exception {
        String plain = "Hello there!";
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("UnicodeBig");
        qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
        String encoded1 = qpcodec.encode(plain, "UnicodeBig");
        String encoded2 = qpcodec.encode(plain);
        assertEquals(encoded1, encoded2);
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
        String test = null;
        String result = qpcodec.encode( test, "charset" );
        assertEquals("Result should be null", null, result);
    }
    @Test
    public void test50() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=3D=0D=0A", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
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
    public void test53() throws Exception {
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
    public void test54() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "";
        String encoded = qpcodec.encode(plain);
        assertEquals("Empty string quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Empty string quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test55() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "1234567890";
        String encoded = qpcodec.encode(plain);
        assertEquals("Numbers quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Numbers quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test56() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "@_.*";
        String encoded = qpcodec.encode(plain);
        assertEquals("Special characters quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Special characters quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test57() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = " \t\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Whitespace quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Whitespace quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
	@Test
	public void test58() throws Exception {
		QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
		String plain = "";
		String encoded = qpcodec.encode(plain);
		assertEquals("Empty string quoted-printable encoding test",
			"", encoded);
		assertEquals("Empty string quoted-printable decoding test",
			plain, qpcodec.decode(encoded));
	}
	@Test
	public void test59() throws Exception {
		QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
		String plain = "   ";
		String encoded = qpcodec.encode(plain);
		assertEquals("Whitespace quoted-printable encoding test",
			plain, encoded);
		assertEquals("Whitespace quoted-printable decoding test",
			plain, qpcodec.decode(encoded));
	}
	@Test
	public void test60() throws Exception {
		QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
		String plain = "!@#$%^&*()-=_+[]{}|;':\",.<>/?`~";
		String encoded = qpcodec.encode(plain);
		assertEquals("Special chars quoted-printable encoding test",
			plain, encoded);
		assertEquals("Special chars quoted-printable decoding test",
			plain, qpcodec.decode(encoded));
	}
	@Test
	public void test61() throws Exception {
		QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
		String plain = "This is a really long string that is going to be encoded and then decoded to check if the result is the same as the original string. This is a really long string that is going to be encoded and then decoded to check if the result is the same as the original string. This is a really long string that is going to be encoded and then decoded to check if the result is the same as the original string. This is a really long string that is going to be encoded and then decoded to check if the result is the same as the original string.";
		String encoded = qpcodec.encode(plain);
		assertEquals("Long string quoted-printable encoding test",
			plain, encoded);
		assertEquals("Long string quoted-printable decoding test",
			plain, qpcodec.decode(encoded));
	}
    @Test
    public void test62() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertNull("Null string quoted-printable encoding test", 
            qpcodec.encode((String)null));
        assertNull("Null string quoted-printable decoding test", 
            qpcodec.decode((String)null));

        // Regression test case with empty string
        String emptyStr = "";
        assertNull("Empty string quoted-printable encoding test",
            qpcodec.encode(emptyStr));
        assertNull("Empty string quoted-printable decoding test", 
            qpcodec.decode(emptyStr));

        // Regression test case with only whitespace characters
        String whitespaceStr = "     ";
        assertNull("Whitespace string quoted-printable encoding test",
            qpcodec.encode(whitespaceStr));
        assertNull("Whitespace string quoted-printable decoding test", 
            qpcodec.decode(whitespaceStr));
    }
    @Test
    public void test63() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));

        // Regression test case with additional safe characters
        String additionalChars = "?:<>";
        String plainWithAdditionalChars = plain + additionalChars;
        String encodedWithAdditionalChars = qpcodec.encode(plainWithAdditionalChars);
        assertEquals("Safe chars with additional chars quoted-printable encoding test", 
            plainWithAdditionalChars, encodedWithAdditionalChars);
        assertEquals("Safe chars with additional chars quoted-printable decoding test", 
            plainWithAdditionalChars, qpcodec.decode(encodedWithAdditionalChars));
    }
    @Test
    public void test64() throws Exception {
        // whitespace, but does not need to be encoded
        String plain ="This is a example of a quoted=printable text file. There is no tt";
        String expected = "This is a example of a quoted=3Dprintable text file. There is no tt";

        assertEquals(expected, new QuotedPrintableCodec().encode(plain));

        // Regression test case with one additional character at the end
        String plainWithAdditionalChar = plain + "x";
        String expectedWithAdditionalChar = expected + "x";
        assertEquals(expectedWithAdditionalChar, new QuotedPrintableCodec().encode(plainWithAdditionalChar));
    }
    @Test
    public void test65() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="This is a example of a quoted-printable text file. This might contain sp=cial chars.";
        String expected = "This is a example of a quoted-printable text file. This might contain sp=3D=\r\ncial chars.";
        assertEquals(expected, qpcodec.encode(plain));

        plain ="This is a example of a quoted-printable text file. This might contain ta\tbs as well.";
        expected = "This is a example of a quoted-printable text file. This might contain ta=09=\r\nbs as well.";
        assertEquals(expected, qpcodec.encode(plain));

        // Regression test case with one additional character after the special character
        String plainWithAdditionalChar = plain + "x";
        String expectedWithAdditionalChar = expected + "x";
        assertEquals(expectedWithAdditionalChar, qpcodec.encode(plainWithAdditionalChar));
    }
    @Test
    public void test66() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        byte[] plain = null;
        byte[] encoded = qpcodec.encode(plain);
        assertEquals("Encoding a null string should return null", 
            null, encoded);

        // Regression test case with empty byte array
        byte[] emptyBytes = new byte[0];
        byte[] encodedEmptyBytes = qpcodec.encode(emptyBytes);
        assertArrayEquals("Empty byte array encoding test", emptyBytes, encodedEmptyBytes);

        // Regression test case with non-empty byte array
        byte[] nonEmptyBytes = {1, 2, 3, 4, 5};
        byte[] encodedNonEmptyBytes = qpcodec.encode(nonEmptyBytes);
        assertArrayEquals("Non-empty byte array encoding test", nonEmptyBytes, encodedNonEmptyBytes);
    }
    @Test
    public void test67() throws Exception {
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

        // Regression test case with one additional character after the soft break
        String plainWithAdditionalChar = plain + "x";
        String expectedWithAdditionalChar = expected + "x";
        assertEquals(expectedWithAdditionalChar, qpcodec.encode(plainWithAdditionalChar));
    }
    @Test
    public void test68() throws Exception {
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
    public void test69() throws Exception {
        String plain = "Hello there!";
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("UnicodeBig");
        qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
        String encoded1 = qpcodec.encode(plain, "UnicodeBig");
        String encoded2 = qpcodec.encode(plain);
        assertEquals(encoded1, encoded2);

        // Regression test case with different default encoding
        qpcodec = new QuotedPrintableCodec("UnicodeLittle");
        qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
        String encoded3 = qpcodec.encode(plain, "UnicodeLittle");
        String encoded4 = qpcodec.encode(plain);
        assertEquals(encoded3, encoded4);
    }
    @Test
    public void test70() {
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

        // Regression test case with empty encoding
        qpcodec = new QuotedPrintableCodec("");
        try {
            qpcodec.encode(plain);
             fail( "We set the encoding to an empty string, this shouldn't have worked.");
         } catch (EncoderException ee) {
             // Exception expected, test segment passes.
         }
         try {
            qpcodec.decode(plain);
             fail( "We set the encoding to an empty string, this shouldn't have worked.");
         } catch (DecoderException ee) {
             // Exception expected, test segment passes.
         }
    }
    @Test
    public void test71() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String test = null;
        String result = qpcodec.encode( test, "charset" );
        assertEquals("Result should be null", null, result);

        // Regression test case with empty charset
        test = "Hello there!";
        result = qpcodec.encode( test, "" );
        assertEquals("Result should be the same as input", test, result);
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

        // Regression test case with additional unsafe characters
        String additionalChars = "\r\n";
        String plainWithAdditionalChars = plain + additionalChars;
        String encodedWithAdditionalChars = qpcodec.encode(plainWithAdditionalChars);
        assertEquals("Unsafe chars with additional chars quoted-printable encoding test", 
            "=3D=0D=0A=0D=0A", encodedWithAdditionalChars);
        assertEquals("Unsafe chars with additional chars quoted-printable decoding test", 
            plainWithAdditionalChars, qpcodec.decode(encodedWithAdditionalChars));
    }
    @Test
    public void test73() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there =\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Hello there =3D=0D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));

        // Regression test case with additional special characters
        String additionalChars = "=: ";
        String plainWithAdditionalChars = plain + additionalChars;
        String encodedWithAdditionalChars = qpcodec.encode(plainWithAdditionalChars);
        assertEquals("Basic chars with additional chars quoted-printable encoding test", 
            "=3D Hello there =3D=0D=0A=3D: =20", encodedWithAdditionalChars);
        assertEquals("Basic chars with additional chars quoted-printable decoding test", 
            plainWithAdditionalChars, qpcodec.decode(encodedWithAdditionalChars));
    }
    @Test
    public void test74() throws Exception {

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

        // Regression test case with additional non-UTF-8 encoding
        String additionalChars = "àé";
        String ru_msgWithAdditionalChars = ru_msg + additionalChars;
        String ch_msgWithAdditionalChars = ch_msg + additionalChars;
        assertEquals(
            "=D0=92=D1=81=D0=B5=D0=BC_=D0=BF=D1=80=D0=B8=D0=B2=D0=B5=D1=82=C3=A0=C3=A9", 
            qpcodec.encode(ru_msgWithAdditionalChars, CharEncoding.UTF_8)
        );
        assertEquals("Gr=C3=BCezi_z=C3=A4m=C3=A4=C3=A0=C3=A9", qpcodec.encode(ch_msgWithAdditionalChars, CharEncoding.UTF_8));
        
        assertEquals(ru_msgWithAdditionalChars, qpcodec.decode(qpcodec.encode(ru_msgWithAdditionalChars, CharEncoding.UTF_8), CharEncoding.UTF_8));
        assertEquals(ch_msgWithAdditionalChars, qpcodec.decode(qpcodec.encode(ch_msgWithAdditionalChars, CharEncoding.UTF_8), CharEncoding.UTF_8));
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

        // Regression test case with additional special characters
        String additionalChars = "=.";
        String qpdataWithAdditionalChars = qpdata + additionalChars;
        String expectedWithAdditionalChars = expected + additionalChars;
        assertEquals(expectedWithAdditionalChars, qpcodec.encode(qpdataWithAdditionalChars));

        String decodedWithAdditionalChars = qpcodec.decode(qpdataWithAdditionalChars);
        assertEquals(qpdataWithAdditionalChars, qpcodec.encode(decodedWithAdditionalChars));
    }
    @Test
    public void test76() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertNull("Null string quoted-printable encoding test", 
            qpcodec.encode((String)null));
        assertNull("Null string quoted-printable decoding test", 
            qpcodec.decode((String)null));
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
        String qpdata = "CRLF in an\n encoded text should be=20=\r\n\rskipped in the\r decoding.";
        String expected = "CRLF in an encoded text should be skipped in the decoding.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(expected, qpcodec.decode(qpdata));

        String encoded = qpcodec.encode(expected);
        assertEquals(expected, qpcodec.decode(encoded));
    }
    @Test
    public void test80() throws Exception {
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
    public void test81() {
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
    public void test82() throws Exception {
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
    public void test83() throws Exception {
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
    public void test84() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there =\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Hello there =3D=0D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test85() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String test = null;
        String result = qpcodec.decode( test, "charset" );
        assertEquals("Result should be null", null, result);
    }
    @Test
    public void test86() throws Exception {

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
    public void test87() throws Exception {
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
    public void test88() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "";
        String encoded = qpcodec.encode(plain);
        assertEquals("Empty string quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Empty string quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test89() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "   ";
        String encoded = qpcodec.encode(plain);
        assertEquals("Whitespace string quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Whitespace string quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test90() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "こんにちは";
        String encoded = qpcodec.encode(plain);
        assertEquals("Non-ASCII characters quoted-printable encoding test", 
            "=E3=81=93=E3=82=93=E3=81=AB=E3=81=A1=E3=81=AF", encoded);
        assertEquals("Non-ASCII characters quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test91() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertNull("Null string quoted-printable encoding test", 
            qpcodec.encode((String)null));
        assertNull("Null string quoted-printable decoding test", 
            qpcodec.decode((String)null));
    }
    @Test
    public void test92() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test93() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=3D=0D=0A", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test94() throws Exception {
        String qpdata = "CRLF in an\n encoded text should be=20=\r\n\rskipped in the\r decoding.";
        String expected = "CRLF in an encoded text should be skipped in the decoding.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(expected, qpcodec.decode(qpdata));

        String encoded = qpcodec.encode(expected);
        assertEquals(expected, qpcodec.decode(encoded));
    }
    @Test
    public void test95() throws Exception {
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
    public void test96() {
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
    public void test97() throws Exception {
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
    public void test98() throws Exception {
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
    public void test99() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there =\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Hello there =3D=0D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test100() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String test = null;
        String result = qpcodec.decode( test, "charset" );
        assertEquals("Result should be null", null, result);
    }
    @Test
    public void test101() throws Exception {

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
    public void test102() throws Exception {
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
    public void test103() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals("", qpcodec.decode(""));
    }
    @Test
    public void test104() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(" ", qpcodec.decode("=20"));
    }
    @Test
    public void test105() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals("A", qpcodec.decode("A"));
    }
    @Test
    public void test106() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals("A ", qpcodec.decode("A ")); 
    }
    @Test
    public void test107() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals("A=", qpcodec.decode("A="));
    }
    @Test
    public void test108() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertNull("Null string quoted-printable encoding test",
            qpcodec.encode((String)null));
        assertNull("Null string quoted-printable decoding test",
            qpcodec.decode((String)null));
        
        // Regression test: empty string
        assertEquals("", qpcodec.encode(""));
        assertEquals("", qpcodec.decode(""));
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
        
        // Regression test: only special characters
        String specialChars = "=<>[]{}(),.:;@^_/\\";
        assertEquals(specialChars, qpcodec.encode(specialChars));
        assertEquals(specialChars, qpcodec.decode(specialChars));
    }
    @Test
    public void test110() throws Exception {
        // Regression test: input with only safe characters
        String plain ="This is a plain text";
        String expected = "This is a plain text";
        assertEquals(expected, new QuotedPrintableCodec().encode(plain));
    }
    @Test
    public void test111() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        
        // Regression test: input without special characters
        String plain ="This is a plain text";
        String expected = "This is a plain text";
        assertEquals(expected, qpcodec.encode(plain));
        
        // Regression test: input with tabs and spaces
        plain ="This is a plain text file. It contains a tab\tand a space "; 
        expected = "This is a plain text file. It contains a tab=09and a space=20";
        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test112() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        byte[] plain = null;
        byte[] encoded = qpcodec.encode(plain);
        assertEquals("Encoding a null string should return null",
            null, encoded);
        
        // Regression test: empty byte array
        assertArrayEquals(new byte[]{}, qpcodec.encode(new byte[]{}));
    }
    @Test
    public void test113() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        
        // Regression test: input without any special characters
        String plain ="This is a plain text ";
        String expected = "This is a plain text";
        assertEquals(expected, qpcodec.encode(plain));
        
        // Regression test: input with spaces before soft break
        plain ="This is a plain text file. There is a space here   ";
        expected = "This is a plain text file. There is a space here=20";
        assertEquals(expected, qpcodec.encode(plain));
        
        // Regression test: input with non-printable character before soft break
        plain ="This is a plain text file. There is a soft break here=\n";
        expected = "This is a plain text file. There is a soft break here=3D0D";
        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test114() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "1+1 = 2";
        String encoded = (String) qpcodec.encode((Object) plain);
        assertEquals("Basic quoted-printable encoding test",
            "1+1 =3D 2", encoded);

        // Regression test: encode empty byte array
        byte[] plainBA = new byte[]{};
        byte[] encodedBA = (byte[]) qpcodec.encode((Object) plainBA);
        assertArrayEquals(new byte[]{}, encodedBA);
                
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
    public void test115() throws Exception {
        String plain = "Hello there!";
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("UnicodeBig");
        qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
        String encoded1 = qpcodec.encode(plain, "UnicodeBig");
        String encoded2 = qpcodec.encode(plain);
        assertEquals(encoded1, encoded2);
        
        // Regression test: input with spaces
        assertEquals("Hello=20there!", qpcodec.encode("Hello there!"));
    }
    @Test
    public void test116() {
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
    public void test117() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String test = null;
        String result = qpcodec.encode( test, "charset" );
        assertEquals("Result should be null", null, result);
        
        // Regression test: empty charset
        assertEquals("test", qpcodec.decode(qpcodec.encode("test", "")));
    }
    @Test
    public void test118() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test",
            "=3D=0D=0A", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test",
            plain, qpcodec.decode(encoded));
        
        // Regression test: input with a null character
        plain = "Hello\u0000there!";
        encoded = qpcodec.encode(plain);
        assertEquals("Hello=00there!", encoded);
        assertEquals(plain, qpcodec.decode(encoded));
    }
    @Test
    public void test119() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there =\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test",
            "=3D Hello there =3D=0D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test",
            plain, qpcodec.decode(encoded));
        
        // Regression test: input with equal sign followed by two digits
        plain = "x=10 and y=11";
        encoded = qpcodec.encode(plain);
        assertEquals("x=3D10 and y=3D11", encoded);
        assertEquals(plain, qpcodec.decode(encoded));
    }
    @Test
    public void test120() throws Exception {

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
    public void test121() throws Exception {
        String qpdata = "If you believe that truth=3Dbeauty, then surely mathematics is the most " +
                "beautiful branch of philosophy.";
        String expected = "If you believe that truth=beauty, then surely mathematics is the most " +
                "beautiful branch of philosophy.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(qpdata, qpcodec.encode(expected));

        String decoded = qpcodec.decode(qpdata);
        assertEquals(qpdata, qpcodec.encode(decoded));
        
        // Regression test: input with equal sign at end of line
        qpdata = "This line ends with an=";
        expected = "This line ends with an=";
        assertEquals(expected, qpcodec.encode(qpdata));
        assertEquals(expected, qpcodec.decode(expected));
        
        // Regression test: input with equal sign at beginning of line
        qpdata = "=This line starts with an";
        expected = "=This line starts with an";
        assertEquals(expected, qpcodec.encode(qpdata));
        assertEquals(expected, qpcodec.decode(expected));
    }
    @Test(expected = NullPointerException.class)
    public void test122() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        qpcodec.encode("Hello\u0000there!");
    }
    @Test
    public void test123() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertNull("Null string quoted-printable encoding test", 
            qpcodec.encode((String)null));
        assertNull("Null string quoted-printable decoding test", 
            qpcodec.decode((String)null));
        // Regression test
        assertNotNull("Null object quoted-printable encoding test",
            qpcodec.encode((Object)null));
    }
    @Test
    public void test124() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        // Regression test
        String[] safeStrings = {"string1", "string2", "string3"};
        for (String str : safeStrings) {
            assertEquals("Safe chars quoted-printable encoding test 2", 
                str, qpcodec.encode(str));
            assertEquals("Safe chars quoted-printable decoding test 2", 
                str, qpcodec.decode(qpcodec.encode(str)));
        }
    }
    @Test
    public void test125() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=3D=0D=0A", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        // Regression test
        String[] unsafeStrings = {"string1", "string2", "string3"};
        for (String str : unsafeStrings) {
            assertEquals("Unsafe chars quoted-printable encoding test", 
                str, qpcodec.encode(str));
            assertEquals("Unsafe chars quoted-printable decoding test", 
                str, qpcodec.decode(qpcodec.encode(str)));
        }
    }
    @Test
    public void test126() throws Exception {
        String qpdata = "CRLF in an\n encoded text should be=20=\r\n\rskipped in the\r decoding.";
        String expected = "CRLF in an encoded text should be skipped in the decoding.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(expected, qpcodec.decode(qpdata));

        String encoded = qpcodec.encode(expected);
        assertEquals(expected, qpcodec.decode(encoded));
        // Regression test
        String[] inputStrings = {"string1", "string2", "string3"};
        for (String str : inputStrings) {
            assertEquals(expected, qpcodec.decode(qpcodec.encode(str)));
        }
    }
    @Test
    public void test127() throws Exception {
        String qpdata = "If you believe that truth=3Dbeauty, then surely=20=\r\nmathematics " +
                "is the most beautiful branch of philosophy.";
        String expected = "If you believe that truth=beauty, then surely mathematics " +
                "is the most beautiful branch of philosophy.";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(expected, qpcodec.decode(qpdata));

        String encoded = qpcodec.encode(expected);
        assertEquals(expected, qpcodec.decode(encoded));
        // Regression test
        String[] inputStrings = {"string1", "string2", "string3"};
        for (String str : inputStrings) {
            assertEquals(expected, qpcodec.decode(qpcodec.encode(str)));
        }
    }
    @Test
    public void test128() {
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
        // Regression test
        String[] inputStrings = {"string1", "string2", "string3"};
        for (String str : inputStrings) {
            try {
                qpcodec.encode(str);
                fail("EncoderException should have been thrown");
            } catch (EncoderException ee) {
                // Exception expected, test segment passes.
            }
            try {
                qpcodec.decode(str);
                fail("DecoderException should have been thrown");
            } catch (DecoderException ee) {
                // Exception expected, test segment passes.
            }
        }
    }
    @Test
    public void test129() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "1+1 = 2";
        String encoded = new String(QuotedPrintableCodec.
            encodeQuotedPrintable(null, plain.getBytes("UTF-8")));
        assertEquals("Basic quoted-printable encoding test", 
            "1+1 =3D 2", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        // Regression test
        String[] inputStrings = {"string1", "string2", "string3"};
        for (String str : inputStrings) {
            assertEquals("Basic quoted-printable encoding test", 
                str, new String(QuotedPrintableCodec.encodeQuotedPrintable(null, str.getBytes("UTF-8"))));
            assertEquals("Basic quoted-printable decoding test", 
                str, qpcodec.decode(qpcodec.encode(str)));
        }
    }
    @Test
    public void test130() throws Exception {
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
        // Regression test
        Object[] inputObjects = {new Integer(3), new Float(3.14), new Double(2.7183)};
        for (Object obj : inputObjects) {
            try {
                qpcodec.decode(obj);
                fail("DecoderException should have been thrown");
            } catch (DecoderException ee) {
                // Exception expected, test segment passes.
            }
        }
    }
    @Test
    public void test131() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there =\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Hello there =3D=0D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        // Regression test
        String[] inputStrings = {"string1", "string2", "string3"};
        for (String str : inputStrings) {
            assertEquals("Basic quoted-printable encoding test", 
                str, qpcodec.encode(str));
            assertEquals("Basic quoted-printable decoding test", 
                str, qpcodec.decode(qpcodec.encode(str)));
        }
    }
    @Test
    public void test132() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String test = null;
        String result = qpcodec.decode( test, "charset" );
        assertEquals("Result should be null", null, result);
        // Regression test
        String[] inputStrings = {"string1", "string2", "string3"};
        for (String str : inputStrings) {
            assertEquals(str, qpcodec.decode(str, "charset"));
        }
    }
    @Test
    public void test133() throws Exception {

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
        // Regression test
        String[] inputStrings = {"string1", "string2", "string3"};
        for (String str : inputStrings) {
            assertEquals(str, qpcodec.decode(qpcodec.encode(str, CharEncoding.UTF_8), CharEncoding.UTF_8));
        }
    }
    @Test
    public void test134() throws Exception {
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
        // Regression test
        String[] invalidStrings = {"abc", "xyz", "!@#"};
        for (String str : invalidStrings) {
            try {
                qpcodec.decode(str);
                fail("DecoderException should have been thrown");
            } catch (DecoderException e) {
                // Expected. Move on
            }
        }
    }
    @Test
    public void test135() {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String encoded = qpcodec.encode("");
        assertEquals("", encoded);
        String decoded = qpcodec.decode("");
        assertEquals("", decoded);
    }
    @Test
    public void test136() {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb.append("a");
        }
        String plain = sb.toString();
        String encoded = qpcodec.encode(plain);
        assertEquals(plain, encoded);
        String decoded = qpcodec.decode(encoded);
        assertEquals(plain, decoded);
    }
    @Test
    public void test137() {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "テスト";
        String encoded = qpcodec.encode(plain);
        assertEquals("=E3=83=86=E3=82=B9=E3=83=88", encoded);
        String decoded = qpcodec.decode(encoded);
        assertEquals(plain, decoded);
    }
    @Test
    public void test138() {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "!@#$%^&*()";
        String encoded = qpcodec.encode(plain);
        assertEquals(plain, encoded);
        String decoded = qpcodec.decode(encoded);
        assertEquals(plain, decoded);
    }
    @Test
    public void test139() {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "  \t  ";
        String encoded = qpcodec.encode(plain);
        assertEquals(plain, encoded);
        String decoded = qpcodec.decode(encoded);
        assertEquals(plain, decoded);
    }
    @Test
    public void test140() {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "This is a test.\nThis is another test.\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("This is a test.\nThis is another test.\n", encoded);
        String decoded = qpcodec.decode(encoded);
        assertEquals(plain, decoded);
    }
@Test
public void test141() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    assertNull("Null string quoted-printable encoding test", 
        qpcodec.encode((String)null));
    assertNull("Null string quoted-printable decoding test", 
        qpcodec.decode((String)null));
    // Regression test: empty string 
    assertEquals("", qpcodec.encode(""));
    assertEquals("", qpcodec.decode(""));
    // Regression test: string with whitespace only
    assertEquals("   ", qpcodec.encode("   "));
    assertEquals("   ", qpcodec.decode("   "));
    // Regression test: string with special characters
    assertEquals("!@#$%^&*()", qpcodec.encode("!@#$%^&*()"));
    assertEquals("!@#$%^&*()", qpcodec.decode("!@#$%^&*()"));
}
@Test
public void test142() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
    String encoded = qpcodec.encode(plain);
    assertEquals("Safe chars quoted-printable encoding test", 
        plain, encoded);
    assertEquals("Safe chars quoted-printable decoding test", 
        plain, qpcodec.decode(encoded));
    // Regression test: string with lowercase letters
    String lowercase = "abcdefghijklmnopqrstuvwxyz";
    String encodedLowercase = qpcodec.encode(lowercase);
    assertEquals("abcdefghijklmnopqrstuvwxyz", encodedLowercase);
    assertEquals(lowercase, qpcodec.decode(encodedLowercase));
    // Regression test: string with uppercase letters
    String uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String encodedUppercase = qpcodec.encode(uppercase);
    assertEquals("ABCDEFGHIJKLMNOPQRSTUVWXYZ", encodedUppercase);
    assertEquals(uppercase, qpcodec.decode(encodedUppercase));
}
@Test
public void test143() throws Exception {
    // whitespace, but does not need to be encoded
    String plain ="This is a example of a quoted=printable text file. There is no tt";
    String expected = "This is a example of a quoted=3Dprintable text file. There is no tt";
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    assertEquals(expected, qpcodec.encode(plain));
    // Regression test: string with mixed characters and digits
    String mixed = "A2B4C6D8E0";
    String encodedMixed = qpcodec.encode(mixed);
    assertEquals("A2B4C6D8E0", encodedMixed);
    assertEquals(mixed, qpcodec.decode(encodedMixed));
}
@Test
public void test144() throws Exception {
    final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain ="This is a example of a quoted-printable text file. This might contain sp=cial chars.";
    String expected = "This is a example of a quoted-printable text file. This might contain sp=3D=\r\ncial chars.";
    assertEquals(expected, qpcodec.encode(plain));
    // Regression test: string with tabs
    String tabs = "hello\tworld";
    String encodedTabs = qpcodec.encode(tabs);
    assertEquals("hello=09world", encodedTabs);
    assertEquals(tabs, qpcodec.decode(encodedTabs));
}
@Test
public void test145() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    byte[] plain = null;
    byte[] encoded = qpcodec.encode(plain);
    assertEquals("Encoding a null string should return null", 
        null, encoded);
    // Regression test: empty byte array
    byte[] emptyArray = new byte[0];
    byte[] encodedEmptyArray = qpcodec.encode(emptyArray);
    assertArrayEquals(emptyArray, encodedEmptyArray);
}
@Test
public void test146() throws Exception {
    final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain ="This is a example of a quoted-printable text file. There is no end to it\t";
    String expected = "This is a example of a quoted-printable text file. There is no end to i=\r\nt=09";
    assertEquals(expected, qpcodec.encode(plain));
    // Regression test: string without trailing tabs
    String noTabs = "This is a example of a quoted-printable text file. There is no end to it ";
    String expectedNoTabs = "This is a example of a quoted-printable text file. There is no end to i=\r\nt=20";
    assertEquals(expectedNoTabs, qpcodec.encode(noTabs));
    // Regression test: string with whitespace before soft break
    String whitespace = "This is a example of a quoted-printable text file. There is no end to   ";
    String expectedWhitespace = "This is a example of a quoted-printable text file. There is no end to=20=\r\n =20";
    assertEquals(expectedWhitespace, qpcodec.encode(whitespace));
    // Regression test: string with non-printable character before soft break
    String nonPrintable = "This is a example of a quoted-printable text file. There is no end to=  ";
    String expectedNonPrintable = "This is a example of a quoted-printable text file. There is no end to=3D=\r\n =20";
    assertEquals(expectedNonPrintable, qpcodec.encode(nonPrintable));
}
@Test
public void test147() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "1+1 = 2";
    String encoded = (String) qpcodec.encode((Object) plain);
    assertEquals("Basic quoted-printable encoding test", 
        "1+1 =3D 2", encoded);
    // Regression test: object of type Integer
    Integer integerObj = new Integer(12345);
    try {
        qpcodec.encode(integerObj);
        fail("Trying to url encode an Integer object should cause an exception.");
    } catch (EncoderException ee) {
        // Exception expected, test segment passes.
    }
    // Regression test: object of type Float
    Float floatObj = new Float(3.14);
    try {
        qpcodec.encode(floatObj);
        fail("Trying to url encode a Float object should cause an exception.");
    } catch (EncoderException ee) {
        // Exception expected, test segment passes.
    }
}
@Test
public void test148() throws Exception {
    String plain = "Hello there!";
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("UnicodeBig");
    qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
    String encoded1 = qpcodec.encode(plain, "UnicodeBig");
    String encoded2 = qpcodec.encode(plain);
    assertEquals(encoded1, encoded2);
    // Regression test: encoding with non-existent charset
    QuotedPrintableCodec invalidCodec = new QuotedPrintableCodec("NONSENSE");
    String invalidEncoded = invalidCodec.encode(plain);
    assertEquals(plain, invalidEncoded);
}
@Test
public void test149() {
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
    // Regression test: encoding with null charset
    QuotedPrintableCodec nullCodec = new QuotedPrintableCodec(null);
    String nullEncoded = nullCodec.encode(plain);
    assertEquals(plain, nullEncoded);
}
@Test
public void test150() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String test = null;
    String result = qpcodec.encode( test, "charset" );
    assertEquals("Result should be null", null, result);
    // Regression test: null charset
    String notNullResult = qpcodec.encode(test, null);
    assertEquals(null, notNullResult);
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
    // Regression test: string with unsafe characters
    String unsafe = "<>&";
    String encodedUnsafe = qpcodec.encode(unsafe);
    assertEquals("<>&", encodedUnsafe);
    assertEquals(unsafe, qpcodec.decode(encodedUnsafe));
}
@Test
public void test152() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "= Hello there =\r\n";
    String encoded = qpcodec.encode(plain);
    assertEquals("Basic quoted-printable encoding test", 
        "=3D Hello there =3D=0D=0A", encoded);
    assertEquals("Basic quoted-printable decoding test", 
        plain, qpcodec.decode(encoded));
    // Regression test: string with trailing spaces
    String trailingSpaces = "Hello there       ";
    String encodedTrailingSpaces = qpcodec.encode(trailingSpaces);
    assertEquals("Hello there       ", encodedTrailingSpaces);
    assertEquals(trailingSpaces, qpcodec.decode(encodedTrailingSpaces));
}
@Test
public void test153() throws Exception {

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
    // Regression test: string with non-ASCII characters
    String nonAscii = "èéêë"; 
    String encodedNonAscii = qpcodec.encode(nonAscii, CharEncoding.UTF_8);
    assertEquals("=C3=A8=C3=A9=C3=AA=C3=AB", encodedNonAscii);
    assertEquals(nonAscii, qpcodec.decode(encodedNonAscii, CharEncoding.UTF_8));
}
@Test
public void test154() throws Exception {
    String qpdata = "If you believe that truth=3Dbeauty, then surely mathematics is the most " +
            "b=\r\neautiful branch of philosophy.";
    String expected = "If you believe that truth=beauty, then surely mathematics is the most " +
            "beautiful branch of philosophy.";

    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    assertEquals(qpdata, qpcodec.encode(expected));

    String decoded = qpcodec.decode(qpdata);
    assertEquals(qpdata, qpcodec.encode(decoded));
    // Regression test: string with no special characters
    String noSpecial = "This is a test";
    String encodedNoSpecial = qpcodec.encode(noSpecial);
    assertEquals(noSpecial, encodedNoSpecial);
    assertEquals(noSpecial, qpcodec.decode(encodedNoSpecial));
}
}