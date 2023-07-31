package nextstep.courses.domain.course;

public class NowBatchNo {

  private final int nowBatchNo;

  public NowBatchNo(int nowBatchNo) {
    this.nowBatchNo = nowBatchNo;
  }

  public NowBatchNo createdBatch() {
    return new NowBatchNo(nowBatchNo + 1);
  }

  public int getNowBatchNo() {
    return nowBatchNo;
  }
}
