package nextstep.courses.domain.session;

import java.time.LocalDate;
import nextstep.courses.domain.coverimage.CoverImage;

public class Session {

    private static final int UNLIMITED_ENROLLMENT = 0;
    private final CoverImage coverImage;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final int limitedEnrollment;
    private SessionStatus sessionStatus;

    private Session(CoverImage coverImage, LocalDate startDate, LocalDate endDate, int limitedEnrollment) {
        validateStartDate(startDate);
        validateEndDate(startDate, endDate);
        this.coverImage = coverImage;
        this.startDate = startDate;
        this.endDate = endDate;
        this.limitedEnrollment = limitedEnrollment;
        this.sessionStatus = updateSessionStatusToEnrollmentOpenOrPreparing(startDate);
    }

    public Session(LocalDate startDate, LocalDate endDate) {
        this(null, startDate, endDate, 0);
    }

    public static Session createFreeSession(CoverImage coverImage, LocalDate startDate, LocalDate endDate) {
        return new Session(coverImage, startDate, endDate, UNLIMITED_ENROLLMENT);
    }

    public static Session createPaidSession(CoverImage coverImage, LocalDate startDate, LocalDate endDate,
        int limitedEnrollment) {
        return new Session(coverImage, startDate, endDate, limitedEnrollment);
    }

    public int limitedEnrollment() {
        return limitedEnrollment;
    }

    private void validateStartDate(LocalDate startDate) {
        if (isBeforeToday(startDate)) {
            throw new IllegalArgumentException("강의 시작일은 오늘 날짜 이후로 설정할 수 있습니다.");
        }
    }

    private void validateEndDate(LocalDate startDate, LocalDate endDate) {
        if (!xDateIsBeforeYDate(startDate, endDate)) {
            throw new IllegalArgumentException("강의 종료일은 강의 시작일 이후로 설정할 수 있습니다.");
        }
    }

    private boolean isBeforeToday(LocalDate date) {
        return date.isBefore(LocalDate.now());
    }

    private boolean xDateIsBeforeYDate(LocalDate startDate, LocalDate endDate) {
        return !startDate.isAfter(endDate);
    }

    private SessionStatus updateSessionStatusToEnrollmentOpenOrPreparing(LocalDate startDate) {
        if (isToday(startDate)) {
            return SessionStatus.ENROLLMENT_OPEN;
        }
        return SessionStatus.PREPARING;
    }

    private boolean isToday(LocalDate date) {
        return date.isEqual(LocalDate.now());
    }
}
