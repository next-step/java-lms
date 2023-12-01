package nextstep.qna.domain.answer;

import java.util.List;

public interface AnswerRepository {
    List<Answer> findByQuestion(Long questionId);
}
