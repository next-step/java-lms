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
        answers.add(answer);
    }

    public List<DeleteHistory> delete(NsUser user) throws CannotDeleteException {
        if (!writer.equals(user)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
        List<DeleteHistory> deleteHistories = new ArrayList<>(
                List.of(new DeleteHistory(ContentType.QUESTION, id, writer, LocalDateTime.now())));
        deleteHistories.addAll(answers.deleteAll(user));
        deleted = true;
        return deleteHistories;
    }

    public boolean isDeleted() {
        return deleted;
    }


    @Override
    public String toString() {
        return "Question [id=" + id + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
