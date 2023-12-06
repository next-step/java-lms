package nextstep.qna.domain;

import nextstep.qna.NotFoundException;
import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUser;

public class Answer {
    private Long id;

    private AnswerContent answerContent;

    private Question question;

    private boolean deleted = false;

    private BaseTime baseTime;

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
        this.answerContent = new AnswerContent(writer, contents);
        this.question = question;
        this.baseTime = new BaseTime();
    }

    public Long getId() {
        return id;
    }

    public NsUser getWriter() {
        return this.answerContent.writer();
    }

    public boolean isDeleted() {
        return deleted;
    }

    public boolean isOwner(NsUser writer) {
        return this.answerContent.equalsWriter(writer);
    }

    public void toQuestion(Question question) {
        this.question = question;
    }

    public void delete() {
        this.deleted = true;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", answerContent=" + answerContent +
                ", question=" + question +
                ", deleted=" + deleted +
                ", baseTime=" + baseTime +
                '}';
    }
}
