package nextstep.courses.exception;

public class OverMaxStudentsException extends RuntimeException {
    public OverMaxStudentsException(int maxStudents) {
        super("최대 수강 인원 " + maxStudents + "에 도달하여 수강 신청할 수 없습니다.");
    }
}
