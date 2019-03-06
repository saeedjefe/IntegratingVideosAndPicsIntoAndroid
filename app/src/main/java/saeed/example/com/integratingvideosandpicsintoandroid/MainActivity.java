package saeed.example.com.integratingvideosandpicsintoandroid;

import android.app.SharedElementCallback;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity  implements View.OnClickListener {

    //craete a videoView object reference
    VideoView myVideoView;

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.btnPauseTheMusic:
                mediaPlayer.pause();

                timer.cancel();
                break;

            case  R.id.btnPlayTheMusic:
                mediaPlayer.start();
               timer.scheduleAtFixedRate( new TimerTask() {
                   @Override
                   public void run() {
                       seekBarTracking.setProgress( mediaPlayer.getCurrentPosition() );

                   }
               } , 0, 1000);
                break;
        }

    }

    //access the video by creating an uri variable
    Uri uriVideo ;
    //declare a media controller variable
    MediaController mediaController;

    Button btnPlayTheVideo, btnPlayTheMusic, btnPauseTheMusic;

    //declare a mediaPlayer variable
    MediaPlayer mediaPlayer;

    //declare an audioManager variable
    AudioManager audioManager;

    //declare a seekBar Variable
    SeekBar seekBarVolume;

    //declare the variable seekBar
    SeekBar seekBarTracking;

    //declare a Timer Class
    Timer timer = new Timer(  ) ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        //initializing the object references
        btnPlayTheVideo = findViewById( R.id.btnPlayTheVideo );
        btnPlayTheMusic = findViewById( R.id.btnPlayTheMusic );
        btnPauseTheMusic = findViewById( R.id.btnPauseTheMusic );
        seekBarVolume = findViewById( R.id.seekBarVolume );
        seekBarTracking = findViewById( R.id.seekBarTracking );

        btnPlayTheMusic.setOnClickListener( this );
        btnPauseTheMusic.setOnClickListener( this );


        //initiallize the videoView
        myVideoView = findViewById( R.id.myVideoView );

        //initialize the uri variable
        uriVideo = Uri.parse( "android.resource://" + getPackageName() + "/" + R.raw.video );

        //put the uri file into the myVideo object reference
        myVideoView.setVideoURI( uriVideo );

        // initilize the media controller
        mediaController = new MediaController( this );

        //initialize an AudioManager variable
        audioManager = (AudioManager) getSystemService( AUDIO_SERVICE );

//
//        myVideoView.setMediaController( mediaController );

//        //initialize the media player
        mediaPlayer = MediaPlayer.create( MainActivity.this, R.raw.music );






        seekBarTracking.setMax( mediaPlayer.getDuration() );

//
        //find out the maximum value of the seekBar



        btnPlayTheVideo.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mediaController.setAnchorView( myVideoView );


                //
                myVideoView.start();

            }
        } );

        btnPlayTheMusic.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                //instantiate the Timer Class
                timer = new Timer();
                timer.scheduleAtFixedRate( new TimerTask() {
                    @Override
                    public void run() {

                        seekBarTracking.setProgress( mediaPlayer.getCurrentPosition() );

                    }
                }, 0, 1000 );
            }
        } );

        btnPauseTheMusic.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();

            }
        } );


        seekBarVolume.setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Toast.makeText( MainActivity.this, progress + "", Toast.LENGTH_SHORT ).show();
                audioManager.setStreamVolume( AudioManager.STREAM_MUSIC, progress , 0 );
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {


            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {



            }
        } );


//
//
//
     seekBarTracking.setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {
         @Override
         public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
       mediaPlayer.seekTo( progress );

         }

         @Override
         public void onStartTrackingTouch(SeekBar seekBar) {


         }

         @Override
         public void onStopTrackingTouch(SeekBar seekBar) {

             timer.cancel();


         }
     } );

//
//
//     mediaPlayer.setOnCompletionListener( new MediaPlayer.OnCompletionListener() {
//         @Override
//         public void onCompletion(MediaPlayer mp) {
//             timer.cancel();
//         }
//     } );
//
//
//
//
//
//
//
//    }
//


//
//}

//
//myVideoView = findViewById( R.id.myVideoView );
//        btnPlayVideo = findViewById( R.id.btnPlayVideo );
//        btnPlayTheMusic = findViewById( R.id.btnPlayTheMusic );
//        btnPauseTheMusic = findViewById( R.id.btnPauseTheMusic );
//        volumeSeekBar = findViewById( R.id.volumeSeekBar );
//        seekBarMove = findViewById( R.id.seekBarMove );
//
//
//
//        uriVideo = Uri.parse( "android.resource://"+getPackageName()+"/"+R.raw.video );
//        myVideoView.setVideoURI( uriVideo );
//
//        mediaController = new MediaController( this );
//        myVideoView.setMediaController( mediaController );
//
//
//
//        mediaPlayer = new MediaPlayer();

//
//
//        mediaPlayer = MediaPlayer.create( this, R.raw.music  );
//
//        btnPlayTheMusic.setOnClickListener( this );
//        btnPauseTheMusic.setOnClickListener( this );
//
//        audioManager = (AudioManager) getSystemService( AUDIO_SERVICE );
////
////          int max = audioManager.getStreamMaxVolume( AudioManager.STREAM_MUSIC );
////          int currentVolume = audioManager.getStreamVolume( AudioManager.STREAM_MUSIC );
//
////        mediaPlayer.OnCompletionListenert(this);
//
//
//        volumeSeekBar.setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {
//@Override
//public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//
//        if(fromUser)
//        {
//
//        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
//        }
//
//        }
//
//@Override
//public void onStartTrackingTouch(SeekBar seekBar) {
//
//        mediaPlayer.pause();
//
//        }
//
//@Override
//public void onStopTrackingTouch(SeekBar seekBar) {
//
//        mediaPlayer.start();
//
//        }
//        } );
//
//
//        seekBarMove.setOnSeekBarChangeListener( this );
//        seekBarMove.setMax( mediaPlayer.getDuration());
//
//        mediaPlayer.setOnCompletionListener( this );
//
//
//
//
//
//
//
//
//
//        }
//
//@Override
//public void onClick(View v) {
//
//        switch (v.getId())
//        {
//
//        case R.id.btnPlayVideo:
//
//
//        mediaController.setAnchorView( myVideoView );
//        myVideoView.start();
//        break;
//
//        case R.id.btnPlayTheMusic:
//        mediaPlayer.start();
//
//        timer= new Timer(  );
//        timer.scheduleAtFixedRate( new TimerTask() {
//@Override
//public void run() {
//        seekBarMove.setProgress( mediaPlayer.getCurrentPosition() );
//
//        }
//        }, 0, 1000 );
//        break;
//
//        case R.id.btnPauseTheMusic:
//        mediaPlayer.pause();
//
//        timer.cancel();
//        break;
//
//
//
//
//
//
//
//        }
//        }
//
//@Override
//public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//
//        mediaPlayer.seekTo( progress );
//
//        }
//
//@Override
//public void onStartTrackingTouch(SeekBar seekBar) {
//
//        mediaPlayer.pause();
//
//        }
//
//@Override
//public void onStopTrackingTouch(SeekBar seekBar) {
//        mediaPlayer.start();
//
//        }
//
//
////    @Override
////    public void onClick(View v) {
////
////        switch (v.getId())
////        {
////            case R.id.btnPauseTheMusic:
////
////                mediaPlayer.pause();
////
////                break;
////
////            case R.id.btnPlayTheMusic:
////
////                mediaPlayer.start();
////
////                break;
////
////            case R.id.btnPlayVideo:
////
////
////
////
////
//////                myVideoView.start();
////
////                break;
////        }
////
////
////}
//
////
////          volumeSeekBar.setOnSeekBarChangeListener( this );
////
////          seekBarMove.setOnSeekBarChangeListener( this );
//
////              public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
////                  if(fromUser)
////                  {
//////                      Toast.makeText( MainActivity.this, Integer.toString(progress), Toast.LENGTH_SHORT).show();
////
////                      audioManager.setStreamVolume( AudioManager.STREAM_MUSIC, progress, 0 );
////                  }
////              }
////
////              @Override
////              public void onStartTrackingTouch(SeekBar seekBar) {
////
////              }
////
////              @Override
////              public void onStopTrackingTouch(SeekBar seekBar) {
////
////              }
////          } );
//
//@Override
//public void onCompletion(MediaPlayer mp) {
//        timer.cancel();
//        }
//
//        //Ui components
//        Uri uriVideo;
//
//
//
//        MediaController mediaController;
//        VideoView myVideoView;
//        Button btnPlayVideo, btnPlayTheMusic, btnPauseTheMusic;
//        MediaPlayer mediaPlayer;
//        SeekBar volumeSeekBar;
//        AudioManager audioManager;
//        SeekBar seekBarMove;
//        Timer timer;


    }}

