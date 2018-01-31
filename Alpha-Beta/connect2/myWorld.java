import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class myWorld here.
 * 
 * @author tiziazine ikram 
 */
public class myWorld  extends World
{
    private WhiteBubble[][] grid = new WhiteBubble[6][7];
    
    private Game game;
    
    //worldID : utilisé par les threads pour s'assurer qu'ils ne se reportent pas au jeu suivant lors de la réinitialisation.
    
    public static volatile int GLOBAL_WID;// = (int) (1000000*Math.random());
    
     /**
     * Constructeur des objets de la classe myWorld.
     * 
     */
    public myWorld()
    {    
        super(400, 320, 1); 
        setBackground("space-background.jpg");
        // Construction des bulles blanches
        for (int x=0 ; x<7 ; x++) {
            for (int y=0 ; y<6 ; y++) {
                WhiteBubble s = new WhiteBubble(x, y);
                addObject(s, x*48+55, y*45+40);
                grid[y][x] = s;
            }
        }
        GLOBAL_WID = (int) (1000000*Math.random());
        game = initGame(); //début du jeu
    }
    // définir une méthode qui permet de commancer le jeu
    private Game initGame() {
        Game g = new Game(2, 1, this);
        g.start();
        return g;
    }
    
    protected Game getGame() {
        return game;
    }
    // retourner notre grille des bulles 
    protected WhiteBubble[][] getGrid() {
        return grid;
    }
    // Construire la grille avec les bulles blanches
    protected void addPiece(int type, int x){
        for (int i=5 ; i>=0 ; i--) {   // i=y 
            if (grid[i][x].getType() == 0) {
                WhiteBubble s = grid[i][x];
                s.add(type);
                return;
            }
           
        }
    }
}
