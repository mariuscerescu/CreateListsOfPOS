package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XmlParser {
    public static void main(String[] args) {
        File inputFile = new File("intrebaria3.xml");
        Document doc;

        Map<String, List<String>> posLists = new HashMap<>();
        posLists.put("NOUN", new ArrayList<>());
        posLists.put("VERB", new ArrayList<>());
        posLists.put("ADJECTIVE", new ArrayList<>());
        posLists.put("DETERMINER", new ArrayList<>());
        posLists.put("PRONOUN", new ArrayList<>());
        posLists.put("ADVERB", new ArrayList<>());
        posLists.put("ADPOSITION", new ArrayList<>());
        posLists.put("ARTICLE", new ArrayList<>());
        posLists.put("NUMERAL", new ArrayList<>());
        posLists.put("CONJUNCTION", new ArrayList<>());

        try {
            doc = Jsoup.parse(inputFile, "UTF-8", "");
            Elements words = doc.select("W");

            for (Element word : words) {
                String pos = word.attr("POS");
                if (posLists.containsKey(pos)) {
                    posLists.get(pos).add(word.text());
                }
            }

            // Write the words for each POS to a separate file
            for (Map.Entry<String, List<String>> entry : posLists.entrySet()) {
                Path file = Paths.get(entry.getKey() + ".txt");
                Files.write(file, entry.getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
