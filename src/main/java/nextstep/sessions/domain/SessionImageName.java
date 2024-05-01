package nextstep.sessions.domain;

public class SessionImageName {
    private final String imageName;

    public SessionImageName(String imageName) {
        validateImageName(imageName);
        this.imageName = imageName;
    }

    private void validateImageName(String imageName) {
        String lowerImageName = imageName.toLowerCase();
        int position = lowerImageName.lastIndexOf(".");
        String extension = lowerImageName.substring(position + 1);

        ImageType.checkImageType(extension);
    }
}
