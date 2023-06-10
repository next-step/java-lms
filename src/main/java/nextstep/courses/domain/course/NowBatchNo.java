package nextstep.courses.domain.course;

public class NowBatchNo {

  private int nowBatchNo;

  public NowBatchNo(int nowBatchNo) {
    this.nowBatchNo = nowBatchNo;
  }

  public int createdBatch() {
    ++nowBatchNo;
    return nowBatchNo;
  }

  public int getNowBatchNo() {
    return nowBatchNo;
  }
}
