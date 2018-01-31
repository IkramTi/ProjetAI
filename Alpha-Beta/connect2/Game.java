import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;

/**
 * Write a description of class Game here.
 * 
 * @author tiziazine ikram
 */
public class Game extends Thread
{
    private int[] players;

    private Board board;
    
    private myWorld world;
    
    private int local_WID;

    //turn will oscillate between 1 and 2.
    private int turn = 1;  // turn = 2 (ordinateur) turn = 1 (joueur) 
    private int true_turn = 1;  // on commence par le tour 1 puis 2 puis 3 jusqu'à la fin du jeu

    private int isGameOver;

    //ais are numbered
    //one is human, two is alphabetaO
    public Game(int one, int two, myWorld world) {
        players = new int[2];
        players[0] = one;  // joueur
        players[1] = two;   //ordinateur
        isGameOver = 0;
        this.world = world;  // initialiser l'objet world de type myWorld
        local_WID = world.GLOBAL_WID;
        board = new Board();
    }
    // Définir qui va jouer
    public void runAIThread(int player, Board b, int ai) {
        switch (ai) {
            case 2: (new AlphaBetaO(player, b, world)).start(); break;  // si ai=2 donc l'ordinateur va commencer à jouer
            default: (new Human(player, b, world)).start();             // si ai=1 (valeur par défaut donc le joueur commence à jouer
        }
    }
    public void run() {
        System.out.println("début du jeu avec WorldID: " + local_WID);
        while(isGameOver==0) {
                
            Human.madeMove = false; // permettre au joueur humain de faire un mouvement
          
            System.out.println("\n*****Turn: " +true_turn + "*****");
            int move;
            runAIThread(turn, board, players[turn-1]);
            while(AI.madeMove == false) {
                try {
                    sleep(100);
                } catch(Exception e) {
                    e.printStackTrace();
                }
                if (local_WID != myWorld.GLOBAL_WID) { // si les ids sont différents donc 
                    System.out.println(                 // le jeu s'est terminé et commence une autre partie
                        "Fin du jeu avec WorldID: " + local_WID  //chaque partie est identifié par un id unique
                            + " Courant WorldID: " + myWorld.GLOBAL_WID);
                    return;
                }
            }
            
            move = AI.move;
            AI.madeMove = false;
            System.out.println("Move: " + move);
            board.makeMove(turn, move);   //faire un mouvement
            world.addPiece(turn, move);    //ajouter la bulle colorée
     

            this.isGameOver = board.isGameOver();

            true_turn++;
            turn++;
            if (turn == 3) {
                turn = 1;
            }
        }
        System.out.println("Joueur " + isGameOver
                + (isGameOver == 1 ? " (X)" : " (O)") + " a gagné!");
                
        System.out.println("Fin du jeu avec WorldID: " + local_WID
            + " Courant WorldID: " + myWorld.GLOBAL_WID);
 
    }
}
