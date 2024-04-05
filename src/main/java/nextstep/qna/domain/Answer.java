package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.NotFoundException;
import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

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

    public boolean isDeleted() {
        return deleted;
    }

    public boolean isOwner(NsUser writer) {
        return this.writer.equals(writer);
    }

    public void toQuestion(Question question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "Answer [id=" + id + ", writer=" + writer + ", contents=" + contents + "]";
    }

    public DeleteHistory deleteBy(NsUser user) throws CannotDeleteException {
        if (!user.matchUser(this.writer)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변은 삭제할 수 없습니다.");
        }
        this.deleted = true;
        return toHistory();
    }

    private DeleteHistory toHistory() throws CannotDeleteException {
        if (!isDeleted()) {
            throw new CannotDeleteException("삭제되지 않은 답변입니다.");
        }
        return new DeleteHistory(ContentType.ANSWER, id, writer, LocalDateTime.now());
    }

    public NsUser getWriter() {
        return writer;
    }

    public Long getId() {
        return id;
    }

    public boolean isDeletableBy(NsUser user) {
        return user.matchUser(writer);
    }
}
