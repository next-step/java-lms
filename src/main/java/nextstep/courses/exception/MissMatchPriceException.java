package nextstep.courses.exception;

import static nextstep.courses.message.ExceptionMessage.MISS_MATCH_PRICE_EXCEPTION;

public class MissMatchPriceException extends RuntimeException {

    private static final String MESSAGE = MISS_MATCH_PRICE_EXCEPTION.getMessage();

    public MissMatchPriceException() {
        super(MESSAGE);
    }
}
