package github.silvasocram;

import github.silvasocram.domain.entity.Cliente;
import github.silvasocram.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.SchemaOutputResolver;
import java.util.List;

@SpringBootApplication
@RestController
public class VendasApplication {

    @Bean
    public CommandLineRunner init (@Autowired ClienteRepository clienteRepository){
        return args -> {
            clienteRepository.salvar(new Cliente("Marcos M. Silva"));
            clienteRepository.salvar(new Cliente("Janaíra C. Alves"));
            clienteRepository.salvar(new Cliente("Mayara C. Silva"));
            clienteRepository.salvar(new Cliente("Vou remover este cliente"));
            clienteRepository.salvar(new Cliente("Bananinha C. Alves"));
            clienteRepository.salvar(new Cliente("Gordo Silva"));
            clienteRepository.salvar(new Cliente("Pe de Pano Silva"));
            clienteRepository.salvar(new Cliente("Toph C. Alves"));

            System.out.println("#### LISTAR CLIENTES QUE FORAM INSERIDOS ####");

            List<Cliente> obterTodosClientes = clienteRepository.obterTodos();
            obterTodosClientes.forEach(System.out::println);

            System.out.println("#### ALTERANDO NOME DO CLIENTE ####");

            obterTodosClientes.forEach(cliente -> {
                String nome = cliente.getNome() + " - Alterado JPA";
                cliente.setNome(nome);
                clienteRepository.atualizar(cliente);
                System.out.println(cliente.getNome());
            });

            System.out.println("#### REMOVER CLIENTE COM ID = 4");
            obterTodosClientes.forEach(cliente -> {
                if(cliente.getId() == 4){
                    clienteRepository.deletar(cliente);
                }
            });

            clienteRepository.deletar(6);

            System.out.println("#### LISTAR TODOS OS CLIENTES - Após exclusão");
            obterTodosClientes = clienteRepository.obterTodos();
            obterTodosClientes.forEach(System.out::println);

            System.out.println("#### LISTAR POR NOME ####");
            List<Cliente> buscarPorNome = clienteRepository.buscarPorNome("Alves");
            buscarPorNome.forEach(System.out::println);
        };
    }

    @GetMapping("/api")
    public String getAllClients(@Autowired ClienteRepository clienteRepository){
        List<Cliente> obterTodosClientes = clienteRepository.obterTodos();
        return  obterTodosClientes.get(1).toString();
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
