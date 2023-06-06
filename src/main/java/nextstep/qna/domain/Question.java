package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Question {
  private Long id;

  private QuestionBody questionBody;

  private NsUser writer;

  private Answers answers = new Answers();

  private boolean deleted = false;

  private LocalDateTime createdDate = LocalDateTime.now();

  private LocalDateTime updatedDate;

  public Question() {
  }

  public Question(NsUser writer, String title, String contents) {
    this(0L, writer, new QuestionBody(title, contents));
  }

  public Question(Long id, NsUser writer, String title, String contents) {
    this(id, writer, new QuestionBody(title, contents));
  }

  public Question(Long id, NsUser writer, QuestionBody questionBody) {
    this.id = id;
    this.writer = writer;
    this.questionBody = questionBody;
  }

  public Long getId() {
    return id;
  }

  public String getTitle() {
    return this.questionBody.getTitle();
  }

  public String getContents() {
    return this.questionBody.getContents();
  }

  public NsUser getWriter() {
    return writer;
  }

  public void addAnswer(Answer answer) {
    answer.toQuestion(this);
    answers.add(answer);
  }

  public boolean isDeleted() {
    return deleted;
  }

  public List<Answer> getAnswers() {
    return answers.getAnswers();
  }

  public List<DeleteHistory> delete(NsUser loginUser) throws CannotDeleteException {
    if (!loginUser.equals(writer)) {
      throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
    }

    deleted = true;

    return deleteHistories(loginUser);
  }

  private List<DeleteHistory> deleteHistories(NsUser loginUser) throws CannotDeleteException {
    LocalDateTime deletedDateTime = LocalDateTime.now();

    List<DeleteHistory> deleteHistories = new ArrayList<>();
    deleteHistories.add(new DeleteHistory(ContentType.QUESTION, this.id, this.writer, deletedDateTime));
    deleteHistories.addAll(answers.deleteAll(loginUser, deletedDateTime));

    return deleteHistories;
  }

  @Override
  public String toString() {
    return "Question [id=" + getId() + ", title=" + this.questionBody.getTitle() + ", contents=" + this.questionBody.getContents() + ", writer="
        + writer + "]";
  }
}
