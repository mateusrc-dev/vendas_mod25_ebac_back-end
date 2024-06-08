package main.java.dao.generics;

import java.util.Collection;

import main.java.domain.Persistente;

public interface IGenericDAO <T extends Persistente> {
	public Boolean cadastrar(T entity);
	public Boolean excluir(Long valor);
	public Boolean alterar(T entity);
	public T consultar(Long valor);
	public Collection<T> buscarTodos();
}
