package nextstep.courses.infrastructure;

import java.util.List;
import java.util.stream.Collectors;
import nextstep.courses.domain.registration.Registration;
import nextstep.courses.domain.registration.RegistrationRepository;
import nextstep.courses.infrastructure.entity.RegistrationEntity;
import nextstep.courses.infrastructure.jdbc.RegistrationJdbcDao;
import nextstep.courses.infrastructure.mapper.RegistrationMapper;
import org.springframework.stereotype.Repository;

@Repository("registrationRepository")
public class RegistrationRepositoryImpl implements RegistrationRepository {
    private final RegistrationJdbcDao registrationJdbcDao;

    public RegistrationRepositoryImpl(RegistrationJdbcDao registrationJdbcDao) {
        this.registrationJdbcDao = registrationJdbcDao;
    }

    @Override
    public int save(Registration registration) {
        RegistrationEntity entity = RegistrationMapper.toEntity(registration);
        return registrationJdbcDao.save(entity);
    }

    @Override
    public Registration findById(Long id) {
        RegistrationEntity entity = registrationJdbcDao.findById(id);
        return RegistrationMapper.toDomain(entity);
    }

    @Override
    public List<Registration> findBySessionId(Long sessionId) {
        List<RegistrationEntity> entities = registrationJdbcDao.findBySessionId(sessionId);
        return entities.stream()
            .map(RegistrationMapper::toDomain)
            .collect(Collectors.toList());
    }

    @Override
    public int countBySessionId(Long sessionId) {
        return registrationJdbcDao.countBySessionId(sessionId);
    }
}