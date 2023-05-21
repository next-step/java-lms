package nextstep.qna.domain;

import nextstep.qna.NotFoundException;
import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Answer {
    private final LocalDateTime createdDate = LocalDateTime.now();
    private Long id;
    private NsUser writer;
    private Question question;
    private String contents;
    private boolean deleted = false;
    private LocalDateTime updatedDate;

    public Answer() {
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

    public boolean isOwner(NsUser writer) {
        return this.writer.equals(writer);
    }

    public void relateToQuestion(Question question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "Answer [id=" + getId() + ", writer=" + writer + ", contents=" + contents + "]";
    }

    public DeleteHistory toDeleteHistory() {
        return DeleteHistory.of(ContentType.ANSWER, this.getId(), this.writer, LocalDateTime.now());
    }

    public void doDelete() {
        this.deleted = true;
    }

    public String getContent() {
        return this.contents;
    }
}
