package hashtagrandom.games.seppe.stakescape.gui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

import hashtagrandom.games.seppe.stakescape.R;
import hashtagrandom.games.seppe.stakescape.items.MagicLogs;
import hashtagrandom.games.seppe.stakescape.items.RangerBoots;
import hashtagrandom.games.seppe.stakescape.pojo.User;
import hashtagrandom.games.seppe.stakescape.util.ImageAdapter;

public class Inventory extends Activity {
    private User user;
    private GridView gvInventory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        user = new User();
        createTestInventory();

        System.out.println("test-------aanmaken" + user.getInventory().size());

        gvInventory = (GridView)findViewById(R.id.gv_inventory);
        gvInventory.setAdapter(new ImageAdapter(this,user));
    }

    public void createTestInventory(){
        MagicLogs logs = new MagicLogs();
        user.addItem(logs);
        user.addItem(logs);
        user.addItem(logs);
        user.addItem(logs);
        user.addItem(logs);
        user.addItem(logs);
        user.addItem(logs);
        user.addItem(logs);
        user.addItem(logs);
        user.addItem(logs);
        user.addItem(logs);

        RangerBoots boots = new RangerBoots();
        user.addItem(boots);
        user.addItem(boots);
        user.addItem(boots);
        user.addItem(boots);
        user.addItem(boots);
        user.addItem(boots);
        user.addItem(boots);
        user.addItem(boots);
        user.addItem(boots);
    }
}
