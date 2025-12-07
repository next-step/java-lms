package nextstep.qna.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

public class Question {
    private Long id;

    private QuestionContent content;

    private NsUser writer;

    private Answers answers = new Answers();

    private boolean deleted = false;

    private LocalDateTime createdDate = LocalDateTime.now();

    private LocalDateTime updatedDate;

    public Question() {
    }

    public Question(NsUser writer, QuestionContent content) {
        this(0L, writer, content);
    }

    public Question(Long id, NsUser writer, QuestionContent content) {
        this.id = id;
        this.writer = writer;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return content.title();
    }


    public String getContents() {
        return content.contents();
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

    public Answers getAnswers() {
        return answers;
    }

    public List<DeleteHistory> delete(NsUser user) throws CannotDeleteException {
        if (!isOwner(user)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        setDeleted(true);
        deleteHistories.add(
                new DeleteHistory(ContentType.QUESTION, id, writer, LocalDateTime.now()));
        deleteHistories.addAll(answers.deleteAll(user));
        return deleteHistories;
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", content=" + content + ", writer=" + writer + "]";
    }
}
