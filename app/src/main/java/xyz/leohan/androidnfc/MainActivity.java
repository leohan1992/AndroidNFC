package xyz.leohan.androidnfc;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import xyz.leohan.androidnfclib.NfcActivity;

public class MainActivity extends NfcActivity {
    private TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvContent = (TextView) findViewById(R.id.tv_content);
    }

    @Override
    protected void onNfcTouch() {
        Log.i("nfc","ontouch");
        tvContent.setText("NFC TagId:" + getTagId());
    }

    public void readNfc(View view) {
        String s = null;
        try {
            //read nfc content from tag;
            s = this.readNfcContent();
        } catch (Exception e) {
            e.printStackTrace();
        }
        tvContent.setText(s);
    }

    public void writeNfcContent(View view) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String content = dateFormat.format(System.currentTimeMillis());
        try {
            //write something to tag;
            this.writeNfc(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearNfc(View view) {
        try {
            //clear nfcContent
            this.deleteNfc();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
