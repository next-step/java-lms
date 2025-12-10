package nextstep.courses.infrastructure;

import java.util.List;
import java.util.stream.Collectors;
import nextstep.courses.domain.image.SessionCoverImage;
import nextstep.courses.domain.image.SessionCoverImages;
import nextstep.courses.domain.image.SessionImageRepository;
import nextstep.courses.infrastructure.entity.SessionCoverImageEntity;
import nextstep.courses.infrastructure.jdbc.SessionImageJdbcDao;
import nextstep.courses.infrastructure.mapper.SessionCoverImageMapper;
import org.springframework.stereotype.Repository;

@Repository("sessionImageRepository")
public class SessionImageRepositoryImpl implements SessionImageRepository {
    private final SessionImageJdbcDao sessionImageJdbcDao;

    public SessionImageRepositoryImpl(SessionImageJdbcDao sessionImageJdbcDao) {
        this.sessionImageJdbcDao = sessionImageJdbcDao;
    }

    @Override
    public int save(SessionCoverImage image) {
        SessionCoverImageEntity entity = SessionCoverImageMapper.toEntity(image);
        return sessionImageJdbcDao.save(entity);
    }

    @Override
    public SessionCoverImage findById(Long id) {
        SessionCoverImageEntity entity = sessionImageJdbcDao.findById(id);
        return SessionCoverImageMapper.toDomain(entity);
    }

    @Override
    public SessionCoverImages findBySessionId(Long sessionId) {
        List<SessionCoverImageEntity> entity = sessionImageJdbcDao.findBySessionId(sessionId);
        return new SessionCoverImages(entity.stream().map(SessionCoverImageMapper::toDomain).collect(Collectors.toList()));
    }
}