package edison.vidya.clouddata_recy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import org.json.JSONArray;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.widget.Toast;
import com.android.volley.RequestQueue;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    List<DataAdapter> DataAdapterClassList;
String buttontext;
    RecyclerView recyclerView;

    RecyclerView.LayoutManager recyclerViewlayoutManager;

    RecyclerView.Adapter recyclerViewadapter;

    ProgressBar progressBar;

    JsonArrayRequest jsonArrayRequest ;

    ArrayList<String> SubjectNames;

    RequestQueue requestQueue ;

  //  String HTTP_SERVER_URL = "https://androidjsonblog.000webhostapp.com/StudentDetails.php";
  String HTTP_SERVER_URL = "http://androidblog.esy.es/AndroidJSon/Subjects.php";

    View ChildView ;

    int RecyclerViewClickedItemPOS ;
    private List<DataAdapter> listSuperHeroes;
    private List<DataAdapter> listSuperHeroes1;
    Button day,week,month,year;
    JSONArray jsonArr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        day= (Button) findViewById(R.id.btn_day);
        week= (Button) findViewById(R.id.btn_week);
        month= (Button) findViewById(R.id.btn_month);
        year= (Button) findViewById(R.id.btn_year);

        day.setOnClickListener(this);
        week.setOnClickListener(this);
        month.setOnClickListener(this);
        year.setOnClickListener(this);

        DataAdapterClassList = new ArrayList<>();

        SubjectNames = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

      //  progressBar = (ProgressBar) findViewById(R.id.progressBar);

        recyclerView.setHasFixedSize(true);

        recyclerViewlayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(recyclerViewlayoutManager);

        // JSON data web call function call from here.
     //   JSON_WEB_CALL();

        //RecyclerView Item click listener code starts from here.
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            GestureDetector gestureDetector = new GestureDetector(MainActivity.this, new GestureDetector.SimpleOnGestureListener() {

                @Override public boolean onSingleTapUp(MotionEvent motionEvent) {

                    return true;
                }

            });
            @Override
            public boolean onInterceptTouchEvent(RecyclerView Recyclerview, MotionEvent motionEvent) {

                ChildView = Recyclerview.findChildViewUnder(motionEvent.getX(), motionEvent.getY());

                if(ChildView != null && gestureDetector.onTouchEvent(motionEvent)) {

                    //Getting RecyclerView Clicked item value.
                    RecyclerViewClickedItemPOS = Recyclerview.getChildAdapterPosition(ChildView);

                    //Printing RecyclerView Clicked item clicked value using Toast Message.
                   Toast.makeText(MainActivity.this, SubjectNames.get(RecyclerViewClickedItemPOS), Toast.LENGTH_LONG).show();

                }

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView Recyclerview, MotionEvent motionEvent) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

    }


    public void JSON_WEB_CALL(){



        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://androidblog.esy.es/AndroidJSon/Subjects.php", new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response) {
                try {
                    jsonArr = new JSONArray(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (buttontext.equals("day")) {
                    //recyclerViewadapter = new RecyclerViewAdapter(DataAdapterClassList, this);

                    JSON_PARSE_DATA_AFTER_WEBCALL(jsonArr);

                } else if (buttontext.equals("week")) {
                    JSON_PARSE_DATA_AFTER_WEBCALL_week(jsonArr);
                }
            }

        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError volleyError)
            {
            }
        })
        {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", "12");
                return params;
            }
        };
        Volley.newRequestQueue(this).add(stringRequest);





        /*jsonArrayRequest = new JsonArrayRequest(HTTP_SERVER_URL,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if(buttontext.equals("day")){
                            //recyclerViewadapter = new RecyclerViewAdapter(DataAdapterClassList, this);

                            JSON_PARSE_DATA_AFTER_WEBCALL(response);

                    }else   if(buttontext.equals("week")){
                            JSON_PARSE_DATA_AFTER_WEBCALL_week(response);
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(jsonArrayRequest);*/
    }

    public void JSON_PARSE_DATA_AFTER_WEBCALL(JSONArray array) {

        for (int i = 0; i < array.length(); i++) {

            DataAdapter GetDataAdapter2 = new DataAdapter();

            JSONObject json = null;

            try {
                json = array.getJSONObject(i);

                GetDataAdapter2.setId(json.getInt("id"));

                GetDataAdapter2.setName(json.getString("subjects"));

                //Adding subject name here to show on click event.
                SubjectNames.add(json.getString("subjects"));

               // GetDataAdapter2.setSubject(json.getString("subject"));

              //  GetDataAdapter2.setPhone_number(json.getString("phone_number"));

            } catch (JSONException e) {

                e.printStackTrace();
            }
           // listSuperHeroes = new ArrayList<>();
           // listSuperHeroes.add(GetDataAdapter2);
            DataAdapterClassList.add(GetDataAdapter2);


        }
        recyclerViewadapter = new RecyclerViewAdapter(DataAdapterClassList, this);

        recyclerView.setAdapter(recyclerViewadapter);

    }

    public void JSON_PARSE_DATA_AFTER_WEBCALL_week(JSONArray array) {

        for (int i = 0; i < array.length(); i++) {

            DataAdapter GetDataAdapter2 = new DataAdapter();

            JSONObject json = null;

            try {
                json = array.getJSONObject(i);

                GetDataAdapter2.setId(json.getInt("id"));

              //  GetDataAdapter2.setName(json.getString("name"));

                //Adding subject name here to show on click event.
               // SubjectNames.add(json.getString("name"));

                //  GetDataAdapter2.setSubject(json.getString("subject"));

                // GetDataAdapter2.setPhone_number(json.getString("phone_number"));

            } catch (JSONException e) {

                e.printStackTrace();
            }
           // listSuperHeroes1 = new ArrayList<>();
           // listSuperHeroes1.add(GetDataAdapter2);
            DataAdapterClassList.add(GetDataAdapter2);

        }


//        progressBar.setVisibility(View.GONE);

        recyclerViewadapter = new RecyclerViewAdapter(DataAdapterClassList, this);

        recyclerView.setAdapter(recyclerViewadapter);
    }



    @Override
    public void onClick(View view) {
      switch (view.getId()){
          case R.id.btn_day:
              DataAdapterClassList.clear();
              buttontext="day";
              JSON_WEB_CALL();
                break;
          case R.id.btn_week:
              DataAdapterClassList.clear();
              buttontext="week";
              JSON_WEB_CALL();

              break;

          case R.id.btn_month:
              buttontext="month";
              JSON_WEB_CALL();
              break;

          case R.id.btn_year:
             // JSON_WEB_CALL();
              break;

      }
    }


}
