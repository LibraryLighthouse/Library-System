package com.example.librarylighthouse.UI.Home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.librarylighthouse.ApiServices;
import com.example.librarylighthouse.RetrofitClient;
import com.example.librarylighthouse.UI.Book.Book;
import com.example.librarylighthouse.UI.Book.BookAdapter;
import com.example.librarylighthouse.databinding.FragmentBookBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookFragment extends Fragment {
    FragmentBookBinding fragmentBookBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
        fetchBook();
        return fragmentBookBinding.getRoot();
    }
    private void fetchBook() {
        ApiServices apiServices = RetrofitClient.getClient().create(ApiServices.class);
        apiServices.getBooks().enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Book> productList = response.body();
                    BookAdapter adapter = new BookAdapter(getContext(), productList);
                    fragmentBookBinding.recyclerView.setAdapter(adapter); // Make sure your RecyclerView ID is correct
                }
            }
            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Toast.makeText(getContext(), "Failed to load products", Toast.LENGTH_SHORT).show();

            }
        });

    }
}
