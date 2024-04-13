package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.NotFoundException;
import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.User;

import java.time.LocalDateTime;

public class Answer {
    public static final String ANSWER_DELETE_ERROR_MESSAGE = "다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.";
    private Long id;

    private User writer;

    private Question question;

    private String contents;

    private boolean deleted = false;

    private LocalDateTime createdDate = LocalDateTime.now();

    private LocalDateTime updatedDate;

    public Answer() {
    }

    public Answer(User writer, Question question, String contents) {
        this(null, writer, question, contents);
    }

    public Answer(Long id, User writer, Question question, String contents) {
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

    public void delete(User loginUser) throws CannotDeleteException {
        if (!isOwner(loginUser)) {
            throw new CannotDeleteException(ANSWER_DELETE_ERROR_MESSAGE);
        }
        this.deleted = true;
    }

    public DeleteHistory toDeleteHistory() {
        return new DeleteHistory(ContentType.ANSWER, id, writer, LocalDateTime.now());
    }

    public boolean isOwner(User writer) {
        return this.writer.equals(writer);
    }

    public User getWriter() {
        return writer;
    }

    public void toQuestion(Question question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "Answer [id=" + getId() + ", writer=" + writer + ", contents=" + contents + "]";
    }
}
