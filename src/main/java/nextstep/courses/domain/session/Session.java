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
    private Price price;
    private Image image;
    private Long course;

    public Session(Long id, String title, SessionPeriod sessionPeriod, Price price, SessionStatus status, Long course) {
        this(id, title, sessionPeriod, price, status, null, course);
    }

    public Session(String title, SessionPeriod sessionPeriod, Price price, SessionStatus status, Image image, Long course) {
        this(null, title, sessionPeriod, price, status, image, course);
    }

    public Session(Long id, String title, SessionPeriod sessionPeriod, Price price, SessionStatus status, Image image, Long course) {
        this.id = id;
        this.title = title;
        this.sessionPeriod = sessionPeriod;
        this.price = price;
        this.status = status;
        this.image = image;
        this.course = course;
    }

    public String title() {
        return this.title;
    }

    public boolean isFree() {
        return price.isFree();
    }

    public void addParticipant(int money, NsUser nsUser) {
        validateRecruiting();
        price.addParticipant(money, nsUser);
    }

    public void validateRecruiting() {
        if (status != SessionStatus.RECRUIT) {
            throw new IllegalArgumentException("모집중인 강의가 아닙니다.");
        }
    }

    public int nowParticipants() {
        return price.nowParticipants();
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

    public int money() {
        return price.money();
    }

    public Long course() {
        return course;
    }

    public int maxParticipants() {
        return price.maxParticipants();
    }

    public Image image() {
        return image;
    }
}
