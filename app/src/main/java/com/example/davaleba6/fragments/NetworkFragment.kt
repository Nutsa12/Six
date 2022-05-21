
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zura.mysuperapp.R
import com.zura.mysuperapp.adapters.ResourceRecyclerAdapter
import com.zura.mysuperapp.api.dto.ReqResData
import com.zura.mysuperapp.api.dto.Resource
import com.zura.mysuperapp.api.dto.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NetworkFragment: Fragment(R.layout.fragment_network) {

    private lateinit var singleUserBtn: Button
    private lateinit var allUsersBtn: Button
    private lateinit var resourcesBtn: Button
    private lateinit var output: TextView
    private lateinit var recycleView: RecyclerView


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        singleUserBtn = view.findViewById(R.id.single_btn)
        allUsersBtn = view.findViewById(R.id.all_btn)
        resourcesBtn = view.findViewById(R.id.recourses_btn)
        output = view.findViewById(R.id.network_text)
        recycleView = view.findViewById(R.id.recycleView)

        allUsersBtn.setOnClickListener {
            loadAllUsers()
        }

        singleUserBtn.setOnClickListener{
            loadSingleUser()
        }

        resourcesBtn.setOnClickListener {
            loadResources()
        }
    }

    fun loadAllUsers(){
        Log.d("zura", "Starting")
        output.text = ""
        RestClient.reqResApi.getUsers(1)
            .enqueue(object : Callback<ReqResData<List<User>>> {

                override fun onResponse(
                    call: Call<ReqResData<List<User>>>,
                    response: Response<ReqResData<List<User>>>
                ) {
                    if(response.isSuccessful){
                        for (user in response.body()?.data!!){
                            Log.d("zura", user.toString())
                            output.text = output.text.toString() + "\n" + user.toString()
                        }
                    }
                }

                override fun onFailure(call: Call<ReqResData<List<User>>>, t: Throwable) {
                    Log.d("error", call.isExecuted.toString())
                    Log.d("error", t.message.toString())
                }

            })
    }

    fun loadSingleUser(){
        Log.d("zura", "Starting")
        output.text = ""
        RestClient.reqResApi.getUser(1).enqueue(object : Callback<ReqResData<User>>{
            override fun onResponse(
                call: Call<ReqResData<User>>,
                response: Response<ReqResData<User>>
            ) {
                output.text = response.body()?.data.toString()
            }

            override fun onFailure(call: Call<ReqResData<User>>, t: Throwable) {
                Log.d("error", call.isExecuted.toString())
                Log.d("error", t.message.toString())
            }
        })
    }

    fun loadResources(){
        Log.d("zura", "Starting")
        output.text = ""
        RestClient.reqResApi.getResources().enqueue(object : Callback<ReqResData<List<Resource>>>{
            override fun onResponse(
                call: Call<ReqResData<List<Resource>>>,
                response: Response<ReqResData<List<Resource>>>
            ) {
                response.body()?.data?.let {
                    recycleView.adapter = ResourceRecyclerAdapter(it)
                    recycleView.layoutManager = LinearLayoutManager(activity)
                }
            }

            override fun onFailure(call: Call<ReqResData<List<Resource>>>, t: Throwable) {
                Log.d("error", call.isExecuted.toString())
                Log.d("error", t.message.toString())
            }
        })
    }
}