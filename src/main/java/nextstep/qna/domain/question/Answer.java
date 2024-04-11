package nextstep.qna.domain.question;

import static nextstep.qna.domain.history.ContentType.ANSWER;

import java.time.LocalDateTime;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.NotFoundException;
import nextstep.qna.UnAuthorizedException;
import nextstep.qna.domain.history.DeleteHistory;
import nextstep.users.domain.NsUser;

public class Answer {

    private final Long id;
    private final NsUser writer;
    private final String contents;
    private final LocalDateTime createdDate;
    private Question question;
    private boolean deleted;
    private LocalDateTime updatedDate;

    public Answer(final NsUser writer, final Question question, final String contents) {
        this(null, writer, question, contents);
    }

    public Answer(final Long id, final NsUser writer, final Question question, final String contents) {
        validateWriterIsNotNull(writer);
        validateQuestionIsNotNull(question);

        this.id = id;
        this.writer = writer;
        this.question = question;
        this.contents = contents;
        this.createdDate = LocalDateTime.now();
        this.deleted = false;
    }

    private void validateWriterIsNotNull(final NsUser writer) {
        if (writer == null) {
            throw new UnAuthorizedException();
        }
    }

    private void validateQuestionIsNotNull(final Question question) {
        if (question == null) {
            throw new NotFoundException();
        }
    }

    public Long id() {
        return this.id;
    }

    public NsUser writer() {
        return this.writer;
    }

    public boolean isDeleted() {
        return this.deleted;
    }

    public boolean isOwner(final NsUser writer) {
        return this.writer.equals(writer);
    }

    public void toQuestion(final Question question) {
        this.question = question;
    }

    public DeleteHistory delete(final NsUser questionWriter, final LocalDateTime deleteDateTime) throws
            CannotDeleteException {
        validateAnswerWriterIsQuestionWriter(questionWriter);

        this.deleted = true;

        return new DeleteHistory(ANSWER, this.id, this.writer, deleteDateTime);
    }

    private void validateAnswerWriterIsQuestionWriter(final NsUser questionWriter) throws CannotDeleteException {
        if (!isOwner(questionWriter)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }

    @Override
    public String toString() {
        return "Answer [id=" + this.id + ", writer=" + this.writer + ", contents=" + this.contents + "]";
    }
}
