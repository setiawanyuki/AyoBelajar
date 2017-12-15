package com.kukuh.ayobelajar;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.kukuh.ayobelajar.Adapter.BinatangAdapter;
import com.kukuh.ayobelajar.Adapter.BuahAdapter;
import com.kukuh.ayobelajar.Model.Binatang;
import com.kukuh.ayobelajar.Model.Buah;
import com.kukuh.ayobelajar.Model.Huruf;

import java.util.ArrayList;

public class BuahActivity extends AppCompatActivity {

    GridView gvBuah;
    ArrayList<Buah> buahs;
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
        setContentView(R.layout.activity_buah);
        gvBuah = findViewById(R.id.gv_binatang);
        mAudioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
        buahs = new ArrayList<>();
        buahs.add(new Buah("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/alpukat.jpg?alt=media&token=a869fbea-9835-479d-853d-f8be654e775f", "Alpukat", "Avocado", R.raw.buah_alpukat));
        buahs.add(new Buah("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/anggur.png?alt=media&token=d1606a5d-a50d-4200-914e-1603353e22d3", "Anggur", "Grape", R.raw.buah_anggur));
        buahs.add(new Buah("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/apel.jpg?alt=media&token=15558ecd-e1a0-4687-835f-7725d90e41d2", "Apel", "Apple", R.raw.buah_apel));
        buahs.add(new Buah("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/asam.png?alt=media&token=d56e1c23-50e4-4556-80b2-7403407bf69c", "Asam Jawa", "Tamarind", R.raw.buah_asam));
        buahs.add(new Buah("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/belimbing.jpg?alt=media&token=8b610b34-4f42-4c0e-9590-91a3f518cb1e", "Belimbing", "Star Fruit", R.raw.buah_belimbing));
        buahs.add(new Buah("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/buah_naga.jpg?alt=media&token=a13f636e-d350-486f-a1d9-3a861f947306", "Buah Naga", "Dragon Fruit", R.raw.buah_naga));
        buahs.add(new Buah("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/ceri.jpg?alt=media&token=4f5df1d7-3b5d-40f9-a11b-70aef15c8341", "Ceri", "Cherry", R.raw.buah_ceri));
        buahs.add(new Buah("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/delima.png?alt=media&token=267d4c57-abc5-4a8f-98b5-cae75087eaf8", "Delima", "Pomegranate", R.raw.buah_delima));
        buahs.add(new Buah("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/duku.png?alt=media&token=83dc1ee0-1f91-48d5-b993-f0dad4ba96fe", "Duku", "Duku", R.raw.buah_duku));
        buahs.add(new Buah("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/durian.jpg?alt=media&token=4a74aabe-0d38-4bff-957b-22d95e48da0f", "Durian", "Durian", R.raw.buah_durian));
        buahs.add(new Buah("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/jambu_air.jpg?alt=media&token=687b3603-28fa-482c-a35b-5d10492651cb", "Jambu Air", "Water Apple", R.raw.buah_jambu_air));
        buahs.add(new Buah("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/jambu_biji.png?alt=media&token=e75c285a-c2f1-4c01-882e-2114f7088364", "Jambu Biji", "Guava", R.raw.buah_jambu_biji));
        buahs.add(new Buah("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/jambu_mede.jpg?alt=media&token=eba660aa-07b6-40ad-a6ea-41e46b07b7d3", "Jambu Mete", "Cashew", R.raw.buah_jambu_mete));
        buahs.add(new Buah("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/jeruk.jpeg?alt=media&token=be0d277d-2e27-484a-9421-dac8975eb560", "Jeruk", "Orange", R.raw.buah_jeruk));
        buahs.add(new Buah("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/jeruk_nipis.jpg?alt=media&token=5b92f27c-efea-4d1c-ac49-c7019e9ff31b", "Jeruk Nipis", "Lime", R.raw.buah_jeruk_nipis));
        buahs.add(new Buah("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/kedondong.jpg?alt=media&token=3c44529d-c77a-45b1-b2a6-26ea29a75938", "Kedondong", "Kedondong", R.raw.buah_kedondong));
        buahs.add(new Buah("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/kelapa.png?alt=media&token=9d30384f-a127-455e-a4dc-fb192e89b985", "Kelapa", "Coconut", R.raw.buah_kelapa));
        buahs.add(new Buah("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/kelengkeng.jpg?alt=media&token=f4aba4ad-f3a9-4b48-af9f-2ad664bb8682", "Kelengkeng", "Longan", R.raw.buah_kelengkeng));
        buahs.add(new Buah("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/kesemek.jpg?alt=media&token=a56b62d4-a9e6-49ca-93ec-4f6bf2d08c97", "Kesemek", "Persimmon", R.raw.buah_kesemek));
        buahs.add(new Buah("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/kiwi.jpg?alt=media&token=31f28847-e28b-453d-8240-6ebc1780c38c", "Kiwi", "Kiwi", R.raw.buah_kiwi));
        buahs.add(new Buah("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/kurma.jpg?alt=media&token=dcd44fe1-0fd1-41aa-b2c0-58e80097e4aa", "Kurma", "Date", R.raw.buah_kurma));
        buahs.add(new Buah("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/leci.jpg?alt=media&token=984a9577-9178-4105-953e-729e99c53874", "Leci", "Lychee", R.raw.buah_leci));
        buahs.add(new Buah("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/lemon.jpg?alt=media&token=91402cbc-600f-4860-9812-e1ecf5fb6e99", "Lemon", "Lemon", R.raw.buah_lemon));
        buahs.add(new Buah("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/mangga.jpeg?alt=media&token=1445388b-c01f-4e48-b5bc-0acbe7793a6e", "Mangga", "Mango", R.raw.buah_mangga));
        buahs.add(new Buah("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/manggis.jpg?alt=media&token=39b72d48-dea7-4fde-a4a8-d5c9ef832c59", "Manggis", "Mangosteen", R.raw.buah_manggis));
        buahs.add(new Buah("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/markisa.jpg?alt=media&token=8fb0e36e-ff85-4a74-b8fb-cde5fac1b285", "Markisa", "Passion Fruit", R.raw.buah_markisa));
        buahs.add(new Buah("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/matoa.jpg?alt=media&token=8969a5fa-dbf9-40b6-8a6d-744ffb58819b", "Matoa", "Matoa", R.raw.buah_matoa));
        buahs.add(new Buah("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/melon.jpg?alt=media&token=1cc2e424-36b3-499f-b63d-8168befa19e4", "Melon", "Melon", R.raw.buah_melon));
        buahs.add(new Buah("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/mengkudu.jpg?alt=media&token=e27262f3-3aeb-426a-a2d9-5b8c9dcafb66", "Mengkudu", "Mengkudu", R.raw.buah_mengkudu));
        buahs.add(new Buah("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/nanas.jpg?alt=media&token=b61455c0-2574-42b7-bd20-6790103aba65", "Nanas", "Pineapple", R.raw.buah_nanas));
        buahs.add(new Buah("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/nangka.jpg?alt=media&token=64e2a0bd-3f79-47e0-bc2e-ee4e220ea882", "Nangka", "Jackfruit", R.raw.buah_nangka));
        buahs.add(new Buah("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/pepaya.jpg?alt=media&token=1e17e7b9-4b34-4169-b4f4-0665b6d65ca0", "Pepaya", "Papaya", R.raw.buah_pepaya));
        buahs.add(new Buah("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/pir.jpg?alt=media&token=a7f3aee8-b4a2-41cb-8a3b-f6b5906c5ce1", "Pir", "Pear", R.raw.buah_pir));
        buahs.add(new Buah("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/pisang.jpg?alt=media&token=d0633131-2d2c-4f88-b60a-552b0765ce9b", "Pisang", "Banana", R.raw.buah_pisang));
        buahs.add(new Buah("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/rambutan.jpg?alt=media&token=fa92a77b-dd02-4c8d-bcdd-7ef48425e735", "Rambutan", "Rambutan", R.raw.buah_rambutan));
        buahs.add(new Buah("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/salak.png?alt=media&token=812c34b7-aae9-4719-ba14-5040968315fb", "Salak", "Bark", R.raw.buah_salak));
        buahs.add(new Buah("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/sawo.jpg?alt=media&token=f6f0b758-78e4-4a27-ac68-328185ab2643", "Sawo", "Sawo", R.raw.buah_sawo));
        buahs.add(new Buah("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/semangka.jpg?alt=media&token=8f176efe-4469-45c6-8487-75b3eb3764ba", "Semangka", "Watermelon", R.raw.buah_semangka));
        buahs.add(new Buah("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/sirsak.jpg?alt=media&token=96155c8e-bd65-4b4f-be45-c95af9759a4c", "Sirsak", "Soursop", R.raw.buah_sirsak));
        buahs.add(new Buah("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/srikaya.jpg?alt=media&token=9978be6f-40e6-4975-bc5f-474a596805d5", "Srikaya", "Srikaya", R.raw.buah_srikaya));
        buahs.add(new Buah("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/stroberi.jpg?alt=media&token=86da87d0-be15-451e-869f-3762c34d8989", "Stroberi", "Strawberry", R.raw.buah_stroberi));

        BuahAdapter buahAdapter = new BuahAdapter(buahs, this);
        gvBuah.setAdapter(buahAdapter);
        gvBuah.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Release the media player if it currently exists because we are about to
                // play a different sound file
                releaseMediaPlayer();

                // Get the {@link Word} object at the given position the user clicked on
                Buah buah = buahs.get(position);

                // Request audio focus so in order to play the audio file. The app needs to play a
                // short audio file, so we will request audio focus with a short amount of time
                // with AUDIOFOCUS_GAIN_TRANSIENT.
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // We have audio focus now.

                    // Create and setup the {@link MediaPlayer} for the audio resource associated
                    // with the current word
                    mMediaPlayer = MediaPlayer.create(BuahActivity.this, buah.getLaguPutar());

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
