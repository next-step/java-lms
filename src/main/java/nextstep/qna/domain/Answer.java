package nextstep.qna.domain;

import nextstep.qna.exception.CannotDeleteException;
import nextstep.qna.exception.NotFoundException;
import nextstep.qna.exception.UnAuthorizedException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.Objects;

public class Answer {
    private Long id;

    private NsUser writer;

    private Question question;

    private String contents;

    private boolean deleted = false;

    private LocalDateTime createdDate = LocalDateTime.now();

    private LocalDateTime updatedDate;

    private Answer() {
    }

    private Answer(NsUser writer, Question question, String contents, boolean deleted){
        this.writer = writer;
        this.question = question;
        this.contents = contents;
        this.deleted = deleted;
    }

    private Answer(NsUser writer, Question question, String contents) {
        this(null, writer, question, contents);
    }

    public static Answer of(NsUser writer, Question question, String contents) {
        return new Answer(writer, question, contents);
    }

    public static Answer of(NsUser writer, Question question, String contents, boolean deleted) {
        return new Answer(writer, question, contents, deleted);
    }

    public static Answer of(Long id, NsUser writer, Question question, String contents) {
        return new Answer(id, writer, question, contents);
    }

    public Answer(Long id, NsUser writer, Question question, String contents) {
        this.id = id;
        if(writer == null) {
            throw new UnAuthorizedException();
        }

        if(question == null) {
            throw new NotFoundException();
        }

        this.writer = writer;
        this.question = question;
        this.contents = contents;
    }

    public Long getId() {
        return id;
    }

    public Answer setDeleted(boolean deleted) {
        this.deleted = deleted;
        return this;
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

    public void delete() {
        this.deleted = true;
    }

    public void deleteBy(NsUser user) throws CannotDeleteException {
        if(!isOwner(user)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
        delete();
    }

    @Override
    public String toString() {
        return "Answer [id=" + getId() + ", writer=" + writer + ", contents=" + contents + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return isDeleted() == answer.isDeleted() && Objects.equals(getId(), answer.getId()) && Objects.equals(getWriter(), answer.getWriter()) && Objects.equals(getContents(), answer.getContents());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getWriter(), getContents(), isDeleted());
    }
}
