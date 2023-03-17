package com.bkavun.shorturlrestapi.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "short_url")
@Data
@NoArgsConstructor
public class ShortUrlEntity {
    @Id
    @GenericGenerator(name = "code", strategy = "com.bkavun.shorturlrestapi.utils.ShortUrlCodeGenerator")
    @GeneratedValue(generator = "code")
    @Column(name = "code")
    private String code;

    @Column(name = "long_url", columnDefinition = "TEXT")
    private String longUrl;

    public ShortUrlEntity(String longUrl) {
        this.longUrl = longUrl;
    }
}
