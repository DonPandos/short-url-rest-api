package com.bkavun.shorturlrestapi.service;

import com.bkavun.shorturlrestapi.dto.LongUrlDTO;
import com.bkavun.shorturlrestapi.dto.ShortUrlDTO;

public interface ShortUrlService {
    ShortUrlDTO createShortUrl(LongUrlDTO dto);
    LongUrlDTO getLongUrl(String code);
}