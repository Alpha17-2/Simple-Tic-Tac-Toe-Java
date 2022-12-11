package Game;
import java.util.*;

public class Game {
    private final char[][] board; // 2D array for creating a board to play
    private final Scanner sc = new Scanner(System.in);
    private String user1;
    private final Map<Integer,Boolean> visitedBoxes = new HashMap<>();
    private boolean draw = false;

    private char winner;

    public Game() {
        this.board = new char[3][3];
        for (int i=0;i<3;++i){
            Arrays.fill(board[i],' ');
        }
        for (int i=1;i<=9;++i){
            visitedBoxes.put(i,false);
        }
    }

    public char checkBoxes(int row,int column){
        boolean X1 = true, O1=true;
        // check current column
        for (int i=0;i<3;++i){
            if (board[row][i]!='x'){
                X1 = false;
            }
            if (board[row][i]!='o'){
                O1 = false;
            }
        }
        // check current row
        boolean X2 = true, O2=true;
        for (int i=0;i<3;++i){
            if (board[i][column]!='x'){
                X2=false;
            }
            if (board[i][column]!='o'){
                O2=false;
            }
        }
        if (X1 || X2) {
            return 'x';
        }
        else if (O1 || O2) {
            return 'o';
        }
        else {
            return ' ';
        }
    }

    public char checkDiagonals(){
        if (board[0][0]== board[1][1] && board[1][1] == board[2][2]){
            if (board[0][0]=='x'){
                return 'x';
            }
            else if (board[0][0]=='o'){
                return 'o';
            }
        }
        if (board[2][0]== board[1][1] && board[1][1] == board[0][2]){
            if (board[2][0]=='x'){
                return 'x';
            }
            else if (board[2][0]=='o'){
                return 'o';
            }
        }
        return ' ';
    }

    public char isGameWonByAnyPlayer(){
        // return x if User won the game
        // return o if CPU won the game
        // return - if no one won the game
        if (!Character.isSpaceChar(checkDiagonals())){
            return checkDiagonals();
        }
        for (int i=0;i<3;i++){
            for (int j=0;j<3;++j){
                char wonByPlayer = checkBoxes(i,j);
                if (wonByPlayer=='x' || wonByPlayer=='o'){
                    return wonByPlayer;
                }
            }
        }

        return ' ';
    }

    // Display the board with updated data
    public void displayBoard(){
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                System.out.print(" "+board[i][j]+" ");
                if (j!=2){
                    System.out.print('|');
                }
            }
            System.out.println();
            if (i!=2){
                System.out.println("-----------");
            }
        }
    }

    public Map<String,Character> isGameCompleted(){
        Map<String,Character> gameDetails = new HashMap<>();

        gameDetails.put("gameover",'n');
        gameDetails.put("winner",'d'); // draw
        if (!Character.isSpaceChar(isGameWonByAnyPlayer())){
            gameDetails.put("gameover",'y');
            gameDetails.put("winner",isGameWonByAnyPlayer());
        }
        int boxesAvailable = 0;
        for (boolean visited : visitedBoxes.values()){
            if (!visited){
                boxesAvailable++;
            }
        }
        if (boxesAvailable==0){
            gameDetails.put("gameover",'y');
        }
        return gameDetails;
    }

    public void displayBoxWithNumbers(){
        int number = 1;
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                System.out.print(" "+ number +" ");
                number++;
                if (j!=2){
                    System.out.print('|');
                }
            }
            System.out.println();
            if (i!=2){
                System.out.println("-----------");
            }
        }
    }

    public void displaySinglePlayerRules(){
        System.out.println("------------------------------------------------");
        System.out.println("    Rules For Single Player Mode:");
        System.out.println("------------------------------------------------");
        System.out.println("1. The game is played on a grid that's 3 squares by 3 squares. Initially all the boxes are empty");
        System.out.println();
        displayBoard();
        System.out.println();
        System.out.println("2. You are X, and CPU is O. Players take turns putting their marks only in empty squares.");
        System.out.println("3. To put your mark on any empty box simply enter the box number. For example, to put your mark at the top right corner box, just enter 3");
        System.out.println();
        displayBoxWithNumbers();
        System.out.println();
        System.out.println("4. The first player to get 3 of her marks in a row (up, down, across, or diagonally) is the winner.");
        System.out.println("5. When all 9 squares are full, the game is over. If no player has 3 marks in a row, the game ends in a tie.");
        System.out.println();
    }

    public void displaySinglePlayerModeDetails(){
        System.out.print("Please enter your name : ");
        user1 = sc.next();
        displaySinglePlayerRules();
        System.out.println("------------------------------------------------");
        System.out.println("    Starting game between "+user1+" and CPU  ");
        System.out.println("            All the best !!    ");
        System.out.println("------------------------------------------------");

    }

    public void playMultiPlayer(){
        System.out.print("Please enter your name : ");
        user1 = sc.next();
        System.out.println("Starting game between "+user1+" and CPU");
    }

    public void markBoxAt(int mark,char c){
        int runner=1;
        for (int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if (runner==mark){
                    board[i][j] = c;
                }
                runner++;
            }
        }
    }

    public void startSinglePlayerGame() {
        System.out.println("----------------------------------------------------------------------------");
        System.out.print("  Get ready for the toss  ");
        try {
            for(int i=0;i<5;i++){
                System.out.print(".");
                Thread.sleep(400);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println();
        System.out.println("----------------------------------------------------------------------------");

        System.out.println();
        System.out.println();
        int max = 1,min = 0;
        int turn = (int) (Math.random()*(max-min+1)+min);
        if (turn==0){
            System.out.println("----------------------------------------------------------------------------");
            System.out.println("    Congratulations!! You won the toss. You can start the game now");
            System.out.println("----------------------------------------------------------------------------");
        }
        else {
            System.out.println("----------------------------------------------------------------------------");
            System.out.println("    Oops! You lost the toss. CPU will start the game");
            System.out.println("----------------------------------------------------------------------------");
        }
        System.out.println();
        while (true){
            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            displayBoard();
            System.out.println();
            if (turn%2==0){
                // user turn (x)
                do {
                    System.out.println("------------------------------------");
                    System.out.print("     "+user1+"'s turn : ");
                    int markAt = sc.nextInt();
                    System.out.println("------------------------------------");
                   if (visitedBoxes.containsKey(markAt) && !visitedBoxes.get(markAt)){
                       visitedBoxes.put(markAt,true);
                       markBoxAt(markAt,'x');
                       break;
                   }
                }
                while (true);

            }
            else {
                // cpu turn (o)
                List<Integer> boxesAvailable = new ArrayList<>();

                for (int box : visitedBoxes.keySet()){
                    if (!visitedBoxes.get(box)){
                        boxesAvailable.add(box);
                    }
                }
                int low = 0, high = boxesAvailable.size()-1;
                int markAt = boxesAvailable.get((int) (Math.random()*(high-low+1)+low));
                System.out.println("------------------------------------");
                System.out.println("    CPU's turn : "+markAt);
                System.out.println("------------------------------------");
                markBoxAt(markAt,'o');
                visitedBoxes.put(markAt,true);
            }
            Map<String,Character> gameDetail = isGameCompleted();
            if (gameDetail.get("gameover")=='y') {
                if (gameDetail.get("winner")=='d'){
                    draw=true;
                }
                else {
                    winner = gameDetail.get("winner");
                }
                displayBoard();
                break;
            }
            turn++;
        }
    }

    public void declareWinner(){
        System.out.println("\n\n");
        System.out.println("----------------------------------------------------------------");
        if (draw){
            System.out.println("    ******  Match draw  ******  ");
        }
        else {
            if (winner=='x'){
                System.out.println("    *****   Congrats "+user1+"! You won the Tic Tac Toe game    *****    ");
            }
            else {
                System.out.println("    *****   CPU won the Tic Tac Toe game    *****    ");
            }
        }
        System.out.println("----------------------------------------------------------------\n\n");
    }

    public void resetBoard(){
        for (int i=0;i<3;++i){
            Arrays.fill(board[i],' ');
        }
        for (int i=1;i<=9;++i){
            visitedBoxes.put(i,false);
        }
    }


    public void play() throws InterruptedException {
        System.out.println("------------------------------------");
        System.out.println("    Welcome to Tic Tac Toe game ");
        System.out.println("------------------------------------\n");
        System.out.println("Please select game mode :-\n");
        System.out.println("1 Single Player (vs CPU)");
        System.out.println("2 Multi Player (vs Friend)");
        System.out.println("3 Exit");
        System.out.println("------------------------------------");
        System.out.print("Your choice : ");

        int choice = sc.nextInt();
        while (choice!=3){
            switch (choice) {
                case 1 -> {
                    displaySinglePlayerModeDetails();
                    Thread.sleep(3500);
                    startSinglePlayerGame();
                    declareWinner();
                }
                case 2 -> {

                }
                default -> System.out.println("Enter a valid game mode");
            }
            System.out.println("------------------------------------");
            System.out.println("Please select game mode :-");
            System.out.println("------------------------------------");
            System.out.println("1 Single Player (vs CPU)");
            System.out.println("2 Multi Player (vs Friend)");
            System.out.println("3 Exit");
            System.out.println("------------------------------------");
            System.out.print("Your choice : ");
            choice = sc.nextInt();
            resetBoard();
        }

    }

}
