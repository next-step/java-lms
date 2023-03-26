package users.infrastructure;

import org.springframework.stereotype.Repository;
import users.domain.NsUser;
import users.domain.UserRepository;

import java.util.Optional;

@Repository("userRepository")
public class JdbcUserRepository implements UserRepository {
    @Override
    public Optional<NsUser> findByUserId(String userId) {
        return Optional.empty();
    }
}
