package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NextStepUser;

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

  public void deleteAnswers(List<DeleteHistory> deleteHistories, NextStepUser loginUser) throws CannotDeleteException {
    for (Answer answer : answers) {
      deleteHistories.add(answer.delete(loginUser));
    }
  }
}
