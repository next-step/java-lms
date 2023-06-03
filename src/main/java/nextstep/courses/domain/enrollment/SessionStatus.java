package nextstep.courses.domain.enrollment;

import java.util.Arrays;

public enum SessionStatus {


    READY("준비중"),
    OPENED("모집중"),
    CLSOED("종료")
    ;

    private String name;

    public static SessionStatus find(String sessionStatus) {
        return Arrays.stream(values())
                .filter(type -> type.name().equalsIgnoreCase(sessionStatus))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 강의상태입니다."));
    }

    SessionStatus(String name) {
        this.name = name;
    }

    public boolean canJoin() {
        return this.equals(OPENED);
    }
}
