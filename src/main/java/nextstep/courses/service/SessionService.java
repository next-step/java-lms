package nextstep.courses.service;

import nextstep.courses.domain.CourseRepository;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import nextstep.courses.domain.SessionUserRepository;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("sessionService")
public class SessionService {
    @Resource(name = "sessionRepository")
    private SessionRepository sessionRepository;

    @Resource(name = "sessionUserRepository")
    private SessionUserRepository sessionUserRepository;

    @Resource(name = "courseRepository")
    private CourseRepository courseRepository;

    @Transactional
    public void save(Session session) {
        sessionRepository.save(session);
    }

    @Transactional
    public void enrollUsers(Session session, List<NsUser> users) {
        users.forEach(session::enroll);
        sessionUserRepository.saveAll(session.getSessionUsers());
    }
}
