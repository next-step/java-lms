package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NextStepUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeleteHistories {
  private final List<DeleteHistory> deleteHistories = new ArrayList<>();

  private DeleteHistories(Question question, NextStepUser loginUser) throws CannotDeleteException {
    deleteQuestion(question, loginUser);
    deleteAllAnswers(question.getAnswers(), loginUser);
  }

  public static DeleteHistories createDeleteHistories(Question question, NextStepUser loginUser) throws CannotDeleteException {
    return new DeleteHistories(question, loginUser);
  }

  public List<DeleteHistory> deleteHistories() {
    return Collections.unmodifiableList(deleteHistories);
  }

  private void deleteQuestion(Question question, NextStepUser loginUser) throws CannotDeleteException {
    question.validateOwnerQuestion(loginUser);

    question.setDeleted(true);
    deleteHistories.add(new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter(), LocalDateTime.now()));
  }

  private void deleteAllAnswers(Answers answers, NextStepUser loginUser) throws CannotDeleteException {
    answers.validateAnswers(loginUser);

    answers.deleteAnswers(deleteHistories);
  }
}
