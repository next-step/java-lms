package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Answers {

    private final List<Answer> answers;

    public Answers() {
        this(new ArrayList<>());
    }

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<DeleteHistory> deleteBy(NsUser user) {
        if (!isDeletableBy(user)) {
            throw new CannotDeleteException("현재 로그인 계정과 다른 답변 작성자가 있습니다.");
        }
        return this.answers.stream().map(answer -> answer.deleteBy(user)).collect(Collectors.toList());
    }

    private boolean isDeletableBy(NsUser user) {
        return this.answers.stream().allMatch(answer -> answer.isDeletableBy(user));
    }

    public void add(Answer answer) {
        this.answers.add(answer);
    }
}
