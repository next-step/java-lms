package nextstep.qna.domain;

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


    public void addAnswer(Answer answer) {
        answer.toQuestion(this);
        this.answers = answers.add(answer);
    }

    public boolean isOwner(NsUser loginUser) {
        return writer.equals(loginUser);
    }

    public boolean isDeleted() {
        return deleted;
    }

    public List<DeleteHistory> delete(NsUser loginUser) {
        List<DeleteHistory> deleteHistories = new ArrayList<>();

        checkWriter(loginUser);
        changeDeleteState(true);
        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, this.id, this.writer));

        deleteHistories.addAll(answers.delete(loginUser));

        return deleteHistories;
    }

    private void changeDeleteState(boolean state) {
        this.deleted = state;
    }

    private void checkWriter(NsUser loginUser) {
        if (!isOwner(loginUser)) {
            throw new UnAuthorizedException("질문을 삭제할 권한이 없습니다.");
        }
    }

    @Override
    public String toString() {
        return "Question [id=" + id + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }


}
