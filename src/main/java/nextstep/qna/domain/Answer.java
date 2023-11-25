package nextstep.qna.domain;

import java.time.LocalDateTime;

import nextstep.qna.*;
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

    @Override
    public String toString() {
        return "Answer [id=" + getId() + ", writer=" + writer + ", contents=" + contents + "]";
    }

    public void delete(NsUser loginUser) throws CannotDeleteException {
        validate(loginUser);
        deleted = true;
    }

    private void validate(NsUser loginUser) throws CannotDeleteException {
        if (!writerEqualsQuestionWriter()) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
        if (!writerEqualsLoginUser(loginUser)) {
            throw new CannotDeleteException("본인이 작성한 답변만 삭제할 수 있습니다.");
        }
    }

    private boolean writerEqualsQuestionWriter() {
        return writer.equals(question.getWriter());
    }

    private boolean writerEqualsLoginUser(NsUser loginUser) {
        return writer.equals(loginUser);
    }
}
