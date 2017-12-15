package com.kukuh.ayobelajar;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.kukuh.ayobelajar.Adapter.AngkaAdapter;
import com.kukuh.ayobelajar.Adapter.LaguAdapter;
import com.kukuh.ayobelajar.Model.Angka;
import com.kukuh.ayobelajar.Model.Huruf;
import com.kukuh.ayobelajar.Model.Lagu;

import java.util.ArrayList;

public class AngkaActivity extends AppCompatActivity {

    private ListView lv;
    /**
     * Handles playback of all the sound files
     */
    private MediaPlayer mMediaPlayer;

    /**
     * Handles audio focus when playing a sound file
     */
    private AudioManager mAudioManager;

    /**
     * This listener gets triggered when the {@link MediaPlayer} has completed
     * playing the audio file.
     */
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };
    /**
     * This listener gets triggered whenever the audio focus changes
     * (i.e., we gain or lose audio focus because of another app or device).
     */
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                // our app is allowed to continue playing sound but at a lower volume. We'll treat
                // both cases the same way because our app is playing short sound files.

                // Pause playback and reset player to the start of the file. That way, we can
                // play the word from the beginning when we resume playback.
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                // Stop playback and clean up resources
                releaseMediaPlayer();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_angka);

        mAudioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
        lv = findViewById(R.id.lv_angka);
        ArrayList<Angka> angkas = new ArrayList<>();
        AngkaAdapter angkaAdapter = new AngkaAdapter(angkas, this);
        lv.setAdapter(angkaAdapter);
        angkas.add(new Angka("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/angka_1.png?alt=media&token=e7cfe0fb-d917-4da8-844c-265e4f224e63", "Satu", "One", R.raw.angka_1));
        angkas.add(new Angka("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/angka_2.png?alt=media&token=fcd8c3e7-36e0-4077-b24f-2883ded1eebe", "Dua", "Two", R.raw.angka_2));
        angkas.add(new Angka("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/angka_3.png?alt=media&token=36611d17-a8cc-48d9-b6c1-f6a836bed3fb", "Tiga", "Three", R.raw.angka_3));
        angkas.add(new Angka("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/angka_4.png?alt=media&token=377a1687-26d6-4b1f-bb5f-15cb907e0143", "Empat", "Four", R.raw.angka_4));
        angkas.add(new Angka("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/angka_5.png?alt=media&token=82503bdb-3a3d-4e87-a0ad-e356beb4c90f", "Lima", "Five", R.raw.angka_5));
        angkas.add(new Angka("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/angka_6.png?alt=media&token=2962a9b0-c234-4898-a199-547b51a005c9", "Enam", "Six", R.raw.angka_6));
        angkas.add(new Angka("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/angka_7.png?alt=media&token=7972cd30-f69b-485f-8c91-3a52cdf0cfc8", "Tujuh", "Seven", R.raw.angka_7));
        angkas.add(new Angka("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/angka_8.png?alt=media&token=d41ca08b-c4f7-4b75-b2bf-9ae1dfb4568f", "Delapan", "Eight", R.raw.angka_8));
        angkas.add(new Angka("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/angka_9.png?alt=media&token=02a3ffa1-af35-4a07-99ba-deac041bc836", "Sembilan", "Nine", R.raw.angka_9));
        angkas.add(new Angka("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/angka_10.png?alt=media&token=a86d1679-ff4a-441c-bed8-da829c0b3ba9", "Sepuluh", "Ten", R.raw.angka_10));
        angkas.add(new Angka("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/angka_11.png?alt=media&token=3939eac0-787a-4bcc-b1ba-484f6e0656bf", "Sebelas", "Eleven", R.raw.angka_11));
        angkas.add(new Angka("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/angka_12.png?alt=media&token=d7ea22ae-1f74-4914-9c66-6923dccffb69", "Dua Belas", "Twelve", R.raw.angka_12));
        angkas.add(new Angka("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/angka_13.png?alt=media&token=a2091ebb-3750-49ed-b6d3-bce4ef9ad7aa", "Tiga Belas", "Thirteen", R.raw.angka_13));
        angkas.add(new Angka("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/angka_14.png?alt=media&token=7780f4d0-808a-41af-a7dd-b398a255a744", "Empat Belas", "Fourteen", R.raw.angka_14));
        angkas.add(new Angka("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/angka_15.png?alt=media&token=433174cd-8b02-4449-bf58-47ec10cb3b70", "Lima Belas", "Fifteen", R.raw.angka_15));
        angkas.add(new Angka("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/angka_16.png?alt=media&token=34e414ba-f397-4153-ace4-b65d391a9c6b", "Enam Belas", "Sixteen", R.raw.angka_16));
        angkas.add(new Angka("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/angka_17.png?alt=media&token=9fe194d4-4ef0-4d52-bd5f-1c69e20f764f", "Tujuh Belas", "Seventeen", R.raw.angka_17));
        angkas.add(new Angka("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/angka_18.png?alt=media&token=4376903d-87b8-44ee-ba60-70e481b5aef7", "Delapan Belas", "Eighteen", R.raw.angka_18));
        angkas.add(new Angka("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/angka_19.png?alt=media&token=26727a98-96fd-4200-b0ae-b2375c445f44", "Sembilan Belas", "Nineteen", R.raw.angka_19));
        angkas.add(new Angka("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/angka_20.png?alt=media&token=b693a2c6-1613-43d6-98bb-84eead0c8cb7", "Dua Puluh", "Twenty", R.raw.angka_20));

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Release the media player if it currently exists because we are about to
                // play a different sound file
                releaseMediaPlayer();

                // Get the {@link Word} object at the given position the user clicked on
                Angka angka = (Angka) lv.getItemAtPosition(position);

                // Request audio focus so in order to play the audio file. The app needs to play a
                // short audio file, so we will request audio focus with a short amount of time
                // with AUDIOFOCUS_GAIN_TRANSIENT.
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // We have audio focus now.

                    // Create and setup the {@link MediaPlayer} for the audio resource associated
                    // with the current word
                    mMediaPlayer = MediaPlayer.create(AngkaActivity.this, angka.getLaguPutar());

                    // Start the audio file
                    mMediaPlayer.start();

                    // Setup a listener on the media player, so that we can stop and release the
                    // media player once the sound has finished playing.
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        // When the activity is stopped, release the media player resources because we won't
        // be playing any more sounds.
        releaseMediaPlayer();
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;

            // Regardless of whether or not we were granted audio focus, abandon it. This also
            // unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}
