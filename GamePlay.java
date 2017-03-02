package XO;

/**
 * Класс управления игрой.
 */
public class GamePlay {
    private int i, j, moveNum = 0;  //Переменные координат нажатых кнопок и подсчета ходов
    public Player player1;          //Первый игрок
    public Player player2;          //Второй игрок
    private GameStatus gameStatus = GameStatus.FINISHED;  //Статус игры. По умолчанию не начата.


    public GamePlay(){
        player1 = new Player(true, FIELD_STATE.X, "Player_1");
        player2 = new Player(false, FIELD_STATE.O, "Player_2");
        player2.setRobot(true);
    }


    //GameButtonListener использует метод для сообщения геймплею координат нажатых кнопок
    public void setIJ(int i, int j){
        this.i = i;
        this.j = j;
    }

    //Игровой процесс. Запускается при нажатии кнопок на игровом поле.
    public void start(){

        if(getGameStatus() == GameStatus.FINISHED) return;

        if(moveNum == 0) {
            moveNum++;
            printLog(moveNum + " " + getActivPlayer().getName() + " move");
        }

        boolean menMoved = humanMove();   //Ход человека
        if(getGameStatus() == GameStatus.FINISHED) return;
        if(menMoved){
            moveNum++;
            if(moveNum == 10){
                finishGame(null);
                return;
            }
            printLog("&nbsp&nbsp&nbsp The man moved");
            printLog(moveNum + " "+ getNonActivPlayer().getName() + " move");
        }

        if( menMoved && player2.isRobot() ) {    //Ход робота, если второй игрок - робот.
            moveNum++;
            if(moveNum == 10){
                finishGame(null);
                return;
            }

            printLog("&nbsp&nbsp&nbsp The robot moved");
            printLog(moveNum + " " + getActivPlayer().getName() + " move");
            robotMove();
            if(getGameStatus() == GameStatus.FINISHED) return;
        }
    }

    public int getI(){
        return i;
    }

    public int getJ(){
        return j;
    }


    public Player getActivPlayer() {
        return player1.isActiv()? player1 : player2;
    }

    public Player getNonActivPlayer()  {
        return !player1.isActiv()? player1 : player2;
    }

    public void changeActivPlayer() {
        player1.setActiv(!player1.getActiv());
        player2.setActiv(!player2.getActiv());
    }
    public GameStatus getGameStatus(){ return gameStatus; }

    public void setGameStatus(GameStatus status){ gameStatus = status;}

    public void printLog(String str){  //Вывод подсказок в окно сообщений

        StringBuffer strBuff = new StringBuffer();
        strBuff.append(XO.gameWindow.logPane.getText());
        strBuff.delete(strBuff.length() - 6,  strBuff.length());
        strBuff.append("<br>" + str + "<html>");
        XO.gameWindow.logPane.setText(strBuff.toString());
    }

    private boolean humanMove(){        //Человеческий ход
        Player player = getActivPlayer();   //Чья очередь ходить
        boolean res = false;                //Ход не сделан

        if (XO.gameField.setGameFieldBit(getI(), getJ(), player.getField())) {  //Если поле свободно, делаем ход
            res = true;
            player.setWinner(XO.gameField.setIndex(getI(), getJ(), player.getField()));
            XO.gameWindow.setTextGameButtons(i, j, player.getField());
            if (player.isWinner()) {
                finishGame(player);
                return true;
            }
            if( !player2.isRobot() )
                changeActivPlayer();
        }
        return res;
    }

    private void robotMove(){       //Робот ходит

            Player player = player2;
            for(int p = 0; p < 2; p++) { //Первым кругом проверяем кресты, вторым ноли

                int[][] v = XO.gameField.getVIndex(); //Если в вертикальном коэффициенте два, ходим в соответствующий столбец

                for(int j = 0; j < 3; j++) {
                    if (v[player.getField().ordinal()][j] == 2) {
                        for (int i = 0; i < 3; i++) {
                            if (XO.gameField.getGameFieldBit(i, j) == FIELD_STATE.EMPTY) {
                                robotOneStep(i, j);

                                if (player2.isWinner()) {
                                    finishGame(player2);
                                }
                                return;
                            }
                        }
                    }
                }

                int[][] h = XO.gameField.getHIndex(); //Если в вертикальном коэффициенте два, ходим в соответствующий столбец

                 for(int j = 0; j < 3; j++) {
                    if (h[player.getField().ordinal()][j] == 2) {
                        for (int i = 0; i < 3; i++) {
                            if (XO.gameField.getGameFieldBit(j, i) == FIELD_STATE.EMPTY) {
                                robotOneStep(j, i);

                                if (player2.isWinner()) {
                                    finishGame(player2);
                                }
                                return;
                            }
                        }
                    }
                }

                int[] d = XO.gameField.getD1Index();    //Если в левой диагонали два, ходим в нее

                    if (d[player.getField().ordinal()] == 2) {
                        for (int i = 0; i < 3; i++) {
                            if (XO.gameField.getGameFieldBit(i, i) == FIELD_STATE.EMPTY) {
                                robotOneStep(i, i);

                                if (player2.isWinner()) {
                                    finishGame(player2);
                                }
                                return;
                            }
                        }
                    }

                d = XO.gameField.getD2Index();  //Если в правой диагонали два, ходим в нее
                j = 2;
                    if (d[player.getField().ordinal()] == 2) {
                        for (int i = 0; i < 3; i++) {
                            if (XO.gameField.getGameFieldBit(i, j) == FIELD_STATE.EMPTY) {
                                robotOneStep(i, j);

                                if (player2.isWinner()) {
                                    finishGame(player2);
                                }
                                return;
                            }
                            j--;
                        }
                    }

                player = player1;
            }
        simpleRobotMove();   //Если в ни в одной линии двух крестов или нолей нет, ходим куда нибудь
    }

    private void simpleRobotMove(){   //Ход робота куда нибудь

            if (XO.gameField.getGameFieldBit(1, 1) == FIELD_STATE.EMPTY) {
                robotOneStep(1, 1);
            } else if (XO.gameField.getGameFieldBit(0, 0) == FIELD_STATE.EMPTY) {
                robotOneStep(0, 0);
            } else if (XO.gameField.getGameFieldBit(0, 2) == FIELD_STATE.EMPTY){
                robotOneStep(0, 2);
            }else if (XO.gameField.getGameFieldBit(2, 2) == FIELD_STATE.EMPTY){
                robotOneStep(2, 2);
            }else if( XO.gameField.getGameFieldBit(2, 0) == FIELD_STATE.EMPTY ) {
                robotOneStep(2, 0);
            }else if (XO.gameField.getGameFieldBit(1, 0) == FIELD_STATE.EMPTY) {
                robotOneStep(1, 0);
            }else if (XO.gameField.getGameFieldBit(1, 2) == FIELD_STATE.EMPTY) {
                robotOneStep(1, 2);
            }else if (XO.gameField.getGameFieldBit(2, 1) == FIELD_STATE.EMPTY) {
                robotOneStep(2, 1);
            }else if (XO.gameField.getGameFieldBit(0, 1) == FIELD_STATE.EMPTY) {
                robotOneStep(0, 1);
            }
    }

    private void robotOneStep(int i, int j) {
        XO.gameField.setGameFieldBit(i, j, player2.getField());
        XO.gameWindow.setTextGameButtons(i, j, player2.getField());
        player2.setWinner(XO.gameField.setIndex(i, j, player2.getField()));
        player2.makeMove();
    }


    public void finishGame(Player player){
        if(player == null) printLog("<br><br>Nobody win!!!");
        else printLog("<br><br>" + ((player.isRobot())? "Robot ": player.getName()) + " is WINNER!!!!");
        setGameStatus(GameStatus.FINISHED);
        XO.gameWindow.restartButton.setEnabled(true);
    }

    public void clear(){
        i = 0;
        j = 0;
        moveNum = 1;
        player1.setActiv(true);
        player1.setWinner(false);
        player2.setActiv(false);
        player2.setWinner(false);
    }

}