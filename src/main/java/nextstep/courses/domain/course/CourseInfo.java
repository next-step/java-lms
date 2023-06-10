package nextstep.courses.domain.course;

public class CourseInfo {

  private Long id;

  private String title;

  private int nowBatchNo = 0;

  public CourseInfo(Long id, String title, int nowBatchNo) {
    this.id = id;
    this.title = title;
    this.nowBatchNo = nowBatchNo;
  }

  public String getTitle() {
    return title;
  }
}
