package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

public class QuestionBody {
    private final String title;
    private final BaseEntity baseEntity;

    public QuestionBody(Long id, NsUser writer, String title, String contents) {
        this(title, new BaseEntity(id, writer, contents));
    }

    public QuestionBody(String title, BaseEntity baseEntity) {
        this.title = title;
        this.baseEntity = baseEntity;
    }

    public Long getId() {
        return baseEntity.getId();
    }

    public boolean isWrittenBy(NsUser writer) {
        return baseEntity.isWrittenBy(writer);
    }

    public DeleteHistory toDeleteHistory() {
        return baseEntity.toDeleteHistory(ContentType.QUESTION);
    }

    @Override
    public String toString() {
        return  "title='" + title + '\'' + baseEntity.toString() + '\'';
    }
}
