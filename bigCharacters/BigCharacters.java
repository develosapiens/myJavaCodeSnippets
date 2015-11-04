import static java.lang.Character.getNumericValue;

public class BigCharacters
{

	public static void main( String... a‮ )
	{
		Integer a = 42;
		Integer b = 42;
		System.out.println( a == b );
		Integer c = 666;
		Integer d = 666;
		System.out.println( c == d );

		Double x = Double.MIN_VALUE;
		if( x != ( x += 0.0f ) )
			System.out.println( "WTF" );

		for( char c‮ = 1; c‮ > 0; c‮++ )
			if( getNumericValue( c‮ ) > 50 )
				System.out.println( c‮ + ": " + getNumericValue( c‮ ) );

	}

}