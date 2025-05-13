/**
 * Interface que define o registo de medições de sinais vitais.
 */
public interface Medicao {
     /**
      * Regista uma nova medição de sinais vitais.
      * @param fq Frequência cardíaca (bpm)
      * @param temp Temperatura corporal (ºC)
      * @param sat Saturação de oxigénio (%)
      * @param dataHora Data e hora da medição dos sinais vitais (dd/MM/yyyy HH:mm)
      */
      void registarMedicao(double fq, double temp, double sat,String dataHora);
}
