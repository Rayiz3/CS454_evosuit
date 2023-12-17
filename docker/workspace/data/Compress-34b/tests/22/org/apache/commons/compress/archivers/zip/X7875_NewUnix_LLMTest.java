package org.apache.commons.compress.archivers.zip;
import org.junit.Before;
import org.junit.Test;
import java.io.File;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.zip.ZipException;
import static org.apache.commons.compress.AbstractTestCase.getFile;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class X7875_NewUnix_LLMTest {
public void test0() {
    X7875_NewUnix xf = new X7875_NewUnix();
    xf.setUID(new BigInteger("2"));
    xf.setGID(new BigInteger("3"));
    byte[] expected = {1, 1, 2, 1, 3};

    assertArrayEquals(expected, xf.getLocalFileDataData());

    xf.setUID(new BigInteger("100"));
    xf.setGID(new BigInteger("200"));
    expected = {1, 1, 100, 1, 200};

    assertArrayEquals(expected, xf.getLocalFileDataData());

    xf.setUID(new BigInteger("65535"));
    xf.setGID(new BigInteger("65535"));
    expected = {1, 1, -1, 1, -1};

    assertArrayEquals(expected, xf.getLocalFileDataData());

    xf.setUID(new BigInteger("1000000"));
    xf.setGID(new BigInteger("2000000"));
    expected = {1, 3, -109, -104, 1, 3, -95, -64};

    assertArrayEquals(expected, xf.getLocalFileDataData());

    xf.setUID(new BigInteger("4294967295"));
    xf.setGID(new BigInteger("4294967294"));
    expected = {1, 4, -1, -1, -1, -1, 4, -2, -1, -1, -1};

    assertArrayEquals(expected, xf.getLocalFileDataData());
}
    public byte[] getCentralDirectoryData() {
        return new byte[0];
    }
    @Test
    public void test1() throws Exception {
        byte[] data = { -1, -1, -1, 1, 127, 0, 0, 1, 127, 0, 0, 1 };
        try {
            parseFromLocalFileData(data, 0, data.length);
            fail("Expected ZipException to be thrown");
        } catch (ZipException e) {
            assertEquals("Invalid version: -1", e.getMessage());
        }
    }
    @Test
    public void test2() throws Exception {
        byte[] data = { 1, -1, 0, 1, 127, 0, 0, 1, 127, 0, 0, 1 };
        try {
            parseFromLocalFileData(data, 0, data.length);
            fail("Expected ZipException to be thrown");
        } catch (ZipException e) {
            assertEquals("Invalid uidSize: -1", e.getMessage());
        }
    }
    @Test
    public void test3() throws Exception {
        byte[] data = { 1, 1, 0, -1, 127, 0, 0, 1, 127, 0, 0, 1 };
        try {
            parseFromLocalFileData(data, 0, data.length);
            fail("Expected ZipException to be thrown");
        } catch (ZipException e) {
            assertEquals("Invalid gidSize: -1", e.getMessage());
        }
    }
    @Test
    public void test4() throws Exception {
        byte[] data = { 1, 5, 0, 0, 0, 0, 1, 5, 1, 0 };
        try {
            parseFromLocalFileData(data, 0, data.length);
            fail("Expected ZipException to be thrown");
        } catch (ZipException e) {
            assertEquals("Data size mismatch: expected 5 bytes for uid but found 4 bytes", e.getMessage());
        }
    }
    @Test
    public void test5() throws Exception {
        byte[] data = { 1, 1, 1, 1, 1 };
        try {
            parseFromLocalFileData(data, 0, data.length);
            fail("Expected ZipException to be thrown");
        } catch (ZipException e) {
            assertEquals("Data size mismatch: expected 1 byte for gid but found 0 bytes", e.getMessage());
        }
    }
    @Test
    public void test6() throws Exception {
        byte[] buffer = {};
        int offset = 0;
        int length = 0;

        try {
            parseFromCentralDirectoryData(buffer, offset, length);
            // If the buffer is empty, the method should throw ZipException.
            fail("Expected ZipException not thrown");
        } catch (ZipException e) {
            // Expected ZipException to be thrown
        }
    }
    @Test
    public void test7() throws Exception {
        byte[] buffer = {0, 1, 2, 3, 4};
        int offset = -1;
        int length = buffer.length;

        try {
            parseFromCentralDirectoryData(buffer, offset, length);
            // If the offset is negative, the method should throw ZipException.
            fail("Expected ZipException not thrown");
        } catch (ZipException e) {
            // Expected ZipException to be thrown
        }
    }
    @Test
    public void test8() throws Exception {
        byte[] buffer = {0, 1, 2, 3, 4};
        int offset = 0;
        int length = -1;

        try {
            parseFromCentralDirectoryData(buffer, offset, length);
            // If the length is negative, the method should throw ZipException.
            fail("Expected ZipException not thrown");
        } catch (ZipException e) {
            // Expected ZipException to be thrown
        }
    }
    @Test
    public void test9() throws Exception {
        byte[] buffer = {0, 1, 2, 3, 4};
        int offset = 3;
        int length = 5;

        try {
            parseFromCentralDirectoryData(buffer, offset, length);
            // If the offset + length is greater than the buffer length, the method should throw ZipException.
            fail("Expected ZipException not thrown");
        } catch (ZipException e) {
            // Expected ZipException to be thrown
        }
    }
    @Test
    public void test10() {
        uid = 0;
        gid = 0;
        reset();
        assertEquals(1000, uid);
        assertEquals(1000, gid);
    }
    @Test
    public void test11() {
        uid = 2000;
        gid = 2000;
        reset();
        assertEquals(1000, uid);
        assertEquals(1000, gid);
    }
    @Test
    public void test12() {
        uid = -100;
        gid = -100;
        reset();
        assertEquals(1000, uid);
        assertEquals(1000, gid);
    }
    @Test
    public void test13() throws Exception {
        xf.setUID(23456);
        assertTrue(xf.toString().endsWith("UID=23456 GID=" + gid));
    }
    @Test
    public void test14() throws Exception {
        xf.setGID(56789);
        assertTrue(xf.toString().endsWith("UID=" + uid + " GID=56789"));
    }
    @Test
    public void test15() throws Exception {
        Object differentObject = new Object();
        assertFalse(xf.equals(differentObject));
    }
    @Test
    public void test16() throws Exception {
        xf.setUID(98765);
        xf.setGID(43210);
        assertFalse(xf.equals(xf.clone()));
    }
@Test
public void test17() throws Exception {
    Object obj = new Object();
    Object clonedObj = obj.clone();

    assertFalse(obj.equals(clonedObj));
}
@Test
public void test18() throws Exception {
    Object obj = new Object();
    Object clonedObj = obj.clone();

    assertNotEquals(obj.hashCode(), clonedObj.hashCode());
}
@Test
public void test19() throws Exception {
    // Create an instance of the class being cloned
    MyClass myObj = new MyClass();
    myObj.setUID(12345);

    // Clone myObj
    MyClass clonedObj = (MyClass) myObj.clone();

    assertNotEquals(myObj.getUID(), clonedObj.getUID());
}
    @Test
    public void test20() {
        X7875_NewUnix xf1 = new X7875_NewUnix(1, "uid", "gid");
        X7875_NewUnix xf2 = new X7875_NewUnix(2, "uid", "gid");
        assertFalse(xf1.equals(xf2));
    }
    @Test
    public void test21() {
        X7875_NewUnix xf1 = new X7875_NewUnix(1, "uid1", "gid");
        X7875_NewUnix xf2 = new X7875_NewUnix(1, "uid2", "gid");
        assertFalse(xf1.equals(xf2));
    }
    @Test
    public void test22() {
        X7875_NewUnix xf1 = new X7875_NewUnix(1, "uid", "gid1");
        X7875_NewUnix xf2 = new X7875_NewUnix(1, "uid", "gid2");
        assertFalse(xf1.equals(xf2));
    }
    @Test
    public void test23() {
        Object obj = new Object();
        X7875_NewUnix xf = new X7875_NewUnix(1, "uid", "gid");
        assertFalse(xf.equals(obj));
    }
    @Test
    public void test24() throws Exception {
        File archive = getFile("COMPRESS-211_uid_gid_zip_test.zip");
        ZipFile zf = null;

        try {
            zf = new ZipFile(archive);
            Enumeration<ZipArchiveEntry> en = zf.getEntries();

            // We expect EVERY entry of this zip file (dir & file) to
            // contain extra field 0x7875.
            while (en.hasMoreElements()) {

                ZipArchiveEntry zae = en.nextElement();
                String name = zae.getName();
                X7875_NewUnix xf = (X7875_NewUnix) zae.getExtraField(X7875);

                // The directory entry in the test zip file is uid/gid 1000.
                long expected = 1000;
                if (name.contains("uid555_gid555")) {
                    expected = 555;
                } else if (name.contains("uid5555_gid5555")) {
                    expected = 5555;
                } else if (name.contains("uid55555_gid55555")) {
                    expected = 55555;
                } else if (name.contains("uid555555_gid555555")) {
                    expected = 555555;
                } else if (name.contains("min_unix")) {
                    expected = 0;
                } else if (name.contains("max_unix")) {
                    // 2^32-2 was the biggest UID/GID I could create on my linux!
                    // (December 2012, linux kernel 3.4)
                    expected = 0x100000000L - 2;
                }
                assertEquals(expected, xf.getUID());
                assertEquals(expected, xf.getGID());
            }
        } finally {
            if (zf != null) {
                zf.close();
            }
        }
    }
    @Test
    public void test25() throws Exception {
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertTrue(xf.equals(o));
        xf.setUID(12345);
        assertFalse(xf.equals(o));
    }
    @Test
    public void test26() throws Exception {
        File archive = getFile("COMPRESS-211_uid_gid_zip_test.zip");
        ZipFile zf = null;

        try {
            zf = new ZipFile(archive);
            Enumeration<ZipArchiveEntry> en = zf.getEntries();

            // We expect EVERY entry of this zip file (dir & file) to
            // contain extra field 0x7875.
            while (en.hasMoreElements()) {

                ZipArchiveEntry zae = en.nextElement();
                String name = zae.getName();
                X7875_NewUnix xf = (X7875_NewUnix) zae.getExtraField(X7875);

                // The directory entry in the test zip file is uid/gid 1000.
                long expected = 1000;
                
                if (name.contains("min_unix")) {
                    expected = 1;
                } 
                assertEquals(expected, xf.getUID());
                assertEquals(expected, xf.getGID());
            }
        } finally {
            if (zf != null) {
                zf.close();
            }
        }
    }
    @Test
    public void test27() throws Exception {
        File archive = getFile("COMPRESS-211_uid_gid_zip_test.zip");
        ZipFile zf = null;

        try {
            zf = new ZipFile(archive);
            Enumeration<ZipArchiveEntry> en = zf.getEntries();

            // We expect EVERY entry of this zip file (dir & file) to
            // contain extra field 0x7875.
            while (en.hasMoreElements()) {

                ZipArchiveEntry zae = en.nextElement();
                String name = zae.getName();
                X7875_NewUnix xf = (X7875_NewUnix) zae.getExtraField(X7875);

                // The directory entry in the test zip file is uid/gid 1000.
                long expected = 1000;
                
                if (name.contains("max_unix")) {
                    expected = 0x100000000L - 1;
                } 
                assertEquals(expected, xf.getUID());
                assertEquals(expected, xf.getGID());
            }
        } finally {
            if (zf != null) {
                zf.close();
            }
        }
    }
}

    @Test
    public void test28() {
    }

    @Test
    public void test29() {
    }

    @Test
    public void test30() {
    }

    @Test
    public void test31() {
    }

    @Test
    public void test32() {
    }

}