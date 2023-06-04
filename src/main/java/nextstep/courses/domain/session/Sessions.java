package nextstep.courses.domain.session;

import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Sessions {
    private List<Session> sessions;

    public Session findSession(Long sessionId) {
        return sessions.stream()
                .filter(session -> sessionId.equals(session.getId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 강의를 찾을 수 없습니다."));
    }

    public Session create(
            NsUser nsUser,
            String title,
            Long sessionNumber,
            LocalDate startDate,
            LocalDate endDate,
            String url,
            SessionFeeType sessionFeeType,
            SessionStatus sessionStatus,
            int capacity
    ) {
        Session session = new Session(
                0L,
                title,
                sessionNumber,
                new SessionPeriod(startDate, endDate),
                new CoverImage(url),
                new Students(capacity, sessionFeeType, sessionStatus),
                nsUser.getId(),
                LocalDateTime.now(),
                null
        );
        sessions.add(session);
        return session;
    }
}
