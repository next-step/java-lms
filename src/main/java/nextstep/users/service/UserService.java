package nextstep.users.service;

import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;
import nextstep.users.exception.CannotFindUserException;

public class UserService {

    public static final String CANNOT_FIND_USER_EXCEPTION = "유저 아이디에 해당하는 유저를 찾을 수 없습니다.";
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public NsUser findUser(String userId) {
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> new CannotFindUserException(CANNOT_FIND_USER_EXCEPTION));
    }
}
