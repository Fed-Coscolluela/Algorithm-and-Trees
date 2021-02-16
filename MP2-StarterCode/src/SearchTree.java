public interface SearchTree<E> {
    public boolean insert(E item);
    public boolean exists(E target);
    public E search(E target);
    public E delete(E target);
}
