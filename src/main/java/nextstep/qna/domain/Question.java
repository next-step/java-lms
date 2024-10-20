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
        this.answers = Answers.createAnswers(new ArrayList<>());
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

    public Question setDeleted() {
        this.deleted = true;
        return this;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void checkIfQuestionOwner(NsUser loginUser) throws CannotDeleteException {
        if (isDifferentOwner(loginUser)) {
            throw new CannotDeleteException(INVALID_OWNER_EXCEPTION_MESSAGE);
        }
    }

    private boolean isDifferentOwner(NsUser loginUser) {
        return !writer.equals(loginUser);
    }

    public void checkIfAnswerOwner(NsUser loginUser) throws CannotDeleteException {
        answers.validateAnswerOwners(loginUser);
    }

    public List<DeleteHistory> generateDeleteHistories() {
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
}
