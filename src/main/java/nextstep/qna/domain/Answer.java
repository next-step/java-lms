package nextstep.qna.domain;

import static nextstep.qna.domain.ContentType.ANSWER;

import java.time.LocalDateTime;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.NotFoundException;
import nextstep.qna.UnAuthorizedException;
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
        if (writer == null) {
            throw new UnAuthorizedException();
        }

        if (question == null) {
            throw new NotFoundException();
        }

        this.id = id;
        this.writer = writer;
        this.question = question;
        this.contents = contents;
        this.createdDate = LocalDateTime.now();
        this.deleted = false;
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

    public DeleteHistory delete(
            final NsUser questionWriter,
            final LocalDateTime deleteDateTime
    ) throws CannotDeleteException {

        if (!isOwner(questionWriter)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }

        this.deleted = true;

        return new DeleteHistory(ANSWER, this.id, this.writer, deleteDateTime);
    }

    @Override
    public String toString() {
        return "Answer [id=" + id() + ", writer=" + this.writer + ", contents=" + this.contents + "]";
    }
}
