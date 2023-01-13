package com.sdapps.f1racecalendar.Adapters

import android.content.Context
import com.sdapps.f1racecalendar.Model.ConstructorBO
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.sdapps.f1racecalendar.R
import com.sdapps.f1racecalendar.Constants.F1Constants
import android.widget.TextView
import androidx.cardview.widget.CardView
import java.lang.Exception
import java.lang.StringBuilder

class ConstructorAdapter(private val consList: List<ConstructorBO>, var context: Context) :
    RecyclerView.Adapter<ConstructorAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.driver_card_standings, parent, false)
        context = parent.context
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val stringBuilder = StringBuilder()
        holder.constructorName.text = stringBuilder.append(consList[position].constructorName)
        holder.points.text = StringBuilder().append("Points: ").append(consList[position].points)
        holder.standing.text = consList[position].position
        try {
            if (consList[position].conId.equals(
                    F1Constants.ALFA_ROMEO,
                    ignoreCase = true
                )
            ) holder.cardView.setCardBackgroundColor(
                context.resources.getColor(R.color.alfa_romeo_racing)
            ) else if (consList[position].conId.equals(
                    F1Constants.ALPHATAURI,
                    ignoreCase = true
                )
            ) holder.cardView.setCardBackgroundColor(
                context.resources.getColor(R.color.alphatauri)
            ) else if (consList[position].conId.equals(
                    F1Constants.ALPINE,
                    ignoreCase = true
                )
            ) holder.cardView.setCardBackgroundColor(
                context.resources.getColor(R.color.alpine)
            ) else if (consList[position].conId.equals(
                    F1Constants.ASTON_MARTIN,
                    ignoreCase = true
                )
            ) holder.cardView.setCardBackgroundColor(
                context.resources.getColor(R.color.aston_martin)
            ) else if (consList[position].conId.equals(
                    F1Constants.FERRARI,
                    ignoreCase = true
                )
            ) holder.cardView.setCardBackgroundColor(
                context.resources.getColor(R.color.ferrari)
            ) else if (consList[position].conId.equals(
                    F1Constants.HAAS,
                    ignoreCase = true
                )
            ) holder.cardView.setCardBackgroundColor(
                context.resources.getColor(R.color.haas)
            ) else if (consList[position].conId.equals(
                    F1Constants.MCLAREN,
                    ignoreCase = true
                )
            ) holder.cardView.setCardBackgroundColor(
                context.resources.getColor(R.color.mclaren)
            ) else if (consList[position].conId.equals(
                    F1Constants.MERCEDES,
                    ignoreCase = true
                )
            ) holder.cardView.setCardBackgroundColor(
                context.resources.getColor(R.color.mercedes)
            ) else if (consList[position].conId.equals(
                    F1Constants.RED_BULL,
                    ignoreCase = true
                )
            ) holder.cardView.setCardBackgroundColor(
                context.resources.getColor(R.color.redbull_racing)
            ) else if (consList[position].conId.equals(
                    F1Constants.WILLIAMS,
                    ignoreCase = true
                )
            ) holder.cardView.setCardBackgroundColor(
                context.resources.getColor(R.color.williams)
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getItemCount(): Int {
        return 5
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var constructorName: TextView
        var points: TextView
        var standing: TextView
        var cardView: CardView

        init {
            constructorName = itemView.findViewById(R.id.driverName)
            cardView = itemView.findViewById(R.id.cardView)
            points = itemView.findViewById(R.id.points)
            standing = itemView.findViewById(R.id.standing)
        }
    }
}