package annahockeyleague.com.ahl;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        new fetcher().execute();
        //WebView w = (WebView) findViewById(R.id.webViewL);
        //w.loadUrl("http://www.annahockeyleague.com");
        Log.v("ELEH", "HELLO");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            //startActivity(new Intent(this, Teams.class));
        } else if (id == R.id.nav_gallery) {
            startActivity(new Intent(this, Gallery.class));
        } else if (id == R.id.nav_teams) {
            startActivity(new Intent(this, Teams.class));
        } else if (id == R.id.nav_results) {
            startActivity(new Intent(this, Results.class));
        } else if (id == R.id.nav_about) {
            startActivity(new Intent(this, About.class));
        } else if (id == R.id.nav_rules) {
            startActivity(new Intent(this, Rules.class));
        } else if (id == R.id.nav_sponsors) {
            startActivity(new Intent(this, SponsorsActivity.class));
        } else if (id == R.id.nav_history) {
            startActivity(new Intent(this, History.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    class fetcher extends AsyncTask<Void,Void,String> {
        Document doc = null;
        Elements ele = null;
        String returner = "";

        @Override
        protected String doInBackground(Void... arg0) {
            Log.v("ELE","I am here");
            try {
                doc = Jsoup.connect("http://annahockeyleague.com").get();
                ele = doc.select("div.leaderboard");
                returner = ele.toString();
                Log.v("ELE",returner);
            } catch (IOException e) {
                e.printStackTrace();
                Log.v("ELEroR", returner);
            }

            return returner;
        }

        @Override
        protected void onPostExecute(String result) {

            String mime = "text/html";
            String encoding = "utf-8";
            WebView webView = (WebView) findViewById(R.id.webViewL);
            webView.loadData(result, mime, encoding);

        }
    }
}