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

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    public List<DeleteHistory> delete(NsUser loginUser) throws CannotDeleteException {
        if (!canDeleteAnswer(loginUser)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
        return answers.stream()
                .map(answer -> answer.delete())
                .collect(Collectors.toList());
    }

    private boolean canDeleteAnswer(NsUser loginUser) {
        return answers.stream().filter(answer -> !answer.isOwner(loginUser))
                .findAny()
                .map(i -> false)
                .orElse(true);
    }
}
