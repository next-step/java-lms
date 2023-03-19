package qna.infrastructure;

import org.springframework.stereotype.Repository;
import qna.domain.User;
import qna.domain.UserRepository;

import java.util.Optional;

@Repository("userRepository")
public class JdbcUserRepository implements UserRepository {
    @Override
    public Optional<User> findByUserId(String userId) {
        return Optional.empty();
    }
}
