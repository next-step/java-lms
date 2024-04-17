package nextstep.courses.error.exception;

import java.text.MessageFormat;
import nextstep.courses.domain.session.RegistrationCount;

public class MaxRegistrationCountNotZero extends RuntimeException {

    public MaxRegistrationCountNotZero(RegistrationCount registrationCount) {
        super(MessageFormat.format("{0} 입력값: {1}", "최대 등록수는 0일수 없습니다",
            registrationCount.getValue()));
    }
}
