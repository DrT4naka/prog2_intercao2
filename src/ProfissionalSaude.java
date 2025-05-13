/**
 * Representa um profissional de saúde no sistema.
 * Contém informações sobre a sua especialidade profissional.
 */
public class ProfissionalSaude extends Pessoa {
    private final String profissao;

    /**
     * Constrói um novo profissional de saúde.
     * @param nome Nome do profissional de saúde
     * @param dataNascimento Data de nascimento do profissional de saúde
     * @param codigo Código numérico único de identificação do profissional de saúde
     * @param profissao Profissão/especialidade do profissional de saúde (ex. cardiologista, enfermeiro)
     */
    public ProfissionalSaude(String nome, String dataNascimento, int codigo,String profissao) {
        super(nome, dataNascimento,codigo);
        this.profissao = profissao;
    }

    /**
     *
     * @return String formatada com todas as informações do profissional de saúde
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Valores registados por:")
                .append("   Código: ").append(codigo)
                .append("   ").append(profissao)
                .append("   ").append(nome).append("\n");
        return sb.toString();
    }
}
