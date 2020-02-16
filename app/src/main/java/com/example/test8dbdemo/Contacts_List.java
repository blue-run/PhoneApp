package com.example.test8dbdemo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.IBinder;
import android.os.ResultReceiver;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputBinding;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.view.inputmethod.InputMethodSession;
import android.view.inputmethod.InputMethodSubtype;

import com.example.test8dbdemo.adapter.RecyclerViewAdapter;
import com.example.test8dbdemo.data.MyDbHandler;
import com.example.test8dbdemo.model.Contact;

import java.util.ArrayList;
import java.util.List;


public class Contacts_List extends Fragment {

    public static List<Contact> allContacts;
    public RecyclerView recyclerViewContactsList;
    public RecyclerViewAdapter recyclerViewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_contacts__list, container, false);

        recyclerViewContactsList = view.findViewById(R.id.recyclerViewContactsList);
        recyclerViewContactsList.setHasFixedSize(true);
        recyclerViewContactsList.setLayoutManager(new LinearLayoutManager(getActivity( )));
        recyclerViewContactsList.setItemAnimator(new DefaultItemAnimator( ));
        recyclerViewContactsList.addItemDecoration(new DividerItemDecoration(getActivity( ), DividerItemDecoration.VERTICAL));
        MyDbHandler myDbHandler = new MyDbHandler(getActivity( ));

//
//        Contact suresh ;
//         suresh =new Contact("karan","8565845234");
//      myDbHandler.addContact(suresh);
//       suresh =new Contact("Arjun","8565845234");
//        myDbHandler.addContact(suresh);
//        suresh =new Contact("Abhishek","8565845234");
//        myDbHandler.addContact(suresh);
//        suresh =new Contact("Batman","8565845234");
//       myDbHandler.addContact(suresh);

//        myDbHandler.deleteAllContacts();


        allContacts = myDbHandler.getAllContacts( );
//       myDbHandler.updateContacts(allContacts.get(1),null,"rakesh");
//        myDbHandler.deleteContact(allContacts.get(1));

        MainActivity.searchEditText.setHint(allContacts.size( ) + " Contacts");

        recyclerViewAdapter = new RecyclerViewAdapter(getActivity( ), allContacts);
        recyclerViewContactsList.setAdapter(recyclerViewAdapter);

        MainActivity.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener( ) {
            @Override
            public boolean onQueryTextSubmit(String query) {
                recyclerViewAdapter.getFilter( ).filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                recyclerViewAdapter.getFilter( ).filter(newText);
                return false;
            }
        });

        return view;
    }

}
