
import java.util.ArrayList;
import java.util.Scanner;

// Classe principal que contém o método main
public class Main {
    public static void main(String[] args) {
        ControleVacinas controleVacinas = new ControleVacinas();
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("===== Controle de Vacinas =====");
            System.out.println("1. Cadastrar nova vacina");
            System.out.println("2. Editar vacina");
            System.out.println("3. Consultar vacinas");
            System.out.println("4. Remover vacina");
            System.out.println("5. Visualizar vacinas específicas");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do teclado

            switch (opcao) {
                case 1:
                    controleVacinas.cadastrarVacina(scanner);
                    break;
                case 2:
                    controleVacinas.editarVacina(scanner);
                    break;
                case 3:
                    controleVacinas.consultarVacinas();
                    break;
                case 4:
                    controleVacinas.removerVacina(scanner);
                    break;
                case 5:
                    controleVacinas.visualizarVacinasEspecificas(scanner);
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);

        scanner.close();
    }
}

// Classe abstrata que representa uma vacina
abstract class Vacina {
    protected String nome;
    protected int codigo;

    public Vacina(String nome, int codigo) {
        this.nome = nome;
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + ", Código: " + codigo;
    }
}

// Subclasse de Vacina específica para vacinas contra Covid-19
class VacinaCovid extends Vacina {
    private int dosesNecessarias;
    private int intervaloEntreDoses;

    public VacinaCovid(String nome, int codigo, int dosesNecessarias, int intervaloEntreDoses) {
        super(nome, codigo);
        this.dosesNecessarias = dosesNecessarias;
        this.intervaloEntreDoses = intervaloEntreDoses;
    }

    public int getDosesNecessarias() {
        return dosesNecessarias;
    }

    public void setDosesNecessarias(int dosesNecessarias) {
        this.dosesNecessarias = dosesNecessarias;
    }

    public int getIntervaloEntreDoses() {
        return intervaloEntreDoses;
    }

    public void setIntervaloEntreDoses(int intervaloEntreDoses) {
        this.intervaloEntreDoses = intervaloEntreDoses;
    }

    @Override
    public String toString() {
        return super.toString() + ", Doses Necessárias: " + dosesNecessarias + ", Intervalo entre doses: " + intervaloEntreDoses + " dias";
    }
}

// Classe responsável pelo controle das vacinas
class ControleVacinas {
    private ArrayList<Vacina> vacinas;

    public ControleVacinas() {
        this.vacinas = new ArrayList<>();
    }

    public void cadastrarVacina(Scanner scanner) {
        System.out.print("Nome da vacina: ");
        String nome = scanner.nextLine();
        System.out.print("Código da vacina: ");
        int codigo = scanner.nextInt();
        System.out.print("Número de doses necessárias: ");
        int dosesNecessarias = scanner.nextInt();
        System.out.print("Intervalo entre doses (em dias): ");
        int intervaloEntreDoses = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do teclado

        VacinaCovid novaVacina = new VacinaCovid(nome, codigo, dosesNecessarias, intervaloEntreDoses);
        vacinas.add(novaVacina);
        System.out.println("Vacina cadastrada com sucesso!");
    }

    public void editarVacina(Scanner scanner) {
        System.out.print("Digite o código da vacina que deseja editar: ");
        int codigo = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do teclado

        for (Vacina vacina : vacinas) {
            if (vacina.getCodigo() == codigo) {
                System.out.println("Digite o novo nome da vacina: ");
                String novoNome = scanner.nextLine();
                vacina.setNome(novoNome);
                System.out.println("Vacina editada com sucesso!");
                return;
            }
        }

        System.out.println("Vacina não encontrada.");
    }

    public void consultarVacinas() {
        System.out.println("===== Lista de Vacinas =====");
        for (Vacina vacina : vacinas) {
            System.out.println(vacina);
        }
        System.out.println("============================");
    }

    public void removerVacina(Scanner scanner) {
        System.out.print("Digite o código da vacina que deseja remover: ");
        int codigo = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do teclado

        for (int i = 0; i < vacinas.size(); i++) {
            if (vacinas.get(i).getCodigo() == codigo) {
                vacinas.remove(i);
                System.out.println("Vacina removida com sucesso!");
                return;
            }
        }

        System.out.println("Vacina não encontrada.");
    }

    public void visualizarVacinasEspecificas(Scanner scanner) {
        System.out.println("===== Escolha o tipo de vacina =====");
        System.out.println("1. Vacina contra Covid-19");
        System.out.println("2. Outras vacinas");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do teclado

        switch (opcao) {
            case 1:
                System.out.println("===== Vacinas contra Covid-19 =====");
                for (Vacina vacina : vacinas) {
                    if (vacina instanceof VacinaCovid) {
                        System.out.println(vacina);
                    }
                }
                break;
            case 2:
                System.out.println("===== Outras Vacinas =====");
                for (Vacina vacina : vacinas) {
                    if (!(vacina instanceof VacinaCovid)) {
                        System.out.println(vacina);
                    }
                }
                break;
            default:
        }
    }}