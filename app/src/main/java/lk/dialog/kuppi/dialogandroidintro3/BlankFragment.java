package lk.dialog.kuppi.dialogandroidintro3;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



public class BlankFragment extends Fragment {

    private boolean isTaskRunning = false;
    private ProgressDialog dialog;

    public BlankFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);

        if (isTaskRunning) {
            dialog = ProgressDialog.show(getActivity(), "Executing", "Fetching update...");
        } else {
            LargeTask task = new LargeTask();
            task.execute(3);
        }

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (isTaskRunning) {
            dialog = ProgressDialog.show(getActivity(), "Executing", "Fetching update...");

        }
    }


    private class LargeTask extends AsyncTask<Integer, Integer, Integer> {
        @Override
        protected void onPreExecute() {
            dialog = ProgressDialog.show(getActivity(), "Executing", "Starting...");
            isTaskRunning = true;
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
            TextView statusView = (TextView) getActivity().findViewById(R.id.text_status);
            statusView.setText("Got "+ integer);
            dialog.dismiss();
        }
    }
}
