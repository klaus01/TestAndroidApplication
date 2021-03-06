package activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.activeandroid.query.Select;
import com.example.kelei.testandroidapplication.R;

import helper.api.*;
import model.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiRecommend.getList(1, 1, new ApiHelper.ApiComplete<ApiPagingModel<RecommendModel>>() {
                    @Override
                    public void onComplete(int statusCode, ApiInfoModel<ApiPagingModel<RecommendModel>> apiInfo) {
                        if (apiInfo != null && apiInfo.isSuc && apiInfo.result != null && apiInfo.result.Items.length > 0) {
                            for (RecommendModel item : apiInfo.result.Items) {
                                item.save();
                            }
                        }
                    }
                });

                ApiClub.getList("0", 0, 0, 1, 2, new ApiHelper.ApiComplete<ApiPagingModel<ClubModel>>() {
                    @Override
                    public void onComplete(int statusCode, ApiInfoModel<ApiPagingModel<ClubModel>> apiInfo) {
                        if (apiInfo != null && apiInfo.isSuc && apiInfo.result != null && apiInfo.result.Items.length > 0) {
                            for (ClubModel item : apiInfo.result.Items) {
                                item.save();
                            }
                        }
                    }
                });

                ApiClub.getInfo(7, new ApiHelper.ApiComplete<ClubModel>() {
                    @Override
                    public void onComplete(int statusCode, ApiInfoModel<ClubModel> apiInfo) {
                        if (apiInfo != null && apiInfo.isSuc && apiInfo.result != null) {
                            apiInfo.result.save();
                        }
                    }
                });

                RecommendModel item = new Select().from(RecommendModel.class).executeSingle();
                String str = "item is null";
                if (item != null) {
                    str = item.Img;
                    item.delete();
                }
                Snackbar.make(view, str, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
