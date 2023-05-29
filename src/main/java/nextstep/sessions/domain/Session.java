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

    public static Session ofFreeSession(Long id, Course course, LocalDateTime from, LocalDateTime to, Image coverImage) {
        return new Session(id, course, from, to, coverImage, true);
    }

    public static Session ofChargedSession(Long id, Course course, LocalDateTime from, LocalDateTime to, Image coverImage) {
        return new Session(id, course, from, to, coverImage, false);
    }

    public static Session ofDefaultCoverImage(Long id, Course course, LocalDateTime from, LocalDateTime to, boolean isFree) {
        return new Session(id, course, from, to, Image.ofDefault(), isFree);
    }

    public static Session of(Long id, Course course, LocalDateTime from, LocalDateTime to, Image coverImage, boolean isFree, ProgressStatus status) {
        return new Session(id, course, from, to, coverImage, isFree, status);
    }

    private Session(Long id, Course course, LocalDateTime from, LocalDateTime to, Image coverImage, boolean isFree) {
        validatePeriod(from, to);
        this.id = id;
        this.course = course;
        this.from = from;
        this.to = to;
        this.coverImage = coverImage;
        this.isFree = isFree;
        this.status = ProgressStatus.READY;
    }

    private Session(Long id, Course course, LocalDateTime from, LocalDateTime to, Image coverImage, boolean isFree, ProgressStatus status) {
        validatePeriod(from, to);
        this.id = id;
        this.course = course;
        this.from = from;
        this.to = to;
        this.coverImage = coverImage;
        this.isFree = isFree;
        this.status = status;
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

    public void enroll(NsUser student) {
        if (ProgressStatus.OPEN != status) {
            throw new RuntimeException("모집중 이외의 상태에서는 수강신청이 불가합니다.");
        }
    }
}
