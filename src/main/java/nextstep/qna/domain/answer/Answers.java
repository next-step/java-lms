package nextstep.qna.domain.answer;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Answers {

  public static final String ANSWER_CAN_NOT_BE_DELETED = "다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.";
  private final List<Answer> answers = new ArrayList<>();

  public Answers() {}

  public void add(Answer answer) {
    answers.add(answer);
  }

  public void validateDeleteIsPossible(NsUser user) throws CannotDeleteException {
    for (Answer answer : answers) {
      if (answer.isNotOwner(user)) {
        throw new CannotDeleteException(ANSWER_CAN_NOT_BE_DELETED);
      }
    }
  }

  public Answer get(int index) {
    return answers.get(index);
  }

  public int size() {
    return answers.size();
  }

  public boolean deleteAll(NsUser writer) throws CannotDeleteException {
    validateDeleteIsPossible(writer);
    answers.forEach(Answer::deleted);
    return true;
  }
}
