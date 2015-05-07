package lk.dialog.kuppi.dialogandroidintro3;

import android.app.ProgressDialog;
import android.os.AsyncTask;
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


        Button taskButton = (Button) findViewById(R.id.btn_doTask);

        taskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LargeTask task = new LargeTask();
                task.execute(10);
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


    private class LargeTask extends AsyncTask<Integer, Integer, Integer> {
        @Override
        protected void onPreExecute() {
            dialog = ProgressDialog.show(MainActivity.this, "Executing", "Starting...");
        }

        @Override
        protected Integer doInBackground(Integer... params) {
            int reply = 0;
            for (int i = 0; i < params[0]; i++) {
                reply = Utilities.doLargeTask(5);
                Log.i("Demo3", "Reply is " + reply);

                publishProgress(i);

            }

            return reply;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            dialog.setMessage("Times done:"+ values[0]);
        }

        @Override
        protected void onPostExecute(Integer integer) {
            TextView statusView = (TextView) findViewById(R.id.text_status);
            statusView.setText("Got "+ integer);
            dialog.dismiss();
        }
    }
}
