package nextstep.courses.domain;

import java.time.LocalDate;
import java.util.Objects;

public class Session {
    private final Long id;

    private LocalDate startDate;
    private LocalDate endDate;

    public Session(Long id) {
        this.id = id;
        this.startDate = LocalDate.now();
        this.endDate = LocalDate.now().plusDays(14);
    }

    public Session(Long id, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(id, session.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void updatePeriod(String startDate, String endDate) {
        this.startDate = LocalDate.parse(startDate);
        this.endDate = LocalDate.parse(endDate);
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }
}
