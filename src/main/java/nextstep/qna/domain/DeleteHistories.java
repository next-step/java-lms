package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeleteHistories {

  private List<DeleteHistory> deleteHistories = new ArrayList<>();

  public DeleteHistories() {
  }

  public DeleteHistories(List<DeleteHistory> deleteHistories) {
    this.deleteHistories = deleteHistories;
  }

  public void addDeleteHistory(DeleteHistory deleteHistory) {
    deleteHistories.add(deleteHistory);
  }

  public List<DeleteHistory> getDeleteHistories() {
    return Collections.unmodifiableList(deleteHistories);
  }
}
