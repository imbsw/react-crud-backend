package com.example.vms.config.exception;

import com.example.vms.util.ResponseCode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Slf4j
@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class CommonException extends RuntimeException {

    private ResponseCode responseCode;

    public CommonException(ResponseCode responseCode, Throwable e) {
        super(responseCode.getDescription(), e);
        this.responseCode = responseCode;
        log.error(responseCode.getDescription(), this);
    }

    public CommonException(ResponseCode responseCode) {
        super(responseCode.getDescription());
        this.responseCode = responseCode;
        log.error(responseCode.getDescription(), this);
    }

    public CommonException(String description) {
        super(description);
        log.error(description, this);
    }
}
