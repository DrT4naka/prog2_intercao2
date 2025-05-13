import java.util.ArrayList;
import java.util.List;

/**
 * Representa um paciente no sistema de monitorização.
 * Armazena informações pessoais e histórico de medições de sinais vitais.
 * Implementa a interface Medicao para permitir registo de novas medições.
 */
public class Paciente extends Pessoa implements Medicao {
    private final List<MedicaoPaciente> medicoes;

    /**
     * Constrói um novo paciente com as informações fornecidas.
     * @param nome Nome do paciente
     * @param dataNascimento Data de nascimento do paciente (YYYY/MM/DD)
     * @param codigo Código identificador do paciente
     */
    public Paciente(String nome, String dataNascimento, int codigo) {
        super(nome, dataNascimento, codigo);
        this.medicoes = new ArrayList<>();
    }

    /**
     * Regista uma nova medição de sinais vitais deste paciente.
     * @param fc Frequência cardíaca (bpm)
     * @param temp Temperatura Corporal (ºC)
     * @param sat Saturação de Oxigénio (%)
     * @param dataHora Data e hora da medição (dd/MM/yyyy HH:mm)
     */
    @Override
    public void registarMedicao(double fc, double temp, double sat, String dataHora) {
        medicoes.add(new MedicaoPaciente(fc, temp, sat, dataHora));
    }

    /**
     * @return String com todas as informações do paciente e as suas medições.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Paciente: ").append(nome)
                .append("   Código: ").append(codigo)
                .append("   Data de Nascimento: ").append(dataNascimento)
                .append("\nMedições:\n");

        if (medicoes.isEmpty()) {
            sb.append("  Nenhuma medição registrada.\n");
        } else {
            for (MedicaoPaciente m : medicoes) {
                sb.append("  FC: ").append(m.getFc())
                        .append("   Temp: ").append(m.getTemp())
                        .append("   Sat: ").append(m.getSat())
                        .append("   Data/Hora: ").append(m.getDataHora())
                        .append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * @return Lista de todas as medições deste paciente
     */
    public List<MedicaoPaciente> getMedicoes() {
        return medicoes;
    }

    /**
     * Classifica o paciente com base nos seus últimos sinais vitais registados.
     * @return "Crítico", "Atenção" ou "Normal" conforme os valores dos sinais vitais
     */
    public String classificarPaciente() {
        if (medicoes.isEmpty()) {
            return "Sem medições";
        }
        MedicaoPaciente ultima = medicoes.get(medicoes.size() - 1);
        double fc = ultima.getFc();
        double temp = ultima.getTemp();
        double sat = ultima.getSat();
        if ((fc < 60 || fc > 120) || (temp < 36 || temp > 38.5) || (sat < 90)) {
            return "Crítico";
        } else if ((fc > 100 && fc <= 120) || (temp > 37.5 && temp <= 38.5) || (sat >= 90 && sat < 95)) {
            return "Atenção";
        }
        return "Normal";
    }
}

