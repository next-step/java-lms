package nextstep.courses.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import nextstep.courses.domain.session.registration.RegistrationRepository;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;

@Service("sessionService")
public class SessionService {
	@Resource(name = "userRepository")
	private UserRepository userRepository;
	@Resource(name = "sessionRepository")
	private SessionRepository sessionRepository;
	@Resource(name = "registrationRepository")
	private RegistrationRepository registrationRepository;

	public void apply(Payment payment) {
		Session session = sessionRepository.findById(payment.getSessionId())
			.orElseThrow(() -> new IllegalArgumentException("일치하는 강의 id가 없습니다."));
		NsUser nsUser = userRepository.findById(payment.getNsUserId())
			.orElseThrow(() -> new IllegalArgumentException("일치하는 사용자 id가 없습니다."));

		session.apply(nsUser, payment.getAmount());
		sessionRepository.save(session);
	}
}
