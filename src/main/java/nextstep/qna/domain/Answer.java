package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

public class Answer {

    private final Long id;

    private final AnswerInformation information;

    public Answer(Long id, AnswerInformation information) {
        this.id = id;
        this.information = information;
    }

    public Answer delete(NsUser loginUser) {
        information.validateDeleted();
        information.validateWriter(loginUser);
        AnswerInformation updatedInformation = information.delete();
        return new Answer(this.id, updatedInformation);
    }

    public DeletedHistory build() {
        return new DeletedHistory(ContentType.ANSWER, this.id, information.getWriter());
    }

    public boolean isDeleted() {
        return this.information.isDeleted();
    }

    public Long getId() {
        return this.id;
    }

    public NsUser getDeletedBy() {
        return this.information.getWriter();
    }

    public NsUser getWriter() {
        return this.information.getWriter();
    }
}
