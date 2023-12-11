package nextstep.courses.domain.session;

import java.util.Arrays;

public enum PayType {

    FREE("무료"),
    PAY("유료");

    private String description;

    PayType(String description) {
        this.description = description;
    }

    public static PayType payType(String payType) {
        return Arrays.stream(values())
            .filter(val -> val.toString().equals(payType))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("일치하는 PayType이 없습니다. 인자 값 :: " + payType));
    }
}
