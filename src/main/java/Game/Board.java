package Game;

import java.util.ArrayList;

public class Board {
    private int height = 6;
    private int width = 7;
    public int[][] board = new int[height][width];
    protected String winner;
    private ArrayList<Integer> history;

    public void createBoard() {
        for(int i=0; i<height; i++) {
            for(int j=0; j<width; j++) {
                board[i][j] = 0;
            }
        }
    }

    public void printBoard(){

        for(int i=0; i<height; i++) {
            System.out.println();
            for(int j=0; j<width; j++) {
                System.out.print(" "+board[i][j]);
            }
        }
    }

    public void setBoardSize(int col, int row) {
        if ((col<4)||(row<4)) throw new IllegalArgumentException("Za mała plansza! Musi być minimum 4x4!");
        height = row;
        width = col;
        board = new int[height][width];

    }//xcol yrow
    public void winCheck(int row, int col, int disc, String player) {

        int sumhor=0,sumver=0,sumdiagup=0,sumdiagdown=0;//-|/\
        //hor (->)
        for(int i=row; i<(width-1); i++) {
            if(i < (width-1) && board[col][i + 1] == disc) {
                sumhor++;
            } else

                break;
        }
        //hor (<-)
        for(int i=row; i>0; i--) {
            if(i > 0 && board[col][i - 1] == disc) {
                sumhor++;
            } else
                break;
        }

        //ver (+)
        for(int i=col; i<(height-1); i++) {
            if(i < (height-1) && board[i + 1][row] == disc) {
                sumver++;
            } else
                break;
        }
        //ver (-)
        for(int i=col; i>0; i--) {
            if(i > 0 && board[i - 1][row] == disc) {
                sumver++;
            } else
                break;
        }

        //diag ( / + )
        int j = row;
        for(int i=col; i<height; i++) {
            if(( i + 1) < height && ( j - 1) >= 0 && board[i + 1][j - 1] == disc) {
                sumdiagup++;
            } else
                break;
            j--;
        }

        //diag ( / - )
        j = row;
        for(int i=col; i>0; i--) {
            if((i-1) > 0 && (j+1) < width && board[i - 1][j + 1] == disc) {
                sumdiagup++;
            } else
                break;
            j++;
        }

        //diag ( \ - )
        j = row;
        for(int i=col; i<height; i++) {
            if((i+1) < height && (j+1) < width && board[i + 1][j + 1] == disc) {
                sumdiagdown++;
            } else
                break;
            j++;
        }

        //diag ( \ + )
        j = row;
        for(int i=col; i>0; i--) {
            if((i-1) > 0 && (j-1) > 0 && board[i - 1][j - 1] == disc) {
                sumdiagdown++;
            } else
                break;
            j--;
        }


        if(sumhor >= 3 || sumver >=3 || sumdiagdown >= 3 || sumdiagup >= 3) {
            winner = player;
        }



    }
    public boolean putCoin(int row,int disc,String player)throws Exception {
        if (row < 0 || row > width) {
            throw new Exception("Nie ma takiej kolumny!");
        } else {

            if (board[0][row] != 0) {
                System.out.print("Kolumna, pełna! Wybierz inną!");
                return false;
            } else {
                for (int i = height - 1; i >= 0; i--) {


                    if (board[i][row] == 0) {
                        board[i][row] = disc;
                        history.add(row);
                        winCheck(row,i,disc,player);
                        return true;
                    }


                }
                return false;

            }

        }
    }
    }
