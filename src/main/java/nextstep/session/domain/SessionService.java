package nextstep.session.domain;

import nextstep.payments.domain.Payment;
import nextstep.session.domain.Cover;
import nextstep.session.domain.Session;
import nextstep.session.domain.Student;
import nextstep.session.dto.SessionUpdateBasicPropertiesDto;
import nextstep.users.domain.NsUser;

public interface SessionService {

    long save(Session session);

    Session findById(long sessionId);

    int updateBasicProperties(long sessionId, SessionUpdateBasicPropertiesDto sessionUpdateDto);

    int updateCover(long sessionId, long oldCoverId, Cover newCover, NsUser requestUser);

    Session apply(long sessionId, Payment payment, Student student);

    Session deleteStudent(long sessionId, Student student, NsUser requestUser);

    void delete(long sessionId, NsUser requestUser);
}
