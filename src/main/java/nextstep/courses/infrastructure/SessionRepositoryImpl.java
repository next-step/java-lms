package nextstep.courses.infrastructure;

import nextstep.courses.domain.image.SessionCoverImage;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.domain.session.SessionState;
import nextstep.courses.infrastructure.entity.SessionCoverImageEntity;
import nextstep.courses.infrastructure.entity.SessionEntity;
import nextstep.courses.infrastructure.jdbc.SessionJdbcDao;
import nextstep.courses.infrastructure.mapper.SessionCoverImageMapper;
import nextstep.courses.infrastructure.mapper.SessionMapper;
import org.springframework.stereotype.Repository;

@Repository("sessionRepository")
public class SessionRepositoryImpl implements SessionRepository {
    private final SessionJdbcDao sessionJdbcDao;

    public SessionRepositoryImpl(SessionJdbcDao sessionJdbcDao) {
        this.sessionJdbcDao = sessionJdbcDao;
    }

    @Override
    public int save(Session session) {
        SessionEntity entity = SessionMapper.toEntity(session);
        return sessionJdbcDao.save(entity);
    }

    @Override
    public Session findById(Long id) {
        SessionEntity entity = sessionJdbcDao.findById(id);
        SessionCoverImage coverImage = findCoverImageBySessionId(id);
        return SessionMapper.toDomain(entity, coverImage);
    }

    private SessionCoverImage findCoverImageBySessionId(Long sessionId) {
        SessionCoverImageEntity entity = sessionJdbcDao.findCoverImageBySessionId(sessionId);
        if (entity == null) {
            return null;
        }
        return SessionCoverImageMapper.toDomain(entity);
    }

    @Override
    public int updateState(Long id, SessionState state) {
        return sessionJdbcDao.updateState(id, state);
    }
}