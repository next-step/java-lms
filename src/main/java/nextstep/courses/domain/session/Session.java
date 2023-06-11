package nextstep.courses.domain.session;

import nextstep.courses.domain.BaseEntity;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Session extends BaseEntity {
    private final Long courseId;

    private final Long sessionNumber;

    private final SessionPeriod sessionPeriod;

    private final CoverImage coverImage;

    private final Students students;

    private final Candidates candidates;

    public Session(
            Long id,
            String title,
            Long courseId,
            Long sessionNumber,
            SessionPeriod sessionPeriod,
            CoverImage coverImage,
            Students students,
            Long creatorId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this(id, title, courseId, sessionNumber, sessionPeriod, coverImage, students, new Candidates(), creatorId, createdAt, updatedAt);
    }

    public Session(
            Long id,
            String title,
            Long courseId,
            Long sessionNumber,
            SessionPeriod sessionPeriod,
            CoverImage coverImage,
            Students students,
            Candidates candidates,
            Long creatorId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        Objects.requireNonNull(title);
        Objects.requireNonNull(courseId);
        Objects.requireNonNull(sessionNumber);
        Objects.requireNonNull(sessionPeriod);
        Objects.requireNonNull(coverImage);
        Objects.requireNonNull(students);
        Objects.requireNonNull(creatorId);
        Objects.requireNonNull(createdAt);
        this.id = id;
        this.title = title;
        this.courseId = courseId;
        this.sessionNumber = sessionNumber;
        this.sessionPeriod = sessionPeriod;
        this.coverImage = coverImage;
        this.students = students;
        this.candidates = candidates;
        this.creatorId = creatorId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Session initUsers(List<Student> students) {
        this.students.initUsers(students);
        return this;
    }

    public Session initCandidates(Set<Candidate> candidates) {
        this.candidates.initCandidates(candidates);
        return this;
    }

    public Session validateRegister(NsUser nsUser) {
        students.validateRegister(nsUser);
        return this;
    }

    public Session startRecruit() {
        sessionPeriod.checkRecruit();
        students.startRecruit();
        return this;
    }

    public Session validateApprove(NsUser nsUser) {
        students.validateExist(nsUser);
        candidates.validateApprove(id, nsUser.getId());
        return this;
    }

    public Session validateReject(NsUser nsUser) {
        students.validateExist(nsUser);
        candidates.validateReject(id, nsUser.getId());
        return this;
    }

    public Long getCourseId() {
        return courseId;
    }

    public Long getSessionNumber() {
        return sessionNumber;
    }

    public SessionPeriod getSessionPeriod() {
        return sessionPeriod;
    }

    public CoverImage getCoverImage() {
        return coverImage;
    }

    public Students getStudents() {
        return students;
    }

    public Candidates getCandidates() {
        return candidates;
    }

    @Override
    public String toString() {
        return "Session{" +
                "courseId=" + courseId +
                ", sessionNumber=" + sessionNumber +
                ", sessionPeriod=" + sessionPeriod +
                ", coverImage=" + coverImage +
                ", students=" + students +
                ", candidates=" + candidates +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", creatorId=" + creatorId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
