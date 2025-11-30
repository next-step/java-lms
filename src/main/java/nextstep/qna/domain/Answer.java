package nextstep.qna.domain;

import nextstep.qna.NotFoundException;
import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUser;

public class Answer extends BaseEntity {
    private AnswerPost post;
    private Question question;
    private boolean deleted;

    public Answer(Long id, AnswerPost post, Question question, boolean deleted) {
        super(id);
        validateQuestion(question);
        this.post = post;
        this.question = question;
        this.deleted = deleted;
    }

    public Answer(NsUser writer, Question question, String contents) {
        this(null, writer, question, contents);
    }

    public Answer(Long id, NsUser writer, Question question, String contents) {
        this(id, new AnswerPost(writer, contents), question, false);
        validateWriter(writer);
    }

    private static void validateWriter(NsUser writer) {
        if (writer == null) {
            throw new UnAuthorizedException();
        }
    }

    private static void validateQuestion(Question question) {
        if (question == null) {
            throw new NotFoundException();
        }
    }

    public boolean isDeleted() {
        return deleted;
    }

    public boolean isOwner(NsUser user) {
        return post.isOwner(user);
    }

    public NsUser getWriter() {
        return post.getWriter();
    }

    public void delete() {
        this.deleted = true;
    }

    public DeleteHistory createDeleteHistory() {
        return new DeleteHistory(ContentType.ANSWER, getId(), getWriter());
    }

    @Override
    public String toString() {
        return "Answer [id=" + getId() + ", writer=" + getWriter() + ", contents=" + post.getContents() + "]";
    }
}
