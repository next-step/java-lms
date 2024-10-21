package nextstep.qna.domain.repository;

import nextstep.qna.domain.Answer;

import java.util.List;

public interface AnswerRepository {
    List<Answer> findByQuestion(Long questionId);
}
