package nextstep.courses.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Sessions {

    List<Session> values = new ArrayList<>();

    public Sessions() {
    }

    public Sessions(List<Session> values) {
        this.values = values;
    }

    public Session openSession(Long id,
                            Course course,
                            LocalDate sessionStartDate,
                            LocalDate sessionEndDate,
                            String imageUrl,
                            boolean paid,
                            int recruitmentCount) {
        Session session = Session.open(id, course, sessionStartDate, sessionEndDate, imageUrl, paid, recruitmentCount);
        values.add(session);
        return session;
    }

}
