package com.knstech.apnaopd.Patient.ADAPTORS;

import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.knstech.apnaopd.Doctor.EPresc;
import com.knstech.apnaopd.GenModelClasses.Doctor.Patient;
import com.knstech.apnaopd.R;
import com.knstech.apnaopd.Utils.AppUtils;
import com.knstech.apnaopd.Utils.C;
import com.knstech.apnaopd.Utils.Connections.RequestGet;

import org.json.JSONObject;

import java.util.List;

public class PreviousAppointmentsAdapter extends RecyclerView.Adapter {

    public PreviousAppointmentsAdapter(List<Patient> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    private List<Patient> mList;
    private Context mContext;
    private View view;


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        view= LayoutInflater.from(mContext).inflate(R.layout.d_appointment_item_layout,null);
        return new PreviousViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((PreviousViewHolder)viewHolder).bind(mList.get(i));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    public class PreviousViewHolder extends RecyclerView.ViewHolder{

        TextView name,fee,department,timestamp;
        Button changeStatus,cancel;

        public PreviousViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            fee=itemView.findViewById(R.id.fee);
            department=itemView.findViewById(R.id.age);
            changeStatus=itemView.findViewById(R.id.diagnosed);
            cancel=itemView.findViewById(R.id.cancel);
            changeStatus.setVisibility(View.GONE);
            cancel.setVisibility(View.GONE);
            timestamp=itemView.findViewById(R.id.timestamp);
        }
        public void bind(final Patient patient)
        {
            timestamp.setText("Time : "+ C.getDateAndTime(patient.getTime_slab()));
            name.setText("Dr. "+patient.getDoctor_name());
            fee.setText("For - "+patient.getPatient_name()+"\nFee : Rs."+patient.getFee());
            department.setText(patient.getDepartment());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final AlertDialog.Builder dialog=new AlertDialog.Builder(mContext);
                    final View viewerLayout=LayoutInflater.from(mContext).inflate(R.layout.epresc_viewer_layout,null);
                    RequestGet get=new RequestGet(mContext);
                    String url= AppUtils.HOST_ADDRESS+"/api/eprescriptions/"+patient.getEprescription_id();
                    get.getJSONObject(url, new RequestGet.JSONObjectResponseListener() {
                        @Override
                        public void onResponse(JSONObject object) {
                            LinearLayout medLinLayout = viewerLayout.findViewById(R.id.medicineLinLayout);
                            TextView textView = viewerLayout.findViewById(R.id.comment);
                            EPresc ePresc = EPresc.parseFromJson(object.toString());
                            if (ePresc.getMedicineList() != null) {
                                for (int i = 0; i < ePresc.getMedicineList().size(); i++) {
                                    View medView = LayoutInflater.from(mContext).inflate(R.layout.medicine_item_layout, null);
                                    EditText medName, medType, medDday, medDper;
                                    RadioButton after, before;
                                    Button btn;

                                    btn=medView.findViewById(R.id.minus);
                                    btn.setVisibility(View.GONE);

                                    medName = medView.findViewById(R.id.medicine);
                                    medName.setText("Medicine : " + ePresc.getMedicineList().get(i).getMedicine_name());
                                    medName.setEnabled(false);

                                    medType = medView.findViewById(R.id.type);
                                    medType.setText("Type : " + ePresc.getMedicineList().get(i).getType());
                                    medType.setEnabled(false);

                                    medDday = medView.findViewById(R.id.dday);
                                    medDday.setText("Day : " + ePresc.getMedicineList().get(i).getDosage_day());
                                    medDday.setEnabled(false);

                                    medDper = medView.findViewById(R.id.dper);
                                    medDper.setText("Dosage/Day : " + ePresc.getMedicineList().get(i).getDosage_per());
                                    medDper.setEnabled(false);

                                    after = medView.findViewById(R.id.after);
                                    before = medView.findViewById(R.id.before);
                                    if ((ePresc.getMedicineList().get(i).getBefor_after_meal().equals("1"))) {
                                        after.setChecked(true);
                                    } else {
                                        before.setChecked(true);
                                    }
                                    after.setEnabled(false);
                                    before.setEnabled(false);
                                    medLinLayout.addView(medView);
                                }
                            }
                            textView.setText(ePresc.getComment());
                            dialog.setView(viewerLayout);
                            dialog.show();
                        }
                    });

                }
            });

        }
    }
}
