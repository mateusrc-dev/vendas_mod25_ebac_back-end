package test.java;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import main.java.dao.IClienteDAO;
import main.java.domain.Cliente;
import test.java.dao.ClienteDaoMock;

public class ClienteDAOTest {
	private IClienteDAO clienteDao;
	private Cliente cliente;
	
	public ClienteDAOTest() {
		clienteDao = new ClienteDaoMock();
	}
	
	@Before
	public void init() {
		cliente = new Cliente();
		cliente.setCpf(1234567890L);
		cliente.setNome("Mateus");
		cliente.setCidade("Teresina");
		cliente.setEnd("End");
		cliente.setEstado("PI");
		cliente.setNumero(10);
		cliente.setTel(1233445L);
		
		clienteDao.cadastrar(cliente);
	}
	
	@Test
	public void pesquisarCliente() {
		Cliente clienteConsultado = clienteDao.consultar(cliente.getCpf());
		
		Assert.assertNotNull(clienteConsultado);
	}
	
	@Test
	public void salvarCliente() {
		Boolean retorno = clienteDao.cadastrar(cliente);
		
		Assert.assertTrue(retorno);
	}
	
	@Test
	public void excluirCliente() {
		clienteDao.excluir(cliente.getCpf());
	}
	
	@Test
	public void alterarCliente() {
		cliente.setNome("Mateus Raimundo");
		clienteDao.alterar(cliente);
		
		Assert.assertEquals("Mateus Raimundo", cliente.getNome());
	}
}	
