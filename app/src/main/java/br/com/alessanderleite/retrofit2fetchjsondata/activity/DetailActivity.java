package br.com.alessanderleite.retrofit2fetchjsondata.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.util.Linkify;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import br.com.alessanderleite.retrofit2fetchjsondata.R;

public class DetailActivity extends AppCompatActivity {

    private TextView mUsername;
    private TextView mLink;
    private ImageView mAvatarUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initViews();
    }

    private void initViews() {
        mUsername = (TextView) findViewById(R.id.username);
        mLink = (TextView) findViewById(R.id.link);
        mAvatarUrl = (ImageView) findViewById(R.id.user_image_header);

        String username = getIntent().getExtras().getString("login");
        String link = getIntent().getExtras().getString("html_url");
        String avatarUrl = getIntent().getExtras().getString("avatar_url");

        mUsername.setText(username);
        mLink.setText(link);
        Linkify.addLinks(mLink, Linkify.WEB_URLS);

        Glide.with(this)
                .load(avatarUrl)
                .into(mAvatarUrl);
    }
}
