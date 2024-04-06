package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Answers {

    private List<Answer> answers = new ArrayList<>();

    public Answers() {
    }

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<Answer> of() {
        return answers;
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public List<DeleteHistory> delete(NsUser loginUser) throws CannotDeleteException {
        if (answers.stream().anyMatch(answer -> !answer.isOwner(loginUser))) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }

        return answers.stream().map(Answer::delete).collect(toList());
    }
}
