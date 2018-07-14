import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

public class Cliente {
    private UUID ID;
    private String nome;
    private String email;
    private String telefone;
    private String senha;
    private Conta[] contas;

    public static boolean validarNome(String nome) {
        if (!nome.matches("^[A-Z]{2,}.")) {
            return false;
        }
        return true;
    }

    public static boolean validarEmail(String email) {
        if (!email.matches("^\\w+@\\w+\\.\\w+$")) {
            return false;
        }
        return true;
    }

    public static boolean validarTelefone(String telefone) {
        if (!telefone.matches("^\\([0-9]{2}\\)[0-9]{9}$")) {
            return false;
        }
        return true;
    }

    public Cliente(String nome, String email, String telefone) {
        if (!validarNome(nome) || !validarEmail(email) || !validarTelefone(telefone)) {
            return;
        }
        this.ID = UUID.randomUUID();
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.contas = new Conta[2];
    }

    public Conta[] getContas() {
        return contas;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public UUID getID() {
        return ID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(ID, cliente.ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }

    @Override
    public String toString() {
        return "Nome: " + nome + "\n" +
                "Email: " + email + "\n" +
                "Telefone: " + telefone + "\n" +
                "ID: " + ID + "\n" +
                "Contas: " + Arrays.asList(contas) + "\n";
    }
}
