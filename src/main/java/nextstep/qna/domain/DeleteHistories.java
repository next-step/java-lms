package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeleteHistories {
  private final List<DeleteHistory> deleteHistories = new ArrayList<>();

  private DeleteHistories(Question question, NsUser loginUser) throws CannotDeleteException {
    deleteQuestion(question, loginUser);
    deleteAllAnswers(question.getAnswers(), loginUser);
  }

  public static DeleteHistories createDeleteHistories(Question question, NsUser loginUser) throws CannotDeleteException {
    return new DeleteHistories(question, loginUser);
  }

  public List<DeleteHistory> deleteHistories() {
    return Collections.unmodifiableList(deleteHistories);
  }

  private void deleteQuestion(Question question, NsUser loginUser) throws CannotDeleteException {
    question.validateOwnerQuestion(loginUser);

    question.setDeleted(true);
    deleteHistories.add(new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter(), LocalDateTime.now()));
  }

  private void deleteAllAnswers(Answers answers, NsUser loginUser) throws CannotDeleteException {
    answers.validateAnswers(loginUser);

    answers.deleteAnswers(deleteHistories);
  }
}
