package nextstep.courses.domain;

import java.time.LocalDateTime;

class SessionTime {
    private LocalDateTime startDt = null;
    private LocalDateTime endDt = null;

    boolean isStart() {
        return startDt != null;
    }

    void start(LocalDateTime startTime) {
        nullCheck(startTime);

        alreadyStartCheck();

        this.startDt = startTime;
    }

    private void alreadyStartCheck() {
        if (isStart()) {
            throw new IllegalStateException("이미 시작 했습니다.");
        }
    }

    boolean isEnd() {
        return endDt != null;
    }

    void end(LocalDateTime endTime) {
        nullCheck(endTime);

        startCheck();

        alreadyEndCheck();

        isBeforeOrEqualCheck(endTime);

        this.endDt = endTime;
    }

    private void alreadyEndCheck() {
        if (isEnd()) {
            throw new IllegalStateException("이미 종료 했습니다.");
        }
    }

    private void isBeforeOrEqualCheck(LocalDateTime localDateTime) {
        if (startDt.isAfter(localDateTime)) {
            throw new IllegalArgumentException("시작 시간보다 빠를 수 없습니다.");
        }
    }

    private void startCheck() {
        if (startDt == null) {
            throw new IllegalStateException("시작 후에 종료가 가능 합니다.");
        }
    }

    private void nullCheck(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            throw new NullPointerException();
        }
    }
}
