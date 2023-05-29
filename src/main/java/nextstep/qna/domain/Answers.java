package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Answers {
    private List<Answer> answerList;

    public Answers() {
        this.answerList = new ArrayList<>();
    }

    public void add(Answer answer) {
        answerList.add(answer);
    }

    public List<DeleteHistory> delete() {
        return answerList.stream()
                .map(answer -> answer.delete())
                .collect(Collectors.toList());
    }

    public boolean isOwnerAll(NsUser loginUser) {
        return answerList.stream()
                .allMatch(answer -> answer.isOwner(loginUser));
    }
}
