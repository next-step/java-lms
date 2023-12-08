package nextstep.courses.domain;

import nextstep.courses.type.*;

public class SessionImage {
    private static final Capacity MAX_CAPACITY = new Capacity(1024, CapacityUnit.KB);
    private static final Rectangle MIN_SIZE = new Rectangle(300, 200);
    private static final int WIDTH_RATIO = 3;
    private static final int HEIGHT_RATIO = 2;

    /**
     * 이미지 용량
     */
    private Capacity capacity;

    /**
     * 이미지 가로 세로 크기
     */
    private Rectangle size;


    private ImageExtension type;

    public SessionImage(Capacity capacity, Rectangle size, ImageExtension type) {
        validateCapacity(capacity);
        validateSize(size);

        this.capacity = capacity;
        this.size = size;
        this.type = type;
    }

    public SessionImage(int capacity, int width, int height, ImageExtension type) {
        this(
                new Capacity(capacity, CapacityUnit.KB),
                new Rectangle(width, height),
                type
        );
    }

    private static void validateCapacity(Capacity capacity) {
        capacity.throwIfCapacityIsLagerThan(MAX_CAPACITY);
    }

    private static void validateSize(Rectangle size) {
        size.throwIfSizeIsSmallerThan(MIN_SIZE);
        size.throwIfRatioIsNotTheSameWith(WIDTH_RATIO, HEIGHT_RATIO);
    }

}
