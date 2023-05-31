package nextstep.courses.domain;

import java.time.LocalDate;

public class Period {
    private LocalDate startAt;
    private LocalDate endAt;

    public Period(LocalDate startAt, LocalDate endAt){
        validate(startAt, endAt);
        this.startAt = startAt;
        this.endAt = endAt;
    }

    private void validate(LocalDate startAt, LocalDate endAt){
        if(endAt.isBefore(startAt)){
            throw new IllegalArgumentException("종료일은 시작일을 앞설 수 없습니다.");
        }
    }
}
