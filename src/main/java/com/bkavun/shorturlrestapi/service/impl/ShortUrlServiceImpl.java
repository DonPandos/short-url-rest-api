package com.bkavun.shorturlrestapi.service.impl;

import com.bkavun.shorturlrestapi.dto.LongUrlDTO;
import com.bkavun.shorturlrestapi.dto.ShortUrlDTO;
import com.bkavun.shorturlrestapi.entity.ShortUrlEntity;
import com.bkavun.shorturlrestapi.exception.InvalidUrlException;
import com.bkavun.shorturlrestapi.exception.ShortUrlNotFoundException;
import com.bkavun.shorturlrestapi.repository.ShortUrlRepository;
import com.bkavun.shorturlrestapi.service.ShortUrlService;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

@Service
public class ShortUrlServiceImpl implements ShortUrlService {

    private final ShortUrlRepository shortUrlRepository;
    private final String URL_PREFIX = "https://foobar.com/";

    public ShortUrlServiceImpl(ShortUrlRepository shortUrlRepository) {
        this.shortUrlRepository = shortUrlRepository;
    }

    @Override
    public ShortUrlDTO createShortUrl(LongUrlDTO dto) {
        String longUrl = dto.getLongUrl();

        try {
            new URL(longUrl).toURI();
        } catch (URISyntaxException | MalformedURLException e) {
            throw new InvalidUrlException();
        }

        ShortUrlEntity entity = shortUrlRepository.findByLongUrl(longUrl)
                .orElse(new ShortUrlEntity(longUrl));

        shortUrlRepository.save(entity);

        return new ShortUrlDTO(URL_PREFIX + entity.getCode());
    }

    @Override
    public LongUrlDTO getLongUrl(String shortUrl) {
        if (!shortUrl.startsWith(URL_PREFIX)) throw new InvalidUrlException();

        String code = shortUrl.replace(URL_PREFIX, "");

        ShortUrlEntity entity = shortUrlRepository.findById(code)
                .orElseThrow(ShortUrlNotFoundException::new);

        return new LongUrlDTO(entity.getLongUrl());
    }
}
