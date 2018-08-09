package com.tamrakar.uguess.bakingapp.views;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveVideoTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.tamrakar.uguess.bakingapp.R;
import com.tamrakar.uguess.bakingapp.models.RecipeStep;

public class RecipeStepDetailActivity extends AppCompatActivity {

    private static String SELECTED_RECIPE_STEP = "selected_recipe_step";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_step_detail);

        // get Recipe step as parcelable
        RecipeStep recipeStep = getIntent().getParcelableExtra(SELECTED_RECIPE_STEP);

        SimpleExoPlayerView exoPlayerViewRecipeStep = findViewById(R.id.exo_player_recipe_step);

        // Text view is only available in portrait mode
        TextView tvRecipeStepInstruction = null;
        tvRecipeStepInstruction = findViewById(R.id.tv_recipe_step_instruction);

        // get the required data
        String recipeThumbnailUrl = recipeStep.getThumbnailUrl();
        String videoUrl = recipeStep.getVideoUrl();

        if (videoUrl.isEmpty()) {
            videoUrl = recipeThumbnailUrl;
        }

        // set Recipe Step Instruction (only applicable in portrait mode)
        if (tvRecipeStepInstruction != null) {
            tvRecipeStepInstruction.setText(recipeStep.getDescription());
        }

        // Setup Simple Exo player
        TrackSelector trackSelector = new DefaultTrackSelector(
                new AdaptiveVideoTrackSelection.Factory(new DefaultBandwidthMeter()));
        SimpleExoPlayer exoPlayer = ExoPlayerFactory.newSimpleInstance(
                this, trackSelector, new DefaultLoadControl());

        // Set exo player to view
        exoPlayerViewRecipeStep.setPlayer(exoPlayer);

        // Prepare source and player
        DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(
                this,
                Util.getUserAgent(this, "Baking Time"),
                bandwidthMeter);
        MediaSource videoSource = new ExtractorMediaSource(
                Uri.parse(videoUrl),
                dataSourceFactory,
                new DefaultExtractorsFactory(),
                null,
                null);
        exoPlayer.prepare(videoSource);
    }
}
