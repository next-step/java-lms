package nextstep.qna.domain.question;

import java.util.List;

public interface AnswerRepository {

    List<Answer> findByQuestion(final Long questionId);
}
