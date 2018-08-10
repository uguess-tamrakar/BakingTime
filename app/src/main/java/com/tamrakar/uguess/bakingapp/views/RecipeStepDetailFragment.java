package com.tamrakar.uguess.bakingapp.views;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.tamrakar.uguess.bakingapp.adapters.RecipeStepsAdapter;
import com.tamrakar.uguess.bakingapp.models.RecipeStep;

public class RecipeStepDetailFragment extends Fragment {

    private boolean mAutoPlay;
    private int mCurrentWindowIndex;
    private long mCurrentPosition;

    private RecipeStep mRecipeStep;
    private SimpleExoPlayerView mExoPlayerView;
    private SimpleExoPlayer mExoPlayer;

    private static final String AUTO_PLAY = "auto_play";
    private static final String CURRENT_WINDOW_INDEX = "current_window_index";
    private static final String CURRENT_POSITION = "current_position";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recipe_step_detail,
                container, false);

        if (getArguments() != null) {
            mRecipeStep = getArguments().getParcelable(RecipeStepsAdapter.SELECTED_RECIPE_STEP);
        }

        mExoPlayerView = rootView.findViewById(R.id.exo_player_recipe_step);

        // Text view is only available in portrait mode
        TextView tvRecipeStepInstruction;
        tvRecipeStepInstruction = rootView.findViewById(R.id.tv_recipe_step_instruction);

        // set Recipe Step Instruction (only applicable in portrait mode)
        if (tvRecipeStepInstruction != null) {
            tvRecipeStepInstruction.setText(mRecipeStep.getDescription());
        }

        if (savedInstanceState != null) {
            mAutoPlay = savedInstanceState.getBoolean(AUTO_PLAY, false);
            mCurrentWindowIndex = savedInstanceState.getInt(CURRENT_WINDOW_INDEX, 0);
            mCurrentPosition = savedInstanceState.getLong(CURRENT_POSITION, 0);
        }

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        initializeExoPlayer();
    }

    @Override
    public void onResume() {
        super.onResume();
        initializeExoPlayer();
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseExoPlayer();
    }

    @Override
    public void onPause() {
        super.onPause();
        releaseExoPlayer();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        if (mExoPlayer != null) {
            outState.putBoolean(AUTO_PLAY, mAutoPlay);
            outState.putInt(CURRENT_WINDOW_INDEX, mCurrentWindowIndex);
            outState.putLong(CURRENT_POSITION, mCurrentPosition);
        }
    }

    private void initializeExoPlayer() {
        // get the required data
        String recipeThumbnailUrl = mRecipeStep.getThumbnailUrl();
        String videoUrl = mRecipeStep.getVideoUrl();

        if (videoUrl.isEmpty()) {
            videoUrl = recipeThumbnailUrl;
        }

        // Setup Simple Exo player
        TrackSelector trackSelector = new DefaultTrackSelector(
                new AdaptiveVideoTrackSelection.Factory(new DefaultBandwidthMeter()));
        mExoPlayer = ExoPlayerFactory.newSimpleInstance(getActivity(),
                trackSelector, new DefaultLoadControl());

        // Set exo player to view
        mExoPlayerView.setPlayer(mExoPlayer);
        mExoPlayer.setPlayWhenReady(mAutoPlay);

        mExoPlayer.seekTo(mCurrentWindowIndex, mCurrentPosition);

        // Prepare source and player
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(
                getActivity(),
                Util.getUserAgent(getActivity(), "Baking Time"),
                new DefaultBandwidthMeter());
        MediaSource videoSource = new ExtractorMediaSource(
                Uri.parse(videoUrl),
                dataSourceFactory,
                new DefaultExtractorsFactory(),
                null,
                null);
        mExoPlayer.prepare(videoSource);
    }

    private void releaseExoPlayer() {
        if (mExoPlayer != null) {
            mAutoPlay = mExoPlayer.getPlayWhenReady();
            mCurrentWindowIndex = mExoPlayer.getCurrentWindowIndex();
            mCurrentPosition = mExoPlayer.getCurrentPosition();
            mExoPlayer.release();
            mExoPlayer = null;
        }
    }
}
