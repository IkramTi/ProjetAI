import greenfoot.*;

import java.util.List;

public class PathFinderWorld  extends World
{
    private GreenfootImage BackgroundImage;
    private int MouseX=0,MouseY=0;

    public PathFinderWorld()//Le constructeur qui déssine note environnement:les imeges,les couleurs,...
    {    
        
        //Définir la taille de l abyrinthe+Déssiner l abyrinthe
        super(15*60, 12*60+90,1);
        setBackground("sand.jpg");
        BackgroundImage=new GreenfootImage(getBackground());
        BackgroundImage.fillRect(0,12*60,15*60,90);
        BackgroundImage.setColor(Color.GREEN);
        BackgroundImage.fillRect(0,0,60,60);
        BackgroundImage.setColor(Color.RED);
        BackgroundImage.fillRect(14*60,5*60,60,60);
        setBackground(new GreenfootImage(BackgroundImage));
       GreenfootImage person = new GreenfootImage("person.png");
        GreenfootImage house = new GreenfootImage("house-07.png");
        GreenfootImage StartGame = new GreenfootImage("ambulance.png");
        int SGW=StartGame.getWidth(),SGH=StartGame.getHeight();
        
        //Dessiner la partie des images:l ambulance,house
        GreenfootImage [][] Images =
            { 
                {
                }
                ,
                {
                    new GreenfootImage(60,60),
                    new GreenfootImage(60,60),
                    new GreenfootImage(60,60),
                },
                {
                   new GreenfootImage(SGW,SGH),
                    new GreenfootImage(SGW,SGH),
                    new GreenfootImage(SGW,SGH),
                }
            };
        for(int i=0; i<Images.length; i++)
        {
            for(int i2=0; i2<Images[i].length;i2++)
            {
                Images[i][i2].setColor(new Color((2-i2)*50,(2-i2)*100,(2-i2)*100));
                Images[i][i2].fill();
            }
        }
        for(int i=0; i<3; i++)
        {

            Images[1][i].drawImage(house,0,0);
            Images[2][i].drawImage(StartGame,0,0);
        }
 
        addObject(new Button(Images[1],1),15*60-15*25, 12*60+45);
        addObject(new Button(Images[2],2),15*15, 12*60+45);
    }

    public void act()
    {
        UpdateMouseLocation();//On va changer la location de l objet sur lequelle on a cliqué:Utiliser au moment oû on veut insérer les obstacles
    }
//La méthode call va etre appellé lorsque on va cliquer soit sur l ambulance soit sur house
    public void Call(int i)
    {
        switch(i)
        {

            case 1://le cas d un house
            addObject(new Builder(new house()),0,0); break;//on va ajouter l obstacle house
            case 2://le cas de l ambualnce
            addObject(new Men(FindWay()),30,30);//On appelle le constructeur  Men qui va inserer l'image dans la position 30,30
            break;
        }
    }
    public void UpdateMouseLocation()
    {
        MouseInfo M = Greenfoot.getMouseInfo();
        if(M==null)
            return;
            
            //La nouvelle position de l objet
        MouseX=M.getX();
        MouseY=M.getY();
    }

    public int getMouseX()
    {
        return MouseX;
    }

    public int getMouseY()
    {
        return MouseY;
    }

    public Point[] FindWay()//cette méthode va vérifier les cases vides et les cases qui contiennent des obstacles
                            //On va donner la valeur 1 pour les cases vides et 0 pour les cases qui contiennent des obstacles
    {
        int Image [][] = new int[getHeight()][getWidth()];//On va représenter Notre Grille
        for(int x = 0; x<getWidth()/60;x++)
            for(int y = 0; y<getHeight()/60-1;y++)
            {

                List<Obstacle>	AL = getObjectsAt(x*60+30 , y*60+30 , Obstacle.class);//LA liste des obstacles
                if(AL.size()<=0)//Si la case actuelle n est pas un obstacle
                {
                    Image[y][x]=1;//la case va  prendre la valeur 1
                }
                else//Sinon la case contient un objet obstacle
                {
                    if(getObjectsAt(x*60+30,y*60+30,house.class).size()>0)//On va vérifier -->Si l obtacles c est un objet "house" 
                    {
                        Image[y][x]=0;//la case va prendre la valeur 0
                    }
                
                }
        }
        return Pathfinder.getPathInArray(Image,new Point(0,0),new Point(14,5));//Finalement retourner un tableau des Point:Point source+destination+Le tableau Image 
    }

}
