package nextstep.courses.domain.session;

import nextstep.file.ImageFile;

public class SessionImage {
	ImageFile imageFile;

	public SessionImage(Long id, int size, int width, int height, String type) {
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

		this.imageFile = new ImageFile(id, size, width, height, type);
	}

	public Long getId() {
		return imageFile.getId();
	}
}
