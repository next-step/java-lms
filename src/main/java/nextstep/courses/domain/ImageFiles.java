package nextstep.courses.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ImageFiles {
    private final List<ImageFile> imageFiles;

    public ImageFiles() {
        this(new ArrayList<>());
    }

    public ImageFiles(ImageFile imageFile) {
        this(Arrays.asList(imageFile));
    }

    public ImageFiles(List<ImageFile> imageFiles) {
        this.imageFiles = new ArrayList<>(imageFiles);
    }

    public void addImage(ImageFile imageFile) {
        this.imageFiles.add(imageFile);
    }

    public List<ImageFile> getImageFiles() {
        return this.imageFiles;
    }

    public Long getMainImageId() {
        return imageFiles.get(0).getImageId();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ImageFiles that = (ImageFiles) o;
        return Objects.equals(imageFiles, that.imageFiles);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(imageFiles);
    }

    @Override
    public String toString() {
        return "ImageFiles{" +
                "imageFiles=" + imageFiles +
                '}';
    }
}
