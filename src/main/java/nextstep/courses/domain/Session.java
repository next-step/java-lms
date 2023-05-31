package nextstep.courses.domain;

import nextstep.courses.enums.SessionStatus;
import nextstep.courses.enums.SessionType;

import java.time.LocalDateTime;

public class Session {
    private Long id;

    private String title;

    private String startDate;

    private String endDate;

    private Capacity capacity;

    private CoverImage coverImage;

    private SessionType sessionType;

    private SessionStatus sessionStatus;

    private Long creatorId;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt;

    public Session(Long id, String title, String startDate, String endDate, Capacity capacity, CoverImage coverImage
            , SessionType sessionType, SessionStatus sessionStatus, Long creatorId) {

        validDate(startDate, endDate);

        this.id = id;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.capacity = capacity;
        this.coverImage = coverImage;
        this.sessionType = sessionType;
        this.sessionStatus = sessionStatus;
        this.creatorId = creatorId;
    }

    private static void validDate(String startDate, String endDate) {
        if (startDate.compareTo(endDate) > 0) {
            throw new IllegalArgumentException("종료날짜는 시작날짜보다 작을 수 없습니다.");
        }
    }

    public void apply() {
        if (this.sessionStatus == SessionStatus.RECRUTING) {
            capacity.add();
            return;
        }

        throw new IllegalArgumentException("모집 중일때만 수강신청이 가능합니다.");
    }

    public Capacity getCapacity() {
        return capacity;
    }

}
