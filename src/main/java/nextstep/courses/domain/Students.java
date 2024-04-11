package nextstep.courses.domain;

public class Students {

    private final Integer maxStudents;

    public Students(Integer maxStudents, SessionType type) {
        validateMaxStudents(maxStudents, type);
        this.maxStudents = maxStudents;
    }

    private void validateMaxStudents(Integer maxStudents, SessionType type) {
        if (type == SessionType.PAID && maxStudents <= 0) {
            throw new IllegalArgumentException("수강 가능한 인원은 0명 이상이어야 합니다.");
        }

        if (type == SessionType.FREE && maxStudents != null) {
            throw new IllegalArgumentException("무료 강의는 최대 수강 인원 제한이 없습니다.");
        }
    }

    public int getMaxStudents() {
        return maxStudents;
    }
}
