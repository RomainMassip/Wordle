import org.junit.jupiter.api.Test;

import wordle.Word;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

public class WordTest {
    
    @Test 
    void testsetWord(){
        Word Word = new Word() ;

        Word.setWord("AZerT");

        assertEquals("A", Word.word[0]);
        assertEquals("Z", Word.word[1]);
        assertEquals("e", Word.word[2]);
        assertEquals("r", Word.word[3]);
        assertEquals("T", Word.word[4]);

    }

    @Test
    void testgetWord(){
        Word Word = new Word() ;

        Word.word[0] = "A";
        Word.word[1] = "Z";
        Word.word[2] = "e";
        Word.word[3] = "r";
        Word.word[4] = "T";

        String[] tabvalide = new String[5];

        tabvalide[0] = "A";
        tabvalide[1] = "Z";
        tabvalide[2] = "e";
        tabvalide[3] = "r";
        tabvalide[4] = "T";

        assertTrue(Arrays.equals(tabvalide, Word.getWord()));

    }

    @Test
    void testgetOneWord(){
        Word Word = new Word() ;

        Word.word[0] = "A";
        Word.word[1] = "Z";
        Word.word[2] = "e";
        Word.word[3] = "r";
        Word.word[4] = "T";

        assertEquals("A", Word.getOneWord(0));
        assertEquals("Z", Word.getOneWord(1));
        assertEquals("e", Word.getOneWord(2));
        assertEquals("r", Word.getOneWord(3));
        assertEquals("T", Word.getOneWord(4));
    }

    @Test
    void testgetWordString(){
        Word Word = new Word() ;

        Word.word[0] = "A";
        Word.word[1] = "Z";
        Word.word[2] = "e";
        Word.word[3] = "r";
        Word.word[4] = "T";

        assertEquals("AZerT", Word.getWordString());

    }

    @Test
    void testTestWordGlobal(){
        int[] tabscore = new int[5];

        tabscore[0] = 2;
        tabscore[1] = 0;
        tabscore[2] = 1;
        tabscore[3] = 0;
        tabscore[4] = 2;

        Word word = new Word();

        word.word[0] = "A";
        word.word[1] = "Z";
        word.word[2] = "e";
        word.word[3] = "r";
        word.word[4] = "T";

        String[] tabvalide = new String[5];

        tabvalide[0] = "A";
        tabvalide[1] = "x";
        tabvalide[2] = "T";
        tabvalide[3] = "n";
        tabvalide[4] = "T";

        assertTrue(Arrays.equals(tabscore, word.testWord(tabvalide)));
    }

    @Test
    void testTestWord(){
        int[] tabscore = new int[5];

        tabscore[0] = 2;
        tabscore[1] = 0;
        tabscore[2] = 1;
        tabscore[3] = 0;
        tabscore[4] = 2;

        Word word = new Word();

        word.word[0] = "A";
        word.word[1] = "Z";
        word.word[2] = "e";
        word.word[3] = "r";
        word.word[4] = "T";

        String[] tabvalide = new String[5];

        tabvalide[0] = "A";
        tabvalide[1] = "x";
        tabvalide[2] = "T";
        tabvalide[3] = "n";
        tabvalide[4] = "T";

        assertEquals(tabscore[0], word.testWord(tabvalide)[0]);
        assertEquals(tabscore[1], word.testWord(tabvalide)[1]);
        assertEquals(tabscore[2], word.testWord(tabvalide)[2]);
        assertEquals(tabscore[3], word.testWord(tabvalide)[3]);
        assertEquals(tabscore[4], word.testWord(tabvalide)[4]);
    }

    @Test
    void testGetRandomWord() {
        Word word = new Word();

        assertEquals("tests", word.getRandomWord(0));
        assertEquals("pomme", word.getRandomWord(1));
        assertEquals("rouge", word.getRandomWord(2));
        assertEquals("bleus", word.getRandomWord(9));
    }

}
