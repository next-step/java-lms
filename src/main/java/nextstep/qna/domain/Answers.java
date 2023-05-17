package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import nextstep.users.domain.NsUser;

public class Answers {

	private final List<Answer> answers = new ArrayList<>();

	public Answers() {
	}

	public void add(Answer answer) {
		this.answers.add(answer);
	}

	public List<Answer> getAnswers() {
		return Collections.unmodifiableList(this.answers);
	}

	public boolean isOwner(NsUser loginUser) {
		return this.answers.stream().allMatch(answer -> answer.isOwner(loginUser));
	}
}
