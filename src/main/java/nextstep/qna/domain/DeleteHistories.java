package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.List;

public class DeleteHistories {
  private final List<DeleteHistory> histories = new ArrayList<>();

  public DeleteHistories(final DeleteHistory history) {
    this.histories.add(history);
  }

  public DeleteHistories(final List<DeleteHistory> histories) {
    this.histories.addAll(histories);
  }

  public void addAll(final List<DeleteHistory> histories) {
    this.histories.addAll(histories);
  }
}
