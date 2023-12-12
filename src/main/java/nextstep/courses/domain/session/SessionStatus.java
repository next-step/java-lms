package nextstep.courses.domain.session;

import java.util.Arrays;

public enum SessionStatus {

    PREPARE("준비중"),
    PROGRESS("진해중"),
    FINISH("종료");

    private String description;

    SessionStatus(String description) {
        this.description = description;
    }

    public static boolean isNotProgressing(SessionStatus sessionStatus) {
        return !PROGRESS.equals(sessionStatus);
    }

    public static SessionStatus sessionStatus(String sessionStatus) {
        return Arrays.stream(values())
            .filter(val -> val.toString().equals(sessionStatus))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("일치하는 Status가 없습니다. 인자 값 :: " + sessionStatus));
    }

    public String description() {
        return this.description;
    }
}
