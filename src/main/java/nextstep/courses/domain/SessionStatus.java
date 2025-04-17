package nextstep.courses.domain;

public enum SessionStatus {
    READY, OPEN, CLOSE;

    public static void isReady(SessionStatus status) {
        if(!status.equals(READY)){
            throw new IllegalArgumentException("강의 수강신청은 강의 상태가 모집중일 때만 가능합니다");
        }
    }

    public static SessionStatus open(){
        return OPEN;
    }

    public static SessionStatus close(){
        return CLOSE;
    }
}
