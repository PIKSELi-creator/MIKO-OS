package com.MIKO-OS.system

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AppAdapter(
    private val appList: List<AppModel>
) : RecyclerView.Adapter<AppAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Исправленные id: соответствуют item_app.xml
        val appIcon: ImageView = itemView.findViewById(R.id.app_icon)
        val appLabel: TextView = itemView.findViewById(R.id.app_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_app, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val app = appList[position]
        holder.appLabel.text = app.label
        holder.appIcon.setImageDrawable(app.icon)

        // При клике — запуск приложения (если доступен launch intent)
        holder.itemView.setOnClickListener {
            val launchIntent = holder.itemView.context.packageManager
                .getLaunchIntentForPackage(app.packageName)
            if (launchIntent != null) {
                // на случай, если context не Activity
                launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                holder.itemView.context.startActivity(launchIntent)
            }
        }
    }

    override fun getItemCount(): Int = appList.size
}

