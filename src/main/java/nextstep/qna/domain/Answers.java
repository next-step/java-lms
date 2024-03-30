package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Answers {
    private List<Answer> answers = new ArrayList<>();

    List<DeleteHistory> delete(NsUser user) {
        return answers.stream()
                .map(it -> it.delete(user))
                .collect(Collectors.toList());
    }

    public void add(Answer answer) {
        answers.add(answer);
    }
}
