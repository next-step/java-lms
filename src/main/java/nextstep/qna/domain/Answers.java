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

    // 저장되어있는 답변들 중 작성자가 일치하는지 확인한다.
    public void confirmIsOwnerAnswer(NsUser loginUser) {
        this.answers.forEach(answer -> answer.confirmIsOwner(loginUser));
    }

    // 답변 삭제 후 삭제이력을 반환한다.
    public List<DeleteHistory> delete(NsUser loginUser) {
        return this.answers.stream()
                .map(answer -> answer.delete(loginUser))
                .collect(Collectors.toList());
    }
}
