package nextstep.session.domain;

public class ImageReSolution {

	private static final int WIDTH_RATIO = 3;
	private static final int HEIGHT_RATIO = 2;
	private int width;
	private int height;

	public ImageReSolution(int width, int height) {
		validateImageDimensions(width, height);
		this.width = width;
		this.height = height;
	}

	private void validateImageDimensions(int width, int height) {
		if (width <= 0 || height <= 0) {
			throw new IllegalArgumentException("가로와 세로 길이는 양의 정수여야 합니다.");
		}

		if (width < 300 || height < 200) {
			throw new IllegalArgumentException("가로는 300 이상, 세로는 200 이상이어야 합니다.");
		}

		if (isValidRatio(width) != height) {
			throw new IllegalArgumentException("가로세로비율이 3:2가 아닙니다.");
		}
	}

	private int isValidRatio(int width) {
		return width / WIDTH_RATIO * HEIGHT_RATIO;
	}

}
