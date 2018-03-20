package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import Model.*;
import javax.swing.JScrollPane;
import java.awt.SystemColor;

public class SimulatorView extends GUI {

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SimulatorView frame = new SimulatorView();
                    frame.setVisible(true);
                    frame.setTitle("SIMULATOR VIEW");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public SimulatorView() {
        super("Simulator view");
        setBounds(100, 100, 685, 535);
        panel_graph = chart.createPanel(mathematicalModel);
        JButton btnEmergency = new JButton("EMERGENCY");
        btnEmergency.setBackground(Color.RED);
        btnEmergency.setForeground(Color.white);
        btnEmergency.addActionListener(new ActionListener() {
            //send email
            public void actionPerformed(ActionEvent e) {
                String[] recipients = new String[]{"jagruti.gunjal92@gmail.com"};
                String[] bccRecipients = new String[]{"raka9256@gmail.com"};
                String subject = "Hi this is test Mail";
                String messageBody = "Test Mail for insulin pump";
                new MailUtil().sendMail(recipients, bccRecipients, subject, messageBody);
            }
        });

        btnEmergency.setIcon(new ImageIcon(emergencyIcon));
        JScrollPane scrollPane = new JScrollPane();
        JButton btnBolus = new JButton("        BOLUS       ");
        btnBolus.setBackground(new Color(220, 220, 220));
        btnBolus.setIcon(new ImageIcon(bolusIcon));
        btnBolus.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        btnBolus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                BolusGUI.getInstance(chart.getActualGL(), SimulatorView.this, chart, pdata, battery, insulin).setVisible(true);
            }
        });

        JButton btnOptions = new JButton("OPTIONS");
        btnOptions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new OptionsView().setVisible(true);
            }
        });
        btnOptions.setBackground(new Color(220, 220, 220));
        btnOptions.setIcon(new ImageIcon(optionsIcon));
        btnOptions.setAlignmentY(Component.BOTTOM_ALIGNMENT);

        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
        		gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addContainerGap()
									.addComponent(lblInsulin))
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(12)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(panel_graph, GroupLayout.PREFERRED_SIZE, 457, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(progressBar_Insulin, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
												.addComponent(lblGlucagon, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
												.addGroup(gl_panel.createSequentialGroup()
													.addComponent(glucagonBar, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
													.addGap(58)
													.addComponent(timeLbl))))
										.addGroup(gl_panel.createSequentialGroup()
											.addGap(1)
											.addComponent(btnBolus, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(btnOptions, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)))))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
									.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addComponent(progressBar_Battery, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblBattery)))
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(18)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
										.addComponent(btnEmergency, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)))))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(487)
							.addComponent(messageLbl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(

gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblInsulin))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(12)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(panel_graph, GroupLayout.PREFERRED_SIZE, 457, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(progressBar_Insulin, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(glucagonBar, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
									.addGap(58)
									.addComponent(timeLbl)))
							.addPreferredGap(ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblBattery, Alignment.TRAILING)
								.addComponent(progressBar_Battery, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(133)
							.addComponent(lblGlucagon, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
							.addGap(442))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(487)
							.addComponent(btnEmergency, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(487)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(13)
							.addComponent(btnBolus, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnOptions, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(messageLbl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(102)))
					.addGap(22))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(20)
							.addComponent(timeLbl)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(progressBar_Insulin, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
							.addGap(7))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(glucagonBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(7))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(progressBar_Battery, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
							.addGap(7)))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblBattery)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnEmergency, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(152)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblInsulin, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblGlucagon, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_graph, GroupLayout.PREFERRED_SIZE, 277, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(18)
							.addComponent(btnBolus, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(19)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(messageLbl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnOptions, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);

		gl_panel.linkSize(SwingConstants.VERTICAL, new Component[]{glucagonBar, progressBar_Insulin, progressBar_Battery});
        gl_panel.linkSize(SwingConstants.VERTICAL, new Component[]{btnBolus, btnOptions});
        gl_panel.linkSize(SwingConstants.HORIZONTAL, new Component[]{glucagonBar, progressBar_Insulin, progressBar_Battery});
        gl_panel.linkSize(SwingConstants.HORIZONTAL, new Component[]{btnBolus, btnOptions});
        panel.setLayout(gl_panel);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBackground(new Color(173, 216, 230));
        contentPane.add(buttonsPanel, BorderLayout.SOUTH);

        JButton btnLowBtr = new JButton("Low Battery");
        btnLowBtr.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				battery.setPower(20);
				//((GUI) gui).progressBar_Battery.setValue(battery.getAvailable());
				
		         checkBatteryAndInsulinLevels();
			}
		});
       // JButton btnLowSimulation = new JButton("Tired Time");

        JButton btnLowIns = new JButton("Low Insulin");
        btnLowIns.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insulin.setInsulin(19);
				progressBar_Insulin.setValue(insulin.getAvailable());
		       // progressBar_Battery.setValue(battery.getAvailable());
		         checkBatteryAndInsulinLevels();
			}
		});

        JLabel lblTestSimulator = new JLabel("Test Simulator");
        lblTestSimulator.setFont(new Font("Tahoma", Font.ITALIC, 10));
        GroupLayout gl_buttonsPanel = new GroupLayout(buttonsPanel);
        gl_buttonsPanel.setHorizontalGroup(
                gl_buttonsPanel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_buttonsPanel.createSequentialGroup()
                                .addGap(81)
                                .addComponent(btnLowBtr, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
                                .addGap(86)
                             //   .addComponent(btnLowSimulation, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
                                .addComponent(btnLowIns, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
                                .addGap(95))
                        .addGroup(gl_buttonsPanel.createSequentialGroup()
                                .addComponent(lblTestSimulator, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(580, Short.MAX_VALUE))
        );
        gl_buttonsPanel.setVerticalGroup(
                gl_buttonsPanel.createParallelGroup(Alignment.TRAILING)
                        .addGroup(gl_buttonsPanel.createSequentialGroup()
                                .addComponent(lblTestSimulator)
                                .addPreferredGap(ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                                .addGroup(gl_buttonsPanel.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(btnLowBtr)
                                      //  .addComponent(btnLowSimulation)
                                        .addComponent(btnLowIns))
                                .addContainerGap())
        );
        buttonsPanel.setLayout(gl_buttonsPanel);
    }

}
