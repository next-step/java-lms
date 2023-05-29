package nextstep.qna.domain;

import nextstep.global.domain.BaseTimeDomain;
import nextstep.qna.CannotDeleteException;
import nextstep.qna.domain.enums.DeleteStatus;
import nextstep.qna.domain.vo.QuestionDetail;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Question extends BaseTimeDomain {
    private Long id;

    private QuestionDetail detail;

    private Answers answers = Answers.create();

    private DeleteStatus deleteStatus = DeleteStatus.NO;

    public static Question of(Long id, NsUser writer, String title, String contents, LocalDateTime createdDate) {
        return new Question(id, writer, title, contents ,createdDate);
    }

    private Question(Long id, NsUser writer, String title, String contents, LocalDateTime createdDate) {
        this.id = id;
        this.detail = QuestionDetail.of(title, contents, writer);
        this.createdDate = createdDate;
    }

    public Long getId() {
        return id;
    }

    public QuestionDetail getDetail() {
        return detail;
    }

    public void addAnswer(Answer answer) {
        answer.toQuestion(this);
        answers.add(answer);
    }

    public DeleteHistories delete(NsUser loginUser, LocalDateTime now) throws CannotDeleteException {
        checkDeletionAvailability(loginUser);
        changeStatusToBeDeleted();
        return createDeleteHistories(now, deleteAnswers(now));
    }

    private DeleteHistories createDeleteHistories(LocalDateTime now, DeleteHistories deleteHistoriesOfAnswers) {
        DeleteHistories deleteHistories = DeleteHistories.create();
        deleteHistories.add(DeleteHistory.ofQuestion(this.id, this.detail.getWriter(), now));
        deleteHistories.concat(deleteHistoriesOfAnswers);
        return deleteHistories;
    }

    private DeleteHistories deleteAnswers(LocalDateTime now) throws CannotDeleteException {
        return this.answers.deleteAnswers(this.detail.getWriter(), now);
    }

    private void changeStatusToBeDeleted() {
        this.deleteStatus = DeleteStatus.YES;
    }

    private void checkDeletionAvailability(NsUser loginUser) throws CannotDeleteException {
        if (!detail.isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
    }

    public DeleteStatus getDeleteStatus() {
        return deleteStatus;
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + detail.getTitle() + ", contents=" + detail.getContents() + ", writer=" + detail.getContents() + "]";
    }
}
