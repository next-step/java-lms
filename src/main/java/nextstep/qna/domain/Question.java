package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Question {
    private Long id;

    private String title;

    private String contents;

    private NsUser writer;

    //private List<Answer> answers = new ArrayList<>();

    private Answers answers = new Answers(new ArrayList<>());

    private boolean deleted = false;

    private LocalDateTime createdDate = LocalDateTime.now();

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

    public Question(NsUser writer, String title, String contents, Answers answers) {
        this(0L, writer, title, contents);
        this.answers = answers;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Question setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContents() {
        return contents;
    }

    public Question setContents(String contents) {
        this.contents = contents;
        return this;
    }

    public NsUser getWriter() {
        return writer;
    }

    public void addAnswer(Answer answer) {
        answer.toQuestion(this);
        answers.add(answer);
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

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }

    public void delete(NsUser loginUser) throws CannotDeleteException {
        if (!Objects.equals(loginUser, writer)) {
            throw new CannotDeleteException("로그인 사용자와 질문한 사용자가 같아야 합니다.");
        }

        this.deleted = true;
        deleteAnswers(loginUser);
    }

    private void deleteAnswers(NsUser loginUser) throws CannotDeleteException {
        if (Objects.nonNull(this.answers)) {
            this.answers.deleteAnswers(loginUser);
        }
    }

    public DeleteHistory getDeleteHistory() {
        return new DeleteHistory(ContentType.QUESTION, id,  writer, LocalDateTime.now());
    }
}
