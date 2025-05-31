package com.example.librarylighthouse.UI.Book;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.librarylighthouse.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public  class BookAdapter extends RecyclerView.Adapter <BookAdapter.ViewHolder> {
    private Context context;
    private List<Book> bookList;

    public BookAdapter(Context context, List<Book> books) {
        this.context = context;
        this.bookList = books;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name, description, author;

        public ViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.book_image);
            name = view.findViewById(R.id.book_name);
            description = view.findViewById(R.id.book_description);
            author = view.findViewById(R.id.book_author);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_book, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull BookAdapter.ViewHolder holder, int position) {
        Book product = bookList.get(position);
        holder.name.setText(product.getName());
        holder.description.setText(product.getDescription());
        holder.author.setText(product.getAuthor());
        Picasso.get().load(product.getImage()).into(holder.imageView);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, BookDetailActivity.class);
            intent.putExtra("name", product.getName());
            intent.putExtra("image", product.getImage());
            intent.putExtra("author", product.getAuthor());    // double or float (match in detail)
            intent.putExtra("description", product.getDescription());
            context.startActivity(intent);
        });
    }
}
