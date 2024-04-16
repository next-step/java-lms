package nextstep.courses.service;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import org.springframework.stereotype.Service;

@Service
public class SessionService {
    private static final String SESSION_MUST_HAVE_COVER_IMAGE_MESSAGE = "세션에 하나 이상의 커버 이미지가 등록되어야 합니다.";

    private final SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    /*
     * 컨트롤러에서 예외를 핸들링하는 것을 가정하고 작성하였습니다.
     */
    public Long save(Session session) {
        if (session.isCoverImageEmpty()) {
            throw new IllegalStateException(SESSION_MUST_HAVE_COVER_IMAGE_MESSAGE);
        }
        return sessionRepository.saveSessionAndGetId(session);
    }

}
