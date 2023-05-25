package nextstep.courses.domain;

import nextstep.courses.exception.CannotRegisterException;

public class Session {
    private final int capacity;
    private ImageInfo imageInfo;
    private SessionPeriod period;
    private SessionStatus status;
    private SessionType type;
    private int currentUsers;

    public Session(ImageInfo imageInfo, SessionPeriod period, SessionType type, SessionStatus status, int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("총 수용인원은 1명 이상이어야 합니다");
        }

        this.imageInfo = imageInfo;
        this.period = period;
        this.type = type;
        this.status = status;
        this.capacity = capacity;
    }

    public void register() {
        if (status != SessionStatus.RECRUITING) {
            throw new CannotRegisterException("현재 모집중인 강의가 아닙니다");
        }
        if (currentUsers >= capacity) {
            throw new CannotRegisterException("강의 최대 수강 인원을 초과했습니다");
        }
        currentUsers++;
    }
}
