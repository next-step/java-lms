package nextstep.qna.domain;

import java.util.Collection;
import java.util.Collections;
import nextstep.qna.CannotDeleteException;
import nextstep.qna.UnAuthenticationException;
import nextstep.qna.UnAuthorizedException;
import nextstep.qna.exception.Exceptions;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Question extends BaseDomainImpl {

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

    public Answers getAnswers() {
        return answers;
    }

    @Override
    public List<DeleteHistory> delete(NsUser loginUser) throws UnAuthenticationException {
        if (super.isDeleted()) {
            throw new IllegalStateException(Exceptions.NOT_EXIST_QUESTION.message());
        }

        super.validateWriter(loginUser);
        super.changeDeleteStatus(YN.Y);

        List<DeleteHistory> deleteHistories = new ArrayList<>();

        deleteHistories.add(DeleteHistory.of(ContentType.QUESTION, this.id, this.writer, LocalDateTime.now()));
        deleteHistories.addAll(this.answers.delete(loginUser));

        return Collections.unmodifiableList(deleteHistories);
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
