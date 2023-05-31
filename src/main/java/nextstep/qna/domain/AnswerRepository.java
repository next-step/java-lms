package nextstep.qna.domain;

import java.util.List;

public interface AnswerRepository {
    Answers findByQuestion(Long questionId);
}
