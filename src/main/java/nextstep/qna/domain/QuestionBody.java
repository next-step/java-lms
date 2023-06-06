package nextstep.qna.domain;

public class QuestionBody {
  private String title;

  private String contents;

  public QuestionBody(String title, String contents) {
    this.title = title;
    this.contents = contents;
  }

  public String getTitle() {
    return title;
  }

  public String getContents() {
    return contents;
  }
}
