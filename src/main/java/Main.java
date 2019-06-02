import java.io.IOException;
import java.util.HashMap;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;

public class Main {

    public static void main(String[] args) {

        try {

            Sentix sentix = new Sentix("files/sentix.tsv");

            HashMap<String, Float> reviews = Reviews.getReviewsAsMap("files/amazon-reviews.tsv");
            HashMap<String, Float> polarity = new HashMap<>();

            int filenum = 0;
            for(String review : reviews.keySet()){

                filenum++;
                System.out.println("Parsing review " + filenum + " of " + reviews.size());
                // System.out.println(review);

                String jsonFile;
                if(filenum < 10) jsonFile = "files/JSON-files/0" + filenum + ".json";
                else jsonFile = "files/JSON-files/" + filenum + ".json";

                ItalianParser.parseText(review, jsonFile);

                float score = 0;
                for(String lemma : ItalianParser.getLemmas(jsonFile)){
                    score += sentix.getPolarity(lemma) * sentix.getIntensity(lemma);
                }

                polarity.put(review, score);

            }

            double[] ratings = new double[filenum];
            double[] scores = new double[filenum];
            Object[] rev =  reviews.keySet().toArray();
            for(int i = 0; i < filenum; i++){
                ratings[i] = reviews.get(rev[i]);
                scores[i] = polarity.get(rev[i]);
            }

            PearsonsCorrelation pearson = new PearsonsCorrelation();
            double correlation = pearson.correlation(ratings, scores);
            System.out.println("Correlazione: " + correlation);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
