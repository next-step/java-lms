package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Question extends DeletableBaseEntity {
    private QuestionContent content;

    private NsUser writer;

    private Answers answers = new Answers();

    public Question(NsUser writer, String title, String contents) {
        this(0L, writer, title, contents);
    }

    public Question(Long id, NsUser writer, String title, String contents) {
        super(id);
        this.writer = writer;
        this.content = new QuestionContent(title, contents);
    }

    public NsUser getWriter() {
        return writer;
    }

    public void addAnswer(Answer answer) {
        answer.toQuestion(this);
        answers.add(answer);
    }

    public boolean isOwner(NsUser loginUser) {
        return writer.equals(loginUser);
    }

    private void validateOwner(NsUser loginUser) throws CannotDeleteException {
        if (!isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
    }

    public DeleteHistory deleteHistory() {
        return DeleteHistory.from(ContentType.QUESTION, getId(), writer, LocalDateTime.now());
    }

    public void delete(NsUser loginUser) throws CannotDeleteException {
        validateOwner(loginUser);
        delete();

        answers.deleteAll(loginUser);
    }

    public List<DeleteHistory> toDeleteHistories() {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(deleteHistory());
        deleteHistories.addAll(answers.toDeleteHistories());
        return deleteHistories;
    }
}
