package nextstep.qna.domain;

import java.time.LocalDateTime;
import java.util.Optional;

import nextstep.common.BaseEntity;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

public class Question extends BaseEntity {

    private Long id;

    private String title;

    private String contents;

    private NsUser writer;

    private Answers answers = new Answers(this);

    private boolean deleted = false;

    public Question() {
    }

    public Question(NsUser writer, String title, String contents) {
        this(0L, writer, title, contents);
    }

    public Question(Long id, NsUser writer, String title, String contents) {
        super();
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

    public boolean isOwner(NsUser loginUser) {
        return writer.equals(loginUser);
    }


    public boolean isDeleted() {
        return deleted;
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }

    public Optional<DeleteHistory> deleteHistory() {
        if (this.isDeleted()) {
            return Optional.of(new DeleteHistory(ContentType.QUESTION, id, writer, LocalDateTime.now()));
        }

        return Optional.empty();
    }

    public DeleteHistories deleteHistoriesWithQnA() {
        DeleteHistories deleteHistories = new DeleteHistories();

        deleteHistory().ifPresent(deleteHistories::add);

        deleteHistories.addAll(answers.deleteHistories());

        return deleteHistories;
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    public void delete(NsUser loginUser) throws CannotDeleteException {
        deleteAnswers();

        deleteQuestion(loginUser);
    }

    private void deleteAnswers() throws CannotDeleteException {
        answers.deleteAll();
    }

    private void deleteQuestion(NsUser loginUser) throws CannotDeleteException {
        if (!isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }

        this.deleted = true;
    }
}
