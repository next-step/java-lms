package nextstep.courses.domain.session;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Session {
    private final SessionPeriod period;
    private final SessionImage coverImage;
    private final SessionStatus status;
    private final Set<Long> enrolledStudentIds;


    public Session(LocalDate startDate, LocalDate endDate, SessionImage coverImage) {
        this(startDate, endDate, coverImage, SessionStatus.PREPARING, new HashSet<>());
    }

    public Session(LocalDate startDate, LocalDate endDate, SessionImage coverImage, String status) {
        this(startDate, endDate, coverImage, status, new HashSet<>());
    }

    public Session(LocalDate startDate, LocalDate endDate, SessionImage coverImage, String status, Set<Long> enrolledStudentIds) {
        this(startDate, endDate, coverImage, SessionStatus.from(status), enrolledStudentIds);
    }

    public Session(LocalDate startDate, LocalDate endDate, SessionImage coverImage, SessionStatus status, Set<Long> enrolledStudentIds) {
        this(new SessionPeriod(startDate, endDate), coverImage, status, enrolledStudentIds);
    }

    public Session(SessionPeriod period, SessionImage coverImage, SessionStatus status, Set<Long> enrolledStudentIds) {
        this.period = period;
        this.coverImage = coverImage;
        this.status = status;
        this.enrolledStudentIds = enrolledStudentIds;
    }

    public void enroll(Long studentId) {
        if (!status.canEnroll()) {
            throw new IllegalStateException("모집중인 강의만 수강 신청할 수 있다");
        }
        enrolledStudentIds.add(studentId);
    }

    public boolean isEnrolled(Long studentId) {
        return enrolledStudentIds.contains(studentId);
    }
}
