package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.List;

public class DeleteHistories {

  private final List<DeleteHistory> deleteHistories = new ArrayList<>();

  public List<DeleteHistory> makeDeleteHistories(Question question, Answers answers) {
    deleteHistories.add(question.makeDeleteHistory());
    deleteHistories.addAll(answers.makeDeleteHistory());
    return deleteHistories;
  }
}
