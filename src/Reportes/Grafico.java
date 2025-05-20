package Reportes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import Util.Conexion;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import javax.swing.JFrame;

public class Grafico {
    private JFreeChart chart;
    private final Conexion cn = new Conexion();
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    public boolean generarGrafico(String fecha) {
        con = cn.getConnection();
        boolean hayDatos = false;

        try {
            String sql = "SELECT id, TIME(fecha) AS hora, total " +
                    "FROM ventas WHERE DATE(fecha) = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, fecha);
            rs = ps.executeQuery();

            double totalDelDia = 0.0;
            Map<String, Double> ventasMap = new LinkedHashMap<>();

            while (rs.next()) {
                hayDatos = true;
                int id = rs.getInt("id");
                String hora = rs.getString("hora");
                double total = rs.getDouble("total");
                totalDelDia += total;

                String clave = "Venta " + id + " - " + hora;
                ventasMap.put(clave, total);
            }

            if (!hayDatos) return false;

            DefaultPieDataset dataset = new DefaultPieDataset();
            for (Map.Entry<String, Double> entry : ventasMap.entrySet()) {
                String venta = entry.getKey();
                double valor = entry.getValue();
                double porcentaje = (valor / totalDelDia) * 100;
                String etiqueta = venta + "\n" + String.format("%.2f€ (%.0f%%)", valor, porcentaje);
                dataset.setValue(etiqueta, valor);
            }

            String titulo = String.format("Reporte de Venta (por transacción) - Total: %.2f€", totalDelDia);

            chart = ChartFactory.createPieChart(
                    titulo,
                    dataset,
                    true,
                    true,
                    false
            );

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }
    }

    public void mostrarGrafico() {
        if (chart != null) {
            JFrame ventana = new JFrame("Total de Ventas por Día");
            ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            ventana.setSize(800, 600);
            ventana.setLocationRelativeTo(null);
            ventana.add(new ChartPanel(chart));
            ventana.setVisible(true);
        } else {
            System.out.println("No se ha generado el gráfico aún.");
        }
    }
}
