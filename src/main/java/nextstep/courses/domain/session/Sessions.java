package nextstep.courses.domain.session;

import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Sessions {
    private final List<Session> sessions = new ArrayList<>();

    public void init(List<Session> sessionsForInit) {
        sessions.addAll(sessionsForInit);
    }

    public Session findSession(Long sessionId) {
        return sessions.stream()
                .filter(session -> sessionId.equals(session.getId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 강의를 찾을 수 없습니다."));
    }

    public Session create(
            Long courseId,
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
                courseId,
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
