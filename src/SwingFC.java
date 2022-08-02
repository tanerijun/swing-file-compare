// A file comparison utility made with Swing

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class SwingFC implements ActionListener {
    JTextField jtfFirst; // holds the first file name
    JTextField jtfSecond; // holds the second file name
    JButton jbtnComp; // button to compare the files
    JLabel jlabFirst, jlabSecond; // displays prompts
    JLabel jlabResult; // displays results and error messages

    SwingFC() {
        JFrame jfrm = new JFrame("Compare Files");
        jfrm.setLayout(new FlowLayout());
        jfrm.setSize(200, 190);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // text fields for the file names
        jtfFirst = new JTextField(14);
        jtfSecond = new JTextField(14);
        jtfFirst.setActionCommand("fileA");
        jtfSecond.setActionCommand("fileB");

        // compare button
        jbtnComp = new JButton("Compare");
        jbtnComp.addActionListener(this);

        // labels
        jlabFirst = new JLabel("First file: ");
        jlabSecond = new JLabel("Second file: ");
        jlabResult = new JLabel("");

        // add components to content pane
        jfrm.add(jlabFirst);
        jfrm.add(jtfFirst);
        jfrm.add(jlabSecond);
        jfrm.add(jtfSecond);
        jfrm.add(jbtnComp);
        jfrm.add(jlabResult);

        // display frame
        jfrm.setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        int i = 0, j = 0; // cursor that go through both files

        // confirm that both file names have been entered
        if(jtfFirst.getText().equals("")) {
            jlabResult.setText("Please enter the name of the first file!");
            return;
        }
        if (jtfSecond.getText().equals("")) {
            jlabResult.setText("Please enter the name of the second file!");
            return;
        }

        // compare the files
        try (FileInputStream f1 = new FileInputStream(jtfFirst.getText());
             FileInputStream f2 = new FileInputStream(jtfSecond.getText())) {
            do {
                i = f1.read();
                j = f2.read();
                if (i != j) break;
            } while (i != -1 && j != -1);

            if (i != j) {
                jlabResult.setText("Files are not the same.");
            } else {
                jlabResult.setText("Files are the same");
            }
        } catch (IOException exc) {
            jlabResult.setText("Error comparing files.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SwingFC();
            }
        });
    }
}
