package edu.gatech.seclass.sdpvocabquiz.quiz;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuizContent {

    private static Gson gson = new Gson();

    private String name;
    private String description;
    private Map<String, String> words = new HashMap<>();
    private List<String> definitions = new ArrayList<>();

    public static String toJson(QuizContent quizContent) {
        return gson.toJson(quizContent);
    }

    public static QuizContent fromJson(String json) {
        return gson.fromJson(json, QuizContent.class);
    }

    public QuizContent(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Map<String, String> getWords() {
        return words;
    }

    public List<String> getDefinitions() {
        return definitions;
    }

    public void addWord(String word, String definition) {
        words.putIfAbsent(word, definition);
    }

    public void addDefinitions(String... definitions) {
        this.definitions.addAll(Arrays.asList(definitions));
    }

    public ArrayList<String> generateQuestions() {
        // questions are organized as a list of strings, each 5 string group into one question
        ArrayList<String> questions = new ArrayList<>(words.size() * 5);

        List<Map.Entry<String, String>> entries = new ArrayList<>(words.entrySet());
        Collections.shuffle(entries);
        Collections.shuffle(definitions);

        for (Map.Entry<String, String> entry : entries) {
            questions.add(entry.getKey());
            questions.add(entry.getValue());
            questions.add(definitions.remove(0));
            questions.add(definitions.remove(0));
            questions.add(definitions.remove(0));
        }

        return questions;
    }
}
