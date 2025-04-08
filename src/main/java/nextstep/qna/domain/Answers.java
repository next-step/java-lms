package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Answers {
    private final List<Answer> value;

    public Answers() {
        this(new ArrayList<>());
    }

    public Answers(List<Answer> answers) {
        this.value = answers;
    }

    public void add(Answer answer) {
        this.value.add(answer);
    }

    public boolean areAllOwnedBy(NsUser writer) {
        return value.stream().allMatch(answer -> answer.isOwner(writer));
    }

    public List<DeleteHistory> deleteAll() {
        return value.stream().map(Answer::delete).collect(Collectors.toList());
    }
}
