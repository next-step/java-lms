package nextstep.users.service;

import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public Optional<NsUser> findByUserId(String id) {
        return userRepository.findByUserId(id);
    }

    @Transactional(readOnly = true)
    public List<NsUser> findEnrolledUsersByIds(List<Long> enrolledUserIds) {
        return enrolledUserIds.stream()
                .map(userId -> findByUserId(userId.toString())
                        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다.")))
                .collect(Collectors.toList());
    }
} 