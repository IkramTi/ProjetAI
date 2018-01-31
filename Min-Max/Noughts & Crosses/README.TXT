
PROJECT TITLE:              Noughts and Crosses
PURPOSE OF PROJECT:         Educational  
VERSION or DATE:            2011.04.16
HOW TO START THIS PROJECT:  Click 'Run' in the Greenfoot applet
AUTHORS:                    Nasir Ahmad (na200)
USER INSTRUCTIONS:          None


This is a typical Noughts and Crosses computer game with very simple (but unbeatable) AI.

I wrote this as part of revision of one of my AI modules.  I found the Greenfoot environment
very useful for this, as it takes care of most of the graphics part of things, allowing one to
focus on the actual logic of the program.

The game is currently configured for a 3x3 grid.  This can be easily changed in the program code (via
changing one of the constant values).  However, performance of the program will be greatly affected
due to the simplicity of the algorithm used to determine moves for the computer player.

The current implementation of 'Minimax' searches the whole gametree to determine potential winning moves, 
which is fine for the current configuration.  However, for larger problem-sizes, the search-space definitely 
needs to be reduced. Things like game-specific heuristics, alpha-beta pruning, etc. can be used to achieve this.

