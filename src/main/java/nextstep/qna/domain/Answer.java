package nextstep.qna.domain;

import nextstep.global.domain.BaseTimeDomain;
import nextstep.qna.CannotDeleteException;
import nextstep.qna.NotFoundException;
import nextstep.qna.UnAuthorizedException;
import nextstep.qna.domain.enums.ContentType;
import nextstep.qna.domain.enums.DeleteStatus;
import nextstep.qna.domain.vo.AnswerDetail;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Answer extends BaseTimeDomain {
    private Long id;

    private Question question;

    private AnswerDetail detail;

    private DeleteStatus deleteStatus = DeleteStatus.NO;

    public static Answer of(NsUser writer, Question question, String contents) {
        return new Answer(null, writer, question, contents);
    }

    public static Answer of(Long id, NsUser writer, Question question, String contents) {
        return new Answer(id, writer, question, contents);
    }

    private Answer(Long id, NsUser writer, Question question, String contents) {
        this.id = id;
        this.question = validateQuestion(question);
        this.detail = AnswerDetail.of(contents, validateWriter(writer));
    }

    public Long getId() {
        return id;
    }

    public DeleteHistory delete(NsUser loginUser, LocalDateTime now) throws CannotDeleteException {
        checkDeletionAvailability(loginUser);
        changeStatusToBeDeleted();
        return createDeleteHistory(now);
    }

    public DeleteStatus getDeleteStatus() {
        return deleteStatus;
    }

    public NsUser getWriter() {
        return detail.getWriter();
    }

    public void toQuestion(Question question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "Answer [id=" + getId() + ", writer=" + detail.getWriter() + ", contents=" + detail.getContents() + "]";
    }

    private static Question validateQuestion(Question question) {
        if(question == null) {
            throw new NotFoundException();
        }

        return question;
    }

    private static NsUser validateWriter(NsUser writer) {
        if(writer == null) {
            throw new UnAuthorizedException();
        }

        return writer;
    }

    private void checkDeletionAvailability(NsUser loginUser) throws CannotDeleteException {
        if (!detail.isOwner(loginUser)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }

    private void changeStatusToBeDeleted() {
        this.deleteStatus = DeleteStatus.YES;
    }

    private DeleteHistory createDeleteHistory(LocalDateTime now) {
        return new DeleteHistory(ContentType.ANSWER, this.id, this.detail.getWriter(), now);
    }

}
