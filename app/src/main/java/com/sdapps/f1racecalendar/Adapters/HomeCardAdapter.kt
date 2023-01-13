package com.sdapps.f1racecalendar.Adapters

import android.content.Context
import com.sdapps.f1racecalendar.Model.DriverdataBO
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

class HomeCardAdapter(private val driverDataList: List<DriverdataBO?>?, var context: Context) :
    RecyclerView.Adapter<HomeCardAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.driver_card_standings, parent, false)
        context = parent.context
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val stringBuilder = StringBuilder()
        holder.driverName.text =
            stringBuilder.append(driverDataList!![position]!!.driverName).append("\n").append(
                driverDataList[position]!!.driverNumber
            ).toString()
        holder.driverNumber.text = StringBuilder().append(driverDataList[position]!!.constructorName)
        holder.points.text =
            StringBuilder().append("Points: ").append(driverDataList[position]!!.totalPoints)
        holder.standing.text = driverDataList[position]!!.position
        try {
            if (driverDataList[position]!!.driverId.equals(
                    F1Constants.ALBON,
                    ignoreCase = true
                )
            ) holder.cardView.setCardBackgroundColor(
                context.resources.getColor(R.color.williams)
            ) else if (driverDataList[position]!!.driverId.equals(
                    F1Constants.LATIFI,
                    ignoreCase = true
                )
            ) holder.cardView.setCardBackgroundColor(
                context.resources.getColor(R.color.williams)
            ) else if (driverDataList[position]!!.driverId.equals(
                    F1Constants.NICK_DE_VRIES,
                    ignoreCase = true
                )
            ) holder.cardView.setCardBackgroundColor(
                context.resources.getColor(R.color.williams)
            ) else if (driverDataList[position]!!.driverId.equals(
                    F1Constants.ALONSO,
                    ignoreCase = true
                )
            ) holder.cardView.setCardBackgroundColor(
                context.resources.getColor(R.color.alpine)
            ) else if (driverDataList[position]!!.driverId.equals(
                    F1Constants.OCON,
                    ignoreCase = true
                )
            ) holder.cardView.setCardBackgroundColor(
                context.resources.getColor(R.color.alpine)
            ) else if (driverDataList[position]!!.driverId.equals(
                    F1Constants.BOTTAS,
                    ignoreCase = true
                )
            ) holder.cardView.setCardBackgroundColor(
                context.resources.getColor(R.color.alfa_romeo_racing)
            ) else if (driverDataList[position]!!.driverId.equals(
                    F1Constants.ZHOU,
                    ignoreCase = true
                )
            ) holder.cardView.setCardBackgroundColor(
                context.resources.getColor(R.color.alfa_romeo_racing)
            ) else if (driverDataList[position]!!.driverId.equals(
                    F1Constants.GASLY,
                    ignoreCase = true
                )
            ) holder.cardView.setCardBackgroundColor(
                context.resources.getColor(R.color.alphatauri)
            ) else if (driverDataList[position]!!.driverId.equals(
                    F1Constants.TSUNODA,
                    ignoreCase = true
                )
            ) holder.cardView.setCardBackgroundColor(
                context.resources.getColor(R.color.alphatauri)
            ) else if (driverDataList[position]!!.driverId.equals(
                    F1Constants.HAMILTON,
                    ignoreCase = true
                )
            ) holder.cardView.setCardBackgroundColor(
                context.resources.getColor(R.color.mercedes)
            ) else if (driverDataList[position]!!.driverId.equals(
                    F1Constants.RUSSELL,
                    ignoreCase = true
                )
            ) holder.cardView.setCardBackgroundColor(
                context.resources.getColor(R.color.mercedes)
            ) else if (driverDataList[position]!!.driverId.equals(
                    F1Constants.HULKENBERG,
                    ignoreCase = true
                )
            ) holder.cardView.setCardBackgroundColor(
                context.resources.getColor(R.color.aston_martin)
            ) else if (driverDataList[position]!!.driverId.equals(
                    F1Constants.STROLL,
                    ignoreCase = true
                )
            ) holder.cardView.setCardBackgroundColor(
                context.resources.getColor(R.color.aston_martin)
            ) else if (driverDataList[position]!!.driverId.equals(
                    F1Constants.VETTEL,
                    ignoreCase = true
                )
            ) holder.cardView.setCardBackgroundColor(
                context.resources.getColor(R.color.aston_martin)
            ) else if (driverDataList[position]!!.driverId.equals(
                    F1Constants.LECLERC,
                    ignoreCase = true
                )
            ) holder.cardView.setCardBackgroundColor(
                context.resources.getColor(R.color.ferrari)
            ) else if (driverDataList[position]!!.driverId.equals(
                    F1Constants.SAINZ,
                    ignoreCase = true
                )
            ) holder.cardView.setCardBackgroundColor(
                context.resources.getColor(R.color.ferrari)
            ) else if (driverDataList[position]!!.driverId.equals(
                    F1Constants.KMAG,
                    ignoreCase = true
                )
            ) {
                holder.cardView.setCardBackgroundColor(context.resources.getColor(R.color.haas))
                holder.driverName.setTextColor(context.resources.getColor(R.color.black))
                holder.driverNumber.setTextColor(context.resources.getColor(R.color.black))
                holder.points.setTextColor(context.resources.getColor(R.color.black))
                holder.standing.setTextColor(context.resources.getColor(R.color.black))
            } else if (driverDataList[position]!!.driverId.equals(
                    F1Constants.SCHUMACHER,
                    ignoreCase = true
                )
            ) {
                holder.cardView.setCardBackgroundColor(context.resources.getColor(R.color.haas))
                holder.driverName.setTextColor(context.resources.getColor(R.color.black))
                holder.driverNumber.setTextColor(context.resources.getColor(R.color.black))
                holder.points.setTextColor(context.resources.getColor(R.color.black))
                holder.standing.setTextColor(context.resources.getColor(R.color.black))
            } else if (driverDataList[position]!!.driverId.equals(
                    F1Constants.NORRIS,
                    ignoreCase = true
                )
            ) holder.cardView.setCardBackgroundColor(
                context.resources.getColor(R.color.mclaren)
            ) else if (driverDataList[position]!!.driverId.equals(
                    F1Constants.RICCIARDO,
                    ignoreCase = true
                )
            ) holder.cardView.setCardBackgroundColor(
                context.resources.getColor(R.color.mclaren)
            ) else if (driverDataList[position]!!.driverId.equals(
                    F1Constants.PEREZ,
                    ignoreCase = true
                )
            ) holder.cardView.setCardBackgroundColor(
                context.resources.getColor(R.color.redbull_racing)
            ) else if (driverDataList[position]!!.driverId.equals(
                    F1Constants.VERSTAPPEN,
                    ignoreCase = true
                )
            ) holder.cardView.setCardBackgroundColor(
                context.resources.getColor(R.color.redbull_racing)
            ) else holder.cardView.setCardBackgroundColor(
                context.resources.getColor(R.color.generic)
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getItemCount(): Int {
        return driverDataList!!.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var driverName: TextView
        var driverNumber: TextView
        var points: TextView
        var standing: TextView
        var cardView: CardView

        init {
            driverName = itemView.findViewById(R.id.driverName)
            driverNumber = itemView.findViewById(R.id.driverTeamNo)
            cardView = itemView.findViewById(R.id.cardView)
            points = itemView.findViewById(R.id.points)
            standing = itemView.findViewById(R.id.standing)
        }
    }
}