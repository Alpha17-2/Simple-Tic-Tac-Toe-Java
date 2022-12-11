import Game.Game;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        try {
            game.play();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}