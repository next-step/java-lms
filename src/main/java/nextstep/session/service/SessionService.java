package nextstep.session.service;

import javax.annotation.Resource;
import nextstep.session.CannotEnrollException;
import nextstep.session.StudentAlreadyEnrolledException;
import nextstep.session.domain.Session;
import nextstep.session.domain.SessionCoverImageRepository;
import nextstep.session.domain.SessionRepository;
import nextstep.session.domain.SessionStudentRepository;
import nextstep.session.domain.Student;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("sessionService")
public class SessionService {

    @Resource(name = "sessionRepository")
    private SessionRepository sessionRepository;
    @Resource(name = "sessionCoverImageRepository")
    private SessionCoverImageRepository sessionCoverImageRepository;
    @Resource(name = "sessionStudentRepository")
    private SessionStudentRepository sessionStudentRepository;

    @Transactional
    public void enroll(Long id, NsUser user, int payment)
        throws StudentAlreadyEnrolledException, CannotEnrollException {
        Session session = sessionRepository.findById(id);
        Student student = session.enroll(user, payment);
        sessionStudentRepository.save(student);
    }

    @Transactional
    public void createSession(Session session) {
        sessionRepository.save(session);
        sessionCoverImageRepository.save(session.getCoverImage());
    }

}
