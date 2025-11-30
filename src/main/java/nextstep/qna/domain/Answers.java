package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.List;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

public class Answers {

  private List<Answer> answers;

  public Answers() {
    this(new ArrayList<>());
  }

  public Answers(List<Answer> answers) {
    this.answers = new ArrayList<>(answers);
  }

  public void create(Answer answer) {
    answers.add(answer);
  }

  public void deleteBy(NsUser nsUser) throws CannotDeleteException {
    validateDeleteBy(nsUser);
    for (Answer answer : answers) {
      answer.delete();
    }
  }

  private void validateDeleteBy(NsUser writer) throws CannotDeleteException {
    for (Answer answer : answers) {
      if (!answer.isOwner(writer)) {
        throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
      }
    }
  }

  public boolean allDeleted() {
    return answers.stream().allMatch(Answer::isDeleted);
  }

  public boolean contains(Answer answer) {
    return answers.contains(answer);
  }

  public List<DeleteHistory> createDeleteHistories() {
    List<DeleteHistory> deleteHistories = new ArrayList<>();
    for (Answer answer : answers) {
      if (answer.isDeleted()) {
        deleteHistories.add(answer.createDeleteHistory());
      }
    }
    return deleteHistories;
  }

}
