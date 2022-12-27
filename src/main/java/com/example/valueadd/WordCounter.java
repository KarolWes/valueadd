package com.example.valueadd;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.*;

public class WordCounter {
    private List<String> text;
    private List<String> uniqueWords;
    public WordCounter() {
        this.text = new ArrayList<>();
        this.uniqueWords = new ArrayList<>();
    }

    private List<String> Cleanup(String text){
        return Arrays.asList(text.split("[^\\p{IsAlphabetic}]+"));
    }

    private HashMap CountWords(String input){
        text = Cleanup(input);
        uniqueWords = new ArrayList<>();
        HashMap<String, List<List<Integer>>> answer = new HashMap<>();
        for(int i = 0; i < text.size(); i++){
            var word = text.get(i);
            if(answer.containsKey(word)){
                var value = answer.get(word);
                value.get(1).add(i+1);
                value.get(0).set(0, value.get(0).get(0)+1);
            }
            else{
                var value = new ArrayList<List<Integer>>();
                value.add(new ArrayList<>());
                value.get(0).add(1);
                value.add(new ArrayList<>());
                value.get(1).add(i+1);
                answer.put(word, value);
                uniqueWords.add(word);
            }
        }
        return answer;
    }
    private JSONArray JSONConverter(HashMap<String, List<List<Integer>>> dict){
        Collections.sort(uniqueWords);
        JSONArray answer = new JSONArray();
        for(var word: uniqueWords){
            JSONObject tmp = new JSONObject();
            tmp.put("slowo", word);
            tmp.put("powtorzenia", dict.get(word).get(0).get(0).toString());
            tmp.put("pozycje", dict.get(word).get(1).toString());
            answer.put(tmp);
        }
        return answer;
    }

    public JSONArray Proceed(String text){
        return JSONConverter(CountWords(text));
    }
}
