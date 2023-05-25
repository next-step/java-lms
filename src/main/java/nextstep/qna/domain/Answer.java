package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
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

        if (id == 0L) {
            throw new IllegalArgumentException("유효하지 않는 아이디에요 :( [입력 값 : " + id + "]");
        }

        if (Objects.isNull(writer)) {
            throw new UnAuthorizedException("작성자에 값이 입력되질 않았어요 :(");
        }

        if (Objects.isNull(question)) {
            throw new NotFoundException();
        }

        this.id = id;
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

    public static Answer of(long id, NsUser writer, Question question, String contents, LocalDateTime createdDate, DeleteStatus deleted) {

        Answer answer = new Answer(id, writer, question, contents, createdDate);

        if (Objects.nonNull(deleted)) {
            answer.deleted = deleted;
        }
        return answer;
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

        if (deleted.isDeleted()) {
            throw new CannotDeleteException("이미 삭제된 답변이에요 :(");
        }

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
