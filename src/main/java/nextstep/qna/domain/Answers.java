package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Answers {
    private List<Answer> answers = new ArrayList<>();

    public DeleteHistorys delete(NsUser user) throws CannotDeleteException {
        DeleteHistorys historys = new DeleteHistorys();

        hasOtherUserAnswer(user);
        historys.addAll(answers.stream()
                .map(Answer::delete)
                .collect(Collectors.toList()));

        return historys;
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    private void hasOtherUserAnswer(NsUser loginUser) throws CannotDeleteException {
        for (Answer answer : answers) {
            if (!answer.isOwner(loginUser)) {
                throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
            }
        }
    }
}
