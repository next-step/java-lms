package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import javax.swing.ImageIcon;
import nextstep.courses.CannotRegisterException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class Session {

    private long id;
    private String title;

    private SessionDuration sessionDuration;
    private SessionType sessionType;
    private SessionState state;
    private Image image;

    private Course course;

    private Enrollment enrollment;

    public Session(long id, String title, SessionType sessionType, SessionState state, Image image,
            LocalDateTime startDate, LocalDateTime endDate, int studentCapacity, long fee) {
        this.id = id;
        this.title = title;
        this.sessionType = sessionType;
        this.state = state;
        this.image = image;
        this.sessionDuration = new SessionDuration(startDate, endDate);
        this.enrollment = new Enrollment(studentCapacity, fee);
    }

    public Session(long id, String title, SessionType sessionType, SessionState state, Image image,
            LocalDateTime startDate, LocalDateTime endDate) {
        this(id, title, sessionType, state, image, startDate, endDate, 0, 0);
    }

    public void toCourse(Course course) {
        this.course = course;
    }

    public void register(NsUser user) throws CannotRegisterException {
        isRegisteredAllowed();
        this.enrollment.enroll(user);
    }

    public void register(NsUser user, Payment payment) throws CannotRegisterException {
        isRegisteredAllowed();
        this.enrollment.enroll(user, payment);
    }

    public boolean hasId(Long id) {
        return this.id == id;
    }

    private void isRegisteredAllowed() throws CannotRegisterException {
        if (!state.isAllowed()) {
            throw new CannotRegisterException(String.format("해당 강의 상태 %s에서는 수강 신청을 할 수 없습니다.", state.getState()));
        }
    }
}
