package nextstep.courses.cohort.domain;

import static java.util.Objects.isNull;

import java.time.LocalDateTime;

public class Period {
    private final LocalDateTime registerStartDate;
    private final LocalDateTime registerEndDate;

    public Period(LocalDateTime registerStartDate, LocalDateTime registerEndDate) {
        if (isNull(registerStartDate) || isNull(registerEndDate)) {
            throw new IllegalArgumentException("수강신청 시작일과 종료일은 필수값 입니다.");
        }

        if (registerStartDate.isAfter(registerEndDate)) {
            throw new IllegalArgumentException("수강신청 시작일이 종료일보다 미래일수는 없습니다");
        }

        this.registerStartDate = registerStartDate;
        this.registerEndDate = registerEndDate;
    }
}
