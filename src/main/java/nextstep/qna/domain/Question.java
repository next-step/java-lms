package nextstep.qna.domain;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Question {

  private Long id;

  private String title;

  private String contents;

  private NsUser writer;

  private List<Answer> answers = new ArrayList<>();

  private boolean deleted = false;

  private LocalDateTime createdDate = LocalDateTime.now();

  private LocalDateTime updatedDate;

  public Question() {
  }

  public Question(NsUser writer, String title, String contents) {
    this(0L, writer, title, contents);
  }

  public Question(Long id, NsUser writer, String title, String contents) {
    this.id = id;
    this.writer = writer;
    this.title = title;
    this.contents = contents;
  }

  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getContents() {
    return contents;
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
    return answers;
  }

  public List<DeleteHistory> delete(NsUser loginUser) throws CannotDeleteException {
    if (!loginUser.equals(writer)) {
      throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
    }

    deleted = true;

    return deleteHistories(loginUser);
  }

  @Override
  public String toString() {
    return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer="
        + writer + "]";
  }

  private List<DeleteHistory> deleteHistories(NsUser loginUser) throws CannotDeleteException {
    LocalDateTime deletedDateTime = LocalDateTime.now();

    List<DeleteHistory> deleteHistories = new ArrayList<>();

    deleteHistories.add(new DeleteHistory(ContentType.QUESTION, this.id, this.writer, deletedDateTime));

    for (Answer answer : answers) {
      if (answer.isDeleted()) continue;

      deleteHistories.add(answer.delete(loginUser, deletedDateTime));
    }

    return deleteHistories;
  }
}
