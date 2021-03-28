package com.jm.langx.util.io;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Description
 * @Create by yangjm
 * @CreateTime 21.3.28 17:04
 */
public class InputStreams {



    public static byte[] InputStream2ByteArray( InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int len = 0;
        byte[] b = new byte[2048];
        while ((len = is.read(b, 0, b.length)) != -1) {
            baos.write(b, 0, len);
        }
        return baos.toByteArray();
    }
}
