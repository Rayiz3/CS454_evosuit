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
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "= Hello there =\r\n";
    String encoded = qpcodec.encode(plain);
    assertEquals("Basic quoted-printable encoding test", 
        "=3D Hello there =3D=0D=0A", encoded);
    assertEquals("Basic quoted-printable decoding test", 
        plain, qpcodec.decode(encoded));
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
}
@Test
public void test5() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    byte[] plain = null;
    byte[] encoded = qpcodec.encode(plain);
    assertEquals("Encoding a null string should return null", 
        null, encoded);
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
    String plain = "1+1 = 2";
    String encoded = new String(QuotedPrintableCodec.
        encodeQuotedPrintable(null, plain.getBytes("UTF-8")));
    assertEquals("Basic quoted-printable encoding test", 
        "1+1 =3D 2", encoded);
    assertEquals("Basic quoted-printable decoding test", 
        plain, qpcodec.decode(encoded));
    
}
@Test
public void test10() throws Exception {
    // whitespace, but does not need to be encoded
    String plain ="This is a example of a quoted=printable text file. There is no tt";
    String expected = "This is a example of a quoted=3Dprintable text file. There is no tt";

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
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there =\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Hello there =3D=0D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test14() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test15() throws Exception {
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
    public void test16() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="This is a example of a quoted-printable text file. This might contain sp=cial chars.";
        String expected = "This is a example of a quoted-printable text file. This might contain sp=3D=\r\ncial chars.";
        assertEquals(expected, qpcodec.encode(plain));

        plain ="This is a example of a quoted-printable text file. This might contain ta\tbs as well.";
        expected = "This is a example of a quoted-printable text file. This might contain ta=09=\r\nbs as well.";
        assertEquals(expected, qpcodec.encode(plain));
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
        String plain = "1+1 = 2";
        String encoded = new String(QuotedPrintableCodec.
            encodeQuotedPrintable(null, plain.getBytes("UTF-8")));
        assertEquals("Basic quoted-printable encoding test", 
            "1+1 =3D 2", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test19() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there =\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Hello there =3D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
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
    }
    @Test
    public void test21() {
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
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = null;
        String encoded = qpcodec.encode(plain);
        assertEquals("Encoded null value", 
            null, encoded);
    }
    @Test
    public void test24() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "";
        String encoded = qpcodec.encode(plain);
        assertEquals("Encoded blank value", 
            "", encoded);
    }
    @Test
    public void test25() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "   ";
        String encoded = qpcodec.encode(plain);
        assertEquals("Encoded whitespace value", 
            "   ", encoded);
    }
@Test
public void test26() throws Exception {
    String qpdata = "If you believe that truth=beauty, then surely mathematics is the most " +
            "beautiful branch of philosophy.";
    String expected = "If you believe that truth=beauty, then surely mathematics is the most " +
            "beautiful branch of philosophy.";

    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    assertEquals(qpdata, qpcodec.encode(expected));

    String decoded = qpcodec.decode(qpdata);
    assertEquals(qpdata, qpcodec.encode(decoded));
}
@Test
public void test27() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "= Hello there =";
    String encoded = qpcodec.encode(plain);
    assertEquals("Basic quoted-printable encoding test", 
        "=3D Hello there =3D=0D=0A", encoded);
    assertEquals("Basic quoted-printable decoding test", 
        plain, qpcodec.decode(encoded));
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
public void test29() throws Exception {
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
public void test30() {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("ISO-8859-1");
    String plain = "Hello there!";
    try {
        qpcodec.encode(plain);
        fail( "We set the encoding to a different value, this shouldn't have worked.");
    } catch (EncoderException ee) {
        // Exception expected, test segment passes.
    }
    try {
        qpcodec.decode(plain);
        fail( "We set the encoding to a different value, this shouldn't have worked.");
    } catch (DecoderException ee) {
        // Exception expected, test segment passes.
    }
}
@Test
public void test31() throws Exception {
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
public void test32() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    byte[] plain = null;
    byte[] encoded = qpcodec.encode(plain);
    assertEquals("Encoding a null string should return null", 
        null, encoded);
}
@Test
public void test33() throws Exception {
    final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

    String plain ="This is a example of a quoted-printable text file. This might contain sp=cial chars.";
    String expected = "This is a example of a quoted-printable text file. This might contain sp=3D=\r\ncial chars.";
    assertEquals(expected, qpcodec.encode(plain));

    plain ="This is a example of a quoted-printable text file. This might contain ta\tbs as well.";
    expected = "This is a example of a quoted-printable text file. This might contain ta=09=\r\nbs as well.";
    assertEquals(expected, qpcodec.encode(plain));
}
@Test
public void test34() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "=\r\n";
    String encoded = qpcodec.encode(plain);
    assertEquals("Unsafe chars quoted-printable encoding test", 
        "=3D=0D=0A", encoded);
    assertEquals("Unsafe chars quoted-printable decoding test", 
        plain, qpcodec.decode(encoded));
}
@Test
public void test35() throws Exception {

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
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String test = null;
    String result = qpcodec.encode( test, "ISO-8859-1" );
    assertEquals("Result should be null", null, result);
}
@Test
public void test37() throws Exception {
    // whitespace, but does not need to be encoded
    String plain ="This is a example of a quoted=printable text file. There is no tt";
    String expected = "This is a example of a quoted=3Dprintable text file. There is no tt";

    assertEquals(expected, new QuotedPrintableCodec().encode(plain));
}
@Test
public void test38() throws Exception {
    String plain = "Hello there!";
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("UTF-16BE");
    qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
    String encoded1 = qpcodec.encode(plain, "UTF-16BE");
    String encoded2 = qpcodec.encode(plain);
    assertEquals(encoded1, encoded2);
}
    @Test
    public void test39() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "";
        String encoded = qpcodec.encode(plain);
        assertEquals("Empty string quoted-printable encoding test", "", encoded);
        assertEquals("Empty string quoted-printable decoding test", plain, qpcodec.decode(encoded));
    }
    @Test
    public void test40() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "!@#$%^&*()_+-=";
        String encoded = qpcodec.encode(plain);
        assertEquals("Special chars quoted-printable encoding test", plain, encoded);
        assertEquals("Special chars quoted-printable decoding test", plain, qpcodec.decode(encoded));
    }
    @Test
    public void test41() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "     ";
        String encoded = qpcodec.encode(plain);
        assertEquals("Whitespace quoted-printable encoding test", plain, encoded);
        assertEquals("Whitespace quoted-printable decoding test", plain, qpcodec.decode(encoded));
    }
    @Test
    public void test42() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "Hello123_!@#$%^&*()/";
        String encoded = qpcodec.encode(plain);
        assertEquals("Mixed chars quoted-printable encoding test", plain, encoded);
        assertEquals("Mixed chars quoted-printable decoding test", plain, qpcodec.decode(encoded));
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
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    assertNull("Null string quoted-printable encoding test", 
        qpcodec.encode((String)null));
    assertNull("Null string quoted-printable decoding test", 
        qpcodec.decode((String)null));
}
@Test
public void test45() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "= Hello there =\r\n";
    String encoded = qpcodec.encode(plain);
    assertEquals("Basic quoted-printable encoding test", 
        "=3D Hello there =3D=0D=0A", encoded);
    assertEquals("Basic quoted-printable decoding test", 
        plain, qpcodec.decode(encoded));
}
@Test
public void test46() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "abc123_-.*~!@#$%^&()+{}\"\\;:`,/[]";
    String encoded = qpcodec.encode(plain);
    assertEquals("Safe chars quoted-printable encoding test", 
        plain, encoded);
    assertEquals("Safe chars quoted-printable decoding test", 
        plain, qpcodec.decode(encoded));
}
@Test
public void test47() throws Exception {
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
    byte[] plain = null;
    byte[] encoded = qpcodec.encode(plain);
    assertEquals("Encoding a null string should return null", 
        null, encoded);
}
@Test
public void test51() throws Exception {
    final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

    String plain ="This is a example of a quoted-printable text file. This might contain sp=cial chars.";
    String expected = "This is a example of a quoted-printable text file. This might contain sp=3D=\r\ncial chars.";
    assertEquals(expected, qpcodec.encode(plain));

    plain ="This is a example of a quoted-printable text file. This might contain ta\tbs as well.";
    expected = "This is a example of a quoted-printable text file. This might contain ta=09=\r\nbs as well.";
    assertEquals(expected, qpcodec.encode(plain));
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
}
@Test
public void test54() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String test = null;
    String result = qpcodec.encode( test, "charset" );
    assertEquals("Result should be null", null, result);
}
@Test
public void test55() throws Exception {
    // whitespace, but does not need to be encoded
    String plain ="This is a example of a quoted=printable text file. There is no tt";
    String expected = "This is a example of a quoted=3Dprintable text file. There is no tt";

    assertEquals(expected, new QuotedPrintableCodec().encode(plain));
}
@Test
public void test56() throws Exception {
    String plain = "Hello there!";
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("UnicodeBig");
    qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
    String encoded1 = qpcodec.encode(plain, "UnicodeBig");
    String encoded2 = qpcodec.encode(plain);
    assertEquals(encoded1, encoded2);
}
@Test
public void test57() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    assertNull("Null string quoted-printable encoding test", 
        qpcodec.encode((String)null));
    assertNull("Null string quoted-printable decoding test", 
        qpcodec.decode((String)null));
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
    String plain = "    ";
    String encoded = qpcodec.encode(plain);
    assertEquals("Whitespace quoted-printable encoding test", 
        plain, encoded);
    assertEquals("Whitespace quoted-printable decoding test", 
        plain, qpcodec.decode(encoded));
}
@Test
public void test60() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "~!@#$%^&*()_+`{}|:\"<>?[];',./-=";
    String encoded = qpcodec.encode(plain);
    assertEquals("Special characters quoted-printable encoding test", 
        "", encoded);
    assertEquals("Special characters quoted-printable decoding test", 
        plain, qpcodec.decode(encoded));
}
@Test
public void test61() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "中文";
    String encoded = qpcodec.encode(plain);
    assertEquals("Unicode quoted-printable encoding test", 
        "=E4=B8=AD=E6=96=87", encoded);
    assertEquals("Unicode quoted-printable decoding test", 
        plain, qpcodec.decode(encoded));
}
    @Test
    public void test62() throws Exception {
        // Original input: null
        // Mutant-killing input:
        String input = "";
        
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertNull("Null string quoted-printable encoding test", 
            qpcodec.encode(input));
        assertNull("Null string quoted-printable decoding test", 
            qpcodec.decode(input));
    }
    @Test
    public void test63() throws Exception {
        // Original input: "= Hello there =\r\n"
        // Mutant-killing input:
        String input = "";
        
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = input;
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test64() throws Exception {
        // Original input: "1+1 =3D 2"
        // Mutant-killing input:
        Object obj = new Object();
        
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = obj.toString();
        String decoded = (String) qpcodec.decode((Object) plain);
        assertEquals("Basic quoted-printable decoding test", 
            plain, decoded);
        
        byte[] plainBA = plain.getBytes("UTF-8");
        byte[] decodedBA = (byte[]) qpcodec.decode((Object) plainBA);
        decoded = new String(decodedBA);
        assertEquals("Basic quoted-printable decoding test", 
            plain, decoded);
            
        Object result = qpcodec.decode((Object) null);
        assertEquals( "Decoding a null Object should return null", null, result);
        
        try {
            qpcodec.decode(obj);
            fail( "Trying to url encode a non-string Object should cause an exception.");
        } catch (DecoderException ee) {
            // Exception expected, test segment passes.
        }
    }
    @Test
    public void test65() throws Exception {
        // Original input: "abc123_-.*~!@#$%^&()+{}\"\;:`,/[]"
        // Mutant-killing input:
        String input = "";
        
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = input;
        String encoded = qpcodec.encode(plain);
        assertEquals("Safe chars quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Safe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test66() {
        // Original input: "Hello there!"
        // Mutant-killing input:
        String input = "";
        
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("NONSENSE");
        String plain = input;
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
    public void test67() throws Exception {
        // Original input: "If you believe that truth=3Dbeauty, then surely=20=\r\nmathematics is the most beautiful branch of philosophy."
        // Mutant-killing input:
        String input = "";
        
        String expected = input;

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(expected, qpcodec.decode(input));

        String encoded = qpcodec.encode(expected);
        assertEquals(expected, qpcodec.decode(encoded));
    }
    @Test
    public void test68() throws Exception {
        // Original input: "=\r\n"
        // Mutant-killing input:
        String input = "";
                
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = input;
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test69() throws Exception {
        // Original input: Constructed Russian and Swiss German strings
        // Mutant-killing input:
        String input = "";
        
        String ru_msg = constructString(input); 
        String ch_msg = constructString(input); 
        
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        
        assertEquals("", qpcodec.encode(ru_msg, CharEncoding.UTF_8));
        assertEquals("", qpcodec.encode(ch_msg, CharEncoding.UTF_8));
        
        assertEquals(ru_msg, qpcodec.decode(qpcodec.encode(ru_msg, CharEncoding.UTF_8), CharEncoding.UTF_8));
        assertEquals(ch_msg, qpcodec.decode(qpcodec.encode(ch_msg, CharEncoding.UTF_8), CharEncoding.UTF_8));
    }
    @Test
    public void test70() throws Exception {
        // Original input: "1+1 = 2"
        // Mutant-killing input:
        String input = "";
        
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = input;
        String encoded = new String(QuotedPrintableCodec.
            encodeQuotedPrintable(null, plain.getBytes("UTF-8")));
        assertEquals("Basic quoted-printable encoding test", 
            plain, encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
        
    }
    @Test
    public void test71() throws Exception {
        // Original input: null
        // Mutant-killing input:
        String input = "";
        
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String test = input;
        String result = qpcodec.decode(test, "charset");
        assertEquals("Result should be null", null, result);
    }
    @Test
    public void test72() throws Exception {
        // Original input: "CRLF in an\n encoded text should be=20=\r\n\rskipped in the\r decoding."
        // Mutant-killing input:
        String input = "";
        
        String qpdata = input;
        String expected = input;

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(expected, qpcodec.decode(qpdata));

        String encoded = qpcodec.encode(expected);
        assertEquals(expected, qpcodec.decode(encoded));
    }
    @Test
    public void test73() throws Exception {
        // Original input: "="
        // Mutant-killing input:
        String input = "";
        
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        try {
            qpcodec.decode(input);
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
        try {
            qpcodec.decode(input);
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
        try {
            qpcodec.decode(input);
            fail("DecoderException should have been thrown");
        } catch (DecoderException e) {
            // Expected. Move on
        }
    }
@Test
public void test74() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "";
    String encoded = qpcodec.encode(plain);
    assertEquals("Empty string quoted-printable encoding test",
            "", encoded);
    assertEquals("Empty string quoted-printable decoding test",
            plain, qpcodec.decode(encoded));
}
@Test
public void test75() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "   ";
    String encoded = qpcodec.encode(plain);
    assertEquals("Whitespace string quoted-printable encoding test",
            plain, encoded);
    assertEquals("Whitespace string quoted-printable decoding test",
            plain, qpcodec.decode(encoded));
}
@Test
public void test76() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "!@#$%^&*()_+-=[]{}|;':,.<>?/~`";
    String encoded = qpcodec.encode(plain);
    assertEquals("Special characters quoted-printable encoding test",
            plain, encoded);
    assertEquals("Special characters quoted-printable decoding test",
            plain, qpcodec.decode(encoded));
}
@Test
public void test77() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "你好世界";
    String encoded = qpcodec.encode(plain);
    assertEquals("Unicode characters quoted-printable encoding test",
            plain, encoded);
    assertEquals("Unicode characters quoted-printable decoding test",
            plain, qpcodec.decode(encoded));
}
@Test
public void test78() throws Exception {
    QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
    String plain = "a".repeat(10000);
    String encoded = qpcodec.encode(plain);
    assertEquals("Long string quoted-printable encoding test",
            plain, encoded);
    assertEquals("Long string quoted-printable decoding test",
            plain, qpcodec.decode(encoded));
}
    @Test
    public void test79() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        Object result = qpcodec.decode((Object) null);
        assertEquals( "Decoding a null Object should return null", null, result);
    }
    @Test
    public void test80() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        try {
            Object dObj = new Double(3.0);
            qpcodec.decode( dObj );
            fail( "Trying to url encode a Double object should cause an exception.");
        } catch (DecoderException ee) {
            // Exception expected, test segment passes.
        }
    }
    @Test
    public void test81() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "1+1 =3D 2";
        byte[] plainBA = plain.getBytes("UTF-8");
        byte[] decodedBA = (byte[]) qpcodec.decode((Object) plainBA);
        String decoded = new String(decodedBA);
        assertEquals("Basic quoted-printable decoding test", 
            "1+1 = 2", decoded);
    }
    @Test
    public void test82() throws Exception {
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
    public void test83() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "= Hello there =\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Basic quoted-printable encoding test", 
            "=3D Hello there =3D=0D=0A", encoded);
        assertEquals("Basic quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test84() throws Exception {
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
    public void test86() {
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
    public void test87() throws Exception {
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
    public void test88() throws Exception {
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
    public void test89() throws Exception {
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
    public void test90() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="This is a example of a quoted-printable text file. This might contain sp=cial chars.";
        String expected = "This is a example of a quoted-printable text file. This might contain sp=3D=\r\ncial chars.";
        assertEquals(expected, qpcodec.encode(plain));

        plain ="This is a example of a quoted-printable text file. This might contain ta\tbs as well.";
        expected = "This is a example of a quoted-printable text file. This might contain ta=09=\r\nbs as well.";
        assertEquals(expected, qpcodec.encode(plain));
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
    public void test93() throws Exception {
        // whitespace, but does not need to be encoded
        String plain ="This is a example of a quoted=printable text file. There is no tt";
        String expected = "This is a example of a quoted=3Dprintable text file. There is no tt";

        assertEquals(expected, new QuotedPrintableCodec().encode(plain));
    }
    @Test
    public void test94() throws Exception {
        String plain = "Hello there!";
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("UnicodeBig");
        qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
        String encoded1 = qpcodec.encode(plain, "UnicodeBig");
        String encoded2 = qpcodec.encode(plain);
        assertEquals(encoded1, encoded2);
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
    public void test97() throws Exception {
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
    public void test98() throws Exception {
        String qpdata = "";
        String expected = "";

        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertEquals(qpdata, qpcodec.encode(expected));

        String decoded = qpcodec.decode(qpdata);
        assertEquals(qpdata, qpcodec.encode(decoded));
    }
    @Test
    public void test99() throws Exception {
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
    public void test100() throws Exception {
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
    public void test101() throws Exception {
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
    public void test103() throws Exception {
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
    public void test104() throws Exception {
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
    public void test105() {
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
    public void test106() throws Exception {
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
    public void test107() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        byte[] plain = null;
        byte[] encoded = qpcodec.encode(plain);
        assertEquals("Encoding a null string should return null", 
            null, encoded);
    }
    @Test
    public void test108() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        String plain ="This is a example of a quoted-printable text file. This might contain sp=cial chars.";
        String expected = "This is a example of a quoted-printable text file. This might contain sp=3D=\r\ncial chars.";
        assertEquals(expected, qpcodec.encode(plain));

        plain ="This is a example of a quoted-printable text file. This might contain ta\tbs as well.";
        expected = "This is a example of a quoted-printable text file. This might contain ta=09=\r\nbs as well.";
        assertEquals(expected, qpcodec.encode(plain));
    }
    @Test
    public void test109() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String plain = "=\r\n";
        String encoded = qpcodec.encode(plain);
        assertEquals("Unsafe chars quoted-printable encoding test", 
            "=3D=0D=0A", encoded);
        assertEquals("Unsafe chars quoted-printable decoding test", 
            plain, qpcodec.decode(encoded));
    }
    @Test
    public void test110() throws Exception {

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
    public void test111() throws Exception {
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        String test = null;
        String result = qpcodec.encode( test, "charset" );
        assertEquals("Result should be null", null, result);
    }
    @Test
    public void test112() throws Exception {
        // whitespace, but does not need to be encoded
        String plain ="This is a example of a quoted=printable text file. There is no tt";
        String expected = "This is a example of a quoted=3Dprintable text file. There is no tt";

        assertEquals(expected, new QuotedPrintableCodec().encode(plain));
    }
    @Test
    public void test113() throws Exception {
        String plain = "Hello there!";
        QuotedPrintableCodec qpcodec = new QuotedPrintableCodec("UnicodeBig");
        qpcodec.encode(plain); // To work around a weird quirk in Java 1.2.2
        String encoded1 = qpcodec.encode(plain, "UnicodeBig");
        String encoded2 = qpcodec.encode(plain);
        assertEquals(encoded1, encoded2);
    }
}