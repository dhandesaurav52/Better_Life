package better.life;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import java.awt.Font;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.util.Locale;
import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;

public class AlarmClock extends JFrame implements ActionListener {

    public static String ACCOUNT_SID = "AC54ed93e640307d96d1d345e186a86a7f";

    public static String AUTH_TOKEN = "e489d74851e08add2be21fc0c35e5be4";

    private JLabel timeLabel;
    private JTextField hoursTextField, minutesTextField,messageTextField,emergencyContact1TextField;
    private JButton addButton, removeButton;
    private JList<String> alarmList;
    private DefaultListModel<String> alarmListModel;
    private Timer timer;
    private List<Alarm> alarms;
    private Boolean alarmFlag;

    public AlarmClock() {

        super("Alarm Clock");
        alarms = new ArrayList<>();
        timeLabel = new JLabel();
        timeLabel.setFont(new Font("Jua", Font.BOLD, 40));
        JPanel timePanel = new JPanel();
        timePanel.setBorder(new MatteBorder(5, 5,5, 5, (Color) new Color(0, 0, 0)));
        timePanel.add(timeLabel);
        JLabel hoursLabel = new JLabel("Hours:");
        hoursTextField = new JTextField(2);
        JLabel minutesLabel = new JLabel("Minutes:");
        minutesTextField = new JTextField(2);
        JLabel messageLabel = new JLabel("Message:");
        messageTextField = new JTextField(25);
        //
        JLabel emergencyContact1Label = new JLabel("Emergency Contact:");
        emergencyContact1TextField = new JTextField(25);
        emergencyContact1TextField.setText("+917219789870");
        messageTextField.setText("Take your pills by time!");
        addButton = new JButton("Add");
        addButton.addActionListener(this);
        removeButton = new JButton("Remove");
        removeButton.addActionListener(this);
        JPanel alarmPanel = new JPanel();
        alarmPanel.setBorder(new MatteBorder(0, 5, 5, 5, (Color) new Color(0, 0, 0)));
        alarmPanel.add(hoursLabel);
        alarmPanel.add(hoursTextField);
        alarmPanel.add(minutesLabel);
        alarmPanel.add(minutesTextField);
        alarmPanel.add(messageLabel);
        alarmPanel.add(messageTextField);
        //
        alarmPanel.add(emergencyContact1Label);
        alarmPanel.add(emergencyContact1TextField);
        //
        alarmPanel.add(addButton);
        alarmPanel.add(removeButton);
        alarmListModel = new DefaultListModel<>();
        alarmList = new JList<>(alarmListModel);
        alarmList.setBorder(new MatteBorder(0, 5, 0, 5, (Color) new Color(0, 0, 0)));
        JScrollPane scrollPane = new JScrollPane(alarmList);
        getContentPane().add(timePanel, BorderLayout.NORTH);
        getContentPane().add(alarmPanel, BorderLayout.SOUTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        setSize(1200, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        updateClock();
        updateAlarmList();
        alarmFlag = false;
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateClock();
                checkAlarms();
            }
        });
        timer.start();
    }
    // Implementing the actionslistener method for add and remove buttons
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            int hours = Integer.parseInt(hoursTextField.getText());
            int minutes = Integer.parseInt(minutesTextField.getText());
            String message = messageTextField.getText();
            String emergencyContact1 = emergencyContact1TextField.getText();
            Calendar alarm = Calendar.getInstance();
            alarm.set(Calendar.HOUR_OF_DAY, hours);
            alarm.set(Calendar.MINUTE, minutes);
            alarm.set(Calendar.SECOND, 0);

            Alarm alarmobj= new Alarm(message,alarm,emergencyContact1);
            
            alarms.add(alarmobj);
            updateAlarmList();
        } else if (e.getSource() == removeButton) {
            int selectedIndex = alarmList.getSelectedIndex();
            if (selectedIndex != -1) {
                alarms.remove(selectedIndex);
                updateAlarmList();
            }
        }
    }
    // Method to update the time of the clock
    private void updateClock() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss a");
        String time = dateFormat.format(new Date());
        timeLabel.setText(time);
    }
    //method to update the alarm list
    private void updateAlarmList() {
        alarmListModel.clear();
        for (Alarm alarm : alarms) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a");
            String alarmTime = dateFormat.format(alarm.alarm.getTime())+" : "+alarm.message;
            alarmListModel.addElement(alarmTime);
        }
    }
    // Method to check if the alarm time is elapsed or equal to current time
    private void checkAlarms() {
        try{
            ArrayList<Alarm> temp = new ArrayList<>();
            temp.addAll(alarms);
            for (Alarm alarm : temp) {

                if (alarm.alarm.getTimeInMillis() <= System.currentTimeMillis()) {
                    ringAlarm(alarm);
                    
                    alarms.remove(alarm);
                    updateAlarmList();
    //   resume the timer
                    timer.start();
                }
            }
        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(addButton, "Alarm removed");
            e.printStackTrace();
        }
    }
    //Method to ring the alarm
    private void ringAlarm(Alarm alarm) {
    // stop the timer from updating the clock while the alarm is ringing
        timer.stop();
        new Thread ( new Runnable() {

            @Override
            public void run() {
               textToSpeech(alarm.message);
            }
    }).start();
        
        sendMessage(alarm.message, alarm.emergencyContact1);
    // display a message dialog to alert the user
       
        int res = JOptionPane.showOptionDialog(null, alarm.message, "Alert", JOptionPane.DEFAULT_OPTION,
        JOptionPane.INFORMATION_MESSAGE, null, null, null);
        if(res==0){
            alarmFlag = false;
        }
    }
    public void sendMessage(String  message,String emergencyContact1){
        /*Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    Message mobilemessage = Message.creator(

        new PhoneNumber(emergencyContact1),
        
        new PhoneNumber("+15133345602"),
            message)
    .create();*/
        
    }
    public void textToSpeech(String message){
        // Ring the bell
        try {
            alarmFlag = true;
            //setting properties as Kevin Dictionary  
            System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us" + ".cmu_us_kal.KevinVoiceDirectory");
            //registering speech engine  
            Central.registerEngineCentral("com.sun.speech.freetts" + ".jsapi.FreeTTSEngineCentral");
            //create a Synthesizer that generates voice  
            Synthesizer synthesizer = Central.createSynthesizer(new SynthesizerModeDesc(Locale.US));
            //allocates a synthesizer  
            synthesizer.allocate();
            while(alarmFlag){
                //resume a Synthesizer  
                synthesizer.resume();
                //speak the specified text until the QUEUE become empty  
                synthesizer.speakPlainText(message, null);
                synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
                //deallocating the Synthesizer
            }
            //synthesizer.deallocate();  
            Thread.sleep(1000);
            
        } catch (Exception e) {
          alarmFlag=false;
          e.printStackTrace();
        }
    }
    // Main method
    public static void main(String[] args) {
        new AlarmClock();
    }

}
