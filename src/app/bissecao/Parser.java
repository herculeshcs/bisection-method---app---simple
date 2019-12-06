package app.bissecao;


import java.util.ArrayList;
import java.util.Iterator;

public class Parser {

	public String equation;
	public final String validOperatorList= new String("*/^+-");
	String variablesList = new String("QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm");
	public  Parser(String a)
	{
	
		equation= new String(a);
	}

	public Equation precedenceOperators(Equation equation)
	{
		
	ArrayList<Double> numbers = equation.numbers;
	ArrayList<String> operators= equation.operation;

	
	for(int i= 0;i<operators.size();i++)
	{
		if(operators.get(i).charAt(0)=='^'||operators.get(i).charAt(0)=='*'||operators.get(i).charAt(0)=='/')
		{
			equation.numbers.set(i, operation(operators.get(i),numbers.get(i),numbers.get(i+1)));
			
		}
	}
	
	for(int i= 0;i<operators.size();i++)
	{
		if(operators.get(i).charAt(0)=='^'||operators.get(i).charAt(0)=='*'||operators.get(i).charAt(0)=='/')
		{
			equation.numbers.remove(i+1);
			equation.operation.remove(i);
			
		}
	}
		return equation;
	}
	public String  call (String a,double b)
	{
		String s="";
		for(int i=0;i<a.length();i++)
		{
			if(variablesList.contains((String.valueOf(a.charAt(i)))))
			{
				s=s+String.valueOf(b);
			}
			else
			{
				s=s+a.charAt(i);
			}
			
		}
		
		return s;
	}
	public double computing(String a,double b)
	{
		//double result;
		a= call(a,b);
		Equation equation= analysing (a);
		equation = precedenceOperators(equation);
		//System.out.println("depois da precedencia: "+equation.toString());
		Iterator<Double> numbers = equation.numbers.iterator();
		Iterator<String> operators = equation.operation.iterator();
		
		// refaser método quando adicionar operadores unarios
		Double result=numbers.next();
		while(numbers.hasNext())
		{	
			//System.out.println("calculando");
			Double number1 = numbers.next();
			Double number2= result;
			String  op= operators.next();
			result= operation(op,number2,number1);
			
			
		}
		
		return result;
	}
	
	public Double operation (String o,Double a, Double b)
	{
		char op=o.charAt(0);
		//System.out.println("operando"+a+" "+op+" "+b);
		switch (op)
		{
			case '*':
				return a*b;
			case '/':
				return a/b;
			case '-':
				return a-b;
			case '+':
				return a+b;
			case '^':
				return Math.pow(a,b);
		}
		return null;
	}
	public Equation analysing (String a)
	{
		System.out.println("analisando :"+a);
		Equation equation= new Equation();
		double number=0.0;
		int size= a.length();
		int p=0;
		int state=0; // estado 0 processando parte inteira
					// estado 1 processando parte decimal
		
		for( int i=0 ;i<size ;i++ )
		{
			if(Character.isDigit(a.charAt(i))==true)
			{
				
				//System.out.println("Analisando digito");
				// processa parte inteira do numero
				if(state==0)
				{
						number = number*10+ (a.charAt(i)-48.0);
						System.out.println(Math.pow(10.0, p));
						p++;
				}
		
				// processa parte decimal
				else if(state==1)
				{
					number = number +  ((double)a.charAt(i)-48.0)*Math.pow(10.0, p);
					p--;
				}
			}
			else if (a.charAt(i)=='.'||a.charAt(i)==',')
			{
				
				System.out.println("achou ponto e o numero é até agora :"+number);
				state=1;// agora vamos processar a parte decimal do numero
				p=-1; // alterando p para -1 
			}
			// em proximas atualisações termos operadores unarios ou seja uma equação podera terminar com um operador
			else if(validOperatorList.contains(String.valueOf(a.charAt(i))))
			{
				System.out.println("achou um operador");
				System.out.println(" eo numero é "+number);
				equation.operation.add(String.valueOf(a.charAt(i)));
				equation.numbers.add(number);
				p=0;
				state=0;
				number=0.0;
			}
		}
		//System.out.println("achou um operador");
		//equation.operation.add(String.valueOf(a.charAt(i)));
		// a verificação garante que se a equação termina com um operador não vamos repetir o numero
		if(number!=0)
		{
			equation.numbers.add(number);
			p=0;
			state=0;
		
		}
		
		return equation;
	}
}
