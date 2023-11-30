package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Answers {
    private final List<Answer> answers;

    public static Answers init() {
        return new Answers(Collections.emptyList());
    }

    private Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public Answers add(Answer answer) {
        List<Answer> answers = new ArrayList<>(this.answers);
        answers.add(answer);
        return new Answers(answers);
    }

    public boolean checkAnswer(NsUser writer) {
        return this.answers.stream()
                .anyMatch(answer -> !answer.isOwner(writer));
    }

    public List<DeleteHistory> deleted(NsUser loginUser) {
        validateHasAnswerOfOthers(loginUser);
        return answers.stream()
                .map(Answer::deleted)
                .collect(Collectors.toUnmodifiableList());
    }

    private void validateHasAnswerOfOthers(NsUser loginUser) {
        if (hasAnswerOfThers(loginUser)) {
            throw new CannotDeleteException("다른 사용자가 작성한 답변이 있어 삭제할 수 없습니다.");
        }
    }

    private boolean hasAnswerOfThers(NsUser loginUser) {
        return answers.stream().anyMatch(answer -> !answer.isOwner(loginUser));
    }
}
