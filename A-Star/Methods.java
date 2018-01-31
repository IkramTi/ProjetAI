import greenfoot.*;
import java.util.List;

public abstract class Methods  extends Actor
{
    private double Rotation=0;
    private double PosX;
    private double PosY;
    public final static int UP=270,Up=270,up=270,Right=0,RIGHT=0,right=0,LEFT=180,Left=180,left=180,DOWN=90,Down=90,down=90;

  
 
    
    public void removeObjects(List a)
    {
        getWorld().removeObjects(a);
    }




 

  

    public static int RandomPosOrNeg()
    {
        if(Greenfoot.getRandomNumber(2)==1)
            return 1;
        return -1;
    }

    public double getExactX()
    {
        return PosX;
    }

    public double getExactY()
    {
        return PosY;
    }

    public void move(double Speed)
    {
        double a = Math.toRadians(getExactRotation());
        double x = getExactX() + Math.cos(a) * Speed;
        double y = getExactY() + Math.sin(a) * Speed;
        setLocation(x, y);
    }

    public void move(int Speed)
    {
        move((double)Speed);
    }

    public void move(double Speed, double Direction)
    {
        double a = Math.toRadians(Direction);
        double x = getExactX() + Math.cos(a) * Speed;
        double y = getExactY() + Math.sin(a) * Speed;
        setLocation(x, y);
    }

    public void setLocation(double x, double y)
    {
        PosX=x;
        PosY=y;
        super.setLocation((int)x,(int)y);
    }

    public void setLocation(int x, int y)
    {
        setLocation((double)x,(double)y);
    }

   
    
    public double getDegreesTo(Point P)
    {
        return getDegreesTo(P.getExactX(),P.getExactY());
    }
    
    public double getDegreesTo(int x, int y)
    {
        return getDegreesTo((double)x,(double)y);
    }

    public double getDegreesTo(double x,double y)
    {
        return Math.toDegrees(Math.atan2(y-getExactY(),x-getExactX()));
    }


  
    
    public void setRotation(int Degrees)
    {
        setRotation((double)Degrees);
    }

    public GreenfootImage getBackground()
    {
        return getWorld().getBackground();
    }

    public boolean atWorldEdge()
    {
        return getY()>=getWorld().getHeight()-1 || getY()<=0 || getX()>=getWorld().getWidth()-1 || getX()<=0;
    }

    public boolean outOfWorld()
    {
        return getY()>=getWorld().getHeight()+getImage().getWidth()/2-1 ||
        getY()<=-getImage().getHeight()/2 ||
        getX()>=getWorld().getWidth()+getImage().getHeight()/2-1 ||
        getX()<=-getImage().getWidth()/2;
    }

    public boolean outOfWorld(int HowFar)
    {
        return getY()>=getWorld().getHeight()+HowFar-1 ||
        getY()<=-HowFar ||
        getX()>=getWorld().getWidth()+HowFar-1 ||
        getX()<=-HowFar;
    }

    public boolean atWorldsEdge()
    {
        return atWorldEdge();
    }

    public void setRotation(double Degrees)
    {
        Rotation=Degrees;
        super.setRotation((int)Degrees);
    }

    public void addObject(Methods A, int x, int y)
    {
        addObject(A,(double)x,(double)y);
    }

    public void addObject(Methods A, double x, double y)
    {
        getWorld().addObject(A,0,0);
        A.setLocation(x,y);
    }

    public List getObjects(Class clss)
    {
        return getWorld().getObjects(clss);
    }

    public int getRotation()
    {
        return (int)Rotation;
    }

    public double getExactRotation()
    {
        return Rotation;
    }

    public void RemoveObject(Actor A)
    {
        getWorld().removeObject(A);
    }

    public void RemoveObject()
    {
        RemoveObject(this);
    }

    public void setLocationAroundPoint(Point P,double Distance,double Rotation)
    {
        setLocationAroundPoint(P.getX(),P.getY(),Distance,Rotation);
    }

    public void setLocationAroundPoint(double xMiddle,double yMiddle,double Distance,double Rotation)
    {
        Rotation = Math.toRadians(Rotation);
        double x = xMiddle + Math.cos(Rotation) * Distance;
        double y = yMiddle + Math.sin(Rotation) * Distance;
        setLocation((int)Math.round(x),(int)Math.round(y));
    }
}
