package com.example.librarylighthouse.UI.Book;

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
    TextView name, description, author;
    ImageButton btnBack;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        imageView = findViewById(R.id.detail_image);
        name = findViewById(R.id.detail_name);
        description = findViewById(R.id.detail_price);
        author = findViewById(R.id.detail_rating);
        btnBack = findViewById(R.id.btn_back);

        // Set click listener on back button
        btnBack.setOnClickListener(v -> finish());

        String productName = getIntent().getStringExtra("name");
        String productImage = getIntent().getStringExtra("image");
        double productPrice = getIntent().getDoubleExtra("price", 0.0);
        float productRating = getIntent().getFloatExtra("rating", 0.f);
        String productDescription = getIntent().getStringExtra("description");

        name.setText(productName);
        price.setText("$" + productPrice);
        rating.setText("★ " + productRating);
        description.setText(productDescription);

        Picasso.get().load(productImage).into(imageView);
    }
}
