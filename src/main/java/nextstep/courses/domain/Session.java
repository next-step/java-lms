package nextstep.courses.domain;

import static nextstep.courses.domain.SessionStatus.RECRUIT;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import nextstep.courses.CanNotJoinSessionException;
import nextstep.courses.InvalidSessionException;
import nextstep.users.domain.NsUser;

public class Session {

    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private SessionCoverImage coverImage;
    private Integer price;
    private Integer capacity;
    private SessionStatus status = SessionStatus.PREPARE;
    private List<NsUser> learners = new ArrayList<>();
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Session(LocalDateTime startDate, LocalDateTime endDate, SessionCoverImage coverImage,
        LocalDateTime createdAt) {
        this(0L, startDate, endDate, coverImage, 0, 0, createdAt);
    }

    public Session(LocalDateTime startDate, LocalDateTime endDate, SessionCoverImage coverImage,
        int price, int capacity, LocalDateTime createdAt) {
        this(0L, startDate, endDate, coverImage, price, capacity, createdAt);
    }

    public Session(Long id, LocalDateTime startDate, LocalDateTime endDate,
        SessionCoverImage coverImage,
        Integer price, Integer capacity, LocalDateTime createdAt) {

        this(id, startDate, endDate, coverImage, price, capacity, SessionStatus.PREPARE, createdAt);
    }

    public Session(Long id, LocalDateTime startDate, LocalDateTime endDate, SessionCoverImage coverImage,
        Integer price, Integer capacity, SessionStatus status, LocalDateTime createdAt) {
        validateDate(startDate, endDate);
        this.id = id;
        validateDate(startDate, endDate);
        this.startDate = startDate;
        this.endDate = endDate;
        this.coverImage = coverImage;
        this.price = price;
        this.capacity = capacity;
        this.status = status;
        this.createdAt = createdAt;
    }

    private void validateDate(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate.isAfter(endDate)) {
            throw new InvalidSessionException("강의 종료일이 시작일보다 앞섭니다");
        }
    }

    public void join(NsUser learner) {
        validateJoinable();
        learners.add(learner);
    }

    private void validateJoinable() {
        boolean exceedCapacity = isPaidSession() && capacity < learners.size() + 1;
        if (exceedCapacity) {
            throw new CanNotJoinSessionException("모집 정원을 초과했습니다");
        }
        if (status != RECRUIT) {
            throw new CanNotJoinSessionException("모집중 상태가 아닙니다");
        }
    }

    public boolean isPaidSession() {
        return price > 0;
    }

    public List<NsUser> getLearners() {
        return learners;
    }
}
