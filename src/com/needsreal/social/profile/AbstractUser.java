package com.needsreal.social.profile;

import java.util.ArrayList;

import com.google.android.gms.maps.model.LatLng;
import com.needsreal.social.search.Interest;
import com.needsreal.social.socialnetwork.SocialNetworkInterface;


public abstract class AbstractUser
{
	private String hash;
	private String nickname;

	protected String firstname; // John
	protected String lastname; // Doe

	protected String shortDesc;
	protected String description;
	protected String activityPro;

	protected String city;
	protected int postcode;
	protected int country; // check Locale

	protected Mood mood; // a picture
	protected ArrayList<Interest> interests;
	protected ArrayList<Interest> needs;
	protected ArrayList<SocialNetworkInterface> contacts;

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
	 * @return the short description
	 */
	public String getShortDesc ()
	{
		return shortDesc;
	}

	/**
	 * @param shortDesc the short description to set
	 */
	public void setShortDesc (String shortDesc)
	{
		this.shortDesc = shortDesc;
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
	 * @return the last known position
	 */
	public abstract LatLng getPosition ();

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
	public Mood getMood ()
	{
		return mood;
	}

	/**
	 * @param mood the mood to set
	 */
	public void setMood (Mood mood)
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
