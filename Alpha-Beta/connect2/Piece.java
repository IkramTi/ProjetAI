import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Piece définit les bulles vert et rouges jouées
 * 
 * @author tiziazine ikram
 */
public class Piece  extends SActor
{
    public static final int RED = 1; 
    public static final int GREEN = 2;
    
    private int color;
    
    private GreenfootImage finalImage;
    private int radius=2;
    private final int finalRadius = 20;
    // Constructeur
    public Piece(int color) { // on va identifier piece par sa couleur
        this.color = color; // initialisation de la couleur 
        
        switch (color) { // si color=1 donc bulle rouge sinon bulle vert
            case 1: finalImage = new GreenfootImage("red.png");
                break;
            default: finalImage = new GreenfootImage("green.png");
        }
    }
    
    public void act() {
        if (radius != finalRadius) {
            enter();
        }
    }
    
    public void enter() {
        GreenfootImage g = new GreenfootImage(finalImage);
        g.scale(radius*2, radius*2);
        setImage(g);
        radius += 1;
        if (radius == 20) {
            setImage(finalImage);
        }
    }
    public boolean isRadiusComplete() {
        if (radius == 20) {
            return true;
        }
        return false;
    }
}
