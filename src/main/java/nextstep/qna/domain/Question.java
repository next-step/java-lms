package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Question {
    private Long id;

    private String title;

    private String contents;

    private NsUser writer;

    //AS-IS
    private List<Answer> answers = new ArrayList<>();

    //TO-BE
    private Answers answerCollections;

    private boolean deleted = false;

    private LocalDateTime createdDate = LocalDateTime.now();

    private LocalDateTime updatedDate;

    public Question() {
    }

    public Question(NsUser writer) {
        this(0L, writer, null, null);
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

    //TO-BE
    public Question(Long id, NsUser writer, String title, String contents, Answers answerCollections) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.answerCollections = answerCollections;

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

    //AS-IS
    public void addAnswer(Answer answer) {
        answer.toQuestion(this);
        answers.add(answer);
    }

    //AS-IS
    public boolean isOwner(NsUser loginUser) {
        return writer.equals(loginUser);
    }

    //TO-BE
    public void delete(NsUser loginUser) throws CannotDeleteException {
        validateAuthority(loginUser);
        validateWriterOfAnswers();
    }

    private void validateAuthority(NsUser loginUser) throws CannotDeleteException {
        if (!writer.equals(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
    }

    private void validateWriterOfAnswers() throws CannotDeleteException {
        if(!answerCollections.isAllSameBy(this.writer)){
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }

    public Question setDeleted(boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public boolean isDeleted() {
        return deleted;
    }

    //AS-IS
    public List<Answer> getAnswers() {
        return answers;
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }

}
