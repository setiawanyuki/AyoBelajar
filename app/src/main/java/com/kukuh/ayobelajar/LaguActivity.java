package com.kukuh.ayobelajar;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.kukuh.ayobelajar.Adapter.LaguAdapter;
import com.kukuh.ayobelajar.Model.Angka;
import com.kukuh.ayobelajar.Model.Lagu;

import java.util.ArrayList;

public class LaguActivity extends AppCompatActivity {

    // List view
    ListView lv;
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
                // short amount of time. -The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lagu);
        mAudioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);

        lv = findViewById(R.id.lv_lagu);
        ArrayList<Lagu> lagus = new ArrayList<>();
        LaguAdapter laguAdapter = new LaguAdapter(lagus, this);
        lv.setAdapter(laguAdapter);

        lagus.add(new Lagu("4 Sehat 5 Sempurna", R.raw.lagu_4_sehat_5_sempurna));
        lagus.add(new Lagu("Ambilkan Bulanku", R.raw.lagu_ambilkan_bulan));
        lagus.add(new Lagu("Anak Kambing Saya", R.raw.lagu_anak_kambing_saya));
        lagus.add(new Lagu("Bermain Layang-layang", R.raw.lagu_bermain_layang_layang));
        lagus.add(new Lagu("Bintang Kecil", R.raw.lagu_bintang_kecil));
        lagus.add(new Lagu("Burung Kakatua", R.raw.lagu_burung_kakatua));
        lagus.add(new Lagu("Burung Kutilang", R.raw.lagu_burung_kutilang));
        lagus.add(new Lagu("Dua Mata Saya", R.raw.lagu_dua_mata_saya));
        lagus.add(new Lagu("Kasih Ibu", R.raw.lagu_kasih_ibu));
        lagus.add(new Lagu("Kelinciku", R.raw.lagu_kelinci_ku));
        lagus.add(new Lagu("Lihat Kebunku", R.raw.lagu_lihat_kebunku));
        lagus.add(new Lagu("Naik-naik ke Puncak Gunung", R.raw.lagu_naik_naik_kepuncak_gunung));
        lagus.add(new Lagu("Nama-nama Hari", R.raw.lagu_nama_nama_hari));
        lagus.add(new Lagu("Pok Ame Ame", R.raw.lagu_pok_ame_ame));
        lagus.add(new Lagu("Potong Bebek Angsa", R.raw.lagu_potong_bebek_angsa));
        lagus.add(new Lagu("Sayonara", R.raw.lagu_sayonara));
        lagus.add(new Lagu("Selamat Ulang Tahun", R.raw.lagu_selamat_ulang_tahun));
        lagus.add(new Lagu("Sikancil", R.raw.lagu_si_kancil));
        lagus.add(new Lagu("Soleram", R.raw.lagu_soleram));
        lagus.add(new Lagu("Tik-tik Bunyi Hujan", R.raw.lagu_tik_tik_bunyi_hujan));
        lagus.add(new Lagu("Topi Saya Bundar", R.raw.lagu_topi_saya_bundar));
        lagus.add(new Lagu("Tralala Trilili", R.raw.lagu_trilili_tralala));

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Release the media player if it currently exists because we are about to
                // play a different sound file
                releaseMediaPlayer();

                // Get the {@link Word} object at the given position the user clicked on
                Lagu lagu = (Lagu) lv.getItemAtPosition(position);

                // Request audio focus so in order to play the audio file. The app needs to play a
                // short audio file, so we will request audio focus with a short amount of time
                // with AUDIOFOCUS_GAIN_TRANSIENT.
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // We have audio focus now.

                    // Create and setup the {@link MediaPlayer} for the audio resource associated
                    // with the current word
                    mMediaPlayer = MediaPlayer.create(LaguActivity.this, lagu.getLaguPutar());

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