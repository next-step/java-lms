package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import nextstep.users.domain.NsUser;

public class Answers {

	private final List<Answer> answers = new ArrayList<>();

	public List<DeleteHistory> delete(NsUser loginUser) {
		return answers.stream()
				.map(answer -> answer.deleteAnswer(loginUser))
				.collect(Collectors.toList());
	}

	public void add(Answer answer) {
		this.answers.add(answer);
	}
}
