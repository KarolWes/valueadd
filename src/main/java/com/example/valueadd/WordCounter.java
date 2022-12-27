package com.example.valueadd;

import org.json.JSONArray;
import java.util.*;

public class WordCounter {
    //List of unique words in text, used to create alphabetically ordered output
    private List<String> uniqueWords;

    // empty constructor
    public WordCounter() {}

    //Removes numbers and punctuation and splits string into separate words
    private List<String> Cleanup(String text){
        return Arrays.asList(text.split("[^\\p{IsAlphabetic}]+"));
    }

    //Main function
    //Generates dictionary (map) indexed by words, containing list of lists.
    //value[0] -> count, only one elements
    //value[1] -> positions, list
    private HashMap CountWords(String input){
        List<String> text = Cleanup(input);
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

    //converts map into json
    private JSONArray JSONConverter(HashMap<String, List<List<Integer>>> dict){
        Collections.sort(uniqueWords);
        JSONArray answer = new JSONArray();
        for(var word: uniqueWords){
            Map<String, String > tmp = new HashMap<>();
            tmp.put("slowo", word);
            tmp.put("powtorzenia", dict.get(word).get(0).get(0).toString());
            tmp.put("pozycje", dict.get(word).get(1).toString());
            answer.put(tmp);
        }
        return answer;
    }

    //executable function
    public JSONArray Proceed(String text){
        return JSONConverter(CountWords(text));
    }
}
