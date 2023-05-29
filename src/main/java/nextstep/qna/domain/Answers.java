package nextstep.qna.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Answers {

    private List<Answer> answers = new ArrayList<>();

    public void add(final Answer answer) {
        answers.add(answer);
    }

    public List<DeleteHistory> delete(NsUser loginUser) {
        return answers.stream()
                .map(answer -> answer.delete(loginUser))
                .collect(Collectors.toList());
    }

}
