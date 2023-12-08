package nextstep.sessions.service;

import java.util.List;

import nextstep.payments.domain.Payment;
import nextstep.sessions.domain.data.Session;
import nextstep.sessions.domain.data.type.ApprovalType;
import nextstep.sessions.domain.data.type.SelectionType;
import nextstep.sessions.domain.data.vo.Registration;
import nextstep.sessions.domain.exception.SessionsException;
import nextstep.sessions.repository.RegistrationRepository;
import nextstep.sessions.repository.SessionRepository;
import nextstep.users.domain.NsUser;

public class SessionService {

    private final SessionRepository sessionRepository;
    private final RegistrationRepository registrationRepository;

    public SessionService(SessionRepository sessionRepository, RegistrationRepository registrationRepository) {
        this.sessionRepository = sessionRepository;
        this.registrationRepository = registrationRepository;
    }

    public void enroll(int sessionId, NsUser loginUser, Payment payment) {
        Session session = sessionRepository.findById(sessionId)
            .orElseThrow(() -> new SessionsException("강의 정보가 없습니다."));
        List<Registration> registrations = registrationRepository.findAllById(sessionId);
        Session sessionWithRegistrations = session.with(registrations);
        Registration registration = sessionWithRegistrations.registration(loginUser, payment);
        registrationRepository.save(sessionId, registration);
    }

    public void select(int registrationId) {
        registration(registrationId).validateSelection();
        registrationRepository.updateSelectionType(registrationId, SelectionType.SELECTION);
    }

    public void approve(int registrationId) {
        registration(registrationId).validateApproval();
        registrationRepository.updateApprovalType(registrationId, ApprovalType.APPROVAL);
    }

    public void cancel(int registrationId) {
        registration(registrationId).validateCancel();
        registrationRepository.deleteById(registrationId);
    }

    private Registration registration(int registrationId) {
        return registrationRepository.findById(registrationId)
            .orElseThrow(() -> new SessionsException("등록된 수강 정보가 없습니다."));
    }

}
