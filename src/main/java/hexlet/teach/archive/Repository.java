package hexlet.teach.archive;

/**
 * @author andreiserov
 */
public interface Repository<ID, Entity> {

    public Entity save(Entity entity);
}
