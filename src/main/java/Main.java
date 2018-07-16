package src.main.java;

public class Main {
    public static void main(String[] args) {
        /*CLI cli = CLI.getInstance();
        cli.iniciar();*/
        ClienteBean cliente = new ClienteBean();
        cliente.setNome("DIEGO_TEST");
        cliente.setEmail("whatever@gmail.com");
        cliente.setTelefone("(75)999999999");
        cliente.setSenha("123456");

        ClienteDAO dao = new ClienteDAO();
        dao.insertUser(cliente);
    }
}
