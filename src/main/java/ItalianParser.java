import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import eu.fbk.dh.tint.runner.*;

import java.io.*;
import java.util.ArrayList;

class ItalianParser {

    static String parseFile(String textFile) throws IOException {

        // Initialize tint pileline and load default properties
        TintPipeline pipeline = new TintPipeline();
        pipeline.loadDefaultProperties();
        pipeline.setProperty("annotators", "ita_toksent,pos,ita_morpho,ita_lemma");
        pipeline.load();

        File file = new File(textFile);
        FileInputStream inputStream = new FileInputStream(file);
        String outputPath = "files/JSON-files/" + file.getName().replace(".txt", ".json");
        FileOutputStream outputStream = new FileOutputStream(outputPath);
        pipeline.run(inputStream, outputStream, TintRunner.OutputFormat.JSON);

        return outputPath;
    }

    static void parseText(String text, String outputPath) throws IOException {

        // Initialize tint pileline and load default properties
        TintPipeline pipeline = new TintPipeline();
        pipeline.loadDefaultProperties();
        pipeline.setProperty("annotators", "ita_toksent,pos,ita_morpho,ita_lemma");
        pipeline.load();

        FileOutputStream outputStream = new FileOutputStream(outputPath);
        pipeline.run(text, outputStream, TintRunner.OutputFormat.JSON);

    }

    static ArrayList<String> getLemmas(String JSONFile) throws IOException {

        ArrayList<String> lemmas = new ArrayList<>();

        JsonParser parser = new JsonParser();
        JsonObject file = parser.parse(new JsonReader(new FileReader(JSONFile))).getAsJsonObject();
        JsonArray sentences = file.getAsJsonArray("sentences");
        for(JsonElement sentElement: sentences){
            JsonObject sentence = sentElement.getAsJsonObject();
            JsonArray tokens = sentence.getAsJsonArray("tokens");
            for(JsonElement tokElement: tokens){
                JsonObject token = tokElement.getAsJsonObject();
                String lemma = token.get("lemma").getAsString();
                if(!lemma.equals("[PUNCT]")) lemmas.add(lemma);
            }
        }

        return lemmas;
    }
}
