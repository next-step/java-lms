package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Answers {

    private List<Answer> answers = new ArrayList<>();

    public DeleteHistories deleteAnswers(NsUser loginUser) {
        final var deleteHistories = answers.stream()
                .map(answer -> answer.deleteAnswer(loginUser))
                .collect(Collectors.toList());

        return new DeleteHistories(deleteHistories);
    }

    public void add(Answer answer) {
        this.answers.add(answer);
    }
}
