package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.NotFoundException;
import nextstep.qna.UnAuthorizedException;
import nextstep.qna.domain.generator.SimpleIdGenerator;

import java.time.LocalDateTime;
import java.util.Objects;

public class Answer {
    private final long id;

    private final String writerId;

    private final long questionId;

    private final LocalDateTime createdDate;

    private String contents;

    private DeleteStatus deleted = DeleteStatus.NOT_DELETED;

    private LocalDateTime updateAt;

    private Answer(long id, String writerId, long questionId, String contents, LocalDateTime createdDate) {

        if (id == 0L) {
            throw new IllegalArgumentException("유효하지 않는 아이디에요 :( [입력 값 : " + id + "]");
        }

        if (Objects.isNull(writerId)) {
            throw new UnAuthorizedException("작성자에 값이 입력되질 않았어요 :(");
        }

        if (writerId.isEmpty()) {
            throw new UnAuthorizedException("작성자에 값이 입력되질 않았어요 :(");
        }

        if (questionId == 0L) {
            throw new NotFoundException();
        }

        this.id = id;
        this.writerId = writerId;
        this.questionId = questionId;
        this.contents = contents;
        this.createdDate = createdDate;
    }

    public static Answer of(String writerId, long questionId, String contents) {
        long id = SimpleIdGenerator.getAndIncrement(Answer.class);
        return new Answer(id, writerId, questionId, contents, LocalDateTime.now());
    }

    public static Answer of(long id, String writerId, long questionId, String contents, LocalDateTime createdDate) {
        return new Answer(id, writerId, questionId, contents, createdDate);
    }

    public static Answer of(long id, String writerId, long questionId, String contents, LocalDateTime createdDate, DeleteStatus deleted) {

        Answer answer = new Answer(id, writerId, questionId, contents, createdDate);

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
        return DeleteHistory.of(ContentType.ANSWER, this.id, this.writerId);
    }

    public boolean isDeleted() {
        return deleted.isDeleted();
    }

    public boolean isOwner(String writerId) {
        return this.writerId.equals(writerId);
    }

    public String getWriter() {
        return writerId;
    }

    public String getContents() {
        return contents;
    }

    @Override
    public String toString() {
        return "Answer [id=" + getId() + ", writer=" + writerId + ", contents=" + contents + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return id == answer.id && questionId == answer.questionId && Objects.equals(writerId, answer.writerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, writerId, questionId);
    }
}
