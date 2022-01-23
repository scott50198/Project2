package com.hsh.project2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hsh.project2.model.User
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation

class UserRvAdapter(private val context: Context?, private var list: ArrayList<User>, private var listener:OnClickListener) :
    RecyclerView.Adapter<UserRvAdapter.ViewHolder>() {

    fun setList(data: ArrayList<User>) {
        this.list = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]

        Picasso.with(context)
            .load(data.avatarUrl)
            .transform(CropCircleTransformation())
            .into(holder.iv)
        holder.tvLogin.text = data.login

        if (data.siteAdmin) {
            holder.tvStaff.visibility = View.VISIBLE
        } else {
            holder.tvStaff.visibility = View.GONE
        }

        holder.itemView.setOnClickListener {
            listener.onClick(data.login!!)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnClickListener {
        fun onClick(login: String)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var iv: ImageView = itemView.findViewById(R.id.iv_avatar)
        var tvLogin: TextView = itemView.findViewById(R.id.tv_login)
        var tvStaff: TextView = itemView.findViewById(R.id.tv_staff)
    }
}