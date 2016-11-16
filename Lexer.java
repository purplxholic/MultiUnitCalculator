package com.example;

//import calculator.Type;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Calculator lexical analyzer.
 */
public class Lexer {

	/**
	 * Token in the stream.
	 */

//	private final String string;
	private int pointer;
	public static class Token {
		final Type type;
		final String text;

		Token(Type type, String text) {
			this.type = type;
			this.text = text;
		}

		Token(Type type) {
			this(type, null);
		}
	}

	@SuppressWarnings("serial")
	static class TokenMismatchException extends Exception {
	}
//	public static String tokens;
	// TODO write method spec
	public ArrayList<Token> tokens;

	public Lexer(String input) {
		// TODO implement for Problem
//		ArrayList<Token> tokens = new ArrayList<>();

//		Pattern pattern = Pattern.compile("((?:hello){3}|())");
        tokens = new ArrayList<>();
		while (pointer < input.length()) {
			if (Character.isDigit(input.charAt(pointer))) {
				Pattern p = Pattern.compile("(\\d+(\\.\\d+)?)");
				Matcher m = p.matcher(input.substring(pointer));
				if (m.find()) {
					String answer = m.group();
					Token number = new Token(Type.NUMBER, answer);
//					System.out.println("answer: " +answer);
					tokens.add(number);

//					System.out.println("m.end() "+m.end());
					pointer = pointer+m.end(); //so that the pointer will jump to the operations next
//					System.out.println("number");
				}
			}
			//else operators or units
			else {

				if (input.charAt(pointer) == '+') {
					pointer++;
					Token plus = new Token(Type.PLUS, "+");
					tokens.add(plus);
//					System.out.println("plus");
				} else if (input.charAt(pointer) == '-') {
					pointer++;
					Token minus = new Token(Type.MINUS, "-");
					tokens.add(minus);
//					System.out.println("minus");
				} else if (input.charAt(pointer) == '*') {
					pointer++;
					Token times = new Token(Type.TIMES, "*");
					tokens.add(times);
//					System.out.println("times");
				} else if (input.charAt(pointer) == '(') {
					pointer++;
					Token leftpara = new Token(Type.L_PAREN, "(");
					tokens.add(leftpara);
//					System.out.println("(");
				}
				else if (input.charAt(pointer) == '/') {
					Token divide = new Token(Type.DIVIDE, "/");
					tokens.add(divide);
					pointer++;
//					System.out.println("/");
				} else if (input.charAt(pointer) == ')') {
					pointer++;
					Token rightpara = new Token(Type.R_PAREN, ")");
					tokens.add(rightpara);
//					System.out.println(")");
				} else if (input.charAt(pointer) == 'p' && input.charAt(pointer + 1) == 't') {
					pointer += 2;
					Token point = new Token(Type.POINT, "pt");
					tokens.add(point);
//					System.out.println("pt");
				} else if (input.charAt(pointer) == 'i' && input.charAt(pointer + 1) == 'n') {
					pointer += 2;
					Token inch = new Token(Type.INCH, "in");
					tokens.add(inch);
//					System.out.println("in");
				} else if (input.charAt(pointer)== ' ') {
					pointer++;
//					System.out.println("whitespace");
				}
				else {
					try{
						throw new TokenMismatchException();
					}
					catch (TokenMismatchException e){
						e.getMessage();
					}
				}

			}

		}
//        for(Token t:tokens){
//            System.out.println(t.text+t.type);
//        }
	}

}
