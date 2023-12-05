package nextstep.qna.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

public class Question {

    private Long id;

    private String title;

    private String contents;

    private NsUser writer;

    private List<Answer> answers = new ArrayList<>();

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

    public List<Answer> getAnswers() {
        return answers;
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents
            + ", writer=" + writer + "]";
    }

    private Question deleteQuestion(NsUser writer) throws CannotDeleteException {
        if (!this.isOwner(writer)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
        return this.setDeleted(true);
    }

    private List<Answer> deleteAnswersOfThisQuestion(NsUser writer) {
        return getAnswers().stream().map(answer -> {
            try {
                return answer.delete(writer);
            } catch (CannotDeleteException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }

    public List<DeleteHistory> deleteQuestionAndRelatedAnswer(NsUser writer, LocalDateTime now)
        throws CannotDeleteException {
        Question question = deleteQuestion(writer);
        List<Answer> answers = deleteAnswersOfThisQuestion(writer);

        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(
            new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter(), now));

        for (Answer answer : answers) {
            deleteHistories.add(
                new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), now));
        }

        return deleteHistories;
    }
}
