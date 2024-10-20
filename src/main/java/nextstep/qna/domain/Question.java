package nextstep.qna.domain;

import nextstep.qna.domain.common.BaseTime;
import nextstep.qna.exception.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Question extends BaseTime {

    public static final String INVALID_OWNER_EXCEPTION_MESSAGE = "질문을 삭제할 권한이 없습니다.";

    private Long id;

    private String title;

    private String contents;

    private NsUser writer;

    private Answers answers;

    private boolean deleted = false;

    public Question() {
    }

    public Question(NsUser writer, String title, String contents) {
        this(0L, writer, title, contents);
    }

    public Question(Long id, NsUser writer, String title, String contents) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.answers = new Answers(new ArrayList<>());
    }

    public Long getId() {
        return id;
    }

    public NsUser getWriter() {
        return writer;
    }

    public void addAnswer(Answer answer) {
        answers.addAnswer(answer);
    }

    public void deleteQuestion() {
        this.deleted = true;
    }

    public boolean isDeleted() {
        return deleted;
    }

    private void checkIfQuestionOwner(NsUser loginUser) throws CannotDeleteException {
        if (isDifferentOwner(loginUser)) {
            throw new CannotDeleteException(INVALID_OWNER_EXCEPTION_MESSAGE);
        }
    }

    private boolean isDifferentOwner(NsUser loginUser) {
        return !writer.matchUser(loginUser);
    }

    private void checkIfAnswerOwner(NsUser loginUser) throws CannotDeleteException {
        answers.validateAnswerOwners(loginUser);
    }

    private List<DeleteHistory> generateDeleteHistories() {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(createDeleteHistory());
        deleteHistories.addAll(answers.generateAnswerDeleteHistories());
        return deleteHistories;
    }

    private DeleteHistory createDeleteHistory() {
        return new DeleteHistory(ContentType.QUESTION, id, writer);
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }

    public List<DeleteHistory> delete(NsUser loginUser) throws CannotDeleteException {
        checkIfQuestionOwner(loginUser);
        checkIfAnswerOwner(loginUser);
        deleteQuestion();
        return generateDeleteHistories();
    }
}
