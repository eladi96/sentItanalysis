import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

class Reviews {

    static HashMap<String, Float> getReviewsAsMap(String csvPath) throws IOException {

        HashMap<String, Float> reviews = new HashMap<>();

        BufferedReader reader = new BufferedReader(new FileReader(csvPath));
        String line;
        while ((line = reader.readLine()) != null){
            String[] values = line.split("\t");
            reviews.put(values[0], Float.parseFloat(values[1]));
        }

        return reviews;
    }

}
