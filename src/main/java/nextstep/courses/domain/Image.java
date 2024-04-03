package nextstep.courses.domain;

public class Image {
    Integer size;
    ImageType type;
    Integer width;
    Integer height;

    public Image(Integer size, ImageType type, Integer width, Integer height) {
        validateSize(size);
        validateRatio(width, height);
        this.size = size;
        this.type = type;
        this.width = width;
        this.height = height;
    }

    private void validateSize(Integer size) {
        if(size > 1024 * 1024){
            throw new IllegalArgumentException("이미지 크기는 1MB 이하여야합니다.");
        }
    }

    private void validateRatio(Integer width, Integer height){
        if(width * 2 != height * 3){
            throw new IllegalArgumentException("가로, 세로 비율을 3:2여야 합니다.");
        }
    }
}
