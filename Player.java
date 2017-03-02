package XO;

/**
 * Класс игрока.
 */
public class Player {
    private boolean winner = false; // Выиграл или нет
    private boolean state;          // Активность (его ход или нет), когда играют люди
    private boolean isRobot;        // Играет человек или робот
    private FIELD_STATE field;      // Играет крестами или нолями
    private String name;            // Имя игрока
    int move = 0;

    public Player(boolean state, FIELD_STATE field, String name){
        this.state = state;
        this.field = field;
        this.name = name;
        this.isRobot = false;
    }

    public boolean isActiv(){
        return state;
    }

    public boolean isRobot(){
        return isRobot;
    }

    public boolean isWinner(){
        return winner;
    }

    public FIELD_STATE getField(){
        return field;
    }

    public String getName(){
        return name;
    }

    public boolean getActiv(){
        return state;
    }

    public void makeMove(){ move++; }

    public void setActiv(boolean state){
        this.state = state;
    }

    public void setRobot(boolean isRobot){ this.isRobot = isRobot; }

    public void setWinner(boolean winner){
        this.winner = winner;
    }

}
