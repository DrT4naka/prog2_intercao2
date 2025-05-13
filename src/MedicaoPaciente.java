/**
 * Representa uma medição dos sinais vitais de um paciente num momento específico.
 * Armazena a medição dos sinais vitais de um paciente e a data e hora da mesma.
 */
public class MedicaoPaciente {
    private final double fc;
    private final double temp;
    private final double sat;
    private final String dataHora;

    /**
     * Constrói uma nova medição com os valores fornecidos.
     * @param fc Frequência cardíaca (bpm)
     * @param temp Temperatura corporal (ºC)
     * @param sat Saturação de oxigénio (%)
     * @param dataHora Data e hora da medição (dd/MM/yyyy HH:mm)
     */
    public MedicaoPaciente(double fc, double temp, double sat, String dataHora) {
        this.fc = fc;
        this.temp = temp;
        this.sat = sat;
        this.dataHora = dataHora;
    }

    /**
     * @return A frequência cardíaca desta medição
     */
    public double getFc() {
        return fc;
    }

    /**
     * @return A temperatura corporal desta medição
     */
    public double getTemp() {
        return temp;
    }

    /**
     * @return A saturação de oxigénio desta medição
     */
    public double getSat() {
        return sat;
    }

    /**
     * @return A data e hora desta medição
     */
    public String getDataHora() {
        return dataHora;
    }
}
