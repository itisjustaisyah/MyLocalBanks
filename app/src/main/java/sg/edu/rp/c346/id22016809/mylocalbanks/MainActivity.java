package sg.edu.rp.c346.id22016809.mylocalbanks;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button dbs;
    Button ocbc;
    Button uob;
    String bankNum;
    String bankWeb = "";
    Button temp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbs = findViewById(R.id.DBSbutton);
        ocbc = findViewById(R.id.OCBCbutton);
        uob = findViewById(R.id.UOBbutton);

        registerForContextMenu(dbs);
        registerForContextMenu(ocbc);
        registerForContextMenu(uob);



        dbs.setOnClickListener(v -> {
            bankNum = "18001111111";
            bankWeb = "https://www.dbs.com.sg";
            openContextMenu(dbs);
            temp = dbs;
        });
        ocbc.setOnClickListener(v -> {
            bankNum = "18003633333";
            bankWeb = "https://www.ocbc.com.sg";
            openContextMenu(ocbc);
            temp = ocbc;
        });
        uob.setOnClickListener(v -> {
            bankNum = "18002222121";
            bankWeb = "https://www.uob.com.sg";
            openContextMenu(uob);
            temp = uob;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.language_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int ID = item.getItemId();

        if (ID == R.id.englishSelection) {
            Toast.makeText(this, "English is chosen", Toast.LENGTH_SHORT).show();
            dbs.setText("DBS");
            ocbc.setText("OCBC");
            uob.setText("UOB");
            Log.i("MainActivity", "English");
        } else if (ID == R.id.chineseSelection) {
            Toast.makeText(this, "Chinese is chosen", Toast.LENGTH_SHORT).show();
            dbs.setText("星展银行");
            ocbc.setText("华侨银行");
            uob.setText("大华银行");
            Log.i("MainActivity", "Chinese");
        }else{
            Toast.makeText(this, "Error translation", Toast.LENGTH_SHORT).show();
            Log.i("MainActivity", "Error");

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        super.getMenuInflater().inflate(R.menu.bank_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int ID = item.getItemId();
        if (ID == R.id.bankWebsite) {
            Intent intentWeb = new Intent(Intent.ACTION_VIEW, Uri.parse( bankWeb));
            startActivity(intentWeb);
        } else if (ID == R.id.bankCall) {
            Intent intentCall = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" +bankNum));
            startActivity(intentCall);
        } else if (ID == R.id.addFave) {
            if (temp.getCurrentTextColor() == Color.RED){
                temp.setTextColor(getResources().getColor(R.color.white, getTheme()));;
            }else{
                temp.setTextColor(Color.RED);
            }

        }
        return super.onContextItemSelected(item);
    }
}