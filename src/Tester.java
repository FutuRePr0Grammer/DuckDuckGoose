//Duck Duck Goose game
//by David Rochon III
//due Tues 9/28/2021
//Note: I commented out the print statement for the winner here because I made a better print statement
    //in the method to choose winner. It says a different message depending on who wins!

public class Tester {
    public static void main(String[] args) {
        String[] names = {"Alice", "Sally", "Robert", "Joan", "Polly", "Tim", "Stan"};
        DuckDuckGoose game1 = new DuckDuckGoose(names); //Notice player names are included in constructor
        int roundNumber = 1;
        int totalRounds = 10;
        while(roundNumber <= totalRounds) {
            System.out.println("------ Round Number " + roundNumber + " ------");
            System.out.println("Before playing:");
            game1.listPlayers(); //Expected to list the players with a Goose selected
            String winner = game1.playRound();
            //System.out.println(winner + " won ");
            System.out.println("After playing:");
            game1.listPlayers();//Should reflect the results of the game.
            roundNumber++;
        }
    }
}
