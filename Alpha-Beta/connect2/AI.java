import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class AI here.
 * 
 * @author tiziazine ikram
 */
public abstract class AI extends Thread {
    volatile static boolean madeMove = false;
    volatile static int move = -1;
    
    protected int local_WID;
    
    int player;
    Board board;
    //Constructeur
    public AI(int player, Board board, myWorld world) {
        this.player = player;
        this.board = board;
        local_WID = world.GLOBAL_WID;
    }
    
    public abstract int generateMove(int player, Board board); //générer un mouvement
    
    public void run() {
        System.out.println("commencer avec WorldID: " + local_WID);
        move = generateMove(player, board);
        
        //si thread n'est pas terminé correctement,
        if (local_WID != myWorld.GLOBAL_WID) {
            System.out.println("Terminr avec WorldID: " + local_WID 
                + " Current WorldID: " + myWorld.GLOBAL_WID);
            return;
        }
            
        madeMove = true;
        System.out.println("Terminer avec WorldID: " + local_WID 
            + " Courant WorldID: " + myWorld.GLOBAL_WID);
    }
}
