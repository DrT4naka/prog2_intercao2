/**
 * Classe abstrata que representa uma pessoa no sistema.
 * Serve como base tanto para pacientes quanto para profissionais de saúde.
 */
public abstract class Pessoa {
    protected String nome;
    protected String dataNascimento;
    protected int codigo;

    /**
     * Constrói uma nova pessoa com os dados fornecidos.
     *
     * @param nome Nome da pessoa
     * @param dataNascimento Data de nascimento da pessoa
     * @param codigo Código numérico único de identificação
     */
    public Pessoa(String nome, String dataNascimento, int codigo) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.codigo = codigo;
    }

    /**
     * @return O nome da pessoa
     */
    public String getNome() {
        return nome;
    }

    /**
     * @return A data de nascimento da pessoa
     */
    public String getDataNascimento() {
        return dataNascimento;
    }

    /**
     * @return O código identificador da pessoa
     */
    public int getCodigo() {
        return codigo;
    }
}