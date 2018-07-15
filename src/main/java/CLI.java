package src.main.java;

import java.util.List;
import java.util.Scanner;

public final class CLI implements Comandos {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final CLI CLI = new CLI();
    private static final Clientes CLIENTES = new Clientes();

    private CLI() {}

    public static CLI getInstance() {
        return CLI;
    }

    @Override
    public void iniciar() {
        int comando = telaInicial();
        while (comando != 0) {
            switch (comando) {
                case 1:
                    comando = addCliente();
                    break;
                case 2:
                    comando = exibirClientes();
                    break;
                case 3:
                    comando = buscarCliente();
                    break;
                case 4:
                    comando = removerCliente();
                    break;
                default:
                    System.out.println("Desculpe, comando inválido");
                    comando = telaInicial();
                    break;
            }
        }
        System.out.println("Saindo...");
        return;
    }

    @Override
    public int telaInicial() {
        System.out.println("Bem vindo ao Banco do Diego!");
        System.out.println("----------------------------");
        System.out.println("Comandos disponíveis:\n");
        System.out.println("1 - Adicionar um novo cliente");
        System.out.println("2 - Exibir todos os clientes");
        System.out.println("3 - Procurar um cliente");
        System.out.println("4 - Remover um cliente");
        System.out.println("0 - Sair\n");
        System.out.print("Comando: ");
        int comando = SCANNER.nextInt();
        SCANNER.nextLine();
        return comando;
    }

    @Override
    public int exibirClientes() {
        System.out.println("Clientes cadastrados no banco de dados:\n");
        CLIENTES.exibirClientes();
        return telaInicial();
    }
    @Override
    public int addCliente() {
        System.out.print("Qual o nome do cliente a ser adicionado? ");
        String nome = SCANNER.nextLine();
        while (nome.trim().isEmpty() || !Cliente.validarNome(nome)) {
            System.out.println("Nome inválido");
            System.out.print("Qual o nome do cliente a ser adicionado? ");
            nome = SCANNER.nextLine();
        }
        System.out.print("Qual o email? ");
        String email = SCANNER.nextLine();
        while (email.trim().isEmpty() || !Cliente.validarEmail(email)) {
            System.out.println("Email inválido");
            System.out.print("Qual o email? ");
            email = SCANNER.nextLine();
        }
        System.out.print("Qual o telefone? (Formato: (XX)XXXXXXXXX) ");
        String telefone = SCANNER.nextLine();
        while (telefone.trim().isEmpty() || !Cliente.validarTelefone(telefone)) {
            System.out.println("Telefone inválido");
            System.out.print("Qual o telefone? (Formato: (XX)XXXXXXXXX) ");
            telefone = SCANNER.nextLine();
        }
        Cliente novoCliente = new Cliente(nome, email, telefone);
        if (CLIENTES.addCliente(novoCliente)) {
            System.out.println("Cliente criado!");
        }
        return telaInicial();
    }

    @Override
    public int buscarCliente() {
        System.out.print("Nome do cliente a ser buscado: ");
        String nome = SCANNER.nextLine();
        while (nome.trim().isEmpty() || !Cliente.validarNome(nome)) {
            System.out.println("Nome inválido");
            System.out.print("Nome do cliente a ser buscado: ");
            nome = SCANNER.nextLine();
        }
        List<Cliente> clientesEncontrados = CLIENTES.buscarCliente(nome);
        if (clientesEncontrados == null || clientesEncontrados.isEmpty()) {
            System.out.println("Nenhum cliente encontrado");
            return telaInicial();
        }
        int i = 1;
        for (Cliente c : clientesEncontrados) {
            System.out.println(i + " - " + c);
        }
        System.out.print("Cliente a ser visualizado: ");
        int clienteProcurado = SCANNER.nextInt();
        SCANNER.nextLine();
        while (clienteProcurado < 1 || clienteProcurado > i) {
            System.out.println("Valor inválido");
            System.out.print("Cliente a ser visualizado: ");
            clienteProcurado = SCANNER.nextInt();
        }
        System.out.println("Cliente procurado:\n");
        System.out.println(clientesEncontrados.get(clienteProcurado - 1));
        return telaInicial();
    }

    @Override
    public int removerCliente() {
        System.out.print("Nome do cliente a ser removido: ");
        String clienteASerRemovido = SCANNER.nextLine();
        while (clienteASerRemovido.trim().isEmpty() || !Cliente.validarNome(clienteASerRemovido)) {
            System.out.println("Nome inválido!");
            System.out.print("Nome do cliente a ser removido: ");
            clienteASerRemovido = SCANNER.nextLine();
        }
        List<Cliente> clientesEncontrados = CLIENTES.buscarCliente(clienteASerRemovido);
        if (!clientesEncontrados.isEmpty()) {
            int i = 1;
            for (Cliente c : clientesEncontrados) {
                System.out.println(i + " - " + c);
            }
            System.out.print("Cliente a ser removido: ");
            int clienteProcurado = SCANNER.nextInt();
            SCANNER.nextLine();
            while (clienteProcurado < 1 || clienteProcurado > i) {
                System.out.println("Valor inválido");
                System.out.print("Cliente a ser removido: ");
                clienteProcurado = SCANNER.nextInt();
            }
            Cliente aSerRemovido = clientesEncontrados.get(clienteProcurado - 1);
            System.out.println("O seguinte cliente será removido do banco de dados:\n");
            System.out.println(aSerRemovido);
            System.out.print("Confirmar? (S / N) ");
            String confirmar = SCANNER.nextLine();
            while (!confirmar.trim().toUpperCase().matches("^[SN]$")) {
                System.out.println("Valor inválido!");
                System.out.print("Confirmar? (S / N) ");
                confirmar = SCANNER.nextLine();
            }
            if (confirmar.trim().toUpperCase().equals("S")) {
                if (CLIENTES.removerCliente(aSerRemovido)) {
                    System.out.println("Cliente removido. Saindo...");
                } else {
                    System.out.println("Cliente não removido, pois não foi encontrado");
                }
            } else {
                System.out.println("Cliente não removido, pois a confirmação falhou");
            }
        }
        return telaInicial();
    }
}
