package com.example.nyt;

import android.app.DownloadManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;


public class ArticleRecyclerFragment extends Fragment {

    private RecyclerView recyclerView;

    public ArticleRecyclerFragment() {
        // Required empty public constructor
    }

    // Here the View object representing the Fragment is "inflated" from the layout file. You can
    // think of inflating as just transforming the XML to an actual View object that can be
    // displayed on screen. XML is just text. We need to inflate it to become an actual thing.
    //
    // If you want to do your own thing to the View (e.g. setText on specific TextViews, set up
    // RecyclerView, you can do it here. Alternatively, you may override onViewCreated and do the
    // set up there.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_article_recycler, container, false);

        // Now I can do all my normal set up stuff
        recyclerView = view.findViewById(R.id.rv_main);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        final ArticleAdapter articleAdapter = new ArticleAdapter();

        final RequestQueue requestQueue = Volley.newRequestQueue(view.getContext());
        String url = "https://api.nytimes.com/svc/mostpopular/v2/viewed/1.json?api-key="+getString(R.string.api_key);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                TopNewsStories topNewsStories = gson.fromJson(response, TopNewsStories.class);
                articleAdapter.setData((ArrayList<Article>) topNewsStories.results);
                FakeDatabase.addDataToFakeDatabaseFromJson((ArrayList<Article>) topNewsStories.results);
                recyclerView.setAdapter(articleAdapter);
                requestQueue.stop();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error receiving response from API");
                requestQueue.stop();
            }
        });

        requestQueue.add(stringRequest);

        // We wrote our setData method to be like a setter, so we give our ArrayList from
        // FakeDatabase to the Adapter.

        //This is where the data is set... However now we want to parse the data from JSON rather than from the FakeDatabase file...
//        Gson gson = new Gson();
//        TopNewsStories topNewsStories = gson.fromJson(FakeApi.getMostViewedStoriesJsonString(), TopNewsStories.class);
//
//        FakeDatabase.addDataToFakeDatabaseFromJson((ArrayList<Article>) topNewsStories.results);
//
//        articleAdapter.setData(FakeDatabase.getAllJsonArticles());
//        recyclerView.setAdapter(articleAdapter);

        return view;
    }

    // This is just an example of a way that the Fragment can communicate with the parent Activity.
    // Specifically, this is using a method belonging to the parent.
    @Override
    public void onResume() {
        super.onResume();
        MainActivity parent = (MainActivity) getActivity();
        parent.showCoolMessage("cool (from ArticleRecyclerFragment onResume)");
    }
}
