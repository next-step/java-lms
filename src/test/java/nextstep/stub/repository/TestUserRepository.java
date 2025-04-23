package nextstep.stub.repository;

import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestUserRepository implements UserRepository {
    private final Long saveResult;
    private final Map<String, NsUser> users = new HashMap<>();

    public TestUserRepository() {
        this(null);
    }

    public TestUserRepository(Long saveResult) {
        this.saveResult = saveResult;
    }

    @Override
    public long save(NsUser nsUser) {
        return saveResult;
    }

    @Override
    public NsUser findByUserId(String userId) {
        return users.get(userId);
    }

    @Override
    public List<NsUser> findByUserIds(List<String> userIds) {
        List<NsUser> res = new ArrayList<>();
        for (String userId : userIds) {
            res.add(users.get(userId));
        }
        return res;
    }
}
