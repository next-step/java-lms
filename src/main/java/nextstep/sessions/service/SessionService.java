package nextstep.sessions.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionRepository;
import nextstep.sessions.domain.Student;
import nextstep.sessions.domain.StudentRepository;
import nextstep.sessions.domain.Students;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;

@Service("sessionService")
public class SessionService {

	@Resource(name = "sessionRepository")
	private SessionRepository sessionRepository;

	@Resource(name = "studentRepository")
	private StudentRepository studentRepository;

	@Resource(name = "userRepository")
	private UserRepository userRepository;

	public void addSession(Session session) {
		sessionRepository.save(session);
	}

	@Transactional
	public void enroll(long sessionId, String userId) {
		Session session = sessionRepository.findById(sessionId);
		Students students = new Students(studentRepository.findBySessionId(sessionId));

		NsUser nsUser = userRepository.findByUserId(userId).orElse(NsUser.GUEST_USER);

		Student student = session.enroll(nsUser, students);
		studentRepository.save(student);
	}
}
