package com.example.demo;


import javafx.application.Platform;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.*;

public class TestVideo {
    private final JFrame jframe;

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
                    MP4ToElse.mp4_to_mov();
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
                    MP4ToElse.mp4_to_avi();
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
                    MP4ToElse.mp4_to_asf();
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
                    MP4ToElse.mp4_to_flv();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
            conversionThread.start();

        }

    };
    /********* 此区域对应不同格式转换对应的不同侦听器以及不同的具体行为**********/

    public static void videoPlay(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                /*************初始化TFMenu和ListenersForTF ************/
                TFMenu=new ArrayList<>(Arrays.asList("MP4转MOV","MP4转AVI","MP4转ASF","MP4转FLV"));
                ListenersForTF=new ArrayList<>();
                ListenersForTF.add(l0);
                ListenersForTF.add(l1);
                ListenersForTF.add(l2);
                ListenersForTF.add(l3);
                new TestVideo(args);
            }
        });

    }
    public TestVideo(String[] args){
        //component=new EmbeddedMediaPlayerComponent();

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
                JFileChooser chooser=new JFileChooser();
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
                 component.getMediaPlayer().stop();
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


            String path=watchlist.get(NumberOfVideos).toURI().getPath();
            path=path.substring(1);
            path=path.replace("/","\\");
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
