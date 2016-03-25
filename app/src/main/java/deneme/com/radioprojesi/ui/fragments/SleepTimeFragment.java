package deneme.com.radioprojesi.ui.fragments;

import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import deneme.com.radioprojesi.HomeActivty;
import deneme.com.radioprojesi.R;


/**
 * Created by zer0day on 13.3.2016.
 */
public class SleepTimeFragment extends Fragment {


    TextView saatnumberView;
    TextView dakikanumberView;
    Button kaydetButonu;
    Handler handler;
    Runnable tempRunnable;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View myFragmentView = inflater.inflate(R.layout.sleeptime_fragment_layout, container, false);


        saatnumberView = (TextView) myFragmentView.findViewById(R.id.numberview);
        dakikanumberView = (TextView) myFragmentView.findViewById(R.id.dakikanumberview);
        NumberPicker numberPicker = (NumberPicker) myFragmentView.findViewById(R.id.numberpicker);
        numberPicker.setMaxValue(12);
        numberPicker.setMinValue(0);
        numberPicker.setWrapSelectorWheel(true);
        numberPicker.setOnValueChangedListener(new NumberPicker.
                OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int
                    oldVal, int newVal) {
                saatnumberView.setText(""+newVal);
            }
        });


        NumberPicker numberPicker2 = (NumberPicker) myFragmentView.findViewById(R.id.numberpicker2);
        numberPicker2.setMaxValue(60);
        numberPicker2.setMinValue(0);
        numberPicker2.setWrapSelectorWheel(true);
        numberPicker2.setOnValueChangedListener(new NumberPicker.
                OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int
                    oldVal, int newVal) {
                dakikanumberView.setText("" + newVal);
            }
        });

        kaydetButonu = (Button) myFragmentView.findViewById(R.id.kaydetButton);
        kaydetButonu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String test = dakikanumberView.getText().toString();
                if (handler != null)
                    handler.removeCallbacks(tempRunnable);
                handler = new Handler();
                tempRunnable = new Runnable() {
                    public void run() {
                        ((HomeActivty) getActivity()).uykuZamani();
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setMessage("Radyonuz kapanmıştır " + ((HomeActivty) getActivity()).getCounter())
                                .setCancelable(false)
                                .setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        //do things
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                };
                handler.postDelayed(tempRunnable, Integer.parseInt(test) * 60000);

                Toast.makeText(getActivity(), "Radyonuz "+saatnumberView.getText()+" saat ve "+dakikanumberView.getText()+" dakika sonra uykuya geçecektir.", Toast.LENGTH_LONG).show();
            }
        });


        return myFragmentView;
    }
}
