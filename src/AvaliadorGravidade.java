public class AvaliadorGravidade {

    public static double calcularScore(MedicaoPaciente m) {
        int fcScore = pontuarFC(m.getFc());
        int tempScore = pontuarTemp(m.getTemp());
        int satScore = pontuarSat(m.getSat());
        return fcScore * 0.3 + tempScore * 0.4 + satScore * 0.3;
    }

    private static int pontuarFC(double fc) {
        if (fc < 40 || fc > 130) return 5;
        if (fc < 50 || fc > 110) return 4;
        if (fc < 60 || fc > 100) return 3;
        if (fc < 65 || fc > 95) return 2;
        return 1;
    }

    private static int pontuarTemp(double temp) {
        if (temp < 34 || temp > 40) return 5;
        if (temp < 35 || temp > 39) return 4;
        if (temp < 36 || temp > 38) return 3;
        if (temp < 36.5 || temp > 37.5) return 2;
        return 1;
    }

    private static int pontuarSat(double sat) {
        if (sat < 70) return 5;
        if (sat < 80) return 4;
        if (sat < 85) return 3;
        if (sat < 90) return 2;
        return 1;
    }
}