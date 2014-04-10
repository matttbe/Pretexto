package com.needsreal.social.profile;

import java.util.ArrayList;

import com.needsreal.social.search.Interest;
import com.needsreal.social.socialnetwork.SocialNetworkInterface;


public class User
{
	private String hash;
	private String nickname;
	private String firstname; // John
	private String lastname; // Doe
	private String city;
	private int postcode;
	private String country; // check Locale
	private String activityPro; // String?
	private ArrayList<Interest> interests;
	private ArrayList<Interest> needs;
	private ArrayList<SocialNetworkInterface> contacts;
	private String mood; // or Interest?

	public User (String hash, String nickname)
	{
		this.hash = hash;
		this.nickname = nickname;
	}

	/**
	 * @return the nickname
	 */
	public String getNickname ()
	{
		return nickname;
	}

	/**
	 * @param nickname the nickname to set
	 */
	public void setNickname (String nickname)
	{
		this.nickname = nickname;
	}

	/**
	 * @return the firstname
	 */
	public String getFirstname ()
	{
		return firstname;
	}

	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname (String firstname)
	{
		this.firstname = firstname;
	}

	/**
	 * @return the lastname
	 */
	public String getLastname ()
	{
		return lastname;
	}

	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname (String lastname)
	{
		this.lastname = lastname;
	}

	/**
	 * @return the city
	 */
	public String getCity ()
	{
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity (String city)
	{
		this.city = city;
	}

	/**
	 * @return the postcode
	 */
	public int getPostcode ()
	{
		return postcode;
	}

	/**
	 * @param postcode the postcode to set
	 */
	public void setPostcode (int postcode)
	{
		this.postcode = postcode;
	}

	/**
	 * @return the country
	 */
	public String getCountry ()
	{
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry (String country)
	{
		this.country = country;
	}

	/**
	 * @return the activityPro
	 */
	public String getActivityPro ()
	{
		return activityPro;
	}

	/**
	 * @param activityPro the activityPro to set
	 */
	public void setActivityPro (String activityPro)
	{
		this.activityPro = activityPro;
	}

	/**
	 * @return the interests
	 */
	public ArrayList<Interest> getInterests ()
	{
		return interests;
	}

	/**
	 * @param interests the interests to set
	 */
	public void setInterests (ArrayList<Interest> interests)
	{
		this.interests = interests;
	}

	/**
	 * @return the needs
	 */
	public ArrayList<Interest> getNeeds ()
	{
		return needs;
	}

	/**
	 * @param needs the needs to set
	 */
	public void setNeeds (ArrayList<Interest> needs)
	{
		this.needs = needs;
	}

	/**
	 * @return the contacts
	 */
	public ArrayList<SocialNetworkInterface> getContacts ()
	{
		return contacts;
	}

	/**
	 * @param contacts the contacts to set
	 */
	public void setContacts (ArrayList<SocialNetworkInterface> contacts)
	{
		this.contacts = contacts;
	}

	/**
	 * @return the mood
	 */
	public String getMood ()
	{
		return mood;
	}

	/**
	 * @param mood the mood to set
	 */
	public void setMood (String mood)
	{
		this.mood = mood;
	}

	/**
	 * @return the hash
	 */
	public String getHash ()
	{
		return hash;
	}
}
