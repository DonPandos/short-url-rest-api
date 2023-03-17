package com.bkavun.shorturlrestapi.dto;

import com.bkavun.shorturlrestapi.constant.CommonConst;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LongUrlDTO {
    @ApiModelProperty(
            name = "longUrl",
            value = "Long url",
            example = CommonConst.LONG_URL_EXAMPLE
    )
    private String longUrl;
}
