/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacion;

import Persistencia.caso;
import Persistencia.colabora;
import Persistencia.conexionOracle;
import Persistencia.experto;
import Persistencia.manejaCaso;
import Persistencia.manejaColabora;
import Persistencia.manejaExperto;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Miguel Sánchez
 */
public class VentanaGestionCompleta extends javax.swing.JFrame {

    conexionOracle co;
    DefaultTableModel modeloGestionCompletaexperto = new DefaultTableModel();
    DefaultTableModel modeloGestionCompletaCasos = new DefaultTableModel();
    DefaultTableModel modeloGestionCompletaColaboraciones = new DefaultTableModel();
    manejaExperto mexperto;
    manejaCaso mcaso;
    manejaColabora mcolabora;
    SimpleDateFormat formatofecha;
    SimpleDateFormat formatofechaiso;

    /**
     * Creates new form VentanaGestionCompleta
     */
    public VentanaGestionCompleta(conexionOracle co) {
        initComponents();
        this.co = co;
        formatofecha = new SimpleDateFormat("dd/MM/yyyy");
        formatofechaiso = new SimpleDateFormat("yyyy-MM-dd");
        mexperto = new manejaExperto(co);
        mcaso = new manejaCaso(co);
        mcolabora = new manejaColabora(co);

        jTableListadoExpertos.setModel(modeloGestionCompletaexperto);
        String[] columnasExperto = {"Codigo", "Nombre", "Pais", "Sexo", "Especialidad"};
        modeloGestionCompletaexperto.setColumnIdentifiers(columnasExperto);
        //jTableListadoExpertos.getTableHeader().setResizingAllowed(false);//Para que las columnas no se redimensiones
        jTableListadoExpertos.getColumnModel().getColumn(0).setPreferredWidth(10);//Para cambiar el tamaño de las columnas
        jTableListadoExpertos.getColumnModel().getColumn(1).setPreferredWidth(100);
        jTableListadoExpertos.getColumnModel().getColumn(2).setPreferredWidth(10);
        jTableListadoExpertos.getColumnModel().getColumn(3).setPreferredWidth(10);
        jTableListadoExpertos.getColumnModel().getColumn(4).setPreferredWidth(100);

        jTableCasoPolicial.setModel(modeloGestionCompletaCasos);
        String[] columnasCasos = {"Codigo", "Nombre", "Fecha de Inicio", "Fecha Final"};
        modeloGestionCompletaCasos.setColumnIdentifiers(columnasCasos);
        jTableCasoPolicial.getTableHeader().setResizingAllowed(false);//Para que las columnas no se redimensiones
        jTableCasoPolicial.getColumnModel().getColumn(0).setPreferredWidth(10);//Para cambiar el tamaño de las columnas
        jTableCasoPolicial.getColumnModel().getColumn(1).setPreferredWidth(100);
        jTableCasoPolicial.getColumnModel().getColumn(2).setPreferredWidth(10);
        jTableCasoPolicial.getColumnModel().getColumn(3).setPreferredWidth(10);

        jTableColaboraciones.setModel(modeloGestionCompletaColaboraciones);
        String[] columnasColaboraciones = {"Experto", "Caso", "Fecha", "Descripción"};
        modeloGestionCompletaColaboraciones.setColumnIdentifiers(columnasColaboraciones);
        jTableColaboraciones.getTableHeader().setResizingAllowed(false);//Para que las columnas no se redimensiones
        jTableColaboraciones.getColumnModel().getColumn(0).setPreferredWidth(10);//Para cambiar el tamaño de las columnas
        jTableColaboraciones.getColumnModel().getColumn(1).setPreferredWidth(100);
        jTableColaboraciones.getColumnModel().getColumn(2).setPreferredWidth(10);
        jTableColaboraciones.getColumnModel().getColumn(3).setPreferredWidth(10);

        actualizartablaExperto();
        actualizartablaCasos();
        actualizarColaboraciones();

        jTableListadoExpertos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (jTableListadoExpertos.getSelectedRow() == -1) { //no hay nada seleccionado
                    jTextFieldCodigoExCola.setText("");
                } else {
                    jTextFieldCodigoExCola.setText((String) modeloGestionCompletaexperto.getValueAt(jTableListadoExpertos.getSelectedRow(), 0));
                }
            }
        });

        jTableCasoPolicial.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (jTableCasoPolicial.getSelectedRow() == -1) {
                    jTextFieldCodigoCasoCola.setText("");
                } else {
                    jTextFieldCodigoCasoCola.setText((String) modeloGestionCompletaCasos.getValueAt(jTableCasoPolicial.getSelectedRow(), 0));
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label1 = new java.awt.Label();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableListadoExpertos = new javax.swing.JTable();
        label6 = new java.awt.Label();
        label8 = new java.awt.Label();
        label9 = new java.awt.Label();
        jTextFieldCodigoCaso = new javax.swing.JTextField();
        jButtonInsertaCasoPolicial = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableCasoPolicial = new javax.swing.JTable();
        jDateCalendarFechainiCaso = new com.toedter.calendar.JDateChooser();
        jDateCalendarFechaFinCaso = new com.toedter.calendar.JDateChooser();
        label12 = new java.awt.Label();
        label13 = new java.awt.Label();
        label14 = new java.awt.Label();
        label15 = new java.awt.Label();
        jDateCalendarFechaCola = new com.toedter.calendar.JDateChooser();
        jTextFieldCodigoExCola = new javax.swing.JTextField();
        jTextFieldDescripcionCola = new javax.swing.JTextField();
        jButtonInsertaColaboracion = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableColaboraciones = new javax.swing.JTable();
        jTextFieldCodigoCasoCola = new javax.swing.JTextField();
        jTextFieldNombreCaso = new javax.swing.JTextField();
        jTextFieldCodigoExperto = new javax.swing.JTextField();
        jButtonInsertaExperto = new javax.swing.JButton();
        label17 = new java.awt.Label();
        jTextFieldNombreEx = new javax.swing.JTextField();
        jTextFieldPaisEx = new javax.swing.JTextField();
        jTextFieldSexoEx = new javax.swing.JTextField();
        jTextFieldEspecialidadEx = new javax.swing.JTextField();
        label2 = new java.awt.Label();
        label3 = new java.awt.Label();
        label4 = new java.awt.Label();
        label5 = new java.awt.Label();
        jButtonLimpiarTodo = new javax.swing.JButton();
        jButtonEliminarExperto = new javax.swing.JButton();
        jButtonEliminaCasoPolicial = new javax.swing.JButton();
        jButtonEliminaColaboracion = new javax.swing.JButton();
        label7 = new java.awt.Label();
        label10 = new java.awt.Label();
        label11 = new java.awt.Label();
        jButtonListarTodo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        label1.setText("Código");

        jTableListadoExpertos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTableListadoExpertos);

        label6.setText("Código");

        label8.setText("Fecha de Inicio");

        label9.setText("Fecha Final");

        jButtonInsertaCasoPolicial.setText("Insertar Caso Policial");
        jButtonInsertaCasoPolicial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInsertaCasoPolicialActionPerformed(evt);
            }
        });

        jTableCasoPolicial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTableCasoPolicial);

        label12.setText("Código del Experto");

        label13.setText("Código del Caso");

        label14.setText("Fecha de Incorporación");

        label15.setText("Descripción");

        jTextFieldDescripcionCola.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDescripcionColaActionPerformed(evt);
            }
        });

        jButtonInsertaColaboracion.setText("Insertar Colaboración");
        jButtonInsertaColaboracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInsertaColaboracionActionPerformed(evt);
            }
        });

        jTableColaboraciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTableColaboraciones);

        jTextFieldCodigoExperto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCodigoExpertoActionPerformed(evt);
            }
        });

        jButtonInsertaExperto.setText("Insertar Experto");
        jButtonInsertaExperto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInsertaExpertoActionPerformed(evt);
            }
        });

        label17.setText("Nombre");

        jTextFieldNombreEx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNombreExActionPerformed(evt);
            }
        });

        label2.setText("Nombre");

        label3.setText("País");

        label4.setText("Sexo");

        label5.setText("Especialidad");

        jButtonLimpiarTodo.setText("Limpiar Todo");
        jButtonLimpiarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimpiarTodoActionPerformed(evt);
            }
        });

        jButtonEliminarExperto.setText("Eliminar Experto");
        jButtonEliminarExperto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarExpertoActionPerformed(evt);
            }
        });

        jButtonEliminaCasoPolicial.setText("Eliminar Caso");
        jButtonEliminaCasoPolicial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminaCasoPolicialActionPerformed(evt);
            }
        });

        jButtonEliminaColaboracion.setText("Eliminar Colaboración");
        jButtonEliminaColaboracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminaColaboracionActionPerformed(evt);
            }
        });

        label7.setText("Listado de Expertos");

        label10.setText("Listado de Casos Policiales");

        label11.setText("Listado de Colaboraciones");

        jButtonListarTodo.setText("Listar Todo");
        jButtonListarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonListarTodoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 20, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonInsertaColaboracion, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonEliminaColaboracion))
                            .addComponent(label13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(label12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(label14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jDateCalendarFechaCola, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldCodigoCasoCola, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldDescripcionCola, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldCodigoExCola, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 654, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonListarTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonLimpiarTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(label7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(268, 268, 268))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jTextFieldNombreEx)
                                            .addComponent(jTextFieldPaisEx)
                                            .addComponent(jTextFieldEspecialidadEx, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jTextFieldSexoEx, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jTextFieldCodigoExperto, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(179, 179, 179))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButtonInsertaExperto, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButtonEliminarExperto, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 654, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(label17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(94, 94, 94)
                                        .addComponent(jTextFieldNombreCaso, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButtonInsertaCasoPolicial, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButtonEliminaCasoPolicial, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(label8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(label9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(56, 56, 56)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jDateCalendarFechaFinCaso, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jDateCalendarFechainiCaso, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextFieldCodigoCaso, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane2)))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(label10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(250, 250, 250))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(label11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(249, 249, 249))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonListarTodo)
                            .addComponent(jButtonLimpiarTodo)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(label7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jTextFieldCodigoExperto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldNombreEx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldPaisEx, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldSexoEx, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldEspecialidadEx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonInsertaExperto)
                            .addComponent(jButtonEliminarExperto)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(label10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldCodigoCaso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldNombreCaso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateCalendarFechainiCaso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateCalendarFechaFinCaso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonInsertaCasoPolicial)
                            .addComponent(jButtonEliminaCasoPolicial))))
                .addGap(18, 18, 18)
                .addComponent(label11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldCodigoExCola, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldCodigoCasoCola, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateCalendarFechaCola, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldDescripcionCola, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonEliminaColaboracion)
                            .addComponent(jButtonInsertaColaboracion))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldDescripcionColaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldDescripcionColaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDescripcionColaActionPerformed

    private void jTextFieldCodigoExpertoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCodigoExpertoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCodigoExpertoActionPerformed

    private void jButtonLimpiarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimpiarTodoActionPerformed
        // TODO add your handling code here:
        while (modeloGestionCompletaexperto.getRowCount() > 0) {//te da el numero de filas
            modeloGestionCompletaexperto.removeRow(0); //Elimina la fila
        }
        while (modeloGestionCompletaCasos.getRowCount() > 0) {
            modeloGestionCompletaCasos.removeRow(0);
        }
        while (modeloGestionCompletaColaboraciones.getRowCount() > 0) {
            modeloGestionCompletaColaboraciones.removeRow(0);
        }
        jTextFieldCodigoExperto.setText("");
        jTextFieldNombreEx.setText("");
        jTextFieldPaisEx.setText("");
        jTextFieldSexoEx.setText("");
        jTextFieldEspecialidadEx.setText("");
        jTextFieldCodigoCaso.setText("");
        jTextFieldNombreCaso.setText("");
        Date d = null;
        jDateCalendarFechainiCaso.setDate(d);
        jDateCalendarFechaFinCaso.setDate(d);
        jTextFieldCodigoCasoCola.setText("");
        jTextFieldCodigoExCola.setText("");
        jDateCalendarFechaCola.setDateFormatString("");//Tambien podria servir en lugar de declarar un date inicializado a null;
        jTextFieldDescripcionCola.setText("");
    }//GEN-LAST:event_jButtonLimpiarTodoActionPerformed

    private void jTextFieldNombreExActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNombreExActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNombreExActionPerformed

    private void jButtonInsertaCasoPolicialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInsertaCasoPolicialActionPerformed
        // TODO add your handling code here:
        try {
            if (mcaso.existeCaso(jTextFieldCodigoCaso.getText()) == true) {
                JOptionPane.showMessageDialog(null, "El Caso Policial que desea introducir ya existe en la base de datos", "Error al introducir el Caso Policial.", 1);
            }
            if (jDateCalendarFechainiCaso.getDate().compareTo(jDateCalendarFechaFinCaso.getDate()) != -1) {
                JOptionPane.showMessageDialog(null, "La fecha de inicio es mayor que la final", "Error al introducir el Caso Policial.", 2);
            } else {
                mcaso.insertaCaso(new caso(jTextFieldCodigoCaso.getText(), jTextFieldNombreCaso.getText(), formatofecha.format(jDateCalendarFechainiCaso.getDate()), formatofecha.format(jDateCalendarFechaFinCaso.getDate())));
                actualizartablaCasos();
            }
        } catch (SQLException ex3) {
            JOptionPane.showMessageDialog(null,"Error al insertar el Caso Policial. ");
        }
    }//GEN-LAST:event_jButtonInsertaCasoPolicialActionPerformed

    private void jButtonInsertaExpertoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInsertaExpertoActionPerformed
        try {
            if (mexperto.existeExperto(jTextFieldCodigoExperto.getText()) == true) {
                //JOptionPane.showMessageDialog(null, "Este experto ya existe en el listado");
                JOptionPane.showMessageDialog(null, "El Experto que desea introducir ya existe en la base de datos", "Error al introducir el Experto.", 1);
            } else {
                mexperto.insertaExperto(new experto(jTextFieldCodigoExperto.getText(), jTextFieldNombreEx.getText(), jTextFieldPaisEx.getText(), jTextFieldSexoEx.getText(), jTextFieldEspecialidadEx.getText()));
                actualizartablaExperto();
            }

        } catch (SQLException ex2) {
            JOptionPane.showMessageDialog(null, "Error al insertar el experto. ");
        }
    }//GEN-LAST:event_jButtonInsertaExpertoActionPerformed

    private void jButtonEliminarExpertoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarExpertoActionPerformed
        // TODO add your handling code here:
        int seleccion = jTableListadoExpertos.getSelectedRow();//Devuelve la fila seleccionada
        if (seleccion != -1) {//Si es distinto de -1 es que hay algo seleccionado
            try {
                String codigo = (String) modeloGestionCompletaexperto.getValueAt(seleccion, 0);//El 0 es la columna y tenemos que castearlo a String
                if (JOptionPane.showConfirmDialog(this, "¿Estas seguro que desea eliminar al Experto seleccionado?", "Advertencia", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
                    mexperto.eliminarExperto(codigo);
                    actualizartablaExperto();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al eliminar el experto selecionado.");
            }
        }
    }//GEN-LAST:event_jButtonEliminarExpertoActionPerformed

    private void jButtonListarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonListarTodoActionPerformed
        // TODO add your handling code here:
        actualizarColaboraciones();
        actualizartablaCasos();
        actualizartablaExperto();
    }//GEN-LAST:event_jButtonListarTodoActionPerformed

    private void jButtonEliminaCasoPolicialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminaCasoPolicialActionPerformed
        // TODO add your handling code here:
        int seleccion = jTableCasoPolicial.getSelectedRow();//Devuelve la fila seleccionada
        if (seleccion != -1) {//Si es distinto de -1 es que hay algo seleccionado
            try {
                String codigo = (String) modeloGestionCompletaCasos.getValueAt(seleccion, 0);//El 0 es la columna y tenemos que castearlo a String
                if (JOptionPane.showConfirmDialog(this, "¿Estas seguro que desea eliminar el Caso Policial seleccionado?", "Advertencia", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
                    mcaso.eliminarCasoPolicial(codigo);
                    actualizartablaCasos();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al eliminar el Caso Policial seleccionado.");
            }
        }
    }//GEN-LAST:event_jButtonEliminaCasoPolicialActionPerformed

    private void jButtonEliminaColaboracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminaColaboracionActionPerformed
        // TODO add your handling code here:
        int seleccion = jTableColaboraciones.getSelectedRow();//Devuelve la fila seleccionada
        if (seleccion != -1) {//Si es distinto de -1 es que hay algo seleccionado
            try {
                String codigoexperto = (String) modeloGestionCompletaColaboraciones.getValueAt(seleccion, 0);
                String codigocaso = (String) modeloGestionCompletaColaboraciones.getValueAt(seleccion, 1);//El 0 es la columna y tenemos que castearlo a String
                if (JOptionPane.showConfirmDialog(this, "¿Estas seguro que desea eliminar la colaboracion seleccionada?", "Advertencia", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
                    mcolabora.eliminarColaboracion(codigoexperto, codigocaso);
                    actualizarColaboraciones();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al eliminar el la colaboracion seleccionada.");
            }
        }
    }//GEN-LAST:event_jButtonEliminaColaboracionActionPerformed

    private void jButtonInsertaColaboracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInsertaColaboracionActionPerformed
        // TODO add your handling code here:
        try {
            Date fechacolabora = eliminarHora(jDateCalendarFechaCola.getDate());
            int seleccion = jTableCasoPolicial.getSelectedRow();
            if (seleccion != -1) {
                String fechaini = (String) modeloGestionCompletaCasos.getValueAt(seleccion, 2);
                String fechafin = (String) modeloGestionCompletaCasos.getValueAt(seleccion, 3);
                try {
                    System.out.println(fechaini + " " + fechafin);
                    if (formatofechaiso.parse(fechaini).compareTo(fechacolabora) > 0 || formatofechaiso.parse(fechafin).compareTo(fechacolabora) < 0) {//Si es positivo significa que la izquierda es mayor que la derecha
                        JOptionPane.showMessageDialog(null, "La Fecha de Incorporación que desea introducir no está entre la Fecha de inicio y Final del caso seleccionado", "Error al introducir la Colaboración.", 2);
                    } else {
                            mcolabora.insertaColaboracion(new colabora(jTextFieldCodigoExCola.getText(), jTextFieldCodigoCasoCola.getText(), formatofecha.format(fechacolabora), jTextFieldDescripcionCola.getText()));
                            actualizarColaboraciones();  
                    }
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Formato de fecha incorrecto");
                }
            }
        } catch (SQLException ex2) {
            JOptionPane.showMessageDialog(null, "Error al insertar la Colaboración.");
        }
    }//GEN-LAST:event_jButtonInsertaColaboracionActionPerformed

    private void actualizartablaExperto() {
        try {
            // TODO add your handling code here:
            ArrayList<experto> ex = mexperto.listaExpertos();
            while (modeloGestionCompletaexperto.getRowCount() > 0) {//te da el numero de filas
                modeloGestionCompletaexperto.removeRow(0); //Elimina la fila
            }
            for (int i = 0; i < ex.size(); i++) {
                String[] fila = new String[5];
                fila[0] = ex.get(i).getCodExperto();
                fila[1] = ex.get(i).getNombre();
                fila[2] = ex.get(i).getPais();
                fila[3] = ex.get(i).getSexo();
                fila[4] = ex.get(i).getEspecialidad();
                modeloGestionCompletaexperto.addRow(fila); //Agrega una fila
            }
        } catch (SQLException ex1) {
            JOptionPane.showMessageDialog(null, "Error al listar toda la lista de Expertos.");
        }
    }

    private void actualizartablaCasos() {
        try {
            ArrayList<caso> ca = mcaso.listacasospoliciales();
            while (modeloGestionCompletaCasos.getRowCount() > 0) {
                modeloGestionCompletaCasos.removeRow(0);
            }
            for (int i = 0; i < ca.size(); i++) {
                String[] columnas = {ca.get(i).getCodCaso(), ca.get(i).getNombre(), ca.get(i).getFechaInicio(), ca.get(i).getFechaFin()};
                modeloGestionCompletaCasos.addRow(columnas);
            }
        } catch (SQLException ex2) {
            JOptionPane.showInternalMessageDialog(null, "Error al listar todos los casso policiales.");
        }
    }

    private void actualizarColaboraciones() {
        try {
            ArrayList<colabora> col = mcolabora.listadocolaboraciones();
            while (modeloGestionCompletaColaboraciones.getRowCount() > 0) {
                modeloGestionCompletaColaboraciones.removeRow(0);
            }
            for (int i = 0; i < col.size(); i++) {
                String[] columnascolabora = {col.get(i).getCodExperto(), col.get(i).getCodCaso(), col.get(i).getFecha(), col.get(i).getDescripcionColaboracion()};
                modeloGestionCompletaColaboraciones.addRow(columnascolabora);
            }
        } catch (SQLException ex3) {
            JOptionPane.showMessageDialog(null, "Error al listar toda la lista de colaboraciones. ");
        }
    }
    
    private Date eliminarHora(Date fecha) {
        Calendar cal = Calendar.getInstance(); // locale-specific
        cal.setTime(fecha);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return new Date(cal.getTimeInMillis());
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonEliminaCasoPolicial;
    private javax.swing.JButton jButtonEliminaColaboracion;
    private javax.swing.JButton jButtonEliminarExperto;
    private javax.swing.JButton jButtonInsertaCasoPolicial;
    private javax.swing.JButton jButtonInsertaColaboracion;
    private javax.swing.JButton jButtonInsertaExperto;
    private javax.swing.JButton jButtonLimpiarTodo;
    private javax.swing.JButton jButtonListarTodo;
    private com.toedter.calendar.JDateChooser jDateCalendarFechaCola;
    private com.toedter.calendar.JDateChooser jDateCalendarFechaFinCaso;
    private com.toedter.calendar.JDateChooser jDateCalendarFechainiCaso;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableCasoPolicial;
    private javax.swing.JTable jTableColaboraciones;
    private javax.swing.JTable jTableListadoExpertos;
    private javax.swing.JTextField jTextFieldCodigoCaso;
    private javax.swing.JTextField jTextFieldCodigoCasoCola;
    private javax.swing.JTextField jTextFieldCodigoExCola;
    private javax.swing.JTextField jTextFieldCodigoExperto;
    private javax.swing.JTextField jTextFieldDescripcionCola;
    private javax.swing.JTextField jTextFieldEspecialidadEx;
    private javax.swing.JTextField jTextFieldNombreCaso;
    private javax.swing.JTextField jTextFieldNombreEx;
    private javax.swing.JTextField jTextFieldPaisEx;
    private javax.swing.JTextField jTextFieldSexoEx;
    private java.awt.Label label1;
    private java.awt.Label label10;
    private java.awt.Label label11;
    private java.awt.Label label12;
    private java.awt.Label label13;
    private java.awt.Label label14;
    private java.awt.Label label15;
    private java.awt.Label label17;
    private java.awt.Label label2;
    private java.awt.Label label3;
    private java.awt.Label label4;
    private java.awt.Label label5;
    private java.awt.Label label6;
    private java.awt.Label label7;
    private java.awt.Label label8;
    private java.awt.Label label9;
    // End of variables declaration//GEN-END:variables
}
