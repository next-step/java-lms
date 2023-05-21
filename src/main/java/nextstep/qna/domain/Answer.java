package nextstep.qna.domain;

import nextstep.global.domain.BaseTimeDomain;
import nextstep.qna.CannotDeleteException;
import nextstep.qna.NotFoundException;
import nextstep.qna.UnAuthorizedException;
import nextstep.qna.domain.enums.DeleteStatus;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Answer extends BaseTimeDomain {
    private Long id;

    private NsUser writer;

    private Question question;

    private String contents;

    private DeleteStatus deleteStatus = DeleteStatus.NO;

    public Answer() {
    }

    public Answer(NsUser writer, Question question, String contents) {
        this(null, writer, question, contents);
    }

    public Answer(Long id, NsUser writer, Question question, String contents) {
        this.id = id;
        if(writer == null) {
            throw new UnAuthorizedException();
        }

        if(question == null) {
            throw new NotFoundException();
        }

        this.writer = writer;
        this.question = question;
        this.contents = contents;
    }

    public Long getId() {
        return id;
    }

    public DeleteHistory delete(NsUser loginUser, LocalDateTime now) throws CannotDeleteException {
        if (!isOwner(loginUser)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }

        this.deleteStatus = DeleteStatus.YES;

        return new DeleteHistory(ContentType.ANSWER, this.id, this.writer, now);
    }

    public DeleteStatus getDeleteStatus() {
        return deleteStatus;
    }

    public NsUser getWriter() {
        return writer;
    }

    public String getContents() {
        return contents;
    }

    public void toQuestion(Question question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "Answer [id=" + getId() + ", writer=" + writer + ", contents=" + contents + "]";
    }

    private boolean isOwner(NsUser writer) {
        return this.writer.equals(writer);
    }
}
