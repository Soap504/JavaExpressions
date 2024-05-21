//
// Name: Arce, Sophia
// Project: 3
// Due: 10/23/2023
// Course: cs-2400-02-f23
//
// Description:
// This Java project involves implementing an expression evaluation system, 
// focusing on converting arithmetic expressions from infix to postfix notation and evaluating the results. 
// The main `Expression` class, supported by a custom Stack ADT, manages operators during the conversion process. 
// Rigorous testing covers diverse scenarios, ensuring accurate handling of expressions, and the project report 
// includes essential sections like an ADT description, testing methodology, and lessons learned. Grading criteria 
// evaluate report quality, program correctness, and coding standards.
//

import java.util.EmptyStackException;

public class LinkedStack<T> implements StackInterface<T> {
    private Node topNode; // References the first node in the chain

    // Default constructor
    public LinkedStack() {
        topNode = null;
    }

    // Pushes a new entry onto the stack
    public void push(T newEntry) {
        Node newNode = new Node(newEntry, topNode);
        topNode = newNode;
    }// end push

    // Retrieves the top entry of the stack without removing it
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        } else {
            return topNode.getData();
        }
    }// end peek

    // Retrieves and removes the top entry of the stack
    public T pop() {
        T top = peek(); // Might throw EmptyStackException
        topNode = topNode.getNextNode();
        return top;
    }// end pop

    // Checks if the stack is empty
    public boolean isEmpty() {
        return topNode == null;
    }// end isEmpty

    // Removes all entries from the stack
    public void clear() {
        topNode = null;
    }// end clear

    // Inner class representing a node in the stack
    private class Node {
        private T data; // Data stored in the node
        private Node next; // Reference to the next node in the chain

        // Constructor with data only
        private Node(T dataPortion) {
            this(dataPortion, null);
        }

        // Constructor with data and reference to the next node
        private Node(T dataPortion, Node nextNode) {
            data = dataPortion;
            next = nextNode;
        }

        // Getter for data
        private T getData() {
            return data;
        }// end getData

        // Setter for data
        private void setData(T newData) {
            data = newData;
        }// end setData

        // Getter for the next node
        private Node getNextNode() {
            return next;
        }// end getNextNode

        // Setter for the next node
        private void setNextNode(Node nextNode) {
            next = nextNode;
        }// end setNextNode
    }// end class: Node
}
