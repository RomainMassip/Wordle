import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import wordle.Color;
import wordle.Game;
import wordle.Word;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.StringReader;

public class GameTest {

    private Game game;
    private Word mockWordToGuess;
    private Word mockWordEnter;
    private Color mockColor;

    @BeforeEach
    public void setUp() {
        game = new Game();
        mockWordToGuess = mock(Word.class);
        mockWordEnter = mock(Word.class);
        mockColor = mock(Color.class);

        game.wordtoguess = mockWordToGuess;
        game.wordenter = mockWordEnter;
        game.couleur = mockColor;
    }

    @Test
    public void testIsAlphabetic_withValidWord_returnsTrue() {
        assertTrue(game.isAlphabetic("apple"));
    }

    @Test
    public void testIsAlphabetic_withInvalidWord_returnsFalse() {
        assertFalse(game.isAlphabetic("app1e"));
    }

    @Test
    public void testTestLength_withFiveLetterWord_returnsTrue() {
        assertTrue(game.testlenght("hello"));
    }

    @Test
    public void testTestLength_withShortWord_returnsFalse() {
        assertFalse(game.testlenght("hi"));
    }

    @Test
    public void testCalculateValidCount_returnsCorrectCount() {
        int[] tab = {2, 0, 1, 2, 2};
        int score = game.calculateValidCount(tab);
        assertEquals(3, score);
    }

    @Test
    public void testConcatenateWithColor_formatsCorrectly() {
        int[] tab = {2, 1, 0, 0, 2};
        when(mockColor.getGreen()).thenReturn("[G]");
        when(mockColor.getYellow()).thenReturn("[Y]");
        when(mockColor.getGray()).thenReturn("[X]");
        when(mockColor.ClearColor()).thenReturn("[C]");
        when(mockWordEnter.getOneWord(0)).thenReturn("a");
        when(mockWordEnter.getOneWord(1)).thenReturn("b");
        when(mockWordEnter.getOneWord(2)).thenReturn("c");
        when(mockWordEnter.getOneWord(3)).thenReturn("d");
        when(mockWordEnter.getOneWord(4)).thenReturn("e");

        String result = game.concatenetwithcolor(tab, mockWordEnter, mockColor);
        assertEquals("[G]a[C][Y]b[C][X]c[C][X]d[C][G]e[C]", result);
    }


    @Test
    public void testFinPartie_winMessage() {
        when(mockColor.getGreen()).thenReturn("[GREEN]");
        when(mockColor.ClearColor()).thenReturn("[C]");
        when(mockWordEnter.getWordString()).thenReturn("apple");

        String result = game.FinPartie(mockWordEnter, 3, true, mockColor);
        assertTrue(result.contains("Felicitations"));
        assertTrue(result.contains("[GREEN]apple[C]"));
    }

    @Test
    public void testFinPartie_loseMessage() {
        when(mockColor.getRed()).thenReturn("[RED]");
        when(mockColor.ClearColor()).thenReturn("[C]");
        when(mockWordEnter.getWordString()).thenReturn("apple");

        String result = game.FinPartie(mockWordEnter, 5, false, mockColor);
        assertTrue(result.contains("Dommage"));
        assertTrue(result.contains("[RED]apple[C]"));
    }

    @Test
    public void testTestIsValid_acceptsValidInput() {
        StringReader stringReader = new StringReader("apple\n");
        BufferedReader reader = new BufferedReader(stringReader);
        Game localGame = new Game();
        String result = localGame.testisValid(0, reader);
        assertEquals("apple", result);
    }

    @Test
    public void testReadEntrer_returnsInput() {
        BufferedReader reader = new BufferedReader(new StringReader("hello\n"));
        String input = game.readEntrer(reader);
        assertEquals("hello", input);
    }

    @Test
    public void testReadEntrer_handlesException() {
        BufferedReader reader = mock(BufferedReader.class);
        try {
            when(reader.readLine()).thenThrow(new RuntimeException("Test Exception"));
        } catch (Exception ignored) {}
        String input = game.readEntrer(reader);
        assertTrue(input.contains("Error"));
    }

    @Test
    public void testStartGame_userWinsAtThirdAttempt() {
        Game game = new Game();

        Word mockWordToGuess = mock(Word.class);
        Word mockWordEnter = mock(Word.class);
        Color mockColor = mock(Color.class);

        game.wordtoguess = mockWordToGuess;
        game.wordenter = mockWordEnter;
        game.couleur = mockColor;

        // Le mot à deviner est "apple"
        when(mockWordToGuess.getRandomInt()).thenReturn(1);
        when(mockWordToGuess.getRandomWord(1)).thenReturn("apple");
        when(mockWordToGuess.testWord(new String[]{"z","z","z","z","z"})).thenReturn(new int[]{0, 0, 0, 0, 0});
        when(mockWordToGuess.testWord(new String[]{"x","x","x","x","x"})).thenReturn(new int[]{0, 0, 0, 0, 0});
        when(mockWordToGuess.testWord(new String[]{"a","p","p","l","e"})).thenReturn(new int[]{2, 2, 2, 2, 2});

        doNothing().when(mockWordToGuess).setWord("apple");

        // Simulation des entrées utilisateur : 3 tentatives, la 3e est gagnante
        StringReader input = new StringReader("zzzzz\nxxxxx\napple\n");
        BufferedReader reader = new BufferedReader(input);

        when(mockWordEnter.getWord())
            .thenReturn(
                new String[]{"z", "z", "z", "z", "z"},
                new String[]{"x", "x", "x", "x", "x"},
                new String[]{"a", "p", "p", "l", "e"}
            );


        when(mockColor.getGreen()).thenReturn("[G]");
        when(mockColor.getYellow()).thenReturn("[Y]");
        when(mockColor.getRed()).thenReturn("[R]");
        when(mockColor.ClearColor()).thenReturn("[C]");
        when(mockWordEnter.getWordString()).thenReturn("apple");

        game.startGame(reader);

        // On vérifie que la victoire est bien détectée à la 3e tentative
        assertEquals(5, game.cptvalid);
    }

}
