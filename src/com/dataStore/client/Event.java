package com.dataStore.client;

import com.dataStore.model.Node;

public interface Event
{

	enum EventType {
		CREATE, UPDATE, DELETE
	}

	void listen(EventType event);

	boolean register(Node observer, EventType event);

	boolean unRegister(Node observer, EventType event);
}
