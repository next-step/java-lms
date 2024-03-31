package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import nextstep.courses.InvalidSessionException;
import nextstep.users.domain.NsUser;

public class Session {

    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private SessionCoverImage coverImage;
    private Integer price;
    private Integer stock;
    private SessionStatus status = SessionStatus.PREPARE;
    private List<NsUser> learners = new ArrayList<>();
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Session(LocalDateTime startDate, LocalDateTime endDate, SessionCoverImage coverImage,
        LocalDateTime createdAt) {
        this(0L, startDate, endDate, coverImage, 0, 0, createdAt);
    }

    public Session(LocalDateTime startDate, LocalDateTime endDate, SessionCoverImage coverImage,
        int price, int stock, LocalDateTime createdAt) {
        this(0L, startDate, endDate, coverImage, price, stock, createdAt);
    }

    public Session(Long id, LocalDateTime startDate, LocalDateTime endDate,
        SessionCoverImage coverImage,
        Integer price, Integer stock, LocalDateTime createdAt) {
        this.id = id;

        validateDate(startDate, endDate);
        this.startDate = startDate;
        this.endDate = endDate;
        this.coverImage = coverImage;
        this.price = price;
        this.stock = stock;
        this.createdAt = createdAt;
    }

    private void validateDate(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate.isAfter(endDate)) {
            throw new InvalidSessionException("강의 종료일이 시작일보다 앞섭니다");
        }
    }

    public boolean isFree() {
        return price == 0;
    }
}
