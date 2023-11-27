package nextstep.qna.domain;

import nextstep.qna.error.CannotDeleteException;
import nextstep.qna.error.NotFoundException;
import nextstep.qna.error.UnAuthorizedException;
import nextstep.users.domain.NsUser;

public class Answer {

    private Long id;

    private NsUser writer;

    private Question question;

    private String contents;

    private boolean deleted;

    public Answer(NsUser writer,
                  Question question,
                  String contents) {
        this(null, writer, question, contents);
    }

    public Answer(Long id,
                  NsUser writer,
                  Question question,
                  String contents) {
        this.id = id;
        this.writer = writer;
        this.contents = contents;

        if(writer == null) {
            throw new UnAuthorizedException();
        }

        if(question == null) {
            throw new NotFoundException();
        }
    }

    public Long getId() {
        return id;
    }

    public void deleted() {
        this.deleted = true;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void checkOwner(NsUser nsUser) throws CannotDeleteException {
        if (!writer.equals(nsUser)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
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
        return "Answer [id=" + getId() +
               ", writer=" + getWriter() +
               ", contents=" + getContents() +
               "]";
    }
}
