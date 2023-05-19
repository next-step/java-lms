package nextstep.qna.domain;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.List;

public class Question {
    private Long id;

    private String title;

    private String contents;

    private NsUser writer;

    private final Answers answers = new Answers();

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


    public String getContents() {
        return contents;
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

    public Answers getAnswers() {
        return answers;
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }


    public List<DeleteHistory> deleteBy(NsUser writer) throws CannotDeleteException {
        validateOwner(writer);
        List<DeleteHistory> deletedAnswerHistory = answers.deleteAllBy(writer);
        DeleteHistory deletedQuestionHistory = deleted();

        return concat(deletedQuestionHistory, deletedAnswerHistory);
    }

    public void validateOwner(NsUser loginUser) throws CannotDeleteException {
        if (!isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
    }


    private DeleteHistory deleted() {
        this.deleted = true;
        return DeleteHistory.ofQuestion(this.id, this.writer);
    }

    private List<DeleteHistory> concat(DeleteHistory deletedQuestionHistory, List<DeleteHistory> deletedAnswerHistory) {
        return Stream.concat(Stream.of(deletedQuestionHistory), deletedAnswerHistory.stream()).collect(Collectors.toList());
    }

}
