package nextstep.qna.domain;

import java.util.List;

public class AnswerDeleteHistories {

  private final List<DeleteHistory> answerDeleteHistories;

  public AnswerDeleteHistories(List<DeleteHistory> answerDeleteHistories) {
    this.answerDeleteHistories = answerDeleteHistories;
  }

  public List<DeleteHistory> getHistories() {
    return answerDeleteHistories;
  }

  public int historySize() {
    return this.answerDeleteHistories.size();
  }
}
