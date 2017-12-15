package com.kukuh.ayobelajar;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.kukuh.ayobelajar.Adapter.BinatangAdapter;
import com.kukuh.ayobelajar.Model.Binatang;

import java.io.File;
import java.util.ArrayList;

public class BinatangActivity extends AppCompatActivity {

    GridView gvBinatang;
    ArrayList<Binatang> binatangs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StorageReference mStorageRef;
        mStorageRef = FirebaseStorage.getInstance().getReference();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binatang);

        gvBinatang = findViewById(R.id.gv_binatang);
        binatangs = new ArrayList<>();
        binatangs.add(new Binatang("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/angsa.jpg?alt=media&token=e4847b03-8780-42fc-b857-3d6eab676fa3", "Angsa", "Goose"));
        binatangs.add(new Binatang("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/anjing.jpg?alt=media&token=d4ec5036-6124-411a-ad0a-85411d73dda8", "Anjing", "Dog"));
        binatangs.add(new Binatang("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/ayam.jpg?alt=media&token=e450d539-bd65-4e02-b37c-4aa37ee4a1ea", "Ayam", "Chicken"));
        binatangs.add(new Binatang("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/babi.jpg?alt=media&token=c5ffaf6d-c8bb-4e43-aa4e-ad41ca16002e", "Babi", "Rhinoceros"));
        binatangs.add(new Binatang("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/badak.jpg?alt=media&token=f15f5f85-bb17-46c4-a719-cf246b529d99", "Badak", "Goose"));
        binatangs.add(new Binatang("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/bangau.jpg?alt=media&token=785916c9-acd7-4673-a2a3-93ff462aa376", "Bangau", "Stork"));
        binatangs.add(new Binatang("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/banteng.jpg?alt=media&token=2f6d8cc6-15b6-4649-bd23-7cf42ba9a83d", "Banteng", "Bull"));
        binatangs.add(new Binatang("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/bebek.jpg?alt=media&token=233d4821-c221-4987-8572-cfcd6631c161", "Bebek", "Duck"));
        binatangs.add(new Binatang("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/bekantan.jpg?alt=media&token=d588386a-a6d0-428d-8de4-59d97542e6ec", "Bekantan", "Bekantan"));
        binatangs.add(new Binatang("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/belalang.jpg?alt=media&token=e622b4d7-98b5-447b-ae9e-00eb1d9bd643", "Belalang", "Grasshopper"));
        binatangs.add(new Binatang("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/belut.jpg?alt=media&token=ed0b09c3-1e1f-4da4-9d0b-dd10c6e9f8db", "Belut", "Eel"));
        binatangs.add(new Binatang("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/beruang.jpg?alt=media&token=5257467c-c298-4645-9b99-938b89492eb2", "Beruang", "Bear"));
        binatangs.add(new Binatang("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/bintang_laut.jpg?alt=media&token=f009a3fe-45a1-4d99-bd41-0d46c7188fd0", "Bintang Laut", "Starfish"));
        binatangs.add(new Binatang("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/bison.jpg?alt=media&token=1fd20e42-722e-484c-84cc-03ab23bbf941", "Bison", "Bison"));
        binatangs.add(new Binatang("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/buaya.jpg?alt=media&token=a1196f74-d866-412f-9c91-e324a97bcce3", "Buaya", "Crocodile"));
        binatangs.add(new Binatang("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/bunglon.jpg?alt=media&token=ec970a8d-62fc-4afb-8939-c158011aa67d", "Bunglon", "Chameleon"));
        binatangs.add(new Binatang("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/cicak.jpg?alt=media&token=66352cce-d936-465d-bfda-592222d41069", "Cicak", "Lizard"));
        binatangs.add(new Binatang("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/cumi.jpg?alt=media&token=2eabefed-18fd-4fe8-9e9a-29946e985468", "Cumi-cumi", "Squid"));
        binatangs.add(new Binatang("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/domba.jpg?alt=media&token=364025f8-bba3-42d6-9ac9-cbc8d1b1c944", "Domba", "Sheep"));
        binatangs.add(new Binatang("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/elang.jpg?alt=media&token=433297ca-42f7-4449-83d4-03be66e4096e", "Elang", "Eagle"));
        binatangs.add(new Binatang("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/gajah.jpg?alt=media&token=4b61f8f8-a113-4df9-81f4-690944909626", "Gajah", "Elephant"));
        binatangs.add(new Binatang("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/harimau.jpg?alt=media&token=b313766b-b833-4737-a8f2-3bcf7a05c79e", "Harimau", "Tiger"));
        binatangs.add(new Binatang("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/hiu.jpg?alt=media&token=4927fbba-6fe9-49b9-8214-07f3296621c2", "Hiu", "Shark"));
        binatangs.add(new Binatang("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/jerapah.jpg?alt=media&token=53c4442c-244f-43c7-bffc-f9e9f736f6fb", "Jerapah", "Giraffe"));
        binatangs.add(new Binatang("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/kakatua.jpg?alt=media&token=f89f30ac-4c2a-4e3f-9e7d-5545ccab08c8", "Kakatua", "Cockatoo"));
        binatangs.add(new Binatang("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/kalajengking.jpg?alt=media&token=5f8ad4fc-ce41-4ec5-b7cf-a5ca213f4662", "Kalajengking", "Scorpion"));
        binatangs.add(new Binatang("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/kambing.jpg?alt=media&token=e5ed32fd-f2e1-418f-9fc0-79ffc30de85f", "Kambing", "Goat"));
        binatangs.add(new Binatang("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/kanguru.jpg?alt=media&token=2e483b0d-4f7d-42fd-aa56-a27910c01ffa", "Kanguru", "Kangaroo"));
        binatangs.add(new Binatang("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/katak.jpg?alt=media&token=787a199e-8fbf-4cf6-8c9f-53dc01cdf013", "Katak", "Frog"));
        binatangs.add(new Binatang("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/keledai.jpg?alt=media&token=4fb2479b-ad77-4d38-9c31-12e223fb6d9e", "Keledai", "Donkey"));
        binatangs.add(new Binatang("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/kelelawar.jpg?alt=media&token=6432a9c2-99aa-4af7-9fa6-a23e478afa27", "Kelelawar", "Bat"));
        binatangs.add(new Binatang("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/kelinci.png?alt=media&token=cd77df61-b4a0-489a-b80b-2834be8cf259", "Kelinci", "Rabbit"));
        binatangs.add(new Binatang("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/kepiting.jpg?alt=media&token=1b8c7302-53e3-4af8-bce3-aaff2afa6479", "Kepiting", "Crab"));
        binatangs.add(new Binatang("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/kera.jpg?alt=media&token=efec0da2-5960-4126-b44e-a272b7dae330", "Kera", "Monkey"));
        binatangs.add(new Binatang("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/kerbau.jpg?alt=media&token=096f6425-e8c0-4998-a88a-58ed2e7b069f", "Kerbau", "Buffalo"));
        binatangs.add(new Binatang("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/koala.jpg?alt=media&token=c6cea0d3-633d-4722-aab3-d5bc44ed6f58", "Koala", "Koala"));
        binatangs.add(new Binatang("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/komodo.jpg?alt=media&token=8a5aa2f1-e8aa-4f9d-bb88-5ec77443be22", "Komodo", "Komodo"));
        binatangs.add(new Binatang("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/kucing.jpg?alt=media&token=77748d9a-b3a4-42b1-9bb7-aa72197d5218", "Kucing", "Cat"));
        binatangs.add(new Binatang("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/kuda.jpg?alt=media&token=e8433789-becc-4a1e-aa0a-d29b55517490", "Kuda", "Horse"));
        binatangs.add(new Binatang("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/kudanil.jpg?alt=media&token=c97341fe-40a7-4d7c-b4ff-ab27a8127604", "Kudanil", "Hippopotamus"));
        binatangs.add(new Binatang("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/kupukupu.jpg?alt=media&token=475eb070-1c65-4636-90c4-1b063fbc91c5", "Kupu-kupu", "Butterfly"));
        binatangs.add(new Binatang("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/kurakura.jpg?alt=media&token=d7f60c21-0faa-4819-b655-74e92967ee82", "Kura-kura", "Turtle"));
        binatangs.add(new Binatang("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/labalaba.jpg?alt=media&token=c498954a-8643-4655-baf4-cb7a4826465b", "Laba-laba", "Spider"));
        binatangs.add(new Binatang("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/landak.jpg?alt=media&token=aac56fe4-3796-403c-b084-c9adc6c82d07", "Landak", "Hedgehog"));
        binatangs.add(new Binatang("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/lebah.jpg?alt=media&token=68ec23bc-4132-4434-b5d4-d15e110ef550", "Lebah", "Bee"));
        binatangs.add(new Binatang("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/lemon.jpg?alt=media&token=91402cbc-600f-4860-9812-e1ecf5fb6e99", "Lumba-lumba", "Dolphin"));
        binatangs.add(new Binatang("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/panda.jpg?alt=media&token=28a6d36b-11fa-4584-b7c8-a607456d24f0", "Panda", "Panda"));
        binatangs.add(new Binatang("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/paus.jpg?alt=media&token=18b15bfa-d0a4-4be2-b4f4-cd32accc0ccb", "Paus", "Whales"));
        binatangs.add(new Binatang("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/pinguin.jpg?alt=media&token=a0449bc0-1aa4-48ee-8199-18ca24673cc8", "Pinguin", "Penguin"));
        binatangs.add(new Binatang("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/rusa.jpg?alt=media&token=f7647f5f-9b79-4a0c-8190-b5a84fc5afb1", "Rusa", "Deer"));
        binatangs.add(new Binatang("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/sapi.jpg?alt=media&token=6d05cd2e-5bf0-456b-8906-aee9d3aee9b4", "Sapi", "Cow"));
        binatangs.add(new Binatang("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/semut.jpg?alt=media&token=7a749ebf-d6be-459c-a1c9-81485d8ddd38", "Semut", "Ant"));
        binatangs.add(new Binatang("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/singa.jpg?alt=media&token=42c69f81-9ca5-4593-8492-09def5408e8c", "Singa", "Lion"));
        binatangs.add(new Binatang("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/siput.jpg?alt=media&token=a626a5d0-9b62-45f0-9a76-ec0f8a6a77d0", "Siput", "Snail"));
        binatangs.add(new Binatang("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/tapir.jpg?alt=media&token=e2869bd0-bccd-4ac5-8a6c-14dfb17dd67a", "Tapir", "Tapir"));
        binatangs.add(new Binatang("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/tikus.jpg?alt=media&token=f2a3bf66-19fc-47a1-be12-8b75470ee0ca", "Tikus", "Mouse"));
        binatangs.add(new Binatang("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/trenggiling.jpg?alt=media&token=4a71a89b-a19d-4043-b527-a5f6ddfacc58", "Trenggiling", "Pangolin"));
        binatangs.add(new Binatang("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/ubur.jpg?alt=media&token=cfcfb771-7bc8-40e5-9d98-fbe7a8360b1e", "Ubur-ubur", "Jellyfish"));
        binatangs.add(new Binatang("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/udang.jpg?alt=media&token=5daed1cc-d0b8-419b-94b4-650760f60b37", "Udang", "Shrimp"));
        binatangs.add(new Binatang("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/ular.jpg?alt=media&token=3798509c-4ed9-42e9-aa7c-6c81ac832a61", "Ular", "Snake"));
        binatangs.add(new Binatang("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/unta.jpg?alt=media&token=a797a813-dc7d-4baf-8f99-297972f43130", "Unta", "Camel"));
        binatangs.add(new Binatang("https://firebasestorage.googleapis.com/v0/b/ayobelajar-a1e7f.appspot.com/o/zebra.jpg?alt=media&token=9dafe6c2-18fd-4a36-bf1e-4ae71db5f383", "Zebra", "Zebra"));

        BinatangAdapter binatangAdapter = new BinatangAdapter(binatangs, this);
        gvBinatang.setAdapter(binatangAdapter);
    }

}
