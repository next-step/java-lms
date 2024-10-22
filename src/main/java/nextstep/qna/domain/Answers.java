package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.List;
import java.util.stream.Collectors;

public class Answers {
    private List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public List<DeleteHistory> delete(NsUser loginUser) throws CannotDeleteException {
        return answers.stream()
                .map(answer -> {
                    return tryDelete(answer, loginUser);
                })
                .collect(Collectors.toList());
    }

    private DeleteHistory tryDelete(Answer answer, NsUser loginUser) {
        try {
            return answer.delete(loginUser);
        } catch (CannotDeleteException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Answer> getAnswers() {
        return answers;
    }
}
