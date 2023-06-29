package nextstep.courses.domain;


import nextstep.users.domain.NsUser;

public class Session {
    private final Long id;
    private final String image;
    private final LectureType lectureType;
    private final SessionDate sessionDate;
    private final SessionInfo sessionInfo;

    public Session(Long id, String image, LectureType lectureType, SessionDate sessionDate, SessionInfo sessionInfo) {
        this.id = id;
        this.image = image;
        this.lectureType = lectureType;
        this.sessionDate = sessionDate;
        this.sessionInfo = sessionInfo;
    }

    public Long getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public LectureType getLectureType() {
        return lectureType;
    }

    public void register(NsUser user) {
        sessionInfo.register(user);
    }
}