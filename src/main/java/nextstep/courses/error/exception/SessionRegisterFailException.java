package nextstep.courses.error.exception;

import java.text.MessageFormat;

public class SessionRegisterFailException extends RuntimeException {

    public SessionRegisterFailException() {
        super(MessageFormat.format("{0}", "강의 등록에 실패하였습니다"));
    }
}
