package zura.chrelashvili.savingcaptureimagetogalleryonly;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends ActionBarActivity {

     final  int REQUEST_CODE=2;
     Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn= (Button) findViewById(R.id.btn1);

     btn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {


             Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

             File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
             String pictureName = MakePictureName();
             File lastFile = new File(pictureDirectory, pictureName);
             Uri lastUri = Uri.fromFile(lastFile);


             intent.putExtra(MediaStore.EXTRA_OUTPUT, lastUri);
             startActivityForResult(intent, REQUEST_CODE);


         }
     });

    }

        @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==RESULT_OK && requestCode == REQUEST_CODE)
        {
            Toast.makeText(this,"IMAGE SAVED TO YOUR GALLERY",Toast.LENGTH_LONG).show();
        }

    }

    private String MakePictureName() {
       //making sdf format for our picture and returning in from method
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                java.util.Locale.getDefault());
        String aloha=format.format(new Date());
        return aloha + ".jpg";

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
