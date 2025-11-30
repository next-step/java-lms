package nextstep.courses.domain.session;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Session {
    private final LocalDate startDate;
    private final LocalDate endDate;
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
        validateDate(startDate, endDate);
        this.startDate = startDate;
        this.endDate = endDate;
        this.coverImage = coverImage;
        this.status = status;
        this.enrolledStudentIds = enrolledStudentIds;
    }

    private void validateDate(LocalDate startDate, LocalDate endDate) {
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("종료일은 시작일보다 이후여야 한다");
        }
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
