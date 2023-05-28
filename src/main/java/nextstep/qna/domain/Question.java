package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Question {
    private Long id;

    private String title;

    private String contents;

    private NsUser writer;

    private Answers answers;

    private boolean deleted = false;

    private LocalDateTime createdDate = LocalDateTime.now();

    private LocalDateTime updatedDate;

    public Question() {
    }

    public Question(NsUser writer, String title, String contents, Answers answers) {

        this(0L, writer, title, contents, answers);
    }

    public Question(Long id, NsUser writer, String title, String contents, Answers answers) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
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

    public boolean isOwner(NsUser loginUser) {
        return writer.equals(loginUser);
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void addAnswer(Answer answer) {
        answer.toQuestion(this);
        this.answers.addOne(answer);
    }
    public Answers getAnswers() {
        return this.answers;
    }

    private void checkEnableDeleteQuestion(NsUser loginUser) throws CannotDeleteException {
        if(!this.isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
    }
    public List<DeleteHistory> deleteQuestion(NsUser loginUser) throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>(List.of(new DeleteHistory(this)));
                this.checkEnableDeleteQuestion(loginUser);
        if(getAnswers().hasAnswers()) {
            deleteHistories.addAll(getAnswers().deleteAnswers(loginUser));

        }
        this.deleted();
        return deleteHistories;
    }

    private void deleted() {
        this.deleted = true;
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
