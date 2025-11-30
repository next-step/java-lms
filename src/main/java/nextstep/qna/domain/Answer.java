package nextstep.qna.domain;

import java.time.LocalDateTime;
import nextstep.qna.NotFoundException;
import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUser;

public class Answer {
    private Long id;

    private NsUser writer;

    private Question question;

    private String contents;

    private boolean deleted = false;

    private LocalDateTime createdDate = LocalDateTime.now();

    private LocalDateTime updatedDate;

    public Answer() {
    }

    public Answer(NsUser writer, Question question, String contents) {
        this(null, writer, question, contents);
    }

    public Answer(Long id, NsUser writer, Question question, String contents) {
        validate(writer, question);
        this.id = id;
        this.writer = writer;
        this.question = question;
        this.contents = contents;
    }

    private static void validate(NsUser writer, Question question){
      if(writer == null) {
        throw new UnAuthorizedException();
      }

      if(question == null) {
        throw new NotFoundException();
      }
    }

    public Long getId() {
        return id;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public boolean isOwner(NsUser writer) {
        return this.writer.equals(writer);
    }

    public NsUser getWriter() {
        return writer;
    }

    public void delete() {
     this.deleted = true;
    }

    public void toQuestion(Question question) {
        this.question = question;
    }

  public DeleteHistory createDeleteHistory() {
    return new DeleteHistory(ContentType.ANSWER, id, writer, LocalDateTime.now());
  }

  @Override
    public String toString() {
        return "Answer [id=" + getId() + ", writer=" + writer + ", contents=" + contents + "]";
    }

}
