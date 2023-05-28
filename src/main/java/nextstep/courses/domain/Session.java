package nextstep.courses.domain;

import java.time.LocalDate;
import java.util.Objects;

public class Session {
    private final Long id;

    private final Period period;

    private final Image image;

    public Session(Long id) {
        this.id = id;
        this.period = new Period(LocalDate.now(), LocalDate.now());
        this.image = new Image();
    }

    public Session(Long id, String startDate, String endDate, String url) {
        this.id = id;
        this.period = new Period(startDate, endDate);
        this.image = new Image(url);
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
        period.updatePeriod(startDate, endDate);
    }

    public Period getPeriod() {
        return period;
    }

    public Image getImage() {
        return image;
    }
}
