package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Answers {

    private final List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public Answers() {
        this(new ArrayList<>());
    }

    public static Answers create() {
        return new Answers();
    }

    public List<DeleteHistory> delete(NsUser loginUser) throws CannotDeleteException {
        isDeletable(loginUser);

        return answers.stream()
                .map(Answer::delete)
                .collect(Collectors.toList());
    }

    private void isDeletable(NsUser loginUser) throws CannotDeleteException {
        if (hasOtherAnswerUser(loginUser)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }

    private boolean hasOtherAnswerUser(NsUser loginUser) {
        return answers.stream()
                .anyMatch(answer -> !answer.isOwner(loginUser));
    }

    public Answers add(Answer answer) {
        answers.add(answer);
        return new Answers(answers);
    }
}
