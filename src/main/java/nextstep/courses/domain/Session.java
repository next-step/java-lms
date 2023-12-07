package nextstep.courses.domain;

import nextstep.courses.exception.SessionException;
import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Session {
    private long id;

    private String title;

    private SessionState sessionState;

    private SessionType sessionType;

    private ImageInfo coverImage;

    private int maxPersonnel;

    private int enrollCount;

    private List<NsUser> students;

    private Long fee;

    private LocalDate startDate;

    private LocalDate endDate;

    private Long creatorId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Session(long id, SessionState sessionState, ImageInfo imageInfo, LocalDate startDate, LocalDate endDate) {
        checkSessionStatus(sessionState, startDate, endDate);
        this.id = id;
        this.sessionState = sessionState;
        this.coverImage = imageInfo;
        this.startDate = startDate;
        this.endDate = endDate;
        this.enrollCount = 0;
        this.students = new ArrayList<>();
    }

    public Session(long id, SessionType sessionType, SessionState sessionState, int maxPersonnel, List<NsUser> students, int enrollCount) {
        this.id = id;
        this.sessionType = sessionType;
        this.sessionState = sessionState;
        this.maxPersonnel = maxPersonnel;
        this.students = students;
        this.enrollCount = enrollCount;
    }

    private void checkSessionStatus(SessionState sessionState, LocalDate startDate, LocalDate endDate) {
        if(!sessionState.checkStatus(startDate, endDate, LocalDate.now())){
            throw new SessionException("강의 상태가 잘못되었습니다.");
        }
    }

    public void enrollStudent(NsUser student) {
        if(sessionType == SessionType.PAID && enrollCount >= maxPersonnel){
            throw new SessionException("최대 수강 인원을 초과하였습니다.");
        }

        if(!SessionState.isAbleToEnroll(sessionState)){
            throw new SessionException("모집중인 강의가 아닙니다.");
        }

        students.add(student);
        enrollCount++;
    }

    public int getEnrollCount() {
        return enrollCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return id == session.id && maxPersonnel == session.maxPersonnel && enrollCount == session.enrollCount && Objects.equals(title, session.title) && sessionState == session.sessionState && sessionType == session.sessionType && Objects.equals(coverImage, session.coverImage) && Objects.equals(students, session.students) && Objects.equals(fee, session.fee) && Objects.equals(creatorId, session.creatorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, sessionState, sessionType, coverImage, maxPersonnel, enrollCount, students, fee, creatorId);
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", sessionState=" + sessionState +
                ", sessionType=" + sessionType +
                ", coverImage=" + coverImage +
                ", maxPersonnel=" + maxPersonnel +
                ", fee=" + fee +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", creatorId=" + creatorId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
