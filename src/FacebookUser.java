import java.util.ArrayList;

// I'm extending the UserAccount class by creating this subclass FacebookUser.
// I then implement the Comparable class which has FacebookUser as it's generic.
public class FacebookUser extends UserAccount implements Comparable<FacebookUser>
{
	private static final long serialVersionUID = 1L;
	// Field variables.
	// I'm creating an array list of FacebookUser objects and the array list is named friends.
	private String passwordHint;
	private ArrayList<FacebookUser> friends = new ArrayList<FacebookUser>();
	
	// I'm calling for the constructor, the super keyword tells me that
	// it comes from the super class being UserAccount.
	FacebookUser(String username, String password) 
	{
		super(username, password);
	}
	
	// Essentially a setter. I'm setting the passwordHint variable assigned to the variable passed in.
	public void setPasswordHint(String hint)
	{
		this.passwordHint = hint;
	}
	
	// In the case that the friends list already contains this friend they're trying to add,
	// the I want to give an error message, other wise I want to add the new friend to the 
	// array list.
	public void friend(FacebookUser newFriend)
	{
		if(friends.contains(newFriend))
		{
			System.out.println("I couldn't find this friend or they already exist in your friend's list.");
		}
		else
		{
			friends.add(newFriend);
		}
		
	}
	// I basically do the same thing I did with the friend method, only the opposite.
	// if the friends array list contains some former friend then I want to remove them from
	// the array list. Otherwise I give a message stating they aren't there.
	public void defriend(FacebookUser formerFriend)
	{
		if(friends.contains(formerFriend))
		{
			friends.remove(formerFriend);
		}
		else
		{
			System.out.println("There is no friend with this username. ");
		}
	}
	// This method is more involved, essentially I'm creating a copy of my previous array list.
	// I cycle through the array list and add all the friends using the (friends) array list allows me to not have to write the for loop.
	// After that, I use the Collections class combined with the sort method to sort the new array list, namely
	// replicaOfFriendsList. I then call for my getUsername method for both people and compare them ignoring capitalization.
	// The, I finally simply return back to the method the new array list.
	public ArrayList <FacebookUser> getFriends()
	{
		ArrayList<FacebookUser> replicaOfFriendsList = new ArrayList<FacebookUser>(friends);
		return replicaOfFriendsList;
	}
	
	// This is the abstract method actually being created.
	@Override
	public void getPasswordHelp() 
	{
		System.out.println("The hint for your password is " + "(" + passwordHint + ").");
	}
	
	// I'm basically calling for the super class which has the getUsername method,
	// and I then compare it to what ever object is passed into the method from the 
	// FacebookUser.
	@Override
	public int compareTo(FacebookUser personObject) 
	{
		return super.getUsername().compareTo(personObject.getUsername());
	}
	
}

