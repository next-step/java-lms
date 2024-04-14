package nextstep.courses.domain.exception;

public class NotRecruitException extends RuntimeException {

    private static final String ERROR_MESSAGE = "현재 모집중인 강의가 아닙니다.";

    public NotRecruitException() {
        super(ERROR_MESSAGE);
    }

}
