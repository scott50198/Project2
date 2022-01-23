package com.hsh.project2

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.hsh.project2.model.UserDetail
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailFragment : Fragment(R.layout.fragment_detail) {
    private val apiService = AppClientManager.client.create(ApiService::class.java)


    lateinit var ivClose: ImageView
    lateinit var ivAvatar: ImageView
    lateinit var tvName: TextView
    lateinit var tvBio: TextView
    lateinit var tvLogin: TextView
    lateinit var tvStaff: TextView
    lateinit var tvLocation: TextView
    lateinit var tvBlog: TextView

    companion object {
        const val TAG = "DETAIL_FRAGMENT"
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView(view)
        getUserDetail(arguments?.get("login").toString())
    }

    private fun initView(v: View) {
        ivClose = v.findViewById(R.id.iv_close)
        ivAvatar = v.findViewById(R.id.iv_avatar)
        tvName = v.findViewById(R.id.tv_name)
        tvBio = v.findViewById(R.id.tv_bio)
        tvLogin = v.findViewById(R.id.tv_login)
        tvStaff = v.findViewById(R.id.tv_staff)
        tvLocation = v.findViewById(R.id.tv_location)
        tvBlog = v.findViewById(R.id.tv_blog)

        ivClose.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }
    }

    private fun getUserDetail(login: String?) {
        apiService.getUserDetail(login!!).enqueue(object : Callback<UserDetail> {
            override fun onResponse(call: Call<UserDetail>, response: Response<UserDetail>) {
                renewUI(response.body()!!)
            }

            override fun onFailure(call: Call<UserDetail>, t: Throwable) {
            }
        })
    }

    private fun renewUI(detail: UserDetail) {
        Picasso.with(context)
            .load(detail.avatarUrl)
            .transform(CropCircleTransformation())
            .into(ivAvatar)

        tvName.text = detail.name
        tvBio.text = detail.bio
        tvLogin.text = detail.login
        tvLocation.text = detail.location
        tvBlog.text = detail.blog
        if (detail.siteAdmin) {
            tvStaff.visibility = View.VISIBLE
        } else {
            tvStaff.visibility = View.GONE
        }
    }
}