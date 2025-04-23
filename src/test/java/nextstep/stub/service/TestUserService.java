package nextstep.stub.service;

import nextstep.stub.repository.TestUserRepository;
import nextstep.users.service.UserService;

public class TestUserService extends UserService {
    public TestUserService() {
        super(new TestUserRepository());
    }
}
