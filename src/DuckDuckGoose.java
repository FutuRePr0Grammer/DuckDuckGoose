//David Rochon III
//note: I intentionally do not remove or add any elements to the list for goose
    //I felt this was inefficient vs. just checking if the current goose is the current duck
    //from what we learned in class, I've been trying to count steps, and this takes less steps
    //if I'm correct!
//import random library to produce a pseudorandom integer
import java.lang.Math;

public class DuckDuckGoose<E> {

    //boolean for tracking whether or not it is the first round or not
    boolean isFirstRound;
    //initializing instance variable, an array of strings to hold the names, populating elements as null
    private String[] names;
    //node to store head sentinel
    private Node<E> head;
    //node to store tail sentinel
    private Node<E> tail;
    //boolean variable to track if the linked list has been created
    private boolean doesListExist;
    //node to store the goose chosen
    private Node<E> currentGoose;
    //variable to store if a goose exists (first round one will not exist until one is chosen)
    private boolean doesGooseExist;
    //variable to keep track of the size of the linked list (number of players)
    private int size = 0;
    //node to keep track of the next duck for chooseDuck()
    private Node<E> nextDuck;


    //constructor for class. parameter is the list of names of the children
    public DuckDuckGoose(String[] names){
        //assigns the names passed in param to be the names used in the class by instance variable "names"
        this.names = names;
        //initializes head and tail nodes and sets current element, prev, and next all to null
        head = new Node<>(null, null, null);
        tail = new Node<>(null, head, null);
        head.setNext(tail);
        //sets doesListExist to False and gooseExists to false
        doesListExist = false;
        //sets currentGoose to null and doesGooseExist to false
        currentGoose = new Node<>(null, null, null);
        doesGooseExist = false;
        //sets up a node to store the next person in line the goose will have to tap and say "duck"
        nextDuck = new Node<>(null, null, null);
        isFirstRound = true;
    }

    //method to create the linked list using the names passed from Tester Class and to choose fist goose
    private void makeLinkedList(){
        //iterates over the array of names, creates nodes with these names as elements, adds nodes to list
        for(int i = 0; i <= names.length - 1; i++){
            //if it is the first element of the array
            if(i == 0){
                addNode(names[i], head, head.getNext());
            }
            //if it is the last element in the array (always add next node as last node in list of course)
            else if(i > 0 && i <= names.length - 1){
                addNode(names[i], tail.getPrev(), tail);
            }

        }

        //randomly chooses a goose if doesGooseExist is false (first round)
        if(doesGooseExist == false){
            //sets minimum number the random number can to the first person in the list
            int min = 1;
            //sets maximum number random number can choose to the last person in the list
            int max = size;
            //sets the range of numbers that can be chosen. Adds one to include the last number
            //i.e. size = 10, so 10 - 1 = 9 numbers in the range, add one to include the 10th number
            int range = (max - min) + 1;
            //choose a random number using the parameters designated above
            int randomGoose = (int)(Math.random() * range) + min;

            //will use random number to choose the person in that position of the list as the goose
            //creating temp Node to traverse the list, starts pointing to the first elem of the list
            Node<E> traversalNode = new Node<>(null, null, null);

            traversalNode = head.getNext();
            //traverses the list and sets the goose for the first round based on the randomGoose number
            for(int i = 1; i < randomGoose; i++){
                //sets traversal node to point to the next node until it points to the goose
                traversalNode = traversalNode.getNext();
            }
            //sets current goose to be the name stored in the traversalNode
            currentGoose = traversalNode;

            doesGooseExist = true;
        }
    }


    public String playRound(){
        //generate a pseudorandom number up to arbitrary choice: # of players (will use to choose ducks)
        //to make sure goose doesn't keep choosing ducks hundreds of times before saying goose,
        //limited it to 20
        int min = 1;
        int max = 10;
        int range = (max - min) + 1;
        int randomChoiceCount = (int)(Math.random() * range) + min;


        //call chooseDuck() as many times as randomly designated above
        for(int i = 1; i <= randomChoiceCount; i++){
            chooseDuck();
        }

        //choose a goose after choosing ducks a pseudorandom number of times
        chooseGoose();

        //passes the nextDuck and the currentGoose to the footrace to declare a winner of the game
        String raceWinner = footRace(currentGoose.getElement(), nextDuck.getElement());

        //print winner of race
        //if currentGoose won, print that nextDuck is now goose
        if(raceWinner == currentGoose.getElement()){
            System.out.println(raceWinner + " won the footrace! " + nextDuck.getElement() +
                    " is now Goose");
            //set the currentGoose to now be nextDuck
            currentGoose = nextDuck;
        }
        //otherwise, if nextDuck won, currentGoose is still the goose. currentGoose remains the same
        else{
            System.out.println(raceWinner + " won the footrace! " + currentGoose.getElement() +
                    " is still Goose");
        }

        return raceWinner;
    }


    public void listPlayers(){
        //calls method to create list if it is the first round (doesListExist = false)
        if(doesListExist == false){
            makeLinkedList();
            doesListExist = true;
        }


        //node to traverse the list and print the current player
        Node<E> player = head.getNext();

        //print the current goose
        System.out.println("Current Goose: " + currentGoose.getElement());

        System.out.println("Current Ducks: ");

        //prints the players of the game (elements of the linked list)
        for(int i = 1; i <= size; i++){
            //if the player is the goose, do not print, go to the next player
            if(player.getElement() == currentGoose.getElement()){
                player = player.getNext();
                continue;
            }
            //print the player (a duck)
            System.out.println(player.getElement());
            //set player to be the next element in the list (next player) for next iteration
            player = player.getNext();
        }


    }

    //will allow goose to choose to say duck a pseudorandom amount of times
    private void chooseDuck(){

        //if nextDuck is beyond the last player, nextDuck = the first player again
        if(nextDuck == tail || nextDuck == null){
            nextDuck = head.getNext();
        }
        //otherwise, the next player is the player after the Goose
        // however, if second round or beyond, do not start from child after goose again, skip else if
        if (isFirstRound == true){
            //if currentDuck.getNext = tail, must start first round at the first player (head.getNext())
            if(currentGoose.getNext() == tail){
                nextDuck = head.getNext();
            }
            else{
                nextDuck = currentGoose.getNext();
            }
            isFirstRound = false;
        }
        //if the nextDuck is the currentGoose, that player cannot redeclare themselves duck or goose,
            //so skips them of course, goes to next person in list
        if(currentGoose.getElement() == nextDuck.getElement()) {
            nextDuck = nextDuck.getNext();
            //if the goose is the last member of the list, it will point to null when getNext called
            //sets this to not happen, so if nextDuck is now null or tail, go back to head of list
            if(nextDuck == null || nextDuck == tail){
                nextDuck = head.getNext();
            }
        }
        //current goose will go around saying duck to next person in the group
        System.out.println(currentGoose.getElement() + " yelled Duck as they tapped " +
                nextDuck.getElement());


        nextDuck = nextDuck.getNext();


    }

    //will choose the person the goose will race
    private void chooseGoose(){

        //if nextDuck is null, set it to the first player
        if(nextDuck.getElement() == null){
            nextDuck = head.getNext();
        }
        //if nextDuck is the current goose, skip to the next player
        if(currentGoose.getElement() == nextDuck.getElement()){
            nextDuck = nextDuck.getNext();
            //again, like with chooseDuck, if last element, getNext() will make it point to null/tail
            //so if this is now the case, goes back to head of the list (first player)
            if(nextDuck == null || nextDuck == tail){
                nextDuck = head.getNext();
            }
        }

        //print the message to declare that the goose has decided to race the next player
        System.out.println(currentGoose.getElement() + " yelled Goose as they tapped " +
                nextDuck.getElement());
    }

    //will randomly select the winner of the footrace between nextDuck and currentGoose and return victor
    private String footRace(String player1, String player2){
        //set up random integer to determine winner
        int min = 1;
        int max = 2;
        int range = (max - min) + 1;
        int randomWinner = (int)(Math.random() * range) + min;

        //determine winner
        if(randomWinner == 1){
            return currentGoose.getElement();
        }
        else if(randomWinner == 2){
            return nextDuck.getElement();
        }
        else{
            return "Error";
        }
    }

    //method to add a node to the list
    private void addNode(String e, Node<E> predecessor, Node<E> successor){
        //create new node using the current params
        Node<E> newest = new Node<>(e, predecessor, successor);

        //set "next" pointer of previous node to the current node
        predecessor.setNext(newest);
        //set "prev" pointer of next node to the current node
        successor.setPrev(newest);

        size++;

    }
}
