import greenfoot.*;
import java.util.*;

/**
 * X O game logic + AI

 */
public class Game extends Actor 
{
    public static final int SIZE = 3; // la taille de jeu 3*3
    public static final char EMPTY_CELL = ' ';
    public static final char NOUGHT = 'o';
    public static final char CROSS = 'x';
    private Board boardGUI;
    private char[][] board;
    private GameStatus status;
    private int moveCount = 0;

    /**
     * Le constructeur pour initialiser les differents objets de la classe
     */
    public Game(Board boardGUI)
    {
        this.boardGUI = boardGUI;
        board = new char[SIZE][SIZE];// tableau des char :o et x
        newGame();
    }
    
    /**
     * Commencer le jeu:RUN
     */
    public void newGame()
    {
        clearBoard();
        status = GameStatus.RUNNING;
    }    
        
    /**
     * 
     * Changer le status de jeu a chaque tour:homme et computer
     */
    public void act()
    {
        //tour de l homme
        updateStatus();    //changer status    
        if(finished()){     // si c est fini retourner gameOver()
            gameOver();
            return;
        }
        
        //tour de computer
        addCross();        
        updateStatus(); //changer status  
        if(finished()){ // si c est fini retourner gameOver()
            gameOver();
            return;
        }
    }
    
    /**
     * 
     * Ajouter O dans la case adéquate qui comme parametre X et Y
     * Si c est bien ajouter la fonction retourne true sinon false
     */
    public boolean addNought(int x, int y)
    {
       if(!finished() && isValidMove(board, x, y)){
           board[x][y] = NOUGHT;
           boardGUI.addNought(x, y);
           return true;
        }
        
        return false;
    }
            
                    

    
    /**
     * Determiner si le jeu est terminé?
     */
    private boolean finished()
    {
        return status != GameStatus.RUNNING;
    }
    
    /**
     * Ajouter X dans la case adéquate-Le joueur est le computer
     */
    private boolean addCross(int x, int y)
    {
       if(!finished() && isValidMove(board, x, y)){
           board[x][y] = CROSS;
           boardGUI.addCross(x, y);
           return true;//retourner true si c est bien ajouter
        }
        
        return false;// sinon false
    }    
        
    /**
     *Methode Jeu terminé
     */
    private void gameOver()
    {
        Greenfoot.delay(5);//5 c est le nombre Max de tour
        int option = javax.swing.JOptionPane.showConfirmDialog(
            null, "Try again?", status.toString(), javax.swing.JOptionPane.YES_NO_OPTION);//Message pour rejouer: retourne 0 si oui
        if(option == 0){// creer une nouvelle tour
            clearBoard();
            newGame();
        }
    }
    
    /**
     * Changer le status apres chaque itération
     * Vérifier si le jeu est términer ou biel l 'un des 2 a gagné
     */
    private void updateStatus()
    {
        status = getGameStatus(board);// getGameStatus est déclarer en dessous
    }
            
    /**
     * Detérminer si la case est valide
     * 
     */
    private boolean isValidMove(char[][]board, int x, int y)
    {
        return board[x][y] == EMPTY_CELL; // la seule case valide est la case vide
    }        
    
    /**
     * Determiner le status de jeu si c est fini ou non
     * si toutes les cases sont remplies ,ou bien l'un des 2 a gagné
     */
    private GameStatus getGameStatus(char[][] board)
    {
        if(gameWon(board, NOUGHT)){
            return GameStatus.PLAYER_WIN;
        }
        else if(gameWon(board, CROSS)){
            return GameStatus.COMPUTER_WIN;
        }
        else if(gameDrawn(board)){
            return GameStatus.DRAW;
        }
        else{
            return GameStatus.RUNNING;        
        }
    }

    /**
     *Dessiner le jeu
     */
    private boolean gameDrawn(char[][] board)
    {
        
        for(int i = 0; i < SIZE; i++){
            for(int j = 0; j < SIZE; j++){
                if(board[j][i] == EMPTY_CELL){
                    return false;
                }
            }
        }
        return true;        
    }
    
    /**
     * Detérminer qui a gagné le jeu :le joueur avec x ou bien o
     */
    private boolean gameWon(char[][] board, final char MARKER)
    {
        boolean diagonalFound = true, reverseDiagonalFound = true; 
        boolean colFound, rowFound;
        
        //Vérifier les colonnes et les lignes
        for(int i = 0; i < SIZE; i++)
        {
            colFound = true; rowFound = true;
            for(int j = 0; j < SIZE; j++){
                colFound = colFound && board[j][i] == MARKER;
                rowFound = rowFound && board[i][j] == MARKER;
            }
            //la colonnes ou bien la ligne est trouvé-->retourner true
            if(colFound || rowFound){
                return true;
            }
            
            //Sinon chercher le diagonale
            diagonalFound = diagonalFound && board[i][i] == MARKER;
            reverseDiagonalFound = reverseDiagonalFound && board[SIZE - 1 - i][i] == MARKER;
        }
        
        //le diagonale est trouvé-->retourner true
        if(diagonalFound || reverseDiagonalFound){
            return true;
        }
        
        return false;
    }
            
    /**
     *Ajouter X utiliser par l'ordinateur
     */
    private void addCross()
    {
        Point point = getNextMove();
        board[point.getX()][point.getY()] = CROSS;
        boardGUI.addCross(point.getX(), point.getY());        
    }    
            
   
    /**
     *clearBoard: Pour vider tous les cases
     */
    private void clearBoard()
    {
        for(int i = 0; i < SIZE; i++){
            for(int j = 0; j < SIZE; j++){
                board[j][i] = EMPTY_CELL;
            }
        }
        boardGUI.removeObjects(boardGUI.getObjects(null));
    }    
    
    /**
     * Prints board to console - useful for tesing/debugging
     */
    private void printBoard(char[][] board)
    {
        for(int i = 0; i < SIZE; i++){
            System.out.print("|");
            for(int j = 0; j < SIZE; j++){
                System.out.print(board[j][i] + "|");
            }
            System.out.println();
        }
    }    
    
    //=============================================== AI Section ============================================//
    
    
         // Idée: À chaque tour, choisir l’action menant à la plus grande valeur minimax.
         // Cela donne la meilleure action optimale (plus grand gain) contre un joueur optimal. 
        
    /**
     * Retourner la prochaine action jouée par le computer
     */
    private Point getNextMove()
    {        
        Node node = minimax(board);
        return node.getMove();
    }
    
    /**
     *Déterminer la meilleur action pour  X : ç-à-d le joueur computer par l algo MinMax
     */
    private Node minimax(char[][] board)
    {
        List<Node> moves = nextCandidateMoves(board, CROSS);//La liste des actions possibles
        
        for(Node move : moves){
            move.setScore(minimaxValue(move, false, 1));//Parcourir La liste des actions possible pour trouver la meilleur action
        }
        
        return Collections.max(moves);//Retourner la valeur trouvé
    }
    
    /**
     * Depth-first minimax score calculation
     * @param current Node to be determined
     * @param switchPlayer Flag used in recursive calls (in gametree generation)
     * @param depth Current tree depth (useful for testing)
     */
    private int minimaxValue(Node current, boolean switchPlayer, int depth)
    {
        //Get successor nodes of current node
        List<Node> successors = nextCandidateMoves(current.getBoard(), switchPlayer ? CROSS : NOUGHT);
        
        //Base case: 
        //We have reached a terminal node or a 'leaf' in the game-tree. Determine outcome.
        if(successors == null){
            if(gameWon(current.getBoard(), CROSS)){
                return 1;
            }
            else if(gameWon(current.getBoard(), NOUGHT)){
                return -1;
            }
            else if(gameDrawn(current.getBoard())){
                return 0;
            }
        }
        
        //Recursive case, analyse successor nodes
        //by recursively calling minimaxValue, switching players at the next level
        List<Integer> scores = new ArrayList<Integer>();
        for(Node child : successors){
            int score = minimaxValue(child, !switchPlayer, depth + 1);
            scores.add(score);
        }
        //Determine parent score from children scores
        if(switchPlayer){
            return Collections.max(scores);
        }
        else{
            return Collections.min(scores);
        }
    }
        
    /**
     * Déterminer la listes des actions possibles
     * retourne null si le jeu est términé
     */
    private List<Node> nextCandidateMoves(char[][] current, final char MARKER)//@param MARKER chaine de caractère prend 2 valeur soit NOUGHT ou CROSS (O ou X)
    {
        //Null--> jeu terminer-->pas d'actions possibles
        if(getGameStatus(current) != GameStatus.RUNNING){
            return null;
        }
        
        //Determiner la liste des mouvements possibles(Successeur)
        List<Node> moves = new LinkedList<Node>();
        for(int i = 0; i < SIZE; i++){
            for(int j = 0; j < SIZE; j++){
                if(isValidMove(current, i , j)){//vérifier si c est valide-->ç-à-d Vide
                    char[][] move = getBoardCopy(current);//retourner une copie de la configuration actuelle de la grille
                    move[i][j] = MARKER;//si  NOUGHT ou CROSS (O ou X)
                    moves.add(new Node(move, new Point(i, j)));//Ajouter le noeud à la liste
                }
            }
        }
        
        return moves;//retourner la list
    }
    
    /**
     * //retourner une copie de la configuration actuelle de la grille
     */
    private char[][] getBoardCopy(char[][] board)
    {
        char[][] copy = new char[SIZE][SIZE];
        for(int k = 0; k < SIZE; k++){
            for(int l = 0; l < SIZE; l++){
                copy[k][l] = board[k][l];
            }
        }      
        return copy;
    }
    
    
    /**
     * 
     * La classe Node :pour pouvoir représenter la grille comme une arbre des noeuds
     */
    class Node implements Comparable<Node>
    {
        private char[][] board;
        private Integer score;
        private Point move;
        
        /**
         * Constructeur
         */
        Node(char[][] board, Point move)
        {
            this.board = board;
            this.move = move;
        }
    
        /**
         * **donner un score
         */
        public void setScore(int score)
        {
            this.score = score;
        }
        
        /**
         * retourner la configuration de la grille
         */
        public char[][] getBoard()
        {
            return board;
        }
        
        /**
         * retourner l'action
         */
        public int getScore()
        {
            return score;
        }
        
        /**
         * retourner l 'action
         */
        public Point getMove()
        {
            return move;
        }
        
        /**
         * Utiliser pour trouver la valeur min/max
         */
        public int compareTo(Node other){
            return score.compareTo(other.score);
        }   
    }
    
}//end of Game class


