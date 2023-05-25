package nextstep.qna.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import nextstep.qna.UnAuthenticationException;
import nextstep.qna.exception.QnAException;
import nextstep.qna.exception.QnAExceptionCode;
import nextstep.users.domain.NsUser;

public class Question extends AbstractQnA {

    private Long id;

    private String title;

    private String contents;

    private Answers answers = new Answers();

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

    @Override
    public List<DeleteHistory> delete(NsUser loginUser) throws QnAException {
        if (super.isDeleted()) {
            throw new QnAException(QnAExceptionCode.NOT_EXIST_QUESTION);
        }

        super.validateWriter(loginUser);
        super.changeDeleteStatus(true);

        List<DeleteHistory> deleteHistories = new ArrayList<>();

        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, this.id, this.writer, LocalDateTime.now()));
        deleteHistories.addAll(this.answers.delete(loginUser));

        return Collections.unmodifiableList(deleteHistories);
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
