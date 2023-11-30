package nextstep.qna.domain;

import java.util.stream.Collectors;
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

    private LocalDateTime createedDate = LocalDateTime.now();

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

    public NsUser getWriter() {
        return writer;
    }

    public void addAnswer(Answer answer) {
        answer.toQuestion(this);
        answers.add(answer);
    }

    public void isOwner(NsUser loginUser) throws CannotDeleteException {
        if (!writer.equals(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void delete(NsUser user) throws CannotDeleteException {
        isOwner(user);
        isAnswerOwner(user);
        this.answers.forEach(Answer::delete);
        this.deleted = true;
    }

    private void isAnswerOwner(NsUser user) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.isOwner(user);
        }
    }

    public List<DeleteHistory> toHistory() {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, this.id, this.writer, LocalDateTime.now()));
        List<DeleteHistory> deleteAnswerHistory = this.answers.stream()
                                                              .map(Answer::toHistory)
                                                              .collect(Collectors.toList());
        deleteHistories.addAll(deleteAnswerHistory);

        return deleteHistories;
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }


}
