package nextstep.courses.builder;

import nextstep.courses.domain.session.SessionImage;

public class SessionImageBuilder {
	private Long id = 0L;

	private int size = 1024;

	private int width = 300;

	private int height = 200;

	private String type = "jpg";

	public SessionImageBuilder size(int size) {
		this.size = size;
		return this;
	}

	public SessionImageBuilder width(int width) {
		this.width = width;
		return this;
	}

	public SessionImageBuilder height(int height) {
		this.height = height;
		return this;
	}

	public SessionImageBuilder type(String type) {
		this.type = type;
		return this;
	}

	public SessionImage build() {
		return new SessionImage(id, size, width, height, type);
	}
}
