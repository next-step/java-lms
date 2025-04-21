package nextstep.stub.repository;

import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;

import java.util.HashMap;
import java.util.Map;

public class TestUserRepository implements UserRepository {
    private final Long saveResult;
    private final Map<String, NsUser> users = new HashMap<>();
    private int findByUserIdCalled = 0;
    private int saveCalled = 0;

    public TestUserRepository() {
        this(null);
    }

    public TestUserRepository(Long saveResult) {
        this.saveResult = saveResult;
    }

    public void addUser(String userId, NsUser user) {
        users.put(userId, user);
    }

    @Override
    public long save(NsUser nsUser) {
        saveCalled++;
        return saveResult;
    }

    @Override
    public NsUser findByUserId(String userId) {
        findByUserIdCalled++;
        return users.get(userId);
    }

    public Long getSaveResult() {
        return saveResult;
    }

    public int getSaveCalled() {
        return saveCalled;
    }
}
