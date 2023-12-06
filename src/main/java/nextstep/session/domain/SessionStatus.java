package nextstep.session.domain;

import java.util.function.BooleanSupplier;

public enum SessionStatus {
    PREPARING("준비중", () -> false),
    RECRUITING("모집중", () -> true),
    END("종료", () -> false);

    private final String status;

    private final BooleanSupplier isRegistrable;


    SessionStatus(String status, BooleanSupplier isRegistrable) {
        this.status = status;
        this.isRegistrable = isRegistrable;
    }

    public boolean isRegistrable() {
        return this.isRegistrable.getAsBoolean();
    }
}
