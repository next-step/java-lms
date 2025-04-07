package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

public class Answers {
  private final List<Answer> answers = new ArrayList<>();

  public void add(Answer answer) {
    answers.add(answer);
  }

  public void validateAllOwnedBy(NsUser user) throws CannotDeleteException {
    if (answers.stream().anyMatch(answer -> !answer.isOwner(user))) {
      throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
  }

  public List<DeleteHistory> deleteBy(NsUser user) throws CannotDeleteException {
    List<DeleteHistory> histories = new ArrayList<>();
    for (Answer answer : answers) {
      histories.add(answer.deleteBy(user));
    }
    return histories;
  }

  public boolean isEmpty() {
    return answers.isEmpty();
  }

  public int size() {
    return answers.size();
  }

  public List<Answer> getAnswers() {
    return Collections.unmodifiableList(answers);
  }
}
