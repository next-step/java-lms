package nextstep.qna.infrastructure;

import org.springframework.stereotype.Repository;
import nextstep.qna.domain.Answer;
import nextstep.qna.domain.AnswerRepository;

import java.util.List;

@Repository("answerRepository")
public class JdbcAnswerRepository implements AnswerRepository {
    @Override
    public List<Answer> findByQuestion(Long questionId) {
        return null;
    }
}
