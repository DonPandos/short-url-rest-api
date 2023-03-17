package com.bkavun.shorturlrestapi.exception;

import com.bkavun.shorturlrestapi.constant.CommonConst;
import com.bkavun.shorturlrestapi.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdviceController {

    @ExceptionHandler(ShortUrlNotFoundException.class)
    public ResponseEntity<ErrorDTO> shortUrlNotFoundException() {
        return new ResponseEntity<>(
                new ErrorDTO(CommonConst.SHORT_URL_NOT_FOUND_MESSAGE),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(InvalidUrlException.class)
    public ResponseEntity<ErrorDTO> incorrectUrlException() {
        return new ResponseEntity<>(
                new ErrorDTO(CommonConst.INCORRECT_URL_MESSAGE),
                HttpStatus.BAD_REQUEST
        );
    }
}
