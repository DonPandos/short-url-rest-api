package com.bkavun.shorturlrestapi.utils;

import com.bkavun.shorturlrestapi.exception.CompressingException;
import io.seruco.encoding.base62.Base62;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class UrlCompressor {

    public static String gzipCompress(String data) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            GZIPOutputStream gzip = new GZIPOutputStream(out);
            gzip.write(data.getBytes(StandardCharsets.UTF_8));
            gzip.close();
            byte[] compressed = out.toByteArray();
            out.close();
            return new String(Base62.createInstance().encode(compressed));
        } catch (IOException e) {
            throw new CompressingException();
        }
    }

    public static String gzipDecompress(byte[] compressed) {
        compressed = Base62.createInstance().decode(compressed);
        try {
            GZIPInputStream gzip = new GZIPInputStream(new ByteArrayInputStream(compressed));
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = gzip.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
            gzip.close();
            out.close();

            return out.toString(StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new CompressingException();
        }
    }
}
