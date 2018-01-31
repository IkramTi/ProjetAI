import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * X O grille GUI

 */
public class Board  extends World
{

    private Game game; 

    /**
     * Le constructeur
     * 
     */
    public Board()
    {    
        // Creation de World
        super(Game.SIZE, Game.SIZE, 100, true); 
        setBackground("cell.png");
        game = new Game(this);
    }

    /*
     * La methode pour la gestion de cliques des utilisateurs
     */
    public void act()
    {
        if(Greenfoot.mouseClicked(this)) {
            MouseInfo mouse = Greenfoot.getMouseInfo();
            boolean result = game.addNought(mouse.getX(), mouse.getY());
            if(result){
                game.act();
            }
        }
    }

    /**
     * Ajouter O dans la position x et y
     */
    public void addNought(int x, int y)
    {
        Nought nought = new Nought();
        addObject(nought, x, y);
    }

    /**
     * Ajouter  le X dans la position x et y
     */
    public void addCross(int x, int y)
    {
        Cross cross = new Cross();
        addObject(cross, x, y);
    }

}
