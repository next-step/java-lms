package nextstep.users.infrastructure;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;

public class MockUserRepository implements UserRepository {

    private Map<Long, NsUser> userStorage = new LinkedHashMap<>();

    public MockUserRepository() {
        this.userStorage = new LinkedHashMap<>();
        userStorage.put(1L, new NsUser(1L, "wlscww", "1234", "이수찬", "email"));
    }

    @Override
    public Optional<NsUser> findByUserId(String userId) {
        return Optional.ofNullable(userStorage.get(1L));
    }
}
