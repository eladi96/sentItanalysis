import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Reviews {

    public static HashMap<String, Integer> getReviewsAsMap(String csvPath) throws IOException {

        HashMap<String, Integer> reviews = new HashMap<>();

        BufferedReader reader = new BufferedReader(new FileReader(csvPath));
        String line;
        while ((line = reader.readLine()) != null){
            String[] values = line.split("\t");
            reviews.put(values[0], Integer.parseInt(values[1]));
        }

        return reviews;
    }

}
