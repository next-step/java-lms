package nextstep.courses.domain;

import java.util.List;

public interface SessionService {
    long save(Session session);

    Session findById(long id);

    long enroll(long id, List<String> users);
}
