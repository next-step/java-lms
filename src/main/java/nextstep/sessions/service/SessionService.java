package nextstep.sessions.service;

import java.time.LocalDateTime;
import javax.annotation.Resource;
import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("sessionService")
public class SessionService {

    @Resource(name = "sessionRepository")
    private SessionRepository sessionRepository;

    @Transactional
    public void save(String title, String contents, LocalDateTime startDateTime,
        LocalDateTime endDateTime, byte[] coverImage, int capacity) {
        Session session = new Session(
            title,
            contents,
            startDateTime,
            endDateTime,
            coverImage,
            capacity);

        sessionRepository.save(session);
    }

    // start / end각각으로 분리하는 게 아니라 엔티티에 대해 update할 내용을 dto로 받아서
    // 수정하는 방식이라면 내용이 수정되어야 한다
    @Transactional
    public void recruitStart(Long sessionId) {
        Session session = findSessionById(sessionId);
        session.recruitStart();

        sessionRepository.update(session);
    }

    @Transactional
    public void recruitEnd(Long sessionId) {
        Session session = findSessionById(sessionId);
        session.recruitEnd();

        sessionRepository.update(session);
    }

    private Session findSessionById(Long id) {
        return sessionRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 강의입니다."));
    }
}