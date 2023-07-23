package nextstep.courses.infrastructure;

import nextstep.courses.domain.SignUpHistory;
import nextstep.courses.domain.SignUpHistoryRepository;
import org.springframework.stereotype.Repository;

@Repository("signUpHistoryRepository")
public class JdbcSignUpHistoryRepository implements SignUpHistoryRepository {

    @Override
    public int save(SignUpHistory signUpHistory) {
        return 0;
    }



}
