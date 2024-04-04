package nextstep.users;

import nextstep.qna.NotFoundException;
import nextstep.users.domain.NsUser;
import nextstep.users.infrastructure.JdbcUserRepository;

import java.util.Optional;

public class NsUserService {
    private final JdbcUserRepository userRepository;

    public NsUserService(JdbcUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public NsUser findById(Long userIdx) {
        return userRepository.findByUserId(userIdx.toString())
                .orElseThrow(NotFoundException::new);
    }
}
