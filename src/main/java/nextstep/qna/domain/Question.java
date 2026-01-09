package nextstep.qna.domain;

import nextstep.core.domain.SoftDeletableBaseEntity;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Question extends SoftDeletableBaseEntity {
    private String title;

    private String contents;

    private NsUser writer;

    private Answers answers = new Answers();

    public Question() {
    }

    public Question(NsUser writer, String title, String contents) {
        this(0L, writer, title, contents);
    }

    public Question(Long id, NsUser writer, String title, String contents) {
        super(id);
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
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

    public Answers getAnswers() {
        return answers;
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }

    public void delete(NsUser loginUser) throws CannotDeleteException {
        validateOwner(loginUser);
        answers.validateOwner(loginUser);
        answers.delete();
        deleteQuestion();
    }

    private void deleteQuestion() {
        markDeleted();
    }

    public List<DeleteHistory> deleteHistories() {
        List<DeleteHistory> histories = new ArrayList<>();
        histories.add(deleteHistory());
        histories.addAll(answers.deleteHistories());
        return histories;
    }

    private void validateOwner(NsUser loginUser) throws CannotDeleteException {
        if (!isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
    }

    public DeleteHistory deleteHistory() {
        return new DeleteHistory(ContentType.QUESTION, getId(), this.writer, LocalDateTime.now());
    }
}
