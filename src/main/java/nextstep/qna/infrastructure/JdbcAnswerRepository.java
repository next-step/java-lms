package nextstep.qna.infrastructure;

import java.util.List;
import nextstep.qna.domain.Answer;
import nextstep.qna.domain.AnswerRepository;
import org.springframework.stereotype.Repository;

@Repository("answerRepository")
public class JdbcAnswerRepository implements AnswerRepository {
    @Override
    public List<Answer> findByQuestion(Long questionId) {
        return null;
    }
}
