package com.bkavun.shorturlrestapi.exception;

import com.bkavun.shorturlrestapi.constant.CommonConst;
import com.bkavun.shorturlrestapi.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdviceController {

    @ExceptionHandler(InvalidUrlException.class)
    public ResponseEntity<ErrorDTO> incorrectUrlException() {
        return new ResponseEntity<>(
                new ErrorDTO(CommonConst.INCORRECT_URL_MESSAGE),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(CompressingException.class)
    public ResponseEntity<ErrorDTO> compressingException() {
        return new ResponseEntity<>(
                new ErrorDTO(CommonConst.COMPRESSING_EXCEPTION_MESSAGE),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
