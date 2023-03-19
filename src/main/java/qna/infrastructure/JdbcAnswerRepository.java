package qna.infrastructure;

import org.springframework.stereotype.Repository;
import qna.domain.Answer;
import qna.domain.AnswerRepository;

import java.util.List;

@Repository("answerRepository")
public class JdbcAnswerRepository implements AnswerRepository {
    @Override
    public List<Answer> findByQuestion(Long questionId) {
        return null;
    }
}
