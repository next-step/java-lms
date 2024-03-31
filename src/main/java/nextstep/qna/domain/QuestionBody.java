package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.List;

public class QuestionBody {
    private QuestionKey questionKey;
    private QuestionContent questionContent;

    public QuestionBody(Long id, String title, String contents, NsUser writer) {
        this(new QuestionKey(id), new QuestionContent(title, contents, writer));
    }

    public QuestionBody(QuestionKey questionKey, QuestionContent questionContent) {
        this.questionKey = questionKey;
        this.questionContent = questionContent;
    }

    public Long getId() {
        return this.questionKey.getId();
    }

    public QuestionContent getQuestionContent() {
        return questionContent;
    }

    public String getTitle() {
        return this.questionContent.getTitle();
    }

    public void setTitle(String title) {
        this.questionContent.setTitle(title);
    }

    public String getContents() {
        return this.questionContent.getContents();
    }

    public void setContents(String contents) {
        this.questionContent.setContents(contents);
    }

    public NsUser getWriter() {
        return this.questionContent.getWriter();
    }

    public boolean isOwner(NsUser loginUser) {
        return this.questionContent.isOwner(loginUser);
    }

    public void canDeleteAnswers(NsUser loginUser) throws CannotDeleteException {
        this.questionContent.canDeleteAnswers(loginUser);
    }

    public void addAnswer(Answer answer) {
        this.questionContent.addAnswer(answer);
    }

    public List<DeleteHistory> toDeleteHistory() {
        return this.questionContent.toDeleteHistory();
    }
}
