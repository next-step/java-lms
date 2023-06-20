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
    public Session(Long id, Long courseId, String title, Long creatorId, Period period, LocalDateTime createAt, LocalDateTime updateAt, String imageUrl, SessionType sessionType, SessionValidator sessionValidator) {
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
        this.students = new Students();
    }

    public void apply(NsUser loginUser) {
        sessionValidator.addPerson(loginUser);
    }

    public void add(Student student) {
        if (isFull(students.size())) {
            throw new IllegalArgumentException("강의 수강 신청 인원이 다 찼습니다!");
        }
        if (!sessionValidator.isRecuritable()) {
            throw new IllegalArgumentException("모집 중일때만 신청 가능합니다!");
        }
        students.add(student);
    }

    public Student enroll(Long studentId){
        Student student = new Student(studentId, id);
        this.add(student);

        return student;
    }

    private boolean isFull(int size) {
        return this.sessionValidator.maxCount() <= size;
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
}
