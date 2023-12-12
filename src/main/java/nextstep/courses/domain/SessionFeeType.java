package nextstep.courses.domain;

import java.util.Arrays;

public enum SessionFeeType {
    FREE("무료"),
    PAID("유료"),
    NONE("-");
    private String feeType;

    SessionFeeType(String feeType) {
        this.feeType = feeType;
    }

    public static SessionFeeType of(String matchFeeType){
        return Arrays.stream(SessionFeeType.values())
                .filter(s -> s.isSameSessionFeeType(matchFeeType))
                .findFirst()
                .orElse(NONE);
    }

    public boolean isSameSessionFeeType(String matchFeeType) {
        return this.feeType == matchFeeType;
    }
}
