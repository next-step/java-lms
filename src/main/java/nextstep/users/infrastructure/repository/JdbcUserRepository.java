package nextstep.users.infrastructure.repository;

import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;
import nextstep.users.infrastructure.dao.NsUserEntityRepository;
import nextstep.users.infrastructure.entity.NsUserEntity;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * root Domain의 Repository 느낌
 */
@Repository("userRepository")
public class JdbcUserRepository implements UserRepository {
    private final NsUserEntityRepository nsUserEntityRepository;

    public JdbcUserRepository(NsUserEntityRepository nsUserEntityRepository) {
        this.nsUserEntityRepository = nsUserEntityRepository;
    }

    @Override
    public Optional<NsUser> findByUserId(String userId) {
        Optional<NsUserEntity> nsUserEntity = nsUserEntityRepository.findByUserId(userId);
        return nsUserEntity.map(NsUserEntity::toDomain);
    }

}
