package dys.vlctest;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.videolan.libvlc.LibVLC;
import org.videolan.libvlc.LibVlcException;

import activity.Touch;
import activity.VideoActivity;

public class MainActivity extends Activity {
    public final static String TAG = "LibVLCAndroidSample/MainActivity";

    LibVLC mLibVLC;
    private EditText etLocation;
    private final String BIG_H264_FILE_ON_HTTP =
            "此处填写播放地址";
    private Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Initialize the LibVLC multimedia framework.
        // This is required before doing anything with LibVLC.
        try {
            mLibVLC = LibVLC.getInstance();
            mLibVLC.init(MainActivity.this);
        } catch (LibVlcException e) {
            Toast.makeText(MainActivity.this,
                    "Error initializing the libVLC multimedia framework!",
                    Toast.LENGTH_LONG).show();
            finish();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EventBus.getDefault().register(this);

        etLocation = (EditText) findViewById(R.id.et_location);
        etLocation.setText(BIG_H264_FILE_ON_HTTP);

        //跳转播放页
        final VideoActivity videoActivity = new VideoActivity();
        Button load_a_mp3 = (Button) findViewById(R.id.load_a_mp3);
        load_a_mp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, videoActivity.getClass());
//            intent.putExtra(VideoActivity.LOCATION, BIG_H264_FILE_ON_HTTP);
                intent.putExtra(videoActivity.LOCATION, etLocation.getText().toString());
                startActivity(intent);
            }
        });

        mToast = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT);
    }

    //屏幕触摸点
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Touch touch) {
        mToast.setText("pointX,Y=" + touch.pointX + "," + touch.pointY + '\n'
                + "ScreenSize=" + touch.rightBottomX + "," + touch.rightBottomY);
        mToast.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
