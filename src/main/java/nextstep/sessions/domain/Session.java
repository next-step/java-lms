package nextstep.sessions.domain;

import nextstep.courses.domain.Course;
import nextstep.images.domain.Image;
import nextstep.sessions.domain.enums.ProgressStatus;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Session {
    private Long id;

    private Course course;

    private LocalDateTime from;

    private LocalDateTime to;

    private Image coverImage;

    private boolean isFree;

    private ProgressStatus status;

    private int maxEnrollmentCount;

    private int enrollmentCount;

    public static Session ofFreeSession(Long id, Course course, LocalDateTime from, LocalDateTime to, Image coverImage, int maxEnrollmentCount) {
        return new Session(id, course, from, to, coverImage, true, maxEnrollmentCount);
    }

    public static Session ofChargedSession(Long id, Course course, LocalDateTime from, LocalDateTime to, Image coverImage, int maxEnrollmentCount) {
        return new Session(id, course, from, to, coverImage, false, maxEnrollmentCount);
    }

    public static Session ofDefaultCoverImage(Long id, Course course, LocalDateTime from, LocalDateTime to, boolean isFree, int maxEnrollmentCount) {
        return new Session(id, course, from, to, Image.ofDefault(), isFree, maxEnrollmentCount);
    }

    public static Session of(Long id, Course course, LocalDateTime from, LocalDateTime to, Image coverImage, boolean isFree, ProgressStatus status, int maxEnrollmentCount) {
        return new Session(id, course, from, to, coverImage, isFree, status, maxEnrollmentCount);
    }

    private Session(Long id, Course course, LocalDateTime from, LocalDateTime to, Image coverImage, boolean isFree, int maxEnrollmentCount) {
        validatePeriod(from, to);
        validateMaxEnrollmentCount(maxEnrollmentCount);
        this.id = id;
        this.course = course;
        this.from = from;
        this.to = to;
        this.coverImage = coverImage;
        this.isFree = isFree;
        this.status = ProgressStatus.READY;
        this.maxEnrollmentCount = maxEnrollmentCount;
        this.enrollmentCount = 0;
    }

    private Session(Long id, Course course, LocalDateTime from, LocalDateTime to, Image coverImage, boolean isFree, ProgressStatus status, int maxEnrollmentCount) {
        validatePeriod(from, to);
        validateMaxEnrollmentCount(maxEnrollmentCount);
        this.id = id;
        this.course = course;
        this.from = from;
        this.to = to;
        this.coverImage = coverImage;
        this.isFree = isFree;
        this.status = status;
        this.maxEnrollmentCount = maxEnrollmentCount;
        this.enrollmentCount = 0;
    }

    public void toCourse(Course course) {
        this.course = course;
    }

    public Image getCoverImage() {
        return coverImage;
    }

    public boolean isFree() {
        return isFree;
    }

    public void toOpen() {
        this.status = ProgressStatus.OPEN;
    }

    public void toClose() {
        this.status = ProgressStatus.CLOSED;
    }

    public ProgressStatus getStatus() {
        return status;
    }

    private static void validatePeriod(LocalDateTime from, LocalDateTime to) {
        if(from == null || to == null) {
            throw new IllegalArgumentException("시작일 또는 종료일은 null 일 수 없습니다.");
        }

        if (from.isEqual(to) || from.isAfter(to)) {
            throw new IllegalArgumentException("시작일이 종료일과 같거나 이후일 수 없습니다.");
        }
    }

    private static void validateMaxEnrollmentCount(int maxEnrollmentCount) {
        if (maxEnrollmentCount < 1) {
            throw new IllegalArgumentException("최대 수강신청 인원 수가 1보다 작을 수 없습니다.");
        }
    }

    public void enroll(NsUser student) {
        if (ProgressStatus.OPEN != status) {
            throw new RuntimeException("모집중 이외의 상태에서는 수강신청이 불가합니다.");
        }

        if (maxEnrollmentCount == enrollmentCount) {
            throw new RuntimeException("최대 수강신청 인원을 초과하여 수강 신청을 할 수 없습니다.");
        }

        enrollmentCount++;
    }
}
