package nextstep.courses.exception;

public class DuplicateStudentsException extends RuntimeException {
    public DuplicateStudentsException(String userId) {
        super(userId + "님은 이미 수강신청한 회원입니다.");
    }
}
