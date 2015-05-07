package lk.dialog.kuppi.dialogandroidintro3;

import android.app.ProgressDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final TextView statusView = (TextView) findViewById(R.id.text_status);
        Button taskButton = (Button) findViewById(R.id.btn_doTask);

        taskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog = ProgressDialog.show(MainActivity.this, "Executing", "Starting...");
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        for (int i = 0; i < 10; i++) {
                            int reply = Utilities.doLargeTask(5);
                            Log.i("Demo3", "Reply is " + reply);

                            runOnUiThread(new Runnable() {
                               @Override
                               public void run() {
                                   dialog.setMessage("Times done:"+ Utilities.getCounter());
                               }
                           });

                        }

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                statusView.setText("Got "+ Utilities.getCounter());
                                dialog.dismiss();
                            }
                        });

                    }
                }).start();

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
