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

    public void confirmIsOwnerAnswer(NsUser loginUser) {
        this.answers.forEach(answer -> answer.confirmIsOwner(loginUser));
    }

    public List<DeleteHistory> delete(NsUser loginUser) {
        return this.answers.stream()
                .map(answer -> answer.delete(loginUser))
                .collect(Collectors.toList());
    }
}
