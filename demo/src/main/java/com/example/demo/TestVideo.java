package com.example.demo;


import javafx.application.Platform;
import javafx.scene.control.Alert;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import ws.schild.jave.EncoderException;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;

public class TestVideo {
    private static  JFrame jframe;

    private static final EmbeddedMediaPlayerComponent component = new EmbeddedMediaPlayerComponent();

    private final JButton pauseButton;

    private final JButton rewindButton;

    private final JButton skipButton;
    private final JButton nextButton;
    private final JButton selectFilesButton;
    private final JButton MusicPlayerButton;
    private final JProgressBar progressBar;
    private File directory;
    private File[] files;
    private ArrayList<File> watchlist;
    private int NumberOfVideos;

    //以下两个变量带TF表示与格式转换有关系
    private static ArrayList<String> TFMenu;
    private static ArrayList<ActionListener> ListenersForTF=new ArrayList<>();
    /********* 此区域对应不同格式转换对应的不同侦听器以及不同的具体行为 **********/
    static ActionListener l0= new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // 使用Lambda表达式创建一个新线程
            Thread conversionThread = new Thread(() -> {
                try {
                    //JOptionPane.showMessageDialog(null, "提示消息.", "转换完毕",JOptionPane.PLAIN_MESSAGE);

                    int res=VideoCovert.To_mov();
                    //转换成功则弹窗提示
                    if(res==0){
                        JOptionPane.showMessageDialog(null, "转换完毕.", "提示消息",JOptionPane.PLAIN_MESSAGE);
                    }

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
            conversionThread.start();

        }

    };

    static ActionListener l1= new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // 使用Lambda表达式创建一个新线程
            Thread conversionThread = new Thread(() -> {
                try {
                    int res=VideoCovert.To_avi();
                    //转换成功则弹窗提示
                    if(res==0){
                        JOptionPane.showMessageDialog(null, "转换完毕.", "提示消息",JOptionPane.PLAIN_MESSAGE);
                    }

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
            conversionThread.start();

        }

    };
    static ActionListener l2= new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // 使用Lambda表达式创建一个新线程
            Thread conversionThread = new Thread(() -> {
                try {
                    int res=VideoCovert.To_asf();
                    //转换成功则弹窗提示
                    if(res==0){
                        JOptionPane.showMessageDialog(null, "转换完毕.", "提示消息",JOptionPane.PLAIN_MESSAGE);
                    }

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
            conversionThread.start();

        }

    };
    static ActionListener l3= new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // 使用Lambda表达式创建一个新线程
            Thread conversionThread = new Thread(() -> {
                try {

                    int res=VideoCovert.To_flv();
                    //转换成功则弹窗提示
                    if(res==0){
                        JOptionPane.showMessageDialog(null, "转换完毕.", "提示消息",JOptionPane.PLAIN_MESSAGE);
                    }

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
            conversionThread.start();

        }

    };
    static ActionListener l4= new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // 使用Lambda表达式创建一个新线程
            Thread conversionThread = new Thread(() -> {
                try {

                    int res=VideoCovert.To_dvd();
                    //转换成功则弹窗提示
                    if(res==0){
                        JOptionPane.showMessageDialog(null, "转换完毕.", "提示消息",JOptionPane.PLAIN_MESSAGE);
                    }

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
            conversionThread.start();

        }
    };
    static ActionListener l5= new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // 使用Lambda表达式创建一个新线程
            Thread conversionThread = new Thread(() -> {
                try {

                    int res=VideoCovert.toMP4();
                    //转换成功则弹窗提示
                    if(res==0){
                        JOptionPane.showMessageDialog(null, "转换完毕.", "提示消息",JOptionPane.PLAIN_MESSAGE);
                    }


                } catch (EncoderException ex) {
                    throw new RuntimeException(ex);
                }
            });
            conversionThread.start();

        }
    };
        /********* 此区域对应不同格式转换对应的不同侦听器以及不同的具体行为**********/
    private static boolean isinitialized=false;
    public static void videoPlay() {
        // TODO: test
        /////////////// for study //////////
        Thread t=Thread.currentThread();
        long id= t.threadId();
        System.out.println("videoPlay:"+id);
         TFMenu=new ArrayList<>(Arrays.asList("转MOV","转AVI","转ASF","转FLV","转DVD","转MP4"));
         ListenersForTF=new ArrayList<>();
         ListenersForTF.add(l0);
         ListenersForTF.add(l1);
         ListenersForTF.add(l2);
         ListenersForTF.add(l3);
         ListenersForTF.add(l4);
         ListenersForTF.add(l5);
         //isinitialized为假，说明窗口还没有被建立起来
         if(!isinitialized){
             new TestVideo();
             isinitialized=true;
         }else{
             //isinitialized为真，此时只需要将jframe设置为true就可以继续上一次播放的视频为止
             jframe.setVisible(true);
         }
        /**
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                /*************初始化TFMenu和ListenersForTF ************/
                TFMenu=new ArrayList<>(Arrays.asList("转MOV","转AVI","转ASF","转FLV","转DVD"));
                /*************初始化TFMenu和ListenersForTF ***********
                TFMenu=new ArrayList<>(Arrays.asList("MP4转MOV","MP4转AVI","MP4转ASF","MP4转FLV","MP4转DVD"));
                ListenersForTF=new ArrayList<>();
                ListenersForTF.add(l0);
                ListenersForTF.add(l1);
                ListenersForTF.add(l2);
                ListenersForTF.add(l3);
                ListenersForTF.add(l4);
                new TestVideo();
            }
        });*/

    }
    public TestVideo(){
        //component=new EmbeddedMediaPlayerComponent();
        /////////////// for study //////////
        //界面的显示
        jframe=new JFrame("Mul-player");

        jframe.setBounds(new Rectangle(250,150,800,600));
        jframe.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        jframe.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                component.release();
                System.exit(0);
            }
        });
        JPanel contentPane = new JPanel();

        contentPane.setLayout(new BorderLayout());
        contentPane.add(component, BorderLayout.CENTER);

        // new panel to place buttons
        JPanel controlsPane = new JPanel();
        controlsPane.setBackground(new Color(75,115,140));
        /************************** add buttons **************************/
        pauseButton = new JButton("Pause");
        controlsPane.add(pauseButton);

        rewindButton = new JButton("Rewind");
        controlsPane.add(rewindButton);

        skipButton = new JButton("Skip");
        controlsPane.add(skipButton);

        nextButton = new JButton("Next");
        controlsPane.add(nextButton);

        selectFilesButton=new JButton("选择文件");
        controlsPane.add(selectFilesButton);

        MusicPlayerButton=new JButton("Audio");
        controlsPane.add(MusicPlayerButton);
        /************************** add buttons **************************/

        /************************** add progressbar ***********************/
        JPanel progressPanel=new JPanel();	//实例化进度条对应的jpanel
        progressBar=new JProgressBar();
        progressPanel.add(progressBar);
        progressBar.setStringPainted(true);
        controlsPane.add(progressPanel, BorderLayout.NORTH);

        /************************** add progressbar and display the progress ***********************/
        contentPane.add(controlsPane, BorderLayout.SOUTH);

        new SwingWorker<String,Integer>() {
            @Override
            protected String doInBackground() throws Exception {
                //获取视频播放进度
                while(true){
                    float percent=component.getMediaPlayer().getPosition();
                    //通过 publish 方法将当前进度传递给 process 方法
                    publish((int)(percent*100));
                    //100ms 传1次
                    Thread.sleep(100);
                }
            }

            protected void process(java.util.List<Integer> l){
                //显示进度条
                for(int v:l){
                    progressBar.setValue(v);
                }
            }
        }.execute();
        /************************** add progressbar and display the progress ***********************/

        /**************************** 拖动进度条 ********************************/
        progressBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x=e.getX();
                component.getMediaPlayer().setPosition((float)x/progressBar.getWidth());
            }
        });
        /**************************** 拖动进度条 ********************************/

        /***************** bind the button with the event **************************/
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                component.getMediaPlayer().pause();
            }
        });

        rewindButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //component.getMediaPlayer().setPosition(component.getMediaPlayer().getPosition()+0.025f);
                component.getMediaPlayer().skip(-10000);
            }
        });

        skipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                component.getMediaPlayer().skip(10000);
            }
        });

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NumberOfVideos+=1;
                if(NumberOfVideos< watchlist.size()) {
                    String path = watchlist.get(NumberOfVideos).toURI().getPath();
                    path = path.substring(1);
                    path = path.replace("/", "\\");
                    System.out.println(path);
                    component.getMediaPlayer().prepareMedia(path);
                    component.getMediaPlayer().play();
                }else{
                    NumberOfVideos=0;
                    //play it
                    String path = watchlist.get(NumberOfVideos).toURI().getPath();
                    path = path.substring(1);
                    path = path.replace("/", "\\");
                    System.out.println(path);
                    component.getMediaPlayer().prepareMedia(path);
                    component.getMediaPlayer().play();
                }
            }
        });

        selectFilesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread t=Thread.currentThread();
                System.out.println(t.threadId()+":select actionPerformed");
                JFileChooser chooser=new JFileChooser(System.getProperty("user.dir"));
                int v=chooser.showOpenDialog(null);
                if(v==JFileChooser.APPROVE_OPTION){
                    File file=chooser.getSelectedFile();
                    component.getMediaPlayer().playMedia(file.getAbsolutePath());
                }
            }
        });

        MusicPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 Thread t=Thread.currentThread();
                 System.out.println(t.threadId()+":switch actionPerformed");
                 component.getMediaPlayer().pause();
                 jframe.setVisible(false);
                 //component.release();

                 SwitchLater();
            }
        });

        /***************** bind the button with the event **************************/

        /*************** Set JMenu name **************/
        JMenuBar b=new JMenuBar();
        JMenu mn=new JMenu("播放列表");		//设置菜单名
        JMenu tf=new JMenu("视频格式转换");
        b.add(mn);
        b.add(tf);
        /********** 处理视频格式转换的item ***************/
        for(int i=0;i<TFMenu.size();i++){
            JMenuItem TFitem =new JMenuItem(TFMenu.get(i));
            TFitem.addActionListener(ListenersForTF.get(i));
            tf.add(TFitem);
        }

        /********** 处理视频格式转换的item ***************/
        /**** show *****/
        jframe.setContentPane(contentPane);
        jframe.setVisible(true);


        watchlist=new ArrayList<File>();
        directory=new File( "video");

        files=directory.listFiles();

        if(files!=null){
            for(File f:files){
                watchlist.add(f);
                JMenuItem item=new JMenuItem(f.getName());
                item.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //NumberOfVideos=e.getSource().toString()
                        /**遍历菜单，找到对应的JmenuItem，并根据其序号进行播放 **/
                        for(int i=0;i<mn.getItemCount();i++){
                            if(mn.getItem(i).equals(e.getSource()))  {
                                NumberOfVideos=i;
                                break;
                            }
                        }
                        component.getMediaPlayer().startMedia(watchlist.get(NumberOfVideos).getAbsolutePath());
                        //System.out.println(mn.getItemCount());
                    }
                });
                mn.add(item);
            }


            String path=watchlist.get(NumberOfVideos).getAbsolutePath();
            System.out.println(path);

            component.getMediaPlayer().prepareMedia(path);
            component.getMediaPlayer().play();

        }
        jframe.setJMenuBar(b);

    }
    public void SwitchLater() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                /////////////// for study //////////
                Thread t=Thread.currentThread();
                long id= t.threadId();
                System.out.println("switch later:"+id);
                //更新JavaFX的主线程的代码放在此处
                try {
                    HelloApplication.ReloadStage();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}
