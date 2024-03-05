package br.com.peixoto.atacadista.jpamodel;

/**
 *
 * @param <I> Input class
 * @param <O> Output class
 */
public interface MergeService<I, O> {
    O merge(I requestBody);
    I getObjectAtual(Long objectId, Object requestBody);

}
