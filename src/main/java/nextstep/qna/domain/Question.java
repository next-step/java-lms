package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
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

    public Question(NsUser writer, String title, String contents) {
        this(0L, writer, title, contents);
    }

    public Question(Long id, NsUser writer, String title, String contents) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.answers = Answers.init();
    }

    public Long getId() {
        return id;
    }

    public NsUser getWriter() {
        return writer;
    }

    public void addAnswer(Answer answer) {
        answer.toQuestion(this);
        this.answers = answers.add(answer);
    }

    public List<DeleteHistory> deleted(NsUser loginUser) {
        validateDelete(loginUser);
        delete();
        return deletedHistory(loginUser);
    }

    private void validateDelete(NsUser loginUser) {
        validateIsOwner(loginUser);
    }

    private void validateIsOwner(NsUser loginUser) {
        if(!isOwner(loginUser)) {
            throw new CannotDeleteException("다른 사용자가 작성한 질문은 삭제할 수 없습니다.");
        }
    }

    public boolean isOwner(NsUser loginUser) {
        return writer.equals(loginUser);
    }

    public boolean isDeleted() {
        return deleted;
    }

    private void delete() {
        this.deleted = true;
    }

    private List<DeleteHistory> deletedHistory(NsUser loginUser) {
        List<DeleteHistory> result = new ArrayList<>();
        result.add(deletedQuestion());
        result.addAll(deletedAnswer(loginUser));

        return Collections.unmodifiableList(result);
    }

    private DeleteHistory deletedQuestion() {
        return DeleteHistory.question(this.id, this.writer);
    }

    private List<DeleteHistory> deletedAnswer(NsUser loginUser) {
        return this.answers.deleted(loginUser);
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
