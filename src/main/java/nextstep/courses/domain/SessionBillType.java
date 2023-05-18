package nextstep.courses.domain;

import java.util.Arrays;

public enum SessionBillType {
    FREE("무료"),
    PAID("유료")
    ;

    private final String name;

    SessionBillType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static SessionBillType find(String name) {
        return Arrays.stream(values())
                     .filter(v -> v.name().equals(name))
                     .findFirst()
                     .orElseThrow(() -> new IllegalArgumentException(name + "을 찾을 수 없습니다."));
    }
}
