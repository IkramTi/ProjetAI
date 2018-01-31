import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
 
//Button-->Image Ambulance + les obstacles

public class Button  extends Actor
{
    private GreenfootImage [] Image=new GreenfootImage[3];
    private int ButtonCall;
    private double FadeNumber,FadeSpeed;
    private boolean onThis=false,Fade=false;
    public Button(GreenfootImage [] Images,int ButtonCall)
    {
        if(Images.length>=3)
        {
            Image[0]=Images[0];
            Image[1]=Images[1];
            Image[2]=Images[2];
        }
        else
        if(Images.length==2)
        {
            Image[0]=Images[0];
            Image[1]=Images[1];
            Image[2]=new GreenfootImage(Images[1]);
        }
        else
        if(Images.length==1)
        {
            Image[0]=Images[0];
            Image[1]=new GreenfootImage(Images[0]);
            Image[2]=new GreenfootImage(Images[0]);
        }
        else
        {
            Image[0]=getImage();
            Image[1]=new GreenfootImage(getImage());
            Image[2]=new GreenfootImage(getImage());
        }
        setImage(Image[0]);
        this.ButtonCall=ButtonCall;
    }

  public boolean MouseOnThis()
    {
        int x = getMouseX();
        int y = getMouseY();
        GreenfootImage m = new GreenfootImage(getImage());
        int w = m.getWidth();
        int h = m.getHeight();
        if(h>w)
            w=h;
        GreenfootImage i = new GreenfootImage(w*2,w*2);
        i.drawImage(m,w-m.getWidth()/2,w-m.getHeight()/2);
        i.rotate(getRotation());
        x=x-(getX()-i.getWidth()/2);
        y=y-(getY()-i.getHeight()/2);
        if(x>=i.getWidth())
            x=i.getWidth()-1;
        if(y>=i.getHeight())
            y=i.getHeight()-1;
        if(x<0)
            x=0;
        if(y<0)
            y=0;
        return i.getColorAt(x,y).getAlpha()>10;
    }
    
    public int getMouseX()
    {
        return ((PathFinderWorld)getWorld()).getMouseX();
    }

    public int getMouseY()
    {
        return ((PathFinderWorld)getWorld()).getMouseY();
    }
    
    public void act()
    {
        getImage().setTransparency(255);
        onThis=MouseOnThis();
        if(onThis)
        {
            if(getImage()!=Image[2])
                setImage(Image[1]);
            if(Greenfoot.mousePressed(null)||Greenfoot.mouseDragged(null))
                setImage(Image[2]);
        }
        else
            setImage(Image[0]);
        if(Greenfoot.mouseDragEnded(this))
        {
            if(onThis)
                setImage(Image[1]);
            else
                setImage(Image[0]);
        }
        if(onThis && Greenfoot.mouseClicked(this))
        {
            ((PathFinderWorld)getWorld()).Call(ButtonCall);
            setImage(Image[1]);
            if(Fade)
            {
                FadeNumber+=FadeSpeed;
                if(FadeNumber>=255)
                    FadeNumber=255;
                getImage().setTransparency((int)FadeNumber);
                if(FadeNumber>=255)
                    Fade=false;
            }
            return;
        }
    
    }
}
