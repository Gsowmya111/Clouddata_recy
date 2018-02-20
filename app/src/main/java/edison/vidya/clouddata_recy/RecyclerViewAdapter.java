package edison.vidya.clouddata_recy;

import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by Deepak Rao J on 2/7/2018.
 */

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    Context context;

    List<DataAdapter> dataAdapters;

    public RecyclerViewAdapter(List<DataAdapter> getDataAdapter, Context context){

        super();

        this.dataAdapters = getDataAdapter;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recy_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        DataAdapter dataAdapter =  dataAdapters.get(position);

        viewHolder.TextViewID.setText(dataAdapter.getName());

        viewHolder.TextViewName.setText(String.valueOf(dataAdapter.getId()));

        viewHolder.TextViewPhoneNumber.setText(dataAdapter.getPhone_number());

        viewHolder.TextViewSubject.setText(dataAdapter.getSubject());

    }

    @Override
    public int getItemCount() {

        return dataAdapters.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView TextViewName;
        public TextView TextViewID;
        public TextView TextViewPhoneNumber;
        public TextView TextViewSubject;


        public ViewHolder(View itemView) {

            super(itemView);

            TextViewName = (TextView) itemView.findViewById(R.id.date_time) ;
            TextViewID = (TextView) itemView.findViewById(R.id.dev_name) ;
            TextViewPhoneNumber = (TextView) itemView.findViewById(R.id.swt_num) ;
            TextViewSubject = (TextView) itemView.findViewById(R.id.dev_type) ;


        }
    }
}