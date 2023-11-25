package nextstep.courses.domain;

import nextstep.courses.InvalidDurationException;

import java.time.LocalDate;

public class Duration {

    private final LocalDate start;
    private final LocalDate end;

    public Duration(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
        validate();
    }

    private void validate() {
        if (this.start.isAfter(this.end)) {
            throw new InvalidDurationException("종료일이 시작일 이전입니다.");
        }
    }

    public LocalDate start() {
        return this.start;
    }

    public LocalDate end() {
        return this.end;
    }

}
