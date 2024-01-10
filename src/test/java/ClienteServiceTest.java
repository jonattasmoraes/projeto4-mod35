import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dao.ClienteDaoMock;
import dao.IClienteDAO;
import domain.Cliente;
import exceptions.DAOException;
import exceptions.TipoChaveNaoEncontradaException;
import services.ClienteService;
import services.IClienteService;

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
		cliente.setCpf(12312312312L);
		cliente.setNome("Carlos Eduardo");
		cliente.setCidade("Curitiba");
		cliente.setEnd("End A");
		cliente.setEstado("SP");
		cliente.setNumero(10);
		cliente.setTel(1199999999L);

	}

	@Test
	public void pesquisarCliente() throws DAOException {
		Cliente clienteConsultado = clienteService.buscarPorCPF(cliente.getCpf());
		Assert.assertNotNull(clienteConsultado);
	}

	@Test
	public void salvarCliente() throws TipoChaveNaoEncontradaException, DAOException {
		Boolean retorno = clienteService.cadastrar(cliente);

		Assert.assertTrue(retorno);
	}

	@Test
	public void excluirCliente() throws DAOException {
		clienteService.excluir(cliente.getCpf());
	}

	@Test
	public void alterarCliente() throws TipoChaveNaoEncontradaException, DAOException {
		cliente.setNome("Carlos Eduardo");
		clienteService.alterar(cliente);

		Assert.assertEquals("Carlos Eduardo", cliente.getNome());
	}
}
