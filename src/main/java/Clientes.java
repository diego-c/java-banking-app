import java.util.*;

public class Clientes {
    private Set<Cliente> clientes;

    public Clientes() {
        this.clientes = new HashSet<>();
    }

    public boolean addCliente(Cliente c) {
        if (clientes.contains(c)) {
            System.out.println("O cliente já se encontra no banco de dados");
            return false;
        }
        return clientes.add(c);
    }
    // remover diretamente o objeto Cliente em vez de encontrá-lo pelo UUID
    public boolean removerCliente(Cliente c) {
        Iterator<Cliente> iterator = clientes.iterator();
        while (iterator.hasNext()) {
            Cliente atual = iterator.next();
            if (atual.equals(c)) {
                iterator.remove();
                return true;
            }
        }
        System.out.println("Cliente não encontrado");
        return false;
    }

    public List<Cliente> buscarCliente(String nome) {
        List<Cliente> encontrados = new ArrayList<>();

        for (Cliente c : clientes) {
            if (c.getNome().trim().toUpperCase().equals(nome.trim().toUpperCase())) {
                encontrados.add(c);
            }
        }

        if (encontrados.isEmpty()) {
            System.out.println("Nenhum cliente foi encontrado");
            return null;
        }
        return encontrados;
    }
    public void exibirClientes() {
        int count = 1;
        if (clientes == null || clientes.isEmpty()) {
            System.out.println("Não há nenhum cliente cadastrado no banco de dados");
        } else {
            for (Cliente c : clientes) {
                System.out.println(count + " - " + c);
                count++;
            }
        }
    }
    public void abrirConta(Cliente c, Contas tipoDeConta) {
        Conta[] contasDoCliente = c.getContas();
        if (contasDoCliente[0] == null) {
            if (tipoDeConta == Contas.BASICA) {

            }
        } else if (contasDoCliente[1] == null) {

        } else {
            System.out.println("O cliente já possui duas contas cadastradas em seu nome");
        }
    }
}
