package nextstep.courses.domain;

import nextstep.users.domain.NsStudent;
import nextstep.users.domain.NsTeacher;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Session {

    private Long id;
    private String name;
    private SessionPeriod sessionPeriod;
    private String coverImage;
    private SessionOption sessionOption;
    private SessionCapacity sessionCapacity;
    private SessionStudent sessionStudent;
    private NsTeacher nsTeacher;
    private Long courseId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Session(LocalDate startDate, LocalDate endDate, Long courseId) {
        this(null, "test", new SessionPeriod(startDate, endDate), "cover", new SessionOption(), new SessionCapacity(), new SessionStudent(), new NsTeacher(), courseId, LocalDateTime.now(), LocalDateTime.now());
    }

    public Session(SessionEnrollment sessionEnrollment) {
        this("test", new SessionPeriod(LocalDate.now()), "cover", new SessionOption(sessionEnrollment), new SessionCapacity(), new NsTeacher(), 1L);
    }

    public Session(Long id, NsTeacher nsTeacher) {
        this(id, "test", new SessionPeriod(LocalDate.now()), "cover", new SessionOption(), new SessionCapacity(), new SessionStudent(), nsTeacher, 1L, LocalDateTime.now(), LocalDateTime.now());
    }

    public Session(int maxNumberOfStudents) {
        this("test", new SessionPeriod(LocalDate.now()), "image", new SessionOption(), new SessionCapacity(maxNumberOfStudents), new NsTeacher(), 1L);
    }

    public Session(String name, SessionPeriod sessionPeriod, String coverImage, SessionOption sessionOption, SessionCapacity sessionCapacity, NsTeacher nsTeacher, Long courseId) {
        this(null, name, sessionPeriod, coverImage, sessionOption, sessionCapacity, new SessionStudent(), nsTeacher, courseId, LocalDateTime.now(), LocalDateTime.now());
    }

    public Session(Long id, String name, SessionPeriod sessionPeriod, String coverImage, SessionOption sessionOption, SessionCapacity sessionCapacity, SessionStudent sessionStudent, NsTeacher nsTeacher, Long courseId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.sessionPeriod = sessionPeriod;
        this.coverImage = coverImage;
        this.sessionOption = sessionOption;
        this.sessionCapacity = sessionCapacity;
        this.sessionStudent = sessionStudent;
        this.nsTeacher = nsTeacher;
        this.courseId = courseId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void register(NsStudent nsStudent) {
        sessionOption.validateStatusOfSession();
        sessionCapacity.register();
        sessionStudent.register(nsStudent, id);
    }

    public void approve(NsTeacher nsTeacher, NsStudent nsStudent) {
        validateUser(nsTeacher, nsStudent);
        sessionStudent.approve(nsStudent, id);
    }

    public void refuse(NsTeacher nsTeacher, NsStudent nsStudent) {
        validateUser(nsTeacher, nsStudent);
        sessionStudent.refuse(nsStudent, id);
    }

    private void validateUser(NsTeacher nsTeacher, NsStudent student) {
        if(!isRightTeacher(nsTeacher)){
            throw new IllegalArgumentException("담당 강사가 아닙니다.");
        }

        if (!isRightStudent(student)) {
            throw new IllegalArgumentException("신청한 학생이 아닙니다.");
        }
    }

    private boolean isRightStudent(NsStudent student) {
        return sessionStudent.hasStudent(student);
    }

    private boolean isRightTeacher(NsTeacher nsTeacher){
        return nsTeacher.hasSession(id);
    }

    public int getNumberOfRegisteredStudent() {
        return sessionCapacity.getNumberOfRegisteredStudent();
    }

    public String getName() {
        return name;
    }

    public int getMaxNumberOfStudents() {
        return sessionCapacity.getMaxNumberOfStudents();
    }

    public LocalDate getEndDate() {
        return sessionPeriod.getEndDate();
    }

    public LocalDate getStartDate() {
        return sessionPeriod.getStartDate();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public Long getCourseId() {
        return courseId;
    }

    public NsTeacher getNsTeacher() {
        return nsTeacher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(name, session.name) && Objects.equals(sessionPeriod, session.sessionPeriod) && Objects.equals(coverImage, session.coverImage) && Objects.equals(sessionOption, session.sessionOption) && Objects.equals(sessionCapacity, session.sessionCapacity) && Objects.equals(courseId, session.courseId) && Objects.equals(createdAt, session.createdAt) && Objects.equals(updatedAt, session.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, sessionPeriod, coverImage, sessionOption, sessionCapacity, courseId, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "Session{" +
                "name='" + name + '\'' +
                ", sessionPeriod=" + sessionPeriod +
                ", coverImage='" + coverImage + '\'' +
                ", sessionOption=" + sessionOption +
                ", sessionStudent=" + sessionCapacity +
                ", courseId=" + courseId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
