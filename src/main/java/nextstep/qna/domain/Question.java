package nextstep.qna.domain;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }

    public void validateOwner(NsUser loginUser) throws CannotDeleteException {
        if (!isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
    }

    public void validateHasAnswerByOtherUser() throws CannotDeleteException {
        Optional<Answer> optionalAnswer = findNotAnswerOfWriter();
        if (optionalAnswer.isPresent()) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }

    private Optional<Answer> findNotAnswerOfWriter() {
        return answers.stream()
            .filter(answer -> !answer.isOwner(writer))
            .findFirst();
    }

    public DeleteHistory deleted() {
        this.deleted = true;
        return new DeleteHistory(ContentType.QUESTION, this.id, this.writer, LocalDateTime.now());
    }

    public List<DeleteHistory> deleteBy(NsUser loginUser) throws CannotDeleteException {
        validateOwner(loginUser);
        validateHasAnswerByOtherUser();
        DeleteHistory deletedQuestionHistory = deleted();
        List<DeleteHistory> deletedAnswerHistory = answers.stream().map(Answer::deleted)
            .collect(Collectors.toList());

        return concat(deletedQuestionHistory, deletedAnswerHistory);
    }

    private List<DeleteHistory> concat(DeleteHistory deletedQuestionHistory, List<DeleteHistory> deletedAnswerHistory) {
        return Stream.concat(Stream.of(deletedQuestionHistory), deletedAnswerHistory.stream()).collect(Collectors.toList());
    }

}
