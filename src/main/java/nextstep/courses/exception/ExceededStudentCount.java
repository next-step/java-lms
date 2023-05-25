package nextstep.courses.exception;

public class ExceededStudentCount extends IllegalArgumentException{
    public ExceededStudentCount() {
        super("수강 가능한 인원을 초과하였습니다");
    }
}
