package nextstep.qna.domain;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import nextstep.qna.NotFoundException;
import nextstep.qna.UnAuthenticationException;
import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUser;

public class Answer extends BaseDomainImpl {

    private Long id;

    private Question question;

    private String contents;

    public Answer() {
    }

    public Answer(NsUser writer, Question question, String contents) {
        this(null, writer, question, contents);
    }

    public Answer(Long id, NsUser writer, Question question, String contents) {
        this.id = id;
        if (writer == null) {
            throw new UnAuthorizedException();
        }

        if (question == null) {
            throw new NotFoundException();
        }

        this.writer = writer;
        this.question = question;
        this.contents = contents;
    }

    public Long getId() {
        return id;
    }

    @Override
    public List<DeleteHistory> delete(NsUser loginUser) throws UnAuthenticationException {
        super.validateWriter(loginUser);
        super.changeDeleteStatus(YN.Y);

        return Collections.singletonList(DeleteHistory.of(ContentType.ANSWER, this.id, this.writer, LocalDateTime.now()));
    }

    public NsUser getWriter() {
        return writer;
    }

    public String getContents() {
        return contents;
    }

    public void toQuestion(Question question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "Answer [id=" + getId() + ", writer=" + writer + ", contents=" + contents + "]";
    }
}
