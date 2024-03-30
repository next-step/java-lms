package nextstep.qna.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Answers {

  private final List<Answer> answers;
  public Answers(List<Answer> answers) {
    this.answers = answers;
  }

  public List<DeleteHistory> delete() {
    List<DeleteHistory> deleteHistories = new ArrayList<>();
    for (Answer answer : answers) {
      answer.setDeleted(true);
      deleteHistories.add(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
    }
    return deleteHistories;

  }
}
