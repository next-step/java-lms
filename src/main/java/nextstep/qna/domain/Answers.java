package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Answers {
    private final List<Answer> answers = new ArrayList<>();

    public Answers(List<Answer> answers) {
        this.answers.addAll(answers);
    }

    public List<DeleteHistory> delete(NsUser loginUser) {
        return answers.stream()
                .map(answer -> tryDelete(loginUser, answer))
                .collect(Collectors.toList());
    }

    private DeleteHistory tryDelete(NsUser loginUser, Answer answer) {
        try {
            return answer.delete(loginUser);
        } catch (CannotDeleteException e) {
            throw new RuntimeException(e);
        }
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public List<Answer> getAnswers() {
        return answers;
    }
}
