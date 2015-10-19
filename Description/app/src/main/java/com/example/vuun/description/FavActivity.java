package com.example.vuun.description;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;


public class FavActivity extends AppCompatActivity {

    private List<Category> catList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        setContentView(R.layout.activity_fav);

//        ExpandableListView exList = (ExpandableListView) findViewById(R.id.expandableListView1);
//        exList.setIndicatorBounds(5, 5);
//        ExpandableAdapter exAdpt = new ExpandableAdapter(catList, this);
//        exList.setIndicatorBounds(0, 20);
//        exList.setAdapter(exAdpt);
    }

    private void initData() {
//        catList = new ArrayList<Category>();
//
//        Category cat1 = createCategory("Games", "Game for console", 1);
//        cat1.setItemList(createItems("Game Item", "This is the game n.", 5));
//
//        Category cat2 = createCategory("Mobile Phone", "All the mobile phone", 2);
//        cat2.setItemList(createItems("Phone Item", "This is the phone n.", 5));
//
//        catList.add(cat1);
//        catList.add(cat2);
    }

    private Category createCategory(String name, String descr, long id) {
        return new Category(id, name, descr);
    }


    private List<ItemDetail> createItems(String name, String descr, int num) {
        List<ItemDetail> result = new ArrayList<ItemDetail>();

        for (int i=0; i < num; i++) {
            ItemDetail item = new ItemDetail(i, 0, "item" + i, "Descr" + i);
            result.add(item);
        }

        return result;
    }
}
