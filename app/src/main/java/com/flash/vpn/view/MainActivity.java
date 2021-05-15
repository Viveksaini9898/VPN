package com.flash.vpn.view;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.lazycoder.vpn.R;
import com.lazycoder.vpn.adapter.ServerListRVAdapter;
import com.lazycoder.vpn.interfaces.ChangeServer;
import com.lazycoder.vpn.interfaces.NavItemClickListener;
import com.lazycoder.vpn.model.Server;

import java.util.ArrayList;

import com.lazycoder.vpn.Utils;


public class MainActivity extends AppCompatActivity implements NavItemClickListener {
    private FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
    private Fragment fragment;
    private RecyclerView serverListRv;
    private ArrayList<Server> serverLists;
    private ServerListRVAdapter serverListRVAdapter;
    private DrawerLayout drawer;
    private ChangeServer changeServer;

    public static final String TAG = "CakeVPN";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize all variable
        initializeAll();

        ImageButton menuRight = findViewById(R.id.navbar_right);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);

        menuRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeDrawer();
            }
        });

        transaction.add(R.id.container, fragment);
        transaction.commit();

        // Server List recycler view initialize
        if (serverLists != null) {
            serverListRVAdapter = new ServerListRVAdapter(serverLists, this);
            serverListRv.setAdapter(serverListRVAdapter);
        }

    }

    /**
     * Initialize all object, listener etc
     */
    private void initializeAll() {
        drawer = findViewById(R.id.drawer_layout);

        fragment = new MainFragment();
        serverListRv = findViewById(R.id.serverListRv);
        serverListRv.setHasFixedSize(true);

        serverListRv.setLayoutManager(new LinearLayoutManager(this));

        serverLists = getServerList();
        changeServer = (ChangeServer) fragment;

    }

    /**
     * Close navigation drawer
     */
    public void closeDrawer(){
        if (drawer.isDrawerOpen(GravityCompat.END)) {
            drawer.closeDrawer(GravityCompat.END);
        } else {
            drawer.openDrawer(GravityCompat.END);
        }
    }

    /**
     * Generate server array list
     */
    private ArrayList getServerList() {

        ArrayList<Server> servers = new ArrayList<>();

        servers.add(new Server("United States",
                Utils.getImgURL(R.drawable.ic_flag_united_states),
                "us.ovpn",
                "vpn",
                "vpn"
        ));
        servers.add(new Server("Japan",
                Utils.getImgURL(R.drawable.ic_flag_japan),
                "japan.ovpn",
                "vpn",
                "vpn"
        ));
        servers.add(new Server("Sweden",
                Utils.getImgURL(R.drawable.sweden),
                "sweden.ovpn",
                "freevpn4you",
                "3153931"
        ));
        servers.add(new Server("South Korea",
                Utils.getImgURL(R.drawable.ic_flag_south_korea),
                "korea.ovpn",
                "vpn",
                "vpn"
        ));

        servers.add(new Server("India",
                Utils.getImgURL(R.drawable.india),
                "india.ovpn",
                "vpn",
                "vpn"
        ));

        servers.add(new Server("Viet Nam",
                Utils.getImgURL(R.drawable.ic_flag_vietnam),
                "vietnam.ovpn",
                "vpn",
                "vpn"
        ));

        servers.add(new Server("Thailand",
                Utils.getImgURL(R.drawable.ic_flag_thailand),
                "thailand.ovpn",
                "vpn",
                "vpn"
        ));

        servers.add(new Server("Brazil",
                Utils.getImgURL(R.drawable.ic_flag_brazil),
                "brazil.ovpn",
                "vpn",
                "vpn"
        ));

        servers.add(new Server("Singapore",
                Utils.getImgURL(R.drawable.ic_flag_singapore),
                "singapore.ovpn",
                "vpn",
                "vpn"
        ));

        /*servers.add(new Server("Canada",
                Utils.getImgURL(R.drawable.ic_flag_canada),
                "canada.ovpn",
                "vpn",
                "vpn"
        ));*/

        servers.add(new Server("Russia",
                Utils.getImgURL(R.drawable.ic_flag_russia),
                "russia.ovpn",
                "freevpn4you",
                "6993750"
        ));
        return servers;

    }

    /**
     * On navigation item click, close drawer and change server
     * @param index: server index
     */
    @Override
    public void clickedItem(int index) {
        closeDrawer();
        changeServer.newServer(serverLists.get(index));
    }
}
