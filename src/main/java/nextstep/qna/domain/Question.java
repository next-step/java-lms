package nextstep.qna.domain;

import static nextstep.qna.domain.ContentType.QUESTION;

import java.util.ArrayList;
import java.util.List;

import nextstep.qna.error.CannotDeleteException;
import nextstep.users.domain.NsUser;

public class Question {

    private Long id;

    private String title;

    private String contents;

    private NsUser writer;

    private List<Answer> answers = new ArrayList<>();

    private boolean deleted = false;

    public Question(NsUser writer,
                    String title,
                    String contents) {
        this(0L, writer, title, contents);
    }

    public Question(Long id,
                    NsUser writer,
                    String title,
                    String contents) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }

    public Long getId() {
        return id;
    }

    public NsUser getWriter() {
        return writer;
    }

    public List<DeleteHistory> delete(NsUser loginUser, long questionId) throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(deleteQuestions(loginUser, questionId));

        Answers answerList = new Answers(answers);
        deleteHistories.addAll(answerList.deleteAnswers(loginUser));

        return deleteHistories;
    }

    private DeleteHistory deleteQuestions(NsUser loginUser, long questionId) throws CannotDeleteException {
        if (!writer.equals(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
        deleted();

        return new DeleteHistory(QUESTION, questionId, writer);
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void deleted() {
        deleted = true;
    }

    public void addAnswer(Answer answer) {
        answer.toQuestion(this);
        answers.add(answer);
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
