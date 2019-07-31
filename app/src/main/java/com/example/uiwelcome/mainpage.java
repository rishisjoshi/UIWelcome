package com.example.uiwelcome;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class mainpage extends AppCompatActivity {

    ListView listView;
    String mTitle[] = {" Facebook"," Whatsapp"," Twitter"," Instagram"," Youtube"};
    String mDescription[] = {"Facebook Description","Whatsapp Description","Twitter Description","Instagram Description","Youtube Description"};
    int images[] = {R.drawable.facebook, R.drawable.whatsapp,R.drawable.twitter,R.drawable.instagram,R.drawable.youtube};
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);


        listView = findViewById(R.id.listView);
        // now create an adapter class

        MyAdapter adapter = new MyAdapter(this, mTitle, mDescription, images);
        listView.setAdapter(adapter);
        // there is my mistake...
        // now again check this..

        // now set item click on list view
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position ==  0) {
                    //Toast.makeText(mainpage.this, "Facebook Description", Toast.LENGTH_SHORT).show();
                    //startActivity(new Intent(mainpage.this,SmClick.class));
                    Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.facebook.katana");
                    if(launchIntent!=null){
                        startActivity(launchIntent);
                    }

                }
                if (position ==  1) {
                    Toast.makeText(mainpage.this, "Whatsapp Description", Toast.LENGTH_SHORT).show();
                    //Uri uri = Uri.parse("smsto"+"");
                    Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.whatsapp");
                    if(launchIntent!=null){
                        startActivity(launchIntent);
                    }
                }
                if (position ==  2) {
                    //Toast.makeText(mainpage.this, "Twitter Description", Toast.LENGTH_SHORT).show();
                    Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.twitter");
                    if(launchIntent!=null){
                        startActivity(launchIntent);
                    }
                    else{
                        Toast.makeText(mainpage.this, "Twitter app not found", Toast.LENGTH_SHORT).show();

                    }
                }
                if (position ==  3) {
                    Toast.makeText(mainpage.this, "Instagram Description", Toast.LENGTH_SHORT).show();
                    Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.instagram.android");
                    if(launchIntent!=null){
                        startActivity(launchIntent);
                    }
                }
                if (position ==  4) {
                    Toast.makeText(mainpage.this, "Youtube Description", Toast.LENGTH_SHORT).show();
                    Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.google.android.youtube");
                    if(launchIntent!=null){
                        startActivity(launchIntent);
                    }
                }
            }
        });
        // so item click is done now check list view
    }

    class MyAdapter extends ArrayAdapter<String> {

        Context context;
        String rTitle[];
        String rDescription[];
        int rImgs[];

        MyAdapter (Context c, String title[], String description[], int imgs[]) {
            super(c, R.layout.row, R.id.tv1, title);
            this.context = c;
            this.rTitle = title;
            this.rDescription = description;
            this.rImgs = imgs;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row, parent, false);
            ImageView images = row.findViewById(R.id.image);
            TextView myTitle = row.findViewById(R.id.tv1);
            TextView myDescription = row.findViewById(R.id.textView2);

            // now set our resources on views
            images.setImageResource(rImgs[position]);
            //myTitle.setText("");
            myTitle.setText(rTitle[position]);
//            myDescription.setText(rDescription[position]);




            return row;
        }
    }
}
