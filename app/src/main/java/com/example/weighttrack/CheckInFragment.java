package com.example.weighttrack;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class CheckInFragment extends Fragment {

    private TextView tvCheckInUnit;
    private EditText etCheckInWeight;
    private Button btAdd;

    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_check_in,container, false);
    }

    @Override
    public void onViewCreated( View view,  Bundle savedInstanceState) {
        tvCheckInUnit = view.findViewById(R.id.tvCheckInUnit);
        tvCheckInUnit.setText(PreferenceManager.get_weightUnit());
        etCheckInWeight = view.findViewById(R.id.etCheckInWeight);
        btAdd = view.findViewById(R.id.btAdd);

        btAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                AddWeight(etCheckInWeight.getText().toString());
                ((HomeActivity)getActivity()).CreateStandardHomeFragment();
            }
        });
    }

    public void AddWeight(String text){
        double weightVal;
        long date = Stats.getLongDateWithoutTime();

        try{
            weightVal = Double.parseDouble(text);
            if(!PreferenceManager.is_metric()){weightVal = Calc.PoundToKg(weightVal);}
            ((HomeActivity)getActivity()).add_weight(new Weight(date,weightVal));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
