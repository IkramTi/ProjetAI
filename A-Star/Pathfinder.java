import java.util.List;
import java.util.ArrayList;

public class Pathfinder
{

    
    //V�rifier les points autour de l objet
    private static final int[][] PointsAroundToCheck={
            {-1,0,1,0},//les X possibles
            {0,-1,0,1}//les Y possibles
        };

  

   
    public static Point[] getPathInArray(int [][] Maze,int [][] PointsToCheck,Point [] StartPoints, Point [] EndPoints)
    {
        //StartPoints:La liste des points par lesquelles on peut commencer
        //EndPoints:La liste des points par lesquelles on peuts terminer
        List<Point> AllreadyChecked = new ArrayList(); // la liste des points d�ja visit�
        List<Point> toCheck = new ArrayList(); // la liste de tous les points qu'on peut visiter
        List<Point> nextCheck = new ArrayList(); // List of all Points that will be checked the next time:La liste de tous les points qu'on va �xplorer
        List<Point> AimPoints = new ArrayList(); // List of all Points, where the way could go to:La liste de tous les points des chemins possible
        for(int i=0; i < EndPoints.length || i < StartPoints.length;i++)//Si il existe les StartPoints et EndPoints-->on va les ajouter dans des listes
        {
            if(i < EndPoints.length)
            {
                toCheck.add(new Point(EndPoints[i].getX(),EndPoints[i].getY())); // adds the End Points to the next Check list, because it's easier to return the path, if it starts at the end
                //Ajouter EndPoints dand la liste toCheck
            }
            if(i < StartPoints.length)
            {
                AimPoints.add(new Point(StartPoints[i].getX(),StartPoints[i].getY())); // adds all Start Points to the Aim Point List
                //Ajouter StartPoints dans la liste  AimPoints
            }
        }
        while(toCheck.size()>0) // si on a des points � v�rifier on doit chercher un chemin
        {
            for(Point CheckPoint: toCheck) // On va parcourir tous les points dans la liste toCheck(� explorer)
            {
                AllreadyChecked.add(CheckPoint); //Ajouter le point � la liste des points d�ja visit�es
                int
                x = CheckPoint.getX(),  // r�cup�rer le X
                y = CheckPoint.getY();  //r�cup�rer le Y
                for(int i=0; i<PointsAroundToCheck[0].length && i<PointsAroundToCheck[1].length;i++) //V�rifier tous les positions,o� on peut se d�placer
                {
                    int 
                    x2=x+PointsAroundToCheck[0][i], // r�cup�rer le X
                    y2=y+PointsAroundToCheck[1][i];  //r�cup�rer le Y
                    if(y2>=0 && x2>=0 && y2<Maze.length && x2<Maze[y2].length && Maze[y2][x2]>0)
                    //V�rifier si les points X et Y appartiennent au l abyrinthe
                    {
                        if(FreeAt(x2,y2,Maze,PointsToCheck)) // v�rifier si le point peut se d�placer dans la nouvelle position -->appartient au labyrinthe
                        {
                            Point newPoint = new Point(x2,y2,Maze[y2][x2],CheckPoint); // cr�er une nouvelle point pour cette position
                            if(!PointLocationInList(AllreadyChecked,newPoint)) // V�rifier si ce point n'est pas deja visit�
                            {
                                nextCheck.add(newPoint); //Ajouter le point � la liste des points � explorer
                            }
                        }
                     
                            Point P = new Point(x2,y2,CheckPoint); // Cr�er un nouvel point dans cette position
                            if(PointLocationInList(AimPoints,P)) // Verifier s il appartient a la liste des chemins possibles
                            {
                                Point [] Path = {P}; // Cr�er un nouvel point pour l ajouter au chemin
                                for(Point P2 = P.getSavedPoint(); P2!=null; P2=P2.getSavedPoint()) // R�cup�rer tous les points et les ajouter dans le tableau de chemin
                                {
                                    Point [] PH = new Point[Path.length+1];
                                    for(int i2=0; i2<Path.length; i2++)
                                    {
                                        PH[i2]=Path[i2];
                                    }
                                    PH[Path.length]=P2;
                                    Path=PH;
                                }
                                return Path; // returner le chemin
                       
                        }
                    }
                }
            }
            toCheck.clear(); // Vider la liste toCheck parce que tous les points sont verifi�
            
            
            //Dans cette partie on va v�rifier si il existe d autres points quand peut ajouter au chemin et retirer les points qui ne sont pas utilisables
            while(toCheck.size()<=0 && nextCheck.size()>0) // la lsite toChek est vide mais la liste nextCheck contient qlq point quand peut visiter
            {
                List<Point> notRemove=new ArrayList(); // Creates a list for all points, that should not be removed from the nextCheck list
                for(Point P : nextCheck) // goes through all Points from the nextCheck list.
                {
                    P.addToValue(-1); // substract 1 of the Point value. The Point has from begin the number of his field
                    int val = P.getValue(); // gets the value of the Point
                    if(PointLocationInList(AimPoints,P)) // Checks if the Point is one of the AimPoints
                    {
                        Point [] Path = {P}; // Creates a new Point array for the Path
                        for(Point P2 = P.getSavedPoint(); P2!=null; P2=P2.getSavedPoint()) // Goes through all Points, that are useful and adds them to the array
                        {
                            Point [] PH = new Point[Path.length+1];
                            for(int i2=0; i2<Path.length; i2++)
                            {
                                PH[i2]=Path[i2];
                            }
                            PH[Path.length]=P2;
                            Path=PH;
                        }
                        return Path; // returns the path
                    }
                    if(val<=0) // checks, if the value of the Point is less equal to 0
                    {
                        if(!PointLocationInList(toCheck,P)) // checks if the Point is allready in the toCheck List
                            toCheck.add(P);
                    }
                    else
                        notRemove.add(P); // if the value of the Point is more than 0 the Point should not be removed
                }
                nextCheck=notRemove;
            }
        }
        return new Point[]{}; // si aucun chemin est trouv� il retourne un chemin vide
    }
//V�rifier si le point existe dans la liste des points
    private static boolean PointLocationInList(List<Point> PL,Point P)
    {
        for(Point P2 : PL)
            if(P2.sameLocation(P))
                return true;
        return false;
    }
//Cette m�thode permet de v�rifier si la nouvelle position appartient au l abyrinthe
    private static boolean FreeAt(int x, int y, int [][]Maze, int [][] PointsToCheck)
    {
        for(int i=0; i<PointsToCheck[0].length && i<PointsToCheck[1].length;i++)
        {
            int x2=x+PointsToCheck[0][i];
            int y2=y+PointsToCheck[1][i];
            if(x2<0 || y2<0 || y2>=Maze.length || x2>=Maze[y2].length || Maze[y2][x2]<=0)
                return false;
        }
        return true;
    }
      public static Point[] getPathInArray(int [][] Maze,int [][] PointsToCheck,Point StartPoint, Point EndPoint)
    {
        return getPathInArray(Maze,PointsToCheck,new Point[]{StartPoint},new Point[]{EndPoint});
    }

    public static Point[] getPathInArray(int [][] Maze,Point StartPoint, Point EndPoint)
    {
        return getPathInArray(Maze,new int[][]{{},{}},new Point[]{StartPoint},new Point[]{EndPoint});
    }

    public static Point[] getPathInArray(int [][] Maze,Point [] StartPoints, Point [] EndPoints)
    {
        return getPathInArray(Maze,new int[][]{{},{}},StartPoints,EndPoints);
    }
}
