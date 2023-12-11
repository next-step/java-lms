package nextstep.courses.domain.session;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.function.BiFunction;

public enum SessionStatus {
    PREPARING("준비중", (startDate, endDate) -> startDate.isAfter(LocalDate.now())),
    RECRUITING("모집중", (startDate, endDate) -> startDate.compareTo(LocalDate.now()) <= 0 && endDate.compareTo(LocalDate.now()) >= 0),
    CLOSE("종료", (startDate, endDate) -> endDate.isBefore(LocalDate.now()));

    private String statusName;
    private BiFunction<LocalDate, LocalDate, Boolean> getStatus;

    SessionStatus(String statusName, BiFunction<LocalDate, LocalDate, Boolean> getStatus) {
        this.statusName = statusName;
        this.getStatus = getStatus;
    }

    private boolean checkStatus(LocalDate startDate, LocalDate endDate) {
        return getStatus.apply(startDate, endDate);
    }

    public static boolean canSignUp(LocalDate startDate, LocalDate endDate) {
        SessionStatus sessionStatus = fromDate(startDate, endDate);
        System.out.println("sessionStatus = " + sessionStatus);
        return sessionStatus == SessionStatus.RECRUITING;
    }

    public static SessionStatus fromDate(LocalDate startDate, LocalDate endDate) {
        validateDate(startDate, endDate);
        return Arrays.stream(values())
                .filter(SessionStatus -> SessionStatus.checkStatus(startDate, endDate))
                .findFirst()
                .orElse(CLOSE);
    }

    private static void validateDate(LocalDate startDate, LocalDate endDate) {
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("강의의 시작일자와 종료일자는 필수 정보입니다.");
        }
    }

}
