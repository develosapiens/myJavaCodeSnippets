package fizzBuzz;

public class FizzBuzzMain {

	public static void main(String[] args) 
	{
		Displayer disp = new Displayer();
		for(int i = 1; i < 41; i++)
		{
			disp.write(AppLogic.getFizzBuzzResultString(i));
		}
	}
}
