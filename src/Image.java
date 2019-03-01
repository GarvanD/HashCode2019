import java.util.List;

public class Image {
    private boolean isVertical;
     List<String> Tags;
    final int numOfTags, index;

    public Image(boolean V, List<String> T, int Tn, int index) {
        isVertical = V;
        Tags = T;
        numOfTags = Tn;
        this.index = index;
    }

    public int getNumOfTags(){return numOfTags;}
    public boolean isVertical(){return isVertical;}


}
