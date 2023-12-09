package nextstep.sessions.service;

import java.util.List;

import nextstep.payments.domain.Payment;
import nextstep.sessions.domain.data.registration.Registration;
import nextstep.sessions.domain.data.session.Enrollment;
import nextstep.sessions.domain.data.session.Session;
import nextstep.sessions.domain.exception.NotFoundRegistrationException;
import nextstep.sessions.domain.exception.NotFoundSessionException;
import nextstep.sessions.repository.RegistrationRepository;
import nextstep.sessions.repository.SessionRepository;
import nextstep.users.domain.NsUser;

public class RegistrationService {

    private final SessionRepository sessionRepository;
    private final RegistrationRepository registrationRepository;

    public RegistrationService(SessionRepository sessionRepository, RegistrationRepository registrationRepository) {
        this.sessionRepository = sessionRepository;
        this.registrationRepository = registrationRepository;
    }

    public void enroll(int sessionId, NsUser loginUser, Payment payment) {
        Session session = sessionRepository.findById(sessionId)
            .orElseThrow(() -> new NotFoundSessionException("강의 정보가 없습니다."));
        List<Registration> registrations = registrationRepository.findAllById(sessionId);

        Enrollment enrollment = session.enrollment(registrations);
        Registration registration = enrollment.registration(session, loginUser, payment);
        enrollment.enroll(registration);
        registrationRepository.save(registration);
    }

    public void select(int registrationId) {
        Registration registration = registration(registrationId);
        registration.select();
        registrationRepository.updateSelectionType(registration);
    }

    public void approve(int registrationId) {
        Registration registration = registration(registrationId);
        registration.approve();
        registrationRepository.updateApprovalType(registration);
    }

    public void cancel(int registrationId) {
        Registration registration = registration(registrationId);
        registration.cancel();
        registrationRepository.deleteById(registrationId);
    }

    private Registration registration(int registrationId) {
        return registrationRepository.findById(registrationId)
            .orElseThrow(() -> new NotFoundRegistrationException("등록된 수강 정보가 없습니다."));
    }

}
