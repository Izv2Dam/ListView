package com.izv.dam.listviewv3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Principal extends AppCompatActivity {
    private ClaseAdaptador cl;
    private List<String> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void init(){
        final ListView lv = (ListView) findViewById(R.id.lvLista);
        lista = new ArrayList<>();
        lista.add("Pepe");
        lista.add("Juan");
        lista.add("Pepe");
        lista.add("Juana");
        lista.add("Juana1");
        lista.add("Juana2");
        lista.add("Juana3");
        lista.add("Juana4");
        lista.add("Juana5");
        lista.add("Juana7");
        lista.add("Juana8");
        lista.add("Juana9");
        lista.add("Juana12");
        lista.add("Juan342");
        lista.add("Juanare");
        cl = new ClaseAdaptador(this, R.layout.item, lista);
        lv.setAdapter(cl);
        lv.setTag(lista);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String dato = (String) lv.getItemAtPosition(position);
                //dato = ((String[])lv.getTag())[position];
                //dato = lista.get(position);
                Toast.makeText(Principal.this, "Posicion: " + position + " " + dato, Toast.LENGTH_LONG).show();
                //lista.remove(position);
                //cl.notifyDataSetChanged();
                //cl.remove(dato);
                cl.borrar(position);
            }
        });
        /*lv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });*/
        ImageView iv = (ImageView)lv.findViewById(R.id.ivFlor);
        if(iv!=null){
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tostada(v);

                }
            });
        }
        registerForContextMenu(lv);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_contextual, menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        long id = item.getItemId();
        AdapterView.AdapterContextMenuInfo vistaInfo =
                (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int posicion = vistaInfo.position;
        if(id==R.id.menu_borrar){
            //lista.remove(posicion);
            //cl.notifyDataSetChanged();
            cl.borrar(posicion);
            return true;
        } else if(id==R.id.menu_editar){
            return true;
        }else if(id==R.id.menu_insertar){
            lista.add(lista.get(posicion));
            cl.notifyDataSetChanged();
            return true;
        }
        return super.onContextItemSelected(item);
    }

    public void tostada(View v){
        Toast.makeText(this, "flor "+v.getTag(), Toast.LENGTH_LONG).show();
    }


}