package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import nextstep.courses.CannotRegisterException;
import nextstep.users.domain.NsUser;

public class Session {

    private long id;
    private String title;
    private SessionType sessionType;
    private SessionState state;
    private String image;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private Course course;

    private List<NsUser> users;

    private int studentCapacity;

    public Session(long id, String title, SessionType sessionType, SessionState state, String image,
            LocalDateTime startDate, LocalDateTime endDate, int studentCapacity) {
        this.id = id;
        this.title = title;
        this.sessionType = sessionType;
        this.state = state;
        this.image = image;
        this.startDate = startDate;
        this.endDate = endDate;
        this.studentCapacity = studentCapacity;
    }

    public Session(long id, String title, SessionType sessionType, SessionState state, String image,
            LocalDateTime startDate, LocalDateTime endDate) {
        this(id, title, sessionType, state, image, startDate, endDate, 0);
    }

    public void toCourse(Course course) {
        this.course = course;
    }

    public void register(NsUser user) throws CannotRegisterException {
        isRegisteredAllowed();
        if (Objects.isNull(users)) {
            users = new ArrayList<>();
        }
        users.add(user);
    }

    private void isRegisteredAllowed() throws CannotRegisterException {
        if (!state.isAllowed()) {
            throw new CannotRegisterException(String.format("해당 강의 상태 %s에서는 수강 신청을 할 수 없습니다.", state.getState()));
        }
    }
}
