/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polivoto.vistas;

import com.polivoto.shared.Opcion;
import com.polivoto.shared.Pregunta;
import com.polivoto.shared.Votacion;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.border.MatteBorder;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;

/**
 *
 * @author azaraf
 */
public class Charts {
    private final int PIECHART = 0;
    private final int BARCHART = 1;
    private final int TABLE = 2;
    private final Font fuenteNormal= new Font("Roboto", 0, 18);
    private Votacion votacion;
    private JPanel panelGrafica;
    private int state;
    private Dimension preferredDimension;

    public Charts() {
        state = PIECHART;
    }
    
    public Charts(Votacion votacion) {
        state = PIECHART;
        this.votacion = votacion;
    }

    public Charts(Votacion votacion, JPanel panelGrafica) {
        this.votacion = votacion;
        this.panelGrafica = panelGrafica;
        state = PIECHART;
    }
    
    public void getHeader(JPanel header){
        header.removeAll();
        System.out.println(":)");
        header.setLayout(new GridLayout(0, 2, 25, 25));
        header.setBackground(Color.white);
        header.setPreferredSize(new Dimension(0, 100));
        JLabel titulo = new JLabel("<html>     <b>Votación: </b>" + votacion.getTitulo() + "</html>");
        JLabel lugar = new JLabel("<html>     <b>Lugar: </b>" + votacion.getLugar() + "</html>");
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE d' de 'MMMM' del 'yyyy, hh:mm:ss");
        JLabel fechaInicio = new JLabel("<html><b>Fecha de inicio: </b>" + sdf.format(new Date(votacion.getFechaInicio())) + "</html>");
        JLabel fechaFin = new JLabel("<html><b>Fecha de fin: </b>" + sdf.format(new Date(votacion.getFechaFin())) + "</html>");
        titulo.setFont(fuenteNormal);
        lugar.setFont(fuenteNormal);
        fechaFin.setFont(fuenteNormal);
        fechaInicio.setFont(fuenteNormal);
        header.add(titulo);
        header.add(fechaInicio);
        header.add(lugar);
        header.add(fechaFin);
        header.repaint();
        header.revalidate();
        
    }
    
    public void getBotonesPreguntas(JPanel botones){
        botones.removeAll();
        System.out.println(":))");
        JPanel panelRelleno = new JPanel(new BorderLayout(20, 20));
        panelRelleno.setBackground(Color.white);
        JPanel panelContainer = new JPanel(new GridLayout(0, 1, 30, 30));
        panelContainer.setBackground(Color.white);
        botones.setPreferredSize(new Dimension(250, 0));
        botones.setLayout(new GridLayout(2, 1));
        botones.add(panelContainer);
        botones.add(panelRelleno);
        JLabel labelPreguntas = new JLabel("PREGUNTAS");
        labelPreguntas.setFont(new Font("Roboto", 1, 24));
        labelPreguntas.setHorizontalAlignment(0);
        labelPreguntas.setForeground(new Color(137, 36, 31));
        panelContainer.add(labelPreguntas);
        
        for (Pregunta pregunta : votacion.getPreguntas()) {
            JButton boton = new JButton("<html>" + pregunta.getTitulo() + "</html>");
            boton.setPreferredSize(new Dimension(200, 0));
            boton.setHorizontalAlignment(0);
            boton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    crearGrafica(pregunta);
                }
            });
            panelContainer.add(boton);
        }
        
        botones.repaint();
        botones.revalidate();
        
    }
    
    private void crearGrafica(Pregunta pregunta){
        switch(state){
                case PIECHART:
                    System.out.println("piechart de la pregunta " + pregunta.getTitulo());
                    crearPieChart(pregunta);
                    break;
                    
                case BARCHART:
                    System.out.println("barchart de la pregunta " + pregunta.getTitulo());
                    crearBarChart(pregunta);
                    break;
                    
                case TABLE:
                    System.out.println("tabla de la pregunta " + pregunta.getTitulo());
                    crearTabla(pregunta);
                    
                    break;
                    
                default:
                    System.out.println("Error");
                    break;
        }
    }
    private void crearTabla(Pregunta pregunta) {
        panelGrafica.removeAll();
        JScrollPane scrollPanel = new JScrollPane();
        JPanel panel = new JPanel(new BorderLayout());
        panelGrafica.setLayout(new BorderLayout());
        
        preferredDimension = panelGrafica.getSize();
        //Panel completo
        //scrollPanel.setBackground(Color.blue);
        panel.setBackground(Color.white);
        //Título pregunta
        JLabel tituloPregunta = new JLabel("\t" + pregunta.getTitulo());
        tituloPregunta.setFont(new Font("Roboto", 1, 24));
        tituloPregunta.setForeground(Color.black);
        tituloPregunta.setVerticalAlignment(JLabel.CENTER);
        JPanel panelHeader = new JPanel(new GridLayout(0,1));
        panelHeader.add(tituloPregunta);
        panelHeader.setOpaque(false);
        panelHeader.setPreferredSize(new Dimension(0, 80));
        panel.add(panelHeader, BorderLayout.PAGE_START);

        //Panel de la tabla
        
        JPanel tabla = new JPanel(new GridLayout(pregunta.obtenerCantidadDeOpciones() + 2, 3, 5, 5));
        tabla.setBackground(Color.white);
        //Poner el titulo de cada columna
        for (int i = 0; i < 3; i++) {
            JPanel tilt = new JPanel();
            tilt.setBackground(new Color(137, 36, 31));
            JLabel label = new JLabel(i == 0 ? "Opcion" : i == 2 ? "Porcentaje" : "Cantidad");
            label.setFont(new Font("Roboto", 1, 18));
            label.setForeground(Color.white);
            tilt.add(label);
            tilt.setSize(new Dimension(0, 35));
            tilt.setBorder(new MatteBorder(1, 1, 1, 1, new Color(230, 230, 230)));
            tabla.add(tilt);
        }
        
        int sum = 0;
        for (int i = 0; i < pregunta.obtenerCantidadDeOpciones(); i++) {
            sum += pregunta.obtenerOpcion(i).getCantidad();
        }
        
        for (int i = 0; i < pregunta.obtenerCantidadDeOpciones(); i++) {
            Opcion opc = pregunta.obtenerOpcion(i);
            JPanel p1 = new JPanel(new GridLayout(0, 1));
            p1.setBackground(Color.white);
            p1.setBorder(new MatteBorder(1, 1, 1, 1, new Color(230, 230, 230)));
            JTextArea l1 = new JTextArea(opc.getNombre());
            
            l1.setWrapStyleWord(true);
            l1.setLineWrap(true);
            l1.setFont(new Font("Roboto", 0, 18));
            l1.setEditable(false);
            l1.setBorder(null);
            p1.setPreferredSize(l1.getSize());
            p1.add(l1);
            tabla.add(p1);

            JPanel p2 = new JPanel();
            p2.setBackground(Color.white);
            p2.setBorder(new MatteBorder(1, 1, 1, 1, new Color(230, 230, 230)));
            JLabel l2 = new JLabel("" + opc.getCantidad());
            l2.setFont(new Font("Roboto", 0, 18));
            p2.add(l2);
            tabla.add(p2);

            JPanel p3 = new JPanel();
            p3.setBackground(Color.white);
            p3.setBorder(new MatteBorder(1, 1, 1, 1, new Color(230, 230, 230)));
            double porcentaje = (opc.getCantidad() *100.0)/ sum;
            JLabel l3 = new JLabel(String.format("%.2f", porcentaje) + "%");
            l3.setFont(new Font("Roboto", 0, 18));
            p3.add(l3);
            tabla.add(p3);
        }

        JPanel p1 = new JPanel();
        p1.setBackground(Color.white);
        p1.setBorder(new MatteBorder(1, 1, 1, 1, new Color(230, 230, 230)));
        JLabel l1 = new JLabel("Total");
        l1.setHorizontalTextPosition(JLabel.LEFT);
        l1.setFont(new Font("Roboto", 1, 18));
        p1.add(l1);
        tabla.add(p1);

        JPanel p2 = new JPanel();
        p2.setBackground(Color.white);
        p2.setBorder(new MatteBorder(1, 1, 1, 1, new Color(230, 230, 230)));
        JLabel l2 = new JLabel("" + sum);
        l2.setFont(new Font("Roboto", 1, 18));
        p2.add(l2);
        tabla.add(p2);

        JPanel p3 = new JPanel();
        p3.setBackground(Color.white);
        p3.setBorder(new MatteBorder(1, 1, 1, 1, new Color(230, 230, 230)));
        JLabel l3 = new JLabel("100.00%");
        l3.setFont(new Font("Roboto", 1, 18));
        p3.add(l3);
        tabla.add(p3);
        
        panel.add(tabla, BorderLayout.CENTER);

        //Relleno
        JPanel x = new JPanel(new GridLayout());
        x.setPreferredSize(new Dimension(100, 0));
        x.setBackground(Color.white);
        panel.add(x, BorderLayout.LINE_START);
        JPanel y = new JPanel(new GridLayout());
        y.setPreferredSize(new Dimension(100, 0));
        y.setBackground(Color.white);
        panel.add(y, BorderLayout.LINE_END);
        JPanel z = new JPanel(new GridLayout());
        z.setBackground(Color.white);
        z.setPreferredSize(new Dimension(0,40));
        panel.add(z, BorderLayout.PAGE_END);
        
        scrollPanel.setViewportView(panel);
        
        panelGrafica.add(scrollPanel, BorderLayout.CENTER);
        panel.repaint();
        panel.revalidate();
        panelGrafica.repaint();
        panelGrafica.revalidate();
    } 

    private void crearBarChart(Pregunta pregunta){
        panelGrafica.removeAll();
        JPanel panel = new JPanel();
        panel.setBackground(Color.white);
        panelGrafica.add(panel);

        DefaultCategoryDataset data = new DefaultCategoryDataset();
        // Fuente de Datos
        for(int i = 0; i < pregunta.obtenerCantidadDeOpciones(); i++){
            Opcion opc = pregunta.obtenerOpcion(i);
            data.setValue(opc.getCantidad(), opc.getNombre(), "Perfil");
        }
        

        // Creando el Grafico       
        JFreeChart chart = ChartFactory.createBarChart(
                "\n" + pregunta.getTitulo(),
                "Perfil",
                "Total de votos",
                data,
                PlotOrientation.VERTICAL,
                true, // include legend
                true, // tooltips?
                false // URLs?
        );

        //chart.setBackgroundPaint(Color.white);
        chart.getTitle().setFont(new Font("Roboto", 0, 28));
        //chart.addSubtitle(new TextTitle("Titulo jajaja"));
        //chart.setBackgroundPaint(new GradientPaint(0, 0, Color.white, 0, 1000, Color.white));
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.white);
        plot.setRangeGridlinePaint(Color.DARK_GRAY);
        plot.setOutlineVisible(false);

        ChartPanel barChart = new ChartPanel(chart);
        barChart.setPreferredSize(new Dimension(900, 600));
        //barChart.setBounds(panel.getVisibleRect());

        //Colores de Barras
        Paint[] colors = {
            new Color(124, 181, 236),
            new Color(244, 91, 91),
            new Color(144, 237, 125),
            new Color(67, 67, 72),
            new Color(247, 163, 92),
            new Color(128, 133, 233),
            new Color(241, 92, 128),
            new Color(228, 211, 84),
            new Color(43, 144, 143),
            new Color(145, 232, 225)
        };

        ((org.jfree.chart.renderer.category.BarRenderer) plot.getRenderer()).setBarPainter(new StandardBarPainter()); // Quita Efecto luz
        BarRenderer renderer = new BarRenderer(colors);
        renderer.setColor(plot, data);

        //Numeros sobre barras
        CategoryItemRenderer renderizar;
        renderizar = plot.getRenderer();
        renderizar.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderizar.setBaseItemLabelsVisible(true);
        renderizar.setItemLabelFont(new Font("Roboto", 0, 18));

        //Valores eje Y
        ValueAxis rangeAxis = plot.getRangeAxis();
        rangeAxis.setLabelFont(new Font("Roboto", 0, 17));
        rangeAxis.setTickLabelFont(new Font("Roboto", 0, 17));

        //Diseño categorias
        org.jfree.chart.axis.CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setLabelFont(new Font("Roboto", 0, 17));
        domainAxis.setTickLabelFont(new Font("Roboto", 0, 17));
        /*domainAxis.setTickLabelPaint(new Color(160, 163, 165));
         domainAxis.setCategoryLabelPositionOffset(4);
         domainAxis.setLowerMargin(0);
         domainAxis.setUpperMargin(0);
         domainAxis.setCategoryMargin(0.2);
         */

        //Leyendas
        LegendTitle legend = chart.getLegend();
        legend.setPosition(RectangleEdge.BOTTOM);
        Font nwfont = new Font("Arial", 0, 18);
        legend.setItemFont(nwfont);
        legend.setBorder(0, 0, 0, 0);
        legend.setBackgroundPaint(Color.WHITE);
        legend.setItemLabelPadding(new RectangleInsets(8, 8, 8, 15));

        /*
         plot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator("{1} {0}"));
         plot.setLegendItemShape(new Rectangle(25, 25));
         */
        // Pintar
        panel.removeAll();
        panel.add(barChart);
        panel.repaint();
        panel.revalidate();
        panelGrafica.repaint();
        panelGrafica.revalidate();
    }
    
    private void crearPieChart(Pregunta pregunta){
        panelGrafica.removeAll();
        JPanel panel = new JPanel();
        JTabbedPane tabPanel = new JTabbedPane();
        panel.setBackground(Color.white);
        panelGrafica.add(panel);

        DefaultPieDataset data = new DefaultPieDataset();
        // Fuente de Datos
        for(int i = 0; i < pregunta.obtenerCantidadDeOpciones(); i++){
            Opcion opc = pregunta.obtenerOpcion(i);
            data.setValue(opc.getNombre(), opc.getCantidad());
        }
        
        // Creando el Grafico
        JFreeChart chart = ChartFactory.createPieChart(
                "\n" + pregunta.getTitulo(),
                data,
                true,
                false, //TOOLTIPS
                false);
        chart.setBackgroundPaint(Color.white);
        chart.getTitle().setFont(new Font("Roboto", 0, 28));

        // Crear el Panel del Grafico con ChartPanel
        ChartPanel chartPanel = new ChartPanel(chart);
        PiePlot plot = (PiePlot) chart.getPlot();

        chartPanel.setPreferredSize(new Dimension(900, 650));

        panel.add(chartPanel);

        plot.setLabelGenerator(null);
        plot.setBackgroundPaint(Color.white);
        plot.setOutlineVisible(false);
        //StandardPieSectionLabelGenerator labels = new StandardPieSectionLabelGenerator("{0} = {1}");
        //plot.setLabelGenerator(labels);

        plot.setBaseSectionOutlinePaint(Color.white);
        plot.setShadowXOffset(0);
        plot.setShadowYOffset(0);

        //#7cb5ec,#f45b5b,#90ed7d,#434348,
        //#f7a35c,#8085e9,#f15c80,#e4d354,
        //#2b908f,#91e8e1
        Color[] colors = {
            new Color(124, 181, 236),
            new Color(244, 91, 91),
            new Color(144, 237, 125),
            new Color(67, 67, 72),
            new Color(247, 163, 92),
            new Color(128, 133, 233),
            new Color(241, 92, 128),
            new Color(228, 211, 84),
            new Color(43, 144, 143),
            new Color(145, 232, 225)
        };
        PieRenderer renderer = new PieRenderer(colors);
        renderer.setColor(plot, data);

        LegendTitle legend = chart.getLegend();
        legend.setPosition(RectangleEdge.RIGHT);
        Font nwfont = new Font("Roboto", 0, 18);
        legend.setItemFont(nwfont);
        legend.setBorder(0, 0, 0, 0);
        legend.setBackgroundPaint(Color.WHITE);
        legend.setItemLabelPadding(new RectangleInsets(8, 8, 8, 0));
        //RectangleInsets padding = new RectangleInsets(5, 5, 5, 5);
        //legend.setItemLabelPadding(padding);
        plot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator("{1} {0}"));
        plot.setLegendItemShape(new Rectangle(25, 25));
        
        panel.repaint();
        panel.revalidate();
        panelGrafica.repaint();
        panelGrafica.revalidate();
    }
    public Votacion getVotacion() {
        return votacion;
    }

    public void setVotacion(Votacion votacion) {
        this.votacion = votacion;
    }

    public JPanel getPanelGrafica() {
        return panelGrafica;
    }

    public void setPanelGrafica(JPanel panelGrafica) {
        this.panelGrafica = panelGrafica;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
    
    public static class BarRenderer {

        private Paint[] color;

        public BarRenderer(Paint[] color) {
            this.color = color;
        }

        public void setColor(CategoryPlot plot, DefaultCategoryDataset dataset) {
            List<Comparable> keys = dataset.getRowKeys();
            int aInt;
            for (int i = 0; i < keys.size(); i++) {
                aInt = i % this.color.length;
                plot.getRenderer().setSeriesPaint(i, this.color[aInt]);
            }
        }

    }
    
    public static class PieRenderer {

        private Color[] color;

        public PieRenderer(Color[] color) {
            this.color = color;
        }

        public void setColor(PiePlot plot, DefaultPieDataset dataset) {
            List<Comparable> keys = dataset.getKeys();
            int aInt;

            for (int i = 0; i < keys.size(); i++) {
                aInt = i % this.color.length;
                plot.setSectionPaint(keys.get(i), this.color[aInt]);
            }
        }
    }
}
