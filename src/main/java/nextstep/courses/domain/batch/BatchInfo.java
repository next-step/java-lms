package nextstep.courses.domain.batch;

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

  public Long getId() {
    return id;
  }

  public int getBatchNo() {
    return batchNo;
  }
}
