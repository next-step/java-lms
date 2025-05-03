package nextstep.stub.repository;

import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestUserRepository implements UserRepository {
    private final Long saveResult;
    private final Map<String, NsUser> users;
    private int saveCalled = 0;
    private int findByUserIdCalled = 0;
    private int findByUserIdsCalled = 0;

    public TestUserRepository() {
        this(null, new HashMap<>());
    }

    public TestUserRepository(Long saveResult) {
        this(saveResult, new HashMap<>());
    }

    public TestUserRepository(Map<String, NsUser> users) {
        this(null, users);
    }

    public TestUserRepository(Long saveResult, Map<String, NsUser> users) {
        this.saveResult = saveResult;
        this.users = users;
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

    @Override
    public List<NsUser> findByUserIds(List<String> userIds) {
        findByUserIdsCalled++;
        List<NsUser> res = new ArrayList<>();
        for (String userId : userIds) {
            res.add(users.get(userId));
        }
        return res;
    }

    public int getSaveCalled() {
        return saveCalled;
    }

    public int getFindByUserIdCalled() {
        return findByUserIdCalled;
    }

    public int getFindByUserIdsCalled() {
        return findByUserIdsCalled;
    }
}
