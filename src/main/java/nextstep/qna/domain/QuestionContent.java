package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.List;

public class QuestionContent {
    private Content content;
    private Answers answers = new Answers();

    public QuestionContent(String title, String contents, NsUser writer) {
        this(new Content(title, contents, writer));
    }

    public QuestionContent(Content content) {
        this.content = content;
    }

    public String getTitle() {
        return this.content.getTitle();
    }

    public String getContents() {
        return this.content.getContents();
    }

    public NsUser getWriter() {
        return this.content.getWriter();
    }

    public void setTitle(String title) {
        this.content.setTitle(title);
    }

    public void setContents(String contents) {
        this.content.setContents(contents);
    }

    public boolean isOwner(NsUser loginUser) {
        return this.content.isOwner(loginUser);
    }

    public void canDeleteAnswers(NsUser loginUser) throws CannotDeleteException {
        this.answers.canDelete(loginUser);
    }

    public void addAnswer(Answer answer) {
        this.answers.add(answer);
    }

    public List<DeleteHistory> toDeleteHistory() {
        return this.answers.toDeleteHistory();
    }
}
