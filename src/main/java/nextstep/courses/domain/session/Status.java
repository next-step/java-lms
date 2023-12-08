package nextstep.courses.domain.session;

import java.util.Arrays;

public enum Status {

    PREPARE("준비중"),
    RECRUIT("모집중"),
    FINISH("종료");

    private String description;

    Status(String description) {
        this.description = description;
    }

    public static boolean isNotRecruiting(Status status) {
        return !RECRUIT.equals(status);
    }

    public static Status status(String status) {
        return Arrays.stream(values())
            .filter(val -> val.toString().equals(status))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("일치하는 Status가 없습니다. 인자 값 :: " + status));
    }

    public String description() {
        return this.description;
    }
}
