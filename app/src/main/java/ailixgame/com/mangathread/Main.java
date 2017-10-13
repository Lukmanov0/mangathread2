package ailixgame.com.mangathread;


import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class Main extends Activity{

    private RecyclerView recyclearView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    JsonRequestActivity jsonRequestActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        recyclearView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclearView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclearView.setLayoutManager(layoutManager);
        //adapter = new MyAdpater();
        recyclearView.setAdapter(adapter);

        jsonRequestActivity = new JsonRequestActivity();
        jsonRequestActivity.makeJsonObjReq();
    }

    @Override
    protected void onResume() {
        super.onResume();
}
}
