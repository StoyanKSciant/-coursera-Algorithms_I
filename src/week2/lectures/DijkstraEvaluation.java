package week2.lectures;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class DijkstraEvaluation {

	/**
	 * E. W. Dijkstra algorithm for Arithmetic expression evaluation.
	 */

	public static void main(String[] args) {

		Stack<String> ops = new Stack<String>();
		Stack<Double> vals = new Stack<Double>();
		while (!StdIn.isEmpty()) { // Read token, push if operator.
			String s = StdIn.readString();
			if (s.equals("("))
				/* do nothing */ ;
			else if (s.equals("+"))
				ops.push(s);
			else if (s.equals("-"))
				ops.push(s);
			else if (s.equals("*"))
				ops.push(s);
			else if (s.equals("/"))
				ops.push(s);
			else if (s.equals("sqrt"))
				ops.push(s);
			else if (s.equals(")")) { // Pop, evaluate, and push result if token is ")".
				String op = ops.pop();
				double v = vals.pop();
				if (op.equals("+"))
					v = vals.pop() + v;
				else if (op.equals("-"))
					v = vals.pop() - v;
				else if (op.equals("*"))
					v = vals.pop() * v;
				else if (op.equals("/"))
					v = vals.pop() / v;
				else if (op.equals("sqrt"))
					v = Math.sqrt(v);
				vals.push(v);
			} // Token not operator or paren: push double value.
			else
				vals.push(Double.parseDouble(s));
		}
		StdOut.println(vals.pop());
	}
}