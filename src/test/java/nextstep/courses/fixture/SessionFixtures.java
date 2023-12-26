package nextstep.courses.fixture;

import nextstep.courses.domain.course.session.*;
import nextstep.courses.domain.course.session.apply.Applies;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class SessionFixtures {
    public static final LocalDate DATE_2023_12_5 = LocalDate.of(2023, 12, 5);
    public static final LocalDate DATE_2023_12_6 = LocalDate.of(2023, 12, 6);
    public static final LocalDate DATE_2023_12_10 = LocalDate.of(2023, 12, 10);
    public static final LocalDate DATE_2023_12_12 = LocalDate.of(2023, 12, 12);
    public static final LocalDateTime DATETIME_2023_12_5 = LocalDateTime.of(2023, 12, 5, 0, 0);

    public static SessionDuration duration() {
        return new SessionDuration(DATE_2023_12_5, DATE_2023_12_10);
    }

    public static Session createdFreeSession() {
        return createdFreeSession(SessionRecruitStatus.NOT_RECRUIT, SessionProgressStatus.READY);
    }

    public static Session createdFreeSession(SessionRecruitStatus sessionRecruitStatus, SessionProgressStatus sessionProgressStatus) {
        return new Session(
                1L,
                ImageFixtures.images(),
                new SessionDuration(DATE_2023_12_5, DATE_2023_12_10),
                freeSessionStateZero(),
                sessionRecruitStatus,
                sessionProgressStatus,
                1L,
                DATETIME_2023_12_5,
                null);
    }

    public static Session createdChargedSession() {
        return createdChargedSession(SessionRecruitStatus.NOT_RECRUIT, SessionProgressStatus.READY);
    }

    public static Session createdChargedSession(
            SessionRecruitStatus sessionRecruitStatus, SessionProgressStatus sessionProgressStatus
    ) {
        return new Session(
                1L,
                ImageFixtures.images(),
                new SessionDuration(DATE_2023_12_5, DATE_2023_12_10),
                chargedSessionStateZero(1000L, 2, new Applies()),
                sessionRecruitStatus,
                sessionProgressStatus,
                1L,
                DATETIME_2023_12_5,
                null
        );
    }

    public static Session chargedSessionFullCanceled() {
        return new Session(
                1L,
                ImageFixtures.images(),
                new SessionDuration(DATE_2023_12_5, DATE_2023_12_10),
                chargedSessionStateZero(1000L, 2, ApplyFixtures.applies_two_canceled()),
                SessionRecruitStatus.RECRUIT,
                SessionProgressStatus.READY,
                1L,
                DATETIME_2023_12_5,
                null
        );
    }

    public static Session chargedSessionFullApproved() {
        return new Session(
                1L,
                ImageFixtures.images(),
                new SessionDuration(DATE_2023_12_5, DATE_2023_12_10),
                chargedSessionStateZero(1000L, 2, ApplyFixtures.applies_two_approved()),
                SessionRecruitStatus.RECRUIT,
                SessionProgressStatus.READY,
                1L,
                DATETIME_2023_12_5,
                null
        );
    }

    public static SessionState freeSessionStateZero() {
        return new SessionState();
    }

    public static SessionState chargedSessionStateZero(Long amount, int quota, Applies applies) {
        return new SessionState(SessionType.CHARGE, amount, quota, applies);
    }
}
