package ED2;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class KruskalGUI2 extends JFrame {

    private Grafo grafo;
    private JTextField txtVertices, txtOrigem, txtDestino, txtPeso;
    private JTextArea txtArestas, txtResultado;
    private JLabel lblPesoTotal;

    public KruskalGUI2() {

        setTitle("Algoritmo de Kruskal - Árvore Geradora Mínima");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ---------------- SUPERIOR ----------------
        JPanel top = new JPanel();
        top.add(new JLabel("Vértices:"));
        txtVertices = new JTextField(5);
        top.add(txtVertices);

        JButton btnCriar = new JButton("Criar Grafo");
        top.add(btnCriar);

        JButton btnLimpar = new JButton("Limpar");
        top.add(btnLimpar);

        add(top, BorderLayout.NORTH);

        // ---------------- CENTRO ----------------
        JPanel center = new JPanel(new GridLayout(1, 2));

        txtArestas = new JTextArea();
        txtArestas.setBorder(BorderFactory.createTitledBorder("Arestas Adicionadas"));
        center.add(new JScrollPane(txtArestas));

        txtResultado = new JTextArea();
        txtResultado.setBorder(BorderFactory.createTitledBorder("Resultado do Kruskal"));
        center.add(new JScrollPane(txtResultado));

        add(center, BorderLayout.CENTER);

        // ---------------- INFERIOR ----------------
        JPanel bottom = new JPanel();

        bottom.add(new JLabel("Origem:"));
        txtOrigem = new JTextField(3);
        bottom.add(txtOrigem);

        bottom.add(new JLabel("Destino:"));
        txtDestino = new JTextField(3);
        bottom.add(txtDestino);

        bottom.add(new JLabel("Peso:"));
        txtPeso = new JTextField(3);
        bottom.add(txtPeso);

        JButton btnAdd = new JButton("Adicionar Aresta");
        bottom.add(btnAdd);

        JButton btnCalcular = new JButton("Rodar Kruskal");
        bottom.add(btnCalcular);

        lblPesoTotal = new JLabel("Peso total: 0");
        lblPesoTotal.setFont(new Font("Arial", Font.BOLD, 14));
        bottom.add(lblPesoTotal);

        add(bottom, BorderLayout.SOUTH);

        // ---------------- EVENTOS E EXCEÇÕES ----------------

        // Criar Grafo
        btnCriar.addActionListener(e -> {
            try {
                int v = Integer.parseInt(txtVertices.getText());
                grafo = new Grafo(v);
                limparCamposParciais();
                JOptionPane.showMessageDialog(this, "Grafo criado com " + v + " vértices!");
            } catch (NumberFormatException ex) {
                mostrarErro("Digite um número válido para os vértices!");
            } catch (Exception ex) {
                mostrarErro(ex.getMessage());
            }
        });

        // Adicionar Aresta
        btnAdd.addActionListener(e -> {
            try {
                validarGrafoCriado();

                int o = Integer.parseInt(txtOrigem.getText());
                int d = Integer.parseInt(txtDestino.getText());
                int p = Integer.parseInt(txtPeso.getText());

                grafo.adicionarAresta(o, d, p);
                txtArestas.append(o + " - " + d + " (peso: " + p + ")\n");

            } catch (NumberFormatException ex) {
                mostrarErro("Digite valores numéricos válidos!");
            } catch (IndexOutOfBoundsException ex) {
                mostrarErro("Um dos vértices não existe no grafo.");
            } catch (IllegalArgumentException ex) {
                mostrarErro(ex.getMessage());
            } catch (Exception ex) {
                mostrarErro("Erro desconhecido: " + ex.getMessage());
            }
        });

        // Rodar Kruskal
        btnCalcular.addActionListener(e -> {
            try {
                validarGrafoCriado();

                List<Aresta> mst = grafo.kruskal();

                txtResultado.setText("");
                int pesoTotal = 0;

                for (Aresta a : mst) {
                    txtResultado.append(a.toString() + "\n");
                    pesoTotal += a.peso;
                }

                lblPesoTotal.setText("Peso total: " + pesoTotal);

            } catch (IllegalStateException ex) {
                mostrarErro("Não existem arestas suficientes para executar Kruskal.");
            } catch (Exception ex) {
                mostrarErro("Erro ao calcular: " + ex.getMessage());
            }
        });

        // Limpar Interface
        btnLimpar.addActionListener(e -> limparTudo());
    }

    private void validarGrafoCriado() {
        if (grafo == null)
            throw new IllegalStateException("Crie o grafo antes de adicionar arestas.");
    }

    private void mostrarErro(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    private void limparCamposParciais() {
        txtArestas.setText("");
        txtResultado.setText("");
        lblPesoTotal.setText("Peso total: 0");
    }

    private void limparTudo() {
        txtArestas.setText("");
        txtResultado.setText("");
        txtVertices.setText("");
        txtOrigem.setText("");
        txtDestino.setText("");
        txtPeso.setText("");
        lblPesoTotal.setText("Peso total: 0");
        grafo = null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new KruskalGUI2().setVisible(true));
    }
}
