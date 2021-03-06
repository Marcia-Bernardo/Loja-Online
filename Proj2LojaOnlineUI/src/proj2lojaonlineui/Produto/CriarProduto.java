/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj2lojaonlineui.Produto;

import BLL.CorBLL;
import BLL.GeneroBLL;
import BLL.ProdutoBLL;
import BLL.TamanhoBLL;
import BLL.TipoBLL;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import proj2lojaonline.DAL.Cor;
import proj2lojaonline.DAL.Genero;
import proj2lojaonline.DAL.Produto;
import proj2lojaonline.DAL.Tamanho;
import proj2lojaonline.DAL.Tipo;
import proj2lojaonlineui.CoresUI.CriarCorUI;

/**
 *
 * @author marci
 */
public class CriarProduto extends javax.swing.JFrame {
    int modoOper = 0; //0-Criar; 1- Update
    Produto prodToUpdte = null;
    JPanel painelVisivel = null;
    List<Tipo> listaTipo = null;
    List<Cor> listaCores = null;
    List<Tamanho> listaTamanhos = null;
    List<Genero> listaGeneros = null;
    /**
     * Creates new form CriarProduto
     */
    public CriarProduto() {
        initComponents();
        this.painelVisivel = this.jPanel1;
        this.setContentPane(this.painelVisivel);
        this.repaint();
//        listar cores

               
        listaCores = CorBLL.readAll();
           ArrayList<String> cores = new ArrayList<String>();
        for(Cor cor1 : listaCores){
           cores.add( cor1.getNome());
           
           nomeCor.addItem(cor1.getNome());
        }   
        
        
        //listar generos
        
                
        listaGeneros = GeneroBLL.readAll();
           ArrayList<String> generos = new ArrayList<String>();
        for(Genero genero : listaGeneros){
           generos.add(genero.getNome());
                  
            nomeGen.addItem(genero.getNome());
        }
           
        
              
        listaTamanhos = TamanhoBLL.readAll();
           ArrayList<String> tamanhos = new ArrayList<String>();
        for(Tamanho tam : listaTamanhos){
           tamanhos.add( tam.getNome()); 
           
            nomeTam.addItem(tam.getNome());
        }
            
         //List<Tipo> listaTipos = null;
        
        this.listaTipo = TipoBLL.readAll();
        ArrayList<String> tipos = new ArrayList<String>();
        for(Tipo tipo1 : listaTipo){
           tipos.add( tipo1.getNome());
                  
            nomeTipo.addItem(tipo1.getNome());
        }  
        
    }
    
    public CriarProduto(Produto prod) {
        initComponents();
//        this.CriarProd.setText("Editar Produto - codProd " + prod.getCodprod());
//        this.escreverProd.setText(prod.getNome());
//        this.descricaoProd.setText(prod.getDescricao());
////        this.escreverPrecVend.setText(prod.getPrecovenda());
////        this.escrPrecAtualVend.setText(prod.getPrecoatualvenda());
//        this.escrqtdStock.setColumns(prod.getQtdstock());
//        this.escreverAtivo.setText(prod.getNome());
        
        
       this.modoOper = 1;
        prodToUpdte = prod;
//        
        this.escreverProd.setText(prod.getNome());
        this.descricaoProd.setText(prod.getNome());
        this.escreverPrecVend.setText(prod.getNome());
        this.escrPrecAtualVend.setText(prod.getNome());
        this.escrqtdStock.setText(prod.getNome());
        this.Ativar.setText(prod.getNome());
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        nomeCor = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        nomeGen = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        nomeTam = new javax.swing.JComboBox<>();
        nomeTipo = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        escreverProd = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        descricaoProd = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        escreverPrecVend = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        escrPrecAtualVend = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        escrqtdStock = new javax.swing.JFormattedTextField();
        jLabel10 = new javax.swing.JLabel();
        feedbackMessage = new javax.swing.JLabel();
        ok = new javax.swing.JButton();
        cancelar = new javax.swing.JButton();
        CriarProd = new javax.swing.JLabel();
        Ativar = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        nomeCor.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        nomeCor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "escolha a cor" }));
        nomeCor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                nomeCorMousePressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel2.setText("Cor :");

        nomeGen.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        nomeGen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "escolha o g??nero" }));

        jLabel3.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel3.setText("G??nero :");

        nomeTam.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        nomeTam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "escolha o tamanho" }));

        nomeTipo.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        nomeTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "escolha o tipo" }));

        jLabel4.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel4.setText("Tipo :");

        escreverProd.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        escreverProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                escreverProdActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel6.setText("Nome:");

        descricaoProd.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel7.setText("Descri????o :");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel5.setText("Tamanho :");

        jLabel1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel1.setText("Preco Venda :");

        escreverPrecVend.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel8.setText("Preco Atual Venda :");

        escrPrecAtualVend.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        escrPrecAtualVend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                escrPrecAtualVendActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel9.setText("Quantidade Stock :");

        escrqtdStock.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel10.setText("Ativo: ");

        feedbackMessage.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        ok.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        ok.setText("CONFIRMAR");
        ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okActionPerformed(evt);
            }
        });

        cancelar.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        cancelar.setText("CANCELAR");
        cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarActionPerformed(evt);
            }
        });

        CriarProd.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        CriarProd.setText("Criar Produto");

        Ativar.setFont(new java.awt.Font("Arial", 0, 48)); // NOI18N
        Ativar.setAlignmentX(0.5F);
        Ativar.setInheritsPopupMenu(true);
        Ativar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AtivarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel1)
                    .addComponent(jLabel7)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Ativar)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(feedbackMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(188, 188, 188))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(escreverPrecVend, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(escrqtdStock, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                                            .addComponent(escrPrecAtualVend, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addComponent(escreverProd, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
                                        .addComponent(descricaoProd)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(nomeTam, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(nomeTipo, javax.swing.GroupLayout.Alignment.LEADING, 0, 296, Short.MAX_VALUE)
                                                .addComponent(nomeCor, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(nomeGen, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                                .addGap(21, 21, 21))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(ok)
                                .addGap(91, 91, 91)))
                        .addComponent(cancelar)))
                .addContainerGap(51, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(CriarProd)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(CriarProd)
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nomeCor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(nomeGen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nomeTam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(nomeTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(escreverProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(descricaoProd, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(escreverPrecVend, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(escrPrecAtualVend, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(escrqtdStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(feedbackMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel10)
                                .addComponent(Ativar)))
                        .addContainerGap(77, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ok, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 97, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okActionPerformed
        // TODO add your handling code here:
        feedbackMessage.setText("");
        if (nomeTipo.getSelectedIndex()<1) {
                feedbackMessage.setText("Deve selecionar um Tipo v??lido!");
        }
        else{
            if (nomeGen.getSelectedIndex()<1) {
                feedbackMessage.setText("Deve selecionar um Genero v??lido!");
            }
            else{
                if(nomeTam.getSelectedIndex()<1) {
                feedbackMessage.setText("Deve selecionar um Tamanho v??lido!");   
                }
                else{
                    if(nomeCor.getSelectedIndex()<1) {
                    feedbackMessage.setText("Deve selecionar uma Cor v??lida!");
                    }
                }
            } 
        }
    
        if (modoOper == 0) {
            
                
            Produto produto = new Produto();
            produto.setNome(escreverProd.getText());
            produto.setDescricao(descricaoProd.getText());
            Tipo tipo = this.listaTipo.get(nomeTipo.getSelectedIndex()-1);
            produto.setCodtipo(tipo);
            Genero genero = this.listaGeneros.get(nomeGen.getSelectedIndex()-1);
            produto.setCodgenero(genero);
            Tamanho tamanho= this.listaTamanhos.get(nomeTam.getSelectedIndex()-1);
            produto.setCodtamanho(tamanho);
            Cor cor = this.listaCores.get(nomeCor.getSelectedIndex()-1);
            produto.setCodcor(cor);
            
            produto.setPrecovenda(BigDecimal.valueOf(Double.parseDouble(escreverPrecVend.getText())));
            produto.setPrecoatualvenda(BigDecimal.valueOf(Double.parseDouble(escrPrecAtualVend.getText())));
            produto.setQtdstock(Integer.parseInt(escrqtdStock.getText()));
            //produto.setAtivo(Short.parseShort(string));
            if(Ativar.isSelected())
                produto.setAtivo((short)1);
            else
                produto.setAtivo((short)0);
                
            
            ProdutoBLL.create(produto);

            feedbackMessage.setText("PRODUTO CRIADO COM SUCESSO!");
            escreverProd.setText("");
            descricaoProd.setText("");
            escreverPrecVend.setValue("");
            escrPrecAtualVend.setValue("");
            escrqtdStock.setValue("");
            Ativar.setText("");
                      
        }
        else{
            //Modo Update
            prodToUpdte.setNome(escreverProd.getText());
            prodToUpdte.setDescricao(descricaoProd.getText());
            prodToUpdte.setPrecovenda(BigDecimal.valueOf(Double.parseDouble(escreverPrecVend.getText())));
            prodToUpdte.setPrecoatualvenda(BigDecimal.valueOf(Double.parseDouble(escrPrecAtualVend.getText())));
            prodToUpdte.setQtdstock(Integer.parseInt(escrqtdStock.getText()));
            prodToUpdte.setAtivo((short)0);
            
            
            
            ProdutoBLL.update(prodToUpdte);
            feedbackMessage.setText("PRODUTO ATUALIZADO COM SUCESSO!");
            escreverProd.setText("");
            descricaoProd.setText("");
            escreverPrecVend.setValue("");
            escrPrecAtualVend.setValue("");
            escrqtdStock.setValue("");
            Ativar.setText("");
            
            }
    }//GEN-LAST:event_okActionPerformed

    private void cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarActionPerformed
        // TODO add your handling code here:
        this.show(false);
    }//GEN-LAST:event_cancelarActionPerformed

    private void nomeCorMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nomeCorMousePressed
        // TODO add your handling code here:
        //listar cores
        //         List<Cor> listaCores = null;
//               
//        listaCores = CorBLL.readAll();
//           ArrayList<String> cores = new ArrayList<String>();
//        for(Cor cor1 : listaCores){
//           cores.add( cor1.getNome());
//           
//           nomeCor.addItem(cor1.getNome());
//        } 
    }//GEN-LAST:event_nomeCorMousePressed

    private void escreverProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_escreverProdActionPerformed
        // TODO add your handling code here:
        CriarCorUI updateCor = new CriarCorUI();
        
        updateCor.setVisible(true);
    }//GEN-LAST:event_escreverProdActionPerformed

    private void escrPrecAtualVendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_escrPrecAtualVendActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_escrPrecAtualVendActionPerformed

    private void AtivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AtivarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AtivarActionPerformed

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
            java.util.logging.Logger.getLogger(CriarProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CriarProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CriarProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CriarProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CriarProduto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox Ativar;
    private javax.swing.JLabel CriarProd;
    private javax.swing.JButton cancelar;
    private javax.swing.JFormattedTextField descricaoProd;
    private javax.swing.JFormattedTextField escrPrecAtualVend;
    private javax.swing.JFormattedTextField escreverPrecVend;
    private javax.swing.JFormattedTextField escreverProd;
    private javax.swing.JFormattedTextField escrqtdStock;
    private javax.swing.JLabel feedbackMessage;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox<String> nomeCor;
    private javax.swing.JComboBox<String> nomeGen;
    private javax.swing.JComboBox<String> nomeTam;
    private javax.swing.JComboBox<String> nomeTipo;
    private javax.swing.JButton ok;
    // End of variables declaration//GEN-END:variables
}
