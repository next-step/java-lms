package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Question {
    private final SoftDeletableBaseEntity baseEntity;
    private final QuestionBody content;
    private Answers answers = new Answers();

    public Question(NsUser writer, String title, String contents) {
        this(0L, writer, title, contents);
    }

    public Question(Long id, NsUser writer, String title, String contents) {
        this(new SoftDeletableBaseEntity(id), new QuestionBody(writer, title, contents));
    }

    Question(SoftDeletableBaseEntity baseEntity, QuestionBody content) {
        this.baseEntity = baseEntity;
        this.content = content;
    }

    public Long getId() {
        return baseEntity.getId();
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    private boolean isOwner(NsUser loginUser) {
        return content.isWrittenBy(loginUser);
    }

    public void deleteBy(NsUser requestUser) throws CannotDeleteException {
        if (!isOwner(requestUser)) {
            throw new CannotDeleteException("작성자 외에는 질문을 삭제할 수 없습니다.");
        }

        baseEntity.delete();
        answers.deleteBy(requestUser);
    }

    public boolean isDeleted() {
        return baseEntity.isDeleted();
    }

    public List<DeleteHistory> toDeleteHistories() throws CannotDeleteException {
        if (!baseEntity.isDeleted()) {
            return Collections.emptyList();
        }
        List<DeleteHistory> deleteHistories = new ArrayList<>();

        deleteHistories.add(this.toDeleteHistory());
        deleteHistories.addAll(answers.toDeleteHistories());
        return deleteHistories;
    }

    private DeleteHistory toDeleteHistory() {
        return new DeleteHistory(ContentType.QUESTION, baseEntity.getId(), content.getWriter());
    }

    @Override
    public String toString() {
        return "Question [" + content.toString() + "]";
    }
}
