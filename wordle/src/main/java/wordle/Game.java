package wordle;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Game {

    public Color couleur;
    public Word wordtoguess;
    public Word wordenter;
    public int[] tabresult;
    public int cptvalid;

    public Game() {
        this.couleur = new Color();
        this.wordtoguess = new Word();
        this.wordenter = new Word();
        this.tabresult = new int[5];
        this.cptvalid = 0;
    }

    public void startGame(BufferedReader reader) {
        try {
            String word = initializeGame();
            printMessage("Wordle");
            printMessage("Le jeu commence");

            for (int i = 0; i < 6; i++) {

                String userWord = getUserInput(i, reader);
                wordenter.setWord(userWord);

                tabresult = wordtoguess.testWord(wordenter.getWord());
                String result = evaluateResult(tabresult, wordenter);

                cptvalid = calculateValidCount(tabresult);
                if (checkWinOrLose(cptvalid,i)) {
                    break;
                }
                printGameStatus(i, result);
            }
        } catch (Exception e) {
            printMessage("Problème lors de l'exécution : " + e.getMessage());
        }
    }

    private String initializeGame() {
        String word = wordtoguess.getRandomWord(wordtoguess.getRandomInt());
        wordtoguess.setWord(word);
        return word;
    }

    private String getUserInput(int round, BufferedReader reader) {
        return testisValid(round, reader);
    }

    private String evaluateResult(int[] tabresult, Word wordenter) {
        return concatenetwithcolor(tabresult, wordenter, this.couleur);
    }

    private boolean checkWinOrLose(int cptvalid, int round) {
        if (cptvalid == 5) {
            printMessage(FinPartie(wordenter, round, true, this.couleur));
            return true;
        } else {
            return false;
        }
    }

    private void printGameStatus(int round, String result) {
        printMessage(result);
        if (round == 5) {
            printMessage(FinPartie(wordtoguess, round, false, this.couleur));
        }
    }

    private void printMessage(String message) {
        System.out.println(message);
    }

    public String FinPartie(Word wordenter, int nbessaie, boolean find, Color couleur) {
        if (find) {
            return "Felicitations\nLe mot etait : " + couleur.getGreen() + wordenter.getWordString() + couleur.ClearColor() +
                   "\nVous l'avez trouve en " + (nbessaie + 1) + " essais";
        } else {
            return "Dommage\nLe mot était : " + couleur.getRed() + wordenter.getWordString() + couleur.ClearColor();
        }
    }

    public boolean isAlphabetic(String word) {
        return word.matches("[a-z]+");
    }

    public boolean testlenght(String word) {
        return word.length() == 5;
    }

    public String readEntrer(BufferedReader reader) {
        try {
            return reader.readLine();
        } catch (Exception e) {
            return "Error : " + e.getMessage();
        }
    }

    public String testisValid(int i, BufferedReader reader) {
        boolean wordvalid = false;
        String entrer = "";

        while (!wordvalid) {
            printMessage("Veuillez entrer un mot. (" + (6 - i) + " essais restants)");
            entrer = readEntrer(reader);

            if (testlenght(entrer) && isAlphabetic(entrer)) {
                wordvalid = true;
            } else {
                printMessage("Le mot que vous avez rentre est non valide ! Le mot doit faire 5 et seulement 5 lettres.");
            }
        }

        return entrer;
    }

    public String concatenetwithcolor(int[] tabresult, Word wordenter, Color couleur) {
        StringBuilder resultat = new StringBuilder();

        for (int j = 0; j < tabresult.length; j++) {
            if (tabresult[j] == 2) {
                resultat.append(couleur.getGreen()).append(wordenter.getOneWord(j)).append(couleur.ClearColor());
            } else if (tabresult[j] == 1) {
                resultat.append(couleur.getYellow()).append(wordenter.getOneWord(j)).append(couleur.ClearColor());
            } else if (tabresult[j] == 0) {
                resultat.append(couleur.getGray()).append(wordenter.getOneWord(j)).append(couleur.ClearColor());
            }
        }

        return resultat.toString();
    }

    public int calculateValidCount(int[] tabresult) {
        int score = 0;
        for (int i = 0; i < tabresult.length; i++) {
            if (tabresult[i] == 2) {
                score += 1;
            }
        }

        return score;
    }

    public BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }
}
