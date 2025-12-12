package nextstep.qna.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

public class Question extends SoftDeletableBaseEntity {
    private QuestionContent content;

    private Answers answers = new Answers();

    public Question(NsUser writer, QuestionContent content) {
        this(0L, writer, content);
    }

    public Question(Long id, NsUser writer, QuestionContent content) {
        super(id, writer);
        this.content = content;
    }

    public String getTitle() {
        return content.title();
    }

    public String getContents() {
        return content.contents();
    }

    public void addAnswer(Answer answer) {
        answer.toQuestion(this);
        answers.add(answer);
    }

    public Answers getAnswers() {
        return answers;
    }

    public void delete(NsUser user) throws CannotDeleteException {
        if (!isOwner(user)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
        markAsDeleted();
        answers.delete(user);
    }

    public List<DeleteHistory> toDeleteHistories() throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(
                new DeleteHistory(ContentType.QUESTION, getId(), getWriter(), LocalDateTime.now()));
        deleteHistories.addAll(answers.toDeleteHistories());
        return deleteHistories;
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", content=" + content + ", writer=" + getWriter() + "]";
    }
}
