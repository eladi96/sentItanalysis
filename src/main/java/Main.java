import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        try {

            /*Sentix sentix = new Sentix("files/sentix.tsv");
            ArrayList<String> lemmas = ItalianParser.getLemmas("testo-parsed.json");

            float positività = 0;
            float negatività = 0;
            float polarità = 0;
            int max = 0;
            for(String lemma : lemmas){
                max += 1;
                positività += sentix.getPositiveScore(lemma);
                negatività += sentix.getNegativeScore(lemma);
                polarità += sentix.getPolarity(lemma);
            }

            System.out.printf("Positività: %f\n", positività/max);
            System.out.printf("Negatività: %f\n", negatività/max);
            System.out.printf("Polarità: %f\n", polarità);*/

            HashMap<String, Integer> reviews = Reviews.getReviewsAsMap("files/amazon-reviews.tsv");
            System.out.println(reviews.keySet().toArray()[0]);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
