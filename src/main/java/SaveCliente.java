import br.edu.ifpb.dac.projeto.dao.ClienteDao;
import br.edu.ifpb.dac.projeto.entities.Cliente;
import br.edu.ifpb.dac.projeto.entities.Endereco;
import br.edu.ifpb.dac.projeto.enumerations.Estados;
import br.edu.ifpb.dac.projeto.exceptions.PartsShopException;

public class SaveCliente {

	public static void main(String[] args) throws PartsShopException {
		ClienteDao dao = new ClienteDao();
		
			Cliente cliente = new Cliente();
			Endereco endereco = new Endereco();
			
			cliente.setNome("Emerson Davi");
			cliente.setCpf("113.21.544-75");
			cliente.setCelular("(87) 99134-9106");
			cliente.setTelefone("(87) 3841-1844");
			cliente.setSexo("M");
			cliente.setEmail("emersondavi2013@gmail.com");
			
			endereco.setCep("56600-000");
			endereco.setCidade("Sertânia");
			endereco.setComplemento("Casa");
			endereco.setEstado(Estados.PE);
			endereco.setLogradouro("Avenida Joaquim Nabuco");
			endereco.setNumero("154");
			endereco.setZona("Zona Urbana");
			endereco.setBairro("Centro");
			
			cliente.setEndereco(endereco);
			
			System.out.println(cliente);
			
			dao.add(cliente);

			System.out.println(cliente);
			
		
	}
}