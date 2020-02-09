package com.dataStore.model;

import java.util.ArrayList;

public class Node
{
	public Node(String path, String value, ArrayList<Node> children)
	{

		this.setPath(path);
		this.value = value;
		this.children = children;
	}

	private String path;
	private String value;
	private ArrayList<Node> children = null;

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	public ArrayList<Node> getChildren()
	{
		return children;
	}

	public void setChildren(ArrayList<Node> children)
	{
		this.children = children;
	}

	public String getPath()
	{
		return path;
	}

	public void setPath(String path)
	{
		this.path = path;
	}

}
