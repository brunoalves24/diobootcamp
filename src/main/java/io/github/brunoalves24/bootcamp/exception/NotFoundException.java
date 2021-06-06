package io.github.brunoalves24.bootcamp.exception;

import io.github.brunoalves24.bootcamp.util.MessageUtils;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super(MessageUtils.NO_RECORDS_FOUND);
    }
}
