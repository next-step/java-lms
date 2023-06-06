package nextstep.qna.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

public class Answers {
  private List<Answer> answers = new ArrayList<>();

  public void add(Answer answer) {
    this.answers.add(answer);
  }

  public List<Answer> getAnswers() {
    return answers;
  }

  public List<DeleteHistory> deleteAll(NsUser loginUser, LocalDateTime deletedDateTime) throws CannotDeleteException {
    List<DeleteHistory> deleteHistories = new ArrayList<>();

    for (Answer answer : answers) {
      if (answer.isDeleted()) continue;

      deleteHistories.add(answer.delete(loginUser, deletedDateTime));
    }

    return deleteHistories;
  }
}
