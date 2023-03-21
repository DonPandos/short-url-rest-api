package com.bkavun.shorturlrestapi.service.impl;

import com.bkavun.shorturlrestapi.dto.LongUrlDTO;
import com.bkavun.shorturlrestapi.dto.ShortUrlDTO;
import com.bkavun.shorturlrestapi.exception.InvalidUrlException;
import com.bkavun.shorturlrestapi.service.ShortUrlService;
import com.bkavun.shorturlrestapi.utils.UrlCompressor;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

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

        return new ShortUrlDTO(URL_PREFIX + UrlCompressor.gzipCompress(longUrl));
    }

    @Override
    public LongUrlDTO getLongUrl(String shortUrl) {
        if (!shortUrl.startsWith(URL_PREFIX)) throw new InvalidUrlException();

        String code = shortUrl.replace(URL_PREFIX, "");

        return new LongUrlDTO(UrlCompressor.gzipDecompress(code.getBytes(StandardCharsets.UTF_8)));
    }

}
