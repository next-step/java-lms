package nextstep.common;

import java.time.LocalDate;

public class Period {

    // 시작일
    private LocalDate startAt;

    // 종료일
    private LocalDate endAt;

    public Period(LocalDate startAt, LocalDate endAt) {
        if (endAt.isBefore(startAt)) {
            throw new IllegalStateException("종료일은 시작일 이전일 수 없습니다.");
        }
        this.startAt = startAt;
        this.endAt = endAt;
    }

    public LocalDate getStartAt() {
        return startAt;
    }

    public LocalDate getEndAt() {
        return endAt;
    }

    public boolean isPreparing() {
        LocalDate nowDate = LocalDate.now();
        return startAt.isAfter(nowDate) && endAt.isAfter(nowDate);
    }

    public boolean isInProgress() {
        LocalDate nowDate = LocalDate.now();
        if ((startAt.isEqual(nowDate) || startAt.isBefore(nowDate)) && (endAt.isEqual(nowDate) || endAt.isAfter(nowDate))) {
            return true;
        }
        return false;
    }
}
