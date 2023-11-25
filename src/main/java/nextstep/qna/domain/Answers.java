package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class Answers {

    private final List<Answer> list;

    public Answers(List<Answer> answerList) {
        this.list = answerList;
    }

    public List<DeleteHistory> delete(NsUser nsUser, LocalDateTime now) {
        return list.stream()
                .map(answer -> answer.delete(nsUser,now))
                .collect(Collectors.toList());
    }
}
