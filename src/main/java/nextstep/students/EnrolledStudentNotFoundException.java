package nextstep.students;

public class EnrolledStudentNotFoundException extends RuntimeException {

    public EnrolledStudentNotFoundException() {
        super("등록된 학생을 찾을 수 없습니다.");
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

}
