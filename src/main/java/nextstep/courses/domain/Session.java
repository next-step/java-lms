package nextstep.courses.domain;

import lombok.Builder;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Session {

    private final Long id;
    private final Long courseId;
    private String title;
    private Period period;
    private String imageUrl;
    private SessionType sessionType;
    private SessionValidator sessionValidator;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private final Long creatorId;

    private final Students students;

    @Builder
    public Session(Long id, Long courseId, String title, Long creatorId, Period period, LocalDateTime createAt, LocalDateTime updateAt, String imageUrl, SessionType sessionType, SessionValidator sessionValidator, Students students) {
        this.id = id;
        this.courseId = courseId;
        this.title = title;
        this.sessionValidator = sessionValidator;
        this.period = period;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.imageUrl = imageUrl;
        this.sessionType = sessionType;
        this.createAt = LocalDateTime.now();
        this.creatorId = creatorId;
        this.students = students;
    }

    public void apply(NsUser loginUser) {
        sessionValidator.addPerson(loginUser);
    }

    public void add(Student student) {
        if (!sessionValidator.isRecuritable()) {
            throw new IllegalArgumentException("모집 중일때만 신청 가능합니다!");
        }
        if (!sessionValidator.isProceeding() || !sessionValidator.isPreparing()) {
            throw new IllegalArgumentException("준비중, 진행중 일때만 신청 가능합니다!");
        }
        students.add(student);
    }

    public Student enroll(Student student){
        this.add(student);
        return student;
    }

    public Student requestEnroll(Long studentId){
        Student student = new Student(studentId, id);
        return student;
    }

    public Long getId() {
        return id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public String getTitle() {
        return this.title;
    }

    public LocalDateTime startDate() {
        return period.getStartDate();
    }

    public LocalDateTime endDate() {
        return period.getStartDate();
    }

    public LocalDateTime createAt() {
        return this.createAt;
    }

    public Long creatorId() {
        return this.creatorId;
    }

    public String imageUrl() {
        return imageUrl;
    }

    public String sessionType() {
        return this.sessionType.name();
    }

    public SessionValidator sessionValidator() {
        return this.sessionValidator;
    }

    public int StudentsMaxCount() {
        return students.maxCount();
    }
}
