package com.needsreal.social.profile;

import java.util.ArrayList;

import com.needsreal.social.search.Interest;
import com.needsreal.social.socialnetwork.SocialNetworkInterface;


public abstract class AbstractUser
{
	private String hash;
	private String nickname;

	protected String firstname; // John
	protected String lastname; // Doe

	protected String shortDescription;
	protected String description;
	protected String activityPro; // String?

	protected String city;
	protected int postcode;
	protected int country; // check Locale

	protected ArrayList<Interest> interests;
	protected ArrayList<Interest> needs;
	protected ArrayList<SocialNetworkInterface> contacts;
	protected String mood; // or Interest?

	public AbstractUser (String hash, String nickname)
	{
		this.hash = hash;
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
	 * @return the shortDescription
	 */
	public String getShortDescription ()
	{
		return shortDescription;
	}

	/**
	 * @param shortDescription the shortDescription to set
	 */
	public void setShortDescription (String shortDescription)
	{
		this.shortDescription = shortDescription;
	}

	/**
	 * @return the description
	 */
	public String getDescription ()
	{
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription (String description)
	{
		this.description = description;
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
	 * @return the country id
	 */
	public int getCountryId ()
	{
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountryId (int country)
	{
		this.country = country;
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

	/**
	 * @return the nickname
	 */
	public String getNickname ()
	{
		return nickname;
	}
}
