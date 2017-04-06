package id.ac.ui.cs.ristek.testgit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.ac.ui.cs.ristek.testgit.adapter.PixabayAdapter;
import id.ac.ui.cs.ristek.testgit.model.Hit;
import id.ac.ui.cs.ristek.testgit.model.Pixabay;
import id.ac.ui.cs.ristek.testgit.model.ResponsePixabay;
import id.ac.ui.cs.ristek.testgit.service.PixabayService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public static final String BASE_URL = "https://pixabay.com/";

    RecyclerView RV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RV = (RecyclerView) findViewById(R.id.rv);

        ArrayList<Pixabay> pixabays = new ArrayList<>();

        pixabays.add(new Pixabay("https://cdn.pixabay.com/photo/2017/03/17/16/27/shell-2152029_960_720.jpg", "Telur Paskah"));
        pixabays.add(new Pixabay("https://cdn.pixabay.com/photo/2015/04/08/13/12/food-712662_960_720.jpg", "Makanan Spanyol"));
        pixabays.add(new Pixabay("https://cdn.pixabay.com/photo/2015/01/31/14/40/peanuts-618547_960_720.jpg", "Kacang"));

        PixabayAdapter pixabayAdapter = new PixabayAdapter(this, pixabays);

        RV.setLayoutManager(new LinearLayoutManager(this));
        RV.setAdapter(pixabayAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //https://pixabay.com/api/?key=3968517-94dbe52e08b9ec52249a64fdc&q=yellow+flowers&image_type=photo

        PixabayService service = retrofit.create(PixabayService.class);
        service.getImages("3968517-94dbe52e08b9ec52249a64fdc", "animal").enqueue(new Callback<ResponsePixabay>() {
            @Override
            public void onResponse(Call<ResponsePixabay> call, Response<ResponsePixabay> response) {
                if (response.isSuccessful()) {
                    List<Hit> hits = response.body().hits;
                    for (int i = 0; i < hits.size(); i++) {
                        Log.d("wahyu",hits.get(i).webformatURL);
                        System.out.print(hits.get(i).webformatURL);
                    }
                    Toast.makeText(MainActivity.this, "berhasil coy", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsePixabay> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}
