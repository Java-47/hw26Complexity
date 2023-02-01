package dao;
import java.util.HashSet;
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

class ABC implements EntityCollection { 

	private HashSet<Entity> entities;

	private Entity maxEntity = null;

	public ABC() {
		entities = new HashSet<>();
	}

	// O(1)
	@Override
	public void add(Entity entity) {
		if (maxEntity == null) {
			maxEntity = entity;
		} else {
			if (maxEntity.getValue() < entity.getValue()) {
				maxEntity = entity;
			}
		}
		entities.add(entity);
	}

	// O(1)
	@Override
	public Entity removeMaxValue() {
		entities.remove(maxEntity);
		return maxEntity;
	}
}
