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

    public List<DeleteHistory> delete(NsUser loginUser) throws CannotDeleteException {
        validateDelete(loginUser);
        return answers.stream().map(answer -> answer.delete()).collect(Collectors.toList());
    }

    public void validateDelete(NsUser loginUser) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.validateDelete(loginUser);
        }
    }
}
