package nextstep.session.repository;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
import nextstep.users.domain.NsUser;

public class MockSessionRepository implements SessionRepository {

    private Map<Long, Session> sessionStorage = new LinkedHashMap<>();

    public MockSessionRepository() {
        this.sessionStorage.put(1L, new Session(
                new SessionPeriod(LocalDate.of(2023, 12, 20), LocalDate.of(2023, 12, 30)),
                new Image(new ImageCapacity(10, "kb"), ImageType.JPEG, new ImageSize(600, 400)),
                SessionType.PAID,
                SessionStatus.RECRUITING,
                new Enrollment(new SessionParticipants(List.of(new NsUser(0L, "tempId", "5678", "이수찬", "email")), 3),
                        List.of(new SessionPayment(new NsUser(0L, "tempId", "5678", "이수찬", "email"),
                                PaymentType.COMPLETED)),
                        10000
                ),
                10000
        ));
    }

    @Override
    public Long save(Session session) {
        long size = sessionStorage.size();
        long sessionId = size + 1;
        sessionStorage.put(sessionId, session);
        return sessionId;
    }

    @Override
    public Session findById(Long id) {
        return Optional.ofNullable(sessionStorage.get(id))
                .orElseThrow(() -> new IllegalStateException("강의를 찾을 수 없습니다."));
    }
}
