package nextstep.courses.domain;

public class SessionEssentialInfo {


    private int generation;

    private String coverImage;

    private int headCount;

    public SessionEssentialInfo(int generation, String coverImage, int headCount) {
        this.generation = generation;
        this.coverImage = coverImage;
        this.headCount = headCount;
    }

    public int getGeneration() {
        return generation;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public int getHeadCount() {
        return headCount;
    }

    @Override
    public String toString() {
        return "SessionEssentialInfo{" +
                "generation=" + generation +
                ", coverImage='" + coverImage + '\'' +
                ", headCount=" + headCount +
                '}';
    }

}
