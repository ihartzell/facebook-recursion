import java.util.ArrayList;

public class Facebook 
{
	// This array list is an array list of facebook user objects and
	// encompasses everyone across the board in regards to facebook.
	private ArrayList<FacebookUser> everybody = new ArrayList<FacebookUser>();
	
	// Here I'm creating an array list for the friends recommendations.
	private ArrayList<FacebookUser> recommendations = new ArrayList<FacebookUser>();
	
	// I'm passing into this method three String arguments that the user
	// will create in the method call. If the username is nothing then 
	// I create a newUser object from the FacebookUser class.
	// I then use that object to call for the setPasswordHint method so the user can set their own password hint.
	// Then I add this newUser object to the everybody array list.
	// If the previous didn't occur then I print out that the username that they tried to enter
	// already exists.
	public void addUser(String username, String password, String passwordHint)
	{
		if(getUser(username) == null)
		{
			FacebookUser newUser = new FacebookUser(username, password);
			newUser.setPasswordHint(passwordHint);
			everybody.add(newUser);
		}
		else
		{
			System.out.println("Username: " + username + " already exists!");
		}
	}
	
	// Here I create a FacebookUser object and assign it to the getUser method which has the username
	// as an argument. If the user object doesn't equal nothing then I remove from the array list the user object.
	// If the previous doesn't occur then I simply print out that the username that the user is trying to delete
	// doesn't exist. 
	public void deleteUser(String username)
	{
		FacebookUser user = getUser(username);
		
		if (user != null)
		{
			everybody.remove(user);
			System.out.println("Removed " + username);
		}
		else
		{
			System.out.println("Username: " + username + " doesn't exist!");
		}
	}
	// This method is passed a username argument and is of type FacebookUser or a class type.
	// For each user, I cycle through the everybody array list and if the user is itself then I return that user.
	// Other wise I return null or nothing.
	public FacebookUser getUser(String username)
	{
		for (FacebookUser user: everybody)
		{
			if(user.getUsername().equalsIgnoreCase(username))
				return user;
		}
		return null;
	}
	// I'm passing into this method a users argument of type ArrayList FacebookUser objects.
	// I then assign everybody array list to this new users array list.
	public void setUsers(ArrayList<FacebookUser> users)
	{
		everybody = new ArrayList<FacebookUser>(users);
	}
	// I'm making an array list that's a pure copy of the everybody array list.
	// getUsers has a return type of an array list of FacebookUser objects.
	public ArrayList<FacebookUser> getUsers()
	{
		ArrayList<FacebookUser> copyList = new ArrayList<FacebookUser>(everybody);
		return copyList;
	}
	// This method basically does the exact same thing as the getUser method accept that it tackles passwords and not usernames.
	public FacebookUser getPassword(String passwordArg)
	{
		for(FacebookUser password: everybody)
		{
			if(password.getPassword().equalsIgnoreCase(passwordArg))
				return password;
		}
		return null;
	}
	
	// This method basically cycles through the getFriends method and if
	// the recommendations array list doesn't contain a friend with whatever argument is passed into it
	// then I add that friend to the recommendations array list.
	public ArrayList<FacebookUser> recommendFriends(FacebookUser friendsArg)
	{	
		// for each friend using the friendsArg passed into the method calling for the getFriends method
		// which returns an array list of all friends, if the recommendations array list contains the friend then
		// I return the recommendations array list. Otherwise I add the friend to the recommendations array list and 
		// then call for this recommendFriends method with the friend as the argument.
		for(FacebookUser friend: friendsArg.getFriends())
		{
			if(recommendations.contains(friend))
			{
				return recommendations;
			}
			else
			{	
				recommendations.add(friend);
				System.out.println("Recommendations for friend list :" + friend + recommendFriends(friend));
			}
		}
		return recommendations;
	} // End of recommendFriends method.
} // End of Facebook class.
