package dao;
import java.util.Collections;
import java.util.HashSet;
import java.util.TreeMap;
import java.util.TreeSet;
import interfaces.Entity;
import interfaces.EntityCollection;

public class EntityImpl implements Entity {

	private int value;

	public EntityImpl(int value) {
		this.value = value;
	}

	@Override
	public int getValue() {
		return value;
	}
}

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

	private TreeMap<Integer, Entity> entities;

	public B() {
		entities = new TreeMap<>();

	}

	// O(log(n))
	@Override
	public void add(Entity entity) {
		entities.putIfAbsent(entity.getValue(), entity);

	}

	// O(log(n))
	@Override
	public Entity removeMaxValue() {
		Entity temp = entities.get(entities.lastKey());
		entities.remove(entities.lastKey());
		return temp;
	}
}

class C implements EntityCollection { // add-low , removeMax-high

	private TreeSet<Entity> entities;

	public C() {
		entities = new TreeSet<>((e1, e2) -> Integer.compare(e1.getValue(), e1.getValue()));
	}
	
	// O(log(n))
	@Override
	public void add(Entity entity) {
		entities.add(entity);
	}

	// O(1)
	@Override
	public Entity removeMaxValue() {
		Entity temp = entities.last();
		entities.remove(entities.last());
		return temp;
	}
}
