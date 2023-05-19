package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.List;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

public class Answers {

  private final List<Answer> answers;

  public Answers(List<Answer> answers) {
    this.answers = answers;
  }

  public Answers() {
    this(new ArrayList<>());
  }

  public void add(Answer answer) {
    answers.add(answer);
  }

  public List<DeleteHistory> deleteAllBy(NsUser writer) throws CannotDeleteException {
    List<DeleteHistory> deleteHistories = new ArrayList<>();
    for(Answer answer : answers) {
      DeleteHistory deleteHistory = answer.deleteBy(writer);
      deleteHistories.add(deleteHistory);
    }
    return deleteHistories;
  }
}
