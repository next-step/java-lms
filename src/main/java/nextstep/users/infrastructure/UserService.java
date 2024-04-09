package nextstep.users.infrastructure;

import nextstep.exception.NsUserException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserService {

    @Resource(name = "userRepository")
    private UserRepository userRepository;

    public NsUser findByUserId(String userId) {
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> new NsUserException(String.format("%s 사용자는 존재하지 않습니다.", userId)));
    }
}
