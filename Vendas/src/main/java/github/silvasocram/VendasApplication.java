package github.silvasocram;

import github.silvasocram.domain.entity.Cliente;
import github.silvasocram.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.List;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init (@Autowired ClienteRepository clienteRepository){
        return args -> {
            clienteRepository.save(new Cliente("Marcos M. Silva"));
            clienteRepository.save(new Cliente("Janaíra C. Alves"));
            clienteRepository.save(new Cliente("Mayara C. Silva"));
            clienteRepository.save(new Cliente("Vou remover este cliente"));
            clienteRepository.save(new Cliente("Bananinha C. Alves"));
            clienteRepository.save(new Cliente("Gordo Silva"));
            clienteRepository.save(new Cliente("Pe de Pano Silva"));
            clienteRepository.save(new Cliente("Toph C. Alves"));

            System.out.println("#### LISTAR CLIENTES QUE FORAM INSERIDOS ####");

            List<Cliente> obterTodosClientes = clienteRepository.findAll();
            obterTodosClientes.forEach(System.out::println);

            System.out.println("#### ALTERANDO NOME DO CLIENTE ####");

            obterTodosClientes.forEach(cliente -> {
                String nome = cliente.getNome() + " - Alterado Spring Data";
                cliente.setNome(nome);
                clienteRepository.save(cliente);
                System.out.println(cliente.getNome());
            });

            System.out.println("#### REMOVER CLIENTE COM ID = 4 e ID = 6");
            obterTodosClientes.forEach(cliente -> {
                if(cliente.getId() == 4){
                    clienteRepository.delete(cliente);
                }
            });

            clienteRepository.deleteById(6);

            System.out.println("#### LISTAR TODOS OS CLIENTES - Após exclusão");
            obterTodosClientes = clienteRepository.findAll();
            obterTodosClientes.forEach(System.out::println);

            System.out.println("#### LISTAR POR NOME ####");
            clienteRepository.findByNomeContaining("Alves").forEach(System.out::println);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
