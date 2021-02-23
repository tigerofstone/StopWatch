package StopWatch;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StopWatchC extends JApplet
{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private boolean isStandalone = false;
    JLabel jLabel1 = new JLabel();
    JTextField jtfTime = new JTextField();
    JButton jbtnStart = new JButton();
    JButton jbtnEnd = new JButton();
    JButton jbtnSuspend = new JButton();
    JButton jbtnResume = new JButton();

    private StopWatchThreadC objThread;
    private long lngStartMilliSecond;
    private String strShowText;

    //Main method
    public static void main(String[] args)
    {
        StopWatchC applet = new StopWatchC();
        applet.isStandalone = true;

        JFrame frame = new JFrame();
        //EXIT_ON_CLOSE == 3
        frame.setDefaultCloseOperation(3);
        frame.setTitle("秒表");
        frame.getContentPane().add(applet, java.awt.BorderLayout.CENTER);

        applet.init();
        applet.start();

        frame.setSize(500, 190);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((d.width - frame.getSize().width) / 2, (d.height - frame.getSize().height) / 2);
        frame.setVisible(true);
    }


    //Get a parameter value
    public String getParameter(String key, String def)
    {
        return isStandalone ? System.getProperty(key, def) : (getParameter(key) != null ? getParameter(key) : def);
    }

    //Construct the applet
    public StopWatchC()
    {

    	
    	
    }

    //Initialize the applet
    public void init()
    {
        try
        {
            jbInit();
            jbInitEvent();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    //Component initialization
    private void jbInit() throws Exception
    {
        this.getContentPane().setBackground(SystemColor.lightGray);
        this.setForeground(Color.black);
        this.setSize(new Dimension(500, 161));
        this.getContentPane().setLayout(null);
        jLabel1.setFont(new java.awt.Font("Serif", 1, 20));
        jLabel1.setForeground(Color.black);
        jLabel1.setText("经历时间：");
        jLabel1.setBounds(new Rectangle(12, 32, 150, 35));

        jtfTime.setEnabled(false);
        jtfTime.setFont(new java.awt.Font("SansSerif", 1, 20));
        jtfTime.setForeground(Color.red);
        jtfTime.setAlignmentX((float)0.5);
        jtfTime.setDisabledTextColor(Color.red);
        jtfTime.setEditable(true);
        jtfTime.setSelectionEnd(14);
        jtfTime.setSelectionStart(14);
        jtfTime.setHorizontalAlignment(SwingConstants.RIGHT);
        jtfTime.setBounds(new Rectangle(164, 16, 300, 59));
        jtfTime.setText("00:00:00:000");

        jbtnStart.setBounds(new Rectangle(27, 87, 80, 34));
        jbtnStart.setFont(new java.awt.Font("SansSerif", 0, 15));
        jbtnStart.setText("开始");

        jbtnEnd.setText("停止");
        jbtnEnd.setFont(new java.awt.Font("SansSerif", 0, 15));
        jbtnEnd.setToolTipText("");
        jbtnEnd.setSelected(false);
        jbtnEnd.setBounds(new Rectangle(298, 88, 87, 33));

        jbtnSuspend.setBounds(new Rectangle(119, 87, 86, 34));
        jbtnSuspend.setFont(new java.awt.Font("SansSerif", 0, 15));
        jbtnSuspend.setText("暂停");

        jbtnResume.setText("继续");
        jbtnResume.setFont(new java.awt.Font("SansSerif", 0, 15));
        jbtnResume.setBounds(new Rectangle(208, 88, 86, 34));

        this.getContentPane().add(jtfTime, null);
        this.getContentPane().add(jbtnStart, null);
        this.getContentPane().add(jLabel1, null);
        this.getContentPane().add(jbtnSuspend, null);
        this.getContentPane().add(jbtnResume, null);
        this.getContentPane().add(jbtnEnd, null);

        jbtnStart.setEnabled(true);
        jbtnSuspend.setEnabled(false);
        jbtnResume.setEnabled(false);
        jbtnEnd.setEnabled(false);
    }

    private void jbInitEvent()
    {
        this.jbtnStart.addMouseListener(new MouseClickEvent());
        this.jbtnEnd.addMouseListener(new MouseClickEvent());
        this.jbtnSuspend.addMouseListener(new MouseClickEvent());
        this.jbtnResume.addMouseListener(new MouseClickEvent());

        this.jbtnStart.addKeyListener(new MouseKeyEvent());
        //this.jbtnEnd.addMouseListener(new MouseClickEvent());
        //this.jbtnSuspend.addMouseListener(new MouseClickEvent());
        //this.jbtnResume.addMouseListener(new MouseClickEvent());
    }


    private void ChangeTime(String strTime)
    {
        strShowText = strTime;
        jtfTime.setText(strShowText);

        //javax.swing.JOptionPane.showMessageDialog(this,"��ʼ" ,"��ʾ",javax.swing.JOptionPane.INFORMATION_MESSAGE );
    }

    private void jbtnStart_MouseClick(java.awt.event.MouseEvent e)
    {
        this.jbtnStart_MouseClickFun();
    }



    private void jbtnStart_MouseClickFun()
    {
        //javax.swing.JOptionPane.showMessageDialog(this,"提示" ,"开始了",javax.swing.JOptionPane.INFORMATION_MESSAGE );


        //JOptionPane.showMessageDialog(null, String.valueOf(j), "开始了！", JOptionPane.INFORMATION_MESSAGE);


        objThread = new StopWatchThreadC();
        objThread.setPriority(java.lang.Thread.MIN_PRIORITY);

        lngStartMilliSecond = java.lang.System.currentTimeMillis();
        objThread.start();

        jbtnStart.setEnabled(false);
        jbtnSuspend.setEnabled(true);
        jbtnResume.setEnabled(false);
        jbtnEnd.setEnabled(true);
    }



    @SuppressWarnings("deprecation")
    private void jbtnEnd_MouseClick(java.awt.event.MouseEvent e)
    {
        try
        {
            if(objThread.isAlive())
            {
                objThread.stop();

                jbtnStart.setEnabled(true);
                jbtnSuspend.setEnabled(false);
                jbtnResume.setEnabled(false);
                jbtnEnd.setEnabled(false);

                jtfTime.setText(strShowText);
            }
        }
        catch(java.lang.Exception ee)
        {

        }
    }


    @SuppressWarnings("deprecation")
    private void jbtnSuspend_MouseClickFun(java.awt.event.MouseEvent e)
    {
        try
        {
            if(objThread.isAlive())
            {
                objThread.suspend();

                jbtnStart.setEnabled(false);
                jbtnSuspend.setEnabled(false);
                jbtnResume.setEnabled(true);
                jbtnEnd.setEnabled(false);

                jtfTime.setText(strShowText);
            }
        }
        catch(java.lang.Exception ee)
        {

        }

    }

    @SuppressWarnings("deprecation")
    private void jbtnSuspend_KeyClick(java.awt.event.KeyAdapter e)
    {
        this.jbtnSuspend_KeyClickfun();
    }

    @SuppressWarnings("deprecation")
    private void jbtnSuspend_KeyClickfun()
    {
        try
        {
            if(objThread.isAlive())
            {
                objThread.suspend();

                jbtnStart.setEnabled(false);
                jbtnSuspend.setEnabled(false);
                jbtnResume.setEnabled(true);
                jbtnEnd.setEnabled(false);

                jtfTime.setText(strShowText);
            }
        }
        catch(java.lang.Exception ee)
        {

        }

    }

    @SuppressWarnings("deprecation")
    private void jbtnResume_MouseClick(java.awt.event.MouseEvent e)
    {
        try
        {
            lngStartMilliSecond = java.lang.System.currentTimeMillis() - objThread.lngMilliSeconds;
            objThread.resume();

            jbtnStart.setEnabled(false);
            jbtnSuspend.setEnabled(true);
            jbtnResume.setEnabled(false);
            jbtnEnd.setEnabled(true);
        }
        catch(java.lang.Exception ee)
        {

        }

    }


    //---------- �ڲ��¼������� --------------------------------------------------------------------------
    private class MouseClickEvent extends java.awt.event.MouseAdapter
    {
        public void mouseClicked(MouseEvent e)
        {
            if(e.getSource() == jbtnStart)
            {
                if(jbtnStart.isEnabled())
                {
                    jbtnStart_MouseClick(e);
                }
            }
            else if(e.getSource() == jbtnEnd)
            {
                if(jbtnEnd.isEnabled())
                {
                    jbtnEnd_MouseClick(e);
                }
            }
            else if(e.getSource() == jbtnSuspend)
            {
                if(jbtnSuspend.isEnabled())
                {
                    jbtnSuspend_MouseClickFun(e);
                }
            }
            else if(e.getSource() == jbtnResume)
            {
                if(jbtnResume.isEnabled())
                {
                    jbtnResume_MouseClick(e);
                }
            }

        }
    }

    private class MouseKeyEvent extends java.awt.event.KeyAdapter
    {
        public void keyPressed(KeyEvent e)
        {
            if(e.getSource() == jbtnStart)
            {
                if(jbtnStart.isEnabled())
                {
                    jbtnStart_MouseClickFun();
                }
            }
            else if(e.getSource() == jbtnEnd)
            {
                if(jbtnEnd.isEnabled())
                {
                    //jbtnEnd_MouseClick(e);
                }
            }
            else if(e.getSource() == jbtnSuspend)
            {
                if(jbtnSuspend.isEnabled())
                {
                    //jbtnSuspend_MouseClick(e);
                }
            }
            else if(e.getSource() == jbtnResume)
            {
                if(jbtnResume.isEnabled())
                {
                    //jbtnResume_MouseClick(e);
                }
            }

        }
    }



    //============= �߳��� ==============================================
    private class StopWatchThreadC extends java.lang.Thread
    {
        public long lngMilliSeconds = 0;
        private ChangeMilliSocendToTimeC objChange2Time = new ChangeMilliSocendToTimeC();
        StopWatchThreadC()
        {
        }

        public void run()
        {
            String strTime;
            try
            {
                while(true)
                {
                    lngMilliSeconds = java.lang.System.currentTimeMillis() - lngStartMilliSecond;

                    strTime = objChange2Time.getTime(lngMilliSeconds);

                    ChangeTime(strTime);

                    Thread.sleep(1);
                }
            }
            catch(java.lang.InterruptedException e)
            {

            }
        }

    }


}
