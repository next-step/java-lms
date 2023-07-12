package nextstep.courses.domain;

public class SessionInfoBuilder {
    private LectureStatus lectureStatus = LectureStatus.PREPARING;
    private Students students = new Students();
    private int maxUser = 100;

    public SessionInfoBuilder lectureStatus(LectureStatus lectureStatus) {
        this.lectureStatus = lectureStatus;
        return this;
    }

    public SessionInfoBuilder users(Students students) {
        this.students = students;
        return this;
    }

    public SessionInfoBuilder maxUser(int maxUser) {
        this.maxUser = maxUser;
        return this;
    }

    public SessionInfo build() {
        return new SessionInfo(
                lectureStatus,
                students,
                maxUser
        );
    }

    public static SessionInfoBuilder sessionInfo() {
        return new SessionInfoBuilder();
    }
}
