package com.dataStore.model;

import java.util.ArrayList;

import com.dataStore.service.Event;

public class EventHandler implements Event
{
	protected ArrayList<Node> createEventListners = new ArrayList<Node>();
	protected ArrayList<Node> updateEventListners = new ArrayList<Node>();
	protected ArrayList<Node> deleteEventListners = new ArrayList<Node>();
	private final Object MUTEX = new Object();

	@Override
	public void listen(EventType event)
	{
		if (event == EventType.CREATE)
		{
			System.out.println(" \tListner : Add child");
		} else if (event == EventType.UPDATE)
		{
			System.out.println("\t Listner : Update child");
		} else if (event == EventType.DELETE)
		{
			System.out.println(" \tListner : Delete child");
		}
	}

	@Override
	public void register(Node observer, EventType event)
	{
		if (observer == null)
			throw new NullPointerException();
		if (event == EventType.CREATE)
		{
			synchronized (MUTEX)
			{
				if (!createEventListners.contains(observer))
					createEventListners.add(observer);
			}
		}
		if (event == EventType.UPDATE)
		{
			synchronized (MUTEX)
			{
				if (!updateEventListners.contains(observer))
					updateEventListners.add(observer);
			}
		}
		if (event == EventType.DELETE)
		{
			synchronized (MUTEX)
			{
				if (!deleteEventListners.contains(observer))
					deleteEventListners.add(observer);
			}
		}
	}

	@Override
	public void unRegister(Node observer, EventType event)
	{
		if (event == EventType.CREATE)
			synchronized (MUTEX)
			{
				createEventListners.remove(observer);
			}
		if (event == EventType.UPDATE)
			synchronized (MUTEX)
			{
				updateEventListners.remove(observer);
			}
		if (event == EventType.DELETE)
			synchronized (MUTEX)
			{
				deleteEventListners.remove(observer);
			}
	}
}
