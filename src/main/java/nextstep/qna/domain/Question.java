package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Question extends BaseEntity {
    private PostContent postContent;

    private Answers answers = new Answers();

    public Question() {
    }

    public Question(NsUser writer, String title, String contents) {
        this(0L, new PostContent(writer, title, contents));
    }

    public Question(Long id, NsUser writer, String title, String contents) {
        this(id, new PostContent(writer, title, contents));
    }
    public Question(Long id, PostContent postContent) {
        super(id);
        this.postContent = postContent;
    }

    public NsUser getWriter() {
        return postContent.getWriter();
    }

    public void addAnswer(Answer answer) {
        answer.toQuestion(this);
        answers.add(answer);
    }

    public boolean isOwner(NsUser loginUser) {
        return postContent.isOwner(loginUser);
    }

    public void markAsDeleted(NsUser loginUser) throws CannotDeleteException {
        validateDeletableBy(loginUser);
        markAsDeleted();
    }

    public void delete(NsUser loginUser) throws CannotDeleteException {
        this.markAsDeleted(loginUser);
        answers.delete(loginUser);
    }

    public List<DeleteHistory> createDeleteHistories() {
        List<DeleteHistory> histories = new ArrayList<>();

        histories.add(new DeleteHistory(
                ContentType.QUESTION,
                getId(),
                getWriter(),
                LocalDateTime.now()
        ));

        histories.addAll(answers.createDeleteHistories());

        return histories;
    }

    public void validateDeletableBy(NsUser loginUser) throws CannotDeleteException {
        if (!isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
    }

    @Override
    public String toString() {
        return "Question{" +
                "postContent=" + postContent +
                ", answers=" + answers +
                '}';
    }
}
