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

  public List<DeleteHistory> delete() {
    List<DeleteHistory> deleteHistories = new ArrayList<>();
    answers.stream()
        .map(Answer::makeDeleted)
        .forEach(deleteHistories::add);

    return deleteHistories;
  }

  public boolean checkAnswersOwner(NsUser loginUser) {
    Optional<Answer> optionalAnswer = answers.stream()
        .filter(answer -> !answer.checkAnswerOwner(loginUser))
        .findAny();

    return !optionalAnswer.isPresent();
  }
}
