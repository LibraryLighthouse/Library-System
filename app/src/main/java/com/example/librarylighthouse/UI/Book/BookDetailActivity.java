package com.example.librarylighthouse.UI.Book;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.librarylighthouse.R;
import com.squareup.picasso.Picasso;

public class BookDetailActivity extends AppCompatActivity {
    ImageView imageView;
    TextView title, description, author;
    ImageButton btnBack;
    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_book);
        imageView = findViewById(R.id.bookImage);
        title = findViewById(R.id.book_name);
        description = findViewById(R.id.book_description);
        author = findViewById(R.id.book_author);
        btnBack = findViewById(R.id.btn_back);
        // Set click listener on back button
        btnBack.setOnClickListener(v -> finish());

        String bookName = getIntent().getStringExtra("name");
        String bookImage = getIntent().getStringExtra("image");
        String bookAuthor = getIntent().getStringExtra("author");
        String bookDescription = getIntent().getStringExtra("description");

        title.setText(bookName);
        description.setText(bookDescription);
        author.setText(bookAuthor);
        Picasso.get().load(bookImage).into(imageView);
    }
}
