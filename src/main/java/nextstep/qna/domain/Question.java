package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
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

    public List<DeleteHistory> delete(NsUser loginUser) throws CannotDeleteException {
        if (!isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }

        this.deleted = true;
        List<DeleteHistory> deleteHistory = new ArrayList<>(List.of(new DeleteHistory(ContentType.QUESTION, id, writer, LocalDateTime.now())));
        deleteHistory.addAll(answers.delete(loginUser));

        return deleteHistory;

    }


    public void addAnswer(Answer answer) {
        answer.toQuestion(this);
        answers.add(answer);
    }

    public List<Answer> getAnswers() {
        return answers.getAnswers();
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
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
