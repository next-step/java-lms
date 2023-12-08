package nextstep.session.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;
import nextstep.image.domain.Image;
import nextstep.image.domain.ImageCapacity;
import nextstep.image.domain.ImageSize;
import nextstep.image.domain.ImageType;
import nextstep.session.domain.Enrollment;
import nextstep.session.domain.PaymentType;
import nextstep.session.domain.Session;
import nextstep.session.domain.SessionParticipants;
import nextstep.session.domain.SessionPayment;
import nextstep.session.domain.SessionPeriod;
import nextstep.session.domain.SessionStatus;
import nextstep.session.domain.SessionType;
import nextstep.session.dto.EnrollSessionRequest;
import nextstep.session.repository.MockSessionRepository;
import nextstep.users.domain.NsUser;
import nextstep.users.infrastructure.MockUserRepository;
import nextstep.users.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SessionServiceTest {

    @Test
    @DisplayName("강의를 수강신청한다.")
    void enroll_session() {
        // given
        EnrollSessionRequest request = new EnrollSessionRequest(1L, 10000, "wlscww");

        // when
        MockSessionRepository sessionRepository = new MockSessionRepository();
        SessionService sessionService = new SessionService(
                sessionRepository,
                new UserService(new MockUserRepository())
        );
        sessionService.enrollSession(request);

        // then
        Session findSession = sessionRepository.findById(request.getSessionId());

        assertThat(findSession.getEnrollment().getPayments()).contains(
                new SessionPayment(new NsUser(0L, "tempId", "5678", "이수찬", "email"), PaymentType.COMPLETED),
                new SessionPayment(new NsUser(1L, "wlscww", "1234", "이수찬", "email"), PaymentType.COMPLETED));
        assertThat(findSession.getEnrollment().getParticipants().getParticipants()).contains(
                new NsUser(0L, "tempId", "5678", "이수찬", "email"),
                new NsUser(1L, "wlscww", "1234", "이수찬", "email"));
    }
}
