package nextstep.courses.domain;

import java.time.LocalDate;

public class SessionBuilder {
    private Long id = 1L;
    private String image = "test.jpg";
    private LectureType lectureType = LectureType.FREE;
    private SessionDate sessionDate = new SessionDate(LocalDate.now(), LocalDate.now().plusDays(30));
    private SessionInfo sessionInfo = SessionInfoBuilder.sessionInfo().build();

    public SessionBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public SessionBuilder image(String image) {
        this.image = image;
        return this;
    }

    public SessionBuilder lectureType(LectureType lectureType) {
        this.lectureType = lectureType;
        return this;
    }

    public SessionBuilder sessionDate(SessionDate sessionDate) {
        this.sessionDate = sessionDate;
        return this;
    }

    public SessionBuilder sessionInfo(SessionInfo sessionInfo) {
        this.sessionInfo = sessionInfo;
        return this;
    }

    public static SessionBuilder session() {
        return new SessionBuilder();
    }

    public Session build() {
        return new Session(
                id, image, lectureType, sessionDate, sessionInfo
        );
    }
}
