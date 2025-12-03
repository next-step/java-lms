package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.NotFoundException;
import nextstep.users.domain.NsUser;

public class Answer extends SoftDeletable {
    private final BaseEntity contents;
    private final Question question;
    private TimeStamp timeStamp;


    public Answer(NsUser writer, Question question, String contents) {
        this(null, writer, question, contents);
    }

    public Answer(Long id, NsUser writer, Question question, String contents) {
        this(question, new BaseEntity(id, writer, contents), new TimeStamp());
    }

    public Answer(Question question, BaseEntity contents, TimeStamp timeStamp) {
        if(question == null) {
            throw new NotFoundException();
        }
        this.question = question;
        this.contents = contents;
        this.timeStamp = timeStamp;
    }

    public boolean isOwner(NsUser writer) {
        return contents.isWrittenBy(writer);
    }

    @Override
    public void deleteBy(NsUser requestUser) throws CannotDeleteException {
        if (!isOwner(requestUser)) {
            throw new CannotDeleteException("답변자 외에는 답변을 삭제할 수 없습니다.");
        }
        delete();
    }

    public DeleteHistory toDeleteHistory() throws CannotDeleteException {
        if (!isDeleted()) {
            throw new CannotDeleteException("삭제되지 않아서 삭제 히스토리를 구할 수 없습니다.");
        }
        return contents.toDeleteHistory(ContentType.ANSWER);
    }

    @Override
    public String toString() {
        return "Answer [" + contents.toString() + "]";
    }
}
