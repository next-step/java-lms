package nextstep.session.domain;

import java.util.List;

public interface AdmissionRepository {
    void save(Admission admission);

    List<Admission> findAllBySessionId(Long sessionId);
}
