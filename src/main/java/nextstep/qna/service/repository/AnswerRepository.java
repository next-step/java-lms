package nextstep.qna.service.repository;

import java.util.List;
import nextstep.qna.domain.Answer;

public interface AnswerRepository {
    List<Answer> findByQuestion(Long questionId);
}
