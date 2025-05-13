public class GraficoBarras {
    public static void imprimir(MedicaoPaciente m) {
        System.out.println("Frequência cardíaca: " + "*".repeat((int)m.getFc()/10));
        System.out.println("Temperatura: " + "*".repeat((int)(m.getTemp())));
        System.out.println("Saturação: " + "*".repeat((int)m.getSat()/10));
    }
}