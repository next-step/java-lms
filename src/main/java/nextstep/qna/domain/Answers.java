package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

public class Answers {

    private final List<Answer> answers = new ArrayList<>();

    private Answers() {

    }

    private Answers(List<Answer> answers) {
        this.answers.addAll(answers);
    }

    public static Answers from(List<Answer> answers) {
        return new Answers(answers);
    }

    public static Answers from() {
        return new Answers();
    }

    public List<DeleteHistory> deleteAll(NsUser nsUser) throws CannotDeleteException {
        if (!isDeletedAllowed(nsUser)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }

        return answers.stream()
                .map(Answer::delete)
                .collect(Collectors.toList());
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    private boolean isDeletedAllowed(NsUser nsUser) {
        int count = (int) answers.stream()
                .filter(answer -> !answer.isOwner(nsUser))
                .count();
        return count == 0;
    }
}
