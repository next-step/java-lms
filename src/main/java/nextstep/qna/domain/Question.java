package nextstep.qna.domain;

import nextstep.global.domain.BaseTimeDomain;
import nextstep.qna.CannotDeleteException;
import nextstep.qna.domain.enums.DeleteStatus;
import nextstep.qna.domain.vo.QuestionDetail;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Question extends BaseTimeDomain {
    private Long id;

    private QuestionDetail detail;

    private List<Answer> answers = new ArrayList<>();

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

    public List<DeleteHistory> delete(NsUser loginUser, LocalDateTime now) throws CannotDeleteException {
        if (!detail.isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }

        this.deleteStatus = DeleteStatus.YES;

        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, this.id, this.detail.getWriter(), now));

        for (Answer answer : this.answers) {
            deleteHistories.add(answer.delete(this.getDetail().getWriter(), now));
        }

        return deleteHistories;
    }

    public DeleteStatus getDeleteStatus() {
        return deleteStatus;
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + detail.getTitle() + ", contents=" + detail.getContents() + ", writer=" + detail.getContents() + "]";
    }
}
