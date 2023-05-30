package nextstep.qna.domain;

import java.time.LocalDateTime;
import java.util.Optional;

import nextstep.common.BaseEntity;
import nextstep.qna.CannotDeleteException;
import nextstep.qna.NotFoundException;
import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUser;

public class Answer extends BaseEntity {
    private Long id;

    private NsUser writer;

    private Question question;

    private String contents;

    private boolean deleted = false;

    public Answer() {
    }

    public Answer(NsUser writer, Question question, String contents) {
        this(null, writer, question, contents);
    }

    public Answer(Long id, NsUser writer, Question question, String contents) {
        super();

        validate(writer, question);
        this.id = id;
        this.writer = writer;
        this.question = question;
        this.contents = contents;
    }

    private void validate(NsUser writer, Question question) {
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

    public void toQuestion(Question question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "Answer [id=" + getId() + ", writer=" + writer + ", contents=" + contents + "]";
    }

    public Optional<DeleteHistory> deleteHistory() {
        if (!isDeleted()) {
            return null;
        }

        return Optional.of(new DeleteHistory(ContentType.ANSWER, id, writer, LocalDateTime.now()));
    }

    public void delete() throws CannotDeleteException {
        if (!isOwner(question.getWriter())) {
            throw new CannotDeleteException("질문자와 답변자가 다르면 삭제할 수 없습니다.");
        }

        this.deleted = true;
    }
}
