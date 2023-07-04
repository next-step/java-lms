package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class SessionInfoBuilder {
    private LectureStatus lectureStatus = LectureStatus.PREPARING;
    private List<Student> users = new ArrayList<>();
    private int maxUser = 100;

    public SessionInfoBuilder lectureStatus(LectureStatus lectureStatus) {
        this.lectureStatus = lectureStatus;
        return this;
    }

    public SessionInfoBuilder users(List<Student> users) {
        this.users = users;
        return this;
    }

    public SessionInfoBuilder maxUser(int maxUser) {
        this.maxUser = maxUser;
        return this;
    }

    public SessionInfo build() {
        return new SessionInfo(
                lectureStatus,
                users,
                maxUser
        );
    }

    public static SessionInfoBuilder sessionInfo(){
        return new SessionInfoBuilder();
    }
}
