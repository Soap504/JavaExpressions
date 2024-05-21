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


/**
 * Interface representing a generic stack data structure.
 * @param <T> The type of elements to be stored in the stack.
 */
public interface StackInterface<T> {
    
    /**
     * Adds a new entry to the top of the stack.
     * @param newEntry The element to be added.
     */
    void push(T newEntry);

    /**
     * Removes and returns the element at the top of the stack.
     * @return The element removed from the top of the stack.
     */
    T pop();

    /**
     * Retrieves the element at the top of the stack without removing it.
     * @return The element at the top of the stack.
     */
    T peek();

    /**
     * Checks if the stack is empty.
     * @return True if the stack is empty, false otherwise.
     */
    boolean isEmpty();

    /**
     * Clears all elements from the stack.
     */
    void clear();
}
