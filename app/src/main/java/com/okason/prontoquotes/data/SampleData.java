package com.okason.prontoquotes.data;

import android.content.Context;
import android.content.Intent;

import com.okason.prontoquotes.core.listeners.OnDatabaseOperationCompleteListener;
import com.okason.prontoquotes.models.Author;
import com.okason.prontoquotes.models.Quote;
import com.okason.prontoquotes.ui.quotelist.QuoteListActivity;

import java.util.HashMap;

/**
 * Created by Valentine on 2/16/2017.
 */

public class SampleData {

    private final QuoteRealmRepository repository;
    private final Context context;
    private final AuthorRealmRepository authorRealmRepository;


    OnDatabaseOperationCompleteListener listener = new OnDatabaseOperationCompleteListener() {
        @Override
        public void onSaveOperationFailed(String error) {

        }

        @Override
        public void onSaveOperationSucceeded(long id) {

            //Once the data is added, simply restart the Notelist
            //Activity so the default data will be displayed
           if (id > 1){
               Intent restartIntent = new Intent(context, QuoteListActivity.class);
               restartIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               context.startActivity(restartIntent);
           }

        }

        @Override
        public void onDeleteOperationCompleted(String message) {

        }

        @Override
        public void onDeleteOperationFailed(String error) {

        }

        @Override
        public void onUpdateOperationCompleted(String message) {

        }

        @Override
        public void onUpdateOperationFailed(String error) {

        }
    };


    HashMap<String, HashMap<String, String>> authorQuotes = new HashMap<>();

    public SampleData(Context context) {
        this.context = context;
        repository = new QuoteRealmRepository();
        authorRealmRepository = new AuthorRealmRepository(context);
        addAuthors();
        addQuotes();

    }




    public void addQuotes() {
        Quote quote1 = new Quote();
        quote1.setQuote("We are what we repeatedly do. Excellence, therefore, is not an act but a habit");
        quote1.setQuoteCloudImageUrl("https://i2.wallpaperscraft.com/image/wood_trees_gloomy_fog_haze_darkness_50175_300x168.jpg");
        repository.addAsync(quote1, "Motivational", "Aristotle", listener);

        Quote quote2 = new Quote();
        quote2.setQuote("The best way out is always through");
        quote2.setQuoteCloudImageUrl("https://i0.wallpaperscraft.com/image/evening_sunset_tree_beautifully_81886_300x168.jpg");
        repository.addAsync(quote2, "Motivational", "Robert Frost", listener);

        Quote quote3 = new Quote();
        quote3.setQuote("Do not wait to strike till the iron is hot; but make it hot by striking");
        quote3.setQuoteCloudImageUrl("https://i0.wallpaperscraft.com/image/ng_gate_sun_tree_leaves_earth_emptiness_60174_300x168.jpg");
        repository.addAsync(quote3, "Motivational", "William B. Sprague", listener);

        Quote quote4 = new Quote();
        quote4.setQuote("Great spirits have always encountered violent opposition from mediocre minds");
        quote4.setQuoteCloudImageUrl("https://i2.wallpaperscraft.com/image/wood_black-and-white_from_below_trees_gloomy_kroner_fog_silence_60216_300x168.jpg");
        repository.addAsync(quote4, "Motivational", "Albert Einstein", listener);

        Quote quote5 = new Quote();
        quote5.setQuote("Whether you think you can or think you can’t, you’re right");
        quote5.setQuoteCloudImageUrl("https://i2.wallpaperscraft.com/image/mountains_sky_fog_clouds_tops_84363_300x168.jpg");
        repository.addAsync(quote5, "Motivational", "Henry Ford", listener);



    }

    public void addAuthors(){
        Author author1 = new Author();
        author1.setAuthorName("Aristotle");
        author1.setAuthorCloudImageUrl("https://upload.wikimedia.org/wikipedia/commons/a/ae/Aristotle_Altemps_Inv8575.jpg");
        authorRealmRepository.saveAsync(author1,listener);

        Author author2 = new Author();
        author2.setAuthorName("Robert Frost");
        author2.setAuthorCloudImageUrl("https://upload.wikimedia.org/wikipedia/commons/6/6f/MTE5NDg0MDU1MjkxOTIxOTM1.jpg");
        authorRealmRepository.saveAsync(author2,listener);

        Author author3 = new Author();
        author3.setAuthorName("William B. Sprague");
        author3.setAuthorCloudImageUrl("https://upload.wikimedia.org/wikipedia/commons/6/6d/William_Sprague_1830-1915_-_Brady-Handy-cropped-retouched.jpg");
        authorRealmRepository.saveAsync(author3,listener);

        Author author4 = new Author();
        author4.setAuthorName("Albert Einstein");
        author4.setAuthorCloudImageUrl("https://upload.wikimedia.org/wikipedia/commons/1/18/Henry_ford_1919.jpg");
        authorRealmRepository.saveAsync(author4,listener);

        Author author5 = new Author();
        author5.setAuthorName("Henry Ford");
        author5.setAuthorCloudImageUrl("https://upload.wikimedia.org/wikipedia/commons/d/d3/Albert_Einstein_Head.jpg");
        authorRealmRepository.saveAsync(author5,listener);
    }


}
