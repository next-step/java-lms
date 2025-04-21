package nextstep.stub;

import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;

public class TestUserRepository implements UserRepository {
    private final Long saveResult;
    private final NsUser findByUserIdResult;
    private int findByUserIdCalled = 0;
    private int saveCalled = 0;

    public TestUserRepository(Long saveResult) {
        this(saveResult, null);
    }

    public TestUserRepository(NsUser findByUserIdResult) {
        this(null, findByUserIdResult);
    }

    public TestUserRepository(Long saveResult, NsUser findByUserIdResult) {
        this.saveResult = saveResult;
        this.findByUserIdResult = findByUserIdResult;
    }

    @Override
    public long save(NsUser nsUser) {
        saveCalled++;
        return saveResult;
    }

    @Override
    public NsUser findByUserId(String userId) {
        findByUserIdCalled++;
        return findByUserIdResult;
    }

    public Long getSaveResult() {
        return saveResult;
    }

    public int getSaveCalled() {
        return saveCalled;
    }
}
