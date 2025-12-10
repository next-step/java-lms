package nextstep.courses.infrastructure;

import java.util.List;
import java.util.stream.Collectors;
import nextstep.courses.domain.image.SessionCoverImage;
import nextstep.courses.domain.image.SessionCoverImages;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionProgressState;
import nextstep.courses.domain.session.SessionRepository;
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
        SessionCoverImages coverImages = findCoverImagesBySessionId(id);
        return SessionMapper.toDomain(entity, coverImages);
    }

    private SessionCoverImages findCoverImagesBySessionId(Long sessionId) {
        List<SessionCoverImageEntity> entity = sessionJdbcDao.findCoverImagesBySessionId(sessionId);
        if (entity == null) {
            return null;
        }
        return new SessionCoverImages(entity.stream().map(SessionCoverImageMapper::toDomain).collect(Collectors.toList()));
    }

    @Override
    public int updateState(Long id, SessionProgressState state) {
        return sessionJdbcDao.updateState(id, state);
    }
}