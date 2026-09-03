package com.MIKO-OS.system;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

		LinearLayout topStatusBar = findViewById(R.id.topStatusBar);
		ImageView dockSettings = findViewById(R.id.dockSettings);
		ImageView dockShade = findViewById(R.id.dockShade);
		RecyclerView appsRecyclerView = findViewById(R.id.appsRecyclerView);

		// Настройка сетки: 4 приложения в ряд
		appsRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));

		// Получаем все установленные приложения и передаем в адаптер
		List<AppModel> installedApps = getInstalledApps();
		AppAdapter adapter = new AppAdapter(installedApps);
		appsRecyclerView.setAdapter(adapter);

		// Открытие Cyber-HUD шторки при клике на верхний бар
		topStatusBar.setOnClickListener(v -> {
			Intent intent = new Intent(MainActivity.this, QuickSettingsActivity.class);
			startActivity(intent);
		});

		// Открытие шторки из дока
		dockShade.setOnClickListener(v -> {
			Intent intent = new Intent(MainActivity.this, QuickSettingsActivity.class);
			startActivity(intent);
		});

		// Открытие Настроек из дока
		dockSettings.setOnClickListener(v -> {
			Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
			startActivity(intent);
		});
    }

    // Сканер приложений
    private List<AppModel> getInstalledApps() {
        List<AppModel> apps = new ArrayList<>();
        PackageManager pm = getPackageManager();

        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> allApps = pm.queryIntentActivities(intent, 0);

        for (ResolveInfo ri : allApps) {
            String label = ri.loadLabel(pm).toString();
            String packageName = ri.activityInfo.packageName;
            Drawable icon = ri.loadIcon(pm);

            apps.add(new AppModel(label, packageName, icon));
        }

        return apps;
    }
}
