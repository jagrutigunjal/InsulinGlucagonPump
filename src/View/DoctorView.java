package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JScrollPane;

public class DoctorView extends GUI {

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DoctorView frame = new DoctorView();
                    frame.setVisible(true);
                    frame.setTitle("DOCTOR VIEW");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public DoctorView() {
        super("Doctor View");
        setBounds(100, 100, 670, 450);
        panel_graph = chart.createPanel(mathematicalModel);
        JScrollPane scrollPane = new JScrollPane();
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
                gl_panel.createParallelGroup(Alignment.TRAILING)
                        .addGroup(gl_panel.createSequentialGroup()
                                .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_panel.createSequentialGroup()
                                                .addGap(487)
                                                .addComponent(messageLbl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                                                .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(progressBar_Battery, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lblBattery, Alignment.TRAILING)))
                                        .addGroup(gl_panel.createSequentialGroup()
                                                .addGap(12)
                                                .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                                                        .addGroup(gl_panel.createSequentialGroup()
                                                                .addComponent(panel_graph, GroupLayout.PREFERRED_SIZE, 447, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18))
                                                        .addGroup(gl_panel.createSequentialGroup()
                                                                .addComponent(progressBar_Insulin, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                                .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                                                                        .addComponent(lblGlucagon)
                                                                        .addGroup(gl_panel.createSequentialGroup()
                                                                                .addComponent(glucagonBar, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                                                                                .addComponent(timeLbl)
                                                                                .addGap(83)))))
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(gl_panel.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(lblInsulin)
                                                .addPreferredGap(ComponentPlacement.RELATED, 606, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        gl_panel.setVerticalGroup(
                gl_panel.createParallelGroup(Alignment.TRAILING)
                        .addGroup(gl_panel.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(glucagonBar, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                        .addComponent(progressBar_Insulin, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                                                .addComponent(progressBar_Battery, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(timeLbl)))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblBattery)
                                        .addComponent(lblInsulin, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblGlucagon))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(panel_graph, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                                        .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(messageLbl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        gl_panel.linkSize(SwingConstants.HORIZONTAL, new Component[]{progressBar_Insulin, progressBar_Battery});
        panel.setLayout(gl_panel);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBackground(UIManager.getColor("Panel.background"));
        contentPane.add(buttonsPanel, BorderLayout.SOUTH);

        JButton btnOptions = new JButton("OPTIONS");
        btnOptions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new OptionsView().setVisible(true);
            }
        });
        btnOptions.setBackground(new Color(220, 220, 220));
        btnOptions.setIcon(new ImageIcon(optionsIcon));
        btnOptions.setAlignmentY(Component.BOTTOM_ALIGNMENT);

        GroupLayout gl_buttonsPanel = new GroupLayout(buttonsPanel);
        gl_buttonsPanel.setHorizontalGroup(
                gl_buttonsPanel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_buttonsPanel.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnOptions, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(485, Short.MAX_VALUE))
        );
        gl_buttonsPanel.setVerticalGroup(
                gl_buttonsPanel.createParallelGroup(Alignment.TRAILING)
                        .addGroup(Alignment.LEADING, gl_buttonsPanel.createSequentialGroup()
                                .addComponent(btnOptions, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        buttonsPanel.setLayout(gl_buttonsPanel);
    }
}
