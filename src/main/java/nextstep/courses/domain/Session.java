package nextstep.courses.domain;

import lombok.Builder;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


public class Session {

    private Long id;
    private Long courseId;
    private String title;
    private Period period;
    private String imageUrl;
    private SessionType sessionType;
    private SessionValidator sessionValidator;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private Long creatorId;

    private final Set<Student> students = new HashSet<>();

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
    }

    public void apply(NsUser loginUser) {
        sessionValidator.addPerson(loginUser);
    }


    public void add(Student student) {
        if (isFull()) {
            throw new IllegalArgumentException("강의 수강 신청 인원이 다 찼습니다!");
        }
        if (!sessionValidator.sessionState().isRecruitable()) {
            throw new IllegalArgumentException("모집 중일때만 신청 가능합니다!");
        }
        students.add(student);
    }

    private boolean isFull() {
        return this.sessionValidator.maxCount() <= this.students.size();
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
