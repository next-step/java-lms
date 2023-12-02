package nextstep.courses.domain.constant;

import java.util.Random;

public enum SessionFee {

    A(100_000),
    B(200_000),
    C(300_000);

    private static final Random RANDOM = new Random();

    private int fee;

    SessionFee(int fee) {
        this.fee = fee;
    }

    public static SessionFee random() {
        SessionFee[] values = SessionFee.values();
        return values[RANDOM.nextInt(values.length)];
    }

    public int getFee() {
        return fee;
    }
}
