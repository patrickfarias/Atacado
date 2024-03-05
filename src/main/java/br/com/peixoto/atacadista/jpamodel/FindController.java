package br.com.peixoto.atacadista.jpamodel;

import java.util.List;

/**
 *
 * @param <I> Input class
 * @param <O> Output class
 */
public interface FindController<I, O> {
    List<O> findAll();
    O findById(Long objectId);

}
