package nextstep.courses.error.exception;

import java.text.MessageFormat;

public class MaxRegistrationCountNotZero extends RuntimeException {

    public MaxRegistrationCountNotZero(int maxRegistrationCount) {
        super(MessageFormat.format("{0} 입력값: {1}", "최대 등록수는 0일수 없습니다",
            maxRegistrationCount));
    }
}
