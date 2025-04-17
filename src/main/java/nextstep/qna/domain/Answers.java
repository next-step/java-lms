package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Answers {
    private final List<Answer> answers = new ArrayList<>();

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    public List<DeleteHistory> deleteAll(NsUser user) {
        return answers.stream()
                .map(answer -> answer.delete(user))
                .collect(Collectors.toList());
    }
}
