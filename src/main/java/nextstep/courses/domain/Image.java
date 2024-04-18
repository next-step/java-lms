package nextstep.courses.domain;

public class Image extends BaseEntity{
    Integer size;
    ImageType type;
    Dimension dimension;

    public Image(Integer size, ImageType type, Integer width, Integer height) {
        this(size, type, new Dimension(width, height));
    }

    public Image(Long id, Integer size, ImageType type, Dimension dimension) {
        super(id);
        validateSize(size);
        this.dimension = dimension;
        this.size = size;
        this.type = type;
    }

    public Image(Integer size, ImageType type, Dimension dimension) {
        super(1L);
        validateSize(size);
        this.dimension = dimension;
        this.size = size;
        this.type = type;
    }

    private void validateSize(Integer size) {
        if(isMaxOverSize(size)){
            throw new IllegalArgumentException("이미지 크기는 1MB 이하여야합니다.");
        }
    }

    private boolean isMaxOverSize(Integer size){
        int MAX_SIZE = 1024 * 1024;
        return size > MAX_SIZE;
    }

    public int getSize() {
        return this.size;
    }

    public String getType() {
        return this.type.name();
    }

    public int getWidth() {
        return this.dimension.width;
    }

    public int getHeight() {
        return this.dimension.height;
    }

    public static class Dimension{

        private Integer width;
        private Integer height;

        public Dimension(Integer width, Integer height) {
            validateRatio(width, height);
            this.width = width;
            this.height = height;
        }

        private void validateRatio(Integer width, Integer height){
            if(width * 2 != height * 3){
                throw new IllegalArgumentException("가로, 세로 비율을 3:2여야 합니다.");
            }
        }
    }
}
