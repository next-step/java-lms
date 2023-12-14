package nextstep.courses.service;

import nextstep.courses.domain.image.ImageRepository;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.domain.participant.SessionUserEnrolment;
import nextstep.courses.domain.participant.SessionUserEnrolmentRepository;
import nextstep.courses.exception.*;
import nextstep.courses.type.ParticipantSelectionStatus;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("sessionService")
public class SessionService {

    private SessionRepository sessionRepository;

    private ImageRepository imageRepository;

    private UserRepository userRepository;

    private SessionUserEnrolmentRepository sessionUserEnrolmentRepository;

    public SessionService(SessionRepository sessionRepository, ImageRepository imageRepository,
                          UserRepository userRepository, SessionUserEnrolmentRepository sessionUserEnrolmentRepository) {
        this.sessionRepository = sessionRepository;
        this.imageRepository = imageRepository;
        this.userRepository = userRepository;
        this.sessionUserEnrolmentRepository = sessionUserEnrolmentRepository;
    }

    @Transactional
    public void saveSession(Session session) {
        sessionRepository.save(session);
        if (session.images() != null) {
            imageRepository.saveAll(session.images());
        }
    }

    @Transactional(readOnly = true)
    public Session findById(Long id) {
        Session findSession = sessionRepository.findById(id);
        findSession.mappadByImage(imageRepository.findBySessionId(id));
        return findSession;
    }

    @Transactional
    public void SessionPayment(Payment payment) {
        Session session = sessionRepository.findById(payment.sessionId());
        NsUser nsUser = userRepository.findById(payment.nsUserId()).orElseThrow(NotFoundUserException::new);
        session.mappaedBySessionParticipants(sessionUserEnrolmentRepository.findBySessionId(session.id()));

        SessionUserEnrolment sessionUserEnrolment = new SessionUserEnrolment(nsUser.getId(), session.id(), ParticipantSelectionStatus.WAITING);
        session.addParticipant(payment.amount(), sessionUserEnrolment);

        sessionUserEnrolmentRepository.save(sessionUserEnrolment);
    }

    @Transactional
    public void accept(NsUser user, Long sessionId) {
        SessionUserEnrolment sessionUserEnrolment = sessionUserEnrolmentRepository.findBySessionIdAndUserId(sessionId, user.getId())
                .orElseThrow(NotFoundEnrolmentException::new);
        sessionUserEnrolmentRepository.updateSubscriptionStatus(sessionUserEnrolment.accept());
    }

    @Transactional
    public void reject(NsUser user, Long sessionId) {
        SessionUserEnrolment sessionUserEnrolment = sessionUserEnrolmentRepository.findBySessionIdAndUserId(sessionId, user.getId())
                .orElseThrow(NotFoundEnrolmentException::new);
        sessionUserEnrolmentRepository.updateSubscriptionStatus(sessionUserEnrolment.reject());
    }

    @Transactional
    public void approve(NsUser user, Long sessionId) {
        SessionUserEnrolment sessionUserEnrolment = sessionUserEnrolmentRepository.findBySessionIdAndUserId(sessionId, user.getId())
                .orElseThrow(NotFoundEnrolmentException::new);
        sessionUserEnrolmentRepository.updateApprovalStatus(sessionUserEnrolment.approve());
    }

    @Transactional
    public void cancel(NsUser user, Long sessionId) {
        SessionUserEnrolment sessionUserEnrolment = sessionUserEnrolmentRepository.findBySessionIdAndUserId(sessionId, user.getId())
                .orElseThrow(NotFoundEnrolmentException::new);
        sessionUserEnrolmentRepository.updateApprovalStatus(sessionUserEnrolment.cancel());
    }
}
