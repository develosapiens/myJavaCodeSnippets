package dSCHcalcProject.ObjectModels;

public class FoodType
{
	// the name will be shown in the GUI
	protected String           foodName;

	// unique identifier of food type
	protected Long             ID;

	// group name like vegetables or biscuits
	protected String           groupName;

	// market place name where the food is from
	protected String           marketPlaceName;

	// cH percentage
	protected int              cHcontent;

	// cH content amount type (TypeOfCarbAmount)
	protected TypeOfCarbAmount type;

	// frequency How often is this food used
	protected int              freq;

}
