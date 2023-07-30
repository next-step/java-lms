package nextstep.courses.domain.batch;

import java.util.Objects;

public class BatchInfo {

  private Long id;

  private int batchNo;

  public BatchInfo(Long id, int batchNo) {
    this.id = id;
    this.batchNo = batchNo;
  }

  public boolean checkBatchNo(int batchNo) {
    return this.batchNo == batchNo;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BatchInfo batchInfo = (BatchInfo) o;
    return batchNo == batchInfo.batchNo && Objects.equals(id, batchInfo.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, batchNo);
  }

  public Long getId() {
    return id;
  }

  public int getBatchNo() {
    return batchNo;
  }
}
