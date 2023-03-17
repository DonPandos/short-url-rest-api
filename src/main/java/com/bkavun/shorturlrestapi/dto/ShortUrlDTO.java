package com.bkavun.shorturlrestapi.dto;

import com.bkavun.shorturlrestapi.constant.CommonConst;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShortUrlDTO {
    @ApiModelProperty(
            name = "shortUrl",
            value = "Short url",
            example = CommonConst.SHORT_URL_EXAMPLE
    )
    private String shortUrl;
}
