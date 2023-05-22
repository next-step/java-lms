package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Answers {

    private List<Answer> answers = new ArrayList<>();

    public List<Answer> getAnswers() {
        return Collections.unmodifiableList(answers);
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public boolean isAllOwnerAnswer(NsUser nsUser) {
        return answers.stream()
                .allMatch(answer -> answer.isOwner(nsUser));
    }

    public void deleteAnswer(NsUser nsUser) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.deleteAnswer(nsUser);
        }
    }
}
