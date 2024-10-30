package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Answers {
    private List<Answer> answers = new ArrayList<>();

    public Answers() {}

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<Answer> getValue() {
        return new ArrayList<>(answers);
    }

    public List<DeleteHistory> delete(NsUser user) {
        return answers.stream()
                .map(answer -> {
                    try {
                        return answer.delete(user);
                    } catch (CannotDeleteException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
    }

    public void add(Answer answer) {
        this.answers.add(answer);
    }
}
