package nextstep.courses.domain.session;

import java.util.Objects;

public class BatchInfo {

  private final Long courseId;

  private final int batchNo;

  public BatchInfo(Long courseId, int batchNo) {
    this.courseId = courseId;
    this.batchNo = batchNo;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BatchInfo that = (BatchInfo) o;
    return Objects.equals(courseId, that.courseId) && Objects
        .equals(batchNo, that.batchNo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(courseId, batchNo);
  }

  public Long getCourseId() {
    return courseId;
  }

  public int getBatchNo() {
    return batchNo;
  }
}
