package nextstep.qna.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

public class Question {

  private Long id;

  private String title;

  private String contents;

  private NsUser writer;

  private Answers answers = new Answers();

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

  public void addAnswer(Answer answer) {
    answer.toQuestion(this);
    answers.add(answer);
  }

  public List<DeleteHistory> delete(NsUser loginUser) {
    validateQuestionOwner(loginUser);
    answers.validateAnswersOwner(loginUser);

    List<DeleteHistory> deleteHistories = new ArrayList<>();
    deleteHistories.add(makeDeleted());
    deleteHistories.addAll(answers.delete(loginUser));

    return deleteHistories;
  }

  private void validateQuestionOwner(NsUser loginUser) {
    if (!writer.equals(loginUser)) {
      throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
    }
  }

  private DeleteHistory makeDeleted() {
    this.deleted = true;
    return toDeleteHistory();
  }

  public DeleteHistory toDeleteHistory() {
    return new DeleteHistory(ContentType.QUESTION, id, writer, LocalDateTime.now());
  }

  public boolean isDeleted() {
    return deleted;
  }

  @Override
  public String toString() {
    return "Question [id=" + id + ", title=" + title + ", contents=" + contents + ", writer="
        + writer + "]";
  }
}
