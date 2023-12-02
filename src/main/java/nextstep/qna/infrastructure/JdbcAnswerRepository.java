package nextstep.qna.infrastructure;

import nextstep.qna.domain.Answers;
import nextstep.qna.domain.AnswerRepository;
import org.springframework.stereotype.Repository;

@Repository("answerRepository")
public class JdbcAnswerRepository implements AnswerRepository {
    @Override
    public Answers findByQuestion(Long questionId) {
        return null;
    }
}
