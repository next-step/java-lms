package nextstep.courses.domain;

import java.util.ArrayList;
import java.util.List;
import nextstep.qna.NotFoundException;

public class Batches {

  private List<Batch> batches = new ArrayList<>();

  public Batches() {
  }

  public void addBatch(Batch batch) {
    batches.add(batch);
  }

  public void addSession(int batchNo, Session session) {
    getBatch(batchNo).addSession(session);
  }

  private Batch getBatch(int batchNo) {
    return batches.stream()
        .filter(batch -> batch.checkBatchNo(batchNo))
        .findAny()
        .orElseThrow(NotFoundException::new);
  }
}
