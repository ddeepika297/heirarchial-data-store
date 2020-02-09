package com.dataStore.client;

import com.dataStore.model.Node;

public interface Event
{

	enum EventType {
		CREATE, UPDATE, DELETE
	}

	void listen(EventType event);
	void register(Node observer,EventType event);
	void unRegister(Node observer,EventType event);
}
