package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.domain.base.BaseDate;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.time.LocalDateTime.now;
import static nextstep.qna.domain.ContentType.QUESTION;

public class Question extends BaseDate {

    private Long id;

    private Content content;

    private Answers answers = new Answers();

    public Question() {
    }

    public Question(Long id, Content content) {
        this.id = id;
        this.content = content;
    }

    public static Question toQuestion(NsUser writer, String title, String contents) {
        return new Question(0L, new Content(title, contents, writer));
    }

    public Long getId() {
        return id;
    }

    public void addAnswer(Answer answer) {
        answer.toQuestion(this);
        answers.addAnswer(answer);
    }

    public List<DeleteHistory> delete(NsUser loginUser) throws CannotDeleteException {
        if (!content.isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
        content.delete();
        return addAllDeleteHistories(makeDeleteHistory(loginUser), answers.delete(loginUser));
    }

    private DeleteHistory makeDeleteHistory(NsUser loginUser) {
        return new DeleteHistory(QUESTION, id, loginUser, now());
    }

    private List<DeleteHistory> addAllDeleteHistories(DeleteHistory questionDeleteHistory, List<DeleteHistory> answerDeleteHistories) {
        List<DeleteHistory> deleteHistories = new ArrayList<>(Arrays.asList(questionDeleteHistory));
        for (DeleteHistory deleteHistory : answerDeleteHistories) {
            deleteHistories.add(deleteHistory);
        }
        return deleteHistories;
    }

    public boolean isDeleted() {
        return content.isDeleted();
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", content=" + content + "]";
    }
}
