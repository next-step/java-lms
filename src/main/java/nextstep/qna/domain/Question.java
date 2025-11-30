package nextstep.qna.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

public class Question extends BaseEntity {
    private QuestionPost post;
    private Answers answers;
    private boolean deleted;

  public Question() {
    this(null, null, new Answers(), false);
  }

  public Question(NsUser writer, String title, String contents) {
    this(null, new QuestionPost(writer, title, contents), new Answers(), false);
  }

  public Question(Long id, NsUser writer, String title, String contents) {
    this(id, new QuestionPost(writer, title, contents), new Answers(), false);
  }

  public Question(Long id, QuestionPost post, Answers answers, boolean deleted) {
    super(id);
    this.post = post;
    this.answers = answers;
    this.deleted = deleted;
  }

  public void add(Answer answer) {
    answers.create(answer);
    }

  public void deleteBy(NsUser loginUser) throws CannotDeleteException {
    deleteRelatedAnswers(loginUser);
    deleteQuestion(loginUser);
  }

  private void validateOwner(NsUser loginUser) throws CannotDeleteException {
    if (!post.isOwner(loginUser)) {
      throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
    }
  }

  public boolean isDeleted() {
    return deleted;
  }

  public NsUser getWriter() {
    return this.post.getWriter();
  }

  private void deleteQuestion(NsUser loginUser) throws CannotDeleteException {
    validateOwner(loginUser);
    this.deleted = true;
  }

  private void deleteRelatedAnswers(NsUser loginUser) throws CannotDeleteException {
    answers.deleteBy(loginUser);
    }

  public List<DeleteHistory> createDeleteHistories() {
    List<DeleteHistory> deleteHistories = new ArrayList<>();
    deleteHistories.add(new DeleteHistory(ContentType.QUESTION, getId(), getWriter()));
    deleteHistories.addAll(answers.createDeleteHistories());
    return deleteHistories;
  }

  @Override
  public String toString() {
    return "Question [id=" + getId() + ", title=" + post.getTitle() + ", contents=" + post.getContents() + ", writer="
        + getWriter() + "]";
  }

}
