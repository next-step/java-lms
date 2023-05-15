package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Question {
    private static final String OTHERUSER_ANSWER_MESSAGE = "다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.";
    private static final String ALREADY_DELETED_MESSAGE = "이미 삭제된 질문입니다.";
    private static final String PERMISSION_DENIED_MESSAGE = "질문을 삭제할 권한이 없습니다.";
    private Long id;

    private String title;

    private String contents;

    private NsUser writer;

    private Answers answers = new Answers(new ArrayList<>());

    private boolean deleted = false;

    private final LocalDateTime createdDate = LocalDateTime.now();

    private LocalDateTime updatedDate;

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
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Question setTitle(String title) {
        this.title = title;
        updatedDate = LocalDateTime.now();
        return this;
    }

    public String getContents() {
        return contents;
    }

    public Question setContents(String contents) {
        this.contents = contents;
        updatedDate = LocalDateTime.now();
        return this;
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

    public Question setDeleted(boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public DeleteHistories delete(NsUser requestUser) throws CannotDeleteException {
        DeleteHistories deleteHistories = new DeleteHistories();
        deleteHistories.add(deleteQustion(requestUser));
        deleteHistories.addAll(deleteAnswers(requestUser));

        return deleteHistories;
    }

    private DeleteHistories deleteAnswers(NsUser requestUser) throws CannotDeleteException {
        DeleteHistories deleteHistories = new DeleteHistories();
        try {
            deleteHistories.addAll(answers.deleteAll(requestUser));
        } catch (CannotDeleteException e) {
            setDeleted(false);
            throw new CannotDeleteException(OTHERUSER_ANSWER_MESSAGE);
        }
        return deleteHistories;
    }

    private DeleteHistory deleteQustion(NsUser requestUser) throws CannotDeleteException {
        if (isDeleted()) {
            throw new CannotDeleteException(ALREADY_DELETED_MESSAGE);
        }
        if (!isOwner(requestUser)) {
            throw new CannotDeleteException(PERMISSION_DENIED_MESSAGE);
        }
        setDeleted(true);
        return new DeleteHistory(ContentType.QUESTION, id, requestUser, LocalDateTime.now());
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
