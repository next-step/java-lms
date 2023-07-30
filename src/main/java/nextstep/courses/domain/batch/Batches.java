package nextstep.courses.domain.batch;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import nextstep.qna.NotFoundException;

public class Batches {

  private Set<Batch> batches = new HashSet<>();

  public Batches() {
  }

  public Batches(List<Batch> batches) {
    this(new HashSet<>(batches));
  }

  public Batches(Set<Batch> batches) {
    this.batches = batches;
  }

  public void addBatch(Batch batch) {
    batches.add(batch);
  }

  public void removeBatch(Batch batch) {
    batches.remove(batch);
  }

  public Batch getBatch(int batchNo) {
    return batches.stream()
        .filter(batch -> batch.checkBatchNo(batchNo))
        .findAny()
        .orElseThrow(NotFoundException::new);
  }

  public int getSize() {
    return batches.size();
  }
}
