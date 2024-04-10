package nextstep.sessions.domain;

import nextstep.sessions.exception.CannotEnrollException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUsers;

import java.time.LocalDateTime;

public class Session {
    private final Long id;

    private final String title;

    private final Period period;

    private final CoverImage coverImage;

    private final SessionStatus sessionStatus;

    private final SessionType sessionType;

    private final NsUsers students;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;

    public static Session freeSession(Long id, String title, Period period, CoverImage coverImage, SessionStatus sessionStatus, NsUsers students, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new Session(id, title, period, coverImage, sessionStatus, new FreeSession(), students, createdAt, updatedAt);
    }

    public Session(Long id, String title, Period period, CoverImage coverImage, SessionStatus sessionStatus, SessionType sessionType, NsUsers students, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.period = period;
        this.coverImage = coverImage;
        this.sessionStatus = sessionStatus;
        this.sessionType = sessionType;
        this.students = students;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void assertCanEnroll(NsUser requestUser, LocalDateTime requestDatetime) {
        if (!sessionStatus.canRecruit() || !period.isAfterStartDate(requestDatetime.toLocalDate())) {
            throw new CannotEnrollException("현재 모집중인 강의가 아닙니다.");
        }

        if (students.contains(requestUser)) {
            throw new CannotEnrollException("이미 신청한 강의입니다.");
        }

        if (sessionType.isFull(students.size())) {
            throw new CannotEnrollException("현재 수강 인원이 가득 찼습니다.");
        }
    }


}
