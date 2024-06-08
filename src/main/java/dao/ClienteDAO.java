package main.java.dao;

import main.java.dao.generics.GenericDAO;
import main.java.domain.Cliente;

public class ClienteDAO extends GenericDAO<Cliente> implements IClienteDAO {
	public ClienteDAO() {
		super();
	}

	@Override
	public Class<Cliente> getTipoClasse() {
		// TODO Auto-generated method stub
		return Cliente.class;
	}

	@Override
	public void atualizarDados(Cliente entity, Cliente entityCadastrado) {
		// TODO Auto-generated method stub
		
	}
	

	
}
