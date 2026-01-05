package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.ContentNotDeletedException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Question extends Content {
    private String title;
    private Answers answers;

    public Question() {
    }

    public Question(NsUser writer, String title, String contents) {
        this(0L, writer, title, contents);
    }

    public Question(Long id, NsUser writer, String title, String contents) {
        super(id, writer, contents);
        this.title = title;
        this.answers = new Answers();
    }

    public void delete(NsUser loginUser) throws CannotDeleteException {
        if (!isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }

        info.delete();
        answers.delete(loginUser);
    }

    public List<DeleteHistory> history() throws ContentNotDeletedException {
        if (!isDeleted()) {
            throw new ContentNotDeletedException("아직 삭제되지 않은 질문입니다.");
        }

        List<DeleteHistory> deleteHistory = new ArrayList<>(List.of(new DeleteHistory(ContentType.QUESTION, id, info.getWriter(), LocalDateTime.now())));
        deleteHistory.addAll(answers.history());

        return deleteHistory;
    }


    public void addAnswer(Answer answer) {
        answer.toQuestion(this);
        answers.add(answer);
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + info + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Question question = (Question) o;
        return Objects.equals(title, question.title) && Objects.equals(answers, question.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title, answers);
    }
}
