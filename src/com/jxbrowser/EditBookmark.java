/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jxbrowser;

import com.MultiBrowse.customcontrol.LocalDBConnect;
import com.MultiBrowse.customcontrol.objBookmarks;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author title
 */
public class EditBookmark extends javax.swing.JDialog {

    /**
     * Creates new form EditBookmark
     */
    private String m_strUrl;
    private String m_strTitle;
    private String m_strBookmarkPath;
    private int m_nProfileId;
    private Image m_favIcon;
    private String m_strUserDir;
    private String m_StrFavDir;
    public EditBookmark(java.awt.Frame parent, boolean modal, String strTitle, String strUrl, String strBookmarkPath, int nProfileId, Image favIcon, String strUserDir) {
        super(parent, modal);
        
        setTitle("Edit Bookmark");
        initComponents();
        
        m_strUrl = strUrl;
        m_strTitle = strTitle;
        m_strBookmarkPath = strBookmarkPath;
        m_nProfileId = nProfileId;
        m_favIcon = favIcon;
        
        m_strUserDir = strUserDir;
        createFavIconFolder();
        initNameText();
    }

    
    private boolean createFavIconFolder(){
        File file = new File(m_strUserDir + "/FavIcons");
        if (!file.exists()) {
            if (file.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }
        
        m_StrFavDir = m_strUserDir + "/FavIcons";
        return true;
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
        jTxt_Name = new javax.swing.JTextField();
        jBtn_Ok = new javax.swing.JButton();
        jBtn_Cancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Name:");

        jTxt_Name.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jBtn_Ok.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jBtn_Ok.setText("Ok");
        jBtn_Ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtn_OkActionPerformed(evt);
            }
        });

        jBtn_Cancel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jBtn_Cancel.setText("Cancel");
        jBtn_Cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtn_CancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTxt_Name)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(137, Short.MAX_VALUE)
                .addComponent(jBtn_Ok, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBtn_Cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jTxt_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBtn_Cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jBtn_Ok, javax.swing.GroupLayout.PREFERRED_SIZE, 20, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void initNameText(){
        jTxt_Name.setText(m_strTitle);
    }
    
    private void writeToFile(String list) throws IOException{
///     
        
        File f = new File(m_strBookmarkPath);
        
//        System.out.println(f);
       
//        if (list.contains("https://www.google.com/sorry/index"))
//            return;
        FileWriter fw = new FileWriter(f,true);
//        System.out.println(fw);
        try{
            BufferedWriter bw = new BufferedWriter(fw);
//            System.out.println(bw);
            bw.write(list);
            bw.newLine();
            bw.flush();
            bw.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
          ///
    }
    
    private String saveFavIcon(){
        // get image from imageicon
        java.awt.Image image = m_favIcon;

        // cast it to bufferedimage
        BufferedImage buffered = getBufferedImage(image);
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd HH_mm_ss");  
        LocalDateTime now = LocalDateTime.now(); 
        
        String strFavIconPath = m_StrFavDir + "/" + dtf.format(now) + ".png";
   
        try {
            // save to file
            File outputfile = new File(m_StrFavDir + "/" + dtf.format(now) + ".png");
            ImageIO.write(buffered, "png", outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        

        return strFavIconPath;
    }
    
    public static BufferedImage getBufferedImage(Image img)
    {
        if (img instanceof BufferedImage)
        {
           return (BufferedImage) img;
        }

        BufferedImage bimage = new BufferedImage(img.getWidth(null), 
                        img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }
    
    private void jBtn_OkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtn_OkActionPerformed
        // TODO add your handling code here:
        if (jTxt_Name.getText().isEmpty())
            JOptionPane.showMessageDialog(null, "Please enter the name");
        
        String strFavIconPath = saveFavIcon();
//        try {
//            writeToFile(jTxt_Name.getText() + "::" + m_strUrl);
//        } catch (IOException ex) {
//            Logger.getLogger(JXTabControl.class.getName()).log(Level.SEVERE, null, ex);
//        }
        LocalDBConnect dbCon = new LocalDBConnect();
        objBookmarks addData = new objBookmarks();
        addData.id = -1;
        addData.profileId = m_nProfileId;
        addData.strBookmarkName = jTxt_Name.getText();
        addData.strBookmarkUrl = m_strUrl;
        addData.strFavIconPath = strFavIconPath;
       
        dbCon.addBookmark(addData);
        
        dispose();
    }//GEN-LAST:event_jBtn_OkActionPerformed

    private void jBtn_CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtn_CancelActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jBtn_CancelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtn_Cancel;
    private javax.swing.JButton jBtn_Ok;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTxt_Name;
    // End of variables declaration//GEN-END:variables
}
