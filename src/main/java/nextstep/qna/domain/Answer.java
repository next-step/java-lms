package nextstep.qna.domain;

import nextstep.qna.NotFoundException;
import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Answer {
    private final long id;

    private final NsUser writer;

    private final Question question;

    private final LocalDateTime createdDate;

    private String contents;

    private boolean deleted;

    private LocalDateTime updateAt;

    public Answer(long id, NsUser writer, Question question, String contents, LocalDateTime createdDate) {
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
        this.createdDate = createdDate;
    }

    public long getId() {
        return id;
    }

    public Answer changeContents(String contents) {
        this.contents = contents;
        updateAt = LocalDateTime.now();
        return this;
    }

    public DeleteHistory remove() {
        this.deleted = false;
        updateAt = LocalDateTime.now();
        return DeleteHistory.from(ContentType.ANSWER, this.id, this.writer);
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

    @Override
    public String toString() {
        return "Answer [id=" + getId() + ", writer=" + writer + ", contents=" + contents + "]";
    }
}
