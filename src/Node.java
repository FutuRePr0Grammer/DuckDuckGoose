//I will be implementing a doubly linked list for this game

public class Node<E> {


    //the element being stored in the current node
    private String element;
    //a pointer to the node previous to the current one
    private Node<E> prev;
    //a pointer to the node after the current one
    private Node<E> next;

    //constructor
    public Node(String e, Node<E> prev, Node<E> next){
        this.element = e;
        this.prev = prev;
        this.next = next;
    }

    //utility methods:

    //methods to get and set the pointer to the previous node
    public Node<E> getPrev(){return prev;}
    public void setPrev(Node<E> prev){this.prev = prev;}

    //methods to get and set the pointer to the next node
    public Node<E> getNext(){return next;}
    public void setNext(Node<E> next){this.next = next;}

    //method to get the current element stored in the current node
    public String getElement(){return element;}
}
