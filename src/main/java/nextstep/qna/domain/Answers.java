package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

public class Answers {

  private List<Answer> answers;

  private Answers() {
    this.answers = new ArrayList<>();
  }

  private Answers(List<Answer> answers) {
    this.answers = answers;
  }
  public static Answers defaultOf() {
    return new Answers();
  }

  public static Answers defaultOf(List<Answer> answers) {
    return new Answers(answers);
  }

  public void delete() {
    answers.forEach(Answer::delete);
  }

  public void add(Answer answer) {
    this.answers.add(answer);
  }

  public int size() {
    return this.answers.size();
  }

  public void isOwner(NsUser user) throws CannotDeleteException {
    for (Answer answer : answers) {
      answer.isOwner(user);
    }
  }

  public List<DeleteHistory> toHistory() {
    return this.answers.stream()
                      .map(Answer::toHistory)
                      .collect(Collectors.toList());
  }
}
