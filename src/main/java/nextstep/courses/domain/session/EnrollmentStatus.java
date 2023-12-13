package nextstep.courses.domain.session;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.function.BiFunction;

public enum EnrollmentStatus {
    PREPARING("준비중"),
    RECRUITING("모집중"),
    CLOSE("종료");

    private String status;

    EnrollmentStatus(String status) {
        this.status = status;
    }

    public static boolean canSignUp(EnrollmentStatus enrollmentStatus) {
        return enrollmentStatus == EnrollmentStatus.RECRUITING;
    }
}
