package XO;

/**
 * Игра "Крестики, нолики"
 *
 * Main создает три класса:
 * GameWindow - интерфейс игры, реакции кнопок
 * GamField - модель игрового поля
 * GamePlay - игровой процесс: игровая последовательность, взаимотействие игроков, взаимотействие GameWindow и GamField
 */

public class XO {
    public static GameWindow gameWindow;
    public static GameField gameField;
    public static GamePlay gamePlay;

    public static void main(String[] args) {
        gameWindow = new GameWindow();
        gameField = new GameField();
        gamePlay = new GamePlay();
        System.out.println(FIELD_STATE.O);
    }
}