package qna.domain;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByUserId(String userId);
}
