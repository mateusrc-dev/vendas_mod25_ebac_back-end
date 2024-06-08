package test.java.dao;

import java.util.Collection;

import main.java.dao.IClienteDAO;
import main.java.domain.Cliente;

public class ClienteDaoMock implements IClienteDAO {

	@Override
	public Boolean cadastrar(Cliente entity) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Boolean excluir(Long valor) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Boolean alterar(Cliente entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cliente consultar(Long valor) {
		Cliente cliente = new Cliente();
		cliente.setCpf(valor);
		return cliente;
	}

	@Override
	public Collection<Cliente> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}



}
