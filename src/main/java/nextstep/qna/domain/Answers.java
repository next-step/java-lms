package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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

  public List<DeleteHistory> delete(NsUser loginUser) {
    List<DeleteHistory> deleteHistories = new ArrayList<>();
    deleteHistories.addAll(makeDeleted(loginUser));

    return deleteHistories;
  }

  private List<DeleteHistory> makeDeleted(NsUser loginUser) {
    return answers.stream()
        .map(answer -> answer.delete(loginUser))
        .collect(Collectors.toList());
  }
}
