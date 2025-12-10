package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.NotFoundException;
import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUser;

public class Answer extends BaseEntity {
    private Question question;

    private PostContent postContent;

    public Answer() {
    }

    public Answer(NsUser writer, Question question, String contents) {
        this(null, writer, question, contents);
    }

    public Answer(Long id, NsUser writer, Question question, String contents) {
        this(id, question, new PostContent(writer,contents));
    }

    public Answer(Long id, Question question, PostContent postContent) {
        super(id);
        if(postContent.isWriter()) {
            throw new UnAuthorizedException();
        }

        if(question == null) {
            throw new NotFoundException();
        }

        this.question = question;
        this.postContent = postContent;
    }

    public void markAsDeleted(NsUser loginUser) throws CannotDeleteException {
        validateDeletableBy(loginUser);
        delete();
    }

    public boolean isOwner(NsUser writer) {
        return this.postContent.isOwner(writer);
    }

    public NsUser getWriter() {
        return this.postContent.getWriter();
    }

    public void toQuestion(Question question) {
        this.question = question;
    }

    public void validateDeletableBy(NsUser loginUser) throws CannotDeleteException {
        if (!isOwner(loginUser)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }

    @Override
    public String toString() {
        return "Answer{" +
                "question=" + question +
                ", postContent=" + postContent +
                '}';
    }
}
