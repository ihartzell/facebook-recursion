import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
// This is the driver class. It's the class where the program begins.
public class UserAccountDriver 
{
	// This is the main method where the program is essentially executed.
	public static void main(String[] args) 
	{
		Scanner input = new Scanner(System.in);
		
		
		// Initializied variables so I could use them in my method
		// calls such as getUser(username),because as of now the 
		// computer doesn't know what those arguments are unless they're assigned
		// or there's user input where the user essentially decides what those arguments are.
		String username = null;
		String password = null;
		String passwordHint = null;
		String newFriendName = null;
		String formerFriendName = null;
		
		int menuChoice = 0;
		
		// I created a facebook object from my Facebook class
		// and I assign it to the deSerialization method so I don't lose the data.
		// If I don't assign the deSerialization method won't be assigned to anything and essentially does nothing.
		Facebook facebookObj = deSerialization();
		
		
		// I basically repeat my menu as long as the user doesn't choose option
		// 5 to exit the program, and the user picks an option not valid.
		do
		{	
			// I assign my menu method to the variable menuChoice so I can make a switch statement
			// for my menu.
			menuChoice = menu();
			
			switch(menuChoice){
			
			// Lists all users.
			case 1:
						// I simply use my facebook object from my Facebook class to call my getUsers method
						// which displays a copy array list of the everybody array list.
						System.out.println(facebookObj.getUsers());
						System.out.println();
						
				break; // end of Add User case.
				
			// Adds a user.	
			case 2: System.out.println("Creation of new Facebook user:");
					System.out.print("----------------------");
					System.out.println();
					username = requestString("Username:");
					password = requestString("Password:");
					passwordHint = requestString("What do you want the hint for your password to be?");
					System.out.println("Added: " + username);
					facebookObj.addUser(username, password, passwordHint);
					System.out.println();
					
				break;
				
			// Removes a user.	
			case 3: System.out.println("Deletion of Facebook user:");
					System.out.println("----------------");
					username = requestString("Remove user:");
					facebookObj.deleteUser(username);
					System.out.println();
					
				break; // end of Delete User case.
				
			// Gives the password help.	
			case 4:	System.out.println("*Password Help*");
					username = requestString("What is the username?");
					FacebookUser userObj = facebookObj.getUser(username);
					
					// This simply checks to make sure the FacebookUser object isn't equal to nothing.
					// if it is I put out an error message. Every case where I have a username request I have this if else branch.
					if (userObj == null)
					{
						System.out.println("No user with username: " + username);
						System.out.println();
						break;
					}
					else
					{
						userObj.getPasswordHelp();
						System.out.println();
					}
					
				break; // end of Password Hint case.
				
			// Friending someone.
			case 5:	System.out.println("Friending someone");
					System.out.println("-----------------");
					username = requestString("Before adding a friend, what is your Facebook username?");
					// I'm creating an object from my FacebookUser class so I can assign it to my facebookObj
					// so that I can call for my getUser method inside the Facebook class and call for that method with the username
					// argument the user constructs.
					FacebookUser userObjForFriendsCase = facebookObj.getUser(username);
					
					if (userObjForFriendsCase == null)
					{
						System.out.println("No user with username: " + username);
						System.out.println();
						break;
					}
					else
					{
						password = requestString("What is the password for the account?");
						FacebookUser passObjForFriendsCase = facebookObj.getPassword(password);
						
						if(passObjForFriendsCase == null)
						{
							userObjForFriendsCase.checkPassword(password);
							System.out.println();
							break;
						}
						
						System.out.println("Everything is in order.");
						System.out.println();
						
						// Creation of the friend essentially.
						newFriendName = requestString("What is the new friend's name you wish to add?");
						
						// Creation of a newFriend object from the FacebookUser class and I assign it to the facebook object
						// from the facebook class and call for the getUser method with the newFriendName string variable as its
						//argument.
						FacebookUser newFriend = facebookObj.getUser(newFriendName);
						
						// If the newFriend is nothing. I print out an error message and recyle the menu.
						if(newFriend == null)
						{
							System.out.println("This friend doesn't exist. Please first add this user using");
							System.out.println("menu option 2.");
							System.out.println();
							break;
						}
						// Other wise I use my object that I created near the beginning of this case so I can simply friend whoever
						// was choosen.
						else
						{
							userObjForFriendsCase.friend(newFriend);
							System.out.println("Added " + newFriend);
							System.out.println();
						}
					} // end of main else branch.
					
				break; // end of Friend case.
				
			// de-friending someone.
			case 6: System.out.println("De-Friending someone.");
					System.out.println("--------------------");
					// This is input, I'm asking for the Facebook username
					username = requestString("What is your Facebook username?");
					
					// I'm creating a facebook user object and assigning it to the facebook object and calling for the getUser method
					// with the username the user creates as the argument.
					FacebookUser userForDeFriendingCase = facebookObj.getUser(username);
					
					if(userForDeFriendingCase == null)
					{
						System.out.println("No user with username: " + username);
						System.out.println();
						break;
					}
					else
					{
						formerFriendName = requestString("What is the name of the friend you wish to remove?");
						FacebookUser formerFriendObjForFriendCase = facebookObj.getUser(formerFriendName);
						
						if(formerFriendName != null)
						{
							userForDeFriendingCase.defriend(formerFriendObjForFriendCase);
							System.out.println("Removed " + "(" + formerFriendName + ")" );
							System.out.println();
							break;
						}
						else
						{
							System.out.println("The friend " + formerFriendName + " doesn't exist!");
							System.out.println();
						}
					}
					
				break; // end of De-Friend case.
				
			// Listing Friends.
			case 7: System.out.println("Listing all friends of Facebook user.");
					System.out.println("-------------------------------------");
					username = requestString("What is your Facebook username?");
					// Here I'm creating a FacebookUser object and assigning it to the facebook object from the Facebook class calling for
					// the getUser method which has the username argument that the user of the program creates.
					FacebookUser facebookUserForListAllFriendsCaseObj = facebookObj.getUser(username);
					
					// If the facebook user I created is equivalent to nothing for example, when I call for the getUser method
					// If I input Isaac and Isaac isn't equivalent to nothing, then the getUser method returns null.
					// So if this object is null then I print out an error message.
					if (facebookUserForListAllFriendsCaseObj == null)
					{
						System.out.println("No user with username: " + username);
						System.out.println();
						break;
					}
					// Otherwise I use this object to call for the getFriends method which has all the friends in the friends array list
					// and I simply print out all of them.
					else
					{
						facebookUserForListAllFriendsCaseObj.getFriends();
						System.out.println(username + "'s friends:" +facebookUserForListAllFriendsCaseObj.getFriends());
						System.out.println();
					}
				break;
				
			// Recommending friends. Use Recursion.
			case 8:	System.out.println("Friends Recommendations");
					System.out.println("-----------------------");
					username = requestString("What is your Facebook username?");
					FacebookUser fbUserObjForRecommendingCase = facebookObj.getUser(username);
					
					if (fbUserObjForRecommendingCase == null)
					{
						System.out.println("No user with username: " + username);
						System.out.println();
						break;
					}
					else
					{	
						// I'm creating an array list called recommendations and assigning it to my recommendFriends method essentially
						// with the username as the argument. Then I print out the array list.
						ArrayList<FacebookUser> recommendations = facebookObj.recommendFriends(facebookObj.getUser(username));
						System.out.println(username + "'s potential friends " + recommendations);
						System.out.println();
					}
					
				break;
				
			// Exits the program.
			// Apon termination I serialize the facebook object.
			case 9: serialization(facebookObj);
					System.out.println();
					System.out.println("Logging off...");
				break;
				
			} // end of switch statement or else-if essentially.
		} while(menuChoice >= 1 && menuChoice <= 8 ); // end of the do-while loop and really what runs the program.	
	} // end of the main method.
	
	// This method makes String input much easier and cleaner throughout the program.
	// I pass to the method a String variable and I then print that out.
	// I then return whatever String input back to the method.
	public static String requestString(String request)
	{
		Scanner input = new Scanner(System.in);
		System.out.print(request);
		
		return input.nextLine();
	}
	// This method purely displays the menu as well as checks to see
	// if the user's input is < 1 or > 5 and if it is the menu will repeat
	// untill the user selects a valid menu option.
	public static int menu()
	{
		int menuChoice = 0;
		Scanner input = new Scanner(System.in);
		
		System.out.println("Facebook Menu:");
		System.out.println("-------------");
		System.out.println("1.List User(s):");
		System.out.println("2.Add User:");
		System.out.println("3.Delete User:");
		System.out.println("4.Password Hint:");
		System.out.println("5.Friend:");
		System.out.println("6.De-friend:");
		System.out.println("7.List friends:");
		System.out.println("8.Recommend new friends:");
		System.out.println("9.Quit:");
		System.out.println();
		System.out.print("Choose 1-9.");
		menuChoice = input.nextInt();
		System.out.println();
		while (menuChoice < 1 || menuChoice > 9 )
		{
			System.out.println("Facebook Menu:");
			System.out.println(menuChoice + " is an invalid option, please choose 1-5.");
			System.out.println("-------------");
			System.out.println("1.List User(s):");
			System.out.println("2.Add User:");
			System.out.println("3.Delete User:");
			System.out.println("4.Password Hint:");
			System.out.println("5.Friend:");
			System.out.println("6.De-friend:");
			System.out.println("7.List friends:");
			System.out.println("8.Recommend new friends:");
			System.out.println("9.Quit:");
			System.out.println();
			System.out.print("Choose 1-9.");
			menuChoice = input.nextInt();
			System.out.println();
		}
		return menuChoice;
	} // end of menu method.
	
	// This method serializes the Facebook object. In other words it writes the object to the file.
	public static void serialization(Facebook facebookObj)
	{
		try 
		{
			// I'm creating a file output object which has the file name assigned to it.
			FileOutputStream fbDataOutput = new FileOutputStream("fbDataFile.txt");
			
			// Here I'm creating an output stream object which is assigned to the file output object.
			ObjectOutputStream objOutStream = new ObjectOutputStream(fbDataOutput);
			
			// I am then writing the facebookObj to the file.
			objOutStream.writeObject(facebookObj.getUsers());
			
			//closing file object and output stream object.
			objOutStream.close();
			fbDataOutput.close();
			
			System.out.printf("Data saved successfully, and is now viewable in your file which can only be read by the computer. ");
		} 
		catch (IOException ex) 
		{
			ex.printStackTrace();
		}
		
	} // end of serialization method.
	
	@SuppressWarnings("unchecked")
	public static Facebook deSerialization()
	{
		// Creating a facebook object.
		Facebook facebookObj = new Facebook();
		try 
		{
			// I'm making a file input object so I can now read in the program the file.
			FileInputStream fbDataInput = new FileInputStream("fbDataFile.txt");
			
			// I'm making an input stream object so I can read in the facebook object.
			ObjectInputStream objInStream = new ObjectInputStream(fbDataInput);
			
			// This line is involved but I'm basically using the facebook object to call for the setUsers method where I essentially
			// cast the object input stream and call for the readObject method so I can read in the object.
			facebookObj.setUsers((ArrayList<FacebookUser>) objInStream.readObject());
			
            // I'm closing the file input object and the object input stream.
			objInStream.close();
			fbDataInput.close();
			
			return facebookObj;
		} 
		catch (IOException ex) 
		{
			System.out.println("Starting app with no data!");
			return facebookObj;
		}
		catch (ClassNotFoundException ex)
		{
			System.out.println("Failed to serialize! possibility of data corruption.");
			System.out.println("Starting app with no data!");
			return facebookObj;
		}
	} 	// End of deSerialization method.
} 	// End of driver class.