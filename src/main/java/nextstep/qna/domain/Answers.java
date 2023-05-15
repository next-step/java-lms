package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NextStepUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Answers {

  private final List<Answer> answers = new ArrayList<>();

  public List<Answer> answers() {
    return Collections.unmodifiableList(answers);
  }

  public void addNewAnswer(Answer answer) {
    answers.add(answer);
  }

  public void validateAnswers(NextStepUser loginUser) throws CannotDeleteException {
    for (Answer answer : answers) {
      answer.validateIsOwner(loginUser);
    }
  }

  public void deleteAnswers(List<DeleteHistory> deleteHistories) {
    for (Answer answer : answers) {
      answer.setDeleted(true);
      deleteHistories.add(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
    }
  }
}
