package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import nextstep.users.domain.NsUser;

public class Answers {
	private final List<Answer> answers;

	public Answers() {
		this.answers = new ArrayList<>();
	}

	public List<DeleteHistory> deleteAnswers(NsUser loginUser) {
		return answers.stream()
			.map(a -> a.deleteAnswer(loginUser))
			.collect(Collectors.toList());
	}

	public void add(Answer answer) {
		this.answers.add(answer);
	}
}
