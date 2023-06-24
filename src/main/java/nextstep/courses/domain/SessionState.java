package nextstep.courses.domain;

import java.time.LocalDate;

public enum SessionState {
    PREPARING {
        @Override
        public String desc() {
            return "준비중";
        }

        @Override
        public void validate(SessionPeriod sessionPeriod, SessionState sessionState) {

            if (sessionState == SessionState.PROGRESSING) {
                validateProgressing(sessionPeriod);
            }

            if (sessionState == SessionState.FINISH) {
                validateFinish(sessionPeriod);
            }
        }

        private void validateProgressing(SessionPeriod sessionPeriod) {
            if (sessionPeriod.isBeforeStartDate(LocalDate.now())) {
                throw new IllegalArgumentException("강의 시작 일시 전이에요, '진행중' 상태로 변경할수 없어요 :(");
            }
        }

        private void validateFinish(SessionPeriod sessionPeriod) {
            if (sessionPeriod.isAfterEndDate(LocalDate.now())) {
                throw new IllegalArgumentException("강의 종료 일시 전이에요, '종료' 상태로 변경할수 없어요 :(");
            }
        }

        @Override
        public boolean isAvailableManualChangeSession() {
            return true;
        }
    },
    PROGRESSING {
        @Override
        public String desc() {
            return "진행중";
        }

        @Override
        public void validate(SessionPeriod sessionPeriod, SessionState sessionState) {

        }

        @Override
        public boolean isAvailableManualChangeSession() {
            return false;
        }
    },
    FINISH {
        @Override
        public String desc() {
            return "종료";
        }

        @Override
        public void validate(SessionPeriod sessionPeriod, SessionState sessionState) {

        }

        @Override
        public boolean isAvailableManualChangeSession() {
            return false;
        }
    };

    SessionState() {
    }

    public SessionState syncSessionStateAccordingTo(LocalDate now, SessionPeriod sessionPeriod) {

        if (sessionPeriod.isAfterEndDate(now)) {
            return SessionState.FINISH;
        }

        if (sessionPeriod.isBeforeStartDate(now)) {
            return SessionState.PREPARING;
        }

        return SessionState.PROGRESSING;
    }

    public abstract String desc();

    public abstract void validate(SessionPeriod sessionPeriod, SessionState sessionState);

    public abstract boolean isAvailableManualChangeSession();

}
