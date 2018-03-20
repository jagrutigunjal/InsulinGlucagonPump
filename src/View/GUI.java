package View;

import Model.*;
import javafx.embed.swing.JFXPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static Model.SimulatorUtility.getStringDateFormat;

/**
 * Created by syka on 7/7/16.
 */
public abstract class GUI extends JFrame{

    static final long serialVersionUID = 1L;
    JPanel panel;
    JPanel contentPane;
    public JProgressBar progressBar_Battery;
    public JProgressBar progressBar_Insulin;
    Timer updateTimeTimer;
    JLabel timeLbl;
    AnimatedLineChart chart;
    JTextArea messageLbl;
    JLabel lblInsulin;
    JLabel lblBattery;
    JFXPanel panel_graph;
    JProgressBar glucagonBar;
    JLabel lblGlucagon;
    Battery battery;
    InsulinReservoir insulin;
    PatientData pdata;
    Patient p1;
    Image emergencyIcon;
    Image bolusIcon;
    Image optionsIcon;
    MathematicalModel mathematicalModel;

    public GUI(String title){
        mathematicalModel = new MathematicalModel();
        battery = new Battery();
        insulin = new InsulinReservoir();
        pdata = new PatientData();
        p1 = new Patient();
        instantiateIconsImages();
        createPatient();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 665, 367);
        setResizable(false);
        setVisible(true);
        setTitle(title);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        setDefaultLookAndFeelDecorated(true);
        panel = new JPanel();
        panel.setBackground(UIManager.getColor("Panel.background"));
        contentPane.add(panel, BorderLayout.CENTER);

        chart = new AnimatedLineChart(false, false, this, battery, insulin, pdata);

        progressBar_Insulin = new JProgressBar();
        progressBar_Insulin.setStringPainted(true);
        progressBar_Insulin.setValue(insulin.getAvailable());
        progressBar_Insulin.setForeground(new Color(0, 128, 0));

        progressBar_Battery = new JProgressBar();
        progressBar_Battery.setStringPainted(true);
        progressBar_Battery.setBackground(new Color(250, 240, 240));
        progressBar_Battery.setForeground(new Color(30, 144, 255));
        progressBar_Battery.setValue(battery.getAvailable());

        lblInsulin = new JLabel("Insulin");
        lblInsulin.setFont(new Font("Tahoma", Font.PLAIN, 14));

        lblBattery = new JLabel("Battery");
        lblBattery.setFont(new Font("Tahoma", Font.PLAIN, 14));

        String currentTimeString = getStringDateFormat();
        timeLbl = new JLabel(currentTimeString);
        timeLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
        // Create a timer.
        ActionListener action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                timeLbl.setText(getStringDateFormat());
                checkBatteryAndInsulinLevels();
            }
        };
        updateTimeTimer = new Timer(1000, action);
        updateTimeTimer.setInitialDelay(1000);
        updateTimeTimer.start();

        panel_graph = new JFXPanel();
        panel_graph.setBackground(new Color(255, 255, 255));
        panel_graph.setSize(250, 130);

        messageLbl = new JTextArea("");
    //    messageLbl.setText("Mesazhi");
        messageLbl.setWrapStyleWord(true);
        messageLbl.setBackground(UIManager.getColor("Panel.background"));
        // messageLbl.setHorizontalAlignment(SwingConstants.CENTER);
        messageLbl.setFont(new Font("Serif", Font.PLAIN, 14));

        glucagonBar = new JProgressBar();
        glucagonBar.setForeground(new Color(0, 128, 128));
        glucagonBar.setValue(100);
        glucagonBar.setStringPainted(true);
        glucagonBar.setBackground(new Color(0, 128, 128));

        lblGlucagon = new JLabel("Glucagon");
        lblGlucagon.setFont(new Font("Tahoma", Font.PLAIN, 14));
    }

    private void setMessageFont(Font font, String insulinLowLevelMessage) {
        messageLbl.setForeground(Color.red);
        messageLbl.setFont(font);
        
        messageLbl.setText(insulinLowLevelMessage);
    }

    void checkBatteryAndInsulinLevels() {
        if (battery.getAvailable() < 20 && insulin.getAvailable() < 20) {
            if (!messageLbl.getText().contains(Constants.batteryLowLevelMessage + Constants.insulinLowLevelMessage)) {
                setMessageFont(new Font(Font.SERIF, Font.PLAIN, 13),
                        Constants.batteryLowLevelMessage + "\n" + Constants.insulinLowLevelMessage);
                progressBar_Battery.setForeground(Color.red);
                progressBar_Insulin.setForeground(Color.red);
            }
        } else if (battery.getAvailable() < 20) {
            if (!messageLbl.getText().equals(Constants.batteryLowLevelMessage)) {
                setMessageFont(new Font(Font.MONOSPACED, Font.BOLD, 16), Constants.batteryLowLevelMessage);
                progressBar_Battery.setForeground(Color.red);
            }
        } else if (insulin.getAvailable() < 20) {
            if (!messageLbl.getText().equals(Constants.batteryLowLevelMessage)) {
                setMessageFont(new Font(Font.SERIF, Font.PLAIN, 13), Constants.insulinLowLevelMessage);
                progressBar_Insulin.setForeground(Color.red);
            }
        } else {
            messageLbl.setText("");
            progressBar_Battery.setForeground(new Color(30, 144, 255));
            progressBar_Insulin.setForeground(new Color(0, 128, 0));
        }
    }


    //create patient data
    void createPatient() {
        p1.setName("Patient1");
        p1.setNormal_bg_level(100);
        p1.setBasal_prescribed_rate(0.7);
        p1.setDaily_basal_prescribed(17);
        p1.setDaily_bolus_prescribed(27);
        p1.setPrescribed_glucagon(1);

        pdata.savePatient(p1);

        DailyEvents e1 = new EatingEvent("Breakfast", 30,
                SimulatorUtility.formatTimeInMinutes("9:00"),
                p1.getNormal_bg_level());
        DailyEvents e2 = new EatingEvent("Lunch", 90,
                SimulatorUtility.formatTimeInMinutes("14:43"),
                p1.getNormal_bg_level());
        DailyEvents e3 = new EatingEvent("Dinner", 80,
                SimulatorUtility.formatTimeInMinutes("20:00"),
                p1.getNormal_bg_level());
        pdata.addPatientEvent(e1, p1.getName());
        pdata.addPatientEvent(e2, p1.getName());
        pdata.addPatientEvent(e3, p1.getName());
    }

    public void instantiateIconsImages(){
        try {
            emergencyIcon = ImageIO.read(getClass().getResource("/images/emergency.png"));
            bolusIcon = ImageIO.read(getClass().getResource("/images/bolus.png"));
            optionsIcon =  ImageIO.read(getClass().getResource("/images/settings.png"));
        } catch (IOException ex) {
        }
    }
}
