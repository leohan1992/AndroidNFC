# AndroidNFC
a base activity for use nfc
#### dependency

Maven
````
<dependency>
  <groupId>xyz.leohan</groupId>
  <artifactId>AndroidNFC</artifactId>
  <version>1.0.0</version>
  <type>pom</type>
</dependency>
````

Gradle
````
compile 'xyz.leohan:AndroidNFC:1.0.0'
````

#### How to Use

1. create a New Activity  extends xyz.leohan.androidnfclib.NfcActivity,implement onNfcTouch() method.Then do other things in an Activit as usual
````java
public class MyActivity extends NfcActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onNfcTouch() {
        //this method will called when a NFC tag touched the phone and can be analysed
        //we can get NFC tag id here;
    }
}
````
2. write these in your AndroidManifest.xml.  
**android:launchMode="singleTask" and the intent-filter is necessary**
````
       <activity
            android:name=".yourActivityName"
            android:configChanges="keyboardHidden|orientation|screenSize"
            android:launchMode="singleTask">
            <intent-filter>
                <action android:name="android.nfc.action.NDEF_DISCOVERED" />
                <category android:name="android.intent.category.DEFAULT" />
                <data android:mimeType="*/*" />
            </intent-filter>
        </activity>
````


3. main methods in NFCActivity:  
````
void readNfcContent();//can read message from NFC tag
boolean writeNfc(String msg);//write something to NFC tag
boolean deleteNfc(); //clear NFC tag
````

#### a simple use

````java 
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
````

4. I already checked the Runtime Permission in library. Don't worry about it.

#### contact me

you can sended me an e-mail :leo@leohan.xyz


