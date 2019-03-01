import java.io.*;
import java.lang.*;
import java.util.*;

public class SlideShow {
    private static int numberOfSlides;
    List<Image> L_reversed;
    List<Image> H_descending;
    List<Image> V_descending;
    List<Slide> slides = new ArrayList<>();
    public static void main(String args[]) throws IOException {
        SlideShow S = new SlideShow();
        S.numberOfSlides = S.H_descending.size();
        for(int i = 0; i < numberOfSlides; i++){
            S.slides.add(new Slide(S.H_descending.get(i), false));
        }
        outputSlideShow(S.slides);
    }


    public SlideShow() throws FileNotFoundException {

        L_reversed = loadInput();
        H_descending = sortH(L_reversed);
        V_descending = sortV(L_reversed);
        L_reversed.sort(Comparator.comparing(Image::getNumOfTags).reversed());


    }

    private static List<Image> sortV(List<Image> L) {
        List<Image> result = new ArrayList<>();
        for (int i = 0; i < L.size(); i++) {
            if (L.get(i).isVertical()) {
                result.add(L.get(i));
            }
        }
        return result;
    }

    private static List<Image> sortH(List<Image> L) {
        List<Image> result = new ArrayList<>();
        for (int i = 0; i < L.size(); i++) {
            if (!L.get(i).isVertical()) {
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
            List<String> tags_i = new ArrayList<>();
            for (int k = 0; k < numOfTags; k++) {
                tags_i.add(i,reader.next());
            }
            Image tmp = new Image(isVertical, tags_i, numOfTags, i);
            images.add(tmp);
        }

        return images;

    }

    public static List <Slide> mergeVertical (List <Image> verticalList ) {
    	List <Slide> slides  = new ArrayList <>();

   	Slide slide;

    	for (int i = 0; i<=verticalList.size(); i++) {
        	slide =  new Slide (verticalList.get(i), true);

        	slide.images[i]=verticalList.get(verticalList.size() - i);

        	int sz=slide.images[0].numOfTags;

        	if (slide.images[0].numOfTags<slide.images[1].numOfTags)
        		sz = slide.images[1].numOfTags; //greater

        		for (int l=0; l<=slide.images[0].numOfTags;l++) {
        			slide.Tags.add(slide.images[0].Tags.get(l));
        		}

        		for (int l=0; l<=slide.images[1].numOfTags;l++) {

        		if (!slide.Tags.contains(slide.images[1].Tags.get(l)))
        			slide.Tags.add(slide.images[0].Tags.get(l));
        		}

        	slides.add(slide);


    	}
    	return slides;
    }

    private static void outputSlideShow(List<Slide> L) throws IOException {
        File Output = new File("Output");
        BufferedWriter out = new BufferedWriter(new FileWriter(Output));
        out.write(numberOfSlides);
        out.newLine();
        for(int i = 0; i < L.size(); i++)
        {
            for(int k = 0; k < L.get(i).images.length; k++)
            out.write(L.get(i).images[k].index);
            out.write(" ");
        }
        out.newLine();
        out.close();
    }

    public int minScore(Slide L, Slide R){
        List<String> common = new ArrayList<>();
        int sz = (L.getNumTags() < R.getNumTags())? L.getNumTags() : R.getNumTags();

        for(int i = 0; i< sz ; i++){
            if(R.Tags.contains(L.Tags.get(i))) common.add(L.Tags.get(i));
        }
        int min = (Math.abs(L.getNumTags()-common.size()) < Math.abs(R.getNumTags()))? Math.abs(L.getNumTags()-common.size()) : Math.abs(R.getNumTags()-common.size());
        min = (min < common.size())? min : common.size();

        return min;
    }
}



