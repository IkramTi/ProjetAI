/**
 * Game Status - Current game status
 * 
 */
public enum GameStatus  
{
    RUNNING {
        public String toString(){
            return "Gameplay in progress...";
        }
    }, 
    
    PLAYER_WIN {
        public String toString(){
            return "You won!";
        }        
    }, 
    
    COMPUTER_WIN{
        public String toString(){
            return "Computer won!";
        }
    }, 
    
    DRAW {
        public String toString(){
            return "Draw!";
        }
    };
}
