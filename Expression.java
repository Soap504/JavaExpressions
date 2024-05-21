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

import java.util.Stack;

public class Expression {
    // Stacks for operators and postfix expression
    private static StackInterface<String> operatorStack = new LinkedStack<>();
    private static StackInterface<String> postfixList = new LinkedStack<>();

    // Private constructor to prevent instantiation of the class
    private Expression() {}

    // Method to convert infix expression to postfix
    public static String[] convertToPostfix(String[] infixExpression) {
        // Clear the stacks and initialize counts for operands and operators
        operatorStack.clear();
        postfixList.clear();
        int operandCount = 0;
        int operatorCount = 0;

        // Iterate through each token in the infix expression
        for (String token : infixExpression) {
            if (isOperand(token)) {
                // If the token is an operand, push it to the postfix expression and increment operand count
                postfixList.push(token);
                operandCount++;
            } else if (isOperator(token)) {
                // If the token is an operator, handle operator precedence and associativity
                while (!operatorStack.isEmpty() && hasHigherPrecedence(operatorStack.peek(), token)) {
                    postfixList.push(operatorStack.pop());
                    operatorCount--;
                }
                operatorStack.push(token);
                operatorCount++;
            } else if (token.equals("(")) {
                // If the token is an opening parenthesis, push it to the operator stack and increment operator count
                operatorStack.push(token);
                operatorCount++;
            } else if (token.equals(")")) {
                // If the token is a closing parenthesis, handle operators until the matching opening parenthesis
                while (!operatorStack.isEmpty() && !operatorStack.peek().equals("(")) {
                    postfixList.push(operatorStack.pop());
                    operatorCount--;
                }
                // Pop the opening parenthesis from the stack and decrement operator count
                if (!operatorStack.isEmpty() && operatorStack.peek().equals("(")) {
                    operatorStack.pop();
                    operatorCount--;
                } else {
                    // Throw an exception for mismatched parentheses
                    throw new RuntimeException("Mismatched parentheses");
                }
            }
        }

        // Handle remaining operators in the stack
        while (!operatorStack.isEmpty()) {
            if (operatorStack.peek().equals("(")) {
                // Throw an exception for unmatched opening parenthesis
                throw new RuntimeException("Mismatched parentheses");
            }
            postfixList.push(operatorStack.pop());
            operatorCount--;
        }

        // Check if the number of operands is one more than the number of operators
        if (operandCount != operatorCount + 1) {
            throw new RuntimeException("Operand missing");
        }

        // Reverse the order of postfixList using a temporary stack
        Stack<String> reversedPostfix = new Stack<>();
        while (!postfixList.isEmpty()) {
            reversedPostfix.push(postfixList.pop());
        }

        // Copy the reversed postfix expression to the result array
        Stack<String> resultStack = new Stack<>();
        while (!reversedPostfix.isEmpty()) {
            resultStack.push(reversedPostfix.pop());
        }
        String[] result = new String[resultStack.size()];
        int index = 0;
        while (!resultStack.isEmpty()) {
            result[index++] = resultStack.pop();
        }

        // Check for unmatched parentheses at the end
        if (!operatorStack.isEmpty()) {
            throw new RuntimeException("Mismatched parentheses");
        }

        return result;
    }

    // Method to evaluate a postfix expression
    public static double evaluatePostfix(String[] postfixExpression) {
        // Stack to store operands during evaluation
        Stack<Double> operandStack = new Stack<>();

        // Iterate through each token in the postfix expression
        for (String token : postfixExpression) {
            if (isOperand(token)) {
                // If the token is an operand, push its numeric value to the operand stack
                operandStack.push(Double.parseDouble(token));
            } else if (isOperator(token)) {
                // If the token is an operator, apply the operator to the top two operands on the stack
                if (operandStack.size() < 2) {
                    // Throw an exception for missing operands
                    throw new RuntimeException("Operand missing");
                }
                double operand2 = operandStack.pop();
                double operand1 = operandStack.pop();
                operandStack.push(applyOperator(operand1, operand2, token));
            }
        }

        // Check if there is exactly one operand remaining on the stack
        if (operandStack.size() != 1) {
            // Throw an exception for missing operands
            throw new RuntimeException("Operand missing");
        }

        // Return the result of the expression
        return operandStack.pop();
    }

    // Method to check if a token is an operand
    private static boolean isOperand(String token) {
        try {
            // Attempt to parse the token as a double; if successful, it is an operand
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            // If parsing fails, the token is not an operand
            return false;
        }
    }
    
    // Method to check if a token is an operator
    private static boolean isOperator(String token) {
        // Array of valid operators
        String[] validOperators = {"+", "-", "*", "/", "^"};
        for (String op : validOperators) {
            if (op.equals(token)) {
                // If the token matches a valid operator, it is an operator
                return true;
            }
        }
        // If the token does not match any valid operator, it is not an operator
        return false;
    }    

    // Method to check if an operator has higher precedence than another operator
    private static boolean hasHigherPrecedence(String option1, String option2) {
        // Get precedence values for the two operators
        int precedence1 = getPrecedence(option1);
        int precedence2 = getPrecedence(option2);

        // Compare precedence values
        if (precedence1 == precedence2) {
            // If precedence values are equal, consider associativity
            return isLeftAssociative(option1);
        }

        // Return true if precedence1 is higher than precedence2
        return precedence1 > precedence2;
    }

    // Method to get the precedence value of an operator
    private static int getPrecedence(String operator) {
        // Switch statement to assign precedence values based on the operator
        switch (operator) {
            case "^":
                return 3;
            case "*":
            case "/":
                return 2;
            case "+":
            case "-":
                return 1;
            default:
                // Default precedence value for unknown operators
                return 0;
        }
    }

    // Method to check if an operator is left-associative
    private static boolean isLeftAssociative(String operator) {
        // Return true if the operator is not the power operator "^"
        return !operator.equals("^");
    }

    // Method to apply an operator to two operands
    private static double applyOperator(double operand1, double operand2, String operator) {
        // Switch statement to perform the operation based on the operator
        switch (operator) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                // Check for division by zero
                if (operand2 == 0) {
                    throw new RuntimeException("Division by zero");
                }
                return operand1 / operand2;
            case "^":
                return Math.pow(operand1, operand2);
            default:
                // Throw an exception for unknown operators
                throw new RuntimeException("Invalid operator");
        }
    }
}
