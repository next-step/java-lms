package nextstep.courses.domain;

import java.util.HashSet;
import java.util.Set;
import nextstep.qna.NotFoundException;

public class Batches {

  private Set<Batch> batches = new HashSet<>();

  public Batches() {
  }

  public void addBatch(Batch batch) {
    batches.add(batch);
  }

  public Curriculum addSession(int batchNo, Session session) {
    return getBatch(batchNo).addSession(session);
  }

  private Batch getBatch(int batchNo) {
    return batches.stream()
        .filter(batch -> batch.checkBatchNo(batchNo))
        .findAny()
        .orElseThrow(NotFoundException::new);
  }
}
