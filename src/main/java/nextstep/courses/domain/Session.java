package nextstep.courses.domain;

import nextstep.courses.exception.SessionException;
import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Session {
    private long id;

    private String title;

    private SessionState sessionState;

    private SessionType sessionType;

    private ImageInfo coverImage;

    private int maxPersonnel;

    private List<NsUser> students;

    private Long fee;

    private LocalDate startDate;

    private LocalDate endDate;

    private Long creatorId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Session(long id, SessionState sessionState, LocalDate startDate, LocalDate endDate) {
        checkSessionStatus(sessionState, startDate, endDate);

        this.id = id;
        this.sessionState = sessionState;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private void checkSessionStatus(SessionState sessionState, LocalDate startDate, LocalDate endDate) {
        if(!sessionState.checkStatus(startDate, endDate, LocalDate.now())){
            throw new SessionException("강의 상태가 잘못되었습니다.");
        }
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
