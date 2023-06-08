package nextstep.qna.repository;

import nextstep.qna.domain.answer.Answer;

import java.util.List;

public interface AnswerRepository {
    List<Answer> findByQuestion(Long questionId);
}
