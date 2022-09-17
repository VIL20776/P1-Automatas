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

        case '.':
            return 2;
 
        case '+':
        case '|':
            return 1;

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
        int conCounter = 0;
 
        // initializing empty stack
        Deque<Character> stack
            = new ArrayDeque<Character>();
        
        for (int i = 0; i < exp.length(); ++i) {
            char c = exp.charAt(i);
 
            // If the scanned character is an
            // operand, add it to output.
            if (Character.isLetterOrDigit(c))
            {
                conCounter++;
                char tc = '#';
                if ((i + 1) < exp.length()) {
                    tc = exp.charAt(i + 1);
                }
                
                if (conCounter > 1 && tc != '*') {
                    while (!stack.isEmpty()
                        && Prec('.') <= Prec(stack.peek())) {
                        result += stack.peek();
                        stack.pop();
                    }
                    stack.push('.');
                }
                result += c;
            }
            // If the scanned character is an '(',
            // push it to the stack.
            else if (c == '(')
            {
                if(conCounter > 0) 
                {
                    conCounter = 0;
                    stack.push('.');
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
                conCounter = 1;
            }
            else // an operator is encountered
            {
                while (!stack.isEmpty()
                    && Prec(c) <= Prec(stack.peek())) {
                    result += stack.peek();
                    stack.pop();
                }
                stack.push(c);

                if (c == '+') conCounter = 0;
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