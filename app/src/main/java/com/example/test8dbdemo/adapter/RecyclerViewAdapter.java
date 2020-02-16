package com.example.test8dbdemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test8dbdemo.ContactProfile;
import com.example.test8dbdemo.R;
import com.example.test8dbdemo.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter <RecyclerViewAdapter.ViewHolder > implements Filterable {

    private static Context context;
    private static List<Contact> contactList;
    private static List<Contact> filteredContactList;

    public RecyclerViewAdapter(Context context, List<Contact> contactList) {
        this.context = context;
        this.contactList = contactList;
        filteredContactList=new ArrayList<>(contactList);
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        Contact contact=filteredContactList.get(position);

        holder.contactname.setText(contact.getName());
        holder.contactno.setText(contact.getPhoneNumber());

    }

    @Override
    public int getItemCount() {
        return filteredContactList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter=new Filter( ) {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            String charString=constraint.toString();
            if(charString.isEmpty()){
                filteredContactList=contactList;
            }else{
                List<Contact> filterList=new ArrayList<>();
                for(Contact row:contactList){
                    if(row.getName().toLowerCase().contains(charString.toLowerCase())||row.getPhoneNumber().contains(charString)){
                        filterList.add(row);
                    }
                }
                filteredContactList=filterList;
            }
            FilterResults filterResults=new FilterResults();
            filterResults.values=filteredContactList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredContactList=(ArrayList<Contact>) results.values;
            notifyDataSetChanged();
        }
    };

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

       public TextView contactname;
        public TextView contactno;
        public ImageView contactphoto;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            contactphoto=itemView.findViewById(R.id.contact_photo);
            contactname=itemView.findViewById(R.id.contact_name);
            contactno=itemView.findViewById(R.id.contact_no);


            contactphoto.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int pos=this.getAdapterPosition();
            Contact contact=filteredContactList.get(pos);
//            Log.i("clicked",String.valueOf(pos));
//            Log.i("name",contact.getName()+","+contact.getPhoneNumber()+","+contact.getId());

            Intent intent=new Intent(context, ContactProfile.class);
            intent.putExtra("name",contact.getName());
            intent.putExtra("phone",contact.getPhoneNumber());
            context.startActivity(intent);
        }
    }
}
