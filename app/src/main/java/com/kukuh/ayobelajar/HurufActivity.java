package com.kukuh.ayobelajar;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.kukuh.ayobelajar.Adapter.HurufAdapter;
import com.kukuh.ayobelajar.Model.Huruf;

import java.util.ArrayList;

public class HurufActivity extends AppCompatActivity {
    GridView gvMenu;
    ArrayList<Huruf> hurufs;
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
        setContentView(R.layout.activity_huruf);
        gvMenu = findViewById(R.id.gv_huruf);
        mAudioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);

        hurufs = new ArrayList<>();
        hurufs.add(new Huruf("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/huruf_a.png?alt=media&token=27f4654f-886d-40fc-816e-198a9f0962c1", R.raw.suara_a));
        hurufs.add(new Huruf("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/huruf_b.png?alt=media&token=a7f56b79-1f1d-462b-9157-a5b4fd7f6161", R.raw.suara_b));
        hurufs.add(new Huruf("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/huruf_c.png?alt=media&token=61dc38ba-f355-4a79-8ecc-6e455af63962", R.raw.suara_c));
        hurufs.add(new Huruf("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/huruf_d.png?alt=media&token=655a3891-be16-488b-9571-1dddb91ae7b4", R.raw.suara_d));
        hurufs.add(new Huruf("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/huruf_e.png?alt=media&token=c24f90b6-c5b1-42f8-a1b4-d52ce0e74c91", R.raw.suara_e));
        hurufs.add(new Huruf("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/huruf_f.png?alt=media&token=b36f4613-ddb2-4bec-8d0c-e861cc8d0e2d", R.raw.suara_f));
        hurufs.add(new Huruf("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/huruf_g.png?alt=media&token=d1f08426-bdc5-4ae5-9858-805620c0e481", R.raw.suara_g));
        hurufs.add(new Huruf("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/huruf_h.png?alt=media&token=4b3d5d68-15cc-4d15-9096-f91724a69380", R.raw.suara_h));
        hurufs.add(new Huruf("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/huruf_i.png?alt=media&token=9dc66e1f-4ba7-4610-8ae7-2c9a036b613c", R.raw.suara_i));
        hurufs.add(new Huruf("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/huruf_j.png?alt=media&token=9841dae9-edac-418e-ae47-237fdc1aa17d", R.raw.suara_j));
        hurufs.add(new Huruf("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/huruf_k.png?alt=media&token=6e521e10-ea08-4e30-818c-fb6d6b041212", R.raw.suara_k));
        hurufs.add(new Huruf("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/huruf_l.png?alt=media&token=142d36d4-0353-40aa-a567-209760760834", R.raw.suara_l));
        hurufs.add(new Huruf("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/huruf_m.png?alt=media&token=2c051cec-cc7f-4137-8de0-c851d9057492", R.raw.suara_m));
        hurufs.add(new Huruf("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/huruf_n.png?alt=media&token=17f6f9c4-2c6c-43b5-80fb-b7caa8607a4f", R.raw.suara_n));
        hurufs.add(new Huruf("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/huruf_o.png?alt=media&token=d5a0c1df-1508-4606-b74e-bf430c895c57", R.raw.suara_o));
        hurufs.add(new Huruf("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/huruf_p.png?alt=media&token=7bf93283-a44a-4230-80a8-1ff45a5df802", R.raw.suara_p));
        hurufs.add(new Huruf("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/huruf_q.png?alt=media&token=657f8279-059a-454b-9ce6-736a78926983", R.raw.suara_q));
        hurufs.add(new Huruf("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/huruf_r.png?alt=media&token=2255b733-add8-4847-8f20-4f0c0d364c28", R.raw.suara_r));
        hurufs.add(new Huruf("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/huruf_s.png?alt=media&token=7ff9052e-d7ef-433b-99ce-0eb1cbd0c3a8", R.raw.suara_s));
        hurufs.add(new Huruf("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/huruf_t.png?alt=media&token=a7e27708-9891-4988-8128-4530cc154889", R.raw.suara_t));
        hurufs.add(new Huruf("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/huruf_u.png?alt=media&token=1a4eef02-6a84-4fb3-a292-e44ed1330ef8", R.raw.suara_u));
        hurufs.add(new Huruf("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/huruf_v.png?alt=media&token=ffccf669-e0a8-4b2e-8a2c-9d2fa4887d5f", R.raw.suara_v));
        hurufs.add(new Huruf("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/huruf_v.png?alt=media&token=ffccf669-e0a8-4b2e-8a2c-9d2fa4887d5f", R.raw.suara_w));
        hurufs.add(new Huruf("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/huruf_x.png?alt=media&token=0b3e4157-7417-4c01-9314-1213307179f9", R.raw.suara_x));
        hurufs.add(new Huruf("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/huruf_y.png?alt=media&token=7539531e-aa6f-4442-a895-f03cce718045", R.raw.suara_y));
        hurufs.add(new Huruf("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/huruf_z.png?alt=media&token=6f248af3-ba98-4815-bd2c-67c1eabcd464", R.raw.suara_z));

        HurufAdapter hurufAdapter = new HurufAdapter(hurufs, this);
        gvMenu.setAdapter(hurufAdapter);
        gvMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Release the media player if it currently exists because we are about to
                // play a different sound file
                releaseMediaPlayer();

                // Get the {@link Word} object at the given position the user clicked on
                Huruf word = hurufs.get(position);

                // Request audio focus so in order to play the audio file. The app needs to play a
                // short audio file, so we will request audio focus with a short amount of time
                // with AUDIOFOCUS_GAIN_TRANSIENT.
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // We have audio focus now.

                    // Create and setup the {@link MediaPlayer} for the audio resource associated
                    // with the current word
                    mMediaPlayer = MediaPlayer.create(HurufActivity.this, word.getLaguPutar());

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
