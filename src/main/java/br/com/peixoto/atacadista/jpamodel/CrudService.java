package br.com.peixoto.atacadista.jpamodel;

/**
 *
 * @param <I> Input class
 * @param <O> Output class
 */
public interface CrudService<I, O> {
    O save(I requestBody);
    O update(Long objectId, I requestBody);
    void delete(Long objectId);

}
