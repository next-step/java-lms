package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.NotFoundException;
import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.Objects;

public class Answer {
    private final Long id;

    private final NsUser writer;

    private Question question;

    private final String contents;

    private boolean deleted = false;

    private final LocalDateTime createdDate = LocalDateTime.now();

    private LocalDateTime updatedDate;

    public Answer(NsUser writer, Question question, String contents) {
        this(null, writer, question, contents);
    }

    public Answer(Long id, NsUser writer, Question question, String contents) {
        this.id = id;
        if (writer == null) {
            throw new UnAuthorizedException();
        }

        if (question == null) {
            throw new NotFoundException();
        }

        this.writer = writer;
        this.question = question;
        this.contents = contents;
    }

    public Long getId() {
        return id;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
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

    public String getContents() {
        return contents;
    }

    public void toQuestion(Question question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "Answer [id=" + getId() + ", writer=" + writer + ", contents=" + contents + "]";
    }

    public DeleteHistory delete()  {
        if (!this.writer.matchUser(this.question.getWriter())) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
        this.setDeleted(true);
        return DeleteHistory.createAnswer(this.id, this.writer);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Answer answer = (Answer) o;
        return isDeleted() == answer.isDeleted()
                && Objects.equals(getId(), answer.getId())
                && Objects.equals(getWriter(), answer.getWriter())
                && Objects.equals(question, answer.question)
                && Objects.equals(getContents(), answer.getContents())
                && Objects.equals(createdDate, answer.createdDate)
                && Objects.equals(updatedDate, answer.updatedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getWriter(), question, getContents(), isDeleted(), createdDate, updatedDate);
    }
}
