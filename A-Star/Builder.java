import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)



public class Builder  extends Actor
{
    private int MX=0,MY=0;
    private Obstacle ToBuild;
    private boolean Initialized=false;
    
    //le constructeur pour l initialisation 
    public Builder(Obstacle A)
    {
        setImage(A.getImage());//Récupérer l image sur laquelle on a cliqué
      
        ToBuild=A;//initialisation d'un obstacle
    }

    public void act()
    {
     
        MouseInfo M = Greenfoot.getMouseInfo();//Récupérer des infos sur le clique-->ç-à-d prendre une copie de l image sur l aquelle on a cliqué
        if(M!=null)
        {
            MX=M.getX();
            MY=M.getY();
        }
        setLocation(MX,MY);
        if(Greenfoot.mouseClicked(null))//Vérifier si on a cliquer sur une images(Button) //Apres déplacer l objet cliqué dans la case sur laquelle on va cliquer
        {
         
                if(!((getX()/60==0 && getY()/60==0) || (getX()/60==14 && getY()/60==5) || getY()>=12*60))
                {
               
                    getWorld().addObject(ToBuild,getX(),getY());//Ajouter l objet dans la case sur laquelle on a cliquer
                    Obstacle A = ToBuild.duplicate();
                    setImage(A.getImage());//L image de l objet
                
                    ToBuild=A;
                }
             
         
          
        }
    }
//Déplacer l objet dans la nouvelle position
    public void setLocation(int x,int y)
    {
        super.setLocation(60*(x/60)+30,60*(y/60)+30);
    }
}
