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

	protected ArrayList<Interest> interests;
	protected ArrayList<Interest> needs;
	protected ArrayList<SocialNetworkInterface> contacts;

	public AbstractUser (String hash, String nickname)
	{
		this.hash = hash;
		this.nickname = nickname;
		interests = new ArrayList<Interest> ();
		needs = new ArrayList<Interest> ();
		contacts = new ArrayList<SocialNetworkInterface> ();
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
	 * @return the interests list
	 */
	public ArrayList<Interest> getInterests ()
	{
		return interests;
	}

	/**
	 * @param interest the interest to add
	 */
	public void addInterest (Interest interest)
	{
		interests.add (interest);
	}

	/**
	 * @param interest the interest to remove
	 */
	public void removeInterest (Interest interest)
	{
		interests.remove (interest);
	}

	/**
	 * @return the needs list
	 */
	public ArrayList<Interest> getNeeds ()
	{
		return needs;
	}

	/**
	 * @param need the need to add
	 */
	public void addNeed (Interest need)
	{
		needs.add (need);
	}

	/**
	 * @param need the need to remove
	 */
	public void removeNeed (Interest need)
	{
		needs.remove (need);
	}

	/**
	 * @return the contacts list
	 */
	public ArrayList<SocialNetworkInterface> getContacts ()
	{
		return contacts;
	}

	/**
	 * @param socialNetwork the social network to add
	 */
	public void addContacts (SocialNetworkInterface socialNetwork)
	{
		contacts.add (socialNetwork);
	}

	/**
	 * @param interest the interest to remove
	 */
	public void removeContacts (SocialNetworkInterface socialNetwork)
	{
		contacts.remove (socialNetwork);
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
