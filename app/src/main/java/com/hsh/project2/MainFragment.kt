package com.hsh.project2

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hsh.project2.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class MainFragment : Fragment(R.layout.fragment_main), UserRvAdapter.OnClickListener {

    lateinit var rv :RecyclerView

    private val apiService = AppClientManager.client.create(ApiService::class.java)

    private var userData = ArrayList<User>()
    private var layoutManager = LinearLayoutManager(context)

    private lateinit var rvAdapter:UserRvAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        rv = view.findViewById(R.id.rv)
        getUserList()
        initRv()
    }

    private fun getUserList(){
        apiService.getUsers().enqueue(object : Callback<ArrayList<User>> {
            override fun onResponse(
                call: Call<ArrayList<User>>,
                response: Response<ArrayList<User>>
            ) {
                userData = response.body()!!
                rvAdapter.setList(userData)
                rvAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
            }

        })
    }

    private fun initRv() {
        rvAdapter = UserRvAdapter(context, userData, this)
        rv.adapter = rvAdapter
        rv.layoutManager = layoutManager
    }

    override fun onClick(login: String) {
        val detailFragment = DetailFragment()

        val args = Bundle()
        args.putString("login", login)
        detailFragment.arguments = args

        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.main_layout, detailFragment)
            ?.addToBackStack(DetailFragment.TAG)?.commitAllowingStateLoss()

    }
}