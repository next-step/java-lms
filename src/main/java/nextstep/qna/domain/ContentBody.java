package nextstep.qna.domain;

import java.util.Objects;

public class ContentBody {

	private ContentType contentType;

	private Long contentId;

	public ContentBody(ContentType contentType, Long contentId) {
		this.contentType = contentType;
		this.contentId = contentId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ContentBody that = (ContentBody)o;
		return contentType == that.contentType && Objects.equals(contentId, that.contentId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(contentType, contentId);
	}
}
