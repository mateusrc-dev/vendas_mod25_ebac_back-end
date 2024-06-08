package main.java.dao.generics;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import main.java.anotacao.anotacao.TipoChave;
import main.java.anotacao.domain.Cliente;
import main.java.anotacao.domain.Produto;
import main.java.domain.Persistente;

public abstract class GenericDAO<T extends Persistente> implements IGenericDAO<T> {

	protected Map<Class, Map<Long, T>> map;
	
	public abstract Class<T> getTipoClasse();
	public abstract void atualizarDados(T entity, T entityCadastrado);
	
	public GenericDAO() {
		if (this.map == null) {
			this.map = new HashMap<>();
		}
	}
	
	public Long getChave(T entity) {
		Field[] fields = entity.getClass().getDeclaredFields();
		
		for (Field field : fields) {
			if (field.isAnnotationPresent(TipoChave.class)) {
				TipoChave tipoChave = field.getAnnotation(TipoChave.class);
				String nomeMetodo = tipoChave.value();
				try {
					Method method = entity.getClass().getMethod(nomeMetodo);
					Object value = method.invoke(entity);
					return (Long) value;
				} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					// criar exception de negócio chamada TipoChaveNaoEncontradaException
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	@Override
	public Boolean cadastrar(T entity) {
		Map<Long, T> mapaInterno = this.map.get(getTipoClasse());
		Long chave = getChave(entity);
		if (mapaInterno.containsKey(chave)) {
			return false;
		}
		
		mapaInterno.put(chave, entity);
		return true;
	}

	@Override
	public Boolean excluir(Long valor) {
		Map<Long, T> mapaInterno = this.map.get(getTipoClasse());
		
		T objetoCadastrado = mapaInterno.get(valor);
		
		if (objetoCadastrado != null) {
			mapaInterno.remove(valor, objetoCadastrado);
			
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Boolean alterar(T entity) {
		Map<Long, T> mapaInterno = this.map.get(getTipoClasse());
		Long chave = getChave(entity);
		T entityCadastrado = mapaInterno.get(chave);
		
		if (entityCadastrado != null) {
			atualizarDados(entity, entityCadastrado);
			
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Collection<T> buscarTodos() {
		Map<Long, T> mapaInterno = this.map.get(getTipoClasse());
		
		return mapaInterno.values();
	}

	@Override
	public T consultar(Long valor) {
		Map<Long, T> mapaInterno = this.map.get(getTipoClasse());
		
		return mapaInterno.get(valor);
	}
	
	
	
	
	
}
