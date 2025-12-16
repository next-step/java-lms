package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.List;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Question extends DeletableBaseEntity {
    private Long id;

    private QuestionContent questionContent;

    private NsUser writer;

    private Answers answers = new Answers();

    public Question() {
    }

    public Question(NsUser writer, String title, String contents) {
        this(0L, writer, title, contents);
    }

    public Question(NsUser writer, QuestionContent questionContent) {
        this(0L, writer, questionContent);
    }

    public Question(Long id, NsUser writer, String title, String contents) {
        this(id, writer, new QuestionContent(title, contents));
    }

    public Question(Long id, NsUser writer, QuestionContent questionContent) {
        this.id = id;
        this.writer = writer;
        this.questionContent = questionContent;
    }

    public Long getId() {
        return id;
    }

    public NsUser getWriter() {
        return writer;
    }

    public void addAnswer(Answer answer) {
        answer.toQuestion(this);
        answers.add(answer);
    }

    public void delete(NsUser loginUser) throws CannotDeleteException {
        validateOwner(loginUser);
        answers.deleteAll(loginUser);
        delete();
    }

    private void validateOwner(NsUser writer) throws CannotDeleteException {
        if (isNotOwner(writer)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
    }

    private boolean isNotOwner(NsUser loginUser) {
        return !writer.equals(loginUser);
    }

    public boolean isDeleted() {
        return super.isDeleted();
    }

    public Answers answers() {
        return answers;
    }

    public List<DeleteHistory> deleteHistories() {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, id, writer, LocalDateTime.now()));
        deleteHistories.addAll(answers.deleteHistories());
        return deleteHistories;
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + questionContent.title()
                + ", contents=" + questionContent.contents() + ", writer=" + writer + "]";
    }
}
