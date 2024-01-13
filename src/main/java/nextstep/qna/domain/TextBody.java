package nextstep.qna.domain;

import java.util.Objects;

import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUser;

public class TextBody {
	private NsUser writer;

	private String contents;

	private boolean deleted = false;

	public TextBody(NsUser writer, String contents, boolean deleted) {
		this.writer = writer;
		this.contents = contents;
		this.deleted = deleted;
	}

	public static TextBody of(TextBody textBody) {
		return new TextBody(textBody.writer, textBody.contents, true);
	}

	public void isNull() {
		if(writer == null) {
			throw new UnAuthorizedException();
		}
	}

	public NsUser getWriter() {
		return writer;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void deleted() {
		this.deleted = true;
	}

	public boolean isOwner(NsUser writer) {
		return this.writer.equals(writer);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		TextBody textBody = (TextBody)o;
		return deleted == textBody.deleted && Objects.equals(writer, textBody.writer) && Objects.equals(
			contents, textBody.contents);
	}

	@Override
	public int hashCode() {
		return Objects.hash(writer, contents, deleted);
	}

	@Override
	public String toString() {
		return "TextBody{" +
			"writer=" + writer +
			", contents='" + contents + '\'' +
			", deleted=" + deleted +
			'}';
	}
}
