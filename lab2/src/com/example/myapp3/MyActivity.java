package com.example.myapp3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyActivity extends Activity implements View.OnClickListener{

    private static final int DELETE = 1;
    private static final int IDM_EDIT = 0;
    final int SEL_LABEL_CODE = 1;
    final String ATTRIBUTE_NAME_TEXT = "text";
    //final String ATTRIBUTE_NAME_IMAGE = "image";


    SimpleAdapter sAdapter;
    ListView lvSimple;
    ArrayList<Map<String, Object>> data;
    Map<String, Object> m;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button get_name_button = (Button) findViewById(R.id.get_name_button);
        get_name_button.setOnClickListener(this);
        // упаковываем данные в понятную для адаптера структуру
        data = new ArrayList<Map<String, Object>>();
        // массив имен атрибутов, из которых будут читаться данные
        String[] from = {ATTRIBUTE_NAME_TEXT};
        // массив ID View-компонентов, в которые будут вставлять данные
        int[] to = {R.id.tvText};
        // создаем адаптер
        sAdapter = new SimpleAdapter(this, data, R.layout.item, from, to);
        // определяем список и присваиваем ему адаптер

        lvSimple = (ListView) findViewById(R.id.lvSimple);
        lvSimple.setAdapter(sAdapter);
        registerForContextMenu(lvSimple);

/*
        //удаление
        lvSimple.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                data.remove(position);
                sAdapter.notifyDataSetChanged();
                return false;
            }
        });

        lvSimple.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //sAdapter.remove(sAdapter.getItem(position));

            }
        });
*/
    }


    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.get_name_button:

                intent = new Intent(this, SetLabelActivity.class);
                startActivityForResult(intent, SEL_LABEL_CODE);

                // создаем новый Map
                m = new HashMap<String, Object>();
                m.put(ATTRIBUTE_NAME_TEXT, " ");
                //m.put(ATTRIBUTE_NAME_IMAGE, R.drawable.ic_launcher);
                // добавляем его в коллекцию
                data.add(m);
                // уведомляем, что данные изменились
                sAdapter.notifyDataSetChanged();
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SEL_LABEL_CODE) {
                String inputText = data.getExtras().getString("inputText");
                m.put(ATTRIBUTE_NAME_TEXT, inputText);
                // уведомляем, что данные изменились
                sAdapter.notifyDataSetChanged();
            }
        } else {
            Toast.makeText(this, "Wrong result", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, DELETE, 0, "Удалить запись");
        menu.add(0, IDM_EDIT, 0, "Изменить текст");

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // получаем инфу о пункте списка
        AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            case DELETE:
                // удаляем Map из коллекции, используя позицию пункта в списке
                data.remove(acmi.position);
                // уведомляем, что данные изменились
                sAdapter.notifyDataSetChanged();
                break;

            case IDM_EDIT:
                //AdapterContextMenuInfo acmi = (AdapterContextMenuInfo) item.getMenuInfo();

                Intent intent = new Intent(this, SetLabelActivity.class);
                startActivityForResult(intent, SEL_LABEL_CODE);

                data.get(acmi.position);
                sAdapter.notifyDataSetChanged();
                break;

        }
        return super.onContextItemSelected(item);
    }

}

/*
    private static final int CM_DELETE_ID = 1;
    private static final int IDM_EDIT = 0;

    // имена атрибутов для Map
    final String ATTRIBUTE_NAME_TEXT = "text";
    final String ATTRIBUTE_NAME_IMAGE = "image";

    ListView lvSimple;
    SimpleAdapter sAdapter;
    ArrayList<Map<String, Object>> data;
    Map<String, Object> m;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // упаковываем данные в понятную для адаптера структуру

        data = new ArrayList<Map<String, Object>>();

        // массив имен атрибутов, из которых будут читаться данные
        String[] from = {ATTRIBUTE_NAME_TEXT, ATTRIBUTE_NAME_IMAGE};

        // массив ID View-компонентов, в которые будут вставлять данные
        int[] to = {R.id.tvText, R.id.ivImg};

        // создаем адаптер
        sAdapter = new SimpleAdapter(this, data, R.layout.item, from, to);

        // определяем список и присваиваем ему адаптер
        lvSimple = (ListView) findViewById(R.id.lvSimple);
        lvSimple.setAdapter(sAdapter);
        registerForContextMenu(lvSimple);

    }

    public void onButtonClick(View v) {
        // создаем новый Map
        m = new HashMap<String, Object>();
        m.put(ATTRIBUTE_NAME_TEXT, "text " + (data.size() + 1));
        m.put(ATTRIBUTE_NAME_IMAGE, R.drawable.ic_launcher);
        // добавляем его в коллекцию
        data.add(m);
        // уведомляем, что данные изменились
        sAdapter.notifyDataSetChanged();

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.add(0, CM_DELETE_ID, 0, "Удалить запись");
        menu.add(0, IDM_EDIT, 0, "Изменить текст");

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case CM_DELETE_ID:
                // получаем инфу о пункте списка
                AdapterContextMenuInfo acmi = (AdapterContextMenuInfo) item.getMenuInfo();
                // удаляем Map из коллекции, используя позицию пункта в списке
                data.remove(acmi.position);
                // уведомляем, что данные изменились
                sAdapter.notifyDataSetChanged();
                break;

            case IDM_EDIT:
                Toast.makeText(this, "Текст изменился", Toast.LENGTH_SHORT).show();
                AdapterContextMenuInfo acmi2 = (AdapterContextMenuInfo) item.getMenuInfo();

                data.get(acmi2.position);
                sAdapter.notifyDataSetChanged();
                break;

        }
        return super.onContextItemSelected(item);
    }
*/

