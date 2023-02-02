package dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.TreeSet;
import interfaces.Entity;
import interfaces.EntityCollection;

class A implements EntityCollection { // add-high , removeMax-low

	private HashSet<Entity> entities;

	public A() {
		entities = new HashSet<>();
	}

	// O(1)
	@Override
	public void add(Entity entity) {
		entities.add(entity);
	}

	// O(n)
	@Override
	public Entity removeMaxValue() {
		Entity maxValue = Collections.max(entities, (e1, e2) -> e1.getValue() - e2.getValue());
		entities.remove(maxValue);
		return maxValue;
	}
}

class B implements EntityCollection { // add-medium , removeMax-medium

	private TreeSet<Entity> entities;

	public B() {
		entities = new TreeSet<>((e1, e2) -> e1.getValue() - e2.getValue());
	}

	// O(log(n))
	@Override
	public void add(Entity entity) {
		entities.add(entity);
	}

	// O(log(n))
	@Override
	public Entity removeMaxValue() {
		return entities.pollLast();
	}
}

class C implements EntityCollection { // add-Low, removeMax-High

	private ArrayList<Entity> entities;

	public C() {
		entities = new ArrayList<Entity>();
	}

	// O(n)
	@Override
	public void add(Entity entity) {
		if (entity != null) {

			int index = Collections.binarySearch(entities, entity, (e1, e2) -> e1.getValue() - e2.getValue());
			if (index >= 0) {

			} else {
				index = -Collections.binarySearch(entities, entity, (e1, e2) -> e1.getValue() - e2.getValue()) - 1;
				entities.add(index, entity);
			}
		}

	}

	// O(1)
	@Override
	public Entity removeMaxValue() {
		return entities.remove(entities.size() - 1);
	}
}
