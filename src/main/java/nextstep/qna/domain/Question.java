package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Question{
    private final Long id;

    private final String title;

    private final String contents;

    private final NsUser writer;

    private final Answers answers;

    private boolean deleted = false;

    private final LocalDateTime createdDate = LocalDateTime.now();

    private final LocalDateTime updatedDate;

    public Question(NsUser writer, String title, String contents) {
        this(0L, writer, title, contents);
    }

    public Question(NsUser writer, String title, String contents, boolean deleted, List<Answer> answers) {
        this(0L, title, contents, writer, answers, deleted, LocalDateTime.now());
    }

    public Question(Long id, NsUser writer, String title, String contents) {
        this(id, title, contents, writer, new ArrayList<>(), false, LocalDateTime.now());
    }

    private Question(Long id, String title, String contents, NsUser writer, List<Answer> answers, boolean deleted, LocalDateTime updatedDate) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.writer = writer;
        this.answers = new Answers(answers);
        this.deleted = deleted;
        this.updatedDate = updatedDate;
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

    public void delete(NsUser nsUser){
        checkIfOwner(nsUser);
        this.answers.delete(nsUser);
        this.deleted = true;
    }

    public void checkIfOwner(NsUser nsUser){
        if(!isOwner(nsUser)){
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question)) return false;
        Question question = (Question) o;
        return deleted == question.deleted && Objects.equals(id, question.id) && Objects.equals(title, question.title) && Objects.equals(contents, question.contents) && Objects.equals(writer, question.writer) && Objects.equals(answers, question.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, contents, writer, answers, deleted);
    }

    public List<DeleteHistory> generateDeleteHistory() {
        List<DeleteHistory> deleteHistories = new ArrayList<>(List.of(new DeleteHistory(ContentType.QUESTION, this.id, this.writer)));
        deleteHistories.addAll(this.answers.generateDeleteHistory());
        return deleteHistories;
    }
}
