import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Human here.
 * 
 * @author tiziazine ikram
 */
public class Human extends AI {
    volatile static boolean madeMove = false;
    volatile static int move;
    // constructeur
    public Human(int player, Board board, myWorld world) {
        super(player, board, world);  // h�rite de la classe AI
    }
    public int generateMove(int player, Board board) {
        while(madeMove == false){ //blocker
            if (local_WID != myWorld.GLOBAL_WID) { // si les ids diff�rents donc la partie s'est termin�e
                return -1;
            }
            try {
                sleep(100);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return move;
    }
}
