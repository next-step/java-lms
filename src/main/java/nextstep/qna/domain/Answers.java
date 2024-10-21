package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.List;

public class Answers {
    private List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void checkDeletePermission(NsUser loginUser) throws CannotDeleteException {
        boolean isNoDeletePermission = answers.stream()
                .anyMatch(answer -> answer.isNotOwner(loginUser));

        if(isNoDeletePermission){
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public void markAllDeleted() {
        answers.forEach(Answer::markDeleted);
    }

    public List<Answer> getAnswers() {
        return answers;
    }
}
