package nextstep.courses.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SessionDate {
    private String startDate;
    private String endDate;
    private static final DateTimeFormatter format_yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMdd");

    public SessionDate(String startDate, String endDate) {
        this(startDate, endDate, true);
    }

    public SessionDate(String startDate, String endDate, boolean checkDuration) {
        validateNullOrEmpty(startDate, endDate);
        validateFormat(startDate, endDate);

        if (checkDuration) {
            validateDuration(startDate, endDate);
        }

        this.startDate = startDate.trim();
        this.endDate = endDate.trim();
    }

    private void validateNullOrEmpty(String startDate, String endDate) {
        if (startDate == null || startDate.trim().isEmpty()) {
            throw new IllegalArgumentException("시작일자가 Null 또는 빈값 입니다.");
        }

        if (endDate == null || endDate.trim().isEmpty()) {
            throw new IllegalArgumentException("종료일자가 Null 또는 빈값 입니다.");
        }
    }

    private void validateFormat(String startDate, String endDate) {
        try {
            LocalDate.parse(startDate.trim(), format_yyyyMMdd);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("강의 시작일이 유효한 일자가 아닙니다.");
        }

        try {
            LocalDate.parse(endDate.trim(), format_yyyyMMdd);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("강의 종료일이 유효한 일자가 아닙니다.");
        }
    }

    private void validateDuration(String startDate, String endDate) {
        if (endDate.compareTo(startDate) < 0) {
            throw new IllegalArgumentException("종료일은 시작일보다 과거일 수 없습니다.");
        }

        if (startDate.compareTo(LocalDate.now().format(format_yyyyMMdd)) < 0) {
            throw new IllegalArgumentException("시작일은 현재보다 과거일 수 없습니다.");
        }
    }

    public boolean isExpired() {
        return endDate.compareTo(LocalDate.now().format(format_yyyyMMdd)) < 0;
    }
}
