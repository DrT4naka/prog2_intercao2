import java.util.List;

public class AlteradorSinaisVitais {
    public static void alterarTodos(List<Paciente> pacientes, double percentagem) {
        for (Paciente p : pacientes) {
            for (MedicaoPaciente m : p.getMedicoes()) {
                m.aplicarVariacao(percentagem);
            }
        }
    }
}