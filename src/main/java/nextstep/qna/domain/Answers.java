package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

  public void delete(DeleteHistories deleteHistories) {
    answers.stream()
        .forEach(ans -> ans.makeDeleted(deleteHistories));
  }

  public boolean checkAnswerOwner(NsUser loginUser) {
    Optional<Answer> optionalAnswer = answers.stream()
        .filter(answer -> !answer.isOwner(loginUser))
        .findAny();

    return !optionalAnswer.isPresent();
  }
}
