package nextstep.qna.domain;

import javax.xml.crypto.Data;
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

    private DateRecord dateRecord;

    public Answer() {
    }

    public Answer(NsUser writer, Question question, String contents) {
        this(null, writer, question, contents);
    }

    public Answer(Long id, NsUser writer, Question question, String contents) {
        this.id = id;
        validateWriter(writer);
        validateQuestion(question);

        this.writer = writer;
        this.question = question;
        this.contents = contents;
    }

    private static void validateWriter(NsUser writer) {
        if(writer == null) {
            throw new UnAuthorizedException();
        }
    }

    private static void validateQuestion(Question question) {
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

    public NsUser getWriter() {
        return writer;
    }

    public void toQuestion(Question question) {
        this.question = question;
    }

    public DeleteHistory delete(NsUser loginUser) throws CannotDeleteException {
        validateSamePerson(loginUser);
        this.deleted = true;
        return new DeleteHistory(ContentType.ANSWER, this.id, this.writer, LocalDateTime.now());
    }

    private void validateSamePerson(NsUser loginUser) throws CannotDeleteException {
        if (!this.isOwner(loginUser)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }

    private boolean isOwner(NsUser writer) {
        return this.writer.equals(writer);
    }

    @Override
    public String toString() {
        return "Answer [id=" + getId() + ", writer=" + writer + ", contents=" + contents + "]";
    }
}
