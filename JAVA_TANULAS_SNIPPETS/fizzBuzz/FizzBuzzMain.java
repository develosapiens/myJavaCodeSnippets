package fizzBuzz;

public class FizzBuzzMain 
{
	public static void main(String[] args) 
	{
		RangeSetter setter = new RangeSetter();
		Range myRange = setter.setRange(args);
		
		int firstNumber = myRange.getFirstNumber();
		int lastNumber = myRange.getLastNumber();
		
		Displayer disp = new Displayer();
		for(int i = firstNumber; i <= lastNumber; i++)
		{
			disp.write(AppLogic.getFizzBuzzResultString(i));
		}
	}
}
