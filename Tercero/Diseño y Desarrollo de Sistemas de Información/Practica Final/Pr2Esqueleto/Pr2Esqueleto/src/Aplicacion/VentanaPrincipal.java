/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacion;

import Persistencia.conexionOracle;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Miguel Sánchez
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    private conexionOracle co = null;

    /**
     * Creates new form VentanaPrincipal
     */
    public VentanaPrincipal() {
        initComponents();
        this.setLocationRelativeTo(null);
        setTitle("Práctica DDSI - Swing");
    }
    
    public void EstablecerConexion(conexionOracle c){
        if(co != null){
            try {
                co.desconexion();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al desconectar");
            }
        }
        this.co = c;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem3 = new javax.swing.JMenuItem();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1Conexión = new javax.swing.JMenu();
        jMenuItemConectar = new javax.swing.JMenuItem();
        jMenu1Salir = new javax.swing.JMenuItem();
        jMenu2Expertos = new javax.swing.JMenu();
        jMenu2Gexpertos = new javax.swing.JMenuItem();
        jMenuColaboraciones = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuGestionCompleta = new javax.swing.JMenu();
        jMenuGetionCompleta = new javax.swing.JMenuItem();

        jMenuItem3.setText("jMenuItem3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Imagen practica DDSI.jpg"))); // NOI18N

        jMenu1Conexión.setText("Conexión");

        jMenuItemConectar.setText("Conectar");
        jMenuItemConectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemConectarActionPerformed(evt);
            }
        });
        jMenu1Conexión.add(jMenuItemConectar);

        jMenu1Salir.setText("Salir Aplicación");
        jMenu1Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1SalirActionPerformed(evt);
            }
        });
        jMenu1Conexión.add(jMenu1Salir);

        jMenuBar1.add(jMenu1Conexión);

        jMenu2Expertos.setText("Expertos");
        jMenu2Expertos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu2ExpertosActionPerformed(evt);
            }
        });

        jMenu2Gexpertos.setText("Gestión de Expertos");
        jMenu2Gexpertos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu2GexpertosActionPerformed(evt);
            }
        });
        jMenu2Expertos.add(jMenu2Gexpertos);

        jMenuBar1.add(jMenu2Expertos);

        jMenuColaboraciones.setText("Colaboraciones");
        jMenuColaboraciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuColaboracionesActionPerformed(evt);
            }
        });

        jMenuItem1.setText("Gestión Colaboraciones");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenuColaboraciones.add(jMenuItem1);

        jMenuItem2.setText("Colaboradores por Caso");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenuColaboraciones.add(jMenuItem2);

        jMenuBar1.add(jMenuColaboraciones);

        jMenuGestionCompleta.setText("Gestión Completa");
        jMenuGestionCompleta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuGestionCompletaActionPerformed(evt);
            }
        });

        jMenuGetionCompleta.setText("Gestión Completa de la BD");
        jMenuGetionCompleta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuGetionCompletaActionPerformed(evt);
            }
        });
        jMenuGestionCompleta.add(jMenuGetionCompleta);

        jMenuBar1.add(jMenuGestionCompleta);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu2GexpertosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu2GexpertosActionPerformed
        // TODO add your handling code here:
            VentanaExpertos ve = new VentanaExpertos(co);
            ve.setLocationRelativeTo(null);
            ve.setTitle("Gestión de Expertos");
        ve.setVisible(true);

    }//GEN-LAST:event_jMenu2GexpertosActionPerformed

    private void jMenu1SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1SalirActionPerformed
        // TODO add your handling code here:
        try {
            co.desconexion();
            System.exit(0);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } finally {
            this.dispose();
        }
    }//GEN-LAST:event_jMenu1SalirActionPerformed

    private void jMenu2ExpertosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu2ExpertosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu2ExpertosActionPerformed

    private void jMenuColaboracionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuColaboracionesActionPerformed
        // TODO add your handling code here:
            
    }//GEN-LAST:event_jMenuColaboracionesActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        VentanaColaboraciones ve = new VentanaColaboraciones(co);
        ve.setLocationRelativeTo(null);
        ve.setTitle("Gestión de Colaboraciones");
            ve.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        VentanaColaboracionesPorCaso vc = new VentanaColaboracionesPorCaso(co);
        vc.setLocationRelativeTo(null);
        vc.setTitle("Gestión de Colaboraciones");
        vc.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuGestionCompletaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuGestionCompletaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuGestionCompletaActionPerformed

    private void jMenuGetionCompletaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuGetionCompletaActionPerformed
        // TODO add your handling code here:
         VentanaGestionCompleta ve = new VentanaGestionCompleta(co);
         ve.setLocationRelativeTo(null);
        ve.setTitle("Gestion Completa de la BD");
        ve.setVisible(true);
    }//GEN-LAST:event_jMenuGetionCompletaActionPerformed

    private void jMenuItemConectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemConectarActionPerformed
        // TODO add your handling code here:
        VentanaConectar ve = new VentanaConectar(this);
        ve.setLocationRelativeTo(null);
        ve.setTitle("Iniciar Sesion");
        ve.setVisible(true);
        
    }//GEN-LAST:event_jMenuItemConectarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VentanaPrincipal().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1Conexión;
    private javax.swing.JMenuItem jMenu1Salir;
    private javax.swing.JMenu jMenu2Expertos;
    private javax.swing.JMenuItem jMenu2Gexpertos;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuColaboraciones;
    private javax.swing.JMenu jMenuGestionCompleta;
    private javax.swing.JMenuItem jMenuGetionCompleta;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItemConectar;
    // End of variables declaration//GEN-END:variables
}
