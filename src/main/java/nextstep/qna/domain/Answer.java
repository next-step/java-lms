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

    public boolean isDeleted() {
        return deleted;
    }

    public NsUser getWriter() {
        return writer;
    }

    public void toQuestion(Question question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "Answer [id=" + getId() + ", writer=" + writer + ", contents=" + contents + "]";
    }

    public void delete(NsUser loginUser) throws CannotDeleteException {
        validateDeletingPolicies(loginUser);
        deleted = true;
    }

    private void validateDeletingPolicies(NsUser loginUser) throws CannotDeleteException {
        if (!writer.equals(question.getWriter())) {
            throw new CannotDeleteException("답변 작성자와 질문 작성자가 일치해야 삭제할 수 있습니다.");
        }
        if (!writer.equals(loginUser)) {
            throw new CannotDeleteException("본인이 작성한 답변만 삭제할 수 있습니다.");
        }
    }

}
