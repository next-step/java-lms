package nextstep.courses.domain;

import nextstep.courses.exception.InvalidSessionDateTimeException;

import java.time.LocalDateTime;

public class SessionTime extends BaseEntity {
    private Long id;
    private LocalDateTime openingDateTime;
    private LocalDateTime closingDateTime;

    public SessionTime(LocalDateTime openingDateTime, LocalDateTime closingDateTime) {
        checkDateTime(openingDateTime, closingDateTime);
        this.openingDateTime = openingDateTime;
        this.closingDateTime = closingDateTime;
    }

    public SessionTime(Long id, LocalDateTime openingDateTime, LocalDateTime closingDateTime) {
        checkDateTime(openingDateTime, closingDateTime);
        this.id = id;
        this.openingDateTime = openingDateTime;
        this.closingDateTime = closingDateTime;
    }

    private static void checkDateTime(LocalDateTime openingDateTime, LocalDateTime closingDateTime) throws InvalidSessionDateTimeException {
        if (openingDateTime.isAfter(closingDateTime)) {
            throw new InvalidSessionDateTimeException("강의 시작 시간은 종료 시간보다 늦을 수 없습니다.");
        }
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getOpeningDateTime() {
        return openingDateTime;
    }

    public LocalDateTime getClosingDateTime() {
        return closingDateTime;
    }
}
