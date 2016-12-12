package de.mobileanwendungen.mytictactoe;

import android.app.Activity;

/**
 * Created by Francois on 12/12/2016.
 */

public class Gameboard {

    // Spielfeld auf 3x3 festgelegt
    private String[][] board = new String[3][3];

    /**
     * Inizialisiert das Spielfeld
     */
    Gameboard(){
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                board[i][j] = "";
            }
        }
    }

    /**
     * getter fuer das Spielfeld
     * @return
     */
    public String[][] getBoard(){
        return board;
    }

    /**
     * setzt das entsprechende Zeichen
     * @param xPosition
     * @param yPosition
     * @param zeichen
     */
    public void setzeZeichen(int xPosition, int yPosition, String zeichen){
        if(board[xPosition][yPosition] == ""){
            board[xPosition][yPosition] = zeichen;
        }
    }

    /**
     * Ueberpruefen ob ein Gewinner vorliegt
     */
    public  boolean isWinner(){
        // Prueft Diagonale
        if(board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] != ""){
            return true;
        }

        if(board[2][0] == board[1][1] && board[2][0] == board[0][2] && board[2][0] != ""){
            return true;
        }

        for (int i = 0; i < 3; i++) {
            //Prueft Zeilen
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != "")

                return true;

            //Prueft Spalten
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != "")

                return true;
        }
        return false;
    }

    /**
     * Resettet das Spielfeld
     */
    public void clear(){
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                board[i][j] = "";
            }
        }
    }
}


