package nextstep.users.service;

import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public NsUser getUser(String userId) {
        return userRepository.findByUserId(userId);
    }

    public List<NsUser> getUsers(List<String> userIds) {
        return userRepository.findByUserIds(userIds);
    }

    public Long saveUser(NsUser nsUser) {
        return userRepository.save(nsUser);
    }

    public boolean canApprove(String approverId, String applicantId) {
        NsUser approver = userRepository.findByUserId(approverId);
        NsUser applicant = userRepository.findByUserId(applicantId);
        return approver.canApprove(applicant);
    }

    public boolean canCancel(String approverId, String applicantId) {
        NsUser approver = userRepository.findByUserId(approverId);
        NsUser applicant = userRepository.findByUserId(applicantId);
        return approver.canCancel(applicant);
    }
}
