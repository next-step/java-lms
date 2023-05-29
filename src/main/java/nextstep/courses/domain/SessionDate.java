package nextstep.courses.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SessionDate {
    private String startDate;
    private String endDate;
    private static final DateTimeFormatter formatYyyymmdd = DateTimeFormatter.ofPattern("yyyyMMdd");

    public SessionDate(String startDate, String endDate) {
        this(startDate, endDate, true);
    }

    public SessionDate(String startDate, String endDate, boolean checkDuration) {
        DateValidator.validateNullOrEmpty(startDate, endDate);
        DateValidator.validateFormat(startDate, endDate);

        if (checkDuration) {
            DateValidator.validateDuration(startDate, endDate);
        }

        this.startDate = startDate.trim();
        this.endDate = endDate.trim();
    }

    public boolean isExpired() {
        return endDate.compareTo(LocalDate.now().format(formatYyyymmdd)) < 0;
    }
}
