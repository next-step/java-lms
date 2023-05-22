package nextstep.qna.domain;

import nextstep.qna.NotFoundException;
import nextstep.qna.UnAuthorizedException;
import nextstep.qna.domain.generator.SimpleIdGenerator;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.Objects;

public class Answer {
    private final long id;

    private final NsUser writer;

    private final Question question;

    private final LocalDateTime createdDate;

    private String contents;

    private DeleteStatus deleted = DeleteStatus.NOT_DELETED;

    private LocalDateTime updateAt;

    private Answer(long id, NsUser writer, Question question, String contents, LocalDateTime createdDate) {
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

    public static Answer of(NsUser writer, Question question, String contents) {
        long id = SimpleIdGenerator.getAndIncrement(Answer.class);
        return new Answer(id, writer, question, contents, LocalDateTime.now());
    }

    public static Answer of(long id, NsUser writer, Question question, String contents, LocalDateTime createdDate) {
        return new Answer(id, writer, question, contents, createdDate);
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
        this.deleted = DeleteStatus.DELETED;
        updateAt = LocalDateTime.now();
        return DeleteHistory.of(ContentType.ANSWER, this.id, this.writer);
    }

    public boolean isDeleted() {
        return deleted.isDeleted();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return id == answer.id && deleted == answer.deleted && Objects.equals(writer, answer.writer) && Objects.equals(question, answer.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, writer, question, deleted);
    }
}
