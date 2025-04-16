package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Answers {
    private final List<Answer> answers = new ArrayList<>();

    public void add(Answer answer) {
        if (answers.contains(answer))
            throw new IllegalArgumentException("답변이 이미 등록되어있습니다.");

        answers.add(answer);
    }

    public List<Answer> getAnswers() {
        return Collections.unmodifiableList(answers);
    }

    public List<DeleteHistory> deleteAll() {
        return answers.stream()
                .map(Answer::delete)
                .collect(Collectors.toList());
    }

    public boolean isOwner(NsUser loginUser) {
        return answers.stream()
                .allMatch(answer -> answer.isOwner(loginUser));
    }

    public boolean isNotOwner(NsUser loginUser) {
        return !isOwner(loginUser);
    }
}
