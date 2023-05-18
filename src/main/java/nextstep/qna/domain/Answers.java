package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

public class Answers {

  private List<Answer> answers = new ArrayList<>();

  public Answers() {
  }

  public Answers(List<Answer> answers) {
    this.answers = answers;
  }

  public void add(Answer answer) {
    answers.add(answer);
  }

  public void delete(NsUser loginUser, DeleteHistories deleteHistories)
      throws CannotDeleteException {
    if (!checkAnswerOwner(loginUser)) {
      throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    answers.stream()
        .forEach(ans -> ans.makeDeleted(deleteHistories));
  }

  private boolean checkAnswerOwner(NsUser loginUser) {
    Optional<Answer> optionalAnswer = answers.stream()
        .filter(answer -> !answer.isOwner(loginUser))
        .findAny();

    return !optionalAnswer.isPresent();
  }
}
