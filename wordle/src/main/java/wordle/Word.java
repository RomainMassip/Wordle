package wordle;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Word {

    public String[] word = new String[5];

    public void setWord(String paramword){

        String[] caracteres = paramword.split("");

        for (int i = 0; i < 5; i++) {
            this.word[i] = caracteres[i];;
        }
    }

    public String[] getWord(){
        return this.word;
    }

    public String getOneWord(int number){
        return this.word[number];
    }

    public String getWordString(){
        String wordString = "";

        for (int i = 0; i < word.length; i++) {
            wordString += this.word[i];
        }

        return wordString;
    }

    public int[] testWord(String[] Wordenter){

        int[] tabscore = new int[5];

        for (int i = 0; i < Wordenter.length; i++) {
            for (int j = 0; j < word.length; j++) {
                if(Wordenter[i].equals(this.word[j])){
                    if(i == j){
                        tabscore[i] = 2;
                        j = word.length;
                    }else{
                        tabscore[i] = 1;
                    }
                }
            }
        }

        return tabscore;

    }

    public String getRandomWord(int random) {
        List<String> dictionary = Arrays.asList(
            "tests", "pomme", "rouge", "verre", "chien", "table", "livre", "blanc", "noirs", "bleus"
        );

        return dictionary.get(random);
    }

    public int getRandomInt(){
        Random random = new Random();
        return random.nextInt(10);
    }
}
