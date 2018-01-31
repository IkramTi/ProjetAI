import greenfoot.*;
import java.util.List;

//L ambulance
public class Men  extends Methods
{
    private Point[] Path;
    private int PathPoint=0;

    public Men(Point[] Path)
    {
        this.Path=Path;
        setImage("ambulance.png");
    }


    public void act()
    {
      
        int Moves=10;//Lier au speed=vitesse
        for(int i=0; i<Moves; i++)
        {
            double Speed=4;
            //Faire disparaitre l ambulance quand elle va arriver au destination
            if(PathPoint>=Path.length)
            {
                if(getObjects(Men.class).size()>0)
                    RemoveObject();
                return;
            }

           //Déplacement de l ambulance
            move(Speed/(double)Moves,getDegreesTo(new Point(Path[PathPoint].getX()*60+30,Path[PathPoint].getY()*60+30)));
            if(new Point(getX(),getY()).sameLocation(new Point(Path[PathPoint].getX()*60+30,Path[PathPoint].getY()*60+30)))
            {
                setLocation(Path[PathPoint].getX()*60+30,Path[PathPoint].getY()*60+30);
                PathPoint++;
            }
        }
    }
}
