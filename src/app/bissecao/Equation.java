package app.bissecao;


import java.util.ArrayList;
import java.util.Iterator;

public class Equation {

	public ArrayList<Double> numbers;
	public ArrayList<String> operation;
	
	public Equation (ArrayList<Double> numbers,ArrayList<String> operation)
	{
		this.numbers=numbers;
		this.operation=operation;
	}
	public Equation ()
	{
		numbers= new ArrayList<Double>();
		operation= new ArrayList<String>();
	}
	public String toString()
	{
		String equation= new String();
		
		Iterator<Double> numberi= numbers.iterator();
	
		while(numberi.hasNext())
		{
			equation= equation+" "+ numberi.next().toString();
			
			//System.out.println(equation);
		}
		Iterator<String> stringi= operation.iterator();
		while(stringi.hasNext())
		{
			equation= equation+" "+stringi.next();
			//System.out.println(equation);
		}
		//System.out.println("imprimindo equation");
		//System.out.println(equation);
		return equation;
		
	}
}