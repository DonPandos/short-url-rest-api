package com.bkavun.shorturlrestapi.controller;

import com.bkavun.shorturlrestapi.constant.CommonConst;
import com.bkavun.shorturlrestapi.dto.LongUrlDTO;
import com.bkavun.shorturlrestapi.dto.ShortUrlDTO;
import com.bkavun.shorturlrestapi.service.ShortUrlService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/short-url")
public class ShortUrlController {

    private final ShortUrlService shortUrlService;

    public ShortUrlController(ShortUrlService shortUrlService) {
        this.shortUrlService = shortUrlService;
    }

    @PostMapping
    @ApiOperation(value = "Generate short url based on provided url", notes = "Returns short url as per long url")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created"),
            @ApiResponse(code = 400, message = CommonConst.INCORRECT_URL_MESSAGE)
    })
    public ResponseEntity<ShortUrlDTO> createShortUrl(@RequestBody LongUrlDTO dto) {
        return ResponseEntity.ok(shortUrlService.createShortUrl(dto));
    }

    @GetMapping
    @ApiOperation(value = "Get long url by short url", notes = "Returns long url as per short url")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 400, message = CommonConst.INCORRECT_URL_MESSAGE)
    })
    public ResponseEntity<LongUrlDTO> getLongUrl(@RequestParam
            @ApiParam(name = "shortUrl", value = "Short Url", example = CommonConst.SHORT_URL_EXAMPLE) String shortUrl) {
        return ResponseEntity.ok(shortUrlService.getLongUrl(shortUrl));
    }

}
