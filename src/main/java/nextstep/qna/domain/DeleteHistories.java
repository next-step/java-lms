package nextstep.qna.domain;

import java.util.List;

public class DeleteHistories {

  private final List<DeleteHistory> deleteHistories;

  public DeleteHistories(List<DeleteHistory> deleteHistories) {
    this.deleteHistories = deleteHistories;
  }

  public void add(DeleteHistory deleteHistory) {
    deleteHistories.add(deleteHistory);
  }

  public List<DeleteHistory> getDeleteHistories() {
    return deleteHistories;
  }

}
