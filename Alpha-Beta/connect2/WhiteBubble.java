import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WhiteBubble here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WhiteBubble  extends SActor
{
    private int gridx;
    private int gridy;
    
    private int type;
    // constructeur pour initialiser l'image et les positions
    public WhiteBubble(int x, int y) {
        setImage("bubble.png");
        gridx = x;
        gridy = y;
        type = 0; // le type soit = 0 (une bulle blanche) soit = piece (bulle colorée jouée)
    }
    
    public void act() {
         if (Greenfoot.mouseClicked(this)&&(Human.madeMove == false)) {
             Human.move = gridx;
             Human.madeMove = true; // 
         }
    }
    
    public int getType() {
        return type;
    }
    
    protected void add(int piece) throws RuntimeException {
        if (getType() != 0) { 
            throw new RuntimeException(gridx + ", " + gridy + 
                " already has a piece!");
        }
        getWorld().addObject(new Piece(piece), getX(), getY());
        this.type = piece;
    }
}
