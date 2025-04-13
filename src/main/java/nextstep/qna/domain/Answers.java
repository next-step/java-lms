package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Answers {
    private final List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = new ArrayList<>(answers);
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public List<DeleteHistory> deleteAll(NsUser loginUser) {
        if(!canAllDelete(loginUser)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
        return answers.stream().map(Answer::delete).collect(Collectors.toList());
    }

    private boolean canAllDelete(NsUser loginUser) {
        return answers.stream().filter(answer -> answer.isOwner(loginUser)).count()
                == answers.size();

    }
}
