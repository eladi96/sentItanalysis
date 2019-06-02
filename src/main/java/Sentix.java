import java.io.*;
import java.util.HashMap;

public class Sentix {

    private HashMap<String, SentixEntry> sentix;

    Sentix(String path) throws IOException {

        sentix = new HashMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line;
        while ((line = reader.readLine()) != null){
            String[] values = line.split("\t");
            SentixEntry entry = new SentixEntry(values[0],
                    values[1],
                    values[2],
                    Float.parseFloat(values[3]),
                    Float.parseFloat(values[4]),
                    Float.parseFloat(values[5]),
                    Float.parseFloat(values[6]));
            sentix.put(values[0], entry);
        }

    }

    float getPositiveScore(String lemma){

        return sentix.containsKey(lemma) ? sentix.get(lemma).positive : 0;
    }

    public float getNegativeScore(String lemma){

        return sentix.containsKey(lemma) ? sentix.get(lemma).negative : 0;
    }

    public float getPolarity(String lemma){

        return sentix.containsKey(lemma) ? sentix.get(lemma).polarity : 0;
    }

    public float getIntensity(String lemma){

        return sentix.containsKey(lemma) ? sentix.get(lemma).intensity : 0;
    }

    class SentixEntry {

        String lemma;
        String POS;
        String WNID;
        float positive;
        float negative;
        float polarity;
        float intensity;

        SentixEntry(String lemma,
                      String POS,
                      String WNID,
                      float positive,
                      float negative,
                      float polarity,
                      float intensity){
            this.lemma = lemma;
            this.POS = POS;
            this.WNID = WNID;
            this.positive = positive;
            this.negative = negative;
            this.polarity = polarity;
            this.intensity = intensity;
        }
    }

}
