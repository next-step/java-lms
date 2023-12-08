package nextstep.courses.domain.session;

import nextstep.courses.domain.image.Image;
import nextstep.courses.type.SessionStatus;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Session {

    private Long id;
    private String title;
    private SessionPeriod sessionPeriod;
    private SessionStatus status;
    private Enrolment enrolment;
    private Image image;
    private Long courseId;

    public Session(Long id, String title, SessionPeriod sessionPeriod, Enrolment enrolment, SessionStatus status, Long courseId) {
        this(id, title, sessionPeriod, enrolment, status, null, courseId);
    }

    public Session(String title, SessionPeriod sessionPeriod, Enrolment enrolment, SessionStatus status, Image image, Long courseId) {
        this(null, title, sessionPeriod, enrolment, status, image, courseId);
    }

    public Session(Long id, String title, SessionPeriod sessionPeriod, Enrolment enrolment, SessionStatus status, Image image, Long courseId) {
        this.id = id;
        this.title = title;
        this.sessionPeriod = sessionPeriod;
        this.enrolment = enrolment;
        this.status = status;
        this.image = image;
        this.courseId = courseId;
    }

    public String title() {
        return this.title;
    }

    public boolean isFree() {
        return enrolment.isFree();
    }

    public void addParticipant(int money, NsUser nsUser) {
        validateRecruiting();
        enrolment.addParticipant(money, nsUser);
    }

    public void validateRecruiting() {
        if (status != SessionStatus.RECRUIT) {
            throw new IllegalArgumentException("모집중인 강의가 아닙니다.");
        }
    }

    public int nowParticipants() {
        return enrolment.nowParticipants();
    }

    public LocalDateTime startDateTime() {
        return sessionPeriod.startDate();
    }

    public LocalDateTime endDateTime() {
        return sessionPeriod.startDate();
    }

    public String status() {
        return status.toString();
    }

    public int price() {
        return enrolment.price();
    }

    public Long courseId() {
        return courseId;
    }

    public int maxParticipants() {
        return enrolment.maxParticipants();
    }

    public Image image() {
        return image;
    }

    public void mappadByImage(Image image) {
        this.image = image;
    }
}
