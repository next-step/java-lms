package nextstep.session.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class SessionUpdateBasicPropertiesDto {

    public static final int NOTHING_TO_UPDATE_COUNT = 0;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String sessionName;

    public SessionUpdateBasicPropertiesDto(LocalDateTime startDate, LocalDateTime endDate, String sessionName) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.sessionName = sessionName;
    }

    public boolean hasStartDate() {
        return Objects.nonNull(startDate);
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public boolean hasEndDate() {
        return Objects.nonNull(endDate);
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public boolean hasSessionName() {
        return Objects.nonNull(sessionName);
    }

    public String getSessionName() {
        return sessionName;
    }

    public int countNotNullProperties() {
        int count = 0;
        if (hasStartDate()) {
            count++;
        }

        if (hasEndDate()) {
            count++;
        }

        if (hasSessionName()) {
            count++;
        }

        validateUpdateCount(count);
        return count;
    }

    private void validateUpdateCount(int count) {
        if (count == NOTHING_TO_UPDATE_COUNT) {
            throw new IllegalArgumentException("Session의 변경 조건이 존재하지 않습니다.");
        }
    }
}
