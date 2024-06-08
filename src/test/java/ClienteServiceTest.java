package test.java;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import main.java.dao.IClienteDAO;
import main.java.domain.Cliente;
import main.java.services.ClienteService;
import main.java.services.IClienteService;
import test.java.dao.ClienteDaoMock;

public class ClienteServiceTest {
	private IClienteService clienteService;
	
	private Cliente cliente;
	
	public ClienteServiceTest() {
		IClienteDAO dao = new ClienteDaoMock();
		clienteService = new ClienteService(dao);
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
	}
	
	@Test
	public void pesquisarCliente() {

		Cliente clienteConsultado = clienteService.buscarPorCpf(cliente.getCpf());
		
		Assert.assertNotNull(clienteConsultado);
	}
	
	@Test
	public void salvarCliente() {
		Boolean retorno = clienteService.salvar(cliente);
		
		Assert.assertTrue(retorno);
	}
	
	@Test
	public void excluirCliente() {
		clienteService.excluir(cliente.getCpf());
	}
	
	@Test
	public void alterarCliente() {
		cliente.setNome("Mateus Raimundo");
		clienteService.alterar(cliente);
		
		Assert.assertEquals("Mateus Raimundo", cliente.getNome());
	}
}
