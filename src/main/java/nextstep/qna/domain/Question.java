package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Question extends SoftDeletable{
    private QuestionBody content;
    private Answers answers = new Answers();
    private TimeStamp timestamp = new TimeStamp();

    public Question(NsUser writer, String title, String contents) {
        this(0L, writer, title, contents);
    }

    public Question(Long id, NsUser writer, String title, String contents) {
        this(new QuestionBody(id, writer, title, contents));
    }

    public Question(QuestionBody questionBody) {
        this.content = questionBody;
    }

    public Long getId() {
        return content.getId();
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    private boolean isOwner(NsUser loginUser) {
        return content.isWrittenBy(loginUser);
    }

    @Override
    public void deleteBy(NsUser requestUser) throws CannotDeleteException {
        if (!isOwner(requestUser)) {
            throw new CannotDeleteException("작성자 외에는 질문을 삭제할 수 없습니다.");
        }

        delete();
        answers.deleteBy(requestUser);
    }

    public List<DeleteHistory> toDeleteHistories() throws CannotDeleteException {
        if (!isDeleted()) {
            return Collections.emptyList();
        }
        List<DeleteHistory> deleteHistories = new ArrayList<>();

        deleteHistories.add(content.toDeleteHistory());
        deleteHistories.addAll(answers.toDeleteHistories());
        return deleteHistories;
    }

    @Override
    public String toString() {
        return "Question [" + content.toString() + "]";
    }
}
