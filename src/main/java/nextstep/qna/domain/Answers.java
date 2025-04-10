package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Answers {
    private List<Answer> answers;

    public Answers() {
        this(new ArrayList<>());
    }

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public boolean validateDeletableBy(NsUser user) throws CannotDeleteException {
        if (!isAllAnswersOwnedBy(user)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }

        return true;
    }
    private boolean isAllAnswersOwnedBy(NsUser user) {
        return answers.stream()
                .allMatch(answer -> answer.isOwner(user));
    }

    public List<DeleteHistory> delete(NsUser user) throws CannotDeleteException {
        validateDeletableBy(user);

        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : answers) {
            deleteHistories.add(answer.delete(user));
        }
        return deleteHistories;
    }
}
