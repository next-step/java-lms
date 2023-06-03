package nextstep.courses.domain;


import java.time.LocalDate;
import java.util.Objects;

public class CourseDuration {
    private static final String TYPE_1 = "시작일과 종료일은 모두 선택해 줘야 됩니다.";
    private static final String TYPE_2 = "종료일이 시작일보다 이전입니다.";
    private static final String TYPE_3 = "시작일은 현재시간의 최소 7일 이후에 등록해야됩니다.";
    private static final int MIN_DURATION = 7;

    private LocalDate startedAt;
    private LocalDate endedAt;

    public CourseDuration(LocalDate startedAt, LocalDate endedAt) {
        checkCourseDuration(startedAt, endedAt);
        this.startedAt = startedAt;
        this.endedAt = endedAt;
    }

    void checkCourseDuration(LocalDate startedAt, LocalDate endedAt) {
        if (Objects.isNull(startedAt)) {
            throw new IllegalArgumentException(TYPE_1);
        }
        if (Objects.isNull(endedAt)) {
            throw new IllegalArgumentException(TYPE_1);
        }
        if (!validateStartDate(startedAt)) {
            throw new IllegalArgumentException(TYPE_3);
        }
        if (!validateDateRange(startedAt, endedAt)) {
            throw new IllegalArgumentException(TYPE_2);
        }
    }
    private boolean validateDateRange(LocalDate startDate, LocalDate endDate) {
        return !endDate.isBefore(startDate);
    }

    public static boolean validateStartDate(LocalDate startDate) {
        LocalDate minStartDate = LocalDate.now().plusDays(MIN_DURATION); // 최소 7일 이전 날짜 계산
        return startDate.isAfter(minStartDate);
    }
}
