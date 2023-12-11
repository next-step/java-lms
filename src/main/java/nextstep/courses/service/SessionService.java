package nextstep.courses.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import nextstep.courses.domain.enums.ApprovalStatus;
import nextstep.courses.domain.session.registration.Registration;
import nextstep.courses.domain.session.registration.RegistrationRepository;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.domain.session.registration.Students;
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

	public void cancelRegistration(Long registrationId) {
		Registration registration = registrationRepository.findById(registrationId)
			.orElseThrow(() -> new IllegalArgumentException("일치하는 수강 신청 id가 없습니다."));
		registration.cancel();
	}

	public void approveRegistration(Long sessionId, Long registrationId) {
		Session session = sessionRepository.findById(sessionId)
			.orElseThrow(() -> new IllegalArgumentException("일치하는 강의 id가 없습니다."));
		session.checkCanApprove();

		Registration registration = registrationRepository.findById(registrationId)
			.orElseThrow(() -> new IllegalArgumentException("일치하는 수강 신청 id가 없습니다."));
		registration.approve();
	}
}
