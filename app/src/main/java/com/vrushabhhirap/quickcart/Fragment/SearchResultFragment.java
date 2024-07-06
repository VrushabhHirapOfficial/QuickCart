package com.vrushabhhirap.quickcart.Fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.vrushabhhirap.quickcart.Activity.MainActivity;
import com.vrushabhhirap.quickcart.Adapter.SearchViewAdapter;
import com.vrushabhhirap.quickcart.Model.SearchViewModel;
import com.vrushabhhirap.quickcart.R;

import java.util.ArrayList;
import java.util.List;

public class SearchResultFragment extends Fragment {
    RecyclerView recyclerView;
    SearchViewAdapter searchViewAdapter;
    List<SearchViewModel> searchViewModelList;

    FirebaseFirestore firestore;

    private static final String ARG_TYPE = "type";
    private String type;

    public SearchResultFragment() {
        // Required empty public constructor
    }

    public static SearchResultFragment newInstance(String type) {
        SearchResultFragment fragment = new SearchResultFragment();
        Bundle args = new Bundle();
        args.putString("type", type);
        Log.d("SearchResultFragment", "newInstance: Type set to " + type);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getArguments().getString("type");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_result, container, false);


        Log.d("SearchResultFragment", "onCreateView: View created");
        Log.d("SearchResultFragment", "onCreateView: Received type " + type);

        firestore = FirebaseFirestore.getInstance();

        recyclerView = view.findViewById(R.id.Search_result_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        searchViewModelList = new ArrayList<>();
        searchViewAdapter = new SearchViewAdapter(getContext(), searchViewModelList, (MainActivity) getActivity());
        recyclerView.setAdapter(searchViewAdapter);

        Log.d("SearchResultFragment", "onCreateView: Received type " + type);


        if (type == null || type.isEmpty()) {
            firestore.collection("AllProducts")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (DocumentSnapshot doc : task.getResult().getDocuments()) {
                                    SearchViewModel searchViewModel = doc.toObject(SearchViewModel.class);
                                    searchViewModelList.add(searchViewModel);
                                    searchViewAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    });
        }
        if (type != null && type.equalsIgnoreCase("Shoes")) {
            firestore.collection("AllProducts").whereEqualTo("type", "Shoes")
                    .whereEqualTo("type", type)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (DocumentSnapshot doc : task.getResult().getDocuments()) {
                                    SearchViewModel searchViewModel = doc.toObject(SearchViewModel.class);
                                    searchViewModelList.add(searchViewModel);
                                    searchViewAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    });
        }
        if (type != null && type.equalsIgnoreCase("Toys")) {
            firestore.collection("AllProducts").whereEqualTo("type", "Toys")
                    .whereEqualTo("type", type)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (DocumentSnapshot doc : task.getResult().getDocuments()) {
                                    SearchViewModel searchViewModel = doc.toObject(SearchViewModel.class);
                                    searchViewModelList.add(searchViewModel);
                                    searchViewAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    });
        }
        if (type != null && type.equalsIgnoreCase("Fashion")) {
            firestore.collection("AllProducts").whereEqualTo("type", "Fashion")
                    .whereEqualTo("type",type)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {

                            if (task.isSuccessful()) {
                                for (DocumentSnapshot doc : task.getResult().getDocuments()) {
                                    SearchViewModel searchViewModel = doc.toObject(SearchViewModel.class);
                                    searchViewModelList.add(searchViewModel);
                                    searchViewAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    });
        }
        if (type != null && type.equalsIgnoreCase("Beauty")) {
            firestore.collection("AllProducts").whereEqualTo("type", "Beauty")
                    .whereEqualTo("type", type)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (DocumentSnapshot doc : task.getResult().getDocuments()) {
                                    SearchViewModel searchViewModel = doc.toObject(SearchViewModel.class);
                                    searchViewModelList.add(searchViewModel);
                                    searchViewAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    });
        }
        if (type != null && type.equalsIgnoreCase("Electronics")) {
            firestore.collection("AllProducts").whereEqualTo("type", "Electronics")
                    .whereEqualTo("type", type)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (DocumentSnapshot doc : task.getResult().getDocuments()) {
                                    SearchViewModel searchViewModel = doc.toObject(SearchViewModel.class);
                                    searchViewModelList.add(searchViewModel);
                                    searchViewAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    });
        }
        if (type != null && type.equalsIgnoreCase("Books")) {
            firestore.collection("AllProducts").whereEqualTo("type", "Books")
                    .whereEqualTo("type", type)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (DocumentSnapshot doc : task.getResult().getDocuments()) {
                                    SearchViewModel searchViewModel = doc.toObject(SearchViewModel.class);
                                    searchViewModelList.add(searchViewModel);
                                    searchViewAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    });
        }
        if (type != null && type.equalsIgnoreCase("Watches")) {
            firestore.collection("AllProducts").whereEqualTo("type", "Watches")
                    .whereEqualTo("type", type)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (DocumentSnapshot doc : task.getResult().getDocuments()) {
                                    SearchViewModel searchViewModel = doc.toObject(SearchViewModel.class);
                                    searchViewModelList.add(searchViewModel);
                                    searchViewAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    });
        }
        if (type != null && type.equalsIgnoreCase("Jewellery")) {
            firestore.collection("AllProducts").whereEqualTo("type", "Jewellery")
                    .whereEqualTo("type", type)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (DocumentSnapshot doc : task.getResult().getDocuments()) {
                                    SearchViewModel searchViewModel = doc.toObject(SearchViewModel.class);
                                    searchViewModelList.add(searchViewModel);
                                    searchViewAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    });
        }
        return view;

    }


}
