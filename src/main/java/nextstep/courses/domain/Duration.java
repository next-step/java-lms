package nextstep.courses.domain;

import nextstep.courses.InvalidDurationException;
import nextstep.courses.domain.type.SessionStatus;

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

    public SessionStatus sessionStatus(LocalDate today) {
        if (today.isBefore(this.start)) {
            return SessionStatus.READY;
        }
        if (today.isBefore(this.end)) {
            return SessionStatus.RECRUITING;
        }
        return SessionStatus.TERMINATE;
    }

    public LocalDate start() {
        return this.start;
    }

    public LocalDate end() {
        return this.end;
    }

}
