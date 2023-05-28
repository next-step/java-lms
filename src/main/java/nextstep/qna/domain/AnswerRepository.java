package nextstep.qna.domain;

import java.util.List;

public interface AnswerRepository {
    List<Answer> findAllByQuestion(Long questionId);

    Answer save(Answer answer);
}
