package nextstep.qna.domain;

import nextstep.qna.NotFoundException;
import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUser;
import org.w3c.dom.html.HTMLModElement;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Answer {

    private AnswerDetails details;

    private Question question;

    private boolean deleted = false;

    private final ContentsDateTime contentsDateTime = new ContentsDateTime();

    public Answer() {
    }

    public Answer(NsUser writer, Question question, String contents) {
        this(null, writer, question, contents);
    }

    public Answer(Long id, NsUser writer, Question question, String contents) {
        if(question == null) {
            throw new NotFoundException();
        }

        this.details = new AnswerDetails(id, writer, contents);
        this.question = question;
    }

    public Long getId() {
        return details.getId();
    }

    public boolean isDeleted() {
        return deleted;
    }

    public boolean isOwner(NsUser writer) {
        return this.details.isOwner(writer);
    }

    public NsUser getWriter() {
        return details.getWriter();
    }

    public String getContents() {
        return details.getContents();
    }

    public void toQuestion(Question question) {
        this.question = question;
    }

    public DeleteHistory delete() {
        this.deleted = true;
        return new DeleteHistory(ContentType.ANSWER, details.getId(), details.getWriter(), LocalDateTime.now());
    }

    @Override
    public String toString() {
        return "Answer [id=" + getId() + ", writer=" + details.getWriter() + ", contents=" + details.getContents() + "]";
    }
}
