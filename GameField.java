package XO;

/**
 * Класс игрового поля
 */
public class GameField {

    private FIELD_STATE gameField[][] = new FIELD_STATE[3][3];  //Модель игрового поля
    private int[][] v = new int[2][3];      //Вертикальный коэффициент
    private int[][] h = new int[2][3];      //Горизонтальный коэффициент
    private int[] d1 = new int[2];          //Коэффициент левой диагонали (слева направо вниз)
    private int[] d2 = new int[2];          //Коэффициент правой диагонали (справа налево вниз)

    public GameField(){
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++)
                gameField[i][j] = FIELD_STATE.EMPTY;
    }

    public FIELD_STATE getGameFieldBit(int i, int j){ return gameField[i][j]; } //Возвращает значение клетки поля (Х, 0, пусто)


    public boolean setGameFieldBit(int i, int j, FIELD_STATE state){        //Устанавливает значение клетки поля (Х, 0, пусто)
        boolean res = false;

        if( gameField[i][j] == FIELD_STATE.EMPTY ) {
            gameField[i][j] = state;
            res = true;
        }
        return res;
    }

    public int[][] getVIndex(){ return v; }

    public int[][] getHIndex(){ return h; }

    public int[] getD1Index(){ return d1; }

    public int[] getD2Index(){ return d2; }



    public boolean setIndex(int i, int j, FIELD_STATE state){  //Устанавливает значение индекса для Х или 0

        int id = i*10 + j;  //Вычисляем идентификатор для действий в зависимости от координаты хода
        int s = state.ordinal();

        boolean res = false;

        switch(id){
            case 0:
                if (++v[s][j] == 3 | ++h[s][i] == 3 | ++d1[s] == 3)
                    res = true;
                break;

            case 1:
                if (++v[s][j] == 3 | ++h[s][i] == 3)
                    res = true;
                break;

            case 2:
               if ( ++v[s][j] == 3 | ++h[s][i] == 3 | ++d2[s] == 3 )
                   res = true;
                break;

            case 10:
                if ( ++v[s][j] == 3  | ++h[s][i] == 3)
                    res = true;
                break;

            case 11:
                if ( ++v[s][j] == 3 | ++h[s][i] == 3 | ++d1[s] == 3 | ++d2[s] == 3 )
                    res = true;
                break;

            case 12:
                if( ++v[s][j] == 3 | ++h[s][i] == 3)
                    res = true;
                break;

            case 20:
                if ( ++v[s][j] == 3 | ++h[s][i] == 3 |  ++d2[s] == 3)
                    res = true;
                break;

            case 21:
                if ( ++v[s][j] == 3 | ++h[s][i] == 3 )
                    res = true;
                break;

            case 22:
                if ( ++v[s][j] == 3 | ++h[s][i] == 3 | ++d1[s] == 3)
                    res = true;
                break;

        }
        return res;
    }

    public void clear(){  //Возвращаем поля класса в исходное состояние
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++)
                gameField[i][j] = FIELD_STATE.EMPTY;

        v = new int[2][3];
        h = new int[2][3];
        d1 = new int[2];
        d2 = new int[2];
    }

    //@Override
    //public String toString(){
    //    return "X  " + "v0 = " + v[1][0] + "  v1 = " + v[1][1] + "  v2 = " + v[1][2] + "  h0 = " + h[1][0] + "  h1 = " + h[1][1] + "  h2 = " + h[1][2] + "  d1 = " + d1[1] + "  d2 = " + d2[1]+"\n" +
    //           "0  " + "v0 = " + v[0][0] + "  v1 = " + v[0][1] + "  v2 = " + v[0][2] + "  h0 = " + h[0][0] + "  h1 = " + h[0][1] + "  h2 = " + h[0][2] + "  d1 = " + d1[0] + "  d2 = " + d2[0]+"\n";

    //}
}