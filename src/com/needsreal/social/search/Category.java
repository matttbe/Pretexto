package com.needsreal.social.search;

public class Category
{
	private int id;
	private String name;
	private String description;

	public Category (int id, String name, String description)
	{
		this.id = id;
		this.name = name;
		this.description = description;
	}

	/**
	 * @return the id
	 */
	public int getId ()
	{
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName ()
	{
		return name;
	}

	/**
	 * @return the description
	 */
	public String getDescription ()
	{
		return description;
	}
}
