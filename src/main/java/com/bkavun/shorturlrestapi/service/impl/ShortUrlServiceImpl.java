package com.bkavun.shorturlrestapi.service.impl;

import com.bkavun.shorturlrestapi.dto.LongUrlDTO;
import com.bkavun.shorturlrestapi.dto.ShortUrlDTO;
import com.bkavun.shorturlrestapi.exception.CompressingException;
import com.bkavun.shorturlrestapi.exception.InvalidUrlException;
import com.bkavun.shorturlrestapi.service.ShortUrlService;
import io.seruco.encoding.base62.Base62;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

@Service
public class ShortUrlServiceImpl implements ShortUrlService {

    private final String URL_PREFIX = "https://foobar.com/";

    @Override
    public ShortUrlDTO createShortUrl(LongUrlDTO dto) {
        String longUrl = dto.getLongUrl();

        try {
            new URL(longUrl).toURI();
        } catch (URISyntaxException | MalformedURLException e) {
            throw new InvalidUrlException();
        }

        return new ShortUrlDTO(URL_PREFIX + gzipCompress(longUrl));
    }

    @Override
    public LongUrlDTO getLongUrl(String shortUrl) {
        if (!shortUrl.startsWith(URL_PREFIX)) throw new InvalidUrlException();

        String code = shortUrl.replace(URL_PREFIX, "");

        return new LongUrlDTO(gzipDecompress(code.getBytes(StandardCharsets.UTF_8)));
    }

    private String gzipCompress(String data) {
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

    private String gzipDecompress(byte[] compressed) {
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
