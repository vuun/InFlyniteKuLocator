package com.example.vuun.description;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivitySearch extends AppCompatActivity {

    private List<Category> catList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        setContentView(R.layout.activity_main_activity_search);

        ExpandableListView exList = (ExpandableListView) findViewById(R.id.expandableListView1);
        exList.setIndicatorBounds(5, 5);
        ExpandableAdapter exAdpt = new ExpandableAdapter(catList, this);
        exList.setIndicatorBounds(0, 20);
        exList.setAdapter(exAdpt);
    }

    private void initData() {
        catList = new ArrayList<Category>();

        Category cat1 = createCategory("   Stationary Store", "", 1);
        cat1.setItemList(createItems("Store", "x", 5));

        Category cat2 = createCategory("   Toilet", "", 2);
        cat2.setItemList(createItems("Toilet", "x", 5));

        Category cat3 = createCategory("   COPY SHOP", "", 3);
        cat3.setItemList(createItems("Shop", "x", 5));

        catList.add(cat1);
        catList.add(cat2);
        catList.add(cat3);
    }

    private Category createCategory(String name, String descr, long id) {
        return new Category(id, name, descr);
    }


    private List<ItemDetail> createItems(String name, String descr, int num) {
        List<ItemDetail> result = new ArrayList<ItemDetail>();

        for (int i=0; i < num; i++) {
            ItemDetail item = new ItemDetail(i, 0, name + i, descr + i);
            result.add(item);
        }

        return result;
    }

    @Override
    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");
        Toast.makeText(getApplicationContext(), "Closed Search page", Toast.LENGTH_SHORT).show();
        finish();
    }
}
