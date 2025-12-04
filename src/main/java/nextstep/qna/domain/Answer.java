package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.NotFoundException;
import nextstep.users.domain.NsUser;

public class Answer {
    private final SoftDeletableBaseEntity baseEntity;
    private final Question question;
    private final ContentDetails contentDetails;


    public Answer(NsUser writer, Question question, String entity) {
        this(null, writer, question, entity);
    }

    Answer(Long id, NsUser writer, Question question, String content) {
        this(new SoftDeletableBaseEntity(id), question, new ContentDetails(writer, content));
    }

    Answer(SoftDeletableBaseEntity baseEntity, Question question, ContentDetails contentDetails) {
        if(question == null) {
            throw new NotFoundException();
        }
        this.baseEntity = baseEntity;
        this.question = question;
        this.contentDetails = contentDetails;
    }

    public void deleteBy(NsUser requestUser) throws CannotDeleteException {
        if (!isOwner(requestUser)) {
            throw new CannotDeleteException("답변자 외에는 답변을 삭제할 수 없습니다.");
        }
        baseEntity.delete();
    }

    private boolean isOwner(NsUser writer) {
        return contentDetails.isWrittenBy(writer);
    }

    public boolean isDeleted() {
        return baseEntity.isDeleted();
    }

    public DeleteHistory toDeleteHistory() throws CannotDeleteException {
        if (!baseEntity.isDeleted()) {
            throw new CannotDeleteException("삭제되지 않아서 삭제 히스토리를 구할 수 없습니다.");
        }
        return new DeleteHistory(ContentType.ANSWER, baseEntity.getId(), contentDetails.getWriter());
    }

    @Override
    public String toString() {
        return "Answer [" + super.toString() + "]";
    }
}
