package nextstep.qna.domain;

import nextstep.qna.NotFoundException;
import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Question {
    private Long id;

    private String title;

    private String contents;

    private NsUser writer;

    private Answers answers = new Answers(this);

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
        answers.addAnswer(answer);
    }

    public boolean isOwner(NsUser loginUser) {
        return writer.equals(loginUser);
    }

    public boolean isNotOwner(NsUser loginUser) {
        return !isOwner(loginUser);
    }

    public boolean isDeleted() {
        return deleted;
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }

    public List<DeleteHistory> delete(NsUser loginUser) {
        validateToDelete(loginUser);

        deleted = true;
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, id, writer, LocalDateTime.now()));
        deleteHistories.addAll(answers.deleteAll(loginUser));
        return deleteHistories;
    }

    private void validateToDelete(NsUser loginUser) {
        if (deleted) {
            throw new NotFoundException();
        }

        if (isNotOwner(loginUser)) {
            throw new UnAuthorizedException("질문을 삭제할 권한이 없습니다.");
        }

        if (isNotSameWriterOfAnswers()) {
            throw new UnAuthorizedException("질문자와 답변글의 모든 답변자 다른 경우, 삭제할 수 없습니다.");
        }
    }

    public boolean isSameWriterOfAnswers() {
        return answers.getWriters().size() == 1 && answers.getWriters().contains(writer);
    }

    public boolean isNotSameWriterOfAnswers() {
        return !isSameWriterOfAnswers();
    }
}
