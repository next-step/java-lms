package nextstep.qna.infrastructure;

import nextstep.qna.domain.answer.Answer;
import nextstep.qna.domain.answer.AnswerRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("answerRepository")
public class JdbcAnswerRepository implements AnswerRepository {
    @Override
    public List<Answer> findByQuestion(Long questionId) {
        return null;
    }
}
