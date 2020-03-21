package com.example.asynctaskdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyTask myTask = new MyTask();
        myTask.execute("Red", "Green", "Blue");
    }

    class MyTask extends AsyncTask<String , String , String>{

        private static final String TAG = "MyTag" ;

        @Override
        protected void onPreExecute() {
            Log.d(TAG, "onPreExecute: ");
        }

        /**
         * Override this method to perform a computation on a background thread. The
         * specified parameters are the parameters passed to {@link #execute}
         * by the caller of this task.
         * <p>
         * This will normally run on a background thread. But to better
         * support testing frameworks, it is recommended that this also tolerates
         * direct execution on the foreground thread, as part of the {@link #execute} call.
         * <p>
         * This method can call {@link #publishProgress} to publish updates
         * on the UI thread.
         *
         * @param strings The parameters of the task.
         * @return A result, defined by the subclass of this task.
         * @see #onPreExecute()
         * @see #onPostExecute
         * @see #publishProgress
         */
        @Override
        protected String doInBackground(String... strings) {

            for (String color: strings) {
                
                if (color.contains("Green")){
                    cancel(true);
                    break;
                }

                try {
                    Thread.sleep(2000);
                    Log.d(TAG, "doInBackground: " + color);
                    publishProgress(color);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "Download Completed";
        }


        @Override
        protected void onProgressUpdate(String... values) {
            Log.d(TAG, "onProgressUpdate: " +values[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            Log.d(TAG, "onPostExecute: "+s);
        }
        @Override
        protected void onCancelled() {
            Log.d(TAG, "onCancelled: is called");
        }
    }
}
