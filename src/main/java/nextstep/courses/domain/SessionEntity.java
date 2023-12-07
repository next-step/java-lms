package nextstep.courses.domain;

import nextstep.courses.domain.*;
import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.util.List;

public class SessionEntity {
    private Long id;

    private String title;

    private Long creatorId;

    private String coverImage;

    private ChargeStatus status;

    private int price;

    private SessionStatus sessionStatus;

    private int capacity;

    private LocalDate startDate;

    private LocalDate endDate;

    public SessionEntity() {
    }

    public SessionEntity(String title, Long creatorId, String coverImage, ChargeStatus status, int price, SessionStatus sessionStatus, int capacity, LocalDate startDate, LocalDate endDate) {
        this.title = title;
        this.creatorId = creatorId;
        this.coverImage = coverImage;
        this.status = status;
        this.price = price;
        this.sessionStatus = sessionStatus;
        this.capacity = capacity;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Enrollment enrollment(List<Student> students) {
        return new Enrollment(capacity, sessionStatus, students);
    }

    public SessionPeriod sessionPeriod() {
        return new SessionPeriod(startDate, endDate);
    }
}
