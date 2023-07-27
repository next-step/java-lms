package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Answers {
    private final List<Answer> answers;

    public Answers() {
        this.answers = new ArrayList<>();
    }

    public Answers(List<Answer> answers) {
        if (answers == null) {
            this.answers = new ArrayList<>();
            return;
        }

        this.answers = answers;
    }

    public List<DeleteHistory> delete(NsUser user) {
        return answers.stream().map(answer -> {
            try {
                return answer.delete(user);
            } catch (CannotDeleteException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }
}
