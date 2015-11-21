package net.develoSapiens.excersises.binarySeek;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class EntryPoint
{
	private static Random rnd = new Random();

	public static void main( String[] args )
	{
		int areaOfRnd = 100;
		int lengthOfArea = 25;
		EntryPoint eP = new EntryPoint();
		int toFind = rnd.nextInt( areaOfRnd );
		int result = eP.findByBinarySearch( eP.creatingTheArray( lengthOfArea, areaOfRnd ), toFind );
		if( result == -1 )
		{
			System.out.println( toFind + " is not in the array." );
		}
		else
		{
			System.out.println( toFind + " is at the position number: " + result );
		}
	}

	private int[] creatingTheArray( int lengthOfArea, int areaOfRnd )
	{
		if( lengthOfArea < 20 )
		{
			throw new IllegalArgumentException( "lengthOfArea must be bigger!" );
		}
		if( areaOfRnd < 15 )
		{
			throw new IllegalArgumentException( "areaOfRnd must be bigger!" );
		}
		int[] numbers = new int[lengthOfArea];
		List<Integer> just4Sorting = new ArrayList<>();
		System.out.println( "random array:" );
		for( int i = 0; i < lengthOfArea; )
		{
			int num = rnd.nextInt( areaOfRnd );
			System.out.print( "" + num + ", " );
			if( !just4Sorting.contains( num ) )
			{
				just4Sorting.add( num );
				i++;
			}
		}
		Collections.sort( just4Sorting );
		System.out.println();
		System.out.println( "sorted array:" );
		for( int i = 0; i < just4Sorting.size(); i++ )
		{
			numbers[i] = just4Sorting.get( i );
			System.out.print( numbers[i] + ", " );
		}
		System.out.println();

		return numbers;
	}

	private int findByBinarySearch( int[] numbers, int toFind )
	{
		if( numbers == null )
		{
			throw new IllegalArgumentException( "array for searching must not be null!" );
		}
		if( numbers.length == 0 )
		{
			throw new IllegalArgumentException( "array for searching must not be empty!" );
		}
		System.out.println( "to find: " + toFind );

		int firstIndex = 0;
		int lastIndex = numbers.length - 1;
		int middleIndex = ( lastIndex - firstIndex ) / 2;
		int step = 1;

		while( lastIndex - firstIndex > 1 )
		{
			System.out.print( step++ );
			System.out.print( ". firstIndex: " + firstIndex );
			System.out.print( ", lastIndex: " + lastIndex );
			System.out.print( ", middleIndex: " + middleIndex );
			System.out.println( ", numbers[" + middleIndex + "]: " + numbers[middleIndex] );
			if( numbers[middleIndex] == toFind )
			{
				return middleIndex;
			}
			else if( numbers[middleIndex] < toFind )
			{
				firstIndex = middleIndex;
			}
			else if( numbers[middleIndex] > toFind )
			{
				lastIndex = middleIndex;
			}
			middleIndex = firstIndex + ( ( lastIndex - firstIndex ) / 2 );
		}
		if( numbers[firstIndex] == toFind )
		{
			return firstIndex;
		}
		else if( numbers[lastIndex] == toFind )
		{
			return lastIndex;
		}
		return -1;
	}
}
