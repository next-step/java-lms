package nextstep.sessions.domain;

public class SessionImage {

    // 이미지 최대 크기
    private static final int MAX_SIZE = 1_000_000;

    // 이미지 크기
    private int size;

    // 이미지 가로 사이즈
    private double width;

    // 이미지 세로 사이즈
    private double height;

    // 이미지 타입
    private ImageType type;

    public SessionImage(int size, double width, double height, String type) {
        if (size <= 0 || size > MAX_SIZE) {
            throw new IllegalArgumentException("이미지 크기는 1MB 이하여야 합니다.");
        }
        if (width < 300 || height < 200) {
            throw new IllegalArgumentException("이미지는 가로 300px, 세로 200px 이상이여야 합니다.");
        }
        if (width / height != 1.5) {
            throw new IllegalArgumentException("이미지의 비율은 3:2(가로:세로)여야 한다.");
        }
        this.size = size;
        this.width = width;
        this.height = height;
        this.type = ImageType.from(type);
    }
}
