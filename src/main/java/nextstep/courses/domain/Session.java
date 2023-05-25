package nextstep.courses.domain;

import nextstep.courses.exception.CannotRegisterException;

public class Session {
    private String coverImageUrl;
    private SessionPeriod period;
    private SessionType type;
    private SessionStatus status;
    private int capacity;


    public void register() {
        if (status != SessionStatus.RECRUITING) {
            throw new CannotRegisterException("현재 모집중인 강의가 아닙니다");
        }
        if (currentUsers <= capacity) {
            throw new CannotRegisterException("강의 최대 수강 인원을 초과했습니다");
        }
        currentUsers++;
    }
}
