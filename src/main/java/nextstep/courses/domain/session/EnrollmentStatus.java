package nextstep.courses.domain.session;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.function.BiFunction;

public enum EnrollmentStatus {
    RECRUITING("모집중"),
    CLOSE("비모집중");

    private String status;

    EnrollmentStatus(String status) {
        this.status = status;
    }

    public static boolean canSignUp(EnrollmentStatus enrollmentStatus) {
        return enrollmentStatus == EnrollmentStatus.RECRUITING;
    }
}
