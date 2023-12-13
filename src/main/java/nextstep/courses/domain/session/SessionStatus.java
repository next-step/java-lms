package nextstep.courses.domain.session;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.function.BiFunction;

public enum SessionStatus {
    NOT_STARTED("강의 시작 전", (startDate, endDate) -> startDate.isAfter(LocalDate.now())),
    IN_PROGRESS("강의 진행 중", (startDate, endDate) -> startDate.compareTo(LocalDate.now()) <= 0 && endDate.compareTo(LocalDate.now()) >= 0),
    COMPLETED("강의 종료", (startDate, endDate) -> endDate.isBefore(LocalDate.now()));

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
        return sessionStatus == SessionStatus.IN_PROGRESS;
    }

    public static SessionStatus fromDate(LocalDate startDate, LocalDate endDate) {
        return Arrays.stream(values())
                .filter(SessionStatus -> SessionStatus.checkStatus(startDate, endDate))
                .findFirst()
                .orElse(COMPLETED);
    }

}
