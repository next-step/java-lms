package nextstep.courses.domain;

import lombok.Builder;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Session {

    private final Long id;
    private final Long courseId;
    private String title;
    private Period period;
    private String imageUrl;
    private SessionType sessionType;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private final Long creatorId;

    private final Students students;

    private final Set<NsUser> nsUsers;
    private SessionRecruitState sessionRecruitState;
    private SessionState sessionState;

    @Builder
    public Session(Long id, Long courseId, String title, Long creatorId, Period period, LocalDateTime createAt, LocalDateTime updateAt, String imageUrl, SessionType sessionType, SessionRecruitState sessionRecruitState, SessionState sessionState, Students students) {
        this.id = id;
        this.courseId = courseId;
        this.title = title;
        this.sessionRecruitState = sessionRecruitState;
        this.sessionState = sessionState;
        this.period = period;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.imageUrl = imageUrl;
        this.sessionType = sessionType;
        this.createAt = LocalDateTime.now();
        this.creatorId = creatorId;
        this.students = students;
        this.nsUsers = new HashSet<>();
    }

    public void apply(NsUser loginUser) {
        if (!sessionRecruitState.isRecruitable()) {
            throw new IllegalArgumentException("해당 강의는 수강신청중이 아닙니다.");
        }

        if (nsUsers.contains(loginUser)) {
            throw new IllegalArgumentException("중복 신청입니다.");
        }
        nsUsers.add(loginUser);
    }

    public void add(Student student) {
        if (!sessionRecruitState.isRecruitable()) {
            throw new IllegalArgumentException("모집 중일때만 신청 가능합니다!");
        }
        if (!sessionState.isProceeding() || !sessionState.isPreparing()) {
            throw new IllegalArgumentException("준비중, 진행중 일때만 신청 가능합니다!");
        }
        students.add(student);
    }

    public Student enroll(Student student){
        this.add(student);
        return student;
    }

    public Student requestEnroll(Long studentId){
        return new Student(studentId, id);
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

    public int studentsMaxCount() {
        return students.maxCount();
    }
}
