/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj2lojaonlineui.Generos;

import BLL.GeneroBLL;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import proj2lojaonline.DAL.Genero;

/**
 *
 * @author marci
 */
public class ListarGeneroUI extends javax.swing.JPanel {

    /**
     * Creates new form ListarGeneroUI
     */
    public ListarGeneroUI() {
        initComponents();
           List<Genero> listaGeneros = null;
        
        DefaultTableModel tmodel = (DefaultTableModel)jTable1.getModel();
        
        listaGeneros = GeneroBLL.readAll();
        
        for(Genero genero : listaGeneros){
            String[] linhaTabela = new String[2];
            linhaTabela[0] = genero.getCodgenero().toString();
            linhaTabela[1] = genero.getNome();
            
            tmodel.addRow(linhaTabela);
        }
            
//            tmodel.addRow(linhaTabela);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        SAIR = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(873, 513));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        jLabel1.setText("Listar Gêneros");
        jLabel1.setMaximumSize(new java.awt.Dimension(183, 43));
        jLabel1.setMinimumSize(new java.awt.Dimension(183, 43));
        jLabel1.setPreferredSize(new java.awt.Dimension(183, 43));

        jTable1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "codGenero", "nome"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        SAIR.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        SAIR.setText("SAIR");
        SAIR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SAIRActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 94, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(SAIR)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 638, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(84, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(SAIR, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
        // TODO add your handling code here:
          
//          int linha = this.jTable1.getSelectedRow();
//        int codGenero = Integer.parseInt((String) ((DefaultTableModel)this.jTable1.getModel() ).getValueAt(linha, 0));
//        
//        Genero genero = GeneroBLL.read(codGenero);
//        
//        CriarGenerosUI updateCor = new CriarGenerosUI(genero);
//        updateCor.setVisible(true);
    }//GEN-LAST:event_jTable1MousePressed

    private void SAIRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SAIRActionPerformed
        // TODO add your handling code here:
        this.show(false);
    }//GEN-LAST:event_SAIRActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton SAIR;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
