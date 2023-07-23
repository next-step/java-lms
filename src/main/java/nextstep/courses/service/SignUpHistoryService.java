package nextstep.courses.service;

import nextstep.courses.domain.SignUpHistory;
import nextstep.courses.domain.SignUpHistoryRepository;
import org.springframework.stereotype.Service;

@Service
public class SignUpHistoryService {

    private final SignUpHistoryRepository signUpHistoryRepository;

    public SignUpHistoryService(SignUpHistoryRepository signUpHistoryRepository) {
        this.signUpHistoryRepository = signUpHistoryRepository;
    }

    public void save(SignUpHistory signUpHistory) {
        signUpHistoryRepository.save(signUpHistory);
    }

}
