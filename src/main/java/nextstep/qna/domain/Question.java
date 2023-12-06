package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Question {

    private final static String NO_PERMISSION_TO_DELETE_QUESTIONS = "질문을 삭제할 권한이 없습니다.";

    private String title;

    private Answers answers = new Answers();

    private Contents contents;

    private boolean deleted = false;

    public Question() {
    }

    public Question(String title, Contents contents) {
        this.title = title;
        this.contents = contents;
    }

    public String getTitle() {
        return title;
    }

    public Question setTitle(String title) {
        this.title = title;
        return this;
    }

    public void addAnswer(Answer answer) {
        answer.toQuestion(this);
        answers.add(answer);
    }

    public boolean isDeleted() {
        return deleted;
    }

    public Answers getAnswers() {
        return answers;
    }

    public List<DeleteHistory> delete(NsUser loginUser) throws CannotDeleteException {
        if (contents.isNotOwner(loginUser)) {
            throw new CannotDeleteException(NO_PERMISSION_TO_DELETE_QUESTIONS);
        }
        this.deleted = true;
        List<DeleteHistory> deleteHistories = new DeleteHistory().makeDeleteHistorys(this.answers, contents, loginUser);

        return deleteHistories;
    }

    @Override
    public String toString() {
        return "Question [id=" + contents.getId() + ", title=" + title + ", contents=" + contents.getContents() + ", writer=" + contents.getWriter() + "]";
    }
}
