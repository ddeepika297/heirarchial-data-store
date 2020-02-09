package com.dataStore.handler;

import java.util.ArrayList;
import java.util.List;

import com.dataStore.client.Event;
import com.dataStore.model.Node;

public class EventHandler implements Event
{
	protected final List<Node> createEventListners = new ArrayList<Node>();
	protected final List<Node> updateEventListners = new ArrayList<Node>();
	protected final List<Node> deleteEventListners = new ArrayList<Node>();
	private final Object MUTEX = new Object();

	@Override
	public void listen(EventType event)
	{
		if (event == EventType.CREATE)
		{
			System.out.println(" \tListner : Add child event");
		} else if (event == EventType.UPDATE)
		{
			System.out.println("\t Listner : Update child event ");
		} else if (event == EventType.DELETE)
		{
			System.out.println(" \tListner : Delete child event");
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
