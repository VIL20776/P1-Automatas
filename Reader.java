import java.lang.String;
import java.util.ArrayDeque;
import java.util.Deque;


/**
 * Reader
 */
public class Reader {

    // A utility function to return
    // precedence of a given operator
    // Higher returned value means
    // higher precedence
    static int Prec(char ch)
    {
        switch (ch) {
        case '*':
            return 3;
 
        case '+':
        case '|':
            return 1;

        case '.':
            return 2;

        }
        return -1;
    }

    // The main method that converts
    // given infix expression
    // to postfix expression.
    static String regexpToPostfix(String exp)
    {
        // initializing empty String for result
        String result = new String("");
        boolean concat = false;
 
        // initializing empty stack
        Deque<Character> stack
            = new ArrayDeque<Character>();
        
        for (int i = 0; i < exp.length(); ++i) {
            char c = exp.charAt(i);
 
            // If the scanned character is an
            // operand, add it to output.
            if (Character.isLetterOrDigit(c))
            {     
                if (concat) {
                    while (!stack.isEmpty()
                        && Prec('.') <= Prec(stack.peek())) {
                        result += stack.peek();
                        stack.pop();
                    }
                    stack.push('.');
                }
                result += c;
                concat = true;
            }
            // If the scanned character is an '(',
            // push it to the stack.
            else if (c == '(')
            {
                if(concat) 
                {
                    while (!stack.isEmpty()
                        && Prec('.') <= Prec(stack.peek())) {
                        result += stack.peek();
                        stack.pop();
                    }
                    stack.push('.');
                    concat = false;
                }
                stack.push(c);
                continue;
            }
            //  If the scanned character is an ')',
            // pop and output from the stack
            // until an '(' is encountered.
            else if (c == ')') {
                while (!stack.isEmpty()
                       && stack.peek() != '(') {
                    result += stack.peek();
                    stack.pop();
                }
                stack.pop();
                concat = true;
            }
            else // an operator is encountered
            {
                while (!stack.isEmpty()
                    && Prec(c) <= Prec(stack.peek())) {
                    result += stack.peek();
                    stack.pop();
                }
                stack.push(c);

                if (c == '+')
                    concat = false;
            }               
        }
 
        // pop all the operators from the stack
        while (!stack.isEmpty()) {
            if (stack.peek() == '(')
                return "Invalid Expression";
            result += stack.peek();
            stack.pop();
        }
       
        return result;
    }
}