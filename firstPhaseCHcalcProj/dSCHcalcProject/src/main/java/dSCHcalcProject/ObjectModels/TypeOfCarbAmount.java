package dSCHcalcProject.ObjectModels;

public enum TypeOfCarbAmount
{
	// private String perc, piece, liq;
	percentage("százalékos"), piece("darab"), liquid("deciliter");
	private final String TOCA;

	private TypeOfCarbAmount( final String TOCA )
	{
		setStrings();
		this.TOCA = TOCA;
	}

	private void setStrings()
	{
		// setting the string values up

	}

	@Override
	public String toString()
	{
		return TOCA;
	}
}
