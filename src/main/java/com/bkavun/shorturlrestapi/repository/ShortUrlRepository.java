package com.bkavun.shorturlrestapi.repository;

import com.bkavun.shorturlrestapi.entity.ShortUrlEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ShortUrlRepository extends CrudRepository<ShortUrlEntity, String> {
    Optional<ShortUrlEntity> findByLongUrl(String longUrl);
}
