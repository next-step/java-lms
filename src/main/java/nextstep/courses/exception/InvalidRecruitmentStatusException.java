package nextstep.courses.exception;

public class InvalidRecruitmentStatusException extends RuntimeException {

    public InvalidRecruitmentStatusException(String code) {
        super("존재하지 않는 강의 모집 상태입니다." + code);
    }
}
