package nextstep.courses.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateValidator {
    private static final DateTimeFormatter formatYyyymmdd = DateTimeFormatter.ofPattern("yyyyMMdd");

    public static void validateDate(String startDate, String endDate) {

    }

    public static void validateNullOrEmpty(String startDate, String endDate) {
        if (startDate == null || startDate.trim().isEmpty()) {
            throw new IllegalArgumentException("시작일자가 Null 또는 빈값 입니다.");
        }

        if (endDate == null || endDate.trim().isEmpty()) {
            throw new IllegalArgumentException("종료일자가 Null 또는 빈값 입니다.");
        }
    }

    public static void validateFormat(String startDate, String endDate) {
        try {
            LocalDate.parse(startDate.trim(), formatYyyymmdd);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("강의 시작일이 유효한 일자가 아닙니다.");
        }

        try {
            LocalDate.parse(endDate.trim(), formatYyyymmdd);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("강의 종료일이 유효한 일자가 아닙니다.");
        }
    }

    public static void validateDuration(String startDate, String endDate) {
        if (endDate.compareTo(startDate) < 0) {
            throw new IllegalArgumentException("종료일은 시작일보다 과거일 수 없습니다.");
        }

        if (startDate.compareTo(LocalDate.now().format(formatYyyymmdd)) < 0) {
            throw new IllegalArgumentException("시작일은 현재보다 과거일 수 없습니다.");
        }
    }
}
