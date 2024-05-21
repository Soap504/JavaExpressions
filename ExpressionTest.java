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


public class ExpressionTest {
    public static void main(String[] args) {
        // Iterate through each expression provided as command-line arguments
        System.out.println("Expression by S. Arce \n");
        for (String expression : args) {
            // Split the expression into tokens based on spaces
            String[] tokens = expression.split("\\s+"); // Split based on spaces
            
            
            // Print the original infix expression
            System.out.println("Infix Expression: " + expression);

            try {
                // Convert the infix expression to postfix
                String[] postfix = Expression.convertToPostfix(tokens);

                // Print the postfix expression
                System.out.print("Postfix Expression: ");
                for (String token : postfix) {
                    System.out.print(token + " ");
                }

                // Evaluate the postfix expression and print the result
                double result = Expression.evaluatePostfix(postfix);
                System.out.println("= " + result);
            } catch (RuntimeException e) {
                // Handle any exceptions thrown by the Expression class
                System.err.println("Error: " + e.getMessage());
            }
        }
    }
}
