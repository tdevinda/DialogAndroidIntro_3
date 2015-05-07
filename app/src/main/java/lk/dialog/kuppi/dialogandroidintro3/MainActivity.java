package lk.dialog.kuppi.dialogandroidintro3;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    private static String EXTRA_RESULT = "lk.dialog.kuppi.dialogandroidintro3.result";
    private static Handler messageHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final
        Button taskButton = (Button) findViewById(R.id.btn_doTask);

        messageHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                TextView statusView = (TextView) findViewById(R.id.text_status);
                int result = msg.getData().getInt(EXTRA_RESULT);
                statusView.setText("Got " + result);

            }
        };

        taskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int resultFromExec = Utilities.doLargeTask(5);
                        Bundle data = new Bundle();
                        data.putInt(EXTRA_RESULT, resultFromExec);

                        Message msg = new Message();
                        msg.setData(data);

                        messageHandler.sendMessage(msg);
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


    class DataHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);


        }
    }
}
