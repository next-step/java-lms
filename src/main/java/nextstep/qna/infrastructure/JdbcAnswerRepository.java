package nextstep.qna.infrastructure;

import nextstep.qna.domain.answer.AnswerRepository;
import nextstep.qna.domain.answer.Answers;
import org.springframework.stereotype.Repository;

@Repository("answerRepository")
public class JdbcAnswerRepository implements AnswerRepository {
    @Override
    public Answers findByQuestion(Long questionId) {
        return null;
    }
}
