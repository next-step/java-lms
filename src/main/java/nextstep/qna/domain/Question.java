package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public NsUser getWriter() {
        return writer;
    }

    public void addAnswer(Answer answer) {
        answer.toQuestion(this);
        answers.add(answer);
    }

    public boolean validateOwner(NsUser loginUser) throws CannotDeleteException {
        if (writer != loginUser) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
        return true;
    }

    public List<DeleteHistory> delete(NsUser nsUser) throws CannotDeleteException {
        validateOwner(nsUser);

        Answers answersCollection = new Answers(answers);
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        this.deleted = true;
        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, id, writer, createdDate));

        return Stream.of(deleteHistories, answersCollection.deleteAll(writer))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public boolean isDeleted() {
        return deleted;
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
