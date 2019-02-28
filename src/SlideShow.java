import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SlideShow {


    public SlideShow() throws FileNotFoundException {
        List<Image> L_reversed= loadInput();
        List<Image> H_descending = sortH(L_reversed);
        List<Image> V_descending = sortV(L_reversed);
        L_reversed.sort(Comparator.comparing(Image::getNumOfTags).reversed());


    }

    private static List<Image> sortV(List<Image> L)
    {
        List<Image> result = new ArrayList<>();
        for(int i = 0; i < L.size(); i++)
        {
            if(L.get(i).isVertical())
            {
                result.add(L.get(i));
            }
        }
        return result;
    }
    private static List<Image> sortH(List<Image> L)
    {
        List<Image> result = new ArrayList<>();
        for(int i = 0; i < L.size(); i++)
        {
            if(!L.get(i).isVertical())
            {
                result.add(L.get(i));
            }
        }
        return result;
    }




    private static List<Image> loadInput() throws FileNotFoundException {
        Scanner reader = new Scanner(new File("a_example.txt"));
        int numOfInputs = reader.nextInt();
        List<Image> images = new ArrayList<>();
        boolean isVertical;
        for (int i = 0; i < numOfInputs; i++) {

            reader.nextLine();
            if (reader.next() == String.valueOf('V')) {
                isVertical = true;
            } else {
                isVertical = false;
            }
            int numOfTags = reader.nextInt();
            String[] tags_i = new String[numOfTags];
            for (int k = 0; k < numOfTags; k++) {
                tags_i[i] = reader.next();
            }
            Image tmp = new Image(isVertical, tags_i, numOfTags, i);
            images.add(tmp);
        }

        return images;

}



