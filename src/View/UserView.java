package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import Model.MailUtil;

import static Model.SimulatorUtility.MONITORING_GLUCOSE_TIME;

public class UserView extends GUI {

    JLabel lblGlucoseLevelStatus;
    ExecutorService executor;
    int actualGL;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UserView frame = new UserView();
                    frame.setVisible(true);
                    frame.setTitle("Insulin Pump");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public UserView() {
        super("User View");
        JPanel panel_graph = new JPanel();
        panel_graph.setLayout(new BorderLayout());
        JButton btnEmergency = new JButton("EMERGENCY");
        Color red = Color.decode("#ee2211");
        btnEmergency.setBackground(red);
        btnEmergency.setForeground(Color.white);
        btnEmergency.setIcon(new ImageIcon(emergencyIcon));
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

        JButton btnBolus = new JButton("        BOLUS       ");
        btnBolus.setBackground(new Color(220, 220, 220));
        btnBolus.setIcon(new ImageIcon(bolusIcon));
        btnBolus.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        btnBolus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                BolusGUI.getInstance(actualGL, UserView.this, chart, pdata, battery, insulin).setVisible(true);
            }
        });
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
                                                                                .addGap(7)
                                                                                .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                                                                                        .addComponent(lblGlucagon)
                                                                                        .addGroup(gl_panel.createSequentialGroup()
                                                                                                .addComponent(glucagonBar, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(105)
                                                                                                .addComponent(timeLbl)))))))
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
                                                        .addComponent(progressBar_Battery, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lblBattery)
                                                        .addComponent(btnEmergency, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)))
                                        .addGroup(gl_panel.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(btnBolus, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(gl_panel.createSequentialGroup()
                                                .addGap(243)
                                                .addComponent(messageLbl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );
        gl_panel.setVerticalGroup(
                gl_panel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel.createSequentialGroup()
                                .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_panel.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                                                        .addGroup(gl_panel.createSequentialGroup()
                                                                .addComponent(progressBar_Insulin, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                                .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                                                                        .addComponent(lblBattery)
                                                                        .addComponent(lblInsulin, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)))
                                                        .addGroup(gl_panel.createSequentialGroup()
                                                                .addComponent(glucagonBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                                .addComponent(lblGlucagon)))
                                                .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                                                        .addGroup(gl_panel.createSequentialGroup()
                                                                .addGap(26)
                                                                .addComponent(btnEmergency, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(gl_panel.createSequentialGroup()
                                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                                .addComponent(panel_graph, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(27)
                                                                .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                                                                        .addComponent(messageLbl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(btnBolus, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)))))
                                        .addGroup(gl_panel.createSequentialGroup()
                                                .addGap(20)
                                                .addComponent(timeLbl))
                                        .addGroup(gl_panel.createSequentialGroup()
                                                .addGap(11)
                                                .addComponent(progressBar_Battery, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(27, Short.MAX_VALUE))
        );

        lblGlucoseLevelStatus = new JLabel("Normal Blood Glucose ");
        lblGlucoseLevelStatus.setFont(new Font("Tahoma", Font.PLAIN, 17));
        panel_graph.add(lblGlucoseLevelStatus, BorderLayout.CENTER);
        gl_panel.linkSize(SwingConstants.VERTICAL, new Component[]{glucagonBar, progressBar_Insulin, progressBar_Battery});
        gl_panel.linkSize(SwingConstants.HORIZONTAL, new Component[]{glucagonBar, progressBar_Insulin, progressBar_Battery});
        panel.setLayout(gl_panel);

        //start monitoring
        monitorGlucoseLevel();
    }

    private class AddToQueue implements Runnable {
        public void run() {
            try {
                // add a item of random data to queue
                long injectedTime = System.currentTimeMillis();
                actualGL = mathematicalModel.glucoseLevelChange(110, 120, injectedTime, false,
                        false, UserView.this, battery, insulin, pdata);
                if (actualGL > 200) {
                    //String song="C:\\Users\\RAKA\\Desktop\\bewafa.wav";
                    //audioplayer02.playAudio(song);
                    lblGlucoseLevelStatus.setForeground(Color.red);
                    lblGlucoseLevelStatus.setText("WARNING! Glucose level is high: " + actualGL);
                } else if (actualGL < 70) {
                    //String song="C:\\Users\\RAKA\\Desktop\\bewafa.wav";
                    //audioplayer02.playAudio(song);
                    lblGlucoseLevelStatus.setForeground(Color.blue);
                    lblGlucoseLevelStatus.setText("WARNING! Glucose level is low: " + actualGL);
                } else {
                    lblGlucoseLevelStatus.setForeground(Color.green);
                    lblGlucoseLevelStatus.setText("Normal Glucose level: " + actualGL);
                }
                Thread.sleep(MONITORING_GLUCOSE_TIME);
                executor.execute(this);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void monitorGlucoseLevel(){
        executor = Executors.newCachedThreadPool(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setDaemon(true);
                return thread;
            }
        });

        AddToQueue addToQueue = new AddToQueue();
        executor.execute(addToQueue);
    }

}
