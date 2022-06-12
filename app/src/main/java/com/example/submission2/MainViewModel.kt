package com.example.submission2

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    private val _listUser = MutableLiveData<List<ItemUser>>()
    val listUser: LiveData<List<ItemUser>> = _listUser

    private val _users = MutableLiveData<ItemUser>()
    val users: LiveData<ItemUser> = _users

    private val _follow = MutableLiveData<ArrayList<ItemUser>>()
    val follow: LiveData<ArrayList<ItemUser>> = _follow

    private val _isLoad = MutableLiveData<Boolean>()
    val isLoad: LiveData<Boolean> = _isLoad

    private val _error = MutableLiveData<Event<String>>()
    val error: LiveData<Event<String>> = _error

    companion object {
        private const val TAG = "MyViewModel"
    }

    fun setSearchResponse(query: String) {
        _isLoad.value = true
        val client = ApiConfig.getApi().getSearchUsers(query)
        client.enqueue(object : Callback<SearchResponse> {
            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                _isLoad.value = false
                val listGit = response.body()?.items
                if (response.isSuccessful) {
                    if (listGit.isNullOrEmpty()) {
                        _error.value = Event("Not found")
                    } else {
                        _listUser.value = listGit
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                    _error.value = Event("Not found")
                }
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                _isLoad.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }

    fun setUserDetail(username: String?) {
        _isLoad.value = true
        val client = ApiConfig.getApi().getDetailUsers(username)
        client.enqueue(object : Callback<ItemUser> {
            override fun onResponse(
                call: Call<ItemUser>,
                response: Response<ItemUser>
            ) {
                _isLoad.value = false
                if (response.isSuccessful) {
                    _users.value = response.body()
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ItemUser>, t: Throwable) {
                _isLoad.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }

    fun setUserFollowers(username: String?) {
        _isLoad.value = true
        val client = ApiConfig.getApi().getUserFollowers(username)
        client.enqueue(object : Callback<ArrayList<ItemUser>> {
            override fun onResponse(
                call: Call<ArrayList<ItemUser>>,
                response: Response<ArrayList<ItemUser>>
            ) {
                _isLoad.value = false
                if (response.isSuccessful) {
                    _follow.value = response.body()
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ArrayList<ItemUser>>, t: Throwable) {
                _isLoad.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun setUserFollowing(username: String?) {
        _isLoad.value = true
        val client = ApiConfig.getApi().getUserFollowing(username)
        client.enqueue(object : Callback<ArrayList<ItemUser>> {
            override fun onResponse(
                call: Call<ArrayList<ItemUser>>,
                response: Response<ArrayList<ItemUser>>
            ) {
                _isLoad.value = false
                if (response.isSuccessful) {
                    _follow.value = response.body()
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ArrayList<ItemUser>>, t: Throwable) {
                _isLoad.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

}