package nextstep.qna.domain;

import static nextstep.qna.domain.ContentType.ANSWER;

import nextstep.common.entity.BaseEntity;
import nextstep.qna.NotFoundException;
import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUser;

public class Answer extends BaseEntity {

    private Question question;

    private NsUser writer;

    private String contents;

    public Answer(NsUser writer, Question question, String contents) {
        this(null, writer, question, contents);
    }

    public Answer(Long id, NsUser writer, Question question, String contents) {
        if(writer == null) {
            throw new UnAuthorizedException();
        }
        if(question == null) {
            throw new NotFoundException();
        }
        this.id = id;
        this.writer = writer;
        this.question = question;
        this.contents = contents;
    }

    public Long getId() {
        return id;
    }

    public void deleteSelf() {
        this.deleted = true;
    }

    public DeleteHistory deleteHistory() {
        if(isDeleted()) {
            return new DeleteHistory(ANSWER, this.id, this.writer);
        }
        return new DeleteHistory();
    }

    public boolean isDeleted() {
        return deleted;
    }

    public boolean isNotOwner(NsUser writer) {
        return !this.writer.equals(writer);
    }

    @Override
    public String toString() {
        return "Answer [id=" + getId() + ", writer=" + writer + ", contents=" + contents + "]";
    }
}
