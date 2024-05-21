package nextstep.courses.domain.session;

import nextstep.file.domain.ImageFile;

public class SessionImage {
	private final ImageFile imageFile;

	public SessionImage(Long id) {
		this(new ImageFile(id));
	}


	public SessionImage(Long id, int size, int width, int height, String type) {
		this(new ImageFile(id, size, width, height, type));
	}

	public SessionImage(ImageFile imageFile) {
		valid(imageFile.getSize(), imageFile.getWidth(), imageFile.getHeight(), imageFile.getTypeString());
		this.imageFile = imageFile;
	}

	public Long getId() {
		return imageFile.getId();
	}

	private void valid(int size, int width, int height, String type) {
		if(size > 1024) {
			throw new IllegalArgumentException("이미지 크기는 1MB 이하여야 합니다.");
		}

		if(width < 300) {
			throw new IllegalArgumentException("이미지 width는 300픽셀 이상이여야 합니다.");
		}

		if(height < 200) {
			throw new IllegalArgumentException("이미지 height는 200픽셀 이상이여야 합니다.");
		}

		if((double) width / height != 1.5) {
			throw new IllegalArgumentException("이미지 비율을 3:2 이어야 합니다.");
		}
	}
}
