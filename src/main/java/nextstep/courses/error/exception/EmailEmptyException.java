package nextstep.courses.error.exception;

import java.text.MessageFormat;

public class EmailEmptyException extends RuntimeException {

    public EmailEmptyException(String email) {
        super(MessageFormat.format("{0} 입력값: {1}", "이메일 이름이 입력되지 않았습니다",
            email));
    }
}
