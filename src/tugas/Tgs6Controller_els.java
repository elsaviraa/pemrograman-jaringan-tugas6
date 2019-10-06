/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PushbackReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

/**
 *
 * @author Elsa
 */
public class Tgs6Controller_els {
    private Tgs6_els view;
    private List<Integer> list = new ArrayList<>();
 
     public Tgs6Controller_els(Tgs6_els view) {
         this.view = view;
         
         this.view.getBtBaca().addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 try {
                proses();      
                 } catch (FileNotFoundException ex) {
                Logger.getLogger(Tgs6_els.class.getName()).log(Level.SEVERE, null, ex);     
                } catch (BadLocationException | IOException ex) {
                Logger.getLogger(Tgs6_els.class.getName()).log(Level.SEVERE, null, ex);
                }
             }
         });
         
         this.view.getBtSimpan().addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 save();
             }
         });
         
     }
     
     private void proses() throws FileNotFoundException, BadLocationException, IOException{
         JFileChooser loadFile = view.getLoadFile();
         StyledDocument doc = view.getTxtPane().getStyledDocument();
             if (JFileChooser.APPROVE_OPTION == loadFile.showOpenDialog(view)) {
                 
                 PushbackReader  reader = new PushbackReader(new FileReader(loadFile.getSelectedFile()));
                 LineNumberReader numberReader = new LineNumberReader(new FileReader(loadFile.getSelectedFile()));
                 
                 char[] kat = new char[(int) loadFile.getSelectedFile().length()];
                 try {
                     reader.read(kat);
                     String file = null;
                     String file1 = null;
                     doc.insertString(0, "", null);
                     
                     int hitungKat=0;
                     int hitungKar=0;
                     
                     if ((file = new String(kat))!=null) {
                         String[] listkat = file.split("\\s+");
                      
                        hitungKar += kat.length;
                        hitungKat += listkat.length;
                       
                        doc.insertString(doc.getLength(), "" + file + "\n", null);
                     }while ((file1 = numberReader.readLine()) !=null) {                         
                     }
                        int hitungLine =numberReader.getLineNumber();
                        
                     JOptionPane.showMessageDialog(null, "File Berhasil Dibaca" + "\n"
                        + "Jumlah Baris = " + hitungLine + "\n"
                        + "Jumlah Kata = " + hitungKat + "\n"
                        + "Jumlah Karakter = " + hitungKar);

                 } catch (IOException | BadLocationException ex) {
                     Logger.getLogger(Tgs6Controller_els.class.getName()).log(Level.SEVERE, null, ex);
                 } finally {
                     if (reader != null) {
                         try {
                             reader.close();
                         } catch (IOException ex) {
                             Logger.getLogger(Tgs6Controller_els.class.getName()).log(Level.SEVERE, null, ex);
                         }
                     }
                 }
             }
     }

     private void save(){
        JFileChooser loadFile = view.getLoadFile();
        if (JFileChooser.APPROVE_OPTION == loadFile.showSaveDialog(view)) {
            BufferedOutputStream writer = null;
            try {
                String contents = view.getTxtPane().getText();
                 if (contents != null && !contents.isEmpty()) {
                     writer = new BufferedOutputStream(new FileOutputStream(loadFile.getSelectedFile()));
                     writer.write(contents.getBytes());
                     JOptionPane.showMessageDialog(view, "File berhasil ditulis.", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                }
//            BufferedWriter writer = null;
//            try {
//                String contents = view.getTxtPane().getText();
//                if (contents != null && !contents.isEmpty()) {
//                    writer = new BufferedWriter(new FileWriter(loadFile.getSelectedFile()));
//                    writer.write(contents);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Tgs6Controller_els.class.getName()).log(Level.SEVERE,null,ex);
            } catch (IOException ex){
                Logger.getLogger(Tgs6Controller_els.class.getName()).log(Level.SEVERE,null,ex);
            }finally{
                if (writer != null) {
                    try {
                        writer.flush();
                        writer.close();
                        view.getTxtPane().setText("");
                    } catch (IOException ex) {
                        Logger.getLogger(Tgs6Controller_els.class.getName()).log(Level.SEVERE,null,ex);
                    }
   
                }
            }
        }
    }
}
