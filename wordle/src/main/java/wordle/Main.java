package wordle;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        game.startGame(game.getReader());
    }
}
