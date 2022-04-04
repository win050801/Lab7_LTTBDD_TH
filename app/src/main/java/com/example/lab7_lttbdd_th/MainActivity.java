package com.example.lab7_lttbdd_th;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
    DataUser dataUser;
    Button add,remove,cacel;
    EditText txtname;
    ListView lvName;
    ArrayList namelist;
    ArrayAdapter adapter;
    ArrayList idList;
    int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataUser = new DataUser(this,"userdb.sqlite",null,1);
//        dataUser.addUser(new User("Thang"));
        add = findViewById(R.id.btnAdd);
        remove = findViewById(R.id.btnRemove);
        cacel = findViewById(R.id.btnCancel);
        txtname = findViewById(R.id.txtName);
        lvName = findViewById(R.id.idListView);

        namelist = new ArrayList();
        idList = new ArrayList<>();
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,namelist);
        namelist = getNamelist();
        lvName.setAdapter(adapter);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dataUser.addUser(new User(txtname.getText().toString()));
                adapter.clear();
                namelist = getNamelist();
                adapter.notifyDataSetChanged();
            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataUser.RemoveUser(Integer.parseInt(idList.get(index).toString()) );
                adapter.clear();
                getNamelist();
                adapter.notifyDataSetChanged();


            }
        });

        lvName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                index = i ;
            }
        });
    }

    private  ArrayList getNamelist(){
        ArrayList<String> nameList = new ArrayList<>();
        idList.clear();
        for (Iterator iterator = dataUser.getAll().iterator(); iterator.hasNext(); ) {
            User user = (User) iterator.next();
            namelist.add(user.getName());
            idList.add(user.getId());
        }

        return namelist;
    }
}