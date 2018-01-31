/**
 * Write a description of class Point here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Point  
{
    private double x,y;
    private int Value=0;
    private Point SavedPoint;

    public Point(double x, double y)
    {
        this.x=x;
        this.y=y;
    }

    public Point(double x, double y, int Value)
    {
        this(x,y);
        this.Value=Value;
    }

    public Point(double x, double y, Point Point_to_save)
    {
        this(x,y);
        this.SavedPoint=Point_to_save;
    }

    public Point(double x, double y, int Value, Point Point_to_save)
    {
        this(x,y,Point_to_save);
        this.Value=Value;
    }

    public Point(double x, double y, Point Point_to_save, int Value)
    {
        this(x,y,Value,Point_to_save);
    }

    public int getValue()
    {
        return Value;
    }

    public void addToValue(int What)
    {
        this.Value+=What;
    }

    public void setValue(int Value)
    {
        this.Value=Value;
    }

    public int getX()
    {
        return (int) x;
    }

    public int getY()
    {
        return (int) y;
    }

    public double getExactX()
    {
        return x;
    }

    public double getExactY()
    {
        return y;
    }

    public void setLocation(double x,double y)
    {
        this.x=x;
        this.y=y;
    }

    public void setSavedPoint(Point newPoint)
    {
        this.SavedPoint=newPoint;
    }

    public Point getSavedPoint()
    {
        return SavedPoint;
    }

    public boolean sameLocation(Point P)
    {
        return x==P.x && y==P.y;
    }
}
