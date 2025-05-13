import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Classe principal do sistema de monitoramento de pacientes.
 * Gerencia o perfil dos profissinais e pacientes, e fornece relatórios.
 */
public class Main {
    static Scanner sc = new Scanner(System.in);
    private static String nome, dataNascimento;
    private static int codigo;
    private static List<ProfissionalSaude> profissinais = new ArrayList<>();
    private static List<Paciente> pacientes = new ArrayList<>();
    private static int opcao = 0;
    private static int escolha = 0;
    private static double somaFC = 0, somaTemp = 0, somaSat = 0;
    private static double somaFC2 = 0, somaTemp2 = 0, somaSat2 = 0;
    private static double minFC = Double.MAX_VALUE, maxFC = Double.MIN_VALUE; // em vez de definirmos um valor mínimo ou maximo o programa define sozinho
    private static double minTemp = Double.MAX_VALUE, maxTemp = Double.MIN_VALUE;
    private static double minSat = Double.MAX_VALUE, maxSat = Double.MIN_VALUE;

    /**
     * Inicia o programa e carrega os dados iniciais.
     *
     * @param args
     * @throws IOException
     * @throws ParseException
     */
    public static void main(String[] args) throws IOException, ParseException {
        registarProfissional();
        gerarPacientesAutomaticamente();

        do {
            mostrarOpcoes();
        } while (opcao != 7);
    }

    /**
     * Regista o profissional de saúde utilizando um profissional já autenticado ou inserindo um novo profissional.
     */
    private static void registarProfissional() {
        int opcao = -1;
        
        while (true) {
            System.out.println("Escolha uma das opções:");
            System.out.println("1. Continuar com o perfil do profissional de saúde já cadastrado.");
            System.out.println("2. Alterar perfil de acesso.");
            
            if (sc.hasNextInt()) {
                opcao = sc.nextInt();
                sc.nextLine(); // limpar o buffer
                if (opcao == 1 || opcao == 2) {
                    break; // valor válido
                } else {
                    System.out.println("Valor inválido. Por favor, escolha 1 ou 2.");
                }
            } else {
                System.out.println("Entrada inválida. Insira um número.");
                sc.nextLine(); // limpar entrada inválida
            }
        }
        
        if (opcao == 1) { 
            ProfissionalSaude ps1 = new ProfissionalSaude("Dr. Filipe Almeida", "12/05/1988", 55894, "Cardiologista");
            profissinais.add(ps1);
        } else if (opcao == 2) {
            System.out.println("Nome:");
            String nome = sc.nextLine();
            
            String dataNascimento;
            while (true) {
                System.out.println("Data de Nascimento (DD/MM/AAAA):");
                dataNascimento = sc.nextLine();
                if (dataNascimento.matches("^\\d{2}/\\d{2}/\\d{4}$")) {
                    break;
                } else {
                    System.out.println("Formato inválido! Use DD/MM/AAAA.");
                }
            }
            
            int codigo = -1;
            while (true) {
                System.out.println("Código:");
                if (sc.hasNextInt()) {
                    codigo = sc.nextInt();
                    sc.nextLine(); // limpar o buffer
                    break;
                } else {
                    System.out.println("Código inválido. Insira um número.");
                    sc.nextLine(); // limpar entrada inválida
                }
            }
            
            System.out.println("Profissão:");
            String prof = sc.nextLine();
            
            ProfissionalSaude ps1 = new ProfissionalSaude(nome, dataNascimento, codigo, prof);
            profissinais.add(ps1);
        }
    }

    /**
     * Cria pacientes exemplo com medições pré-definidas.
     * Usado para demonstração e testes.
     */
    private static void gerarPacientesAutomaticamente() {
        Paciente p1 = new Paciente("João Silva", "1990/05/12", 12345);
        p1.registarMedicao(54, 37.8, 99, "03/05/2022 16:45");
        pacientes.add(p1);
        Paciente p2 = new Paciente("Maria Andrade", "2006/09/11", 23456);
        p2.registarMedicao(80, 39, 87, "23/07/2024 10:18");
        pacientes.add(p2);
        Paciente p3 = new Paciente("Carlos Barros", "1996/03/19", 34567);
        p3.registarMedicao(110, 36.4, 98, "14/03/2021 11:22");
        pacientes.add(p3);
        Paciente p4 = new Paciente("Joana Marques", "2002/07/21", 39264);
        p4.registarMedicao(94, 35.8, 93, "03/02/2025 04:38");
        pacientes.add(p4);
        System.out.println("\nPacientes previamente introduzidos.\n");
        System.out.printf("%-8s  %-20s  %-12s \n", "Código:", "Nome:", "Classificação:");
        for (Paciente p : pacientes) { //output de tabela com a classificação dos pacientes
            System.out.printf("%-8s  %-20s  %-12s \n", p.getCodigo(), p.getNome(), p.classificarPaciente());
        }
    }

    /**
     * Mostra o menu de opções e executa as opções conforme o que o utilizador escolher.
     *
     * @throws IOException    caso exista um erro de input/output
     * @throws ParseException caso exista um erro de date parsing
     */
    private static void mostrarOpcoes() throws IOException, ParseException {
        System.out.println("\nEscolha uma das opções:");
        System.out.println("1. Adicionar paciente. \n2. Adicionar medições a paciente já registado.\n3. Colocar pacientes ordenados por ordem alfabética. \n4. Colocar pacientes ordenados por data de nascimento. \n5. Visualizar medidas sumário dos sinais vitais. \n6. Visualizar classificação dos pacientes.\n7. Sair e criar registo em ficheiro de texto.");
        opcao = sc.nextInt();
        sc.nextLine();
        if (opcao == 1) {
            adicionarPaciente();
        }
        if (opcao == 2) {
            while (opcao == 2) {
                adicionarMedicao();
                System.out.println("\nDeseja adicionar mais medições? \n1. Não.\n2. Sim");
                opcao = sc.nextInt();
                sc.nextLine();
            }
        }
        if (opcao == 3) {
            listarPacientesPorNome();
        }
        if (opcao == 4) {
            listarPacientesPorData();
        }
        if (opcao == 5) {
            calcularMedidasSumario();
        }
        if (opcao == 6) {
            visualizarAvaliacoes();
        }
        if (opcao == 7) {
            criarFicheiroRegisto();
            System.out.println("\nSair.");
        }
        if (opcao < 1 || opcao > 7) {
            System.out.println("Opção invalida.");
        }
    }

    /**
     * Adiciona um paciente ao sistema com as suas medições iniciais.
     */
    private static void adicionarPaciente() {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Data de Nascimento (YYYY/MM/DD): ");
        String dataNascimento = sc.nextLine();
        System.out.print("Código do paciente: ");
        int codigo = sc.nextInt();
        System.out.print("Frequência Cardíaca: ");
        double fc = sc.nextDouble();
        System.out.print("Temperatura: ");
        double temp = sc.nextDouble();
        System.out.print("Saturação de Oxigénio: ");
        double sat = sc.nextDouble();
        sc.nextLine();
        SimpleDateFormat DataHora = new SimpleDateFormat("dd/MM/yyyy HH:mm"); // registo da hora
        String dataHora = DataHora.format(new Date());

        Paciente novoPaciente = new Paciente(nome, dataNascimento, codigo);
        novoPaciente.registarMedicao(fc, temp, sat, dataHora);
        pacientes.add(novoPaciente);
        System.out.println("Paciente adicionado.");
    }

    /**
     * Adiciona novas medições a um paciente existente.
     */
    private static void adicionarMedicao() {
        boolean codigoEncontrado = false;
        System.out.print("Código do paciente: ");
        int codigo = sc.nextInt();
        sc.nextLine();

        for (Paciente p : pacientes) {
            if (p.getCodigo() == codigo) {
                codigoEncontrado = true;
                System.out.print("Frequência Cardíaca: ");
                double fc = sc.nextDouble();
                System.out.print("Temperatura: ");
                double temp = sc.nextDouble();
                System.out.print("Saturação de Oxigénio: ");
                double sat = sc.nextDouble();
                sc.nextLine();
                p.registarMedicao(fc, temp, sat, new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date()));
                System.out.println("Medição adicionada.");
            }
        }
        if (!codigoEncontrado) {
            System.out.println("Paciente não encontrado.");
        }
    }

    /**
     * Lista pacientes ordenados por ordem alfabética.
     */
    private static void listarPacientesPorNome() {
        pacientes.sort(Comparator.comparing(Paciente::getNome)); // Ordenar lista utilizando interface nativa
        System.out.println("\nLista de Pacientes Ordenados por Nome:");
        for (Paciente p : pacientes) {
            System.out.println(p.getNome() + " - " + p.classificarPaciente());
        }
    }

    /**
     * Lista pacientes ordenados de acordo com a data de nascimento.
     */
    private static void listarPacientesPorData() {
        pacientes.sort(Comparator.comparing(Paciente::getDataNascimento)); // Ordenar lista utilizando interface nativa
        System.out.println("\nLista de Pacientes Ordenados por Data de Nascimento:");
        for (Paciente p : pacientes) {
            System.out.println(p.getNome() + " - " + p.classificarPaciente());
        }
    }

    /**
     * Calcula e mostra medidas sumário dos sinais vitais dos pacientes.
     *
     * @throws ParseException Caso exista um erro de date parsing
     */
    private static void calcularMedidasSumario() throws ParseException {
        int n = 0;
        // dar reset nas variáveis caso estas já tenham sido utilizadas anteriormente
        somaFC = somaTemp = somaSat = somaFC2 = somaTemp2 = somaSat2 = 0;
        minFC = minTemp = minSat = Double.MAX_VALUE;
        maxFC = maxTemp = maxSat = Double.MIN_VALUE;
        System.out.println("1) Calcular Medidas Sumário para todos os pacientes.\n2) Calcular Medidas Sumário apenas para um grupo de pacientes.\n3) Calcular medida sumário de um dado período de tempo.");
        escolha = sc.nextInt();
        if (escolha == 1) {
            for (Paciente p : pacientes) {
                List<MedicaoPaciente> medicoes = p.getMedicoes();
                for (MedicaoPaciente med : medicoes) {
                    double fc = med.getFc();
                    double temp = med.getTemp();
                    double sat = med.getSat();
                    calcularMaxMinMedia(fc, temp, sat);
                }
            }
            for (Paciente p : pacientes) {
                for (MedicaoPaciente med : p.getMedicoes()) {
                    n++;
                }
            }
        } else if (escolha == 2) {
            System.out.println("De quantos pacientes quer calcular Medidas Sumário: ");
            for (int i = sc.nextInt(); i > 0; i--) {
                System.out.println("Código do paciente:");
                int codigo = sc.nextInt();

                Paciente pacienteEncontrado = null;
                for (Paciente p : pacientes) {
                    if (p.getCodigo() == codigo) {
                        pacienteEncontrado = p;
                        break;
                    }
                }

                if (pacienteEncontrado != null) {
                    List<MedicaoPaciente> medicoes = pacienteEncontrado.getMedicoes();
                    for (MedicaoPaciente med : medicoes) {
                        double fc = med.getFc();
                        double temp = med.getTemp();
                        double sat = med.getSat();

                        calcularMaxMinMedia(fc, temp, sat);
                        n++;
                    }
                } else {
                    System.out.println("Paciente não encontrado!");
                }
            }
        } else if (escolha == 3) {
            sc.nextLine();
            System.out.println("Insira a data inicial (dd/MM/yyyy HH:mm):");
            String dataInicialStr = sc.nextLine();
            System.out.println("Insira a data final (dd/MM/yyyy HH:mm):");
            String dataFinalStr = sc.nextLine();

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            Date dataInicial = sdf.parse(dataInicialStr); // passar as datas de String para SimpleDateFormat
            Date dataFinal = sdf.parse(dataFinalStr);

            for (Paciente p : pacientes) {
                for (MedicaoPaciente med : p.getMedicoes()) {
                    Date dataMedicao = sdf.parse(med.getDataHora());
                    if (!dataMedicao.before(dataInicial) && !dataMedicao.after(dataFinal)) {
                        double fc = med.getFc();
                        double temp = med.getTemp();
                        double sat = med.getSat();

                        calcularMaxMinMedia(fc, temp, sat);
                        n++;
                    }
                }
            }
            if (n == 0) {
                System.out.println("Não existem medições registadas nesse período de tempo.");
            }

        } else {
            System.out.println("Valor inválido.");
        }
        //Tabela com as medidas sumário dos pacientes
        System.out.println(n);
        System.out.println("\nMedidas Sumário dos Sinais Vitais:");
        System.out.printf("%-20s | %-8s | %-8s | %-8s | %-8s |\n", "Sinal Vital", "Média", "Desvio", "Mínimo", "Máximo");
        System.out.printf("%-20s | %-8.2f | %-8.2f | %-8.2f | %-8.2f |\n", "Frequência Cardíaca", (somaFC / n), Math.sqrt((somaFC2 / n) - Math.pow(somaFC / n, 2)), minFC, maxFC);
        System.out.printf("%-20s | %-8.2f | %-8.2f | %-8.2f | %-8.2f |\n", "Temperatura", (somaTemp / n), Math.sqrt((somaTemp2 / n) - Math.pow(somaTemp / n, 2)), minTemp, maxTemp);
        System.out.printf("%-20s | %-8.2f | %-8.2f | %-8.2f | %-8.2f |\n", "Saturação Oxigénio", (somaSat / n), Math.sqrt((somaSat2 / n) - Math.pow(somaSat / n, 2)), minSat, maxSat);
    }

    /**
     * Método utilizado para calcular mínimo, máximo e média de cada variável, sem ocorrer repetição do código no método onde este é chamado.
     *
     * @param fc Frequência cardíaca
     * @param temp Temperatura corporal
     * @param sat Saturação de oxigénio
     */
    private static void calcularMaxMinMedia(double fc, double temp, double sat) {
        somaFC = somaFC + fc;
        somaTemp = somaTemp + temp;
        somaSat = somaSat + sat;

        somaFC2 = somaFC2 + fc * fc;
        somaTemp2 = somaTemp2 + temp * temp;
        somaSat2 = somaSat2 + sat * sat;

        minFC = Math.min(minFC, fc);
        maxFC = Math.max(maxFC, fc);
        minTemp = Math.min(minTemp, temp);
        maxTemp = Math.max(maxTemp, temp);
        minSat = Math.min(minSat, sat);
        maxSat = Math.max(maxSat, sat);
    }

    /**
     * Mostra a classificação atual de cada paciente.
     */
    private static void visualizarAvaliacoes() {
        System.out.printf("\n%-8s  %-20s  %-12s \n", "Código:", "Nome:", "Classificação:");
        for (Paciente p : pacientes) {
            System.out.printf("%-8s  %-20s  %-12s \n", p.getCodigo(), p.getNome(), p.classificarPaciente());
        }
    }

    /**
     * Cria um ficheiro de texto com o registo os dados de todos os pacientes, assim como a identificação do profissional que inseriu as medições.
     *
     * @throws IOException
     */
    private static void criarFicheiroRegisto() throws IOException {
        System.out.println("Pathname do ficheiro de registo:");
        String pathname = sc.nextLine();
        File registo = new File(pathname);
        registo.createNewFile();
        Formatter out = new Formatter(registo);
        for (ProfissionalSaude ps : profissinais) {
            out.format("%s%n", ps.toString());
        }
        for (Paciente p : pacientes) {
            out.format("%s%n", p.toString());
        }
        out.close();
        System.out.println("Ficheiro criado.");
    }

    public static double calcularPercentagemCriticos(List<Paciente> pacientes) {
    int criticos = 0, total = 0;
    for (Paciente p : pacientes) {
        for (MedicaoPaciente m : p.getMedicoes()) {
            if (p.classificarPaciente().equals("Crítico")) {
                criticos++;
                break;
            }
        }
        total++;
    }
    return 100.0 * criticos / total;
}
}