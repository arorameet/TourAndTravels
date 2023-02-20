package com.example.tourandtravels.ui.home;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tourandtravels.R;
import com.example.tourandtravels.databinding.FragmentHomeBinding;

import java.util.Calendar;

public class HomeFragment extends Fragment implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
//    private FragmentHomeBinding binding;

    Button btn;
    EditText from;
    EditText to;
    TextView date;
    int d,m,y;
    int day, month, year;
    Calendar ca;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, null, true);
        from = v.findViewById(R.id.editTextTextPersonName3);
        to = v.findViewById(R.id.editTextTextPersonName5);
        date = v.findViewById(R.id.editTextDate);

        btn=v.findViewById(R.id.button);
        date.setOnClickListener(this);

        return v;
    }
    @Override
    public void onClick(View view) {
        if (view==date){
            ca = Calendar.getInstance();
            d = ca.get(Calendar.DAY_OF_MONTH);
            m = ca.get(Calendar.MONTH);
            y = ca.get(Calendar.YEAR);

            DatePickerDialog dp = new DatePickerDialog(getActivity(), this, y, m, d);
            dp.show();
        }

    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

        day = i2;
        month = i1+1;
        year = i;

        date.setText(day+"/"+month+"/"+year);
    }
}